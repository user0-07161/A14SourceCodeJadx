package com.android.egg.landroid;

import android.util.ArraySet;
import android.util.Log;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import com.android.egg.landroid.Spark;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class Universe extends Simulator {
    public static final int $stable = 8;
    private Body follow;
    private Planet latestDiscovery;
    private final Namer namer;
    private final List planets;
    private final Container ringfence;
    public Spacecraft ship;
    public Star star;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Universe(Namer namer, long j) {
        super(j);
        Intrinsics.checkNotNullParameter(namer, "namer");
        this.namer = namer;
        this.planets = new ArrayList();
        this.ringfence = new Container(200000.0f);
    }

    public final Planet closestPlanet() {
        List list = this.planets;
        Star star = getStar();
        Intrinsics.checkNotNullParameter(list, "<this>");
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.addAll(list);
        arrayList.add(star);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Planet planet = (Planet) it.next();
            arrayList2.add(new Pair(Offset.m42boximpl(Offset.m47minusMKHz9U(planet.m423getPosF1C5BW0(), getShip().m423getPosF1C5BW0())), planet));
        }
        return (Planet) ((Pair) CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: com.android.egg.landroid.Universe$closestPlanet$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ComparisonsKt.compareValues(Float.valueOf(Vec2Kt.m446magk4lQ0M(((Offset) ((Pair) obj).getFirst()).m51unboximpl())), Float.valueOf(Vec2Kt.m446magk4lQ0M(((Offset) ((Pair) obj2).getFirst()).m51unboximpl())));
            }
        }).get(0)).getSecond();
    }

    public final Body getFollow() {
        return this.follow;
    }

    public final Planet getLatestDiscovery() {
        return this.latestDiscovery;
    }

    public final Namer getNamer() {
        return this.namer;
    }

    public final List getPlanets() {
        return this.planets;
    }

    public final Container getRingfence() {
        return this.ringfence;
    }

    public final Spacecraft getShip() {
        Spacecraft spacecraft = this.ship;
        if (spacecraft != null) {
            return spacecraft;
        }
        Intrinsics.throwUninitializedPropertyAccessException("ship");
        throw null;
    }

    public final Star getStar() {
        Star star = this.star;
        if (star != null) {
            return star;
        }
        Intrinsics.throwUninitializedPropertyAccessException("star");
        throw null;
    }

    public final void initRandom() {
        String nameSystem = this.namer.nameSystem(getRng());
        setStar(new Star((StarClass) RandomnessKt.choose(getRng(), StarClass.values()), RandomnessKt.nextFloatInRange(getRng(), UniverseKt.getSTAR_RADIUS_RANGE())));
        getStar().setName(nameSystem);
        int i = 1;
        int nextInt = getRng().nextInt(UniverseKt.getNUM_PLANETS_RANGE().getFirst(), UniverseKt.getNUM_PLANETS_RANGE().getLast() + 1);
        int i2 = 0;
        while (i2 < nextInt) {
            float nextFloatInRange = RandomnessKt.nextFloatInRange(getRng(), UniverseKt.getPLANET_RADIUS_RANGE());
            float floatValue = ((Number) UniverseKt.getPLANET_ORBIT_RANGE().getStart()).floatValue();
            float floatValue2 = ((Number) UniverseKt.getPLANET_ORBIT_RANGE().getEndInclusive()).floatValue();
            float pow = (float) Math.pow(getRng().nextFloat(), 1.0f);
            float f = (pow * floatValue2) + ((i - pow) * floatValue);
            float sqrt = ((float) Math.sqrt(((float) Math.pow(f, 3.0f)) / getStar().getMass())) * 50.0f;
            float f2 = (f * 6.2831855f) / sqrt;
            Planet planet = new Planet(getStar().m423getPosF1C5BW0(), nextFloatInRange, Offset.m48plusMKHz9U(getStar().m423getPosF1C5BW0(), Vec2Kt.makeWithAngleMag(Offset.Companion, 6.2831855f * getRng().nextFloat(), f)), f2, Colors.INSTANCE.m432getEigengrau40d7_KjU(), null);
            Log.v("Landroid", "created planet " + planet + " with period " + sqrt + " and vel " + f2);
            planet.setDescription(this.namer.describePlanet(getRng()));
            planet.setAtmosphere(this.namer.describeAtmo(getRng()));
            planet.setFlora(this.namer.describeLife(getRng()));
            planet.setFauna(this.namer.describeLife(getRng()));
            this.planets.add(planet);
            add(planet);
            i2++;
            i = 1;
        }
        List list = this.planets;
        if (list.size() > 1) {
            CollectionsKt.sortWith(list, new Comparator() { // from class: com.android.egg.landroid.Universe$initRandom$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ComparisonsKt.compareValues(Float.valueOf(Vec2Kt.m444distance0a9Yr6o(((Planet) obj).m423getPosF1C5BW0(), Universe.this.getStar().m423getPosF1C5BW0())), Float.valueOf(Vec2Kt.m444distance0a9Yr6o(((Planet) obj2).m423getPosF1C5BW0(), Universe.this.getStar().m423getPosF1C5BW0())));
                }
            });
        }
        int i3 = 0;
        for (Object obj : this.planets) {
            int i4 = i3 + 1;
            if (i3 >= 0) {
                ((Planet) obj).setName(nameSystem + " " + i4);
                i3 = i4;
            } else {
                CollectionsKt.throwIndexOverflow();
                throw null;
            }
        }
        add(getStar());
        setShip(new Spacecraft());
        getShip().m426setPosk4lQ0M(Offset.m48plusMKHz9U(getStar().m423getPosF1C5BW0(), Vec2Kt.makeWithAngleMag(Offset.Companion, getRng().nextFloat() * 6.2831855f, RandomnessKt.nextFloatInRange(getRng(), ((Number) UniverseKt.getPLANET_ORBIT_RANGE().getStart()).floatValue(), ((Number) UniverseKt.getPLANET_ORBIT_RANGE().getEndInclusive()).floatValue()))));
        getShip().setAngle(getRng().nextFloat() * 6.2831855f);
        add(getShip());
        this.ringfence.add(getShip());
        add(this.ringfence);
        this.follow = getShip();
    }

    public final void initTest() {
        Star star = new Star(StarClass.A, ((Number) UniverseKt.getSTAR_RADIUS_RANGE().getEndInclusive()).floatValue());
        star.setName("TEST SYSTEM");
        setStar(star);
        int i = 0;
        for (int last = UniverseKt.getNUM_PLANETS_RANGE().getLast(); i < last; last = last) {
            float last2 = i / (UniverseKt.getNUM_PLANETS_RANGE().getLast() - 1);
            float floatValue = ((Number) UniverseKt.getPLANET_RADIUS_RANGE().getStart()).floatValue();
            float f = 1 - last2;
            float floatValue2 = (((Number) UniverseKt.getPLANET_RADIUS_RANGE().getEndInclusive()).floatValue() * last2) + (floatValue * f);
            float floatValue3 = ((Number) UniverseKt.getPLANET_ORBIT_RANGE().getStart()).floatValue();
            float floatValue4 = (((Number) UniverseKt.getPLANET_ORBIT_RANGE().getEndInclusive()).floatValue() * last2) + (f * floatValue3);
            float sqrt = ((float) Math.sqrt(((float) Math.pow(floatValue4, 3.0f)) / getStar().getMass())) * 50.0f;
            float f2 = (floatValue4 * 6.2831855f) / sqrt;
            Planet planet = new Planet(getStar().m423getPosF1C5BW0(), floatValue2, Offset.m48plusMKHz9U(getStar().m423getPosF1C5BW0(), Vec2Kt.makeWithAngleMag(Offset.Companion, last2 * 6.2831855f, floatValue4)), f2, Colors.INSTANCE.m432getEigengrau40d7_KjU(), null);
            Log.v("Landroid", "created planet " + planet + " with period " + sqrt + " and vel " + f2);
            i++;
            StringBuilder sb = new StringBuilder("TEST PLANET #");
            sb.append(i);
            planet.setDescription(sb.toString());
            planet.setAtmosphere("radius=" + floatValue2);
            float mass = planet.getMass();
            planet.setFlora("mass=" + mass);
            planet.setFauna("speed=" + f2);
            this.planets.add(planet);
            add(planet);
        }
        List list = this.planets;
        if (list.size() > 1) {
            CollectionsKt.sortWith(list, new Comparator() { // from class: com.android.egg.landroid.Universe$initTest$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ComparisonsKt.compareValues(Float.valueOf(Vec2Kt.m444distance0a9Yr6o(((Planet) obj).m423getPosF1C5BW0(), Universe.this.getStar().m423getPosF1C5BW0())), Float.valueOf(Vec2Kt.m444distance0a9Yr6o(((Planet) obj2).m423getPosF1C5BW0(), Universe.this.getStar().m423getPosF1C5BW0())));
                }
            });
        }
        int i2 = 0;
        for (Object obj : this.planets) {
            int i3 = i2 + 1;
            if (i2 >= 0) {
                ((Planet) obj).setName("TEST SYSTEM " + i3);
                i2 = i3;
            } else {
                CollectionsKt.throwIndexOverflow();
                throw null;
            }
        }
        add(getStar());
        setShip(new Spacecraft());
        getShip().m426setPosk4lQ0M(Offset.m48plusMKHz9U(getStar().m423getPosF1C5BW0(), Vec2Kt.makeWithAngleMag(Offset.Companion, 0.7853982f, ((Number) UniverseKt.getPLANET_ORBIT_RANGE().getStart()).floatValue())));
        getShip().setAngle(0.0f);
        add(getShip());
        this.ringfence.add(getShip());
        add(this.ringfence);
        this.follow = getShip();
    }

    @Override // com.android.egg.landroid.Simulator
    public void postUpdateAll(float f, ArraySet entities) {
        Intrinsics.checkNotNullParameter(entities, "entities");
        super.postUpdateAll(f, entities);
        ArrayList arrayList = new ArrayList();
        for (Object obj : entities) {
            if (obj instanceof Removable) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (((Removable) obj2).canBeRemoved()) {
                arrayList2.add(obj2);
            }
        }
        ArrayList<Entity> arrayList3 = new ArrayList();
        for (Object obj3 : arrayList2) {
            if (obj3 instanceof Entity) {
                arrayList3.add(obj3);
            }
        }
        for (Entity entity : arrayList3) {
            remove(entity);
        }
    }

    public final void setFollow(Body body) {
        this.follow = body;
    }

    public final void setLatestDiscovery(Planet planet) {
        this.latestDiscovery = planet;
    }

    public final void setShip(Spacecraft spacecraft) {
        Intrinsics.checkNotNullParameter(spacecraft, "<set-?>");
        this.ship = spacecraft;
    }

    public final void setStar(Star star) {
        Intrinsics.checkNotNullParameter(star, "<set-?>");
        this.star = star;
    }

    @Override // com.android.egg.landroid.Simulator
    public void solveAll(float f, ArraySet constraints) {
        long j;
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        if (getShip().getLanding() == null) {
            Planet closestPlanet = closestPlanet();
            if (closestPlanet.getCollides()) {
                float m446magk4lQ0M = (Vec2Kt.m446magk4lQ0M(Offset.m47minusMKHz9U(getShip().m423getPosF1C5BW0(), closestPlanet.m423getPosF1C5BW0())) - getShip().getRadius()) - closestPlanet.getRadius();
                float m443anglek4lQ0M = Vec2Kt.m443anglek4lQ0M(Offset.m47minusMKHz9U(getShip().m423getPosF1C5BW0(), closestPlanet.m423getPosF1C5BW0()));
                if (m446magk4lQ0M < 0.0f) {
                    Vec2Kt.m446magk4lQ0M(Offset.m47minusMKHz9U(getShip().m424getVelocityF1C5BW0(), closestPlanet.m424getVelocityF1C5BW0()));
                    if (Math.abs(getShip().getAngle() - m443anglek4lQ0M) < 0.7853982f) {
                        Landing landing = new Landing(getShip(), closestPlanet, m443anglek4lQ0M);
                        getShip().setLanding(landing);
                        getShip().m427setVelocityk4lQ0M(closestPlanet.m424getVelocityF1C5BW0());
                        add(landing);
                        closestPlanet.setExplored(true);
                        this.latestDiscovery = closestPlanet;
                    } else {
                        long m423getPosF1C5BW0 = closestPlanet.m423getPosF1C5BW0();
                        Offset.Companion companion = Offset.Companion;
                        long m48plusMKHz9U = Offset.m48plusMKHz9U(m423getPosF1C5BW0, Vec2Kt.makeWithAngleMag(companion, m443anglek4lQ0M, closestPlanet.getRadius()));
                        getShip().m426setPosk4lQ0M(Offset.m48plusMKHz9U(closestPlanet.m423getPosF1C5BW0(), Vec2Kt.makeWithAngleMag(companion, m443anglek4lQ0M, (getShip().getRadius() + closestPlanet.getRadius()) - m446magk4lQ0M)));
                        Iterator it = new IntRange(1, 10).iterator();
                        while (it.hasNext()) {
                            ((IntProgressionIterator) it).nextInt();
                            float nextFloatInRange = RandomnessKt.nextFloatInRange(getRng(), 0.5f, 2.0f);
                            Spark.Style style = Spark.Style.DOT;
                            Color.Companion companion2 = Color.Companion;
                            j = Color.White;
                            Spark spark = new Spark(nextFloatInRange, false, 0.0f, style, j, 1.0f, 6, null);
                            Offset.Companion companion3 = Offset.Companion;
                            spark.m426setPosk4lQ0M(Offset.m48plusMKHz9U(m48plusMKHz9U, Vec2Kt.makeWithAngleMag(companion3, RandomnessKt.nextFloatInRange(getRng(), 0.0f, 6.2831855f), RandomnessKt.nextFloatInRange(getRng(), 0.1f, 0.5f))));
                            spark.m425setOposk4lQ0M(spark.m423getPosF1C5BW0());
                            spark.m427setVelocityk4lQ0M(Offset.m48plusMKHz9U(Offset.m49timestuRUvjQ(getShip().m424getVelocityF1C5BW0(), 0.8f), Vec2Kt.makeWithAngleMag(companion3, RandomnessKt.nextFloatInRange(getRng(), 0.0f, 6.2831855f), RandomnessKt.nextFloatInRange(getRng(), 0.1f, 0.5f))));
                            add(spark);
                        }
                    }
                }
            }
        }
        super.solveAll(f, constraints);
    }

    @Override // com.android.egg.landroid.Simulator
    public void updateAll(float f, ArraySet entities) {
        Intrinsics.checkNotNullParameter(entities, "entities");
        getShip().setTransit(false);
        List list = this.planets;
        Star star = getStar();
        Intrinsics.checkNotNullParameter(list, "<this>");
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.addAll(list);
        arrayList.add(star);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Planet planet = (Planet) it.next();
            long m47minusMKHz9U = Offset.m47minusMKHz9U(planet.m423getPosF1C5BW0(), getShip().m423getPosF1C5BW0());
            float m446magk4lQ0M = Vec2Kt.m446magk4lQ0M(m47minusMKHz9U);
            if (m446magk4lQ0M < planet.getRadius()) {
                if (planet instanceof Star) {
                    getShip().setTransit(true);
                }
            } else if (getNow() > getShip().getLaunchClock() + 2.0f) {
                getShip().m427setVelocityk4lQ0M(Offset.m48plusMKHz9U(getShip().m424getVelocityF1C5BW0(), Offset.m49timestuRUvjQ(Vec2Kt.makeWithAngleMag(Offset.Companion, Vec2Kt.m443anglek4lQ0M(m47minusMKHz9U), ((planet.getMass() * getShip().getMass()) * 0.01f) / ((float) Math.pow(m446magk4lQ0M, 2))), f)));
            }
        }
        super.updateAll(f, entities);
    }
}
