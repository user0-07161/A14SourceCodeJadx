package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class FocusEventModifierNodeKt {
    /* JADX WARN: Code restructure failed: missing block: B:39:0x002f, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.focus.FocusStateImpl getFocusState(androidx.compose.ui.focus.FocusEventModifierNode r3) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            androidx.compose.ui.Modifier$Node r3 = (androidx.compose.ui.Modifier.Node) r3
            androidx.compose.ui.Modifier$Node r0 = r3.getNode()
            boolean r0 = r0.isAttached()
            if (r0 == 0) goto L76
            androidx.compose.runtime.collection.MutableVector r0 = new androidx.compose.runtime.collection.MutableVector
            r1 = 16
            androidx.compose.ui.Modifier$Node[] r1 = new androidx.compose.ui.Modifier.Node[r1]
            r0.<init>(r1)
            androidx.compose.ui.Modifier$Node r1 = r3.getNode()
            androidx.compose.ui.Modifier$Node r1 = r1.getChild$ui_release()
            if (r1 != 0) goto L2c
            androidx.compose.ui.Modifier$Node r3 = r3.getNode()
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r0, r3)
            goto L2f
        L2c:
            r0.add(r1)
        L2f:
            boolean r3 = r0.isNotEmpty()
            if (r3 == 0) goto L73
            int r3 = r0.getSize()
            r1 = 1
            int r3 = r3 - r1
            java.lang.Object r3 = r0.removeAt(r3)
            androidx.compose.ui.Modifier$Node r3 = (androidx.compose.ui.Modifier.Node) r3
            int r2 = r3.getAggregateChildKindSet$ui_release()
            r2 = r2 & 1024(0x400, float:1.435E-42)
            if (r2 != 0) goto L4d
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r0, r3)
            goto L2f
        L4d:
            if (r3 == 0) goto L2f
            int r2 = r3.getKindSet$ui_release()
            r2 = r2 & 1024(0x400, float:1.435E-42)
            if (r2 == 0) goto L6e
            boolean r2 = r3 instanceof androidx.compose.ui.focus.FocusTargetModifierNode
            if (r2 == 0) goto L2f
            androidx.compose.ui.focus.FocusTargetModifierNode r3 = (androidx.compose.ui.focus.FocusTargetModifierNode) r3
            androidx.compose.ui.focus.FocusStateImpl r3 = r3.getFocusStateImpl$ui_release()
            int r2 = r3.ordinal()
            if (r2 == 0) goto L6d
            if (r2 == r1) goto L6d
            r1 = 2
            if (r2 == r1) goto L6d
            goto L2f
        L6d:
            return r3
        L6e:
            androidx.compose.ui.Modifier$Node r3 = r3.getChild$ui_release()
            goto L4d
        L73:
            androidx.compose.ui.focus.FocusStateImpl r3 = androidx.compose.ui.focus.FocusStateImpl.Inactive
            return r3
        L76:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusEventModifierNodeKt.getFocusState(androidx.compose.ui.focus.FocusEventModifierNode):androidx.compose.ui.focus.FocusStateImpl");
    }

    public static final void refreshFocusEventNodes(FocusTargetModifierNode focusTargetModifierNode) {
        NodeChain nodes$ui_release;
        boolean z;
        Intrinsics.checkNotNullParameter(focusTargetModifierNode, "<this>");
        if (focusTargetModifierNode.getNode().isAttached()) {
            Modifier.Node parent$ui_release = focusTargetModifierNode.getNode().getParent$ui_release();
            LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetModifierNode);
            while (requireLayoutNode != null) {
                if ((requireLayoutNode.getNodes$ui_release().getHead$ui_release().getAggregateChildKindSet$ui_release() & 5120) != 0) {
                    while (parent$ui_release != null) {
                        if ((parent$ui_release.getKindSet$ui_release() & 5120) != 0) {
                            if ((parent$ui_release.getKindSet$ui_release() & 1024) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z) {
                                return;
                            }
                            if (!(parent$ui_release instanceof FocusEventModifierNode)) {
                                throw new IllegalStateException("Check failed.".toString());
                            }
                            getFocusState((FocusEventModifierNode) parent$ui_release);
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        parent$ui_release = parent$ui_release.getParent$ui_release();
                    }
                }
                requireLayoutNode = requireLayoutNode.getParent$ui_release();
                if (requireLayoutNode != null && (nodes$ui_release = requireLayoutNode.getNodes$ui_release()) != null) {
                    parent$ui_release = nodes$ui_release.getTail$ui_release();
                } else {
                    parent$ui_release = null;
                }
            }
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }
}
