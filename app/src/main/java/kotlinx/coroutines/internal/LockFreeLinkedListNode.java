package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.NodeList;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class LockFreeLinkedListNode {
    private final AtomicRef _next = AtomicFU.atomic(this);
    private final AtomicRef _prev = AtomicFU.atomic(this);
    private final AtomicRef _removedRef = AtomicFU.atomic((Object) null);

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class CondAddOp extends AtomicOp {
        public final LockFreeLinkedListNode newNode;
        public LockFreeLinkedListNode oldNext;

        public CondAddOp(LockFreeLinkedListNode lockFreeLinkedListNode) {
            this.newNode = lockFreeLinkedListNode;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public final void complete(Object obj, Object obj2) {
            boolean z;
            LockFreeLinkedListNode lockFreeLinkedListNode;
            LockFreeLinkedListNode affected = (LockFreeLinkedListNode) obj;
            Intrinsics.checkNotNullParameter(affected, "affected");
            if (obj2 == null) {
                z = true;
            } else {
                z = false;
            }
            LockFreeLinkedListNode lockFreeLinkedListNode2 = this.newNode;
            if (z) {
                lockFreeLinkedListNode = lockFreeLinkedListNode2;
            } else {
                lockFreeLinkedListNode = this.oldNext;
            }
            if (lockFreeLinkedListNode != null && affected._next.compareAndSet(this, lockFreeLinkedListNode) && z) {
                LockFreeLinkedListNode lockFreeLinkedListNode3 = this.oldNext;
                Intrinsics.checkNotNull(lockFreeLinkedListNode3);
                lockFreeLinkedListNode2.finishAdd(lockFreeLinkedListNode3);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0042, code lost:
        if (r4._next.compareAndSet(r2, ((kotlinx.coroutines.internal.Removed) r5).ref) != false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlinx.coroutines.internal.LockFreeLinkedListNode correctPrev() {
        /*
            r8 = this;
        L0:
            kotlinx.atomicfu.AtomicRef r0 = r8._prev
            java.lang.Object r1 = r0.getValue()
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = r1
        L9:
            r3 = 0
            r4 = r3
        Lb:
            kotlinx.atomicfu.AtomicRef r5 = r2._next
            java.lang.Object r5 = r5.getValue()
            if (r5 != r8) goto L1e
            if (r1 != r2) goto L16
            return r2
        L16:
            boolean r0 = r0.compareAndSet(r1, r2)
            if (r0 != 0) goto L1d
            goto L0
        L1d:
            return r2
        L1e:
            boolean r6 = r8.isRemoved()
            if (r6 == 0) goto L25
            return r3
        L25:
            if (r5 != 0) goto L28
            return r2
        L28:
            boolean r6 = r5 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r6 == 0) goto L32
            kotlinx.coroutines.internal.OpDescriptor r5 = (kotlinx.coroutines.internal.OpDescriptor) r5
            r5.perform(r2)
            goto L0
        L32:
            boolean r6 = r5 instanceof kotlinx.coroutines.internal.Removed
            if (r6 == 0) goto L50
            if (r4 == 0) goto L47
            kotlinx.coroutines.internal.Removed r5 = (kotlinx.coroutines.internal.Removed) r5
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = r5.ref
            kotlinx.atomicfu.AtomicRef r5 = r4._next
            boolean r2 = r5.compareAndSet(r2, r3)
            if (r2 != 0) goto L45
            goto L0
        L45:
            r2 = r4
            goto L9
        L47:
            kotlinx.atomicfu.AtomicRef r2 = r2._prev
            java.lang.Object r2 = r2.getValue()
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            goto Lb
        L50:
            java.lang.String r4 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r4)
            r4 = r5
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r4
            r7 = r4
            r4 = r2
            r2 = r7
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeLinkedListNode.correctPrev():kotlinx.coroutines.internal.LockFreeLinkedListNode");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishAdd(LockFreeLinkedListNode lockFreeLinkedListNode) {
        LockFreeLinkedListNode lockFreeLinkedListNode2;
        AtomicRef atomicRef = lockFreeLinkedListNode._prev;
        do {
            lockFreeLinkedListNode2 = (LockFreeLinkedListNode) atomicRef.getValue();
            if (getNext() != lockFreeLinkedListNode) {
                return;
            }
        } while (!lockFreeLinkedListNode._prev.compareAndSet(lockFreeLinkedListNode2, this));
        if (isRemoved()) {
            lockFreeLinkedListNode.correctPrev();
        }
    }

    public final boolean addNext(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListHead lockFreeLinkedListHead) {
        lockFreeLinkedListNode._prev.lazySet(this);
        lockFreeLinkedListNode._next.lazySet(lockFreeLinkedListHead);
        if (!this._next.compareAndSet(lockFreeLinkedListHead, lockFreeLinkedListNode)) {
            return false;
        }
        lockFreeLinkedListNode.finishAdd(lockFreeLinkedListHead);
        return true;
    }

    public final void addOneIfEmpty(NodeList nodeList) {
        nodeList._prev.lazySet(this);
        nodeList._next.lazySet(this);
        while (getNext() == this) {
            if (this._next.compareAndSet(this, nodeList)) {
                nodeList.finishAdd(this);
                return;
            }
        }
    }

    public void dispose() {
        remove$1();
    }

    public final Object getNext() {
        while (true) {
            Object value = this._next.getValue();
            if (!(value instanceof OpDescriptor)) {
                return value;
            }
            ((OpDescriptor) value).perform(this);
        }
    }

    public final LockFreeLinkedListNode getNextNode() {
        Removed removed;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Object next = getNext();
        int i = LockFreeLinkedListKt.$r8$clinit;
        Intrinsics.checkNotNullParameter(next, "<this>");
        if (next instanceof Removed) {
            removed = (Removed) next;
        } else {
            removed = null;
        }
        if (removed == null || (lockFreeLinkedListNode = removed.ref) == null) {
            return (LockFreeLinkedListNode) next;
        }
        return lockFreeLinkedListNode;
    }

    public final LockFreeLinkedListNode getPrevNode() {
        LockFreeLinkedListNode correctPrev = correctPrev();
        if (correctPrev == null) {
            correctPrev = (LockFreeLinkedListNode) this._prev.getValue();
            while (correctPrev.isRemoved()) {
                correctPrev = (LockFreeLinkedListNode) correctPrev._prev.getValue();
            }
        }
        return correctPrev;
    }

    public final void helpRemove() {
        Object next = getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.Removed");
        ((Removed) next).ref.helpRemovePrev();
    }

    public final void helpRemovePrev() {
        while (true) {
            Object next = this.getNext();
            if (next instanceof Removed) {
                this = ((Removed) next).ref;
            } else {
                this.correctPrev();
                return;
            }
        }
    }

    public boolean isRemoved() {
        return getNext() instanceof Removed;
    }

    public boolean remove$1() {
        if (removeOrNext() == null) {
            return true;
        }
        return false;
    }

    public final LockFreeLinkedListNode removeOrNext() {
        Object next;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Removed removed;
        do {
            next = getNext();
            if (next instanceof Removed) {
                return ((Removed) next).ref;
            }
            if (next == this) {
                return (LockFreeLinkedListNode) next;
            }
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
            AtomicRef atomicRef = lockFreeLinkedListNode._removedRef;
            removed = (Removed) atomicRef.getValue();
            if (removed == null) {
                removed = new Removed(lockFreeLinkedListNode);
                atomicRef.lazySet(removed);
            }
        } while (!this._next.compareAndSet(next, removed));
        lockFreeLinkedListNode.correctPrev();
        return null;
    }

    public String toString() {
        LockFreeLinkedListNode$toString$1 lockFreeLinkedListNode$toString$1 = new LockFreeLinkedListNode$toString$1(this);
        String hexAddress = DebugStringsKt.getHexAddress(this);
        return lockFreeLinkedListNode$toString$1 + "@" + hexAddress;
    }

    public final int tryCondAddNext(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2, CondAddOp condAddOp) {
        lockFreeLinkedListNode._prev.lazySet(this);
        lockFreeLinkedListNode._next.lazySet(lockFreeLinkedListNode2);
        condAddOp.oldNext = lockFreeLinkedListNode2;
        if (!this._next.compareAndSet(lockFreeLinkedListNode2, condAddOp)) {
            return 0;
        }
        if (condAddOp.perform(this) == null) {
            return 1;
        }
        return 2;
    }
}
