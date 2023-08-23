package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface VectorizedDurationBasedAnimationSpec extends VectorizedFiniteAnimationSpec {
    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    default long getDurationNanos(AnimationVector initialValue, AnimationVector targetValue, AnimationVector animationVector) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        VectorizedTweenSpec vectorizedTweenSpec = (VectorizedTweenSpec) this;
        return (vectorizedTweenSpec.getDurationMillis() + vectorizedTweenSpec.getDelayMillis()) * 1000000;
    }
}
