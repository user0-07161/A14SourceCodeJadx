package androidx.compose.runtime.snapshots;

import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class StateListIterator implements ListIterator, KMappedMarker {
    private int index;
    private final SnapshotStateList list;
    private int modification;

    public StateListIterator(SnapshotStateList list, int i) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
        this.index = i - 1;
        this.modification = list.getModification$runtime_release();
    }

    private final void validateModification() {
        if (this.list.getModification$runtime_release() == this.modification) {
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        validateModification();
        this.list.add(this.index + 1, obj);
        this.index++;
        this.modification = this.list.getModification$runtime_release();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        if (this.index < this.list.size() - 1) {
            return true;
        }
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        if (this.index >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        validateModification();
        int i = this.index + 1;
        SnapshotStateListKt.access$validateRange(i, this.list.size());
        Object obj = this.list.get(i);
        this.index = i;
        return obj;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.index + 1;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        validateModification();
        SnapshotStateListKt.access$validateRange(this.index, this.list.size());
        this.index--;
        return this.list.get(this.index);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.index;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        validateModification();
        this.list.remove(this.index);
        this.index--;
        this.modification = this.list.getModification$runtime_release();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        validateModification();
        this.list.set(this.index, obj);
        this.modification = this.list.getModification$runtime_release();
    }
}
