package kotlin.collections;

import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapEntries;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapKeys;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapValues;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AbstractMap implements Map, KMappedMarker {
    public static final String access$toString(AbstractMap abstractMap, Map.Entry entry) {
        String valueOf;
        abstractMap.getClass();
        StringBuilder sb = new StringBuilder();
        Object key = entry.getKey();
        String str = "(this Map)";
        if (key == abstractMap) {
            valueOf = "(this Map)";
        } else {
            valueOf = String.valueOf(key);
        }
        sb.append(valueOf);
        sb.append('=');
        Object value = entry.getValue();
        if (value != abstractMap) {
            str = String.valueOf(value);
        }
        sb.append(str);
        return sb.toString();
    }

    @Override // java.util.Map
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        Set<Map.Entry> entrySet = entrySet();
        if (entrySet.isEmpty()) {
            return false;
        }
        for (Map.Entry entry : entrySet) {
            if (Intrinsics.areEqual(entry.getValue(), obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return new PersistentHashMapEntries((PersistentHashMap) this);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x005c A[SYNTHETIC] */
    @Override // java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L4
            return r0
        L4:
            boolean r1 = r7 instanceof java.util.Map
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            int r1 = r6.getSize()
            java.util.Map r7 = (java.util.Map) r7
            int r3 = r7.size()
            if (r1 == r3) goto L17
            return r2
        L17:
            java.util.Set r7 = r7.entrySet()
            boolean r1 = r7 instanceof java.util.Collection
            if (r1 == 0) goto L26
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto L26
            goto L5d
        L26:
            java.util.Iterator r7 = r7.iterator()
        L2a:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L5d
            java.lang.Object r1 = r7.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            if (r1 != 0) goto L3a
        L38:
            r1 = r2
            goto L5a
        L3a:
            java.lang.Object r3 = r1.getKey()
            java.lang.Object r1 = r1.getValue()
            r4 = r6
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap r4 = (androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap) r4
            java.lang.Object r5 = r4.get(r3)
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r5)
            if (r1 != 0) goto L50
            goto L38
        L50:
            if (r5 != 0) goto L59
            boolean r1 = r4.containsKey(r3)
            if (r1 != 0) goto L59
            goto L38
        L59:
            r1 = r0
        L5a:
            if (r1 != 0) goto L2a
            r0 = r2
        L5d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.AbstractMap.equals(java.lang.Object):boolean");
    }

    public abstract int getSize();

    @Override // java.util.Map
    public final int hashCode() {
        return entrySet().hashCode();
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        if (getSize() == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Map
    public final Set keySet() {
        return new PersistentHashMapKeys((PersistentHashMap) this);
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    public final String toString() {
        return CollectionsKt.joinToString$default(entrySet(), ", ", "{", "}", new Function1() { // from class: kotlin.collections.AbstractMap$toString$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Map.Entry it = (Map.Entry) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                return AbstractMap.access$toString(AbstractMap.this, it);
            }
        }, 24);
    }

    @Override // java.util.Map
    public final Collection values() {
        return new PersistentHashMapValues((PersistentHashMap) this);
    }
}
