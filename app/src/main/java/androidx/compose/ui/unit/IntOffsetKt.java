package androidx.compose.ui.unit;

import androidx.compose.ui.unit.IntOffset;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class IntOffsetKt {
    public static final long IntOffset(int i, int i2) {
        long j = (i2 & 4294967295L) | (i << 32);
        IntOffset.Companion companion = IntOffset.Companion;
        return j;
    }
}
