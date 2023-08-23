package com.android.egg.landroid;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class Body implements Entity {
    public static final int $stable = 8;
    private float angle;
    private boolean collides;
    private float mass;
    private String name;
    private float oangle;
    private long opos;
    private long pos;
    private float radius;
    private long velocity;

    public Body() {
        this(null, 1, null);
    }

    public final float getAngle() {
        return this.angle;
    }

    public final boolean getCollides() {
        return this.collides;
    }

    public final float getMass() {
        return this.mass;
    }

    public final String getName() {
        return this.name;
    }

    public final float getOangle() {
        return this.oangle;
    }

    public final float getOmega() {
        return this.angle - this.oangle;
    }

    /* renamed from: getOpos-F1C5BW0  reason: not valid java name */
    public final long m422getOposF1C5BW0() {
        return this.opos;
    }

    /* renamed from: getPos-F1C5BW0  reason: not valid java name */
    public final long m423getPosF1C5BW0() {
        return this.pos;
    }

    public final float getRadius() {
        return this.radius;
    }

    /* renamed from: getVelocity-F1C5BW0  reason: not valid java name */
    public final long m424getVelocityF1C5BW0() {
        return this.velocity;
    }

    @Override // com.android.egg.landroid.Entity
    public void postUpdate(Simulator sim, float f) {
        Intrinsics.checkNotNullParameter(sim, "sim");
        if (f <= 0.0f) {
            return;
        }
        long m47minusMKHz9U = Offset.m47minusMKHz9U(this.pos, this.opos);
        this.velocity = OffsetKt.Offset(Offset.m45getXimpl(m47minusMKHz9U) / f, Offset.m46getYimpl(m47minusMKHz9U) / f);
    }

    public final void setAngle(float f) {
        this.angle = f;
    }

    public final void setCollides(boolean z) {
        this.collides = z;
    }

    public final void setMass(float f) {
        this.mass = f;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setOangle(float f) {
        this.oangle = f;
    }

    public final void setOmega(float f) {
        this.oangle = this.angle - f;
    }

    /* renamed from: setOpos-k-4lQ0M  reason: not valid java name */
    public final void m425setOposk4lQ0M(long j) {
        this.opos = j;
    }

    /* renamed from: setPos-k-4lQ0M  reason: not valid java name */
    public final void m426setPosk4lQ0M(long j) {
        this.pos = j;
    }

    public final void setRadius(float f) {
        this.radius = f;
    }

    /* renamed from: setVelocity-k-4lQ0M  reason: not valid java name */
    public final void m427setVelocityk4lQ0M(long j) {
        this.velocity = j;
    }

    @Override // com.android.egg.landroid.Entity
    public void update(Simulator sim, float f) {
        Intrinsics.checkNotNullParameter(sim, "sim");
        if (f <= 0.0f) {
            return;
        }
        long m49timestuRUvjQ = Offset.m49timestuRUvjQ(this.velocity, f);
        long j = this.pos;
        this.opos = j;
        this.pos = Offset.m48plusMKHz9U(j, m49timestuRUvjQ);
    }

    public Body(String name) {
        long j;
        long j2;
        long j3;
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        Offset.Companion companion = Offset.Companion;
        j = Offset.Zero;
        this.pos = j;
        j2 = Offset.Zero;
        this.opos = j2;
        j3 = Offset.Zero;
        this.velocity = j3;
        this.collides = true;
    }

    public /* synthetic */ Body(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "Unknown" : str);
    }
}
