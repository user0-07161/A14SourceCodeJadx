package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MutexImpl implements Mutex {
    private final AtomicRef _state;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class LockCont extends LockFreeLinkedListNode implements DisposableHandle {
        private final CancellableContinuation cont;
        public final Object owner = null;
        private final AtomicBoolean isTaken = new AtomicBoolean(false);

        public LockCont(CancellableContinuationImpl cancellableContinuationImpl) {
            this.cont = cancellableContinuationImpl;
        }

        public final void completeResumeLockWaiter() {
            ((CancellableContinuationImpl) this.cont).completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        public final boolean take() {
            return this.isTaken.compareAndSet();
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public final String toString() {
            return "LockCont[" + this.owner + ", " + this.cont + "] for " + MutexImpl.this;
        }

        public final boolean tryResumeLockWaiter() {
            if (!take()) {
                return false;
            }
            Unit unit = Unit.INSTANCE;
            final MutexImpl mutexImpl = MutexImpl.this;
            if (((CancellableContinuationImpl) this.cont).tryResume((Object) unit, new Function1() { // from class: kotlinx.coroutines.sync.MutexImpl$LockCont$tryResumeLockWaiter$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Throwable it = (Throwable) obj;
                    Intrinsics.checkNotNullParameter(it, "it");
                    MutexImpl.this.unlock(this.owner);
                    return Unit.INSTANCE;
                }
            }) == null) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class LockedQueue extends LockFreeLinkedListHead {
        public volatile Object owner;

        public LockedQueue(Object owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            this.owner = owner;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public final String toString() {
            Object obj = this.owner;
            return "LockedQueue[" + obj + "]";
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class UnlockOp extends AtomicOp {
        public final LockedQueue queue;

        public UnlockOp(LockedQueue queue) {
            Intrinsics.checkNotNullParameter(queue, "queue");
            this.queue = queue;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public final void complete(Object obj, Object obj2) {
            Object obj3;
            MutexImpl affected = (MutexImpl) obj;
            Intrinsics.checkNotNullParameter(affected, "affected");
            if (obj2 == null) {
                obj3 = MutexKt.EMPTY_UNLOCKED;
            } else {
                obj3 = this.queue;
            }
            affected._state.compareAndSet(this, obj3);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public final Symbol prepare(Object obj) {
            boolean z;
            Symbol symbol;
            MutexImpl affected = (MutexImpl) obj;
            Intrinsics.checkNotNullParameter(affected, "affected");
            LockedQueue lockedQueue = this.queue;
            if (lockedQueue.getNext() == lockedQueue) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                symbol = MutexKt.UNLOCK_FAIL;
                return symbol;
            }
            return null;
        }
    }

    public MutexImpl(boolean z) {
        this._state = AtomicFU.atomic(z ? MutexKt.EMPTY_LOCKED : MutexKt.EMPTY_UNLOCKED);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
        if (r0 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003e, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        r12 = kotlinx.coroutines.CancellableContinuationKt.getOrCreateCancellableContinuation(kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r12));
        r0 = new kotlinx.coroutines.sync.MutexImpl.LockCont(r11, r12);
        r2 = r11._state;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
        r7 = r2.getValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
        if ((r7 instanceof kotlinx.coroutines.sync.Empty) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0056, code lost:
        r8 = (kotlinx.coroutines.sync.Empty) r7;
        r9 = r8.locked;
        r10 = kotlinx.coroutines.sync.MutexKt.UNLOCKED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005f, code lost:
        if (r9 == r10) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0061, code lost:
        r11._state.compareAndSet(r7, new kotlinx.coroutines.sync.MutexImpl.LockedQueue(r8.locked));
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006e, code lost:
        r8 = kotlinx.coroutines.sync.MutexKt.EMPTY_LOCKED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0078, code lost:
        if (r11._state.compareAndSet(r7, r8) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007a, code lost:
        r12.resume(r1, new kotlinx.coroutines.sync.MutexImpl$lockSuspend$2$1$1(r11));
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0085, code lost:
        if ((r7 instanceof kotlinx.coroutines.sync.MutexImpl.LockedQueue) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0087, code lost:
        r8 = (kotlinx.coroutines.sync.MutexImpl.LockedQueue) r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008c, code lost:
        if (r8.owner == null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008e, code lost:
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0090, code lost:
        r9 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
        if (r9 == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x009b, code lost:
        if (r8.getPrevNode().addNext(r0, r8) == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a3, code lost:
        if (r11._state.getValue() == r7) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a9, code lost:
        if (r0.take() != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ac, code lost:
        r0 = new kotlinx.coroutines.sync.MutexImpl.LockCont(r11, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b2, code lost:
        kotlinx.coroutines.CancellableContinuationKt.removeOnCancellation(r12, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b5, code lost:
        r11 = r12.getResult();
        r12 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00bb, code lost:
        if (r11 != r12) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00be, code lost:
        r11 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00bf, code lost:
        if (r11 != r12) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00c1, code lost:
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c2, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00cc, code lost:
        throw new java.lang.IllegalStateException("Already locked by null".toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00cf, code lost:
        if ((r7 instanceof kotlinx.coroutines.internal.OpDescriptor) == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d1, code lost:
        ((kotlinx.coroutines.internal.OpDescriptor) r7).perform(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00ed, code lost:
        throw new java.lang.IllegalStateException(("Illegal state " + r7).toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object lock(kotlin.coroutines.Continuation r12) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.MutexImpl.lock(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String toString() {
        AtomicRef atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            if (value instanceof Empty) {
                Object obj = ((Empty) value).locked;
                return "Mutex[" + obj + "]";
            } else if (value instanceof OpDescriptor) {
                ((OpDescriptor) value).perform(this);
            } else if (value instanceof LockedQueue) {
                Object obj2 = ((LockedQueue) value).owner;
                return "Mutex[" + obj2 + "]";
            } else {
                throw new IllegalStateException(("Illegal state " + value).toString());
            }
        }
    }

    public final void unlock(Object obj) {
        Empty empty;
        Symbol symbol;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        AtomicRef atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            boolean z = true;
            if (value instanceof Empty) {
                if (obj == null) {
                    Object obj2 = ((Empty) value).locked;
                    symbol = MutexKt.UNLOCKED;
                    if (obj2 == symbol) {
                        z = false;
                    }
                    if (!z) {
                        throw new IllegalStateException("Mutex is not locked".toString());
                    }
                } else {
                    Object obj3 = ((Empty) value).locked;
                    if (obj3 != obj) {
                        z = false;
                    }
                    if (!z) {
                        throw new IllegalStateException(("Mutex is locked by " + obj3 + " but expected " + obj).toString());
                    }
                }
                AtomicRef atomicRef2 = this._state;
                empty = MutexKt.EMPTY_UNLOCKED;
                if (atomicRef2.compareAndSet(value, empty)) {
                    return;
                }
            } else if (value instanceof OpDescriptor) {
                ((OpDescriptor) value).perform(this);
            } else if (value instanceof LockedQueue) {
                if (obj != null) {
                    LockedQueue lockedQueue = (LockedQueue) value;
                    if (lockedQueue.owner != obj) {
                        z = false;
                    }
                    if (!z) {
                        throw new IllegalStateException(("Mutex is locked by " + lockedQueue.owner + " but expected " + obj).toString());
                    }
                }
                LockedQueue lockedQueue2 = (LockedQueue) value;
                while (true) {
                    Object next = lockedQueue2.getNext();
                    Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                    lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                    if (lockFreeLinkedListNode == lockedQueue2) {
                        lockFreeLinkedListNode = null;
                        break;
                    } else if (lockFreeLinkedListNode.remove$1()) {
                        break;
                    } else {
                        lockFreeLinkedListNode.helpRemove();
                    }
                }
                if (lockFreeLinkedListNode == null) {
                    UnlockOp unlockOp = new UnlockOp(lockedQueue2);
                    if (this._state.compareAndSet(value, unlockOp) && unlockOp.perform(this) == null) {
                        return;
                    }
                } else {
                    LockCont lockCont = (LockCont) lockFreeLinkedListNode;
                    if (lockCont.tryResumeLockWaiter()) {
                        Object obj4 = lockCont.owner;
                        if (obj4 == null) {
                            obj4 = MutexKt.LOCKED;
                        }
                        lockedQueue2.owner = obj4;
                        lockCont.completeResumeLockWaiter();
                        return;
                    }
                }
            } else {
                throw new IllegalStateException(("Illegal state " + value).toString());
            }
        }
    }
}
