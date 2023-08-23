package com.android.egg.landroid;

import androidx.compose.ui.geometry.Offset;
import kotlin.collections.ArrayDeque;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Track {
    public static final int $stable = 8;
    private final ArrayDeque positions = new ArrayDeque(0);
    private final ArrayDeque angles = new ArrayDeque(0);

    public final void add(float f, float f2, float f3) {
        if (this.positions.getSize() >= 9999) {
            this.positions.removeFirst();
            this.angles.removeFirst();
            this.positions.removeFirst();
            this.angles.removeFirst();
        }
        this.positions.addLast(Offset.m42boximpl(Vec2Kt.Vec2(f, f2)));
        this.angles.addLast(Float.valueOf(f3));
    }

    public final ArrayDeque getPositions() {
        return this.positions;
    }
}
