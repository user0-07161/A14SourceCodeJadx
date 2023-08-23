package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SlotReader {
    private boolean closed;
    private int currentEnd;
    private int currentGroup;
    private int currentSlot;
    private int currentSlotEnd;
    private int emptyCount;
    private final int[] groups;
    private final int groupsSize;
    private int parent;
    private final Object[] slots;
    private final int slotsSize;
    private final SlotTable table;

    public SlotReader(SlotTable table) {
        Intrinsics.checkNotNullParameter(table, "table");
        this.table = table;
        this.groups = table.getGroups();
        int groupsSize = table.getGroupsSize();
        this.groupsSize = groupsSize;
        this.slots = table.getSlots();
        this.slotsSize = table.getSlotsSize();
        this.currentEnd = groupsSize;
        this.parent = -1;
    }

    private final Object objectKey(int i, int[] iArr) {
        boolean z = true;
        if ((iArr[(i * 5) + 1] & 536870912) == 0) {
            z = false;
        }
        if (z) {
            return this.slots[SlotTableKt.access$objectKeyIndex(i, iArr)];
        }
        return null;
    }

    public final Anchor anchor(int i) {
        ArrayList anchors$runtime_release = this.table.getAnchors$runtime_release();
        int access$search = SlotTableKt.access$search(anchors$runtime_release, i, this.groupsSize);
        if (access$search < 0) {
            Anchor anchor = new Anchor(i);
            anchors$runtime_release.add(-(access$search + 1), anchor);
            return anchor;
        }
        Object obj = anchors$runtime_release.get(access$search);
        Intrinsics.checkNotNullExpressionValue(obj, "get(location)");
        return (Anchor) obj;
    }

    public final void beginEmpty() {
        this.emptyCount++;
    }

    public final void close() {
        this.closed = true;
        this.table.close$runtime_release(this);
    }

    public final boolean containsMark(int i) {
        return SlotTableKt.access$containsMark(i, this.groups);
    }

    public final void endEmpty() {
        boolean z;
        int i = this.emptyCount;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.emptyCount = i - 1;
            return;
        }
        throw new IllegalArgumentException("Unbalanced begin/end empty".toString());
    }

    public final void endGroup() {
        boolean z;
        int i;
        if (this.emptyCount == 0) {
            if (this.currentGroup == this.currentEnd) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                int[] iArr = this.groups;
                int i2 = iArr[(this.parent * 5) + 2];
                this.parent = i2;
                if (i2 < 0) {
                    i = this.groupsSize;
                } else {
                    i = i2 + iArr[(i2 * 5) + 3];
                }
                this.currentEnd = i;
                return;
            }
            ComposerKt.composeRuntimeError("endGroup() not called at the end of a group".toString());
            throw null;
        }
    }

    public final List extractKeys() {
        int access$nodeCount;
        ArrayList arrayList = new ArrayList();
        if (this.emptyCount > 0) {
            return arrayList;
        }
        int i = this.currentGroup;
        while (i < this.currentEnd) {
            int i2 = i * 5;
            int[] iArr = this.groups;
            int i3 = iArr[i2];
            Object objectKey = objectKey(i, iArr);
            if (SlotTableKt.access$isNode(i, iArr)) {
                access$nodeCount = 1;
            } else {
                access$nodeCount = SlotTableKt.access$nodeCount(i, iArr);
            }
            arrayList.add(new KeyInfo(i3, objectKey, i, access$nodeCount));
            i += iArr[i2 + 3];
        }
        return arrayList;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final Object getGroupAux() {
        int i = this.currentGroup;
        if (i < this.currentEnd) {
            int[] iArr = this.groups;
            if (SlotTableKt.access$hasAux(i, iArr)) {
                return this.slots[SlotTableKt.access$auxIndex(i, iArr)];
            }
            return Composer.Companion.getEmpty();
        }
        return 0;
    }

    public final int getGroupEnd() {
        return this.currentEnd;
    }

    public final int getGroupKey() {
        int i = this.currentGroup;
        if (i < this.currentEnd) {
            return this.groups[i * 5];
        }
        return 0;
    }

    public final Object getGroupObjectKey() {
        int i = this.currentGroup;
        if (i < this.currentEnd) {
            return objectKey(i, this.groups);
        }
        return null;
    }

    public final int getGroupSize() {
        return SlotTableKt.access$groupSize(this.currentGroup, this.groups);
    }

    public final int getGroupSlotIndex() {
        return this.currentSlot - SlotTableKt.access$slotAnchor(this.parent, this.groups);
    }

    public final boolean getInEmpty() {
        if (this.emptyCount > 0) {
            return true;
        }
        return false;
    }

    public final int getParent() {
        return this.parent;
    }

    public final int getParentNodes() {
        int i = this.parent;
        if (i >= 0) {
            return SlotTableKt.access$nodeCount(i, this.groups);
        }
        return 0;
    }

    public final int getSize() {
        return this.groupsSize;
    }

    public final SlotTable getTable$runtime_release() {
        return this.table;
    }

    public final Object groupAux(int i) {
        int[] iArr = this.groups;
        if (SlotTableKt.access$hasAux(i, iArr)) {
            return this.slots[SlotTableKt.access$auxIndex(i, iArr)];
        }
        return Composer.Companion.getEmpty();
    }

    public final Object groupGet(int i) {
        return groupGet(this.currentGroup, i);
    }

    public final int groupKey(int i) {
        return this.groups[i * 5];
    }

    public final Object groupObjectKey(int i) {
        return objectKey(i, this.groups);
    }

    public final int groupSize(int i) {
        return SlotTableKt.access$groupSize(i, this.groups);
    }

    public final boolean hasMark(int i) {
        if ((this.groups[(i * 5) + 1] & 134217728) != 0) {
            return true;
        }
        return false;
    }

    public final boolean hasObjectKey(int i) {
        if ((this.groups[(i * 5) + 1] & 536870912) != 0) {
            return true;
        }
        return false;
    }

    public final boolean isGroupEnd() {
        if (!getInEmpty() && this.currentGroup != this.currentEnd) {
            return false;
        }
        return true;
    }

    public final boolean isNode() {
        return SlotTableKt.access$isNode(this.currentGroup, this.groups);
    }

    public final Object next() {
        int i;
        if (this.emptyCount <= 0 && (i = this.currentSlot) < this.currentSlotEnd) {
            this.currentSlot = i + 1;
            return this.slots[i];
        }
        return Composer.Companion.getEmpty();
    }

    public final Object node(int i) {
        int[] iArr = this.groups;
        if (SlotTableKt.access$isNode(i, iArr)) {
            if (SlotTableKt.access$isNode(i, iArr)) {
                return this.slots[iArr[(i * 5) + 4]];
            }
            return Composer.Companion.getEmpty();
        }
        return null;
    }

    public final int nodeCount(int i) {
        return SlotTableKt.access$nodeCount(i, this.groups);
    }

    public final int parent(int i) {
        return this.groups[(i * 5) + 2];
    }

    public final void reposition(int i) {
        boolean z;
        int i2;
        if (this.emptyCount == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.currentGroup = i;
            int[] iArr = this.groups;
            int i3 = this.groupsSize;
            if (i < i3) {
                i2 = iArr[(i * 5) + 2];
            } else {
                i2 = -1;
            }
            this.parent = i2;
            if (i2 < 0) {
                this.currentEnd = i3;
            } else {
                this.currentEnd = SlotTableKt.access$groupSize(i2, iArr) + i2;
            }
            this.currentSlot = 0;
            this.currentSlotEnd = 0;
            return;
        }
        ComposerKt.composeRuntimeError("Cannot reposition while in an empty region".toString());
        throw null;
    }

    public final void restoreParent(int i) {
        boolean z;
        int access$groupSize = SlotTableKt.access$groupSize(i, this.groups) + i;
        int i2 = this.currentGroup;
        if (i2 >= i && i2 <= access$groupSize) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.parent = i;
            this.currentEnd = access$groupSize;
            this.currentSlot = 0;
            this.currentSlotEnd = 0;
            return;
        }
        ComposerKt.composeRuntimeError(("Index " + i + " is not a parent of " + i2).toString());
        throw null;
    }

    public final int skipGroup() {
        boolean z;
        int i = 1;
        if (this.emptyCount == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            int i2 = this.currentGroup;
            int[] iArr = this.groups;
            if (!SlotTableKt.access$isNode(i2, iArr)) {
                i = SlotTableKt.access$nodeCount(this.currentGroup, iArr);
            }
            int i3 = this.currentGroup;
            this.currentGroup = iArr[(i3 * 5) + 3] + i3;
            return i;
        }
        ComposerKt.composeRuntimeError("Cannot skip while in an empty region".toString());
        throw null;
    }

    public final void skipToGroupEnd() {
        boolean z;
        if (this.emptyCount == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.currentGroup = this.currentEnd;
        } else {
            ComposerKt.composeRuntimeError("Cannot skip the enclosing group while in an empty region".toString());
            throw null;
        }
    }

    public final void startGroup() {
        boolean z;
        int i;
        if (this.emptyCount <= 0) {
            int i2 = this.currentGroup;
            int[] iArr = this.groups;
            if (iArr[(i2 * 5) + 2] == this.parent) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                this.parent = i2;
                this.currentEnd = iArr[(i2 * 5) + 3] + i2;
                int i3 = i2 + 1;
                this.currentGroup = i3;
                this.currentSlot = SlotTableKt.access$slotAnchor(i2, iArr);
                if (i2 >= this.groupsSize - 1) {
                    i = this.slotsSize;
                } else {
                    i = iArr[(i3 * 5) + 4];
                }
                this.currentSlotEnd = i;
                return;
            }
            throw new IllegalArgumentException("Invalid slot table detected".toString());
        }
    }

    public final void startNode() {
        if (this.emptyCount <= 0) {
            if (SlotTableKt.access$isNode(this.currentGroup, this.groups)) {
                startGroup();
                return;
            }
            throw new IllegalArgumentException("Expected a node group".toString());
        }
    }

    public final String toString() {
        return "SlotReader(current=" + this.currentGroup + ", key=" + getGroupKey() + ", parent=" + this.parent + ", end=" + this.currentEnd + ')';
    }

    public final Object groupGet(int i, int i2) {
        int i3;
        int[] iArr = this.groups;
        int access$slotAnchor = SlotTableKt.access$slotAnchor(i, iArr);
        int i4 = i + 1;
        if (i4 < this.groupsSize) {
            i3 = iArr[(i4 * 5) + 4];
        } else {
            i3 = this.slotsSize;
        }
        int i5 = access$slotAnchor + i2;
        return i5 < i3 ? this.slots[i5] : Composer.Companion.getEmpty();
    }

    public final boolean isNode(int i) {
        return SlotTableKt.access$isNode(i, this.groups);
    }
}
