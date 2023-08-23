package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class OnPositionedDispatcher {
    private final MutableVector layoutNodes = new MutableVector(new LayoutNode[16]);

    private static void dispatchHierarchy(LayoutNode layoutNode) {
        layoutNode.dispatchOnPositionedCallbacks$ui_release();
        int i = 0;
        layoutNode.setNeedsOnPositionedDispatch$ui_release(false);
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            do {
                dispatchHierarchy((LayoutNode) content[i]);
                i++;
            } while (i < size);
        }
    }

    public final void dispatch() {
        OnPositionedDispatcher$Companion$DepthComparator onPositionedDispatcher$Companion$DepthComparator = OnPositionedDispatcher$Companion$DepthComparator.INSTANCE;
        MutableVector mutableVector = this.layoutNodes;
        mutableVector.sortWith(onPositionedDispatcher$Companion$DepthComparator);
        int size = mutableVector.getSize();
        if (size > 0) {
            int i = size - 1;
            Object[] content = mutableVector.getContent();
            do {
                LayoutNode layoutNode = (LayoutNode) content[i];
                if (layoutNode.getNeedsOnPositionedDispatch$ui_release()) {
                    dispatchHierarchy(layoutNode);
                }
                i--;
            } while (i >= 0);
            mutableVector.clear();
        }
        mutableVector.clear();
    }

    public final void onNodePositioned(LayoutNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.layoutNodes.add(node);
        node.setNeedsOnPositionedDispatch$ui_release(true);
    }

    public final void onRootNodePositioned(LayoutNode rootNode) {
        Intrinsics.checkNotNullParameter(rootNode, "rootNode");
        MutableVector mutableVector = this.layoutNodes;
        mutableVector.clear();
        mutableVector.add(rootNode);
        rootNode.setNeedsOnPositionedDispatch$ui_release(true);
    }
}
