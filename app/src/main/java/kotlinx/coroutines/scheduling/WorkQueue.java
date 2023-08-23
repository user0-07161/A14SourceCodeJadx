package kotlinx.coroutines.scheduling;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class WorkQueue {
    private final AtomicReferenceArray buffer = new AtomicReferenceArray(128);
    private final AtomicRef lastScheduledTask = new AtomicRef(null);
    private final AtomicInt producerIndex = new AtomicInt();
    private final AtomicInt consumerIndex = new AtomicInt();
    private final AtomicInt blockingTasksInBuffer = new AtomicInt();

    private final Task addLast(Task task) {
        boolean z = true;
        if (((TaskContextImpl) task.taskContext).getTaskMode() != 1) {
            z = false;
        }
        if (z) {
            this.blockingTasksInBuffer.incrementAndGet();
        }
        if (getBufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == 127) {
            return task;
        }
        AtomicInt atomicInt = this.producerIndex;
        int value = 127 & atomicInt.getValue();
        while (true) {
            AtomicReferenceArray atomicReferenceArray = this.buffer;
            if (atomicReferenceArray.get(value) != null) {
                Thread.yield();
            } else {
                atomicReferenceArray.lazySet(value, task);
                atomicInt.incrementAndGet();
                return null;
            }
        }
    }

    private final Task pollBuffer() {
        Task task;
        while (true) {
            AtomicInt atomicInt = this.consumerIndex;
            int value = atomicInt.getValue();
            if (value - this.producerIndex.getValue() == 0) {
                return null;
            }
            int i = value & 127;
            if (atomicInt.compareAndSet(value, value + 1) && (task = (Task) this.buffer.getAndSet(i, null)) != null) {
                boolean z = true;
                if (((TaskContextImpl) task.taskContext).getTaskMode() != 1) {
                    z = false;
                }
                if (z) {
                    this.blockingTasksInBuffer.decrementAndGet();
                }
                return task;
            }
        }
    }

    private final long tryStealLastScheduled(WorkQueue workQueue, boolean z) {
        Task task;
        do {
            task = (Task) workQueue.lastScheduledTask.getValue();
            if (task == null) {
                return -2L;
            }
            if (z) {
                boolean z2 = true;
                if (((TaskContextImpl) task.taskContext).getTaskMode() != 1) {
                    z2 = false;
                }
                if (!z2) {
                    return -2L;
                }
            }
            TasksKt.schedulerTimeSource.getClass();
            long nanoTime = System.nanoTime() - task.submissionTime;
            long j = TasksKt.WORK_STEALING_TIME_RESOLUTION_NS;
            if (nanoTime < j) {
                return j - nanoTime;
            }
        } while (!workQueue.lastScheduledTask.compareAndSet(task, null));
        add(task, false);
        return -1L;
    }

    public final Task add(Task task, boolean z) {
        if (z) {
            return addLast(task);
        }
        Task task2 = (Task) this.lastScheduledTask.getAndSet(task);
        if (task2 == null) {
            return null;
        }
        return addLast(task2);
    }

    public final int getBufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this.producerIndex.getValue() - this.consumerIndex.getValue();
    }

    public final int getSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        Object value = this.lastScheduledTask.getValue();
        int bufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getBufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (value != null) {
            return bufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines + 1;
        }
        return bufferSize$external__kotlinx_coroutines__android_common__kotlinx_coroutines;
    }

    public final void offloadAllWorkTo(GlobalQueue globalQueue) {
        boolean z;
        Intrinsics.checkNotNullParameter(globalQueue, "globalQueue");
        Task task = (Task) this.lastScheduledTask.getAndSet(null);
        if (task != null) {
            globalQueue.addLast(task);
        }
        do {
            Task pollBuffer = pollBuffer();
            if (pollBuffer == null) {
                z = false;
                continue;
            } else {
                globalQueue.addLast(pollBuffer);
                z = true;
                continue;
            }
        } while (z);
    }

    public final Task poll() {
        Task task = (Task) this.lastScheduledTask.getAndSet(null);
        if (task == null) {
            return pollBuffer();
        }
        return task;
    }

    public final long tryStealBlockingFrom(WorkQueue victim) {
        Intrinsics.checkNotNullParameter(victim, "victim");
        int value = victim.consumerIndex.getValue();
        int value2 = victim.producerIndex.getValue();
        AtomicReferenceArray atomicReferenceArray = victim.buffer;
        while (true) {
            boolean z = true;
            if (value == value2) {
                break;
            }
            int i = value & 127;
            AtomicInt atomicInt = victim.blockingTasksInBuffer;
            if (atomicInt.getValue() == 0) {
                break;
            }
            Task task = (Task) atomicReferenceArray.get(i);
            if (task != null) {
                if (((TaskContextImpl) task.taskContext).getTaskMode() != 1) {
                    z = false;
                }
                if (z && atomicReferenceArray.compareAndSet(i, task, null)) {
                    atomicInt.decrementAndGet();
                    add(task, false);
                    return -1L;
                }
            }
            value++;
        }
        return tryStealLastScheduled(victim, true);
    }

    public final long tryStealFrom(WorkQueue victim) {
        Intrinsics.checkNotNullParameter(victim, "victim");
        Task pollBuffer = victim.pollBuffer();
        if (pollBuffer != null) {
            add(pollBuffer, false);
            return -1L;
        }
        return tryStealLastScheduled(victim, false);
    }
}
