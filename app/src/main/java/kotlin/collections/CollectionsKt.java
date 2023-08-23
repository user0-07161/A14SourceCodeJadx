package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CollectionsKt extends CollectionsKt___CollectionsKt {
    public static void addAll(Iterable elements, Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            collection.addAll((Collection) elements);
            return;
        }
        for (Object obj : elements) {
            collection.add(obj);
        }
    }

    public static ArrayList arrayListOf(Object... objArr) {
        if (objArr.length == 0) {
            return new ArrayList();
        }
        return new ArrayList(new ArrayAsCollection(objArr, true));
    }

    public static int collectionSizeOrDefault(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return 10;
    }

    public static boolean contains(Iterable iterable, Object obj) {
        int i;
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).contains(obj);
        }
        if (iterable instanceof List) {
            i = ((List) iterable).indexOf(obj);
        } else {
            Iterator it = iterable.iterator();
            int i2 = 0;
            while (true) {
                if (it.hasNext()) {
                    Object next = it.next();
                    if (i2 >= 0) {
                        if (Intrinsics.areEqual(obj, next)) {
                            i = i2;
                            break;
                        }
                        i2++;
                    } else {
                        throwIndexOverflow();
                        throw null;
                    }
                } else {
                    i = -1;
                    break;
                }
            }
        }
        if (i < 0) {
            return false;
        }
        return true;
    }

    public static Object first(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static Object firstOrNull(Iterable iterable) {
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } else {
            Iterator it = iterable.iterator();
            if (it.hasNext()) {
                return it.next();
            }
        }
        return null;
    }

    public static int getLastIndex(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return list.size() - 1;
    }

    public static String joinToString$default(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Function1 function1, int i) {
        CharSequence prefix;
        CharSequence postfix;
        int i2;
        CharSequence truncated;
        Function1 function12;
        if ((i & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence separator = charSequence;
        if ((i & 2) != 0) {
            prefix = "";
        } else {
            prefix = charSequence2;
        }
        if ((i & 4) != 0) {
            postfix = "";
        } else {
            postfix = charSequence3;
        }
        if ((i & 8) != 0) {
            i2 = -1;
        } else {
            i2 = 0;
        }
        int i3 = i2;
        if ((i & 16) != 0) {
            truncated = "...";
        } else {
            truncated = null;
        }
        if ((i & 32) != 0) {
            function12 = null;
        } else {
            function12 = function1;
        }
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        CollectionsKt___CollectionsKt.joinTo(iterable, sb, separator, prefix, postfix, i3, truncated, function12);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb2;
    }

    public static List listOf(Object obj) {
        List singletonList = Collections.singletonList(obj);
        Intrinsics.checkNotNullExpressionValue(singletonList, "singletonList(element)");
        return singletonList;
    }

    public static List plus(Iterable iterable, Collection collection) {
        Collection collection2 = (Collection) iterable;
        ArrayList arrayList = new ArrayList(collection2.size() + collection.size());
        arrayList.addAll(collection);
        arrayList.addAll(collection2);
        return arrayList;
    }

    public static void sortWith(List list, Comparator comparator) {
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }

    public static List sortedWith(Iterable iterable, Comparator comparator) {
        List list;
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        boolean z = iterable instanceof Collection;
        if (z) {
            Collection collection = (Collection) iterable;
            if (collection.size() <= 1) {
                return toList(iterable);
            }
            Object[] array = collection.toArray(new Object[0]);
            Intrinsics.checkNotNullParameter(array, "<this>");
            if (array.length > 1) {
                Arrays.sort(array, comparator);
            }
            List asList = Arrays.asList(array);
            Intrinsics.checkNotNullExpressionValue(asList, "asList(this)");
            return asList;
        }
        if (z) {
            list = toMutableList((Collection) iterable);
        } else {
            ArrayList arrayList = new ArrayList();
            CollectionsKt___CollectionsKt.toCollection(iterable, arrayList);
            list = arrayList;
        }
        sortWith(list, comparator);
        return list;
    }

    public static void throwIndexOverflow() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    public static int[] toIntArray(Collection collection) {
        int[] iArr = new int[collection.size()];
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = ((Number) it.next()).intValue();
            i++;
        }
        return iArr;
    }

    public static List toList(Iterable iterable) {
        List list;
        Object next;
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        boolean z = iterable instanceof Collection;
        if (z) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size != 0) {
                if (size != 1) {
                    return toMutableList(collection);
                }
                if (iterable instanceof List) {
                    next = ((List) iterable).get(0);
                } else {
                    next = iterable.iterator().next();
                }
                return listOf(next);
            }
            return EmptyList.INSTANCE;
        }
        if (z) {
            list = toMutableList((Collection) iterable);
        } else {
            ArrayList arrayList = new ArrayList();
            CollectionsKt___CollectionsKt.toCollection(iterable, arrayList);
            list = arrayList;
        }
        int size2 = list.size();
        if (size2 != 0) {
            if (size2 == 1) {
                return listOf(list.get(0));
            }
            return list;
        }
        return EmptyList.INSTANCE;
    }

    public static List toMutableList(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        return new ArrayList(collection);
    }

    public static List listOf(Object... objArr) {
        if (objArr.length > 0) {
            List asList = Arrays.asList(objArr);
            Intrinsics.checkNotNullExpressionValue(asList, "asList(this)");
            return asList;
        }
        return EmptyList.INSTANCE;
    }

    public static Object firstOrNull(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
