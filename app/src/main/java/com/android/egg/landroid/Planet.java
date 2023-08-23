package com.android.egg.landroid;

import androidx.compose.ui.geometry.Offset;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class Planet extends Body {
    public static final int $stable = 8;
    private String atmosphere;
    private long color;
    private String description;
    private boolean explored;
    private String fauna;
    private String flora;
    private final long orbitCenter;
    private final float orbitRadius;
    private float speed;

    public /* synthetic */ Planet(long j, float f, long j2, float f2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, f, j2, f2, j3);
    }

    public final String getAtmosphere() {
        return this.atmosphere;
    }

    /* renamed from: getColor-0d7_KjU  reason: not valid java name */
    public final long m437getColor0d7_KjU() {
        return this.color;
    }

    public final String getDescription() {
        return this.description;
    }

    public final boolean getExplored() {
        return this.explored;
    }

    public final String getFauna() {
        return this.fauna;
    }

    public final String getFlora() {
        return this.flora;
    }

    /* renamed from: getOrbitCenter-F1C5BW0  reason: not valid java name */
    public final long m438getOrbitCenterF1C5BW0() {
        return this.orbitCenter;
    }

    public final float getSpeed() {
        return this.speed;
    }

    @Override // com.android.egg.landroid.Body, com.android.egg.landroid.Entity
    public void postUpdate(Simulator sim, float f) {
        Intrinsics.checkNotNullParameter(sim, "sim");
        m426setPosk4lQ0M(Offset.m48plusMKHz9U(this.orbitCenter, Vec2Kt.makeWithAngleMag(Offset.Companion, Vec2Kt.m443anglek4lQ0M(Offset.m47minusMKHz9U(m423getPosF1C5BW0(), this.orbitCenter)), this.orbitRadius)));
        super.postUpdate(sim, f);
    }

    public final void setAtmosphere(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.atmosphere = str;
    }

    /* renamed from: setColor-8_81llA  reason: not valid java name */
    public final void m439setColor8_81llA(long j) {
        this.color = j;
    }

    public final void setDescription(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.description = str;
    }

    public final void setExplored(boolean z) {
        this.explored = z;
    }

    public final void setFauna(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fauna = str;
    }

    public final void setFlora(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.flora = str;
    }

    public final void setSpeed(float f) {
        this.speed = f;
    }

    @Override // com.android.egg.landroid.Body, com.android.egg.landroid.Entity
    public void update(Simulator sim, float f) {
        Intrinsics.checkNotNullParameter(sim, "sim");
        m427setVelocityk4lQ0M(Vec2Kt.makeWithAngleMag(Offset.Companion, Vec2Kt.m443anglek4lQ0M(Offset.m47minusMKHz9U(m423getPosF1C5BW0(), this.orbitCenter)) + 1.5707964f, this.speed));
        super.update(sim, f);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Planet(long r13, float r15, long r16, float r18, long r19, int r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
        /*
            r12 = this;
            r0 = r21 & 16
            if (r0 == 0) goto Lc
            androidx.compose.ui.graphics.Color$Companion r0 = androidx.compose.ui.graphics.Color.Companion
            long r0 = androidx.compose.ui.graphics.Color.access$getWhite$cp()
            r9 = r0
            goto Le
        Lc:
            r9 = r19
        Le:
            r11 = 0
            r2 = r12
            r3 = r13
            r5 = r15
            r6 = r16
            r8 = r18
            r2.<init>(r3, r5, r6, r8, r9, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.landroid.Planet.<init>(long, float, long, float, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    private Planet(long j, float f, long j2, float f2, long j3) {
        super(null, 1, null);
        this.orbitCenter = j;
        this.speed = f2;
        this.color = j3;
        this.atmosphere = "";
        this.description = "";
        this.flora = "";
        this.fauna = "";
        setRadius(f);
        m426setPosk4lQ0M(j2);
        this.orbitRadius = Vec2Kt.m444distance0a9Yr6o(j2, j);
        setMass(((float) Math.pow(f, 3)) * 3.1415927f * 2.5f);
    }
}
