package androidx.compose.ui.text.caches;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SimpleArrayMap {
    private int[] hashes = ContainerHelpersKt.EMPTY_INTS;
    private Object[] keyValues = ContainerHelpersKt.EMPTY_OBJECTS;
    private int _size = 0;

    public final boolean equals(Object obj) {
        int indexOf;
        boolean z;
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof SimpleArrayMap) {
                SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
                int i = this._size;
                if (i != simpleArrayMap._size) {
                    return false;
                }
                for (int i2 = 0; i2 < i; i2++) {
                    Object[] objArr = this.keyValues;
                    int i3 = i2 << 1;
                    Object obj2 = objArr[i3];
                    Object obj3 = objArr[i3 + 1];
                    Object obj4 = simpleArrayMap.get(obj2);
                    if (obj3 == null) {
                        if (obj4 == null) {
                            if (obj2 == null) {
                                indexOf = simpleArrayMap.indexOfNull();
                            } else {
                                indexOf = simpleArrayMap.indexOf(obj2.hashCode(), obj2);
                            }
                            if (indexOf >= 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (!z) {
                            }
                        }
                        return false;
                    } else if (!Intrinsics.areEqual(obj3, obj4)) {
                        return false;
                    }
                }
                return true;
            } else if (!(obj instanceof Map) || this._size != ((Map) obj).size()) {
                return false;
            } else {
                int i4 = this._size;
                for (int i5 = 0; i5 < i4; i5++) {
                    Object[] objArr2 = this.keyValues;
                    int i6 = i5 << 1;
                    Object obj5 = objArr2[i6];
                    Object obj6 = objArr2[i6 + 1];
                    Object obj7 = ((Map) obj).get(obj5);
                    if (obj6 == null) {
                        if (obj7 != null || !((Map) obj).containsKey(obj5)) {
                            return false;
                        }
                    } else if (!Intrinsics.areEqual(obj6, obj7)) {
                        return false;
                    }
                }
                return true;
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final Object get(Object obj) {
        int indexOf;
        if (obj == null) {
            indexOf = indexOfNull();
        } else {
            indexOf = indexOf(obj.hashCode(), obj);
        }
        if (indexOf >= 0) {
            return this.keyValues[(indexOf << 1) + 1];
        }
        return null;
    }

    public final int hashCode() {
        int i;
        int[] iArr = this.hashes;
        Object[] objArr = this.keyValues;
        int i2 = this._size;
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            int i6 = iArr[i4];
            if (obj != null) {
                i = obj.hashCode();
            } else {
                i = 0;
            }
            i5 += i ^ i6;
            i4++;
            i3 += 2;
        }
        return i5;
    }

    protected final int indexOf(int i, Object key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int i2 = this._size;
        if (i2 == 0) {
            return -1;
        }
        int binarySearchInternal = ContainerHelpersKt.binarySearchInternal(i2, i, this.hashes);
        if (binarySearchInternal < 0) {
            return binarySearchInternal;
        }
        if (Intrinsics.areEqual(key, this.keyValues[binarySearchInternal << 1])) {
            return binarySearchInternal;
        }
        int i3 = binarySearchInternal + 1;
        while (i3 < i2 && this.hashes[i3] == i) {
            if (Intrinsics.areEqual(key, this.keyValues[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = binarySearchInternal - 1; i4 >= 0 && this.hashes[i4] == i; i4--) {
            if (Intrinsics.areEqual(key, this.keyValues[i4 << 1])) {
                return i4;
            }
        }
        return ~i3;
    }

    protected final int indexOfNull() {
        int i = this._size;
        if (i == 0) {
            return -1;
        }
        int binarySearchInternal = ContainerHelpersKt.binarySearchInternal(i, 0, this.hashes);
        if (binarySearchInternal < 0) {
            return binarySearchInternal;
        }
        if (this.keyValues[binarySearchInternal << 1] == null) {
            return binarySearchInternal;
        }
        int i2 = binarySearchInternal + 1;
        while (i2 < i && this.hashes[i2] == 0) {
            if (this.keyValues[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = binarySearchInternal - 1; i3 >= 0 && this.hashes[i3] == 0; i3--) {
            if (this.keyValues[i3 << 1] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public final String toString() {
        boolean z;
        int i = this._size;
        if (i <= 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            int i4 = i3 << 1;
            Object obj = this.keyValues[i4];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object obj2 = this.keyValues[i4 + 1];
            if (obj2 != this) {
                sb.append(obj2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "buffer.toString()");
        return sb2;
    }
}
