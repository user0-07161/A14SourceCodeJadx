package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class VectorizedFloatAnimationSpec implements VectorizedFiniteAnimationSpec {
    private final Animations anims;
    private AnimationVector endVelocityVector;
    private AnimationVector valueVector;
    private AnimationVector velocityVector;

    public VectorizedFloatAnimationSpec(Animations animations) {
        this.anims = animations;
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final long getDurationNanos(AnimationVector initialValue, AnimationVector targetValue, AnimationVector animationVector) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        IntProgressionIterator it = RangesKt.until(0, initialValue.getSize$animation_core_release()).iterator();
        long j = 0;
        while (it.hasNext()) {
            int nextInt = it.nextInt();
            j = Math.max(j, this.anims.get(nextInt).getDurationNanos(initialValue.get$animation_core_release(nextInt), targetValue.get$animation_core_release(nextInt), animationVector.get$animation_core_release(nextInt)));
        }
        return j;
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getEndVelocity(AnimationVector initialValue, AnimationVector targetValue, AnimationVector animationVector) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        if (this.endVelocityVector == null) {
            this.endVelocityVector = AnimationVectorsKt.newInstance(animationVector);
        }
        AnimationVector animationVector2 = this.endVelocityVector;
        if (animationVector2 != null) {
            int size$animation_core_release = animationVector2.getSize$animation_core_release();
            for (int i = 0; i < size$animation_core_release; i++) {
                AnimationVector animationVector3 = this.endVelocityVector;
                if (animationVector3 != null) {
                    animationVector3.set$animation_core_release(this.anims.get(i).getEndVelocity(initialValue.get$animation_core_release(i), targetValue.get$animation_core_release(i), animationVector.get$animation_core_release(i)), i);
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("endVelocityVector");
                    throw null;
                }
            }
            AnimationVector animationVector4 = this.endVelocityVector;
            if (animationVector4 != null) {
                return animationVector4;
            }
            Intrinsics.throwUninitializedPropertyAccessException("endVelocityVector");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("endVelocityVector");
        throw null;
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getValueFromNanos(long j, AnimationVector initialValue, AnimationVector targetValue, AnimationVector initialVelocity) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        Intrinsics.checkNotNullParameter(initialVelocity, "initialVelocity");
        if (this.valueVector == null) {
            this.valueVector = AnimationVectorsKt.newInstance(initialValue);
        }
        AnimationVector animationVector = this.valueVector;
        if (animationVector != null) {
            int size$animation_core_release = animationVector.getSize$animation_core_release();
            for (int i = 0; i < size$animation_core_release; i++) {
                AnimationVector animationVector2 = this.valueVector;
                if (animationVector2 != null) {
                    animationVector2.set$animation_core_release(this.anims.get(i).getValueFromNanos(j, initialValue.get$animation_core_release(i), targetValue.get$animation_core_release(i), initialVelocity.get$animation_core_release(i)), i);
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("valueVector");
                    throw null;
                }
            }
            AnimationVector animationVector3 = this.valueVector;
            if (animationVector3 != null) {
                return animationVector3;
            }
            Intrinsics.throwUninitializedPropertyAccessException("valueVector");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("valueVector");
        throw null;
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getVelocityFromNanos(long j, AnimationVector initialValue, AnimationVector targetValue, AnimationVector initialVelocity) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        Intrinsics.checkNotNullParameter(initialVelocity, "initialVelocity");
        if (this.velocityVector == null) {
            this.velocityVector = AnimationVectorsKt.newInstance(initialVelocity);
        }
        AnimationVector animationVector = this.velocityVector;
        if (animationVector != null) {
            int size$animation_core_release = animationVector.getSize$animation_core_release();
            for (int i = 0; i < size$animation_core_release; i++) {
                AnimationVector animationVector2 = this.velocityVector;
                if (animationVector2 != null) {
                    animationVector2.set$animation_core_release(this.anims.get(i).getVelocityFromNanos(j, initialValue.get$animation_core_release(i), targetValue.get$animation_core_release(i), initialVelocity.get$animation_core_release(i)), i);
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
                    throw null;
                }
            }
            AnimationVector animationVector3 = this.velocityVector;
            if (animationVector3 != null) {
                return animationVector3;
            }
            Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
        throw null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VectorizedFloatAnimationSpec(final FloatAnimationSpec anim) {
        this(new Animations() { // from class: androidx.compose.animation.core.VectorizedFloatAnimationSpec.1
            @Override // androidx.compose.animation.core.Animations
            public final FloatAnimationSpec get(int i) {
                return FloatAnimationSpec.this;
            }
        });
        Intrinsics.checkNotNullParameter(anim, "anim");
    }
}
