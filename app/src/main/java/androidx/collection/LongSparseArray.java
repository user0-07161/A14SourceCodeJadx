package androidx.collection;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LongSparseArray implements Cloneable {
    public /* synthetic */ boolean garbage;
    public /* synthetic */ long[] keys;
    public /* synthetic */ int size;
    public /* synthetic */ Object[] values;

    public LongSparseArray() {
        int i;
        int i2 = 4;
        while (true) {
            i = 80;
            if (i2 >= 32) {
                break;
            }
            int i3 = (1 << i2) - 12;
            if (80 <= i3) {
                i = i3;
                break;
            }
            i2++;
        }
        int i4 = i / 8;
        this.keys = new long[i4];
        this.values = new Object[i4];
    }

    public final Object clone() {
        Object clone = super.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>");
        LongSparseArray longSparseArray = (LongSparseArray) clone;
        longSparseArray.keys = (long[]) this.keys.clone();
        longSparseArray.values = (Object[]) this.values.clone();
        return longSparseArray;
    }

    public final int size() {
        Object obj;
        if (this.garbage) {
            int i = this.size;
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Object obj2 = objArr[i3];
                obj = LongSparseArrayKt.DELETED;
                if (obj2 != obj) {
                    if (i3 != i2) {
                        jArr[i2] = jArr[i3];
                        objArr[i2] = obj2;
                        objArr[i3] = null;
                    }
                    i2++;
                }
            }
            this.garbage = false;
            this.size = i2;
        }
        return this.size;
    }

    public final String toString() {
        boolean z;
        Object obj;
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 28);
        sb.append('{');
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            if (i2 >= 0 && i2 < this.size) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                if (this.garbage) {
                    int i3 = this.size;
                    long[] jArr = this.keys;
                    Object[] objArr = this.values;
                    int i4 = 0;
                    for (int i5 = 0; i5 < i3; i5++) {
                        Object obj2 = objArr[i5];
                        obj = LongSparseArrayKt.DELETED;
                        if (obj2 != obj) {
                            if (i5 != i4) {
                                jArr[i4] = jArr[i5];
                                objArr[i4] = obj2;
                                objArr[i5] = null;
                            }
                            i4++;
                        }
                    }
                    this.garbage = false;
                    this.size = i4;
                }
                sb.append(this.keys[i2]);
                sb.append('=');
                Object valueAt = valueAt(i2);
                if (valueAt != sb) {
                    sb.append(valueAt);
                } else {
                    sb.append("(this Map)");
                }
            } else {
                throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i2).toString());
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    public final Object valueAt(int i) {
        boolean z;
        Object obj;
        if (i >= 0 && i < this.size) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (this.garbage) {
                int i2 = this.size;
                long[] jArr = this.keys;
                Object[] objArr = this.values;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj2 = objArr[i4];
                    obj = LongSparseArrayKt.DELETED;
                    if (obj2 != obj) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr[i3] = obj2;
                            objArr[i4] = null;
                        }
                        i3++;
                    }
                }
                this.garbage = false;
                this.size = i3;
            }
            return this.values[i];
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i).toString());
    }
}
