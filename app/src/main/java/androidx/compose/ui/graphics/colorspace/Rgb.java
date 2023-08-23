package androidx.compose.ui.graphics.colorspace;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Rgb extends ColorSpace {
    public static final Companion Companion = new Companion();
    private static final Rgb$$ExternalSyntheticLambda1 DoubleIdentity = new Rgb$$ExternalSyntheticLambda1();
    private final Rgb$$ExternalSyntheticLambda0 eotfFunc;
    private final DoubleFunction eotfOrig;
    private final float[] inverseTransform;
    private final boolean isSrgb;
    private final float max;
    private final float min;
    private final Rgb$$ExternalSyntheticLambda0 oetfFunc;
    private final DoubleFunction oetfOrig;
    private final float[] primaries;
    private final TransferParameters transferParameters;
    private final float[] transform;
    private final WhitePoint whitePoint;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        public static final boolean access$isSrgb(float[] fArr, WhitePoint whitePoint, DoubleFunction doubleFunction, DoubleFunction doubleFunction2, float f, float f2, int i) {
            float[] fArr2;
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            boolean z5;
            if (i == 0) {
                return true;
            }
            fArr2 = ColorSpaces.SrgbPrimaries;
            if (fArr != fArr2) {
                int length = fArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    if (Float.compare(fArr[i2], fArr2[i2]) != 0 && Math.abs(fArr[i2] - fArr2[i2]) > 0.001f) {
                        z = false;
                        break;
                    }
                }
            }
            z = true;
            if (z && ColorSpaceKt.compare(whitePoint, Illuminant.getD65())) {
                if (f == 0.0f) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    if (f2 == 1.0f) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        Rgb srgb = ColorSpaces.getSrgb();
                        for (double d = 0.0d; d <= 1.0d; d += 0.00392156862745098d) {
                            if (Math.abs(doubleFunction.invoke(d) - srgb.getOetfOrig$ui_graphics_release().invoke(d)) <= 0.001d) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            if (z4) {
                                if (Math.abs(doubleFunction2.invoke(d) - srgb.getEotfOrig$ui_graphics_release().invoke(d)) <= 0.001d) {
                                    z5 = true;
                                } else {
                                    z5 = false;
                                }
                                if (z5) {
                                }
                            }
                        }
                        return true;
                    }
                }
            }
            return false;
        }

        public static final void access$isWideGamut(float[] fArr, float f, float f2) {
            float[] fArr2;
            float[] unused;
            float area = area(fArr);
            fArr2 = ColorSpaces.Ntsc1953Primaries;
            if (area / area(fArr2) > 0.9f) {
                unused = ColorSpaces.SrgbPrimaries;
                float f3 = fArr[0];
                float f4 = fArr[1];
                float f5 = fArr[2];
                float f6 = fArr[3];
                float f7 = fArr[4];
                float f8 = fArr[5];
            }
        }

        private static float area(float[] fArr) {
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[2];
            float f4 = fArr[3];
            float f5 = fArr[4];
            float f6 = fArr[5];
            float f7 = (((((f3 * f6) + ((f2 * f5) + (f * f4))) - (f4 * f5)) - (f2 * f3)) - (f * f6)) * 0.5f;
            if (f7 < 0.0f) {
                return -f7;
            }
            return f7;
        }
    }

    public static double $r8$lambda$FANKyyW7TMwi4gnihl1LqxbkU6k(Rgb this$0, double d) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return RangesKt.coerceIn(this$0.oetfOrig.invoke(d), this$0.min, this$0.max);
    }

    public static double $r8$lambda$OfmTeMXzL_nayJmS5mO6N4G4DlI(Rgb this$0, double d) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.eotfOrig.invoke(RangesKt.coerceIn(d, this$0.min, this$0.max));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Rgb(java.lang.String r33, float[] r34, androidx.compose.ui.graphics.colorspace.WhitePoint r35, float[] r36, androidx.compose.ui.graphics.colorspace.DoubleFunction r37, androidx.compose.ui.graphics.colorspace.DoubleFunction r38, float r39, float r40, androidx.compose.ui.graphics.colorspace.TransferParameters r41, int r42) {
        /*
            Method dump skipped, instructions count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Rgb.<init>(java.lang.String, float[], androidx.compose.ui.graphics.colorspace.WhitePoint, float[], androidx.compose.ui.graphics.colorspace.DoubleFunction, androidx.compose.ui.graphics.colorspace.DoubleFunction, float, float, androidx.compose.ui.graphics.colorspace.TransferParameters, int):void");
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Rgb.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        Rgb rgb = (Rgb) obj;
        if (Float.compare(rgb.min, this.min) != 0 || Float.compare(rgb.max, this.max) != 0 || !Intrinsics.areEqual(this.whitePoint, rgb.whitePoint) || !Arrays.equals(this.primaries, rgb.primaries)) {
            return false;
        }
        TransferParameters transferParameters = rgb.transferParameters;
        TransferParameters transferParameters2 = this.transferParameters;
        if (transferParameters2 != null) {
            return Intrinsics.areEqual(transferParameters2, transferParameters);
        }
        if (transferParameters == null) {
            return true;
        }
        if (!Intrinsics.areEqual(this.oetfOrig, rgb.oetfOrig)) {
            return false;
        }
        return Intrinsics.areEqual(this.eotfOrig, rgb.eotfOrig);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float[] fromXyz(float[] v) {
        Intrinsics.checkNotNullParameter(v, "v");
        ColorSpaceKt.mul3x3Float3(this.inverseTransform, v);
        Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = this.oetfFunc;
        v[0] = (float) rgb$$ExternalSyntheticLambda0.invoke(v[0]);
        v[1] = (float) rgb$$ExternalSyntheticLambda0.invoke(v[1]);
        v[2] = (float) rgb$$ExternalSyntheticLambda0.invoke(v[2]);
        return v;
    }

    public final Rgb$$ExternalSyntheticLambda0 getEotfFunc$ui_graphics_release() {
        return this.eotfFunc;
    }

    public final DoubleFunction getEotfOrig$ui_graphics_release() {
        return this.eotfOrig;
    }

    public final float[] getInverseTransform$ui_graphics_release() {
        return this.inverseTransform;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMaxValue(int i) {
        return this.max;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMinValue(int i) {
        return this.min;
    }

    public final Rgb$$ExternalSyntheticLambda0 getOetfFunc$ui_graphics_release() {
        return this.oetfFunc;
    }

    public final DoubleFunction getOetfOrig$ui_graphics_release() {
        return this.oetfOrig;
    }

    public final float[] getTransform$ui_graphics_release() {
        return this.transform;
    }

    public final WhitePoint getWhitePoint() {
        return this.whitePoint;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final int hashCode() {
        boolean z;
        int i;
        int i2;
        int hashCode = (Arrays.hashCode(this.primaries) + ((this.whitePoint.hashCode() + (super.hashCode() * 31)) * 31)) * 31;
        float f = this.min;
        boolean z2 = true;
        int i3 = 0;
        if (f == 0.0f) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            i = Float.floatToIntBits(f);
        } else {
            i = 0;
        }
        int i4 = (hashCode + i) * 31;
        float f2 = this.max;
        if (f2 != 0.0f) {
            z2 = false;
        }
        if (!z2) {
            i2 = Float.floatToIntBits(f2);
        } else {
            i2 = 0;
        }
        int i5 = (i4 + i2) * 31;
        TransferParameters transferParameters = this.transferParameters;
        if (transferParameters != null) {
            i3 = transferParameters.hashCode();
        }
        int i6 = i5 + i3;
        if (transferParameters == null) {
            return this.eotfOrig.hashCode() + ((this.oetfOrig.hashCode() + (i6 * 31)) * 31);
        }
        return i6;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final boolean isSrgb() {
        return this.isSrgb;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float[] toXyz(float[] fArr) {
        Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = this.eotfFunc;
        fArr[0] = (float) rgb$$ExternalSyntheticLambda0.invoke(fArr[0]);
        fArr[1] = (float) rgb$$ExternalSyntheticLambda0.invoke(fArr[1]);
        fArr[2] = (float) rgb$$ExternalSyntheticLambda0.invoke(fArr[2]);
        ColorSpaceKt.mul3x3Float3(this.transform, fArr);
        return fArr;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Rgb(java.lang.String r12, float[] r13, androidx.compose.ui.graphics.colorspace.WhitePoint r14, final androidx.compose.ui.graphics.colorspace.TransferParameters r15, int r16) {
        /*
            r11 = this;
            r9 = r15
            r4 = 0
            double r0 = r15.getE()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            r5 = 0
            if (r0 != 0) goto L10
            r0 = r1
            goto L11
        L10:
            r0 = r5
        L11:
            if (r0 == 0) goto L26
            double r6 = r15.getF()
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 != 0) goto L1d
            r0 = r1
            goto L1e
        L1d:
            r0 = r5
        L1e:
            if (r0 == 0) goto L26
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2
            r0.<init>()
            goto L2b
        L26:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2
            r0.<init>()
        L2b:
            r6 = r0
            double r7 = r15.getE()
            int r0 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r0 != 0) goto L36
            r0 = r1
            goto L37
        L36:
            r0 = r5
        L37:
            if (r0 == 0) goto L4c
            double r7 = r15.getF()
            int r0 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r0 != 0) goto L42
            goto L43
        L42:
            r1 = r5
        L43:
            if (r1 == 0) goto L4c
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2
            r1 = 2
            r0.<init>()
            goto L52
        L4c:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2
            r1 = 3
            r0.<init>()
        L52:
            r7 = r0
            r8 = 0
            r10 = 1065353216(0x3f800000, float:1.0)
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r10
            r9 = r15
            r10 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Rgb.<init>(java.lang.String, float[], androidx.compose.ui.graphics.colorspace.WhitePoint, androidx.compose.ui.graphics.colorspace.TransferParameters, int):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda3] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Rgb(java.lang.String r16, float[] r17, androidx.compose.ui.graphics.colorspace.WhitePoint r18, final double r19, float r21, float r22, int r23) {
        /*
            r15 = this;
            r1 = r19
            r11 = 0
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r3 = 1
            r4 = 0
            if (r0 != 0) goto Ld
            r5 = r3
            goto Le
        Ld:
            r5 = r4
        Le:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda1 r6 = androidx.compose.ui.graphics.colorspace.Rgb.DoubleIdentity
            if (r5 == 0) goto L14
            r12 = r6
            goto L1a
        L14:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda3 r5 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda3
            r5.<init>()
            r12 = r5
        L1a:
            if (r0 != 0) goto L1d
            r4 = r3
        L1d:
            if (r4 == 0) goto L21
            r13 = r6
            goto L27
        L21:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda3 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda3
            r0.<init>()
            r13 = r0
        L27:
            androidx.compose.ui.graphics.colorspace.TransferParameters r14 = new androidx.compose.ui.graphics.colorspace.TransferParameters
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r5 = 0
            r7 = 0
            r9 = 0
            r0 = r14
            r1 = r19
            r0.<init>(r1, r3, r5, r7, r9)
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r11
            r5 = r12
            r6 = r13
            r7 = r21
            r8 = r22
            r9 = r14
            r10 = r23
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Rgb.<init>(java.lang.String, float[], androidx.compose.ui.graphics.colorspace.WhitePoint, double, float, float, int):void");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Rgb(Rgb colorSpace, float[] fArr, WhitePoint whitePoint) {
        this(colorSpace.getName(), colorSpace.primaries, whitePoint, fArr, colorSpace.oetfOrig, colorSpace.eotfOrig, colorSpace.min, colorSpace.max, colorSpace.transferParameters, -1);
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
    }
}
