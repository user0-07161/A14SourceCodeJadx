package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.lang.reflect.Constructor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SavedStateViewModelFactory extends ViewModelProvider.OnRequeryFactory implements ViewModelProvider.Factory {
    private Application application;
    private Bundle defaultArgs;
    private final ViewModelProvider.AndroidViewModelFactory factory;
    private Lifecycle lifecycle;
    private SavedStateRegistry savedStateRegistry;

    public SavedStateViewModelFactory(Application application, SavedStateRegistryOwner owner, Bundle bundle) {
        ViewModelProvider.AndroidViewModelFactory androidViewModelFactory;
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.savedStateRegistry = owner.getSavedStateRegistry();
        this.lifecycle = owner.getLifecycle();
        this.defaultArgs = bundle;
        this.application = application;
        if (application != null) {
            if (ViewModelProvider.AndroidViewModelFactory.access$getSInstance$cp() == null) {
                ViewModelProvider.AndroidViewModelFactory.access$setSInstance$cp(new ViewModelProvider.AndroidViewModelFactory(application));
            }
            androidViewModelFactory = ViewModelProvider.AndroidViewModelFactory.access$getSInstance$cp();
            Intrinsics.checkNotNull(androidViewModelFactory);
        } else {
            androidViewModelFactory = new ViewModelProvider.AndroidViewModelFactory();
        }
        this.factory = androidViewModelFactory;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create() {
        String canonicalName = SavedStateHandlesVM.class.getCanonicalName();
        if (canonicalName != null) {
            return create(canonicalName);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.ViewModelProvider.OnRequeryFactory
    public final void onRequery(ViewModel viewModel) {
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle != null) {
            LegacySavedStateHandleController.attachHandleIfNeeded(viewModel, this.savedStateRegistry, lifecycle);
        }
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(MutableCreationExtras mutableCreationExtras) {
        Constructor findMatchingConstructor;
        String str = (String) mutableCreationExtras.get(ViewModelProvider.NewInstanceFactory.INSTANCE$1);
        if (str != null) {
            if (mutableCreationExtras.get(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY) != null && mutableCreationExtras.get(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY) != null) {
                CreationExtras.Key key = ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;
                Application application = (Application) mutableCreationExtras.get(ViewModelProvider.NewInstanceFactory.INSTANCE);
                boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(SavedStateHandlesVM.class);
                if (isAssignableFrom && application != null) {
                    findMatchingConstructor = SavedStateViewModelFactoryKt.findMatchingConstructor(SavedStateViewModelFactoryKt.access$getANDROID_VIEWMODEL_SIGNATURE$p());
                } else {
                    findMatchingConstructor = SavedStateViewModelFactoryKt.findMatchingConstructor(SavedStateViewModelFactoryKt.access$getVIEWMODEL_SIGNATURE$p());
                }
                if (findMatchingConstructor == null) {
                    return this.factory.create(mutableCreationExtras);
                }
                if (isAssignableFrom && application != null) {
                    return SavedStateViewModelFactoryKt.newInstance(findMatchingConstructor, application, SavedStateHandleSupport.createSavedStateHandle(mutableCreationExtras));
                }
                return SavedStateViewModelFactoryKt.newInstance(findMatchingConstructor, SavedStateHandleSupport.createSavedStateHandle(mutableCreationExtras));
            } else if (this.lifecycle != null) {
                return create(str);
            } else {
                throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
            }
        }
        throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
    }

    public final ViewModel create(String str) {
        Constructor findMatchingConstructor;
        ViewModel newInstance;
        Application application;
        if (this.lifecycle != null) {
            boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(SavedStateHandlesVM.class);
            if (isAssignableFrom && this.application != null) {
                findMatchingConstructor = SavedStateViewModelFactoryKt.findMatchingConstructor(SavedStateViewModelFactoryKt.access$getANDROID_VIEWMODEL_SIGNATURE$p());
            } else {
                findMatchingConstructor = SavedStateViewModelFactoryKt.findMatchingConstructor(SavedStateViewModelFactoryKt.access$getVIEWMODEL_SIGNATURE$p());
            }
            if (findMatchingConstructor == null) {
                if (this.application != null) {
                    return this.factory.create();
                }
                if (ViewModelProvider.NewInstanceFactory.access$getSInstance$cp() == null) {
                    ViewModelProvider.NewInstanceFactory.access$setSInstance$cp(new ViewModelProvider.NewInstanceFactory());
                }
                ViewModelProvider.NewInstanceFactory access$getSInstance$cp = ViewModelProvider.NewInstanceFactory.access$getSInstance$cp();
                Intrinsics.checkNotNull(access$getSInstance$cp);
                return access$getSInstance$cp.create();
            }
            SavedStateHandleController create = LegacySavedStateHandleController.create(this.savedStateRegistry, this.lifecycle, str, this.defaultArgs);
            if (isAssignableFrom && (application = this.application) != null) {
                SavedStateHandle handle = create.getHandle();
                Intrinsics.checkNotNullExpressionValue(handle, "controller.handle");
                newInstance = SavedStateViewModelFactoryKt.newInstance(findMatchingConstructor, application, handle);
            } else {
                SavedStateHandle handle2 = create.getHandle();
                Intrinsics.checkNotNullExpressionValue(handle2, "controller.handle");
                newInstance = SavedStateViewModelFactoryKt.newInstance(findMatchingConstructor, handle2);
            }
            newInstance.setTagIfAbsent(create);
            return newInstance;
        }
        throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
    }
}
