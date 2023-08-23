package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.EndOfChain;
import java.util.Iterator;
import kotlin.collections.AbstractSet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PersistentOrderedSet extends AbstractSet implements PersistentSet {
    private static final PersistentOrderedSet EMPTY;
    private final Object firstElement;
    private final PersistentHashMap hashMap;
    private final Object lastElement;

    static {
        EndOfChain endOfChain = EndOfChain.INSTANCE;
        PersistentHashMap persistentHashMap = PersistentHashMap.EMPTY;
        Intrinsics.checkNotNull(persistentHashMap, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf>");
        EMPTY = new PersistentOrderedSet(endOfChain, endOfChain, persistentHashMap);
    }

    public PersistentOrderedSet(Object obj, Object obj2, PersistentHashMap persistentHashMap) {
        this.firstElement = obj;
        this.lastElement = obj2;
        this.hashMap = persistentHashMap;
    }

    @Override // java.util.Collection, java.util.Set
    public final PersistentOrderedSet add(Object obj) {
        if (this.hashMap.containsKey(obj)) {
            return this;
        }
        boolean isEmpty = isEmpty();
        EndOfChain endOfChain = EndOfChain.INSTANCE;
        if (isEmpty) {
            return new PersistentOrderedSet(obj, obj, this.hashMap.put(obj, new Links(endOfChain, endOfChain)));
        }
        Object obj2 = this.lastElement;
        Object obj3 = this.hashMap.get(obj2);
        Intrinsics.checkNotNull(obj3);
        return new PersistentOrderedSet(this.firstElement, obj, this.hashMap.put(obj2, ((Links) obj3).withNext(obj)).put(obj, new Links(obj2, endOfChain)));
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return this.hashMap.containsKey(obj);
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        return this.hashMap.getSize();
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new PersistentOrderedSetIterator(this.firstElement, this.hashMap);
    }

    @Override // java.util.Collection, java.util.Set
    public final PersistentOrderedSet remove(Object obj) {
        Object obj2;
        Object obj3;
        Links links = (Links) this.hashMap.get(obj);
        if (links == null) {
            return this;
        }
        PersistentHashMap remove = this.hashMap.remove(obj);
        if (links.getHasPrevious()) {
            Object obj4 = remove.get(links.getPrevious());
            Intrinsics.checkNotNull(obj4);
            remove = remove.put(links.getPrevious(), ((Links) obj4).withNext(links.getNext()));
        }
        if (links.getHasNext()) {
            Object obj5 = remove.get(links.getNext());
            Intrinsics.checkNotNull(obj5);
            remove = remove.put(links.getNext(), ((Links) obj5).withPrevious(links.getPrevious()));
        }
        if (!links.getHasPrevious()) {
            obj2 = links.getNext();
        } else {
            obj2 = this.firstElement;
        }
        if (!links.getHasNext()) {
            obj3 = links.getPrevious();
        } else {
            obj3 = this.lastElement;
        }
        return new PersistentOrderedSet(obj2, obj3, remove);
    }
}
