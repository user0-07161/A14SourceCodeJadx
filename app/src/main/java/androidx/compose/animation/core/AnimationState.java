package androidx.compose.animation.core;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AnimationState implements State {
    private long finishedTimeNanos;
    private boolean isRunning;
    private long lastFrameTimeNanos;
    private final TwoWayConverter typeConverter;
    private final ParcelableSnapshotMutableState value$delegate;
    private AnimationVector velocityVector;

    public AnimationState(TwoWayConverter typeConverter, Object obj, AnimationVector animationVector, long j, long j2, boolean z) {
        AnimationVector createZeroVectorFrom;
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        this.typeConverter = typeConverter;
        this.value$delegate = SnapshotStateKt.mutableStateOf$default(obj);
        if (animationVector != null) {
            createZeroVectorFrom = AnimationVectorsKt.copy(animationVector);
        } else {
            createZeroVectorFrom = AnimationStateKt.createZeroVectorFrom(typeConverter, obj);
        }
        this.velocityVector = createZeroVectorFrom;
        this.lastFrameTimeNanos = j;
        this.finishedTimeNanos = j2;
        this.isRunning = z;
    }

    public final long getLastFrameTimeNanos() {
        return this.lastFrameTimeNanos;
    }

    public final TwoWayConverter getTypeConverter() {
        return this.typeConverter;
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return this.value$delegate.getValue();
    }

    public final AnimationVector getVelocityVector() {
        return this.velocityVector;
    }

    public final boolean isRunning() {
        return this.isRunning;
    }

    public final void setFinishedTimeNanos$animation_core_release(long j) {
        this.finishedTimeNanos = j;
    }

    public final void setLastFrameTimeNanos$animation_core_release(long j) {
        this.lastFrameTimeNanos = j;
    }

    public final void setRunning$animation_core_release(boolean z) {
        this.isRunning = z;
    }

    public final void setValue$animation_core_release(Object obj) {
        this.value$delegate.setValue(obj);
    }

    public final void setVelocityVector$animation_core_release(AnimationVector animationVector) {
        Intrinsics.checkNotNullParameter(animationVector, "<set-?>");
        this.velocityVector = animationVector;
    }

    public final String toString() {
        return "AnimationState(value=" + getValue() + ", velocity=" + ((TwoWayConverterImpl) this.typeConverter).getConvertFromVector().invoke(this.velocityVector) + ", isRunning=" + this.isRunning + ", lastFrameTimeNanos=" + this.lastFrameTimeNanos + ", finishedTimeNanos=" + this.finishedTimeNanos + ')';
    }
}
