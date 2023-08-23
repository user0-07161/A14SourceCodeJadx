package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class StartedEagerly implements SharingStarted {
    @Override // kotlinx.coroutines.flow.SharingStarted
    public final Flow command(StateFlow stateFlow) {
        return new Flow() { // from class: kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2
            final /* synthetic */ Object $value$inlined = SharingCommand.START;

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object emit = flowCollector.emit(this.$value$inlined, continuation);
                if (emit == CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return emit;
                }
                return Unit.INSTANCE;
            }
        };
    }

    public final String toString() {
        return "SharingStarted.Eagerly";
    }
}
