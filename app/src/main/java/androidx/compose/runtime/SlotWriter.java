package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SlotWriter {
    public static final Companion Companion = new Companion();
    private ArrayList anchors;
    private boolean closed;
    private int currentGroup;
    private int currentGroupEnd;
    private int currentSlot;
    private int currentSlotEnd;
    private final IntStack endStack;
    private int groupGapLen;
    private int groupGapStart;
    private int[] groups;
    private int insertCount;
    private int nodeCount;
    private final IntStack nodeCountStack;
    private int parent;
    private PrioritySet pendingRecalculateMarks;
    private Object[] slots;
    private int slotsGapLen;
    private int slotsGapOwner;
    private int slotsGapStart;
    private final IntStack startStack;
    private final SlotTable table;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        /* JADX WARN: Multi-variable type inference failed */
        public static final List access$moveGroup(SlotWriter slotWriter, int i, SlotWriter slotWriter2, boolean z, boolean z2) {
            int i2;
            EmptyList emptyList;
            boolean z3;
            int groupSize = slotWriter.groupSize(i);
            int i3 = i + groupSize;
            int access$dataIndex = SlotWriter.access$dataIndex(slotWriter, i);
            int access$dataIndex2 = SlotWriter.access$dataIndex(slotWriter, i3);
            int i4 = access$dataIndex2 - access$dataIndex;
            boolean access$containsAnyGroupMarks = SlotWriter.access$containsAnyGroupMarks(slotWriter, i);
            slotWriter2.insertGroups(groupSize);
            slotWriter2.insertSlots(i4, slotWriter2.getCurrentGroup());
            if (slotWriter.groupGapStart < i3) {
                slotWriter.moveGroupGapTo(i3);
            }
            if (slotWriter.slotsGapStart < access$dataIndex2) {
                slotWriter.moveSlotGapTo(access$dataIndex2, i3);
            }
            int[] iArr = slotWriter2.groups;
            int currentGroup = slotWriter2.getCurrentGroup();
            int i5 = currentGroup * 5;
            ArraysKt.copyInto(i5, i * 5, i3 * 5, slotWriter.groups, iArr);
            Object[] objArr = slotWriter2.slots;
            int i6 = slotWriter2.currentSlot;
            ArraysKt.copyInto(slotWriter.slots, objArr, i6, access$dataIndex, access$dataIndex2);
            int parent = slotWriter2.getParent();
            iArr[i5 + 2] = parent;
            int i7 = currentGroup - i;
            int i8 = currentGroup + groupSize;
            int dataIndex = i6 - slotWriter2.dataIndex(currentGroup, iArr);
            int i9 = slotWriter2.slotsGapOwner;
            int i10 = slotWriter2.slotsGapLen;
            int length = objArr.length;
            int i11 = i9;
            int i12 = currentGroup;
            while (true) {
                i2 = 0;
                if (i12 >= i8) {
                    break;
                }
                if (i12 != currentGroup) {
                    int i13 = (i12 * 5) + 2;
                    iArr[i13] = iArr[i13] + i7;
                }
                int i14 = i8;
                int dataIndex2 = slotWriter2.dataIndex(i12, iArr) + dataIndex;
                if (i11 >= i12) {
                    i2 = slotWriter2.slotsGapStart;
                }
                int i15 = dataIndex;
                iArr[(i12 * 5) + 4] = SlotWriter.access$dataIndexToDataAnchor(slotWriter2, dataIndex2, i2, i10, length);
                if (i12 == i11) {
                    i11++;
                }
                i12++;
                dataIndex = i15;
                i8 = i14;
            }
            int i16 = i8;
            slotWriter2.slotsGapOwner = i11;
            int access$locationOf = SlotTableKt.access$locationOf(slotWriter.anchors, i, slotWriter.getSize$runtime_release());
            int access$locationOf2 = SlotTableKt.access$locationOf(slotWriter.anchors, i3, slotWriter.getSize$runtime_release());
            if (access$locationOf < access$locationOf2) {
                ArrayList arrayList = slotWriter.anchors;
                ArrayList arrayList2 = new ArrayList(access$locationOf2 - access$locationOf);
                for (int i17 = access$locationOf; i17 < access$locationOf2; i17++) {
                    Object obj = arrayList.get(i17);
                    Intrinsics.checkNotNullExpressionValue(obj, "sourceAnchors[anchorIndex]");
                    Anchor anchor = (Anchor) obj;
                    anchor.setLocation$runtime_release(anchor.getLocation$runtime_release() + i7);
                    arrayList2.add(anchor);
                }
                slotWriter2.anchors.addAll(SlotTableKt.access$locationOf(slotWriter2.anchors, slotWriter2.getCurrentGroup(), slotWriter2.getSize$runtime_release()), arrayList2);
                arrayList.subList(access$locationOf, access$locationOf2).clear();
                emptyList = arrayList2;
            } else {
                emptyList = EmptyList.INSTANCE;
            }
            int parent2 = slotWriter.parent(i);
            int i18 = 1;
            if (z) {
                if (parent2 >= 0) {
                    i2 = 1;
                }
                if (i2 != 0) {
                    slotWriter.startGroup();
                    slotWriter.advanceBy(parent2 - slotWriter.getCurrentGroup());
                    slotWriter.startGroup();
                }
                slotWriter.advanceBy(i - slotWriter.getCurrentGroup());
                z3 = slotWriter.removeGroup();
                if (i2 != 0) {
                    slotWriter.skipToGroupEnd();
                    slotWriter.endGroup();
                    slotWriter.skipToGroupEnd();
                    slotWriter.endGroup();
                }
            } else {
                boolean access$removeGroups = SlotWriter.access$removeGroups(i, groupSize, slotWriter);
                slotWriter.removeSlots(access$dataIndex, i4, i - 1);
                z3 = access$removeGroups;
            }
            if (!z3) {
                int i19 = slotWriter2.nodeCount;
                if (!SlotTableKt.access$isNode(currentGroup, iArr)) {
                    i18 = SlotTableKt.access$nodeCount(currentGroup, iArr);
                }
                slotWriter2.nodeCount = i19 + i18;
                if (z2) {
                    slotWriter2.currentGroup = i16;
                    slotWriter2.currentSlot = i6 + i4;
                }
                if (access$containsAnyGroupMarks) {
                    slotWriter2.updateContainsMark(parent);
                }
                return emptyList;
            }
            ComposerKt.composeRuntimeError("Unexpectedly removed anchors".toString());
            throw null;
        }
    }

    public SlotWriter(SlotTable table) {
        Intrinsics.checkNotNullParameter(table, "table");
        this.table = table;
        this.groups = table.getGroups();
        this.slots = table.getSlots();
        this.anchors = table.getAnchors$runtime_release();
        this.groupGapStart = table.getGroupsSize();
        this.groupGapLen = (this.groups.length / 5) - table.getGroupsSize();
        this.currentGroupEnd = table.getGroupsSize();
        this.slotsGapStart = table.getSlotsSize();
        this.slotsGapLen = this.slots.length - table.getSlotsSize();
        this.slotsGapOwner = table.getGroupsSize();
        this.startStack = new IntStack();
        this.endStack = new IntStack();
        this.nodeCountStack = new IntStack();
        this.parent = -1;
    }

    public static final boolean access$containsAnyGroupMarks(SlotWriter slotWriter, int i) {
        boolean z;
        if (i >= 0) {
            if ((slotWriter.groups[(slotWriter.groupIndexToAddress(i) * 5) + 1] & 201326592) != 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return false;
            }
            return true;
        }
        slotWriter.getClass();
        return false;
    }

    public static final int access$dataIndexToDataAnchor(SlotWriter slotWriter, int i, int i2, int i3, int i4) {
        slotWriter.getClass();
        if (i > i2) {
            return -(((i4 - i3) - i) + 1);
        }
        return i;
    }

    public static final /* synthetic */ boolean access$removeGroups(int i, int i2, SlotWriter slotWriter) {
        return slotWriter.removeGroups(i, i2);
    }

    private final int auxIndex(int i, int[] iArr) {
        int countOneBits;
        int dataIndex = dataIndex(i, iArr);
        countOneBits = SlotTableKt.countOneBits(iArr[(i * 5) + 1] >> 29);
        return countOneBits + dataIndex;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int i, int[] iArr) {
        if (i >= this.groups.length / 5) {
            return this.slots.length - this.slotsGapLen;
        }
        int i2 = iArr[(i * 5) + 4];
        int i3 = this.slotsGapLen;
        int length = this.slots.length;
        if (i2 < 0) {
            return (length - i3) + i2 + 1;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAddress(int i) {
        if (i >= this.slotsGapStart) {
            return i + this.slotsGapLen;
        }
        return i;
    }

    private final void fixParentAnchorsFor(int i, int i2, int i3) {
        if (i >= this.groupGapStart) {
            i = -((getSize$runtime_release() - i) + 2);
        }
        while (i3 < i2) {
            this.groups[(groupIndexToAddress(i3) * 5) + 2] = i;
            int access$groupSize = SlotTableKt.access$groupSize(groupIndexToAddress(i3), this.groups) + i3;
            fixParentAnchorsFor(i3, access$groupSize, i3 + 1);
            i3 = access$groupSize;
        }
    }

    private final int groupIndexToAddress(int i) {
        if (i >= this.groupGapStart) {
            return i + this.groupGapLen;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertGroups(int i) {
        int i2;
        if (i > 0) {
            int i3 = this.currentGroup;
            moveGroupGapTo(i3);
            int i4 = this.groupGapStart;
            int i5 = this.groupGapLen;
            int[] iArr = this.groups;
            int length = iArr.length / 5;
            int i6 = length - i5;
            int i7 = 0;
            if (i5 < i) {
                int max = Math.max(Math.max(length * 2, i6 + i), 32);
                int[] iArr2 = new int[max * 5];
                int i8 = max - i6;
                ArraysKt.copyInto(0, 0, i4 * 5, iArr, iArr2);
                ArraysKt.copyInto((i4 + i8) * 5, (i5 + i4) * 5, length * 5, iArr, iArr2);
                this.groups = iArr2;
                i5 = i8;
            }
            int i9 = this.currentGroupEnd;
            if (i9 >= i4) {
                this.currentGroupEnd = i9 + i;
            }
            int i10 = i4 + i;
            this.groupGapStart = i10;
            this.groupGapLen = i5 - i;
            if (i6 > 0) {
                i2 = dataIndex(groupIndexToAddress(i3 + i), this.groups);
            } else {
                i2 = 0;
            }
            if (this.slotsGapOwner >= i4) {
                i7 = this.slotsGapStart;
            }
            int i11 = this.slotsGapLen;
            int length2 = this.slots.length;
            if (i2 > i7) {
                i2 = -(((length2 - i11) - i2) + 1);
            }
            for (int i12 = i4; i12 < i10; i12++) {
                this.groups[(i12 * 5) + 4] = i2;
            }
            int i13 = this.slotsGapOwner;
            if (i13 >= i4) {
                this.slotsGapOwner = i13 + i;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertSlots(int i, int i2) {
        if (i > 0) {
            moveSlotGapTo(this.currentSlot, i2);
            int i3 = this.slotsGapStart;
            int i4 = this.slotsGapLen;
            if (i4 < i) {
                Object[] objArr = this.slots;
                int length = objArr.length;
                int i5 = length - i4;
                int max = Math.max(Math.max(length * 2, i5 + i), 32);
                Object[] objArr2 = new Object[max];
                for (int i6 = 0; i6 < max; i6++) {
                    objArr2[i6] = null;
                }
                int i7 = max - i5;
                ArraysKt.copyInto(objArr, objArr2, 0, 0, i3);
                ArraysKt.copyInto(objArr, objArr2, i3 + i7, i4 + i3, length);
                this.slots = objArr2;
                i4 = i7;
            }
            int i8 = this.currentSlotEnd;
            if (i8 >= i3) {
                this.currentSlotEnd = i8 + i;
            }
            this.slotsGapStart = i3 + i;
            this.slotsGapLen = i4 - i;
        }
    }

    public static void markGroup$default(SlotWriter slotWriter) {
        int i = slotWriter.parent;
        int groupIndexToAddress = slotWriter.groupIndexToAddress(i);
        int[] iArr = slotWriter.groups;
        boolean z = true;
        int i2 = (groupIndexToAddress * 5) + 1;
        int i3 = iArr[i2];
        if ((i3 & 134217728) == 0) {
            z = false;
        }
        if (!z) {
            iArr[i2] = i3 | 134217728;
            if (!SlotTableKt.access$containsMark(groupIndexToAddress, iArr)) {
                slotWriter.updateContainsMark(slotWriter.parent(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveGroupGapTo(int i) {
        int size$runtime_release;
        int i2;
        int i3 = this.groupGapLen;
        int i4 = this.groupGapStart;
        if (i4 != i) {
            boolean z = true;
            if (!this.anchors.isEmpty()) {
                int length = (this.groups.length / 5) - this.groupGapLen;
                if (i4 < i) {
                    for (int access$locationOf = SlotTableKt.access$locationOf(this.anchors, i4, length); access$locationOf < this.anchors.size(); access$locationOf++) {
                        Object obj = this.anchors.get(access$locationOf);
                        Intrinsics.checkNotNullExpressionValue(obj, "anchors[index]");
                        Anchor anchor = (Anchor) obj;
                        int location$runtime_release = anchor.getLocation$runtime_release();
                        if (location$runtime_release >= 0 || (i2 = location$runtime_release + length) >= i) {
                            break;
                        }
                        anchor.setLocation$runtime_release(i2);
                    }
                } else {
                    for (int access$locationOf2 = SlotTableKt.access$locationOf(this.anchors, i, length); access$locationOf2 < this.anchors.size(); access$locationOf2++) {
                        Object obj2 = this.anchors.get(access$locationOf2);
                        Intrinsics.checkNotNullExpressionValue(obj2, "anchors[index]");
                        Anchor anchor2 = (Anchor) obj2;
                        int location$runtime_release2 = anchor2.getLocation$runtime_release();
                        if (location$runtime_release2 < 0) {
                            break;
                        }
                        anchor2.setLocation$runtime_release(-(length - location$runtime_release2));
                    }
                }
            }
            if (i3 > 0) {
                int[] iArr = this.groups;
                int i5 = i * 5;
                int i6 = i3 * 5;
                int i7 = i4 * 5;
                if (i < i4) {
                    ArraysKt.copyInto(i6 + i5, i5, i7, iArr, iArr);
                } else {
                    ArraysKt.copyInto(i7, i7 + i6, i5 + i6, iArr, iArr);
                }
            }
            if (i < i4) {
                i4 = i + i3;
            }
            int length2 = this.groups.length / 5;
            if (i4 >= length2) {
                z = false;
            }
            ComposerKt.runtimeCheck(z);
            while (i4 < length2) {
                int i8 = (i4 * 5) + 2;
                int i9 = this.groups[i8];
                if (i9 > -2) {
                    size$runtime_release = i9;
                } else {
                    size$runtime_release = getSize$runtime_release() + i9 + 2;
                }
                if (size$runtime_release >= i) {
                    size$runtime_release = -((getSize$runtime_release() - size$runtime_release) + 2);
                }
                if (size$runtime_release != i9) {
                    this.groups[i8] = size$runtime_release;
                }
                i4++;
                if (i4 == i) {
                    i4 += i3;
                }
            }
        }
        this.groupGapStart = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveSlotGapTo(int i, int i2) {
        boolean z;
        boolean z2;
        int i3 = this.slotsGapLen;
        int i4 = this.slotsGapStart;
        int i5 = this.slotsGapOwner;
        if (i4 != i) {
            Object[] objArr = this.slots;
            if (i < i4) {
                ArraysKt.copyInto(objArr, objArr, i + i3, i, i4);
            } else {
                ArraysKt.copyInto(objArr, objArr, i4, i4 + i3, i + i3);
            }
            ArraysKt.fill(i, i + i3, objArr);
        }
        int min = Math.min(i2 + 1, getSize$runtime_release());
        if (i5 != min) {
            int length = this.slots.length - i3;
            if (min < i5) {
                int groupIndexToAddress = groupIndexToAddress(min);
                int groupIndexToAddress2 = groupIndexToAddress(i5);
                int i6 = this.groupGapStart;
                while (groupIndexToAddress < groupIndexToAddress2) {
                    int[] iArr = this.groups;
                    int i7 = (groupIndexToAddress * 5) + 4;
                    int i8 = iArr[i7];
                    if (i8 >= 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        iArr[i7] = -((length - i8) + 1);
                        groupIndexToAddress++;
                        if (groupIndexToAddress == i6) {
                            groupIndexToAddress += this.groupGapLen;
                        }
                    } else {
                        ComposerKt.composeRuntimeError("Unexpected anchor value, expected a positive anchor".toString());
                        throw null;
                    }
                }
            } else {
                int groupIndexToAddress3 = groupIndexToAddress(i5);
                int groupIndexToAddress4 = groupIndexToAddress(min);
                while (groupIndexToAddress3 < groupIndexToAddress4) {
                    int[] iArr2 = this.groups;
                    int i9 = (groupIndexToAddress3 * 5) + 4;
                    int i10 = iArr2[i9];
                    if (i10 < 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        iArr2[i9] = i10 + length + 1;
                        groupIndexToAddress3++;
                        if (groupIndexToAddress3 == this.groupGapStart) {
                            groupIndexToAddress3 += this.groupGapLen;
                        }
                    } else {
                        ComposerKt.composeRuntimeError("Unexpected anchor value, expected a negative anchor".toString());
                        throw null;
                    }
                }
            }
            this.slotsGapOwner = min;
        }
        this.slotsGapStart = i;
    }

    private final void recalculateMarks() {
        boolean z;
        boolean z2;
        boolean z3;
        PrioritySet prioritySet = this.pendingRecalculateMarks;
        if (prioritySet != null) {
            while (prioritySet.isNotEmpty()) {
                int takeMax = prioritySet.takeMax();
                int groupIndexToAddress = groupIndexToAddress(takeMax);
                int i = takeMax + 1;
                int groupSize = groupSize(takeMax) + takeMax;
                while (true) {
                    z = false;
                    if (i < groupSize) {
                        if ((this.groups[(groupIndexToAddress(i) * 5) + 1] & 201326592) != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (z3) {
                            z2 = true;
                            break;
                        }
                        i += groupSize(i);
                    } else {
                        z2 = false;
                        break;
                    }
                }
                if (SlotTableKt.access$containsMark(groupIndexToAddress, this.groups) != z2) {
                    z = true;
                }
                if (z) {
                    int[] iArr = this.groups;
                    int i2 = (groupIndexToAddress * 5) + 1;
                    if (z2) {
                        iArr[i2] = iArr[i2] | 67108864;
                    } else {
                        iArr[i2] = iArr[i2] & (-67108865);
                    }
                    int parent = parent(takeMax);
                    if (parent >= 0) {
                        prioritySet.add(parent);
                    }
                }
            }
        }
    }

    private final boolean removeGroups(int i, int i2) {
        boolean z;
        boolean z2 = false;
        if (i2 <= 0) {
            return false;
        }
        ArrayList arrayList = this.anchors;
        moveGroupGapTo(i);
        if (!arrayList.isEmpty()) {
            int i3 = i2 + i;
            int access$locationOf = SlotTableKt.access$locationOf(this.anchors, i3, (this.groups.length / 5) - this.groupGapLen);
            if (access$locationOf >= this.anchors.size()) {
                access$locationOf--;
            }
            int i4 = access$locationOf + 1;
            int i5 = 0;
            while (access$locationOf >= 0) {
                Object obj = this.anchors.get(access$locationOf);
                Intrinsics.checkNotNullExpressionValue(obj, "anchors[index]");
                Anchor anchor = (Anchor) obj;
                int anchorIndex = anchorIndex(anchor);
                if (anchorIndex < i) {
                    break;
                }
                if (anchorIndex < i3) {
                    anchor.setLocation$runtime_release(Integer.MIN_VALUE);
                    if (i5 == 0) {
                        i5 = access$locationOf + 1;
                    }
                    i4 = access$locationOf;
                }
                access$locationOf--;
            }
            if (i4 < i5) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                this.anchors.subList(i4, i5).clear();
            }
        } else {
            z = false;
        }
        this.groupGapStart = i;
        this.groupGapLen += i2;
        int i6 = this.slotsGapOwner;
        if (i6 > i) {
            this.slotsGapOwner = Math.max(i, i6 - i2);
        }
        int i7 = this.currentGroupEnd;
        if (i7 >= this.groupGapStart) {
            this.currentGroupEnd = i7 - i2;
        }
        int i8 = this.parent;
        if (i8 >= 0) {
            if (SlotTableKt.access$containsMark(groupIndexToAddress(i8), this.groups)) {
                z2 = true;
            }
        }
        if (z2) {
            updateContainsMark(this.parent);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSlots(int i, int i2, int i3) {
        if (i2 > 0) {
            int i4 = this.slotsGapLen;
            int i5 = i + i2;
            moveSlotGapTo(i5, i3);
            this.slotsGapStart = i;
            this.slotsGapLen = i4 + i2;
            ArraysKt.fill(i, i5, this.slots);
            int i6 = this.currentSlotEnd;
            if (i6 >= i) {
                this.currentSlotEnd = i6 - i2;
            }
        }
    }

    private final int slotIndex(int i, int[] iArr) {
        if (i >= this.groups.length / 5) {
            return this.slots.length - this.slotsGapLen;
        }
        int access$slotAnchor = SlotTableKt.access$slotAnchor(i, iArr);
        int i2 = this.slotsGapLen;
        int length = this.slots.length;
        if (access$slotAnchor < 0) {
            return (length - i2) + access$slotAnchor + 1;
        }
        return access$slotAnchor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateContainsMark(int i) {
        if (i >= 0) {
            PrioritySet prioritySet = this.pendingRecalculateMarks;
            if (prioritySet == null) {
                prioritySet = new PrioritySet();
                this.pendingRecalculateMarks = prioritySet;
            }
            prioritySet.add(i);
        }
    }

    private final void updateNodeOfGroup(int i, Object obj) {
        boolean z;
        int groupIndexToAddress = groupIndexToAddress(i);
        int[] iArr = this.groups;
        if (groupIndexToAddress < iArr.length && SlotTableKt.access$isNode(groupIndexToAddress, iArr)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.slots[dataIndexToDataAddress(dataIndex(groupIndexToAddress, this.groups))] = obj;
            return;
        }
        ComposerKt.composeRuntimeError(("Updating the node of a group at " + i + " that was not created with as a node group").toString());
        throw null;
    }

    public final void advanceBy(int i) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (this.insertCount <= 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (i == 0) {
                    return;
                }
                int i2 = this.currentGroup + i;
                if (i2 < this.parent || i2 > this.currentGroupEnd) {
                    z3 = false;
                }
                if (z3) {
                    this.currentGroup = i2;
                    int dataIndex = dataIndex(groupIndexToAddress(i2), this.groups);
                    this.currentSlot = dataIndex;
                    this.currentSlotEnd = dataIndex;
                    return;
                }
                ComposerKt.composeRuntimeError(("Cannot seek outside the current group (" + this.parent + '-' + this.currentGroupEnd + ')').toString());
                throw null;
            }
            throw new IllegalStateException("Cannot call seek() while inserting".toString());
        }
        ComposerKt.composeRuntimeError("Cannot seek backwards".toString());
        throw null;
    }

    public final Anchor anchor(int i) {
        int search;
        ArrayList arrayList = this.anchors;
        search = SlotTableKt.search(arrayList, i, getSize$runtime_release());
        if (search < 0) {
            if (i > this.groupGapStart) {
                i = -(getSize$runtime_release() - i);
            }
            Anchor anchor = new Anchor(i);
            arrayList.add(-(search + 1), anchor);
            return anchor;
        }
        Object obj = arrayList.get(search);
        Intrinsics.checkNotNullExpressionValue(obj, "get(location)");
        return (Anchor) obj;
    }

    public final int anchorIndex(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        int location$runtime_release = anchor.getLocation$runtime_release();
        if (location$runtime_release < 0) {
            return location$runtime_release + getSize$runtime_release();
        }
        return location$runtime_release;
    }

    public final void beginInsert() {
        int i = this.insertCount;
        this.insertCount = i + 1;
        if (i == 0) {
            this.endStack.push(((this.groups.length / 5) - this.groupGapLen) - this.currentGroupEnd);
        }
    }

    public final void close() {
        this.closed = true;
        if (this.startStack.isEmpty()) {
            moveGroupGapTo(getSize$runtime_release());
            moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
            recalculateMarks();
        }
        this.table.close$runtime_release(this, this.groups, this.groupGapStart, this.slots, this.slotsGapStart, this.anchors);
    }

    public final void endGroup() {
        boolean z;
        int i;
        int i2 = 1;
        int i3 = 0;
        if (this.insertCount > 0) {
            z = true;
        } else {
            z = false;
        }
        int i4 = this.currentGroup;
        int i5 = this.currentGroupEnd;
        int i6 = this.parent;
        int groupIndexToAddress = groupIndexToAddress(i6);
        int i7 = this.nodeCount;
        int i8 = i4 - i6;
        boolean access$isNode = SlotTableKt.access$isNode(groupIndexToAddress, this.groups);
        IntStack intStack = this.nodeCountStack;
        if (z) {
            SlotTableKt.access$updateGroupSize(groupIndexToAddress, i8, this.groups);
            SlotTableKt.access$updateNodeCount(groupIndexToAddress, i7, this.groups);
            int pop = intStack.pop();
            if (!access$isNode) {
                i2 = i7;
            }
            this.nodeCount = pop + i2;
            this.parent = parent(i6, this.groups);
            return;
        }
        if (i4 != i5) {
            i2 = 0;
        }
        if (i2 != 0) {
            int access$groupSize = SlotTableKt.access$groupSize(groupIndexToAddress, this.groups);
            int access$nodeCount = SlotTableKt.access$nodeCount(groupIndexToAddress, this.groups);
            SlotTableKt.access$updateGroupSize(groupIndexToAddress, i8, this.groups);
            SlotTableKt.access$updateNodeCount(groupIndexToAddress, i7, this.groups);
            int pop2 = this.startStack.pop();
            this.currentGroupEnd = ((this.groups.length / 5) - this.groupGapLen) - this.endStack.pop();
            this.parent = pop2;
            int parent = parent(i6, this.groups);
            int pop3 = intStack.pop();
            this.nodeCount = pop3;
            if (parent == pop2) {
                if (!access$isNode) {
                    i3 = i7 - access$nodeCount;
                }
                this.nodeCount = pop3 + i3;
                return;
            }
            int i9 = i8 - access$groupSize;
            if (access$isNode) {
                i = 0;
            } else {
                i = i7 - access$nodeCount;
            }
            if (i9 != 0 || i != 0) {
                while (parent != 0 && parent != pop2 && (i != 0 || i9 != 0)) {
                    int groupIndexToAddress2 = groupIndexToAddress(parent);
                    if (i9 != 0) {
                        SlotTableKt.access$updateGroupSize(groupIndexToAddress2, SlotTableKt.access$groupSize(groupIndexToAddress2, this.groups) + i9, this.groups);
                    }
                    if (i != 0) {
                        int[] iArr = this.groups;
                        SlotTableKt.access$updateNodeCount(groupIndexToAddress2, SlotTableKt.access$nodeCount(groupIndexToAddress2, iArr) + i, iArr);
                    }
                    if (SlotTableKt.access$isNode(groupIndexToAddress2, this.groups)) {
                        i = 0;
                    }
                    parent = parent(parent, this.groups);
                }
            }
            this.nodeCount += i;
            return;
        }
        ComposerKt.composeRuntimeError("Expected to be at the end of a group".toString());
        throw null;
    }

    public final void endInsert() {
        boolean z;
        int i = this.insertCount;
        boolean z2 = true;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            int i2 = i - 1;
            this.insertCount = i2;
            if (i2 == 0) {
                if (this.nodeCountStack.getSize() != this.startStack.getSize()) {
                    z2 = false;
                }
                if (z2) {
                    this.currentGroupEnd = ((this.groups.length / 5) - this.groupGapLen) - this.endStack.pop();
                    return;
                } else {
                    ComposerKt.composeRuntimeError("startGroup/endGroup mismatch while inserting".toString());
                    throw null;
                }
            }
            return;
        }
        throw new IllegalStateException("Unbalanced begin/end insert".toString());
    }

    public final void ensureStarted(int i) {
        boolean z;
        boolean z2 = true;
        if (this.insertCount <= 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            int i2 = this.parent;
            if (i2 != i) {
                if (i < i2 || i >= this.currentGroupEnd) {
                    z2 = false;
                }
                if (z2) {
                    int i3 = this.currentGroup;
                    int i4 = this.currentSlot;
                    int i5 = this.currentSlotEnd;
                    this.currentGroup = i;
                    startGroup();
                    this.currentGroup = i3;
                    this.currentSlot = i4;
                    this.currentSlotEnd = i5;
                    return;
                }
                ComposerKt.composeRuntimeError(("Started group at " + i + " must be a subgroup of the group at " + i2).toString());
                throw null;
            }
            return;
        }
        ComposerKt.composeRuntimeError("Cannot call ensureStarted() while inserting".toString());
        throw null;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final int getParent() {
        return this.parent;
    }

    public final int getSize$runtime_release() {
        return (this.groups.length / 5) - this.groupGapLen;
    }

    public final SlotTable getTable$runtime_release() {
        return this.table;
    }

    public final Object groupAux(int i) {
        int groupIndexToAddress = groupIndexToAddress(i);
        if (SlotTableKt.access$hasAux(groupIndexToAddress, this.groups)) {
            return this.slots[auxIndex(groupIndexToAddress, this.groups)];
        }
        return Composer.Companion.getEmpty();
    }

    public final int groupKey(int i) {
        return this.groups[groupIndexToAddress(i) * 5];
    }

    public final Object groupObjectKey(int i) {
        int groupIndexToAddress = groupIndexToAddress(i);
        int[] iArr = this.groups;
        boolean z = true;
        if ((iArr[(groupIndexToAddress * 5) + 1] & 536870912) == 0) {
            z = false;
        }
        if (z) {
            return this.slots[SlotTableKt.access$objectKeyIndex(groupIndexToAddress, iArr)];
        }
        return null;
    }

    public final int groupSize(int i) {
        return SlotTableKt.access$groupSize(groupIndexToAddress(i), this.groups);
    }

    public final SlotWriter$groupSlots$1 groupSlots() {
        int dataIndex = dataIndex(groupIndexToAddress(this.currentGroup), this.groups);
        int[] iArr = this.groups;
        int i = this.currentGroup;
        return new SlotWriter$groupSlots$1(dataIndex, dataIndex(groupIndexToAddress(groupSize(i) + i), iArr), this);
    }

    public final boolean indexInCurrentGroup(int i) {
        return indexInGroup(i, this.currentGroup);
    }

    public final boolean indexInGroup(int i, int i2) {
        int length;
        int groupSize;
        if (i2 == this.parent) {
            length = this.currentGroupEnd;
        } else {
            IntStack intStack = this.startStack;
            if (i2 > intStack.peekOr(0)) {
                groupSize = groupSize(i2);
            } else {
                int indexOf = intStack.indexOf(i2);
                if (indexOf < 0) {
                    groupSize = groupSize(i2);
                } else {
                    length = ((this.groups.length / 5) - this.groupGapLen) - this.endStack.peek(indexOf);
                }
            }
            length = groupSize + i2;
        }
        if (i <= i2 || i >= length) {
            return false;
        }
        return true;
    }

    public final boolean indexInParent(int i) {
        int i2 = this.parent;
        if ((i > i2 && i < this.currentGroupEnd) || (i2 == 0 && i == 0)) {
            return true;
        }
        return false;
    }

    public final boolean isNode() {
        int i = this.currentGroup;
        if (i < this.currentGroupEnd) {
            if (SlotTableKt.access$isNode(groupIndexToAddress(i), this.groups)) {
                return true;
            }
        }
        return false;
    }

    public final void moveFrom(SlotTable table, int i) {
        boolean z;
        Intrinsics.checkNotNullParameter(table, "table");
        if (this.insertCount > 0) {
            z = true;
        } else {
            z = false;
        }
        ComposerKt.runtimeCheck(z);
        if (i == 0 && this.currentGroup == 0 && this.table.getGroupsSize() == 0) {
            int[] iArr = this.groups;
            Object[] objArr = this.slots;
            ArrayList arrayList = this.anchors;
            int[] groups = table.getGroups();
            int groupsSize = table.getGroupsSize();
            Object[] slots = table.getSlots();
            int slotsSize = table.getSlotsSize();
            this.groups = groups;
            this.slots = slots;
            this.anchors = table.getAnchors$runtime_release();
            this.groupGapStart = groupsSize;
            this.groupGapLen = (groups.length / 5) - groupsSize;
            this.slotsGapStart = slotsSize;
            this.slotsGapLen = slots.length - slotsSize;
            this.slotsGapOwner = groupsSize;
            table.setTo$runtime_release(iArr, 0, objArr, 0, arrayList);
            return;
        }
        SlotWriter openWriter = table.openWriter();
        try {
            Companion.access$moveGroup(openWriter, i, this, true, true);
        } finally {
            openWriter.close();
        }
    }

    public final void moveGroup(int i) {
        boolean z;
        boolean z2;
        int i2;
        boolean z3;
        if (this.insertCount == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (i >= 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (i == 0) {
                    return;
                }
                int i3 = this.currentGroup;
                int i4 = this.parent;
                int i5 = this.currentGroupEnd;
                int i6 = i3;
                for (int i7 = i; i7 > 0; i7--) {
                    i6 += SlotTableKt.access$groupSize(groupIndexToAddress(i6), this.groups);
                    if (i6 <= i5) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (!z3) {
                        ComposerKt.composeRuntimeError("Parameter offset is out of bounds".toString());
                        throw null;
                    }
                }
                int access$groupSize = SlotTableKt.access$groupSize(groupIndexToAddress(i6), this.groups);
                int i8 = this.currentSlot;
                int dataIndex = dataIndex(groupIndexToAddress(i6), this.groups);
                int i9 = i6 + access$groupSize;
                int dataIndex2 = dataIndex(groupIndexToAddress(i9), this.groups);
                int i10 = dataIndex2 - dataIndex;
                insertSlots(i10, Math.max(this.currentGroup - 1, 0));
                insertGroups(access$groupSize);
                int[] iArr = this.groups;
                int groupIndexToAddress = groupIndexToAddress(i9) * 5;
                ArraysKt.copyInto(groupIndexToAddress(i3) * 5, groupIndexToAddress, (access$groupSize * 5) + groupIndexToAddress, iArr, iArr);
                if (i10 > 0) {
                    Object[] objArr = this.slots;
                    ArraysKt.copyInto(objArr, objArr, i8, dataIndexToDataAddress(dataIndex + i10), dataIndexToDataAddress(dataIndex2 + i10));
                }
                int i11 = dataIndex + i10;
                int i12 = i11 - i8;
                int i13 = this.slotsGapStart;
                int i14 = this.slotsGapLen;
                int length = this.slots.length;
                int i15 = this.slotsGapOwner;
                int i16 = i3 + access$groupSize;
                int i17 = i3;
                while (i17 < i16) {
                    int groupIndexToAddress2 = groupIndexToAddress(i17);
                    int i18 = i16;
                    int dataIndex3 = dataIndex(groupIndexToAddress2, iArr) - i12;
                    int i19 = i12;
                    if (i15 < groupIndexToAddress2) {
                        i2 = 0;
                    } else {
                        i2 = i13;
                    }
                    if (dataIndex3 > i2) {
                        dataIndex3 = -(((length - i14) - dataIndex3) + 1);
                    }
                    int i20 = this.slotsGapStart;
                    int i21 = i13;
                    int i22 = this.slotsGapLen;
                    int i23 = i14;
                    int length2 = this.slots.length;
                    if (dataIndex3 > i20) {
                        dataIndex3 = -(((length2 - i22) - dataIndex3) + 1);
                    }
                    iArr[(groupIndexToAddress2 * 5) + 4] = dataIndex3;
                    i17++;
                    i16 = i18;
                    i12 = i19;
                    i13 = i21;
                    i14 = i23;
                }
                int i24 = access$groupSize + i9;
                int size$runtime_release = getSize$runtime_release();
                int access$locationOf = SlotTableKt.access$locationOf(this.anchors, i9, size$runtime_release);
                ArrayList arrayList = new ArrayList();
                if (access$locationOf >= 0) {
                    while (access$locationOf < this.anchors.size()) {
                        Object obj = this.anchors.get(access$locationOf);
                        Intrinsics.checkNotNullExpressionValue(obj, "anchors[index]");
                        Anchor anchor = (Anchor) obj;
                        int anchorIndex = anchorIndex(anchor);
                        if (anchorIndex < i9 || anchorIndex >= i24) {
                            break;
                        }
                        arrayList.add(anchor);
                        this.anchors.remove(access$locationOf);
                    }
                }
                int i25 = i3 - i9;
                int size = arrayList.size();
                for (int i26 = 0; i26 < size; i26++) {
                    Anchor anchor2 = (Anchor) arrayList.get(i26);
                    int anchorIndex2 = anchorIndex(anchor2) + i25;
                    if (anchorIndex2 >= this.groupGapStart) {
                        anchor2.setLocation$runtime_release(-(size$runtime_release - anchorIndex2));
                    } else {
                        anchor2.setLocation$runtime_release(anchorIndex2);
                    }
                    this.anchors.add(SlotTableKt.access$locationOf(this.anchors, anchorIndex2, size$runtime_release), anchor2);
                }
                if (!removeGroups(i9, access$groupSize)) {
                    fixParentAnchorsFor(i4, this.currentGroupEnd, i3);
                    if (i10 > 0) {
                        removeSlots(i11, i10, i9 - 1);
                        return;
                    }
                    return;
                }
                ComposerKt.composeRuntimeError("Unexpectedly removed anchors".toString());
                throw null;
            }
            ComposerKt.composeRuntimeError("Parameter offset is out of bounds".toString());
            throw null;
        }
        ComposerKt.composeRuntimeError("Cannot move a group while inserting".toString());
        throw null;
    }

    public final List moveIntoGroupFrom(SlotTable table) {
        boolean z;
        Intrinsics.checkNotNullParameter(table, "table");
        if (this.insertCount <= 0 && groupSize(this.currentGroup + 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        ComposerKt.runtimeCheck(z);
        int i = this.currentGroup;
        int i2 = this.currentSlot;
        int i3 = this.currentSlotEnd;
        advanceBy(1);
        startGroup();
        beginInsert();
        SlotWriter openWriter = table.openWriter();
        try {
            List access$moveGroup = Companion.access$moveGroup(openWriter, 2, this, false, true);
            openWriter.close();
            endInsert();
            endGroup();
            this.currentGroup = i;
            this.currentSlot = i2;
            this.currentSlotEnd = i3;
            return access$moveGroup;
        } catch (Throwable th) {
            openWriter.close();
            throw th;
        }
    }

    public final Object node(int i) {
        int groupIndexToAddress = groupIndexToAddress(i);
        if (SlotTableKt.access$isNode(groupIndexToAddress, this.groups)) {
            return this.slots[dataIndexToDataAddress(dataIndex(groupIndexToAddress, this.groups))];
        }
        return null;
    }

    public final int nodeCount(int i) {
        return SlotTableKt.access$nodeCount(groupIndexToAddress(i), this.groups);
    }

    public final int parent(int i) {
        return parent(i, this.groups);
    }

    public final boolean removeGroup() {
        boolean z;
        if (this.insertCount == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            int i = this.currentGroup;
            int i2 = this.currentSlot;
            int skipGroup = skipGroup();
            PrioritySet prioritySet = this.pendingRecalculateMarks;
            if (prioritySet != null) {
                while (prioritySet.isNotEmpty() && prioritySet.peek() >= i) {
                    prioritySet.takeMax();
                }
            }
            boolean removeGroups = removeGroups(i, this.currentGroup - i);
            removeSlots(i2, this.currentSlot - i2, i - 1);
            this.currentGroup = i;
            this.currentSlot = i2;
            this.nodeCount -= skipGroup;
            return removeGroups;
        }
        ComposerKt.composeRuntimeError("Cannot remove group while inserting".toString());
        throw null;
    }

    public final void reset() {
        boolean z;
        if (this.insertCount == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            recalculateMarks();
            this.currentGroup = 0;
            this.currentGroupEnd = (this.groups.length / 5) - this.groupGapLen;
            this.currentSlot = 0;
            this.currentSlotEnd = 0;
            this.nodeCount = 0;
            return;
        }
        ComposerKt.composeRuntimeError("Cannot reset when inserting".toString());
        throw null;
    }

    public final Object set(int i, Object obj) {
        int slotIndex = slotIndex(groupIndexToAddress(this.currentGroup), this.groups);
        boolean z = true;
        int i2 = slotIndex + i;
        if ((i2 < slotIndex || i2 >= dataIndex(groupIndexToAddress(this.currentGroup + 1), this.groups)) ? false : false) {
            int dataIndexToDataAddress = dataIndexToDataAddress(i2);
            Object[] objArr = this.slots;
            Object obj2 = objArr[dataIndexToDataAddress];
            objArr[dataIndexToDataAddress] = obj;
            return obj2;
        }
        ComposerKt.composeRuntimeError(("Write to an invalid slot index " + i + " for group " + this.currentGroup).toString());
        throw null;
    }

    public final int skipGroup() {
        int groupIndexToAddress = groupIndexToAddress(this.currentGroup);
        int access$groupSize = SlotTableKt.access$groupSize(groupIndexToAddress, this.groups) + this.currentGroup;
        this.currentGroup = access$groupSize;
        this.currentSlot = dataIndex(groupIndexToAddress(access$groupSize), this.groups);
        if (SlotTableKt.access$isNode(groupIndexToAddress, this.groups)) {
            return 1;
        }
        return SlotTableKt.access$nodeCount(groupIndexToAddress, this.groups);
    }

    public final void skipToGroupEnd() {
        int i = this.currentGroupEnd;
        this.currentGroup = i;
        this.currentSlot = dataIndex(groupIndexToAddress(i), this.groups);
    }

    public final Object slot(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        int anchorIndex = anchorIndex(anchor);
        int slotIndex = slotIndex(groupIndexToAddress(anchorIndex), this.groups);
        boolean z = true;
        int dataIndex = dataIndex(groupIndexToAddress(anchorIndex + 1), this.groups);
        int i = 0 + slotIndex;
        if (slotIndex > i || i >= dataIndex) {
            z = false;
        }
        if (!z) {
            return Composer.Companion.getEmpty();
        }
        return this.slots[dataIndexToDataAddress(i)];
    }

    public final void startData(int i, Object obj, Object obj2) {
        startGroup(i, obj, false, obj2);
    }

    public final void startGroup() {
        if (this.insertCount == 0) {
            startGroup(0, Composer.Companion.getEmpty(), false, Composer.Companion.getEmpty());
        } else {
            ComposerKt.composeRuntimeError("Key must be supplied when inserting".toString());
            throw null;
        }
    }

    public final void startNode(int i, Composer$Companion$Empty$1 composer$Companion$Empty$1) {
        startGroup(i, composer$Companion$Empty$1, true, Composer.Companion.getEmpty());
    }

    public final String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime_release() + " gap=" + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + ')';
    }

    public final void update(Object obj) {
        boolean z;
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        Object obj2 = objArr[dataIndexToDataAddress(i)];
        int i2 = this.currentSlot;
        if (i2 <= this.currentSlotEnd) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.slots[dataIndexToDataAddress(i2 - 1)] = obj;
        } else {
            ComposerKt.composeRuntimeError("Writing to an invalid slot".toString());
            throw null;
        }
    }

    public final void updateAux(Object obj) {
        int groupIndexToAddress = groupIndexToAddress(this.currentGroup);
        if (SlotTableKt.access$hasAux(groupIndexToAddress, this.groups)) {
            this.slots[dataIndexToDataAddress(auxIndex(groupIndexToAddress, this.groups))] = obj;
        } else {
            ComposerKt.composeRuntimeError("Updating the data of a group that was not created with a data slot".toString());
            throw null;
        }
    }

    public final void updateNode(Anchor anchor, Object obj) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        updateNodeOfGroup(anchorIndex(anchor), obj);
    }

    public static final int access$dataIndex(SlotWriter slotWriter, int i) {
        return slotWriter.dataIndex(slotWriter.groupIndexToAddress(i), slotWriter.groups);
    }

    private final int parent(int i, int[] iArr) {
        int i2 = iArr[(groupIndexToAddress(i) * 5) + 2];
        return i2 > -2 ? i2 : getSize$runtime_release() + i2 + 2;
    }

    public final boolean isNode(int i) {
        return SlotTableKt.access$isNode(groupIndexToAddress(i), this.groups);
    }

    public final void startGroup(int i, Object obj) {
        startGroup(i, obj, false, Composer.Companion.getEmpty());
    }

    private final void startGroup(int i, Object obj, boolean z, Object obj2) {
        int access$groupSize;
        int i2 = 1;
        byte b = this.insertCount > 0 ? (byte) 1 : (byte) 0;
        this.nodeCountStack.push(this.nodeCount);
        if (b != 0) {
            insertGroups(1);
            int i3 = this.currentGroup;
            int groupIndexToAddress = groupIndexToAddress(i3);
            int i4 = obj != Composer.Companion.getEmpty() ? 1 : 0;
            if (z || obj2 == Composer.Companion.getEmpty()) {
                i2 = 0;
            }
            int[] iArr = this.groups;
            int i5 = this.parent;
            int i6 = this.currentSlot;
            int i7 = z ? 1073741824 : 0;
            int i8 = i4 != 0 ? 536870912 : 0;
            int i9 = i2 != 0 ? 268435456 : 0;
            int i10 = groupIndexToAddress * 5;
            iArr[i10 + 0] = i;
            iArr[i10 + 1] = i7 | i8 | i9;
            iArr[i10 + 2] = i5;
            iArr[i10 + 3] = 0;
            iArr[i10 + 4] = i6;
            this.currentSlotEnd = i6;
            int i11 = (z ? 1 : 0) + i4 + i2;
            if (i11 > 0) {
                insertSlots(i11, i3);
                Object[] objArr = this.slots;
                int i12 = this.currentSlot;
                if (z) {
                    objArr[i12] = obj2;
                    i12++;
                }
                if (i4 != 0) {
                    objArr[i12] = obj;
                    i12++;
                }
                if (i2 != 0) {
                    objArr[i12] = obj2;
                    i12++;
                }
                this.currentSlot = i12;
            }
            this.nodeCount = 0;
            access$groupSize = i3 + 1;
            this.parent = i3;
            this.currentGroup = access$groupSize;
        } else {
            this.startStack.push(this.parent);
            this.endStack.push(((this.groups.length / 5) - this.groupGapLen) - this.currentGroupEnd);
            int i13 = this.currentGroup;
            int groupIndexToAddress2 = groupIndexToAddress(i13);
            if (!Intrinsics.areEqual(obj2, Composer.Companion.getEmpty())) {
                if (z) {
                    updateNodeOfGroup(this.currentGroup, obj2);
                } else {
                    updateAux(obj2);
                }
            }
            this.currentSlot = slotIndex(groupIndexToAddress2, this.groups);
            this.currentSlotEnd = dataIndex(groupIndexToAddress(this.currentGroup + 1), this.groups);
            this.nodeCount = SlotTableKt.access$nodeCount(groupIndexToAddress2, this.groups);
            this.parent = i13;
            this.currentGroup = i13 + 1;
            access$groupSize = i13 + SlotTableKt.access$groupSize(groupIndexToAddress2, this.groups);
        }
        this.currentGroupEnd = access$groupSize;
    }
}
