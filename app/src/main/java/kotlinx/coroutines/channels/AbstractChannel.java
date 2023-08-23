package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BeforeResumeCancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AbstractChannel extends AbstractSendChannel implements Channel {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Itr implements ChannelIterator {
        public final AbstractChannel channel;
        private Object result;

        public Itr(AbstractChannel channel) {
            Intrinsics.checkNotNullParameter(channel, "channel");
            this.channel = channel;
            this.result = AbstractChannelKt.POLL_FAILED;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public final Object hasNext(ContinuationImpl continuationImpl) {
            Function1 function1;
            Object obj = this.result;
            Symbol symbol = AbstractChannelKt.POLL_FAILED;
            boolean z = false;
            if (obj != symbol) {
                if (obj instanceof Closed) {
                    Closed closed = (Closed) obj;
                    if (closed.closeCause != null) {
                        Throwable receiveException = closed.getReceiveException();
                        int i = StackTraceRecoveryKt.$r8$clinit;
                        throw receiveException;
                    }
                } else {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
            AbstractChannel abstractChannel = this.channel;
            Object pollInternal = abstractChannel.pollInternal();
            this.result = pollInternal;
            if (pollInternal != symbol) {
                if (pollInternal instanceof Closed) {
                    Closed closed2 = (Closed) pollInternal;
                    if (closed2.closeCause != null) {
                        Throwable receiveException2 = closed2.getReceiveException();
                        int i2 = StackTraceRecoveryKt.$r8$clinit;
                        throw receiveException2;
                    }
                } else {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
            CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuationImpl));
            ReceiveHasNext receiveHasNext = new ReceiveHasNext(this, orCreateCancellableContinuation);
            while (true) {
                if (abstractChannel.enqueueReceiveInternal(receiveHasNext)) {
                    orCreateCancellableContinuation.invokeOnCancellation(new RemoveReceiveOnCancel(receiveHasNext));
                    break;
                }
                Object pollInternal2 = abstractChannel.pollInternal();
                this.result = pollInternal2;
                if (pollInternal2 instanceof Closed) {
                    Closed closed3 = (Closed) pollInternal2;
                    if (closed3.closeCause == null) {
                        orCreateCancellableContinuation.resumeWith(Boolean.FALSE);
                    } else {
                        orCreateCancellableContinuation.resumeWith(ResultKt.createFailure(closed3.getReceiveException()));
                    }
                } else if (pollInternal2 != AbstractChannelKt.POLL_FAILED) {
                    Boolean bool = Boolean.TRUE;
                    Function1 function12 = abstractChannel.onUndeliveredElement;
                    if (function12 != null) {
                        function1 = OnUndeliveredElementKt.bindCancellationFun(function12, pollInternal2, orCreateCancellableContinuation.getContext());
                    } else {
                        function1 = null;
                    }
                    orCreateCancellableContinuation.resume(bool, function1);
                }
            }
            return orCreateCancellableContinuation.getResult();
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public final Object next() {
            Object obj = this.result;
            if (!(obj instanceof Closed)) {
                Symbol symbol = AbstractChannelKt.POLL_FAILED;
                if (obj != symbol) {
                    this.result = symbol;
                    return obj;
                }
                throw new IllegalStateException("'hasNext' should be called prior to 'next' invocation");
            }
            Throwable receiveException = ((Closed) obj).getReceiveException();
            int i = StackTraceRecoveryKt.$r8$clinit;
            throw receiveException;
        }

        public final void setResult(Object obj) {
            this.result = obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public class ReceiveElement extends Receive {
        public final CancellableContinuation cont;
        public final int receiveMode;

        public ReceiveElement(CancellableContinuationImpl cancellableContinuationImpl, int i) {
            this.cont = cancellableContinuationImpl;
            this.receiveMode = i;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public final void completeResumeReceive(Object obj) {
            ((CancellableContinuationImpl) this.cont).completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public final void resumeReceiveClosed(Closed closed) {
            Intrinsics.checkNotNullParameter(closed, "closed");
            int i = this.receiveMode;
            CancellableContinuation cancellableContinuation = this.cont;
            if (i == 1) {
                ((CancellableContinuationImpl) cancellableContinuation).resumeWith(ChannelResult.m485boximpl(new ChannelResult.Closed(closed.closeCause)));
                return;
            }
            ((CancellableContinuationImpl) cancellableContinuation).resumeWith(ResultKt.createFailure(closed.getReceiveException()));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public final String toString() {
            String hexAddress = DebugStringsKt.getHexAddress(this);
            return "ReceiveElement@" + hexAddress + "[receiveMode=" + this.receiveMode + "]";
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public final Symbol tryResumeReceive(Object obj) {
            Object obj2;
            if (this.receiveMode == 1) {
                obj2 = ChannelResult.m485boximpl(obj);
            } else {
                obj2 = obj;
            }
            if (((CancellableContinuationImpl) this.cont).tryResume(obj2, resumeOnCancellationFun(obj)) == null) {
                return null;
            }
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ReceiveElementWithUndeliveredHandler extends ReceiveElement {
        public final Function1 onUndeliveredElement;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReceiveElementWithUndeliveredHandler(CancellableContinuationImpl cancellableContinuationImpl, int i, Function1 onUndeliveredElement) {
            super(cancellableContinuationImpl, i);
            Intrinsics.checkNotNullParameter(onUndeliveredElement, "onUndeliveredElement");
            this.onUndeliveredElement = onUndeliveredElement;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public final Function1 resumeOnCancellationFun(Object obj) {
            return OnUndeliveredElementKt.bindCancellationFun(this.onUndeliveredElement, obj, ((CancellableContinuationImpl) this.cont).getContext());
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class ReceiveHasNext extends Receive {
        public final CancellableContinuation cont;
        public final Itr iterator;

        public ReceiveHasNext(Itr iterator, CancellableContinuationImpl cancellableContinuationImpl) {
            Intrinsics.checkNotNullParameter(iterator, "iterator");
            this.iterator = iterator;
            this.cont = cancellableContinuationImpl;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public final void completeResumeReceive(Object obj) {
            this.iterator.setResult(obj);
            ((CancellableContinuationImpl) this.cont).completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public final Function1 resumeOnCancellationFun(Object obj) {
            Function1 function1 = this.iterator.channel.onUndeliveredElement;
            if (function1 != null) {
                return OnUndeliveredElementKt.bindCancellationFun(function1, obj, ((CancellableContinuationImpl) this.cont).getContext());
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public final void resumeReceiveClosed(Closed closed) {
            Symbol tryResumeWithException;
            Intrinsics.checkNotNullParameter(closed, "closed");
            Throwable th = closed.closeCause;
            CancellableContinuation cancellableContinuation = this.cont;
            if (th == null) {
                tryResumeWithException = ((CancellableContinuationImpl) cancellableContinuation).tryResume(Boolean.FALSE, (Object) null);
            } else {
                tryResumeWithException = ((CancellableContinuationImpl) cancellableContinuation).tryResumeWithException(closed.getReceiveException());
            }
            if (tryResumeWithException != null) {
                this.iterator.setResult(closed);
                ((CancellableContinuationImpl) cancellableContinuation).completeResume(tryResumeWithException);
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public final String toString() {
            return "ReceiveHasNext@".concat(DebugStringsKt.getHexAddress(this));
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public final Symbol tryResumeReceive(Object obj) {
            if (((CancellableContinuationImpl) this.cont).tryResume((Object) Boolean.TRUE, resumeOnCancellationFun(obj)) == null) {
                return null;
            }
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class RemoveReceiveOnCancel extends BeforeResumeCancelHandler {
        private final Receive receive;

        public RemoveReceiveOnCancel(Receive receive) {
            this.receive = receive;
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return Unit.INSTANCE;
        }

        public final String toString() {
            return "RemoveReceiveOnCancel[" + this.receive + "]";
        }

        @Override // kotlinx.coroutines.CancelHandler
        public final void invoke(Throwable th) {
            if (this.receive.remove$1()) {
                AbstractChannel.this.getClass();
            }
        }
    }

    private final Object receiveSuspend(int i, ContinuationImpl continuationImpl) {
        ReceiveElement receiveElementWithUndeliveredHandler;
        Object obj;
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuationImpl));
        Function1 function1 = this.onUndeliveredElement;
        if (function1 == null) {
            receiveElementWithUndeliveredHandler = new ReceiveElement(orCreateCancellableContinuation, i);
        } else {
            receiveElementWithUndeliveredHandler = new ReceiveElementWithUndeliveredHandler(orCreateCancellableContinuation, i, function1);
        }
        while (true) {
            if (enqueueReceiveInternal(receiveElementWithUndeliveredHandler)) {
                orCreateCancellableContinuation.invokeOnCancellation(new RemoveReceiveOnCancel(receiveElementWithUndeliveredHandler));
                break;
            }
            Object pollInternal = pollInternal();
            if (pollInternal instanceof Closed) {
                receiveElementWithUndeliveredHandler.resumeReceiveClosed((Closed) pollInternal);
                break;
            } else if (pollInternal != AbstractChannelKt.POLL_FAILED) {
                if (receiveElementWithUndeliveredHandler.receiveMode == 1) {
                    obj = ChannelResult.m485boximpl(pollInternal);
                } else {
                    obj = pollInternal;
                }
                orCreateCancellableContinuation.resume(obj, receiveElementWithUndeliveredHandler.resumeOnCancellationFun(pollInternal));
            }
        }
        return orCreateCancellableContinuation.getResult();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cancellationException) {
        if (isClosedForReceive()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new CancellationException(DebugStringsKt.getClassSimpleName(this).concat(" was cancelled"));
        }
        onCancelIdempotent(close(cancellationException));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean enqueueReceiveInternal(final Receive receive) {
        int tryCondAddNext;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysEmpty()) {
            LockFreeLinkedListHead queue = getQueue();
            do {
                prevNode = queue.getPrevNode();
                if (!(!(prevNode instanceof Send))) {
                }
            } while (!prevNode.addNext(receive, queue));
            return true;
        }
        LockFreeLinkedListHead queue2 = getQueue();
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(receive) { // from class: kotlinx.coroutines.channels.AbstractChannel$enqueueReceiveInternal$$inlined$addLastIfPrevAndIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            public final Symbol prepare(Object obj) {
                LockFreeLinkedListNode affected = (LockFreeLinkedListNode) obj;
                Intrinsics.checkNotNullParameter(affected, "affected");
                if (this.isBufferEmpty()) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        do {
            LockFreeLinkedListNode prevNode2 = queue2.getPrevNode();
            if (!(!(prevNode2 instanceof Send))) {
                break;
            }
            tryCondAddNext = prevNode2.tryCondAddNext(receive, queue2, condAddOp);
            if (tryCondAddNext == 1) {
                return true;
            }
        } while (tryCondAddNext != 2);
        return false;
    }

    protected abstract boolean isBufferAlwaysEmpty();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isBufferEmpty();

    public boolean isClosedForReceive() {
        if (getClosedForReceive() != null && isBufferEmpty()) {
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final ChannelIterator iterator() {
        return new Itr(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCancelIdempotent(boolean z) {
        Closed closedForSend = getClosedForSend();
        if (closedForSend != null) {
            Object obj = null;
            while (true) {
                LockFreeLinkedListNode prevNode = closedForSend.getPrevNode();
                if (prevNode instanceof LockFreeLinkedListHead) {
                    mo481onCancelIdempotentListww6eGU(obj, closedForSend);
                    return;
                } else if (!prevNode.remove$1()) {
                    prevNode.helpRemove();
                } else {
                    obj = InlineList.m487plusFjFbRPM(obj, (Send) prevNode);
                }
            }
        } else {
            throw new IllegalStateException("Cannot happen".toString());
        }
    }

    /* renamed from: onCancelIdempotentList-w-w6eGU  reason: not valid java name */
    protected void mo481onCancelIdempotentListww6eGU(Object obj, Closed closed) {
        if (obj != null) {
            if (!(obj instanceof ArrayList)) {
                ((Send) obj).resumeSendClosed(closed);
                return;
            }
            ArrayList arrayList = (ArrayList) obj;
            for (int size = arrayList.size() - 1; -1 < size; size--) {
                ((Send) arrayList.get(size)).resumeSendClosed(closed);
            }
        }
    }

    protected Object pollInternal() {
        while (true) {
            Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
            if (takeFirstSendOrPeekClosed == null) {
                return AbstractChannelKt.POLL_FAILED;
            }
            if (takeFirstSendOrPeekClosed.tryResumeSend() != null) {
                takeFirstSendOrPeekClosed.completeResumeSend();
                return takeFirstSendOrPeekClosed.getPollResult();
            }
            takeFirstSendOrPeekClosed.undeliveredElement();
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final Object receive(SuspendLambda suspendLambda) {
        Object pollInternal = pollInternal();
        if (pollInternal != AbstractChannelKt.POLL_FAILED && !(pollInternal instanceof Closed)) {
            return pollInternal;
        }
        return receiveSuspend(0, suspendLambda);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: receiveCatching-JP2dKIU  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object mo482receiveCatchingJP2dKIU(kotlin.coroutines.Continuation r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1 r0 = (kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1 r0 = new kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r5)
            goto L51
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            kotlin.ResultKt.throwOnFailure(r5)
            java.lang.Object r5 = r4.pollInternal()
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED
            if (r5 == r2) goto L48
            boolean r4 = r5 instanceof kotlinx.coroutines.channels.Closed
            if (r4 == 0) goto L47
            kotlinx.coroutines.channels.Closed r5 = (kotlinx.coroutines.channels.Closed) r5
            java.lang.Throwable r4 = r5.closeCause
            kotlinx.coroutines.channels.ChannelResult$Closed r5 = new kotlinx.coroutines.channels.ChannelResult$Closed
            r5.<init>(r4)
        L47:
            return r5
        L48:
            r0.label = r3
            java.lang.Object r5 = r4.receiveSuspend(r3, r0)
            if (r5 != r1) goto L51
            return r1
        L51:
            kotlinx.coroutines.channels.ChannelResult r5 = (kotlinx.coroutines.channels.ChannelResult) r5
            java.lang.Object r4 = r5.m486unboximpl()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractChannel.mo482receiveCatchingJP2dKIU(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final ReceiveOrClosed takeFirstReceiveOrPeekClosed() {
        ReceiveOrClosed takeFirstReceiveOrPeekClosed = super.takeFirstReceiveOrPeekClosed();
        if (takeFirstReceiveOrPeekClosed != null) {
            boolean z = takeFirstReceiveOrPeekClosed instanceof Closed;
        }
        return takeFirstReceiveOrPeekClosed;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: tryReceive-PtdJZtk  reason: not valid java name */
    public final Object mo483tryReceivePtdJZtk() {
        ChannelResult.Failed failed;
        Object pollInternal = pollInternal();
        if (pollInternal == AbstractChannelKt.POLL_FAILED) {
            failed = ChannelResult.failed;
            return failed;
        } else if (pollInternal instanceof Closed) {
            return new ChannelResult.Closed(((Closed) pollInternal).closeCause);
        } else {
            return pollInternal;
        }
    }
}
