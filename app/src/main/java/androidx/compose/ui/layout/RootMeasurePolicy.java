package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RootMeasurePolicy extends LayoutNode.NoIntrinsicsMeasurePolicy {
    public static final RootMeasurePolicy INSTANCE = new RootMeasurePolicy();

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo1measure3p2s80s(MeasureScope measure, List list, long j) {
        MeasureScope$layout$1 layout;
        MeasureScope$layout$1 layout2;
        MeasureScope$layout$1 layout3;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        if (list.isEmpty()) {
            layout3 = measure.layout(Constraints.m383getMinWidthimpl(j), Constraints.m382getMinHeightimpl(j), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.layout.RootMeasurePolicy$measure$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Placeable.PlacementScope layout4 = (Placeable.PlacementScope) obj;
                    Intrinsics.checkNotNullParameter(layout4, "$this$layout");
                    return Unit.INSTANCE;
                }
            });
            return layout3;
        }
        if (list.size() == 1) {
            final Placeable mo210measureBRTryo0 = ((Measurable) list.get(0)).mo210measureBRTryo0(j);
            layout2 = measure.layout(RangesKt.coerceIn(mo210measureBRTryo0.getWidth(), Constraints.m383getMinWidthimpl(j), Constraints.m381getMaxWidthimpl(j)), RangesKt.coerceIn(mo210measureBRTryo0.getHeight(), Constraints.m382getMinHeightimpl(j), Constraints.m380getMaxHeightimpl(j)), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.layout.RootMeasurePolicy$measure$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Placeable.PlacementScope layout4 = (Placeable.PlacementScope) obj;
                    Intrinsics.checkNotNullParameter(layout4, "$this$layout");
                    Placeable.PlacementScope.placeRelativeWithLayer$default(layout4, Placeable.this);
                    return Unit.INSTANCE;
                }
            });
            return layout2;
        }
        final ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(((Measurable) list.get(i)).mo210measureBRTryo0(j));
        }
        int size2 = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < size2; i4++) {
            Placeable placeable = (Placeable) arrayList.get(i4);
            i2 = Math.max(placeable.getWidth(), i2);
            i3 = Math.max(placeable.getHeight(), i3);
        }
        layout = measure.layout(RangesKt.coerceIn(i2, Constraints.m383getMinWidthimpl(j), Constraints.m381getMaxWidthimpl(j)), RangesKt.coerceIn(i3, Constraints.m382getMinHeightimpl(j), Constraints.m380getMaxHeightimpl(j)), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.layout.RootMeasurePolicy$measure$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope layout4 = (Placeable.PlacementScope) obj;
                Intrinsics.checkNotNullParameter(layout4, "$this$layout");
                List list2 = arrayList;
                int size3 = list2.size();
                for (int i5 = 0; i5 < size3; i5++) {
                    Placeable.PlacementScope.placeRelativeWithLayer$default(layout4, (Placeable) list2.get(i5));
                }
                return Unit.INSTANCE;
            }
        });
        return layout;
    }
}
