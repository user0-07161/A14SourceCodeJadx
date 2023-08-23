package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class DelayKt {
    public static final Object delay(long j, Continuation continuation) {
        Delay delay;
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        Unit unit = Unit.INSTANCE;
        if (i <= 0) {
            return unit;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        if (j < Long.MAX_VALUE) {
            CoroutineContext context = cancellableContinuationImpl.getContext();
            Intrinsics.checkNotNullParameter(context, "<this>");
            CoroutineContext.Element element = context.get(ContinuationInterceptor.Key);
            if (element instanceof Delay) {
                delay = (Delay) element;
            } else {
                delay = null;
            }
            if (delay == null) {
                delay = DefaultExecutorKt.getDefaultDelay();
            }
            delay.scheduleResumeAfterDelay(j, cancellableContinuationImpl);
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return result;
        }
        return unit;
    }
}
