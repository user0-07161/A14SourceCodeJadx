package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PrioritySet {
    private final List list = new ArrayList();

    public final void add(int i) {
        List list = this.list;
        if ((!list.isEmpty()) && (((Number) list.get(0)).intValue() == i || ((Number) list.get(list.size() - 1)).intValue() == i)) {
            return;
        }
        int size = list.size();
        list.add(Integer.valueOf(i));
        while (size > 0) {
            int i2 = ((size + 1) >>> 1) - 1;
            int intValue = ((Number) list.get(i2)).intValue();
            if (i <= intValue) {
                break;
            }
            list.set(size, Integer.valueOf(intValue));
            size = i2;
        }
        list.set(size, Integer.valueOf(i));
    }

    public final boolean isNotEmpty() {
        return !this.list.isEmpty();
    }

    public final int peek() {
        return ((Number) CollectionsKt.first(this.list)).intValue();
    }

    public final int takeMax() {
        boolean z;
        int intValue;
        List list = this.list;
        if (list.size() > 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            int intValue2 = ((Number) list.get(0)).intValue();
            while ((!list.isEmpty()) && ((Number) list.get(0)).intValue() == intValue2) {
                if (!list.isEmpty()) {
                    list.set(0, list.get(CollectionsKt.getLastIndex(list)));
                    list.remove(list.size() - 1);
                    int size = list.size();
                    int size2 = list.size() >>> 1;
                    int i = 0;
                    while (i < size2) {
                        int intValue3 = ((Number) list.get(i)).intValue();
                        int i2 = (i + 1) * 2;
                        int i3 = i2 - 1;
                        int intValue4 = ((Number) list.get(i3)).intValue();
                        if (i2 < size && (intValue = ((Number) list.get(i2)).intValue()) > intValue4) {
                            if (intValue > intValue3) {
                                list.set(i, Integer.valueOf(intValue));
                                list.set(i2, Integer.valueOf(intValue3));
                                i = i2;
                            }
                        } else if (intValue4 > intValue3) {
                            list.set(i, Integer.valueOf(intValue4));
                            list.set(i3, Integer.valueOf(intValue3));
                            i = i3;
                        }
                    }
                } else {
                    throw new NoSuchElementException("List is empty.");
                }
            }
            return intValue2;
        }
        ComposerKt.composeRuntimeError("Set is empty".toString());
        throw null;
    }
}
