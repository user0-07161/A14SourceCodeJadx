package androidx.compose.ui.unit;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class DpSize {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final long Unspecified = DpKt.m394DpSizeYgX7TsA(Float.NaN, Float.NaN);
    private static final long Zero;

    static {
        float f = 0;
        Zero = DpKt.m394DpSizeYgX7TsA(f, f);
    }

    /* renamed from: getHeight-D9Ej5fM  reason: not valid java name */
    public static final float m399getHeightD9Ej5fM(long j) {
        boolean z;
        if (j != Unspecified) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return Float.intBitsToFloat((int) (j & 4294967295L));
        }
        throw new IllegalStateException("DpSize is unspecified".toString());
    }

    /* renamed from: getWidth-D9Ej5fM  reason: not valid java name */
    public static final float m400getWidthD9Ej5fM(long j) {
        boolean z;
        if (j != Unspecified) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return Float.intBitsToFloat((int) (j >> 32));
        }
        throw new IllegalStateException("DpSize is unspecified".toString());
    }
}
