package com.android.egg.landroid;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class VisibleUniverse extends Universe {
    public static final int $stable = 0;
    private final MutableState triggerDraw;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VisibleUniverse(Namer namer, long j) {
        super(namer, j);
        Intrinsics.checkNotNullParameter(namer, "namer");
        this.triggerDraw = SnapshotStateKt.mutableStateOf$default(0L);
    }

    public final MutableState getTriggerDraw() {
        return this.triggerDraw;
    }

    public final void simulateAndDrawFrame(long j) {
        ((SnapshotMutableStateImpl) this.triggerDraw).setValue(Long.valueOf(j));
        step(j);
    }
}
