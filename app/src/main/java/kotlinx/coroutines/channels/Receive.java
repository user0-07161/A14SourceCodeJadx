package kotlinx.coroutines.channels;

import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Receive extends LockFreeLinkedListNode implements ReceiveOrClosed {
    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public final Object getOfferResult() {
        return AbstractChannelKt.OFFER_SUCCESS;
    }

    public Function1 resumeOnCancellationFun(Object obj) {
        return null;
    }

    public abstract void resumeReceiveClosed(Closed closed);
}
