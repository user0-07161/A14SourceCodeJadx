package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FocusOwnerImpl$moveFocus$foundNextItem$1 extends Lambda implements Function1 {
    final /* synthetic */ FocusTargetModifierNode $source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FocusOwnerImpl$moveFocus$foundNextItem$1(FocusTargetModifierNode focusTargetModifierNode) {
        super(1);
        this.$source = focusTargetModifierNode;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        FocusTargetModifierNode destination = (FocusTargetModifierNode) obj;
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (Intrinsics.areEqual(destination, this.$source)) {
            return Boolean.FALSE;
        }
        Modifier.Node nearestAncestor = DelegatableNodeKt.nearestAncestor(destination, 1024);
        if (!(nearestAncestor instanceof FocusTargetModifierNode)) {
            nearestAncestor = null;
        }
        if (((FocusTargetModifierNode) nearestAncestor) != null) {
            return Boolean.valueOf(FocusTransactionsKt.requestFocus(destination));
        }
        throw new IllegalStateException("Focus search landed at the root.".toString());
    }
}
