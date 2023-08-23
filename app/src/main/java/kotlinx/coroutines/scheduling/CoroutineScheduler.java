package kotlinx.coroutines.scheduling;

import androidx.compose.foundation.text.HeightInLinesModifierKt$$ExternalSyntheticOutline0;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.ResizableAtomicArray;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class CoroutineScheduler implements Executor, Closeable {
    public static final Symbol NOT_IN_STACK = new Symbol("NOT_IN_STACK");
    private final AtomicBoolean _isTerminated;
    private final AtomicLong controlState;
    public final int corePoolSize;
    public final GlobalQueue globalBlockingQueue;
    public final GlobalQueue globalCpuQueue;
    public final long idleWorkerKeepAliveNs;
    public final int maxPoolSize;
    private final AtomicLong parkedWorkersStack;
    public final String schedulerName;
    public final ResizableAtomicArray workers;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Worker extends Thread {
        private volatile int indexInArray;
        public final WorkQueue localQueue;
        public boolean mayHaveLocalTasks;
        private long minDelayUntilStealableTaskNs;
        private volatile Object nextParkedWorker;
        private int rngState;
        public WorkerState state;
        private long terminationDeadline;
        private final AtomicInt workerCtl;

        public Worker(int i) {
            setDaemon(true);
            this.localQueue = new WorkQueue();
            this.state = WorkerState.DORMANT;
            this.workerCtl = new AtomicInt();
            this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
            this.rngState = Random.Default.nextInt();
            setIndexInArray(i);
        }

        private final Task pollGlobalQueues() {
            if (nextInt(2) == 0) {
                Task task = (Task) CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull();
                if (task != null) {
                    return task;
                }
                return (Task) CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            }
            Task task2 = (Task) CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            if (task2 != null) {
                return task2;
            }
            return (Task) CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull();
        }

        private final Task trySteal(boolean z) {
            long tryStealFrom;
            int value = (int) (CoroutineScheduler.this.controlState.getValue() & 2097151);
            if (value < 2) {
                return null;
            }
            int nextInt = nextInt(value);
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            long j = Long.MAX_VALUE;
            for (int i = 0; i < value; i++) {
                nextInt++;
                if (nextInt > value) {
                    nextInt = 1;
                }
                Worker worker = (Worker) coroutineScheduler.workers.get(nextInt);
                if (worker != null && worker != this) {
                    if (z) {
                        tryStealFrom = this.localQueue.tryStealBlockingFrom(worker.localQueue);
                    } else {
                        tryStealFrom = this.localQueue.tryStealFrom(worker.localQueue);
                    }
                    if (tryStealFrom == -1) {
                        return this.localQueue.poll();
                    }
                    if (tryStealFrom > 0) {
                        j = Math.min(j, tryStealFrom);
                    }
                }
            }
            if (j == Long.MAX_VALUE) {
                j = 0;
            }
            this.minDelayUntilStealableTaskNs = j;
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0073  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final kotlinx.coroutines.scheduling.Task findTask(boolean r11) {
            /*
                r10 = this;
                kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r0 = r10.state
                kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r1 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.CPU_ACQUIRED
                r2 = 0
                r3 = 1
                if (r0 != r1) goto L9
                goto L37
            L9:
                kotlinx.coroutines.scheduling.CoroutineScheduler r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                kotlinx.atomicfu.AtomicLong r4 = kotlinx.coroutines.scheduling.CoroutineScheduler.access$getControlState$p(r0)
            Lf:
                long r5 = r4.getValue()
                r7 = 9223367638808264704(0x7ffffc0000000000, double:NaN)
                long r7 = r7 & r5
                r9 = 42
                long r7 = r7 >> r9
                int r7 = (int) r7
                if (r7 != 0) goto L21
                r0 = r2
                goto L33
            L21:
                r7 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
                long r7 = r5 - r7
                kotlinx.atomicfu.AtomicLong r9 = kotlinx.coroutines.scheduling.CoroutineScheduler.access$getControlState$p(r0)
                boolean r5 = r9.compareAndSet(r5, r7)
                if (r5 == 0) goto Lf
                r0 = r3
            L33:
                if (r0 == 0) goto L39
                r10.state = r1
            L37:
                r0 = r3
                goto L3a
            L39:
                r0 = r2
            L3a:
                if (r0 == 0) goto L73
                if (r11 == 0) goto L67
                kotlinx.coroutines.scheduling.CoroutineScheduler r11 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                int r11 = r11.corePoolSize
                int r11 = r11 * 2
                int r11 = r10.nextInt(r11)
                if (r11 != 0) goto L4b
                goto L4c
            L4b:
                r3 = r2
            L4c:
                if (r3 == 0) goto L55
                kotlinx.coroutines.scheduling.Task r11 = r10.pollGlobalQueues()
                if (r11 == 0) goto L55
                goto L72
            L55:
                kotlinx.coroutines.scheduling.WorkQueue r11 = r10.localQueue
                kotlinx.coroutines.scheduling.Task r11 = r11.poll()
                if (r11 == 0) goto L5e
                goto L72
            L5e:
                if (r3 != 0) goto L6e
                kotlinx.coroutines.scheduling.Task r11 = r10.pollGlobalQueues()
                if (r11 == 0) goto L6e
                goto L72
            L67:
                kotlinx.coroutines.scheduling.Task r11 = r10.pollGlobalQueues()
                if (r11 == 0) goto L6e
                goto L72
            L6e:
                kotlinx.coroutines.scheduling.Task r11 = r10.trySteal(r2)
            L72:
                return r11
            L73:
                if (r11 == 0) goto L88
                kotlinx.coroutines.scheduling.WorkQueue r11 = r10.localQueue
                kotlinx.coroutines.scheduling.Task r11 = r11.poll()
                if (r11 != 0) goto L92
                kotlinx.coroutines.scheduling.CoroutineScheduler r11 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                kotlinx.coroutines.scheduling.GlobalQueue r11 = r11.globalBlockingQueue
                java.lang.Object r11 = r11.removeFirstOrNull()
                kotlinx.coroutines.scheduling.Task r11 = (kotlinx.coroutines.scheduling.Task) r11
                goto L92
            L88:
                kotlinx.coroutines.scheduling.CoroutineScheduler r11 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                kotlinx.coroutines.scheduling.GlobalQueue r11 = r11.globalBlockingQueue
                java.lang.Object r11 = r11.removeFirstOrNull()
                kotlinx.coroutines.scheduling.Task r11 = (kotlinx.coroutines.scheduling.Task) r11
            L92:
                if (r11 != 0) goto L98
                kotlinx.coroutines.scheduling.Task r11 = r10.trySteal(r3)
            L98:
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker.findTask(boolean):kotlinx.coroutines.scheduling.Task");
        }

        public final int getIndexInArray() {
            return this.indexInArray;
        }

        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        public final AtomicInt getWorkerCtl() {
            return this.workerCtl;
        }

        public final int nextInt(int i) {
            int i2 = this.rngState;
            int i3 = i2 ^ (i2 << 13);
            int i4 = i3 ^ (i3 >> 17);
            int i5 = i4 ^ (i4 << 5);
            this.rngState = i5;
            int i6 = i - 1;
            if ((i6 & i) == 0) {
                return i6 & i5;
            }
            return (Integer.MAX_VALUE & i5) % i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:104:0x0002, code lost:
            continue;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 346
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker.run():void");
        }

        public final void setIndexInArray(int i) {
            String valueOf;
            String str = CoroutineScheduler.this.schedulerName;
            if (i == 0) {
                valueOf = "TERMINATED";
            } else {
                valueOf = String.valueOf(i);
            }
            setName(str + "-worker-" + valueOf);
            this.indexInArray = i;
        }

        public final void setNextParkedWorker(Object obj) {
            this.nextParkedWorker = obj;
        }

        public final boolean tryReleaseCpu(WorkerState workerState) {
            boolean z;
            WorkerState workerState2 = this.state;
            if (workerState2 == WorkerState.CPU_ACQUIRED) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                CoroutineScheduler.this.controlState.addAndGet(4398046511104L);
            }
            if (workerState2 != workerState) {
                this.state = workerState;
            }
            return z;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public enum WorkerState {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED
    }

    public CoroutineScheduler(int i, int i2, long j, String schedulerName) {
        boolean z;
        boolean z2;
        boolean z3;
        Intrinsics.checkNotNullParameter(schedulerName, "schedulerName");
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = schedulerName;
        if (i >= 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (i2 >= i) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (i2 <= 2097150) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    if (j > 0) {
                        this.globalCpuQueue = new GlobalQueue();
                        this.globalBlockingQueue = new GlobalQueue();
                        this.parkedWorkersStack = new AtomicLong(0L);
                        this.workers = new ResizableAtomicArray(i + 1);
                        this.controlState = new AtomicLong(i << 42);
                        this._isTerminated = new AtomicBoolean(false);
                        return;
                    }
                    throw new IllegalArgumentException(("Idle worker keep alive time " + j + " must be positive").toString());
                }
                throw new IllegalArgumentException(("Max pool size " + i2 + " should not exceed maximal supported number of threads 2097150").toString());
            }
            throw new IllegalArgumentException(HeightInLinesModifierKt$$ExternalSyntheticOutline0.m("Max pool size ", i2, " should be greater than or equals to core pool size ", i).toString());
        }
        throw new IllegalArgumentException(("Core pool size " + i + " should be at least 1").toString());
    }

    private final int createNewWorker() {
        boolean z;
        synchronized (this.workers) {
            if (isTerminated()) {
                return -1;
            }
            long value = this.controlState.getValue();
            int i = (int) (value & 2097151);
            int i2 = i - ((int) ((value & 4398044413952L) >> 21));
            boolean z2 = false;
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 >= this.corePoolSize) {
                return 0;
            }
            if (i >= this.maxPoolSize) {
                return 0;
            }
            int value2 = ((int) (this.controlState.getValue() & 2097151)) + 1;
            if (value2 > 0 && this.workers.get(value2) == null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                Worker worker = new Worker(value2);
                this.workers.setSynchronized(value2, worker);
                if (value2 == ((int) (2097151 & this.controlState.incrementAndGet()))) {
                    z2 = true;
                }
                if (z2) {
                    worker.start();
                    return i2 + 1;
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private final boolean tryCreateWorker(long j) {
        int i = ((int) (2097151 & j)) - ((int) ((j & 4398044413952L) >> 21));
        if (i < 0) {
            i = 0;
        }
        if (i < this.corePoolSize) {
            int createNewWorker = createNewWorker();
            if (createNewWorker == 1 && this.corePoolSize > 1) {
                createNewWorker();
            }
            if (createNewWorker > 0) {
                return true;
            }
        }
        return false;
    }

    private final boolean tryUnpark() {
        Worker worker;
        Symbol symbol;
        int i;
        do {
            AtomicLong atomicLong = this.parkedWorkersStack;
            while (true) {
                long value = atomicLong.getValue();
                worker = (Worker) this.workers.get((int) (2097151 & value));
                if (worker == null) {
                    worker = null;
                    break;
                }
                long j = (2097152 + value) & (-2097152);
                Object nextParkedWorker = worker.getNextParkedWorker();
                while (true) {
                    symbol = NOT_IN_STACK;
                    if (nextParkedWorker == symbol) {
                        i = -1;
                        break;
                    } else if (nextParkedWorker == null) {
                        i = 0;
                        break;
                    } else {
                        Worker worker2 = (Worker) nextParkedWorker;
                        i = worker2.getIndexInArray();
                        if (i != 0) {
                            break;
                        }
                        nextParkedWorker = worker2.getNextParkedWorker();
                    }
                }
                if (i >= 0 && this.parkedWorkersStack.compareAndSet(value, j | i)) {
                    worker.setNextParkedWorker(symbol);
                    break;
                }
            }
            if (worker == null) {
                return false;
            }
        } while (!worker.getWorkerCtl().compareAndSet(-1, 0));
        LockSupport.unpark(worker);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x006d, code lost:
        if (r0 == null) goto L41;
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void close() {
        /*
            r7 = this;
            kotlinx.atomicfu.AtomicBoolean r0 = r7._isTerminated
            boolean r0 = r0.compareAndSet()
            if (r0 != 0) goto La
            goto L96
        La:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof kotlinx.coroutines.scheduling.CoroutineScheduler.Worker
            r2 = 0
            if (r1 == 0) goto L16
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r0 = (kotlinx.coroutines.scheduling.CoroutineScheduler.Worker) r0
            goto L17
        L16:
            r0 = r2
        L17:
            if (r0 == 0) goto L22
            kotlinx.coroutines.scheduling.CoroutineScheduler r1 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r7)
            if (r1 == 0) goto L22
            r2 = r0
        L22:
            kotlinx.coroutines.internal.ResizableAtomicArray r0 = r7.workers
            monitor-enter(r0)
            kotlinx.atomicfu.AtomicLong r1 = r7.controlState     // Catch: java.lang.Throwable -> La8
            long r3 = r1.getValue()     // Catch: java.lang.Throwable -> La8
            r5 = 2097151(0x1fffff, double:1.0361303E-317)
            long r3 = r3 & r5
            int r1 = (int) r3
            monitor-exit(r0)
            r3 = 1
            if (r3 > r1) goto L5d
            r0 = r3
        L35:
            kotlinx.coroutines.internal.ResizableAtomicArray r4 = r7.workers
            java.lang.Object r4 = r4.get(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r4 = (kotlinx.coroutines.scheduling.CoroutineScheduler.Worker) r4
            if (r4 == r2) goto L58
        L42:
            boolean r5 = r4.isAlive()
            if (r5 == 0) goto L51
            java.util.concurrent.locks.LockSupport.unpark(r4)
            r5 = 10000(0x2710, double:4.9407E-320)
            r4.join(r5)
            goto L42
        L51:
            kotlinx.coroutines.scheduling.WorkQueue r4 = r4.localQueue
            kotlinx.coroutines.scheduling.GlobalQueue r5 = r7.globalBlockingQueue
            r4.offloadAllWorkTo(r5)
        L58:
            if (r0 == r1) goto L5d
            int r0 = r0 + 1
            goto L35
        L5d:
            kotlinx.coroutines.scheduling.GlobalQueue r0 = r7.globalBlockingQueue
            r0.close()
            kotlinx.coroutines.scheduling.GlobalQueue r0 = r7.globalCpuQueue
            r0.close()
        L67:
            if (r2 == 0) goto L6f
            kotlinx.coroutines.scheduling.Task r0 = r2.findTask(r3)
            if (r0 != 0) goto L97
        L6f:
            kotlinx.coroutines.scheduling.GlobalQueue r0 = r7.globalCpuQueue
            java.lang.Object r0 = r0.removeFirstOrNull()
            kotlinx.coroutines.scheduling.Task r0 = (kotlinx.coroutines.scheduling.Task) r0
            if (r0 != 0) goto L97
            kotlinx.coroutines.scheduling.GlobalQueue r0 = r7.globalBlockingQueue
            java.lang.Object r0 = r0.removeFirstOrNull()
            kotlinx.coroutines.scheduling.Task r0 = (kotlinx.coroutines.scheduling.Task) r0
            if (r0 != 0) goto L97
            if (r2 == 0) goto L8a
            kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.TERMINATED
            r2.tryReleaseCpu(r0)
        L8a:
            kotlinx.atomicfu.AtomicLong r0 = r7.parkedWorkersStack
            r1 = 0
            r0.setValue(r1)
            kotlinx.atomicfu.AtomicLong r7 = r7.controlState
            r7.setValue(r1)
        L96:
            return
        L97:
            r0.run()     // Catch: java.lang.Throwable -> L9b
            goto L67
        L9b:
            r0 = move-exception
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            java.lang.Thread$UncaughtExceptionHandler r4 = r1.getUncaughtExceptionHandler()
            r4.uncaughtException(r1, r0)
            goto L67
        La8:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.close():void");
    }

    public final void dispatch(Runnable block, TaskContext taskContext, boolean z) {
        Task taskImpl;
        Worker worker;
        Task task;
        boolean z2;
        boolean addLast;
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(taskContext, "taskContext");
        TasksKt.schedulerTimeSource.getClass();
        long nanoTime = System.nanoTime();
        if (block instanceof Task) {
            taskImpl = (Task) block;
            taskImpl.submissionTime = nanoTime;
            taskImpl.taskContext = taskContext;
        } else {
            taskImpl = new TaskImpl(block, nanoTime, taskContext);
        }
        Thread currentThread = Thread.currentThread();
        Worker worker2 = null;
        if (currentThread instanceof Worker) {
            worker = (Worker) currentThread;
        } else {
            worker = null;
        }
        if (worker != null && Intrinsics.areEqual(CoroutineScheduler.this, this)) {
            worker2 = worker;
        }
        boolean z3 = true;
        if (worker2 == null || worker2.state == WorkerState.TERMINATED || (((TaskContextImpl) taskImpl.taskContext).getTaskMode() == 0 && worker2.state == WorkerState.BLOCKING)) {
            task = taskImpl;
        } else {
            worker2.mayHaveLocalTasks = true;
            task = worker2.localQueue.add(taskImpl, z);
        }
        if (task != null) {
            if (((TaskContextImpl) task.taskContext).getTaskMode() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                addLast = this.globalBlockingQueue.addLast(task);
            } else {
                addLast = this.globalCpuQueue.addLast(task);
            }
            if (!addLast) {
                throw new RejectedExecutionException(this.schedulerName + " was terminated");
            }
        }
        if (!z || worker2 == null) {
            z3 = false;
        }
        if (((TaskContextImpl) taskImpl.taskContext).getTaskMode() == 0) {
            if (z3) {
                return;
            }
            signalCpuWork();
            return;
        }
        long addAndGet = this.controlState.addAndGet(2097152L);
        if (!z3 && !tryUnpark() && !tryCreateWorker(addAndGet)) {
            tryUnpark();
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable command) {
        Intrinsics.checkNotNullParameter(command, "command");
        dispatch(command, TasksKt.NonBlockingContext, false);
    }

    public final boolean isTerminated() {
        return this._isTerminated.getValue();
    }

    public final void parkedWorkersStackPush(Worker worker) {
        long value;
        int indexInArray;
        Intrinsics.checkNotNullParameter(worker, "worker");
        if (worker.getNextParkedWorker() != NOT_IN_STACK) {
            return;
        }
        AtomicLong atomicLong = this.parkedWorkersStack;
        do {
            value = atomicLong.getValue();
            indexInArray = worker.getIndexInArray();
            worker.setNextParkedWorker(this.workers.get((int) (2097151 & value)));
        } while (!this.parkedWorkersStack.compareAndSet(value, ((2097152 + value) & (-2097152)) | indexInArray));
    }

    public final void parkedWorkersStackTopUpdate(Worker worker, int i, int i2) {
        Intrinsics.checkNotNullParameter(worker, "worker");
        AtomicLong atomicLong = this.parkedWorkersStack;
        while (true) {
            long value = atomicLong.getValue();
            int i3 = (int) (2097151 & value);
            long j = (2097152 + value) & (-2097152);
            if (i3 == i) {
                if (i2 == 0) {
                    Object nextParkedWorker = worker.getNextParkedWorker();
                    while (true) {
                        if (nextParkedWorker == NOT_IN_STACK) {
                            i3 = -1;
                            break;
                        } else if (nextParkedWorker == null) {
                            i3 = 0;
                            break;
                        } else {
                            Worker worker2 = (Worker) nextParkedWorker;
                            int indexInArray = worker2.getIndexInArray();
                            if (indexInArray != 0) {
                                i3 = indexInArray;
                                break;
                            }
                            nextParkedWorker = worker2.getNextParkedWorker();
                        }
                    }
                } else {
                    i3 = i2;
                }
            }
            if (i3 >= 0 && this.parkedWorkersStack.compareAndSet(value, j | i3)) {
                return;
            }
        }
    }

    public final void signalCpuWork() {
        if (tryUnpark() || tryCreateWorker(this.controlState.getValue())) {
            return;
        }
        tryUnpark();
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        int currentLength = this.workers.currentLength();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 1; i6 < currentLength; i6++) {
            Worker worker = (Worker) this.workers.get(i6);
            if (worker != null) {
                int size$external__kotlinx_coroutines__android_common__kotlinx_coroutines = worker.localQueue.getSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
                int ordinal = worker.state.ordinal();
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        if (ordinal != 2) {
                            if (ordinal != 3) {
                                if (ordinal == 4) {
                                    i5++;
                                }
                            } else {
                                i4++;
                                if (size$external__kotlinx_coroutines__android_common__kotlinx_coroutines > 0) {
                                    arrayList.add(size$external__kotlinx_coroutines__android_common__kotlinx_coroutines + "d");
                                }
                            }
                        } else {
                            i3++;
                        }
                    } else {
                        i2++;
                        arrayList.add(size$external__kotlinx_coroutines__android_common__kotlinx_coroutines + "b");
                    }
                } else {
                    i++;
                    arrayList.add(size$external__kotlinx_coroutines__android_common__kotlinx_coroutines + "c");
                }
            }
        }
        long value = this.controlState.getValue();
        return this.schedulerName + "@" + DebugStringsKt.getHexAddress(this) + "[Pool Size {core = " + this.corePoolSize + ", max = " + this.maxPoolSize + "}, Worker States {CPU = " + i + ", blocking = " + i2 + ", parked = " + i3 + ", dormant = " + i4 + ", terminated = " + i5 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.globalCpuQueue.getSize() + ", global blocking queue size = " + this.globalBlockingQueue.getSize() + ", Control State {created workers= " + ((int) (value & 2097151)) + ", blocking tasks = " + ((int) ((4398044413952L & value) >> 21)) + ", CPUs acquired = " + (this.corePoolSize - ((int) ((value & 9223367638808264704L) >> 42))) + "}]";
    }
}
