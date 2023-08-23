package androidx.compose.runtime.snapshots;

import java.util.ArrayList;
import java.util.List;
import kotlin.Function;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Snapshot$Companion$registerApplyObserver$2 implements ObserverHandle {
    final /* synthetic */ Function $observer;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ Snapshot$Companion$registerApplyObserver$2(Lambda lambda, int i) {
        this.$r8$classId = i;
        this.$observer = lambda;
    }

    @Override // androidx.compose.runtime.snapshots.ObserverHandle
    public final void dispose() {
        List list;
        List list2;
        switch (this.$r8$classId) {
            case 0:
                Function2 function2 = (Function2) this.$observer;
                synchronized (SnapshotKt.getLock()) {
                    list2 = SnapshotKt.applyObservers;
                    ((ArrayList) list2).remove(function2);
                }
                return;
            default:
                Function1 function1 = (Function1) this.$observer;
                synchronized (SnapshotKt.getLock()) {
                    list = SnapshotKt.globalWriteObservers;
                    list.remove(function1);
                }
                SnapshotKt.advanceGlobalSnapshot(new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$advanceGlobalSnapshot$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        SnapshotIdSet it = (SnapshotIdSet) obj;
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Unit.INSTANCE;
                    }
                });
                return;
        }
    }
}
