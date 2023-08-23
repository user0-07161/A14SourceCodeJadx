package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.AbstractCollection;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PersistentVectorBuilder extends AbstractMutableList implements Collection {
    private MutabilityOwnership ownership;
    private Object[] root;
    private int rootShift;
    private int size;
    private Object[] tail;
    private PersistentList vector;
    private Object[] vectorRoot;
    private Object[] vectorTail;

    public PersistentVectorBuilder(PersistentList vector, Object[] objArr, Object[] vectorTail, int i) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        Intrinsics.checkNotNullParameter(vectorTail, "vectorTail");
        this.vector = vector;
        this.vectorRoot = objArr;
        this.vectorTail = vectorTail;
        this.rootShift = i;
        this.ownership = new MutabilityOwnership();
        this.root = objArr;
        this.tail = vectorTail;
        this.size = ((AbstractCollection) vector).getSize();
    }

    private static void copyToBuffer(Object[] objArr, int i, Iterator it) {
        while (i < 32 && it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
    }

    private final void insertIntoRoot(Collection collection, int i, int i2, Object[][] objArr, int i3, Object[] objArr2) {
        if (this.root != null) {
            int i4 = i >> 5;
            AbstractListIterator leafBufferIterator = leafBufferIterator(rootSize() >> 5);
            int i5 = i3;
            Object[] objArr3 = objArr2;
            while (leafBufferIterator.previousIndex() != i4) {
                Object[] objArr4 = (Object[]) leafBufferIterator.previous();
                ArraysKt.copyInto(objArr4, objArr3, 0, 32 - i2, 32);
                objArr3 = makeMutableShiftingRight(objArr4, i2);
                i5--;
                objArr[i5] = objArr3;
            }
            Object[] objArr5 = (Object[]) leafBufferIterator.previous();
            int rootSize = i3 - (((rootSize() >> 5) - 1) - i4);
            if (rootSize < i3) {
                objArr2 = objArr[rootSize];
                Intrinsics.checkNotNull(objArr2);
            }
            splitToBuffers(collection, i, objArr5, 32, objArr, rootSize, objArr2);
            return;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    private final Object[] insertIntoRoot$1(Object[] objArr, int i, int i2, Object obj, ObjectRef objectRef) {
        Object obj2;
        int i3 = (i2 >> i) & 31;
        if (i == 0) {
            objectRef.setValue(objArr[31]);
            Object[] makeMutable = makeMutable(objArr);
            ArraysKt.copyInto(objArr, makeMutable, i3 + 1, i3, 31);
            makeMutable[i3] = obj;
            return makeMutable;
        }
        Object[] makeMutable2 = makeMutable(objArr);
        int i4 = i - 5;
        Object obj3 = makeMutable2[i3];
        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        makeMutable2[i3] = insertIntoRoot$1((Object[]) obj3, i4, i2, obj, objectRef);
        while (true) {
            i3++;
            if (i3 >= 32 || (obj2 = makeMutable2[i3]) == null) {
                break;
            }
            makeMutable2[i3] = insertIntoRoot$1((Object[]) obj2, i4, 0, objectRef.getValue(), objectRef);
        }
        return makeMutable2;
    }

    private final void insertIntoTail(Object[] objArr, int i, Object obj) {
        int tailSize = tailSize();
        Object[] makeMutable = makeMutable(this.tail);
        if (tailSize < 32) {
            ArraysKt.copyInto(this.tail, makeMutable, i + 1, i, tailSize);
            makeMutable[i] = obj;
            this.root = objArr;
            this.tail = makeMutable;
            this.size++;
            return;
        }
        Object[] objArr2 = this.tail;
        Object obj2 = objArr2[31];
        ArraysKt.copyInto(objArr2, makeMutable, i + 1, i, 31);
        makeMutable[i] = obj;
        pushFilledTail(objArr, makeMutable, mutableBufferWith(obj2));
    }

    private final boolean isMutable(Object[] objArr) {
        if (objArr.length == 33 && objArr[32] == this.ownership) {
            return true;
        }
        return false;
    }

    private final AbstractListIterator leafBufferIterator(int i) {
        if (this.root != null) {
            int rootSize = rootSize() >> 5;
            ListImplementation.checkPositionIndex$runtime_release(i, rootSize);
            int i2 = this.rootShift;
            if (i2 == 0) {
                Object[] objArr = this.root;
                Intrinsics.checkNotNull(objArr);
                return new SingleElementListIterator(i, objArr);
            }
            Object[] objArr2 = this.root;
            Intrinsics.checkNotNull(objArr2);
            return new TrieIterator(objArr2, i, rootSize, i2 / 5);
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    private final Object[] makeMutable(Object[] objArr) {
        int i;
        if (objArr == null) {
            return mutableBuffer();
        }
        if (isMutable(objArr)) {
            return objArr;
        }
        Object[] mutableBuffer = mutableBuffer();
        int length = objArr.length;
        if (length > 32) {
            i = 32;
        } else {
            i = length;
        }
        ArraysKt.copyInto$default(objArr, mutableBuffer, 0, 0, i, 6);
        return mutableBuffer;
    }

    private final Object[] makeMutableShiftingRight(Object[] objArr, int i) {
        if (isMutable(objArr)) {
            ArraysKt.copyInto(objArr, objArr, i, 0, 32 - i);
            return objArr;
        }
        Object[] mutableBuffer = mutableBuffer();
        ArraysKt.copyInto(objArr, mutableBuffer, i, 0, 32 - i);
        return mutableBuffer;
    }

    private final Object[] mutableBuffer() {
        Object[] objArr = new Object[33];
        objArr[32] = this.ownership;
        return objArr;
    }

    private final Object[] mutableBufferWith(Object obj) {
        Object[] objArr = new Object[33];
        objArr[0] = obj;
        objArr[32] = this.ownership;
        return objArr;
    }

    private final Object[] nullifyAfter(int i, int i2, Object[] objArr) {
        boolean z;
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (i2 == 0) {
                return objArr;
            }
            int i3 = (i >> i2) & 31;
            Object obj = objArr[i3];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            Object nullifyAfter = nullifyAfter(i, i2 - 5, (Object[]) obj);
            if (i3 < 31) {
                int i4 = i3 + 1;
                if (objArr[i4] != null) {
                    if (isMutable(objArr)) {
                        Arrays.fill(objArr, i4, 32, (Object) null);
                    }
                    Object[] mutableBuffer = mutableBuffer();
                    ArraysKt.copyInto(objArr, mutableBuffer, 0, 0, i4);
                    objArr = mutableBuffer;
                }
            }
            if (nullifyAfter != objArr[i3]) {
                Object[] makeMutable = makeMutable(objArr);
                makeMutable[i3] = nullifyAfter;
                return makeMutable;
            }
            return objArr;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private final Object[] pullLastBuffer$1(Object[] objArr, int i, int i2, ObjectRef objectRef) {
        Object[] pullLastBuffer$1;
        int i3 = ((i2 - 1) >> i) & 31;
        if (i == 5) {
            objectRef.setValue(objArr[i3]);
            pullLastBuffer$1 = null;
        } else {
            Object obj = objArr[i3];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            pullLastBuffer$1 = pullLastBuffer$1((Object[]) obj, i - 5, i2, objectRef);
        }
        if (pullLastBuffer$1 == null && i3 == 0) {
            return null;
        }
        Object[] makeMutable = makeMutable(objArr);
        makeMutable[i3] = pullLastBuffer$1;
        return makeMutable;
    }

    private final void pullLastBufferFromRoot(int i, int i2, Object[] objArr) {
        if (i2 == 0) {
            this.root = null;
            if (objArr == null) {
                objArr = new Object[0];
            }
            this.tail = objArr;
            this.size = i;
            this.rootShift = i2;
            return;
        }
        ObjectRef objectRef = new ObjectRef(null);
        Intrinsics.checkNotNull(objArr);
        Object[] pullLastBuffer$1 = pullLastBuffer$1(objArr, i2, i, objectRef);
        Intrinsics.checkNotNull(pullLastBuffer$1);
        Object value = objectRef.getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        this.tail = (Object[]) value;
        this.size = i;
        if (pullLastBuffer$1[1] == null) {
            this.root = (Object[]) pullLastBuffer$1[0];
            this.rootShift = i2 - 5;
            return;
        }
        this.root = pullLastBuffer$1;
        this.rootShift = i2;
    }

    private final Object[] pushBuffers(Object[] objArr, int i, int i2, Iterator it) {
        boolean z;
        if (it.hasNext()) {
            if (i2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                if (i2 == 0) {
                    return (Object[]) it.next();
                }
                Object[] makeMutable = makeMutable(objArr);
                int i3 = (i >> i2) & 31;
                int i4 = i2 - 5;
                makeMutable[i3] = pushBuffers((Object[]) makeMutable[i3], i, i4, it);
                while (true) {
                    i3++;
                    if (i3 >= 32 || !it.hasNext()) {
                        break;
                    }
                    makeMutable[i3] = pushBuffers((Object[]) makeMutable[i3], 0, i4, it);
                }
                return makeMutable;
            }
            throw new IllegalStateException("Check failed.".toString());
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private final Object[] pushBuffersIncreasingHeightIfNeeded(Object[] objArr, int i, Object[][] objArr2) {
        Object[] makeMutable;
        Iterator it = ArrayIteratorKt.iterator(objArr2);
        int i2 = i >> 5;
        int i3 = this.rootShift;
        if (i2 < (1 << i3)) {
            makeMutable = pushBuffers(objArr, i, i3, it);
        } else {
            makeMutable = makeMutable(objArr);
        }
        while (it.hasNext()) {
            this.rootShift += 5;
            makeMutable = mutableBufferWith(makeMutable);
            int i4 = this.rootShift;
            pushBuffers(makeMutable, 1 << i4, i4, it);
        }
        return makeMutable;
    }

    private final void pushFilledTail(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int i = this.size;
        int i2 = i >> 5;
        int i3 = this.rootShift;
        if (i2 > (1 << i3)) {
            this.root = pushTail(this.rootShift + 5, mutableBufferWith(objArr), objArr2);
            this.tail = objArr3;
            this.rootShift += 5;
            this.size++;
        } else if (objArr == null) {
            this.root = objArr2;
            this.tail = objArr3;
            this.size = i + 1;
        } else {
            this.root = pushTail(i3, objArr, objArr2);
            this.tail = objArr3;
            this.size++;
        }
    }

    private final Object[] pushTail(int i, Object[] objArr, Object[] objArr2) {
        int size = ((getSize() - 1) >> i) & 31;
        Object[] makeMutable = makeMutable(objArr);
        if (i == 5) {
            makeMutable[size] = objArr2;
        } else {
            makeMutable[size] = pushTail(i - 5, (Object[]) makeMutable[size], objArr2);
        }
        return makeMutable;
    }

    private final int recyclableRemoveAll(Function1 function1, Object[] objArr, int i, int i2, ObjectRef objectRef, List list, List list2) {
        Object[] mutableBuffer;
        if (isMutable(objArr)) {
            ((ArrayList) list).add(objArr);
        }
        Object value = objectRef.getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr2 = (Object[]) value;
        Object[] objArr3 = objArr2;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (!((Boolean) function1.invoke(obj)).booleanValue()) {
                if (i2 == 32) {
                    ArrayList arrayList = (ArrayList) list;
                    if (!arrayList.isEmpty()) {
                        mutableBuffer = (Object[]) arrayList.remove(arrayList.size() - 1);
                    } else {
                        mutableBuffer = mutableBuffer();
                    }
                    objArr3 = mutableBuffer;
                    i2 = 0;
                }
                objArr3[i2] = obj;
                i2++;
            }
        }
        objectRef.setValue(objArr3);
        if (objArr2 != objectRef.getValue()) {
            ((ArrayList) list2).add(objArr2);
        }
        return i2;
    }

    private final int removeAllFromTail(Function1 function1, int i, ObjectRef objectRef) {
        int removeAll = removeAll(function1, this.tail, i, objectRef);
        if (removeAll == i) {
            return i;
        }
        Object value = objectRef.getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr = (Object[]) value;
        Arrays.fill(objArr, removeAll, i, (Object) null);
        this.tail = objArr;
        this.size -= i - removeAll;
        return removeAll;
    }

    private final Object[] removeFromRootAt(Object[] objArr, int i, int i2, ObjectRef objectRef) {
        int i3 = 31;
        int i4 = (i2 >> i) & 31;
        if (i == 0) {
            Object obj = objArr[i4];
            Object[] makeMutable = makeMutable(objArr);
            ArraysKt.copyInto(objArr, makeMutable, i4, i4 + 1, 32);
            makeMutable[31] = objectRef.getValue();
            objectRef.setValue(obj);
            return makeMutable;
        }
        if (objArr[31] == null) {
            i3 = 31 & ((rootSize() - 1) >> i);
        }
        Object[] makeMutable2 = makeMutable(objArr);
        int i5 = i - 5;
        int i6 = i4 + 1;
        if (i6 <= i3) {
            while (true) {
                Object obj2 = makeMutable2[i3];
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                makeMutable2[i3] = removeFromRootAt((Object[]) obj2, i5, 0, objectRef);
                if (i3 == i6) {
                    break;
                }
                i3--;
            }
        }
        Object obj3 = makeMutable2[i4];
        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        makeMutable2[i4] = removeFromRootAt((Object[]) obj3, i5, i2, objectRef);
        return makeMutable2;
    }

    private final Object removeFromTailAt(Object[] objArr, int i, int i2, int i3) {
        int i4 = this.size - i;
        if (i4 == 1) {
            Object obj = this.tail[0];
            pullLastBufferFromRoot(i, i2, objArr);
            return obj;
        }
        Object[] objArr2 = this.tail;
        Object obj2 = objArr2[i3];
        Object[] makeMutable = makeMutable(objArr2);
        ArraysKt.copyInto(objArr2, makeMutable, i3, i3 + 1, i4);
        makeMutable[i4 - 1] = null;
        this.root = objArr;
        this.tail = makeMutable;
        this.size = (i + i4) - 1;
        this.rootShift = i2;
        return obj2;
    }

    private final int rootSize() {
        if (getSize() <= 32) {
            return 0;
        }
        return (getSize() - 1) & (-32);
    }

    private final Object[] setInRoot(Object[] objArr, int i, int i2, Object obj, ObjectRef objectRef) {
        int i3 = (i2 >> i) & 31;
        Object[] makeMutable = makeMutable(objArr);
        if (i == 0) {
            if (makeMutable != objArr) {
                ((AbstractList) this).modCount++;
            }
            objectRef.setValue(makeMutable[i3]);
            makeMutable[i3] = obj;
            return makeMutable;
        }
        Object obj2 = makeMutable[i3];
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        makeMutable[i3] = setInRoot((Object[]) obj2, i - 5, i2, obj, objectRef);
        return makeMutable;
    }

    private final void splitToBuffers(Collection collection, int i, Object[] objArr, int i2, Object[][] objArr2, int i3, Object[] objArr3) {
        boolean z;
        Object[] mutableBuffer;
        if (i3 >= 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            Object[] makeMutable = makeMutable(objArr);
            objArr2[0] = makeMutable;
            int i4 = i & 31;
            int size = ((collection.size() + i) - 1) & 31;
            int i5 = (i2 - i4) + size;
            if (i5 < 32) {
                ArraysKt.copyInto(makeMutable, objArr3, size + 1, i4, i2);
            } else {
                int i6 = (i5 - 32) + 1;
                if (i3 == 1) {
                    mutableBuffer = makeMutable;
                } else {
                    mutableBuffer = mutableBuffer();
                    i3--;
                    objArr2[i3] = mutableBuffer;
                }
                int i7 = i2 - i6;
                ArraysKt.copyInto(makeMutable, objArr3, 0, i7, i2);
                ArraysKt.copyInto(makeMutable, mutableBuffer, size + 1, i4, i7);
                objArr3 = mutableBuffer;
            }
            Iterator it = collection.iterator();
            copyToBuffer(makeMutable, i4, it);
            for (int i8 = 1; i8 < i3; i8++) {
                Object[] mutableBuffer2 = mutableBuffer();
                copyToBuffer(mutableBuffer2, 0, it);
                objArr2[i8] = mutableBuffer2;
            }
            copyToBuffer(objArr3, 0, it);
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private final int tailSize() {
        int i = this.size;
        if (i > 32) {
            return i - ((i - 1) & (-32));
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        ListImplementation.checkPositionIndex$runtime_release(i, this.size);
        if (i == this.size) {
            add(obj);
            return;
        }
        ((AbstractList) this).modCount++;
        int rootSize = rootSize();
        if (i >= rootSize) {
            insertIntoTail(this.root, i - rootSize, obj);
            return;
        }
        ObjectRef objectRef = new ObjectRef(null);
        Object[] objArr = this.root;
        Intrinsics.checkNotNull(objArr);
        insertIntoTail(insertIntoRoot$1(objArr, this.rootShift, i, obj, objectRef), 0, objectRef.getValue());
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection elements) {
        Object[] mutableBuffer;
        Intrinsics.checkNotNullParameter(elements, "elements");
        ListImplementation.checkPositionIndex$runtime_release(i, this.size);
        if (i == this.size) {
            return addAll(elements);
        }
        if (elements.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int i2 = (i >> 5) << 5;
        int size = ((elements.size() + (this.size - i2)) - 1) / 32;
        if (size == 0) {
            int i3 = i & 31;
            Object[] objArr = this.tail;
            Object[] makeMutable = makeMutable(objArr);
            ArraysKt.copyInto(objArr, makeMutable, (((elements.size() + i) - 1) & 31) + 1, i3, tailSize());
            copyToBuffer(makeMutable, i3, elements.iterator());
            this.tail = makeMutable;
            this.size = elements.size() + this.size;
            return true;
        }
        Object[][] objArr2 = new Object[size];
        int tailSize = tailSize();
        int size2 = elements.size() + this.size;
        if (size2 > 32) {
            size2 -= (size2 - 1) & (-32);
        }
        if (i >= rootSize()) {
            mutableBuffer = mutableBuffer();
            splitToBuffers(elements, i, this.tail, tailSize, objArr2, size, mutableBuffer);
        } else if (size2 > tailSize) {
            int i4 = size2 - tailSize;
            mutableBuffer = makeMutableShiftingRight(this.tail, i4);
            insertIntoRoot(elements, i, i4, objArr2, size, mutableBuffer);
        } else {
            Object[] objArr3 = this.tail;
            mutableBuffer = mutableBuffer();
            int i5 = tailSize - size2;
            ArraysKt.copyInto(objArr3, mutableBuffer, 0, i5, tailSize);
            int i6 = 32 - i5;
            Object[] makeMutableShiftingRight = makeMutableShiftingRight(this.tail, i6);
            int i7 = size - 1;
            objArr2[i7] = makeMutableShiftingRight;
            insertIntoRoot(elements, i, i6, objArr2, i7, makeMutableShiftingRight);
        }
        this.root = pushBuffersIncreasingHeightIfNeeded(this.root, i2, objArr2);
        this.tail = mutableBuffer;
        this.size = elements.size() + this.size;
        return true;
    }

    public final PersistentList build() {
        PersistentList persistentVector;
        boolean z;
        Object[] objArr = this.root;
        if (objArr == this.vectorRoot && this.tail == this.vectorTail) {
            persistentVector = this.vector;
        } else {
            this.ownership = new MutabilityOwnership();
            this.vectorRoot = objArr;
            Object[] objArr2 = this.tail;
            this.vectorTail = objArr2;
            if (objArr == null) {
                if (objArr2.length == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    persistentVector = SmallPersistentVector.access$getEMPTY$cp();
                } else {
                    Object[] copyOf = Arrays.copyOf(this.tail, this.size);
                    Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                    persistentVector = new SmallPersistentVector(copyOf);
                }
            } else {
                persistentVector = new PersistentVector(objArr, objArr2, this.size, this.rootShift);
            }
        }
        this.vector = persistentVector;
        return persistentVector;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        Object[] objArr;
        ListImplementation.checkElementIndex$runtime_release(i, getSize());
        if (rootSize() <= i) {
            objArr = this.tail;
        } else {
            Object[] objArr2 = this.root;
            Intrinsics.checkNotNull(objArr2);
            for (int i2 = this.rootShift; i2 > 0; i2 -= 5) {
                Object[] objArr3 = objArr2[(i >> i2) & 31];
                Intrinsics.checkNotNull(objArr3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                objArr2 = objArr3;
            }
            objArr = objArr2;
        }
        return objArr[i & 31];
    }

    public final int getModCount$runtime_release() {
        return ((AbstractList) this).modCount;
    }

    public final Object[] getRoot$runtime_release() {
        return this.root;
    }

    public final int getRootShift$runtime_release() {
        return this.rootShift;
    }

    @Override // kotlin.collections.AbstractMutableList
    public final int getSize() {
        return this.size;
    }

    public final Object[] getTail$runtime_release() {
        return this.tail;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        ListImplementation.checkPositionIndex$runtime_release(i, getSize());
        return new PersistentVectorMutableIterator(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(final Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return removeAllWithPredicate(new Function1() { // from class: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder$removeAll$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(elements.contains(obj));
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0046, code lost:
        if (r0 != r10) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0017, code lost:
        if (removeAllFromTail(r19, r10, r11) != r10) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean removeAllWithPredicate(kotlin.jvm.functions.Function1 r19) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder.removeAllWithPredicate(kotlin.jvm.functions.Function1):boolean");
    }

    @Override // kotlin.collections.AbstractMutableList
    public final Object removeAt(int i) {
        ListImplementation.checkElementIndex$runtime_release(i, getSize());
        ((AbstractList) this).modCount++;
        int rootSize = rootSize();
        if (i >= rootSize) {
            return removeFromTailAt(this.root, rootSize, this.rootShift, i - rootSize);
        }
        ObjectRef objectRef = new ObjectRef(this.tail[0]);
        Object[] objArr = this.root;
        Intrinsics.checkNotNull(objArr);
        removeFromTailAt(removeFromRootAt(objArr, this.rootShift, i, objectRef), rootSize, this.rootShift, 0);
        return objectRef.getValue();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        ListImplementation.checkElementIndex$runtime_release(i, getSize());
        if (rootSize() <= i) {
            Object[] makeMutable = makeMutable(this.tail);
            if (makeMutable != this.tail) {
                ((AbstractList) this).modCount++;
            }
            int i2 = i & 31;
            Object obj2 = makeMutable[i2];
            makeMutable[i2] = obj;
            this.tail = makeMutable;
            return obj2;
        }
        ObjectRef objectRef = new ObjectRef(null);
        Object[] objArr = this.root;
        Intrinsics.checkNotNull(objArr);
        this.root = setInRoot(objArr, this.rootShift, i, obj, objectRef);
        return objectRef.getValue();
    }

    private final int removeAll(Function1 function1, Object[] objArr, int i, ObjectRef objectRef) {
        Object[] objArr2 = objArr;
        int i2 = i;
        boolean z = false;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                if (!z) {
                    objArr2 = makeMutable(objArr);
                    z = true;
                    i2 = i3;
                }
            } else if (z) {
                objArr2[i2] = obj;
                i2++;
            }
        }
        objectRef.setValue(objArr2);
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        ((AbstractList) this).modCount++;
        int tailSize = tailSize();
        if (tailSize < 32) {
            Object[] makeMutable = makeMutable(this.tail);
            makeMutable[tailSize] = obj;
            this.tail = makeMutable;
            this.size = getSize() + 1;
        } else {
            pushFilledTail(this.root, this.tail, mutableBufferWith(obj));
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int tailSize = tailSize();
        Iterator it = elements.iterator();
        if (32 - tailSize >= elements.size()) {
            Object[] makeMutable = makeMutable(this.tail);
            copyToBuffer(makeMutable, tailSize, it);
            this.tail = makeMutable;
            this.size = elements.size() + this.size;
        } else {
            int size = ((elements.size() + tailSize) - 1) / 32;
            Object[][] objArr = new Object[size];
            Object[] makeMutable2 = makeMutable(this.tail);
            copyToBuffer(makeMutable2, tailSize, it);
            objArr[0] = makeMutable2;
            for (int i = 1; i < size; i++) {
                Object[] mutableBuffer = mutableBuffer();
                copyToBuffer(mutableBuffer, 0, it);
                objArr[i] = mutableBuffer;
            }
            this.root = pushBuffersIncreasingHeightIfNeeded(this.root, rootSize(), objArr);
            Object[] mutableBuffer2 = mutableBuffer();
            copyToBuffer(mutableBuffer2, 0, it);
            this.tail = mutableBuffer2;
            this.size = elements.size() + this.size;
        }
        return true;
    }
}
