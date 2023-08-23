package androidx.compose.ui.semantics;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.SemanticsModifierNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SemanticsNodeKt {
    private static final void findOneLayerOfSemanticsWrappers(LayoutNode layoutNode, List list) {
        MutableVector zSortedChildren = layoutNode.getZSortedChildren();
        int size = zSortedChildren.getSize();
        if (size > 0) {
            Object[] content = zSortedChildren.getContent();
            int i = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) content[i];
                SemanticsModifierNode outerSemantics = getOuterSemantics(layoutNode2);
                if (outerSemantics != null) {
                    list.add(outerSemantics);
                } else {
                    findOneLayerOfSemanticsWrappers(layoutNode2, list);
                }
                i++;
            } while (i < size);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List findOneLayerOfSemanticsWrappers$default(LayoutNode layoutNode) {
        ArrayList arrayList = new ArrayList();
        findOneLayerOfSemanticsWrappers(layoutNode, arrayList);
        return arrayList;
    }

    public static final SemanticsModifierNode getOuterMergingSemantics(LayoutNode layoutNode) {
        Modifier.Node node;
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        NodeChain nodes$ui_release = layoutNode.getNodes$ui_release();
        if ((NodeChain.access$getAggregateChildKindSet(nodes$ui_release) & 8) != 0) {
            node = nodes$ui_release.getHead$ui_release();
            while (node != null) {
                if ((node.getKindSet$ui_release() & 8) == 0 || !(node instanceof SemanticsModifierNode) || !((SemanticsModifierNode) node).getSemanticsConfiguration().isMergingSemanticsOfDescendants()) {
                    if ((node.getAggregateChildKindSet$ui_release() & 8) == 0) {
                        break;
                    }
                    node = node.getChild$ui_release();
                } else {
                    break;
                }
            }
        }
        node = null;
        return (SemanticsModifierNode) node;
    }

    public static final SemanticsModifierNode getOuterSemantics(LayoutNode layoutNode) {
        Modifier.Node node;
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        NodeChain nodes$ui_release = layoutNode.getNodes$ui_release();
        if ((NodeChain.access$getAggregateChildKindSet(nodes$ui_release) & 8) != 0) {
            node = nodes$ui_release.getHead$ui_release();
            while (node != null) {
                if ((node.getKindSet$ui_release() & 8) != 0 && (node instanceof SemanticsModifierNode)) {
                    break;
                } else if ((node.getAggregateChildKindSet$ui_release() & 8) == 0) {
                    break;
                } else {
                    node = node.getChild$ui_release();
                }
            }
        }
        node = null;
        return (SemanticsModifierNode) node;
    }
}
