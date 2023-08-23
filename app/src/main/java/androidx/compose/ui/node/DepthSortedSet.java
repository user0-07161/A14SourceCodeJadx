package androidx.compose.ui.node;

import java.util.LinkedHashMap;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DepthSortedSet {
    private final DepthSortedSet$DepthComparator$1 DepthComparator;
    private final TreeSet set;

    public DepthSortedSet() {
        LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.node.DepthSortedSet$mapOfOriginalDepth$2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new LinkedHashMap();
            }
        });
        DepthSortedSet$DepthComparator$1 depthSortedSet$DepthComparator$1 = new DepthSortedSet$DepthComparator$1();
        this.DepthComparator = depthSortedSet$DepthComparator$1;
        this.set = new TreeSet(depthSortedSet$DepthComparator$1);
    }

    public final void add(LayoutNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (node.isAttached()) {
            this.set.add(node);
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final boolean isEmpty() {
        return this.set.isEmpty();
    }

    public final LayoutNode pop() {
        LayoutNode node = (LayoutNode) this.set.first();
        Intrinsics.checkNotNullExpressionValue(node, "node");
        remove(node);
        return node;
    }

    public final boolean remove(LayoutNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (node.isAttached()) {
            return this.set.remove(node);
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final String toString() {
        String treeSet = this.set.toString();
        Intrinsics.checkNotNullExpressionValue(treeSet, "set.toString()");
        return treeSet;
    }
}
