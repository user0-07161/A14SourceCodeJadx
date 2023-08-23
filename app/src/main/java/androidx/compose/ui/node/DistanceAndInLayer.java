package androidx.compose.ui.node;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
abstract class DistanceAndInLayer {
    /* renamed from: compareTo-S_HNhKs  reason: not valid java name */
    public static final int m225compareToS_HNhKs(long j, long j2) {
        boolean m226isInLayerimpl = m226isInLayerimpl(j);
        if (m226isInLayerimpl != m226isInLayerimpl(j2)) {
            if (m226isInLayerimpl) {
                return -1;
            }
            return 1;
        }
        return (int) Math.signum(Float.intBitsToFloat((int) (j >> 32)) - Float.intBitsToFloat((int) (j2 >> 32)));
    }

    /* renamed from: isInLayer-impl  reason: not valid java name */
    public static final boolean m226isInLayerimpl(long j) {
        if (((int) (j & 4294967295L)) != 0) {
            return true;
        }
        return false;
    }
}
