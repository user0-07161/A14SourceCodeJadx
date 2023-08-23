package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SharedFlowSlot extends AbstractSharedFlowSlot {
    public CancellableContinuationImpl cont;
    public long index = -1;

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public final boolean allocateLocked(AbstractSharedFlow abstractSharedFlow) {
        SharedFlowImpl flow = (SharedFlowImpl) abstractSharedFlow;
        Intrinsics.checkNotNullParameter(flow, "flow");
        if (this.index >= 0) {
            return false;
        }
        this.index = flow.updateNewCollectorIndexLocked$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        return true;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public final Continuation[] freeLocked(AbstractSharedFlow abstractSharedFlow) {
        SharedFlowImpl flow = (SharedFlowImpl) abstractSharedFlow;
        Intrinsics.checkNotNullParameter(flow, "flow");
        long j = this.index;
        this.index = -1L;
        this.cont = null;
        return flow.updateCollectorIndexLocked$external__kotlinx_coroutines__android_common__kotlinx_coroutines(j);
    }
}
