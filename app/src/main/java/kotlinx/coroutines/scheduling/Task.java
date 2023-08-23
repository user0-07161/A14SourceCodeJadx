package kotlinx.coroutines.scheduling;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Task implements Runnable {
    public long submissionTime;
    public TaskContext taskContext;

    public Task(long j, TaskContext taskContext) {
        Intrinsics.checkNotNullParameter(taskContext, "taskContext");
        this.submissionTime = j;
        this.taskContext = taskContext;
    }
}
