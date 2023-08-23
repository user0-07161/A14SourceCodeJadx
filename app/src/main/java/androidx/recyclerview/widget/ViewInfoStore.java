package androidx.recyclerview.widget;

import androidx.collection.LongSparseArray;
import androidx.collection.LongSparseArrayKt;
import androidx.collection.SimpleArrayMap;
import androidx.collection.internal.ContainerHelpersKt;
import androidx.core.util.Pools$SimplePool;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ViewInfoStore {
    final SimpleArrayMap mLayoutHolderMap = new SimpleArrayMap();
    final LongSparseArray mOldChangedHolders = new LongSparseArray();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class InfoRecord {
        static Pools$SimplePool sPool = new Pools$SimplePool(20);
        int flags;
        RecyclerView$ItemAnimator$ItemHolderInfo postInfo;
        RecyclerView$ItemAnimator$ItemHolderInfo preInfo;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static InfoRecord obtain() {
            InfoRecord infoRecord = (InfoRecord) sPool.acquire();
            if (infoRecord == null) {
                return new InfoRecord();
            }
            return infoRecord;
        }
    }

    private RecyclerView$ItemAnimator$ItemHolderInfo popFromLayoutStep(RecyclerView.ViewHolder viewHolder, int i) {
        InfoRecord infoRecord;
        RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo;
        SimpleArrayMap simpleArrayMap = this.mLayoutHolderMap;
        int indexOfKey = simpleArrayMap.indexOfKey(viewHolder);
        if (indexOfKey >= 0 && (infoRecord = (InfoRecord) simpleArrayMap.valueAt(indexOfKey)) != null) {
            int i2 = infoRecord.flags;
            if ((i2 & i) != 0) {
                int i3 = i2 & (~i);
                infoRecord.flags = i3;
                if (i == 4) {
                    recyclerView$ItemAnimator$ItemHolderInfo = infoRecord.preInfo;
                } else if (i == 8) {
                    recyclerView$ItemAnimator$ItemHolderInfo = infoRecord.postInfo;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((i3 & 12) == 0) {
                    simpleArrayMap.removeAt(indexOfKey);
                    infoRecord.flags = 0;
                    infoRecord.preInfo = null;
                    infoRecord.postInfo = null;
                    InfoRecord.sPool.release(infoRecord);
                }
                return recyclerView$ItemAnimator$ItemHolderInfo;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addToOldChangeHolders(long j, RecyclerView.ViewHolder viewHolder) {
        Object obj;
        Object obj2;
        LongSparseArray longSparseArray = this.mOldChangedHolders;
        int binarySearch = ContainerHelpersKt.binarySearch(longSparseArray.keys, longSparseArray.size, j);
        if (binarySearch >= 0) {
            longSparseArray.values[binarySearch] = viewHolder;
            return;
        }
        int i = ~binarySearch;
        if (i < longSparseArray.size) {
            Object obj3 = longSparseArray.values[i];
            obj2 = LongSparseArrayKt.DELETED;
            if (obj3 == obj2) {
                longSparseArray.keys[i] = j;
                longSparseArray.values[i] = viewHolder;
                return;
            }
        }
        if (longSparseArray.garbage) {
            int i2 = longSparseArray.size;
            long[] jArr = longSparseArray.keys;
            if (i2 >= jArr.length) {
                Object[] objArr = longSparseArray.values;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj4 = objArr[i4];
                    obj = LongSparseArrayKt.DELETED;
                    if (obj4 != obj) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr[i3] = obj4;
                            objArr[i4] = null;
                        }
                        i3++;
                    }
                }
                longSparseArray.garbage = false;
                longSparseArray.size = i3;
                i = ~ContainerHelpersKt.binarySearch(longSparseArray.keys, i3, j);
            }
        }
        int i5 = longSparseArray.size;
        if (i5 >= longSparseArray.keys.length) {
            int i6 = (i5 + 1) * 8;
            int i7 = 4;
            while (true) {
                if (i7 >= 32) {
                    break;
                }
                int i8 = (1 << i7) - 12;
                if (i6 <= i8) {
                    i6 = i8;
                    break;
                }
                i7++;
            }
            int i9 = i6 / 8;
            long[] copyOf = Arrays.copyOf(longSparseArray.keys, i9);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            longSparseArray.keys = copyOf;
            Object[] copyOf2 = Arrays.copyOf(longSparseArray.values, i9);
            Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
            longSparseArray.values = copyOf2;
        }
        int i10 = longSparseArray.size - i;
        if (i10 != 0) {
            long[] jArr2 = longSparseArray.keys;
            int i11 = i + 1;
            Intrinsics.checkNotNullParameter(jArr2, "<this>");
            System.arraycopy(jArr2, i, jArr2, i11, i10);
            Object[] objArr2 = longSparseArray.values;
            ArraysKt.copyInto(objArr2, objArr2, i11, i, longSparseArray.size);
        }
        longSparseArray.keys[i] = j;
        longSparseArray.values[i] = viewHolder;
        longSparseArray.size++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addToPostLayout(RecyclerView.ViewHolder viewHolder, RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo) {
        SimpleArrayMap simpleArrayMap = this.mLayoutHolderMap;
        InfoRecord infoRecord = (InfoRecord) simpleArrayMap.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.obtain();
            simpleArrayMap.put(viewHolder, infoRecord);
        }
        infoRecord.postInfo = recyclerView$ItemAnimator$ItemHolderInfo;
        infoRecord.flags |= 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final RecyclerView$ItemAnimator$ItemHolderInfo popFromPostLayout(RecyclerView.ViewHolder viewHolder) {
        return popFromLayoutStep(viewHolder, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final RecyclerView$ItemAnimator$ItemHolderInfo popFromPreLayout(RecyclerView.ViewHolder viewHolder) {
        return popFromLayoutStep(viewHolder, 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeFromDisappearedInLayout(RecyclerView.ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.mLayoutHolderMap.get(viewHolder);
        if (infoRecord == null) {
            return;
        }
        infoRecord.flags &= -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeViewHolder(RecyclerView.ViewHolder viewHolder) {
        Object obj;
        Object obj2;
        Object obj3;
        LongSparseArray longSparseArray = this.mOldChangedHolders;
        int size = longSparseArray.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            } else if (viewHolder == longSparseArray.valueAt(size)) {
                Object obj4 = longSparseArray.values[size];
                obj2 = LongSparseArrayKt.DELETED;
                if (obj4 != obj2) {
                    Object[] objArr = longSparseArray.values;
                    obj3 = LongSparseArrayKt.DELETED;
                    objArr[size] = obj3;
                    longSparseArray.garbage = true;
                }
            } else {
                size--;
            }
        }
        SimpleArrayMap simpleArrayMap = this.mLayoutHolderMap;
        int indexOfKey = simpleArrayMap.indexOfKey(viewHolder);
        if (indexOfKey >= 0) {
            obj = simpleArrayMap.removeAt(indexOfKey);
        } else {
            obj = null;
        }
        InfoRecord infoRecord = (InfoRecord) obj;
        if (infoRecord != null) {
            infoRecord.flags = 0;
            infoRecord.preInfo = null;
            infoRecord.postInfo = null;
            InfoRecord.sPool.release(infoRecord);
        }
    }
}
