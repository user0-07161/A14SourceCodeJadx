package kotlinx.coroutines;

import kotlin.ExceptionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TasksKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class DispatchedTask extends Task {
    public int resumeMode;

    public DispatchedTask(int i) {
        super(0L, TasksKt.NonBlockingContext);
        this.resumeMode = i;
    }

    public abstract void cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object obj, Throwable th);

    public abstract Continuation getDelegate$external__kotlinx_coroutines__android_common__kotlinx_coroutines();

    public Throwable getExceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object obj) {
        CompletedExceptionally completedExceptionally;
        if (obj instanceof CompletedExceptionally) {
            completedExceptionally = (CompletedExceptionally) obj;
        } else {
            completedExceptionally = null;
        }
        if (completedExceptionally == null) {
            return null;
        }
        return completedExceptionally.cause;
    }

    public final void handleFatalException(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            ExceptionsKt.addSuppressed(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        Intrinsics.checkNotNull(th);
        CoroutineExceptionHandlerKt.handleCoroutineException(getDelegate$external__kotlinx_coroutines__android_common__kotlinx_coroutines().getContext(), new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0040, code lost:
        r10 = kotlinx.coroutines.Job.Key;
        r7 = (kotlinx.coroutines.Job) r7.get(kotlinx.coroutines.Job.Key.$$INSTANCE);
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r13 = this;
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            kotlinx.coroutines.scheduling.TaskContext r1 = r13.taskContext
            kotlin.coroutines.Continuation r2 = r13.getDelegate$external__kotlinx_coroutines__android_common__kotlinx_coroutines()     // Catch: java.lang.Throwable -> L9f
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r3)     // Catch: java.lang.Throwable -> L9f
            kotlinx.coroutines.internal.DispatchedContinuation r2 = (kotlinx.coroutines.internal.DispatchedContinuation) r2     // Catch: java.lang.Throwable -> L9f
            kotlin.coroutines.Continuation r3 = r2.continuation     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r2 = r2.countOrElement     // Catch: java.lang.Throwable -> L9f
            kotlin.coroutines.CoroutineContext r4 = r3.getContext()     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r2 = kotlinx.coroutines.internal.ThreadContextKt.updateThreadContext(r4, r2)     // Catch: java.lang.Throwable -> L9f
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.internal.ThreadContextKt.NO_THREAD_ELEMENTS     // Catch: java.lang.Throwable -> L9f
            r6 = 0
            if (r2 == r5) goto L25
            kotlinx.coroutines.UndispatchedCoroutine r5 = kotlinx.coroutines.CoroutineContextKt.updateUndispatchedCompletion(r3, r4, r2)     // Catch: java.lang.Throwable -> L9f
            goto L26
        L25:
            r5 = r6
        L26:
            kotlin.coroutines.CoroutineContext r7 = r3.getContext()     // Catch: java.lang.Throwable -> L92
            java.lang.Object r8 = r13.takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines()     // Catch: java.lang.Throwable -> L92
            java.lang.Throwable r9 = r13.getExceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(r8)     // Catch: java.lang.Throwable -> L92
            if (r9 != 0) goto L4b
            int r10 = r13.resumeMode     // Catch: java.lang.Throwable -> L92
            r11 = 1
            if (r10 == r11) goto L3e
            r12 = 2
            if (r10 != r12) goto L3d
            goto L3e
        L3d:
            r11 = 0
        L3e:
            if (r11 == 0) goto L4b
            kotlinx.coroutines.Job$Key r10 = kotlinx.coroutines.Job.Key     // Catch: java.lang.Throwable -> L92
            kotlinx.coroutines.Job$Key r10 = kotlinx.coroutines.Job.Key.$$INSTANCE     // Catch: java.lang.Throwable -> L92
            kotlin.coroutines.CoroutineContext$Element r7 = r7.get(r10)     // Catch: java.lang.Throwable -> L92
            kotlinx.coroutines.Job r7 = (kotlinx.coroutines.Job) r7     // Catch: java.lang.Throwable -> L92
            goto L4c
        L4b:
            r7 = r6
        L4c:
            if (r7 == 0) goto L65
            boolean r10 = r7.isActive()     // Catch: java.lang.Throwable -> L92
            if (r10 != 0) goto L65
            kotlinx.coroutines.JobSupport r7 = (kotlinx.coroutines.JobSupport) r7     // Catch: java.lang.Throwable -> L92
            java.util.concurrent.CancellationException r7 = r7.getCancellationException()     // Catch: java.lang.Throwable -> L92
            r13.cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(r8, r7)     // Catch: java.lang.Throwable -> L92
            kotlin.Result$Failure r7 = kotlin.ResultKt.createFailure(r7)     // Catch: java.lang.Throwable -> L92
            r3.resumeWith(r7)     // Catch: java.lang.Throwable -> L92
            goto L76
        L65:
            if (r9 == 0) goto L6f
            kotlin.Result$Failure r7 = kotlin.ResultKt.createFailure(r9)     // Catch: java.lang.Throwable -> L92
            r3.resumeWith(r7)     // Catch: java.lang.Throwable -> L92
            goto L76
        L6f:
            java.lang.Object r7 = r13.getSuccessfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(r8)     // Catch: java.lang.Throwable -> L92
            r3.resumeWith(r7)     // Catch: java.lang.Throwable -> L92
        L76:
            if (r5 == 0) goto L7e
            boolean r3 = r5.clearThreadContext()     // Catch: java.lang.Throwable -> L9f
            if (r3 == 0) goto L81
        L7e:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r4, r2)     // Catch: java.lang.Throwable -> L9f
        L81:
            r1.getClass()     // Catch: java.lang.Throwable -> L85
            goto L8a
        L85:
            r0 = move-exception
            kotlin.Result$Failure r0 = kotlin.ResultKt.createFailure(r0)
        L8a:
            java.lang.Throwable r0 = kotlin.Result.m478exceptionOrNullimpl(r0)
            r13.handleFatalException(r6, r0)
            goto Lb0
        L92:
            r3 = move-exception
            if (r5 == 0) goto L9b
            boolean r5 = r5.clearThreadContext()     // Catch: java.lang.Throwable -> L9f
            if (r5 == 0) goto L9e
        L9b:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r4, r2)     // Catch: java.lang.Throwable -> L9f
        L9e:
            throw r3     // Catch: java.lang.Throwable -> L9f
        L9f:
            r2 = move-exception
            r1.getClass()     // Catch: java.lang.Throwable -> La4
            goto La9
        La4:
            r0 = move-exception
            kotlin.Result$Failure r0 = kotlin.ResultKt.createFailure(r0)
        La9:
            java.lang.Throwable r0 = kotlin.Result.m478exceptionOrNullimpl(r0)
            r13.handleFatalException(r2, r0)
        Lb0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DispatchedTask.run():void");
    }

    public abstract Object takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();

    public Object getSuccessfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object obj) {
        return obj;
    }
}
