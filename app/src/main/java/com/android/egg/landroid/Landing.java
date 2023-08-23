package com.android.egg.landroid;

import androidx.compose.ui.geometry.Offset;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Landing implements Constraint {
    public static final int $stable = 8;
    private final float angle;
    private final long landingVector;
    private final Planet planet;
    private final Spacecraft ship;

    public Landing(Spacecraft ship, Planet planet, float f) {
        Intrinsics.checkNotNullParameter(ship, "ship");
        Intrinsics.checkNotNullParameter(planet, "planet");
        this.ship = ship;
        this.planet = planet;
        this.angle = f;
        this.landingVector = Vec2Kt.makeWithAngleMag(Offset.Companion, f, planet.getRadius() + ship.getRadius());
    }

    public final float getAngle() {
        return this.angle;
    }

    public final Planet getPlanet() {
        return this.planet;
    }

    public final Spacecraft getShip() {
        return this.ship;
    }

    @Override // com.android.egg.landroid.Constraint
    public void solve(Simulator sim, float f) {
        Intrinsics.checkNotNullParameter(sim, "sim");
        long m48plusMKHz9U = Offset.m48plusMKHz9U(this.planet.m423getPosF1C5BW0(), this.landingVector);
        Spacecraft spacecraft = this.ship;
        spacecraft.m426setPosk4lQ0M(Offset.m48plusMKHz9U(Offset.m49timestuRUvjQ(spacecraft.m423getPosF1C5BW0(), 0.5f), Offset.m49timestuRUvjQ(m48plusMKHz9U, 0.5f)));
        this.ship.setAngle(this.angle);
    }
}
