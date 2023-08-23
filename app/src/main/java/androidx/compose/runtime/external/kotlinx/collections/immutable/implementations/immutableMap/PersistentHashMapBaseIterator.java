package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class PersistentHashMapBaseIterator implements Iterator, KMappedMarker {
    private boolean hasNext;
    private final TrieNodeBaseIterator[] path;
    private int pathLastIndex;

    public PersistentHashMapBaseIterator(TrieNode node, TrieNodeBaseIterator[] trieNodeBaseIteratorArr) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.path = trieNodeBaseIteratorArr;
        this.hasNext = true;
        TrieNodeBaseIterator trieNodeBaseIterator = trieNodeBaseIteratorArr[0];
        Object[] buffer = node.getBuffer$runtime_release();
        trieNodeBaseIterator.getClass();
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        trieNodeBaseIterator.reset(node.entryCount$runtime_release() * 2, 0, buffer);
        this.pathLastIndex = 0;
        ensureNextEntryIsReady();
    }

    private final void ensureNextEntryIsReady() {
        TrieNode trieNode;
        if (this.path[this.pathLastIndex].hasNextKey()) {
            return;
        }
        for (int i = this.pathLastIndex; -1 < i; i--) {
            int moveToNextNodeWithData = moveToNextNodeWithData(i);
            if (moveToNextNodeWithData == -1 && this.path[i].hasNextNode()) {
                this.path[i].moveToNextNode();
                moveToNextNodeWithData = moveToNextNodeWithData(i);
            }
            if (moveToNextNodeWithData != -1) {
                this.pathLastIndex = moveToNextNodeWithData;
                return;
            }
            if (i > 0) {
                this.path[i - 1].moveToNextNode();
            }
            TrieNodeBaseIterator trieNodeBaseIterator = this.path[i];
            trieNode = TrieNode.EMPTY;
            Object[] buffer = trieNode.getBuffer$runtime_release();
            trieNodeBaseIterator.getClass();
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            trieNodeBaseIterator.reset(0, 0, buffer);
        }
        this.hasNext = false;
    }

    private final int moveToNextNodeWithData(int i) {
        if (this.path[i].hasNextKey()) {
            return i;
        }
        if (this.path[i].hasNextNode()) {
            TrieNode currentNode = this.path[i].currentNode();
            if (i == 6) {
                TrieNodeBaseIterator trieNodeBaseIterator = this.path[i + 1];
                Object[] buffer = currentNode.getBuffer$runtime_release();
                int length = currentNode.getBuffer$runtime_release().length;
                trieNodeBaseIterator.getClass();
                Intrinsics.checkNotNullParameter(buffer, "buffer");
                trieNodeBaseIterator.reset(length, 0, buffer);
            } else {
                TrieNodeBaseIterator trieNodeBaseIterator2 = this.path[i + 1];
                Object[] buffer2 = currentNode.getBuffer$runtime_release();
                trieNodeBaseIterator2.getClass();
                Intrinsics.checkNotNullParameter(buffer2, "buffer");
                trieNodeBaseIterator2.reset(currentNode.entryCount$runtime_release() * 2, 0, buffer2);
            }
            return moveToNextNodeWithData(i + 1);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object currentKey() {
        if (this.hasNext) {
            return this.path[this.pathLastIndex].currentKey();
        }
        throw new NoSuchElementException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final TrieNodeBaseIterator[] getPath() {
        return this.path;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.hasNext) {
            Object next = this.path[this.pathLastIndex].next();
            ensureNextEntryIsReady();
            return next;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setPathLastIndex(int i) {
        this.pathLastIndex = i;
    }
}
