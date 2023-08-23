package androidx.compose.ui.platform;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class WindowInfoImpl {
    private static final ParcelableSnapshotMutableState GlobalKeyboardModifiers = SnapshotStateKt.mutableStateOf$default(PointerKeyboardModifiers.m200boximpl(0));
    private final ParcelableSnapshotMutableState _isWindowFocused = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);

    /* renamed from: setKeyboardModifiers-5xRPYO0  reason: not valid java name */
    public static void m289setKeyboardModifiers5xRPYO0(int i) {
        GlobalKeyboardModifiers.setValue(PointerKeyboardModifiers.m200boximpl(i));
    }

    public final void setWindowFocused(boolean z) {
        this._isWindowFocused.setValue(Boolean.valueOf(z));
    }
}
