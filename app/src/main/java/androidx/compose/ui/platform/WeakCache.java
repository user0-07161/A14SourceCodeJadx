package androidx.compose.ui.platform;

import androidx.compose.runtime.collection.MutableVector;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class WeakCache {
    private final MutableVector values = new MutableVector(new Reference[16]);
    private final ReferenceQueue referenceQueue = new ReferenceQueue();

    /* JADX WARN: Removed duplicated region for block: B:8:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object pop() {
        /*
            r2 = this;
        L0:
            java.lang.ref.ReferenceQueue r0 = r2.referenceQueue
            java.lang.ref.Reference r0 = r0.poll()
            androidx.compose.runtime.collection.MutableVector r1 = r2.values
            if (r0 == 0) goto Ld
            r1.remove(r0)
        Ld:
            if (r0 != 0) goto L0
        Lf:
            boolean r2 = r1.isNotEmpty()
            if (r2 == 0) goto L28
            int r2 = r1.getSize()
            int r2 = r2 + (-1)
            java.lang.Object r2 = r1.removeAt(r2)
            java.lang.ref.Reference r2 = (java.lang.ref.Reference) r2
            java.lang.Object r2 = r2.get()
            if (r2 == 0) goto Lf
            return r2
        L28:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.WeakCache.pop():java.lang.Object");
    }

    public final void push(Object obj) {
        ReferenceQueue referenceQueue;
        Reference poll;
        MutableVector mutableVector;
        do {
            referenceQueue = this.referenceQueue;
            poll = referenceQueue.poll();
            mutableVector = this.values;
            if (poll != null) {
                mutableVector.remove(poll);
                continue;
            }
        } while (poll != null);
        mutableVector.add(new WeakReference(obj, referenceQueue));
    }
}
