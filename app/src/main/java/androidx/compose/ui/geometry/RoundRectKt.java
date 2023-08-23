package androidx.compose.ui.geometry;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class RoundRectKt {
    /* renamed from: RoundRect-gG7oq9Y  reason: not valid java name */
    public static final RoundRect m61RoundRectgG7oq9Y(float f, float f2, float f3, float f4, long j) {
        float m39getXimpl = CornerRadius.m39getXimpl(j);
        float m40getYimpl = CornerRadius.m40getYimpl(j);
        long floatToIntBits = (Float.floatToIntBits(m40getYimpl) & 4294967295L) | (Float.floatToIntBits(m39getXimpl) << 32);
        return new RoundRect(f, f2, f3, f4, floatToIntBits, floatToIntBits, floatToIntBits, floatToIntBits);
    }
}
