package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.unit.Constraints;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MeasureAndLayoutDelegate {
    private boolean duringMeasureLayout;
    private final LayoutNode root;
    private Constraints rootConstraints;
    private final DepthSortedSet relayoutNodes = new DepthSortedSet();
    private final OnPositionedDispatcher onPositionedDispatcher = new OnPositionedDispatcher();
    private final MutableVector onLayoutCompletedListeners = new MutableVector(new Owner.OnLayoutCompletedListener[16]);
    private final MutableVector postponedMeasureRequests = new MutableVector(new PostponedRequest[16]);

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class PostponedRequest {
        private final boolean isForced;
        private final boolean isLookahead;
        private final LayoutNode node;

        public PostponedRequest(LayoutNode node, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(node, "node");
            this.node = node;
            this.isLookahead = z;
            this.isForced = z2;
        }

        public final LayoutNode getNode() {
            return this.node;
        }

        public final boolean isForced() {
            return this.isForced;
        }

        public final boolean isLookahead() {
            return this.isLookahead;
        }
    }

    public MeasureAndLayoutDelegate(LayoutNode layoutNode) {
        this.root = layoutNode;
    }

    /* renamed from: doLookaheadRemeasure-sdFAvZA  reason: not valid java name */
    private final boolean m247doLookaheadRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        layoutNode.getClass();
        return false;
    }

    /* renamed from: doRemeasure-sdFAvZA  reason: not valid java name */
    private final boolean m248doRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean m232remeasure_Sx5XlM$ui_release;
        if (constraints != null) {
            m232remeasure_Sx5XlM$ui_release = layoutNode.m232remeasure_Sx5XlM$ui_release(constraints);
        } else {
            m232remeasure_Sx5XlM$ui_release = layoutNode.m232remeasure_Sx5XlM$ui_release(layoutNode.layoutDelegate.m242getLastConstraintsDWUhwKw());
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if (m232remeasure_Sx5XlM$ui_release && parent$ui_release != null) {
            if (layoutNode.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InMeasureBlock) {
                requestRemeasure(parent$ui_release, false);
            } else if (layoutNode.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InLayoutBlock) {
                requestRelayout(parent$ui_release, false);
            }
        }
        return m232remeasure_Sx5XlM$ui_release;
    }

    private static boolean getCanAffectParentInLookahead(LayoutNode layoutNode) {
        boolean z;
        AlignmentLines alignmentLines;
        if (!layoutNode.getLookaheadLayoutPending$ui_release()) {
            return false;
        }
        if (layoutNode.getMeasuredByParentInLookahead$ui_release() != LayoutNode.UsageByParent.InMeasureBlock) {
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadAlignmentLinesOwner$ui_release = layoutNode.getLayoutDelegate$ui_release().getLookaheadAlignmentLinesOwner$ui_release();
            if (lookaheadAlignmentLinesOwner$ui_release != null && (alignmentLines = lookaheadAlignmentLinesOwner$ui_release.getAlignmentLines()) != null && alignmentLines.getRequired$ui_release()) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private static boolean getMeasureAffectsParent(LayoutNode layoutNode) {
        if (layoutNode.getMeasuredByParent$ui_release() != LayoutNode.UsageByParent.InMeasureBlock && !layoutNode.getLayoutDelegate$ui_release().getAlignmentLinesOwner$ui_release().getAlignmentLines().getRequired$ui_release()) {
            return false;
        }
        return true;
    }

    private final void recurseRemeasure(LayoutNode layoutNode) {
        remeasureOnly(layoutNode);
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) content[i];
                if (getMeasureAffectsParent(layoutNode2)) {
                    recurseRemeasure(layoutNode2);
                }
                i++;
            } while (i < size);
            remeasureOnly(layoutNode);
        }
        remeasureOnly(layoutNode);
    }

    private final boolean remeasureAndRelayoutIfNeeded(LayoutNode layoutNode) {
        Constraints constraints;
        boolean m248doRemeasuresdFAvZA;
        boolean z;
        int i = 0;
        if (!layoutNode.isPlaced()) {
            if (layoutNode.getMeasurePending$ui_release() && getMeasureAffectsParent(layoutNode)) {
                z = true;
            } else {
                z = false;
            }
            if (!z && !Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE) && !getCanAffectParentInLookahead(layoutNode) && !layoutNode.getAlignmentLinesRequired$ui_release()) {
                return false;
            }
        }
        boolean lookaheadMeasurePending$ui_release = layoutNode.getLookaheadMeasurePending$ui_release();
        LayoutNode layoutNode2 = this.root;
        if (!lookaheadMeasurePending$ui_release && !layoutNode.getMeasurePending$ui_release()) {
            m248doRemeasuresdFAvZA = false;
        } else {
            if (layoutNode == layoutNode2) {
                constraints = this.rootConstraints;
                Intrinsics.checkNotNull(constraints);
            } else {
                constraints = null;
            }
            layoutNode.getLookaheadMeasurePending$ui_release();
            m248doRemeasuresdFAvZA = m248doRemeasuresdFAvZA(layoutNode, constraints);
        }
        if (layoutNode.getLookaheadLayoutPending$ui_release() && Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE)) {
            layoutNode.lookaheadReplace$ui_release();
        }
        if (layoutNode.getLayoutPending$ui_release() && layoutNode.isPlaced()) {
            if (layoutNode == layoutNode2) {
                layoutNode.place$ui_release();
            } else {
                layoutNode.replace$ui_release();
            }
            this.onPositionedDispatcher.onNodePositioned(layoutNode);
        }
        MutableVector mutableVector = this.postponedMeasureRequests;
        if (mutableVector.isNotEmpty()) {
            int size = mutableVector.getSize();
            if (size > 0) {
                Object[] content = mutableVector.getContent();
                do {
                    PostponedRequest postponedRequest = (PostponedRequest) content[i];
                    if (postponedRequest.getNode().isAttached()) {
                        if (!postponedRequest.isLookahead()) {
                            requestRemeasure(postponedRequest.getNode(), postponedRequest.isForced());
                        } else {
                            requestLookaheadRemeasure(postponedRequest.getNode(), postponedRequest.isForced());
                            throw null;
                        }
                    }
                    i++;
                } while (i < size);
                mutableVector.clear();
            } else {
                mutableVector.clear();
            }
        }
        return m248doRemeasuresdFAvZA;
    }

    private final void remeasureOnly(LayoutNode layoutNode) {
        Constraints constraints;
        if (!layoutNode.getMeasurePending$ui_release() && !layoutNode.getLookaheadMeasurePending$ui_release()) {
            return;
        }
        if (layoutNode == this.root) {
            constraints = this.rootConstraints;
            Intrinsics.checkNotNull(constraints);
        } else {
            constraints = null;
        }
        if (layoutNode.getLookaheadMeasurePending$ui_release()) {
            m247doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
        }
        m248doRemeasuresdFAvZA(layoutNode, constraints);
    }

    public final void dispatchOnPositionedCallbacks(boolean z) {
        OnPositionedDispatcher onPositionedDispatcher = this.onPositionedDispatcher;
        if (z) {
            onPositionedDispatcher.onRootNodePositioned(this.root);
        }
        onPositionedDispatcher.dispatch();
    }

    public final void forceMeasureTheSubtree(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        DepthSortedSet depthSortedSet = this.relayoutNodes;
        if (depthSortedSet.isEmpty()) {
            return;
        }
        if (this.duringMeasureLayout) {
            if (!layoutNode.getMeasurePending$ui_release()) {
                MutableVector mutableVector = layoutNode.get_children$ui_release();
                int size = mutableVector.getSize();
                if (size > 0) {
                    Object[] content = mutableVector.getContent();
                    int i = 0;
                    do {
                        LayoutNode layoutNode2 = (LayoutNode) content[i];
                        if (layoutNode2.getMeasurePending$ui_release() && depthSortedSet.remove(layoutNode2)) {
                            remeasureAndRelayoutIfNeeded(layoutNode2);
                        }
                        if (!layoutNode2.getMeasurePending$ui_release()) {
                            forceMeasureTheSubtree(layoutNode2);
                        }
                        i++;
                    } while (i < size);
                    if (!layoutNode.getMeasurePending$ui_release() && depthSortedSet.remove(layoutNode)) {
                        remeasureAndRelayoutIfNeeded(layoutNode);
                        return;
                    }
                    return;
                } else if (!layoutNode.getMeasurePending$ui_release()) {
                    return;
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v2, types: [int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    public final boolean measureAndLayout(Function0 function0) {
        boolean z;
        int i;
        DepthSortedSet depthSortedSet = this.relayoutNodes;
        LayoutNode layoutNode = this.root;
        if (layoutNode.isAttached()) {
            if (layoutNode.isPlaced()) {
                if (!this.duringMeasureLayout) {
                    ?? r3 = 0;
                    if (this.rootConstraints != null) {
                        this.duringMeasureLayout = true;
                        try {
                            if (!depthSortedSet.isEmpty()) {
                                z = false;
                                while (!depthSortedSet.isEmpty()) {
                                    LayoutNode pop = depthSortedSet.pop();
                                    boolean remeasureAndRelayoutIfNeeded = remeasureAndRelayoutIfNeeded(pop);
                                    if (pop == layoutNode && remeasureAndRelayoutIfNeeded) {
                                        z = true;
                                    }
                                }
                                if (function0 != null) {
                                    function0.invoke();
                                }
                            } else {
                                z = false;
                            }
                        } finally {
                            this.duringMeasureLayout = false;
                        }
                    } else {
                        z = false;
                    }
                    MutableVector mutableVector = this.onLayoutCompletedListeners;
                    int size = mutableVector.getSize();
                    if (size > 0) {
                        Object[] content = mutableVector.getContent();
                        do {
                            ((LayoutNode) ((Owner.OnLayoutCompletedListener) content[r3])).onLayoutComplete();
                            i = r3 + 1;
                            r3 = i;
                        } while (i < size);
                        mutableVector.clear();
                        return z;
                    }
                    mutableVector.clear();
                    return z;
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public final void measureOnly() {
        LayoutNode layoutNode = this.root;
        if (layoutNode.isAttached()) {
            if (layoutNode.isPlaced()) {
                if (!this.duringMeasureLayout) {
                    if (this.rootConstraints != null) {
                        this.duringMeasureLayout = true;
                        try {
                            recurseRemeasure(layoutNode);
                            return;
                        } finally {
                            this.duringMeasureLayout = false;
                        }
                    }
                    return;
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public final void onNodeDetached(LayoutNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.relayoutNodes.remove(node);
    }

    public final boolean requestLookaheadRelayout(LayoutNode layoutNode, boolean z) {
        boolean z2;
        boolean z3;
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        int ordinal = layoutNode.getLayoutState$ui_release().ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        if (ordinal != 4) {
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                }
            }
            return false;
        }
        if ((!layoutNode.getLookaheadMeasurePending$ui_release() && !layoutNode.getLookaheadLayoutPending$ui_release()) || z) {
            layoutNode.markLookaheadLayoutPending$ui_release();
            layoutNode.markLayoutPending$ui_release();
            if (Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE)) {
                LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                if (parent$ui_release != null && parent$ui_release.getLookaheadMeasurePending$ui_release()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z2) {
                    if (parent$ui_release != null && parent$ui_release.getLookaheadLayoutPending$ui_release()) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (!z3) {
                        this.relayoutNodes.add(layoutNode);
                    }
                }
            }
            if (!this.duringMeasureLayout) {
                return true;
            }
        }
        return false;
    }

    public final boolean requestLookaheadRemeasure(LayoutNode layoutNode, boolean z) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        throw new IllegalStateException("Error: requestLookaheadRemeasure cannot be called on a node outside LookaheadLayout".toString());
    }

    public final void requestOnPositionedCallback(LayoutNode layoutNode) {
        this.onPositionedDispatcher.onNodePositioned(layoutNode);
    }

    public final boolean requestRelayout(LayoutNode layoutNode, boolean z) {
        boolean z2;
        boolean z3;
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        int ordinal = layoutNode.getLayoutState$ui_release().ordinal();
        if (ordinal == 0 || ordinal == 1 || ordinal == 2 || ordinal == 3) {
            return false;
        }
        if (ordinal == 4) {
            if (!z && (layoutNode.getMeasurePending$ui_release() || layoutNode.getLayoutPending$ui_release())) {
                return false;
            }
            layoutNode.markLayoutPending$ui_release();
            if (layoutNode.isPlaced()) {
                LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                if (parent$ui_release != null && parent$ui_release.getLayoutPending$ui_release()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z2) {
                    if (parent$ui_release != null && parent$ui_release.getMeasurePending$ui_release()) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (!z3) {
                        this.relayoutNodes.add(layoutNode);
                    }
                }
            }
            if (this.duringMeasureLayout) {
                return false;
            }
            return true;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x003d, code lost:
        if (r6 != false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean requestRemeasure(androidx.compose.ui.node.LayoutNode r5, boolean r6) {
        /*
            r4 = this;
            java.lang.String r0 = "layoutNode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            androidx.compose.ui.node.LayoutNode$LayoutState r0 = r5.getLayoutState$ui_release()
            int r0 = r0.ordinal()
            r1 = 0
            if (r0 == 0) goto L6b
            r2 = 1
            if (r0 == r2) goto L6b
            r3 = 2
            if (r0 == r3) goto L61
            r3 = 3
            if (r0 == r3) goto L61
            r3 = 4
            if (r0 != r3) goto L5b
            boolean r0 = r5.getMeasurePending$ui_release()
            if (r0 == 0) goto L25
            if (r6 != 0) goto L25
            goto L6b
        L25:
            r5.markMeasurePending$ui_release()
            boolean r6 = r5.isPlaced()
            if (r6 != 0) goto L3f
            boolean r6 = r5.getMeasurePending$ui_release()
            if (r6 == 0) goto L3c
            boolean r6 = getMeasureAffectsParent(r5)
            if (r6 == 0) goto L3c
            r6 = r2
            goto L3d
        L3c:
            r6 = r1
        L3d:
            if (r6 == 0) goto L55
        L3f:
            androidx.compose.ui.node.LayoutNode r6 = r5.getParent$ui_release()
            if (r6 == 0) goto L4d
            boolean r6 = r6.getMeasurePending$ui_release()
            if (r6 != r2) goto L4d
            r6 = r2
            goto L4e
        L4d:
            r6 = r1
        L4e:
            if (r6 != 0) goto L55
            androidx.compose.ui.node.DepthSortedSet r6 = r4.relayoutNodes
            r6.add(r5)
        L55:
            boolean r4 = r4.duringMeasureLayout
            if (r4 != 0) goto L6b
            r1 = r2
            goto L6b
        L5b:
            kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
            r4.<init>()
            throw r4
        L61:
            androidx.compose.ui.node.MeasureAndLayoutDelegate$PostponedRequest r0 = new androidx.compose.ui.node.MeasureAndLayoutDelegate$PostponedRequest
            r0.<init>(r5, r1, r6)
            androidx.compose.runtime.collection.MutableVector r4 = r4.postponedMeasureRequests
            r4.add(r0)
        L6b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.MeasureAndLayoutDelegate.requestRemeasure(androidx.compose.ui.node.LayoutNode, boolean):boolean");
    }

    /* renamed from: updateRootConstraints-BRTryo0  reason: not valid java name */
    public final void m249updateRootConstraintsBRTryo0(long j) {
        boolean m377equalsimpl0;
        Constraints constraints = this.rootConstraints;
        if (constraints == null) {
            m377equalsimpl0 = false;
        } else {
            m377equalsimpl0 = Constraints.m377equalsimpl0(constraints.m385unboximpl(), j);
        }
        if (!m377equalsimpl0) {
            if (!this.duringMeasureLayout) {
                this.rootConstraints = Constraints.m376boximpl(j);
                LayoutNode layoutNode = this.root;
                layoutNode.markMeasurePending$ui_release();
                this.relayoutNodes.add(layoutNode);
                return;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }
}
