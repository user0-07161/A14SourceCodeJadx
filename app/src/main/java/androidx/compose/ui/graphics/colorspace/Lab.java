package androidx.compose.ui.graphics.colorspace;

import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Lab extends ColorSpace {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Lab() {
        /*
            r4 = this;
            r0 = 15
            long r1 = androidx.compose.ui.graphics.colorspace.ColorModel.access$getLab$cp()
            java.lang.String r3 = "Generic L*a*b*"
            r4.<init>(r3, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Lab.<init>():void");
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float[] fromXyz(float[] v) {
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        float f;
        float f2;
        float f3;
        Intrinsics.checkNotNullParameter(v, "v");
        float f4 = v[0];
        fArr = Illuminant.D50Xyz;
        float f5 = f4 / fArr[0];
        float f6 = v[1];
        fArr2 = Illuminant.D50Xyz;
        float f7 = f6 / fArr2[1];
        float f8 = v[2];
        fArr3 = Illuminant.D50Xyz;
        float f9 = f8 / fArr3[2];
        if (f5 > 0.008856452f) {
            f = (float) Math.pow(f5, 0.33333334f);
        } else {
            f = (f5 * 7.787037f) + 0.13793103f;
        }
        if (f7 > 0.008856452f) {
            f2 = (float) Math.pow(f7, 0.33333334f);
        } else {
            f2 = (f7 * 7.787037f) + 0.13793103f;
        }
        if (f9 > 0.008856452f) {
            f3 = (float) Math.pow(f9, 0.33333334f);
        } else {
            f3 = (f9 * 7.787037f) + 0.13793103f;
        }
        v[0] = RangesKt.coerceIn((116.0f * f2) - 16.0f, 0.0f, 100.0f);
        v[1] = RangesKt.coerceIn((f - f2) * 500.0f, -128.0f, 128.0f);
        v[2] = RangesKt.coerceIn((f2 - f3) * 200.0f, -128.0f, 128.0f);
        return v;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMaxValue(int i) {
        if (i == 0) {
            return 100.0f;
        }
        return 128.0f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMinValue(int i) {
        if (i == 0) {
            return 0.0f;
        }
        return -128.0f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float[] toXyz(float[] fArr) {
        float f;
        float f2;
        float f3;
        float[] fArr2;
        float[] fArr3;
        float[] fArr4;
        fArr[0] = RangesKt.coerceIn(fArr[0], 0.0f, 100.0f);
        fArr[1] = RangesKt.coerceIn(fArr[1], -128.0f, 128.0f);
        float coerceIn = RangesKt.coerceIn(fArr[2], -128.0f, 128.0f);
        fArr[2] = coerceIn;
        float f4 = (fArr[0] + 16.0f) / 116.0f;
        float f5 = (fArr[1] * 0.002f) + f4;
        float f6 = f4 - (coerceIn * 0.005f);
        if (f5 > 0.20689656f) {
            f = f5 * f5 * f5;
        } else {
            f = (f5 - 0.13793103f) * 0.12841855f;
        }
        if (f4 > 0.20689656f) {
            f2 = f4 * f4 * f4;
        } else {
            f2 = (f4 - 0.13793103f) * 0.12841855f;
        }
        if (f6 > 0.20689656f) {
            f3 = f6 * f6 * f6;
        } else {
            f3 = (f6 - 0.13793103f) * 0.12841855f;
        }
        fArr2 = Illuminant.D50Xyz;
        fArr[0] = f * fArr2[0];
        fArr3 = Illuminant.D50Xyz;
        fArr[1] = f2 * fArr3[1];
        fArr4 = Illuminant.D50Xyz;
        fArr[2] = f3 * fArr4[2];
        return fArr;
    }
}
