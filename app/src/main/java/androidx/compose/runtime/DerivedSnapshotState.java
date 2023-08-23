package androidx.compose.runtime;

import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateRecord;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DerivedSnapshotState implements StateObject, DerivedState {
    private final Function0 calculation;
    private final SnapshotMutationPolicy policy = null;
    private ResultRecord first = new ResultRecord();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ResultRecord extends StateRecord {
        private static final Object Unset = new Object();
        private IdentityArrayMap dependencies;
        private Object result = Unset;
        private int resultHash;

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final void assign(StateRecord value) {
            Intrinsics.checkNotNullParameter(value, "value");
            ResultRecord resultRecord = (ResultRecord) value;
            this.dependencies = resultRecord.dependencies;
            this.result = resultRecord.result;
            this.resultHash = resultRecord.resultHash;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final StateRecord create() {
            return new ResultRecord();
        }

        public final IdentityArrayMap getDependencies() {
            return this.dependencies;
        }

        public final Object getResult() {
            return this.result;
        }

        public final boolean isValid(DerivedState derivedState, Snapshot snapshot) {
            Intrinsics.checkNotNullParameter(derivedState, "derivedState");
            if (this.result != Unset && this.resultHash == readableHash(derivedState, snapshot)) {
                return true;
            }
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0047 A[Catch: all -> 0x00a2, TryCatch #1 {all -> 0x00a2, blocks: (B:16:0x0040, B:18:0x0047, B:21:0x0063, B:23:0x0067, B:25:0x0076, B:24:0x006e), top: B:48:0x0040 }] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x008d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int readableHash(androidx.compose.runtime.DerivedState r9, androidx.compose.runtime.snapshots.Snapshot r10) {
            /*
                r8 = this;
                java.lang.String r0 = "derivedState"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                java.lang.Object r0 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()
                monitor-enter(r0)
                androidx.compose.runtime.collection.IdentityArrayMap r8 = r8.dependencies     // Catch: java.lang.Throwable -> Lc0
                monitor-exit(r0)
                r0 = 7
                if (r8 == 0) goto Lbf
                androidx.compose.runtime.SnapshotThreadLocal r1 = androidx.compose.runtime.SnapshotStateKt__DerivedStateKt.access$getDerivedStateObservers$p()
                java.lang.Object r1 = r1.get()
                androidx.compose.runtime.collection.MutableVector r1 = (androidx.compose.runtime.collection.MutableVector) r1
                r2 = 0
                if (r1 != 0) goto L24
                androidx.compose.runtime.collection.MutableVector r1 = new androidx.compose.runtime.collection.MutableVector
                kotlin.Pair[] r3 = new kotlin.Pair[r2]
                r1.<init>(r3)
            L24:
                int r3 = r1.getSize()
                r4 = 1
                if (r3 <= 0) goto L40
                java.lang.Object[] r5 = r1.getContent()
                r6 = r2
            L30:
                r7 = r5[r6]
                kotlin.Pair r7 = (kotlin.Pair) r7
                java.lang.Object r7 = r7.component1()
                kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
                r7.invoke(r9)
                int r6 = r6 + r4
                if (r6 < r3) goto L30
            L40:
                int r3 = r8.getSize$runtime_release()     // Catch: java.lang.Throwable -> La2
                r5 = r2
            L45:
                if (r5 >= r3) goto L87
                java.lang.Object[] r6 = r8.getKeys$runtime_release()     // Catch: java.lang.Throwable -> La2
                r6 = r6[r5]     // Catch: java.lang.Throwable -> La2
                java.lang.String r7 = "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r7)     // Catch: java.lang.Throwable -> La2
                java.lang.Object[] r7 = r8.getValues$runtime_release()     // Catch: java.lang.Throwable -> La2
                r7 = r7[r5]     // Catch: java.lang.Throwable -> La2
                java.lang.Number r7 = (java.lang.Number) r7     // Catch: java.lang.Throwable -> La2
                int r7 = r7.intValue()     // Catch: java.lang.Throwable -> La2
                androidx.compose.runtime.snapshots.StateObject r6 = (androidx.compose.runtime.snapshots.StateObject) r6     // Catch: java.lang.Throwable -> La2
                if (r7 == r4) goto L63
                goto L84
            L63:
                boolean r7 = r6 instanceof androidx.compose.runtime.DerivedSnapshotState     // Catch: java.lang.Throwable -> La2
                if (r7 == 0) goto L6e
                androidx.compose.runtime.DerivedSnapshotState r6 = (androidx.compose.runtime.DerivedSnapshotState) r6     // Catch: java.lang.Throwable -> La2
                androidx.compose.runtime.DerivedSnapshotState$ResultRecord r6 = r6.current(r10)     // Catch: java.lang.Throwable -> La2
                goto L76
            L6e:
                androidx.compose.runtime.snapshots.StateRecord r6 = r6.getFirstStateRecord()     // Catch: java.lang.Throwable -> La2
                androidx.compose.runtime.snapshots.StateRecord r6 = androidx.compose.runtime.snapshots.SnapshotKt.current(r6, r10)     // Catch: java.lang.Throwable -> La2
            L76:
                int r0 = r0 * 31
                int r7 = java.lang.System.identityHashCode(r6)     // Catch: java.lang.Throwable -> La2
                int r0 = r0 + r7
                int r0 = r0 * 31
                int r6 = r6.getSnapshotId$runtime_release()     // Catch: java.lang.Throwable -> La2
                int r0 = r0 + r6
            L84:
                int r5 = r5 + 1
                goto L45
            L87:
                int r8 = r1.getSize()
                if (r8 <= 0) goto Lbf
                java.lang.Object[] r10 = r1.getContent()
            L91:
                r1 = r10[r2]
                kotlin.Pair r1 = (kotlin.Pair) r1
                java.lang.Object r1 = r1.component2()
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                r1.invoke(r9)
                int r2 = r2 + r4
                if (r2 < r8) goto L91
                goto Lbf
            La2:
                r8 = move-exception
                int r10 = r1.getSize()
                if (r10 <= 0) goto Lbe
                java.lang.Object[] r0 = r1.getContent()
            Lad:
                r1 = r0[r2]
                kotlin.Pair r1 = (kotlin.Pair) r1
                java.lang.Object r1 = r1.component2()
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                r1.invoke(r9)
                int r2 = r2 + r4
                if (r2 >= r10) goto Lbe
                goto Lad
            Lbe:
                throw r8
            Lbf:
                return r0
            Lc0:
                r8 = move-exception
                monitor-exit(r0)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.DerivedSnapshotState.ResultRecord.readableHash(androidx.compose.runtime.DerivedState, androidx.compose.runtime.snapshots.Snapshot):int");
        }

        public final void setDependencies(IdentityArrayMap identityArrayMap) {
            this.dependencies = identityArrayMap;
        }

        public final void setResult(Object obj) {
            this.result = obj;
        }

        public final void setResultHash(int i) {
            this.resultHash = i;
        }
    }

    public DerivedSnapshotState(Function0 function0) {
        this.calculation = function0;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048 A[Catch: all -> 0x00ae, TryCatch #2 {all -> 0x00ae, blocks: (B:13:0x0038, B:15:0x0048, B:18:0x0050, B:20:0x0057, B:22:0x0082, B:23:0x0085, B:24:0x0088), top: B:89:0x0038 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0050 A[Catch: all -> 0x00ae, TryCatch #2 {all -> 0x00ae, blocks: (B:13:0x0038, B:15:0x0048, B:18:0x0050, B:20:0x0057, B:22:0x0082, B:23:0x0085, B:24:0x0088), top: B:89:0x0038 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0152 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.compose.runtime.DerivedSnapshotState.ResultRecord currentRecord(androidx.compose.runtime.DerivedSnapshotState.ResultRecord r9, androidx.compose.runtime.snapshots.Snapshot r10, boolean r11, kotlin.jvm.functions.Function0 r12) {
        /*
            Method dump skipped, instructions count: 445
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.DerivedSnapshotState.currentRecord(androidx.compose.runtime.DerivedSnapshotState$ResultRecord, androidx.compose.runtime.snapshots.Snapshot, boolean, kotlin.jvm.functions.Function0):androidx.compose.runtime.DerivedSnapshotState$ResultRecord");
    }

    public final ResultRecord current(Snapshot snapshot) {
        return currentRecord((ResultRecord) SnapshotKt.current(this.first, snapshot), snapshot, false, this.calculation);
    }

    @Override // androidx.compose.runtime.DerivedState
    public final Object getCurrentValue() {
        return currentRecord((ResultRecord) SnapshotKt.current(this.first), SnapshotKt.currentSnapshot(), false, this.calculation).getResult();
    }

    @Override // androidx.compose.runtime.DerivedState
    public final Object[] getDependencies() {
        Object[] keys$runtime_release;
        IdentityArrayMap dependencies = currentRecord((ResultRecord) SnapshotKt.current(this.first), SnapshotKt.currentSnapshot(), false, this.calculation).getDependencies();
        if (dependencies == null || (keys$runtime_release = dependencies.getKeys$runtime_release()) == null) {
            return new Object[0];
        }
        return keys$runtime_release;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord getFirstStateRecord() {
        return this.first;
    }

    @Override // androidx.compose.runtime.DerivedState
    public final SnapshotMutationPolicy getPolicy() {
        return this.policy;
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        Function1 readObserver$runtime_release = SnapshotKt.currentSnapshot().getReadObserver$runtime_release();
        if (readObserver$runtime_release != null) {
            readObserver$runtime_release.invoke(this);
        }
        return currentRecord((ResultRecord) SnapshotKt.current(this.first), SnapshotKt.currentSnapshot(), true, this.calculation).getResult();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final void prependStateRecord(StateRecord stateRecord) {
        this.first = (ResultRecord) stateRecord;
    }

    public final String toString() {
        String str;
        ResultRecord resultRecord = (ResultRecord) SnapshotKt.current(this.first);
        StringBuilder sb = new StringBuilder("DerivedState(value=");
        ResultRecord resultRecord2 = (ResultRecord) SnapshotKt.current(this.first);
        if (resultRecord2.isValid(this, SnapshotKt.currentSnapshot())) {
            str = String.valueOf(resultRecord2.getResult());
        } else {
            str = "<Not calculated>";
        }
        sb.append(str);
        sb.append(")@");
        sb.append(hashCode());
        return sb.toString();
    }
}
