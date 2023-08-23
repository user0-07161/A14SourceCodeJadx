package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractPersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.SmallPersistentVector;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.AbstractCollection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SnapshotStateList implements List, StateObject, KMutableCollection {
    private StateListStateRecord firstStateRecord = new StateListStateRecord(SmallPersistentVector.access$getEMPTY$cp());

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class StateListStateRecord extends StateRecord {
        private PersistentList list;
        private int modification;

        public StateListStateRecord(PersistentList list) {
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final void assign(StateRecord value) {
            Object obj;
            Intrinsics.checkNotNullParameter(value, "value");
            obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                this.list = ((StateListStateRecord) value).list;
                this.modification = ((StateListStateRecord) value).modification;
            }
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final StateRecord create() {
            return new StateListStateRecord(this.list);
        }

        public final PersistentList getList$runtime_release() {
            return this.list;
        }

        public final int getModification$runtime_release() {
            return this.modification;
        }

        public final void setList$runtime_release(PersistentList persistentList) {
            Intrinsics.checkNotNullParameter(persistentList, "<set-?>");
            this.list = persistentList;
        }

        public final void setModification$runtime_release(int i) {
            this.modification = i;
        }
    }

    private final boolean mutateBoolean(Function1 function1) {
        Object obj;
        int modification$runtime_release;
        PersistentList list$runtime_release;
        Object invoke;
        Object obj2;
        Snapshot currentSnapshot;
        boolean z;
        do {
            obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                modification$runtime_release = stateListStateRecord2.getModification$runtime_release();
                list$runtime_release = stateListStateRecord2.getList$runtime_release();
            }
            Intrinsics.checkNotNull(list$runtime_release);
            PersistentVectorBuilder builder = list$runtime_release.builder();
            invoke = function1.invoke(builder);
            PersistentList build = builder.build();
            if (Intrinsics.areEqual(build, list$runtime_release)) {
                break;
            }
            obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                synchronized (SnapshotKt.getLock()) {
                    currentSnapshot = SnapshotKt.currentSnapshot();
                    StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                    if (stateListStateRecord4.getModification$runtime_release() == modification$runtime_release) {
                        stateListStateRecord4.setList$runtime_release(build);
                        z = true;
                        stateListStateRecord4.setModification$runtime_release(stateListStateRecord4.getModification$runtime_release() + 1);
                    } else {
                        z = false;
                    }
                }
                SnapshotKt.notifyWrite(currentSnapshot, this);
            }
        } while (!z);
        return ((Boolean) invoke).booleanValue();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        Object obj2;
        int modification$runtime_release;
        PersistentList list$runtime_release;
        boolean z;
        Object obj3;
        Snapshot currentSnapshot;
        do {
            obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                modification$runtime_release = stateListStateRecord2.getModification$runtime_release();
                list$runtime_release = stateListStateRecord2.getList$runtime_release();
            }
            Intrinsics.checkNotNull(list$runtime_release);
            PersistentList add = list$runtime_release.add(obj);
            z = false;
            if (Intrinsics.areEqual(add, list$runtime_release)) {
                return false;
            }
            obj3 = SnapshotStateListKt.sync;
            synchronized (obj3) {
                StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                synchronized (SnapshotKt.getLock()) {
                    currentSnapshot = SnapshotKt.currentSnapshot();
                    StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                    if (stateListStateRecord4.getModification$runtime_release() == modification$runtime_release) {
                        stateListStateRecord4.setList$runtime_release(add);
                        stateListStateRecord4.setModification$runtime_release(stateListStateRecord4.getModification$runtime_release() + 1);
                        z = true;
                    }
                }
                SnapshotKt.notifyWrite(currentSnapshot, this);
            }
        } while (!z);
        return true;
    }

    @Override // java.util.List
    public final boolean addAll(final int i, final Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return mutateBoolean(new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateList$addAll$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List it = (List) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it.addAll(i, elements));
            }
        });
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        Object obj;
        Snapshot currentSnapshot;
        obj = SnapshotStateListKt.sync;
        synchronized (obj) {
            StateListStateRecord stateListStateRecord = this.firstStateRecord;
            Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (SnapshotKt.getLock()) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord, this, currentSnapshot);
                stateListStateRecord2.setList$runtime_release(SmallPersistentVector.access$getEMPTY$cp());
                stateListStateRecord2.setModification$runtime_release(stateListStateRecord2.getModification$runtime_release() + 1);
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return ((AbstractPersistentList) getReadable$runtime_release().getList$runtime_release()).contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ((AbstractPersistentList) getReadable$runtime_release().getList$runtime_release()).containsAll(elements);
    }

    @Override // java.util.List
    public final Object get(int i) {
        return getReadable$runtime_release().getList$runtime_release().get(i);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord getFirstStateRecord() {
        return this.firstStateRecord;
    }

    public final int getModification$runtime_release() {
        StateListStateRecord stateListStateRecord = this.firstStateRecord;
        Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        return ((StateListStateRecord) SnapshotKt.current(stateListStateRecord)).getModification$runtime_release();
    }

    public final StateListStateRecord getReadable$runtime_release() {
        StateListStateRecord stateListStateRecord = this.firstStateRecord;
        Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        return (StateListStateRecord) SnapshotKt.readable(stateListStateRecord, this);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return getReadable$runtime_release().getList$runtime_release().indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return ((AbstractCollection) getReadable$runtime_release().getList$runtime_release()).isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return getReadable$runtime_release().getList$runtime_release().lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new StateListIterator(this, 0);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final void prependStateRecord(StateRecord stateRecord) {
        stateRecord.setNext$runtime_release(this.firstStateRecord);
        this.firstStateRecord = (StateListStateRecord) stateRecord;
    }

    @Override // java.util.List
    public final Object remove(int i) {
        Object obj;
        int modification$runtime_release;
        PersistentList list$runtime_release;
        Object obj2;
        Snapshot currentSnapshot;
        boolean z;
        Object obj3 = get(i);
        do {
            obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                modification$runtime_release = stateListStateRecord2.getModification$runtime_release();
                list$runtime_release = stateListStateRecord2.getList$runtime_release();
            }
            Intrinsics.checkNotNull(list$runtime_release);
            PersistentList removeAt = list$runtime_release.removeAt(i);
            if (Intrinsics.areEqual(removeAt, list$runtime_release)) {
                break;
            }
            obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                synchronized (SnapshotKt.getLock()) {
                    currentSnapshot = SnapshotKt.currentSnapshot();
                    StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                    if (stateListStateRecord4.getModification$runtime_release() == modification$runtime_release) {
                        stateListStateRecord4.setList$runtime_release(removeAt);
                        z = true;
                        stateListStateRecord4.setModification$runtime_release(stateListStateRecord4.getModification$runtime_release() + 1);
                    } else {
                        z = false;
                    }
                }
                SnapshotKt.notifyWrite(currentSnapshot, this);
            }
        } while (!z);
        return obj3;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection elements) {
        Object obj;
        int modification$runtime_release;
        PersistentList list$runtime_release;
        boolean z;
        Object obj2;
        Snapshot currentSnapshot;
        Intrinsics.checkNotNullParameter(elements, "elements");
        do {
            obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                modification$runtime_release = stateListStateRecord2.getModification$runtime_release();
                list$runtime_release = stateListStateRecord2.getList$runtime_release();
            }
            Intrinsics.checkNotNull(list$runtime_release);
            PersistentList removeAll = ((AbstractPersistentList) list$runtime_release).removeAll(elements);
            z = false;
            if (!Intrinsics.areEqual(removeAll, list$runtime_release)) {
                obj2 = SnapshotStateListKt.sync;
                synchronized (obj2) {
                    StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
                    Intrinsics.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                    synchronized (SnapshotKt.getLock()) {
                        currentSnapshot = SnapshotKt.currentSnapshot();
                        StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                        if (stateListStateRecord4.getModification$runtime_release() == modification$runtime_release) {
                            stateListStateRecord4.setList$runtime_release(removeAll);
                            stateListStateRecord4.setModification$runtime_release(stateListStateRecord4.getModification$runtime_release() + 1);
                            z = true;
                        }
                    }
                    SnapshotKt.notifyWrite(currentSnapshot, this);
                }
            } else {
                return false;
            }
        } while (!z);
        return true;
    }

    public final void removeRange(int i, int i2) {
        Object obj;
        int modification$runtime_release;
        PersistentList list$runtime_release;
        Object obj2;
        Snapshot currentSnapshot;
        boolean z;
        do {
            obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                modification$runtime_release = stateListStateRecord2.getModification$runtime_release();
                list$runtime_release = stateListStateRecord2.getList$runtime_release();
            }
            Intrinsics.checkNotNull(list$runtime_release);
            PersistentVectorBuilder builder = list$runtime_release.builder();
            builder.subList(i, i2).clear();
            PersistentList build = builder.build();
            if (!Intrinsics.areEqual(build, list$runtime_release)) {
                obj2 = SnapshotStateListKt.sync;
                synchronized (obj2) {
                    StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
                    Intrinsics.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                    synchronized (SnapshotKt.getLock()) {
                        currentSnapshot = SnapshotKt.currentSnapshot();
                        StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                        if (stateListStateRecord4.getModification$runtime_release() == modification$runtime_release) {
                            stateListStateRecord4.setList$runtime_release(build);
                            z = true;
                            stateListStateRecord4.setModification$runtime_release(stateListStateRecord4.getModification$runtime_release() + 1);
                        } else {
                            z = false;
                        }
                    }
                    SnapshotKt.notifyWrite(currentSnapshot, this);
                }
            } else {
                return;
            }
        } while (!z);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(final Collection elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return mutateBoolean(new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateList$retainAll$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List it = (List) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it.retainAll(elements));
            }
        });
    }

    public final int retainAllInRange$runtime_release(int i, int i2, Collection elements) {
        Object obj;
        int modification$runtime_release;
        PersistentList list$runtime_release;
        Object obj2;
        Snapshot currentSnapshot;
        boolean z;
        Intrinsics.checkNotNullParameter(elements, "elements");
        int size = size();
        do {
            obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                modification$runtime_release = stateListStateRecord2.getModification$runtime_release();
                list$runtime_release = stateListStateRecord2.getList$runtime_release();
            }
            Intrinsics.checkNotNull(list$runtime_release);
            PersistentVectorBuilder builder = list$runtime_release.builder();
            builder.subList(i, i2).retainAll(elements);
            PersistentList build = builder.build();
            if (Intrinsics.areEqual(build, list$runtime_release)) {
                break;
            }
            obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                synchronized (SnapshotKt.getLock()) {
                    currentSnapshot = SnapshotKt.currentSnapshot();
                    StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                    if (stateListStateRecord4.getModification$runtime_release() == modification$runtime_release) {
                        stateListStateRecord4.setList$runtime_release(build);
                        z = true;
                        stateListStateRecord4.setModification$runtime_release(stateListStateRecord4.getModification$runtime_release() + 1);
                    } else {
                        z = false;
                    }
                }
                SnapshotKt.notifyWrite(currentSnapshot, this);
            }
        } while (!z);
        return size - size();
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        Object obj2;
        int modification$runtime_release;
        PersistentList list$runtime_release;
        Object obj3;
        Snapshot currentSnapshot;
        boolean z;
        Object obj4 = get(i);
        do {
            obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                modification$runtime_release = stateListStateRecord2.getModification$runtime_release();
                list$runtime_release = stateListStateRecord2.getList$runtime_release();
            }
            Intrinsics.checkNotNull(list$runtime_release);
            PersistentList persistentList = list$runtime_release.set(i, obj);
            if (Intrinsics.areEqual(persistentList, list$runtime_release)) {
                break;
            }
            obj3 = SnapshotStateListKt.sync;
            synchronized (obj3) {
                StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                synchronized (SnapshotKt.getLock()) {
                    currentSnapshot = SnapshotKt.currentSnapshot();
                    StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                    if (stateListStateRecord4.getModification$runtime_release() == modification$runtime_release) {
                        stateListStateRecord4.setList$runtime_release(persistentList);
                        z = true;
                        stateListStateRecord4.setModification$runtime_release(stateListStateRecord4.getModification$runtime_release() + 1);
                    } else {
                        z = false;
                    }
                }
                SnapshotKt.notifyWrite(currentSnapshot, this);
            }
        } while (!z);
        return obj4;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return ((AbstractCollection) getReadable$runtime_release().getList$runtime_release()).getSize();
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
        if (!z || i2 > size()) {
            z2 = false;
        }
        if (z2) {
            return new SubList(this, i, i2);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection elements) {
        Object obj;
        int modification$runtime_release;
        PersistentList list$runtime_release;
        boolean z;
        Object obj2;
        Snapshot currentSnapshot;
        Intrinsics.checkNotNullParameter(elements, "elements");
        do {
            obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                modification$runtime_release = stateListStateRecord2.getModification$runtime_release();
                list$runtime_release = stateListStateRecord2.getList$runtime_release();
            }
            Intrinsics.checkNotNull(list$runtime_release);
            PersistentList addAll = list$runtime_release.addAll(elements);
            z = false;
            if (Intrinsics.areEqual(addAll, list$runtime_release)) {
                return false;
            }
            obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                synchronized (SnapshotKt.getLock()) {
                    currentSnapshot = SnapshotKt.currentSnapshot();
                    StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                    if (stateListStateRecord4.getModification$runtime_release() == modification$runtime_release) {
                        stateListStateRecord4.setList$runtime_release(addAll);
                        stateListStateRecord4.setModification$runtime_release(stateListStateRecord4.getModification$runtime_release() + 1);
                        z = true;
                    }
                }
                SnapshotKt.notifyWrite(currentSnapshot, this);
            }
        } while (!z);
        return true;
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        return new StateListIterator(this, i);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return CollectionToArray.toArray(this, array);
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        Object obj2;
        int modification$runtime_release;
        PersistentList list$runtime_release;
        Object obj3;
        Snapshot currentSnapshot;
        boolean z;
        do {
            obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                modification$runtime_release = stateListStateRecord2.getModification$runtime_release();
                list$runtime_release = stateListStateRecord2.getList$runtime_release();
            }
            Intrinsics.checkNotNull(list$runtime_release);
            PersistentList add = list$runtime_release.add(i, obj);
            if (Intrinsics.areEqual(add, list$runtime_release)) {
                return;
            }
            obj3 = SnapshotStateListKt.sync;
            synchronized (obj3) {
                StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                synchronized (SnapshotKt.getLock()) {
                    currentSnapshot = SnapshotKt.currentSnapshot();
                    StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                    if (stateListStateRecord4.getModification$runtime_release() == modification$runtime_release) {
                        stateListStateRecord4.setList$runtime_release(add);
                        z = true;
                        stateListStateRecord4.setModification$runtime_release(stateListStateRecord4.getModification$runtime_release() + 1);
                    } else {
                        z = false;
                    }
                }
                SnapshotKt.notifyWrite(currentSnapshot, this);
            }
        } while (!z);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        Object obj2;
        int modification$runtime_release;
        PersistentList list$runtime_release;
        boolean z;
        Object obj3;
        Snapshot currentSnapshot;
        do {
            obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                modification$runtime_release = stateListStateRecord2.getModification$runtime_release();
                list$runtime_release = stateListStateRecord2.getList$runtime_release();
            }
            Intrinsics.checkNotNull(list$runtime_release);
            AbstractPersistentList abstractPersistentList = (AbstractPersistentList) list$runtime_release;
            int indexOf = abstractPersistentList.indexOf(obj);
            PersistentList persistentList = abstractPersistentList;
            if (indexOf != -1) {
                persistentList = abstractPersistentList.removeAt(indexOf);
            }
            z = false;
            if (Intrinsics.areEqual(persistentList, list$runtime_release)) {
                return false;
            }
            obj3 = SnapshotStateListKt.sync;
            synchronized (obj3) {
                StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
                Intrinsics.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                synchronized (SnapshotKt.getLock()) {
                    currentSnapshot = SnapshotKt.currentSnapshot();
                    StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                    if (stateListStateRecord4.getModification$runtime_release() == modification$runtime_release) {
                        stateListStateRecord4.setList$runtime_release(persistentList);
                        stateListStateRecord4.setModification$runtime_release(stateListStateRecord4.getModification$runtime_release() + 1);
                        z = true;
                    }
                }
                SnapshotKt.notifyWrite(currentSnapshot, this);
            }
        } while (!z);
        return true;
    }
}
