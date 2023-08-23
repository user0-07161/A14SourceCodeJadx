package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.PointerInputModifierNodeKt;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class NodeParent {
    private final MutableVector children = new MutableVector(new Node[16]);

    public boolean buildCache(Map changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        Intrinsics.checkNotNullParameter(changes, "changes");
        Intrinsics.checkNotNullParameter(parentCoordinates, "parentCoordinates");
        MutableVector mutableVector = this.children;
        int size = mutableVector.getSize();
        if (size <= 0) {
            return false;
        }
        Object[] content = mutableVector.getContent();
        int i = 0;
        boolean z2 = false;
        do {
            if (!((Node) content[i]).buildCache(changes, parentCoordinates, internalPointerEvent, z) && !z2) {
                z2 = false;
            } else {
                z2 = true;
            }
            i++;
        } while (i < size);
        return z2;
    }

    public void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        MutableVector mutableVector = this.children;
        for (int size = mutableVector.getSize() - 1; -1 < size; size--) {
            if (((Node) mutableVector.getContent()[size]).getPointerIds().isEmpty()) {
                mutableVector.removeAt(size);
            }
        }
    }

    public final void clear() {
        this.children.clear();
    }

    public void dispatchCancel() {
        MutableVector mutableVector = this.children;
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                ((Node) content[i]).dispatchCancel();
                i++;
            } while (i < size);
        }
    }

    public boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        MutableVector mutableVector = this.children;
        int size = mutableVector.getSize();
        boolean z = false;
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            boolean z2 = false;
            do {
                if (!((Node) content[i]).dispatchFinalEventPass(internalPointerEvent) && !z2) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                i++;
            } while (i < size);
            z = z2;
        }
        cleanUpHits(internalPointerEvent);
        return z;
    }

    public boolean dispatchMainEventPass(Map changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        Intrinsics.checkNotNullParameter(changes, "changes");
        Intrinsics.checkNotNullParameter(parentCoordinates, "parentCoordinates");
        MutableVector mutableVector = this.children;
        int size = mutableVector.getSize();
        if (size <= 0) {
            return false;
        }
        Object[] content = mutableVector.getContent();
        int i = 0;
        boolean z2 = false;
        do {
            if (!((Node) content[i]).dispatchMainEventPass(changes, parentCoordinates, internalPointerEvent, z) && !z2) {
                z2 = false;
            } else {
                z2 = true;
            }
            i++;
        } while (i < size);
        return z2;
    }

    public final MutableVector getChildren() {
        return this.children;
    }

    public final void removeDetachedPointerInputFilters() {
        int i = 0;
        while (true) {
            MutableVector mutableVector = this.children;
            if (i < mutableVector.getSize()) {
                Node node = (Node) mutableVector.getContent()[i];
                if (!PointerInputModifierNodeKt.isAttached(node.getPointerInputNode())) {
                    mutableVector.removeAt(i);
                    node.dispatchCancel();
                } else {
                    i++;
                    node.removeDetachedPointerInputFilters();
                }
            } else {
                return;
            }
        }
    }
}
