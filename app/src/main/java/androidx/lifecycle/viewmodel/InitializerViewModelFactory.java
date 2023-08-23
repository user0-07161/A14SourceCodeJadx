package androidx.lifecycle.viewmodel;

import androidx.lifecycle.SavedStateHandlesVM;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class InitializerViewModelFactory implements ViewModelProvider.Factory {
    private final ViewModelInitializer[] initializers;

    public InitializerViewModelFactory(ViewModelInitializer... initializers) {
        Intrinsics.checkNotNullParameter(initializers, "initializers");
        this.initializers = initializers;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(MutableCreationExtras mutableCreationExtras) {
        ViewModelInitializer[] viewModelInitializerArr;
        ViewModel viewModel = null;
        for (ViewModelInitializer viewModelInitializer : this.initializers) {
            if (Intrinsics.areEqual(viewModelInitializer.getClazz$lifecycle_viewmodel_release(), SavedStateHandlesVM.class)) {
                Object invoke = viewModelInitializer.getInitializer$lifecycle_viewmodel_release().invoke(mutableCreationExtras);
                if (invoke instanceof ViewModel) {
                    viewModel = (ViewModel) invoke;
                } else {
                    viewModel = null;
                }
            }
        }
        if (viewModel != null) {
            return viewModel;
        }
        throw new IllegalArgumentException("No initializer set for given class ".concat(SavedStateHandlesVM.class.getName()));
    }
}
