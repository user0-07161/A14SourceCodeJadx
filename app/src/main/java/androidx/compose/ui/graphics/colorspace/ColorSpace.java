package androidx.compose.ui.graphics.colorspace;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ColorSpace {
    private final int id;
    private final long model;
    private final String name;

    public ColorSpace(String str, long j, int i) {
        boolean z;
        this.name = str;
        this.model = j;
        this.id = i;
        if (str.length() == 0) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            if (i >= -1 && i <= 63) {
                return;
            }
            throw new IllegalArgumentException("The id must be between -1 and 63");
        }
        throw new IllegalArgumentException("The name of a color space cannot be null and must contain at least 1 character");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorSpace colorSpace = (ColorSpace) obj;
        if (this.id != colorSpace.id || !Intrinsics.areEqual(this.name, colorSpace.name)) {
            return false;
        }
        return ColorModel.m128equalsimpl0(this.model, colorSpace.model);
    }

    public abstract float[] fromXyz(float[] fArr);

    public final int getComponentCount() {
        int i = ColorModel.$r8$clinit;
        return (int) (this.model >> 32);
    }

    public final int getId$ui_graphics_release() {
        return this.id;
    }

    public abstract float getMaxValue(int i);

    public abstract float getMinValue(int i);

    /* renamed from: getModel-xdoWZVw  reason: not valid java name */
    public final long m130getModelxdoWZVw() {
        return this.model;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int i = ColorModel.$r8$clinit;
        return ((Long.hashCode(this.model) + (this.name.hashCode() * 31)) * 31) + this.id;
    }

    public boolean isSrgb() {
        return false;
    }

    public final String toString() {
        return this.name + " (id=" + this.id + ", model=" + ((Object) ColorModel.m129toStringimpl(this.model)) + ')';
    }

    public abstract float[] toXyz(float[] fArr);
}
