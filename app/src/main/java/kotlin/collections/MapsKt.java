package kotlin.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class MapsKt extends MapsKt___MapsJvmKt {
    public static Map emptyMap() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        Intrinsics.checkNotNull(emptyMap, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return emptyMap;
    }

    public static int mapCapacity(int i) {
        if (i >= 0) {
            if (i < 3) {
                return i + 1;
            }
            if (i < 1073741824) {
                return (int) ((i / 0.75f) + 1.0f);
            }
            return Integer.MAX_VALUE;
        }
        return i;
    }

    public static Map mapOf(Pair... pairArr) {
        if (pairArr.length > 0) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity(pairArr.length));
            MapsKt___MapsJvmKt.putAll(linkedHashMap, pairArr);
            return linkedHashMap;
        }
        return emptyMap();
    }

    public static Map toMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        int size = map.size();
        if (size != 0) {
            if (size != 1) {
                return new LinkedHashMap(map);
            }
            return MapsKt__MapsJVMKt.toSingletonMap(map);
        }
        return emptyMap();
    }

    public static Map toMap(Iterable iterable) {
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size != 0) {
            if (size != 1) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity(collection.size()));
                MapsKt___MapsJvmKt.toMap(iterable, linkedHashMap);
                return linkedHashMap;
            }
            Pair pair = (Pair) ((List) iterable).get(0);
            Intrinsics.checkNotNullParameter(pair, "pair");
            Map singletonMap = Collections.singletonMap(pair.getFirst(), pair.getSecond());
            Intrinsics.checkNotNullExpressionValue(singletonMap, "singletonMap(pair.first, pair.second)");
            return singletonMap;
        }
        return emptyMap();
    }
}
