package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Pending {
    private int groupIndex;
    private final HashMap groupInfos;
    private final List keyInfos;
    private final Lazy keyMap$delegate;
    private final int startIndex;
    private final List usedKeys;

    public Pending(List list, int i) {
        boolean z;
        this.keyInfos = list;
        this.startIndex = i;
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.usedKeys = new ArrayList();
            HashMap hashMap = new HashMap();
            int size = ((ArrayList) list).size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                KeyInfo keyInfo = (KeyInfo) this.keyInfos.get(i3);
                hashMap.put(Integer.valueOf(keyInfo.getLocation()), new GroupInfo(i3, i2, keyInfo.getNodes()));
                i2 += keyInfo.getNodes();
            }
            this.groupInfos = hashMap;
            this.keyMap$delegate = LazyKt.lazy$1(new Function0() { // from class: androidx.compose.runtime.Pending$keyMap$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Object valueOf;
                    int i4 = ComposerKt.$r8$clinit;
                    HashMap hashMap2 = new HashMap();
                    Pending pending = Pending.this;
                    int size2 = pending.getKeyInfos().size();
                    for (int i5 = 0; i5 < size2; i5++) {
                        KeyInfo keyInfo2 = (KeyInfo) pending.getKeyInfos().get(i5);
                        if (keyInfo2.getObjectKey() != null) {
                            valueOf = new JoinedKey(Integer.valueOf(keyInfo2.getKey()), keyInfo2.getObjectKey());
                        } else {
                            valueOf = Integer.valueOf(keyInfo2.getKey());
                        }
                        Object obj = hashMap2.get(valueOf);
                        if (obj == null) {
                            obj = new LinkedHashSet();
                            hashMap2.put(valueOf, obj);
                        }
                        ((LinkedHashSet) obj).add(keyInfo2);
                    }
                    return hashMap2;
                }
            });
            return;
        }
        throw new IllegalArgumentException("Invalid start index".toString());
    }

    public final int getGroupIndex() {
        return this.groupIndex;
    }

    public final List getKeyInfos() {
        return this.keyInfos;
    }

    public final KeyInfo getNext(int i, Object obj) {
        Object valueOf;
        Object obj2;
        if (obj != null) {
            valueOf = new JoinedKey(Integer.valueOf(i), obj);
        } else {
            valueOf = Integer.valueOf(i);
        }
        HashMap hashMap = (HashMap) this.keyMap$delegate.getValue();
        int i2 = ComposerKt.$r8$clinit;
        LinkedHashSet linkedHashSet = (LinkedHashSet) hashMap.get(valueOf);
        if (linkedHashSet != null && (obj2 = CollectionsKt.firstOrNull(linkedHashSet)) != null) {
            LinkedHashSet linkedHashSet2 = (LinkedHashSet) hashMap.get(valueOf);
            if (linkedHashSet2 != null) {
                linkedHashSet2.remove(obj2);
                if (linkedHashSet2.isEmpty()) {
                    hashMap.remove(valueOf);
                }
            }
        } else {
            obj2 = null;
        }
        return (KeyInfo) obj2;
    }

    public final int getStartIndex() {
        return this.startIndex;
    }

    public final List getUsed() {
        return this.usedKeys;
    }

    public final int nodePositionOf(KeyInfo keyInfo) {
        Intrinsics.checkNotNullParameter(keyInfo, "keyInfo");
        GroupInfo groupInfo = (GroupInfo) this.groupInfos.get(Integer.valueOf(keyInfo.getLocation()));
        if (groupInfo != null) {
            return groupInfo.getNodeIndex();
        }
        return -1;
    }

    public final void recordUsed(KeyInfo keyInfo) {
        ((ArrayList) this.usedKeys).add(keyInfo);
    }

    public final void registerInsert(KeyInfo keyInfo, int i) {
        this.groupInfos.put(Integer.valueOf(keyInfo.getLocation()), new GroupInfo(-1, i, 0));
    }

    public final void registerMoveNode(int i, int i2, int i3) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        HashMap hashMap = this.groupInfos;
        if (i > i2) {
            Collection<GroupInfo> values = hashMap.values();
            Intrinsics.checkNotNullExpressionValue(values, "groupInfos.values");
            for (GroupInfo groupInfo : values) {
                int nodeIndex = groupInfo.getNodeIndex();
                if (i <= nodeIndex && nodeIndex < i + i3) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    groupInfo.setNodeIndex((nodeIndex - i) + i2);
                } else {
                    if (i2 <= nodeIndex && nodeIndex < i) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        groupInfo.setNodeIndex(nodeIndex + i3);
                    }
                }
            }
        } else if (i2 > i) {
            Collection<GroupInfo> values2 = hashMap.values();
            Intrinsics.checkNotNullExpressionValue(values2, "groupInfos.values");
            for (GroupInfo groupInfo2 : values2) {
                int nodeIndex2 = groupInfo2.getNodeIndex();
                if (i <= nodeIndex2 && nodeIndex2 < i + i3) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    groupInfo2.setNodeIndex((nodeIndex2 - i) + i2);
                } else {
                    if (i + 1 <= nodeIndex2 && nodeIndex2 < i2) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        groupInfo2.setNodeIndex(nodeIndex2 - i3);
                    }
                }
            }
        }
    }

    public final void registerMoveSlot(int i, int i2) {
        boolean z;
        boolean z2;
        HashMap hashMap = this.groupInfos;
        if (i > i2) {
            Collection<GroupInfo> values = hashMap.values();
            Intrinsics.checkNotNullExpressionValue(values, "groupInfos.values");
            for (GroupInfo groupInfo : values) {
                int slotIndex = groupInfo.getSlotIndex();
                if (slotIndex == i) {
                    groupInfo.setSlotIndex(i2);
                } else {
                    if (i2 <= slotIndex && slotIndex < i) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        groupInfo.setSlotIndex(slotIndex + 1);
                    }
                }
            }
        } else if (i2 > i) {
            Collection<GroupInfo> values2 = hashMap.values();
            Intrinsics.checkNotNullExpressionValue(values2, "groupInfos.values");
            for (GroupInfo groupInfo2 : values2) {
                int slotIndex2 = groupInfo2.getSlotIndex();
                if (slotIndex2 == i) {
                    groupInfo2.setSlotIndex(i2);
                } else {
                    if (i + 1 <= slotIndex2 && slotIndex2 < i2) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        groupInfo2.setSlotIndex(slotIndex2 - 1);
                    }
                }
            }
        }
    }

    public final void setGroupIndex(int i) {
        this.groupIndex = i;
    }

    public final int slotPositionOf(KeyInfo keyInfo) {
        GroupInfo groupInfo = (GroupInfo) this.groupInfos.get(Integer.valueOf(keyInfo.getLocation()));
        if (groupInfo != null) {
            return groupInfo.getSlotIndex();
        }
        return -1;
    }

    public final boolean updateNodeCount(int i, int i2) {
        int nodeIndex;
        HashMap hashMap = this.groupInfos;
        GroupInfo groupInfo = (GroupInfo) hashMap.get(Integer.valueOf(i));
        if (groupInfo != null) {
            int nodeIndex2 = groupInfo.getNodeIndex();
            int nodeCount = i2 - groupInfo.getNodeCount();
            groupInfo.setNodeCount(i2);
            if (nodeCount != 0) {
                Collection<GroupInfo> values = hashMap.values();
                Intrinsics.checkNotNullExpressionValue(values, "groupInfos.values");
                for (GroupInfo groupInfo2 : values) {
                    if (groupInfo2.getNodeIndex() >= nodeIndex2 && !Intrinsics.areEqual(groupInfo2, groupInfo) && (nodeIndex = groupInfo2.getNodeIndex() + nodeCount) >= 0) {
                        groupInfo2.setNodeIndex(nodeIndex);
                    }
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public final int updatedNodeCountOf(KeyInfo keyInfo) {
        Intrinsics.checkNotNullParameter(keyInfo, "keyInfo");
        GroupInfo groupInfo = (GroupInfo) this.groupInfos.get(Integer.valueOf(keyInfo.getLocation()));
        if (groupInfo != null) {
            return groupInfo.getNodeCount();
        }
        return keyInfo.getNodes();
    }
}
