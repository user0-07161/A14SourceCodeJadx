package androidx.compose.ui.graphics.drawscope;

import androidx.compose.animation.core.AnimationVector4D$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.AndroidPathEffect;
import androidx.compose.ui.graphics.PathEffect;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Stroke extends DrawStyle {
    private final int cap;
    private final int join;
    private final float miter;
    private final PathEffect pathEffect;
    private final float width;

    public Stroke(float f, AndroidPathEffect androidPathEffect, int i) {
        f = (i & 1) != 0 ? 0.0f : f;
        float f2 = (i & 2) != 0 ? 4.0f : 0.0f;
        androidPathEffect = (i & 16) != 0 ? null : androidPathEffect;
        this.width = f;
        this.miter = f2;
        this.cap = 0;
        this.join = 0;
        this.pathEffect = androidPathEffect;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Stroke)) {
            return false;
        }
        Stroke stroke = (Stroke) obj;
        if (this.width == stroke.width) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        if (this.miter == stroke.miter) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            return false;
        }
        if (this.cap == stroke.cap) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z3) {
            return false;
        }
        if (this.join == stroke.join) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 && Intrinsics.areEqual(this.pathEffect, stroke.pathEffect)) {
            return true;
        }
        return false;
    }

    /* renamed from: getCap-KaPHkGw  reason: not valid java name */
    public final int m168getCapKaPHkGw() {
        return this.cap;
    }

    /* renamed from: getJoin-LxFBmk8  reason: not valid java name */
    public final int m169getJoinLxFBmk8() {
        return this.join;
    }

    public final float getMiter() {
        return this.miter;
    }

    public final PathEffect getPathEffect() {
        return this.pathEffect;
    }

    public final float getWidth() {
        return this.width;
    }

    public final int hashCode() {
        int i;
        int m = AnimationVector4D$$ExternalSyntheticOutline0.m(this.miter, Float.hashCode(this.width) * 31, 31);
        int hashCode = (Integer.hashCode(this.join) + ((Integer.hashCode(this.cap) + m) * 31)) * 31;
        PathEffect pathEffect = this.pathEffect;
        if (pathEffect != null) {
            i = pathEffect.hashCode();
        } else {
            i = 0;
        }
        return hashCode + i;
    }

    public final String toString() {
        boolean z;
        boolean z2;
        boolean z3;
        String str;
        boolean z4;
        boolean z5;
        StringBuilder sb = new StringBuilder("Stroke(width=");
        sb.append(this.width);
        sb.append(", miter=");
        sb.append(this.miter);
        sb.append(", cap=");
        boolean z6 = true;
        int i = this.cap;
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        String str2 = "Unknown";
        if (z) {
            str = "Butt";
        } else {
            if (i == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                str = "Round";
            } else {
                if (i == 2) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (!z3) {
                    str = "Unknown";
                } else {
                    str = "Square";
                }
            }
        }
        sb.append((Object) str);
        sb.append(", join=");
        int i2 = this.join;
        if (i2 == 0) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4) {
            str2 = "Miter";
        } else {
            if (i2 == 1) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (z5) {
                str2 = "Round";
            } else {
                if (i2 != 2) {
                    z6 = false;
                }
                if (z6) {
                    str2 = "Bevel";
                }
            }
        }
        sb.append((Object) str2);
        sb.append(", pathEffect=");
        sb.append(this.pathEffect);
        sb.append(')');
        return sb.toString();
    }
}
