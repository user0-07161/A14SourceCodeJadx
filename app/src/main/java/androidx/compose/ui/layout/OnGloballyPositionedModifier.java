package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.NodeCoordinator;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface OnGloballyPositionedModifier extends Modifier.Element {
    void onGloballyPositioned(NodeCoordinator nodeCoordinator);
}
