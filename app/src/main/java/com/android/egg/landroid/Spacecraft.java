package com.android.egg.landroid;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import com.android.egg.landroid.Spark;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Spacecraft extends Body {
    public static final int $stable = 8;
    private Landing landing;
    private float launchClock;
    private long thrust;
    private final Track track;
    private boolean transit;

    public Spacecraft() {
        super(null, 1, null);
        long j;
        Offset.Companion companion = Offset.Companion;
        j = Offset.Zero;
        this.thrust = j;
        this.track = new Track();
        setMass(10.0f);
        setRadius(12.0f);
    }

    public final Landing getLanding() {
        return this.landing;
    }

    public final float getLaunchClock() {
        return this.launchClock;
    }

    /* renamed from: getThrust-F1C5BW0  reason: not valid java name */
    public final long m440getThrustF1C5BW0() {
        return this.thrust;
    }

    public final Track getTrack() {
        return this.track;
    }

    public final boolean getTransit() {
        return this.transit;
    }

    @Override // com.android.egg.landroid.Body, com.android.egg.landroid.Entity
    public void postUpdate(Simulator sim, float f) {
        Intrinsics.checkNotNullParameter(sim, "sim");
        super.postUpdate(sim, f);
        this.track.add(Offset.m45getXimpl(m423getPosF1C5BW0()), Offset.m46getYimpl(m423getPosF1C5BW0()), getAngle());
        float m446magk4lQ0M = Vec2Kt.m446magk4lQ0M(this.thrust);
        if (sim.getRng().nextFloat() < m446magk4lQ0M) {
            Color.Companion companion = Color.Companion;
            Spark spark = new Spark(RandomnessKt.nextFloatInRange(sim.getRng(), 0.5f, 1.0f), true, 1.0f, Spark.Style.RING, 1090519039 << 32, 3.0f, null);
            spark.m426setPosk4lQ0M(m423getPosF1C5BW0());
            spark.m425setOposk4lQ0M(m423getPosF1C5BW0());
            spark.m427setVelocityk4lQ0M(Offset.m48plusMKHz9U(m424getVelocityF1C5BW0(), Vec2Kt.makeWithAngleMag(Offset.Companion, RandomnessKt.nextFloatInRange(sim.getRng(), -0.2f, 0.2f) + getAngle(), m446magk4lQ0M * (-1000.0f) * 10.0f * f)));
            sim.add(spark);
        }
    }

    public final void setLanding(Landing landing) {
        this.landing = landing;
    }

    public final void setLaunchClock(float f) {
        this.launchClock = f;
    }

    /* renamed from: setThrust-k-4lQ0M  reason: not valid java name */
    public final void m441setThrustk4lQ0M(long j) {
        this.thrust = j;
    }

    public final void setTransit(boolean z) {
        this.transit = z;
    }

    @Override // com.android.egg.landroid.Body, com.android.egg.landroid.Entity
    public void update(Simulator sim, float f) {
        Intrinsics.checkNotNullParameter(sim, "sim");
        float m446magk4lQ0M = Vec2Kt.m446magk4lQ0M(this.thrust);
        float f2 = 0.0f;
        boolean z = true;
        if (m446magk4lQ0M > 0.0f) {
            float coerceIn = RangesKt.coerceIn(m446magk4lQ0M, 0.0f, 1.0f) * 1000.0f * f;
            Landing landing = this.landing;
            if (landing != null && landing != null) {
                if (this.launchClock != 0.0f) {
                    z = false;
                }
                if (z) {
                    this.launchClock = sim.getNow() + 1.0f;
                }
                if (sim.getNow() > this.launchClock) {
                    sim.remove(landing);
                    this.landing = null;
                    f2 = coerceIn;
                }
                coerceIn = f2;
            }
            m427setVelocityk4lQ0M(Offset.m48plusMKHz9U(m424getVelocityF1C5BW0(), Vec2Kt.makeWithAngleMag(Offset.Companion, getAngle(), coerceIn)));
        } else {
            if (this.launchClock != 0.0f) {
                z = false;
            }
            if (!z) {
                this.launchClock = 0.0f;
            }
        }
        if (Vec2Kt.m446magk4lQ0M(m424getVelocityF1C5BW0()) > 5000.0f) {
            m427setVelocityk4lQ0M(Vec2Kt.makeWithAngleMag(Offset.Companion, Vec2Kt.m443anglek4lQ0M(m424getVelocityF1C5BW0()), 5000.0f));
        }
        super.update(sim, f);
    }
}
