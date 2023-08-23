package androidx.compose.foundation.text;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class HeightInLinesModifierKt {
    public static final void validateMinMaxLines(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (i > 0 && i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (i > i2) {
                z2 = false;
            }
            if (z2) {
                return;
            }
            throw new IllegalArgumentException(HeightInLinesModifierKt$$ExternalSyntheticOutline0.m("minLines ", i, " must be less than or equal to maxLines ", i2).toString());
        }
        throw new IllegalArgumentException(("both minLines " + i + " and maxLines " + i2 + " must be greater than zero").toString());
    }
}
