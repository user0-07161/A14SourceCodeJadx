package androidx.compose.ui.unit;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class IntSize {
    public static final Companion Companion = new Companion();
    private final long packedValue;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    private /* synthetic */ IntSize(long j) {
        this.packedValue = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ IntSize m404boximpl(long j) {
        return new IntSize(j);
    }

    /* renamed from: getHeight-impl  reason: not valid java name */
    public static final int m405getHeightimpl(long j) {
        return (int) (j & 4294967295L);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m406toStringimpl(long j) {
        return ((int) (j >> 32)) + " x " + m405getHeightimpl(j);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof IntSize)) {
            return false;
        }
        if (this.packedValue != ((IntSize) obj).packedValue) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m406toStringimpl(this.packedValue);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m407unboximpl() {
        return this.packedValue;
    }
}
