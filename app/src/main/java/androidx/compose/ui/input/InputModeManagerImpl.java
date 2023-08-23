package androidx.compose.ui.input;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class InputModeManagerImpl {
    private final ParcelableSnapshotMutableState inputMode$delegate;

    public InputModeManagerImpl(int i) {
        this.inputMode$delegate = SnapshotStateKt.mutableStateOf$default(InputMode.m170boximpl(i));
    }

    /* renamed from: setInputMode-iuPiT84  reason: not valid java name */
    public final void m172setInputModeiuPiT84(int i) {
        this.inputMode$delegate.setValue(InputMode.m170boximpl(i));
    }
}
