package androidx.compose.runtime;

import java.util.ArrayList;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Stack {
    private final ArrayList backing = new ArrayList();

    public final void clear() {
        this.backing.clear();
    }

    public final int getSize() {
        return this.backing.size();
    }

    public final boolean isEmpty() {
        return this.backing.isEmpty();
    }

    public final Object peek() {
        return this.backing.get(getSize() - 1);
    }

    public final Object pop() {
        return this.backing.remove(getSize() - 1);
    }

    public final void push(Object obj) {
        this.backing.add(obj);
    }

    public final Object[] toArray() {
        ArrayList arrayList = this.backing;
        int size = arrayList.size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            objArr[i] = arrayList.get(i);
        }
        return objArr;
    }

    public final Object peek(int i) {
        return this.backing.get(i);
    }
}
