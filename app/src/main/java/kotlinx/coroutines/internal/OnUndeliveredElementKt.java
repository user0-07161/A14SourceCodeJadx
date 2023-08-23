package kotlinx.coroutines.internal;

import kotlin.ExceptionsKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class OnUndeliveredElementKt {
    public static final Function1 bindCancellationFun(final Function1 function1, final Object obj, final CoroutineContext context) {
        Intrinsics.checkNotNullParameter(function1, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        return new Function1() { // from class: kotlinx.coroutines.internal.OnUndeliveredElementKt$bindCancellationFun$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                Intrinsics.checkNotNullParameter((Throwable) obj2, "<anonymous parameter 0>");
                Function1 function12 = Function1.this;
                Object obj3 = obj;
                CoroutineContext context2 = context;
                Intrinsics.checkNotNullParameter(function12, "<this>");
                Intrinsics.checkNotNullParameter(context2, "context");
                UndeliveredElementException callUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function12, obj3, null);
                if (callUndeliveredElementCatchingException != null) {
                    CoroutineExceptionHandlerKt.handleCoroutineException(context2, callUndeliveredElementCatchingException);
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static final UndeliveredElementException callUndeliveredElementCatchingException(Function1 function1, Object obj, UndeliveredElementException undeliveredElementException) {
        Intrinsics.checkNotNullParameter(function1, "<this>");
        try {
            function1.invoke(obj);
        } catch (Throwable th) {
            if (undeliveredElementException != null && undeliveredElementException.getCause() != th) {
                ExceptionsKt.addSuppressed(undeliveredElementException, th);
            } else {
                return new UndeliveredElementException("Exception in undelivered element handler for " + obj, th);
            }
        }
        return undeliveredElementException;
    }
}
