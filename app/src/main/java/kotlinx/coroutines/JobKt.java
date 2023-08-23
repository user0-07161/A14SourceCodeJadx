package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class JobKt {
    public static final void cancel(CoroutineContext coroutineContext, CancellationException cancellationException) {
        Intrinsics.checkNotNullParameter(coroutineContext, "<this>");
        Job.Key key = Job.Key;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job != null) {
            job.cancel(cancellationException);
        }
    }

    public static final void ensureActive(CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "<this>");
        Job.Key key = Job.Key;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job != null) {
            ensureActive(job);
        }
    }

    public static final Job getJob(CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "<this>");
        Job.Key key = Job.Key;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job != null) {
            return job;
        }
        throw new IllegalStateException(("Current context doesn't contain Job in it: " + coroutineContext).toString());
    }

    public static final boolean isActive(CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "<this>");
        Job.Key key = Job.Key;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job != null && job.isActive()) {
            return true;
        }
        return false;
    }

    public static final void ensureActive(Job job) {
        if (!job.isActive()) {
            throw ((JobSupport) job).getCancellationException();
        }
    }
}
