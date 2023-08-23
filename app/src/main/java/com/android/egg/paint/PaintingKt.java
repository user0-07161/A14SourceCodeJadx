package com.android.egg.paint;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PaintingKt {
    public static final float hypot(float f, float f2) {
        return (float) Math.hypot(f, f2);
    }

    public static final float invlerp(float f, float f2, float f3) {
        if (f3 > f2) {
            return (f - f2) / (f3 - f2);
        }
        return 1.0f;
    }
}
