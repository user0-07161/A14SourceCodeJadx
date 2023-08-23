package androidx.compose.ui.focus;

import android.view.KeyEvent;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.KeyInputInputModifierNodeImpl;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryInputModifierNodeImpl;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FocusOwnerImpl implements FocusOwner {
    private final FocusInvalidationManager focusInvalidationManager;
    public LayoutDirection layoutDirection;
    private final FocusOwnerImpl$special$$inlined$modifierElementOf$2 modifier;
    private FocusTargetModifierNode rootFocusNode = new FocusTargetModifierNode();

    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.ui.focus.FocusOwnerImpl$special$$inlined$modifierElementOf$2] */
    public FocusOwnerImpl(Function1 function1) {
        this.focusInvalidationManager = new FocusInvalidationManager(function1);
        final Function1 noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        this.modifier = new ModifierNodeElement(noInspectorInfo) { // from class: androidx.compose.ui.focus.FocusOwnerImpl$special$$inlined$modifierElementOf$2
            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node create() {
                return this.getRootFocusNode$ui_release();
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node update(Modifier.Node node) {
                Intrinsics.checkNotNullParameter(node, "node");
                return node;
            }
        };
    }

    public final void clearFocus(boolean z, boolean z2) {
        FocusStateImpl focusStateImpl;
        FocusStateImpl focusStateImpl$ui_release = this.rootFocusNode.getFocusStateImpl$ui_release();
        if (FocusTransactionsKt.clearFocus(this.rootFocusNode, z, z2)) {
            FocusTargetModifierNode focusTargetModifierNode = this.rootFocusNode;
            int ordinal = focusStateImpl$ui_release.ordinal();
            if (ordinal != 0 && ordinal != 1 && ordinal != 2) {
                if (ordinal == 3) {
                    focusStateImpl = FocusStateImpl.Inactive;
                } else {
                    throw new NoWhenBranchMatchedException();
                }
            } else {
                focusStateImpl = FocusStateImpl.Active;
            }
            focusTargetModifierNode.setFocusStateImpl$ui_release(focusStateImpl);
        }
    }

    /* renamed from: dispatchKeyEvent-ZmokQxo  reason: not valid java name */
    public final boolean m29dispatchKeyEventZmokQxo(KeyEvent keyEvent) {
        KeyInputModifierNode keyInputModifierNode;
        KeyInputModifierNode keyInputModifierNode2;
        int i;
        boolean z;
        Intrinsics.checkNotNullParameter(keyEvent, "keyEvent");
        FocusTargetModifierNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        if (findActiveFocusNode != null) {
            if (findActiveFocusNode.getNode().isAttached()) {
                Modifier.Node node = findActiveFocusNode.getNode();
                List list = null;
                if ((node.getAggregateChildKindSet$ui_release() & 9216) != 0) {
                    keyInputModifierNode = null;
                    for (Modifier.Node child$ui_release = node.getChild$ui_release(); child$ui_release != null; child$ui_release = child$ui_release.getChild$ui_release()) {
                        if ((child$ui_release.getKindSet$ui_release() & 9216) != 0) {
                            if ((child$ui_release.getKindSet$ui_release() & 1024) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z) {
                                keyInputModifierNode2 = keyInputModifierNode;
                                break;
                            } else if (child$ui_release instanceof KeyInputModifierNode) {
                                keyInputModifierNode = child$ui_release;
                            } else {
                                throw new IllegalStateException("Check failed.".toString());
                            }
                        }
                    }
                } else {
                    keyInputModifierNode = null;
                }
                keyInputModifierNode2 = keyInputModifierNode;
                if (keyInputModifierNode2 == null) {
                    Modifier.Node nearestAncestor = DelegatableNodeKt.nearestAncestor(findActiveFocusNode, 8192);
                    if (!(nearestAncestor instanceof KeyInputModifierNode)) {
                        nearestAncestor = null;
                    }
                    keyInputModifierNode2 = (KeyInputModifierNode) nearestAncestor;
                }
                if (keyInputModifierNode2 != null) {
                    List ancestors = DelegatableNodeKt.ancestors(keyInputModifierNode2, 8192);
                    if (ancestors instanceof List) {
                        list = ancestors;
                    }
                    if (list != null && list.size() - 1 >= 0) {
                        while (true) {
                            int i2 = i - 1;
                            if (((KeyInputInputModifierNodeImpl) ((KeyInputModifierNode) list.get(i))).m177onPreKeyEventZmokQxo(keyEvent)) {
                                return true;
                            }
                            if (i2 < 0) {
                                break;
                            }
                            i = i2;
                        }
                    }
                    KeyInputInputModifierNodeImpl keyInputInputModifierNodeImpl = (KeyInputInputModifierNodeImpl) keyInputModifierNode2;
                    if (keyInputInputModifierNodeImpl.m177onPreKeyEventZmokQxo(keyEvent) || keyInputInputModifierNodeImpl.m176onKeyEventZmokQxo(keyEvent)) {
                        return true;
                    }
                    if (list != null) {
                        int size = list.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            if (((KeyInputInputModifierNodeImpl) ((KeyInputModifierNode) list.get(i3))).m176onKeyEventZmokQxo(keyEvent)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            throw new IllegalStateException("Check failed.".toString());
        }
        throw new IllegalStateException("Event can't be processed because we do not have an active focus target.".toString());
    }

    public final boolean dispatchRotaryEvent(RotaryScrollEvent rotaryScrollEvent) {
        RotaryInputModifierNode rotaryInputModifierNode;
        int i;
        FocusTargetModifierNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        List list = null;
        if (findActiveFocusNode != null) {
            Modifier.Node nearestAncestor = DelegatableNodeKt.nearestAncestor(findActiveFocusNode, 16384);
            if (!(nearestAncestor instanceof RotaryInputModifierNode)) {
                nearestAncestor = null;
            }
            rotaryInputModifierNode = (RotaryInputModifierNode) nearestAncestor;
        } else {
            rotaryInputModifierNode = null;
        }
        if (rotaryInputModifierNode != null) {
            List ancestors = DelegatableNodeKt.ancestors(rotaryInputModifierNode, 16384);
            if (ancestors instanceof List) {
                list = ancestors;
            }
            if (list != null && list.size() - 1 >= 0) {
                while (true) {
                    int i2 = i - 1;
                    ((RotaryInputModifierNodeImpl) ((RotaryInputModifierNode) list.get(i))).getClass();
                    if (i2 < 0) {
                        break;
                    }
                    i = i2;
                }
            }
            if (((RotaryInputModifierNodeImpl) rotaryInputModifierNode).onRotaryScrollEvent(rotaryScrollEvent)) {
                return true;
            }
            if (list != null) {
                int size = list.size();
                for (int i3 = 0; i3 < size; i3++) {
                    if (((RotaryInputModifierNodeImpl) ((RotaryInputModifierNode) list.get(i3))).onRotaryScrollEvent(rotaryScrollEvent)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final Rect getFocusRect() {
        FocusTargetModifierNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        if (findActiveFocusNode != null) {
            return FocusTraversalKt.focusRect(findActiveFocusNode);
        }
        return null;
    }

    public final FocusOwnerImpl$special$$inlined$modifierElementOf$2 getModifier() {
        return this.modifier;
    }

    public final FocusTargetModifierNode getRootFocusNode$ui_release() {
        return this.rootFocusNode;
    }

    /* JADX WARN: Removed duplicated region for block: B:212:? A[RETURN, SYNTHETIC] */
    /* renamed from: moveFocus-3ESFkO8  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean m30moveFocus3ESFkO8(int r17) {
        /*
            Method dump skipped, instructions count: 641
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.m30moveFocus3ESFkO8(int):boolean");
    }

    public final void releaseFocus() {
        FocusTransactionsKt.clearFocus(this.rootFocusNode, true, true);
    }

    public final void scheduleInvalidation(FocusTargetModifierNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    public final void takeFocus() {
        if (this.rootFocusNode.getFocusStateImpl$ui_release() == FocusStateImpl.Inactive) {
            this.rootFocusNode.setFocusStateImpl$ui_release(FocusStateImpl.Active);
        }
    }

    public final void scheduleInvalidation(FocusEventModifierNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.focusInvalidationManager.scheduleInvalidation(node);
    }
}
