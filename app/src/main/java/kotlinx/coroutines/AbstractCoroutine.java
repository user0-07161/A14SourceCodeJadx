package kotlinx.coroutines;

import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.intrinsics.CancellableKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AbstractCoroutine extends JobSupport implements Continuation, CoroutineScope {
    private final CoroutineContext context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractCoroutine(CoroutineContext parentContext, boolean z) {
        super(z);
        Intrinsics.checkNotNullParameter(parentContext, "parentContext");
        initParentJob((Job) parentContext.get(Job.Key.$$INSTANCE));
        this.context = parentContext.plus(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.JobSupport
    public final String cancellationExceptionMessage() {
        return DebugStringsKt.getClassSimpleName(this).concat(" was cancelled");
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public final CoroutineContext getCoroutineContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(CompletionHandlerException completionHandlerException) {
        CoroutineExceptionHandlerKt.handleCoroutineException(this.context, completionHandlerException);
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.JobSupport
    public String nameString$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        Intrinsics.checkNotNullParameter(this.context, "<this>");
        return DebugStringsKt.getClassSimpleName(this);
    }

    protected void onCancelled(Throwable cause, boolean z) {
        Intrinsics.checkNotNullParameter(cause, "cause");
    }

    @Override // kotlinx.coroutines.JobSupport
    protected final void onCompletionInternal(Object obj) {
        if (obj instanceof CompletedExceptionally) {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
            onCancelled(completedExceptionally.cause, completedExceptionally.getHandled());
            return;
        }
        onCompleted(obj);
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Throwable m478exceptionOrNullimpl = Result.m478exceptionOrNullimpl(obj);
        if (m478exceptionOrNullimpl != null) {
            obj = new CompletedExceptionally(m478exceptionOrNullimpl, false);
        }
        Object makeCompletingOnce$external__kotlinx_coroutines__android_common__kotlinx_coroutines = makeCompletingOnce$external__kotlinx_coroutines__android_common__kotlinx_coroutines(obj);
        if (makeCompletingOnce$external__kotlinx_coroutines__android_common__kotlinx_coroutines == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return;
        }
        afterResume(makeCompletingOnce$external__kotlinx_coroutines__android_common__kotlinx_coroutines);
    }

    public final void start(CoroutineStart start, AbstractCoroutine abstractCoroutine, Function2 block) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(block, "block");
        int ordinal = start.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal == 3) {
                        try {
                            CoroutineContext coroutineContext = this.context;
                            Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, null);
                            TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, block);
                            Object invoke = block.invoke(abstractCoroutine, this);
                            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
                            if (invoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                resumeWith(invoke);
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            resumeWith(ResultKt.createFailure(th));
                            return;
                        }
                    }
                    throw new NoWhenBranchMatchedException();
                }
                IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(abstractCoroutine, this, block)).resumeWith(Unit.INSTANCE);
                return;
            }
            return;
        }
        CancellableKt.startCoroutineCancellable$default(block, abstractCoroutine, this);
    }

    protected void onCompleted(Object obj) {
    }
}
