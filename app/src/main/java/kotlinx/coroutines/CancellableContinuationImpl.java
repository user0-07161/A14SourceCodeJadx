package kotlinx.coroutines;

import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class CancellableContinuationImpl extends DispatchedTask implements CancellableContinuation, CoroutineStackFrame {
    private final AtomicInt _decision;
    private final AtomicRef _state;
    private final CoroutineContext context;
    private final Continuation delegate;
    private DisposableHandle parentHandle;

    public CancellableContinuationImpl(int i, Continuation continuation) {
        super(i);
        this.delegate = continuation;
        this.context = continuation.getContext();
        this._decision = new AtomicInt();
        this._state = new AtomicRef(Active.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0024, code lost:
        if (r0 == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0026, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
        r0 = r6.delegate;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002a, code lost:
        if (r7 != 4) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002e, code lost:
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002f, code lost:
        if (r1 != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0033, code lost:
        if ((r0 instanceof kotlinx.coroutines.internal.DispatchedContinuation) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0035, code lost:
        if (r7 == 1) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0037, code lost:
        if (r7 != 2) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003a, code lost:
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x003c, code lost:
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003d, code lost:
        r5 = r6.resumeMode;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003f, code lost:
        if (r5 == 1) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0041, code lost:
        if (r5 != 2) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0043, code lost:
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0044, code lost:
        if (r7 != r3) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0046, code lost:
        r7 = ((kotlinx.coroutines.internal.DispatchedContinuation) r0).dispatcher;
        r0 = r0.getContext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0053, code lost:
        if (r7.isDispatchNeeded(r0) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0055, code lost:
        r7.dispatch(r0, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
        r7 = kotlinx.coroutines.ThreadLocalEventLoop.getEventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0061, code lost:
        if (r7.isUnconfinedLoopActive() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0063, code lost:
        r7.dispatchUnconfined(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0067, code lost:
        r7.incrementUseCount(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006a, code lost:
        kotlinx.coroutines.DispatchedTaskKt.resume(r6, r6.delegate, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0073, code lost:
        if (r7.processUnconfinedEvent() != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0076, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0078, code lost:
        handleFatalException(r0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x007f, code lost:
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0080, code lost:
        r7.decrementUseCount(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0083, code lost:
        throw r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0084, code lost:
        kotlinx.coroutines.DispatchedTaskKt.resume(r6, r0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0087, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:?, code lost:
        return;
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void dispatchResume(int r7) {
        /*
            r6 = this;
            kotlinx.atomicfu.AtomicInt r0 = r6._decision
        L2:
            int r1 = r0.getValue()
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L1b
            if (r1 != r4) goto Lf
            r0 = r3
            goto L24
        Lf:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Already resumed"
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L1b:
            kotlinx.atomicfu.AtomicInt r1 = r6._decision
            boolean r1 = r1.compareAndSet(r3, r2)
            if (r1 == 0) goto L2
            r0 = r4
        L24:
            if (r0 == 0) goto L27
            return
        L27:
            kotlin.coroutines.Continuation r0 = r6.delegate
            r1 = 4
            if (r7 != r1) goto L2e
            r1 = r4
            goto L2f
        L2e:
            r1 = r3
        L2f:
            if (r1 != 0) goto L84
            boolean r5 = r0 instanceof kotlinx.coroutines.internal.DispatchedContinuation
            if (r5 == 0) goto L84
            if (r7 == r4) goto L3c
            if (r7 != r2) goto L3a
            goto L3c
        L3a:
            r7 = r3
            goto L3d
        L3c:
            r7 = r4
        L3d:
            int r5 = r6.resumeMode
            if (r5 == r4) goto L43
            if (r5 != r2) goto L44
        L43:
            r3 = r4
        L44:
            if (r7 != r3) goto L84
            r7 = r0
            kotlinx.coroutines.internal.DispatchedContinuation r7 = (kotlinx.coroutines.internal.DispatchedContinuation) r7
            kotlinx.coroutines.CoroutineDispatcher r7 = r7.dispatcher
            kotlin.coroutines.CoroutineContext r0 = r0.getContext()
            boolean r1 = r7.isDispatchNeeded(r0)
            if (r1 == 0) goto L59
            r7.dispatch(r0, r6)
            goto L87
        L59:
            kotlinx.coroutines.EventLoopImplPlatform r7 = kotlinx.coroutines.ThreadLocalEventLoop.getEventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines()
            boolean r0 = r7.isUnconfinedLoopActive()
            if (r0 == 0) goto L67
            r7.dispatchUnconfined(r6)
            goto L87
        L67:
            r7.incrementUseCount(r4)
            kotlin.coroutines.Continuation r0 = r6.delegate     // Catch: java.lang.Throwable -> L76
            kotlinx.coroutines.DispatchedTaskKt.resume(r6, r0, r4)     // Catch: java.lang.Throwable -> L76
        L6f:
            boolean r0 = r7.processUnconfinedEvent()     // Catch: java.lang.Throwable -> L76
            if (r0 != 0) goto L6f
            goto L7b
        L76:
            r0 = move-exception
            r1 = 0
            r6.handleFatalException(r0, r1)     // Catch: java.lang.Throwable -> L7f
        L7b:
            r7.decrementUseCount(r4)
            goto L87
        L7f:
            r6 = move-exception
            r7.decrementUseCount(r4)
            throw r6
        L84:
            kotlinx.coroutines.DispatchedTaskKt.resume(r6, r0, r1)
        L87:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CancellableContinuationImpl.dispatchResume(int):void");
    }

    private final DisposableHandle installParentHandle() {
        CoroutineContext coroutineContext = this.context;
        Job.Key key = Job.Key;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job == null) {
            return null;
        }
        DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, new ChildContinuation(this), 2);
        this.parentHandle = invokeOnCompletion$default;
        return invokeOnCompletion$default;
    }

    private final boolean isReusable() {
        boolean z;
        if (this.resumeMode == 2) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            Continuation continuation = this.delegate;
            Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (((DispatchedContinuation) continuation).isReusable()) {
                return true;
            }
        }
        return false;
    }

    private static void multipleHandlersError(Object obj, Function1 function1) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + function1 + ", already has " + obj).toString());
    }

    private final void resumeImpl(Object obj, int i, Function1 function1) {
        Object value;
        AtomicRef atomicRef = this._state;
        do {
            value = atomicRef.getValue();
            if (value instanceof NotCompleted) {
            } else {
                if (value instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) value;
                    if (cancelledContinuation.makeResumed()) {
                        if (function1 != null) {
                            callOnCancellation(function1, cancelledContinuation.cause);
                            return;
                        }
                        return;
                    }
                }
                throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
            }
        } while (!this._state.compareAndSet(value, resumedState((NotCompleted) value, obj, i, function1, null)));
        if (!isReusable()) {
            detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        }
        dispatchResume(i);
    }

    private static Object resumedState(NotCompleted notCompleted, Object obj, int i, Function1 function1, Object obj2) {
        CancelHandler cancelHandler;
        if (!(obj instanceof CompletedExceptionally)) {
            boolean z = true;
            if (i != 1 && i != 2) {
                z = false;
            }
            if (z || obj2 != null) {
                if (function1 != null || (((notCompleted instanceof CancelHandler) && !(notCompleted instanceof BeforeResumeCancelHandler)) || obj2 != null)) {
                    if (notCompleted instanceof CancelHandler) {
                        cancelHandler = (CancelHandler) notCompleted;
                    } else {
                        cancelHandler = null;
                    }
                    return new CompletedContinuation(obj, cancelHandler, function1, obj2, null, 16);
                }
                return obj;
            }
            return obj;
        }
        return obj;
    }

    private final Symbol tryResumeImpl(Object obj, Object obj2, Function1 function1) {
        Object value;
        AtomicRef atomicRef = this._state;
        do {
            value = atomicRef.getValue();
            if (value instanceof NotCompleted) {
            } else if ((value instanceof CompletedContinuation) && obj2 != null && ((CompletedContinuation) value).idempotentResume == obj2) {
                return CancellableContinuationImplKt.RESUME_TOKEN;
            } else {
                return null;
            }
        } while (!this._state.compareAndSet(value, resumedState((NotCompleted) value, obj, this.resumeMode, function1, obj2)));
        if (!isReusable()) {
            detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        }
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    public final void callCancelHandler(CancelHandler cancelHandler, Throwable th) {
        try {
            cancelHandler.invoke(th);
        } catch (Throwable th2) {
            CoroutineContext coroutineContext = this.context;
            CoroutineExceptionHandlerKt.handleCoroutineException(coroutineContext, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void callOnCancellation(Function1 onCancellation, Throwable cause) {
        Intrinsics.checkNotNullParameter(onCancellation, "onCancellation");
        Intrinsics.checkNotNullParameter(cause, "cause");
        try {
            onCancellation.invoke(cause);
        } catch (Throwable th) {
            CoroutineContext coroutineContext = this.context;
            CoroutineExceptionHandlerKt.handleCoroutineException(coroutineContext, new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th));
        }
    }

    public final boolean cancel(Throwable th) {
        Object value;
        boolean z;
        CancelHandler cancelHandler;
        AtomicRef atomicRef = this._state;
        do {
            value = atomicRef.getValue();
            if (!(value instanceof NotCompleted)) {
                return false;
            }
            z = value instanceof CancelHandler;
        } while (!this._state.compareAndSet(value, new CancelledContinuation(this, th, z)));
        if (z) {
            cancelHandler = (CancelHandler) value;
        } else {
            cancelHandler = null;
        }
        if (cancelHandler != null) {
            callCancelHandler(cancelHandler, th);
        }
        if (!isReusable()) {
            detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        }
        dispatchResume(this.resumeMode);
        return true;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final void cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object obj, Throwable th) {
        boolean z;
        AtomicRef atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            if (!(value instanceof NotCompleted)) {
                if (value instanceof CompletedExceptionally) {
                    return;
                }
                if (value instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) value;
                    if (completedContinuation.cancelCause != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        if (this._state.compareAndSet(value, CompletedContinuation.copy$default(completedContinuation, null, th, 15))) {
                            CancelHandler cancelHandler = completedContinuation.cancelHandler;
                            if (cancelHandler != null) {
                                callCancelHandler(cancelHandler, th);
                            }
                            Function1 function1 = completedContinuation.onCancellation;
                            if (function1 != null) {
                                callOnCancellation(function1, th);
                                return;
                            }
                            return;
                        }
                    } else {
                        throw new IllegalStateException("Must be called at most once".toString());
                    }
                } else if (this._state.compareAndSet(value, new CompletedContinuation(value, null, null, null, th, 14))) {
                    return;
                }
            } else {
                throw new IllegalStateException("Not completed".toString());
            }
        }
    }

    public final void completeResume(Symbol token) {
        Intrinsics.checkNotNullParameter(token, "token");
        dispatchResume(this.resumeMode);
    }

    public final void detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        DisposableHandle disposableHandle = this.parentHandle;
        if (disposableHandle == null) {
            return;
        }
        disposableHandle.dispose();
        this.parentHandle = NonDisposableHandle.INSTANCE;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation getDelegate$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this.delegate;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Throwable getExceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object obj) {
        Throwable exceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines = super.getExceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(obj);
        if (exceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines == null) {
            return null;
        }
        return exceptionalResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
        if (r1 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
        if (r6.parentHandle != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
        installParentHandle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0032, code lost:
        if (r0 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0034, code lost:
        r0 = r6.delegate;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0038, code lost:
        if ((r0 instanceof kotlinx.coroutines.internal.DispatchedContinuation) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003a, code lost:
        r2 = (kotlinx.coroutines.internal.DispatchedContinuation) r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003d, code lost:
        if (r2 == null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        r0 = r2.tryReleaseClaimedContinuation(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0043, code lost:
        if (r0 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0046, code lost:
        detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        cancel(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004e, code lost:
        return kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004f, code lost:
        if (r0 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0051, code lost:
        r0 = r6.delegate;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0055, code lost:
        if ((r0 instanceof kotlinx.coroutines.internal.DispatchedContinuation) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0057, code lost:
        r2 = (kotlinx.coroutines.internal.DispatchedContinuation) r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005a, code lost:
        if (r2 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005c, code lost:
        r0 = r2.tryReleaseClaimedContinuation(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0060, code lost:
        if (r0 != null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0063, code lost:
        detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        cancel(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0069, code lost:
        r0 = r6._state.getValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0071, code lost:
        if ((r0 instanceof kotlinx.coroutines.CompletedExceptionally) != false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0073, code lost:
        r1 = r6.resumeMode;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0075, code lost:
        if (r1 == 1) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0077, code lost:
        if (r1 != 2) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007a, code lost:
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x007b, code lost:
        if (r4 == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x007d, code lost:
        r1 = r6.context;
        r2 = kotlinx.coroutines.Job.Key;
        r1 = (kotlinx.coroutines.Job) r1.get(kotlinx.coroutines.Job.Key.$$INSTANCE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0089, code lost:
        if (r1 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x008f, code lost:
        if (r1.isActive() == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0092, code lost:
        r1 = ((kotlinx.coroutines.JobSupport) r1).getCancellationException();
        cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(r0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x009b, code lost:
        throw r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00a0, code lost:
        return getSuccessfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a5, code lost:
        throw ((kotlinx.coroutines.CompletedExceptionally) r0).cause;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getResult() {
        /*
            r6 = this;
            boolean r0 = r6.isReusable()
            kotlinx.atomicfu.AtomicInt r1 = r6._decision
        L6:
            int r2 = r1.getValue()
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L1f
            if (r2 != r3) goto L13
            r1 = r5
            goto L28
        L13:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "Already suspended"
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        L1f:
            kotlinx.atomicfu.AtomicInt r2 = r6._decision
            boolean r2 = r2.compareAndSet(r5, r4)
            if (r2 == 0) goto L6
            r1 = r4
        L28:
            r2 = 0
            if (r1 == 0) goto L4f
            kotlinx.coroutines.DisposableHandle r1 = r6.parentHandle
            if (r1 != 0) goto L32
            r6.installParentHandle()
        L32:
            if (r0 == 0) goto L4c
            kotlin.coroutines.Continuation r0 = r6.delegate
            boolean r1 = r0 instanceof kotlinx.coroutines.internal.DispatchedContinuation
            if (r1 == 0) goto L3d
            r2 = r0
            kotlinx.coroutines.internal.DispatchedContinuation r2 = (kotlinx.coroutines.internal.DispatchedContinuation) r2
        L3d:
            if (r2 == 0) goto L4c
            java.lang.Throwable r0 = r2.tryReleaseClaimedContinuation(r6)
            if (r0 != 0) goto L46
            goto L4c
        L46:
            r6.detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines()
            r6.cancel(r0)
        L4c:
            kotlin.coroutines.intrinsics.CoroutineSingletons r6 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            return r6
        L4f:
            if (r0 == 0) goto L69
            kotlin.coroutines.Continuation r0 = r6.delegate
            boolean r1 = r0 instanceof kotlinx.coroutines.internal.DispatchedContinuation
            if (r1 == 0) goto L5a
            r2 = r0
            kotlinx.coroutines.internal.DispatchedContinuation r2 = (kotlinx.coroutines.internal.DispatchedContinuation) r2
        L5a:
            if (r2 == 0) goto L69
            java.lang.Throwable r0 = r2.tryReleaseClaimedContinuation(r6)
            if (r0 != 0) goto L63
            goto L69
        L63:
            r6.detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines()
            r6.cancel(r0)
        L69:
            kotlinx.atomicfu.AtomicRef r0 = r6._state
            java.lang.Object r0 = r0.getValue()
            boolean r1 = r0 instanceof kotlinx.coroutines.CompletedExceptionally
            if (r1 != 0) goto La1
            int r1 = r6.resumeMode
            if (r1 == r4) goto L7b
            if (r1 != r3) goto L7a
            goto L7b
        L7a:
            r4 = r5
        L7b:
            if (r4 == 0) goto L9c
            kotlin.coroutines.CoroutineContext r1 = r6.context
            kotlinx.coroutines.Job$Key r2 = kotlinx.coroutines.Job.Key
            kotlinx.coroutines.Job$Key r2 = kotlinx.coroutines.Job.Key.$$INSTANCE
            kotlin.coroutines.CoroutineContext$Element r1 = r1.get(r2)
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            if (r1 == 0) goto L9c
            boolean r2 = r1.isActive()
            if (r2 == 0) goto L92
            goto L9c
        L92:
            kotlinx.coroutines.JobSupport r1 = (kotlinx.coroutines.JobSupport) r1
            java.util.concurrent.CancellationException r1 = r1.getCancellationException()
            r6.cancelCompletedResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(r0, r1)
            throw r1
        L9c:
            java.lang.Object r6 = r6.getSuccessfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(r0)
            return r6
        La1:
            kotlinx.coroutines.CompletedExceptionally r0 = (kotlinx.coroutines.CompletedExceptionally) r0
            java.lang.Throwable r6 = r0.cause
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CancellableContinuationImpl.getResult():java.lang.Object");
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Object getSuccessfulResult$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object obj) {
        if (obj instanceof CompletedContinuation) {
            return ((CompletedContinuation) obj).result;
        }
        return obj;
    }

    public final void initCancellability() {
        DisposableHandle installParentHandle = installParentHandle();
        if (installParentHandle != null && (!(this._state.getValue() instanceof NotCompleted))) {
            installParentHandle.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
    }

    public final void invokeOnCancellation(Function1 function1) {
        CancelHandler invokeOnCancel;
        boolean z;
        if (function1 instanceof CancelHandler) {
            invokeOnCancel = (CancelHandler) function1;
        } else {
            invokeOnCancel = new InvokeOnCancel(function1);
        }
        AtomicRef atomicRef = this._state;
        while (true) {
            Object value = atomicRef.getValue();
            if (value instanceof Active) {
                if (this._state.compareAndSet(value, invokeOnCancel)) {
                    return;
                }
            } else {
                Throwable th = null;
                if (!(value instanceof CancelHandler)) {
                    boolean z2 = value instanceof CompletedExceptionally;
                    if (z2) {
                        CompletedExceptionally completedExceptionally = (CompletedExceptionally) value;
                        if (completedExceptionally.makeHandled()) {
                            if (value instanceof CancelledContinuation) {
                                if (!z2) {
                                    completedExceptionally = null;
                                }
                                if (completedExceptionally != null) {
                                    th = completedExceptionally.cause;
                                }
                                callCancelHandler(function1, th);
                                return;
                            }
                            return;
                        }
                        multipleHandlersError(value, function1);
                        throw null;
                    } else if (value instanceof CompletedContinuation) {
                        CompletedContinuation completedContinuation = (CompletedContinuation) value;
                        if (completedContinuation.cancelHandler == null) {
                            if (invokeOnCancel instanceof BeforeResumeCancelHandler) {
                                return;
                            }
                            Throwable th2 = completedContinuation.cancelCause;
                            if (th2 != null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z) {
                                callCancelHandler(function1, th2);
                                return;
                            }
                            if (this._state.compareAndSet(value, CompletedContinuation.copy$default(completedContinuation, invokeOnCancel, null, 29))) {
                                return;
                            }
                        } else {
                            multipleHandlersError(value, function1);
                            throw null;
                        }
                    } else if (invokeOnCancel instanceof BeforeResumeCancelHandler) {
                        return;
                    } else {
                        if (this._state.compareAndSet(value, new CompletedContinuation(value, invokeOnCancel, null, null, null, 28))) {
                            return;
                        }
                    }
                } else {
                    multipleHandlersError(value, function1);
                    throw null;
                }
            }
        }
    }

    public final void parentCancelled$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Throwable th) {
        boolean postponeCancellation;
        if (!isReusable()) {
            postponeCancellation = false;
        } else {
            Continuation continuation = this.delegate;
            Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            postponeCancellation = ((DispatchedContinuation) continuation).postponeCancellation(th);
        }
        if (postponeCancellation) {
            return;
        }
        cancel(th);
        if (!isReusable()) {
            detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        }
    }

    public final boolean resetStateReusable() {
        Object value = this._state.getValue();
        if ((value instanceof CompletedContinuation) && ((CompletedContinuation) value).idempotentResume != null) {
            detachChild$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            return false;
        }
        this._decision.setValue(0);
        this._state.setValue(Active.INSTANCE);
        return true;
    }

    public final void resume(Object obj, Function1 function1) {
        resumeImpl(obj, this.resumeMode, function1);
    }

    public final void resumeUndispatched(CoroutineDispatcher coroutineDispatcher) {
        DispatchedContinuation dispatchedContinuation;
        CoroutineDispatcher coroutineDispatcher2;
        int i;
        Unit unit = Unit.INSTANCE;
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "<this>");
        Continuation continuation = this.delegate;
        if (continuation instanceof DispatchedContinuation) {
            dispatchedContinuation = (DispatchedContinuation) continuation;
        } else {
            dispatchedContinuation = null;
        }
        if (dispatchedContinuation != null) {
            coroutineDispatcher2 = dispatchedContinuation.dispatcher;
        } else {
            coroutineDispatcher2 = null;
        }
        if (coroutineDispatcher2 == coroutineDispatcher) {
            i = 4;
        } else {
            i = this.resumeMode;
        }
        resumeImpl(unit, i, null);
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Throwable m478exceptionOrNullimpl = Result.m478exceptionOrNullimpl(obj);
        if (m478exceptionOrNullimpl != null) {
            obj = new CompletedExceptionally(m478exceptionOrNullimpl, false);
        }
        resumeImpl(obj, this.resumeMode, null);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Object takeState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this._state.getValue();
    }

    public final String toString() {
        String str;
        String debugString = DebugStringsKt.toDebugString(this.delegate);
        Object value = this._state.getValue();
        if (value instanceof NotCompleted) {
            str = "Active";
        } else if (value instanceof CancelledContinuation) {
            str = "Cancelled";
        } else {
            str = "Completed";
        }
        String hexAddress = DebugStringsKt.getHexAddress(this);
        return "CancellableContinuation(" + debugString + "){" + str + "}@" + hexAddress;
    }

    public final Symbol tryResume(Object obj, Object obj2) {
        return tryResumeImpl(obj, obj2, null);
    }

    public final Symbol tryResumeWithException(Throwable th) {
        return tryResumeImpl(new CompletedExceptionally(th, false), null, null);
    }

    public final Symbol tryResume(Object obj, Function1 function1) {
        return tryResumeImpl(obj, null, function1);
    }

    private final void callCancelHandler(Function1 function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            CoroutineContext coroutineContext = this.context;
            CoroutineExceptionHandlerKt.handleCoroutineException(coroutineContext, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }
}
