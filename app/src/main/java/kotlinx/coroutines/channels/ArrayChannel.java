package kotlinx.coroutines.channels;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.UndeliveredElementException;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ArrayChannel extends AbstractChannel {
    private Object[] buffer;
    private final int capacity;
    private int head;
    private final ReentrantLock lock;
    private final BufferOverflow onBufferOverflow;
    private final AtomicInt size;

    public ArrayChannel(int i, BufferOverflow bufferOverflow, Function1 function1) {
        super(function1);
        this.capacity = i;
        this.onBufferOverflow = bufferOverflow;
        if (i >= 1) {
            this.lock = new ReentrantLock();
            Object[] objArr = new Object[Math.min(i, 8)];
            ArraysKt.fill$default(objArr, AbstractChannelKt.EMPTY);
            this.buffer = objArr;
            this.size = new AtomicInt();
            return;
        }
        throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + i + " was specified").toString());
    }

    private final void enqueueElement(int i, Object obj) {
        int i2 = this.capacity;
        if (i < i2) {
            Object[] objArr = this.buffer;
            if (i >= objArr.length) {
                int min = Math.min(objArr.length * 2, i2);
                Object[] objArr2 = new Object[min];
                for (int i3 = 0; i3 < i; i3++) {
                    Object[] objArr3 = this.buffer;
                    objArr2[i3] = objArr3[(this.head + i3) % objArr3.length];
                }
                Arrays.fill(objArr2, i, min, AbstractChannelKt.EMPTY);
                this.buffer = objArr2;
                this.head = 0;
            }
            Object[] objArr4 = this.buffer;
            objArr4[(this.head + i) % objArr4.length] = obj;
            return;
        }
        Object[] objArr5 = this.buffer;
        int i4 = this.head;
        objArr5[i4 % objArr5.length] = null;
        objArr5[(i + i4) % objArr5.length] = obj;
        this.head = (i4 + 1) % objArr5.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean enqueueReceiveInternal(Receive receive) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final Object enqueueSend(SendElement sendElement) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueSend(sendElement);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final String getBufferDebugString() {
        int value = this.size.getValue();
        return "(buffer:capacity=" + this.capacity + ",size=" + value + ")";
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        if (this.size.getValue() == 0) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        if (this.size.getValue() == this.capacity) {
            if (this.onBufferOverflow == BufferOverflow.SUSPEND) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isClosedForReceive() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003c A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0040  */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object offerInternal(java.lang.Object r6) {
        /*
            r5 = this;
            kotlinx.atomicfu.AtomicInt r0 = r5.size
            java.util.concurrent.locks.ReentrantLock r1 = r5.lock
            r1.lock()
            int r2 = r0.getValue()     // Catch: java.lang.Throwable -> L71
            kotlinx.coroutines.channels.Closed r3 = r5.getClosedForSend()     // Catch: java.lang.Throwable -> L71
            if (r3 == 0) goto L15
            r1.unlock()
            return r3
        L15:
            int r3 = r5.capacity     // Catch: java.lang.Throwable -> L71
            if (r2 >= r3) goto L1f
            int r3 = r2 + 1
            r0.setValue(r3)     // Catch: java.lang.Throwable -> L71
            goto L36
        L1f:
            kotlinx.coroutines.channels.BufferOverflow r3 = r5.onBufferOverflow     // Catch: java.lang.Throwable -> L71
            int r3 = r3.ordinal()     // Catch: java.lang.Throwable -> L71
            if (r3 == 0) goto L38
            r4 = 1
            if (r3 == r4) goto L36
            r4 = 2
            if (r3 != r4) goto L30
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS     // Catch: java.lang.Throwable -> L71
            goto L3a
        L30:
            kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException     // Catch: java.lang.Throwable -> L71
            r5.<init>()     // Catch: java.lang.Throwable -> L71
            throw r5     // Catch: java.lang.Throwable -> L71
        L36:
            r3 = 0
            goto L3a
        L38:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED     // Catch: java.lang.Throwable -> L71
        L3a:
            if (r3 == 0) goto L40
            r1.unlock()
            return r3
        L40:
            if (r2 != 0) goto L68
        L42:
            kotlinx.coroutines.channels.ReceiveOrClosed r3 = r5.takeFirstReceiveOrPeekClosed()     // Catch: java.lang.Throwable -> L71
            if (r3 != 0) goto L49
            goto L68
        L49:
            boolean r4 = r3 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L71
            if (r4 == 0) goto L54
            r0.setValue(r2)     // Catch: java.lang.Throwable -> L71
            r1.unlock()
            return r3
        L54:
            kotlinx.coroutines.internal.Symbol r4 = r3.tryResumeReceive(r6)     // Catch: java.lang.Throwable -> L71
            if (r4 == 0) goto L42
            r0.setValue(r2)     // Catch: java.lang.Throwable -> L71
            r1.unlock()
            r3.completeResumeReceive(r6)
            java.lang.Object r5 = r3.getOfferResult()
            return r5
        L68:
            r5.enqueueElement(r2, r6)     // Catch: java.lang.Throwable -> L71
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS     // Catch: java.lang.Throwable -> L71
            r1.unlock()
            return r5
        L71:
            r5 = move-exception
            r1.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.offerInternal(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final void onCancelIdempotent(boolean z) {
        AtomicInt atomicInt = this.size;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int value = atomicInt.getValue();
            UndeliveredElementException undeliveredElementException = null;
            for (int i = 0; i < value; i++) {
                Object obj = this.buffer[this.head];
                Function1 function1 = this.onUndeliveredElement;
                if (function1 != null && obj != AbstractChannelKt.EMPTY) {
                    undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, undeliveredElementException);
                }
                Object[] objArr = this.buffer;
                int i2 = this.head;
                objArr[i2] = AbstractChannelKt.EMPTY;
                this.head = (i2 + 1) % objArr.length;
            }
            atomicInt.setValue(0);
            reentrantLock.unlock();
            super.onCancelIdempotent(z);
            if (undeliveredElementException == null) {
                return;
            }
            throw undeliveredElementException;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final Object pollInternal() {
        AtomicInt atomicInt = this.size;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int value = atomicInt.getValue();
            if (value == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object[] objArr = this.buffer;
            int i = this.head;
            Object obj = objArr[i];
            Send send = null;
            objArr[i] = null;
            atomicInt.setValue(value - 1);
            Object obj2 = AbstractChannelKt.POLL_FAILED;
            boolean z = false;
            if (value == this.capacity) {
                while (true) {
                    Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (takeFirstSendOrPeekClosed == null) {
                        break;
                    } else if (takeFirstSendOrPeekClosed.tryResumeSend() != null) {
                        obj2 = takeFirstSendOrPeekClosed.getPollResult();
                        send = takeFirstSendOrPeekClosed;
                        z = true;
                        break;
                    } else {
                        takeFirstSendOrPeekClosed.undeliveredElement();
                        send = takeFirstSendOrPeekClosed;
                    }
                }
            }
            if (obj2 != AbstractChannelKt.POLL_FAILED && !(obj2 instanceof Closed)) {
                atomicInt.setValue(value);
                Object[] objArr2 = this.buffer;
                objArr2[(this.head + value) % objArr2.length] = obj2;
            }
            this.head = (this.head + 1) % this.buffer.length;
            if (z) {
                Intrinsics.checkNotNull(send);
                send.completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }
}
