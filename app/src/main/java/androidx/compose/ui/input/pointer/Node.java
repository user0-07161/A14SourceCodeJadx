package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.BackwardsCompatNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.PointerInputModifierNodeKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Node extends NodeParent {
    private NodeCoordinator coordinates;
    private boolean hasExited;
    private boolean isIn;
    private PointerEvent pointerEvent;
    private final MutableVector pointerIds;
    private final PointerInputModifierNode pointerInputNode;
    private final Map relevantChanges;
    private boolean wasIn;

    public Node(PointerInputModifierNode pointerInputNode) {
        Intrinsics.checkNotNullParameter(pointerInputNode, "pointerInputNode");
        this.pointerInputNode = pointerInputNode;
        this.pointerIds = new MutableVector(new PointerId[16]);
        this.relevantChanges = new LinkedHashMap();
        this.isIn = true;
        this.hasExited = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x018d, code lost:
        if (r3 != false) goto L61;
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b7  */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean buildCache(java.util.Map r22, androidx.compose.ui.layout.LayoutCoordinates r23, androidx.compose.ui.input.pointer.InternalPointerEvent r24, boolean r25) {
        /*
            Method dump skipped, instructions count: 554
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.Node.buildCache(java.util.Map, androidx.compose.ui.layout.LayoutCoordinates, androidx.compose.ui.input.pointer.InternalPointerEvent, boolean):boolean");
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public final void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        super.cleanUpHits(internalPointerEvent);
        PointerEvent pointerEvent = this.pointerEvent;
        if (pointerEvent == null) {
            return;
        }
        this.wasIn = this.isIn;
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        boolean z = false;
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= size) {
                break;
            }
            PointerInputChange pointerInputChange = (PointerInputChange) changes.get(i);
            if (pointerInputChange.getPressed() || (internalPointerEvent.m180issuesEnterExitEvent0FcD4WY(pointerInputChange.m189getIdJ3iCeTQ()) && this.isIn)) {
                z2 = false;
            }
            if (z2) {
                this.pointerIds.remove(PointerId.m184boximpl(pointerInputChange.m189getIdJ3iCeTQ()));
            }
            i++;
        }
        this.isIn = false;
        if (pointerEvent.m181getType7fucELk() == 5) {
            z = true;
        }
        this.hasExited = z;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public final void dispatchCancel() {
        MutableVector children = getChildren();
        int size = children.getSize();
        if (size > 0) {
            Object[] content = children.getContent();
            int i = 0;
            do {
                ((Node) content[i]).dispatchCancel();
                i++;
            } while (i < size);
            ((BackwardsCompatNode) this.pointerInputNode).onCancelPointerInput();
        }
        ((BackwardsCompatNode) this.pointerInputNode).onCancelPointerInput();
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public final boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        MutableVector children;
        int size;
        Map map = this.relevantChanges;
        boolean z = false;
        int i = 0;
        z = false;
        if (!map.isEmpty()) {
            PointerInputModifierNode pointerInputModifierNode = this.pointerInputNode;
            if (PointerInputModifierNodeKt.isAttached(pointerInputModifierNode)) {
                PointerEvent pointerEvent = this.pointerEvent;
                Intrinsics.checkNotNull(pointerEvent);
                NodeCoordinator nodeCoordinator = this.coordinates;
                Intrinsics.checkNotNull(nodeCoordinator);
                ((BackwardsCompatNode) pointerInputModifierNode).m222onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Final, nodeCoordinator.m215getSizeYbymL2g());
                if (PointerInputModifierNodeKt.isAttached(pointerInputModifierNode) && (size = (children = getChildren()).getSize()) > 0) {
                    Object[] content = children.getContent();
                    do {
                        ((Node) content[i]).dispatchFinalEventPass(internalPointerEvent);
                        i++;
                    } while (i < size);
                    z = true;
                } else {
                    z = true;
                }
            }
        }
        cleanUpHits(internalPointerEvent);
        ((LinkedHashMap) map).clear();
        this.coordinates = null;
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005d  */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean dispatchMainEventPass(java.util.Map r11, androidx.compose.ui.layout.LayoutCoordinates r12, androidx.compose.ui.input.pointer.InternalPointerEvent r13, boolean r14) {
        /*
            r10 = this;
            java.lang.String r0 = "changes"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r11 = "parentCoordinates"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r11)
            java.util.Map r11 = r10.relevantChanges
            boolean r12 = r11.isEmpty()
            r0 = 0
            if (r12 == 0) goto L14
            goto L63
        L14:
            androidx.compose.ui.node.PointerInputModifierNode r12 = r10.pointerInputNode
            boolean r1 = androidx.compose.ui.node.PointerInputModifierNodeKt.isAttached(r12)
            if (r1 != 0) goto L1d
            goto L63
        L1d:
            androidx.compose.ui.input.pointer.PointerEvent r1 = r10.pointerEvent
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            androidx.compose.ui.node.NodeCoordinator r2 = r10.coordinates
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            long r2 = r2.m215getSizeYbymL2g()
            androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
            r5 = r12
            androidx.compose.ui.node.BackwardsCompatNode r5 = (androidx.compose.ui.node.BackwardsCompatNode) r5
            r5.m222onPointerEventH0pRuoY(r1, r4, r2)
            boolean r4 = androidx.compose.ui.node.PointerInputModifierNodeKt.isAttached(r12)
            r6 = 1
            if (r4 == 0) goto L57
            androidx.compose.runtime.collection.MutableVector r4 = r10.getChildren()
            int r7 = r4.getSize()
            if (r7 <= 0) goto L57
            java.lang.Object[] r4 = r4.getContent()
        L48:
            r8 = r4[r0]
            androidx.compose.ui.input.pointer.Node r8 = (androidx.compose.ui.input.pointer.Node) r8
            androidx.compose.ui.node.NodeCoordinator r9 = r10.coordinates
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            r8.dispatchMainEventPass(r11, r9, r13, r14)
            int r0 = r0 + r6
            if (r0 < r7) goto L48
        L57:
            boolean r10 = androidx.compose.ui.node.PointerInputModifierNodeKt.isAttached(r12)
            if (r10 == 0) goto L62
            androidx.compose.ui.input.pointer.PointerEventPass r10 = androidx.compose.ui.input.pointer.PointerEventPass.Main
            r5.m222onPointerEventH0pRuoY(r1, r10, r2)
        L62:
            r0 = r6
        L63:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(java.util.Map, androidx.compose.ui.layout.LayoutCoordinates, androidx.compose.ui.input.pointer.InternalPointerEvent, boolean):boolean");
    }

    public final MutableVector getPointerIds() {
        return this.pointerIds;
    }

    public final PointerInputModifierNode getPointerInputNode() {
        return this.pointerInputNode;
    }

    public final void markIsIn() {
        this.isIn = true;
    }

    public final String toString() {
        return "Node(pointerInputFilter=" + this.pointerInputNode + ", children=" + getChildren() + ", pointerIds=" + this.pointerIds + ')';
    }
}
