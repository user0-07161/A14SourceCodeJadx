package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet;

import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PersistentOrderedSetIterator implements Iterator, KMappedMarker {
    private int index;
    private final Map map;
    private Object nextElement;

    public PersistentOrderedSetIterator(Object obj, PersistentHashMap map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.nextElement = obj;
        this.map = map;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.index < this.map.size()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            Object obj = this.nextElement;
            this.index++;
            Object obj2 = this.map.get(obj);
            if (obj2 != null) {
                this.nextElement = ((Links) obj2).getNext();
                return obj;
            }
            throw new ConcurrentModificationException("Hash code of an element (" + obj + ") has changed after it was added to the persistent set.");
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
