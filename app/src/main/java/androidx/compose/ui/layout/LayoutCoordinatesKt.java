package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntSize;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class LayoutCoordinatesKt {
    public static final Rect boundsInParent(InnerNodeCoordinator innerNodeCoordinator) {
        Intrinsics.checkNotNullParameter(innerNodeCoordinator, "<this>");
        NodeCoordinator parentLayoutCoordinates = innerNodeCoordinator.getParentLayoutCoordinates();
        if (parentLayoutCoordinates != null) {
            return parentLayoutCoordinates.localBoundingBoxOf(innerNodeCoordinator, true);
        }
        return new Rect(0.0f, 0.0f, (int) (innerNodeCoordinator.m215getSizeYbymL2g() >> 32), IntSize.m405getHeightimpl(innerNodeCoordinator.m215getSizeYbymL2g()));
    }

    public static final Rect boundsInRoot(NodeCoordinator nodeCoordinator) {
        Intrinsics.checkNotNullParameter(nodeCoordinator, "<this>");
        return findRootCoordinates(nodeCoordinator).localBoundingBoxOf(nodeCoordinator, true);
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        NodeCoordinator nodeCoordinator;
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        NodeCoordinator parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            NodeCoordinator nodeCoordinator2 = parentLayoutCoordinates;
            layoutCoordinates2 = layoutCoordinates;
            layoutCoordinates = nodeCoordinator2;
            if (layoutCoordinates == null) {
                break;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
        if (layoutCoordinates2 instanceof NodeCoordinator) {
            nodeCoordinator = (NodeCoordinator) layoutCoordinates2;
        } else {
            nodeCoordinator = null;
        }
        if (nodeCoordinator == null) {
            return layoutCoordinates2;
        }
        NodeCoordinator wrappedBy$ui_release = nodeCoordinator.getWrappedBy$ui_release();
        while (true) {
            NodeCoordinator nodeCoordinator3 = wrappedBy$ui_release;
            NodeCoordinator nodeCoordinator4 = nodeCoordinator;
            nodeCoordinator = nodeCoordinator3;
            if (nodeCoordinator != null) {
                wrappedBy$ui_release = nodeCoordinator.getWrappedBy$ui_release();
            } else {
                return nodeCoordinator4;
            }
        }
    }
}
