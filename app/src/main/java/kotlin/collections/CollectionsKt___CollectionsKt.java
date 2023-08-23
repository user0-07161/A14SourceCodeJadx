package kotlin.collections;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CollectionsKt___CollectionsKt extends CollectionsKt__ReversedViewsKt {
    public static final void joinTo(Iterable iterable, Appendable appendable, CharSequence separator, CharSequence prefix, CharSequence postfix, int i, CharSequence truncated, Function1 function1) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = (StringBuilder) appendable;
        sb.append(prefix);
        int i2 = 0;
        for (Object obj : iterable) {
            i2++;
            boolean z = true;
            if (i2 > 1) {
                sb.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            } else if (function1 != null) {
                sb.append((CharSequence) function1.invoke(obj));
            } else {
                if (obj != null) {
                    z = obj instanceof CharSequence;
                }
                if (z) {
                    sb.append((CharSequence) obj);
                } else if (obj instanceof Character) {
                    sb.append(((Character) obj).charValue());
                } else {
                    sb.append((CharSequence) String.valueOf(obj));
                }
            }
        }
        if (i >= 0 && i2 > i) {
            sb.append(truncated);
        }
        sb.append(postfix);
    }

    public static final void toCollection(Iterable iterable, Collection collection) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        for (Object obj : iterable) {
            collection.add(obj);
        }
    }
}
