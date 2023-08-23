package androidx.compose.ui.text;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextRange {
    public static final Companion Companion = new Companion();
    private static final long Zero = TextRangeKt.TextRange(0, 0);
    private final long packedValue;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    private /* synthetic */ TextRange(long j) {
        this.packedValue = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ TextRange m314boximpl(long j) {
        return new TextRange(j);
    }

    /* renamed from: getEnd-impl  reason: not valid java name */
    public static final int m315getEndimpl(long j) {
        return (int) (j & 4294967295L);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m316toStringimpl(long j) {
        return "TextRange(" + ((int) (j >> 32)) + ", " + m315getEndimpl(j) + ')';
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TextRange)) {
            return false;
        }
        if (this.packedValue != ((TextRange) obj).packedValue) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m316toStringimpl(this.packedValue);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m317unboximpl() {
        return this.packedValue;
    }
}
