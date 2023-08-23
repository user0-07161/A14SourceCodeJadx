package androidx.compose.animation.core;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FloatSpringSpec implements FloatAnimationSpec {
    private final SpringSimulation spring;
    private final float visibilityThreshold;

    public FloatSpringSpec(float f, float f2, float f3) {
        this.visibilityThreshold = f3;
        SpringSimulation springSimulation = new SpringSimulation();
        springSimulation.setDampingRatio(f);
        springSimulation.setStiffness(f2);
        this.spring = springSimulation;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0148, code lost:
        if (r15 < 0.0d) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0196  */
    @Override // androidx.compose.animation.core.FloatAnimationSpec
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long getDurationNanos(float r39, float r40, float r41) {
        /*
            Method dump skipped, instructions count: 750
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.FloatSpringSpec.getDurationNanos(float, float, float):long");
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getEndVelocity(float f, float f2, float f3) {
        return 0.0f;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getValueFromNanos(long j, float f, float f2, float f3) {
        SpringSimulation springSimulation = this.spring;
        springSimulation.setFinalPosition(f2);
        return Float.intBitsToFloat((int) (springSimulation.m6updateValuesIJZedt4$animation_core_release(f, j / 1000000, f3) >> 32));
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getVelocityFromNanos(long j, float f, float f2, float f3) {
        SpringSimulation springSimulation = this.spring;
        springSimulation.setFinalPosition(f2);
        return Float.intBitsToFloat((int) (springSimulation.m6updateValuesIJZedt4$animation_core_release(f, j / 1000000, f3) & 4294967295L));
    }
}
