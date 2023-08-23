package kotlin.ranges;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LongRange extends LongProgression implements ClosedRange {
    private static final LongRange EMPTY = new LongRange(1, 0);

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
        if (r0 == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof kotlin.ranges.LongRange
            r1 = 0
            if (r0 == 0) goto L46
            long r2 = r7.getFirst()
            long r4 = r7.getLast()
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r2 = 1
            if (r0 <= 0) goto L14
            r0 = r2
            goto L15
        L14:
            r0 = r1
        L15:
            if (r0 == 0) goto L2b
            r0 = r8
            kotlin.ranges.LongRange r0 = (kotlin.ranges.LongRange) r0
            long r3 = r0.getFirst()
            long r5 = r0.getLast()
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 <= 0) goto L28
            r0 = r2
            goto L29
        L28:
            r0 = r1
        L29:
            if (r0 != 0) goto L45
        L2b:
            long r3 = r7.getFirst()
            kotlin.ranges.LongRange r8 = (kotlin.ranges.LongRange) r8
            long r5 = r8.getFirst()
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 != 0) goto L46
            long r3 = r7.getLast()
            long r7 = r8.getLast()
            int r7 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r7 != 0) goto L46
        L45:
            r1 = r2
        L46:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.LongRange.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        boolean z;
        if (getFirst() > getLast()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return -1;
        }
        return (int) ((31 * (getFirst() ^ (getFirst() >>> 32))) + (getLast() ^ (getLast() >>> 32)));
    }

    public final String toString() {
        return getFirst() + ".." + getLast();
    }
}
