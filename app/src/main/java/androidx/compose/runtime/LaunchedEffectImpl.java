package androidx.compose.runtime;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LaunchedEffectImpl implements RememberObserver {
    private Job job;
    private final ContextScope scope;
    private final Function2 task;

    public LaunchedEffectImpl(CoroutineContext parentCoroutineContext, Function2 task) {
        Intrinsics.checkNotNullParameter(parentCoroutineContext, "parentCoroutineContext");
        Intrinsics.checkNotNullParameter(task, "task");
        this.task = task;
        this.scope = CoroutineScopeKt.CoroutineScope(parentCoroutineContext);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onAbandoned() {
        Job job = this.job;
        if (job != null) {
            ((JobSupport) job).cancel(null);
        }
        this.job = null;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onForgotten() {
        Job job = this.job;
        if (job != null) {
            ((JobSupport) job).cancel(null);
        }
        this.job = null;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onRemembered() {
        Job job = this.job;
        if (job != null) {
            CancellationException cancellationException = new CancellationException("Old job was still running!");
            cancellationException.initCause(null);
            ((JobSupport) job).cancel(cancellationException);
        }
        this.job = BuildersKt.launch$default(this.scope, null, null, this.task, 3);
    }
}
