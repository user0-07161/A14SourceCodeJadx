package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class InternalPointerEvent {
    private final Map changes;
    private final PointerInputEvent pointerInputEvent;

    public InternalPointerEvent(Map map, PointerInputEvent pointerInputEvent) {
        this.changes = map;
        this.pointerInputEvent = pointerInputEvent;
    }

    public final Map getChanges() {
        return this.changes;
    }

    public final MotionEvent getMotionEvent() {
        return this.pointerInputEvent.getMotionEvent();
    }

    /* renamed from: issuesEnterExitEvent-0FcD4WY  reason: not valid java name */
    public final boolean m180issuesEnterExitEvent0FcD4WY(long j) {
        Object obj;
        List pointers = this.pointerInputEvent.getPointers();
        int size = pointers.size();
        int i = 0;
        while (true) {
            if (i < size) {
                obj = pointers.get(i);
                if (PointerId.m185equalsimpl0(((PointerInputEventData) obj).m194getIdJ3iCeTQ(), j)) {
                    break;
                }
                i++;
            } else {
                obj = null;
                break;
            }
        }
        PointerInputEventData pointerInputEventData = (PointerInputEventData) obj;
        if (pointerInputEventData == null) {
            return false;
        }
        return pointerInputEventData.getIssuesEnterExit();
    }
}
