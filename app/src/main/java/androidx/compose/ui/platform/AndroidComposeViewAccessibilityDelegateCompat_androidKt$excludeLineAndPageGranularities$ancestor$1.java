package androidx.compose.ui.platform;

import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidComposeViewAccessibilityDelegateCompat_androidKt$excludeLineAndPageGranularities$ancestor$1 extends Lambda implements Function1 {
    public static final AndroidComposeViewAccessibilityDelegateCompat_androidKt$excludeLineAndPageGranularities$ancestor$1 INSTANCE = new AndroidComposeViewAccessibilityDelegateCompat_androidKt$excludeLineAndPageGranularities$ancestor$1();

    AndroidComposeViewAccessibilityDelegateCompat_androidKt$excludeLineAndPageGranularities$ancestor$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        SemanticsConfiguration semanticsConfiguration;
        boolean z;
        LayoutNode it = (LayoutNode) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        SemanticsModifierNode outerSemantics = SemanticsNodeKt.getOuterSemantics(it);
        if (outerSemantics != null) {
            semanticsConfiguration = SemanticsModifierNodeKt.collapsedSemanticsConfiguration(outerSemantics);
        } else {
            semanticsConfiguration = null;
        }
        boolean z2 = true;
        if (semanticsConfiguration != null && semanticsConfiguration.isMergingSemanticsOfDescendants()) {
            z = true;
        } else {
            z = false;
        }
        if (!z || !semanticsConfiguration.contains(SemanticsActions.getSetText())) {
            z2 = false;
        }
        return Boolean.valueOf(z2);
    }
}
