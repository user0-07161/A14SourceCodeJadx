package androidx.lifecycle.viewmodel;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ViewModelInitializer {
    private final Class clazz;
    private final Function1 initializer;

    public ViewModelInitializer(Class cls, Function1 initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        this.clazz = cls;
        this.initializer = initializer;
    }

    public final Class getClazz$lifecycle_viewmodel_release() {
        return this.clazz;
    }

    public final Function1 getInitializer$lifecycle_viewmodel_release() {
        return this.initializer;
    }
}
