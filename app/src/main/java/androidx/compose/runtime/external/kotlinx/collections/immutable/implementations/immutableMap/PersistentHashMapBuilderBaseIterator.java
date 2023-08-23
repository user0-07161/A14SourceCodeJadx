package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.ConcurrentModificationException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class PersistentHashMapBuilderBaseIterator extends PersistentHashMapBaseIterator {
    private final PersistentHashMapBuilder builder;
    private int expectedModCount;
    private Object lastIteratedKey;
    private boolean nextWasInvoked;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersistentHashMapBuilderBaseIterator(PersistentHashMapBuilder builder, TrieNodeBaseIterator[] trieNodeBaseIteratorArr) {
        super(builder.getNode$runtime_release(), trieNodeBaseIteratorArr);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
        this.expectedModCount = builder.getModCount$runtime_release();
    }

    private final void resetPath(int i, TrieNode trieNode, Object obj, int i2) {
        int i3 = i2 * 5;
        if (i3 > 30) {
            getPath()[i2].reset(trieNode.getBuffer$runtime_release().length, 0, trieNode.getBuffer$runtime_release());
            while (!Intrinsics.areEqual(getPath()[i2].currentKey(), obj)) {
                getPath()[i2].moveToNextKey();
            }
            setPathLastIndex(i2);
            return;
        }
        int i4 = 1 << ((i >> i3) & 31);
        if (trieNode.hasEntryAt$runtime_release(i4)) {
            int entryKeyIndex$runtime_release = trieNode.entryKeyIndex$runtime_release(i4);
            getPath()[i2].reset(trieNode.entryCount$runtime_release() * 2, entryKeyIndex$runtime_release, trieNode.getBuffer$runtime_release());
            setPathLastIndex(i2);
            return;
        }
        int nodeIndex$runtime_release = trieNode.nodeIndex$runtime_release(i4);
        TrieNode nodeAtIndex$runtime_release = trieNode.nodeAtIndex$runtime_release(nodeIndex$runtime_release);
        getPath()[i2].reset(trieNode.entryCount$runtime_release() * 2, nodeIndex$runtime_release, trieNode.getBuffer$runtime_release());
        resetPath(i, nodeAtIndex$runtime_release, obj, i2 + 1);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBaseIterator, java.util.Iterator
    public final Object next() {
        if (this.builder.getModCount$runtime_release() == this.expectedModCount) {
            this.lastIteratedKey = currentKey();
            this.nextWasInvoked = true;
            return super.next();
        }
        throw new ConcurrentModificationException();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBaseIterator, java.util.Iterator
    public final void remove() {
        int i;
        if (this.nextWasInvoked) {
            if (hasNext()) {
                Object currentKey = currentKey();
                TypeIntrinsics.asMutableMap(this.builder).remove(this.lastIteratedKey);
                if (currentKey != null) {
                    i = currentKey.hashCode();
                } else {
                    i = 0;
                }
                resetPath(i, this.builder.getNode$runtime_release(), currentKey, 0);
            } else {
                TypeIntrinsics.asMutableMap(this.builder).remove(this.lastIteratedKey);
            }
            this.lastIteratedKey = null;
            this.nextWasInvoked = false;
            this.expectedModCount = this.builder.getModCount$runtime_release();
            return;
        }
        throw new IllegalStateException();
    }

    public final void setValue(Object obj, Object obj2) {
        int i;
        if (!this.builder.containsKey(obj)) {
            return;
        }
        if (hasNext()) {
            Object currentKey = currentKey();
            this.builder.put(obj, obj2);
            if (currentKey != null) {
                i = currentKey.hashCode();
            } else {
                i = 0;
            }
            resetPath(i, this.builder.getNode$runtime_release(), currentKey, 0);
        } else {
            this.builder.put(obj, obj2);
        }
        this.expectedModCount = this.builder.getModCount$runtime_release();
    }
}
