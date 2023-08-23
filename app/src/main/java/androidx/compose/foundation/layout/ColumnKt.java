package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.CrossAxisAlignment;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ColumnKt {
    private static final RowColumnImplKt$rowColumnMeasurePolicy$1 DefaultColumnMeasurePolicy;

    static {
        int i = Arrangement.$r8$clinit;
        CrossAxisAlignment.HorizontalCrossAxisAlignment horizontalCrossAxisAlignment = new CrossAxisAlignment.HorizontalCrossAxisAlignment(Alignment.Companion.getStart());
        DefaultColumnMeasurePolicy = RowColumnImplKt.m13rowColumnMeasurePolicyTDGSqEk(new Function5() { // from class: androidx.compose.foundation.layout.ColumnKt$DefaultColumnMeasurePolicy$1
            @Override // kotlin.jvm.functions.Function5
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                int intValue = ((Number) obj).intValue();
                int[] size = (int[]) obj2;
                Density density = (Density) obj4;
                int[] outPosition = (int[]) obj5;
                Intrinsics.checkNotNullParameter(size, "size");
                Intrinsics.checkNotNullParameter((LayoutDirection) obj3, "<anonymous parameter 2>");
                Intrinsics.checkNotNullParameter(density, "density");
                Intrinsics.checkNotNullParameter(outPosition, "outPosition");
                Arrangement.getTop().arrange(density, intValue, size, outPosition);
                return Unit.INSTANCE;
            }
        }, 0, horizontalCrossAxisAlignment);
    }

    public static final MeasurePolicy columnMeasurePolicy(final Arrangement$Top$1 arrangement$Top$1, BiasAlignment.Horizontal horizontal, Composer composer) {
        MeasurePolicy measurePolicy;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(1089876336);
        int i = ComposerKt.$r8$clinit;
        if (Intrinsics.areEqual(arrangement$Top$1, Arrangement.getTop()) && Intrinsics.areEqual(horizontal, Alignment.Companion.getStart())) {
            measurePolicy = DefaultColumnMeasurePolicy;
        } else {
            composerImpl.startReplaceableGroup(511388516);
            boolean changed = composerImpl.changed(arrangement$Top$1) | composerImpl.changed(horizontal);
            Object nextSlot = composerImpl.nextSlot();
            if (changed || nextSlot == Composer.Companion.getEmpty()) {
                CrossAxisAlignment.HorizontalCrossAxisAlignment horizontalCrossAxisAlignment = new CrossAxisAlignment.HorizontalCrossAxisAlignment(horizontal);
                nextSlot = RowColumnImplKt.m13rowColumnMeasurePolicyTDGSqEk(new Function5() { // from class: androidx.compose.foundation.layout.ColumnKt$columnMeasurePolicy$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(5);
                    }

                    @Override // kotlin.jvm.functions.Function5
                    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                        int intValue = ((Number) obj).intValue();
                        int[] size = (int[]) obj2;
                        Density density = (Density) obj4;
                        int[] outPosition = (int[]) obj5;
                        Intrinsics.checkNotNullParameter(size, "size");
                        Intrinsics.checkNotNullParameter((LayoutDirection) obj3, "<anonymous parameter 2>");
                        Intrinsics.checkNotNullParameter(density, "density");
                        Intrinsics.checkNotNullParameter(outPosition, "outPosition");
                        arrangement$Top$1.arrange(density, intValue, size, outPosition);
                        return Unit.INSTANCE;
                    }
                }, 0, horizontalCrossAxisAlignment);
                composerImpl.updateValue(nextSlot);
            }
            composerImpl.endReplaceableGroup();
            measurePolicy = (MeasurePolicy) nextSlot;
        }
        composerImpl.endReplaceableGroup();
        return measurePolicy;
    }
}
