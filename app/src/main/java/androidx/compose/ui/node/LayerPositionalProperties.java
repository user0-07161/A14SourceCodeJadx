package androidx.compose.ui.node;

import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LayerPositionalProperties {
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private long transformOrigin;
    private float translationX;
    private float translationY;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float cameraDistance = 8.0f;

    public LayerPositionalProperties() {
        long j;
        int i = TransformOrigin.$r8$clinit;
        j = TransformOrigin.Center;
        this.transformOrigin = j;
    }

    public final void copyFrom(LayerPositionalProperties layerPositionalProperties) {
        this.scaleX = layerPositionalProperties.scaleX;
        this.scaleY = layerPositionalProperties.scaleY;
        this.translationX = layerPositionalProperties.translationX;
        this.translationY = layerPositionalProperties.translationY;
        this.rotationX = layerPositionalProperties.rotationX;
        this.rotationY = layerPositionalProperties.rotationY;
        this.rotationZ = layerPositionalProperties.rotationZ;
        this.cameraDistance = layerPositionalProperties.cameraDistance;
        this.transformOrigin = layerPositionalProperties.transformOrigin;
    }

    public final boolean hasSameValuesAs(LayerPositionalProperties layerPositionalProperties) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        if (this.scaleX == layerPositionalProperties.scaleX) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (this.scaleY == layerPositionalProperties.scaleY) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (this.translationX == layerPositionalProperties.translationX) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    if (this.translationY == layerPositionalProperties.translationY) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        if (this.rotationX == layerPositionalProperties.rotationX) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (z5) {
                            if (this.rotationY == layerPositionalProperties.rotationY) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            if (z6) {
                                if (this.rotationZ == layerPositionalProperties.rotationZ) {
                                    z7 = true;
                                } else {
                                    z7 = false;
                                }
                                if (z7) {
                                    if (this.cameraDistance == layerPositionalProperties.cameraDistance) {
                                        z8 = true;
                                    } else {
                                        z8 = false;
                                    }
                                    if (z8) {
                                        long j = this.transformOrigin;
                                        long j2 = layerPositionalProperties.transformOrigin;
                                        int i = TransformOrigin.$r8$clinit;
                                        if (j == j2) {
                                            z9 = true;
                                        } else {
                                            z9 = false;
                                        }
                                        if (z9) {
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
        return false;
    }

    public final void copyFrom(ReusableGraphicsLayerScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.scaleX = scope.getScaleX();
        this.scaleY = scope.getScaleY();
        this.translationX = scope.getTranslationX();
        this.translationY = scope.getTranslationY();
        this.rotationX = scope.getRotationX();
        this.rotationY = scope.getRotationY();
        this.rotationZ = scope.getRotationZ();
        this.cameraDistance = scope.getCameraDistance();
        this.transformOrigin = scope.m110getTransformOriginSzJe1aQ();
    }
}
