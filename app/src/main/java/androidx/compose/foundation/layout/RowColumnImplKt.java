package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class RowColumnImplKt {
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.foundation.layout.RowColumnImplKt$rowColumnMeasurePolicy$1] */
    /* renamed from: rowColumnMeasurePolicy-TDGSqEk  reason: not valid java name */
    public static final RowColumnImplKt$rowColumnMeasurePolicy$1 m13rowColumnMeasurePolicyTDGSqEk(final Function5 arrangement, final float f, final CrossAxisAlignment crossAxisAlignment) {
        Intrinsics.checkNotNullParameter(arrangement, "arrangement");
        return new MeasurePolicy() { // from class: androidx.compose.foundation.layout.RowColumnImplKt$rowColumnMeasurePolicy$1
            final /* synthetic */ SizeMode $crossAxisSize;
            final /* synthetic */ LayoutOrientation $orientation;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                LayoutOrientation layoutOrientation = LayoutOrientation.Vertical;
                SizeMode sizeMode = SizeMode.Wrap;
                this.$orientation = layoutOrientation;
                this.$crossAxisSize = sizeMode;
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* renamed from: measure-3p2s80s */
            public final MeasureResult mo1measure3p2s80s(final MeasureScope measure, List list, long j) {
                int crossAxisSize;
                int mainAxisSize;
                MeasureScope$layout$1 layout;
                Intrinsics.checkNotNullParameter(measure, "$this$measure");
                final RowColumnMeasurementHelper rowColumnMeasurementHelper = new RowColumnMeasurementHelper(this.$orientation, arrangement, f, this.$crossAxisSize, crossAxisAlignment, list, new Placeable[list.size()]);
                final RowColumnMeasureHelperResult m14measureWithoutPlacing_EkL_Y = rowColumnMeasurementHelper.m14measureWithoutPlacing_EkL_Y(measure, j, list.size());
                if (this.$orientation == LayoutOrientation.Horizontal) {
                    crossAxisSize = m14measureWithoutPlacing_EkL_Y.getMainAxisSize();
                    mainAxisSize = m14measureWithoutPlacing_EkL_Y.getCrossAxisSize();
                } else {
                    crossAxisSize = m14measureWithoutPlacing_EkL_Y.getCrossAxisSize();
                    mainAxisSize = m14measureWithoutPlacing_EkL_Y.getMainAxisSize();
                }
                layout = measure.layout(crossAxisSize, mainAxisSize, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.RowColumnImplKt$rowColumnMeasurePolicy$1$measure$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Placeable.PlacementScope layout2 = (Placeable.PlacementScope) obj;
                        Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                        RowColumnMeasurementHelper.this.placeHelper(layout2, m14measureWithoutPlacing_EkL_Y, measure.getLayoutDirection());
                        return Unit.INSTANCE;
                    }
                });
                return layout;
            }
        };
    }
}
