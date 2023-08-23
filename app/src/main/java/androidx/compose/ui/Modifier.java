package androidx.compose.ui;

import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.ModifierNodeOwnerScope;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface Modifier {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion implements Modifier {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @Override // androidx.compose.ui.Modifier
        public final boolean all(Function1 predicate) {
            Intrinsics.checkNotNullParameter(predicate, "predicate");
            return true;
        }

        @Override // androidx.compose.ui.Modifier
        public final Object foldIn(Object obj, Function2 operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            return obj;
        }

        @Override // androidx.compose.ui.Modifier
        public final Modifier then(Modifier other) {
            Intrinsics.checkNotNullParameter(other, "other");
            return other;
        }

        public final String toString() {
            return "Modifier";
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Element extends Modifier {
        @Override // androidx.compose.ui.Modifier
        default boolean all(Function1 predicate) {
            Intrinsics.checkNotNullParameter(predicate, "predicate");
            return ((Boolean) predicate.invoke(this)).booleanValue();
        }

        @Override // androidx.compose.ui.Modifier
        default Object foldIn(Object obj, Function2 operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            return operation.invoke(obj, this);
        }
    }

    boolean all(Function1 function1);

    Object foldIn(Object obj, Function2 function2);

    default Modifier then(Modifier other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (other != Companion.$$INSTANCE) {
            return new CombinedModifier(this, other);
        }
        return this;
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class Node implements DelegatableNode {
        private int aggregateChildKindSet;
        private Node child;
        private NodeCoordinator coordinator;
        private boolean isAttached;
        private int kindSet;
        private Node node = this;
        private ModifierNodeOwnerScope ownerScope;
        private Node parent;

        public final void attach$ui_release() {
            boolean z;
            if (!this.isAttached) {
                if (this.coordinator != null) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    this.isAttached = true;
                    onAttach();
                    return;
                }
                throw new IllegalStateException("Check failed.".toString());
            }
            throw new IllegalStateException("Check failed.".toString());
        }

        public final void detach$ui_release() {
            boolean z;
            if (this.isAttached) {
                if (this.coordinator != null) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    onDetach();
                    this.isAttached = false;
                    return;
                }
                throw new IllegalStateException("Check failed.".toString());
            }
            throw new IllegalStateException("Check failed.".toString());
        }

        public final int getAggregateChildKindSet$ui_release() {
            return this.aggregateChildKindSet;
        }

        public final Node getChild$ui_release() {
            return this.child;
        }

        public final NodeCoordinator getCoordinator$ui_release() {
            return this.coordinator;
        }

        public final int getKindSet$ui_release() {
            return this.kindSet;
        }

        public final Node getNode() {
            return this.node;
        }

        public final ModifierNodeOwnerScope getOwnerScope$ui_release() {
            return this.ownerScope;
        }

        public final Node getParent$ui_release() {
            return this.parent;
        }

        public final boolean isAttached() {
            return this.isAttached;
        }

        public boolean isValidOwnerScope() {
            return isAttached();
        }

        public final void setAggregateChildKindSet$ui_release(int i) {
            this.aggregateChildKindSet = i;
        }

        public final void setChild$ui_release(Node node) {
            this.child = node;
        }

        public final void setKindSet$ui_release(int i) {
            this.kindSet = i;
        }

        public final void setOwnerScope$ui_release(ModifierNodeOwnerScope modifierNodeOwnerScope) {
            this.ownerScope = modifierNodeOwnerScope;
        }

        public final void setParent$ui_release(Node node) {
            this.parent = node;
        }

        public final void updateCoordinator$ui_release(NodeCoordinator nodeCoordinator) {
            this.coordinator = nodeCoordinator;
        }

        public void onAttach() {
        }

        public void onDetach() {
        }
    }
}
