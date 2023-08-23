package androidx.compose.ui.unit;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextUnitType {
    private final long type;

    private /* synthetic */ TextUnitType(long j) {
        this.type = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ TextUnitType m416boximpl(long j) {
        return new TextUnitType(j);
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m417equalsimpl0(long j, long j2) {
        if (j == j2) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TextUnitType)) {
            return false;
        }
        if (this.type != ((TextUnitType) obj).type) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.type);
    }

    public final String toString() {
        long j = this.type;
        if (m417equalsimpl0(j, 0L)) {
            return "Unspecified";
        }
        if (m417equalsimpl0(j, 4294967296L)) {
            return "Sp";
        }
        if (m417equalsimpl0(j, 8589934592L)) {
            return "Em";
        }
        return "Invalid";
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m418unboximpl() {
        return this.type;
    }
}
