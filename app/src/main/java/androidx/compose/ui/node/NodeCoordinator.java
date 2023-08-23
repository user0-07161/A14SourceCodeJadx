package androidx.compose.ui.node;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LookaheadLayoutCoordinatesImpl;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class NodeCoordinator extends LookaheadCapablePlaceable implements Measurable, LayoutCoordinates, OwnerScope, Function1 {
    private static final NodeCoordinator$Companion$SemanticsSource$1 PointerInputSource;
    private static final NodeCoordinator$Companion$SemanticsSource$1 SemanticsSource;
    private MeasureResult _measureResult;
    private MutableRect _rectCache;
    private final Function0 invalidateParentLayer;
    private boolean isClipping;
    private float lastLayerAlpha;
    private boolean lastLayerDrawingWasSkipped;
    private OwnedLayer layer;
    private Function1 layerBlock;
    private Density layerDensity;
    private LayoutDirection layerLayoutDirection;
    private LayerPositionalProperties layerPositionalProperties;
    private final LayoutNode layoutNode;
    private LookaheadDelegate lookaheadDelegate;
    private Map oldAlignmentLines;
    private long position;
    private NodeCoordinator wrapped;
    private NodeCoordinator wrappedBy;
    private float zIndex;
    private static final Function1 onCommitAffectingLayerParams = new Function1() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$onCommitAffectingLayerParams$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayerPositionalProperties layerPositionalProperties;
            LayerPositionalProperties layerPositionalProperties2;
            LayerPositionalProperties layerPositionalProperties3;
            NodeCoordinator coordinator = (NodeCoordinator) obj;
            Intrinsics.checkNotNullParameter(coordinator, "coordinator");
            if (coordinator.isValidOwnerScope()) {
                layerPositionalProperties = coordinator.layerPositionalProperties;
                if (layerPositionalProperties == null) {
                    coordinator.updateLayerParameters();
                } else {
                    layerPositionalProperties2 = NodeCoordinator.tmpLayerPositionalProperties;
                    layerPositionalProperties2.copyFrom(layerPositionalProperties);
                    coordinator.updateLayerParameters();
                    layerPositionalProperties3 = NodeCoordinator.tmpLayerPositionalProperties;
                    if (!layerPositionalProperties3.hasSameValuesAs(layerPositionalProperties)) {
                        LayoutNode layoutNode = coordinator.getLayoutNode();
                        LayoutNodeLayoutDelegate layoutDelegate$ui_release = layoutNode.getLayoutDelegate$ui_release();
                        if (layoutDelegate$ui_release.getChildrenAccessingCoordinatesDuringPlacement() > 0) {
                            if (layoutDelegate$ui_release.getCoordinatesAccessedDuringPlacement()) {
                                layoutNode.requestRelayout$ui_release(false);
                            }
                            layoutDelegate$ui_release.getMeasurePassDelegate$ui_release().notifyChildrenUsingCoordinatesWhilePlacing();
                        }
                        Owner owner$ui_release = layoutNode.getOwner$ui_release();
                        if (owner$ui_release != null) {
                            ((AndroidComposeView) owner$ui_release).requestOnPositionedCallback(layoutNode);
                        }
                    }
                }
            }
            return Unit.INSTANCE;
        }
    };
    private static final Function1 onCommitAffectingLayer = new Function1() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$onCommitAffectingLayer$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            NodeCoordinator coordinator = (NodeCoordinator) obj;
            Intrinsics.checkNotNullParameter(coordinator, "coordinator");
            OwnedLayer layer = coordinator.getLayer();
            if (layer != null) {
                layer.invalidate();
            }
            return Unit.INSTANCE;
        }
    };
    private static final ReusableGraphicsLayerScope graphicsLayerScope = new ReusableGraphicsLayerScope();
    private static final LayerPositionalProperties tmpLayerPositionalProperties = new LayerPositionalProperties();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface HitTestSource {
    }

    static {
        Matrix.m104constructorimpl$default();
        PointerInputSource = new NodeCoordinator$Companion$SemanticsSource$1(1);
        SemanticsSource = new NodeCoordinator$Companion$SemanticsSource$1(0);
    }

    public NodeCoordinator(LayoutNode layoutNode) {
        long j;
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.layoutNode = layoutNode;
        this.layerDensity = layoutNode.getDensity();
        this.layerLayoutDirection = layoutNode.getLayoutDirection();
        this.lastLayerAlpha = 0.8f;
        IntOffset.Companion companion = IntOffset.Companion;
        j = IntOffset.Zero;
        this.position = j;
        this.invalidateParentLayer = new NodeCoordinator$invalidateParentLayer$1(this);
    }

    private final void ancestorToLocal(NodeCoordinator nodeCoordinator, MutableRect mutableRect, boolean z) {
        if (nodeCoordinator == this) {
            return;
        }
        NodeCoordinator nodeCoordinator2 = this.wrappedBy;
        if (nodeCoordinator2 != null) {
            nodeCoordinator2.ancestorToLocal(nodeCoordinator, mutableRect, z);
        }
        long j = this.position;
        IntOffset.Companion companion = IntOffset.Companion;
        float f = (int) (j >> 32);
        mutableRect.setLeft(mutableRect.getLeft() - f);
        mutableRect.setRight(mutableRect.getRight() - f);
        float m402getYimpl = IntOffset.m402getYimpl(this.position);
        mutableRect.setTop(mutableRect.getTop() - m402getYimpl);
        mutableRect.setBottom(mutableRect.getBottom() - m402getYimpl);
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mapBounds(mutableRect, true);
            if (this.isClipping && z) {
                mutableRect.intersect(0.0f, 0.0f, (int) (m213getMeasuredSizeYbymL2g() >> 32), IntSize.m405getHeightimpl(m213getMeasuredSizeYbymL2g()));
            }
        }
    }

    /* renamed from: ancestorToLocal-R5De75A */
    private final long m253ancestorToLocalR5De75A(NodeCoordinator nodeCoordinator, long j) {
        if (nodeCoordinator == this) {
            return j;
        }
        NodeCoordinator nodeCoordinator2 = this.wrappedBy;
        if (nodeCoordinator2 != null && !Intrinsics.areEqual(nodeCoordinator, nodeCoordinator2)) {
            return m259fromParentPositionMKHz9U(nodeCoordinator2.m253ancestorToLocalR5De75A(nodeCoordinator, j));
        }
        return m259fromParentPositionMKHz9U(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v3 */
    public final void drawContainedDrawModifiers(Canvas canvas) {
        boolean m268getIncludeSelfInTraversalH91voCI = NodeKindKt.m268getIncludeSelfInTraversalH91voCI(4);
        Modifier.Node tail = getTail();
        DrawModifierNode drawModifierNode = null;
        if (m268getIncludeSelfInTraversalH91voCI || (tail = tail.getParent$ui_release()) != null) {
            Modifier.Node headNode = headNode(m268getIncludeSelfInTraversalH91voCI);
            while (true) {
                if (headNode != null && (headNode.getAggregateChildKindSet$ui_release() & 4) != 0) {
                    if ((headNode.getKindSet$ui_release() & 4) != 0) {
                        if (headNode instanceof DrawModifierNode) {
                            drawModifierNode = headNode;
                        }
                        drawModifierNode = drawModifierNode;
                    } else if (headNode == tail) {
                        break;
                    } else {
                        headNode = headNode.getChild$ui_release();
                    }
                } else {
                    break;
                }
            }
        }
        DrawModifierNode drawModifierNode2 = drawModifierNode;
        if (drawModifierNode2 == null) {
            performDraw(canvas);
            return;
        }
        LayoutNode layoutNode = this.layoutNode;
        layoutNode.getClass();
        ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).getSharedDrawScope().m234drawx_KDEd0$ui_release(canvas, IntSizeKt.m408toSizeozmzZPI(m213getMeasuredSizeYbymL2g()), this, drawModifierNode2);
    }

    public final Modifier.Node headNode(boolean z) {
        Modifier.Node tail;
        LayoutNode layoutNode = this.layoutNode;
        if (layoutNode.getOuterCoordinator$ui_release() == this) {
            return layoutNode.getNodes$ui_release().getHead$ui_release();
        }
        if (z) {
            NodeCoordinator nodeCoordinator = this.wrappedBy;
            if (nodeCoordinator != null && (tail = nodeCoordinator.getTail()) != null) {
                return tail.getChild$ui_release();
            }
        } else {
            NodeCoordinator nodeCoordinator2 = this.wrappedBy;
            if (nodeCoordinator2 != null) {
                return nodeCoordinator2.getTail();
            }
        }
        return null;
    }

    /* renamed from: hit-1hIXUjU */
    public final void m254hit1hIXUjU(final DelegatableNode delegatableNode, final HitTestSource hitTestSource, final long j, final HitTestResult hitTestResult, final boolean z, final boolean z2) {
        if (delegatableNode == null) {
            mo228hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
            return;
        }
        Function0 function0 = new Function0() { // from class: androidx.compose.ui.node.NodeCoordinator$hit$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                NodeCoordinator.this.m254hit1hIXUjU(NodeCoordinatorKt.m267access$nextUncheckedUntilhw7D004(delegatableNode, ((NodeCoordinator$Companion$SemanticsSource$1) hitTestSource).m266entityTypeOLwlOKw()), hitTestSource, j, hitTestResult, z, z2);
                return Unit.INSTANCE;
            }
        };
        hitTestResult.getClass();
        hitTestResult.hitInMinimumTouchTarget(delegatableNode, -1.0f, z2, function0);
    }

    /* renamed from: hitNear-JHbHoSQ */
    public final void m255hitNearJHbHoSQ(final DelegatableNode delegatableNode, final HitTestSource hitTestSource, final long j, final HitTestResult hitTestResult, final boolean z, final boolean z2, final float f) {
        if (delegatableNode == null) {
            mo228hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
        } else {
            hitTestResult.hitInMinimumTouchTarget(delegatableNode, f, z2, new Function0() { // from class: androidx.compose.ui.node.NodeCoordinator$hitNear$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    NodeCoordinator.this.m255hitNearJHbHoSQ(NodeCoordinatorKt.m267access$nextUncheckedUntilhw7D004(delegatableNode, ((NodeCoordinator$Companion$SemanticsSource$1) hitTestSource).m266entityTypeOLwlOKw()), hitTestSource, j, hitTestResult, z, z2, f);
                    return Unit.INSTANCE;
                }
            });
        }
    }

    private final void onLayerBlockUpdated(Function1 function1, boolean z) {
        boolean z2;
        Owner owner$ui_release;
        Function1 function12 = this.layerBlock;
        LayoutNode layoutNode = this.layoutNode;
        if (function12 == function1 && Intrinsics.areEqual(this.layerDensity, layoutNode.getDensity()) && this.layerLayoutDirection == layoutNode.getLayoutDirection() && !z) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.layerBlock = function1;
        this.layerDensity = layoutNode.getDensity();
        this.layerLayoutDirection = layoutNode.getLayoutDirection();
        boolean isAttached = isAttached();
        Function0 function0 = this.invalidateParentLayer;
        if (isAttached && function1 != null) {
            if (this.layer == null) {
                OwnedLayer createLayer = ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).createLayer(function0, this);
                createLayer.mo272resizeozmzZPI(m213getMeasuredSizeYbymL2g());
                createLayer.mo271movegyyYBs(this.position);
                this.layer = createLayer;
                updateLayerParameters();
                layoutNode.setInnerLayerCoordinatorIsDirty$ui_release();
                ((NodeCoordinator$invalidateParentLayer$1) function0).invoke();
                return;
            } else if (z2) {
                updateLayerParameters();
                return;
            } else {
                return;
            }
        }
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.destroy();
            layoutNode.setInnerLayerCoordinatorIsDirty$ui_release();
            ((NodeCoordinator$invalidateParentLayer$1) function0).invoke();
            if (isAttached() && (owner$ui_release = layoutNode.getOwner$ui_release()) != null) {
                ((AndroidComposeView) owner$ui_release).onLayoutChange(layoutNode);
            }
        }
        this.layer = null;
        this.lastLayerDrawingWasSkipped = false;
    }

    /* renamed from: speculativeHit-JHbHoSQ */
    private final void m256speculativeHitJHbHoSQ(DelegatableNode delegatableNode, HitTestSource hitTestSource, long j, HitTestResult hitTestResult, boolean z, boolean z2, float f) {
        if (delegatableNode == null) {
            mo228hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
            return;
        }
        NodeCoordinator$Companion$SemanticsSource$1 nodeCoordinator$Companion$SemanticsSource$1 = (NodeCoordinator$Companion$SemanticsSource$1) hitTestSource;
        switch (nodeCoordinator$Companion$SemanticsSource$1.$r8$classId) {
            case 0:
                SemanticsModifierNode semanticsModifierNode = (SemanticsModifierNode) delegatableNode;
                break;
            default:
                ((BackwardsCompatNode) ((PointerInputModifierNode) delegatableNode)).interceptOutOfBoundsChildEvents();
                break;
        }
        m256speculativeHitJHbHoSQ(NodeCoordinatorKt.m267access$nextUncheckedUntilhw7D004(delegatableNode, nodeCoordinator$Companion$SemanticsSource$1.m266entityTypeOLwlOKw()), hitTestSource, j, hitTestResult, z, z2, f);
    }

    public final void updateLayerParameters() {
        LayoutNode layoutNode;
        ReusableGraphicsLayerScope reusableGraphicsLayerScope;
        boolean z;
        OwnedLayer ownedLayer = this.layer;
        ReusableGraphicsLayerScope reusableGraphicsLayerScope2 = graphicsLayerScope;
        LayoutNode layoutNode2 = this.layoutNode;
        if (ownedLayer != null) {
            final Function1 function1 = this.layerBlock;
            if (function1 != null) {
                reusableGraphicsLayerScope2.reset();
                reusableGraphicsLayerScope2.setGraphicsDensity$ui_release(layoutNode2.getDensity());
                IntSizeKt.m408toSizeozmzZPI(m213getMeasuredSizeYbymL2g());
                ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode2)).getSnapshotObserver().observeReads$ui_release(this, onCommitAffectingLayerParams, new Function0() { // from class: androidx.compose.ui.node.NodeCoordinator$updateLayerParameters$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        ReusableGraphicsLayerScope reusableGraphicsLayerScope3;
                        Function1 function12 = Function1.this;
                        reusableGraphicsLayerScope3 = NodeCoordinator.graphicsLayerScope;
                        function12.invoke(reusableGraphicsLayerScope3);
                        return Unit.INSTANCE;
                    }
                });
                LayerPositionalProperties layerPositionalProperties = this.layerPositionalProperties;
                if (layerPositionalProperties == null) {
                    layerPositionalProperties = new LayerPositionalProperties();
                    this.layerPositionalProperties = layerPositionalProperties;
                }
                layerPositionalProperties.copyFrom(reusableGraphicsLayerScope2);
                layoutNode = layoutNode2;
                reusableGraphicsLayerScope = reusableGraphicsLayerScope2;
                ownedLayer.mo273updateLayerPropertiesdDxrwY(reusableGraphicsLayerScope2.getScaleX(), reusableGraphicsLayerScope2.getScaleY(), reusableGraphicsLayerScope2.getAlpha(), reusableGraphicsLayerScope2.getTranslationX(), reusableGraphicsLayerScope2.getTranslationY(), reusableGraphicsLayerScope2.getShadowElevation(), reusableGraphicsLayerScope2.getRotationX(), reusableGraphicsLayerScope2.getRotationY(), reusableGraphicsLayerScope2.getRotationZ(), reusableGraphicsLayerScope2.getCameraDistance(), reusableGraphicsLayerScope2.m110getTransformOriginSzJe1aQ(), reusableGraphicsLayerScope2.getShape(), reusableGraphicsLayerScope2.getClip(), reusableGraphicsLayerScope2.m107getAmbientShadowColor0d7_KjU(), reusableGraphicsLayerScope2.m109getSpotShadowColor0d7_KjU(), reusableGraphicsLayerScope.m108getCompositingStrategyNrFUSI(), layoutNode.getLayoutDirection(), layoutNode.getDensity());
                this.isClipping = reusableGraphicsLayerScope.getClip();
            } else {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
        } else {
            layoutNode = layoutNode2;
            reusableGraphicsLayerScope = reusableGraphicsLayerScope2;
            if (this.layerBlock == null) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
        this.lastLayerAlpha = reusableGraphicsLayerScope.getAlpha();
        Owner owner$ui_release = layoutNode.getOwner$ui_release();
        if (owner$ui_release != null) {
            ((AndroidComposeView) owner$ui_release).onLayoutChange(layoutNode);
        }
    }

    public final void attach() {
        onLayerBlockUpdated(this.layerBlock, false);
    }

    /* renamed from: calculateMinimumTouchTargetPadding-E7KxVPU */
    protected final long m257calculateMinimumTouchTargetPaddingE7KxVPU(long j) {
        return SizeKt.Size(Math.max(0.0f, (Size.m64getWidthimpl(j) - getMeasuredWidth()) / 2.0f), Math.max(0.0f, (Size.m63getHeightimpl(j) - getMeasuredHeight()) / 2.0f));
    }

    public final void detach() {
        if (this.layer != null) {
            onLayerBlockUpdated(null, false);
        }
    }

    /* renamed from: distanceInMinimumTouchTarget-tz77jQw */
    public final float m258distanceInMinimumTouchTargettz77jQw(long j, long j2) {
        float measuredWidth;
        float measuredHeight;
        if (getMeasuredWidth() >= Size.m64getWidthimpl(j2) && getMeasuredHeight() >= Size.m63getHeightimpl(j2)) {
            return Float.POSITIVE_INFINITY;
        }
        long m257calculateMinimumTouchTargetPaddingE7KxVPU = m257calculateMinimumTouchTargetPaddingE7KxVPU(j2);
        float m64getWidthimpl = Size.m64getWidthimpl(m257calculateMinimumTouchTargetPaddingE7KxVPU);
        float m63getHeightimpl = Size.m63getHeightimpl(m257calculateMinimumTouchTargetPaddingE7KxVPU);
        float m45getXimpl = Offset.m45getXimpl(j);
        if (m45getXimpl < 0.0f) {
            measuredWidth = -m45getXimpl;
        } else {
            measuredWidth = m45getXimpl - getMeasuredWidth();
        }
        float max = Math.max(0.0f, measuredWidth);
        float m46getYimpl = Offset.m46getYimpl(j);
        if (m46getYimpl < 0.0f) {
            measuredHeight = -m46getYimpl;
        } else {
            measuredHeight = m46getYimpl - getMeasuredHeight();
        }
        long Offset = OffsetKt.Offset(max, Math.max(0.0f, measuredHeight));
        if ((m64getWidthimpl <= 0.0f && m63getHeightimpl <= 0.0f) || Offset.m45getXimpl(Offset) > m64getWidthimpl || Offset.m46getYimpl(Offset) > m63getHeightimpl) {
            return Float.POSITIVE_INFINITY;
        }
        return (Offset.m46getYimpl(Offset) * Offset.m46getYimpl(Offset)) + (Offset.m45getXimpl(Offset) * Offset.m45getXimpl(Offset));
    }

    public final void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.drawLayer(canvas);
            return;
        }
        long j = this.position;
        float f = (int) (j >> 32);
        float m402getYimpl = IntOffset.m402getYimpl(j);
        canvas.translate(f, m402getYimpl);
        drawContainedDrawModifiers(canvas);
        canvas.translate(-f, -m402getYimpl);
    }

    public final void drawBorder(Canvas canvas, AndroidPaint paint) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(paint, "paint");
        Rect rect = new Rect(0.5f, 0.5f, ((int) (m213getMeasuredSizeYbymL2g() >> 32)) - 0.5f, IntSize.m405getHeightimpl(m213getMeasuredSizeYbymL2g()) - 0.5f);
        canvas.drawRect(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), paint);
    }

    public final NodeCoordinator findCommonAncestor$ui_release(NodeCoordinator nodeCoordinator) {
        LayoutNode layoutNode = this.layoutNode;
        LayoutNode layoutNode2 = nodeCoordinator.layoutNode;
        if (layoutNode2 == layoutNode) {
            Modifier.Node tail = nodeCoordinator.getTail();
            Modifier.Node tail2 = getTail();
            if (tail2.getNode().isAttached()) {
                for (Modifier.Node parent$ui_release = tail2.getNode().getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
                    if ((parent$ui_release.getKindSet$ui_release() & 2) != 0 && parent$ui_release == tail) {
                        return nodeCoordinator;
                    }
                }
                return this;
            }
            throw new IllegalStateException("Check failed.".toString());
        }
        LayoutNode layoutNode3 = layoutNode2;
        while (layoutNode3.getDepth$ui_release() > layoutNode.getDepth$ui_release()) {
            layoutNode3 = layoutNode3.getParent$ui_release();
            Intrinsics.checkNotNull(layoutNode3);
        }
        LayoutNode layoutNode4 = layoutNode;
        while (layoutNode4.getDepth$ui_release() > layoutNode3.getDepth$ui_release()) {
            layoutNode4 = layoutNode4.getParent$ui_release();
            Intrinsics.checkNotNull(layoutNode4);
        }
        while (layoutNode3 != layoutNode4) {
            layoutNode3 = layoutNode3.getParent$ui_release();
            layoutNode4 = layoutNode4.getParent$ui_release();
            if (layoutNode3 != null) {
                if (layoutNode4 == null) {
                }
            }
            throw new IllegalArgumentException("layouts are not part of the same hierarchy");
        }
        if (layoutNode4 != layoutNode) {
            if (layoutNode3 == layoutNode2) {
                return nodeCoordinator;
            }
            return layoutNode3.getInnerCoordinator$ui_release();
        }
        return this;
    }

    /* renamed from: fromParentPosition-MK-Hz9U */
    public final long m259fromParentPositionMKHz9U(long j) {
        long j2 = this.position;
        float m45getXimpl = Offset.m45getXimpl(j);
        IntOffset.Companion companion = IntOffset.Companion;
        long Offset = OffsetKt.Offset(m45getXimpl - ((int) (j2 >> 32)), Offset.m46getYimpl(j) - IntOffset.m402getYimpl(j2));
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            return ownedLayer.mo270mapOffset8S9VItk(Offset, true);
        }
        return Offset;
    }

    public final AlignmentLinesOwner getAlignmentLinesOwner() {
        return this.layoutNode.getLayoutDelegate$ui_release().getAlignmentLinesOwner$ui_release();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LookaheadCapablePlaceable getChild() {
        return this.wrapped;
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.layoutNode.getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getFontScale() {
        return this.layoutNode.getDensity().getFontScale();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final boolean getHasMeasureResult() {
        if (this._measureResult != null) {
            return true;
        }
        return false;
    }

    public final boolean getLastLayerDrawingWasSkipped$ui_release() {
        return this.lastLayerDrawingWasSkipped;
    }

    public final OwnedLayer getLayer() {
        return this.layer;
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public final LayoutDirection getLayoutDirection() {
        return this.layoutNode.getLayoutDirection();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public final LookaheadDelegate getLookaheadDelegate$ui_release() {
        return this.lookaheadDelegate;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final MeasureResult getMeasureResult$ui_release() {
        MeasureResult measureResult = this._measureResult;
        if (measureResult != null) {
            return measureResult;
        }
        throw new IllegalStateException("Asking for measurement result of unmeasured layout modifier".toString());
    }

    /* renamed from: getMinimumTouchTargetSize-NH-jbRc */
    public final long m260getMinimumTouchTargetSizeNHjbRc() {
        return this.layerDensity.mo207toSizeXkaWNTQ(this.layoutNode.getViewConfiguration().mo233getMinimumTouchTargetSizeMYxV2XQ());
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LookaheadCapablePlaceable getParent() {
        return this.wrappedBy;
    }

    @Override // androidx.compose.ui.layout.Measurable
    public final Object getParentData() {
        boolean z;
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        Modifier.Node tail = getTail();
        LayoutNode layoutNode = this.layoutNode;
        if (layoutNode.getNodes$ui_release().m250hasH91voCI$ui_release(64)) {
            Density density = layoutNode.getDensity();
            for (Modifier.Node tail$ui_release = layoutNode.getNodes$ui_release().getTail$ui_release(); tail$ui_release != null; tail$ui_release = tail$ui_release.getParent$ui_release()) {
                if (tail$ui_release != tail) {
                    if ((tail$ui_release.getKindSet$ui_release() & 64) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z && (tail$ui_release instanceof ParentDataModifierNode)) {
                        ref$ObjectRef.element = ((BackwardsCompatNode) ((ParentDataModifierNode) tail$ui_release)).modifyParentData(density, ref$ObjectRef.element);
                    }
                }
            }
        }
        return ref$ObjectRef.element;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final NodeCoordinator getParentLayoutCoordinates() {
        if (isAttached()) {
            return this.layoutNode.getOuterCoordinator$ui_release().wrappedBy;
        }
        throw new IllegalStateException("LayoutCoordinate operations are only valid when isAttached is true".toString());
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* renamed from: getPosition-nOcc-ac */
    public final long mo246getPositionnOccac() {
        return this.position;
    }

    public abstract Modifier.Node getTail();

    public final NodeCoordinator getWrapped$ui_release() {
        return this.wrapped;
    }

    public final NodeCoordinator getWrappedBy$ui_release() {
        return this.wrappedBy;
    }

    public final float getZIndex() {
        return this.zIndex;
    }

    /* renamed from: hitTest-YqVAtuI */
    public final void m261hitTestYqVAtuI(HitTestSource hitTestSource, long j, HitTestResult hitTestResult, boolean z, boolean z2) {
        Modifier.Node node;
        boolean z3;
        float m258distanceInMinimumTouchTargettz77jQw;
        Intrinsics.checkNotNullParameter(hitTestSource, "hitTestSource");
        Intrinsics.checkNotNullParameter(hitTestResult, "hitTestResult");
        int m266entityTypeOLwlOKw = ((NodeCoordinator$Companion$SemanticsSource$1) hitTestSource).m266entityTypeOLwlOKw();
        boolean m268getIncludeSelfInTraversalH91voCI = NodeKindKt.m268getIncludeSelfInTraversalH91voCI(m266entityTypeOLwlOKw);
        Modifier.Node tail = getTail();
        if (m268getIncludeSelfInTraversalH91voCI || (tail = tail.getParent$ui_release()) != null) {
            for (Modifier.Node headNode = headNode(m268getIncludeSelfInTraversalH91voCI); headNode != null && (headNode.getAggregateChildKindSet$ui_release() & m266entityTypeOLwlOKw) != 0; headNode = headNode.getChild$ui_release()) {
                if ((headNode.getKindSet$ui_release() & m266entityTypeOLwlOKw) != 0) {
                    node = headNode;
                    break;
                } else if (headNode == tail) {
                    break;
                }
            }
        }
        node = null;
        boolean z4 = true;
        if (!m265withinLayerBoundsk4lQ0M(j)) {
            if (z) {
                float m258distanceInMinimumTouchTargettz77jQw2 = m258distanceInMinimumTouchTargettz77jQw(j, m260getMinimumTouchTargetSizeNHjbRc());
                if (((Float.isInfinite(m258distanceInMinimumTouchTargettz77jQw2) || Float.isNaN(m258distanceInMinimumTouchTargettz77jQw2)) ? false : false) && hitTestResult.isHitInMinimumTouchTargetBetter(m258distanceInMinimumTouchTargettz77jQw2, false)) {
                    m255hitNearJHbHoSQ(node, hitTestSource, j, hitTestResult, z, false, m258distanceInMinimumTouchTargettz77jQw2);
                }
            }
        } else if (node == null) {
            mo228hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
        } else {
            float m45getXimpl = Offset.m45getXimpl(j);
            float m46getYimpl = Offset.m46getYimpl(j);
            if (m45getXimpl >= 0.0f && m46getYimpl >= 0.0f && m45getXimpl < getMeasuredWidth() && m46getYimpl < getMeasuredHeight()) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                m254hit1hIXUjU(node, hitTestSource, j, hitTestResult, z, z2);
                return;
            }
            if (!z) {
                m258distanceInMinimumTouchTargettz77jQw = Float.POSITIVE_INFINITY;
            } else {
                m258distanceInMinimumTouchTargettz77jQw = m258distanceInMinimumTouchTargettz77jQw(j, m260getMinimumTouchTargetSizeNHjbRc());
            }
            float f = m258distanceInMinimumTouchTargettz77jQw;
            if (((Float.isInfinite(f) || Float.isNaN(f)) ? false : false) && hitTestResult.isHitInMinimumTouchTargetBetter(f, z2)) {
                m255hitNearJHbHoSQ(node, hitTestSource, j, hitTestResult, z, z2, f);
                return;
            }
            m256speculativeHitJHbHoSQ(node, hitTestSource, j, hitTestResult, z, z2, f);
        }
    }

    /* renamed from: hitTestChild-YqVAtuI */
    public void mo228hitTestChildYqVAtuI(HitTestSource hitTestSource, long j, HitTestResult hitTestResult, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(hitTestSource, "hitTestSource");
        Intrinsics.checkNotNullParameter(hitTestResult, "hitTestResult");
        NodeCoordinator nodeCoordinator = this.wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.m261hitTestYqVAtuI(hitTestSource, nodeCoordinator.m259fromParentPositionMKHz9U(j), hitTestResult, z, z2);
        }
    }

    public final void invalidateLayer() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.invalidateLayer();
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        final Canvas canvas = (Canvas) obj;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        LayoutNode layoutNode = this.layoutNode;
        if (layoutNode.isPlaced()) {
            ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).getSnapshotObserver().observeReads$ui_release(this, onCommitAffectingLayer, new Function0() { // from class: androidx.compose.ui.node.NodeCoordinator$invoke$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    NodeCoordinator.this.drawContainedDrawModifiers(canvas);
                    return Unit.INSTANCE;
                }
            });
            this.lastLayerDrawingWasSkipped = false;
        } else {
            this.lastLayerDrawingWasSkipped = true;
        }
        return Unit.INSTANCE;
    }

    public final boolean isAttached() {
        return getTail().isAttached();
    }

    public final boolean isTransparent() {
        if (this.layer != null && this.lastLayerAlpha <= 0.0f) {
            return true;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            return nodeCoordinator.isTransparent();
        }
        return false;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        if (this.layer != null && isAttached()) {
            return true;
        }
        return false;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final Rect localBoundingBoxOf(NodeCoordinator sourceCoordinates, boolean z) {
        LookaheadLayoutCoordinatesImpl lookaheadLayoutCoordinatesImpl;
        NodeCoordinator nodeCoordinator;
        Rect rect;
        Intrinsics.checkNotNullParameter(sourceCoordinates, "sourceCoordinates");
        if (isAttached()) {
            if (sourceCoordinates.isAttached()) {
                if (sourceCoordinates instanceof LookaheadLayoutCoordinatesImpl) {
                    lookaheadLayoutCoordinatesImpl = (LookaheadLayoutCoordinatesImpl) sourceCoordinates;
                } else {
                    lookaheadLayoutCoordinatesImpl = null;
                }
                if (lookaheadLayoutCoordinatesImpl == null || (nodeCoordinator = lookaheadLayoutCoordinatesImpl.getCoordinator()) == null) {
                    nodeCoordinator = sourceCoordinates;
                }
                NodeCoordinator findCommonAncestor$ui_release = findCommonAncestor$ui_release(nodeCoordinator);
                MutableRect mutableRect = this._rectCache;
                if (mutableRect == null) {
                    mutableRect = new MutableRect();
                    this._rectCache = mutableRect;
                }
                mutableRect.setLeft(0.0f);
                mutableRect.setTop(0.0f);
                mutableRect.setRight((int) (sourceCoordinates.m213getMeasuredSizeYbymL2g() >> 32));
                mutableRect.setBottom(IntSize.m405getHeightimpl(sourceCoordinates.m213getMeasuredSizeYbymL2g()));
                while (nodeCoordinator != findCommonAncestor$ui_release) {
                    nodeCoordinator.rectInParent$ui_release(mutableRect, z, false);
                    if (mutableRect.isEmpty()) {
                        rect = Rect.Zero;
                        return rect;
                    }
                    nodeCoordinator = nodeCoordinator.wrappedBy;
                    Intrinsics.checkNotNull(nodeCoordinator);
                }
                ancestorToLocal(findCommonAncestor$ui_release, mutableRect, z);
                return new Rect(mutableRect.getLeft(), mutableRect.getTop(), mutableRect.getRight(), mutableRect.getBottom());
            }
            throw new IllegalStateException(("LayoutCoordinates " + sourceCoordinates + " is not attached!").toString());
        }
        throw new IllegalStateException("LayoutCoordinate operations are only valid when isAttached is true".toString());
    }

    /* renamed from: localPositionOf-R5De75A */
    public final long m262localPositionOfR5De75A(LayoutCoordinates sourceCoordinates, long j) {
        LookaheadLayoutCoordinatesImpl lookaheadLayoutCoordinatesImpl;
        NodeCoordinator nodeCoordinator;
        Intrinsics.checkNotNullParameter(sourceCoordinates, "sourceCoordinates");
        if (sourceCoordinates instanceof LookaheadLayoutCoordinatesImpl) {
            lookaheadLayoutCoordinatesImpl = (LookaheadLayoutCoordinatesImpl) sourceCoordinates;
        } else {
            lookaheadLayoutCoordinatesImpl = null;
        }
        if (lookaheadLayoutCoordinatesImpl == null || (nodeCoordinator = lookaheadLayoutCoordinatesImpl.getCoordinator()) == null) {
            nodeCoordinator = (NodeCoordinator) sourceCoordinates;
        }
        NodeCoordinator findCommonAncestor$ui_release = findCommonAncestor$ui_release(nodeCoordinator);
        while (nodeCoordinator != findCommonAncestor$ui_release) {
            j = nodeCoordinator.m264toParentPositionMKHz9U(j);
            nodeCoordinator = nodeCoordinator.wrappedBy;
            Intrinsics.checkNotNull(nodeCoordinator);
        }
        return m253ancestorToLocalR5De75A(findCommonAncestor$ui_release, j);
    }

    /* renamed from: localToRoot-MK-Hz9U */
    public final long m263localToRootMKHz9U(long j) {
        if (isAttached()) {
            while (this != null) {
                j = this.m264toParentPositionMKHz9U(j);
                this = this.wrappedBy;
            }
            return j;
        }
        throw new IllegalStateException("LayoutCoordinate operations are only valid when isAttached is true".toString());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToWindow-MK-Hz9U */
    public final long mo209localToWindowMKHz9U(long j) {
        return ((AndroidComposeView) LayoutNodeKt.requireOwner(this.layoutNode)).m277calculatePositionInWindowMKHz9U(m263localToRootMKHz9U(j));
    }

    public void onLayoutModifierNodeChanged() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
        }
    }

    public final void onMeasured() {
        Modifier.Node parent$ui_release;
        boolean z;
        boolean m268getIncludeSelfInTraversalH91voCI = NodeKindKt.m268getIncludeSelfInTraversalH91voCI(128);
        Modifier.Node headNode = headNode(m268getIncludeSelfInTraversalH91voCI);
        boolean z2 = false;
        if (headNode != null) {
            if ((headNode.getNode().getAggregateChildKindSet$ui_release() & 128) != 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                z2 = true;
            }
        }
        if (z2) {
            Snapshot createNonObservableSnapshot = Snapshot.Companion.createNonObservableSnapshot();
            try {
                Snapshot makeCurrent = createNonObservableSnapshot.makeCurrent();
                if (m268getIncludeSelfInTraversalH91voCI) {
                    parent$ui_release = getTail();
                } else {
                    parent$ui_release = getTail().getParent$ui_release();
                    if (parent$ui_release == null) {
                        Snapshot.restoreCurrent(makeCurrent);
                    }
                }
                for (Modifier.Node headNode2 = headNode(m268getIncludeSelfInTraversalH91voCI); headNode2 != null && (headNode2.getAggregateChildKindSet$ui_release() & 128) != 0; headNode2 = headNode2.getChild$ui_release()) {
                    if ((headNode2.getKindSet$ui_release() & 128) != 0 && (headNode2 instanceof LayoutAwareModifierNode)) {
                        LayoutAwareModifierNode layoutAwareModifierNode = (LayoutAwareModifierNode) headNode2;
                    }
                    if (headNode2 == parent$ui_release) {
                        break;
                    }
                }
                Snapshot.restoreCurrent(makeCurrent);
            } finally {
                createNonObservableSnapshot.dispose();
            }
        }
    }

    public final void onPlaced$1() {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        boolean m268getIncludeSelfInTraversalH91voCI = NodeKindKt.m268getIncludeSelfInTraversalH91voCI(128);
        if (lookaheadDelegate != null) {
            Modifier.Node tail = getTail();
            if (m268getIncludeSelfInTraversalH91voCI || (tail = tail.getParent$ui_release()) != null) {
                for (Modifier.Node headNode = headNode(m268getIncludeSelfInTraversalH91voCI); headNode != null && (headNode.getAggregateChildKindSet$ui_release() & 128) != 0; headNode = headNode.getChild$ui_release()) {
                    if ((headNode.getKindSet$ui_release() & 128) != 0 && (headNode instanceof LayoutAwareModifierNode)) {
                        LayoutAwareModifierNode layoutAwareModifierNode = (LayoutAwareModifierNode) headNode;
                        LookaheadLayoutCoordinatesImpl coordinates = lookaheadDelegate.getLookaheadLayoutCoordinates();
                        Intrinsics.checkNotNullParameter(coordinates, "coordinates");
                    }
                    if (headNode == tail) {
                        break;
                    }
                }
            }
        }
        Modifier.Node tail2 = getTail();
        if (m268getIncludeSelfInTraversalH91voCI || (tail2 = tail2.getParent$ui_release()) != null) {
            for (Modifier.Node headNode2 = headNode(m268getIncludeSelfInTraversalH91voCI); headNode2 != null && (headNode2.getAggregateChildKindSet$ui_release() & 128) != 0; headNode2 = headNode2.getChild$ui_release()) {
                if ((headNode2.getKindSet$ui_release() & 128) != 0 && (headNode2 instanceof LayoutAwareModifierNode)) {
                    ((BackwardsCompatNode) ((LayoutAwareModifierNode) headNode2)).onPlaced(this);
                }
                if (headNode2 == tail2) {
                    return;
                }
            }
        }
    }

    public abstract void performDraw(Canvas canvas);

    @Override // androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public void mo216placeAtf8xVGno(long j, float f, Function1 function1) {
        boolean z = false;
        onLayerBlockUpdated(function1, false);
        long j2 = this.position;
        IntOffset.Companion companion = IntOffset.Companion;
        if (j2 == j) {
            z = true;
        }
        if (!z) {
            this.position = j;
            LayoutNode layoutNode = this.layoutNode;
            layoutNode.getLayoutDelegate$ui_release().getMeasurePassDelegate$ui_release().notifyChildrenUsingCoordinatesWhilePlacing();
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.mo271movegyyYBs(j);
            } else {
                NodeCoordinator nodeCoordinator = this.wrappedBy;
                if (nodeCoordinator != null) {
                    nodeCoordinator.invalidateLayer();
                }
            }
            LookaheadCapablePlaceable.invalidateAlignmentLinesFromPositionChange(this);
            Owner owner$ui_release = layoutNode.getOwner$ui_release();
            if (owner$ui_release != null) {
                ((AndroidComposeView) owner$ui_release).onLayoutChange(layoutNode);
            }
        }
        this.zIndex = f;
    }

    public final void rectInParent$ui_release(MutableRect mutableRect, boolean z, boolean z2) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            if (this.isClipping) {
                if (z2) {
                    long m260getMinimumTouchTargetSizeNHjbRc = m260getMinimumTouchTargetSizeNHjbRc();
                    float m64getWidthimpl = Size.m64getWidthimpl(m260getMinimumTouchTargetSizeNHjbRc) / 2.0f;
                    float m63getHeightimpl = Size.m63getHeightimpl(m260getMinimumTouchTargetSizeNHjbRc) / 2.0f;
                    mutableRect.intersect(-m64getWidthimpl, -m63getHeightimpl, ((int) (m213getMeasuredSizeYbymL2g() >> 32)) + m64getWidthimpl, IntSize.m405getHeightimpl(m213getMeasuredSizeYbymL2g()) + m63getHeightimpl);
                } else if (z) {
                    mutableRect.intersect(0.0f, 0.0f, (int) (m213getMeasuredSizeYbymL2g() >> 32), IntSize.m405getHeightimpl(m213getMeasuredSizeYbymL2g()));
                }
                if (mutableRect.isEmpty()) {
                    return;
                }
            }
            ownedLayer.mapBounds(mutableRect, false);
        }
        long j = this.position;
        IntOffset.Companion companion = IntOffset.Companion;
        float f = (int) (j >> 32);
        mutableRect.setLeft(mutableRect.getLeft() + f);
        mutableRect.setRight(mutableRect.getRight() + f);
        float m402getYimpl = IntOffset.m402getYimpl(this.position);
        mutableRect.setTop(mutableRect.getTop() + m402getYimpl);
        mutableRect.setBottom(mutableRect.getBottom() + m402getYimpl);
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final void replace$ui_release() {
        mo216placeAtf8xVGno(this.position, this.zIndex, this.layerBlock);
    }

    public final void setMeasureResult$ui_release(MeasureResult value) {
        boolean z;
        Intrinsics.checkNotNullParameter(value, "value");
        MeasureResult measureResult = this._measureResult;
        if (value != measureResult) {
            this._measureResult = value;
            if (measureResult == null || value.getWidth() != measureResult.getWidth() || value.getHeight() != measureResult.getHeight()) {
                int width = value.getWidth();
                int height = value.getHeight();
                OwnedLayer ownedLayer = this.layer;
                if (ownedLayer != null) {
                    ownedLayer.mo272resizeozmzZPI(IntSizeKt.IntSize(width, height));
                } else {
                    NodeCoordinator nodeCoordinator = this.wrappedBy;
                    if (nodeCoordinator != null) {
                        nodeCoordinator.invalidateLayer();
                    }
                }
                LayoutNode layoutNode = this.layoutNode;
                Owner owner$ui_release = layoutNode.getOwner$ui_release();
                if (owner$ui_release != null) {
                    ((AndroidComposeView) owner$ui_release).onLayoutChange(layoutNode);
                }
                m217setMeasuredSizeozmzZPI(IntSizeKt.IntSize(width, height));
                IntSizeKt.m408toSizeozmzZPI(m213getMeasuredSizeYbymL2g());
                graphicsLayerScope.getClass();
                boolean m268getIncludeSelfInTraversalH91voCI = NodeKindKt.m268getIncludeSelfInTraversalH91voCI(4);
                Modifier.Node tail = getTail();
                if (m268getIncludeSelfInTraversalH91voCI || (tail = tail.getParent$ui_release()) != null) {
                    for (Modifier.Node headNode = headNode(m268getIncludeSelfInTraversalH91voCI); headNode != null && (headNode.getAggregateChildKindSet$ui_release() & 4) != 0; headNode = headNode.getChild$ui_release()) {
                        if ((headNode.getKindSet$ui_release() & 4) != 0 && (headNode instanceof DrawModifierNode)) {
                            ((DrawModifierNode) headNode).onMeasureResultChanged();
                        }
                        if (headNode == tail) {
                            break;
                        }
                    }
                }
            }
            Map map = this.oldAlignmentLines;
            if (map != null && !map.isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            if ((!z || (!value.getAlignmentLines().isEmpty())) && !Intrinsics.areEqual(value.getAlignmentLines(), this.oldAlignmentLines)) {
                ((LayoutNodeLayoutDelegate.MeasurePassDelegate) getAlignmentLinesOwner()).getAlignmentLines().onAlignmentsChanged();
                Map map2 = this.oldAlignmentLines;
                if (map2 == null) {
                    map2 = new LinkedHashMap();
                    this.oldAlignmentLines = map2;
                }
                map2.clear();
                map2.putAll(value.getAlignmentLines());
            }
        }
    }

    public final void setWrapped$ui_release(NodeCoordinator nodeCoordinator) {
        this.wrapped = nodeCoordinator;
    }

    public final void setWrappedBy$ui_release(NodeCoordinator nodeCoordinator) {
        this.wrappedBy = nodeCoordinator;
    }

    public final void shouldSharePointerInputWithSiblings() {
        Modifier.Node headNode = headNode(NodeKindKt.m268getIncludeSelfInTraversalH91voCI(16));
        if (headNode == null) {
            return;
        }
        if (headNode.getNode().isAttached()) {
            Modifier.Node node = headNode.getNode();
            if ((node.getAggregateChildKindSet$ui_release() & 16) != 0) {
                for (Modifier.Node child$ui_release = node.getChild$ui_release(); child$ui_release != null; child$ui_release = child$ui_release.getChild$ui_release()) {
                    if ((child$ui_release.getKindSet$ui_release() & 16) != 0 && (child$ui_release instanceof PointerInputModifierNode)) {
                        ((BackwardsCompatNode) ((PointerInputModifierNode) child$ui_release)).sharePointerInputWithSiblings();
                    }
                }
                return;
            }
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* renamed from: toParentPosition-MK-Hz9U */
    public final long m264toParentPositionMKHz9U(long j) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            j = ownedLayer.mo270mapOffset8S9VItk(j, false);
        }
        long j2 = this.position;
        float m45getXimpl = Offset.m45getXimpl(j);
        IntOffset.Companion companion = IntOffset.Companion;
        return OffsetKt.Offset(m45getXimpl + ((int) (j2 >> 32)), Offset.m46getYimpl(j) + IntOffset.m402getYimpl(j2));
    }

    public final Rect touchBoundsInRoot() {
        Rect rect;
        Rect rect2;
        if (!isAttached()) {
            rect2 = Rect.Zero;
            return rect2;
        }
        LayoutCoordinates findRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(this);
        MutableRect mutableRect = this._rectCache;
        if (mutableRect == null) {
            mutableRect = new MutableRect();
            this._rectCache = mutableRect;
        }
        long m257calculateMinimumTouchTargetPaddingE7KxVPU = m257calculateMinimumTouchTargetPaddingE7KxVPU(m260getMinimumTouchTargetSizeNHjbRc());
        mutableRect.setLeft(-Size.m64getWidthimpl(m257calculateMinimumTouchTargetPaddingE7KxVPU));
        mutableRect.setTop(-Size.m63getHeightimpl(m257calculateMinimumTouchTargetPaddingE7KxVPU));
        mutableRect.setRight(Size.m64getWidthimpl(m257calculateMinimumTouchTargetPaddingE7KxVPU) + getMeasuredWidth());
        mutableRect.setBottom(Size.m63getHeightimpl(m257calculateMinimumTouchTargetPaddingE7KxVPU) + getMeasuredHeight());
        while (this != findRootCoordinates) {
            this.rectInParent$ui_release(mutableRect, false, true);
            if (mutableRect.isEmpty()) {
                rect = Rect.Zero;
                return rect;
            }
            this = this.wrappedBy;
            Intrinsics.checkNotNull(this);
        }
        return new Rect(mutableRect.getLeft(), mutableRect.getTop(), mutableRect.getRight(), mutableRect.getBottom());
    }

    public final void updateLayerBlock(Function1 function1) {
        Function1 function12 = this.layerBlock;
        this.layerBlock = function1;
        onLayerBlockUpdated(function1, true);
    }

    public final void updateLookaheadDelegate(LookaheadDelegate lookaheadDelegate) {
        this.lookaheadDelegate = lookaheadDelegate;
    }

    public final void updateLookaheadScope$ui_release() {
        this.lookaheadDelegate = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0031 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0032  */
    /* renamed from: withinLayerBounds-k-4lQ0M */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean m265withinLayerBoundsk4lQ0M(long r5) {
        /*
            r4 = this;
            float r0 = androidx.compose.ui.geometry.Offset.m45getXimpl(r5)
            boolean r1 = java.lang.Float.isInfinite(r0)
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L14
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L14
            r0 = r2
            goto L15
        L14:
            r0 = r3
        L15:
            if (r0 == 0) goto L2e
            float r0 = androidx.compose.ui.geometry.Offset.m46getYimpl(r5)
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L29
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L29
            r0 = r2
            goto L2a
        L29:
            r0 = r3
        L2a:
            if (r0 == 0) goto L2e
            r0 = r2
            goto L2f
        L2e:
            r0 = r3
        L2f:
            if (r0 != 0) goto L32
            return r3
        L32:
            androidx.compose.ui.node.OwnedLayer r0 = r4.layer
            if (r0 == 0) goto L42
            boolean r4 = r4.isClipping
            if (r4 == 0) goto L42
            boolean r4 = r0.mo269isInLayerk4lQ0M(r5)
            if (r4 == 0) goto L41
            goto L42
        L41:
            r2 = r3
        L42:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeCoordinator.m265withinLayerBoundsk4lQ0M(long):boolean");
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LayoutCoordinates getCoordinates() {
        return this;
    }
}
