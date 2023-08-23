package kotlinx.coroutines.channels;

import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Closed extends Send implements ReceiveOrClosed {
    public final Throwable closeCause;

    public Closed(Throwable th) {
        this.closeCause = th;
    }

    public final Throwable getReceiveException() {
        Throwable th = this.closeCause;
        if (th == null) {
            return new ClosedReceiveChannelException("Channel was closed");
        }
        return th;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public final String toString() {
        String hexAddress = DebugStringsKt.getHexAddress(this);
        return "Closed@" + hexAddress + "[" + this.closeCause + "]";
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public final Symbol tryResumeReceive(Object obj) {
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    @Override // kotlinx.coroutines.channels.Send
    public final Symbol tryResumeSend() {
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    @Override // kotlinx.coroutines.channels.Send
    public final void completeResumeSend() {
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public final Object getOfferResult() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.Send
    public final Object getPollResult() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public final void completeResumeReceive(Object obj) {
    }

    @Override // kotlinx.coroutines.channels.Send
    public final void resumeSendClosed(Closed closed) {
    }
}
