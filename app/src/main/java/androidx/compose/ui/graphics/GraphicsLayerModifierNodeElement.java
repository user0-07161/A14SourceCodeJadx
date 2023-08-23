package androidx.compose.ui.graphics;

import androidx.compose.animation.core.AnimationVector4D$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class GraphicsLayerModifierNodeElement extends ModifierNodeElement {
    private final float alpha;
    private final long ambientShadowColor;
    private final float cameraDistance;
    private final boolean clip;
    private final int compositingStrategy;
    private final float rotationX;
    private final float rotationY;
    private final float rotationZ;
    private final float scaleX;
    private final float scaleY;
    private final float shadowElevation;
    private final Shape shape;
    private final long spotShadowColor;
    private final long transformOrigin;
    private final float translationX;
    private final float translationY;

    public GraphicsLayerModifierNodeElement(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j, Shape shape, boolean z, long j2, long j3, int i) {
        super(null, true, InspectableValueKt.getNoInspectorInfo());
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

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new SimpleGraphicsLayerModifier(this.scaleX, this.scaleY, this.alpha, this.translationX, this.translationY, this.shadowElevation, this.rotationX, this.rotationY, this.rotationZ, this.cameraDistance, this.transformOrigin, this.shape, this.clip, this.ambientShadowColor, this.spotShadowColor, this.compositingStrategy);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ModifierNodeElement) || !(obj instanceof GraphicsLayerModifierNodeElement)) {
            return false;
        }
        GraphicsLayerModifierNodeElement graphicsLayerModifierNodeElement = (GraphicsLayerModifierNodeElement) obj;
        if (this.scaleX == graphicsLayerModifierNodeElement.scaleX) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (this.scaleY == graphicsLayerModifierNodeElement.scaleY) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (this.alpha == graphicsLayerModifierNodeElement.alpha) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    if (this.translationX == graphicsLayerModifierNodeElement.translationX) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        if (this.translationY == graphicsLayerModifierNodeElement.translationY) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (z5) {
                            if (this.shadowElevation == graphicsLayerModifierNodeElement.shadowElevation) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            if (z6) {
                                if (this.rotationX == graphicsLayerModifierNodeElement.rotationX) {
                                    z7 = true;
                                } else {
                                    z7 = false;
                                }
                                if (z7) {
                                    if (this.rotationY == graphicsLayerModifierNodeElement.rotationY) {
                                        z8 = true;
                                    } else {
                                        z8 = false;
                                    }
                                    if (z8) {
                                        if (this.rotationZ == graphicsLayerModifierNodeElement.rotationZ) {
                                            z9 = true;
                                        } else {
                                            z9 = false;
                                        }
                                        if (z9) {
                                            if (this.cameraDistance == graphicsLayerModifierNodeElement.cameraDistance) {
                                                z10 = true;
                                            } else {
                                                z10 = false;
                                            }
                                            if (z10) {
                                                int i = TransformOrigin.$r8$clinit;
                                                if (this.transformOrigin == graphicsLayerModifierNodeElement.transformOrigin) {
                                                    z11 = true;
                                                } else {
                                                    z11 = false;
                                                }
                                                if (z11 && Intrinsics.areEqual(this.shape, graphicsLayerModifierNodeElement.shape) && this.clip == graphicsLayerModifierNodeElement.clip && Intrinsics.areEqual(null, null) && Color.m93equalsimpl0(this.ambientShadowColor, graphicsLayerModifierNodeElement.ambientShadowColor) && Color.m93equalsimpl0(this.spotShadowColor, graphicsLayerModifierNodeElement.spotShadowColor)) {
                                                    if (this.compositingStrategy == graphicsLayerModifierNodeElement.compositingStrategy) {
                                                        z12 = true;
                                                    } else {
                                                        z12 = false;
                                                    }
                                                    if (z12) {
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final int hashCode() {
        int m = AnimationVector4D$$ExternalSyntheticOutline0.m(this.cameraDistance, AnimationVector4D$$ExternalSyntheticOutline0.m(this.rotationZ, AnimationVector4D$$ExternalSyntheticOutline0.m(this.rotationY, AnimationVector4D$$ExternalSyntheticOutline0.m(this.rotationX, AnimationVector4D$$ExternalSyntheticOutline0.m(this.shadowElevation, AnimationVector4D$$ExternalSyntheticOutline0.m(this.translationY, AnimationVector4D$$ExternalSyntheticOutline0.m(this.translationX, AnimationVector4D$$ExternalSyntheticOutline0.m(this.alpha, AnimationVector4D$$ExternalSyntheticOutline0.m(this.scaleY, Float.hashCode(this.scaleX) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31);
        int i = TransformOrigin.$r8$clinit;
        int hashCode = this.shape.hashCode();
        int hashCode2 = Boolean.hashCode(this.clip);
        Color.Companion companion = Color.Companion;
        int hashCode3 = Long.hashCode(this.ambientShadowColor);
        int hashCode4 = Long.hashCode(this.spotShadowColor);
        return Integer.hashCode(this.compositingStrategy) + ((hashCode4 + ((hashCode3 + ((((hashCode2 + ((hashCode + ((Long.hashCode(this.transformOrigin) + m) * 31)) * 31)) * 31) + 0) * 31)) * 31)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node update(Modifier.Node node) {
        SimpleGraphicsLayerModifier node2 = (SimpleGraphicsLayerModifier) node;
        Intrinsics.checkNotNullParameter(node2, "node");
        node2.setScaleX(this.scaleX);
        node2.setScaleY(this.scaleY);
        node2.setAlpha(this.alpha);
        node2.setTranslationX(this.translationX);
        node2.setTranslationY(this.translationY);
        node2.setShadowElevation(this.shadowElevation);
        node2.setRotationX(this.rotationX);
        node2.setRotationY(this.rotationY);
        node2.setRotationZ(this.rotationZ);
        node2.setCameraDistance(this.cameraDistance);
        node2.m124setTransformOrigin__ExYCQ(this.transformOrigin);
        node2.setShape(this.shape);
        node2.setClip(this.clip);
        node2.m121setAmbientShadowColor8_81llA(this.ambientShadowColor);
        node2.m123setSpotShadowColor8_81llA(this.spotShadowColor);
        node2.m122setCompositingStrategyaDBOjCE(this.compositingStrategy);
        node2.invalidateLayerBlock();
        return node2;
    }
}
