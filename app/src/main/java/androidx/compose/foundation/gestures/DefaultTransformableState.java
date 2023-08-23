package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.jvm.functions.Function3;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DefaultTransformableState implements TransformableState {
    private final Function3 onTransformation;
    private final DefaultTransformableState$transformScope$1 transformScope = new DefaultTransformableState$transformScope$1(this);
    private final MutatorMutex transformMutex = new MutatorMutex();
    private final ParcelableSnapshotMutableState isTransformingState = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);

    public DefaultTransformableState(Function3 function3) {
        this.onTransformation = function3;
    }

    public final Function3 getOnTransformation() {
        return this.onTransformation;
    }
}
