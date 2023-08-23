package kotlin.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Pair;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
abstract class MapsKt___MapsJvmKt extends MapsKt__MapsJVMKt {
    public static final void putAll(Map map, Pair[] pairArr) {
        for (Pair pair : pairArr) {
            map.put(pair.component1(), pair.component2());
        }
    }

    public static final void toMap(Iterable iterable, Map map) {
        Iterator it = ((ArrayList) iterable).iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            map.put(pair.component1(), pair.component2());
        }
    }
}
