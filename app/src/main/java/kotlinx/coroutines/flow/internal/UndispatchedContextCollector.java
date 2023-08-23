package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class UndispatchedContextCollector implements FlowCollector {
    private final Object countOrElement;
    private final CoroutineContext emitContext;
    private final Function2 emitRef;

    public UndispatchedContextCollector(FlowCollector downstream, CoroutineContext emitContext) {
        Intrinsics.checkNotNullParameter(downstream, "downstream");
        Intrinsics.checkNotNullParameter(emitContext, "emitContext");
        this.emitContext = emitContext;
        this.countOrElement = ThreadContextKt.threadContextElements(emitContext);
        this.emitRef = new UndispatchedContextCollector$emitRef$1(downstream, null);
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        Function2 function2 = this.emitRef;
        CoroutineContext coroutineContext = this.emitContext;
        Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, this.countOrElement);
        try {
            StackFrameContinuation stackFrameContinuation = new StackFrameContinuation(continuation, coroutineContext);
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, function2);
            Object invoke = ((UndispatchedContextCollector$emitRef$1) function2).invoke(obj, stackFrameContinuation);
            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            if (invoke == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return invoke;
            }
            return Unit.INSTANCE;
        } catch (Throwable th) {
            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            throw th;
        }
    }
}
