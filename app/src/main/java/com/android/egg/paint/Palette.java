package com.android.egg.paint;

import android.graphics.Color;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Palette {
    public static final int $stable = 8;
    private int[] colors;
    private int darkest;
    private int lightest;

    public Palette(int i) {
        this.colors = new int[i];
        randomize(1.0f, 1.0f);
    }

    private final float lum(int i) {
        return ((Color.blue(i) * 114.0f) + ((Color.green(i) * 587.0f) + (Color.red(i) * 299.0f))) / 1000.0f;
    }

    public final int[] getColors() {
        return this.colors;
    }

    public final int getDarkest() {
        return this.darkest;
    }

    public final int getLightest() {
        return this.lightest;
    }

    public final void randomize(float f, float f2) {
        float[] fArr = {(float) (Math.random() * 360.0f), f, f2};
        int[] iArr = this.colors;
        int length = iArr.length;
        iArr[0] = Color.HSVToColor(fArr);
        this.lightest = 0;
        this.darkest = 0;
        for (int i = 0; i < length; i++) {
            fArr[0] = ((360.0f / length) + fArr[0]) % 360.0f;
            int HSVToColor = Color.HSVToColor(fArr);
            this.colors[i] = HSVToColor;
            float lum = lum(HSVToColor);
            if (lum < lum(this.colors[this.darkest])) {
                this.darkest = i;
            }
            if (lum > lum(this.colors[this.lightest])) {
                this.lightest = i;
            }
        }
    }

    public final void setColors(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.colors = iArr;
    }

    public final void setDarkest(int i) {
        this.darkest = i;
    }

    public final void setLightest(int i) {
        this.lightest = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Palette{ ");
        for (int i : this.colors) {
            String format = String.format("#%08x ", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            sb.append(format);
        }
        sb.append("}");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "str.toString()");
        return sb2;
    }

    public Palette(int i, float f, float f2) {
        this.colors = new int[i];
        randomize(f, f2);
    }

    public Palette(int[] _colors) {
        Intrinsics.checkNotNullParameter(_colors, "_colors");
        this.colors = _colors;
        int length = _colors.length;
        for (int i = 0; i < length; i++) {
            float lum = lum(this.colors[i]);
            if (lum < lum(this.colors[this.darkest])) {
                this.darkest = i;
            }
            if (lum > lum(this.colors[this.lightest])) {
                this.lightest = i;
            }
        }
    }
}
