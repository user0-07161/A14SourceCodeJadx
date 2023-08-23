package kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ULong implements Comparable {
    private final long data;

    private /* synthetic */ ULong(long j) {
        this.data = j;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ ULong m479boximpl(long j) {
        return new ULong(j);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        int i = ((this.data ^ Long.MIN_VALUE) > (((ULong) obj).data ^ Long.MIN_VALUE) ? 1 : ((this.data ^ Long.MIN_VALUE) == (((ULong) obj).data ^ Long.MIN_VALUE) ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        if (i == 0) {
            return 0;
        }
        return 1;
    }

    public final boolean equals(Object obj) {
        long j = this.data;
        if (!(obj instanceof ULong) || j != ((ULong) obj).data) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.data);
    }

    public final String toString() {
        long j = this.data;
        if (j >= 0) {
            CharsKt.checkRadix(10);
            String l = Long.toString(j, 10);
            Intrinsics.checkNotNullExpressionValue(l, "toString(this, checkRadix(radix))");
            return l;
        }
        long j2 = 10;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        CharsKt.checkRadix(10);
        String l2 = Long.toString(j3, 10);
        Intrinsics.checkNotNullExpressionValue(l2, "toString(this, checkRadix(radix))");
        CharsKt.checkRadix(10);
        String l3 = Long.toString(j4, 10);
        Intrinsics.checkNotNullExpressionValue(l3, "toString(this, checkRadix(radix))");
        return l2.concat(l3);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m480unboximpl() {
        return this.data;
    }
}
