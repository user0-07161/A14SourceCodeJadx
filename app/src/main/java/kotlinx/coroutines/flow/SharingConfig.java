package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class SharingConfig {
    public final CoroutineContext context;
    public final Flow upstream;

    public SharingConfig(CoroutineContext context, BufferOverflow onBufferOverflow, Flow flow) {
        Intrinsics.checkNotNullParameter(onBufferOverflow, "onBufferOverflow");
        Intrinsics.checkNotNullParameter(context, "context");
        this.upstream = flow;
        this.context = context;
    }
}
