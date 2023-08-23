package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.modifier.ModifierLocalNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.ObserverNode;
import androidx.compose.ui.node.ObserverNodeKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FocusTargetModifierNode extends Modifier.Node implements ObserverNode, ModifierLocalNode {
    private FocusStateImpl focusStateImpl = FocusStateImpl.Inactive;

    static {
        final Function1 noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        new ModifierNodeElement(noInspectorInfo) { // from class: androidx.compose.ui.focus.FocusTargetModifierNode$special$$inlined$modifierElementOf$2
            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node create() {
                return new FocusTargetModifierNode();
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node update(Modifier.Node node) {
                Intrinsics.checkNotNullParameter(node, "node");
                return node;
            }
        };
    }

    public final FocusPropertiesImpl fetchFocusProperties$ui_release() {
        NodeChain nodes$ui_release;
        boolean z;
        FocusPropertiesImpl focusPropertiesImpl = new FocusPropertiesImpl();
        if (getNode().isAttached()) {
            Modifier.Node parent$ui_release = getNode().getParent$ui_release();
            LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(this);
            while (requireLayoutNode != null) {
                if ((requireLayoutNode.getNodes$ui_release().getHead$ui_release().getAggregateChildKindSet$ui_release() & 3072) != 0) {
                    while (parent$ui_release != null) {
                        if ((parent$ui_release.getKindSet$ui_release() & 3072) != 0) {
                            if ((parent$ui_release.getKindSet$ui_release() & 1024) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z) {
                                return focusPropertiesImpl;
                            }
                            if (!(parent$ui_release instanceof FocusPropertiesModifierNode)) {
                                throw new IllegalStateException("Check failed.".toString());
                            }
                            FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) parent$ui_release;
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        parent$ui_release = parent$ui_release.getParent$ui_release();
                    }
                }
                requireLayoutNode = requireLayoutNode.getParent$ui_release();
                if (requireLayoutNode != null && (nodes$ui_release = requireLayoutNode.getNodes$ui_release()) != null) {
                    parent$ui_release = nodes$ui_release.getTail$ui_release();
                } else {
                    parent$ui_release = null;
                }
            }
            return focusPropertiesImpl;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final FocusStateImpl getFocusState() {
        return this.focusStateImpl;
    }

    public final FocusStateImpl getFocusStateImpl$ui_release() {
        return this.focusStateImpl;
    }

    public final void invalidateFocus$ui_release() {
        boolean z;
        FocusStateImpl focusStateImpl = this.focusStateImpl;
        if (focusStateImpl == FocusStateImpl.Active || focusStateImpl == FocusStateImpl.Captured) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            ObserverNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.ui.focus.FocusTargetModifierNode$invalidateFocus$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Ref$ObjectRef.this.element = this.fetchFocusProperties$ui_release();
                    return Unit.INSTANCE;
                }
            });
            Object obj = ref$ObjectRef.element;
            if (obj != null) {
                if (!((FocusProperties) obj).getCanFocus()) {
                    ((FocusOwnerImpl) ((AndroidComposeView) DelegatableNodeKt.requireOwner(this)).getFocusOwner()).clearFocus(true, true);
                    return;
                }
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("focusProperties");
            throw null;
        }
    }

    public final void onObservedReadsChanged() {
        FocusStateImpl focusStateImpl = this.focusStateImpl;
        invalidateFocus$ui_release();
        if (!Intrinsics.areEqual(focusStateImpl, this.focusStateImpl)) {
            FocusEventModifierNodeKt.refreshFocusEventNodes(this);
        }
    }

    public final void onRemoved$ui_release() {
        boolean z;
        FocusStateImpl focusStateImpl = this.focusStateImpl;
        boolean z2 = false;
        if (focusStateImpl == FocusStateImpl.Active || focusStateImpl == FocusStateImpl.Captured) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            ((FocusOwnerImpl) ((AndroidComposeView) DelegatableNodeKt.requireOwner(this)).getFocusOwner()).clearFocus(true, true);
            return;
        }
        if (focusStateImpl == FocusStateImpl.ActiveParent || focusStateImpl == FocusStateImpl.Inactive) {
            z2 = true;
        }
        if (z2) {
            scheduleInvalidationForFocusEvents$ui_release();
        }
    }

    public final void scheduleInvalidationForFocusEvents$ui_release() {
        NodeChain nodes$ui_release;
        boolean z;
        if (getNode().isAttached()) {
            Modifier.Node parent$ui_release = getNode().getParent$ui_release();
            LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(this);
            while (requireLayoutNode != null) {
                if ((requireLayoutNode.getNodes$ui_release().getHead$ui_release().getAggregateChildKindSet$ui_release() & 5120) != 0) {
                    while (parent$ui_release != null) {
                        if ((parent$ui_release.getKindSet$ui_release() & 5120) != 0) {
                            if ((parent$ui_release.getKindSet$ui_release() & 1024) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z) {
                                continue;
                            } else if (parent$ui_release instanceof FocusEventModifierNode) {
                                ((FocusOwnerImpl) ((AndroidComposeView) DelegatableNodeKt.requireOwner(this)).getFocusOwner()).scheduleInvalidation((FocusEventModifierNode) parent$ui_release);
                            } else {
                                throw new IllegalStateException("Check failed.".toString());
                            }
                        }
                        parent$ui_release = parent$ui_release.getParent$ui_release();
                    }
                }
                requireLayoutNode = requireLayoutNode.getParent$ui_release();
                if (requireLayoutNode != null && (nodes$ui_release = requireLayoutNode.getNodes$ui_release()) != null) {
                    parent$ui_release = nodes$ui_release.getTail$ui_release();
                } else {
                    parent$ui_release = null;
                }
            }
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final void setFocusStateImpl$ui_release(FocusStateImpl focusStateImpl) {
        this.focusStateImpl = focusStateImpl;
    }
}
