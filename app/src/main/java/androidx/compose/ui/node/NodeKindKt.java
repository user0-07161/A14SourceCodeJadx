package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.input.pointer.PointerInputModifier;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.SemanticsModifier;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class NodeKindKt {
    public static final void autoInvalidateInsertedNode(Modifier.Node node) {
        autoInvalidateNode(node, 1);
    }

    private static final void autoInvalidateNode(Modifier.Node node, int i) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        if (node.isAttached()) {
            boolean z8 = true;
            if ((node.getKindSet$ui_release() & 2) != 0) {
                z = true;
            } else {
                z = false;
            }
            if (z && (node instanceof LayoutModifierNode)) {
                DelegatableNodeKt.requireLayoutNode((LayoutModifierNode) node).invalidateMeasurements$ui_release();
                if (i == 2) {
                    DelegatableNodeKt.m224requireCoordinator64DMado(node, 2).detach();
                }
            }
            if ((node.getKindSet$ui_release() & 256) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 && (node instanceof GlobalPositionAwareModifierNode)) {
                DelegatableNodeKt.requireLayoutNode(node).invalidateMeasurements$ui_release();
            }
            if ((node.getKindSet$ui_release() & 4) != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3 && (node instanceof DrawModifierNode)) {
                DrawModifierNodeKt.invalidateDraw((DrawModifierNode) node);
            }
            if ((node.getKindSet$ui_release() & 8) != 0) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (z4 && (node instanceof SemanticsModifierNode)) {
                ((AndroidComposeView) DelegatableNodeKt.requireOwner((SemanticsModifierNode) node)).onSemanticsChange();
            }
            if ((node.getKindSet$ui_release() & 64) != 0) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (z5 && (node instanceof ParentDataModifierNode)) {
                DelegatableNodeKt.requireLayoutNode((ParentDataModifierNode) node).invalidateParentData$ui_release();
            }
            if ((node.getKindSet$ui_release() & 1024) != 0) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (z6 && (node instanceof FocusTargetModifierNode)) {
                if (i == 2) {
                    ((FocusTargetModifierNode) node).onRemoved$ui_release();
                } else {
                    ((FocusOwnerImpl) ((AndroidComposeView) DelegatableNodeKt.requireOwner(node)).getFocusOwner()).scheduleInvalidation((FocusTargetModifierNode) node);
                }
            }
            if ((node.getKindSet$ui_release() & 2048) != 0) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (z7 && (node instanceof FocusPropertiesModifierNode)) {
                FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) node;
                throw new IllegalStateException("Check failed.".toString());
            }
            if ((node.getKindSet$ui_release() & 4096) == 0) {
                z8 = false;
            }
            if (z8 && (node instanceof FocusEventModifierNode) && i != 2) {
                ((FocusOwnerImpl) ((AndroidComposeView) DelegatableNodeKt.requireOwner(node)).getFocusOwner()).scheduleInvalidation((FocusEventModifierNode) node);
                return;
            }
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public static final void autoInvalidateRemovedNode(Modifier.Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        autoInvalidateNode(node, 2);
    }

    public static final void autoInvalidateUpdatedNode(Modifier.Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        autoInvalidateNode(node, 0);
    }

    public static final int calculateNodeKindSetFrom(Modifier.Element element) {
        int i;
        Intrinsics.checkNotNullParameter(element, "element");
        if (element instanceof LayoutModifier) {
            i = 3;
        } else {
            i = 1;
        }
        if (element instanceof SemanticsModifier) {
            i |= 8;
        }
        if (element instanceof PointerInputModifier) {
            i |= 16;
        }
        if (element instanceof OnGloballyPositionedModifier) {
            i |= 256;
        }
        if (element instanceof ParentDataModifier) {
            return i | 64;
        }
        return i;
    }

    /* renamed from: getIncludeSelfInTraversal-H91voCI  reason: not valid java name */
    public static final boolean m268getIncludeSelfInTraversalH91voCI(int i) {
        if ((i & 128) != 0) {
            return true;
        }
        return false;
    }
}
