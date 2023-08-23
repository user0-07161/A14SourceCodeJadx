package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation;
import java.util.Arrays;
import java.util.ListIterator;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PersistentVector extends AbstractPersistentList {
    private final Object[] root;
    private final int rootShift;
    private final int size;
    private final Object[] tail;

    public PersistentVector(Object[] root, Object[] tail, int i, int i2) {
        boolean z;
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(tail, "tail");
        this.root = root;
        this.tail = tail;
        this.size = i;
        this.rootShift = i2;
        if (getSize() > 32) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(("Trie-based persistent vector should have at least 33 elements, got " + getSize()).toString());
    }

    private static Object[] insertIntoRoot(Object[] objArr, int i, int i2, Object obj, ObjectRef objectRef) {
        Object[] copyOf;
        int i3 = (i2 >> i) & 31;
        if (i == 0) {
            if (i3 == 0) {
                copyOf = new Object[32];
            } else {
                copyOf = Arrays.copyOf(objArr, 32);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            }
            ArraysKt.copyInto(objArr, copyOf, i3 + 1, i3, 31);
            objectRef.setValue(objArr[31]);
            copyOf[i3] = obj;
            return copyOf;
        }
        Object[] copyOf2 = Arrays.copyOf(objArr, 32);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
        int i4 = i - 5;
        Object obj2 = objArr[i3];
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        copyOf2[i3] = insertIntoRoot((Object[]) obj2, i4, i2, obj, objectRef);
        while (true) {
            i3++;
            if (i3 >= 32 || copyOf2[i3] == null) {
                break;
            }
            Object obj3 = objArr[i3];
            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            copyOf2[i3] = insertIntoRoot((Object[]) obj3, i4, 0, objectRef.getValue(), objectRef);
        }
        return copyOf2;
    }

    private final PersistentVector insertIntoTail(Object[] objArr, int i, Object obj) {
        int rootSize = this.size - rootSize();
        Object[] copyOf = Arrays.copyOf(this.tail, 32);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        if (rootSize < 32) {
            ArraysKt.copyInto(this.tail, copyOf, i + 1, i, rootSize);
            copyOf[i] = obj;
            return new PersistentVector(objArr, copyOf, this.size + 1, this.rootShift);
        }
        Object[] objArr2 = this.tail;
        Object obj2 = objArr2[31];
        ArraysKt.copyInto(objArr2, copyOf, i + 1, i, rootSize - 1);
        copyOf[i] = obj;
        Object[] objArr3 = new Object[32];
        objArr3[0] = obj2;
        return pushFilledTail(objArr, copyOf, objArr3);
    }

    private static Object[] pullLastBuffer(Object[] objArr, int i, int i2, ObjectRef objectRef) {
        Object[] pullLastBuffer;
        int i3 = (i2 >> i) & 31;
        if (i == 5) {
            objectRef.setValue(objArr[i3]);
            pullLastBuffer = null;
        } else {
            Object obj = objArr[i3];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            pullLastBuffer = pullLastBuffer((Object[]) obj, i - 5, i2, objectRef);
        }
        if (pullLastBuffer == null && i3 == 0) {
            return null;
        }
        Object[] copyOf = Arrays.copyOf(objArr, 32);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        copyOf[i3] = pullLastBuffer;
        return copyOf;
    }

    private final PersistentVector pushFilledTail(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int i = this.size >> 5;
        int i2 = this.rootShift;
        if (i > (1 << i2)) {
            Object[] objArr4 = new Object[32];
            objArr4[0] = objArr;
            int i3 = i2 + 5;
            return new PersistentVector(pushTail(i3, objArr4, objArr2), objArr3, this.size + 1, i3);
        }
        return new PersistentVector(pushTail(i2, objArr, objArr2), objArr3, this.size + 1, this.rootShift);
    }

    private final Object[] pushTail(int i, Object[] objArr, Object[] objArr2) {
        Object[] objArr3;
        int size = ((getSize() - 1) >> i) & 31;
        if (objArr != null) {
            objArr3 = Arrays.copyOf(objArr, 32);
            Intrinsics.checkNotNullExpressionValue(objArr3, "copyOf(this, newSize)");
        } else {
            objArr3 = new Object[32];
        }
        if (i == 5) {
            objArr3[size] = objArr2;
        } else {
            objArr3[size] = pushTail(i - 5, (Object[]) objArr3[size], objArr2);
        }
        return objArr3;
    }

    private final Object[] removeFromRootAt(Object[] objArr, int i, int i2, ObjectRef objectRef) {
        Object[] copyOf;
        int i3 = 31;
        int i4 = (i2 >> i) & 31;
        if (i == 0) {
            if (i4 == 0) {
                copyOf = new Object[32];
            } else {
                copyOf = Arrays.copyOf(objArr, 32);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            }
            ArraysKt.copyInto(objArr, copyOf, i4, i4 + 1, 32);
            copyOf[31] = objectRef.getValue();
            objectRef.setValue(objArr[i4]);
            return copyOf;
        }
        if (objArr[31] == null) {
            i3 = 31 & ((rootSize() - 1) >> i);
        }
        Object[] copyOf2 = Arrays.copyOf(objArr, 32);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
        int i5 = i - 5;
        int i6 = i4 + 1;
        if (i6 <= i3) {
            while (true) {
                Object obj = copyOf2[i3];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                copyOf2[i3] = removeFromRootAt((Object[]) obj, i5, 0, objectRef);
                if (i3 == i6) {
                    break;
                }
                i3--;
            }
        }
        Object obj2 = copyOf2[i4];
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        copyOf2[i4] = removeFromRootAt((Object[]) obj2, i5, i2, objectRef);
        return copyOf2;
    }

    private final AbstractPersistentList removeFromTailAt(Object[] objArr, int i, int i2, int i3) {
        PersistentVector persistentVector;
        int i4 = this.size - i;
        if (i4 == 1) {
            if (i2 == 0) {
                if (objArr.length == 33) {
                    objArr = Arrays.copyOf(objArr, 32);
                    Intrinsics.checkNotNullExpressionValue(objArr, "copyOf(this, newSize)");
                }
                return new SmallPersistentVector(objArr);
            }
            ObjectRef objectRef = new ObjectRef(null);
            Object[] pullLastBuffer = pullLastBuffer(objArr, i2, i - 1, objectRef);
            Intrinsics.checkNotNull(pullLastBuffer);
            Object value = objectRef.getValue();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            Object[] objArr2 = (Object[]) value;
            if (pullLastBuffer[1] == null) {
                Object obj = pullLastBuffer[0];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                persistentVector = new PersistentVector((Object[]) obj, objArr2, i, i2 - 5);
            } else {
                persistentVector = new PersistentVector(pullLastBuffer, objArr2, i, i2);
            }
            return persistentVector;
        }
        Object[] copyOf = Arrays.copyOf(this.tail, 32);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        int i5 = i4 - 1;
        if (i3 < i5) {
            ArraysKt.copyInto(this.tail, copyOf, i3, i3 + 1, i4);
        }
        copyOf[i5] = null;
        return new PersistentVector(objArr, copyOf, (i + i4) - 1, i2);
    }

    private final int rootSize() {
        return (getSize() - 1) & (-32);
    }

    private static Object[] setInRoot(int i, int i2, Object obj, Object[] objArr) {
        int i3 = (i2 >> i) & 31;
        Object[] copyOf = Arrays.copyOf(objArr, 32);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        if (i == 0) {
            copyOf[i3] = obj;
        } else {
            Object obj2 = copyOf[i3];
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            copyOf[i3] = setInRoot(i - 5, i2, obj, (Object[]) obj2);
        }
        return copyOf;
    }

    @Override // java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList add(int i, Object obj) {
        ListImplementation.checkPositionIndex$runtime_release(i, this.size);
        if (i == this.size) {
            return add(obj);
        }
        int rootSize = rootSize();
        if (i >= rootSize) {
            return insertIntoTail(this.root, i - rootSize, obj);
        }
        ObjectRef objectRef = new ObjectRef(null);
        return insertIntoTail(insertIntoRoot(this.root, this.rootShift, i, obj, objectRef), 0, objectRef.getValue());
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    /* renamed from: builder$1 */
    public final PersistentVectorBuilder builder() {
        return new PersistentVectorBuilder(this, this.root, this.tail, this.rootShift);
    }

    @Override // java.util.List
    public final Object get(int i) {
        Object[] objArr;
        ListImplementation.checkElementIndex$runtime_release(i, getSize());
        if (rootSize() <= i) {
            objArr = this.tail;
        } else {
            Object[] objArr2 = this.root;
            for (int i2 = this.rootShift; i2 > 0; i2 -= 5) {
                Object[] objArr3 = objArr2[(i >> i2) & 31];
                Intrinsics.checkNotNull(objArr3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                objArr2 = objArr3;
            }
            objArr = objArr2;
        }
        return objArr[i & 31];
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        return this.size;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        ListImplementation.checkPositionIndex$runtime_release(i, getSize());
        return new PersistentVectorIterator(this.root, this.tail, i, getSize(), (this.rootShift / 5) + 1);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList removeAll(Function1 function1) {
        PersistentVectorBuilder builder = builder();
        builder.removeAllWithPredicate(function1);
        return builder.build();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList removeAt(int i) {
        ListImplementation.checkElementIndex$runtime_release(i, this.size);
        int rootSize = rootSize();
        if (i >= rootSize) {
            return removeFromTailAt(this.root, rootSize, this.rootShift, i - rootSize);
        }
        return removeFromTailAt(removeFromRootAt(this.root, this.rootShift, i, new ObjectRef(this.tail[0])), rootSize, this.rootShift, 0);
    }

    @Override // kotlin.collections.AbstractList, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList set(int i, Object obj) {
        ListImplementation.checkElementIndex$runtime_release(i, this.size);
        if (rootSize() <= i) {
            Object[] copyOf = Arrays.copyOf(this.tail, 32);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            copyOf[i & 31] = obj;
            return new PersistentVector(this.root, copyOf, this.size, this.rootShift);
        }
        return new PersistentVector(setInRoot(this.rootShift, i, obj, this.root), this.tail, this.size, this.rootShift);
    }

    @Override // java.util.Collection, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList add(Object obj) {
        int rootSize = this.size - rootSize();
        if (rootSize < 32) {
            Object[] copyOf = Arrays.copyOf(this.tail, 32);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            copyOf[rootSize] = obj;
            return new PersistentVector(this.root, copyOf, this.size + 1, this.rootShift);
        }
        Object[] objArr = new Object[32];
        objArr[0] = obj;
        return pushFilledTail(this.root, this.tail, objArr);
    }
}
