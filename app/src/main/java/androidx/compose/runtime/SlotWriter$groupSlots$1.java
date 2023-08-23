package androidx.compose.runtime;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SlotWriter$groupSlots$1 implements Iterator, KMappedMarker {
    final /* synthetic */ int $end;
    private int current;
    final /* synthetic */ SlotWriter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SlotWriter$groupSlots$1(int i, int i2, SlotWriter slotWriter) {
        this.$end = i2;
        this.this$0 = slotWriter;
        this.current = i;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.current < this.$end) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int dataIndexToDataAddress;
        if (hasNext()) {
            Object[] objArr = this.this$0.slots;
            SlotWriter slotWriter = this.this$0;
            int i = this.current;
            this.current = i + 1;
            dataIndexToDataAddress = slotWriter.dataIndexToDataAddress(i);
            return objArr[dataIndexToDataAddress];
        }
        return null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
