package androidx.compose.ui.graphics.colorspace;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ColorSpaces {
    private static final Lab CieLab;
    private static final Xyz CieXyz;
    private static final ColorSpace[] ColorSpacesArray;
    private static final float[] Ntsc1953Primaries;
    private static final Oklab Oklab;
    private static final Rgb Srgb;
    private static final float[] SrgbPrimaries;
    private static final Rgb Unspecified;

    static {
        float[] fArr = {0.64f, 0.33f, 0.3f, 0.6f, 0.15f, 0.06f};
        SrgbPrimaries = fArr;
        float[] fArr2 = {0.67f, 0.33f, 0.21f, 0.71f, 0.14f, 0.08f};
        Ntsc1953Primaries = fArr2;
        TransferParameters transferParameters = new TransferParameters(2.4d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d);
        TransferParameters transferParameters2 = new TransferParameters(2.2d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d);
        Rgb rgb = new Rgb("sRGB IEC61966-2.1", fArr, Illuminant.getD65(), transferParameters, 0);
        Srgb = rgb;
        Rgb rgb2 = new Rgb("sRGB IEC61966-2.1 (Linear)", fArr, Illuminant.getD65(), 1.0d, 0.0f, 1.0f, 1);
        Rgb rgb3 = new Rgb("scRGB-nl IEC 61966-2-2:2003", fArr, Illuminant.getD65(), null, new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.ColorSpaces$$ExternalSyntheticLambda0
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                double d2;
                double d3;
                double d4;
                double d5;
                switch (r1) {
                    case 0:
                        if (d < 0.0d) {
                            d4 = -d;
                        } else {
                            d4 = d;
                        }
                        if (d4 >= 0.0031308049535603718d) {
                            d5 = (Math.pow(d4, 0.4166666666666667d) - 0.05213270142180095d) / 0.9478672985781991d;
                        } else {
                            d5 = d4 / 0.07739938080495357d;
                        }
                        return Math.copySign(d5, d);
                    default:
                        if (d < 0.0d) {
                            d2 = -d;
                        } else {
                            d2 = d;
                        }
                        if (d2 >= 0.04045d) {
                            d3 = Math.pow((0.9478672985781991d * d2) + 0.05213270142180095d, 2.4d);
                        } else {
                            d3 = 0.07739938080495357d * d2;
                        }
                        return Math.copySign(d3, d);
                }
            }
        }, new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.ColorSpaces$$ExternalSyntheticLambda0
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                double d2;
                double d3;
                double d4;
                double d5;
                switch (r1) {
                    case 0:
                        if (d < 0.0d) {
                            d4 = -d;
                        } else {
                            d4 = d;
                        }
                        if (d4 >= 0.0031308049535603718d) {
                            d5 = (Math.pow(d4, 0.4166666666666667d) - 0.05213270142180095d) / 0.9478672985781991d;
                        } else {
                            d5 = d4 / 0.07739938080495357d;
                        }
                        return Math.copySign(d5, d);
                    default:
                        if (d < 0.0d) {
                            d2 = -d;
                        } else {
                            d2 = d;
                        }
                        if (d2 >= 0.04045d) {
                            d3 = Math.pow((0.9478672985781991d * d2) + 0.05213270142180095d, 2.4d);
                        } else {
                            d3 = 0.07739938080495357d * d2;
                        }
                        return Math.copySign(d3, d);
                }
            }
        }, -0.799f, 2.399f, transferParameters, 2);
        Rgb rgb4 = new Rgb("scRGB IEC 61966-2-2:2003", fArr, Illuminant.getD65(), 1.0d, -0.5f, 7.499f, 3);
        Rgb rgb5 = new Rgb("Rec. ITU-R BT.709-5", new float[]{0.64f, 0.33f, 0.3f, 0.6f, 0.15f, 0.06f}, Illuminant.getD65(), new TransferParameters(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d), 4);
        Rgb rgb6 = new Rgb("Rec. ITU-R BT.2020-1", new float[]{0.708f, 0.292f, 0.17f, 0.797f, 0.131f, 0.046f}, Illuminant.getD65(), new TransferParameters(2.2222222222222223d, 0.9096697898662786d, 0.09033021013372146d, 0.2222222222222222d, 0.08145d), 5);
        Rgb rgb7 = new Rgb("SMPTE RP 431-2-2007 DCI (P3)", new float[]{0.68f, 0.32f, 0.265f, 0.69f, 0.15f, 0.06f}, new WhitePoint(0.314f, 0.351f), 2.6d, 0.0f, 1.0f, 6);
        Rgb rgb8 = new Rgb("Display P3", new float[]{0.68f, 0.32f, 0.265f, 0.69f, 0.15f, 0.06f}, Illuminant.getD65(), transferParameters, 7);
        Rgb rgb9 = new Rgb("NTSC (1953)", fArr2, Illuminant.getC(), new TransferParameters(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d), 8);
        Rgb rgb10 = new Rgb("SMPTE-C RGB", new float[]{0.63f, 0.34f, 0.31f, 0.595f, 0.155f, 0.07f}, Illuminant.getD65(), new TransferParameters(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d), 9);
        Rgb rgb11 = new Rgb("Adobe RGB (1998)", new float[]{0.64f, 0.33f, 0.21f, 0.71f, 0.15f, 0.06f}, Illuminant.getD65(), 2.2d, 0.0f, 1.0f, 10);
        Rgb rgb12 = new Rgb("ROMM RGB ISO 22028-2:2013", new float[]{0.7347f, 0.2653f, 0.1596f, 0.8404f, 0.0366f, 1.0E-4f}, Illuminant.getD50(), new TransferParameters(1.8d, 1.0d, 0.0d, 0.0625d, 0.031248d), 11);
        Rgb rgb13 = new Rgb("SMPTE ST 2065-1:2012 ACES", new float[]{0.7347f, 0.2653f, 0.0f, 1.0f, 1.0E-4f, -0.077f}, Illuminant.getD60(), 1.0d, -65504.0f, 65504.0f, 12);
        Rgb rgb14 = new Rgb("Academy S-2014-004 ACEScg", new float[]{0.713f, 0.293f, 0.165f, 0.83f, 0.128f, 0.044f}, Illuminant.getD60(), 1.0d, -65504.0f, 65504.0f, 13);
        Xyz xyz = new Xyz();
        CieXyz = xyz;
        Lab lab = new Lab();
        CieLab = lab;
        Rgb rgb15 = new Rgb("None", fArr, Illuminant.getD65(), transferParameters2, 16);
        Unspecified = rgb15;
        Oklab oklab = new Oklab();
        Oklab = oklab;
        ColorSpacesArray = new ColorSpace[]{rgb, rgb2, rgb3, rgb4, rgb5, rgb6, rgb7, rgb8, rgb9, rgb10, rgb11, rgb12, rgb13, rgb14, xyz, lab, rgb15, oklab};
    }

    public static ColorSpace[] getColorSpacesArray$ui_graphics_release() {
        return ColorSpacesArray;
    }

    public static Oklab getOklab() {
        return Oklab;
    }

    public static Rgb getSrgb() {
        return Srgb;
    }
}
