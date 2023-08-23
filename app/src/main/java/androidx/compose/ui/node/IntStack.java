package androidx.compose.ui.node;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class IntStack {
    private int lastIndex;
    private int[] stack;

    public IntStack(int i) {
        this.stack = new int[i];
    }

    private final void quickSort(int i, int i2) {
        boolean z;
        if (i < i2) {
            int i3 = i - 3;
            for (int i4 = i; i4 < i2; i4 += 3) {
                int[] iArr = this.stack;
                int i5 = iArr[i4];
                int i6 = iArr[i2];
                if (i5 >= i6 && (i5 != i6 || iArr[i4 + 1] > iArr[i2 + 1])) {
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    i3 += 3;
                    swapDiagonal(i3, i4);
                }
            }
            int i7 = i3 + 3;
            swapDiagonal(i7, i2);
            quickSort(i, i7 - 3);
            quickSort(i7 + 3, i2);
        }
    }

    private final void swapDiagonal(int i, int i2) {
        int[] iArr = this.stack;
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
        int i4 = i + 1;
        int i5 = i2 + 1;
        int i6 = iArr[i4];
        iArr[i4] = iArr[i5];
        iArr[i5] = i6;
        int i7 = i + 2;
        int i8 = i2 + 2;
        int i9 = iArr[i7];
        iArr[i7] = iArr[i8];
        iArr[i8] = i9;
    }

    public final boolean isNotEmpty() {
        if (this.lastIndex != 0) {
            return true;
        }
        return false;
    }

    public final int pop() {
        int[] iArr = this.stack;
        int i = this.lastIndex - 1;
        this.lastIndex = i;
        return iArr[i];
    }

    public final void pushDiagonal(int i, int i2, int i3) {
        int i4 = this.lastIndex;
        int i5 = i4 + 3;
        int[] iArr = this.stack;
        if (i5 >= iArr.length) {
            int[] copyOf = Arrays.copyOf(iArr, iArr.length * 2);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.stack = copyOf;
        }
        int[] iArr2 = this.stack;
        iArr2[i4 + 0] = i + i3;
        iArr2[i4 + 1] = i2 + i3;
        iArr2[i4 + 2] = i3;
        this.lastIndex = i5;
    }

    public final void pushRange(int i, int i2, int i3, int i4) {
        int i5 = this.lastIndex;
        int i6 = i5 + 4;
        int[] iArr = this.stack;
        if (i6 >= iArr.length) {
            int[] copyOf = Arrays.copyOf(iArr, iArr.length * 2);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.stack = copyOf;
        }
        int[] iArr2 = this.stack;
        iArr2[i5 + 0] = i;
        iArr2[i5 + 1] = i2;
        iArr2[i5 + 2] = i3;
        iArr2[i5 + 3] = i4;
        this.lastIndex = i6;
    }

    public final void sortDiagonals() {
        boolean z;
        int i = this.lastIndex;
        if (i % 3 == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (i > 3) {
                quickSort(0, i - 3);
                return;
            }
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }
}
