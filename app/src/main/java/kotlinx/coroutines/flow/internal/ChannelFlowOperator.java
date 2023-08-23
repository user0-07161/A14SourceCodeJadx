package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ChannelFlowOperator extends ChannelFlow {
    protected final Flow flow;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelFlowOperator(int i, CoroutineContext context, BufferOverflow onBufferOverflow, Flow flow) {
        super(context, i, onBufferOverflow);
        Intrinsics.checkNotNullParameter(flow, "flow");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onBufferOverflow, "onBufferOverflow");
        this.flow = flow;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect;
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Unit unit = Unit.INSTANCE;
        if (this.capacity == -3) {
            CoroutineContext context = continuation.getContext();
            CoroutineContext plus = context.plus(this.context);
            if (Intrinsics.areEqual(plus, context)) {
                collect = flowCollect(flowCollector, continuation);
                if (collect != coroutineSingletons) {
                    return unit;
                }
                return collect;
            }
            ContinuationInterceptor.Key key = ContinuationInterceptor.Key;
            if (Intrinsics.areEqual(plus.get(key), context.get(key))) {
                CoroutineContext context2 = continuation.getContext();
                if (flowCollector instanceof SendingCollector) {
                    z = true;
                } else {
                    z = flowCollector instanceof NopCollector;
                }
                if (!z) {
                    flowCollector = new UndispatchedContextCollector(flowCollector, context2);
                }
                ChannelFlowOperator$collectWithContextUndispatched$2 channelFlowOperator$collectWithContextUndispatched$2 = new ChannelFlowOperator$collectWithContextUndispatched$2(this, null);
                Object updateThreadContext = ThreadContextKt.updateThreadContext(plus, ThreadContextKt.threadContextElements(plus));
                try {
                    StackFrameContinuation stackFrameContinuation = new StackFrameContinuation(continuation, plus);
                    TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, channelFlowOperator$collectWithContextUndispatched$2);
                    Object invoke = channelFlowOperator$collectWithContextUndispatched$2.invoke(flowCollector, stackFrameContinuation);
                    if (invoke != coroutineSingletons) {
                        invoke = unit;
                    }
                    if (invoke == coroutineSingletons) {
                        return invoke;
                    }
                    return unit;
                } finally {
                    ThreadContextKt.restoreThreadContext(plus, updateThreadContext);
                }
            }
        }
        collect = super.collect(flowCollector, continuation);
        if (collect != coroutineSingletons) {
            return unit;
        }
        return collect;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public final Object collectTo(ProducerScope producerScope, Continuation continuation) {
        Object flowCollect = flowCollect(new SendingCollector(producerScope), continuation);
        if (flowCollect != CoroutineSingletons.COROUTINE_SUSPENDED) {
            return Unit.INSTANCE;
        }
        return flowCollect;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object flowCollect(FlowCollector flowCollector, Continuation continuation);

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public final String toString() {
        String channelFlow = super.toString();
        return this.flow + " -> " + channelFlow;
    }
}
