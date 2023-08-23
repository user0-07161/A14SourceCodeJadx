package androidx.compose.ui.node;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.layout.MeasurePolicy;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class IntrinsicsPolicy {
    private final ParcelableSnapshotMutableState measurePolicyState$delegate;

    public IntrinsicsPolicy(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.measurePolicyState$delegate = SnapshotStateKt.mutableStateOf$default(null);
    }

    public final void updateFrom(MeasurePolicy measurePolicy) {
        Intrinsics.checkNotNullParameter(measurePolicy, "measurePolicy");
        this.measurePolicyState$delegate.setValue(measurePolicy);
    }
}
