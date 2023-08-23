package androidx.customview.poolingcontainer;

import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PoolingContainerListenerHolder {
    private final ArrayList listeners = new ArrayList();

    public final void addListener(PoolingContainerListener poolingContainerListener) {
        this.listeners.add(poolingContainerListener);
    }

    public final void onRelease() {
        ArrayList arrayList = this.listeners;
        for (int lastIndex = CollectionsKt.getLastIndex(arrayList); -1 < lastIndex; lastIndex--) {
            ((PoolingContainerListener) arrayList.get(lastIndex)).onRelease();
        }
    }

    public final void removeListener(PoolingContainerListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.remove(listener);
    }
}
