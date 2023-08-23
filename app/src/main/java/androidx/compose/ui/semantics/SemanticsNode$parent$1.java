package androidx.compose.ui.semantics;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class SemanticsNode$parent$1 extends Lambda implements Function1 {
    public static final SemanticsNode$parent$1 INSTANCE = new SemanticsNode$parent$1();

    SemanticsNode$parent$1() {
        super(1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0018, code lost:
        if (r0.isMergingSemanticsOfDescendants() == true) goto L7;
     */
    @Override // kotlin.jvm.functions.Function1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invoke(java.lang.Object r1) {
        /*
            r0 = this;
            androidx.compose.ui.node.LayoutNode r1 = (androidx.compose.ui.node.LayoutNode) r1
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            androidx.compose.ui.node.SemanticsModifierNode r0 = androidx.compose.ui.semantics.SemanticsNodeKt.getOuterSemantics(r1)
            if (r0 == 0) goto L1b
            androidx.compose.ui.semantics.SemanticsConfiguration r0 = androidx.compose.ui.node.SemanticsModifierNodeKt.collapsedSemanticsConfiguration(r0)
            if (r0 == 0) goto L1b
            boolean r0 = r0.isMergingSemanticsOfDescendants()
            r1 = 1
            if (r0 != r1) goto L1b
            goto L1c
        L1b:
            r1 = 0
        L1c:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.semantics.SemanticsNode$parent$1.invoke(java.lang.Object):java.lang.Object");
    }
}
