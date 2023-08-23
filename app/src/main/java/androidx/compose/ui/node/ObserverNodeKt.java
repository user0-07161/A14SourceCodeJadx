package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ObserverNodeKt {
    public static final void observeReads(Modifier.Node node, Function0 function0) {
        Function1 function1;
        Intrinsics.checkNotNullParameter(node, "<this>");
        ModifierNodeOwnerScope ownerScope$ui_release = node.getOwnerScope$ui_release();
        if (ownerScope$ui_release == null) {
            ownerScope$ui_release = new ModifierNodeOwnerScope((ObserverNode) node);
            node.setOwnerScope$ui_release(ownerScope$ui_release);
        }
        OwnerSnapshotObserver snapshotObserver = ((AndroidComposeView) DelegatableNodeKt.requireOwner(node)).getSnapshotObserver();
        function1 = ModifierNodeOwnerScope.OnObserveReadsChanged;
        snapshotObserver.observeReads$ui_release(ownerScope$ui_release, function1, function0);
    }
}
