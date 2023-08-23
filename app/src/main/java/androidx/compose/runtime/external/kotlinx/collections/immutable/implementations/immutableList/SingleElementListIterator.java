package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import java.util.NoSuchElementException;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SingleElementListIterator extends AbstractListIterator {
    private final Object element;

    public SingleElementListIterator(int i, Object obj) {
        super(i, 1);
        this.element = obj;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            setIndex(getIndex() + 1);
            return this.element;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious()) {
            setIndex(getIndex() - 1);
            return this.element;
        }
        throw new NoSuchElementException();
    }
}
