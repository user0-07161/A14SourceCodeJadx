package androidx.compose.ui.text;

import androidx.compose.ui.text.TextRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TextRangeKt {
    public static final long TextRange(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (i2 < 0) {
                z2 = false;
            }
            if (z2) {
                long j = (i2 & 4294967295L) | (i << 32);
                TextRange.Companion companion = TextRange.Companion;
                return j;
            }
            throw new IllegalArgumentException(("end cannot be negative. [start: " + i + ", end: " + i2 + ']').toString());
        }
        throw new IllegalArgumentException(("start cannot be negative. [start: " + i + ", end: " + i2 + ']').toString());
    }

    /* renamed from: constrain-8ffj60Q  reason: not valid java name */
    public static final long m318constrain8ffj60Q(long j, int i) {
        TextRange.Companion companion = TextRange.Companion;
        int i2 = (int) (j >> 32);
        int coerceIn = RangesKt.coerceIn(i2, 0, i);
        int coerceIn2 = RangesKt.coerceIn(TextRange.m315getEndimpl(j), 0, i);
        if (coerceIn == i2 && coerceIn2 == TextRange.m315getEndimpl(j)) {
            return j;
        }
        return TextRange(coerceIn, coerceIn2);
    }
}
