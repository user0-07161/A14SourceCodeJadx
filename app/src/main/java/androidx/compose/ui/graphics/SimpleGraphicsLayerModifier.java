package androidx.compose.ui.graphics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class SimpleGraphicsLayerModifier extends Modifier.Node implements LayoutModifierNode {
    private float alpha;
    private long ambientShadowColor;
    private float cameraDistance;
    private boolean clip;
    private int compositingStrategy;
    private Function1 layerBlock = new Function1() { // from class: androidx.compose.ui.graphics.SimpleGraphicsLayerModifier$layerBlock$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            ReusableGraphicsLayerScope reusableGraphicsLayerScope = (ReusableGraphicsLayerScope) obj;
            Intrinsics.checkNotNullParameter(reusableGraphicsLayerScope, "$this$null");
            reusableGraphicsLayerScope.setScaleX(SimpleGraphicsLayerModifier.this.getScaleX());
            reusableGraphicsLayerScope.setScaleY(SimpleGraphicsLayerModifier.this.getScaleY());
            reusableGraphicsLayerScope.setAlpha(SimpleGraphicsLayerModifier.this.getAlpha());
            reusableGraphicsLayerScope.setTranslationX(SimpleGraphicsLayerModifier.this.getTranslationX());
            reusableGraphicsLayerScope.setTranslationY(SimpleGraphicsLayerModifier.this.getTranslationY());
            reusableGraphicsLayerScope.setShadowElevation(SimpleGraphicsLayerModifier.this.getShadowElevation());
            reusableGraphicsLayerScope.setRotationX(SimpleGraphicsLayerModifier.this.getRotationX());
            reusableGraphicsLayerScope.setRotationY(SimpleGraphicsLayerModifier.this.getRotationY());
            reusableGraphicsLayerScope.setRotationZ(SimpleGraphicsLayerModifier.this.getRotationZ());
            reusableGraphicsLayerScope.setCameraDistance(SimpleGraphicsLayerModifier.this.getCameraDistance());
            reusableGraphicsLayerScope.m114setTransformOrigin__ExYCQ(SimpleGraphicsLayerModifier.this.m120getTransformOriginSzJe1aQ());
            reusableGraphicsLayerScope.setShape(SimpleGraphicsLayerModifier.this.getShape());
            reusableGraphicsLayerScope.setClip(SimpleGraphicsLayerModifier.this.getClip());
            SimpleGraphicsLayerModifier.this.getClass();
            reusableGraphicsLayerScope.m111setAmbientShadowColor8_81llA(SimpleGraphicsLayerModifier.this.m117getAmbientShadowColor0d7_KjU());
            reusableGraphicsLayerScope.m113setSpotShadowColor8_81llA(SimpleGraphicsLayerModifier.this.m119getSpotShadowColor0d7_KjU());
            reusableGraphicsLayerScope.m112setCompositingStrategyaDBOjCE(SimpleGraphicsLayerModifier.this.m118getCompositingStrategyNrFUSI());
            return Unit.INSTANCE;
        }
    };
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float scaleX;
    private float scaleY;
    private float shadowElevation;
    private Shape shape;
    private long spotShadowColor;
    private long transformOrigin;
    private float translationX;
    private float translationY;

    public SimpleGraphicsLayerModifier(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j, Shape shape, boolean z, long j2, long j3, int i) {
        this.scaleX = f;
        this.scaleY = f2;
        this.alpha = f3;
        this.translationX = f4;
        this.translationY = f5;
        this.shadowElevation = f6;
        this.rotationX = f7;
        this.rotationY = f8;
        this.rotationZ = f9;
        this.cameraDistance = f10;
        this.transformOrigin = j;
        this.shape = shape;
        this.clip = z;
        this.ambientShadowColor = j2;
        this.spotShadowColor = j3;
        this.compositingStrategy = i;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    /* renamed from: getAmbientShadowColor-0d7_KjU  reason: not valid java name */
    public final long m117getAmbientShadowColor0d7_KjU() {
        return this.ambientShadowColor;
    }

    public final float getCameraDistance() {
        return this.cameraDistance;
    }

    public final boolean getClip() {
        return this.clip;
    }

    /* renamed from: getCompositingStrategy--NrFUSI  reason: not valid java name */
    public final int m118getCompositingStrategyNrFUSI() {
        return this.compositingStrategy;
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
    public final long m119getSpotShadowColor0d7_KjU() {
        return this.spotShadowColor;
    }

    /* renamed from: getTransformOrigin-SzJe1aQ  reason: not valid java name */
    public final long m120getTransformOriginSzJe1aQ() {
        return this.transformOrigin;
    }

    public final float getTranslationX() {
        return this.translationX;
    }

    public final float getTranslationY() {
        return this.translationY;
    }

    public final void invalidateLayerBlock() {
        NodeCoordinator wrapped$ui_release = DelegatableNodeKt.m224requireCoordinator64DMado(this, 2).getWrapped$ui_release();
        if (wrapped$ui_release != null) {
            wrapped$ui_release.updateLayerBlock(this.layerBlock);
        }
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo88measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        MeasureScope$layout$1 layout;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        final Placeable mo210measureBRTryo0 = measurable.mo210measureBRTryo0(j);
        layout = measure.layout(mo210measureBRTryo0.getWidth(), mo210measureBRTryo0.getHeight(), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.graphics.SimpleGraphicsLayerModifier$measure$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Function1 function1;
                Placeable.PlacementScope layout2 = (Placeable.PlacementScope) obj;
                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                Placeable placeable = Placeable.this;
                function1 = this.layerBlock;
                Placeable.PlacementScope.placeWithLayer$default(layout2, placeable, function1);
                return Unit.INSTANCE;
            }
        });
        return layout;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    /* renamed from: setAmbientShadowColor-8_81llA  reason: not valid java name */
    public final void m121setAmbientShadowColor8_81llA(long j) {
        this.ambientShadowColor = j;
    }

    public final void setCameraDistance(float f) {
        this.cameraDistance = f;
    }

    public final void setClip(boolean z) {
        this.clip = z;
    }

    /* renamed from: setCompositingStrategy-aDBOjCE  reason: not valid java name */
    public final void m122setCompositingStrategyaDBOjCE(int i) {
        this.compositingStrategy = i;
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
    public final void m123setSpotShadowColor8_81llA(long j) {
        this.spotShadowColor = j;
    }

    /* renamed from: setTransformOrigin-__ExYCQ  reason: not valid java name */
    public final void m124setTransformOrigin__ExYCQ(long j) {
        this.transformOrigin = j;
    }

    public final void setTranslationX(float f) {
        this.translationX = f;
    }

    public final void setTranslationY(float f) {
        this.translationY = f;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SimpleGraphicsLayerModifier(scaleX=");
        sb.append(this.scaleX);
        sb.append(", scaleY=");
        sb.append(this.scaleY);
        sb.append(", alpha = ");
        sb.append(this.alpha);
        sb.append(", translationX=");
        sb.append(this.translationX);
        sb.append(", translationY=");
        sb.append(this.translationY);
        sb.append(", shadowElevation=");
        sb.append(this.shadowElevation);
        sb.append(", rotationX=");
        sb.append(this.rotationX);
        sb.append(", rotationY=");
        sb.append(this.rotationY);
        sb.append(", rotationZ=");
        sb.append(this.rotationZ);
        sb.append(", cameraDistance=");
        sb.append(this.cameraDistance);
        sb.append(", transformOrigin=");
        long j = this.transformOrigin;
        int i = TransformOrigin.$r8$clinit;
        sb.append((Object) ("TransformOrigin(packedValue=" + j + ')'));
        sb.append(", shape=");
        sb.append(this.shape);
        sb.append(", clip=");
        sb.append(this.clip);
        sb.append(", renderEffect=null, ambientShadowColor=");
        sb.append((Object) Color.m98toStringimpl(this.ambientShadowColor));
        sb.append(", spotShadowColor=");
        sb.append((Object) Color.m98toStringimpl(this.spotShadowColor));
        sb.append(", compositingStrategy=");
        int i2 = this.compositingStrategy;
        sb.append((Object) ("CompositingStrategy(value=" + i2 + ')'));
        sb.append(')');
        return sb.toString();
    }
}
