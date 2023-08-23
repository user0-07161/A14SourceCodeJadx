package androidx.compose.foundation.layout;

import androidx.compose.animation.core.AnimationVector4D$$ExternalSyntheticOutline0;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class PaddingModifier extends InspectorValueInfo implements LayoutModifier {
    private final float bottom;
    private final float end;
    private final boolean rtlAware;
    private final float start;
    private final float top;

    public PaddingModifier(float f, float f2, float f3, float f4, Function1 function1) {
        super(function1);
        this.start = f;
        this.top = f2;
        this.end = f3;
        this.bottom = f4;
        boolean z = true;
        this.rtlAware = true;
        if ((f < 0.0f && !Dp.m390equalsimpl0(f, Float.NaN)) || ((f2 < 0.0f && !Dp.m390equalsimpl0(f2, Float.NaN)) || ((f3 < 0.0f && !Dp.m390equalsimpl0(f3, Float.NaN)) || (f4 < 0.0f && !Dp.m390equalsimpl0(f4, Float.NaN))))) {
            z = false;
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException("Padding must be non-negative".toString());
    }

    public final boolean equals(Object obj) {
        PaddingModifier paddingModifier;
        if (obj instanceof PaddingModifier) {
            paddingModifier = (PaddingModifier) obj;
        } else {
            paddingModifier = null;
        }
        if (paddingModifier == null || !Dp.m390equalsimpl0(this.start, paddingModifier.start) || !Dp.m390equalsimpl0(this.top, paddingModifier.top) || !Dp.m390equalsimpl0(this.end, paddingModifier.end) || !Dp.m390equalsimpl0(this.bottom, paddingModifier.bottom) || this.rtlAware != paddingModifier.rtlAware) {
            return false;
        }
        return true;
    }

    public final boolean getRtlAware() {
        return this.rtlAware;
    }

    /* renamed from: getStart-D9Ej5fM  reason: not valid java name */
    public final float m11getStartD9Ej5fM() {
        return this.start;
    }

    /* renamed from: getTop-D9Ej5fM  reason: not valid java name */
    public final float m12getTopD9Ej5fM() {
        return this.top;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.rtlAware) + AnimationVector4D$$ExternalSyntheticOutline0.m(this.bottom, AnimationVector4D$$ExternalSyntheticOutline0.m(this.end, AnimationVector4D$$ExternalSyntheticOutline0.m(this.top, Float.hashCode(this.start) * 31, 31), 31), 31);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(final MeasureScope measure, Measurable measurable, long j) {
        MeasureScope$layout$1 layout;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        int mo202roundToPx0680j_4 = measure.mo202roundToPx0680j_4(this.end) + measure.mo202roundToPx0680j_4(this.start);
        int mo202roundToPx0680j_42 = measure.mo202roundToPx0680j_4(this.bottom) + measure.mo202roundToPx0680j_4(this.top);
        final Placeable mo210measureBRTryo0 = measurable.mo210measureBRTryo0(ConstraintsKt.m388offsetNN6EwU(j, -mo202roundToPx0680j_4, -mo202roundToPx0680j_42));
        layout = measure.layout(RangesKt.coerceIn(mo210measureBRTryo0.getWidth() + mo202roundToPx0680j_4, Constraints.m383getMinWidthimpl(j), Constraints.m381getMaxWidthimpl(j)), RangesKt.coerceIn(mo210measureBRTryo0.getHeight() + mo202roundToPx0680j_42, Constraints.m382getMinHeightimpl(j), Constraints.m380getMaxHeightimpl(j)), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.PaddingModifier$measure$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope layout2 = (Placeable.PlacementScope) obj;
                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                if (PaddingModifier.this.getRtlAware()) {
                    Placeable.PlacementScope.placeRelative$default(layout2, mo210measureBRTryo0, measure.mo202roundToPx0680j_4(PaddingModifier.this.m11getStartD9Ej5fM()), measure.mo202roundToPx0680j_4(PaddingModifier.this.m12getTopD9Ej5fM()));
                } else {
                    Placeable.PlacementScope.place$default(layout2, mo210measureBRTryo0, measure.mo202roundToPx0680j_4(PaddingModifier.this.m11getStartD9Ej5fM()), measure.mo202roundToPx0680j_4(PaddingModifier.this.m12getTopD9Ej5fM()));
                }
                return Unit.INSTANCE;
            }
        });
        return layout;
    }
}
