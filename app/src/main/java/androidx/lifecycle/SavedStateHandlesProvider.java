package androidx.lifecycle;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;
import com.android.egg.landroid.MainActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SavedStateHandlesProvider implements SavedStateRegistry.SavedStateProvider {
    private boolean restored;
    private Bundle restoredState;
    private final SavedStateRegistry savedStateRegistry;
    private final Lazy viewModel$delegate;

    public SavedStateHandlesProvider(SavedStateRegistry savedStateRegistry, final MainActivity mainActivity) {
        Intrinsics.checkNotNullParameter(savedStateRegistry, "savedStateRegistry");
        this.savedStateRegistry = savedStateRegistry;
        this.viewModel$delegate = LazyKt.lazy$1(new Function0() { // from class: androidx.lifecycle.SavedStateHandlesProvider$viewModel$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SavedStateHandleSupport.getSavedStateHandlesVM(mainActivity);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (r4.isEmpty() == true) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.os.Bundle consumeRestoredStateForKey(java.lang.String r4) {
        /*
            r3 = this;
            r3.performRestore()
            android.os.Bundle r0 = r3.restoredState
            r1 = 0
            if (r0 == 0) goto Ld
            android.os.Bundle r0 = r0.getBundle(r4)
            goto Le
        Ld:
            r0 = r1
        Le:
            android.os.Bundle r2 = r3.restoredState
            if (r2 == 0) goto L15
            r2.remove(r4)
        L15:
            android.os.Bundle r4 = r3.restoredState
            if (r4 == 0) goto L21
            boolean r4 = r4.isEmpty()
            r2 = 1
            if (r4 != r2) goto L21
            goto L22
        L21:
            r2 = 0
        L22:
            if (r2 == 0) goto L26
            r3.restoredState = r1
        L26:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.SavedStateHandlesProvider.consumeRestoredStateForKey(java.lang.String):android.os.Bundle");
    }

    public final void performRestore() {
        if (!this.restored) {
            this.restoredState = this.savedStateRegistry.consumeRestoredStateForKey("androidx.lifecycle.internal.SavedStateHandlesProvider");
            this.restored = true;
            SavedStateHandlesVM savedStateHandlesVM = (SavedStateHandlesVM) this.viewModel$delegate.getValue();
        }
    }

    @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
    public final Bundle saveState() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.restoredState;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        for (Map.Entry entry : ((LinkedHashMap) ((SavedStateHandlesVM) this.viewModel$delegate.getValue()).getHandles()).entrySet()) {
            String str = (String) entry.getKey();
            Bundle saveState = ((SavedStateHandle$$ExternalSyntheticLambda0) ((SavedStateHandle) entry.getValue()).savedStateProvider()).saveState();
            if (!Intrinsics.areEqual(saveState, Bundle.EMPTY)) {
                bundle.putBundle(str, saveState);
            }
        }
        this.restored = false;
        return bundle;
    }
}
