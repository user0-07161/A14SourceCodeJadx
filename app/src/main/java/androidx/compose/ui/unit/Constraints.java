package androidx.compose.ui.unit;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Constraints {
    private final long value;
    private static final int[] MinHeightOffsets = {18, 20, 17, 15};
    private static final int[] WidthMask = {65535, 262143, 32767, 8191};
    private static final int[] HeightMask = {32767, 8191, 65535, 262143};

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class Companion {
        private static int bitsNeedForSize(int i) {
            if (i < 8191) {
                return 13;
            }
            if (i < 32767) {
                return 15;
            }
            if (i < 65535) {
                return 16;
            }
            if (i < 262143) {
                return 18;
            }
            throw new IllegalArgumentException("Can't represent a size of " + i + " in Constraints");
        }

        /* renamed from: createConstraints-Zbe2FdA$ui_unit_release  reason: not valid java name */
        public static long m386createConstraintsZbe2FdA$ui_unit_release(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            long j;
            int i7;
            if (i4 == Integer.MAX_VALUE) {
                i5 = i3;
            } else {
                i5 = i4;
            }
            int bitsNeedForSize = bitsNeedForSize(i5);
            if (i2 == Integer.MAX_VALUE) {
                i6 = i;
            } else {
                i6 = i2;
            }
            int bitsNeedForSize2 = bitsNeedForSize(i6);
            if (bitsNeedForSize + bitsNeedForSize2 <= 31) {
                if (bitsNeedForSize2 != 13) {
                    if (bitsNeedForSize2 != 18) {
                        if (bitsNeedForSize2 != 15) {
                            if (bitsNeedForSize2 == 16) {
                                j = 0;
                            } else {
                                throw new IllegalStateException("Should only have the provided constants.");
                            }
                        } else {
                            j = 2;
                        }
                    } else {
                        j = 1;
                    }
                } else {
                    j = 3;
                }
                int i8 = 0;
                if (i2 == Integer.MAX_VALUE) {
                    i7 = 0;
                } else {
                    i7 = i2 + 1;
                }
                if (i4 != Integer.MAX_VALUE) {
                    i8 = i4 + 1;
                }
                int i9 = Constraints.MinHeightOffsets[(int) j];
                return (i7 << 33) | j | (i << 2) | (i3 << i9) | (i8 << (i9 + 31));
            }
            throw new IllegalArgumentException("Can't represent a width of " + i6 + " and height of " + i5 + " in Constraints");
        }
    }

    private /* synthetic */ Constraints(long j) {
        this.value = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Constraints m376boximpl(long j) {
        return new Constraints(j);
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m377equalsimpl0(long j, long j2) {
        if (j == j2) {
            return true;
        }
        return false;
    }

    /* renamed from: getHasBoundedHeight-impl  reason: not valid java name */
    public static final boolean m378getHasBoundedHeightimpl(long j) {
        int i = (int) (3 & j);
        if ((((int) (j >> (MinHeightOffsets[i] + 31))) & HeightMask[i]) != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: getHasBoundedWidth-impl  reason: not valid java name */
    public static final boolean m379getHasBoundedWidthimpl(long j) {
        if ((((int) (j >> 33)) & WidthMask[(int) (3 & j)]) != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: getMaxHeight-impl  reason: not valid java name */
    public static final int m380getMaxHeightimpl(long j) {
        int i = (int) (3 & j);
        int i2 = ((int) (j >> (MinHeightOffsets[i] + 31))) & HeightMask[i];
        if (i2 == 0) {
            return Integer.MAX_VALUE;
        }
        return i2 - 1;
    }

    /* renamed from: getMaxWidth-impl  reason: not valid java name */
    public static final int m381getMaxWidthimpl(long j) {
        int i = ((int) (j >> 33)) & WidthMask[(int) (3 & j)];
        if (i == 0) {
            return Integer.MAX_VALUE;
        }
        return i - 1;
    }

    /* renamed from: getMinHeight-impl  reason: not valid java name */
    public static final int m382getMinHeightimpl(long j) {
        int i = (int) (3 & j);
        return ((int) (j >> MinHeightOffsets[i])) & HeightMask[i];
    }

    /* renamed from: getMinWidth-impl  reason: not valid java name */
    public static final int m383getMinWidthimpl(long j) {
        return ((int) (j >> 2)) & WidthMask[(int) (3 & j)];
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m384toStringimpl(long j) {
        String valueOf;
        int m381getMaxWidthimpl = m381getMaxWidthimpl(j);
        String str = "Infinity";
        if (m381getMaxWidthimpl == Integer.MAX_VALUE) {
            valueOf = "Infinity";
        } else {
            valueOf = String.valueOf(m381getMaxWidthimpl);
        }
        int m380getMaxHeightimpl = m380getMaxHeightimpl(j);
        if (m380getMaxHeightimpl != Integer.MAX_VALUE) {
            str = String.valueOf(m380getMaxHeightimpl);
        }
        return "Constraints(minWidth = " + m383getMinWidthimpl(j) + ", maxWidth = " + valueOf + ", minHeight = " + m382getMinHeightimpl(j) + ", maxHeight = " + str + ')';
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Constraints)) {
            return false;
        }
        if (this.value != ((Constraints) obj).value) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.value);
    }

    public final String toString() {
        return m384toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m385unboximpl() {
        return this.value;
    }
}
