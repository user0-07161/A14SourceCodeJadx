package kotlinx.coroutines.channels;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class SendElement extends Send {
    public final CancellableContinuation cont;
    private final Object pollResult;

    public SendElement(Object obj, CancellableContinuationImpl cancellableContinuationImpl) {
        this.pollResult = obj;
        this.cont = cancellableContinuationImpl;
    }

    @Override // kotlinx.coroutines.channels.Send
    public final void completeResumeSend() {
        ((CancellableContinuationImpl) this.cont).completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
    }

    @Override // kotlinx.coroutines.channels.Send
    public final Object getPollResult() {
        return this.pollResult;
    }

    @Override // kotlinx.coroutines.channels.Send
    public final void resumeSendClosed(Closed closed) {
        Throwable th = closed.closeCause;
        if (th == null) {
            th = new ClosedSendChannelException("Channel was closed");
        }
        ((CancellableContinuationImpl) this.cont).resumeWith(ResultKt.createFailure(th));
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public final String toString() {
        String classSimpleName = DebugStringsKt.getClassSimpleName(this);
        String hexAddress = DebugStringsKt.getHexAddress(this);
        return classSimpleName + "@" + hexAddress + "(" + this.pollResult + ")";
    }

    @Override // kotlinx.coroutines.channels.Send
    public final Symbol tryResumeSend() {
        if (((CancellableContinuationImpl) this.cont).tryResume(Unit.INSTANCE, (Object) null) == null) {
            return null;
        }
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }
}
