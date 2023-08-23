package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PersistentVectorIterator extends AbstractListIterator {
    private final Object[] tail;
    private final TrieIterator trieIterator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersistentVectorIterator(Object[] root, Object[] tail, int i, int i2, int i3) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(tail, "tail");
        this.tail = tail;
        int i4 = (i2 - 1) & (-32);
        this.trieIterator = new TrieIterator(root, i > i4 ? i4 : i, i4, i3);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            if (this.trieIterator.hasNext()) {
                setIndex(getIndex() + 1);
                return this.trieIterator.next();
            }
            Object[] objArr = this.tail;
            int index = getIndex();
            setIndex(index + 1);
            return objArr[index - this.trieIterator.getSize()];
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious()) {
            if (getIndex() > this.trieIterator.getSize()) {
                Object[] objArr = this.tail;
                setIndex(getIndex() - 1);
                return objArr[getIndex() - this.trieIterator.getSize()];
            }
            setIndex(getIndex() - 1);
            return this.trieIterator.previous();
        }
        throw new NoSuchElementException();
    }
}
