package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FillModifier extends InspectorValueInfo implements LayoutModifier {
    private final Direction direction;
    private final float fraction;

    public FillModifier(Direction direction, float f, Function1 function1) {
        super(function1);
        this.direction = direction;
        this.fraction = f;
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (!(obj instanceof FillModifier)) {
            return false;
        }
        FillModifier fillModifier = (FillModifier) obj;
        if (this.direction != fillModifier.direction) {
            return false;
        }
        if (this.fraction == fillModifier.fraction) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Float.hashCode(this.fraction) + (this.direction.hashCode() * 31);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        int m383getMinWidthimpl;
        int m381getMaxWidthimpl;
        int m382getMinHeightimpl;
        int m380getMaxHeightimpl;
        MeasureScope$layout$1 layout;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        boolean m379getHasBoundedWidthimpl = Constraints.m379getHasBoundedWidthimpl(j);
        float f = this.fraction;
        Direction direction = this.direction;
        if (m379getHasBoundedWidthimpl && direction != Direction.Vertical) {
            m383getMinWidthimpl = RangesKt.coerceIn(MathKt.roundToInt(Constraints.m381getMaxWidthimpl(j) * f), Constraints.m383getMinWidthimpl(j), Constraints.m381getMaxWidthimpl(j));
            m381getMaxWidthimpl = m383getMinWidthimpl;
        } else {
            m383getMinWidthimpl = Constraints.m383getMinWidthimpl(j);
            m381getMaxWidthimpl = Constraints.m381getMaxWidthimpl(j);
        }
        if (Constraints.m378getHasBoundedHeightimpl(j) && direction != Direction.Horizontal) {
            m382getMinHeightimpl = RangesKt.coerceIn(MathKt.roundToInt(Constraints.m380getMaxHeightimpl(j) * f), Constraints.m382getMinHeightimpl(j), Constraints.m380getMaxHeightimpl(j));
            m380getMaxHeightimpl = m382getMinHeightimpl;
        } else {
            m382getMinHeightimpl = Constraints.m382getMinHeightimpl(j);
            m380getMaxHeightimpl = Constraints.m380getMaxHeightimpl(j);
        }
        final Placeable mo210measureBRTryo0 = measurable.mo210measureBRTryo0(ConstraintsKt.Constraints(m383getMinWidthimpl, m381getMaxWidthimpl, m382getMinHeightimpl, m380getMaxHeightimpl));
        layout = measure.layout(mo210measureBRTryo0.getWidth(), mo210measureBRTryo0.getHeight(), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.FillModifier$measure$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope layout2 = (Placeable.PlacementScope) obj;
                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                Placeable.PlacementScope.placeRelative$default(layout2, Placeable.this, 0, 0);
                return Unit.INSTANCE;
            }
        });
        return layout;
    }
}
