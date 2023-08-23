package androidx.compose.ui.graphics.colorspace;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class Connector {
    private final float[] transform;
    private final ColorSpace transformDestination;
    private final ColorSpace transformSource;
    public static final Companion Companion = new Companion();
    private static final Connector$Companion$identity$1 SrgbIdentity = Companion.identity$ui_graphics_release(ColorSpaces.getSrgb());
    private static final Connector SrgbToOklabPerceptual = new Connector(ColorSpaces.getSrgb(), ColorSpaces.getOklab(), 0);
    private static final Connector OklabToSrgbPerceptual = new Connector(ColorSpaces.getOklab(), ColorSpaces.getSrgb(), 0);

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1] */
        public static Connector$Companion$identity$1 identity$ui_graphics_release(ColorSpace source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new Connector(source) { // from class: androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(source, source, 1);
                }

                @Override // androidx.compose.ui.graphics.colorspace.Connector
                public final void transform(float[] fArr) {
                }
            };
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class RgbConnector extends Connector {
        private final Rgb mDestination;
        private final Rgb mSource;
        private final float[] mTransform;

        public RgbConnector(Rgb rgb, Rgb rgb2, int i) {
            super(rgb, rgb2, (float[]) null);
            boolean z;
            float[] mul3x3;
            Adaptation$Companion$Bradford$1 adaptation$Companion$Bradford$1;
            float[] fArr;
            Adaptation$Companion$Bradford$1 adaptation$Companion$Bradford$12;
            float[] fArr2;
            this.mSource = rgb;
            this.mDestination = rgb2;
            if (ColorSpaceKt.compare(rgb.getWhitePoint(), rgb2.getWhitePoint())) {
                mul3x3 = ColorSpaceKt.mul3x3(rgb2.getInverseTransform$ui_graphics_release(), rgb.getTransform$ui_graphics_release());
            } else {
                float[] transform$ui_graphics_release = rgb.getTransform$ui_graphics_release();
                float[] inverseTransform$ui_graphics_release = rgb2.getInverseTransform$ui_graphics_release();
                float[] xyz$ui_graphics_release = rgb.getWhitePoint().toXyz$ui_graphics_release();
                float[] xyz$ui_graphics_release2 = rgb2.getWhitePoint().toXyz$ui_graphics_release();
                if (!ColorSpaceKt.compare(rgb.getWhitePoint(), Illuminant.getD50())) {
                    adaptation$Companion$Bradford$12 = Adaptation.Bradford;
                    float[] transform$ui_graphics_release2 = adaptation$Companion$Bradford$12.getTransform$ui_graphics_release();
                    fArr2 = Illuminant.D50Xyz;
                    float[] copyOf = Arrays.copyOf(fArr2, 3);
                    Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
                    transform$ui_graphics_release = ColorSpaceKt.mul3x3(ColorSpaceKt.chromaticAdaptation(transform$ui_graphics_release2, xyz$ui_graphics_release, copyOf), rgb.getTransform$ui_graphics_release());
                }
                if (!ColorSpaceKt.compare(rgb2.getWhitePoint(), Illuminant.getD50())) {
                    adaptation$Companion$Bradford$1 = Adaptation.Bradford;
                    float[] transform$ui_graphics_release3 = adaptation$Companion$Bradford$1.getTransform$ui_graphics_release();
                    fArr = Illuminant.D50Xyz;
                    float[] copyOf2 = Arrays.copyOf(fArr, 3);
                    Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
                    inverseTransform$ui_graphics_release = ColorSpaceKt.inverse3x3(ColorSpaceKt.mul3x3(ColorSpaceKt.chromaticAdaptation(transform$ui_graphics_release3, xyz$ui_graphics_release2, copyOf2), rgb2.getTransform$ui_graphics_release()));
                }
                if (i == 3) {
                    z = true;
                } else {
                    z = false;
                }
                mul3x3 = ColorSpaceKt.mul3x3(inverseTransform$ui_graphics_release, z ? ColorSpaceKt.mul3x3Diag(new float[]{xyz$ui_graphics_release[0] / xyz$ui_graphics_release2[0], xyz$ui_graphics_release[1] / xyz$ui_graphics_release2[1], xyz$ui_graphics_release[2] / xyz$ui_graphics_release2[2]}, transform$ui_graphics_release) : transform$ui_graphics_release);
            }
            this.mTransform = mul3x3;
        }

        @Override // androidx.compose.ui.graphics.colorspace.Connector
        public final void transform(float[] fArr) {
            Rgb rgb = this.mSource;
            fArr[0] = (float) rgb.getEotfFunc$ui_graphics_release().invoke(fArr[0]);
            fArr[1] = (float) rgb.getEotfFunc$ui_graphics_release().invoke(fArr[1]);
            fArr[2] = (float) rgb.getEotfFunc$ui_graphics_release().invoke(fArr[2]);
            ColorSpaceKt.mul3x3Float3(this.mTransform, fArr);
            Rgb rgb2 = this.mDestination;
            fArr[0] = (float) rgb2.getOetfFunc$ui_graphics_release().invoke(fArr[0]);
            fArr[1] = (float) rgb2.getOetfFunc$ui_graphics_release().invoke(fArr[1]);
            fArr[2] = (float) rgb2.getOetfFunc$ui_graphics_release().invoke(fArr[2]);
        }
    }

    public Connector(ColorSpace colorSpace, ColorSpace colorSpace2, float[] fArr) {
        this.transformSource = colorSpace;
        this.transformDestination = colorSpace2;
        this.transform = fArr;
    }

    public void transform(float[] fArr) {
        float[] xyz = this.transformSource.toXyz(fArr);
        float[] fArr2 = this.transform;
        if (fArr2 != null) {
            xyz[0] = xyz[0] * fArr2[0];
            xyz[1] = xyz[1] * fArr2[1];
            xyz[2] = xyz[2] * fArr2[2];
        }
        this.transformDestination.fromXyz(xyz);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Connector(androidx.compose.ui.graphics.colorspace.ColorSpace r10, androidx.compose.ui.graphics.colorspace.ColorSpace r11, int r12) {
        /*
            r9 = this;
            long r0 = r10.m130getModelxdoWZVw()
            long r2 = androidx.compose.ui.graphics.colorspace.ColorModel.access$getRgb$cp()
            boolean r0 = androidx.compose.ui.graphics.colorspace.ColorModel.m128equalsimpl0(r0, r2)
            if (r0 == 0) goto L17
            androidx.compose.ui.graphics.colorspace.WhitePoint r0 = androidx.compose.ui.graphics.colorspace.Illuminant.getD50()
            androidx.compose.ui.graphics.colorspace.ColorSpace r0 = androidx.compose.ui.graphics.colorspace.ColorSpaceKt.adapt$default(r10, r0)
            goto L18
        L17:
            r0 = r10
        L18:
            long r1 = r11.m130getModelxdoWZVw()
            long r3 = androidx.compose.ui.graphics.colorspace.ColorModel.access$getRgb$cp()
            boolean r1 = androidx.compose.ui.graphics.colorspace.ColorModel.m128equalsimpl0(r1, r3)
            if (r1 == 0) goto L2f
            androidx.compose.ui.graphics.colorspace.WhitePoint r1 = androidx.compose.ui.graphics.colorspace.Illuminant.getD50()
            androidx.compose.ui.graphics.colorspace.ColorSpace r1 = androidx.compose.ui.graphics.colorspace.ColorSpaceKt.adapt$default(r11, r1)
            goto L30
        L2f:
            r1 = r11
        L30:
            r2 = 1
            r3 = 0
            r4 = 3
            if (r12 != r4) goto L37
            r12 = r2
            goto L38
        L37:
            r12 = r3
        L38:
            if (r12 != 0) goto L3b
            goto L5d
        L3b:
            long r5 = r10.m130getModelxdoWZVw()
            long r7 = androidx.compose.ui.graphics.colorspace.ColorModel.access$getRgb$cp()
            boolean r12 = androidx.compose.ui.graphics.colorspace.ColorModel.m128equalsimpl0(r5, r7)
            long r5 = r11.m130getModelxdoWZVw()
            long r7 = androidx.compose.ui.graphics.colorspace.ColorModel.access$getRgb$cp()
            boolean r5 = androidx.compose.ui.graphics.colorspace.ColorModel.m128equalsimpl0(r5, r7)
            if (r12 == 0) goto L58
            if (r5 == 0) goto L58
            goto L5d
        L58:
            if (r12 != 0) goto L5f
            if (r5 == 0) goto L5d
            goto L5f
        L5d:
            r10 = 0
            goto L9c
        L5f:
            if (r12 == 0) goto L62
            goto L63
        L62:
            r10 = r11
        L63:
            androidx.compose.ui.graphics.colorspace.Rgb r10 = (androidx.compose.ui.graphics.colorspace.Rgb) r10
            if (r12 == 0) goto L70
            androidx.compose.ui.graphics.colorspace.WhitePoint r11 = r10.getWhitePoint()
            float[] r11 = r11.toXyz$ui_graphics_release()
            goto L74
        L70:
            float[] r11 = androidx.compose.ui.graphics.colorspace.Illuminant.getD50Xyz$ui_graphics_release()
        L74:
            if (r5 == 0) goto L7f
            androidx.compose.ui.graphics.colorspace.WhitePoint r10 = r10.getWhitePoint()
            float[] r10 = r10.toXyz$ui_graphics_release()
            goto L83
        L7f:
            float[] r10 = androidx.compose.ui.graphics.colorspace.Illuminant.getD50Xyz$ui_graphics_release()
        L83:
            float[] r12 = new float[r4]
            r4 = r11[r3]
            r5 = r10[r3]
            float r4 = r4 / r5
            r12[r3] = r4
            r3 = r11[r2]
            r4 = r10[r2]
            float r3 = r3 / r4
            r12[r2] = r3
            r2 = 2
            r11 = r11[r2]
            r10 = r10[r2]
            float r11 = r11 / r10
            r12[r2] = r11
            r10 = r12
        L9c:
            r9.<init>(r0, r1, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Connector.<init>(androidx.compose.ui.graphics.colorspace.ColorSpace, androidx.compose.ui.graphics.colorspace.ColorSpace, int):void");
    }
}
