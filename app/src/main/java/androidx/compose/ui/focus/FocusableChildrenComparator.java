package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class FocusableChildrenComparator implements Comparator {
    public static final FocusableChildrenComparator INSTANCE = new FocusableChildrenComparator();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        LayoutNode layoutNode;
        FocusTargetModifierNode focusTargetModifierNode = (FocusTargetModifierNode) obj;
        FocusTargetModifierNode focusTargetModifierNode2 = (FocusTargetModifierNode) obj2;
        if (focusTargetModifierNode != null) {
            if (focusTargetModifierNode2 != null) {
                int i = 0;
                if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode) && FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode2)) {
                    NodeCoordinator coordinator$ui_release = focusTargetModifierNode.getCoordinator$ui_release();
                    LayoutNode layoutNode2 = null;
                    if (coordinator$ui_release != null) {
                        layoutNode = coordinator$ui_release.getLayoutNode();
                    } else {
                        layoutNode = null;
                    }
                    if (layoutNode != null) {
                        NodeCoordinator coordinator$ui_release2 = focusTargetModifierNode2.getCoordinator$ui_release();
                        if (coordinator$ui_release2 != null) {
                            layoutNode2 = coordinator$ui_release2.getLayoutNode();
                        }
                        if (layoutNode2 != null) {
                            if (!Intrinsics.areEqual(layoutNode, layoutNode2)) {
                                MutableVector mutableVector = new MutableVector(new LayoutNode[16]);
                                while (layoutNode != null) {
                                    mutableVector.add(0, layoutNode);
                                    layoutNode = layoutNode.getParent$ui_release();
                                }
                                MutableVector mutableVector2 = new MutableVector(new LayoutNode[16]);
                                while (layoutNode2 != null) {
                                    mutableVector2.add(0, layoutNode2);
                                    layoutNode2 = layoutNode2.getParent$ui_release();
                                }
                                int min = Math.min(mutableVector.getSize() - 1, mutableVector2.getSize() - 1);
                                if (min >= 0) {
                                    while (Intrinsics.areEqual(mutableVector.getContent()[i], mutableVector2.getContent()[i])) {
                                        if (i != min) {
                                            i++;
                                        }
                                    }
                                    return Intrinsics.compare(((LayoutNode) mutableVector.getContent()[i]).getPlaceOrder$ui_release(), ((LayoutNode) mutableVector2.getContent()[i]).getPlaceOrder$ui_release());
                                }
                                throw new IllegalStateException("Could not find a common ancestor between the two FocusModifiers.".toString());
                            }
                        } else {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                    } else {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                } else if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode)) {
                    return -1;
                } else {
                    if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode2)) {
                        return 1;
                    }
                }
                return 0;
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }
}
