package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class SpacerMeasurePolicy implements MeasurePolicy {
    public static final SpacerMeasurePolicy INSTANCE = new SpacerMeasurePolicy();

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo1measure3p2s80s(MeasureScope measure, List list, long j) {
        boolean z;
        int i;
        MeasureScope$layout$1 layout;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        boolean z2 = true;
        int i2 = 0;
        if (Constraints.m381getMaxWidthimpl(j) == Constraints.m383getMinWidthimpl(j)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i = Constraints.m381getMaxWidthimpl(j);
        } else {
            i = 0;
        }
        if (Constraints.m380getMaxHeightimpl(j) != Constraints.m382getMinHeightimpl(j)) {
            z2 = false;
        }
        if (z2) {
            i2 = Constraints.m380getMaxHeightimpl(j);
        }
        layout = measure.layout(i, i2, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.SpacerMeasurePolicy$measure$1$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope layout2 = (Placeable.PlacementScope) obj;
                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                return Unit.INSTANCE;
            }
        });
        return layout;
    }
}
