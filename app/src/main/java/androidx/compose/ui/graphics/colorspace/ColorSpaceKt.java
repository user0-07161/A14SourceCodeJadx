package androidx.compose.ui.graphics.colorspace;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ColorSpaceKt {
    public static ColorSpace adapt$default(ColorSpace colorSpace, WhitePoint whitePoint) {
        Adaptation$Companion$Bradford$1 adaptation$Companion$Bradford$1;
        long j;
        adaptation$Companion$Bradford$1 = Adaptation.Bradford;
        Intrinsics.checkNotNullParameter(colorSpace, "<this>");
        long m130getModelxdoWZVw = colorSpace.m130getModelxdoWZVw();
        j = ColorModel.Rgb;
        if (ColorModel.m128equalsimpl0(m130getModelxdoWZVw, j)) {
            Rgb rgb = (Rgb) colorSpace;
            if (!compare(rgb.getWhitePoint(), whitePoint)) {
                return new Rgb(rgb, mul3x3(chromaticAdaptation(adaptation$Companion$Bradford$1.getTransform$ui_graphics_release(), rgb.getWhitePoint().toXyz$ui_graphics_release(), whitePoint.toXyz$ui_graphics_release()), rgb.getTransform$ui_graphics_release()), whitePoint);
            }
            return colorSpace;
        }
        return colorSpace;
    }

    public static final float[] chromaticAdaptation(float[] matrix, float[] fArr, float[] fArr2) {
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        mul3x3Float3(matrix, fArr);
        mul3x3Float3(matrix, fArr2);
        return mul3x3(inverse3x3(matrix), mul3x3Diag(new float[]{fArr2[0] / fArr[0], fArr2[1] / fArr[1], fArr2[2] / fArr[2]}, matrix));
    }

    public static final boolean compare(WhitePoint a, WhitePoint b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        if (a == b) {
            return true;
        }
        if (Math.abs(a.getX() - b.getX()) < 0.001f && Math.abs(a.getY() - b.getY()) < 0.001f) {
            return true;
        }
        return false;
    }

    public static final float[] inverse3x3(float[] m) {
        Intrinsics.checkNotNullParameter(m, "m");
        float f = m[0];
        float f2 = m[3];
        float f3 = m[6];
        float f4 = m[1];
        float f5 = m[4];
        float f6 = m[7];
        float f7 = m[2];
        float f8 = m[5];
        float f9 = m[8];
        float f10 = (f5 * f9) - (f6 * f8);
        float f11 = (f6 * f7) - (f4 * f9);
        float f12 = (f4 * f8) - (f5 * f7);
        float f13 = (f3 * f12) + (f2 * f11) + (f * f10);
        float[] fArr = new float[m.length];
        fArr[0] = f10 / f13;
        fArr[1] = f11 / f13;
        fArr[2] = f12 / f13;
        fArr[3] = ((f3 * f8) - (f2 * f9)) / f13;
        fArr[4] = ((f9 * f) - (f3 * f7)) / f13;
        fArr[5] = ((f7 * f2) - (f8 * f)) / f13;
        fArr[6] = ((f2 * f6) - (f3 * f5)) / f13;
        fArr[7] = ((f3 * f4) - (f6 * f)) / f13;
        fArr[8] = ((f * f5) - (f2 * f4)) / f13;
        return fArr;
    }

    public static final float[] mul3x3(float[] lhs, float[] rhs) {
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        float f = lhs[3];
        float f2 = rhs[1];
        float f3 = f * f2;
        float f4 = lhs[6];
        float f5 = rhs[2];
        float f6 = f4 * f5;
        float f7 = lhs[1];
        float f8 = rhs[0];
        float f9 = lhs[4];
        float f10 = f2 * f9;
        float f11 = lhs[7];
        float f12 = f11 * f5;
        float f13 = lhs[5];
        float f14 = rhs[1] * f13;
        float f15 = lhs[8];
        float f16 = f5 * f15;
        float f17 = lhs[0];
        float f18 = rhs[4];
        float f19 = (f * f18) + (rhs[3] * f17);
        float f20 = rhs[5];
        float f21 = lhs[1];
        float f22 = rhs[3];
        float f23 = f9 * f18;
        float f24 = lhs[2];
        float f25 = f13 * rhs[4];
        float f26 = f17 * rhs[6];
        float f27 = lhs[3];
        float f28 = rhs[7];
        float f29 = (f27 * f28) + f26;
        float f30 = rhs[8];
        float f31 = rhs[6];
        return new float[]{f6 + f3 + (lhs[0] * rhs[0]), f12 + f10 + (f7 * f8), f16 + f14 + (lhs[2] * f8), (f4 * f20) + f19, (f11 * f20) + f23 + (f21 * f22), (f20 * f15) + f25 + (f22 * f24), (f4 * f30) + f29, (f11 * f30) + (lhs[4] * f28) + (f21 * f31), (f15 * f30) + (lhs[5] * rhs[7]) + (f24 * f31)};
    }

    public static final float[] mul3x3Diag(float[] fArr, float[] rhs) {
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        return new float[]{fArr[0] * rhs[0], fArr[1] * rhs[1], fArr[2] * rhs[2], rhs[3] * f, rhs[4] * f2, rhs[5] * f3, f * rhs[6], f2 * rhs[7], f3 * rhs[8]};
    }

    public static final void mul3x3Float3(float[] lhs, float[] rhs) {
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        float f = rhs[0];
        float f2 = rhs[1];
        float f3 = rhs[2];
        rhs[0] = (lhs[6] * f3) + (lhs[3] * f2) + (lhs[0] * f);
        rhs[1] = (lhs[7] * f3) + (lhs[4] * f2) + (lhs[1] * f);
        float f4 = lhs[2] * f;
        rhs[2] = (lhs[8] * f3) + (lhs[5] * f2) + f4;
    }
}
