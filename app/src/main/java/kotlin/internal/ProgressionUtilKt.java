package kotlin.internal;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ProgressionUtilKt {
    public static final int getProgressionLastElement(int i, int i2, int i3) {
        if (i3 > 0) {
            if (i >= i2) {
                return i2;
            }
            int i4 = i2 % i3;
            if (i4 < 0) {
                i4 += i3;
            }
            int i5 = i % i3;
            if (i5 < 0) {
                i5 += i3;
            }
            int i6 = (i4 - i5) % i3;
            if (i6 < 0) {
                i6 += i3;
            }
            return i2 - i6;
        } else if (i3 < 0) {
            if (i <= i2) {
                return i2;
            }
            int i7 = -i3;
            int i8 = i % i7;
            if (i8 < 0) {
                i8 += i7;
            }
            int i9 = i2 % i7;
            if (i9 < 0) {
                i9 += i7;
            }
            int i10 = (i8 - i9) % i7;
            if (i10 < 0) {
                i10 += i7;
            }
            return i2 + i10;
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    public static final long getProgressionLastElement(long j, long j2) {
        if (j >= j2) {
            return j2;
        }
        long j3 = j2 % 1;
        if (j3 < 0) {
            j3++;
        }
        long j4 = j % 1;
        if (j4 < 0) {
            j4++;
        }
        long j5 = (j3 - j4) % 1;
        if (j5 < 0) {
            j5++;
        }
        return j2 - j5;
    }
}
