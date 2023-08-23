package androidx.window.layout.adapter.sidecar;

import androidx.window.core.VerificationMode;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarDisplayFeature;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SidecarAdapter {
    private final VerificationMode verificationMode = VerificationMode.QUIET;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class Companion {
        public static int getSidecarDevicePosture$window_release(SidecarDeviceState sidecarDeviceState) {
            int i;
            Intrinsics.checkNotNullParameter(sidecarDeviceState, "sidecarDeviceState");
            try {
                try {
                    i = sidecarDeviceState.posture;
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                    i = 0;
                }
            } catch (NoSuchFieldError unused2) {
                Object invoke = SidecarDeviceState.class.getMethod("getPosture", new Class[0]).invoke(sidecarDeviceState, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.Int");
                i = ((Integer) invoke).intValue();
            }
            if (i < 0 || i > 4) {
                return 0;
            }
            return i;
        }

        public static List getSidecarDisplayFeatures(SidecarWindowLayoutInfo info) {
            Intrinsics.checkNotNullParameter(info, "info");
            try {
                try {
                    List list = info.displayFeatures;
                    if (list == null) {
                        return EmptyList.INSTANCE;
                    }
                    return list;
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                    return EmptyList.INSTANCE;
                }
            } catch (NoSuchFieldError unused2) {
                Object invoke = SidecarWindowLayoutInfo.class.getMethod("getDisplayFeatures", new Class[0]).invoke(info, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.collections.List<androidx.window.sidecar.SidecarDisplayFeature>");
                return (List) invoke;
            }
        }
    }

    public static boolean isEqualSidecarWindowLayoutInfo(SidecarWindowLayoutInfo sidecarWindowLayoutInfo, SidecarWindowLayoutInfo sidecarWindowLayoutInfo2) {
        boolean z;
        if (Intrinsics.areEqual(sidecarWindowLayoutInfo, sidecarWindowLayoutInfo2)) {
            return true;
        }
        if (sidecarWindowLayoutInfo == null || sidecarWindowLayoutInfo2 == null) {
            return false;
        }
        List sidecarDisplayFeatures = Companion.getSidecarDisplayFeatures(sidecarWindowLayoutInfo);
        List sidecarDisplayFeatures2 = Companion.getSidecarDisplayFeatures(sidecarWindowLayoutInfo2);
        if (sidecarDisplayFeatures == sidecarDisplayFeatures2) {
            return true;
        }
        if (sidecarDisplayFeatures != null && sidecarDisplayFeatures2 != null && sidecarDisplayFeatures.size() == sidecarDisplayFeatures2.size()) {
            int size = sidecarDisplayFeatures.size();
            for (int i = 0; i < size; i++) {
                SidecarDisplayFeature sidecarDisplayFeature = (SidecarDisplayFeature) sidecarDisplayFeatures.get(i);
                SidecarDisplayFeature sidecarDisplayFeature2 = (SidecarDisplayFeature) sidecarDisplayFeatures2.get(i);
                if (Intrinsics.areEqual(sidecarDisplayFeature, sidecarDisplayFeature2)) {
                    z = true;
                } else if (sidecarDisplayFeature == null || sidecarDisplayFeature2 == null || sidecarDisplayFeature.getType() != sidecarDisplayFeature2.getType()) {
                    z = false;
                } else {
                    z = Intrinsics.areEqual(sidecarDisplayFeature.getRect(), sidecarDisplayFeature2.getRect());
                }
                if (z) {
                }
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:76:0x00ab, code lost:
        if (r5 == 4) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00c7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0048 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.window.layout.WindowLayoutInfo translate(androidx.window.sidecar.SidecarWindowLayoutInfo r9, androidx.window.sidecar.SidecarDeviceState r10) {
        /*
            r8 = this;
            java.lang.String r0 = "state"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            if (r9 != 0) goto Lf
            androidx.window.layout.WindowLayoutInfo r8 = new androidx.window.layout.WindowLayoutInfo
            kotlin.collections.EmptyList r9 = kotlin.collections.EmptyList.INSTANCE
            r8.<init>(r9)
            return r8
        Lf:
            androidx.window.sidecar.SidecarDeviceState r0 = new androidx.window.sidecar.SidecarDeviceState
            r0.<init>()
            int r10 = androidx.window.layout.adapter.sidecar.SidecarAdapter.Companion.getSidecarDevicePosture$window_release(r10)
            r1 = 1
            r0.posture = r10     // Catch: java.lang.NoSuchFieldError -> L1c
            goto L36
        L1c:
            java.lang.Class<androidx.window.sidecar.SidecarDeviceState> r2 = androidx.window.sidecar.SidecarDeviceState.class
            java.lang.String r3 = "setPosture"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.Throwable -> L36
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch: java.lang.Throwable -> L36
            r6 = 0
            r4[r6] = r5     // Catch: java.lang.Throwable -> L36
            java.lang.reflect.Method r2 = r2.getMethod(r3, r4)     // Catch: java.lang.Throwable -> L36
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L36
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch: java.lang.Throwable -> L36
            r3[r6] = r10     // Catch: java.lang.Throwable -> L36
            r2.invoke(r0, r3)     // Catch: java.lang.Throwable -> L36
        L36:
            java.util.List r9 = androidx.window.layout.adapter.sidecar.SidecarAdapter.Companion.getSidecarDisplayFeatures(r9)
            java.lang.String r10 = "sidecarDisplayFeatures"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r10)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.Iterator r9 = r9.iterator()
        L48:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto Lcc
            java.lang.Object r2 = r9.next()
            androidx.window.sidecar.SidecarDisplayFeature r2 = (androidx.window.sidecar.SidecarDisplayFeature) r2
            java.lang.String r3 = "feature"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            androidx.window.core.VerificationMode r3 = r8.verificationMode
            androidx.window.core.SpecificationComputer r3 = androidx.window.core.SpecificationComputer.Companion.startSpecification$default(r2, r3)
            java.lang.String r4 = "Type must be either TYPE_FOLD or TYPE_HINGE"
            androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$1 r5 = new kotlin.jvm.functions.Function1() { // from class: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$1
                static {
                    /*
                        androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$1 r0 = new androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT  (r0 I:androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$1) androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$1.INSTANCE androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r2) {
                    /*
                        r1 = this;
                        androidx.window.sidecar.SidecarDisplayFeature r2 = (androidx.window.sidecar.SidecarDisplayFeature) r2
                        java.lang.String r1 = "$this$require"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
                        int r1 = r2.getType()
                        r0 = 1
                        if (r1 == r0) goto L17
                        int r1 = r2.getType()
                        r2 = 2
                        if (r1 != r2) goto L16
                        goto L17
                    L16:
                        r0 = 0
                    L17:
                        java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            androidx.window.core.SpecificationComputer r3 = r3.require(r4, r5)
            java.lang.String r4 = "Feature bounds must not be 0"
            androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$2 r5 = new kotlin.jvm.functions.Function1() { // from class: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$2
                static {
                    /*
                        androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$2 r0 = new androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT  (r0 I:androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$2) androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$2.INSTANCE androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$2.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$2.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        androidx.window.sidecar.SidecarDisplayFeature r1 = (androidx.window.sidecar.SidecarDisplayFeature) r1
                        java.lang.String r0 = "$this$require"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
                        android.graphics.Rect r0 = r1.getRect()
                        int r0 = r0.width()
                        if (r0 != 0) goto L1e
                        android.graphics.Rect r0 = r1.getRect()
                        int r0 = r0.height()
                        if (r0 == 0) goto L1c
                        goto L1e
                    L1c:
                        r0 = 0
                        goto L1f
                    L1e:
                        r0 = 1
                    L1f:
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$2.invoke(java.lang.Object):java.lang.Object");
                }
            }
            androidx.window.core.SpecificationComputer r3 = r3.require(r4, r5)
            java.lang.String r4 = "TYPE_FOLD must have 0 area"
            androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$3 r5 = new kotlin.jvm.functions.Function1() { // from class: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$3
                static {
                    /*
                        androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$3 r0 = new androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$3
                        r0.<init>()
                        
                        // error: 0x0005: SPUT  (r0 I:androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$3) androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$3.INSTANCE androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$3
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$3.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$3.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r2) {
                    /*
                        r1 = this;
                        androidx.window.sidecar.SidecarDisplayFeature r2 = (androidx.window.sidecar.SidecarDisplayFeature) r2
                        java.lang.String r1 = "$this$require"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
                        int r1 = r2.getType()
                        r0 = 1
                        if (r1 != r0) goto L24
                        android.graphics.Rect r1 = r2.getRect()
                        int r1 = r1.width()
                        if (r1 == 0) goto L24
                        android.graphics.Rect r1 = r2.getRect()
                        int r1 = r1.height()
                        if (r1 != 0) goto L23
                        goto L24
                    L23:
                        r0 = 0
                    L24:
                        java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$3.invoke(java.lang.Object):java.lang.Object");
                }
            }
            androidx.window.core.SpecificationComputer r3 = r3.require(r4, r5)
            java.lang.String r4 = "Feature be pinned to either left or top"
            androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$4 r5 = new kotlin.jvm.functions.Function1() { // from class: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$4
                static {
                    /*
                        androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$4 r0 = new androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$4
                        r0.<init>()
                        
                        // error: 0x0005: SPUT  (r0 I:androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$4) androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$4.INSTANCE androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$4
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$4.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$4.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r1) {
                    /*
                        r0 = this;
                        androidx.window.sidecar.SidecarDisplayFeature r1 = (androidx.window.sidecar.SidecarDisplayFeature) r1
                        java.lang.String r0 = "$this$require"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
                        android.graphics.Rect r0 = r1.getRect()
                        int r0 = r0.left
                        if (r0 == 0) goto L1a
                        android.graphics.Rect r0 = r1.getRect()
                        int r0 = r0.top
                        if (r0 != 0) goto L18
                        goto L1a
                    L18:
                        r0 = 0
                        goto L1b
                    L1a:
                        r0 = 1
                    L1b:
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter$translate$checkedFeature$4.invoke(java.lang.Object):java.lang.Object");
                }
            }
            androidx.window.core.SpecificationComputer r3 = r3.require(r4, r5)
            java.lang.Object r3 = r3.compute()
            androidx.window.sidecar.SidecarDisplayFeature r3 = (androidx.window.sidecar.SidecarDisplayFeature) r3
            if (r3 != 0) goto L88
            goto Lc4
        L88:
            int r3 = r3.getType()
            r4 = 2
            if (r3 == r1) goto L97
            if (r3 == r4) goto L92
            goto Lc4
        L92:
            androidx.window.layout.HardwareFoldingFeature$Type r3 = androidx.window.layout.HardwareFoldingFeature.Type.access$getHINGE$cp()
            goto L9b
        L97:
            androidx.window.layout.HardwareFoldingFeature$Type r3 = androidx.window.layout.HardwareFoldingFeature.Type.access$getFOLD$cp()
        L9b:
            int r5 = androidx.window.layout.adapter.sidecar.SidecarAdapter.Companion.getSidecarDevicePosture$window_release(r0)
            if (r5 == 0) goto Lc4
            if (r5 == r1) goto Lc4
            if (r5 == r4) goto Lae
            androidx.window.layout.FoldingFeature$State r4 = androidx.window.layout.FoldingFeature.State.FLAT
            r6 = 3
            if (r5 == r6) goto Lb0
            r6 = 4
            if (r5 == r6) goto Lc4
            goto Lb0
        Lae:
            androidx.window.layout.FoldingFeature$State r4 = androidx.window.layout.FoldingFeature.State.HALF_OPENED
        Lb0:
            androidx.window.layout.HardwareFoldingFeature r5 = new androidx.window.layout.HardwareFoldingFeature
            androidx.window.core.Bounds r6 = new androidx.window.core.Bounds
            android.graphics.Rect r2 = r2.getRect()
            java.lang.String r7 = "feature.rect"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r7)
            r6.<init>(r2)
            r5.<init>(r6, r3, r4)
            goto Lc5
        Lc4:
            r5 = 0
        Lc5:
            if (r5 == 0) goto L48
            r10.add(r5)
            goto L48
        Lcc:
            androidx.window.layout.WindowLayoutInfo r8 = new androidx.window.layout.WindowLayoutInfo
            r8.<init>(r10)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarAdapter.translate(androidx.window.sidecar.SidecarWindowLayoutInfo, androidx.window.sidecar.SidecarDeviceState):androidx.window.layout.WindowLayoutInfo");
    }
}
