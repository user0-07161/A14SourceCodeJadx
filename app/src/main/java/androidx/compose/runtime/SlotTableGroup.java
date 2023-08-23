package androidx.compose.runtime;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class SlotTableGroup implements Iterable, KMappedMarker {
    private final int group;
    private final SlotTable table;
    private final int version;

    public SlotTableGroup(int i, int i2, SlotTable table) {
        Intrinsics.checkNotNullParameter(table, "table");
        this.table = table;
        this.group = i;
        this.version = i2;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        if (this.table.getVersion$runtime_release() == this.version) {
            SlotTable slotTable = this.table;
            int i = this.group;
            return new GroupIterator(i + 1, SlotTableKt.access$groupSize(this.group, slotTable.getGroups()) + i, slotTable);
        }
        throw new ConcurrentModificationException();
    }
}
