package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FloatTweenSpec implements FloatAnimationSpec {
    private final int delay;
    private final int duration;
    private final Easing easing;

    public FloatTweenSpec(int i, int i2, Easing easing) {
        Intrinsics.checkNotNullParameter(easing, "easing");
        this.duration = i;
        this.delay = i2;
        this.easing = easing;
    }

    private final long clampPlayTime(long j) {
        long j2 = j - this.delay;
        long j3 = this.duration;
        if (0 <= j3) {
            if (j2 < 0) {
                return 0L;
            }
            if (j2 > j3) {
                return j3;
            }
            return j2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j3 + " is less than minimum 0.");
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final long getDurationNanos(float f, float f2, float f3) {
        return (this.delay + this.duration) * 1000000;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getValueFromNanos(long j, float f, float f2, float f3) {
        float f4;
        long clampPlayTime = clampPlayTime(j / 1000000);
        int i = this.duration;
        if (i == 0) {
            f4 = 1.0f;
        } else {
            f4 = ((float) clampPlayTime) / i;
        }
        float transform = this.easing.transform(RangesKt.coerceIn(f4, 0.0f, 1.0f));
        int i2 = VectorConvertersKt.$r8$clinit;
        return (f2 * transform) + ((1 - transform) * f);
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getVelocityFromNanos(long j, float f, float f2, float f3) {
        long clampPlayTime = clampPlayTime(j / 1000000);
        int i = (clampPlayTime > 0L ? 1 : (clampPlayTime == 0L ? 0 : -1));
        if (i < 0) {
            return 0.0f;
        }
        if (i == 0) {
            return f3;
        }
        return (getValueFromNanos(clampPlayTime * 1000000, f, f2, f3) - getValueFromNanos((clampPlayTime - 1) * 1000000, f, f2, f3)) * 1000.0f;
    }
}
