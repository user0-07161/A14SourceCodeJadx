package com.android.egg.landroid;

import androidx.compose.ui.graphics.ColorKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class UniverseKt {
    public static final float CRAFT_SPEED_LIMIT = 5000.0f;
    public static final float GRAVITATION = 0.01f;
    public static final float KEPLER_CONSTANT = 50.0f;
    public static final float LAUNCH_MECO = 2.0f;
    public static final float MAIN_ENGINE_ACCEL = 1000.0f;
    private static final IntRange NUM_PLANETS_RANGE = new IntRange(1, 10);
    public static final float PLANETARY_DENSITY = 2.5f;
    private static final ClosedFloatingPointRange PLANET_ORBIT_RANGE;
    private static final ClosedFloatingPointRange PLANET_RADIUS_RANGE;
    public static final boolean SCALED_THRUST = true;
    public static final boolean SIMPLE_TRACK_DRAWING = true;
    public static final float SPACECRAFT_MASS = 10.0f;
    private static final ClosedFloatingPointRange STAR_RADIUS_RANGE;
    public static final float STELLAR_DENSITY = 0.5f;
    public static final int TRACK_LENGTH = 10000;
    public static final float UNIVERSE_RANGE = 200000.0f;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StarClass.values().length];
            try {
                iArr[StarClass.O.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[StarClass.B.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[StarClass.A.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[StarClass.F.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[StarClass.G.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[StarClass.K.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[StarClass.M.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        ClosedFloatingPointRange rangeTo = RangesKt.rangeTo(1000.0f, 8000.0f);
        STAR_RADIUS_RANGE = rangeTo;
        PLANET_RADIUS_RANGE = RangesKt.rangeTo(50.0f, 2000.0f);
        PLANET_ORBIT_RANGE = RangesKt.rangeTo(((Number) rangeTo.getEndInclusive()).floatValue() * 2.0f, 150000.0f);
    }

    public static final IntRange getNUM_PLANETS_RANGE() {
        return NUM_PLANETS_RANGE;
    }

    public static final ClosedFloatingPointRange getPLANET_ORBIT_RANGE() {
        return PLANET_ORBIT_RANGE;
    }

    public static final ClosedFloatingPointRange getPLANET_RADIUS_RANGE() {
        return PLANET_RADIUS_RANGE;
    }

    public static final ClosedFloatingPointRange getSTAR_RADIUS_RANGE() {
        return STAR_RADIUS_RANGE;
    }

    public static final long starColor(StarClass cls) {
        Intrinsics.checkNotNullParameter(cls, "cls");
        switch (WhenMappings.$EnumSwitchMapping$0[cls.ordinal()]) {
            case 1:
                return ColorKt.Color(4284901119L);
            case 2:
                return ColorKt.Color(4291611903L);
            case 3:
                return ColorKt.Color(4293848831L);
            case 4:
                return ColorKt.Color(4294967295L);
            case 5:
                return ColorKt.Color(4294967142L);
            case 6:
                return ColorKt.Color(4294954035L);
            case 7:
                return ColorKt.Color(4294936576L);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
