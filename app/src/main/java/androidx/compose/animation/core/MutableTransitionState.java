package androidx.compose.animation.core;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MutableTransitionState {
    private final ParcelableSnapshotMutableState currentState$delegate;
    private final ParcelableSnapshotMutableState isRunning$delegate;

    public MutableTransitionState(Object obj) {
        this.currentState$delegate = SnapshotStateKt.mutableStateOf$default(obj);
        SnapshotStateKt.mutableStateOf$default(obj);
        this.isRunning$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
    }

    public final Object getCurrentState() {
        return this.currentState$delegate.getValue();
    }

    public final void setCurrentState$animation_core_release(Object obj) {
        this.currentState$delegate.setValue(obj);
    }

    public final void setRunning$animation_core_release(boolean z) {
        this.isRunning$delegate.setValue(Boolean.valueOf(z));
    }
}
