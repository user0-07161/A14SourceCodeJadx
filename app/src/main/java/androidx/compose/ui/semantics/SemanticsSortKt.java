package androidx.compose.ui.semantics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.NodeLocationHolder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SemanticsSortKt {
    public static final NodeCoordinator findCoordinatorToGetBounds(LayoutNode layoutNode) {
        Modifier.Node node;
        NodeCoordinator coordinator$ui_release;
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        SemanticsModifierNode outerMergingSemantics = SemanticsNodeKt.getOuterMergingSemantics(layoutNode);
        if (outerMergingSemantics == null) {
            outerMergingSemantics = SemanticsNodeKt.getOuterSemantics(layoutNode);
        }
        if (outerMergingSemantics == null || (node = ((Modifier.Node) outerMergingSemantics).getNode()) == null || (coordinator$ui_release = node.getCoordinator$ui_release()) == null) {
            return layoutNode.getInnerCoordinator$ui_release();
        }
        return coordinator$ui_release;
    }

    public static final LayoutNode findNodeByPredicateTraversal(LayoutNode layoutNode, Function1 function1) {
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        if (((Boolean) function1.invoke(layoutNode)).booleanValue()) {
            return layoutNode;
        }
        List children$ui_release = layoutNode.getChildren$ui_release();
        int size = children$ui_release.size();
        for (int i = 0; i < size; i++) {
            LayoutNode findNodeByPredicateTraversal = findNodeByPredicateTraversal((LayoutNode) children$ui_release.get(i), function1);
            if (findNodeByPredicateTraversal != null) {
                return findNodeByPredicateTraversal;
            }
        }
        return null;
    }

    public static final void findOneLayerOfSemanticsWrappersSortedByBounds(LayoutNode layoutNode, List list) {
        List mutableList;
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        Intrinsics.checkNotNullParameter(list, "list");
        if (!layoutNode.isAttached()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        List children$ui_release = layoutNode.getChildren$ui_release();
        int size = children$ui_release.size();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode2 = (LayoutNode) children$ui_release.get(i);
            if (layoutNode2.isAttached()) {
                arrayList.add(new NodeLocationHolder(layoutNode, layoutNode2));
            }
        }
        try {
            NodeLocationHolder.access$setComparisonStrategy$cp(NodeLocationHolder.ComparisonStrategy.Stripe);
            mutableList = CollectionsKt.toMutableList(arrayList);
            if (((ArrayList) mutableList).size() > 1) {
                Collections.sort(mutableList);
            }
        } catch (IllegalArgumentException unused) {
            NodeLocationHolder.access$setComparisonStrategy$cp(NodeLocationHolder.ComparisonStrategy.Location);
            mutableList = CollectionsKt.toMutableList(arrayList);
            if (((ArrayList) mutableList).size() > 1) {
                Collections.sort(mutableList);
            }
        }
        ArrayList arrayList2 = new ArrayList(mutableList.size());
        int size2 = mutableList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            arrayList2.add(((NodeLocationHolder) mutableList.get(i2)).getNode$ui_release());
        }
        int size3 = arrayList2.size();
        for (int i3 = 0; i3 < size3; i3++) {
            LayoutNode layoutNode3 = (LayoutNode) arrayList2.get(i3);
            SemanticsModifierNode outerSemantics = SemanticsNodeKt.getOuterSemantics(layoutNode3);
            if (outerSemantics != null) {
                list.add(outerSemantics);
            } else {
                findOneLayerOfSemanticsWrappersSortedByBounds(layoutNode3, list);
            }
        }
    }
}
