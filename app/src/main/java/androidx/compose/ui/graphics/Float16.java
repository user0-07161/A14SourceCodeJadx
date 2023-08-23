package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Float16 implements Comparable {
    public static final Companion Companion = new Companion();
    private static final float FP32_DENORMAL_FLOAT;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    static {
        m101constructorimpl(1.0f);
        m101constructorimpl(-1.0f);
        FP32_DENORMAL_FLOAT = Float.intBitsToFloat(1056964608);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static short m101constructorimpl(float f) {
        int i;
        int i2;
        Companion.getClass();
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        int i3 = floatToRawIntBits >>> 31;
        int i4 = (floatToRawIntBits >>> 23) & 255;
        int i5 = floatToRawIntBits & 8388607;
        int i6 = 31;
        int i7 = 0;
        if (i4 == 255) {
            if (i5 != 0) {
                i2 = 512;
                i7 = i2;
            }
            i = (i3 << 15) | (i6 << 10) | i7;
        } else {
            int i8 = (i4 - 127) + 15;
            if (i8 >= 31) {
                i6 = 49;
            } else if (i8 <= 0) {
                if (i8 >= -10) {
                    int i9 = (i5 | 8388608) >> (1 - i8);
                    if ((i9 & 4096) != 0) {
                        i9 += 8192;
                    }
                    i2 = i9 >> 13;
                    i6 = 0;
                    i7 = i2;
                } else {
                    i6 = 0;
                }
            } else {
                i7 = i5 >> 13;
                if ((i5 & 4096) != 0) {
                    i = (((i8 << 10) | i7) + 1) | (i3 << 15);
                } else {
                    i6 = i8;
                }
            }
            i = (i3 << 15) | (i6 << 10) | i7;
        }
        return (short) i;
    }

    /* renamed from: toFloat-impl  reason: not valid java name */
    public static final float m102toFloatimpl(short s) {
        int i;
        int i2;
        int i3;
        int i4 = s & 65535;
        int i5 = 32768 & i4;
        int i6 = (i4 >>> 10) & 31;
        int i7 = i4 & 1023;
        if (i6 == 0) {
            if (i7 != 0) {
                float intBitsToFloat = Float.intBitsToFloat(i7 + 1056964608) - FP32_DENORMAL_FLOAT;
                if (i5 != 0) {
                    return -intBitsToFloat;
                }
                return intBitsToFloat;
            }
            i3 = 0;
            i2 = 0;
        } else {
            int i8 = i7 << 13;
            if (i6 == 31) {
                i = 255;
                if (i8 != 0) {
                    i8 |= 4194304;
                }
            } else {
                i = (i6 - 15) + 127;
            }
            int i9 = i;
            i2 = i8;
            i3 = i9;
        }
        return Float.intBitsToFloat((i3 << 23) | (i5 << 16) | i2);
    }
}
