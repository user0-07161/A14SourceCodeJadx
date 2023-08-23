package androidx.compose.runtime;

import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CompositionLocal {
    private final LazyValueHolder defaultValueHolder;

    public CompositionLocal(Function0 function0) {
        this.defaultValueHolder = new LazyValueHolder(function0);
    }

    public final LazyValueHolder getDefaultValueHolder$runtime_release() {
        return this.defaultValueHolder;
    }

    public abstract State provided$runtime_release(Object obj, Composer composer);
}
