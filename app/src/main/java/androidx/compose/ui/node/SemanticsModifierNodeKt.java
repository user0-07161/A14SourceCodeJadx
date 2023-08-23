package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SemanticsModifierNodeKt {
    public static final SemanticsConfiguration collapsedSemanticsConfiguration(SemanticsModifierNode semanticsModifierNode) {
        Intrinsics.checkNotNullParameter(semanticsModifierNode, "<this>");
        Modifier.Node child$ui_release = ((Modifier.Node) semanticsModifierNode).getNode().getChild$ui_release();
        SemanticsModifierNode semanticsModifierNode2 = null;
        if (child$ui_release != null && (child$ui_release.getAggregateChildKindSet$ui_release() & 8) != 0) {
            while (child$ui_release != null) {
                if ((child$ui_release.getKindSet$ui_release() & 8) != 0) {
                    break;
                }
                child$ui_release = child$ui_release.getChild$ui_release();
            }
        }
        child$ui_release = null;
        if (child$ui_release instanceof SemanticsModifierNode) {
            semanticsModifierNode2 = child$ui_release;
        }
        SemanticsModifierNode semanticsModifierNode3 = semanticsModifierNode2;
        if (semanticsModifierNode3 != null && !semanticsModifierNode.getSemanticsConfiguration().isClearingSemantics()) {
            SemanticsConfiguration copy = semanticsModifierNode.getSemanticsConfiguration().copy();
            copy.collapsePeer$ui_release(collapsedSemanticsConfiguration(semanticsModifierNode3));
            return copy;
        }
        return semanticsModifierNode.getSemanticsConfiguration();
    }
}
