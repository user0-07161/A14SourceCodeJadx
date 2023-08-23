package androidx.compose.ui.input.pointer;

import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.NodeCoordinator;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class PointerInputFilter {
    private LayoutCoordinates layoutCoordinates;

    public final void setLayoutCoordinates$ui_release(NodeCoordinator nodeCoordinator) {
        this.layoutCoordinates = nodeCoordinator;
    }
}
