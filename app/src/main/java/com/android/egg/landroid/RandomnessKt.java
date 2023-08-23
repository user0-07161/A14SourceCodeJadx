package com.android.egg.landroid;

import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.ClosedFloatingPointRange;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RandomnessKt {
    public static final Object choose(Random random, Object[] array) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(array, "array");
        return array[random.nextInt(array.length)];
    }

    public static final float nextFloatInRange(Random random, float f, float f2) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return (random.nextFloat() * (f2 - f)) + f;
    }

    public static final float nextFloatInRange(Random random, ClosedFloatingPointRange fromUntil) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(fromUntil, "fromUntil");
        return nextFloatInRange(random, ((Number) fromUntil.getStart()).floatValue(), ((Number) fromUntil.getEndInclusive()).floatValue());
    }

    public static final float nextFloatInRange(Random random, Pair fromUntil) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(fromUntil, "fromUntil");
        return nextFloatInRange(random, ((Number) fromUntil.getFirst()).floatValue(), ((Number) fromUntil.getSecond()).floatValue());
    }
}
