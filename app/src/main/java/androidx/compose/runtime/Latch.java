package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Latch {
    private final Object lock = new Object();
    private List awaiters = new ArrayList();
    private List spareList = new ArrayList();
    private boolean _isOpen = true;

    public final Object await(Continuation continuation) {
        boolean z;
        synchronized (this.lock) {
            z = this._isOpen;
        }
        if (z) {
            return Unit.INSTANCE;
        }
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        synchronized (this.lock) {
            this.awaiters.add(cancellableContinuationImpl);
        }
        cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: androidx.compose.runtime.Latch$await$2$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Object obj2;
                List list;
                Throwable th = (Throwable) obj;
                obj2 = Latch.this.lock;
                Latch latch = Latch.this;
                CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
                synchronized (obj2) {
                    list = latch.awaiters;
                    list.remove(cancellableContinuation);
                }
                return Unit.INSTANCE;
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return result;
        }
        return Unit.INSTANCE;
    }

    public final void closeLatch() {
        synchronized (this.lock) {
            this._isOpen = false;
        }
    }

    public final void openLatch() {
        boolean z;
        synchronized (this.lock) {
            synchronized (this.lock) {
                z = this._isOpen;
            }
            if (z) {
                return;
            }
            List list = this.awaiters;
            this.awaiters = this.spareList;
            this.spareList = list;
            this._isOpen = true;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((Continuation) list.get(i)).resumeWith(Unit.INSTANCE);
            }
            list.clear();
        }
    }
}
