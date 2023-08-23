package androidx.collection;

import androidx.collection.internal.Lock;
import androidx.collection.internal.LruHashMap;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LruCache {
    private int hitCount;
    private int missCount;
    private final LruHashMap map = new LruHashMap();
    private final Lock lock = new Lock();

    public final String toString() {
        int i;
        String str;
        synchronized (this.lock) {
            int i2 = this.hitCount;
            int i3 = this.missCount + i2;
            if (i3 != 0) {
                i = (i2 * 100) / i3;
            } else {
                i = 0;
            }
            str = "LruCache[maxSize=16,hits=" + this.hitCount + ",misses=" + this.missCount + ",hitRate=" + i + "%]";
        }
        return str;
    }
}
