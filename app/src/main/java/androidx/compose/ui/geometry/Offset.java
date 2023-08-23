package androidx.compose.ui.geometry;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Offset {
    private final long packedValue;
    public static final Companion Companion = new Companion();
    private static final long Zero = OffsetKt.Offset(0.0f, 0.0f);
    private static final long Infinite = OffsetKt.Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    private static final long Unspecified = OffsetKt.Offset(Float.NaN, Float.NaN);

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    private /* synthetic */ Offset(long j) {
        this.packedValue = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Offset m42boximpl(long j) {
        return new Offset(j);
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m43equalsimpl0(long j, long j2) {
        if (j == j2) {
            return true;
        }
        return false;
    }

    /* renamed from: getDistance-impl  reason: not valid java name */
    public static final float m44getDistanceimpl(long j) {
        return (float) Math.sqrt((m46getYimpl(j) * m46getYimpl(j)) + (m45getXimpl(j) * m45getXimpl(j)));
    }

    /* renamed from: getX-impl  reason: not valid java name */
    public static final float m45getXimpl(long j) {
        boolean z;
        if (j != Unspecified) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return Float.intBitsToFloat((int) (j >> 32));
        }
        throw new IllegalStateException("Offset is unspecified".toString());
    }

    /* renamed from: getY-impl  reason: not valid java name */
    public static final float m46getYimpl(long j) {
        boolean z;
        if (j != Unspecified) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return Float.intBitsToFloat((int) (j & 4294967295L));
        }
        throw new IllegalStateException("Offset is unspecified".toString());
    }

    /* renamed from: minus-MK-Hz9U  reason: not valid java name */
    public static final long m47minusMKHz9U(long j, long j2) {
        return OffsetKt.Offset(m45getXimpl(j) - m45getXimpl(j2), m46getYimpl(j) - m46getYimpl(j2));
    }

    /* renamed from: plus-MK-Hz9U  reason: not valid java name */
    public static final long m48plusMKHz9U(long j, long j2) {
        return OffsetKt.Offset(m45getXimpl(j2) + m45getXimpl(j), m46getYimpl(j2) + m46getYimpl(j));
    }

    /* renamed from: times-tuRUvjQ  reason: not valid java name */
    public static final long m49timestuRUvjQ(long j, float f) {
        return OffsetKt.Offset(m45getXimpl(j) * f, m46getYimpl(j) * f);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m50toStringimpl(long j) {
        boolean z;
        if (j != Unspecified) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return "Offset(" + GeometryUtilsKt.toStringAsFixed(m45getXimpl(j)) + ", " + GeometryUtilsKt.toStringAsFixed(m46getYimpl(j)) + ')';
        }
        return "Offset.Unspecified";
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Offset)) {
            return false;
        }
        if (this.packedValue != ((Offset) obj).packedValue) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m50toStringimpl(this.packedValue);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m51unboximpl() {
        return this.packedValue;
    }
}
