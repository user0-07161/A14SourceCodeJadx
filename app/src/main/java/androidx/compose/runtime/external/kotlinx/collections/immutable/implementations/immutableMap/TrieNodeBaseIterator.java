package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TrieNodeBaseIterator implements Iterator, KMappedMarker {
    private Object[] buffer;
    private int dataSize;
    private int index;

    public TrieNodeBaseIterator() {
        TrieNode trieNode;
        int i = TrieNode.$r8$clinit;
        trieNode = TrieNode.EMPTY;
        this.buffer = trieNode.getBuffer$runtime_release();
    }

    public final Object currentKey() {
        return this.buffer[this.index];
    }

    public final TrieNode currentNode() {
        hasNextNode();
        Object obj = this.buffer[this.index];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNodeBaseIterator, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNodeBaseIterator>");
        return (TrieNode) obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object[] getBuffer() {
        return this.buffer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getIndex() {
        return this.index;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return hasNextKey();
    }

    public final boolean hasNextKey() {
        if (this.index < this.dataSize) {
            return true;
        }
        return false;
    }

    public final boolean hasNextNode() {
        if (this.index < this.buffer.length) {
            return true;
        }
        return false;
    }

    public final void moveToNextKey() {
        this.index += 2;
    }

    public final void moveToNextNode() {
        hasNextNode();
        this.index++;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void reset(int i, int i2, Object[] buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.buffer = buffer;
        this.dataSize = i;
        this.index = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setIndex(int i) {
        this.index = i;
    }
}
