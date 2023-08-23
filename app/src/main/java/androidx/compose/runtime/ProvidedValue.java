package androidx.compose.runtime;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ProvidedValue {
    private final boolean canOverride;
    private final CompositionLocal compositionLocal;
    private final Object value;

    public ProvidedValue(CompositionLocal compositionLocal, Object obj, boolean z) {
        Intrinsics.checkNotNullParameter(compositionLocal, "compositionLocal");
        this.compositionLocal = compositionLocal;
        this.value = obj;
        this.canOverride = z;
    }

    public final boolean getCanOverride() {
        return this.canOverride;
    }

    public final CompositionLocal getCompositionLocal() {
        return this.compositionLocal;
    }

    public final Object getValue() {
        return this.value;
    }
}
