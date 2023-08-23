package androidx.compose.ui.input.pointer;

import androidx.compose.animation.core.AnimationVector4D$$ExternalSyntheticOutline0;
import androidx.compose.ui.geometry.Offset;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PointerInputEventData {
    private final boolean down;
    private final List historical;
    private final long id;
    private final boolean issuesEnterExit;
    private final long position;
    private final long positionOnScreen;
    private final float pressure;
    private final long scrollDelta;
    private final int type;
    private final long uptime;

    public PointerInputEventData(long j, long j2, long j3, long j4, boolean z, float f, int i, boolean z2, List list, long j5) {
        this.id = j;
        this.uptime = j2;
        this.positionOnScreen = j3;
        this.position = j4;
        this.down = z;
        this.pressure = f;
        this.type = i;
        this.issuesEnterExit = z2;
        this.historical = list;
        this.scrollDelta = j5;
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PointerInputEventData)) {
            return false;
        }
        PointerInputEventData pointerInputEventData = (PointerInputEventData) obj;
        if (!PointerId.m185equalsimpl0(this.id, pointerInputEventData.id) || this.uptime != pointerInputEventData.uptime || !Offset.m43equalsimpl0(this.positionOnScreen, pointerInputEventData.positionOnScreen) || !Offset.m43equalsimpl0(this.position, pointerInputEventData.position) || this.down != pointerInputEventData.down || Float.compare(this.pressure, pointerInputEventData.pressure) != 0) {
            return false;
        }
        if (this.type == pointerInputEventData.type) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.issuesEnterExit == pointerInputEventData.issuesEnterExit && Intrinsics.areEqual(this.historical, pointerInputEventData.historical) && Offset.m43equalsimpl0(this.scrollDelta, pointerInputEventData.scrollDelta)) {
            return true;
        }
        return false;
    }

    public final boolean getDown() {
        return this.down;
    }

    public final List getHistorical() {
        return this.historical;
    }

    /* renamed from: getId-J3iCeTQ  reason: not valid java name */
    public final long m194getIdJ3iCeTQ() {
        return this.id;
    }

    public final boolean getIssuesEnterExit() {
        return this.issuesEnterExit;
    }

    /* renamed from: getPosition-F1C5BW0  reason: not valid java name */
    public final long m195getPositionF1C5BW0() {
        return this.position;
    }

    /* renamed from: getPositionOnScreen-F1C5BW0  reason: not valid java name */
    public final long m196getPositionOnScreenF1C5BW0() {
        return this.positionOnScreen;
    }

    public final float getPressure() {
        return this.pressure;
    }

    /* renamed from: getScrollDelta-F1C5BW0  reason: not valid java name */
    public final long m197getScrollDeltaF1C5BW0() {
        return this.scrollDelta;
    }

    /* renamed from: getType-T8wyACA  reason: not valid java name */
    public final int m198getTypeT8wyACA() {
        return this.type;
    }

    public final long getUptime() {
        return this.uptime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int hashCode() {
        int hashCode = Long.hashCode(this.uptime);
        Offset.Companion companion = Offset.Companion;
        int hashCode2 = (Long.hashCode(this.position) + ((Long.hashCode(this.positionOnScreen) + ((hashCode + (Long.hashCode(this.id) * 31)) * 31)) * 31)) * 31;
        int i = 1;
        boolean z = this.down;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int hashCode3 = (Integer.hashCode(this.type) + AnimationVector4D$$ExternalSyntheticOutline0.m(this.pressure, (hashCode2 + i2) * 31, 31)) * 31;
        boolean z2 = this.issuesEnterExit;
        if (!z2) {
            i = z2 ? 1 : 0;
        }
        return Long.hashCode(this.scrollDelta) + ((this.historical.hashCode() + ((hashCode3 + i) * 31)) * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("PointerInputEventData(id=");
        sb.append((Object) PointerId.m186toStringimpl(this.id));
        sb.append(", uptime=");
        sb.append(this.uptime);
        sb.append(", positionOnScreen=");
        sb.append((Object) Offset.m50toStringimpl(this.positionOnScreen));
        sb.append(", position=");
        sb.append((Object) Offset.m50toStringimpl(this.position));
        sb.append(", down=");
        sb.append(this.down);
        sb.append(", pressure=");
        sb.append(this.pressure);
        sb.append(", type=");
        int i = this.type;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        str = "Unknown";
                    } else {
                        str = "Eraser";
                    }
                } else {
                    str = "Stylus";
                }
            } else {
                str = "Mouse";
            }
        } else {
            str = "Touch";
        }
        sb.append((Object) str);
        sb.append(", issuesEnterExit=");
        sb.append(this.issuesEnterExit);
        sb.append(", historical=");
        sb.append(this.historical);
        sb.append(", scrollDelta=");
        sb.append((Object) Offset.m50toStringimpl(this.scrollDelta));
        sb.append(')');
        return sb.toString();
    }
}
