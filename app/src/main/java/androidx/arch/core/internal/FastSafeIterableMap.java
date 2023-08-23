package androidx.arch.core.internal;

import androidx.arch.core.internal.SafeIterableMap;
import java.util.HashMap;
import java.util.Map;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FastSafeIterableMap extends SafeIterableMap {
    private final HashMap mHashMap = new HashMap();

    public final Map.Entry ceil(Object obj) {
        if (contains(obj)) {
            return ((SafeIterableMap.Entry) this.mHashMap.get(obj)).mPrevious;
        }
        return null;
    }

    public final boolean contains(Object obj) {
        return this.mHashMap.containsKey(obj);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    protected final SafeIterableMap.Entry get(Object obj) {
        return (SafeIterableMap.Entry) this.mHashMap.get(obj);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public final Object putIfAbsent(Object obj, Object obj2) {
        SafeIterableMap.Entry entry = get(obj);
        if (entry != null) {
            return entry.mValue;
        }
        this.mHashMap.put(obj, put(obj, obj2));
        return null;
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public final Object remove(Object obj) {
        Object remove = super.remove(obj);
        this.mHashMap.remove(obj);
        return remove;
    }
}
