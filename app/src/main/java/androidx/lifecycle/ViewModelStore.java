package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ViewModelStore {
    private final HashMap mMap = new HashMap();

    public final void clear() {
        HashMap hashMap = this.mMap;
        for (ViewModel viewModel : hashMap.values()) {
            viewModel.clear();
        }
        hashMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ViewModel get(String str) {
        return (ViewModel) this.mMap.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set keys() {
        return new HashSet(this.mMap.keySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void put(ViewModel viewModel) {
        ViewModel viewModel2 = (ViewModel) this.mMap.put("androidx.lifecycle.internal.SavedStateHandlesVM", viewModel);
    }
}
