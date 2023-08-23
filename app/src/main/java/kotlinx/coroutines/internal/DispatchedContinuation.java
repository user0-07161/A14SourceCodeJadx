package kotlinx.coroutines.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoopImplPlatform;
import kotlinx.coroutines.ThreadLocalEventLoop;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DispatchedContinuation extends DispatchedTask implements CoroutineStackFrame, Continuation {
    private final AtomicRef _reusableCancellableContinuation;
    public Object _state;
    public final Continuation continuation;
    public final Object countOrElement;
    public final CoroutineDispatcher dispatcher;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DispatchedContinuation(CoroutineDispatcher coroutineDispatcher, Continuation continuation) {
        super(-1);
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        this.dispatcher = coroutineDispatcher;
        this.continuation = continuation;
        this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
        this.countOrElement = ThreadContextKt.threadContextElements(getContext());
        this._reusableCancellableContinuation = new AtomicRef(null);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final void cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object obj, Throwable th) {
        if (obj instanceof CompletedWithCancellation) {
            ((CompletedWithCancellation) obj).onCancellation.invoke(th);
        }
    }

    public final CancellableContinuationImpl claimReusableCancellableContinuation() {
        AtomicRef atomicRef = this._reusableCancellableContinuation;
        while (true) {
            Object value = atomicRef.getValue();
            if (value == null) {
                this._reusableCancellableContinuation.setValue(DispatchedContinuationKt.REUSABLE_CLAIMED);
                return null;
            } else if (value instanceof CancellableContinuationImpl) {
                if (this._reusableCancellableContinuation.compareAndSet(value, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                    return (CancellableContinuationImpl) value;
                }
            } else if (value != DispatchedContinuationKt.REUSABLE_CLAIMED && !(value instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + value).toString());
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.continuation;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    public final boolean isReusable() {
        if (this._reusableCancellableContinuation.getValue() != null) {
            return true;
        }
        return false;
    }

    public final boolean postponeCancellation(Throwable th) {
        AtomicRef atomicRef = this._reusableCancellableContinuation;
        while (true) {
            Object value = atomicRef.getValue();
            Symbol symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (Intrinsics.areEqual(value, symbol)) {
                if (this._reusableCancellableContinuation.compareAndSet(symbol, th)) {
                    return true;
                }
            } else if (value instanceof Throwable) {
                return true;
            } else {
                if (this._reusableCancellableContinuation.compareAndSet(value, null)) {
                    return false;
                }
            }
        }
    }

    public final void release() {
        CancellableContinuationImpl cancellableContinuationImpl;
        do {
        } while (this._reusableCancellableContinuation.getValue() == DispatchedContinuationKt.REUSABLE_CLAIMED);
        Object value = this._reusableCancellableContinuation.getValue();
        if (value instanceof CancellableContinuationImpl) {
            cancellableContinuationImpl = (CancellableContinuationImpl) value;
        } else {
            cancellableContinuationImpl = null;
        }
        if (cancellableContinuationImpl != null) {
            cancellableContinuationImpl.detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        }
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Object completedExceptionally;
        CoroutineContext context = this.continuation.getContext();
        Throwable m478exceptionOrNullimpl = Result.m478exceptionOrNullimpl(obj);
        if (m478exceptionOrNullimpl == null) {
            completedExceptionally = obj;
        } else {
            completedExceptionally = new CompletedExceptionally(m478exceptionOrNullimpl, false);
        }
        if (this.dispatcher.isDispatchNeeded(context)) {
            this._state = completedExceptionally;
            this.resumeMode = 0;
            this.dispatcher.dispatch(context, this);
            return;
        }
        EventLoopImplPlatform eventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines = ThreadLocalEventLoop.getEventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (eventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines.isUnconfinedLoopActive()) {
            this._state = completedExceptionally;
            this.resumeMode = 0;
            eventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines.dispatchUnconfined(this);
            return;
        }
        eventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines.incrementUseCount(true);
        try {
            CoroutineContext context2 = getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context2, this.countOrElement);
            this.continuation.resumeWith(obj);
            ThreadContextKt.restoreThreadContext(context2, updateThreadContext);
            do {
            } while (eventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines.processUnconfinedEvent());
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Object takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        Object obj = this._state;
        this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
        return obj;
    }

    public final String toString() {
        CoroutineDispatcher coroutineDispatcher = this.dispatcher;
        String debugString = DebugStringsKt.toDebugString(this.continuation);
        return "DispatchedContinuation[" + coroutineDispatcher + ", " + debugString + "]";
    }

    public final Throwable tryReleaseClaimedContinuation(CancellableContinuation continuation) {
        Symbol symbol;
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        AtomicRef atomicRef = this._reusableCancellableContinuation;
        do {
            Object value = atomicRef.getValue();
            symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (value != symbol) {
                if (value instanceof Throwable) {
                    if (this._reusableCancellableContinuation.compareAndSet(value, null)) {
                        return (Throwable) value;
                    }
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                throw new IllegalStateException(("Inconsistent state " + value).toString());
            }
        } while (!this._reusableCancellableContinuation.compareAndSet(symbol, continuation));
        return null;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation getDelegate$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this;
    }
}
