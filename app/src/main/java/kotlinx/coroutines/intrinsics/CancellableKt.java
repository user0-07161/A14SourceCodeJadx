package kotlinx.coroutines.intrinsics;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CancellableKt {
    public static void startCoroutineCancellable$default(Function2 function2, AbstractCoroutine abstractCoroutine, AbstractCoroutine abstractCoroutine2) {
        Intrinsics.checkNotNullParameter(function2, "<this>");
        try {
            DispatchedContinuationKt.resumeCancellableWith(IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(abstractCoroutine, abstractCoroutine2, function2)), Unit.INSTANCE, null);
        } catch (Throwable th) {
            abstractCoroutine2.resumeWith(ResultKt.createFailure(th));
            throw th;
        }
    }
}
