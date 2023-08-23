package androidx.compose.ui.unit;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DpOffset {
    public static final Companion Companion = new Companion();
    private static final long Unspecified;
    private final long packedValue;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    static {
        float f = 0;
        DpKt.m393DpOffsetYgX7TsA(f, f);
        Unspecified = DpKt.m393DpOffsetYgX7TsA(Float.NaN, Float.NaN);
    }

    private /* synthetic */ DpOffset(long j) {
        this.packedValue = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ DpOffset m395boximpl(long j) {
        return new DpOffset(j);
    }

    /* renamed from: getX-D9Ej5fM  reason: not valid java name */
    public static final float m396getXD9Ej5fM(long j) {
        boolean z;
        if (j != Unspecified) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return Float.intBitsToFloat((int) (j >> 32));
        }
        throw new IllegalStateException("DpOffset is unspecified".toString());
    }

    /* renamed from: getY-D9Ej5fM  reason: not valid java name */
    public static final float m397getYD9Ej5fM(long j) {
        boolean z;
        if (j != Unspecified) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return Float.intBitsToFloat((int) (j & 4294967295L));
        }
        throw new IllegalStateException("DpOffset is unspecified".toString());
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof DpOffset)) {
            return false;
        }
        if (this.packedValue != ((DpOffset) obj).packedValue) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        boolean z;
        long j = Unspecified;
        long j2 = this.packedValue;
        if (j2 != j) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return "(" + ((Object) Dp.m391toStringimpl(m396getXD9Ej5fM(j2))) + ", " + ((Object) Dp.m391toStringimpl(m397getYD9Ej5fM(j2))) + ')';
        }
        return "DpOffset.Unspecified";
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m398unboximpl() {
        return this.packedValue;
    }
}
