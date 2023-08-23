package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.NodeCoordinator;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface LayoutCoordinates {
    NodeCoordinator getParentLayoutCoordinates();

    /* renamed from: getSize-YbymL2g  reason: not valid java name */
    long mo208getSizeYbymL2g();

    Rect localBoundingBoxOf(NodeCoordinator nodeCoordinator, boolean z);

    /* renamed from: localToWindow-MK-Hz9U  reason: not valid java name */
    long mo209localToWindowMKHz9U(long j);
}
