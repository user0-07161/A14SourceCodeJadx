package com.android.egg.landroid;

import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.os.Build;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPathEffect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import com.android.egg.landroid.Spark;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class VisibleUniverseKt {
    public static final boolean DRAW_GRAVITATIONAL_FIELDS = true;
    public static final boolean DRAW_ORBITS = true;
    public static final boolean DRAW_STAR_GRAVITATIONAL_FIELDS = true;
    private static final int STAR_POINTS;
    private static final Path spaceshipPath;
    private static final Path thrustPath;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Spark.Style.values().length];
            try {
                iArr[Spark.Style.LINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Spark.Style.LINE_ABSOLUTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Spark.Style.DOT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Spark.Style.DOT_ABSOLUTE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Spark.Style.RING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        int i;
        Integer valueOf = Integer.valueOf(Build.VERSION.SDK_INT);
        int intValue = valueOf.intValue();
        boolean z = true;
        if (!((1 > intValue || intValue >= 100) ? false : false)) {
            valueOf = null;
        }
        if (valueOf != null) {
            i = valueOf.intValue();
        } else {
            i = 31;
        }
        STAR_POINTS = i;
        AndroidPath Path = AndroidPath_androidKt.Path();
        PathToolsKt.parseSvgPathData(Path, "\nM11.853 0\nC11.853 -4.418 8.374 -8 4.083 -8\nL-5.5 -8\nC-6.328 -8 -7 -7.328 -7 -6.5\nC-7 -5.672 -6.328 -5 -5.5 -5\nL-2.917 -5\nC-1.26 -5 0.083 -3.657 0.083 -2\nL0.083 2\nC0.083 3.657 -1.26 5 -2.917 5\nL-5.5 5\nC-6.328 5 -7 5.672 -7 6.5\nC-7 7.328 -6.328 8 -5.5 8\nL4.083 8\nC8.374 8 11.853 4.418 11.853 0\nZ\n");
        spaceshipPath = Path;
        AndroidPath androidPath = (AndroidPath) PathToolsKt.createPolygon(-3.0f, 3);
        androidPath.m87translatek4lQ0M(Vec2Kt.Vec2(-4.0f, 0.0f));
        thrustPath = androidPath;
    }

    public static final void drawContainer(ZoomedDrawScope zoomedDrawScope, Container container) {
        long j;
        Intrinsics.checkNotNullParameter(zoomedDrawScope, "<this>");
        Intrinsics.checkNotNullParameter(container, "container");
        long Color = ColorKt.Color(4286578688L);
        float radius = container.getRadius();
        j = Offset.Zero;
        DrawScope.m163drawCircleVaOC9Bg$default(zoomedDrawScope, Color, radius, j, new Stroke(1.0f / zoomedDrawScope.getZoom(), new AndroidPathEffect(new DashPathEffect(new float[]{8.0f / zoomedDrawScope.getZoom(), 8.0f / zoomedDrawScope.getZoom()}, 0.0f)), 14), 104);
    }

    public static final void drawGravitationalField(ZoomedDrawScope zoomedDrawScope, Planet planet) {
        Intrinsics.checkNotNullParameter(zoomedDrawScope, "<this>");
        Intrinsics.checkNotNullParameter(planet, "planet");
        for (int i = 0; i < 8; i++) {
            float f = i / 8;
            float f2 = 1 - f;
            long Color = ColorKt.Color(1.0f, 0.0f, 0.0f, (f * 0.1f) + (f2 * 0.5f), ColorSpaces.getSrgb());
            DrawScope.m163drawCircleVaOC9Bg$default(zoomedDrawScope, Color, (float) Math.sqrt(((planet.getMass() * 0.01f) * 10.0f) / ((f * 0.01f) + (200.0f * f2))), planet.m423getPosF1C5BW0(), new Stroke(2.0f / zoomedDrawScope.getZoom(), null, 30), 104);
        }
    }

    public static final void drawLanding(ZoomedDrawScope zoomedDrawScope, Landing landing) {
        long j;
        long j2;
        Intrinsics.checkNotNullParameter(zoomedDrawScope, "<this>");
        Intrinsics.checkNotNullParameter(landing, "landing");
        long m48plusMKHz9U = Offset.m48plusMKHz9U(landing.getPlanet().m423getPosF1C5BW0(), Vec2Kt.makeWithAngleMag(Offset.Companion, landing.getAngle(), landing.getPlanet().getRadius()));
        j = Color.Red;
        zoomedDrawScope.mo145drawLineNGM6Ib0(j, Offset.m48plusMKHz9U(m48plusMKHz9U, Vec2Kt.Vec2(-5.0f, -5.0f)), Offset.m48plusMKHz9U(m48plusMKHz9U, Vec2Kt.Vec2(5.0f, 5.0f)), 1.0f / zoomedDrawScope.getZoom(), 0, null, 1.0f, null, 3);
        j2 = Color.Red;
        zoomedDrawScope.mo145drawLineNGM6Ib0(j2, Offset.m48plusMKHz9U(m48plusMKHz9U, Vec2Kt.Vec2(5.0f, -5.0f)), Offset.m48plusMKHz9U(m48plusMKHz9U, Vec2Kt.Vec2(-5.0f, 5.0f)), 1.0f / zoomedDrawScope.getZoom(), 0, null, 1.0f, null, 3);
    }

    public static final void drawPlanet(ZoomedDrawScope zoomedDrawScope, Planet planet) {
        Intrinsics.checkNotNullParameter(zoomedDrawScope, "<this>");
        Intrinsics.checkNotNullParameter(planet, "planet");
        DrawScope.m163drawCircleVaOC9Bg$default(zoomedDrawScope, ColorKt.Color(2147549183L), Vec2Kt.m444distance0a9Yr6o(planet.m423getPosF1C5BW0(), planet.m438getOrbitCenterF1C5BW0()), planet.m438getOrbitCenterF1C5BW0(), new Stroke(1.0f / zoomedDrawScope.getZoom(), null, 30), 104);
        drawGravitationalField(zoomedDrawScope, planet);
        DrawScope.m163drawCircleVaOC9Bg$default(zoomedDrawScope, Colors.INSTANCE.m429getEigengrau0d7_KjU(), planet.getRadius(), planet.m423getPosF1C5BW0(), null, 120);
        DrawScope.m163drawCircleVaOC9Bg$default(zoomedDrawScope, planet.m437getColor0d7_KjU(), planet.getRadius(), planet.m423getPosF1C5BW0(), new Stroke(2.0f / zoomedDrawScope.getZoom(), null, 30), 104);
    }

    public static final void drawSpacecraft(ZoomedDrawScope zoomedDrawScope, Spacecraft ship) {
        long j;
        Intrinsics.checkNotNullParameter(zoomedDrawScope, "<this>");
        Intrinsics.checkNotNullParameter(ship, "ship");
        float angle = ship.getAngle();
        long m423getPosF1C5BW0 = ship.m423getPosF1C5BW0();
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = (CanvasDrawScope$drawContext$1) zoomedDrawScope.getDrawContext();
        long m159getSizeNHjbRc = canvasDrawScope$drawContext$1.m159getSizeNHjbRc();
        canvasDrawScope$drawContext$1.getCanvas().save();
        canvasDrawScope$drawContext$1.getTransform().m161rotateUv8p0NA(m423getPosF1C5BW0, angle * 57.29578f);
        float m45getXimpl = Offset.m45getXimpl(ship.m423getPosF1C5BW0());
        float m46getYimpl = Offset.m46getYimpl(ship.m423getPosF1C5BW0());
        ((CanvasDrawScope$drawContext$1) zoomedDrawScope.getDrawContext()).getTransform().translate(m45getXimpl, m46getYimpl);
        Path path = spaceshipPath;
        DrawScope.m165drawPathLG529CI$default(zoomedDrawScope, path, Colors.INSTANCE.m429getEigengrau0d7_KjU(), null, 60);
        DrawScope.m165drawPathLG529CI$default(zoomedDrawScope, path, ship.getTransit() ? Color.Black : Color.White, new Stroke(2.0f / zoomedDrawScope.getZoom(), null, 30), 52);
        long m440getThrustF1C5BW0 = ship.m440getThrustF1C5BW0();
        j = Offset.Zero;
        if (!Offset.m43equalsimpl0(m440getThrustF1C5BW0, j)) {
            DrawScope.m165drawPathLG529CI$default(zoomedDrawScope, thrustPath, ColorKt.Color(4294936576L), new Stroke(2.0f / zoomedDrawScope.getZoom(), new AndroidPathEffect(new CornerPathEffect(1.0f)), 14), 52);
        }
        ((CanvasDrawScope$drawContext$1) zoomedDrawScope.getDrawContext()).getTransform().translate(-m45getXimpl, -m46getYimpl);
        canvasDrawScope$drawContext$1.getCanvas().restore();
        canvasDrawScope$drawContext$1.m160setSizeuvyYCjk(m159getSizeNHjbRc);
        drawTrack(zoomedDrawScope, ship.getTrack());
    }

    public static final void drawSpark(ZoomedDrawScope zoomedDrawScope, Spark spark) {
        long j;
        long j2;
        Intrinsics.checkNotNullParameter(zoomedDrawScope, "<this>");
        Intrinsics.checkNotNullParameter(spark, "spark");
        if (spark.getLifetime() < 0.0f) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[spark.getStyle().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            DrawScope.m163drawCircleVaOC9Bg$default(zoomedDrawScope, spark.m442getColor0d7_KjU(), spark.getSize(), spark.m423getPosF1C5BW0(), new Stroke(1.0f / zoomedDrawScope.getZoom(), null, 30), 104);
                            return;
                        }
                        return;
                    }
                    long m442getColor0d7_KjU = spark.m442getColor0d7_KjU();
                    float size = spark.getSize();
                    long m423getPosF1C5BW0 = spark.m423getPosF1C5BW0();
                    float zoom = zoomedDrawScope.getZoom();
                    DrawScope.m163drawCircleVaOC9Bg$default(zoomedDrawScope, m442getColor0d7_KjU, size, OffsetKt.Offset(Offset.m45getXimpl(m423getPosF1C5BW0) / zoom, Offset.m46getYimpl(m423getPosF1C5BW0) / zoom), null, 120);
                    return;
                }
                DrawScope.m163drawCircleVaOC9Bg$default(zoomedDrawScope, spark.m442getColor0d7_KjU(), spark.getSize(), spark.m423getPosF1C5BW0(), null, 120);
                return;
            }
            long m422getOposF1C5BW0 = spark.m422getOposF1C5BW0();
            j2 = Offset.Zero;
            if (!Offset.m43equalsimpl0(m422getOposF1C5BW0, j2)) {
                zoomedDrawScope.mo145drawLineNGM6Ib0(spark.m442getColor0d7_KjU(), spark.m422getOposF1C5BW0(), spark.m423getPosF1C5BW0(), spark.getSize() / zoomedDrawScope.getZoom(), 0, null, 1.0f, null, 3);
                return;
            }
            return;
        }
        long m422getOposF1C5BW02 = spark.m422getOposF1C5BW0();
        j = Offset.Zero;
        if (!Offset.m43equalsimpl0(m422getOposF1C5BW02, j)) {
            zoomedDrawScope.mo145drawLineNGM6Ib0(spark.m442getColor0d7_KjU(), spark.m422getOposF1C5BW0(), spark.m423getPosF1C5BW0(), spark.getSize(), 0, null, 1.0f, null, 3);
        }
    }

    public static final void drawStar(ZoomedDrawScope zoomedDrawScope, Star star) {
        long j;
        long j2;
        long j3;
        Intrinsics.checkNotNullParameter(zoomedDrawScope, "<this>");
        Intrinsics.checkNotNullParameter(star, "star");
        float m45getXimpl = Offset.m45getXimpl(star.m423getPosF1C5BW0());
        float m46getYimpl = Offset.m46getYimpl(star.m423getPosF1C5BW0());
        ((CanvasDrawScope$drawContext$1) zoomedDrawScope.getDrawContext()).getTransform().translate(m45getXimpl, m46getYimpl);
        long m437getColor0d7_KjU = star.m437getColor0d7_KjU();
        float radius = star.getRadius();
        j = Offset.Zero;
        DrawScope.m163drawCircleVaOC9Bg$default(zoomedDrawScope, m437getColor0d7_KjU, radius, j, null, 120);
        drawGravitationalField(zoomedDrawScope, star);
        j2 = Offset.Zero;
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = (CanvasDrawScope$drawContext$1) zoomedDrawScope.getDrawContext();
        long m159getSizeNHjbRc = canvasDrawScope$drawContext$1.m159getSizeNHjbRc();
        canvasDrawScope$drawContext$1.getCanvas().save();
        canvasDrawScope$drawContext$1.getTransform().m161rotateUv8p0NA(j2, (star.getAnim() / 23.0f) * 6.2831855f * 57.29578f);
        int i = STAR_POINTS;
        DrawScope.m165drawPathLG529CI$default(zoomedDrawScope, PathToolsKt.createStar(star.getRadius() + 80, star.getRadius() + 250, i), star.m437getColor0d7_KjU(), new Stroke(3.0f / zoomedDrawScope.getZoom(), new AndroidPathEffect(new CornerPathEffect(200.0f)), 14), 52);
        canvasDrawScope$drawContext$1.getCanvas().restore();
        canvasDrawScope$drawContext$1.m160setSizeuvyYCjk(m159getSizeNHjbRc);
        j3 = Offset.Zero;
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$12 = (CanvasDrawScope$drawContext$1) zoomedDrawScope.getDrawContext();
        long m159getSizeNHjbRc2 = canvasDrawScope$drawContext$12.m159getSizeNHjbRc();
        canvasDrawScope$drawContext$12.getCanvas().save();
        canvasDrawScope$drawContext$12.getTransform().m161rotateUv8p0NA(j3, (star.getAnim() / (-19.0f)) * 6.2831855f * 57.29578f);
        DrawScope.m165drawPathLG529CI$default(zoomedDrawScope, PathToolsKt.createStar(star.getRadius() + 20, star.getRadius() + 200, i + 1), star.m437getColor0d7_KjU(), new Stroke(3.0f / zoomedDrawScope.getZoom(), new AndroidPathEffect(new CornerPathEffect(200.0f)), 14), 52);
        canvasDrawScope$drawContext$12.getCanvas().restore();
        canvasDrawScope$drawContext$12.m160setSizeuvyYCjk(m159getSizeNHjbRc2);
        ((CanvasDrawScope$drawContext$1) zoomedDrawScope.getDrawContext()).getTransform().translate(-m45getXimpl, -m46getYimpl);
    }

    public static final void drawTrack(ZoomedDrawScope zoomedDrawScope, Track track) {
        long j;
        Intrinsics.checkNotNullParameter(zoomedDrawScope, "<this>");
        Intrinsics.checkNotNullParameter(track, "track");
        ArrayDeque positions = track.getPositions();
        Color.Companion companion = Color.Companion;
        j = Color.Green;
        zoomedDrawScope.mo150drawPointsF8ZwMP8(positions, 1, j, 1.0f / zoomedDrawScope.getZoom(), 0, null, 1.0f, null, 3);
    }

    public static final void drawUniverse(ZoomedDrawScope zoomedDrawScope, VisibleUniverse universe) {
        Intrinsics.checkNotNullParameter(zoomedDrawScope, "<this>");
        Intrinsics.checkNotNullParameter(universe, "universe");
        ((SnapshotMutableStateImpl) universe.getTriggerDraw()).getValue();
        for (Constraint it : universe.getConstraints()) {
            if (it instanceof Landing) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                drawLanding(zoomedDrawScope, (Landing) it);
            } else if (it instanceof Container) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                drawContainer(zoomedDrawScope, (Container) it);
            }
        }
        drawStar(zoomedDrawScope, universe.getStar());
        for (Entity it2 : universe.getEntities()) {
            if (it2 != universe.getShip() && it2 != universe.getStar()) {
                if (it2 instanceof Spacecraft) {
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    drawSpacecraft(zoomedDrawScope, (Spacecraft) it2);
                } else if (it2 instanceof Spark) {
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    drawSpark(zoomedDrawScope, (Spark) it2);
                } else if (it2 instanceof Planet) {
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    drawPlanet(zoomedDrawScope, (Planet) it2);
                }
            }
        }
        drawSpacecraft(zoomedDrawScope, universe.getShip());
    }

    public static final int getSTAR_POINTS() {
        return STAR_POINTS;
    }

    public static final Path getSpaceshipPath() {
        return spaceshipPath;
    }

    public static final Path getThrustPath() {
        return thrustPath;
    }

    public static final void zoom(DrawScope drawScope, float f, Function1 block) {
        Intrinsics.checkNotNullParameter(drawScope, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        VisibleUniverseKt$zoom$ds$1 visibleUniverseKt$zoom$ds$1 = new VisibleUniverseKt$zoom$ds$1(drawScope, f);
        long mo166getCenterF1C5BW0 = visibleUniverseKt$zoom$ds$1.mo166getCenterF1C5BW0();
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = (CanvasDrawScope$drawContext$1) visibleUniverseKt$zoom$ds$1.getDrawContext();
        long m159getSizeNHjbRc = canvasDrawScope$drawContext$1.m159getSizeNHjbRc();
        canvasDrawScope$drawContext$1.getCanvas().save();
        canvasDrawScope$drawContext$1.getTransform().m162scale0AR0LA0(f, mo166getCenterF1C5BW0, f);
        block.invoke(visibleUniverseKt$zoom$ds$1);
        canvasDrawScope$drawContext$1.getCanvas().restore();
        canvasDrawScope$drawContext$1.m160setSizeuvyYCjk(m159getSizeNHjbRc);
    }
}
