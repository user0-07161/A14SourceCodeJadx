package androidx.compose.runtime.snapshots;

import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SnapshotDoubleIndexHeap {
    private int firstFreeHandle;
    private int[] handles;
    private int size;
    private int[] values = new int[16];
    private int[] index = new int[16];

    public SnapshotDoubleIndexHeap() {
        int[] iArr = new int[16];
        int i = 0;
        while (i < 16) {
            int i2 = i + 1;
            iArr[i] = i2;
            i = i2;
        }
        this.handles = iArr;
    }

    private final void swap(int i, int i2) {
        int[] iArr = this.values;
        int[] iArr2 = this.index;
        int[] iArr3 = this.handles;
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
        int i4 = iArr2[i];
        iArr2[i] = iArr2[i2];
        iArr2[i2] = i4;
        iArr3[iArr2[i]] = i;
        iArr3[iArr2[i2]] = i2;
    }

    public final int add(int i) {
        int i2 = this.size + 1;
        int[] iArr = this.values;
        int length = iArr.length;
        if (i2 > length) {
            int i3 = length * 2;
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            ArraysKt.copyInto$default(iArr, iArr2, 0, 14);
            ArraysKt.copyInto$default(this.index, iArr3, 0, 14);
            this.values = iArr2;
            this.index = iArr3;
        }
        int i4 = this.size;
        this.size = i4 + 1;
        int length2 = this.handles.length;
        if (this.firstFreeHandle >= length2) {
            int i5 = length2 * 2;
            int[] iArr4 = new int[i5];
            int i6 = 0;
            while (i6 < i5) {
                int i7 = i6 + 1;
                iArr4[i6] = i7;
                i6 = i7;
            }
            ArraysKt.copyInto$default(this.handles, iArr4, 0, 14);
            this.handles = iArr4;
        }
        int i8 = this.firstFreeHandle;
        int[] iArr5 = this.handles;
        this.firstFreeHandle = iArr5[i8];
        int[] iArr6 = this.values;
        iArr6[i4] = i;
        this.index[i4] = i8;
        iArr5[i8] = i4;
        int i9 = iArr6[i4];
        while (i4 > 0) {
            int i10 = ((i4 + 1) >> 1) - 1;
            if (iArr6[i10] <= i9) {
                break;
            }
            swap(i10, i4);
            i4 = i10;
        }
        return i8;
    }

    public final int lowestOrDefault(int i) {
        if (this.size > 0) {
            return this.values[0];
        }
        return i;
    }

    public final void remove(int i) {
        int i2;
        int i3 = this.handles[i];
        swap(i3, this.size - 1);
        this.size--;
        int[] iArr = this.values;
        int i4 = iArr[i3];
        int i5 = i3;
        while (i5 > 0) {
            int i6 = ((i5 + 1) >> 1) - 1;
            if (iArr[i6] <= i4) {
                break;
            }
            swap(i6, i5);
            i5 = i6;
        }
        int[] iArr2 = this.values;
        int i7 = this.size >> 1;
        while (i3 < i7) {
            int i8 = (i3 + 1) << 1;
            int i9 = i8 - 1;
            if (i8 < this.size && (i2 = iArr2[i8]) < iArr2[i9]) {
                if (i2 >= iArr2[i3]) {
                    break;
                }
                swap(i8, i3);
                i3 = i8;
            } else if (iArr2[i9] >= iArr2[i3]) {
                break;
            } else {
                swap(i9, i3);
                i3 = i9;
            }
        }
        this.handles[i] = this.firstFreeHandle;
        this.firstFreeHandle = i;
    }
}
