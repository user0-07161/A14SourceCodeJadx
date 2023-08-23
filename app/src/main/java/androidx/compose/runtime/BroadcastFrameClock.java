package androidx.compose.runtime;

import androidx.compose.runtime.BroadcastFrameClock;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class BroadcastFrameClock implements MonotonicFrameClock {
    private Throwable failureCause;
    private final Function0 onNewAwaiters;
    private final Object lock = new Object();
    private List awaiters = new ArrayList();
    private List spareList = new ArrayList();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class FrameAwaiter {
        private final Continuation continuation;
        private final Function1 onFrame;

        public FrameAwaiter(Function1 onFrame, CancellableContinuationImpl cancellableContinuationImpl) {
            Intrinsics.checkNotNullParameter(onFrame, "onFrame");
            this.onFrame = onFrame;
            this.continuation = cancellableContinuationImpl;
        }

        public final Continuation getContinuation() {
            return this.continuation;
        }

        public final void resume(long j) {
            Object createFailure;
            try {
                createFailure = this.onFrame.invoke(Long.valueOf(j));
            } catch (Throwable th) {
                createFailure = ResultKt.createFailure(th);
            }
            this.continuation.resumeWith(createFailure);
        }
    }

    public BroadcastFrameClock(Function0 function0) {
        this.onNewAwaiters = function0;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        return operation.invoke(obj, this);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return CoroutineContext.DefaultImpls.get(this, key);
    }

    public final boolean getHasAwaiters() {
        boolean z;
        synchronized (this.lock) {
            z = !this.awaiters.isEmpty();
        }
        return z;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return CoroutineContext.DefaultImpls.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CoroutineContext.DefaultImpls.plus(this, context);
    }

    public final void sendFrame(long j) {
        synchronized (this.lock) {
            List list = this.awaiters;
            this.awaiters = this.spareList;
            this.spareList = list;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((FrameAwaiter) list.get(i)).resume(j);
            }
            list.clear();
        }
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock
    public final Object withFrameNanos(Function1 function1, Continuation continuation) {
        boolean z;
        Function0 function0;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        synchronized (this.lock) {
            Throwable th = this.failureCause;
            if (th != null) {
                cancellableContinuationImpl.resumeWith(ResultKt.createFailure(th));
            } else {
                ref$ObjectRef.element = new FrameAwaiter(function1, cancellableContinuationImpl);
                if (!this.awaiters.isEmpty()) {
                    z = true;
                } else {
                    z = false;
                }
                List list = this.awaiters;
                Object obj = ref$ObjectRef.element;
                if (obj != null) {
                    list.add((FrameAwaiter) obj);
                    boolean z2 = !z;
                    cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: androidx.compose.runtime.BroadcastFrameClock$withFrameNanos$2$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            Object obj3;
                            List list2;
                            Throwable th2 = (Throwable) obj2;
                            obj3 = BroadcastFrameClock.this.lock;
                            BroadcastFrameClock broadcastFrameClock = BroadcastFrameClock.this;
                            Ref$ObjectRef ref$ObjectRef2 = ref$ObjectRef;
                            synchronized (obj3) {
                                list2 = broadcastFrameClock.awaiters;
                                Object obj4 = ref$ObjectRef2.element;
                                if (obj4 != null) {
                                    list2.remove((BroadcastFrameClock.FrameAwaiter) obj4);
                                } else {
                                    Intrinsics.throwUninitializedPropertyAccessException("awaiter");
                                    throw null;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    });
                    if (z2 && (function0 = this.onNewAwaiters) != null) {
                        try {
                            function0.invoke();
                        } catch (Throwable th2) {
                            synchronized (this.lock) {
                                if (this.failureCause == null) {
                                    this.failureCause = th2;
                                    List list2 = this.awaiters;
                                    int size = list2.size();
                                    for (int i = 0; i < size; i++) {
                                        ((FrameAwaiter) list2.get(i)).getContinuation().resumeWith(ResultKt.createFailure(th2));
                                    }
                                    this.awaiters.clear();
                                }
                            }
                        }
                    }
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("awaiter");
                    throw null;
                }
            }
        }
        return cancellableContinuationImpl.getResult();
    }
}
