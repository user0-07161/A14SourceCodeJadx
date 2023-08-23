package androidx.compose.runtime;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LazyValueHolder implements State {
    private final Lazy current$delegate;

    public LazyValueHolder(Function0 valueProducer) {
        Intrinsics.checkNotNullParameter(valueProducer, "valueProducer");
        this.current$delegate = LazyKt.lazy$1(valueProducer);
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return this.current$delegate.getValue();
    }
}
