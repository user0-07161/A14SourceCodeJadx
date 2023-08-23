package androidx.compose.runtime.collection;

import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class IdentityScopeMap {
    private IdentityArraySet[] scopeSets;
    private int size;
    private int[] valueOrder;
    private Object[] values;

    public IdentityScopeMap() {
        int[] iArr = new int[50];
        for (int i = 0; i < 50; i++) {
            iArr[i] = i;
        }
        this.valueOrder = iArr;
        this.values = new Object[50];
        this.scopeSets = new IdentityArraySet[50];
    }

    public static final IdentityArraySet access$scopeSetAt(IdentityScopeMap identityScopeMap, int i) {
        IdentityArraySet identityArraySet = identityScopeMap.scopeSets[identityScopeMap.valueOrder[i]];
        Intrinsics.checkNotNull(identityArraySet);
        return identityArraySet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int find(Object obj) {
        int identityHashCode = System.identityHashCode(obj);
        int i = this.size - 1;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            Object obj2 = this.values[this.valueOrder[i3]];
            Intrinsics.checkNotNull(obj2);
            int identityHashCode2 = System.identityHashCode(obj2);
            if (identityHashCode2 < identityHashCode) {
                i2 = i3 + 1;
            } else if (identityHashCode2 <= identityHashCode) {
                if (obj == obj2) {
                    return i3;
                } else {
                    for (int i4 = i3 - 1; -1 < i4; i4--) {
                        Object obj3 = this.values[this.valueOrder[i4]];
                        Intrinsics.checkNotNull(obj3);
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
                        Object obj4 = this.values[this.valueOrder[i5]];
                        Intrinsics.checkNotNull(obj4);
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

    public final void add(Object value, Object scope) {
        int i;
        IdentityArraySet identityArraySet;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(scope, "scope");
        if (this.size > 0) {
            i = find(value);
            if (i >= 0) {
                identityArraySet = this.scopeSets[this.valueOrder[i]];
                Intrinsics.checkNotNull(identityArraySet);
                identityArraySet.add(scope);
            }
        } else {
            i = -1;
        }
        int i2 = -(i + 1);
        int i3 = this.size;
        int[] iArr = this.valueOrder;
        if (i3 < iArr.length) {
            int i4 = iArr[i3];
            this.values[i4] = value;
            IdentityArraySet[] identityArraySetArr = this.scopeSets;
            IdentityArraySet identityArraySet2 = identityArraySetArr[i4];
            if (identityArraySet2 == null) {
                identityArraySet2 = new IdentityArraySet();
                identityArraySetArr[i4] = identityArraySet2;
            }
            if (i2 < i3) {
                ArraysKt.copyInto(i2 + 1, i2, i3, iArr, iArr);
            }
            this.valueOrder[i2] = i4;
            this.size++;
            identityArraySet = identityArraySet2;
        } else {
            int length = iArr.length * 2;
            Object[] copyOf = Arrays.copyOf(this.scopeSets, length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            IdentityArraySet[] identityArraySetArr2 = (IdentityArraySet[]) copyOf;
            this.scopeSets = identityArraySetArr2;
            IdentityArraySet identityArraySet3 = new IdentityArraySet();
            identityArraySetArr2[i3] = identityArraySet3;
            Object[] copyOf2 = Arrays.copyOf(this.values, length);
            Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
            this.values = copyOf2;
            copyOf2[i3] = value;
            int[] iArr2 = new int[length];
            int i5 = this.size;
            while (true) {
                i5++;
                if (i5 >= length) {
                    break;
                }
                iArr2[i5] = i5;
            }
            int i6 = this.size;
            if (i2 < i6) {
                ArraysKt.copyInto(i2 + 1, i2, i6, this.valueOrder, iArr2);
            }
            iArr2[i2] = i3;
            if (i2 > 0) {
                ArraysKt.copyInto$default(this.valueOrder, iArr2, i2, 6);
            }
            this.valueOrder = iArr2;
            this.size++;
            identityArraySet = identityArraySet3;
        }
        identityArraySet.add(scope);
    }

    public final void clear() {
        int length = this.scopeSets.length;
        for (int i = 0; i < length; i++) {
            IdentityArraySet identityArraySet = this.scopeSets[i];
            if (identityArraySet != null) {
                identityArraySet.clear();
            }
            this.valueOrder[i] = i;
            this.values[i] = null;
        }
        this.size = 0;
    }

    public final boolean contains(Object element) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (find(element) >= 0) {
            return true;
        }
        return false;
    }

    public final IdentityArraySet[] getScopeSets() {
        return this.scopeSets;
    }

    public final int getSize() {
        return this.size;
    }

    public final int[] getValueOrder() {
        return this.valueOrder;
    }

    public final Object[] getValues() {
        return this.values;
    }

    public final boolean remove(Object value, Object scope) {
        int i;
        IdentityArraySet identityArraySet;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(scope, "scope");
        int find = find(value);
        if (find < 0 || (identityArraySet = this.scopeSets[(i = this.valueOrder[find])]) == null) {
            return false;
        }
        boolean remove = identityArraySet.remove(scope);
        if (identityArraySet.size() == 0) {
            int i2 = find + 1;
            int i3 = this.size;
            if (i2 < i3) {
                int[] iArr = this.valueOrder;
                ArraysKt.copyInto(find, i2, i3, iArr, iArr);
            }
            int[] iArr2 = this.valueOrder;
            int i4 = this.size - 1;
            iArr2[i4] = i;
            this.values[i] = null;
            this.size = i4;
        }
        return remove;
    }

    public final void removeScope(Object scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        int i = this.size;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = this.valueOrder[i3];
            IdentityArraySet identityArraySet = this.scopeSets[i4];
            Intrinsics.checkNotNull(identityArraySet);
            identityArraySet.remove(scope);
            if (identityArraySet.size() > 0) {
                if (i2 != i3) {
                    int[] iArr = this.valueOrder;
                    int i5 = iArr[i2];
                    iArr[i2] = i4;
                    iArr[i3] = i5;
                }
                i2++;
            }
        }
        int i6 = this.size;
        for (int i7 = i2; i7 < i6; i7++) {
            this.values[this.valueOrder[i7]] = null;
        }
        this.size = i2;
    }

    public final void setSize(int i) {
        this.size = i;
    }
}
