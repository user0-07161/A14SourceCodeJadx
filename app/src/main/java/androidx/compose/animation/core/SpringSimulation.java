package androidx.compose.animation.core;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SpringSimulation {
    private double dampedFreq;
    private double gammaMinus;
    private double gammaPlus;
    private boolean initialized;
    private float finalPosition = 1.0f;
    private double naturalFreq = Math.sqrt(50.0d);
    private float dampingRatio = 1.0f;

    public final float getDampingRatio() {
        return this.dampingRatio;
    }

    public final float getStiffness() {
        double d = this.naturalFreq;
        return (float) (d * d);
    }

    public final void setDampingRatio(float f) {
        if (f >= 0.0f) {
            this.dampingRatio = f;
            this.initialized = false;
            return;
        }
        throw new IllegalArgumentException("Damping ratio must be non-negative");
    }

    public final void setFinalPosition(float f) {
        this.finalPosition = f;
    }

    public final void setStiffness(float f) {
        double d = this.naturalFreq;
        if (((float) (d * d)) > 0.0f) {
            this.naturalFreq = Math.sqrt(f);
            this.initialized = false;
            return;
        }
        throw new IllegalArgumentException("Spring stiffness constant must be positive.");
    }

    /* renamed from: updateValues-IJZedt4$animation_core_release  reason: not valid java name */
    public final long m6updateValuesIJZedt4$animation_core_release(float f, long j, float f2) {
        float f3;
        double cos;
        double d;
        boolean z;
        boolean z2 = false;
        if (!this.initialized) {
            if (this.finalPosition == Float.MAX_VALUE) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                float f4 = this.dampingRatio;
                double d2 = f4;
                double d3 = d2 * d2;
                if (f4 > 1.0f) {
                    double d4 = this.naturalFreq;
                    double d5 = d3 - 1;
                    this.gammaPlus = (Math.sqrt(d5) * d4) + ((-f4) * d4);
                    double d6 = this.naturalFreq;
                    this.gammaMinus = ((-this.dampingRatio) * d6) - (Math.sqrt(d5) * d6);
                } else if (f4 >= 0.0f && f4 < 1.0f) {
                    this.dampedFreq = Math.sqrt(1 - d3) * this.naturalFreq;
                }
                this.initialized = true;
            } else {
                throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
            }
        }
        float f5 = f - this.finalPosition;
        double d7 = j / 1000.0d;
        float f6 = this.dampingRatio;
        if (f6 > 1.0f) {
            double d8 = f5;
            double d9 = this.gammaMinus;
            double d10 = f2;
            double d11 = this.gammaPlus;
            double d12 = d8 - (((d9 * d8) - d10) / (d9 - d11));
            double d13 = ((d8 * d9) - d10) / (d9 - d11);
            d = (Math.exp(this.gammaPlus * d7) * d13) + (Math.exp(d9 * d7) * d12);
            double d14 = this.gammaMinus;
            double exp = Math.exp(d14 * d7) * d12 * d14;
            double d15 = this.gammaPlus;
            cos = (Math.exp(d15 * d7) * d13 * d15) + exp;
        } else {
            if (f6 == 1.0f) {
                z2 = true;
            }
            if (z2) {
                double d16 = this.naturalFreq;
                double d17 = f5;
                double d18 = (d16 * d17) + f2;
                double d19 = (d18 * d7) + d17;
                double exp2 = Math.exp((-d16) * d7) * d19;
                double exp3 = Math.exp((-this.naturalFreq) * d7) * d19;
                double d20 = this.naturalFreq;
                cos = (Math.exp((-d20) * d7) * d18) + (exp3 * (-d20));
                d = exp2;
            } else {
                double d21 = 1 / this.dampedFreq;
                double d22 = this.naturalFreq;
                double d23 = f5;
                double d24 = ((f6 * d22 * d23) + f2) * d21;
                double exp4 = Math.exp((-f6) * d22 * d7) * ((Math.sin(this.dampedFreq * d7) * d24) + (Math.cos(this.dampedFreq * d7) * d23));
                double d25 = this.naturalFreq;
                double d26 = (-d25) * exp4 * this.dampingRatio;
                double exp5 = Math.exp((-f3) * d25 * d7);
                double d27 = this.dampedFreq;
                double d28 = this.dampedFreq;
                cos = (((Math.cos(d28 * d7) * d24 * d28) + (Math.sin(d27 * d7) * (-d27) * d23)) * exp5) + d26;
                d = exp4;
            }
        }
        return (Float.floatToIntBits((float) cos) & 4294967295L) | (Float.floatToIntBits((float) (d + this.finalPosition)) << 32);
    }
}
