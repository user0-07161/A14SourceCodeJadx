package androidx.compose.ui.graphics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class BlockGraphicsLayerModifier extends Modifier.Node implements LayoutModifierNode {
    private Function1 layerBlock;

    public BlockGraphicsLayerModifier(Function1 layerBlock) {
        Intrinsics.checkNotNullParameter(layerBlock, "layerBlock");
        this.layerBlock = layerBlock;
    }

    public final Function1 getLayerBlock() {
        return this.layerBlock;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s  reason: not valid java name */
    public final MeasureResult mo88measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        MeasureScope$layout$1 layout;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        final Placeable mo210measureBRTryo0 = measurable.mo210measureBRTryo0(j);
        layout = measure.layout(mo210measureBRTryo0.getWidth(), mo210measureBRTryo0.getHeight(), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.graphics.BlockGraphicsLayerModifier$measure$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope layout2 = (Placeable.PlacementScope) obj;
                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                Placeable.PlacementScope.placeWithLayer$default(layout2, Placeable.this, this.getLayerBlock());
                return Unit.INSTANCE;
            }
        });
        return layout;
    }

    public final void setLayerBlock(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.layerBlock = function1;
    }

    public final String toString() {
        return "BlockGraphicsLayerModifier(block=" + this.layerBlock + ')';
    }
}
