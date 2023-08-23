package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RowColumnMeasurementHelper {
    private final Function5 arrangement;
    private final float arrangementSpacing;
    private final CrossAxisAlignment crossAxisAlignment;
    private final SizeMode crossAxisSize;
    private final List measurables;
    private final LayoutOrientation orientation;
    private final Placeable[] placeables;
    private final RowColumnParentData[] rowColumnParentData;

    public RowColumnMeasurementHelper(LayoutOrientation layoutOrientation, Function5 function5, float f, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, List list, Placeable[] placeableArr) {
        RowColumnParentData rowColumnParentData;
        this.orientation = layoutOrientation;
        this.arrangement = function5;
        this.arrangementSpacing = f;
        this.crossAxisSize = sizeMode;
        this.crossAxisAlignment = crossAxisAlignment;
        this.measurables = list;
        this.placeables = placeableArr;
        int size = list.size();
        RowColumnParentData[] rowColumnParentDataArr = new RowColumnParentData[size];
        for (int i = 0; i < size; i++) {
            Measurable measurable = (Measurable) this.measurables.get(i);
            Intrinsics.checkNotNullParameter(measurable, "<this>");
            Object parentData = measurable.getParentData();
            if (parentData instanceof RowColumnParentData) {
                rowColumnParentData = (RowColumnParentData) parentData;
            } else {
                rowColumnParentData = null;
            }
            rowColumnParentDataArr[i] = rowColumnParentData;
        }
        this.rowColumnParentData = rowColumnParentDataArr;
    }

    public final int crossAxisSize(Placeable placeable) {
        if (this.orientation == LayoutOrientation.Horizontal) {
            return placeable.getHeight();
        }
        return placeable.getWidth();
    }

    public final int mainAxisSize(Placeable placeable) {
        Intrinsics.checkNotNullParameter(placeable, "<this>");
        if (this.orientation == LayoutOrientation.Horizontal) {
            return placeable.getWidth();
        }
        return placeable.getHeight();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01eb A[LOOP:1: B:100:0x01e9->B:101:0x01eb, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01f4 A[LOOP:2: B:103:0x01f2->B:104:0x01f4, LOOP_END] */
    /* renamed from: measureWithoutPlacing-_EkL_-Y  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.foundation.layout.RowColumnMeasureHelperResult m14measureWithoutPlacing_EkL_Y(androidx.compose.ui.layout.MeasureScope r23, long r24, int r26) {
        /*
            Method dump skipped, instructions count: 538
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.RowColumnMeasurementHelper.m14measureWithoutPlacing_EkL_Y(androidx.compose.ui.layout.MeasureScope, long, int):androidx.compose.foundation.layout.RowColumnMeasureHelperResult");
    }

    public final void placeHelper(Placeable.PlacementScope placeableScope, RowColumnMeasureHelperResult measureResult, LayoutDirection layoutDirection) {
        RowColumnParentData rowColumnParentData;
        CrossAxisAlignment crossAxisAlignment;
        LayoutDirection layoutDirection2;
        Intrinsics.checkNotNullParameter(placeableScope, "placeableScope");
        Intrinsics.checkNotNullParameter(measureResult, "measureResult");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        int endIndex = measureResult.getEndIndex();
        for (int startIndex = measureResult.getStartIndex(); startIndex < endIndex; startIndex++) {
            Placeable placeable = this.placeables[startIndex];
            Intrinsics.checkNotNull(placeable);
            int[] mainAxisPositions = measureResult.getMainAxisPositions();
            Object parentData = ((Measurable) this.measurables.get(startIndex)).getParentData();
            if (parentData instanceof RowColumnParentData) {
                rowColumnParentData = (RowColumnParentData) parentData;
            } else {
                rowColumnParentData = null;
            }
            int crossAxisSize = measureResult.getCrossAxisSize();
            if (rowColumnParentData == null || (crossAxisAlignment = rowColumnParentData.getCrossAxisAlignment()) == null) {
                crossAxisAlignment = this.crossAxisAlignment;
            }
            int crossAxisSize2 = crossAxisSize - crossAxisSize(placeable);
            LayoutOrientation layoutOrientation = LayoutOrientation.Horizontal;
            LayoutOrientation layoutOrientation2 = this.orientation;
            if (layoutOrientation2 == layoutOrientation) {
                layoutDirection2 = LayoutDirection.Ltr;
            } else {
                layoutDirection2 = layoutDirection;
            }
            int align$foundation_layout_release = crossAxisAlignment.align$foundation_layout_release(crossAxisSize2, layoutDirection2, placeable) + 0;
            if (layoutOrientation2 == layoutOrientation) {
                Placeable.PlacementScope.place$default(placeableScope, placeable, mainAxisPositions[startIndex - measureResult.getStartIndex()], align$foundation_layout_release);
            } else {
                Placeable.PlacementScope.place$default(placeableScope, placeable, align$foundation_layout_release, mainAxisPositions[startIndex - measureResult.getStartIndex()]);
            }
        }
    }
}
