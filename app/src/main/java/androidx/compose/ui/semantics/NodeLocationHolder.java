package androidx.compose.ui.semantics;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$$ExternalSyntheticLambda0;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class NodeLocationHolder implements Comparable {
    private static ComparisonStrategy comparisonStrategy = ComparisonStrategy.Stripe;
    private final LayoutDirection layoutDirection;
    private final Rect location;
    private final LayoutNode node;
    private final LayoutNode subtreeRoot;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public enum ComparisonStrategy {
        Stripe,
        Location
    }

    public NodeLocationHolder(LayoutNode subtreeRoot, LayoutNode layoutNode) {
        Rect rect;
        Intrinsics.checkNotNullParameter(subtreeRoot, "subtreeRoot");
        this.subtreeRoot = subtreeRoot;
        this.node = layoutNode;
        this.layoutDirection = subtreeRoot.getLayoutDirection();
        InnerNodeCoordinator innerCoordinator$ui_release = subtreeRoot.getInnerCoordinator$ui_release();
        NodeCoordinator findCoordinatorToGetBounds = SemanticsSortKt.findCoordinatorToGetBounds(layoutNode);
        if (innerCoordinator$ui_release.isAttached() && findCoordinatorToGetBounds.isAttached()) {
            rect = innerCoordinator$ui_release.localBoundingBoxOf(findCoordinatorToGetBounds, true);
        } else {
            rect = null;
        }
        this.location = rect;
    }

    public static final /* synthetic */ void access$setComparisonStrategy$cp(ComparisonStrategy comparisonStrategy2) {
        comparisonStrategy = comparisonStrategy2;
    }

    public final LayoutNode getNode$ui_release() {
        return this.node;
    }

    @Override // java.lang.Comparable
    public final int compareTo(NodeLocationHolder other) {
        LayoutNode$$ExternalSyntheticLambda0 layoutNode$$ExternalSyntheticLambda0;
        Intrinsics.checkNotNullParameter(other, "other");
        Rect rect = this.location;
        if (rect == null) {
            return 1;
        }
        if (other.location == null) {
            return -1;
        }
        if (comparisonStrategy == ComparisonStrategy.Stripe) {
            if (rect.getBottom() - other.location.getTop() <= 0.0f) {
                return -1;
            }
            if (this.location.getTop() - other.location.getBottom() >= 0.0f) {
                return 1;
            }
        }
        if (this.layoutDirection == LayoutDirection.Ltr) {
            int i = ((this.location.getLeft() - other.location.getLeft()) > 0.0f ? 1 : ((this.location.getLeft() - other.location.getLeft()) == 0.0f ? 0 : -1));
            if (!(i == 0)) {
                return i < 0 ? -1 : 1;
            }
        } else {
            int i2 = ((this.location.getRight() - other.location.getRight()) > 0.0f ? 1 : ((this.location.getRight() - other.location.getRight()) == 0.0f ? 0 : -1));
            if (!(i2 == 0)) {
                return i2 < 0 ? 1 : -1;
            }
        }
        int i3 = ((this.location.getTop() - other.location.getTop()) > 0.0f ? 1 : ((this.location.getTop() - other.location.getTop()) == 0.0f ? 0 : -1));
        if (!(i3 == 0)) {
            return i3 < 0 ? -1 : 1;
        }
        final Rect boundsInRoot = LayoutCoordinatesKt.boundsInRoot(SemanticsSortKt.findCoordinatorToGetBounds(this.node));
        final Rect boundsInRoot2 = LayoutCoordinatesKt.boundsInRoot(SemanticsSortKt.findCoordinatorToGetBounds(other.node));
        LayoutNode findNodeByPredicateTraversal = SemanticsSortKt.findNodeByPredicateTraversal(this.node, new Function1() { // from class: androidx.compose.ui.semantics.NodeLocationHolder$compareTo$child1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean z;
                LayoutNode it = (LayoutNode) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                NodeCoordinator findCoordinatorToGetBounds = SemanticsSortKt.findCoordinatorToGetBounds(it);
                if (findCoordinatorToGetBounds.isAttached() && !Intrinsics.areEqual(Rect.this, LayoutCoordinatesKt.boundsInRoot(findCoordinatorToGetBounds))) {
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        });
        LayoutNode findNodeByPredicateTraversal2 = SemanticsSortKt.findNodeByPredicateTraversal(other.node, new Function1() { // from class: androidx.compose.ui.semantics.NodeLocationHolder$compareTo$child2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean z;
                LayoutNode it = (LayoutNode) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                NodeCoordinator findCoordinatorToGetBounds = SemanticsSortKt.findCoordinatorToGetBounds(it);
                if (findCoordinatorToGetBounds.isAttached() && !Intrinsics.areEqual(Rect.this, LayoutCoordinatesKt.boundsInRoot(findCoordinatorToGetBounds))) {
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        });
        if (findNodeByPredicateTraversal == null || findNodeByPredicateTraversal2 == null) {
            if (findNodeByPredicateTraversal != null) {
                return 1;
            }
            if (findNodeByPredicateTraversal2 != null) {
                return -1;
            }
            layoutNode$$ExternalSyntheticLambda0 = LayoutNode.ZComparator;
            int compare = layoutNode$$ExternalSyntheticLambda0.compare(this.node, other.node);
            return compare != 0 ? -compare : this.node.getSemanticsId() - other.node.getSemanticsId();
        }
        return new NodeLocationHolder(this.subtreeRoot, findNodeByPredicateTraversal).compareTo(new NodeLocationHolder(other.subtreeRoot, findNodeByPredicateTraversal2));
    }
}
