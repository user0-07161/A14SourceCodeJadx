package androidx.compose.animation;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.unit.IntSize;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AnimatedVisibilityScopeImpl implements AnimatedVisibilityScope {
    private final ParcelableSnapshotMutableState targetSize = SnapshotStateKt.mutableStateOf$default(IntSize.m404boximpl(0));

    public AnimatedVisibilityScopeImpl(Transition transition) {
    }

    public final ParcelableSnapshotMutableState getTargetSize$animation_release() {
        return this.targetSize;
    }
}
