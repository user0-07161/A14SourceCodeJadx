package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface MeasureScope extends Density {
    LayoutDirection getLayoutDirection();

    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.ui.layout.MeasureScope$layout$1] */
    default MeasureScope$layout$1 layout(final int i, final int i2, final Map alignmentLines, final Function1 placementBlock) {
        Intrinsics.checkNotNullParameter(alignmentLines, "alignmentLines");
        Intrinsics.checkNotNullParameter(placementBlock, "placementBlock");
        return new MeasureResult(i, i2, alignmentLines, this, placementBlock) { // from class: androidx.compose.ui.layout.MeasureScope$layout$1
            final /* synthetic */ Function1 $placementBlock;
            final /* synthetic */ int $width;
            private final Map alignmentLines;
            private final int height;
            final /* synthetic */ MeasureScope this$0;
            private final int width;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$width = i;
                this.this$0 = this;
                this.$placementBlock = placementBlock;
                this.width = i;
                this.height = i2;
                this.alignmentLines = alignmentLines;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final Map getAlignmentLines() {
                return this.alignmentLines;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final int getHeight() {
                return this.height;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final int getWidth() {
                return this.width;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final void placeChildren() {
                LookaheadCapablePlaceable lookaheadCapablePlaceable;
                LayoutCoordinates layoutCoordinates;
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
                Placeable.PlacementScope.Companion companion = Placeable.PlacementScope.Companion;
                MeasureScope measureScope = this.this$0;
                LayoutDirection layoutDirection = measureScope.getLayoutDirection();
                if (measureScope instanceof LookaheadCapablePlaceable) {
                    lookaheadCapablePlaceable = (LookaheadCapablePlaceable) measureScope;
                } else {
                    lookaheadCapablePlaceable = null;
                }
                layoutCoordinates = Placeable.PlacementScope._coordinates;
                int i3 = Placeable.PlacementScope.parentWidth;
                LayoutDirection layoutDirection2 = Placeable.PlacementScope.parentLayoutDirection;
                layoutNodeLayoutDelegate = Placeable.PlacementScope.layoutDelegate;
                Placeable.PlacementScope.parentWidth = this.$width;
                Placeable.PlacementScope.parentLayoutDirection = layoutDirection;
                boolean access$configureForPlacingForAlignment = Placeable.PlacementScope.Companion.access$configureForPlacingForAlignment(lookaheadCapablePlaceable);
                this.$placementBlock.invoke(companion);
                if (lookaheadCapablePlaceable != null) {
                    lookaheadCapablePlaceable.setPlacingForAlignment$ui_release(access$configureForPlacingForAlignment);
                }
                Placeable.PlacementScope.parentWidth = i3;
                Placeable.PlacementScope.parentLayoutDirection = layoutDirection2;
                Placeable.PlacementScope._coordinates = layoutCoordinates;
                Placeable.PlacementScope.layoutDelegate = layoutNodeLayoutDelegate;
            }
        };
    }
}
