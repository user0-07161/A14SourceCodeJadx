package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class DebugStringsKt {
    public static final String getClassSimpleName(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        return obj.getClass().getSimpleName();
    }

    public static final String getHexAddress(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        String hexString = Integer.toHexString(System.identityHashCode(obj));
        Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(System.identityHashCode(this))");
        return hexString;
    }

    public static final String toDebugString(Continuation continuation) {
        String str;
        Intrinsics.checkNotNullParameter(continuation, "<this>");
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            str = continuation + "@" + getHexAddress(continuation);
        } catch (Throwable th) {
            str = ResultKt.createFailure(th);
        }
        Throwable m478exceptionOrNullimpl = Result.m478exceptionOrNullimpl(str);
        String str2 = str;
        if (m478exceptionOrNullimpl != null) {
            str2 = continuation.getClass().getName() + "@" + getHexAddress(continuation);
        }
        return (String) str2;
    }
}
