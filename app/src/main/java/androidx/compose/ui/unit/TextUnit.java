package androidx.compose.ui.unit;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextUnit {
    public static final Companion Companion = new Companion();
    private static final TextUnitType[] TextUnitTypes = {TextUnitType.m416boximpl(0), TextUnitType.m416boximpl(4294967296L), TextUnitType.m416boximpl(8589934592L)};
    private static final long Unspecified = TextUnitKt.pack(0, Float.NaN);
    private final long packedValue;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    private /* synthetic */ TextUnit(long j) {
        this.packedValue = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ TextUnit m409boximpl(long j) {
        return new TextUnit(j);
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m410equalsimpl0(long j, long j2) {
        if (j == j2) {
            return true;
        }
        return false;
    }

    /* renamed from: getType-UIouoOA  reason: not valid java name */
    public static final long m411getTypeUIouoOA(long j) {
        return TextUnitTypes[(int) ((j & 1095216660480L) >>> 32)].m418unboximpl();
    }

    /* renamed from: getValue-impl  reason: not valid java name */
    public static final float m412getValueimpl(long j) {
        return Float.intBitsToFloat((int) (j & 4294967295L));
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m413toStringimpl(long j) {
        long m411getTypeUIouoOA = m411getTypeUIouoOA(j);
        if (TextUnitType.m417equalsimpl0(m411getTypeUIouoOA, 0L)) {
            return "Unspecified";
        }
        if (TextUnitType.m417equalsimpl0(m411getTypeUIouoOA, 4294967296L)) {
            return m412getValueimpl(j) + ".sp";
        } else if (TextUnitType.m417equalsimpl0(m411getTypeUIouoOA, 8589934592L)) {
            return m412getValueimpl(j) + ".em";
        } else {
            return "Invalid";
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TextUnit)) {
            return false;
        }
        if (this.packedValue != ((TextUnit) obj).packedValue) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m413toStringimpl(this.packedValue);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m414unboximpl() {
        return this.packedValue;
    }
}
