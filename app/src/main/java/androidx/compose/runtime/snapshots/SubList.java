package androidx.compose.runtime.snapshots;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.RangesKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SubList implements List, KMutableCollection {
    private int modification;
    private final int offset;
    private final SnapshotStateList parentList;
    private int size;

    public SubList(SnapshotStateList parentList, int i, int i2) {
        Intrinsics.checkNotNullParameter(parentList, "parentList");
        this.parentList = parentList;
        this.offset = i;
        this.modification = parentList.getModification$runtime_release();
        this.size = i2 - i;
    }

    private final void validateModification() {
        if (this.parentList.getModification$runtime_release() == this.modification) {
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        validateModification();
        this.parentList.add(this.offset + this.size, obj);
        this.size++;
        this.modification = this.parentList.getModification$runtime_release();
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return addAll(this.size, elements);
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        if (this.size > 0) {
            validateModification();
            SnapshotStateList snapshotStateList = this.parentList;
            int i = this.offset;
            snapshotStateList.removeRange(i, this.size + i);
            this.size = 0;
            this.modification = this.parentList.getModification$runtime_release();
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (indexOf(obj) >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return true;
        }
        for (Object obj : elements) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public final Object get(int i) {
        validateModification();
        SnapshotStateListKt.access$validateRange(i, this.size);
        return this.parentList.get(this.offset + i);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        validateModification();
        int i = this.offset;
        Iterator it = RangesKt.until(i, this.size + i).iterator();
        while (it.hasNext()) {
            int nextInt = ((IntProgressionIterator) it).nextInt();
            if (Intrinsics.areEqual(obj, this.parentList.get(nextInt))) {
                return nextInt - this.offset;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        validateModification();
        int i = this.offset + this.size;
        while (true) {
            i--;
            if (i >= this.offset) {
                if (Intrinsics.areEqual(obj, this.parentList.get(i))) {
                    return i - this.offset;
                }
            } else {
                return -1;
            }
        }
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            remove(indexOf);
            return true;
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        while (true) {
            boolean z = false;
            for (Object obj : elements) {
                if (remove(obj) || z) {
                    z = true;
                }
            }
            return z;
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        validateModification();
        SnapshotStateList snapshotStateList = this.parentList;
        int i = this.offset;
        int retainAllInRange$runtime_release = snapshotStateList.retainAllInRange$runtime_release(i, this.size + i, elements);
        if (retainAllInRange$runtime_release > 0) {
            this.modification = this.parentList.getModification$runtime_release();
            this.size -= retainAllInRange$runtime_release;
        }
        if (retainAllInRange$runtime_release > 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        SnapshotStateListKt.access$validateRange(i, this.size);
        validateModification();
        Object obj2 = this.parentList.set(i + this.offset, obj);
        this.modification = this.parentList.getModification$runtime_release();
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.size;
    }

    @Override // java.util.List
    public final List subList(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (i >= 0 && i <= i2) {
            z = true;
        } else {
            z = false;
        }
        if (!z || i2 > this.size) {
            z2 = false;
        }
        if (z2) {
            validateModification();
            SnapshotStateList snapshotStateList = this.parentList;
            int i3 = this.offset;
            return new SubList(snapshotStateList, i + i3, i2 + i3);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        validateModification();
        Ref$IntRef ref$IntRef = new Ref$IntRef();
        ref$IntRef.element = i - 1;
        return new SubList$listIterator$1(ref$IntRef, this);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return CollectionToArray.toArray(this, array);
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        validateModification();
        boolean addAll = this.parentList.addAll(i + this.offset, elements);
        if (addAll) {
            this.size = elements.size() + this.size;
            this.modification = this.parentList.getModification$runtime_release();
        }
        return addAll;
    }

    @Override // java.util.List
    public final Object remove(int i) {
        validateModification();
        Object remove = this.parentList.remove(this.offset + i);
        this.size--;
        this.modification = this.parentList.getModification$runtime_release();
        return remove;
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        validateModification();
        this.parentList.add(this.offset + i, obj);
        this.size++;
        this.modification = this.parentList.getModification$runtime_release();
    }
}
