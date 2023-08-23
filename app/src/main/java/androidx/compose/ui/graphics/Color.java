package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import kotlin.UnsignedKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Color {
    private static final long Blue;
    private static final long Gray;
    private static final long Green;
    private static final long Red;
    private static final long Transparent;
    private static final long Unspecified;
    private static final long White;
    private final long value;
    public static final Companion Companion = new Companion();
    private static final long Black = ColorKt.Color(4278190080L);

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    static {
        ColorKt.Color(4282664004L);
        Gray = ColorKt.Color(4287137928L);
        ColorKt.Color(4291611852L);
        White = ColorKt.Color(4294967295L);
        Red = ColorKt.Color(4294901760L);
        Green = ColorKt.Color(4278255360L);
        Blue = ColorKt.Color(4278190335L);
        ColorKt.Color(4294967040L);
        ColorKt.Color(4278255615L);
        ColorKt.Color(4294902015L);
        Transparent = 0 << 32;
        Unspecified = ColorKt.Color(0.0f, 0.0f, 0.0f, 0.0f, ColorSpaces.getUnspecified$ui_graphics_release());
    }

    private /* synthetic */ Color(long j) {
        this.value = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Color m91boximpl(long j) {
        return new Color(j);
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m93equalsimpl0(long j, long j2) {
        if (j == j2) {
            return true;
        }
        return false;
    }

    /* renamed from: getAlpha-impl  reason: not valid java name */
    public static final float m94getAlphaimpl(long j) {
        float ulongToDouble;
        float f;
        if ((63 & j) == 0) {
            ulongToDouble = (float) UnsignedKt.ulongToDouble((j >>> 56) & 255);
            f = 255.0f;
        } else {
            ulongToDouble = (float) UnsignedKt.ulongToDouble((j >>> 6) & 1023);
            f = 1023.0f;
        }
        return ulongToDouble / f;
    }

    /* renamed from: getBlue-impl  reason: not valid java name */
    public static final float m95getBlueimpl(long j) {
        if ((63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble((j >>> 32) & 255)) / 255.0f;
        }
        return Float16.m102toFloatimpl((short) ((j >>> 16) & 65535));
    }

    /* renamed from: getGreen-impl  reason: not valid java name */
    public static final float m96getGreenimpl(long j) {
        if ((63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble((j >>> 40) & 255)) / 255.0f;
        }
        return Float16.m102toFloatimpl((short) ((j >>> 32) & 65535));
    }

    /* renamed from: getRed-impl  reason: not valid java name */
    public static final float m97getRedimpl(long j) {
        if ((63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble((j >>> 48) & 255)) / 255.0f;
        }
        return Float16.m102toFloatimpl((short) ((j >>> 48) & 65535));
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m98toStringimpl(long j) {
        return "Color(" + m97getRedimpl(j) + ", " + m96getGreenimpl(j) + ", " + m95getBlueimpl(j) + ", " + m94getAlphaimpl(j) + ", " + ColorSpaces.getColorSpacesArray$ui_graphics_release()[(int) (j & 63)].getName() + ')';
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Color)) {
            return false;
        }
        if (this.value != ((Color) obj).value) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.value);
    }

    public final String toString() {
        return m98toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m99unboximpl() {
        return this.value;
    }
}
