package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.EventLoopImplBase;
import kotlinx.coroutines.internal.ArrayQueue;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class EventLoopImplPlatform extends CoroutineDispatcher {
    public static final /* synthetic */ int $r8$clinit = 0;
    private boolean shared;
    private ArrayQueue unconfinedQueue;
    private long useCount;

    public final void decrementUseCount(boolean z) {
        long j;
        long j2 = this.useCount;
        if (z) {
            j = 4294967296L;
        } else {
            j = 1;
        }
        long j3 = j2 - j;
        this.useCount = j3;
        if (j3 <= 0 && this.shared) {
            shutdown();
        }
    }

    public final void dispatchUnconfined(DispatchedTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        ArrayQueue arrayQueue = this.unconfinedQueue;
        if (arrayQueue == null) {
            arrayQueue = new ArrayQueue();
            this.unconfinedQueue = arrayQueue;
        }
        arrayQueue.addLast(task);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getNextTime() {
        ArrayQueue arrayQueue = this.unconfinedQueue;
        if (arrayQueue == null || arrayQueue.isEmpty()) {
            return Long.MAX_VALUE;
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Thread getThread();

    public final void incrementUseCount(boolean z) {
        long j;
        long j2 = this.useCount;
        if (z) {
            j = 4294967296L;
        } else {
            j = 1;
        }
        this.useCount = j2 + j;
        if (!z) {
            this.shared = true;
        }
    }

    public final boolean isUnconfinedLoopActive() {
        if (this.useCount >= 4294967296L) {
            return true;
        }
        return false;
    }

    public final boolean isUnconfinedQueueEmpty() {
        ArrayQueue arrayQueue = this.unconfinedQueue;
        if (arrayQueue != null) {
            return arrayQueue.isEmpty();
        }
        return true;
    }

    public abstract long processNextEvent();

    public final boolean processUnconfinedEvent() {
        DispatchedTask dispatchedTask;
        ArrayQueue arrayQueue = this.unconfinedQueue;
        if (arrayQueue == null || (dispatchedTask = (DispatchedTask) arrayQueue.removeFirstOrNull()) == null) {
            return false;
        }
        dispatchedTask.run();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reschedule(long j, EventLoopImplBase.DelayedTask delayedTask) {
        DefaultExecutor.INSTANCE.schedule(j, delayedTask);
    }

    public abstract void shutdown();
}
