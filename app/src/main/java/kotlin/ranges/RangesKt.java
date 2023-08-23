package kotlin.ranges;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class RangesKt extends RangesKt___RangesKt {
    public static int coerceIn(int i, int i2, int i3) {
        if (i2 <= i3) {
            return i < i2 ? i2 : i > i3 ? i3 : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i3 + " is less than minimum " + i2 + '.');
    }

    public static ClosedFloatingPointRange rangeTo(float f, float f2) {
        return new ClosedFloatRange(f, f2);
    }

    public static IntProgression step(IntRange intRange, int i) {
        boolean z;
        Intrinsics.checkNotNullParameter(intRange, "<this>");
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        Integer step = Integer.valueOf(i);
        Intrinsics.checkNotNullParameter(step, "step");
        if (z) {
            int first = intRange.getFirst();
            int last = intRange.getLast();
            if (intRange.getStep() <= 0) {
                i = -i;
            }
            return new IntProgression(first, last, i);
        }
        throw new IllegalArgumentException("Step must be positive, was: " + step + '.');
    }

    public static IntRange until(int i, int i2) {
        if (i2 <= Integer.MIN_VALUE) {
            return IntRange.access$getEMPTY$cp();
        }
        return new IntRange(i, i2 - 1);
    }

    public static float coerceIn(float f, float f2, float f3) {
        if (f2 <= f3) {
            return f < f2 ? f2 : f > f3 ? f3 : f;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f3 + " is less than minimum " + f2 + '.');
    }

    public static double coerceIn(double d, double d2, double d3) {
        if (d2 <= d3) {
            return d < d2 ? d2 : d > d3 ? d3 : d;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + d3 + " is less than minimum " + d2 + '.');
    }
}
