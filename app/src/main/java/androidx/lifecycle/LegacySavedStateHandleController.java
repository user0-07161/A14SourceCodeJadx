package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.SavedStateHandle;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class LegacySavedStateHandleController {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class OnRecreation implements SavedStateRegistry.AutoRecreated {
        OnRecreation() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.AutoRecreated
        public final void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner) {
            if (savedStateRegistryOwner instanceof ViewModelStoreOwner) {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) savedStateRegistryOwner).getViewModelStore();
                SavedStateRegistry savedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
                Iterator it = ((HashSet) viewModelStore.keys()).iterator();
                while (it.hasNext()) {
                    LegacySavedStateHandleController.attachHandleIfNeeded(viewModelStore.get((String) it.next()), savedStateRegistry, savedStateRegistryOwner.getLifecycle());
                }
                if (!((HashSet) viewModelStore.keys()).isEmpty()) {
                    savedStateRegistry.runOnNextRecreation(OnRecreation.class);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void attachHandleIfNeeded(ViewModel viewModel, SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) viewModel.getTag();
        if (savedStateHandleController != null && !savedStateHandleController.isAttached()) {
            savedStateHandleController.attachToLifecycle(lifecycle, savedStateRegistry);
            tryToAddRecreator(lifecycle, savedStateRegistry);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SavedStateHandleController create(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle, String str, Bundle bundle) {
        Bundle consumeRestoredStateForKey = savedStateRegistry.consumeRestoredStateForKey(str);
        int i = SavedStateHandle.$r8$clinit;
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, SavedStateHandle.Companion.createHandle(consumeRestoredStateForKey, bundle));
        savedStateHandleController.attachToLifecycle(lifecycle, savedStateRegistry);
        tryToAddRecreator(lifecycle, savedStateRegistry);
        return savedStateHandleController;
    }

    private static void tryToAddRecreator(final Lifecycle lifecycle, final SavedStateRegistry savedStateRegistry) {
        boolean z;
        Lifecycle.State currentState = lifecycle.getCurrentState();
        if (currentState != Lifecycle.State.INITIALIZED) {
            if (currentState.compareTo(Lifecycle.State.STARTED) >= 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                lifecycle.addObserver(new LifecycleEventObserver() { // from class: androidx.lifecycle.LegacySavedStateHandleController.1
                    @Override // androidx.lifecycle.LifecycleEventObserver
                    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                        if (event == Lifecycle.Event.ON_START) {
                            Lifecycle.this.removeObserver(this);
                            savedStateRegistry.runOnNextRecreation(OnRecreation.class);
                        }
                    }
                });
                return;
            }
        }
        savedStateRegistry.runOnNextRecreation(OnRecreation.class);
    }
}
