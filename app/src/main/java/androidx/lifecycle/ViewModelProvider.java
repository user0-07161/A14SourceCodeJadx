package androidx.lifecycle;

import android.app.Application;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactory;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ViewModelProvider {
    private final CreationExtras defaultCreationExtras;
    private final Factory factory;
    private final ViewModelStore store;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Factory {
        default ViewModel create() {
            throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
        }

        default ViewModel create(MutableCreationExtras mutableCreationExtras) {
            return create();
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public class NewInstanceFactory implements CreationExtras.Key, Factory {
        public static final NewInstanceFactory INSTANCE = new NewInstanceFactory();
        public static final NewInstanceFactory INSTANCE$1 = new NewInstanceFactory();
        private static NewInstanceFactory sInstance;

        public static final /* synthetic */ NewInstanceFactory access$getSInstance$cp() {
            return sInstance;
        }

        public static final /* synthetic */ void access$setSInstance$cp(NewInstanceFactory newInstanceFactory) {
            sInstance = newInstanceFactory;
        }

        public ViewModel create() {
            try {
                Object newInstance = SavedStateHandlesVM.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                Intrinsics.checkNotNullExpressionValue(newInstance, "{\n                modelC…wInstance()\n            }");
                return (ViewModel) newInstance;
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e3);
            }
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class OnRequeryFactory {
        public abstract void onRequery(ViewModel viewModel);
    }

    public ViewModelProvider(ViewModelStoreOwner owner, InitializerViewModelFactory initializerViewModelFactory) {
        CreationExtras defaultCreationExtras;
        Intrinsics.checkNotNullParameter(owner, "owner");
        ViewModelStore viewModelStore = owner.getViewModelStore();
        Intrinsics.checkNotNullExpressionValue(viewModelStore, "owner.viewModelStore");
        if (owner instanceof HasDefaultViewModelProviderFactory) {
            defaultCreationExtras = ((HasDefaultViewModelProviderFactory) owner).getDefaultViewModelCreationExtras();
            Intrinsics.checkNotNullExpressionValue(defaultCreationExtras, "{\n        owner.defaultV…ModelCreationExtras\n    }");
        } else {
            defaultCreationExtras = CreationExtras.Empty.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(defaultCreationExtras, "defaultCreationExtras");
        this.store = viewModelStore;
        this.factory = initializerViewModelFactory;
        this.defaultCreationExtras = defaultCreationExtras;
    }

    public final ViewModel get() {
        ViewModel create;
        OnRequeryFactory onRequeryFactory;
        ViewModelStore viewModelStore = this.store;
        ViewModel viewModel = viewModelStore.get("androidx.lifecycle.internal.SavedStateHandlesVM");
        boolean isInstance = SavedStateHandlesVM.class.isInstance(viewModel);
        Factory factory = this.factory;
        if (isInstance) {
            if (factory instanceof OnRequeryFactory) {
                onRequeryFactory = (OnRequeryFactory) factory;
            } else {
                onRequeryFactory = null;
            }
            if (onRequeryFactory != null) {
                Intrinsics.checkNotNullExpressionValue(viewModel, "viewModel");
                onRequeryFactory.onRequery(viewModel);
            }
            Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get");
            return viewModel;
        }
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras(this.defaultCreationExtras);
        mutableCreationExtras.getMap$lifecycle_viewmodel_release().put(NewInstanceFactory.INSTANCE$1, "androidx.lifecycle.internal.SavedStateHandlesVM");
        try {
            create = factory.create(mutableCreationExtras);
        } catch (AbstractMethodError unused) {
            create = factory.create();
        }
        viewModelStore.put(create);
        return create;
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class AndroidViewModelFactory extends NewInstanceFactory {
        public static final CreationExtras.Key APPLICATION_KEY = NewInstanceFactory.INSTANCE;
        private static AndroidViewModelFactory sInstance;
        private final Application application;

        private AndroidViewModelFactory(Application application, int i) {
            this.application = application;
        }

        public static final /* synthetic */ AndroidViewModelFactory access$getSInstance$cp() {
            return sInstance;
        }

        public static final /* synthetic */ void access$setSInstance$cp(AndroidViewModelFactory androidViewModelFactory) {
            sInstance = androidViewModelFactory;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(MutableCreationExtras mutableCreationExtras) {
            if (this.application != null) {
                return create();
            }
            Application application = (Application) mutableCreationExtras.get(NewInstanceFactory.INSTANCE);
            if (application != null) {
                return create(application);
            }
            if (!AndroidViewModel.class.isAssignableFrom(SavedStateHandlesVM.class)) {
                return super.create();
            }
            throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
        }

        public AndroidViewModelFactory() {
            this(null, 0);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public AndroidViewModelFactory(Application application) {
            this(application, 0);
            Intrinsics.checkNotNullParameter(application, "application");
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create() {
            Application application = this.application;
            if (application != null) {
                return create(application);
            }
            throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
        }

        private final ViewModel create(Application application) {
            if (AndroidViewModel.class.isAssignableFrom(SavedStateHandlesVM.class)) {
                try {
                    ViewModel viewModel = (ViewModel) SavedStateHandlesVM.class.getConstructor(Application.class).newInstance(application);
                    Intrinsics.checkNotNullExpressionValue(viewModel, "{\n                try {\n…          }\n            }");
                    return viewModel;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e);
                } catch (InstantiationException e2) {
                    throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e2);
                } catch (NoSuchMethodException e3) {
                    throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e3);
                } catch (InvocationTargetException e4) {
                    throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e4);
                }
            }
            return super.create();
        }
    }
}
