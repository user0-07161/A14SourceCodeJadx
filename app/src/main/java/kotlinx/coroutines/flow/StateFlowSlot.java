package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.internal.Symbol;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class StateFlowSlot extends AbstractSharedFlowSlot {
    private final AtomicRef _state = AtomicFU.atomic((Object) null);

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public final boolean allocateLocked(AbstractSharedFlow abstractSharedFlow) {
        Symbol symbol;
        StateFlowImpl flow = (StateFlowImpl) abstractSharedFlow;
        Intrinsics.checkNotNullParameter(flow, "flow");
        AtomicRef atomicRef = this._state;
        if (atomicRef.getValue() == null) {
            symbol = StateFlowKt.NONE;
            atomicRef.setValue(symbol);
            return true;
        }
        return false;
    }

    public final Object awaitPending(Continuation continuation) {
        Symbol symbol;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        symbol = StateFlowKt.NONE;
        boolean compareAndSet = this._state.compareAndSet(symbol, cancellableContinuationImpl);
        Unit unit = Unit.INSTANCE;
        if (!compareAndSet) {
            cancellableContinuationImpl.resumeWith(unit);
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return result;
        }
        return unit;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public final Continuation[] freeLocked(AbstractSharedFlow abstractSharedFlow) {
        StateFlowImpl flow = (StateFlowImpl) abstractSharedFlow;
        Intrinsics.checkNotNullParameter(flow, "flow");
        this._state.setValue(null);
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }

    public final void makePending() {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        while (true) {
            AtomicRef atomicRef = this._state;
            Object value = atomicRef.getValue();
            if (value != null) {
                symbol = StateFlowKt.PENDING;
                if (value != symbol) {
                    symbol2 = StateFlowKt.NONE;
                    if (value == symbol2) {
                        symbol3 = StateFlowKt.PENDING;
                        if (atomicRef.compareAndSet(value, symbol3)) {
                            return;
                        }
                    } else {
                        symbol4 = StateFlowKt.NONE;
                        if (atomicRef.compareAndSet(value, symbol4)) {
                            ((CancellableContinuationImpl) value).resumeWith(Unit.INSTANCE);
                            return;
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public final boolean takePending() {
        Symbol symbol;
        Symbol symbol2;
        AtomicRef atomicRef = this._state;
        symbol = StateFlowKt.NONE;
        Object andSet = atomicRef.getAndSet(symbol);
        Intrinsics.checkNotNull(andSet);
        symbol2 = StateFlowKt.PENDING;
        if (andSet == symbol2) {
            return true;
        }
        return false;
    }
}
