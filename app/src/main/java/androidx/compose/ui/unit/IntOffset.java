package androidx.compose.ui.unit;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class IntOffset {
    public static final Companion Companion = new Companion();
    private static final long Zero = IntOffsetKt.IntOffset(0, 0);
    private final long packedValue;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    private /* synthetic */ IntOffset(long j) {
        this.packedValue = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ IntOffset m401boximpl(long j) {
        return new IntOffset(j);
    }

    /* renamed from: getY-impl  reason: not valid java name */
    public static final int m402getYimpl(long j) {
        return (int) (j & 4294967295L);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof IntOffset)) {
            return false;
        }
        if (this.packedValue != ((IntOffset) obj).packedValue) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(");
        long j = this.packedValue;
        sb.append((int) (j >> 32));
        sb.append(", ");
        sb.append(m402getYimpl(j));
        sb.append(')');
        return sb.toString();
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m403unboximpl() {
        return this.packedValue;
    }
}
