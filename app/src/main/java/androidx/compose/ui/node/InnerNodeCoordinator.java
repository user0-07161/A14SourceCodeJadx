package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class InnerNodeCoordinator extends NodeCoordinator {
    private static final AndroidPaint innerBoundsPaint;
    private final InnerNodeCoordinator$tail$1 tail;

    static {
        long j;
        AndroidPaint Paint = AndroidPaint_androidKt.Paint();
        Color.Companion companion = Color.Companion;
        j = Color.Red;
        Paint.m82setColor8_81llA(j);
        Paint.setStrokeWidth(1.0f);
        Paint.m86setStylek9PVt8s(1);
        innerBoundsPaint = Paint;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InnerNodeCoordinator(LayoutNode layoutNode) {
        super(layoutNode);
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        InnerNodeCoordinator$tail$1 innerNodeCoordinator$tail$1 = new InnerNodeCoordinator$tail$1();
        this.tail = innerNodeCoordinator$tail$1;
        innerNodeCoordinator$tail$1.updateCoordinator$ui_release(this);
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
        Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
        LookaheadDelegate lookaheadDelegate$ui_release = getLookaheadDelegate$ui_release();
        if (lookaheadDelegate$ui_release != null) {
            return lookaheadDelegate$ui_release.calculateAlignmentLine(alignmentLine);
        }
        Integer num = (Integer) ((LayoutNodeLayoutDelegate.MeasurePassDelegate) getAlignmentLinesOwner()).calculateAlignmentLines().get(alignmentLine);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final Modifier.Node getTail() {
        return this.tail;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    @Override // androidx.compose.ui.node.NodeCoordinator
    /* renamed from: hitTestChild-YqVAtuI  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mo228hitTestChildYqVAtuI(androidx.compose.ui.node.NodeCoordinator.HitTestSource r18, long r19, androidx.compose.ui.node.HitTestResult r21, boolean r22, boolean r23) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r6 = r19
            r8 = r21
            java.lang.String r2 = "hitTestSource"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            java.lang.String r2 = "hitTestResult"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r2)
            androidx.compose.ui.node.LayoutNode r2 = r17.getLayoutNode()
            androidx.compose.ui.node.NodeCoordinator$Companion$SemanticsSource$1 r1 = (androidx.compose.ui.node.NodeCoordinator$Companion$SemanticsSource$1) r1
            r9 = 1
            r10 = 0
            int r11 = r1.$r8$classId
            java.lang.String r1 = "parentLayoutNode"
            switch(r11) {
                case 0: goto L22;
                default: goto L21;
            }
        L21:
            goto L3c
        L22:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            androidx.compose.ui.node.SemanticsModifierNode r1 = androidx.compose.ui.semantics.SemanticsNodeKt.getOuterSemantics(r2)
            if (r1 == 0) goto L39
            androidx.compose.ui.semantics.SemanticsConfiguration r1 = androidx.compose.ui.node.SemanticsModifierNodeKt.collapsedSemanticsConfiguration(r1)
            if (r1 == 0) goto L39
            boolean r1 = r1.isClearingSemantics()
            if (r1 != r9) goto L39
            r1 = r9
            goto L3a
        L39:
            r1 = r10
        L3a:
            r1 = r1 ^ r9
            goto L40
        L3c:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            r1 = r9
        L40:
            if (r1 == 0) goto L6a
            boolean r1 = r0.m265withinLayerBoundsk4lQ0M(r6)
            if (r1 == 0) goto L4c
            r12 = r23
            r1 = r9
            goto L6d
        L4c:
            if (r22 == 0) goto L6a
            long r1 = r17.m260getMinimumTouchTargetSizeNHjbRc()
            float r1 = r0.m258distanceInMinimumTouchTargettz77jQw(r6, r1)
            boolean r2 = java.lang.Float.isInfinite(r1)
            if (r2 != 0) goto L64
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L64
            r1 = r9
            goto L65
        L64:
            r1 = r10
        L65:
            if (r1 == 0) goto L6a
            r1 = r9
            r12 = r10
            goto L6d
        L6a:
            r12 = r23
            r1 = r10
        L6d:
            if (r1 == 0) goto Lc7
            int r13 = androidx.compose.ui.node.HitTestResult.access$getHitDepth$p(r21)
            androidx.compose.ui.node.LayoutNode r0 = r17.getLayoutNode()
            androidx.compose.runtime.collection.MutableVector r0 = r0.getZSortedChildren()
            int r1 = r0.getSize()
            if (r1 <= 0) goto Lc4
            int r1 = r1 - r9
            java.lang.Object[] r14 = r0.getContent()
            r15 = r1
        L87:
            r0 = r14[r15]
            r5 = r0
            androidx.compose.ui.node.LayoutNode r5 = (androidx.compose.ui.node.LayoutNode) r5
            boolean r0 = r5.isPlaced()
            if (r0 == 0) goto Lbd
            switch(r11) {
                case 0: goto L96;
                default: goto L95;
            }
        L95:
            goto L9c
        L96:
            r5.m231hitTestSemanticsM_7yMNQ$ui_release(r6, r8, r12)
            r16 = r5
            goto La9
        L9c:
            r0 = r5
            r1 = r19
            r3 = r21
            r4 = r22
            r16 = r5
            r5 = r12
            r0.m230hitTestM_7yMNQ$ui_release(r1, r3, r4, r5)
        La9:
            boolean r0 = r21.hasHit()
            if (r0 != 0) goto Lb1
            r0 = r9
            goto Lb9
        Lb1:
            androidx.compose.ui.node.NodeCoordinator r0 = r16.getOuterCoordinator$ui_release()
            r0.shouldSharePointerInputWithSiblings()
            r0 = r10
        Lb9:
            if (r0 != 0) goto Lbd
            r0 = r9
            goto Lbe
        Lbd:
            r0 = r10
        Lbe:
            if (r0 != 0) goto Lc4
            int r15 = r15 + (-1)
            if (r15 >= 0) goto L87
        Lc4:
            androidx.compose.ui.node.HitTestResult.access$setHitDepth$p(r8, r13)
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.InnerNodeCoordinator.mo228hitTestChildYqVAtuI(androidx.compose.ui.node.NodeCoordinator$HitTestSource, long, androidx.compose.ui.node.HitTestResult, boolean, boolean):void");
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0 */
    public final Placeable mo210measureBRTryo0(long j) {
        m218setMeasurementConstraintsBRTryo0(j);
        MutableVector mutableVector = getLayoutNode().get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                ((LayoutNode) content[i]).setMeasuredByParent$ui_release(LayoutNode.UsageByParent.NotUsed);
                i++;
            } while (i < size);
            setMeasureResult$ui_release(getLayoutNode().getMeasurePolicy().mo1measure3p2s80s(this, getLayoutNode().getChildMeasurables$ui_release(), j));
            onMeasured();
            return this;
        }
        setMeasureResult$ui_release(getLayoutNode().getMeasurePolicy().mo1measure3p2s80s(this, getLayoutNode().getChildMeasurables$ui_release(), j));
        onMeasured();
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // androidx.compose.ui.node.NodeCoordinator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void performDraw(androidx.compose.ui.graphics.Canvas r7) {
        /*
            r6 = this;
            java.lang.String r0 = "canvas"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            androidx.compose.ui.node.LayoutNode r0 = r6.getLayoutNode()
            androidx.compose.ui.node.Owner r0 = androidx.compose.ui.node.LayoutNodeKt.requireOwner(r0)
            androidx.compose.ui.node.LayoutNode r1 = r6.getLayoutNode()
            androidx.compose.runtime.collection.MutableVector r1 = r1.getZSortedChildren()
            int r2 = r1.getSize()
            if (r2 <= 0) goto L31
            java.lang.Object[] r1 = r1.getContent()
            r3 = 0
        L20:
            r4 = r1[r3]
            androidx.compose.ui.node.LayoutNode r4 = (androidx.compose.ui.node.LayoutNode) r4
            boolean r5 = r4.isPlaced()
            if (r5 == 0) goto L2d
            r4.draw$ui_release(r7)
        L2d:
            int r3 = r3 + 1
            if (r3 < r2) goto L20
        L31:
            androidx.compose.ui.platform.AndroidComposeView r0 = (androidx.compose.ui.platform.AndroidComposeView) r0
            boolean r0 = r0.getShowLayoutBounds()
            if (r0 == 0) goto L3e
            androidx.compose.ui.graphics.AndroidPaint r0 = androidx.compose.ui.node.InnerNodeCoordinator.innerBoundsPaint
            r6.drawBorder(r7, r0)
        L3e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.InnerNodeCoordinator.performDraw(androidx.compose.ui.graphics.Canvas):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.node.NodeCoordinator, androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public final void mo216placeAtf8xVGno(long j, float f, Function1 function1) {
        super.mo216placeAtf8xVGno(j, f, function1);
        if (isShallowPlacing$ui_release()) {
            return;
        }
        onPlaced$1();
        getLayoutNode().onNodePlaced$ui_release();
    }
}
