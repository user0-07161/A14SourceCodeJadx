package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ReusableGraphicsLayerScope implements Density {
    private boolean clip;
    private int compositingStrategy;
    private Density graphicsDensity;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float shadowElevation;
    private Shape shape;
    private long transformOrigin;
    private float translationX;
    private float translationY;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float alpha = 1.0f;
    private long ambientShadowColor = GraphicsLayerScopeKt.getDefaultShadowColor();
    private long spotShadowColor = GraphicsLayerScopeKt.getDefaultShadowColor();
    private float cameraDistance = 8.0f;

    public ReusableGraphicsLayerScope() {
        long j;
        j = TransformOrigin.Center;
        this.transformOrigin = j;
        this.shape = RectangleShapeKt.getRectangleShape();
        this.compositingStrategy = 0;
        Size.Companion companion = Size.Companion;
        this.graphicsDensity = DensityKt.Density$default();
    }

    public final float getAlpha() {
        return this.alpha;
    }

    /* renamed from: getAmbientShadowColor-0d7_KjU  reason: not valid java name */
    public final long m107getAmbientShadowColor0d7_KjU() {
        return this.ambientShadowColor;
    }

    public final float getCameraDistance() {
        return this.cameraDistance;
    }

    public final boolean getClip() {
        return this.clip;
    }

    /* renamed from: getCompositingStrategy--NrFUSI  reason: not valid java name */
    public final int m108getCompositingStrategyNrFUSI() {
        return this.compositingStrategy;
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.graphicsDensity.getDensity();
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getFontScale() {
        return this.graphicsDensity.getFontScale();
    }

    public final float getRotationX() {
        return this.rotationX;
    }

    public final float getRotationY() {
        return this.rotationY;
    }

    public final float getRotationZ() {
        return this.rotationZ;
    }

    public final float getScaleX() {
        return this.scaleX;
    }

    public final float getScaleY() {
        return this.scaleY;
    }

    public final float getShadowElevation() {
        return this.shadowElevation;
    }

    public final Shape getShape() {
        return this.shape;
    }

    /* renamed from: getSpotShadowColor-0d7_KjU  reason: not valid java name */
    public final long m109getSpotShadowColor0d7_KjU() {
        return this.spotShadowColor;
    }

    /* renamed from: getTransformOrigin-SzJe1aQ  reason: not valid java name */
    public final long m110getTransformOriginSzJe1aQ() {
        return this.transformOrigin;
    }

    public final float getTranslationX() {
        return this.translationX;
    }

    public final float getTranslationY() {
        return this.translationY;
    }

    public final void reset() {
        long j;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.alpha = 1.0f;
        this.translationX = 0.0f;
        this.translationY = 0.0f;
        this.shadowElevation = 0.0f;
        this.ambientShadowColor = GraphicsLayerScopeKt.getDefaultShadowColor();
        this.spotShadowColor = GraphicsLayerScopeKt.getDefaultShadowColor();
        this.rotationX = 0.0f;
        this.rotationY = 0.0f;
        this.rotationZ = 0.0f;
        this.cameraDistance = 8.0f;
        j = TransformOrigin.Center;
        this.transformOrigin = j;
        this.shape = RectangleShapeKt.getRectangleShape();
        this.clip = false;
        this.compositingStrategy = 0;
        Size.Companion companion = Size.Companion;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    /* renamed from: setAmbientShadowColor-8_81llA  reason: not valid java name */
    public final void m111setAmbientShadowColor8_81llA(long j) {
        this.ambientShadowColor = j;
    }

    public final void setCameraDistance(float f) {
        this.cameraDistance = f;
    }

    public final void setClip(boolean z) {
        this.clip = z;
    }

    /* renamed from: setCompositingStrategy-aDBOjCE  reason: not valid java name */
    public final void m112setCompositingStrategyaDBOjCE(int i) {
        this.compositingStrategy = i;
    }

    public final void setGraphicsDensity$ui_release(Density density) {
        Intrinsics.checkNotNullParameter(density, "<set-?>");
        this.graphicsDensity = density;
    }

    public final void setRotationX(float f) {
        this.rotationX = f;
    }

    public final void setRotationY(float f) {
        this.rotationY = f;
    }

    public final void setRotationZ(float f) {
        this.rotationZ = f;
    }

    public final void setScaleX(float f) {
        this.scaleX = f;
    }

    public final void setScaleY(float f) {
        this.scaleY = f;
    }

    public final void setShadowElevation(float f) {
        this.shadowElevation = f;
    }

    public final void setShape(Shape shape) {
        Intrinsics.checkNotNullParameter(shape, "<set-?>");
        this.shape = shape;
    }

    /* renamed from: setSpotShadowColor-8_81llA  reason: not valid java name */
    public final void m113setSpotShadowColor8_81llA(long j) {
        this.spotShadowColor = j;
    }

    /* renamed from: setTransformOrigin-__ExYCQ  reason: not valid java name */
    public final void m114setTransformOrigin__ExYCQ(long j) {
        this.transformOrigin = j;
    }

    public final void setTranslationX(float f) {
        this.translationX = f;
    }

    public final void setTranslationY(float f) {
        this.translationY = f;
    }
}
