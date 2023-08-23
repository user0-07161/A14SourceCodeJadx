package kotlinx.coroutines;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class DispatchedTaskKt {
    public static final void resume(DispatchedTask dispatchedTask, Continuation delegate, boolean z) {
        Object successfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines;
        UndispatchedCoroutine undispatchedCoroutine;
        boolean clearThreadContext;
        Intrinsics.checkNotNullParameter(dispatchedTask, "<this>");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Object takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines = dispatchedTask.takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        Throwable exceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines = dispatchedTask.getExceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines);
        if (exceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines != null) {
            successfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines = ResultKt.createFailure(exceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines);
        } else {
            successfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines = dispatchedTask.getSuccessfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines);
        }
        if (z) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) delegate;
            Continuation continuation = dispatchedContinuation.continuation;
            Object obj = dispatchedContinuation.countOrElement;
            CoroutineContext context = continuation.getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj);
            if (updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS) {
                undispatchedCoroutine = CoroutineContextKt.updateUndispatchedCompletion(continuation, context, updateThreadContext);
            } else {
                undispatchedCoroutine = null;
            }
            try {
                dispatchedContinuation.continuation.resumeWith(successfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines);
                if (undispatchedCoroutine != null) {
                    if (!clearThreadContext) {
                        return;
                    }
                }
                return;
            } finally {
                if (undispatchedCoroutine == null || undispatchedCoroutine.clearThreadContext()) {
                    ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                }
            }
        }
        delegate.resumeWith(successfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines);
    }
}
