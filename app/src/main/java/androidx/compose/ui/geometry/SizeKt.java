package androidx.compose.ui.geometry;

import androidx.compose.ui.geometry.Size;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SizeKt {
    public static final long Size(float f, float f2) {
        long floatToIntBits = (Float.floatToIntBits(f2) & 4294967295L) | (Float.floatToIntBits(f) << 32);
        Size.Companion companion = Size.Companion;
        return floatToIntBits;
    }
}
