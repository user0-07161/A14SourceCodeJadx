package com.android.egg.landroid;

import android.util.ArraySet;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.XorWowRandom;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class Simulator {
    public static final int $stable = 8;
    private float dt;
    private float now;
    private final long randomSeed;
    private final Random rng;
    private long wallClockNanos;
    private final ArraySet entities = new ArraySet(1000);
    private final ArraySet constraints = new ArraySet(100);

    public Simulator(long j) {
        this.randomSeed = j;
        this.rng = new XorWowRandom((int) j, (int) (j >> 32));
    }

    public final boolean add(Entity e) {
        Intrinsics.checkNotNullParameter(e, "e");
        return this.entities.add(e);
    }

    public final ArraySet getConstraints() {
        return this.constraints;
    }

    public final float getDt() {
        return this.dt;
    }

    public final ArraySet getEntities() {
        return this.entities;
    }

    public final float getNow() {
        return this.now;
    }

    public final long getRandomSeed() {
        return this.randomSeed;
    }

    public final Random getRng() {
        return this.rng;
    }

    public void postUpdateAll(float f, ArraySet entities) {
        Intrinsics.checkNotNullParameter(entities, "entities");
        Iterator it = entities.iterator();
        while (it.hasNext()) {
            ((Entity) it.next()).postUpdate(this, f);
        }
    }

    public final boolean remove(Entity e) {
        Intrinsics.checkNotNullParameter(e, "e");
        return this.entities.remove(e);
    }

    public final void setDt(float f) {
        this.dt = f;
    }

    public final void setNow(float f) {
        this.now = f;
    }

    public void solveAll(float f, ArraySet constraints) {
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        Iterator it = constraints.iterator();
        while (it.hasNext()) {
            ((Constraint) it.next()).solve(this, f);
        }
    }

    public final void step(long j) {
        boolean z;
        long j2 = this.wallClockNanos;
        if (j2 == 0) {
            z = true;
        } else {
            z = false;
        }
        float f = (((float) (j - j2)) / 1.0E9f) * 1.0f;
        this.dt = f;
        this.wallClockNanos = j;
        if (!z && f <= 1.0f) {
            this.now += f;
            ArraySet arraySet = new ArraySet(this.entities);
            ArraySet arraySet2 = new ArraySet(this.constraints);
            updateAll(this.dt, arraySet);
            solveAll(this.dt, arraySet2);
            postUpdateAll(this.dt, arraySet);
        }
    }

    public void updateAll(float f, ArraySet entities) {
        Intrinsics.checkNotNullParameter(entities, "entities");
        Iterator it = entities.iterator();
        while (it.hasNext()) {
            ((Entity) it.next()).update(this, f);
        }
    }

    public final boolean add(Constraint c) {
        Intrinsics.checkNotNullParameter(c, "c");
        return this.constraints.add(c);
    }

    public final boolean remove(Constraint c) {
        Intrinsics.checkNotNullParameter(c, "c");
        return this.constraints.remove(c);
    }
}
