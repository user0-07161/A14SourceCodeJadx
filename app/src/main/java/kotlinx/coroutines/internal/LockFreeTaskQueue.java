package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicRef;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class LockFreeTaskQueue {
    private final AtomicRef _cur = new AtomicRef(new LockFreeTaskQueueCore(8, false));

    public final boolean addLast(Object element) {
        Intrinsics.checkNotNullParameter(element, "element");
        while (true) {
            AtomicRef atomicRef = this._cur;
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) atomicRef.getValue();
            int addLast = lockFreeTaskQueueCore.addLast(element);
            if (addLast == 0) {
                return true;
            }
            if (addLast != 1) {
                if (addLast == 2) {
                    return false;
                }
            } else {
                atomicRef.compareAndSet(lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
            }
        }
    }

    public final void close() {
        while (true) {
            AtomicRef atomicRef = this._cur;
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) atomicRef.getValue();
            if (lockFreeTaskQueueCore.close()) {
                return;
            }
            atomicRef.compareAndSet(lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
        }
    }

    public final int getSize() {
        return ((LockFreeTaskQueueCore) this._cur.getValue()).getSize();
    }

    public final Object removeFirstOrNull() {
        while (true) {
            AtomicRef atomicRef = this._cur;
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) atomicRef.getValue();
            Object removeFirstOrNull = lockFreeTaskQueueCore.removeFirstOrNull();
            if (removeFirstOrNull != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                return removeFirstOrNull;
            }
            atomicRef.compareAndSet(lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
        }
    }
}
