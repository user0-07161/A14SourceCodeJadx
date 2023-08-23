package kotlinx.coroutines.scheduling;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SchedulerCoroutineDispatcher extends ExecutorCoroutineDispatcher {
    private CoroutineScheduler coroutineScheduler;

    public SchedulerCoroutineDispatcher(int i, int i2, long j) {
        this.coroutineScheduler = new CoroutineScheduler(i, i2, j, "DefaultDispatcher");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext context, Runnable block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(block, "block");
        CoroutineScheduler coroutineScheduler = this.coroutineScheduler;
        Symbol symbol = CoroutineScheduler.NOT_IN_STACK;
        coroutineScheduler.dispatch(block, TasksKt.NonBlockingContext, false);
    }

    public final void dispatchWithContext$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Runnable block, TaskContext context, boolean z) {
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(context, "context");
        this.coroutineScheduler.dispatch(block, context, z);
    }
}
