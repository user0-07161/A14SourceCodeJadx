package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LayoutNodeLayoutDelegate {
    private int childrenAccessingCoordinatesDuringPlacement;
    private boolean coordinatesAccessedDuringPlacement;
    private final LayoutNode layoutNode;
    private boolean layoutPending;
    private boolean layoutPendingForAlignment;
    private LayoutNode.LayoutState layoutState;
    private boolean lookaheadLayoutPending;
    private boolean lookaheadLayoutPendingForAlignment;
    private boolean lookaheadMeasurePending;
    private LookaheadPassDelegate lookaheadPassDelegate;
    private final MeasurePassDelegate measurePassDelegate;
    private boolean measurePending;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class LookaheadPassDelegate extends Placeable implements Measurable, AlignmentLinesOwner {
        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public abstract AlignmentLines getAlignmentLines();

        public abstract void invalidateParentData();

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public abstract boolean isPlaced();

        public abstract void notifyChildrenUsingCoordinatesWhilePlacing();

        public abstract void replace();

        public abstract void setChildMeasurablesDirty$ui_release();

        public abstract boolean updateParentData();
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class MeasurePassDelegate extends Placeable implements Measurable, AlignmentLinesOwner {
        private final MutableVector _childMeasurables;
        private final LayoutNodeAlignmentLines alignmentLines;
        private boolean childMeasurablesDirty;
        private Function1 lastLayerBlock;
        private long lastPosition;
        private float lastZIndex;
        private boolean measuredOnce;
        private Object parentData;
        private boolean parentDataDirty;
        private boolean placedOnce;

        public MeasurePassDelegate() {
            long j;
            LayoutNodeLayoutDelegate.this = r3;
            j = IntOffset.Zero;
            this.lastPosition = j;
            this.parentDataDirty = true;
            this.alignmentLines = new LayoutNodeAlignmentLines(this);
            this._childMeasurables = new MutableVector(new Measurable[16]);
            this.childMeasurablesDirty = true;
        }

        /* renamed from: placeOuterCoordinator-f8xVGno */
        private final void m243placeOuterCoordinatorf8xVGno(final long j, final float f, final Function1 function1) {
            this.lastPosition = j;
            this.lastZIndex = f;
            this.lastLayerBlock = function1;
            this.placedOnce = true;
            this.alignmentLines.setUsedByModifierLayout$ui_release(false);
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            layoutNodeLayoutDelegate.setCoordinatesAccessedDuringPlacement();
            OwnerSnapshotObserver snapshotObserver = ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNodeLayoutDelegate.layoutNode)).getSnapshotObserver();
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            final LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = LayoutNodeLayoutDelegate.this;
            snapshotObserver.observeLayoutModifierSnapshotReads$ui_release(layoutNode, false, new Function0() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$placeOuterCoordinator$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Function1 function12 = Function1.this;
                    LayoutNodeLayoutDelegate layoutNodeLayoutDelegate3 = layoutNodeLayoutDelegate2;
                    long j2 = j;
                    float f2 = f;
                    if (function12 == null) {
                        Placeable.PlacementScope.m219place70tqf50(layoutNodeLayoutDelegate3.getOuterCoordinator(), j2, f2);
                    } else {
                        Placeable.PlacementScope.m220placeWithLayeraW9wM(layoutNodeLayoutDelegate3.getOuterCoordinator(), j2, f2, function12);
                    }
                    return Unit.INSTANCE;
                }
            });
        }

        public final Map calculateAlignmentLines() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode.LayoutState layoutState$ui_release = layoutNodeLayoutDelegate.getLayoutState$ui_release();
            LayoutNode.LayoutState layoutState = LayoutNode.LayoutState.Measuring;
            LayoutNodeAlignmentLines layoutNodeAlignmentLines = this.alignmentLines;
            if (layoutState$ui_release == layoutState) {
                layoutNodeAlignmentLines.setUsedByModifierMeasurement$ui_release(true);
                if (layoutNodeAlignmentLines.getDirty$ui_release()) {
                    layoutNodeLayoutDelegate.markLayoutPending$ui_release();
                }
            } else {
                layoutNodeAlignmentLines.setUsedByModifierLayout$ui_release(true);
            }
            getInnerCoordinator().setPlacingForAlignment$ui_release(true);
            layoutChildren();
            getInnerCoordinator().setPlacingForAlignment$ui_release(false);
            return layoutNodeAlignmentLines.getLastCalculation();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void forEachChildAlignmentLinesOwner(Function1 block) {
            Intrinsics.checkNotNullParameter(block, "block");
            List children$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getChildren$ui_release();
            int size = children$ui_release.size();
            for (int i = 0; i < size; i++) {
                block.invoke(((LayoutNode) children$ui_release.get(i)).getLayoutDelegate$ui_release().getAlignmentLinesOwner$ui_release());
            }
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final AlignmentLines getAlignmentLines() {
            return this.alignmentLines;
        }

        public final List getChildMeasurables$ui_release() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            layoutNodeLayoutDelegate.layoutNode.updateChildrenIfDirty$ui_release();
            boolean z = this.childMeasurablesDirty;
            MutableVector mutableVector = this._childMeasurables;
            if (!z) {
                return mutableVector.asMutableList();
            }
            LayoutNodeLayoutDelegateKt.access$updateChildMeasurables(layoutNodeLayoutDelegate.layoutNode, mutableVector, new Function1() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$childMeasurables$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LayoutNode it = (LayoutNode) obj;
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.getLayoutDelegate$ui_release().getMeasurePassDelegate$ui_release();
                }
            });
            this.childMeasurablesDirty = false;
            return mutableVector.asMutableList();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final InnerNodeCoordinator getInnerCoordinator() {
            return LayoutNodeLayoutDelegate.this.layoutNode.getInnerCoordinator$ui_release();
        }

        /* renamed from: getLastConstraints-DWUhwKw */
        public final Constraints m244getLastConstraintsDWUhwKw() {
            if (this.measuredOnce) {
                return Constraints.m376boximpl(m214getMeasurementConstraintsmsEJaDk());
            }
            return null;
        }

        @Override // androidx.compose.ui.layout.Placeable
        public final int getMeasuredWidth() {
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getMeasuredWidth();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final AlignmentLinesOwner getParentAlignmentLinesOwner() {
            LayoutNodeLayoutDelegate layoutDelegate$ui_release;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (parent$ui_release != null && (layoutDelegate$ui_release = parent$ui_release.getLayoutDelegate$ui_release()) != null) {
                return layoutDelegate$ui_release.getAlignmentLinesOwner$ui_release();
            }
            return null;
        }

        @Override // androidx.compose.ui.layout.Measurable
        public final Object getParentData() {
            return this.parentData;
        }

        public final void invalidateParentData() {
            this.parentDataDirty = true;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final boolean isPlaced() {
            return LayoutNodeLayoutDelegate.this.layoutNode.isPlaced();
        }

        /* JADX WARN: Removed duplicated region for block: B:70:0x009b  */
        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void layoutChildren() {
            /*
                r9 = this;
                androidx.compose.ui.node.LayoutNodeAlignmentLines r0 = r9.alignmentLines
                r0.recalculateQueryOwner()
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r1 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.this
                boolean r2 = r1.getLayoutPending$ui_release()
                r3 = 0
                if (r2 == 0) goto L44
                androidx.compose.ui.node.LayoutNode r2 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$getLayoutNode$p(r1)
                androidx.compose.runtime.collection.MutableVector r2 = r2.get_children$ui_release()
                int r4 = r2.getSize()
                if (r4 <= 0) goto L44
                java.lang.Object[] r2 = r2.getContent()
                r5 = r3
            L21:
                r6 = r2[r5]
                androidx.compose.ui.node.LayoutNode r6 = (androidx.compose.ui.node.LayoutNode) r6
                boolean r7 = r6.getMeasurePending$ui_release()
                if (r7 == 0) goto L40
                androidx.compose.ui.node.LayoutNode$UsageByParent r7 = r6.getMeasuredByParent$ui_release()
                androidx.compose.ui.node.LayoutNode$UsageByParent r8 = androidx.compose.ui.node.LayoutNode.UsageByParent.InMeasureBlock
                if (r7 != r8) goto L40
                boolean r6 = androidx.compose.ui.node.LayoutNode.m229remeasure_Sx5XlM$ui_release$default(r6)
                if (r6 == 0) goto L40
                androidx.compose.ui.node.LayoutNode r6 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$getLayoutNode$p(r1)
                r6.requestRemeasure$ui_release(r3)
            L40:
                int r5 = r5 + 1
                if (r5 < r4) goto L21
            L44:
                boolean r2 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$getLayoutPendingForAlignment$p(r1)
                if (r2 != 0) goto L5a
                androidx.compose.ui.node.InnerNodeCoordinator r2 = r9.getInnerCoordinator()
                boolean r2 = r2.isPlacingForAlignment$ui_release()
                if (r2 != 0) goto L95
                boolean r2 = r1.getLayoutPending$ui_release()
                if (r2 == 0) goto L95
            L5a:
                androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$setLayoutPending$p(r1)
                androidx.compose.ui.node.LayoutNode$LayoutState r2 = r1.getLayoutState$ui_release()
                androidx.compose.ui.node.LayoutNode$LayoutState r4 = androidx.compose.ui.node.LayoutNode.LayoutState.LayingOut
                androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$setLayoutState$p(r1, r4)
                androidx.compose.ui.node.LayoutNode r4 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$getLayoutNode$p(r1)
                androidx.compose.ui.node.Owner r5 = androidx.compose.ui.node.LayoutNodeKt.requireOwner(r4)
                androidx.compose.ui.platform.AndroidComposeView r5 = (androidx.compose.ui.platform.AndroidComposeView) r5
                androidx.compose.ui.node.OwnerSnapshotObserver r5 = r5.getSnapshotObserver()
                androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$layoutChildren$1$1 r6 = new androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$layoutChildren$1$1
                r6.<init>()
                r5.observeLayoutSnapshotReads$ui_release(r4, r3, r6)
                androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$setLayoutState$p(r1, r2)
                androidx.compose.ui.node.InnerNodeCoordinator r2 = r9.getInnerCoordinator()
                boolean r2 = r2.isPlacingForAlignment$ui_release()
                if (r2 == 0) goto L92
                boolean r2 = r1.getCoordinatesAccessedDuringPlacement()
                if (r2 == 0) goto L92
                r9.requestLayout()
            L92:
                androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$setLayoutPendingForAlignment$p(r1)
            L95:
                boolean r9 = r0.getUsedDuringParentLayout$ui_release()
                if (r9 == 0) goto L9f
                r9 = 1
                r0.setPreviousUsedDuringParentLayout$ui_release(r9)
            L9f:
                boolean r9 = r0.getDirty$ui_release()
                if (r9 == 0) goto Lae
                boolean r9 = r0.getRequired$ui_release()
                if (r9 == 0) goto Lae
                r0.recalculate()
            Lae:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutNodeLayoutDelegate.MeasurePassDelegate.layoutChildren():void");
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public final Placeable mo210measureBRTryo0(long j) {
            LayoutNode.UsageByParent usageByParent;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode.UsageByParent intrinsicsUsageByParent$ui_release = layoutNodeLayoutDelegate.layoutNode.getIntrinsicsUsageByParent$ui_release();
            LayoutNode.UsageByParent usageByParent2 = LayoutNode.UsageByParent.NotUsed;
            if (intrinsicsUsageByParent$ui_release == usageByParent2) {
                layoutNodeLayoutDelegate.layoutNode.clearSubtreeIntrinsicsUsage$ui_release();
            }
            boolean z = true;
            if (LayoutNodeLayoutDelegate.access$isOutMostLookaheadRoot(layoutNodeLayoutDelegate, layoutNodeLayoutDelegate.layoutNode)) {
                this.measuredOnce = true;
                m218setMeasurementConstraintsBRTryo0(j);
                layoutNodeLayoutDelegate.layoutNode.setMeasuredByParentInLookahead$ui_release(usageByParent2);
                LookaheadPassDelegate lookaheadPassDelegate$ui_release = layoutNodeLayoutDelegate.getLookaheadPassDelegate$ui_release();
                Intrinsics.checkNotNull(lookaheadPassDelegate$ui_release);
                lookaheadPassDelegate$ui_release.mo210measureBRTryo0(j);
            }
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            if (parent$ui_release != null) {
                if (layoutNode.getMeasuredByParent$ui_release() != usageByParent2 && !layoutNode.getCanMultiMeasure$ui_release()) {
                    z = false;
                }
                if (z) {
                    int ordinal = parent$ui_release.getLayoutState$ui_release().ordinal();
                    if (ordinal != 0) {
                        if (ordinal == 2) {
                            usageByParent = LayoutNode.UsageByParent.InLayoutBlock;
                        } else {
                            throw new IllegalStateException("Measurable could be only measured from the parent's measure or layout block. Parents state is " + parent$ui_release.getLayoutState$ui_release());
                        }
                    } else {
                        usageByParent = LayoutNode.UsageByParent.InMeasureBlock;
                    }
                    layoutNode.setMeasuredByParent$ui_release(usageByParent);
                } else {
                    throw new IllegalStateException(("measure() may not be called multiple times on the same Measurable. Current state " + layoutNode.getMeasuredByParent$ui_release() + ". Parent state " + parent$ui_release.getLayoutState$ui_release() + '.').toString());
                }
            } else {
                layoutNode.setMeasuredByParent$ui_release(usageByParent2);
            }
            m245remeasureBRTryo0(j);
            return this;
        }

        public final void notifyChildrenUsingCoordinatesWhilePlacing() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (layoutNodeLayoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() > 0) {
                List children$ui_release = layoutNodeLayoutDelegate.layoutNode.getChildren$ui_release();
                int size = children$ui_release.size();
                for (int i = 0; i < size; i++) {
                    LayoutNode layoutNode = (LayoutNode) children$ui_release.get(i);
                    LayoutNodeLayoutDelegate layoutDelegate$ui_release = layoutNode.getLayoutDelegate$ui_release();
                    if (layoutDelegate$ui_release.getCoordinatesAccessedDuringPlacement() && !layoutDelegate$ui_release.getLayoutPending$ui_release()) {
                        layoutNode.requestRelayout$ui_release(false);
                    }
                    layoutDelegate$ui_release.getMeasurePassDelegate$ui_release().notifyChildrenUsingCoordinatesWhilePlacing();
                }
            }
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* renamed from: placeAt-f8xVGno */
        protected final void mo216placeAtf8xVGno(long j, float f, Function1 function1) {
            boolean z;
            long j2 = this.lastPosition;
            IntOffset.Companion companion = IntOffset.Companion;
            if (j == j2) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                notifyChildrenUsingCoordinatesWhilePlacing();
            }
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (LayoutNodeLayoutDelegate.access$isOutMostLookaheadRoot(layoutNodeLayoutDelegate, layoutNodeLayoutDelegate.layoutNode)) {
                Placeable.PlacementScope.Companion companion2 = Placeable.PlacementScope.Companion;
                LookaheadPassDelegate lookaheadPassDelegate$ui_release = layoutNodeLayoutDelegate.getLookaheadPassDelegate$ui_release();
                Intrinsics.checkNotNull(lookaheadPassDelegate$ui_release);
                Placeable.PlacementScope.place$default(companion2, lookaheadPassDelegate$ui_release, (int) (j >> 32), IntOffset.m402getYimpl(j));
            }
            layoutNodeLayoutDelegate.layoutState = LayoutNode.LayoutState.LayingOut;
            m243placeOuterCoordinatorf8xVGno(j, f, function1);
            layoutNodeLayoutDelegate.layoutState = LayoutNode.LayoutState.Idle;
        }

        /* renamed from: remeasure-BRTryo0 */
        public final boolean m245remeasureBRTryo0(long j) {
            boolean z;
            boolean z2;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            Owner requireOwner = LayoutNodeKt.requireOwner(layoutNodeLayoutDelegate.layoutNode);
            LayoutNode parent$ui_release = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            boolean z3 = true;
            if (!layoutNodeLayoutDelegate.layoutNode.getCanMultiMeasure$ui_release() && (parent$ui_release == null || !parent$ui_release.getCanMultiMeasure$ui_release())) {
                z = false;
            } else {
                z = true;
            }
            layoutNode.setCanMultiMeasure$ui_release(z);
            if (!layoutNodeLayoutDelegate.layoutNode.getMeasurePending$ui_release() && Constraints.m377equalsimpl0(m214getMeasurementConstraintsmsEJaDk(), j)) {
                ((AndroidComposeView) requireOwner).forceMeasureTheSubtree(layoutNodeLayoutDelegate.layoutNode);
                layoutNodeLayoutDelegate.layoutNode.resetSubtreeIntrinsicsUsage$ui_release();
                return false;
            }
            this.alignmentLines.setUsedByModifierMeasurement$ui_release(false);
            forEachChildAlignmentLinesOwner(new Function1() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$remeasure$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    AlignmentLinesOwner it = (AlignmentLinesOwner) obj;
                    Intrinsics.checkNotNullParameter(it, "it");
                    it.getAlignmentLines().setUsedDuringParentMeasurement$ui_release();
                    return Unit.INSTANCE;
                }
            });
            this.measuredOnce = true;
            long j2 = layoutNodeLayoutDelegate.getOuterCoordinator().m215getSizeYbymL2g();
            m218setMeasurementConstraintsBRTryo0(j);
            LayoutNodeLayoutDelegate.m241access$performMeasureBRTryo0(layoutNodeLayoutDelegate, j);
            if (layoutNodeLayoutDelegate.getOuterCoordinator().m215getSizeYbymL2g() == j2) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 && layoutNodeLayoutDelegate.getOuterCoordinator().getWidth() == getWidth() && layoutNodeLayoutDelegate.getOuterCoordinator().getHeight() == getHeight()) {
                z3 = false;
            }
            m217setMeasuredSizeozmzZPI(IntSizeKt.IntSize(layoutNodeLayoutDelegate.getOuterCoordinator().getWidth(), layoutNodeLayoutDelegate.getOuterCoordinator().getHeight()));
            return z3;
        }

        public final void replace() {
            if (this.placedOnce) {
                m243placeOuterCoordinatorf8xVGno(this.lastPosition, this.lastZIndex, this.lastLayerBlock);
                return;
            }
            throw new IllegalStateException("Check failed.".toString());
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void requestLayout() {
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            int i = LayoutNode.$r8$clinit;
            layoutNode.requestRelayout$ui_release(false);
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void requestMeasure() {
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            int i = LayoutNode.$r8$clinit;
            layoutNode.requestRemeasure$ui_release(false);
        }

        public final void setChildMeasurablesDirty$ui_release() {
            this.childMeasurablesDirty = true;
        }

        public final boolean updateParentData() {
            if (!this.parentDataDirty) {
                return false;
            }
            this.parentDataDirty = false;
            Object obj = this.parentData;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            boolean z = !Intrinsics.areEqual(obj, layoutNodeLayoutDelegate.getOuterCoordinator().getParentData());
            this.parentData = layoutNodeLayoutDelegate.getOuterCoordinator().getParentData();
            return z;
        }
    }

    public LayoutNodeLayoutDelegate(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.layoutNode = layoutNode;
        this.layoutState = LayoutNode.LayoutState.Idle;
        this.measurePassDelegate = new MeasurePassDelegate();
    }

    public static final boolean access$isOutMostLookaheadRoot(LayoutNodeLayoutDelegate layoutNodeLayoutDelegate, LayoutNode layoutNode) {
        layoutNodeLayoutDelegate.getClass();
        layoutNode.getClass();
        return Intrinsics.areEqual(null, layoutNode);
    }

    /* renamed from: access$performMeasure-BRTryo0 */
    public static final void m241access$performMeasureBRTryo0(LayoutNodeLayoutDelegate layoutNodeLayoutDelegate, final long j) {
        boolean z;
        LayoutNode.LayoutState layoutState = layoutNodeLayoutDelegate.layoutState;
        LayoutNode.LayoutState layoutState2 = LayoutNode.LayoutState.Idle;
        if (layoutState == layoutState2) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            LayoutNode.LayoutState layoutState3 = LayoutNode.LayoutState.Measuring;
            layoutNodeLayoutDelegate.layoutState = layoutState3;
            layoutNodeLayoutDelegate.measurePending = false;
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).getSnapshotObserver().observeMeasureSnapshotReads$ui_release(layoutNode, false, new Function0() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$performMeasure$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    LayoutNodeLayoutDelegate.this.getOuterCoordinator().mo210measureBRTryo0(j);
                    return Unit.INSTANCE;
                }
            });
            if (layoutNodeLayoutDelegate.layoutState == layoutState3) {
                layoutNodeLayoutDelegate.markLayoutPending$ui_release();
                layoutNodeLayoutDelegate.layoutState = layoutState2;
                return;
            }
            return;
        }
        throw new IllegalStateException("layout state is not idle before measure starts".toString());
    }

    private static boolean isOutMostLookaheadRoot(LayoutNode layoutNode) {
        layoutNode.getClass();
        return Intrinsics.areEqual(null, layoutNode);
    }

    public final MeasurePassDelegate getAlignmentLinesOwner$ui_release() {
        return this.measurePassDelegate;
    }

    public final int getChildrenAccessingCoordinatesDuringPlacement() {
        return this.childrenAccessingCoordinatesDuringPlacement;
    }

    public final boolean getCoordinatesAccessedDuringPlacement() {
        return this.coordinatesAccessedDuringPlacement;
    }

    public final int getHeight$ui_release() {
        return this.measurePassDelegate.getHeight();
    }

    /* renamed from: getLastConstraints-DWUhwKw */
    public final Constraints m242getLastConstraintsDWUhwKw() {
        return this.measurePassDelegate.m244getLastConstraintsDWUhwKw();
    }

    public final boolean getLayoutPending$ui_release() {
        return this.layoutPending;
    }

    public final LayoutNode.LayoutState getLayoutState$ui_release() {
        return this.layoutState;
    }

    public final LookaheadPassDelegate getLookaheadAlignmentLinesOwner$ui_release() {
        return this.lookaheadPassDelegate;
    }

    public final boolean getLookaheadLayoutPending$ui_release() {
        return this.lookaheadLayoutPending;
    }

    public final boolean getLookaheadMeasurePending$ui_release() {
        return this.lookaheadMeasurePending;
    }

    public final LookaheadPassDelegate getLookaheadPassDelegate$ui_release() {
        return this.lookaheadPassDelegate;
    }

    public final MeasurePassDelegate getMeasurePassDelegate$ui_release() {
        return this.measurePassDelegate;
    }

    public final boolean getMeasurePending$ui_release() {
        return this.measurePending;
    }

    public final NodeCoordinator getOuterCoordinator() {
        return this.layoutNode.getNodes$ui_release().getOuterCoordinator$ui_release();
    }

    public final int getWidth$ui_release() {
        return this.measurePassDelegate.getWidth();
    }

    public final void invalidateParentData() {
        this.measurePassDelegate.invalidateParentData();
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            lookaheadPassDelegate.invalidateParentData();
        }
    }

    public final void markChildrenDirty() {
        this.measurePassDelegate.setChildMeasurablesDirty$ui_release();
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            lookaheadPassDelegate.setChildMeasurablesDirty$ui_release();
        }
    }

    public final void markLayoutPending$ui_release() {
        this.layoutPending = true;
        this.layoutPendingForAlignment = true;
    }

    public final void markLookaheadLayoutPending$ui_release() {
        this.lookaheadLayoutPending = true;
        this.lookaheadLayoutPendingForAlignment = true;
    }

    public final void markMeasurePending$ui_release() {
        this.measurePending = true;
    }

    public final void onLookaheadScopeChanged$ui_release() {
        this.lookaheadPassDelegate = null;
    }

    public final void resetAlignmentLines() {
        AlignmentLines alignmentLines;
        this.measurePassDelegate.getAlignmentLines().reset$ui_release();
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null && (alignmentLines = lookaheadPassDelegate.getAlignmentLines()) != null) {
            alignmentLines.reset$ui_release();
        }
    }

    public final void setChildrenAccessingCoordinatesDuringPlacement(int i) {
        boolean z;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
        int i2 = this.childrenAccessingCoordinatesDuringPlacement;
        this.childrenAccessingCoordinatesDuringPlacement = i;
        boolean z2 = false;
        if (i2 == 0) {
            z = true;
        } else {
            z = false;
        }
        if (i == 0) {
            z2 = true;
        }
        if (z != z2) {
            LayoutNode parent$ui_release = this.layoutNode.getParent$ui_release();
            if (parent$ui_release != null) {
                layoutNodeLayoutDelegate = parent$ui_release.getLayoutDelegate$ui_release();
            } else {
                layoutNodeLayoutDelegate = null;
            }
            if (layoutNodeLayoutDelegate != null) {
                if (i == 0) {
                    layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement - 1);
                } else {
                    layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement + 1);
                }
            }
        }
    }

    public final void setCoordinatesAccessedDuringPlacement() {
        if (this.coordinatesAccessedDuringPlacement) {
            this.coordinatesAccessedDuringPlacement = false;
            setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement - 1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x001d, code lost:
        if (r3.updateParentData() == true) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateParentData() {
        /*
            r3 = this;
            androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate r0 = r3.measurePassDelegate
            boolean r0 = r0.updateParentData()
            androidx.compose.ui.node.LayoutNode r1 = r3.layoutNode
            r2 = 0
            if (r0 == 0) goto L14
            androidx.compose.ui.node.LayoutNode r0 = r1.getParent$ui_release()
            if (r0 == 0) goto L14
            r0.requestRemeasure$ui_release(r2)
        L14:
            androidx.compose.ui.node.LayoutNodeLayoutDelegate$LookaheadPassDelegate r3 = r3.lookaheadPassDelegate
            if (r3 == 0) goto L20
            boolean r3 = r3.updateParentData()
            r0 = 1
            if (r3 != r0) goto L20
            goto L21
        L20:
            r0 = r2
        L21:
            if (r0 == 0) goto L3f
            boolean r3 = isOutMostLookaheadRoot(r1)
            if (r3 == 0) goto L33
            androidx.compose.ui.node.LayoutNode r3 = r1.getParent$ui_release()
            if (r3 == 0) goto L3f
            r3.requestRemeasure$ui_release(r2)
            goto L3f
        L33:
            androidx.compose.ui.node.LayoutNode r3 = r1.getParent$ui_release()
            if (r3 != 0) goto L3a
            goto L3f
        L3a:
            r3.requestLookaheadRemeasure$ui_release(r2)
            r3 = 0
            throw r3
        L3f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutNodeLayoutDelegate.updateParentData():void");
    }
}
