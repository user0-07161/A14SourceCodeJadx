package androidx.compose.ui.unit;

import androidx.compose.ui.unit.DpOffset;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class DpKt {
    /* renamed from: DpOffset-YgX7TsA  reason: not valid java name */
    public static final long m393DpOffsetYgX7TsA(float f, float f2) {
        long floatToIntBits = (Float.floatToIntBits(f2) & 4294967295L) | (Float.floatToIntBits(f) << 32);
        DpOffset.Companion companion = DpOffset.Companion;
        return floatToIntBits;
    }

    /* renamed from: DpSize-YgX7TsA  reason: not valid java name */
    public static final long m394DpSizeYgX7TsA(float f, float f2) {
        long floatToIntBits = (Float.floatToIntBits(f2) & 4294967295L) | (Float.floatToIntBits(f) << 32);
        int i = DpSize.$r8$clinit;
        return floatToIntBits;
    }
}
