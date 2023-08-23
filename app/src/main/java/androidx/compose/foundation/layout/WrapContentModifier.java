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
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class WrapContentModifier extends InspectorValueInfo implements LayoutModifier {
    private final Object align;
    private final Function2 alignmentCallback;
    private final Direction direction;
    private final boolean unbounded;

    public WrapContentModifier(Direction direction, Function2 function2, Object obj, Function1 function1) {
        super(function1);
        this.direction = direction;
        this.unbounded = false;
        this.alignmentCallback = function2;
        this.align = obj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof WrapContentModifier)) {
            return false;
        }
        WrapContentModifier wrapContentModifier = (WrapContentModifier) obj;
        if (this.direction != wrapContentModifier.direction || this.unbounded != wrapContentModifier.unbounded || !Intrinsics.areEqual(this.align, wrapContentModifier.align)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode = Boolean.hashCode(this.unbounded);
        return this.align.hashCode() + ((hashCode + (this.direction.hashCode() * 31)) * 31);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(final MeasureScope measure, Measurable measurable, long j) {
        int m383getMinWidthimpl;
        int m381getMaxWidthimpl;
        MeasureScope$layout$1 layout;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Direction direction = Direction.Vertical;
        int i = 0;
        Direction direction2 = this.direction;
        if (direction2 != direction) {
            m383getMinWidthimpl = 0;
        } else {
            m383getMinWidthimpl = Constraints.m383getMinWidthimpl(j);
        }
        Direction direction3 = Direction.Horizontal;
        if (direction2 == direction3) {
            i = Constraints.m382getMinHeightimpl(j);
        }
        int i2 = Integer.MAX_VALUE;
        boolean z = this.unbounded;
        if (direction2 != direction && z) {
            m381getMaxWidthimpl = Integer.MAX_VALUE;
        } else {
            m381getMaxWidthimpl = Constraints.m381getMaxWidthimpl(j);
        }
        if (direction2 == direction3 || !z) {
            i2 = Constraints.m380getMaxHeightimpl(j);
        }
        final Placeable mo210measureBRTryo0 = measurable.mo210measureBRTryo0(ConstraintsKt.Constraints(m383getMinWidthimpl, m381getMaxWidthimpl, i, i2));
        final int coerceIn = RangesKt.coerceIn(mo210measureBRTryo0.getWidth(), Constraints.m383getMinWidthimpl(j), Constraints.m381getMaxWidthimpl(j));
        final int coerceIn2 = RangesKt.coerceIn(mo210measureBRTryo0.getHeight(), Constraints.m382getMinHeightimpl(j), Constraints.m380getMaxHeightimpl(j));
        layout = measure.layout(coerceIn, coerceIn2, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.WrapContentModifier$measure$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Function2 function2;
                Placeable.PlacementScope layout2 = (Placeable.PlacementScope) obj;
                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                function2 = WrapContentModifier.this.alignmentCallback;
                Placeable.PlacementScope.m219place70tqf50(mo210measureBRTryo0, ((IntOffset) function2.invoke(IntSize.m404boximpl(IntSizeKt.IntSize(coerceIn - mo210measureBRTryo0.getWidth(), coerceIn2 - mo210measureBRTryo0.getHeight())), measure.getLayoutDirection())).m403unboximpl(), 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout;
    }
}
