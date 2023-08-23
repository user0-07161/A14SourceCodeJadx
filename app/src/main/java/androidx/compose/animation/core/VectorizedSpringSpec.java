package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class VectorizedSpringSpec implements VectorizedFiniteAnimationSpec {
    private final /* synthetic */ VectorizedFloatAnimationSpec $$delegate_0;

    public VectorizedSpringSpec(float f, float f2, AnimationVector animationVector) {
        VectorizedAnimationSpecKt$createSpringAnimations$1 vectorizedAnimationSpecKt$createSpringAnimations$1;
        if (animationVector != null) {
            vectorizedAnimationSpecKt$createSpringAnimations$1 = new VectorizedAnimationSpecKt$createSpringAnimations$1(f, f2, animationVector);
        } else {
            vectorizedAnimationSpecKt$createSpringAnimations$1 = new VectorizedAnimationSpecKt$createSpringAnimations$1(f, f2);
        }
        this.$$delegate_0 = new VectorizedFloatAnimationSpec(vectorizedAnimationSpecKt$createSpringAnimations$1);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final long getDurationNanos(AnimationVector initialValue, AnimationVector targetValue, AnimationVector animationVector) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        return this.$$delegate_0.getDurationNanos(initialValue, targetValue, animationVector);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getEndVelocity(AnimationVector initialValue, AnimationVector targetValue, AnimationVector animationVector) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        return this.$$delegate_0.getEndVelocity(initialValue, targetValue, animationVector);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getValueFromNanos(long j, AnimationVector initialValue, AnimationVector targetValue, AnimationVector initialVelocity) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        Intrinsics.checkNotNullParameter(initialVelocity, "initialVelocity");
        return this.$$delegate_0.getValueFromNanos(j, initialValue, targetValue, initialVelocity);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getVelocityFromNanos(long j, AnimationVector initialValue, AnimationVector targetValue, AnimationVector initialVelocity) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        Intrinsics.checkNotNullParameter(initialVelocity, "initialVelocity");
        return this.$$delegate_0.getVelocityFromNanos(j, initialValue, targetValue, initialVelocity);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final void isInfinite() {
        this.$$delegate_0.getClass();
    }
}
