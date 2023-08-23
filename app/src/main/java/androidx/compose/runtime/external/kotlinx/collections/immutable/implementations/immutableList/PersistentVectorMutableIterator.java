package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PersistentVectorMutableIterator extends AbstractListIterator {
    private final PersistentVectorBuilder builder;
    private int expectedModCount;
    private int lastIteratedIndex;
    private TrieIterator trieIterator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersistentVectorMutableIterator(PersistentVectorBuilder builder, int i) {
        super(i, builder.getSize());
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
        this.expectedModCount = builder.getModCount$runtime_release();
        this.lastIteratedIndex = -1;
        setupTrieIterator();
    }

    private final void checkForComodification() {
        if (this.expectedModCount == this.builder.getModCount$runtime_release()) {
            return;
        }
        throw new ConcurrentModificationException();
    }

    private final void setupTrieIterator() {
        Object[] root$runtime_release = this.builder.getRoot$runtime_release();
        if (root$runtime_release == null) {
            this.trieIterator = null;
            return;
        }
        int size = (this.builder.getSize() - 1) & (-32);
        int index = getIndex();
        if (index > size) {
            index = size;
        }
        int rootShift$runtime_release = (this.builder.getRootShift$runtime_release() / 5) + 1;
        TrieIterator trieIterator = this.trieIterator;
        if (trieIterator == null) {
            this.trieIterator = new TrieIterator(root$runtime_release, index, size, rootShift$runtime_release);
            return;
        }
        Intrinsics.checkNotNull(trieIterator);
        trieIterator.reset$runtime_release(root$runtime_release, index, size, rootShift$runtime_release);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractListIterator, java.util.ListIterator
    public final void add(Object obj) {
        checkForComodification();
        this.builder.add(getIndex(), obj);
        setIndex(getIndex() + 1);
        setSize(this.builder.getSize());
        this.expectedModCount = this.builder.getModCount$runtime_release();
        this.lastIteratedIndex = -1;
        setupTrieIterator();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        checkForComodification();
        if (hasNext()) {
            this.lastIteratedIndex = getIndex();
            TrieIterator trieIterator = this.trieIterator;
            if (trieIterator == null) {
                Object[] tail$runtime_release = this.builder.getTail$runtime_release();
                int index = getIndex();
                setIndex(index + 1);
                return tail$runtime_release[index];
            } else if (trieIterator.hasNext()) {
                setIndex(getIndex() + 1);
                return trieIterator.next();
            } else {
                Object[] tail$runtime_release2 = this.builder.getTail$runtime_release();
                int index2 = getIndex();
                setIndex(index2 + 1);
                return tail$runtime_release2[index2 - trieIterator.getSize()];
            }
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        checkForComodification();
        if (hasPrevious()) {
            this.lastIteratedIndex = getIndex() - 1;
            TrieIterator trieIterator = this.trieIterator;
            if (trieIterator == null) {
                Object[] tail$runtime_release = this.builder.getTail$runtime_release();
                setIndex(getIndex() - 1);
                return tail$runtime_release[getIndex()];
            } else if (getIndex() > trieIterator.getSize()) {
                Object[] tail$runtime_release2 = this.builder.getTail$runtime_release();
                setIndex(getIndex() - 1);
                return tail$runtime_release2[getIndex() - trieIterator.getSize()];
            } else {
                setIndex(getIndex() - 1);
                return trieIterator.previous();
            }
        }
        throw new NoSuchElementException();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractListIterator, java.util.ListIterator, java.util.Iterator
    public final void remove() {
        checkForComodification();
        int i = this.lastIteratedIndex;
        if (i != -1) {
            this.builder.removeAt(i);
            if (this.lastIteratedIndex < getIndex()) {
                setIndex(this.lastIteratedIndex);
            }
            setSize(this.builder.getSize());
            this.expectedModCount = this.builder.getModCount$runtime_release();
            this.lastIteratedIndex = -1;
            setupTrieIterator();
            return;
        }
        throw new IllegalStateException();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractListIterator, java.util.ListIterator
    public final void set(Object obj) {
        checkForComodification();
        int i = this.lastIteratedIndex;
        if (i != -1) {
            this.builder.set(i, obj);
            this.expectedModCount = this.builder.getModCount$runtime_release();
            setupTrieIterator();
            return;
        }
        throw new IllegalStateException();
    }
}
