package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AnimationVectorsKt {
    public static final AnimationVector copy(AnimationVector animationVector) {
        Intrinsics.checkNotNullParameter(animationVector, "<this>");
        AnimationVector newInstance = newInstance(animationVector);
        int size$animation_core_release = newInstance.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            newInstance.set$animation_core_release(animationVector.get$animation_core_release(i), i);
        }
        return newInstance;
    }

    public static final AnimationVector newInstance(AnimationVector animationVector) {
        Intrinsics.checkNotNullParameter(animationVector, "<this>");
        AnimationVector newVector$animation_core_release = animationVector.newVector$animation_core_release();
        Intrinsics.checkNotNull(newVector$animation_core_release, "null cannot be cast to non-null type T of androidx.compose.animation.core.AnimationVectorsKt.newInstance");
        return newVector$animation_core_release;
    }
}
