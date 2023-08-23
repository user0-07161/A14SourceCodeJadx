package androidx.compose.ui.layout;

import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Placeable {
    private int height;
    private long measuredSize = IntSizeKt.IntSize(0, 0);
    private long measurementConstraints = PlaceableKt.access$getDefaultConstraints$p();
    private int width;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class PlacementScope {
        private static LayoutCoordinates _coordinates;
        private static LayoutNodeLayoutDelegate layoutDelegate;
        private static int parentWidth;
        public static final Companion Companion = new Companion();
        private static LayoutDirection parentLayoutDirection = LayoutDirection.Ltr;

        /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
        /* loaded from: classes.dex */
        public final class Companion extends PlacementScope {
            public static final boolean access$configureForPlacingForAlignment(LookaheadCapablePlaceable lookaheadCapablePlaceable) {
                boolean z = false;
                if (lookaheadCapablePlaceable == null) {
                    PlacementScope._coordinates = null;
                    PlacementScope.layoutDelegate = null;
                    return false;
                }
                boolean isPlacingForAlignment$ui_release = lookaheadCapablePlaceable.isPlacingForAlignment$ui_release();
                LookaheadCapablePlaceable parent = lookaheadCapablePlaceable.getParent();
                if (parent != null && parent.isPlacingForAlignment$ui_release()) {
                    z = true;
                }
                if (z) {
                    lookaheadCapablePlaceable.setPlacingForAlignment$ui_release(true);
                }
                PlacementScope.layoutDelegate = lookaheadCapablePlaceable.getLayoutNode().getLayoutDelegate$ui_release();
                if (lookaheadCapablePlaceable.isPlacingForAlignment$ui_release() || lookaheadCapablePlaceable.isShallowPlacing$ui_release()) {
                    PlacementScope._coordinates = null;
                } else {
                    PlacementScope._coordinates = lookaheadCapablePlaceable.getCoordinates();
                }
                return isPlacingForAlignment$ui_release;
            }
        }

        public static final /* synthetic */ LayoutNodeLayoutDelegate access$getLayoutDelegate$cp() {
            return layoutDelegate;
        }

        public static final /* synthetic */ LayoutDirection access$getParentLayoutDirection$cp() {
            return parentLayoutDirection;
        }

        public static final /* synthetic */ int access$getParentWidth$cp() {
            return parentWidth;
        }

        public static final /* synthetic */ LayoutCoordinates access$get_coordinates$cp() {
            return _coordinates;
        }

        public static final /* synthetic */ void access$setLayoutDelegate$cp(LayoutNodeLayoutDelegate layoutNodeLayoutDelegate) {
            layoutDelegate = layoutNodeLayoutDelegate;
        }

        public static final /* synthetic */ void access$setParentLayoutDirection$cp(LayoutDirection layoutDirection) {
            parentLayoutDirection = layoutDirection;
        }

        public static final /* synthetic */ void access$setParentWidth$cp(int i) {
            parentWidth = i;
        }

        public static final /* synthetic */ void access$set_coordinates$cp(LayoutCoordinates layoutCoordinates) {
            _coordinates = layoutCoordinates;
        }

        public static void place$default(PlacementScope placementScope, Placeable placeable, int i, int i2) {
            placementScope.getClass();
            Intrinsics.checkNotNullParameter(placeable, "<this>");
            long IntOffset = IntOffsetKt.IntOffset(i, i2);
            long m211getApparentToRealOffsetnOccac = placeable.m211getApparentToRealOffsetnOccac();
            placeable.mo216placeAtf8xVGno(IntOffsetKt.IntOffset(((int) (IntOffset >> 32)) + ((int) (m211getApparentToRealOffsetnOccac >> 32)), IntOffset.m402getYimpl(m211getApparentToRealOffsetnOccac) + IntOffset.m402getYimpl(IntOffset)), 0.0f, null);
        }

        /* renamed from: place-70tqf50 */
        public static void m219place70tqf50(Placeable place, long j, float f) {
            Intrinsics.checkNotNullParameter(place, "$this$place");
            long m211getApparentToRealOffsetnOccac = place.m211getApparentToRealOffsetnOccac();
            place.mo216placeAtf8xVGno(IntOffsetKt.IntOffset(((int) (j >> 32)) + ((int) (m211getApparentToRealOffsetnOccac >> 32)), IntOffset.m402getYimpl(m211getApparentToRealOffsetnOccac) + IntOffset.m402getYimpl(j)), f, null);
        }

        public static void placeRelative$default(PlacementScope placementScope, Placeable placeable, int i, int i2) {
            placementScope.getClass();
            Intrinsics.checkNotNullParameter(placeable, "<this>");
            long IntOffset = IntOffsetKt.IntOffset(i, i2);
            if (parentLayoutDirection != LayoutDirection.Ltr && parentWidth != 0) {
                long IntOffset2 = IntOffsetKt.IntOffset((parentWidth - placeable.getWidth()) - ((int) (IntOffset >> 32)), IntOffset.m402getYimpl(IntOffset));
                long m211getApparentToRealOffsetnOccac = placeable.m211getApparentToRealOffsetnOccac();
                placeable.mo216placeAtf8xVGno(IntOffsetKt.IntOffset(((int) (IntOffset2 >> 32)) + ((int) (m211getApparentToRealOffsetnOccac >> 32)), IntOffset.m402getYimpl(m211getApparentToRealOffsetnOccac) + IntOffset.m402getYimpl(IntOffset2)), 0.0f, null);
                return;
            }
            long m211getApparentToRealOffsetnOccac2 = placeable.m211getApparentToRealOffsetnOccac();
            placeable.mo216placeAtf8xVGno(IntOffsetKt.IntOffset(((int) (IntOffset >> 32)) + ((int) (m211getApparentToRealOffsetnOccac2 >> 32)), IntOffset.m402getYimpl(m211getApparentToRealOffsetnOccac2) + IntOffset.m402getYimpl(IntOffset)), 0.0f, null);
        }

        public static void placeRelativeWithLayer$default(PlacementScope placementScope, Placeable placeable) {
            Function1 layerBlock;
            layerBlock = PlaceableKt.DefaultLayerBlock;
            placementScope.getClass();
            Intrinsics.checkNotNullParameter(placeable, "<this>");
            Intrinsics.checkNotNullParameter(layerBlock, "layerBlock");
            long IntOffset = IntOffsetKt.IntOffset(0, 0);
            if (parentLayoutDirection != LayoutDirection.Ltr && parentWidth != 0) {
                long IntOffset2 = IntOffsetKt.IntOffset((parentWidth - placeable.getWidth()) - ((int) (IntOffset >> 32)), IntOffset.m402getYimpl(IntOffset));
                long m211getApparentToRealOffsetnOccac = placeable.m211getApparentToRealOffsetnOccac();
                placeable.mo216placeAtf8xVGno(IntOffsetKt.IntOffset(((int) (IntOffset2 >> 32)) + ((int) (m211getApparentToRealOffsetnOccac >> 32)), IntOffset.m402getYimpl(m211getApparentToRealOffsetnOccac) + IntOffset.m402getYimpl(IntOffset2)), 0.0f, layerBlock);
                return;
            }
            long m211getApparentToRealOffsetnOccac2 = placeable.m211getApparentToRealOffsetnOccac();
            placeable.mo216placeAtf8xVGno(IntOffsetKt.IntOffset(((int) (IntOffset >> 32)) + ((int) (m211getApparentToRealOffsetnOccac2 >> 32)), IntOffset.m402getYimpl(m211getApparentToRealOffsetnOccac2) + IntOffset.m402getYimpl(IntOffset)), 0.0f, layerBlock);
        }

        public static void placeWithLayer$default(PlacementScope placementScope, Placeable placeable, Function1 layerBlock) {
            placementScope.getClass();
            Intrinsics.checkNotNullParameter(placeable, "<this>");
            Intrinsics.checkNotNullParameter(layerBlock, "layerBlock");
            long IntOffset = IntOffsetKt.IntOffset(0, 0);
            long m211getApparentToRealOffsetnOccac = placeable.m211getApparentToRealOffsetnOccac();
            placeable.mo216placeAtf8xVGno(IntOffsetKt.IntOffset(((int) (IntOffset >> 32)) + ((int) (m211getApparentToRealOffsetnOccac >> 32)), IntOffset.m402getYimpl(m211getApparentToRealOffsetnOccac) + IntOffset.m402getYimpl(IntOffset)), 0.0f, layerBlock);
        }

        /* renamed from: placeWithLayer-aW-9-wM */
        public static void m220placeWithLayeraW9wM(Placeable placeWithLayer, long j, float f, Function1 layerBlock) {
            Intrinsics.checkNotNullParameter(placeWithLayer, "$this$placeWithLayer");
            Intrinsics.checkNotNullParameter(layerBlock, "layerBlock");
            long m211getApparentToRealOffsetnOccac = placeWithLayer.m211getApparentToRealOffsetnOccac();
            placeWithLayer.mo216placeAtf8xVGno(IntOffsetKt.IntOffset(((int) (j >> 32)) + ((int) (m211getApparentToRealOffsetnOccac >> 32)), IntOffset.m402getYimpl(m211getApparentToRealOffsetnOccac) + IntOffset.m402getYimpl(j)), f, layerBlock);
        }
    }

    private final void recalculateWidthAndHeight() {
        this.width = RangesKt.coerceIn((int) (this.measuredSize >> 32), Constraints.m383getMinWidthimpl(this.measurementConstraints), Constraints.m381getMaxWidthimpl(this.measurementConstraints));
        this.height = RangesKt.coerceIn(IntSize.m405getHeightimpl(this.measuredSize), Constraints.m382getMinHeightimpl(this.measurementConstraints), Constraints.m380getMaxHeightimpl(this.measurementConstraints));
    }

    /* renamed from: getApparentToRealOffset-nOcc-ac */
    public final long m211getApparentToRealOffsetnOccac() {
        int i = this.width;
        long j = this.measuredSize;
        return IntOffsetKt.IntOffset((i - ((int) (j >> 32))) / 2, (this.height - IntSize.m405getHeightimpl(j)) / 2);
    }

    public final int getHeight() {
        return this.height;
    }

    /* renamed from: getLastMeasurementConstraints-msEJaDk$ui_release */
    public long m212getLastMeasurementConstraintsmsEJaDk$ui_release() {
        return m214getMeasurementConstraintsmsEJaDk();
    }

    public final int getMeasuredHeight() {
        return IntSize.m405getHeightimpl(this.measuredSize);
    }

    /* renamed from: getMeasuredSize-YbymL2g */
    public final long m213getMeasuredSizeYbymL2g() {
        return this.measuredSize;
    }

    public int getMeasuredWidth() {
        return (int) (this.measuredSize >> 32);
    }

    /* renamed from: getMeasurementConstraints-msEJaDk */
    public final long m214getMeasurementConstraintsmsEJaDk() {
        return this.measurementConstraints;
    }

    /* renamed from: getSize-YbymL2g */
    public long m215getSizeYbymL2g() {
        return m213getMeasuredSizeYbymL2g();
    }

    public final int getWidth() {
        return this.width;
    }

    /* renamed from: placeAt-f8xVGno */
    protected abstract void mo216placeAtf8xVGno(long j, float f, Function1 function1);

    /* renamed from: setMeasuredSize-ozmzZPI */
    public final void m217setMeasuredSizeozmzZPI(long j) {
        boolean z;
        if (this.measuredSize == j) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            this.measuredSize = j;
            recalculateWidthAndHeight();
        }
    }

    /* renamed from: setMeasurementConstraints-BRTryo0 */
    public final void m218setMeasurementConstraintsBRTryo0(long j) {
        if (!Constraints.m377equalsimpl0(this.measurementConstraints, j)) {
            this.measurementConstraints = j;
            recalculateWidthAndHeight();
        }
    }
}
