package androidx.compose.ui.graphics.colorspace;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Illuminant {
    private static final WhitePoint C = new WhitePoint(0.31006f, 0.31616f);
    private static final WhitePoint D50 = new WhitePoint(0.34567f, 0.3585f);
    private static final WhitePoint D60 = new WhitePoint(0.32168f, 0.33767f);
    private static final WhitePoint D65 = new WhitePoint(0.31271f, 0.32902f);
    private static final float[] D50Xyz = {0.964212f, 1.0f, 0.825188f};

    public static WhitePoint getC() {
        return C;
    }

    public static WhitePoint getD50() {
        return D50;
    }

    public static WhitePoint getD60() {
        return D60;
    }

    public static WhitePoint getD65() {
        return D65;
    }
}
