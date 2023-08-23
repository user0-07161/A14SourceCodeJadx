package androidx.compose.animation.core;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AnimationScope {
    private long finishedTimeNanos;
    private final ParcelableSnapshotMutableState isRunning$delegate;
    private long lastFrameTimeNanos;
    private final Function0 onCancel;
    private final long startTimeNanos;
    private final Object targetValue;
    private final ParcelableSnapshotMutableState value$delegate;
    private AnimationVector velocityVector;

    public AnimationScope(Object obj, TwoWayConverter typeConverter, AnimationVector initialVelocityVector, long j, Object obj2, long j2, Function0 function0) {
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        Intrinsics.checkNotNullParameter(initialVelocityVector, "initialVelocityVector");
        this.targetValue = obj2;
        this.startTimeNanos = j2;
        this.onCancel = function0;
        this.value$delegate = SnapshotStateKt.mutableStateOf$default(obj);
        this.velocityVector = AnimationVectorsKt.copy(initialVelocityVector);
        this.lastFrameTimeNanos = j;
        this.finishedTimeNanos = Long.MIN_VALUE;
        this.isRunning$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
    }

    public final void cancelAnimation() {
        setRunning$animation_core_release();
        this.onCancel.invoke();
    }

    public final long getFinishedTimeNanos() {
        return this.finishedTimeNanos;
    }

    public final long getLastFrameTimeNanos() {
        return this.lastFrameTimeNanos;
    }

    public final long getStartTimeNanos() {
        return this.startTimeNanos;
    }

    public final Object getValue() {
        return this.value$delegate.getValue();
    }

    public final AnimationVector getVelocityVector() {
        return this.velocityVector;
    }

    public final boolean isRunning() {
        return ((Boolean) this.isRunning$delegate.getValue()).booleanValue();
    }

    public final void setFinishedTimeNanos$animation_core_release(long j) {
        this.finishedTimeNanos = j;
    }

    public final void setLastFrameTimeNanos$animation_core_release(long j) {
        this.lastFrameTimeNanos = j;
    }

    public final void setRunning$animation_core_release() {
        this.isRunning$delegate.setValue(Boolean.FALSE);
    }

    public final void setValue$animation_core_release(Object obj) {
        this.value$delegate.setValue(obj);
    }

    public final void setVelocityVector$animation_core_release(AnimationVector animationVector) {
        Intrinsics.checkNotNullParameter(animationVector, "<set-?>");
        this.velocityVector = animationVector;
    }
}
