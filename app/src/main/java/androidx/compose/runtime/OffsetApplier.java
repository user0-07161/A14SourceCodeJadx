package androidx.compose.runtime;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class OffsetApplier implements Applier {
    private final Applier applier;
    private int nesting;
    private final int offset;

    public OffsetApplier(Applier applier, int i) {
        Intrinsics.checkNotNullParameter(applier, "applier");
        this.applier = applier;
        this.offset = i;
    }

    @Override // androidx.compose.runtime.Applier
    public final void clear() {
        ComposerKt.composeRuntimeError("Clear is not valid on OffsetApplier".toString());
        throw null;
    }

    @Override // androidx.compose.runtime.Applier
    public final void down(Object obj) {
        this.nesting++;
        this.applier.down(obj);
    }

    @Override // androidx.compose.runtime.Applier
    public final Object getCurrent() {
        return this.applier.getCurrent();
    }

    @Override // androidx.compose.runtime.Applier
    public final void insertBottomUp(int i, Object obj) {
        int i2;
        if (this.nesting == 0) {
            i2 = this.offset;
        } else {
            i2 = 0;
        }
        this.applier.insertBottomUp(i + i2, obj);
    }

    @Override // androidx.compose.runtime.Applier
    public final void insertTopDown(int i, Object obj) {
        int i2;
        if (this.nesting == 0) {
            i2 = this.offset;
        } else {
            i2 = 0;
        }
        this.applier.insertTopDown(i + i2, obj);
    }

    @Override // androidx.compose.runtime.Applier
    public final void move(int i, int i2, int i3) {
        int i4;
        if (this.nesting == 0) {
            i4 = this.offset;
        } else {
            i4 = 0;
        }
        this.applier.move(i + i4, i2 + i4, i3);
    }

    @Override // androidx.compose.runtime.Applier
    public final void remove(int i, int i2) {
        int i3;
        if (this.nesting == 0) {
            i3 = this.offset;
        } else {
            i3 = 0;
        }
        this.applier.remove(i + i3, i2);
    }

    @Override // androidx.compose.runtime.Applier
    public final void up() {
        boolean z;
        int i = this.nesting;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.nesting = i - 1;
            this.applier.up();
            return;
        }
        ComposerKt.composeRuntimeError("OffsetApplier up called with no corresponding down".toString());
        throw null;
    }
}
