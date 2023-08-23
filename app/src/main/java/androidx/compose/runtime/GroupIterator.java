package androidx.compose.runtime;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class GroupIterator implements Iterator, KMappedMarker {
    private final int end;
    private int index;
    private final SlotTable table;
    private final int version;

    public GroupIterator(int i, int i2, SlotTable table) {
        Intrinsics.checkNotNullParameter(table, "table");
        this.table = table;
        this.end = i2;
        this.index = i;
        this.version = table.getVersion$runtime_release();
        if (!table.getWriter$runtime_release()) {
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.index < this.end) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.table.getVersion$runtime_release() == this.version) {
            int i = this.index;
            this.index = SlotTableKt.access$groupSize(i, this.table.getGroups()) + i;
            return new SlotTableGroup(i, this.version, this.table);
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
