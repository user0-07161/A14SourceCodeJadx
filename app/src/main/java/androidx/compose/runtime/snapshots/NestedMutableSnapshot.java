package androidx.compose.runtime.snapshots;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class NestedMutableSnapshot extends MutableSnapshot {
    private boolean deactivated;
    private final MutableSnapshot parent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NestedMutableSnapshot(int i, SnapshotIdSet invalid, Function1 function1, Function1 function12, MutableSnapshot parent) {
        super(i, invalid, function1, function12);
        Intrinsics.checkNotNullParameter(invalid, "invalid");
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.parent = parent;
        parent.nestedActivated$runtime_release(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0074 A[Catch: all -> 0x00c1, TryCatch #0 {, blocks: (B:13:0x002d, B:15:0x0032, B:18:0x0039, B:22:0x0053, B:24:0x005b, B:25:0x0065, B:27:0x006c, B:29:0x0074, B:30:0x0079, B:26:0x0069), top: B:42:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b6  */
    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.runtime.snapshots.SnapshotApplyResult apply() {
        /*
            r6 = this;
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent
            boolean r0 = r0.getApplied$runtime_release()
            if (r0 != 0) goto Lc4
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent
            boolean r0 = r0.getDisposed$runtime_release()
            if (r0 == 0) goto L12
            goto Lc4
        L12:
            java.util.Set r0 = r6.getModified$runtime_release()
            int r1 = r6.getId()
            if (r0 == 0) goto L27
            androidx.compose.runtime.snapshots.MutableSnapshot r2 = r6.parent
            androidx.compose.runtime.snapshots.SnapshotIdSet r3 = r2.getInvalid$runtime_release()
            java.util.Map r2 = androidx.compose.runtime.snapshots.SnapshotKt.access$optimisticMerges(r2, r6, r3)
            goto L28
        L27:
            r2 = 0
        L28:
            java.lang.Object r3 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()
            monitor-enter(r3)
            androidx.compose.runtime.snapshots.SnapshotKt.access$validateOpen(r6)     // Catch: java.lang.Throwable -> Lc1
            if (r0 == 0) goto L69
            int r4 = r0.size()     // Catch: java.lang.Throwable -> Lc1
            if (r4 != 0) goto L39
            goto L69
        L39:
            androidx.compose.runtime.snapshots.MutableSnapshot r4 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            int r4 = r4.getId()     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.MutableSnapshot r5 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.SnapshotIdSet r5 = r5.getInvalid$runtime_release()     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.SnapshotApplyResult r2 = r6.innerApplyLocked$runtime_release(r4, r2, r5)     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Success r4 = androidx.compose.runtime.snapshots.SnapshotApplyResult.Success.INSTANCE     // Catch: java.lang.Throwable -> Lc1
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)     // Catch: java.lang.Throwable -> Lc1
            if (r4 != 0) goto L53
            monitor-exit(r3)
            return r2
        L53:
            androidx.compose.runtime.snapshots.MutableSnapshot r2 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            java.util.Set r2 = r2.getModified$runtime_release()     // Catch: java.lang.Throwable -> Lc1
            if (r2 != 0) goto L65
            java.util.HashSet r2 = new java.util.HashSet     // Catch: java.lang.Throwable -> Lc1
            r2.<init>()     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.MutableSnapshot r4 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            r4.setModified(r2)     // Catch: java.lang.Throwable -> Lc1
        L65:
            r2.addAll(r0)     // Catch: java.lang.Throwable -> Lc1
            goto L6c
        L69:
            r6.closeAndReleasePinning$runtime_release()     // Catch: java.lang.Throwable -> Lc1
        L6c:
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            int r0 = r0.getId()     // Catch: java.lang.Throwable -> Lc1
            if (r0 >= r1) goto L79
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            r0.advance$runtime_release()     // Catch: java.lang.Throwable -> Lc1
        L79:
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.SnapshotIdSet r2 = r0.getInvalid$runtime_release()     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.SnapshotIdSet r2 = r2.clear(r1)     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.SnapshotIdSet r4 = r6.getPreviousIds$runtime_release()     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.SnapshotIdSet r2 = r2.andNot(r4)     // Catch: java.lang.Throwable -> Lc1
            r0.setInvalid$runtime_release(r2)     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            r0.recordPrevious$runtime_release(r1)     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            int r1 = r6.takeoverPinnedSnapshot$runtime_release()     // Catch: java.lang.Throwable -> Lc1
            r0.recordPreviousPinnedSnapshot$runtime_release(r1)     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.SnapshotIdSet r1 = r6.getPreviousIds$runtime_release()     // Catch: java.lang.Throwable -> Lc1
            r0.recordPreviousList$runtime_release(r1)     // Catch: java.lang.Throwable -> Lc1
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent     // Catch: java.lang.Throwable -> Lc1
            int[] r1 = r6.getPreviousPinnedSnapshots$runtime_release()     // Catch: java.lang.Throwable -> Lc1
            r0.recordPreviousPinnedSnapshots$runtime_release(r1)     // Catch: java.lang.Throwable -> Lc1
            monitor-exit(r3)
            r6.setApplied$runtime_release()
            boolean r0 = r6.deactivated
            if (r0 != 0) goto Lbe
            r0 = 1
            r6.deactivated = r0
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r6.parent
            r0.nestedDeactivated$runtime_release(r6)
        Lbe:
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Success r6 = androidx.compose.runtime.snapshots.SnapshotApplyResult.Success.INSTANCE
            return r6
        Lc1:
            r6 = move-exception
            monitor-exit(r3)
            throw r6
        Lc4:
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Failure r0 = new androidx.compose.runtime.snapshots.SnapshotApplyResult$Failure
            r0.<init>(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.NestedMutableSnapshot.apply():androidx.compose.runtime.snapshots.SnapshotApplyResult");
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public final void dispose() {
        if (!getDisposed$runtime_release()) {
            super.dispose();
            if (!this.deactivated) {
                this.deactivated = true;
                this.parent.nestedDeactivated$runtime_release(this);
            }
        }
    }
}
