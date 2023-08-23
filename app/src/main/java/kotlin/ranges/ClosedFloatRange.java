package kotlin.ranges;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class ClosedFloatRange implements ClosedFloatingPointRange {
    private final float _endInclusive;
    private final float _start;

    public ClosedFloatRange(float f, float f2) {
        this._start = f;
        this._endInclusive = f2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0021, code lost:
        if (r2 == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof kotlin.ranges.ClosedFloatRange
            r1 = 0
            if (r0 == 0) goto L3c
            float r0 = r5._start
            float r5 = r5._endInclusive
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            r3 = 1
            if (r2 <= 0) goto L10
            r2 = r3
            goto L11
        L10:
            r2 = r1
        L11:
            if (r2 == 0) goto L23
            r2 = r6
            kotlin.ranges.ClosedFloatRange r2 = (kotlin.ranges.ClosedFloatRange) r2
            float r4 = r2._start
            float r2 = r2._endInclusive
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 <= 0) goto L20
            r2 = r3
            goto L21
        L20:
            r2 = r1
        L21:
            if (r2 != 0) goto L3b
        L23:
            kotlin.ranges.ClosedFloatRange r6 = (kotlin.ranges.ClosedFloatRange) r6
            float r2 = r6._start
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L2d
            r0 = r3
            goto L2e
        L2d:
            r0 = r1
        L2e:
            if (r0 == 0) goto L3c
            float r6 = r6._endInclusive
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 != 0) goto L38
            r5 = r3
            goto L39
        L38:
            r5 = r1
        L39:
            if (r5 == 0) goto L3c
        L3b:
            r1 = r3
        L3c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.ClosedFloatRange.equals(java.lang.Object):boolean");
    }

    @Override // kotlin.ranges.ClosedRange
    public final Comparable getEndInclusive() {
        return Float.valueOf(this._endInclusive);
    }

    @Override // kotlin.ranges.ClosedRange
    public final Comparable getStart() {
        return Float.valueOf(this._start);
    }

    public final int hashCode() {
        boolean z;
        float f = this._start;
        float f2 = this._endInclusive;
        if (f > f2) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return -1;
        }
        return Float.hashCode(f2) + (Float.hashCode(f) * 31);
    }

    public final String toString() {
        return this._start + ".." + this._endInclusive;
    }
}
