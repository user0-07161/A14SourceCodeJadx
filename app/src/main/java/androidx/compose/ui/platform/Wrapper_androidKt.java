package androidx.compose.ui.platform;

import android.view.ViewGroup;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Wrapper_androidKt {
    private static final ViewGroup.LayoutParams DefaultLayoutParams = new ViewGroup.LayoutParams(-2, -2);

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.runtime.Composition setContent(androidx.compose.ui.platform.AbstractComposeView r4, androidx.compose.runtime.CompositionContext r5, androidx.compose.runtime.internal.ComposableLambdaImpl r6) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            androidx.compose.ui.platform.GlobalSnapshotManager.ensureStarted()
            int r0 = r4.getChildCount()
            r1 = 0
            if (r0 <= 0) goto L1b
            r0 = 0
            android.view.View r0 = r4.getChildAt(r0)
            boolean r2 = r0 instanceof androidx.compose.ui.platform.AndroidComposeView
            if (r2 == 0) goto L1e
            androidx.compose.ui.platform.AndroidComposeView r0 = (androidx.compose.ui.platform.AndroidComposeView) r0
            goto L1f
        L1b:
            r4.removeAllViews()
        L1e:
            r0 = r1
        L1f:
            if (r0 != 0) goto L34
            androidx.compose.ui.platform.AndroidComposeView r0 = new androidx.compose.ui.platform.AndroidComposeView
            android.content.Context r2 = r4.getContext()
            java.lang.String r3 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r0.<init>(r2)
            android.view.ViewGroup$LayoutParams r2 = androidx.compose.ui.platform.Wrapper_androidKt.DefaultLayoutParams
            r4.addView(r0, r2)
        L34:
            java.util.Map r4 = r0.getAttributeSourceResourceMap()
            java.lang.String r2 = "view.attributeSourceResourceMap"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
            boolean r4 = r4.isEmpty()
            r2 = 1
            r4 = r4 ^ r2
            if (r4 == 0) goto L6c
            java.util.WeakHashMap r4 = new java.util.WeakHashMap
            r4.<init>()
            java.util.Set r4 = java.util.Collections.newSetFromMap(r4)
            r3 = 2131099722(0x7f06004a, float:1.7811805E38)
            r0.setTag(r3, r4)
            int r4 = androidx.compose.ui.platform.InspectableValueKt.$r8$clinit
            java.lang.Class<androidx.compose.ui.platform.InspectableValueKt> r4 = androidx.compose.ui.platform.InspectableValueKt.class
            java.lang.String r3 = "isDebugInspectorInfoEnabled"
            java.lang.reflect.Field r4 = r4.getDeclaredField(r3)     // Catch: java.lang.Exception -> L65
            r4.setAccessible(r2)     // Catch: java.lang.Exception -> L65
            r4.setBoolean(r1, r2)     // Catch: java.lang.Exception -> L65
            goto L6c
        L65:
            java.lang.String r4 = "Wrapper"
            java.lang.String r2 = "Could not access isDebugInspectorInfoEnabled. Please set explicitly."
            android.util.Log.w(r4, r2)
        L6c:
            androidx.compose.ui.node.UiApplier r4 = new androidx.compose.ui.node.UiApplier
            androidx.compose.ui.node.LayoutNode r2 = r0.getRoot()
            r4.<init>(r2)
            int r2 = androidx.compose.runtime.CompositionKt.$r8$clinit
            androidx.compose.runtime.CompositionImpl r2 = new androidx.compose.runtime.CompositionImpl
            r2.<init>(r5, r4)
            r4 = 2131099765(0x7f060075, float:1.7811892E38)
            java.lang.Object r5 = r0.getTag(r4)
            boolean r3 = r5 instanceof androidx.compose.ui.platform.WrappedComposition
            if (r3 == 0) goto L8a
            r1 = r5
            androidx.compose.ui.platform.WrappedComposition r1 = (androidx.compose.ui.platform.WrappedComposition) r1
        L8a:
            if (r1 != 0) goto L94
            androidx.compose.ui.platform.WrappedComposition r1 = new androidx.compose.ui.platform.WrappedComposition
            r1.<init>(r0, r2)
            r0.setTag(r4, r1)
        L94:
            r1.setContent(r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.Wrapper_androidKt.setContent(androidx.compose.ui.platform.AbstractComposeView, androidx.compose.runtime.CompositionContext, androidx.compose.runtime.internal.ComposableLambdaImpl):androidx.compose.runtime.Composition");
    }
}
