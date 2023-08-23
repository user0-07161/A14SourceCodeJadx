package com.android.egg.landroid;

import android.util.ArraySet;
import androidx.compose.ui.geometry.Offset;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class Container implements Constraint {
    public static final int $stable = 8;
    private final ArraySet list = new ArraySet();
    private final float radius;
    private final float softness;

    public Container(float f) {
        this.radius = f;
    }

    public final void add(Body p) {
        Intrinsics.checkNotNullParameter(p, "p");
        this.list.add(p);
    }

    public final float getRadius() {
        return this.radius;
    }

    public final void remove(Body p) {
        Intrinsics.checkNotNullParameter(p, "p");
        this.list.remove(p);
    }

    @Override // com.android.egg.landroid.Constraint
    public void solve(Simulator sim, float f) {
        Intrinsics.checkNotNullParameter(sim, "sim");
        Iterator it = this.list.iterator();
        while (it.hasNext()) {
            Body body = (Body) it.next();
            if (body.getRadius() + Vec2Kt.m446magk4lQ0M(body.m423getPosF1C5BW0()) > this.radius) {
                body.m426setPosk4lQ0M(Offset.m48plusMKHz9U(Offset.m49timestuRUvjQ(body.m423getPosF1C5BW0(), this.softness), Offset.m49timestuRUvjQ(Vec2Kt.makeWithAngleMag(Offset.Companion, Vec2Kt.m443anglek4lQ0M(body.m423getPosF1C5BW0()), this.radius - body.getRadius()), 1.0f - this.softness)));
            }
        }
    }

    public String toString() {
        float f = this.radius;
        return "Container(" + f + ")";
    }
}
