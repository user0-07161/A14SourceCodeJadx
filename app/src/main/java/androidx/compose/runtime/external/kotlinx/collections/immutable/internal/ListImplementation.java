package androidx.compose.runtime.external.kotlinx.collections.immutable.internal;

import androidx.compose.foundation.text.HeightInLinesModifierKt$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ListImplementation {
    public static final void checkElementIndex$runtime_release(int i, int i2) {
        if (i >= 0 && i < i2) {
            return;
        }
        throw new IndexOutOfBoundsException(HeightInLinesModifierKt$$ExternalSyntheticOutline0.m("index: ", i, ", size: ", i2));
    }

    public static final void checkPositionIndex$runtime_release(int i, int i2) {
        if (i >= 0 && i <= i2) {
            return;
        }
        throw new IndexOutOfBoundsException(HeightInLinesModifierKt$$ExternalSyntheticOutline0.m("index: ", i, ", size: ", i2));
    }

    public static final void checkRangeIndexes$runtime_release(int i, int i2, int i3) {
        if (i >= 0 && i2 <= i3) {
            if (i <= i2) {
                return;
            }
            throw new IllegalArgumentException(HeightInLinesModifierKt$$ExternalSyntheticOutline0.m("fromIndex: ", i, " > toIndex: ", i2));
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2 + ", size: " + i3);
    }
}
