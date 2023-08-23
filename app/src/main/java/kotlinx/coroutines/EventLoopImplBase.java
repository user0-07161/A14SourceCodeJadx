package kotlinx.coroutines;

import java.util.Arrays;
import java.util.concurrent.locks.LockSupport;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
    private final AtomicRef _queue = AtomicFU.atomic((Object) null);
    private final AtomicRef _delayed = AtomicFU.atomic((Object) null);
    private final AtomicBoolean _isCompleted = AtomicFU.atomic(false);

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class DelayedResumeTask extends DelayedTask {
        private final CancellableContinuation cont;

        public DelayedResumeTask(long j, CancellableContinuationImpl cancellableContinuationImpl) {
            super(j);
            this.cont = cancellableContinuationImpl;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ((CancellableContinuationImpl) this.cont).resumeUndispatched(EventLoopImplBase.this);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public final String toString() {
            String delayedTask = super.toString();
            CancellableContinuation cancellableContinuation = this.cont;
            return delayedTask + cancellableContinuation;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class DelayedTask implements Runnable, Comparable, DisposableHandle {
        private volatile Object _heap;
        private int index = -1;
        public long nanoTime;

        public DelayedTask(long j) {
            this.nanoTime = j;
        }

        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            DelayedTask other = (DelayedTask) obj;
            Intrinsics.checkNotNullParameter(other, "other");
            int i = ((this.nanoTime - other.nanoTime) > 0L ? 1 : ((this.nanoTime - other.nanoTime) == 0L ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            if (i < 0) {
                return -1;
            }
            return 0;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final synchronized void dispose() {
            Symbol symbol;
            DelayedTaskQueue delayedTaskQueue;
            Symbol symbol2;
            Object obj = this._heap;
            symbol = EventLoop_commonKt.DISPOSED_TASK;
            if (obj == symbol) {
                return;
            }
            if (obj instanceof DelayedTaskQueue) {
                delayedTaskQueue = (DelayedTaskQueue) obj;
            } else {
                delayedTaskQueue = null;
            }
            if (delayedTaskQueue != null) {
                synchronized (delayedTaskQueue) {
                    if (getHeap() != null) {
                        delayedTaskQueue.removeAtImpl(getIndex());
                    }
                }
            }
            symbol2 = EventLoop_commonKt.DISPOSED_TASK;
            this._heap = symbol2;
        }

        public final DelayedTaskQueue getHeap() {
            Object obj = this._heap;
            if (obj instanceof DelayedTaskQueue) {
                return (DelayedTaskQueue) obj;
            }
            return null;
        }

        public final int getIndex() {
            return this.index;
        }

        public final synchronized int scheduleTask(long j, DelayedTaskQueue delayedTaskQueue, EventLoopImplBase eventLoop) {
            Symbol symbol;
            Intrinsics.checkNotNullParameter(eventLoop, "eventLoop");
            Object obj = this._heap;
            symbol = EventLoop_commonKt.DISPOSED_TASK;
            if (obj == symbol) {
                return 2;
            }
            synchronized (delayedTaskQueue) {
                DelayedTask firstImpl = delayedTaskQueue.firstImpl();
                if (EventLoopImplBase.access$isCompleted(eventLoop)) {
                    return 1;
                }
                if (firstImpl == null) {
                    delayedTaskQueue.timeNow = j;
                } else {
                    long j2 = firstImpl.nanoTime;
                    if (j2 - j < 0) {
                        j = j2;
                    }
                    if (j - delayedTaskQueue.timeNow > 0) {
                        delayedTaskQueue.timeNow = j;
                    }
                }
                long j3 = this.nanoTime;
                long j4 = delayedTaskQueue.timeNow;
                if (j3 - j4 < 0) {
                    this.nanoTime = j4;
                }
                delayedTaskQueue.addImpl(this);
                return 0;
            }
        }

        public final void setHeap(DelayedTaskQueue delayedTaskQueue) {
            Symbol symbol;
            boolean z;
            Object obj = this._heap;
            symbol = EventLoop_commonKt.DISPOSED_TASK;
            if (obj != symbol) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                this._heap = delayedTaskQueue;
                return;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }

        public final void setIndex(int i) {
            this.index = i;
        }

        public String toString() {
            long j = this.nanoTime;
            return "Delayed[nanos=" + j + "]";
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class DelayedTaskQueue {
        private final AtomicInt _size = new AtomicInt();
        private DelayedTask[] a;
        public long timeNow;

        public DelayedTaskQueue(long j) {
            this.timeNow = j;
        }

        private final void siftUpFrom(int i) {
            while (i > 0) {
                DelayedTask[] delayedTaskArr = this.a;
                Intrinsics.checkNotNull(delayedTaskArr);
                int i2 = (i - 1) / 2;
                DelayedTask delayedTask = delayedTaskArr[i2];
                Intrinsics.checkNotNull(delayedTask);
                DelayedTask delayedTask2 = delayedTaskArr[i];
                Intrinsics.checkNotNull(delayedTask2);
                if (delayedTask.compareTo(delayedTask2) <= 0) {
                    return;
                }
                swap(i, i2);
                i = i2;
            }
        }

        private final void swap(int i, int i2) {
            DelayedTask[] delayedTaskArr = this.a;
            Intrinsics.checkNotNull(delayedTaskArr);
            DelayedTask delayedTask = delayedTaskArr[i2];
            Intrinsics.checkNotNull(delayedTask);
            DelayedTask delayedTask2 = delayedTaskArr[i];
            Intrinsics.checkNotNull(delayedTask2);
            delayedTaskArr[i] = delayedTask;
            delayedTaskArr[i2] = delayedTask2;
            delayedTask.setIndex(i);
            delayedTask2.setIndex(i2);
        }

        public final void addImpl(DelayedTask node) {
            Intrinsics.checkNotNullParameter(node, "node");
            node.setHeap(this);
            DelayedTask[] delayedTaskArr = this.a;
            if (delayedTaskArr == null) {
                delayedTaskArr = new DelayedTask[4];
                this.a = delayedTaskArr;
            } else if (getSize() >= delayedTaskArr.length) {
                Object[] copyOf = Arrays.copyOf(delayedTaskArr, getSize() * 2);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                delayedTaskArr = (DelayedTask[]) copyOf;
                this.a = delayedTaskArr;
            }
            int size = getSize();
            this._size.setValue(size + 1);
            delayedTaskArr[size] = node;
            node.setIndex(size);
            siftUpFrom(size);
        }

        public final DelayedTask firstImpl() {
            DelayedTask[] delayedTaskArr = this.a;
            if (delayedTaskArr != null) {
                return delayedTaskArr[0];
            }
            return null;
        }

        public final int getSize() {
            return this._size.getValue();
        }

        public final DelayedTask removeAtImpl(int i) {
            DelayedTask[] delayedTaskArr = this.a;
            Intrinsics.checkNotNull(delayedTaskArr);
            this._size.setValue(getSize() - 1);
            if (i < getSize()) {
                swap(i, getSize());
                int i2 = (i - 1) / 2;
                if (i > 0) {
                    DelayedTask delayedTask = delayedTaskArr[i];
                    Intrinsics.checkNotNull(delayedTask);
                    DelayedTask delayedTask2 = delayedTaskArr[i2];
                    Intrinsics.checkNotNull(delayedTask2);
                    if (delayedTask.compareTo(delayedTask2) < 0) {
                        swap(i, i2);
                        siftUpFrom(i2);
                    }
                }
                while (true) {
                    int i3 = (i * 2) + 1;
                    if (i3 >= getSize()) {
                        break;
                    }
                    DelayedTask[] delayedTaskArr2 = this.a;
                    Intrinsics.checkNotNull(delayedTaskArr2);
                    int i4 = i3 + 1;
                    if (i4 < getSize()) {
                        DelayedTask delayedTask3 = delayedTaskArr2[i4];
                        Intrinsics.checkNotNull(delayedTask3);
                        DelayedTask delayedTask4 = delayedTaskArr2[i3];
                        Intrinsics.checkNotNull(delayedTask4);
                        if (delayedTask3.compareTo(delayedTask4) < 0) {
                            i3 = i4;
                        }
                    }
                    DelayedTask delayedTask5 = delayedTaskArr2[i];
                    Intrinsics.checkNotNull(delayedTask5);
                    DelayedTask delayedTask6 = delayedTaskArr2[i3];
                    Intrinsics.checkNotNull(delayedTask6);
                    if (delayedTask5.compareTo(delayedTask6) <= 0) {
                        break;
                    }
                    swap(i, i3);
                    i = i3;
                }
            }
            DelayedTask delayedTask7 = delayedTaskArr[getSize()];
            Intrinsics.checkNotNull(delayedTask7);
            delayedTask7.setHeap(null);
            delayedTask7.setIndex(-1);
            delayedTaskArr[getSize()] = null;
            return delayedTask7;
        }
    }

    public static final boolean access$isCompleted(EventLoopImplBase eventLoopImplBase) {
        return eventLoopImplBase._isCompleted.getValue();
    }

    private final boolean enqueueImpl(Runnable runnable) {
        Symbol symbol;
        while (true) {
            AtomicRef atomicRef = this._queue;
            Object value = atomicRef.getValue();
            if (this._isCompleted.getValue()) {
                return false;
            }
            if (value == null) {
                if (atomicRef.compareAndSet(null, runnable)) {
                    return true;
                }
            } else if (!(value instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                if (value == symbol) {
                    return false;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore.addLast((Runnable) value);
                lockFreeTaskQueueCore.addLast(runnable);
                if (atomicRef.compareAndSet(value, lockFreeTaskQueueCore)) {
                    return true;
                }
            } else {
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = (LockFreeTaskQueueCore) value;
                int addLast = lockFreeTaskQueueCore2.addLast(runnable);
                if (addLast == 0) {
                    return true;
                }
                if (addLast != 1) {
                    if (addLast == 2) {
                        return false;
                    }
                } else {
                    atomicRef.compareAndSet(value, lockFreeTaskQueueCore2.next());
                }
            }
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext context, Runnable block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(block, "block");
        enqueue(block);
    }

    public void enqueue(Runnable task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (enqueueImpl(task)) {
            Thread thread = getThread();
            if (Thread.currentThread() != thread) {
                LockSupport.unpark(thread);
                return;
            }
            return;
        }
        DefaultExecutor.INSTANCE.enqueue(task);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isEmpty() {
        Symbol symbol;
        boolean z;
        if (!isUnconfinedQueueEmpty()) {
            return false;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed.getValue();
        if (delayedTaskQueue != null) {
            if (delayedTaskQueue.getSize() == 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return false;
            }
        }
        Object value = this._queue.getValue();
        if (value != null) {
            if (!(value instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                if (value != symbol) {
                    return false;
                }
            } else {
                return ((LockFreeTaskQueueCore) value).isEmpty();
            }
        }
        return true;
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    public final long processNextEvent() {
        DelayedTask firstImpl;
        Symbol symbol;
        Symbol symbol2;
        boolean z;
        boolean z2;
        boolean z3;
        DelayedTask delayedTask;
        if (processUnconfinedEvent()) {
            return 0L;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed.getValue();
        Runnable runnable = null;
        if (delayedTaskQueue != null) {
            if (delayedTaskQueue.getSize() == 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                long nanoTime = System.nanoTime();
                do {
                    synchronized (delayedTaskQueue) {
                        DelayedTask firstImpl2 = delayedTaskQueue.firstImpl();
                        if (firstImpl2 == null) {
                            delayedTask = null;
                            continue;
                        } else {
                            if (nanoTime - firstImpl2.nanoTime >= 0) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                z3 = enqueueImpl(firstImpl2);
                            } else {
                                z3 = false;
                            }
                            if (z3) {
                                delayedTask = delayedTaskQueue.removeAtImpl(0);
                            } else {
                                delayedTask = null;
                            }
                            continue;
                        }
                    }
                } while (delayedTask != null);
            }
        }
        while (true) {
            AtomicRef atomicRef = this._queue;
            Object value = atomicRef.getValue();
            if (value == null) {
                break;
            } else if (!(value instanceof LockFreeTaskQueueCore)) {
                symbol2 = EventLoop_commonKt.CLOSED_EMPTY;
                if (value == symbol2) {
                    break;
                } else if (atomicRef.compareAndSet(value, null)) {
                    runnable = (Runnable) value;
                    break;
                }
            } else {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) value;
                Object removeFirstOrNull = lockFreeTaskQueueCore.removeFirstOrNull();
                if (removeFirstOrNull != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                    runnable = (Runnable) removeFirstOrNull;
                    break;
                }
                atomicRef.compareAndSet(value, lockFreeTaskQueueCore.next());
            }
        }
        if (runnable != null) {
            runnable.run();
            return 0L;
        } else if (super.getNextTime() == 0) {
            return 0L;
        } else {
            Object value2 = this._queue.getValue();
            if (value2 != null) {
                if (!(value2 instanceof LockFreeTaskQueueCore)) {
                    symbol = EventLoop_commonKt.CLOSED_EMPTY;
                    if (value2 != symbol) {
                        return 0L;
                    }
                    return Long.MAX_VALUE;
                } else if (!((LockFreeTaskQueueCore) value2).isEmpty()) {
                    return 0L;
                }
            }
            DelayedTaskQueue delayedTaskQueue2 = (DelayedTaskQueue) this._delayed.getValue();
            if (delayedTaskQueue2 != null) {
                synchronized (delayedTaskQueue2) {
                    firstImpl = delayedTaskQueue2.firstImpl();
                }
                if (firstImpl != null) {
                    long nanoTime2 = firstImpl.nanoTime - System.nanoTime();
                    if (nanoTime2 < 0) {
                        return 0L;
                    }
                    return nanoTime2;
                }
            }
            return Long.MAX_VALUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void resetAll() {
        this._queue.setValue(null);
        this._delayed.setValue(null);
    }

    public final void schedule(long j, DelayedTask delayedTask) {
        int scheduleTask;
        Thread thread;
        boolean value = this._isCompleted.getValue();
        AtomicRef atomicRef = this._delayed;
        DelayedTask delayedTask2 = null;
        boolean z = true;
        if (value) {
            scheduleTask = 1;
        } else {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) atomicRef.getValue();
            if (delayedTaskQueue == null) {
                atomicRef.compareAndSet(null, new DelayedTaskQueue(j));
                Object value2 = atomicRef.getValue();
                Intrinsics.checkNotNull(value2);
                delayedTaskQueue = (DelayedTaskQueue) value2;
            }
            scheduleTask = delayedTask.scheduleTask(j, delayedTaskQueue, this);
        }
        if (scheduleTask != 0) {
            if (scheduleTask != 1) {
                if (scheduleTask != 2) {
                    throw new IllegalStateException("unexpected result".toString());
                }
                return;
            }
            reschedule(j, delayedTask);
            return;
        }
        DelayedTaskQueue delayedTaskQueue2 = (DelayedTaskQueue) atomicRef.getValue();
        if (delayedTaskQueue2 != null) {
            synchronized (delayedTaskQueue2) {
                delayedTask2 = delayedTaskQueue2.firstImpl();
            }
        }
        if (delayedTask2 != delayedTask) {
            z = false;
        }
        if (z && Thread.currentThread() != (thread = getThread())) {
            LockSupport.unpark(thread);
        }
    }

    @Override // kotlinx.coroutines.Delay
    public final void scheduleResumeAfterDelay(long j, CancellableContinuationImpl cancellableContinuationImpl) {
        int i = EventLoop_commonKt.$r8$clinit;
        long j2 = 0;
        if (j > 0) {
            if (j >= 9223372036854L) {
                j2 = Long.MAX_VALUE;
            } else {
                j2 = 1000000 * j;
            }
        }
        if (j2 < 4611686018427387903L) {
            long nanoTime = System.nanoTime();
            DelayedResumeTask delayedResumeTask = new DelayedResumeTask(j2 + nanoTime, cancellableContinuationImpl);
            schedule(nanoTime, delayedResumeTask);
            CancellableContinuationKt.disposeOnCancellation(cancellableContinuationImpl, delayedResumeTask);
        }
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    public void shutdown() {
        Symbol symbol;
        DelayedTask delayedTask;
        Symbol symbol2;
        ThreadLocalEventLoop.resetEventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        this._isCompleted.setValue();
        while (true) {
            AtomicRef atomicRef = this._queue;
            Object value = atomicRef.getValue();
            if (value == null) {
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                if (atomicRef.compareAndSet(null, symbol)) {
                    break;
                }
            } else if (!(value instanceof LockFreeTaskQueueCore)) {
                symbol2 = EventLoop_commonKt.CLOSED_EMPTY;
                if (value == symbol2) {
                    break;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore.addLast((Runnable) value);
                if (atomicRef.compareAndSet(value, lockFreeTaskQueueCore)) {
                    break;
                }
            } else {
                ((LockFreeTaskQueueCore) value).close();
                break;
            }
        }
        do {
        } while (processNextEvent() <= 0);
        long nanoTime = System.nanoTime();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed.getValue();
            if (delayedTaskQueue != null) {
                synchronized (delayedTaskQueue) {
                    if (delayedTaskQueue.getSize() > 0) {
                        delayedTask = delayedTaskQueue.removeAtImpl(0);
                    } else {
                        delayedTask = null;
                    }
                }
                if (delayedTask != null) {
                    reschedule(nanoTime, delayedTask);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
}
