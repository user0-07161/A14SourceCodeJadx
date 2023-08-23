package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MutableVectorWithMutationTracking {
    private final Function0 onVectorMutated;
    private final MutableVector vector;

    public MutableVectorWithMutationTracking(MutableVector mutableVector, Function0 function0) {
        this.vector = mutableVector;
        this.onVectorMutated = function0;
    }

    public final void add(int i, Object obj) {
        this.vector.add(i, obj);
        this.onVectorMutated.invoke();
    }

    public final void clear() {
        this.vector.clear();
        this.onVectorMutated.invoke();
    }

    public final Object get(int i) {
        return this.vector.getContent()[i];
    }

    public final int getSize() {
        return this.vector.getSize();
    }

    public final MutableVector getVector() {
        return this.vector;
    }

    public final Object removeAt(int i) {
        Object removeAt = this.vector.removeAt(i);
        this.onVectorMutated.invoke();
        return removeAt;
    }
}
