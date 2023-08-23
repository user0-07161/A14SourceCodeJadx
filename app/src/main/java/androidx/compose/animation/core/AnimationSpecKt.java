package androidx.compose.animation.core;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AnimationSpecKt {
    public static SpringSpec spring$default(float f, Object obj, int i) {
        float f2;
        if ((i & 1) != 0) {
            f2 = 1.0f;
        } else {
            f2 = 0.0f;
        }
        if ((i & 2) != 0) {
            f = 1500.0f;
        }
        if ((i & 4) != 0) {
            obj = null;
        }
        return new SpringSpec(f2, f, obj);
    }

    public static TweenSpec tween$default(Easing easing) {
        return new TweenSpec(1000, 0, easing);
    }
}
