package com.android.egg.landroid;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MathsKt {
    public static final float invsmoothish(float f) {
        return (f * 0.5f) + ((((float) Math.pow((2.0f * f) - 1.0f, 5.0f)) + 1.0f) * 0.25f);
    }

    public static final float lexp(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    public static final float smooth(float f) {
        return ((((6 * f) - 15) * f) + 10) * f * f * f;
    }
}
