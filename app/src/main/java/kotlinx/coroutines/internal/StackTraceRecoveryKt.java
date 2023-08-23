package kotlinx.coroutines.internal;

import kotlin.Result;
import kotlin.ResultKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class StackTraceRecoveryKt {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        Object createFailure;
        Object createFailure2;
        Object obj = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        try {
            createFailure = Class.forName("kotlin.coroutines.jvm.internal.BaseContinuationImpl").getCanonicalName();
        } catch (Throwable th) {
            createFailure = ResultKt.createFailure(th);
        }
        if (Result.m478exceptionOrNullimpl(createFailure) == null) {
            obj = createFailure;
        }
        String str = (String) obj;
        try {
            createFailure2 = StackTraceRecoveryKt.class.getCanonicalName();
        } catch (Throwable th2) {
            createFailure2 = ResultKt.createFailure(th2);
        }
        if (Result.m478exceptionOrNullimpl(createFailure2) != null) {
            createFailure2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
        String str2 = (String) createFailure2;
    }
}
