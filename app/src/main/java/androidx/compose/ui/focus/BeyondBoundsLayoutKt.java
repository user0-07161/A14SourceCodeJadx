package androidx.compose.ui.focus;

import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.modifier.ModifierLocalNode;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class BeyondBoundsLayoutKt {
    /* renamed from: searchBeyondBounds--OM-vw8  reason: not valid java name */
    public static final void m25searchBeyondBoundsOMvw8(FocusTargetModifierNode focusTargetModifierNode, Function1 function1) {
        Object invoke;
        NodeChain nodes$ui_release;
        ProvidableModifierLocal modifierLocalBeyondBoundsLayout = androidx.compose.ui.layout.BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout();
        Intrinsics.checkNotNullParameter(modifierLocalBeyondBoundsLayout, "<this>");
        if (focusTargetModifierNode.getNode().isAttached()) {
            if (focusTargetModifierNode.getNode().isAttached()) {
                Modifier.Node parent$ui_release = focusTargetModifierNode.getNode().getParent$ui_release();
                LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetModifierNode);
                loop0: while (true) {
                    if (requireLayoutNode != null) {
                        if ((requireLayoutNode.getNodes$ui_release().getHead$ui_release().getAggregateChildKindSet$ui_release() & 32) != 0) {
                            while (parent$ui_release != null) {
                                if ((parent$ui_release.getKindSet$ui_release() & 32) != 0 && (parent$ui_release instanceof ModifierLocalNode)) {
                                    ModifierLocalNode modifierLocalNode = (ModifierLocalNode) parent$ui_release;
                                    if (modifierLocalNode.getProvidedValues().contains$ui_release(modifierLocalBeyondBoundsLayout)) {
                                        invoke = modifierLocalNode.getProvidedValues().get$ui_release(modifierLocalBeyondBoundsLayout);
                                        break loop0;
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
                    } else {
                        invoke = modifierLocalBeyondBoundsLayout.getDefaultFactory$ui_release().invoke();
                        break;
                    }
                }
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(invoke);
                return;
            }
            throw new IllegalStateException("Check failed.".toString());
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }
}
