package androidx.compose.runtime.collection;

import androidx.compose.runtime.DerivedState;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class IdentityArrayMap {
    private int size;
    private Object[] keys = new Object[16];
    private Object[] values = new Object[16];

    private final int find(Object obj) {
        int identityHashCode = System.identityHashCode(obj);
        int i = this.size - 1;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            Object obj2 = this.keys[i3];
            int identityHashCode2 = System.identityHashCode(obj2);
            if (identityHashCode2 < identityHashCode) {
                i2 = i3 + 1;
            } else if (identityHashCode2 <= identityHashCode) {
                if (obj == obj2) {
                    return i3;
                } else {
                    for (int i4 = i3 - 1; -1 < i4; i4--) {
                        Object obj3 = this.keys[i4];
                        if (obj3 != obj) {
                            if (System.identityHashCode(obj3) != identityHashCode) {
                                break;
                            }
                        } else {
                            return i4;
                        }
                    }
                    int i5 = i3 + 1;
                    int i6 = this.size;
                    while (i5 < i6) {
                        Object obj4 = this.keys[i5];
                        if (obj4 == obj) {
                            return i5;
                        }
                        i5++;
                        if (System.identityHashCode(obj4) != identityHashCode) {
                            return -i5;
                        }
                    }
                    return -(this.size + 1);
                }
            } else {
                i = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    public final void clear() {
        this.size = 0;
        ArraysKt.fill$default(this.keys, null);
        ArraysKt.fill$default(this.values, null);
    }

    public final boolean contains(Object key) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (find(key) >= 0) {
            return true;
        }
        return false;
    }

    public final Object get(Object key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int find = find(key);
        if (find >= 0) {
            return this.values[find];
        }
        return null;
    }

    public final Object[] getKeys$runtime_release() {
        return this.keys;
    }

    public final int getSize$runtime_release() {
        return this.size;
    }

    public final Object[] getValues$runtime_release() {
        return this.values;
    }

    public final boolean isNotEmpty() {
        if (this.size > 0) {
            return true;
        }
        return false;
    }

    public final void remove(DerivedState derivedState) {
        int find = find(derivedState);
        if (find >= 0) {
            int i = this.size;
            Object[] objArr = this.keys;
            Object[] objArr2 = this.values;
            int i2 = find + 1;
            ArraysKt.copyInto(objArr, objArr, find, i2, i);
            ArraysKt.copyInto(objArr2, objArr2, find, i2, i);
            int i3 = i - 1;
            objArr[i3] = null;
            objArr2[i3] = null;
            this.size = i3;
        }
    }

    public final void set(Object key, Object obj) {
        boolean z;
        Object[] objArr;
        Object[] objArr2;
        Intrinsics.checkNotNullParameter(key, "key");
        int find = find(key);
        if (find >= 0) {
            this.values[find] = obj;
            return;
        }
        int i = -(find + 1);
        int i2 = this.size;
        Object[] objArr3 = this.keys;
        if (i2 == objArr3.length) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            objArr = new Object[i2 * 2];
        } else {
            objArr = objArr3;
        }
        int i3 = i + 1;
        ArraysKt.copyInto(objArr3, objArr, i3, i, i2);
        if (z) {
            ArraysKt.copyInto$default(this.keys, objArr, 0, 0, i, 6);
        }
        objArr[i] = key;
        this.keys = objArr;
        if (z) {
            objArr2 = new Object[this.size * 2];
        } else {
            objArr2 = this.values;
        }
        ArraysKt.copyInto(this.values, objArr2, i3, i, this.size);
        if (z) {
            ArraysKt.copyInto$default(this.values, objArr2, 0, 0, i, 6);
        }
        objArr2[i] = obj;
        this.values = objArr2;
        this.size++;
    }

    public final void setSize$runtime_release(int i) {
        this.size = i;
    }
}
