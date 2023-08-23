package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface ComposeUiNode {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Function0 Constructor;
        private static final Function2 SetDensity;
        private static final Function2 SetLayoutDirection;
        private static final Function2 SetMeasurePolicy;
        private static final Function2 SetModifier;
        private static final Function2 SetViewConfiguration;

        static {
            Function0 function0;
            int i = LayoutNode.$r8$clinit;
            function0 = LayoutNode.Constructor;
            Constructor = function0;
            SetModifier = new Function2() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetModifier$1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ComposeUiNode composeUiNode = (ComposeUiNode) obj;
                    Modifier it = (Modifier) obj2;
                    Intrinsics.checkNotNullParameter(composeUiNode, "$this$null");
                    Intrinsics.checkNotNullParameter(it, "it");
                    ((LayoutNode) composeUiNode).setModifier(it);
                    return Unit.INSTANCE;
                }
            };
            SetDensity = new Function2() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetDensity$1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ComposeUiNode composeUiNode = (ComposeUiNode) obj;
                    Density it = (Density) obj2;
                    Intrinsics.checkNotNullParameter(composeUiNode, "$this$null");
                    Intrinsics.checkNotNullParameter(it, "it");
                    ((LayoutNode) composeUiNode).setDensity(it);
                    return Unit.INSTANCE;
                }
            };
            SetMeasurePolicy = new Function2() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetMeasurePolicy$1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ComposeUiNode composeUiNode = (ComposeUiNode) obj;
                    MeasurePolicy it = (MeasurePolicy) obj2;
                    Intrinsics.checkNotNullParameter(composeUiNode, "$this$null");
                    Intrinsics.checkNotNullParameter(it, "it");
                    ((LayoutNode) composeUiNode).setMeasurePolicy(it);
                    return Unit.INSTANCE;
                }
            };
            SetLayoutDirection = new Function2() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetLayoutDirection$1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ComposeUiNode composeUiNode = (ComposeUiNode) obj;
                    LayoutDirection it = (LayoutDirection) obj2;
                    Intrinsics.checkNotNullParameter(composeUiNode, "$this$null");
                    Intrinsics.checkNotNullParameter(it, "it");
                    ((LayoutNode) composeUiNode).setLayoutDirection(it);
                    return Unit.INSTANCE;
                }
            };
            SetViewConfiguration = new Function2() { // from class: androidx.compose.ui.node.ComposeUiNode$Companion$SetViewConfiguration$1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ComposeUiNode composeUiNode = (ComposeUiNode) obj;
                    ViewConfiguration it = (ViewConfiguration) obj2;
                    Intrinsics.checkNotNullParameter(composeUiNode, "$this$null");
                    Intrinsics.checkNotNullParameter(it, "it");
                    ((LayoutNode) composeUiNode).setViewConfiguration(it);
                    return Unit.INSTANCE;
                }
            };
        }

        public static Function0 getConstructor() {
            return Constructor;
        }

        public static Function2 getSetDensity() {
            return SetDensity;
        }

        public static Function2 getSetLayoutDirection() {
            return SetLayoutDirection;
        }

        public static Function2 getSetMeasurePolicy() {
            return SetMeasurePolicy;
        }

        public static Function2 getSetModifier() {
            return SetModifier;
        }

        public static Function2 getSetViewConfiguration() {
            return SetViewConfiguration;
        }
    }
}
