package kotlin.text;

import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CharsKt extends CharsKt__CharKt {
    public static void checkRadix(int i) {
        boolean z;
        IntRange intRange = new IntRange(2, 36);
        if (intRange.getFirst() <= i && i <= intRange.getLast()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException("radix " + i + " was not in valid range " + new IntRange(2, 36));
    }
}
