package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TrieNodeKeysIterator extends TrieNodeBaseIterator {
    @Override // java.util.Iterator
    public final Object next() {
        setIndex(getIndex() + 2);
        return getBuffer()[getIndex() - 2];
    }
}
