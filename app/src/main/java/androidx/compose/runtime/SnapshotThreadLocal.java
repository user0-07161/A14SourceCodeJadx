package androidx.compose.runtime;

import androidx.compose.runtime.internal.ThreadMap;
import androidx.compose.runtime.internal.ThreadMapKt;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SnapshotThreadLocal {
    private final AtomicReference map = new AtomicReference(ThreadMapKt.getEmptyThreadMap());
    private final Object writeMutex = new Object();

    public final Object get() {
        return ((ThreadMap) this.map.get()).get(Thread.currentThread().getId());
    }

    public final void set(Object obj) {
        long id = Thread.currentThread().getId();
        synchronized (this.writeMutex) {
            ThreadMap threadMap = (ThreadMap) this.map.get();
            if (threadMap.trySet(id, obj)) {
                return;
            }
            this.map.set(threadMap.newWith(id, obj));
        }
    }
}
