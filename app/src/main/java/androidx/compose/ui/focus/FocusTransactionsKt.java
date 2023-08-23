package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.ObserverNodeKt;
import androidx.compose.ui.node.Owner;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class FocusTransactionsKt {
    public static final boolean clearFocus(FocusTargetModifierNode focusTargetModifierNode, boolean z, boolean z2) {
        boolean z3;
        Intrinsics.checkNotNullParameter(focusTargetModifierNode, "<this>");
        int ordinal = focusTargetModifierNode.getFocusStateImpl$ui_release().ordinal();
        FocusStateImpl focusStateImpl = FocusStateImpl.Inactive;
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                } else if (z) {
                    focusTargetModifierNode.setFocusStateImpl$ui_release(focusStateImpl);
                    if (z2) {
                        FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode);
                        return z;
                    }
                    return z;
                } else {
                    return z;
                }
            } else {
                FocusTargetModifierNode activeChild = FocusTraversalKt.getActiveChild(focusTargetModifierNode);
                if (activeChild != null) {
                    z3 = clearFocus(activeChild, z, z2);
                } else {
                    z3 = true;
                }
                if (z3) {
                    focusTargetModifierNode.setFocusStateImpl$ui_release(focusStateImpl);
                    if (z2) {
                        FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode);
                    }
                } else {
                    return false;
                }
            }
        } else {
            focusTargetModifierNode.setFocusStateImpl$ui_release(focusStateImpl);
            if (z2) {
                FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode);
            }
        }
        return true;
    }

    private static final void grantFocus(final FocusTargetModifierNode focusTargetModifierNode) {
        ObserverNodeKt.observeReads(focusTargetModifierNode, new Function0() { // from class: androidx.compose.ui.focus.FocusTransactionsKt$grantFocus$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                FocusTargetModifierNode.this.fetchFocusProperties$ui_release();
                return Unit.INSTANCE;
            }
        });
        int ordinal = focusTargetModifierNode.getFocusStateImpl$ui_release().ordinal();
        if (ordinal == 1 || ordinal == 3) {
            focusTargetModifierNode.setFocusStateImpl$ui_release(FocusStateImpl.Active);
        }
    }

    public static final boolean requestFocus(FocusTargetModifierNode focusTargetModifierNode) {
        boolean z;
        Intrinsics.checkNotNullParameter(focusTargetModifierNode, "<this>");
        if (focusTargetModifierNode.getNode().isAttached()) {
            focusTargetModifierNode.fetchFocusProperties$ui_release();
            int ordinal = focusTargetModifierNode.getFocusStateImpl$ui_release().ordinal();
            boolean z2 = true;
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal == 3) {
                            Modifier.Node nearestAncestor = DelegatableNodeKt.nearestAncestor(focusTargetModifierNode, 1024);
                            if (!(nearestAncestor instanceof FocusTargetModifierNode)) {
                                nearestAncestor = null;
                            }
                            FocusTargetModifierNode focusTargetModifierNode2 = (FocusTargetModifierNode) nearestAncestor;
                            if (focusTargetModifierNode2 != null) {
                                return requestFocusForChild(focusTargetModifierNode2, focusTargetModifierNode);
                            }
                            if (requestFocusForOwner(focusTargetModifierNode)) {
                                grantFocus(focusTargetModifierNode);
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode);
                            }
                            return z2;
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                } else {
                    FocusTargetModifierNode activeChild = FocusTraversalKt.getActiveChild(focusTargetModifierNode);
                    if (activeChild != null) {
                        z = clearFocus(activeChild, false, true);
                    } else {
                        z = true;
                    }
                    if (z) {
                        grantFocus(focusTargetModifierNode);
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode);
                    }
                    return z2;
                }
            }
            FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode);
            return true;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private static final boolean requestFocusForChild(FocusTargetModifierNode focusTargetModifierNode, FocusTargetModifierNode focusTargetModifierNode2) {
        boolean z;
        Modifier.Node nearestAncestor = DelegatableNodeKt.nearestAncestor(focusTargetModifierNode2, 1024);
        FocusTargetModifierNode focusTargetModifierNode3 = null;
        if (!(nearestAncestor instanceof FocusTargetModifierNode)) {
            nearestAncestor = null;
        }
        if (Intrinsics.areEqual((FocusTargetModifierNode) nearestAncestor, focusTargetModifierNode)) {
            int ordinal = focusTargetModifierNode.getFocusStateImpl$ui_release().ordinal();
            FocusStateImpl focusStateImpl = FocusStateImpl.ActiveParent;
            boolean z2 = true;
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal == 3) {
                            Modifier.Node nearestAncestor2 = DelegatableNodeKt.nearestAncestor(focusTargetModifierNode, 1024);
                            if (nearestAncestor2 instanceof FocusTargetModifierNode) {
                                focusTargetModifierNode3 = nearestAncestor2;
                            }
                            FocusTargetModifierNode focusTargetModifierNode4 = focusTargetModifierNode3;
                            if (focusTargetModifierNode4 == null && requestFocusForOwner(focusTargetModifierNode)) {
                                focusTargetModifierNode.setFocusStateImpl$ui_release(FocusStateImpl.Active);
                                FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode);
                                return requestFocusForChild(focusTargetModifierNode, focusTargetModifierNode2);
                            } else if (focusTargetModifierNode4 != null && requestFocusForChild(focusTargetModifierNode4, focusTargetModifierNode)) {
                                boolean requestFocusForChild = requestFocusForChild(focusTargetModifierNode, focusTargetModifierNode2);
                                if (focusTargetModifierNode.getFocusState() != focusStateImpl) {
                                    z2 = false;
                                }
                                if (z2) {
                                    return requestFocusForChild;
                                }
                                throw new IllegalStateException("Check failed.".toString());
                            }
                        } else {
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                    return false;
                } else if (FocusTraversalKt.getActiveChild(focusTargetModifierNode) != null) {
                    FocusTargetModifierNode activeChild = FocusTraversalKt.getActiveChild(focusTargetModifierNode);
                    if (activeChild != null) {
                        z = clearFocus(activeChild, false, true);
                    } else {
                        z = true;
                    }
                    if (z) {
                        grantFocus(focusTargetModifierNode2);
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode2);
                        return z2;
                    }
                    return z2;
                } else {
                    throw new IllegalStateException("Required value was null.".toString());
                }
            }
            grantFocus(focusTargetModifierNode2);
            focusTargetModifierNode.setFocusStateImpl$ui_release(focusStateImpl);
            FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode2);
            FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode);
            return true;
        }
        throw new IllegalStateException("Non child node cannot request focus.".toString());
    }

    private static final boolean requestFocusForOwner(FocusTargetModifierNode focusTargetModifierNode) {
        LayoutNode layoutNode;
        Owner owner$ui_release;
        NodeCoordinator coordinator$ui_release = focusTargetModifierNode.getCoordinator$ui_release();
        if (coordinator$ui_release != null && (layoutNode = coordinator$ui_release.getLayoutNode()) != null && (owner$ui_release = layoutNode.getOwner$ui_release()) != null) {
            return owner$ui_release.requestFocus();
        }
        throw new IllegalStateException("Owner not initialized.".toString());
    }
}
