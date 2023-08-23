package androidx.compose.runtime.snapshots;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class MutableSnapshot extends Snapshot {
    private boolean applied;
    private Set modified;
    private SnapshotIdSet previousIds;
    private int[] previousPinnedSnapshots;
    private final Function1 readObserver;
    private int snapshots;
    private final Function1 writeObserver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableSnapshot(int i, SnapshotIdSet invalid, Function1 function1, Function1 function12) {
        super(i, invalid);
        SnapshotIdSet snapshotIdSet;
        Intrinsics.checkNotNullParameter(invalid, "invalid");
        this.readObserver = function1;
        this.writeObserver = function12;
        snapshotIdSet = SnapshotIdSet.EMPTY;
        this.previousIds = snapshotIdSet;
        this.previousPinnedSnapshots = new int[0];
        this.snapshots = 1;
    }

    public final void advance$runtime_release() {
        int i;
        SnapshotIdSet snapshotIdSet;
        recordPrevious$runtime_release(getId());
        if (!this.applied && !getDisposed$runtime_release()) {
            int id = getId();
            synchronized (SnapshotKt.getLock()) {
                i = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i + 1;
                setId$runtime_release(i);
                snapshotIdSet = SnapshotKt.openSnapshots;
                SnapshotKt.openSnapshots = snapshotIdSet.set(getId());
            }
            setInvalid$runtime_release(SnapshotKt.addRange(id + 1, getId(), getInvalid$runtime_release()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0110 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.compose.runtime.snapshots.SnapshotApplyResult apply() {
        /*
            Method dump skipped, instructions count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.apply():androidx.compose.runtime.snapshots.SnapshotApplyResult");
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void closeLocked$runtime_release() {
        SnapshotIdSet snapshotIdSet;
        snapshotIdSet = SnapshotKt.openSnapshots;
        SnapshotKt.openSnapshots = snapshotIdSet.clear(getId()).andNot(this.previousIds);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (!getDisposed$runtime_release()) {
            super.dispose();
            nestedDeactivated$runtime_release(this);
        }
    }

    public final boolean getApplied$runtime_release() {
        return this.applied;
    }

    public Set getModified$runtime_release() {
        return this.modified;
    }

    public final SnapshotIdSet getPreviousIds$runtime_release() {
        return this.previousIds;
    }

    public final int[] getPreviousPinnedSnapshots$runtime_release() {
        return this.previousPinnedSnapshots;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final Function1 getReadObserver$runtime_release() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final Function1 getWriteObserver$runtime_release() {
        return this.writeObserver;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x003c, code lost:
        r9 = androidx.compose.runtime.snapshots.SnapshotKt.readable(r7, getId(), r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.runtime.snapshots.SnapshotApplyResult innerApplyLocked$runtime_release(int r13, java.util.Map r14, androidx.compose.runtime.snapshots.SnapshotIdSet r15) {
        /*
            r12 = this;
            java.lang.String r0 = "invalidSnapshots"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            androidx.compose.runtime.snapshots.SnapshotIdSet r0 = r12.getInvalid$runtime_release()
            int r1 = r12.getId()
            androidx.compose.runtime.snapshots.SnapshotIdSet r0 = r0.set(r1)
            androidx.compose.runtime.snapshots.SnapshotIdSet r1 = r12.previousIds
            androidx.compose.runtime.snapshots.SnapshotIdSet r0 = r0.or(r1)
            java.util.Set r1 = r12.getModified$runtime_release()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.util.Iterator r2 = r1.iterator()
            r3 = 0
            r4 = r3
            r5 = r4
        L25:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto Lc1
            java.lang.Object r6 = r2.next()
            androidx.compose.runtime.snapshots.StateObject r6 = (androidx.compose.runtime.snapshots.StateObject) r6
            androidx.compose.runtime.snapshots.StateRecord r7 = r6.getFirstStateRecord()
            androidx.compose.runtime.snapshots.StateRecord r8 = androidx.compose.runtime.snapshots.SnapshotKt.access$readable(r7, r13, r15)
            if (r8 != 0) goto L3c
            goto L25
        L3c:
            int r9 = r12.getId()
            androidx.compose.runtime.snapshots.StateRecord r9 = androidx.compose.runtime.snapshots.SnapshotKt.access$readable(r7, r9, r0)
            if (r9 != 0) goto L47
            goto L25
        L47:
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r9)
            if (r10 != 0) goto L25
            int r10 = r12.getId()
            androidx.compose.runtime.snapshots.SnapshotIdSet r11 = r12.getInvalid$runtime_release()
            androidx.compose.runtime.snapshots.StateRecord r7 = androidx.compose.runtime.snapshots.SnapshotKt.access$readable(r7, r10, r11)
            if (r7 == 0) goto Lbd
            if (r14 == 0) goto L65
            java.lang.Object r10 = r14.get(r8)
            androidx.compose.runtime.snapshots.StateRecord r10 = (androidx.compose.runtime.snapshots.StateRecord) r10
            if (r10 != 0) goto L69
        L65:
            androidx.compose.runtime.snapshots.StateRecord r10 = r6.mergeRecords(r9, r8, r7)
        L69:
            if (r10 != 0) goto L71
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Failure r13 = new androidx.compose.runtime.snapshots.SnapshotApplyResult$Failure
            r13.<init>(r12)
            return r13
        L71:
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r7)
            if (r7 != 0) goto L25
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r8)
            if (r7 == 0) goto L9b
            if (r4 != 0) goto L84
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        L84:
            androidx.compose.runtime.snapshots.StateRecord r7 = r8.create()
            kotlin.Pair r8 = new kotlin.Pair
            r8.<init>(r6, r7)
            r4.add(r8)
            if (r5 != 0) goto L97
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
        L97:
            r5.add(r6)
            goto L25
        L9b:
            if (r4 != 0) goto La2
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        La2:
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r9)
            if (r7 != 0) goto Lae
            kotlin.Pair r7 = new kotlin.Pair
            r7.<init>(r6, r10)
            goto Lb8
        Lae:
            androidx.compose.runtime.snapshots.StateRecord r7 = r9.create()
            kotlin.Pair r8 = new kotlin.Pair
            r8.<init>(r6, r7)
            r7 = r8
        Lb8:
            r4.add(r7)
            goto L25
        Lbd:
            androidx.compose.runtime.snapshots.SnapshotKt.access$readError()
            throw r3
        Lc1:
            if (r4 == 0) goto Lfc
            r12.advance$runtime_release()
            int r13 = r4.size()
            r14 = 0
        Lcb:
            if (r14 >= r13) goto Lfc
            java.lang.Object r15 = r4.get(r14)
            kotlin.Pair r15 = (kotlin.Pair) r15
            java.lang.Object r0 = r15.component1()
            androidx.compose.runtime.snapshots.StateObject r0 = (androidx.compose.runtime.snapshots.StateObject) r0
            java.lang.Object r15 = r15.component2()
            androidx.compose.runtime.snapshots.StateRecord r15 = (androidx.compose.runtime.snapshots.StateRecord) r15
            int r2 = r12.getId()
            r15.setSnapshotId$runtime_release(r2)
            java.lang.Object r2 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()
            monitor-enter(r2)
            androidx.compose.runtime.snapshots.StateRecord r3 = r0.getFirstStateRecord()     // Catch: java.lang.Throwable -> Lf9
            r15.setNext$runtime_release(r3)     // Catch: java.lang.Throwable -> Lf9
            r0.prependStateRecord(r15)     // Catch: java.lang.Throwable -> Lf9
            monitor-exit(r2)
            int r14 = r14 + 1
            goto Lcb
        Lf9:
            r12 = move-exception
            monitor-exit(r2)
            throw r12
        Lfc:
            if (r5 == 0) goto L101
            r1.removeAll(r5)
        L101:
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Success r12 = androidx.compose.runtime.snapshots.SnapshotApplyResult.Success.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.innerApplyLocked$runtime_release(int, java.util.Map, androidx.compose.runtime.snapshots.SnapshotIdSet):androidx.compose.runtime.snapshots.SnapshotApplyResult");
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void nestedActivated$runtime_release(Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this.snapshots++;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void nestedDeactivated$runtime_release(Snapshot snapshot) {
        boolean z;
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        int i = this.snapshots;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            int i2 = i - 1;
            this.snapshots = i2;
            if (i2 == 0 && !this.applied) {
                Set<StateObject> modified$runtime_release = getModified$runtime_release();
                if (modified$runtime_release != null) {
                    if (true ^ this.applied) {
                        setModified(null);
                        int id = getId();
                        for (StateObject stateObject : modified$runtime_release) {
                            for (StateRecord firstStateRecord = stateObject.getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext$runtime_release()) {
                                if (firstStateRecord.getSnapshotId$runtime_release() == id || CollectionsKt.contains(this.previousIds, Integer.valueOf(firstStateRecord.getSnapshotId$runtime_release()))) {
                                    firstStateRecord.setSnapshotId$runtime_release(0);
                                }
                            }
                        }
                    } else {
                        throw new IllegalStateException("Unsupported operation on a snapshot that has been applied".toString());
                    }
                }
                closeAndReleasePinning$runtime_release();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
        if (!this.applied && !getDisposed$runtime_release()) {
            advance$runtime_release();
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void recordModified$runtime_release(StateObject state) {
        Intrinsics.checkNotNullParameter(state, "state");
        Set modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release == null) {
            modified$runtime_release = new HashSet();
            setModified(modified$runtime_release);
        }
        modified$runtime_release.add(state);
    }

    public final void recordPrevious$runtime_release(int i) {
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.set(i);
        }
    }

    public final void recordPreviousList$runtime_release(SnapshotIdSet snapshots) {
        Intrinsics.checkNotNullParameter(snapshots, "snapshots");
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.or(snapshots);
        }
    }

    public final void recordPreviousPinnedSnapshot$runtime_release(int i) {
        if (i >= 0) {
            int[] iArr = this.previousPinnedSnapshots;
            Intrinsics.checkNotNullParameter(iArr, "<this>");
            int length = iArr.length;
            int[] copyOf = Arrays.copyOf(iArr, length + 1);
            copyOf[length] = i;
            this.previousPinnedSnapshots = copyOf;
        }
    }

    public final void recordPreviousPinnedSnapshots$runtime_release(int[] handles) {
        boolean z;
        Intrinsics.checkNotNullParameter(handles, "handles");
        boolean z2 = true;
        if (handles.length == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        int[] iArr = this.previousPinnedSnapshots;
        if (iArr.length != 0) {
            z2 = false;
        }
        if (z2) {
            this.previousPinnedSnapshots = handles;
            return;
        }
        int length = iArr.length;
        int length2 = handles.length;
        int[] result = Arrays.copyOf(iArr, length + length2);
        System.arraycopy(handles, 0, result, length, length2);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        this.previousPinnedSnapshots = result;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void releasePinnedSnapshotsForCloseLocked$runtime_release() {
        int length = this.previousPinnedSnapshots.length;
        for (int i = 0; i < length; i++) {
            SnapshotKt.releasePinningLocked(this.previousPinnedSnapshots[i]);
        }
        releasePinnedSnapshotLocked$runtime_release();
    }

    public final void setApplied$runtime_release() {
        this.applied = true;
    }

    public void setModified(Set set) {
        this.modified = set;
    }

    public MutableSnapshot takeNestedMutableSnapshot(Function1 function1, Function1 function12) {
        int i;
        SnapshotIdSet snapshotIdSet;
        NestedMutableSnapshot nestedMutableSnapshot;
        int i2;
        SnapshotIdSet snapshotIdSet2;
        validateNotDisposed$runtime_release();
        validateNotAppliedOrPinned$runtime_release();
        recordPrevious$runtime_release(getId());
        synchronized (SnapshotKt.getLock()) {
            i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            snapshotIdSet = SnapshotKt.openSnapshots;
            SnapshotKt.openSnapshots = snapshotIdSet.set(i);
            SnapshotIdSet invalid$runtime_release = getInvalid$runtime_release();
            setInvalid$runtime_release(invalid$runtime_release.set(i));
            nestedMutableSnapshot = new NestedMutableSnapshot(i, SnapshotKt.addRange(getId() + 1, i, invalid$runtime_release), SnapshotKt.mergedReadObserver$default(function1, this.readObserver), SnapshotKt.access$mergedWriteObserver(function12, this.writeObserver), this);
        }
        if (!this.applied && !getDisposed$runtime_release()) {
            int id = getId();
            synchronized (SnapshotKt.getLock()) {
                i2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i2 + 1;
                setId$runtime_release(i2);
                snapshotIdSet2 = SnapshotKt.openSnapshots;
                SnapshotKt.openSnapshots = snapshotIdSet2.set(getId());
            }
            setInvalid$runtime_release(SnapshotKt.addRange(id + 1, getId(), getInvalid$runtime_release()));
        }
        return nestedMutableSnapshot;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1 function1) {
        int i;
        SnapshotIdSet snapshotIdSet;
        NestedReadonlySnapshot nestedReadonlySnapshot;
        int i2;
        SnapshotIdSet snapshotIdSet2;
        validateNotDisposed$runtime_release();
        validateNotAppliedOrPinned$runtime_release();
        int id = getId();
        recordPrevious$runtime_release(getId());
        synchronized (SnapshotKt.getLock()) {
            i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            snapshotIdSet = SnapshotKt.openSnapshots;
            SnapshotKt.openSnapshots = snapshotIdSet.set(i);
            nestedReadonlySnapshot = new NestedReadonlySnapshot(i, SnapshotKt.addRange(id + 1, i, getInvalid$runtime_release()), function1, this);
        }
        if (!this.applied && !getDisposed$runtime_release()) {
            int id2 = getId();
            synchronized (SnapshotKt.getLock()) {
                i2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i2 + 1;
                setId$runtime_release(i2);
                snapshotIdSet2 = SnapshotKt.openSnapshots;
                SnapshotKt.openSnapshots = snapshotIdSet2.set(getId());
            }
            setInvalid$runtime_release(SnapshotKt.addRange(id2 + 1, getId(), getInvalid$runtime_release()));
        }
        return nestedReadonlySnapshot;
    }

    public final void validateNotAppliedOrPinned$runtime_release() {
        int i;
        boolean z;
        boolean z2 = true;
        if (this.applied) {
            i = ((Snapshot) this).pinningTrackingHandle;
            if (i >= 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                z2 = false;
            }
        }
        if (z2) {
            return;
        }
        throw new IllegalStateException("Unsupported operation on a disposed or applied snapshot".toString());
    }
}
