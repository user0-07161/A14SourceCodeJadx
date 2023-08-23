package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LayoutModifierNodeCoordinator extends NodeCoordinator {
    private static final AndroidPaint modifierBoundsPaint;
    private LayoutModifierNode layoutModifierNode;
    private IntermediateLayoutModifierNode lookAheadTransientMeasureNode;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class LookaheadDelegateForIntermediateLayoutModifier extends LookaheadDelegate {
        private final IntermediateLayoutModifierNode intermediateMeasureNode;
        final /* synthetic */ LayoutModifierNodeCoordinator this$0;

        /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
        /* loaded from: classes.dex */
        final class PassThroughMeasureResult implements MeasureResult {
            private final Map alignmentLines = MapsKt.emptyMap();

            public PassThroughMeasureResult() {
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final Map getAlignmentLines() {
                return this.alignmentLines;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final int getHeight() {
                LookaheadDelegate lookaheadDelegate$ui_release = LookaheadDelegateForIntermediateLayoutModifier.this.this$0.getWrappedNonNull().getLookaheadDelegate$ui_release();
                Intrinsics.checkNotNull(lookaheadDelegate$ui_release);
                return lookaheadDelegate$ui_release.getMeasureResult$ui_release().getHeight();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final int getWidth() {
                LookaheadDelegate lookaheadDelegate$ui_release = LookaheadDelegateForIntermediateLayoutModifier.this.this$0.getWrappedNonNull().getLookaheadDelegate$ui_release();
                Intrinsics.checkNotNull(lookaheadDelegate$ui_release);
                return lookaheadDelegate$ui_release.getMeasureResult$ui_release().getWidth();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final void placeChildren() {
                Placeable.PlacementScope.Companion companion = Placeable.PlacementScope.Companion;
                LookaheadDelegate lookaheadDelegate$ui_release = LookaheadDelegateForIntermediateLayoutModifier.this.this$0.getWrappedNonNull().getLookaheadDelegate$ui_release();
                Intrinsics.checkNotNull(lookaheadDelegate$ui_release);
                Placeable.PlacementScope.place$default(companion, lookaheadDelegate$ui_release, 0, 0);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LookaheadDelegateForIntermediateLayoutModifier(LayoutModifierNodeCoordinator layoutModifierNodeCoordinator, IntermediateLayoutModifierNode intermediateLayoutModifierNode) {
            super(layoutModifierNodeCoordinator);
            Intrinsics.checkNotNullParameter(null, "scope");
            this.this$0 = layoutModifierNodeCoordinator;
            this.intermediateMeasureNode = intermediateLayoutModifierNode;
            new PassThroughMeasureResult();
        }

        @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
        public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
            Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
            int access$calculateAlignmentAndPlaceChildAsNeeded = LayoutModifierNodeCoordinatorKt.access$calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
            getCachedAlignmentLinesMap().put(alignmentLine, Integer.valueOf(access$calculateAlignmentAndPlaceChildAsNeeded));
            return access$calculateAlignmentAndPlaceChildAsNeeded;
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public final Placeable mo210measureBRTryo0(long j) {
            m218setMeasurementConstraintsBRTryo0(j);
            LookaheadDelegate lookaheadDelegate$ui_release = this.this$0.getWrappedNonNull().getLookaheadDelegate$ui_release();
            Intrinsics.checkNotNull(lookaheadDelegate$ui_release);
            lookaheadDelegate$ui_release.mo210measureBRTryo0(j);
            lookaheadDelegate$ui_release.getMeasureResult$ui_release().getWidth();
            lookaheadDelegate$ui_release.getMeasureResult$ui_release().getHeight();
            ((BackwardsCompatNode) this.intermediateMeasureNode).m223setTargetSizeozmzZPI();
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class LookaheadDelegateForLayoutModifierNode extends LookaheadDelegate {
        final /* synthetic */ LayoutModifierNodeCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LookaheadDelegateForLayoutModifierNode(LayoutModifierNodeCoordinator layoutModifierNodeCoordinator) {
            super(layoutModifierNodeCoordinator);
            Intrinsics.checkNotNullParameter(null, "scope");
            this.this$0 = layoutModifierNodeCoordinator;
        }

        @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
        public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
            Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
            int access$calculateAlignmentAndPlaceChildAsNeeded = LayoutModifierNodeCoordinatorKt.access$calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
            getCachedAlignmentLinesMap().put(alignmentLine, Integer.valueOf(access$calculateAlignmentAndPlaceChildAsNeeded));
            return access$calculateAlignmentAndPlaceChildAsNeeded;
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public final Placeable mo210measureBRTryo0(long j) {
            m218setMeasurementConstraintsBRTryo0(j);
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = this.this$0;
            LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate$ui_release = layoutModifierNodeCoordinator.getWrappedNonNull().getLookaheadDelegate$ui_release();
            Intrinsics.checkNotNull(lookaheadDelegate$ui_release);
            LookaheadDelegate.access$set_measureResult(this, layoutModifierNode.mo88measure3p2s80s(this, lookaheadDelegate$ui_release, j));
            return this;
        }
    }

    static {
        long j;
        AndroidPaint Paint = AndroidPaint_androidKt.Paint();
        Color.Companion companion = Color.Companion;
        j = Color.Blue;
        Paint.m82setColor8_81llA(j);
        Paint.setStrokeWidth(1.0f);
        Paint.m86setStylek9PVt8s(1);
        modifierBoundsPaint = Paint;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LayoutModifierNodeCoordinator(LayoutNode layoutNode, LayoutModifierNode layoutModifierNode) {
        super(layoutNode);
        boolean z;
        IntermediateLayoutModifierNode intermediateLayoutModifierNode;
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.layoutModifierNode = layoutModifierNode;
        if ((((Modifier.Node) layoutModifierNode).getNode().getKindSet$ui_release() & 512) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z && (layoutModifierNode instanceof IntermediateLayoutModifierNode)) {
            intermediateLayoutModifierNode = (IntermediateLayoutModifierNode) layoutModifierNode;
        } else {
            intermediateLayoutModifierNode = null;
        }
        this.lookAheadTransientMeasureNode = intermediateLayoutModifierNode;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
        Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
        LookaheadDelegate lookaheadDelegate$ui_release = getLookaheadDelegate$ui_release();
        if (lookaheadDelegate$ui_release != null) {
            return lookaheadDelegate$ui_release.getCachedAlignmentLine$ui_release(alignmentLine);
        }
        return LayoutModifierNodeCoordinatorKt.access$calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
    }

    public final LayoutModifierNode getLayoutModifierNode() {
        return this.layoutModifierNode;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final Modifier.Node getTail() {
        return ((Modifier.Node) this.layoutModifierNode).getNode();
    }

    public final NodeCoordinator getWrappedNonNull() {
        NodeCoordinator wrapped$ui_release = getWrapped$ui_release();
        Intrinsics.checkNotNull(wrapped$ui_release);
        return wrapped$ui_release;
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0 */
    public final Placeable mo210measureBRTryo0(long j) {
        m218setMeasurementConstraintsBRTryo0(j);
        setMeasureResult$ui_release(this.layoutModifierNode.mo88measure3p2s80s(this, getWrappedNonNull(), j));
        OwnedLayer layer = getLayer();
        if (layer != null) {
            layer.mo272resizeozmzZPI(m213getMeasuredSizeYbymL2g());
        }
        onMeasured();
        return this;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final void onLayoutModifierNodeChanged() {
        boolean z;
        super.onLayoutModifierNodeChanged();
        LayoutModifierNode layoutModifierNode = this.layoutModifierNode;
        if ((((Modifier.Node) layoutModifierNode).getNode().getKindSet$ui_release() & 512) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z && (layoutModifierNode instanceof IntermediateLayoutModifierNode)) {
            IntermediateLayoutModifierNode intermediateLayoutModifierNode = (IntermediateLayoutModifierNode) layoutModifierNode;
            this.lookAheadTransientMeasureNode = intermediateLayoutModifierNode;
            if (getLookaheadDelegate$ui_release() != null) {
                updateLookaheadDelegate(new LookaheadDelegateForIntermediateLayoutModifier(this, intermediateLayoutModifierNode));
                return;
            }
            return;
        }
        this.lookAheadTransientMeasureNode = null;
        if (getLookaheadDelegate$ui_release() != null) {
            updateLookaheadDelegate(new LookaheadDelegateForLayoutModifierNode(this));
        }
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final void performDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        getWrappedNonNull().draw(canvas);
        if (((AndroidComposeView) LayoutNodeKt.requireOwner(getLayoutNode())).getShowLayoutBounds()) {
            drawBorder(canvas, modifierBoundsPaint);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.node.NodeCoordinator, androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public final void mo216placeAtf8xVGno(long j, float f, Function1 function1) {
        LayoutCoordinates layoutCoordinates;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
        super.mo216placeAtf8xVGno(j, f, function1);
        if (isShallowPlacing$ui_release()) {
            return;
        }
        onPlaced$1();
        LayoutDirection layoutDirection = getLayoutDirection();
        layoutCoordinates = Placeable.PlacementScope._coordinates;
        int i = Placeable.PlacementScope.parentWidth;
        LayoutDirection layoutDirection2 = Placeable.PlacementScope.parentLayoutDirection;
        layoutNodeLayoutDelegate = Placeable.PlacementScope.layoutDelegate;
        Placeable.PlacementScope.parentWidth = (int) (m213getMeasuredSizeYbymL2g() >> 32);
        Placeable.PlacementScope.parentLayoutDirection = layoutDirection;
        boolean access$configureForPlacingForAlignment = Placeable.PlacementScope.Companion.access$configureForPlacingForAlignment(this);
        getMeasureResult$ui_release().placeChildren();
        setPlacingForAlignment$ui_release(access$configureForPlacingForAlignment);
        Placeable.PlacementScope.parentWidth = i;
        Placeable.PlacementScope.parentLayoutDirection = layoutDirection2;
        Placeable.PlacementScope._coordinates = layoutCoordinates;
        Placeable.PlacementScope.layoutDelegate = layoutNodeLayoutDelegate;
    }

    public final void setLayoutModifierNode$ui_release(LayoutModifierNode layoutModifierNode) {
        Intrinsics.checkNotNullParameter(layoutModifierNode, "<set-?>");
        this.layoutModifierNode = layoutModifierNode;
    }
}
