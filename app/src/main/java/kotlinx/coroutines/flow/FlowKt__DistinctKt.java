package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
abstract /* synthetic */ class FlowKt__DistinctKt {
    private static final Function1 defaultKeySelector = new Function1() { // from class: kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultKeySelector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return obj;
        }
    };
    private static final Function2 defaultAreEquivalent = new Function2() { // from class: kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultAreEquivalent$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Boolean.valueOf(Intrinsics.areEqual(obj, obj2));
        }
    };

    public static final Flow distinctUntilChanged(Flow flow) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        if (!(flow instanceof StateFlow)) {
            Function1 function1 = defaultKeySelector;
            Function2 function2 = defaultAreEquivalent;
            if (flow instanceof DistinctFlowImpl) {
                DistinctFlowImpl distinctFlowImpl = (DistinctFlowImpl) flow;
                if (distinctFlowImpl.keySelector == function1 && distinctFlowImpl.areEquivalent == function2) {
                    return flow;
                }
            }
            return new DistinctFlowImpl(flow, function1, function2);
        }
        return flow;
    }
}
