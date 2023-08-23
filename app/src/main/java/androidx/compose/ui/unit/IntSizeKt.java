package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.SizeKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class IntSizeKt {
    public static final long IntSize(int i, int i2) {
        return (i2 & 4294967295L) | (i << 32);
    }

    /* renamed from: toSize-ozmzZPI  reason: not valid java name */
    public static final long m408toSizeozmzZPI(long j) {
        return SizeKt.Size((int) (j >> 32), IntSize.m405getHeightimpl(j));
    }
}
