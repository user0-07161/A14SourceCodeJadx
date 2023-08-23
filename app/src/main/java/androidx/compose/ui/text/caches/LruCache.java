package androidx.compose.ui.text.caches;

import androidx.compose.ui.text.platform.SynchronizedObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LruCache {
    private int hitCount;
    private int missCount;
    private int size;
    private final SynchronizedObject monitor = new SynchronizedObject();
    private final HashMap map = new HashMap(0, 0.75f);
    private final LinkedHashSet keySet = new LinkedHashSet();

    public final Object get(Object obj) {
        synchronized (this.monitor) {
            Object obj2 = this.map.get(obj);
            if (obj2 != null) {
                this.keySet.remove(obj);
                this.keySet.add(obj);
                this.hitCount++;
                return obj2;
            }
            this.missCount++;
            return null;
        }
    }

    public final Object put(Object obj, Object obj2) {
        Object put;
        Object obj3;
        Object obj4;
        if (obj != null && obj2 != null) {
            synchronized (this.monitor) {
                this.size = size() + 1;
                put = this.map.put(obj, obj2);
                if (put != null) {
                    this.size = size() - 1;
                }
                if (this.keySet.contains(obj)) {
                    this.keySet.remove(obj);
                }
                this.keySet.add(obj);
            }
            while (true) {
                synchronized (this.monitor) {
                    if (size() < 0 || ((this.map.isEmpty() && size() != 0) || this.map.isEmpty() != this.keySet.isEmpty())) {
                        break;
                    } else if (size() > 16 && !this.map.isEmpty()) {
                        LinkedHashSet linkedHashSet = this.keySet;
                        Intrinsics.checkNotNullParameter(linkedHashSet, "<this>");
                        if (linkedHashSet instanceof List) {
                            obj3 = CollectionsKt.first((List) linkedHashSet);
                        } else {
                            Iterator it = linkedHashSet.iterator();
                            if (it.hasNext()) {
                                obj3 = it.next();
                            } else {
                                throw new NoSuchElementException("Collection is empty.");
                            }
                        }
                        obj4 = this.map.get(obj3);
                        if (obj4 != null) {
                            TypeIntrinsics.asMutableMap(this.map).remove(obj3);
                            TypeIntrinsics.asMutableCollection(this.keySet).remove(obj3);
                            int size = size();
                            Intrinsics.checkNotNull(obj3);
                            this.size = size - 1;
                        } else {
                            throw new IllegalStateException("inconsistent state");
                        }
                    } else {
                        obj3 = null;
                        obj4 = null;
                    }
                }
                if (obj3 == null && obj4 == null) {
                    return put;
                }
                Intrinsics.checkNotNull(obj3);
                Intrinsics.checkNotNull(obj4);
            }
            throw new IllegalStateException("map/keySet size inconsistency");
        }
        throw null;
    }

    public final Object remove(Object obj) {
        Object remove;
        obj.getClass();
        synchronized (this.monitor) {
            remove = this.map.remove(obj);
            this.keySet.remove(obj);
            if (remove != null) {
                this.size = size() - 1;
            }
        }
        return remove;
    }

    public final int size() {
        int i;
        synchronized (this.monitor) {
            i = this.size;
        }
        return i;
    }

    public final String toString() {
        int i;
        String str;
        synchronized (this.monitor) {
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
