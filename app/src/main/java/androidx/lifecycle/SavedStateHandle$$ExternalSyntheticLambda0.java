package androidx.lifecycle;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final /* synthetic */ class SavedStateHandle$$ExternalSyntheticLambda0 implements SavedStateRegistry.SavedStateProvider {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ SavedStateHandle f$0;

    public /* synthetic */ SavedStateHandle$$ExternalSyntheticLambda0(SavedStateHandle savedStateHandle, int i) {
        this.$r8$classId = i;
        this.f$0 = savedStateHandle;
    }

    @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
    public final Bundle saveState() {
        int i = this.$r8$classId;
        SavedStateHandle savedStateHandle = this.f$0;
        switch (i) {
            case 0:
                return SavedStateHandle.$r8$lambda$aMir0GWwzPQviKVGE0DPm0kayew(savedStateHandle);
            default:
                return SavedStateHandle.$r8$lambda$aMir0GWwzPQviKVGE0DPm0kayew(savedStateHandle);
        }
    }
}
