package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.SnapshotThreadLocal;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SnapshotKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final List applyObservers;
    private static final AtomicReference currentGlobalSnapshot;
    private static final List globalWriteObservers;
    private static int nextSnapshotId;
    private static SnapshotIdSet openSnapshots;
    private static final SnapshotDoubleIndexHeap pinningTable;
    private static final Snapshot snapshotInitializer;
    private static final Function1 emptyLambda = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            SnapshotIdSet it = (SnapshotIdSet) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return Unit.INSTANCE;
        }
    };
    private static final SnapshotThreadLocal threadSnapshot = new SnapshotThreadLocal();
    private static final Object lock = new Object();

    static {
        SnapshotIdSet snapshotIdSet;
        SnapshotIdSet snapshotIdSet2;
        int i = SnapshotIdSet.$r8$clinit;
        snapshotIdSet = SnapshotIdSet.EMPTY;
        openSnapshots = snapshotIdSet;
        nextSnapshotId = 1;
        pinningTable = new SnapshotDoubleIndexHeap();
        applyObservers = new ArrayList();
        globalWriteObservers = new ArrayList();
        int i2 = nextSnapshotId;
        nextSnapshotId = i2 + 1;
        snapshotIdSet2 = SnapshotIdSet.EMPTY;
        GlobalSnapshot globalSnapshot = new GlobalSnapshot(i2, snapshotIdSet2);
        openSnapshots = openSnapshots.set(globalSnapshot.getId());
        AtomicReference atomicReference = new AtomicReference(globalSnapshot);
        currentGlobalSnapshot = atomicReference;
        Object obj = atomicReference.get();
        Intrinsics.checkNotNullExpressionValue(obj, "currentGlobalSnapshot.get()");
        snapshotInitializer = (Snapshot) obj;
    }

    public static final Function1 access$mergedReadObserver(Function1 function1, Function1 function12, boolean z) {
        if (!z) {
            function12 = null;
        }
        if (function1 != null && function12 != null && !Intrinsics.areEqual(function1, function12)) {
            return new SnapshotKt$mergedReadObserver$1(function1, function12);
        }
        if (function1 == null) {
            return function12;
        }
        return function1;
    }

    public static final Function1 access$mergedWriteObserver(final Function1 function1, final Function1 function12) {
        if (function1 != null && function12 != null && !Intrinsics.areEqual(function1, function12)) {
            return new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$mergedWriteObserver$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object state) {
                    Intrinsics.checkNotNullParameter(state, "state");
                    Function1.this.invoke(state);
                    function12.invoke(state);
                    return Unit.INSTANCE;
                }
            };
        }
        if (function1 == null) {
            return function12;
        }
        return function1;
    }

    public static final Map access$optimisticMerges(MutableSnapshot mutableSnapshot, MutableSnapshot mutableSnapshot2, SnapshotIdSet snapshotIdSet) {
        StateRecord readable;
        Set<StateObject> modified$runtime_release = mutableSnapshot2.getModified$runtime_release();
        int id = mutableSnapshot.getId();
        if (modified$runtime_release == null) {
            return null;
        }
        SnapshotIdSet or = mutableSnapshot2.getInvalid$runtime_release().set(mutableSnapshot2.getId()).or(mutableSnapshot2.getPreviousIds$runtime_release());
        HashMap hashMap = null;
        for (StateObject stateObject : modified$runtime_release) {
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            StateRecord readable2 = readable(firstStateRecord, id, snapshotIdSet);
            if (readable2 != null && (readable = readable(firstStateRecord, id, or)) != null && !Intrinsics.areEqual(readable2, readable)) {
                StateRecord readable3 = readable(firstStateRecord, mutableSnapshot2.getId(), mutableSnapshot2.getInvalid$runtime_release());
                if (readable3 != null) {
                    StateRecord mergeRecords = stateObject.mergeRecords(readable, readable2, readable3);
                    if (mergeRecords == null) {
                        return null;
                    }
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    hashMap.put(readable2, mergeRecords);
                } else {
                    readError();
                    throw null;
                }
            }
        }
        return hashMap;
    }

    public static final /* synthetic */ void access$readError() {
        readError();
        throw null;
    }

    public static final Snapshot access$takeNewSnapshot(final Function1 function1) {
        return (Snapshot) advanceGlobalSnapshot(new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$takeNewSnapshot$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SnapshotIdSet snapshotIdSet;
                SnapshotIdSet invalid = (SnapshotIdSet) obj;
                Intrinsics.checkNotNullParameter(invalid, "invalid");
                Snapshot snapshot = (Snapshot) Function1.this.invoke(invalid);
                synchronized (SnapshotKt.getLock()) {
                    snapshotIdSet = SnapshotKt.openSnapshots;
                    SnapshotKt.openSnapshots = snapshotIdSet.set(snapshot.getId());
                }
                return snapshot;
            }
        });
    }

    public static final void access$validateOpen(Snapshot snapshot) {
        if (openSnapshots.get(snapshot.getId())) {
            return;
        }
        throw new IllegalStateException("Snapshot is not open".toString());
    }

    public static final SnapshotIdSet addRange(int i, int i2, SnapshotIdSet snapshotIdSet) {
        Intrinsics.checkNotNullParameter(snapshotIdSet, "<this>");
        while (i < i2) {
            snapshotIdSet = snapshotIdSet.set(i);
            i++;
        }
        return snapshotIdSet;
    }

    public static final Object advanceGlobalSnapshot(Function1 function1) {
        Object obj;
        Object takeNewGlobalSnapshot;
        List mutableList;
        Snapshot snapshot = snapshotInitializer;
        Intrinsics.checkNotNull(snapshot, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.GlobalSnapshot");
        GlobalSnapshot globalSnapshot = (GlobalSnapshot) snapshot;
        Object obj2 = lock;
        synchronized (obj2) {
            obj = currentGlobalSnapshot.get();
            Intrinsics.checkNotNullExpressionValue(obj, "currentGlobalSnapshot.get()");
            takeNewGlobalSnapshot = takeNewGlobalSnapshot((Snapshot) obj, function1);
        }
        Set<StateObject> modified$runtime_release = ((GlobalSnapshot) obj).getModified$runtime_release();
        if (modified$runtime_release != null) {
            synchronized (obj2) {
                mutableList = CollectionsKt.toMutableList(applyObservers);
            }
            ArrayList arrayList = (ArrayList) mutableList;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Function2) arrayList.get(i)).invoke(modified$runtime_release, obj);
            }
        }
        synchronized (lock) {
            if (modified$runtime_release != null) {
                for (StateObject stateObject : modified$runtime_release) {
                    overwriteUnusedRecordsLocked(stateObject);
                }
            }
        }
        return takeNewGlobalSnapshot;
    }

    public static final Snapshot createTransparentSnapshotWithNoParentReadObserver(Snapshot snapshot, Function1 function1, boolean z) {
        MutableSnapshot mutableSnapshot;
        boolean z2 = snapshot instanceof MutableSnapshot;
        if (!z2 && snapshot != null) {
            return new TransparentObserverSnapshot(snapshot, function1, z);
        }
        if (z2) {
            mutableSnapshot = (MutableSnapshot) snapshot;
        } else {
            mutableSnapshot = null;
        }
        return new TransparentObserverMutableSnapshot(mutableSnapshot, function1, null, false, z);
    }

    public static final StateRecord current(StateRecord r) {
        StateRecord readable;
        Intrinsics.checkNotNullParameter(r, "r");
        Snapshot currentSnapshot = currentSnapshot();
        StateRecord readable2 = readable(r, currentSnapshot.getId(), currentSnapshot.getInvalid$runtime_release());
        if (readable2 == null) {
            synchronized (lock) {
                Snapshot currentSnapshot2 = currentSnapshot();
                readable = readable(r, currentSnapshot2.getId(), currentSnapshot2.getInvalid$runtime_release());
            }
            if (readable != null) {
                return readable;
            }
            readError();
            throw null;
        }
        return readable2;
    }

    public static final Snapshot currentSnapshot() {
        Snapshot snapshot = (Snapshot) threadSnapshot.get();
        if (snapshot == null) {
            Object obj = currentGlobalSnapshot.get();
            Intrinsics.checkNotNullExpressionValue(obj, "currentGlobalSnapshot.get()");
            return (Snapshot) obj;
        }
        return snapshot;
    }

    public static final Object getLock() {
        return lock;
    }

    public static Function1 mergedReadObserver$default(Function1 function1, Function1 function12) {
        if (function1 != null && function12 != null && !Intrinsics.areEqual(function1, function12)) {
            return new SnapshotKt$mergedReadObserver$1(function1, function12);
        }
        if (function1 == null) {
            return function12;
        }
        return function1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x0048, code lost:
        r3 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.runtime.snapshots.StateRecord newOverwritableRecordLocked(androidx.compose.runtime.snapshots.StateRecord r6, androidx.compose.runtime.snapshots.StateObject r7) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "state"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            androidx.compose.runtime.snapshots.StateRecord r0 = r7.getFirstStateRecord()
            androidx.compose.runtime.snapshots.SnapshotDoubleIndexHeap r1 = androidx.compose.runtime.snapshots.SnapshotKt.pinningTable
            int r2 = androidx.compose.runtime.snapshots.SnapshotKt.nextSnapshotId
            int r1 = r1.lowestOrDefault(r2)
            int r1 = r1 + (-1)
            androidx.compose.runtime.snapshots.SnapshotIdSet r2 = androidx.compose.runtime.snapshots.SnapshotIdSet.access$getEMPTY$cp()
            r3 = 0
            r4 = r3
        L1e:
            if (r0 == 0) goto L51
            int r5 = r0.getSnapshotId$runtime_release()
            if (r5 != 0) goto L27
            goto L48
        L27:
            int r5 = r0.getSnapshotId$runtime_release()
            if (r5 == 0) goto L37
            if (r5 > r1) goto L37
            boolean r5 = r2.get(r5)
            if (r5 != 0) goto L37
            r5 = 1
            goto L38
        L37:
            r5 = 0
        L38:
            if (r5 == 0) goto L4c
            if (r4 != 0) goto L3e
            r4 = r0
            goto L4c
        L3e:
            int r1 = r0.getSnapshotId$runtime_release()
            int r2 = r4.getSnapshotId$runtime_release()
            if (r1 >= r2) goto L4a
        L48:
            r3 = r0
            goto L51
        L4a:
            r3 = r4
            goto L51
        L4c:
            androidx.compose.runtime.snapshots.StateRecord r0 = r0.getNext$runtime_release()
            goto L1e
        L51:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == 0) goto L5a
            r3.setSnapshotId$runtime_release(r0)
            goto L6b
        L5a:
            androidx.compose.runtime.snapshots.StateRecord r3 = r6.create()
            r3.setSnapshotId$runtime_release(r0)
            androidx.compose.runtime.snapshots.StateRecord r6 = r7.getFirstStateRecord()
            r3.setNext$runtime_release(r6)
            r7.prependStateRecord(r3)
        L6b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotKt.newOverwritableRecordLocked(androidx.compose.runtime.snapshots.StateRecord, androidx.compose.runtime.snapshots.StateObject):androidx.compose.runtime.snapshots.StateRecord");
    }

    public static final StateRecord newWritableRecord(StateRecord stateRecord, StateObject state, Snapshot snapshot) {
        StateRecord newOverwritableRecordLocked;
        Intrinsics.checkNotNullParameter(stateRecord, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        synchronized (lock) {
            newOverwritableRecordLocked = newOverwritableRecordLocked(stateRecord, state);
            newOverwritableRecordLocked.assign(stateRecord);
            newOverwritableRecordLocked.setSnapshotId$runtime_release(snapshot.getId());
        }
        return newOverwritableRecordLocked;
    }

    public static final void notifyWrite(Snapshot snapshot, StateObject state) {
        Intrinsics.checkNotNullParameter(state, "state");
        Function1 writeObserver$runtime_release = snapshot.getWriteObserver$runtime_release();
        if (writeObserver$runtime_release != null) {
            writeObserver$runtime_release.invoke(state);
        }
    }

    public static final StateRecord overwritableRecord(StateRecord stateRecord, StateObject state, Snapshot snapshot, StateRecord stateRecord2) {
        StateRecord newOverwritableRecordLocked;
        Intrinsics.checkNotNullParameter(stateRecord, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        if (snapshot.getReadOnly()) {
            snapshot.recordModified$runtime_release(state);
        }
        int id = snapshot.getId();
        if (stateRecord2.getSnapshotId$runtime_release() == id) {
            return stateRecord2;
        }
        synchronized (lock) {
            newOverwritableRecordLocked = newOverwritableRecordLocked(stateRecord, state);
        }
        newOverwritableRecordLocked.setSnapshotId$runtime_release(id);
        snapshot.recordModified$runtime_release(state);
        return newOverwritableRecordLocked;
    }

    public static final boolean overwriteUnusedRecordsLocked(StateObject stateObject) {
        StateRecord stateRecord;
        int lowestOrDefault = pinningTable.lowestOrDefault(nextSnapshotId) - 1;
        StateRecord stateRecord2 = null;
        int i = 0;
        for (StateRecord firstStateRecord = stateObject.getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext$runtime_release()) {
            int snapshotId$runtime_release = firstStateRecord.getSnapshotId$runtime_release();
            if (snapshotId$runtime_release != 0) {
                if (snapshotId$runtime_release <= lowestOrDefault) {
                    if (stateRecord2 == null) {
                        stateRecord2 = firstStateRecord;
                    } else {
                        if (firstStateRecord.getSnapshotId$runtime_release() < stateRecord2.getSnapshotId$runtime_release()) {
                            stateRecord = stateRecord2;
                            stateRecord2 = firstStateRecord;
                        } else {
                            stateRecord = firstStateRecord;
                        }
                        stateRecord2.setSnapshotId$runtime_release(0);
                        stateRecord2.assign(stateRecord);
                        stateRecord2 = stateRecord;
                    }
                } else {
                    i++;
                }
            }
        }
        if (i < 1) {
            return true;
        }
        return false;
    }

    private static final void readError() {
        throw new IllegalStateException("Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied".toString());
    }

    public static final StateRecord readable(StateRecord stateRecord, StateObject state) {
        StateRecord readable;
        Intrinsics.checkNotNullParameter(stateRecord, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Snapshot currentSnapshot = currentSnapshot();
        Function1 readObserver$runtime_release = currentSnapshot.getReadObserver$runtime_release();
        if (readObserver$runtime_release != null) {
            readObserver$runtime_release.invoke(state);
        }
        StateRecord readable2 = readable(stateRecord, currentSnapshot.getId(), currentSnapshot.getInvalid$runtime_release());
        if (readable2 == null) {
            synchronized (lock) {
                Snapshot currentSnapshot2 = currentSnapshot();
                StateRecord firstStateRecord = state.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.readable$lambda$7");
                readable = readable(firstStateRecord, currentSnapshot2.getId(), currentSnapshot2.getInvalid$runtime_release());
                if (readable == null) {
                    readError();
                    throw null;
                }
            }
            return readable;
        }
        return readable2;
    }

    public static final void releasePinningLocked(int i) {
        pinningTable.remove(i);
    }

    public static final Object takeNewGlobalSnapshot(Snapshot snapshot, Function1 function1) {
        Object invoke = function1.invoke(openSnapshots.clear(snapshot.getId()));
        synchronized (lock) {
            int i = nextSnapshotId;
            nextSnapshotId = i + 1;
            SnapshotIdSet clear = openSnapshots.clear(snapshot.getId());
            openSnapshots = clear;
            currentGlobalSnapshot.set(new GlobalSnapshot(i, clear));
            snapshot.dispose();
            openSnapshots = openSnapshots.set(i);
        }
        return invoke;
    }

    public static final int trackPinning(int i, SnapshotIdSet invalid) {
        int add;
        Intrinsics.checkNotNullParameter(invalid, "invalid");
        int lowest = invalid.lowest(i);
        synchronized (lock) {
            add = pinningTable.add(lowest);
        }
        return add;
    }

    public static final StateRecord writableRecord(SnapshotStateList.StateListStateRecord stateListStateRecord, StateObject state, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (snapshot.getReadOnly()) {
            snapshot.recordModified$runtime_release(state);
        }
        StateRecord readable = readable(stateListStateRecord, snapshot.getId(), snapshot.getInvalid$runtime_release());
        if (readable != null) {
            if (readable.getSnapshotId$runtime_release() == snapshot.getId()) {
                return readable;
            }
            StateRecord newWritableRecord = newWritableRecord(readable, state, snapshot);
            snapshot.recordModified$runtime_release(state);
            return newWritableRecord;
        }
        readError();
        throw null;
    }

    public static final StateRecord current(StateRecord r, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(r, "r");
        StateRecord readable = readable(r, snapshot.getId(), snapshot.getInvalid$runtime_release());
        if (readable != null) {
            return readable;
        }
        readError();
        throw null;
    }

    public static final StateRecord readable(StateRecord stateRecord, int i, SnapshotIdSet snapshotIdSet) {
        StateRecord stateRecord2 = null;
        while (stateRecord != null) {
            int snapshotId$runtime_release = stateRecord.getSnapshotId$runtime_release();
            if (((snapshotId$runtime_release == 0 || snapshotId$runtime_release > i || snapshotIdSet.get(snapshotId$runtime_release)) ? false : true) && (stateRecord2 == null || stateRecord2.getSnapshotId$runtime_release() < stateRecord.getSnapshotId$runtime_release())) {
                stateRecord2 = stateRecord;
            }
            stateRecord = stateRecord.getNext$runtime_release();
        }
        if (stateRecord2 != null) {
            return stateRecord2;
        }
        return null;
    }
}
