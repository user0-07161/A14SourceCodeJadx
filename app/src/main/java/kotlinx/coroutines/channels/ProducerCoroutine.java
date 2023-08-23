package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ProducerCoroutine extends ChannelCoroutine implements ProducerScope {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProducerCoroutine(CoroutineContext parentContext, AbstractChannel abstractChannel) {
        super(parentContext, abstractChannel);
        Intrinsics.checkNotNullParameter(parentContext, "parentContext");
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public final boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected final void onCancelled(Throwable cause, boolean z) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        if (!get_channel().close(cause) && !z) {
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), cause);
        }
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    public final void onCompleted(Object obj) {
        Unit value = (Unit) obj;
        Intrinsics.checkNotNullParameter(value, "value");
        get_channel().close(null);
    }
}
