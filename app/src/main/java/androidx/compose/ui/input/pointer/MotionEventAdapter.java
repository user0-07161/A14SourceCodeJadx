package androidx.compose.ui.input.pointer;

import android.util.SparseBooleanArray;
import android.util.SparseLongArray;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MotionEventAdapter {
    private long nextId;
    private final SparseLongArray motionEventToComposePointerIdMap = new SparseLongArray();
    private final SparseBooleanArray canHover = new SparseBooleanArray();
    private final List pointers = new ArrayList();
    private int previousToolType = -1;
    private int previousSource = -1;

    /* JADX WARN: Code restructure failed: missing block: B:74:0x0155, code lost:
        if (r6 != 4) goto L59;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x016f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.ui.input.pointer.PointerInputEvent convertToPointerInputEvent$ui_release(android.view.MotionEvent r38, androidx.compose.ui.input.pointer.PositionCalculator r39) {
        /*
            Method dump skipped, instructions count: 623
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.MotionEventAdapter.convertToPointerInputEvent$ui_release(android.view.MotionEvent, androidx.compose.ui.input.pointer.PositionCalculator):androidx.compose.ui.input.pointer.PointerInputEvent");
    }

    public final void endStream(int i) {
        this.canHover.delete(i);
        this.motionEventToComposePointerIdMap.delete(i);
    }
}
