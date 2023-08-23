package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class NodeCoordinatorKt {
    /* renamed from: access$nextUncheckedUntil-hw7D004  reason: not valid java name */
    public static final Modifier.Node m267access$nextUncheckedUntilhw7D004(DelegatableNode delegatableNode, int i) {
        Modifier.Node child$ui_release = ((Modifier.Node) delegatableNode).getNode().getChild$ui_release();
        if (child$ui_release != null && (child$ui_release.getAggregateChildKindSet$ui_release() & i) != 0) {
            while (child$ui_release != null) {
                int kindSet$ui_release = child$ui_release.getKindSet$ui_release();
                if ((kindSet$ui_release & 2) != 0) {
                    break;
                } else if ((kindSet$ui_release & i) == 0) {
                    child$ui_release = child$ui_release.getChild$ui_release();
                } else {
                    return child$ui_release;
                }
            }
        }
        return null;
    }
}
