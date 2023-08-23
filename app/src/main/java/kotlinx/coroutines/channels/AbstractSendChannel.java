package kotlinx.coroutines.channels;

import java.util.ArrayList;
import kotlin.ExceptionsKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
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
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AbstractSendChannel implements SendChannel {
    protected final Function1 onUndeliveredElement;
    private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();
    private final AtomicRef onCloseHandler = AtomicFU.atomic((Object) null);

    public AbstractSendChannel(Function1 function1) {
        this.onUndeliveredElement = function1;
    }

    public static final void access$helpCloseAndResumeWithSendException(AbstractSendChannel abstractSendChannel, CancellableContinuationImpl cancellableContinuationImpl, Object obj, Closed closed) {
        UndeliveredElementException callUndeliveredElementCatchingException;
        abstractSendChannel.getClass();
        helpClose(closed);
        Throwable th = closed.closeCause;
        if (th == null) {
            th = new ClosedSendChannelException("Channel was closed");
        }
        Function1 function1 = abstractSendChannel.onUndeliveredElement;
        if (function1 != null && (callUndeliveredElementCatchingException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, null)) != null) {
            ExceptionsKt.addSuppressed(callUndeliveredElementCatchingException, th);
            cancellableContinuationImpl.resumeWith(ResultKt.createFailure(callUndeliveredElementCatchingException));
            return;
        }
        cancellableContinuationImpl.resumeWith(ResultKt.createFailure(th));
    }

    private static void helpClose(Closed closed) {
        Receive receive;
        Object obj = null;
        while (true) {
            LockFreeLinkedListNode prevNode = closed.getPrevNode();
            if (prevNode instanceof Receive) {
                receive = (Receive) prevNode;
            } else {
                receive = null;
            }
            if (receive == null) {
                break;
            } else if (!receive.remove$1()) {
                receive.helpRemove();
            } else {
                obj = InlineList.m487plusFjFbRPM(obj, receive);
            }
        }
        if (obj != null) {
            if (!(obj instanceof ArrayList)) {
                ((Receive) obj).resumeReceiveClosed(closed);
                return;
            }
            ArrayList arrayList = (ArrayList) obj;
            for (int size = arrayList.size() - 1; -1 < size; size--) {
                ((Receive) arrayList.get(size)).resumeReceiveClosed(closed);
            }
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean close(Throwable th) {
        boolean z;
        AtomicRef atomicRef;
        Object value;
        Symbol symbol;
        Closed closed = new Closed(th);
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            LockFreeLinkedListNode prevNode = lockFreeLinkedListHead.getPrevNode();
            if (!(!(prevNode instanceof Closed))) {
                z = false;
                break;
            } else if (prevNode.addNext(closed, lockFreeLinkedListHead)) {
                z = true;
                break;
            }
        }
        if (!z) {
            closed = (Closed) lockFreeLinkedListHead.getPrevNode();
        }
        helpClose(closed);
        if (z && (value = (atomicRef = this.onCloseHandler).getValue()) != null && value != (symbol = AbstractChannelKt.HANDLER_INVOKED) && atomicRef.compareAndSet(value, symbol)) {
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(1, value);
            ((Function1) value).invoke(th);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object enqueueSend(final SendElement sendElement) {
        boolean z;
        LockFreeLinkedListNode prevNode;
        boolean isBufferAlwaysFull = isBufferAlwaysFull();
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        if (isBufferAlwaysFull) {
            do {
                prevNode = lockFreeLinkedListHead.getPrevNode();
                if (prevNode instanceof ReceiveOrClosed) {
                    return prevNode;
                }
            } while (!prevNode.addNext(sendElement, lockFreeLinkedListHead));
            return null;
        }
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(sendElement) { // from class: kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            public final Symbol prepare(Object obj) {
                LockFreeLinkedListNode affected = (LockFreeLinkedListNode) obj;
                Intrinsics.checkNotNullParameter(affected, "affected");
                if (this.isBufferFull()) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        while (true) {
            LockFreeLinkedListNode prevNode2 = lockFreeLinkedListHead.getPrevNode();
            if (prevNode2 instanceof ReceiveOrClosed) {
                return prevNode2;
            }
            int tryCondAddNext = prevNode2.tryCondAddNext(sendElement, lockFreeLinkedListHead, condAddOp);
            z = true;
            if (tryCondAddNext != 1) {
                if (tryCondAddNext == 2) {
                    z = false;
                    break;
                }
            } else {
                break;
            }
        }
        if (!z) {
            return AbstractChannelKt.ENQUEUE_FAILED;
        }
        return null;
    }

    protected String getBufferDebugString() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Closed getClosedForReceive() {
        Closed closed;
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode instanceof Closed) {
            closed = (Closed) nextNode;
        } else {
            closed = null;
        }
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Closed getClosedForSend() {
        Closed closed;
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (prevNode instanceof Closed) {
            closed = (Closed) prevNode;
        } else {
            closed = null;
        }
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final void invokeOnClose(Function1 function1) {
        AtomicRef atomicRef = this.onCloseHandler;
        if (!atomicRef.compareAndSet(null, function1)) {
            Object value = atomicRef.getValue();
            if (value == AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException("Another handler was already registered and successfully invoked");
            }
            throw new IllegalStateException("Another handler was already registered: " + value);
        }
        Closed closedForSend = getClosedForSend();
        if (closedForSend != null && atomicRef.compareAndSet(function1, AbstractChannelKt.HANDLER_INVOKED)) {
            ((ProduceKt$awaitClose$4$1) function1).invoke(closedForSend.closeCause);
        }
    }

    protected abstract boolean isBufferAlwaysFull();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isBufferFull();

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        if (getClosedForSend() != null) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object offerInternal(Object obj) {
        ReceiveOrClosed takeFirstReceiveOrPeekClosed;
        do {
            takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (takeFirstReceiveOrPeekClosed == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
        } while (takeFirstReceiveOrPeekClosed.tryResumeReceive(obj) == null);
        takeFirstReceiveOrPeekClosed.completeResumeReceive(obj);
        return takeFirstReceiveOrPeekClosed.getOfferResult();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final Object send(Object obj, Continuation continuation) {
        boolean z;
        SendElement sendElementWithUndeliveredHandler;
        Object offerInternal = offerInternal(obj);
        Symbol symbol = AbstractChannelKt.OFFER_SUCCESS;
        Unit unit = Unit.INSTANCE;
        if (offerInternal == symbol) {
            return unit;
        }
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        while (true) {
            if (!(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull()) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                Function1 function1 = this.onUndeliveredElement;
                if (function1 == null) {
                    sendElementWithUndeliveredHandler = new SendElement(obj, orCreateCancellableContinuation);
                } else {
                    sendElementWithUndeliveredHandler = new SendElementWithUndeliveredHandler(obj, orCreateCancellableContinuation, function1);
                }
                Object enqueueSend = enqueueSend(sendElementWithUndeliveredHandler);
                if (enqueueSend == null) {
                    CancellableContinuationKt.removeOnCancellation(orCreateCancellableContinuation, sendElementWithUndeliveredHandler);
                    break;
                } else if (enqueueSend instanceof Closed) {
                    access$helpCloseAndResumeWithSendException(this, orCreateCancellableContinuation, obj, (Closed) enqueueSend);
                    break;
                } else if (enqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueSend instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + enqueueSend).toString());
                }
            }
            Object offerInternal2 = offerInternal(obj);
            if (offerInternal2 == AbstractChannelKt.OFFER_SUCCESS) {
                orCreateCancellableContinuation.resumeWith(unit);
                break;
            } else if (offerInternal2 != AbstractChannelKt.OFFER_FAILED) {
                if (offerInternal2 instanceof Closed) {
                    access$helpCloseAndResumeWithSendException(this, orCreateCancellableContinuation, obj, (Closed) offerInternal2);
                } else {
                    throw new IllegalStateException(("offerInternal returned " + offerInternal2).toString());
                }
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (result != coroutineSingletons) {
            result = unit;
        }
        if (result == coroutineSingletons) {
            return result;
        }
        return unit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ReceiveOrClosed sendBuffered(Object obj) {
        LockFreeLinkedListNode prevNode;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        SendBuffered sendBuffered = new SendBuffered(obj);
        do {
            prevNode = lockFreeLinkedListHead.getPrevNode();
            if (prevNode instanceof ReceiveOrClosed) {
                return (ReceiveOrClosed) prevNode;
            }
        } while (!prevNode.addNext(sendBuffered, lockFreeLinkedListHead));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReceiveOrClosed takeFirstReceiveOrPeekClosed() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListNode removeOrNext;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            Object next = lockFreeLinkedListHead.getNext();
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
            if (lockFreeLinkedListNode != lockFreeLinkedListHead && (lockFreeLinkedListNode instanceof ReceiveOrClosed)) {
                if (((((ReceiveOrClosed) lockFreeLinkedListNode) instanceof Closed) && !lockFreeLinkedListNode.isRemoved()) || (removeOrNext = lockFreeLinkedListNode.removeOrNext()) == null) {
                    break;
                }
                removeOrNext.helpRemovePrev();
            }
        }
        lockFreeLinkedListNode = null;
        return (ReceiveOrClosed) lockFreeLinkedListNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Send takeFirstSendOrPeekClosed() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListNode removeOrNext;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            Object next = lockFreeLinkedListHead.getNext();
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
            if (lockFreeLinkedListNode != lockFreeLinkedListHead && (lockFreeLinkedListNode instanceof Send)) {
                if (((((Send) lockFreeLinkedListNode) instanceof Closed) && !lockFreeLinkedListNode.isRemoved()) || (removeOrNext = lockFreeLinkedListNode.removeOrNext()) == null) {
                    break;
                }
                removeOrNext.helpRemovePrev();
            }
        }
        lockFreeLinkedListNode = null;
        return (Send) lockFreeLinkedListNode;
    }

    public final String toString() {
        String str;
        String str2;
        String classSimpleName = DebugStringsKt.getClassSimpleName(this);
        String hexAddress = DebugStringsKt.getHexAddress(this);
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        LockFreeLinkedListNode nextNode = lockFreeLinkedListNode.getNextNode();
        if (nextNode == lockFreeLinkedListNode) {
            str2 = "EmptyQueue";
        } else {
            if (nextNode instanceof Closed) {
                str = nextNode.toString();
            } else if (nextNode instanceof Receive) {
                str = "ReceiveQueued";
            } else if (nextNode instanceof Send) {
                str = "SendQueued";
            } else {
                str = "UNEXPECTED:" + nextNode;
            }
            LockFreeLinkedListNode prevNode = lockFreeLinkedListNode.getPrevNode();
            if (prevNode != nextNode) {
                Object next = lockFreeLinkedListNode.getNext();
                Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                int i = 0;
                for (LockFreeLinkedListNode lockFreeLinkedListNode2 = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(lockFreeLinkedListNode2, lockFreeLinkedListNode); lockFreeLinkedListNode2 = lockFreeLinkedListNode2.getNextNode()) {
                    i++;
                }
                str2 = str + ",queueSize=" + i;
                if (prevNode instanceof Closed) {
                    str2 = str2 + ",closedForSend=" + prevNode;
                }
            } else {
                str2 = str;
            }
        }
        return classSimpleName + "@" + hexAddress + "{" + str2 + "}" + getBufferDebugString();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU  reason: not valid java name */
    public final Object mo484trySendJP2dKIU(Object obj) {
        ChannelResult.Closed closed;
        ChannelResult.Failed failed;
        Object offerInternal = offerInternal(obj);
        if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
            return Unit.INSTANCE;
        }
        if (offerInternal == AbstractChannelKt.OFFER_FAILED) {
            Closed closedForSend = getClosedForSend();
            if (closedForSend == null) {
                failed = ChannelResult.failed;
                return failed;
            }
            helpClose(closedForSend);
            Throwable th = closedForSend.closeCause;
            if (th == null) {
                th = new ClosedSendChannelException("Channel was closed");
            }
            closed = new ChannelResult.Closed(th);
        } else if (offerInternal instanceof Closed) {
            Closed closed2 = (Closed) offerInternal;
            helpClose(closed2);
            Throwable th2 = closed2.closeCause;
            if (th2 == null) {
                th2 = new ClosedSendChannelException("Channel was closed");
            }
            closed = new ChannelResult.Closed(th2);
        } else {
            throw new IllegalStateException(("trySend returned " + offerInternal).toString());
        }
        return closed;
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class SendBuffered extends Send {
        public final Object element;

        public SendBuffered(Object obj) {
            this.element = obj;
        }

        @Override // kotlinx.coroutines.channels.Send
        public final Object getPollResult() {
            return this.element;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public final String toString() {
            String hexAddress = DebugStringsKt.getHexAddress(this);
            return "SendBuffered@" + hexAddress + "(" + this.element + ")";
        }

        @Override // kotlinx.coroutines.channels.Send
        public final Symbol tryResumeSend() {
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }

        @Override // kotlinx.coroutines.channels.Send
        public final void completeResumeSend() {
        }

        @Override // kotlinx.coroutines.channels.Send
        public final void resumeSendClosed(Closed closed) {
        }
    }
}
