package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class DrawModifierKt {
    public static final Modifier drawBehind(Modifier modifier, final Function1 onDraw) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(onDraw, "onDraw");
        InspectableValueKt.getNoInspectorInfo();
        final Function1 noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        return modifier.then(new ModifierNodeElement(onDraw, noInspectorInfo) { // from class: androidx.compose.ui.draw.DrawModifierKt$drawBehind$$inlined$modifierElementOf$1
            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node create() {
                return new DrawBackgroundModifier(onDraw);
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node update(Modifier.Node node) {
                Intrinsics.checkNotNullParameter(node, "node");
                ((DrawBackgroundModifier) node).setOnDraw(onDraw);
                return node;
            }
        });
    }
}
