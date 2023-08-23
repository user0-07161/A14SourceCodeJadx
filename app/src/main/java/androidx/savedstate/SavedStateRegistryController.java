package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import com.android.egg.landroid.MainActivity;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SavedStateRegistryController {
    private boolean attached;
    private final SavedStateRegistryOwner owner;
    private final SavedStateRegistry savedStateRegistry = new SavedStateRegistry();

    public SavedStateRegistryController(MainActivity mainActivity) {
        this.owner = mainActivity;
    }

    public final SavedStateRegistry getSavedStateRegistry() {
        return this.savedStateRegistry;
    }

    public final void performAttach() {
        boolean z;
        SavedStateRegistryOwner savedStateRegistryOwner = this.owner;
        Lifecycle lifecycle = savedStateRegistryOwner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "owner.lifecycle");
        if (lifecycle.getCurrentState() == Lifecycle.State.INITIALIZED) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            lifecycle.addObserver(new Recreator(savedStateRegistryOwner));
            this.savedStateRegistry.performAttach$savedstate_release(lifecycle);
            this.attached = true;
            return;
        }
        throw new IllegalStateException("Restarter must be created only during owner's initialization stage".toString());
    }

    public final void performRestore(Bundle bundle) {
        boolean z;
        if (!this.attached) {
            performAttach();
        }
        Lifecycle lifecycle = this.owner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "owner.lifecycle");
        if (lifecycle.getCurrentState().compareTo(Lifecycle.State.STARTED) >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            this.savedStateRegistry.performRestore$savedstate_release(bundle);
            return;
        }
        throw new IllegalStateException(("performRestore cannot be called when owner is " + lifecycle.getCurrentState()).toString());
    }

    public final void performSave(Bundle outBundle) {
        Intrinsics.checkNotNullParameter(outBundle, "outBundle");
        this.savedStateRegistry.performSave(outBundle);
    }
}
