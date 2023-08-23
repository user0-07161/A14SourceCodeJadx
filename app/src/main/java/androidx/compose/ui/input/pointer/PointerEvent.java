package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PointerEvent {
    private final int buttons;
    private final List changes;
    private final InternalPointerEvent internalPointerEvent;
    private int type;

    public PointerEvent(List changes, InternalPointerEvent internalPointerEvent) {
        Intrinsics.checkNotNullParameter(changes, "changes");
        this.changes = changes;
        this.internalPointerEvent = internalPointerEvent;
        MotionEvent motionEvent = internalPointerEvent != null ? internalPointerEvent.getMotionEvent() : null;
        int i = 0;
        this.buttons = motionEvent != null ? motionEvent.getButtonState() : 0;
        MotionEvent motionEvent2 = internalPointerEvent != null ? internalPointerEvent.getMotionEvent() : null;
        if (motionEvent2 != null) {
            motionEvent2.getMetaState();
        }
        MotionEvent motionEvent3 = internalPointerEvent != null ? internalPointerEvent.getMotionEvent() : null;
        int i2 = 1;
        if (motionEvent3 != null) {
            int actionMasked = motionEvent3.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 2) {
                        switch (actionMasked) {
                            case 8:
                                i = 6;
                                break;
                            case 9:
                                i = 4;
                                break;
                            case 10:
                                i = 5;
                                break;
                        }
                        i2 = i;
                    }
                    i = 3;
                    i2 = i;
                }
                i = 2;
                i2 = i;
            }
            i = 1;
            i2 = i;
        } else {
            int size = changes.size();
            while (true) {
                if (i >= size) {
                    i2 = 3;
                    break;
                }
                PointerInputChange pointerInputChange = (PointerInputChange) changes.get(i);
                if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                    i2 = 2;
                    break;
                } else if (PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange)) {
                    break;
                } else {
                    i++;
                }
            }
        }
        this.type = i2;
    }

    public final List getChanges() {
        return this.changes;
    }

    /* renamed from: getType-7fucELk  reason: not valid java name */
    public final int m181getType7fucELk() {
        return this.type;
    }

    /* renamed from: setType-EhbLWgg$ui_release  reason: not valid java name */
    public final void m182setTypeEhbLWgg$ui_release(int i) {
        this.type = i;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PointerEvent(List changes) {
        this(changes, null);
        Intrinsics.checkNotNullParameter(changes, "changes");
    }
}
