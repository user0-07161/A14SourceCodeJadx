package com.android.egg.landroid;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Bag {
    public static final int $stable = 8;
    private int next;
    private final Object[] remaining;

    public Bag(Object[] items) {
        Intrinsics.checkNotNullParameter(items, "items");
        Object[] copyOf = Arrays.copyOf(items, items.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        this.remaining = copyOf;
        this.next = copyOf.length;
    }

    public final Object pull(Random rng) {
        Intrinsics.checkNotNullParameter(rng, "rng");
        int i = this.next;
        Object[] objArr = this.remaining;
        if (i >= objArr.length) {
            Intrinsics.checkNotNullParameter(objArr, "<this>");
            int length = objArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                int nextInt = rng.nextInt(length + 1);
                Object obj = objArr[length];
                objArr[length] = objArr[nextInt];
                objArr[nextInt] = obj;
            }
            this.next = 0;
        }
        Object[] objArr2 = this.remaining;
        int i2 = this.next;
        this.next = i2 + 1;
        return objArr2[i2];
    }
}
