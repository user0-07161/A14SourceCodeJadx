package androidx.compose.runtime;

import androidx.compose.runtime.collection.MutableVector;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract /* synthetic */ class SnapshotStateKt__DerivedStateKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final SnapshotThreadLocal calculationBlockNestedLevel = new SnapshotThreadLocal();
    private static final SnapshotThreadLocal derivedStateObservers = new SnapshotThreadLocal();

    public static final void observeDerivedStateRecalculations(Function1 start, Function1 done, Function0 function0) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(done, "done");
        SnapshotThreadLocal snapshotThreadLocal = derivedStateObservers;
        MutableVector mutableVector = (MutableVector) snapshotThreadLocal.get();
        if (mutableVector == null) {
            mutableVector = new MutableVector(new Pair[16]);
            snapshotThreadLocal.set(mutableVector);
        }
        try {
            mutableVector.add(new Pair(start, done));
            function0.invoke();
        } finally {
            mutableVector.removeAt(mutableVector.getSize() - 1);
        }
    }
}
