package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TargetBasedAnimation implements Animation {
    private final VectorizedFiniteAnimationSpec animationSpec;
    private final long durationNanos;
    private final AnimationVector endVelocity;
    private final Object initialValue;
    private final AnimationVector initialValueVector;
    private final AnimationVector initialVelocityVector;
    private final Object targetValue;
    private final AnimationVector targetValueVector;
    private final TwoWayConverter typeConverter;

    public TargetBasedAnimation(AnimationSpec animationSpec, TwoWayConverter typeConverter, Object obj, Object obj2, AnimationVector animationVector) {
        AnimationVector newInstance;
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        VectorizedFiniteAnimationSpec animationSpec2 = animationSpec.vectorize(typeConverter);
        Intrinsics.checkNotNullParameter(animationSpec2, "animationSpec");
        this.animationSpec = animationSpec2;
        this.typeConverter = typeConverter;
        this.initialValue = obj;
        this.targetValue = obj2;
        TwoWayConverterImpl twoWayConverterImpl = (TwoWayConverterImpl) typeConverter;
        AnimationVector animationVector2 = (AnimationVector) twoWayConverterImpl.getConvertToVector().invoke(obj);
        this.initialValueVector = animationVector2;
        AnimationVector animationVector3 = (AnimationVector) twoWayConverterImpl.getConvertToVector().invoke(obj2);
        this.targetValueVector = animationVector3;
        if (animationVector != null) {
            newInstance = AnimationVectorsKt.copy(animationVector);
        } else {
            newInstance = AnimationVectorsKt.newInstance((AnimationVector) twoWayConverterImpl.getConvertToVector().invoke(obj));
        }
        this.initialVelocityVector = newInstance;
        this.durationNanos = animationSpec2.getDurationNanos(animationVector2, animationVector3, newInstance);
        this.endVelocity = animationSpec2.getEndVelocity(animationVector2, animationVector3, newInstance);
    }

    public final long getDurationNanos() {
        return this.durationNanos;
    }

    public final Object getInitialValue() {
        return this.initialValue;
    }

    public final Object getTargetValue() {
        return this.targetValue;
    }

    public final TwoWayConverter getTypeConverter() {
        return this.typeConverter;
    }

    public final Object getValueFromNanos(long j) {
        if (!isFinishedFromNanos(j)) {
            AnimationVector valueFromNanos = this.animationSpec.getValueFromNanos(j, this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
            int size$animation_core_release = valueFromNanos.getSize$animation_core_release();
            for (int i = 0; i < size$animation_core_release; i++) {
                if (!(!Float.isNaN(valueFromNanos.get$animation_core_release(i)))) {
                    throw new IllegalStateException(("AnimationVector cannot contain a NaN. " + valueFromNanos + ". Animation: " + this + ", playTimeNanos: " + j).toString());
                }
            }
            return ((TwoWayConverterImpl) this.typeConverter).getConvertFromVector().invoke(valueFromNanos);
        }
        return this.targetValue;
    }

    public final AnimationVector getVelocityVectorFromNanos(long j) {
        if (!isFinishedFromNanos(j)) {
            return this.animationSpec.getVelocityFromNanos(j, this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
        }
        return this.endVelocity;
    }

    public final boolean isInfinite() {
        this.animationSpec.isInfinite();
        return false;
    }

    public final String toString() {
        return "TargetBasedAnimation: " + this.initialValue + " -> " + this.targetValue + ",initial velocity: " + this.initialVelocityVector + ", duration: " + (getDurationNanos() / 1000000) + " ms,animationSpec: " + this.animationSpec;
    }
}
