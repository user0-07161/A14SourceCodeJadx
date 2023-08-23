package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FocusRequester {
    private final MutableVector focusRequesterNodes = new MutableVector(new FocusRequesterModifierNode[16]);
    private static final FocusRequester Default = new FocusRequester();
    private static final FocusRequester Cancel = new FocusRequester();

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0057, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Boolean findFocusTarget$ui_release(kotlin.jvm.functions.Function1 r7) {
        /*
            r6 = this;
            java.lang.String r0 = "onFound"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            androidx.compose.ui.focus.FocusRequester r0 = androidx.compose.ui.focus.FocusRequester.Cancel
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
            if (r0 == 0) goto L11
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            goto Lab
        L11:
            androidx.compose.ui.focus.FocusRequester r0 = androidx.compose.ui.focus.FocusRequester.Default
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
            r1 = 0
            if (r0 == 0) goto L1c
            goto Laa
        L1c:
            androidx.compose.runtime.collection.MutableVector r6 = r6.focusRequesterNodes
            int r0 = r6.getSize()
            if (r0 <= 0) goto Laa
            java.lang.Object[] r6 = r6.getContent()
            r2 = 0
        L29:
            r3 = r6[r2]
            androidx.compose.ui.focus.FocusRequesterModifierNode r3 = (androidx.compose.ui.focus.FocusRequesterModifierNode) r3
            androidx.compose.ui.Modifier$Node r3 = (androidx.compose.ui.Modifier.Node) r3
            androidx.compose.ui.Modifier$Node r4 = r3.getNode()
            boolean r4 = r4.isAttached()
            if (r4 == 0) goto L9e
            androidx.compose.runtime.collection.MutableVector r4 = new androidx.compose.runtime.collection.MutableVector
            r5 = 16
            androidx.compose.ui.Modifier$Node[] r5 = new androidx.compose.ui.Modifier.Node[r5]
            r4.<init>(r5)
            androidx.compose.ui.Modifier$Node r5 = r3.getNode()
            androidx.compose.ui.Modifier$Node r5 = r5.getChild$ui_release()
            if (r5 != 0) goto L54
            androidx.compose.ui.Modifier$Node r3 = r3.getNode()
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r4, r3)
            goto L57
        L54:
            r4.add(r5)
        L57:
            boolean r3 = r4.isNotEmpty()
            if (r3 == 0) goto L99
            int r3 = r4.getSize()
            int r3 = r3 + (-1)
            java.lang.Object r3 = r4.removeAt(r3)
            androidx.compose.ui.Modifier$Node r3 = (androidx.compose.ui.Modifier.Node) r3
            int r5 = r3.getAggregateChildKindSet$ui_release()
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 != 0) goto L75
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r4, r3)
            goto L57
        L75:
            if (r3 == 0) goto L57
            int r5 = r3.getKindSet$ui_release()
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 == 0) goto L94
            boolean r5 = r3 instanceof androidx.compose.ui.focus.FocusTargetModifierNode
            if (r5 == 0) goto L57
            androidx.compose.ui.focus.FocusTargetModifierNode r3 = (androidx.compose.ui.focus.FocusTargetModifierNode) r3
            java.lang.Object r3 = r7.invoke(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L57
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            goto L99
        L94:
            androidx.compose.ui.Modifier$Node r3 = r3.getChild$ui_release()
            goto L75
        L99:
            int r2 = r2 + 1
            if (r2 < r0) goto L29
            goto Laa
        L9e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Check failed."
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        Laa:
            r6 = r1
        Lab:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusRequester.findFocusTarget$ui_release(kotlin.jvm.functions.Function1):java.lang.Boolean");
    }
}
