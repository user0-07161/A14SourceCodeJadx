package androidx.compose.ui.geometry;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CornerRadius {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final long Zero = (Float.floatToIntBits(0.0f) << 32) | (Float.floatToIntBits(0.0f) & 4294967295L);

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m38equalsimpl0(long j, long j2) {
        if (j == j2) {
            return true;
        }
        return false;
    }

    /* renamed from: getX-impl  reason: not valid java name */
    public static final float m39getXimpl(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* renamed from: getY-impl  reason: not valid java name */
    public static final float m40getYimpl(long j) {
        return Float.intBitsToFloat((int) (j & 4294967295L));
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m41toStringimpl(long j) {
        boolean z;
        if (m39getXimpl(j) == m40getYimpl(j)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return "CornerRadius.circular(" + GeometryUtilsKt.toStringAsFixed(m39getXimpl(j)) + ')';
        }
        return "CornerRadius.elliptical(" + GeometryUtilsKt.toStringAsFixed(m39getXimpl(j)) + ", " + GeometryUtilsKt.toStringAsFixed(m40getYimpl(j)) + ')';
    }
}
