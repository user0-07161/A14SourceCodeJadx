package androidx.compose.runtime.snapshots;

import java.util.ListIterator;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SubList$listIterator$1 implements ListIterator, KMappedMarker {
    final /* synthetic */ Ref$IntRef $current;
    final /* synthetic */ SubList this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubList$listIterator$1(Ref$IntRef ref$IntRef, SubList subList) {
        this.$current = ref$IntRef;
        this.this$0 = subList;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        int i = SnapshotStateListKt.$r8$clinit;
        throw new IllegalStateException("Cannot modify a state list through an iterator".toString());
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        if (this.$current.element < this.this$0.size() - 1) {
            return true;
        }
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        if (this.$current.element >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        int i = this.$current.element + 1;
        SnapshotStateListKt.access$validateRange(i, this.this$0.size());
        this.$current.element = i;
        return this.this$0.get(i);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.$current.element + 1;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        int i = this.$current.element;
        SnapshotStateListKt.access$validateRange(i, this.this$0.size());
        this.$current.element = i - 1;
        return this.this$0.get(i);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.$current.element;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        int i = SnapshotStateListKt.$r8$clinit;
        throw new IllegalStateException("Cannot modify a state list through an iterator".toString());
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        int i = SnapshotStateListKt.$r8$clinit;
        throw new IllegalStateException("Cannot modify a state list through an iterator".toString());
    }
}
