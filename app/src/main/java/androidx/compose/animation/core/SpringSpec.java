package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SpringSpec implements FiniteAnimationSpec {
    private final float dampingRatio;
    private final float stiffness;
    private final Object visibilityThreshold;

    public SpringSpec(float f, float f2, Object obj) {
        this.dampingRatio = f;
        this.stiffness = f2;
        this.visibilityThreshold = obj;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (!(obj instanceof SpringSpec)) {
            return false;
        }
        SpringSpec springSpec = (SpringSpec) obj;
        if (springSpec.dampingRatio == this.dampingRatio) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        if (springSpec.stiffness == this.stiffness) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2 || !Intrinsics.areEqual(springSpec.visibilityThreshold, this.visibilityThreshold)) {
            return false;
        }
        return true;
    }

    public final float getDampingRatio() {
        return this.dampingRatio;
    }

    public final float getStiffness() {
        return this.stiffness;
    }

    public final Object getVisibilityThreshold() {
        return this.visibilityThreshold;
    }

    public final int hashCode() {
        int i;
        Object obj = this.visibilityThreshold;
        if (obj != null) {
            i = obj.hashCode();
        } else {
            i = 0;
        }
        return Float.hashCode(this.stiffness) + AnimationVector4D$$ExternalSyntheticOutline0.m(this.dampingRatio, i * 31, 31);
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public final VectorizedFiniteAnimationSpec vectorize(TwoWayConverter converter) {
        AnimationVector animationVector;
        Intrinsics.checkNotNullParameter(converter, "converter");
        Object obj = this.visibilityThreshold;
        if (obj == null) {
            animationVector = null;
        } else {
            animationVector = (AnimationVector) ((TwoWayConverterImpl) converter).getConvertToVector().invoke(obj);
        }
        return new VectorizedSpringSpec(this.dampingRatio, this.stiffness, animationVector);
    }
}
