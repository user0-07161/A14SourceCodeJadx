package androidx.compose.ui.node;

import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class OnPositionedDispatcher$Companion$DepthComparator implements Comparator {
    public static final OnPositionedDispatcher$Companion$DepthComparator INSTANCE = new OnPositionedDispatcher$Companion$DepthComparator();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        LayoutNode a = (LayoutNode) obj;
        LayoutNode b = (LayoutNode) obj2;
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        int compare = Intrinsics.compare(b.getDepth$ui_release(), a.getDepth$ui_release());
        if (compare == 0) {
            return Intrinsics.compare(a.hashCode(), b.hashCode());
        }
        return compare;
    }
}
