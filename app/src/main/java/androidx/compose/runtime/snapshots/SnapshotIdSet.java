package androidx.compose.runtime.snapshots;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SnapshotIdSet implements Iterable, KMappedMarker {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final SnapshotIdSet EMPTY = new SnapshotIdSet(0, 0, 0, null);
    private final int[] belowBound;
    private final int lowerBound;
    private final long lowerSet;
    private final long upperSet;

    private SnapshotIdSet(long j, long j2, int i, int[] iArr) {
        this.upperSet = j;
        this.lowerSet = j2;
        this.lowerBound = i;
        this.belowBound = iArr;
    }

    public final SnapshotIdSet andNot(SnapshotIdSet bits) {
        Intrinsics.checkNotNullParameter(bits, "bits");
        SnapshotIdSet snapshotIdSet = EMPTY;
        if (bits == snapshotIdSet) {
            return this;
        }
        if (this == snapshotIdSet) {
            return snapshotIdSet;
        }
        int i = bits.lowerBound;
        int i2 = this.lowerBound;
        if (i == i2) {
            int[] iArr = bits.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                return new SnapshotIdSet(this.upperSet & (~bits.upperSet), this.lowerSet & (~bits.lowerSet), i2, iArr2);
            }
        }
        Iterator it = bits.iterator();
        while (it.hasNext()) {
            this = this.clear(((Number) it.next()).intValue());
        }
        return this;
    }

    public final SnapshotIdSet clear(int i) {
        int[] iArr;
        int binarySearch;
        int i2 = this.lowerBound;
        int i3 = i - i2;
        if (i3 >= 0 && i3 < 64) {
            long j = 1 << i3;
            long j2 = this.lowerSet;
            if ((j2 & j) != 0) {
                return new SnapshotIdSet(this.upperSet, j2 & (~j), i2, this.belowBound);
            }
        } else if (i3 >= 64 && i3 < 128) {
            long j3 = 1 << (i3 - 64);
            long j4 = this.upperSet;
            if ((j4 & j3) != 0) {
                return new SnapshotIdSet((~j3) & j4, this.lowerSet, i2, this.belowBound);
            }
        } else if (i3 < 0 && (iArr = this.belowBound) != null && (binarySearch = SnapshotIdSetKt.binarySearch(i, iArr)) >= 0) {
            int length = iArr.length - 1;
            if (length == 0) {
                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, null);
            }
            int[] iArr2 = new int[length];
            if (binarySearch > 0) {
                ArraysKt.copyInto(0, 0, binarySearch, iArr, iArr2);
            }
            if (binarySearch < length) {
                ArraysKt.copyInto(binarySearch, binarySearch + 1, length + 1, iArr, iArr2);
            }
            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, iArr2);
        }
        return this;
    }

    public final boolean get(int i) {
        int[] iArr;
        int i2 = i - this.lowerBound;
        boolean z = true;
        if (i2 >= 0 && i2 < 64) {
            if ((this.lowerSet & (1 << i2)) != 0) {
                return true;
            }
            return false;
        } else if (i2 >= 64 && i2 < 128) {
            if ((this.upperSet & (1 << (i2 - 64))) != 0) {
                return true;
            }
            return false;
        } else if (i2 > 0 || (iArr = this.belowBound) == null) {
            return false;
        } else {
            if (SnapshotIdSetKt.binarySearch(i, iArr) < 0) {
                z = false;
            }
            return z;
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(new SnapshotIdSet$iterator$1(this, null)).iterator();
    }

    public final int lowest(int i) {
        int i2;
        int access$lowestBitOf;
        int[] iArr = this.belowBound;
        if (iArr != null) {
            return iArr[0];
        }
        long j = this.lowerSet;
        if (j != 0) {
            i2 = this.lowerBound;
            access$lowestBitOf = SnapshotIdSetKt.access$lowestBitOf(j);
        } else {
            long j2 = this.upperSet;
            if (j2 != 0) {
                i2 = this.lowerBound + 64;
                access$lowestBitOf = SnapshotIdSetKt.access$lowestBitOf(j2);
            } else {
                return i;
            }
        }
        return access$lowestBitOf + i2;
    }

    public final SnapshotIdSet or(SnapshotIdSet bits) {
        Intrinsics.checkNotNullParameter(bits, "bits");
        SnapshotIdSet snapshotIdSet = EMPTY;
        if (bits == snapshotIdSet) {
            return this;
        }
        if (this == snapshotIdSet) {
            return bits;
        }
        int i = bits.lowerBound;
        int i2 = this.lowerBound;
        if (i == i2) {
            int[] iArr = bits.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                return new SnapshotIdSet(this.upperSet | bits.upperSet, this.lowerSet | bits.lowerSet, i2, iArr2);
            }
        }
        if (this.belowBound == null) {
            Iterator it = iterator();
            while (it.hasNext()) {
                bits = bits.set(((Number) it.next()).intValue());
            }
            return bits;
        }
        Iterator it2 = bits.iterator();
        while (it2.hasNext()) {
            this = this.set(((Number) it2.next()).intValue());
        }
        return this;
    }

    public final SnapshotIdSet set(int i) {
        int i2;
        int[] iArr;
        int i3 = this.lowerBound;
        int i4 = i - i3;
        long j = 0;
        if (i4 >= 0 && i4 < 64) {
            long j2 = 1 << i4;
            long j3 = this.lowerSet;
            if ((j3 & j2) == 0) {
                return new SnapshotIdSet(this.upperSet, j3 | j2, i3, this.belowBound);
            }
        } else if (i4 >= 64 && i4 < 128) {
            long j4 = 1 << (i4 - 64);
            long j5 = this.upperSet;
            if ((j5 & j4) == 0) {
                return new SnapshotIdSet(j4 | j5, this.lowerSet, i3, this.belowBound);
            }
        } else if (i4 >= 128) {
            if (!get(i)) {
                long j6 = this.upperSet;
                long j7 = this.lowerSet;
                int i5 = this.lowerBound;
                int i6 = ((i + 1) / 64) * 64;
                ArrayList arrayList = null;
                long j8 = j7;
                long j9 = j6;
                while (true) {
                    if (i5 < i6) {
                        if (j8 != j) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                int[] iArr2 = this.belowBound;
                                if (iArr2 != null) {
                                    for (int i7 : iArr2) {
                                        arrayList.add(Integer.valueOf(i7));
                                    }
                                }
                            }
                            for (int i8 = 0; i8 < 64; i8++) {
                                if (((1 << i8) & j8) != 0) {
                                    arrayList.add(Integer.valueOf(i8 + i5));
                                }
                            }
                            j = 0;
                        }
                        if (j9 == j) {
                            i2 = i6;
                            j8 = j;
                            break;
                        }
                        i5 += 64;
                        j8 = j9;
                        j9 = j;
                    } else {
                        i2 = i5;
                        break;
                    }
                }
                if (arrayList != null) {
                    iArr = CollectionsKt.toIntArray(arrayList);
                } else {
                    iArr = this.belowBound;
                }
                return new SnapshotIdSet(j9, j8, i2, iArr).set(i);
            }
        } else {
            int[] iArr3 = this.belowBound;
            if (iArr3 == null) {
                return new SnapshotIdSet(this.upperSet, this.lowerSet, i3, new int[]{i});
            }
            int binarySearch = SnapshotIdSetKt.binarySearch(i, iArr3);
            if (binarySearch < 0) {
                int i9 = -(binarySearch + 1);
                int length = iArr3.length + 1;
                int[] iArr4 = new int[length];
                ArraysKt.copyInto(0, 0, i9, iArr3, iArr4);
                ArraysKt.copyInto(i9 + 1, i9, length - 1, iArr3, iArr4);
                iArr4[i9] = i;
                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, iArr4);
            }
        }
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(this));
        Iterator it = iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(((Number) it.next()).intValue()));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) "");
        int size = arrayList.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = arrayList.get(i2);
            boolean z = true;
            i++;
            if (i > 1) {
                sb2.append((CharSequence) ", ");
            }
            if (obj != null) {
                z = obj instanceof CharSequence;
            }
            if (z) {
                sb2.append((CharSequence) obj);
            } else if (obj instanceof Character) {
                sb2.append(((Character) obj).charValue());
            } else {
                sb2.append((CharSequence) String.valueOf(obj));
            }
        }
        sb2.append((CharSequence) "");
        String sb3 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(sb3, "fastJoinTo(StringBuilder…form)\n        .toString()");
        sb.append(sb3);
        sb.append(']');
        return sb.toString();
    }
}
