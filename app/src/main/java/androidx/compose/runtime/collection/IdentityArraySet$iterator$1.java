package androidx.compose.runtime.collection;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class IdentityArraySet$iterator$1 implements Iterator, KMappedMarker {
    private int index;
    final /* synthetic */ IdentityArraySet this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdentityArraySet$iterator$1(IdentityArraySet identityArraySet) {
        this.this$0 = identityArraySet;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.index < this.this$0.size()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object[] values = this.this$0.getValues();
        int i = this.index;
        this.index = i + 1;
        Object obj = values[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
