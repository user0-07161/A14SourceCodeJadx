package kotlinx.coroutines.channels;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.UndeliveredElementException;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SendElementWithUndeliveredHandler extends SendElement {
    public final Function1 onUndeliveredElement;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SendElementWithUndeliveredHandler(Object obj, CancellableContinuationImpl cancellableContinuationImpl, Function1 onUndeliveredElement) {
        super(obj, cancellableContinuationImpl);
        Intrinsics.checkNotNullParameter(onUndeliveredElement, "onUndeliveredElement");
        this.onUndeliveredElement = onUndeliveredElement;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public final boolean remove$1() {
        if (!super.remove$1()) {
            return false;
        }
        undeliveredElement();
        return true;
    }

    @Override // kotlinx.coroutines.channels.Send
    public final void undeliveredElement() {
        Object pollResult = getPollResult();
        CoroutineContext context = ((CancellableContinuationImpl) this.cont).getContext();
        Function1 function1 = this.onUndeliveredElement;
        Intrinsics.checkNotNullParameter(function1, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        UndeliveredElementException callUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, pollResult, null);
        if (callUndeliveredElementCatchingException != null) {
            CoroutineExceptionHandlerKt.handleCoroutineException(context, callUndeliveredElementCatchingException);
        }
    }
}
