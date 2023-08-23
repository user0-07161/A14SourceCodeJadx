package androidx.compose.ui.focus;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class FocusTraversalKt {
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0048, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.focus.FocusTargetModifierNode findActiveFocusNode(androidx.compose.ui.focus.FocusTargetModifierNode r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            androidx.compose.ui.focus.FocusStateImpl r0 = r4.getFocusStateImpl$ui_release()
            int r0 = r0.ordinal()
            if (r0 == 0) goto L8e
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L20
            r2 = 2
            if (r0 == r2) goto L8e
            r4 = 3
            if (r0 != r4) goto L1a
            return r1
        L1a:
            kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
            r4.<init>()
            throw r4
        L20:
            androidx.compose.ui.Modifier$Node r0 = r4.getNode()
            boolean r0 = r0.isAttached()
            if (r0 == 0) goto L82
            androidx.compose.runtime.collection.MutableVector r0 = new androidx.compose.runtime.collection.MutableVector
            r3 = 16
            androidx.compose.ui.Modifier$Node[] r3 = new androidx.compose.ui.Modifier.Node[r3]
            r0.<init>(r3)
            androidx.compose.ui.Modifier$Node r3 = r4.getNode()
            androidx.compose.ui.Modifier$Node r3 = r3.getChild$ui_release()
            if (r3 != 0) goto L45
            androidx.compose.ui.Modifier$Node r4 = r4.getNode()
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r0, r4)
            goto L48
        L45:
            r0.add(r3)
        L48:
            boolean r4 = r0.isNotEmpty()
            if (r4 == 0) goto L81
            int r4 = r0.getSize()
            int r4 = r4 - r2
            java.lang.Object r4 = r0.removeAt(r4)
            androidx.compose.ui.Modifier$Node r4 = (androidx.compose.ui.Modifier.Node) r4
            int r3 = r4.getAggregateChildKindSet$ui_release()
            r3 = r3 & 1024(0x400, float:1.435E-42)
            if (r3 != 0) goto L65
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r0, r4)
            goto L48
        L65:
            if (r4 == 0) goto L48
            int r3 = r4.getKindSet$ui_release()
            r3 = r3 & 1024(0x400, float:1.435E-42)
            if (r3 == 0) goto L7c
            boolean r3 = r4 instanceof androidx.compose.ui.focus.FocusTargetModifierNode
            if (r3 == 0) goto L48
            androidx.compose.ui.focus.FocusTargetModifierNode r4 = (androidx.compose.ui.focus.FocusTargetModifierNode) r4
            androidx.compose.ui.focus.FocusTargetModifierNode r4 = findActiveFocusNode(r4)
            if (r4 == 0) goto L48
            return r4
        L7c:
            androidx.compose.ui.Modifier$Node r4 = r4.getChild$ui_release()
            goto L65
        L81:
            return r1
        L82:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            throw r4
        L8e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTraversalKt.findActiveFocusNode(androidx.compose.ui.focus.FocusTargetModifierNode):androidx.compose.ui.focus.FocusTargetModifierNode");
    }

    public static final Rect focusRect(FocusTargetModifierNode focusTargetModifierNode) {
        Rect rect;
        Rect localBoundingBoxOf;
        Intrinsics.checkNotNullParameter(focusTargetModifierNode, "<this>");
        NodeCoordinator coordinator$ui_release = focusTargetModifierNode.getCoordinator$ui_release();
        if (coordinator$ui_release == null || (localBoundingBoxOf = LayoutCoordinatesKt.findRootCoordinates(coordinator$ui_release).localBoundingBoxOf(coordinator$ui_release, false)) == null) {
            rect = Rect.Zero;
            return rect;
        }
        return localBoundingBoxOf;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0039, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.focus.FocusTargetModifierNode getActiveChild(androidx.compose.ui.focus.FocusTargetModifierNode r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            androidx.compose.ui.Modifier$Node r0 = r4.getNode()
            boolean r0 = r0.isAttached()
            r1 = 0
            if (r0 != 0) goto L11
            return r1
        L11:
            androidx.compose.ui.Modifier$Node r0 = r4.getNode()
            boolean r0 = r0.isAttached()
            if (r0 == 0) goto L7e
            androidx.compose.runtime.collection.MutableVector r0 = new androidx.compose.runtime.collection.MutableVector
            r2 = 16
            androidx.compose.ui.Modifier$Node[] r2 = new androidx.compose.ui.Modifier.Node[r2]
            r0.<init>(r2)
            androidx.compose.ui.Modifier$Node r2 = r4.getNode()
            androidx.compose.ui.Modifier$Node r2 = r2.getChild$ui_release()
            if (r2 != 0) goto L36
            androidx.compose.ui.Modifier$Node r4 = r4.getNode()
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r0, r4)
            goto L39
        L36:
            r0.add(r2)
        L39:
            boolean r4 = r0.isNotEmpty()
            if (r4 == 0) goto L7d
            int r4 = r0.getSize()
            r2 = 1
            int r4 = r4 - r2
            java.lang.Object r4 = r0.removeAt(r4)
            androidx.compose.ui.Modifier$Node r4 = (androidx.compose.ui.Modifier.Node) r4
            int r3 = r4.getAggregateChildKindSet$ui_release()
            r3 = r3 & 1024(0x400, float:1.435E-42)
            if (r3 != 0) goto L57
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r0, r4)
            goto L39
        L57:
            if (r4 == 0) goto L39
            int r3 = r4.getKindSet$ui_release()
            r3 = r3 & 1024(0x400, float:1.435E-42)
            if (r3 == 0) goto L78
            boolean r3 = r4 instanceof androidx.compose.ui.focus.FocusTargetModifierNode
            if (r3 == 0) goto L39
            androidx.compose.ui.focus.FocusTargetModifierNode r4 = (androidx.compose.ui.focus.FocusTargetModifierNode) r4
            androidx.compose.ui.focus.FocusStateImpl r3 = r4.getFocusStateImpl$ui_release()
            int r3 = r3.ordinal()
            if (r3 == 0) goto L77
            if (r3 == r2) goto L77
            r2 = 2
            if (r3 == r2) goto L77
            goto L39
        L77:
            return r4
        L78:
            androidx.compose.ui.Modifier$Node r4 = r4.getChild$ui_release()
            goto L57
        L7d:
            return r1
        L7e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTraversalKt.getActiveChild(androidx.compose.ui.focus.FocusTargetModifierNode):androidx.compose.ui.focus.FocusTargetModifierNode");
    }

    public static final boolean isEligibleForFocusSearch(FocusTargetModifierNode focusTargetModifierNode) {
        boolean z;
        boolean z2;
        LayoutNode layoutNode;
        LayoutNode layoutNode2;
        Intrinsics.checkNotNullParameter(focusTargetModifierNode, "<this>");
        NodeCoordinator coordinator$ui_release = focusTargetModifierNode.getCoordinator$ui_release();
        if (coordinator$ui_release != null && (layoutNode2 = coordinator$ui_release.getLayoutNode()) != null && layoutNode2.isPlaced()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            NodeCoordinator coordinator$ui_release2 = focusTargetModifierNode.getCoordinator$ui_release();
            if (coordinator$ui_release2 != null && (layoutNode = coordinator$ui_release2.getLayoutNode()) != null && layoutNode.isAttached()) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                return true;
            }
        }
        return false;
    }
}
