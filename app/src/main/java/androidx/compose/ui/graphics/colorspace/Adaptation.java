package androidx.compose.ui.graphics.colorspace;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Adaptation {
    private static final Adaptation$Companion$Bradford$1 Bradford = new Adaptation$Companion$Bradford$1(new float[]{0.8951f, -0.7502f, 0.0389f, 0.2664f, 1.7135f, -0.0685f, -0.1614f, 0.0367f, 1.0296f});
    private final float[] transform;

    public Adaptation(float[] fArr) {
        this.transform = fArr;
    }

    public final float[] getTransform$ui_graphics_release() {
        return this.transform;
    }
}
