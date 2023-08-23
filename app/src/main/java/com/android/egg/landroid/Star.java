package com.android.egg.landroid;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Star extends Planet {
    public static final int $stable = 8;
    private float anim;
    private final StarClass cls;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Star(com.android.egg.landroid.StarClass r13, float r14) {
        /*
            r12 = this;
            java.lang.String r0 = "cls"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            androidx.compose.ui.geometry.Offset$Companion r0 = androidx.compose.ui.geometry.Offset.Companion
            long r2 = androidx.compose.ui.geometry.Offset.access$getZero$cp()
            long r5 = androidx.compose.ui.geometry.Offset.access$getZero$cp()
            r7 = 0
            r8 = 0
            r10 = 16
            r11 = 0
            r1 = r12
            r4 = r14
            r1.<init>(r2, r4, r5, r7, r8, r10, r11)
            r12.cls = r13
            long r0 = androidx.compose.ui.geometry.Offset.access$getZero$cp()
            r12.m426setPosk4lQ0M(r0)
            double r0 = (double) r14
            r14 = 3
            double r2 = (double) r14
            double r0 = java.lang.Math.pow(r0, r2)
            float r14 = (float) r0
            r0 = 1078530011(0x40490fdb, float:3.1415927)
            float r14 = r14 * r0
            r0 = 1056964608(0x3f000000, float:0.5)
            float r14 = r14 * r0
            r12.setMass(r14)
            long r13 = com.android.egg.landroid.UniverseKt.starColor(r13)
            r12.m439setColor8_81llA(r13)
            r13 = 0
            r12.setCollides(r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.landroid.Star.<init>(com.android.egg.landroid.StarClass, float):void");
    }

    public final float getAnim() {
        return this.anim;
    }

    public final StarClass getCls() {
        return this.cls;
    }

    public final void setAnim(float f) {
        this.anim = f;
    }

    @Override // com.android.egg.landroid.Planet, com.android.egg.landroid.Body, com.android.egg.landroid.Entity
    public void update(Simulator sim, float f) {
        Intrinsics.checkNotNullParameter(sim, "sim");
        this.anim += f;
    }
}
