package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.PointerInputModifierNode;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class HitPathTracker {
    private final NodeParent root;
    private final LayoutCoordinates rootCoordinates;

    public HitPathTracker(InnerNodeCoordinator rootCoordinates) {
        Intrinsics.checkNotNullParameter(rootCoordinates, "rootCoordinates");
        this.rootCoordinates = rootCoordinates;
        this.root = new NodeParent();
    }

    /* renamed from: addHitPath-KNwqfcY  reason: not valid java name */
    public final void m179addHitPathKNwqfcY(long j, HitTestResult pointerInputNodes) {
        Node node;
        Intrinsics.checkNotNullParameter(pointerInputNodes, "pointerInputNodes");
        Node node2 = this.root;
        int size = pointerInputNodes.size();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            PointerInputModifierNode pointerInputModifierNode = (PointerInputModifierNode) pointerInputNodes.get(i);
            if (z) {
                MutableVector children = node2.getChildren();
                int size2 = children.getSize();
                if (size2 > 0) {
                    Object[] content = children.getContent();
                    int i2 = 0;
                    do {
                        node = content[i2];
                        if (Intrinsics.areEqual(((Node) node).getPointerInputNode(), pointerInputModifierNode)) {
                            break;
                        }
                        i2++;
                    } while (i2 < size2);
                    node = null;
                } else {
                    node = null;
                }
                Node node3 = node;
                if (node3 != null) {
                    node3.markIsIn();
                    if (!node3.getPointerIds().contains(PointerId.m184boximpl(j))) {
                        node3.getPointerIds().add(PointerId.m184boximpl(j));
                    }
                    node2 = node3;
                } else {
                    z = false;
                }
            }
            Node node4 = new Node(pointerInputModifierNode);
            node4.getPointerIds().add(PointerId.m184boximpl(j));
            node2.getChildren().add(node4);
            node2 = node4;
        }
    }

    public final boolean dispatchChanges(InternalPointerEvent internalPointerEvent, boolean z) {
        NodeParent nodeParent = this.root;
        Map changes = internalPointerEvent.getChanges();
        LayoutCoordinates layoutCoordinates = this.rootCoordinates;
        if (!nodeParent.buildCache(changes, layoutCoordinates, internalPointerEvent, z)) {
            return false;
        }
        boolean dispatchMainEventPass = nodeParent.dispatchMainEventPass(internalPointerEvent.getChanges(), layoutCoordinates, internalPointerEvent, z);
        if (!nodeParent.dispatchFinalEventPass(internalPointerEvent) && !dispatchMainEventPass) {
            return false;
        }
        return true;
    }

    public final void processCancel() {
        NodeParent nodeParent = this.root;
        nodeParent.dispatchCancel();
        nodeParent.clear();
    }

    public final void removeDetachedPointerInputFilters() {
        this.root.removeDetachedPointerInputFilters();
    }
}
