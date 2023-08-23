package com.android.egg.landroid;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RandomTable {
    public static final int $stable = 8;
    private final Pair[] pairs;
    private final float total;

    public RandomTable(Pair... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        this.pairs = pairs;
        ArrayList<Number> arrayList = new ArrayList(pairs.length);
        for (Pair pair : pairs) {
            arrayList.add(Float.valueOf(((Number) pair.getFirst()).floatValue()));
        }
        float f = 0.0f;
        for (Number number : arrayList) {
            f += number.floatValue();
        }
        this.total = f;
    }

    public final Object roll(Random rng) {
        Pair[] pairArr;
        Intrinsics.checkNotNullParameter(rng, "rng");
        float nextFloatInRange = RandomnessKt.nextFloatInRange(rng, 0.0f, this.total);
        boolean z = false;
        for (Pair pair : this.pairs) {
            float floatValue = ((Number) pair.component1()).floatValue();
            Object component2 = pair.component2();
            nextFloatInRange -= floatValue;
            if (nextFloatInRange < 0.0f) {
                return component2;
            }
        }
        Pair[] pairArr2 = this.pairs;
        Intrinsics.checkNotNullParameter(pairArr2, "<this>");
        if (pairArr2.length == 0) {
            z = true;
        }
        if (!z) {
            return pairArr2[pairArr2.length - 1].getSecond();
        }
        throw new NoSuchElementException("Array is empty.");
    }
}
