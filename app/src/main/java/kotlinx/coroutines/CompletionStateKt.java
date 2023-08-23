package kotlinx.coroutines;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CompletionStateKt {
    public static final Object recoverResult(Object obj, Continuation uCont) {
        Intrinsics.checkNotNullParameter(uCont, "uCont");
        if (obj instanceof CompletedExceptionally) {
            return ResultKt.createFailure(((CompletedExceptionally) obj).cause);
        }
        return obj;
    }
}
