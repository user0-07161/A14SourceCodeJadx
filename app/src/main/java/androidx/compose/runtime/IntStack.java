package androidx.compose.runtime;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class IntStack {
    private int[] slots = new int[10];
    private int tos;

    public final void clear() {
        this.tos = 0;
    }

    public final int getSize() {
        return this.tos;
    }

    public final int indexOf(int i) {
        int i2 = this.tos;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.slots[i3] == i) {
                return i3;
            }
        }
        return -1;
    }

    public final boolean isEmpty() {
        if (this.tos == 0) {
            return true;
        }
        return false;
    }

    public final int peek() {
        return this.slots[this.tos - 1];
    }

    public final int peekOr(int i) {
        if (this.tos > 0) {
            return peek();
        }
        return i;
    }

    public final int pop() {
        int[] iArr = this.slots;
        int i = this.tos - 1;
        this.tos = i;
        return iArr[i];
    }

    public final void push(int i) {
        int i2 = this.tos;
        int[] iArr = this.slots;
        if (i2 >= iArr.length) {
            int[] copyOf = Arrays.copyOf(iArr, iArr.length * 2);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.slots = copyOf;
        }
        int[] iArr2 = this.slots;
        int i3 = this.tos;
        this.tos = i3 + 1;
        iArr2[i3] = i;
    }

    public final int peek(int i) {
        return this.slots[i];
    }
}
