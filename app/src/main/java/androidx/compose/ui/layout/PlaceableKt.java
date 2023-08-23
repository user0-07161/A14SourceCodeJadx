package androidx.compose.ui.layout;

import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class PlaceableKt {
    private static final Function1 DefaultLayerBlock = new Function1() { // from class: androidx.compose.ui.layout.PlaceableKt$DefaultLayerBlock$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Intrinsics.checkNotNullParameter((ReusableGraphicsLayerScope) obj, "$this$null");
            return Unit.INSTANCE;
        }
    };
    private static final long DefaultConstraints = ConstraintsKt.Constraints$default(0, 0, 15);

    public static final /* synthetic */ long access$getDefaultConstraints$p() {
        return DefaultConstraints;
    }

    public static final /* synthetic */ Function1 access$getDefaultLayerBlock$p() {
        return DefaultLayerBlock;
    }
}
