package androidx.compose.runtime.collection;

import android.util.SparseArray;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class IntMap {
    private final SparseArray sparseArray = new SparseArray(10);

    public final void clear() {
        this.sparseArray.clear();
    }

    public final Object get(int i) {
        return this.sparseArray.get(i);
    }

    public final void set(int i, Object obj) {
        this.sparseArray.put(i, obj);
    }
}
