package androidx.compose.ui.graphics.colorspace;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ColorModel {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final long Cmyk;
    private static final long Lab;
    private static final long Rgb;
    private static final long Xyz;

    static {
        long j = 3;
        long j2 = j << 32;
        Rgb = (0 & 4294967295L) | j2;
        Xyz = (1 & 4294967295L) | j2;
        Lab = j2 | (2 & 4294967295L);
        Cmyk = (j & 4294967295L) | (4 << 32);
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m128equalsimpl0(long j, long j2) {
        if (j == j2) {
            return true;
        }
        return false;
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m129toStringimpl(long j) {
        if (m128equalsimpl0(j, Rgb)) {
            return "Rgb";
        }
        if (m128equalsimpl0(j, Xyz)) {
            return "Xyz";
        }
        if (m128equalsimpl0(j, Lab)) {
            return "Lab";
        }
        if (m128equalsimpl0(j, Cmyk)) {
            return "Cmyk";
        }
        return "Unknown";
    }
}
