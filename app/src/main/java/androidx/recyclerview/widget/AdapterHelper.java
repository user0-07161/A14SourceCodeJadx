package androidx.recyclerview.widget;

import androidx.core.util.Pools$SimplePool;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AdapterHelper {
    final RecyclerView.AnonymousClass4 mCallback;
    private Pools$SimplePool mUpdateOpPool = new Pools$SimplePool(30);
    final ArrayList mPendingUpdates = new ArrayList();
    final ArrayList mPostponedList = new ArrayList();
    private int mExistingUpdateTypes = 0;
    final OpReorderer mOpReorderer = new OpReorderer(this);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class UpdateOp {
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        UpdateOp(Object obj, int i, int i2, int i3) {
            this.cmd = i;
            this.positionStart = i2;
            this.itemCount = i3;
            this.payload = obj;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UpdateOp)) {
                return false;
            }
            UpdateOp updateOp = (UpdateOp) obj;
            int i = this.cmd;
            if (i != updateOp.cmd) {
                return false;
            }
            if (i == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == updateOp.positionStart && this.positionStart == updateOp.itemCount) {
                return true;
            }
            if (this.itemCount != updateOp.itemCount || this.positionStart != updateOp.positionStart) {
                return false;
            }
            Object obj2 = this.payload;
            if (obj2 != null) {
                if (!obj2.equals(updateOp.payload)) {
                    return false;
                }
            } else if (updateOp.payload != null) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
        }

        public final String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append("[");
            int i = this.cmd;
            if (i != 1) {
                if (i != 2) {
                    if (i != 4) {
                        if (i != 8) {
                            str = "??";
                        } else {
                            str = "mv";
                        }
                    } else {
                        str = "up";
                    }
                } else {
                    str = "rm";
                }
            } else {
                str = "add";
            }
            sb.append(str);
            sb.append(",s:");
            sb.append(this.positionStart);
            sb.append("c:");
            sb.append(this.itemCount);
            sb.append(",p:");
            sb.append(this.payload);
            sb.append("]");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterHelper(RecyclerView.AnonymousClass4 anonymousClass4) {
        this.mCallback = anonymousClass4;
    }

    private boolean canFindInPreLayout(int i) {
        ArrayList arrayList = this.mPostponedList;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = (UpdateOp) arrayList.get(i2);
            int i3 = updateOp.cmd;
            if (i3 == 8) {
                if (findPositionOffset(updateOp.itemCount, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = updateOp.positionStart;
                int i5 = updateOp.itemCount + i4;
                while (i4 < i5) {
                    if (findPositionOffset(i4, i2 + 1) == i) {
                        return true;
                    }
                    i4++;
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void dispatchAndUpdateViewHolders(UpdateOp updateOp) {
        int i;
        boolean z;
        int i2 = updateOp.cmd;
        if (i2 != 1 && i2 != 8) {
            int updatePositionWithPostponed = updatePositionWithPostponed(updateOp.positionStart, i2);
            int i3 = updateOp.positionStart;
            int i4 = updateOp.cmd;
            if (i4 != 2) {
                if (i4 == 4) {
                    i = 1;
                } else {
                    throw new IllegalArgumentException("op should be remove or update." + updateOp);
                }
            } else {
                i = 0;
            }
            int i5 = 1;
            for (int i6 = 1; i6 < updateOp.itemCount; i6++) {
                int updatePositionWithPostponed2 = updatePositionWithPostponed((i * i6) + updateOp.positionStart, updateOp.cmd);
                int i7 = updateOp.cmd;
                if (i7 == 2 ? updatePositionWithPostponed2 == updatePositionWithPostponed : !(i7 != 4 || updatePositionWithPostponed2 != updatePositionWithPostponed + 1)) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    i5++;
                } else {
                    UpdateOp obtainUpdateOp = obtainUpdateOp(updateOp.payload, i7, updatePositionWithPostponed, i5);
                    dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp, i3);
                    recycleUpdateOp(obtainUpdateOp);
                    if (updateOp.cmd == 4) {
                        i3 += i5;
                    }
                    i5 = 1;
                    updatePositionWithPostponed = updatePositionWithPostponed2;
                }
            }
            Object obj = updateOp.payload;
            recycleUpdateOp(updateOp);
            if (i5 > 0) {
                UpdateOp obtainUpdateOp2 = obtainUpdateOp(obj, updateOp.cmd, updatePositionWithPostponed, i5);
                dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp2, i3);
                recycleUpdateOp(obtainUpdateOp2);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("should not dispatch add or move for pre layout");
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateOp) {
        this.mPostponedList.add(updateOp);
        int i = updateOp.cmd;
        RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i == 8) {
                        anonymousClass4.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
                        return;
                    }
                    throw new IllegalArgumentException("Unknown update op type for " + updateOp);
                }
                anonymousClass4.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
                return;
            }
            int i2 = updateOp.positionStart;
            int i3 = updateOp.itemCount;
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.offsetPositionRecordsForRemove(i2, i3, false);
            recyclerView.mItemsAddedOrRemoved = true;
            return;
        }
        anonymousClass4.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
    }

    private int updatePositionWithPostponed(int i, int i2) {
        int i3;
        int i4;
        ArrayList arrayList = this.mPostponedList;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            UpdateOp updateOp = (UpdateOp) arrayList.get(size);
            int i5 = updateOp.cmd;
            if (i5 == 8) {
                int i6 = updateOp.positionStart;
                int i7 = updateOp.itemCount;
                if (i6 < i7) {
                    i4 = i6;
                    i3 = i7;
                } else {
                    i3 = i6;
                    i4 = i7;
                }
                if (i >= i4 && i <= i3) {
                    if (i4 == i6) {
                        if (i2 == 1) {
                            updateOp.itemCount = i7 + 1;
                        } else if (i2 == 2) {
                            updateOp.itemCount = i7 - 1;
                        }
                        i++;
                    } else {
                        if (i2 == 1) {
                            updateOp.positionStart = i6 + 1;
                        } else if (i2 == 2) {
                            updateOp.positionStart = i6 - 1;
                        }
                        i--;
                    }
                } else if (i < i6) {
                    if (i2 == 1) {
                        updateOp.positionStart = i6 + 1;
                        updateOp.itemCount = i7 + 1;
                    } else if (i2 == 2) {
                        updateOp.positionStart = i6 - 1;
                        updateOp.itemCount = i7 - 1;
                    }
                }
            } else {
                int i8 = updateOp.positionStart;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= updateOp.itemCount;
                    } else if (i5 == 2) {
                        i += updateOp.itemCount;
                    }
                } else if (i2 == 1) {
                    updateOp.positionStart = i8 + 1;
                } else if (i2 == 2) {
                    updateOp.positionStart = i8 - 1;
                }
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            UpdateOp updateOp2 = (UpdateOp) arrayList.get(size2);
            if (updateOp2.cmd == 8) {
                int i9 = updateOp2.itemCount;
                if (i9 == updateOp2.positionStart || i9 < 0) {
                    arrayList.remove(size2);
                    recycleUpdateOp(updateOp2);
                }
            } else if (updateOp2.itemCount <= 0) {
                arrayList.remove(size2);
                recycleUpdateOp(updateOp2);
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void consumePostponedUpdates() {
        ArrayList arrayList = this.mPostponedList;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.mCallback.dispatchUpdate((UpdateOp) arrayList.get(i));
        }
        recycleUpdateOpsAndClearList(arrayList);
        this.mExistingUpdateTypes = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        ArrayList arrayList = this.mPendingUpdates;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            UpdateOp updateOp = (UpdateOp) arrayList.get(i);
            int i2 = updateOp.cmd;
            RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 4) {
                        if (i2 == 8) {
                            anonymousClass4.dispatchUpdate(updateOp);
                            anonymousClass4.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
                        }
                    } else {
                        anonymousClass4.dispatchUpdate(updateOp);
                        anonymousClass4.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
                    }
                } else {
                    anonymousClass4.dispatchUpdate(updateOp);
                    int i3 = updateOp.positionStart;
                    int i4 = updateOp.itemCount;
                    RecyclerView recyclerView = RecyclerView.this;
                    recyclerView.offsetPositionRecordsForRemove(i3, i4, true);
                    recyclerView.mItemsAddedOrRemoved = true;
                    recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i4;
                }
            } else {
                anonymousClass4.dispatchUpdate(updateOp);
                anonymousClass4.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
            }
        }
        recycleUpdateOpsAndClearList(arrayList);
        this.mExistingUpdateTypes = 0;
    }

    final void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateOp, int i) {
        RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
        anonymousClass4.dispatchUpdate(updateOp);
        int i2 = updateOp.cmd;
        if (i2 != 2) {
            if (i2 == 4) {
                anonymousClass4.markViewHoldersUpdated(i, updateOp.itemCount, updateOp.payload);
                return;
            }
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
        int i3 = updateOp.itemCount;
        RecyclerView recyclerView = RecyclerView.this;
        recyclerView.offsetPositionRecordsForRemove(i, i3, true);
        recyclerView.mItemsAddedOrRemoved = true;
        recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int findPositionOffset(int i, int i2) {
        ArrayList arrayList = this.mPostponedList;
        int size = arrayList.size();
        while (i2 < size) {
            UpdateOp updateOp = (UpdateOp) arrayList.get(i2);
            int i3 = updateOp.cmd;
            if (i3 == 8) {
                int i4 = updateOp.positionStart;
                if (i4 == i) {
                    i = updateOp.itemCount;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (updateOp.itemCount <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = updateOp.positionStart;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = updateOp.itemCount;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += updateOp.itemCount;
                }
            }
            i2++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasAnyUpdateTypes(int i) {
        if ((this.mExistingUpdateTypes & i) != 0) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasPendingUpdates() {
        if (this.mPendingUpdates.size() > 0) {
            return true;
        }
        return false;
    }

    public final UpdateOp obtainUpdateOp(Object obj, int i, int i2, int i3) {
        UpdateOp updateOp = (UpdateOp) this.mUpdateOpPool.acquire();
        if (updateOp == null) {
            return new UpdateOp(obj, i, i2, i3);
        }
        updateOp.cmd = i;
        updateOp.positionStart = i2;
        updateOp.itemCount = i3;
        updateOp.payload = obj;
        return updateOp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean onItemRangeChanged(int i, int i2, Object obj) {
        if (i2 < 1) {
            return false;
        }
        ArrayList arrayList = this.mPendingUpdates;
        arrayList.add(obtainUpdateOp(obj, 4, i, i2));
        this.mExistingUpdateTypes |= 4;
        if (arrayList.size() != 1) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean onItemRangeInserted(int i, int i2) {
        if (i2 < 1) {
            return false;
        }
        ArrayList arrayList = this.mPendingUpdates;
        arrayList.add(obtainUpdateOp(null, 1, i, i2));
        this.mExistingUpdateTypes |= 1;
        if (arrayList.size() != 1) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean onItemRangeMoved(int i, int i2) {
        if (i == i2) {
            return false;
        }
        ArrayList arrayList = this.mPendingUpdates;
        arrayList.add(obtainUpdateOp(null, 8, i, i2));
        this.mExistingUpdateTypes |= 8;
        if (arrayList.size() != 1) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean onItemRangeRemoved(int i, int i2) {
        if (i2 < 1) {
            return false;
        }
        ArrayList arrayList = this.mPendingUpdates;
        arrayList.add(obtainUpdateOp(null, 2, i, i2));
        this.mExistingUpdateTypes |= 2;
        if (arrayList.size() != 1) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0099 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0110 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0105 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x00c7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void preProcess() {
        /*
            Method dump skipped, instructions count: 642
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.AdapterHelper.preProcess():void");
    }

    public final void recycleUpdateOp(UpdateOp updateOp) {
        updateOp.payload = null;
        this.mUpdateOpPool.release(updateOp);
    }

    final void recycleUpdateOpsAndClearList(List list) {
        ArrayList arrayList = (ArrayList) list;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            recycleUpdateOp((UpdateOp) arrayList.get(i));
        }
        arrayList.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }
}
