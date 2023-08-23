package androidx.lifecycle.viewmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class InitializerViewModelFactoryBuilder {
    private final List initializers = new ArrayList();

    public final void addInitializer(ClassReference classReference, Function1 initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        List list = this.initializers;
        Class jClass = classReference.getJClass();
        Intrinsics.checkNotNull(jClass, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        ((ArrayList) list).add(new ViewModelInitializer(jClass, initializer));
    }

    public final InitializerViewModelFactory build() {
        Object[] array = ((ArrayList) this.initializers).toArray(new ViewModelInitializer[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        ViewModelInitializer[] viewModelInitializerArr = (ViewModelInitializer[]) array;
        return new InitializerViewModelFactory((ViewModelInitializer[]) Arrays.copyOf(viewModelInitializerArr, viewModelInitializerArr.length));
    }
}
