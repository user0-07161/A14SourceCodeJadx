package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ModifierNodeOwnerScope implements OwnerScope {
    private static final Function1 OnObserveReadsChanged = new Function1() { // from class: androidx.compose.ui.node.ModifierNodeOwnerScope$Companion$OnObserveReadsChanged$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            ModifierNodeOwnerScope it = (ModifierNodeOwnerScope) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            if (it.isValidOwnerScope()) {
                ((FocusTargetModifierNode) it.getObserverNode$ui_release()).onObservedReadsChanged();
            }
            return Unit.INSTANCE;
        }
    };
    private final ObserverNode observerNode;

    public ModifierNodeOwnerScope(ObserverNode observerNode) {
        Intrinsics.checkNotNullParameter(observerNode, "observerNode");
        this.observerNode = observerNode;
    }

    public final ObserverNode getObserverNode$ui_release() {
        return this.observerNode;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return ((Modifier.Node) this.observerNode).getNode().isAttached();
    }
}
