package androidx.compose.ui.unit;

import androidx.compose.ui.unit.TextUnit;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TextUnitKt {
    public static final long getSp(int i) {
        return pack(4294967296L, i);
    }

    /* renamed from: isUnspecified--R2X_6o  reason: not valid java name */
    public static final boolean m415isUnspecifiedR2X_6o(long j) {
        TextUnit.Companion companion = TextUnit.Companion;
        if ((j & 1095216660480L) == 0) {
            return true;
        }
        return false;
    }

    public static final long pack(long j, float f) {
        long floatToIntBits = j | (Float.floatToIntBits(f) & 4294967295L);
        TextUnit.Companion companion = TextUnit.Companion;
        return floatToIntBits;
    }
}
