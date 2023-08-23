package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class FlowKt {
    public static final Flow callbackFlow(Function2 function2) {
        return new CallbackFlowBuilder(function2, EmptyCoroutineContext.INSTANCE, -2, BufferOverflow.SUSPEND);
    }

    public static final Object emitAll(FlowCollector flowCollector, ReceiveChannel receiveChannel, Continuation continuation) {
        return FlowKt__ChannelsKt.emitAll(flowCollector, receiveChannel, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x005e, code lost:
        if (r5.collect(r2, r0) == r1) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object first(kotlinx.coroutines.flow.Flow r5, kotlin.jvm.functions.Function2 r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2 r5 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r6 = (kotlin.jvm.internal.Ref$ObjectRef) r6
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L33
            goto L6f
        L33:
            r7 = move-exception
            r4 = r7
            r7 = r6
            r6 = r0
            r0 = r4
            goto L64
        L39:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L41:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.jvm.internal.Ref$ObjectRef r7 = new kotlin.jvm.internal.Ref$ObjectRef
            r7.<init>()
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            r7.element = r2
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2 r2 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2
            r2.<init>(r6, r7)
            r0.L$0 = r6     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L61
            r0.L$1 = r7     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L61
            r0.L$2 = r2     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L61
            r0.label = r3     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L61
            java.lang.Object r5 = r5.collect(r2, r0)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L61
            if (r5 != r1) goto L6d
            goto L75
        L61:
            r5 = move-exception
            r0 = r5
            r5 = r2
        L64:
            java.lang.String r1 = "owner"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)
            kotlinx.coroutines.flow.FlowCollector r1 = r0.owner
            if (r1 != r5) goto L8a
        L6d:
            r0 = r6
            r6 = r7
        L6f:
            java.lang.Object r1 = r6.element
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r1 == r5) goto L76
        L75:
            return r1
        L76:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Expected at least one element matching the predicate "
            r6.<init>(r7)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L8a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt.first(kotlinx.coroutines.flow.Flow, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Flow flow(Function2 function2) {
        return new SafeFlow(function2);
    }

    public static final StateFlow stateIn(Flow flow, ContextScope contextScope, SharingStarted sharingStarted, Object obj) {
        SharingConfig sharingConfig;
        CoroutineStart coroutineStart;
        ChannelFlow channelFlow;
        Flow dropChannelOperators;
        int unused;
        Channel.Factory.getClass();
        unused = Channel.Factory.CHANNEL_DEFAULT_CAPACITY;
        boolean z = flow instanceof ChannelFlow;
        BufferOverflow bufferOverflow = BufferOverflow.SUSPEND;
        if (z && (dropChannelOperators = (channelFlow = (ChannelFlow) flow).dropChannelOperators()) != null) {
            BufferOverflow bufferOverflow2 = channelFlow.onBufferOverflow;
            if (channelFlow.capacity != -3) {
            }
            sharingConfig = new SharingConfig(channelFlow.context, bufferOverflow2, dropChannelOperators);
        } else {
            sharingConfig = new SharingConfig(EmptyCoroutineContext.INSTANCE, bufferOverflow, flow);
        }
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(obj);
        Flow flow2 = sharingConfig.upstream;
        SharingStarted.Companion companion = SharingStarted.Companion;
        if (Intrinsics.areEqual(sharingStarted, SharingStarted.Companion.getEagerly())) {
            coroutineStart = CoroutineStart.DEFAULT;
        } else {
            coroutineStart = CoroutineStart.UNDISPATCHED;
        }
        BuildersKt.launch(contextScope, sharingConfig.context, coroutineStart, new FlowKt__ShareKt$launchSharing$1(sharingStarted, flow2, MutableStateFlow, obj, null));
        return new ReadonlyStateFlow(MutableStateFlow);
    }
}
