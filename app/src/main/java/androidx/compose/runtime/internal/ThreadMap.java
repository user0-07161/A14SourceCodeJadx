package androidx.compose.runtime.internal;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ThreadMap {
    private final long[] keys;
    private final int size;
    private final Object[] values;

    public ThreadMap(int i, long[] jArr, Object[] objArr) {
        this.size = i;
        this.keys = jArr;
        this.values = objArr;
    }

    private final int find(long j) {
        int i = this.size - 1;
        if (i == -1) {
            return -1;
        }
        long[] jArr = this.keys;
        int i2 = 0;
        if (i != 0) {
            while (i2 <= i) {
                int i3 = (i2 + i) >>> 1;
                int i4 = ((jArr[i3] - j) > 0L ? 1 : ((jArr[i3] - j) == 0L ? 0 : -1));
                if (i4 < 0) {
                    i2 = i3 + 1;
                } else if (i4 > 0) {
                    i = i3 - 1;
                } else {
                    return i3;
                }
            }
            return -(i2 + 1);
        }
        long j2 = jArr[0];
        if (j2 == j) {
            return 0;
        }
        if (j2 <= j) {
            return -1;
        }
        return -2;
    }

    public final Object get(long j) {
        int find = find(j);
        if (find >= 0) {
            return this.values[find];
        }
        return null;
    }

    public final ThreadMap newWith(long j, Object obj) {
        long[] jArr;
        int i;
        Object[] objArr = this.values;
        int length = objArr.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= length) {
                break;
            }
            if (objArr[i3] == null) {
                z = false;
            }
            if (z) {
                i4++;
            }
            i3++;
        }
        int i5 = i4 + 1;
        long[] jArr2 = new long[i5];
        Object[] objArr2 = new Object[i5];
        if (i5 > 1) {
            int i6 = 0;
            while (true) {
                jArr = this.keys;
                i = this.size;
                if (i2 >= i5 || i6 >= i) {
                    break;
                }
                long j2 = jArr[i6];
                Object obj2 = objArr[i6];
                if (j2 > j) {
                    jArr2[i2] = j;
                    objArr2[i2] = obj;
                    i2++;
                    break;
                }
                if (obj2 != null) {
                    jArr2[i2] = j2;
                    objArr2[i2] = obj2;
                    i2++;
                }
                i6++;
            }
            if (i6 == i) {
                int i7 = i5 - 1;
                jArr2[i7] = j;
                objArr2[i7] = obj;
            } else {
                while (i2 < i5) {
                    long j3 = jArr[i6];
                    Object obj3 = objArr[i6];
                    if (obj3 != null) {
                        jArr2[i2] = j3;
                        objArr2[i2] = obj3;
                        i2++;
                    }
                    i6++;
                }
            }
        } else {
            jArr2[0] = j;
            objArr2[0] = obj;
        }
        return new ThreadMap(i5, jArr2, objArr2);
    }

    public final boolean trySet(long j, Object obj) {
        int find = find(j);
        if (find < 0) {
            return false;
        }
        this.values[find] = obj;
        return true;
    }
}
