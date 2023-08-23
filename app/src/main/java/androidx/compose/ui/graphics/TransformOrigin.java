package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TransformOrigin {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final long Center = (Float.floatToIntBits(0.5f) << 32) | (Float.floatToIntBits(0.5f) & 4294967295L);
    private final long packedValue;

    private /* synthetic */ TransformOrigin(long j) {
        this.packedValue = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ TransformOrigin m125boximpl(long j) {
        return new TransformOrigin(j);
    }

    /* renamed from: getPivotFractionY-impl  reason: not valid java name */
    public static final float m126getPivotFractionYimpl(long j) {
        return Float.intBitsToFloat((int) (j & 4294967295L));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TransformOrigin)) {
            return false;
        }
        if (this.packedValue != ((TransformOrigin) obj).packedValue) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return "TransformOrigin(packedValue=" + this.packedValue + ')';
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m127unboximpl() {
        return this.packedValue;
    }
}
