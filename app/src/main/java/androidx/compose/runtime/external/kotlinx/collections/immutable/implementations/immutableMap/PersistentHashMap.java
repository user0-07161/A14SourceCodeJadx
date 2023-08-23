package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links;
import kotlin.collections.AbstractMap;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PersistentHashMap extends AbstractMap implements PersistentMap {
    private static final PersistentHashMap EMPTY;
    private final TrieNode node;
    private final int size;

    static {
        TrieNode trieNode;
        trieNode = TrieNode.EMPTY;
        EMPTY = new PersistentHashMap(trieNode, 0);
    }

    public PersistentHashMap(TrieNode node, int i) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.node = node;
        this.size = i;
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        int i;
        TrieNode trieNode = this.node;
        if (obj != null) {
            i = obj.hashCode();
        } else {
            i = 0;
        }
        return trieNode.containsKey(i, 0, obj);
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        int i;
        TrieNode trieNode = this.node;
        if (obj != null) {
            i = obj.hashCode();
        } else {
            i = 0;
        }
        return trieNode.get(i, 0, obj);
    }

    public final TrieNode getNode$runtime_release() {
        return this.node;
    }

    @Override // kotlin.collections.AbstractMap
    public final int getSize() {
        return this.size;
    }

    public final PersistentHashMap put(Object obj, Links links) {
        int i;
        TrieNode trieNode = this.node;
        if (obj != null) {
            i = obj.hashCode();
        } else {
            i = 0;
        }
        TrieNode.ModificationResult put = trieNode.put(i, 0, obj, links);
        if (put == null) {
            return this;
        }
        return new PersistentHashMap(put.getNode(), put.getSizeDelta() + this.size);
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public final PersistentHashMap remove(Object obj) {
        int i;
        TrieNode trieNode = this.node;
        if (obj != null) {
            i = obj.hashCode();
        } else {
            i = 0;
        }
        TrieNode remove = trieNode.remove(i, 0, obj);
        if (this.node == remove) {
            return this;
        }
        if (remove == null) {
            PersistentHashMap persistentHashMap = EMPTY;
            Intrinsics.checkNotNull(persistentHashMap, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf>");
            return persistentHashMap;
        }
        return new PersistentHashMap(remove, this.size - 1);
    }
}
