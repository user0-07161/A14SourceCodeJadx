package androidx.compose.ui.input.pointer;

import androidx.compose.ui.platform.AndroidComposeView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PointerInputChangeEventProducer {
    private final Map previousPointerInputData = new LinkedHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class PointerInputData {
        private final boolean down;
        private final long positionOnScreen;
        private final long uptime;

        public PointerInputData(long j, long j2, boolean z) {
            this.uptime = j;
            this.positionOnScreen = j2;
            this.down = z;
        }

        public final boolean getDown() {
            return this.down;
        }

        /* renamed from: getPositionOnScreen-F1C5BW0  reason: not valid java name */
        public final long m193getPositionOnScreenF1C5BW0() {
            return this.positionOnScreen;
        }

        public final long getUptime() {
            return this.uptime;
        }
    }

    public final void clear() {
        ((LinkedHashMap) this.previousPointerInputData).clear();
    }

    public final InternalPointerEvent produce(PointerInputEvent pointerInputEvent, PositionCalculator positionCalculator) {
        long j;
        boolean z;
        long m279screenToLocalMKHz9U;
        Intrinsics.checkNotNullParameter(positionCalculator, "positionCalculator");
        LinkedHashMap linkedHashMap = new LinkedHashMap(pointerInputEvent.getPointers().size());
        List pointers = pointerInputEvent.getPointers();
        int size = pointers.size();
        for (int i = 0; i < size; i++) {
            PointerInputEventData pointerInputEventData = (PointerInputEventData) pointers.get(i);
            Map map = this.previousPointerInputData;
            PointerInputData pointerInputData = (PointerInputData) ((LinkedHashMap) map).get(PointerId.m184boximpl(pointerInputEventData.m194getIdJ3iCeTQ()));
            if (pointerInputData == null) {
                j = pointerInputEventData.getUptime();
                m279screenToLocalMKHz9U = pointerInputEventData.m195getPositionF1C5BW0();
                z = false;
            } else {
                long uptime = pointerInputData.getUptime();
                boolean down = pointerInputData.getDown();
                j = uptime;
                z = down;
                m279screenToLocalMKHz9U = ((AndroidComposeView) positionCalculator).m279screenToLocalMKHz9U(pointerInputData.m193getPositionOnScreenF1C5BW0());
            }
            linkedHashMap.put(PointerId.m184boximpl(pointerInputEventData.m194getIdJ3iCeTQ()), new PointerInputChange(pointerInputEventData.m194getIdJ3iCeTQ(), pointerInputEventData.getUptime(), pointerInputEventData.m195getPositionF1C5BW0(), pointerInputEventData.getDown(), pointerInputEventData.getPressure(), j, m279screenToLocalMKHz9U, z, pointerInputEventData.m198getTypeT8wyACA(), pointerInputEventData.getHistorical(), pointerInputEventData.m197getScrollDeltaF1C5BW0()));
            if (pointerInputEventData.getDown()) {
                map.put(PointerId.m184boximpl(pointerInputEventData.m194getIdJ3iCeTQ()), new PointerInputData(pointerInputEventData.getUptime(), pointerInputEventData.m196getPositionOnScreenF1C5BW0(), pointerInputEventData.getDown()));
            } else {
                map.remove(PointerId.m184boximpl(pointerInputEventData.m194getIdJ3iCeTQ()));
            }
        }
        return new InternalPointerEvent(linkedHashMap, pointerInputEvent);
    }
}
