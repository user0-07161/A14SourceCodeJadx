package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ScopeCoroutine;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DispatchedCoroutine extends ScopeCoroutine {
    private final AtomicInt _decision;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DispatchedCoroutine(Continuation uCont, CoroutineContext coroutineContext) {
        super(uCont, coroutineContext);
        Intrinsics.checkNotNullParameter(uCont, "uCont");
        this._decision = new AtomicInt();
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    protected final void afterCompletion(Object obj) {
        afterResume(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    public final void afterResume(Object obj) {
        boolean z;
        while (true) {
            AtomicInt atomicInt = this._decision;
            int value = atomicInt.getValue();
            z = false;
            if (value != 0) {
                if (value != 1) {
                    throw new IllegalStateException("Already resumed".toString());
                }
            } else if (atomicInt.compareAndSet(0, 2)) {
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        Continuation continuation = this.uCont;
        DispatchedContinuationKt.resumeCancellableWith(IntrinsicsKt.intercepted(continuation), CompletionStateKt.recoverResult(obj, continuation), null);
    }

    public final Object getResult() {
        boolean z;
        while (true) {
            AtomicInt atomicInt = this._decision;
            int value = atomicInt.getValue();
            z = false;
            if (value != 0) {
                if (value != 2) {
                    throw new IllegalStateException("Already suspended".toString());
                }
            } else if (atomicInt.compareAndSet(0, 1)) {
                z = true;
                break;
            }
        }
        if (z) {
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        Object unboxState = JobSupportKt.unboxState(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines());
        if (!(unboxState instanceof CompletedExceptionally)) {
            return unboxState;
        }
        throw ((CompletedExceptionally) unboxState).cause;
    }
}
