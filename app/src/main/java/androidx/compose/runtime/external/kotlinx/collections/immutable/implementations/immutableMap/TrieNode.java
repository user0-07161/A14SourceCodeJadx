package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TrieNode {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final TrieNode EMPTY = new TrieNode(0, 0, new Object[0], null);
    private Object[] buffer;
    private int dataMap;
    private int nodeMap;
    private final MutabilityOwnership ownedBy;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ModificationResult {
        private TrieNode node;
        private final int sizeDelta;

        public ModificationResult(TrieNode node, int i) {
            Intrinsics.checkNotNullParameter(node, "node");
            this.node = node;
            this.sizeDelta = i;
        }

        public final TrieNode getNode() {
            return this.node;
        }

        public final int getSizeDelta() {
            return this.sizeDelta;
        }

        public final void setNode(TrieNode trieNode) {
            this.node = trieNode;
        }
    }

    public TrieNode(int i, int i2, Object[] objArr, MutabilityOwnership mutabilityOwnership) {
        this.dataMap = i;
        this.nodeMap = i2;
        this.ownedBy = mutabilityOwnership;
        this.buffer = objArr;
    }

    private final Object[] bufferMoveEntryToNode(int i, int i2, int i3, Object obj, Object obj2, int i4, MutabilityOwnership mutabilityOwnership) {
        int i5;
        Object obj3 = this.buffer[i];
        if (obj3 != null) {
            i5 = obj3.hashCode();
        } else {
            i5 = 0;
        }
        TrieNode makeNode = makeNode(i5, obj3, valueAtKeyIndex(i), i3, obj, obj2, i4 + 5, mutabilityOwnership);
        int nodeIndex$runtime_release = nodeIndex$runtime_release(i2) + 1;
        Object[] objArr = this.buffer;
        int i6 = nodeIndex$runtime_release - 2;
        Object[] objArr2 = new Object[(objArr.length - 2) + 1];
        ArraysKt.copyInto$default(objArr, objArr2, 0, 0, i, 6);
        ArraysKt.copyInto(objArr, objArr2, i, i + 2, nodeIndex$runtime_release);
        objArr2[i6] = makeNode;
        ArraysKt.copyInto(objArr, objArr2, i6 + 1, nodeIndex$runtime_release, objArr.length);
        return objArr2;
    }

    private final int calculateSize() {
        if (this.nodeMap == 0) {
            return this.buffer.length / 2;
        }
        int bitCount = Integer.bitCount(this.dataMap);
        int length = this.buffer.length;
        for (int i = bitCount * 2; i < length; i++) {
            bitCount += nodeAtIndex$runtime_release(i).calculateSize();
        }
        return bitCount;
    }

    private final boolean collisionContainsKey(Object obj) {
        IntProgression step = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
            while (!Intrinsics.areEqual(obj, this.buffer[first])) {
                if (first != last) {
                    first += step2;
                }
            }
            return true;
        }
        return false;
    }

    private final boolean elementsIdentityEquals(TrieNode trieNode) {
        if (this == trieNode) {
            return true;
        }
        if (this.nodeMap != trieNode.nodeMap || this.dataMap != trieNode.dataMap) {
            return false;
        }
        int length = this.buffer.length;
        for (int i = 0; i < length; i++) {
            if (this.buffer[i] != trieNode.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    private final boolean hasNodeAt(int i) {
        if ((this.nodeMap & i) != 0) {
            return true;
        }
        return false;
    }

    private static TrieNode makeNode(int i, Object obj, Object obj2, int i2, Object obj3, Object obj4, int i3, MutabilityOwnership mutabilityOwnership) {
        Object[] objArr;
        if (i3 > 30) {
            return new TrieNode(0, 0, new Object[]{obj, obj2, obj3, obj4}, mutabilityOwnership);
        }
        int i4 = (i >> i3) & 31;
        int i5 = (i2 >> i3) & 31;
        if (i4 != i5) {
            if (i4 < i5) {
                objArr = new Object[]{obj, obj2, obj3, obj4};
            } else {
                objArr = new Object[]{obj3, obj4, obj, obj2};
            }
            return new TrieNode((1 << i4) | (1 << i5), 0, objArr, mutabilityOwnership);
        }
        return new TrieNode(0, 1 << i4, new Object[]{makeNode(i, obj, obj2, i2, obj3, obj4, i3 + 5, mutabilityOwnership)}, mutabilityOwnership);
    }

    private final TrieNode mutableCollisionRemoveEntryAtIndex(int i, PersistentHashMapBuilder persistentHashMapBuilder) {
        persistentHashMapBuilder.setSize(persistentHashMapBuilder.getSize() - 1);
        persistentHashMapBuilder.setOperationResult$runtime_release(valueAtKeyIndex(i));
        if (this.buffer.length == 2) {
            return null;
        }
        if (this.ownedBy == persistentHashMapBuilder.getOwnership$runtime_release()) {
            this.buffer = TrieNodeKt.access$removeEntryAtIndex(this.buffer, i);
            return this;
        }
        return new TrieNode(0, 0, TrieNodeKt.access$removeEntryAtIndex(this.buffer, i), persistentHashMapBuilder.getOwnership$runtime_release());
    }

    private final TrieNode mutableRemoveEntryAtIndex(int i, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        persistentHashMapBuilder.setSize(persistentHashMapBuilder.getSize() - 1);
        persistentHashMapBuilder.setOperationResult$runtime_release(valueAtKeyIndex(i));
        if (this.buffer.length == 2) {
            return null;
        }
        if (this.ownedBy == persistentHashMapBuilder.getOwnership$runtime_release()) {
            this.buffer = TrieNodeKt.access$removeEntryAtIndex(this.buffer, i);
            this.dataMap ^= i2;
            return this;
        }
        return new TrieNode(i2 ^ this.dataMap, this.nodeMap, TrieNodeKt.access$removeEntryAtIndex(this.buffer, i), persistentHashMapBuilder.getOwnership$runtime_release());
    }

    private final TrieNode mutableReplaceNode(TrieNode trieNode, TrieNode trieNode2, int i, int i2, MutabilityOwnership mutabilityOwnership) {
        MutabilityOwnership mutabilityOwnership2 = this.ownedBy;
        if (trieNode2 == null) {
            Object[] objArr = this.buffer;
            if (objArr.length == 1) {
                return null;
            }
            if (mutabilityOwnership2 == mutabilityOwnership) {
                this.buffer = TrieNodeKt.access$removeNodeAtIndex(objArr, i);
                this.nodeMap ^= i2;
                return this;
            }
            return new TrieNode(this.dataMap, this.nodeMap ^ i2, TrieNodeKt.access$removeNodeAtIndex(objArr, i), mutabilityOwnership);
        } else if (mutabilityOwnership2 == mutabilityOwnership || trieNode != trieNode2) {
            return mutableUpdateNodeAtIndex(i, trieNode2, mutabilityOwnership);
        } else {
            return this;
        }
    }

    private final TrieNode mutableUpdateNodeAtIndex(int i, TrieNode trieNode, MutabilityOwnership mutabilityOwnership) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1 && trieNode.buffer.length == 2 && trieNode.nodeMap == 0) {
            trieNode.dataMap = this.nodeMap;
            return trieNode;
        } else if (this.ownedBy == mutabilityOwnership) {
            objArr[i] = trieNode;
            return this;
        } else {
            Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
            copyOf[i] = trieNode;
            return new TrieNode(this.dataMap, this.nodeMap, copyOf, mutabilityOwnership);
        }
    }

    private final TrieNode updateNodeAtIndex(int i, int i2, TrieNode trieNode) {
        Object[] objArr = trieNode.buffer;
        if (objArr.length == 2 && trieNode.nodeMap == 0) {
            if (this.buffer.length == 1) {
                trieNode.dataMap = this.nodeMap;
                return trieNode;
            }
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(i2);
            Object[] objArr2 = this.buffer;
            Object obj = objArr[0];
            Object obj2 = objArr[1];
            Object[] copyOf = Arrays.copyOf(objArr2, objArr2.length + 1);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            ArraysKt.copyInto(copyOf, copyOf, i + 2, i + 1, objArr2.length);
            ArraysKt.copyInto(copyOf, copyOf, entryKeyIndex$runtime_release + 2, entryKeyIndex$runtime_release, i);
            copyOf[entryKeyIndex$runtime_release] = obj;
            copyOf[entryKeyIndex$runtime_release + 1] = obj2;
            return new TrieNode(this.dataMap ^ i2, this.nodeMap ^ i2, copyOf, null);
        }
        Object[] objArr3 = this.buffer;
        Object[] copyOf2 = Arrays.copyOf(objArr3, objArr3.length);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
        copyOf2[i] = trieNode;
        return new TrieNode(this.dataMap, this.nodeMap, copyOf2, null);
    }

    private final Object valueAtKeyIndex(int i) {
        return this.buffer[i + 1];
    }

    public final boolean containsKey(int i, int i2, Object obj) {
        int i3 = 1 << ((i >> i2) & 31);
        if (hasEntryAt$runtime_release(i3)) {
            return Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release(i3)]);
        } else if (hasNodeAt(i3)) {
            TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release(i3));
            if (i2 == 30) {
                return nodeAtIndex$runtime_release.collisionContainsKey(obj);
            }
            return nodeAtIndex$runtime_release.containsKey(i, i2 + 5, obj);
        } else {
            return false;
        }
    }

    public final int entryCount$runtime_release() {
        return Integer.bitCount(this.dataMap);
    }

    public final int entryKeyIndex$runtime_release(int i) {
        return Integer.bitCount(this.dataMap & (i - 1)) * 2;
    }

    public final Object get(int i, int i2, Object obj) {
        int i3 = 1 << ((i >> i2) & 31);
        if (hasEntryAt$runtime_release(i3)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(i3);
            if (!Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release])) {
                return null;
            }
            return valueAtKeyIndex(entryKeyIndex$runtime_release);
        } else if (!hasNodeAt(i3)) {
            return null;
        } else {
            TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release(i3));
            if (i2 == 30) {
                IntProgression step = RangesKt.step(RangesKt.until(0, nodeAtIndex$runtime_release.buffer.length), 2);
                int first = step.getFirst();
                int last = step.getLast();
                int step2 = step.getStep();
                if ((step2 <= 0 || first > last) && (step2 >= 0 || last > first)) {
                    return null;
                }
                while (!Intrinsics.areEqual(obj, nodeAtIndex$runtime_release.buffer[first])) {
                    if (first == last) {
                        return null;
                    }
                    first += step2;
                }
                return nodeAtIndex$runtime_release.valueAtKeyIndex(first);
            }
            return nodeAtIndex$runtime_release.get(i, i2 + 5, obj);
        }
    }

    public final Object[] getBuffer$runtime_release() {
        return this.buffer;
    }

    public final boolean hasEntryAt$runtime_release(int i) {
        if ((this.dataMap & i) != 0) {
            return true;
        }
        return false;
    }

    public final TrieNode mutablePut(int i, Object obj, Object obj2, int i2, PersistentHashMapBuilder mutator) {
        TrieNode mutablePut;
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        int i3 = 1 << ((i >> i2) & 31);
        boolean hasEntryAt$runtime_release = hasEntryAt$runtime_release(i3);
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        if (hasEntryAt$runtime_release) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(i3);
            if (Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release])) {
                mutator.setOperationResult$runtime_release(valueAtKeyIndex(entryKeyIndex$runtime_release));
                if (valueAtKeyIndex(entryKeyIndex$runtime_release) == obj2) {
                    return this;
                }
                if (mutabilityOwnership == mutator.getOwnership$runtime_release()) {
                    this.buffer[entryKeyIndex$runtime_release + 1] = obj2;
                    return this;
                }
                mutator.setModCount$runtime_release(mutator.getModCount$runtime_release() + 1);
                Object[] objArr = this.buffer;
                Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
                copyOf[entryKeyIndex$runtime_release + 1] = obj2;
                return new TrieNode(this.dataMap, this.nodeMap, copyOf, mutator.getOwnership$runtime_release());
            }
            mutator.setSize(mutator.getSize() + 1);
            MutabilityOwnership ownership$runtime_release = mutator.getOwnership$runtime_release();
            if (mutabilityOwnership == ownership$runtime_release) {
                this.buffer = bufferMoveEntryToNode(entryKeyIndex$runtime_release, i3, i, obj, obj2, i2, ownership$runtime_release);
                this.dataMap ^= i3;
                this.nodeMap |= i3;
                return this;
            }
            return new TrieNode(this.dataMap ^ i3, this.nodeMap | i3, bufferMoveEntryToNode(entryKeyIndex$runtime_release, i3, i, obj, obj2, i2, ownership$runtime_release), ownership$runtime_release);
        } else if (hasNodeAt(i3)) {
            int nodeIndex$runtime_release = nodeIndex$runtime_release(i3);
            TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
            if (i2 == 30) {
                IntProgression step = RangesKt.step(RangesKt.until(0, nodeAtIndex$runtime_release.buffer.length), 2);
                int first = step.getFirst();
                int last = step.getLast();
                int step2 = step.getStep();
                if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                    while (!Intrinsics.areEqual(obj, nodeAtIndex$runtime_release.buffer[first])) {
                        if (first != last) {
                            first += step2;
                        }
                    }
                    mutator.setOperationResult$runtime_release(nodeAtIndex$runtime_release.valueAtKeyIndex(first));
                    if (nodeAtIndex$runtime_release.ownedBy == mutator.getOwnership$runtime_release()) {
                        nodeAtIndex$runtime_release.buffer[first + 1] = obj2;
                        mutablePut = nodeAtIndex$runtime_release;
                    } else {
                        mutator.setModCount$runtime_release(mutator.getModCount$runtime_release() + 1);
                        Object[] objArr2 = nodeAtIndex$runtime_release.buffer;
                        Object[] copyOf2 = Arrays.copyOf(objArr2, objArr2.length);
                        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
                        copyOf2[first + 1] = obj2;
                        mutablePut = new TrieNode(0, 0, copyOf2, mutator.getOwnership$runtime_release());
                    }
                }
                mutator.setSize(mutator.getSize() + 1);
                mutablePut = new TrieNode(0, 0, TrieNodeKt.access$insertEntryAtIndex(nodeAtIndex$runtime_release.buffer, 0, obj, obj2), mutator.getOwnership$runtime_release());
                break;
            }
            mutablePut = nodeAtIndex$runtime_release.mutablePut(i, obj, obj2, i2 + 5, mutator);
            if (nodeAtIndex$runtime_release == mutablePut) {
                return this;
            }
            return mutableUpdateNodeAtIndex(nodeIndex$runtime_release, mutablePut, mutator.getOwnership$runtime_release());
        } else {
            mutator.setSize(mutator.getSize() + 1);
            MutabilityOwnership ownership$runtime_release2 = mutator.getOwnership$runtime_release();
            int entryKeyIndex$runtime_release2 = entryKeyIndex$runtime_release(i3);
            if (mutabilityOwnership == ownership$runtime_release2) {
                this.buffer = TrieNodeKt.access$insertEntryAtIndex(this.buffer, entryKeyIndex$runtime_release2, obj, obj2);
                this.dataMap |= i3;
                return this;
            }
            return new TrieNode(this.dataMap | i3, this.nodeMap, TrieNodeKt.access$insertEntryAtIndex(this.buffer, entryKeyIndex$runtime_release2, obj, obj2), ownership$runtime_release2);
        }
    }

    public final TrieNode mutablePutAll(TrieNode trieNode, int i, DeltaCounter deltaCounter, PersistentHashMapBuilder mutator) {
        boolean z;
        TrieNode trieNode2;
        Object[] objArr;
        int i2;
        int i3;
        int i4;
        TrieNode makeNode;
        int i5;
        int i6;
        int i7;
        int i8;
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        if (this == trieNode) {
            deltaCounter.plusAssign(calculateSize());
            return this;
        }
        int i9 = 0;
        if (i > 30) {
            MutabilityOwnership ownership$runtime_release = mutator.getOwnership$runtime_release();
            Object[] objArr2 = this.buffer;
            Object[] copyOf = Arrays.copyOf(objArr2, objArr2.length + trieNode.buffer.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            int length = this.buffer.length;
            IntProgression step = RangesKt.step(RangesKt.until(0, trieNode.buffer.length), 2);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                while (true) {
                    if (!collisionContainsKey(trieNode.buffer[first])) {
                        Object[] objArr3 = trieNode.buffer;
                        copyOf[length] = objArr3[first];
                        copyOf[length + 1] = objArr3[first + 1];
                        length += 2;
                    } else {
                        deltaCounter.setCount(deltaCounter.getCount() + 1);
                    }
                    if (first == last) {
                        break;
                    }
                    first += step2;
                }
            }
            if (length == this.buffer.length) {
                return this;
            }
            if (length == trieNode.buffer.length) {
                return trieNode;
            }
            if (length == copyOf.length) {
                return new TrieNode(0, 0, copyOf, ownership$runtime_release);
            }
            Object[] copyOf2 = Arrays.copyOf(copyOf, length);
            Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
            return new TrieNode(0, 0, copyOf2, ownership$runtime_release);
        }
        int i10 = this.nodeMap | trieNode.nodeMap;
        int i11 = this.dataMap;
        int i12 = trieNode.dataMap;
        int i13 = i11 & i12;
        int i14 = (i11 ^ i12) & (~i10);
        while (i13 != 0) {
            int lowestOneBit = Integer.lowestOneBit(i13);
            if (Intrinsics.areEqual(this.buffer[entryKeyIndex$runtime_release(lowestOneBit)], trieNode.buffer[trieNode.entryKeyIndex$runtime_release(lowestOneBit)])) {
                i14 |= lowestOneBit;
            } else {
                i10 |= lowestOneBit;
            }
            i13 ^= lowestOneBit;
        }
        if ((i10 & i14) == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (Intrinsics.areEqual(this.ownedBy, mutator.getOwnership$runtime_release()) && this.dataMap == i14 && this.nodeMap == i10) {
                trieNode2 = this;
            } else {
                trieNode2 = new TrieNode(i14, i10, new Object[Integer.bitCount(i10) + (Integer.bitCount(i14) * 2)], null);
            }
            int i15 = i10;
            int i16 = 0;
            while (i15 != 0) {
                int lowestOneBit2 = Integer.lowestOneBit(i15);
                Object[] objArr4 = trieNode2.buffer;
                int length2 = (objArr4.length - 1) - i16;
                if (hasNodeAt(lowestOneBit2)) {
                    makeNode = nodeAtIndex$runtime_release(nodeIndex$runtime_release(lowestOneBit2));
                    if (trieNode.hasNodeAt(lowestOneBit2)) {
                        makeNode = makeNode.mutablePutAll(trieNode.nodeAtIndex$runtime_release(trieNode.nodeIndex$runtime_release(lowestOneBit2)), i + 5, deltaCounter, mutator);
                    } else if (trieNode.hasEntryAt$runtime_release(lowestOneBit2)) {
                        int entryKeyIndex$runtime_release = trieNode.entryKeyIndex$runtime_release(lowestOneBit2);
                        Object obj = trieNode.buffer[entryKeyIndex$runtime_release];
                        Object valueAtKeyIndex = trieNode.valueAtKeyIndex(entryKeyIndex$runtime_release);
                        int size = mutator.getSize();
                        if (obj != null) {
                            i7 = obj.hashCode();
                        } else {
                            i7 = i9;
                        }
                        int i17 = i7;
                        objArr = objArr4;
                        i8 = lowestOneBit2;
                        makeNode = makeNode.mutablePut(i17, obj, valueAtKeyIndex, i + 5, mutator);
                        if (mutator.getSize() == size) {
                            deltaCounter.setCount(deltaCounter.getCount() + 1);
                        }
                        i2 = i8;
                    }
                    objArr = objArr4;
                    i8 = lowestOneBit2;
                    i2 = i8;
                } else {
                    objArr = objArr4;
                    i2 = lowestOneBit2;
                    if (trieNode.hasNodeAt(i2)) {
                        makeNode = trieNode.nodeAtIndex$runtime_release(trieNode.nodeIndex$runtime_release(i2));
                        if (hasEntryAt$runtime_release(i2)) {
                            int entryKeyIndex$runtime_release2 = entryKeyIndex$runtime_release(i2);
                            Object obj2 = this.buffer[entryKeyIndex$runtime_release2];
                            if (obj2 != null) {
                                i5 = obj2.hashCode();
                            } else {
                                i5 = 0;
                            }
                            int i18 = i + 5;
                            if (makeNode.containsKey(i5, i18, obj2)) {
                                deltaCounter.setCount(deltaCounter.getCount() + 1);
                            } else {
                                Object valueAtKeyIndex2 = valueAtKeyIndex(entryKeyIndex$runtime_release2);
                                if (obj2 != null) {
                                    i6 = obj2.hashCode();
                                } else {
                                    i6 = 0;
                                }
                                makeNode = makeNode.mutablePut(i6, obj2, valueAtKeyIndex2, i18, mutator);
                            }
                        }
                    } else {
                        int entryKeyIndex$runtime_release3 = entryKeyIndex$runtime_release(i2);
                        Object obj3 = this.buffer[entryKeyIndex$runtime_release3];
                        Object valueAtKeyIndex3 = valueAtKeyIndex(entryKeyIndex$runtime_release3);
                        int entryKeyIndex$runtime_release4 = trieNode.entryKeyIndex$runtime_release(i2);
                        Object obj4 = trieNode.buffer[entryKeyIndex$runtime_release4];
                        Object valueAtKeyIndex4 = trieNode.valueAtKeyIndex(entryKeyIndex$runtime_release4);
                        if (obj3 != null) {
                            i3 = obj3.hashCode();
                        } else {
                            i3 = 0;
                        }
                        if (obj4 != null) {
                            i4 = obj4.hashCode();
                        } else {
                            i4 = 0;
                        }
                        makeNode = makeNode(i3, obj3, valueAtKeyIndex3, i4, obj4, valueAtKeyIndex4, i + 5, mutator.getOwnership$runtime_release());
                    }
                }
                objArr[length2] = makeNode;
                i16++;
                i15 ^= i2;
                i9 = 0;
            }
            int i19 = 0;
            while (i14 != 0) {
                int lowestOneBit3 = Integer.lowestOneBit(i14);
                int i20 = i19 * 2;
                if (!trieNode.hasEntryAt$runtime_release(lowestOneBit3)) {
                    int entryKeyIndex$runtime_release5 = entryKeyIndex$runtime_release(lowestOneBit3);
                    Object[] objArr5 = trieNode2.buffer;
                    objArr5[i20] = this.buffer[entryKeyIndex$runtime_release5];
                    objArr5[i20 + 1] = valueAtKeyIndex(entryKeyIndex$runtime_release5);
                } else {
                    int entryKeyIndex$runtime_release6 = trieNode.entryKeyIndex$runtime_release(lowestOneBit3);
                    Object[] objArr6 = trieNode2.buffer;
                    objArr6[i20] = trieNode.buffer[entryKeyIndex$runtime_release6];
                    objArr6[i20 + 1] = trieNode.valueAtKeyIndex(entryKeyIndex$runtime_release6);
                    if (hasEntryAt$runtime_release(lowestOneBit3)) {
                        deltaCounter.setCount(deltaCounter.getCount() + 1);
                    }
                }
                i19++;
                i14 ^= lowestOneBit3;
            }
            if (elementsIdentityEquals(trieNode2)) {
                return this;
            }
            if (trieNode.elementsIdentityEquals(trieNode2)) {
                return trieNode;
            }
            return trieNode2;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final TrieNode mutableRemove(int i, Object obj, int i2, PersistentHashMapBuilder mutator) {
        TrieNode mutableRemove;
        TrieNode trieNode;
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        int i3 = 1 << ((i >> i2) & 31);
        if (hasEntryAt$runtime_release(i3)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(i3);
            return Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release]) ? mutableRemoveEntryAtIndex(entryKeyIndex$runtime_release, i3, mutator) : this;
        } else if (hasNodeAt(i3)) {
            int nodeIndex$runtime_release = nodeIndex$runtime_release(i3);
            TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
            if (i2 == 30) {
                IntProgression step = RangesKt.step(RangesKt.until(0, nodeAtIndex$runtime_release.buffer.length), 2);
                int first = step.getFirst();
                int last = step.getLast();
                int step2 = step.getStep();
                if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                    while (!Intrinsics.areEqual(obj, nodeAtIndex$runtime_release.buffer[first])) {
                        if (first != last) {
                            first += step2;
                        }
                    }
                    mutableRemove = nodeAtIndex$runtime_release.mutableCollisionRemoveEntryAtIndex(first, mutator);
                }
                trieNode = nodeAtIndex$runtime_release;
                return mutableReplaceNode(nodeAtIndex$runtime_release, trieNode, nodeIndex$runtime_release, i3, mutator.getOwnership$runtime_release());
            }
            mutableRemove = nodeAtIndex$runtime_release.mutableRemove(i, obj, i2 + 5, mutator);
            trieNode = mutableRemove;
            return mutableReplaceNode(nodeAtIndex$runtime_release, trieNode, nodeIndex$runtime_release, i3, mutator.getOwnership$runtime_release());
        } else {
            return this;
        }
    }

    public final TrieNode nodeAtIndex$runtime_release(int i) {
        Object obj = this.buffer[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode>");
        return (TrieNode) obj;
    }

    public final int nodeIndex$runtime_release(int i) {
        return (this.buffer.length - 1) - Integer.bitCount(this.nodeMap & (i - 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00d1 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode.ModificationResult put(int r12, int r13, java.lang.Object r14, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links r15) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode.put(int, int, java.lang.Object, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links):androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode$ModificationResult");
    }

    public final TrieNode remove(int i, int i2, Object obj) {
        TrieNode remove;
        int i3 = 1 << ((i >> i2) & 31);
        if (hasEntryAt$runtime_release(i3)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(i3);
            if (Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release])) {
                Object[] objArr = this.buffer;
                if (objArr.length == 2) {
                    return null;
                }
                return new TrieNode(this.dataMap ^ i3, this.nodeMap, TrieNodeKt.access$removeEntryAtIndex(objArr, entryKeyIndex$runtime_release), null);
            }
            return this;
        } else if (hasNodeAt(i3)) {
            int nodeIndex$runtime_release = nodeIndex$runtime_release(i3);
            TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
            if (i2 == 30) {
                IntProgression step = RangesKt.step(RangesKt.until(0, nodeAtIndex$runtime_release.buffer.length), 2);
                int first = step.getFirst();
                int last = step.getLast();
                int step2 = step.getStep();
                if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                    while (!Intrinsics.areEqual(obj, nodeAtIndex$runtime_release.buffer[first])) {
                        if (first != last) {
                            first += step2;
                        }
                    }
                    Object[] objArr2 = nodeAtIndex$runtime_release.buffer;
                    if (objArr2.length == 2) {
                        remove = null;
                    } else {
                        remove = new TrieNode(0, 0, TrieNodeKt.access$removeEntryAtIndex(objArr2, first), null);
                    }
                }
                remove = nodeAtIndex$runtime_release;
                break;
            }
            remove = nodeAtIndex$runtime_release.remove(i, i2 + 5, obj);
            if (remove == null) {
                Object[] objArr3 = this.buffer;
                if (objArr3.length == 1) {
                    return null;
                }
                return new TrieNode(this.dataMap, this.nodeMap ^ i3, TrieNodeKt.access$removeNodeAtIndex(objArr3, nodeIndex$runtime_release), null);
            } else if (nodeAtIndex$runtime_release != remove) {
                return updateNodeAtIndex(nodeIndex$runtime_release, i3, remove);
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    public final TrieNode mutableRemove(int i, Object obj, Object obj2, int i2, PersistentHashMapBuilder mutator) {
        TrieNode mutableRemove;
        TrieNode trieNode;
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        int i3 = 1 << ((i >> i2) & 31);
        if (hasEntryAt$runtime_release(i3)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(i3);
            return (Intrinsics.areEqual(obj, this.buffer[entryKeyIndex$runtime_release]) && Intrinsics.areEqual(obj2, valueAtKeyIndex(entryKeyIndex$runtime_release))) ? mutableRemoveEntryAtIndex(entryKeyIndex$runtime_release, i3, mutator) : this;
        } else if (hasNodeAt(i3)) {
            int nodeIndex$runtime_release = nodeIndex$runtime_release(i3);
            TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
            if (i2 == 30) {
                IntProgression step = RangesKt.step(RangesKt.until(0, nodeAtIndex$runtime_release.buffer.length), 2);
                int first = step.getFirst();
                int last = step.getLast();
                int step2 = step.getStep();
                if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                    while (true) {
                        if (!Intrinsics.areEqual(obj, nodeAtIndex$runtime_release.buffer[first]) || !Intrinsics.areEqual(obj2, nodeAtIndex$runtime_release.valueAtKeyIndex(first))) {
                            if (first == last) {
                                break;
                            }
                            first += step2;
                        } else {
                            mutableRemove = nodeAtIndex$runtime_release.mutableCollisionRemoveEntryAtIndex(first, mutator);
                            break;
                        }
                    }
                }
                trieNode = nodeAtIndex$runtime_release;
                return mutableReplaceNode(nodeAtIndex$runtime_release, trieNode, nodeIndex$runtime_release, i3, mutator.getOwnership$runtime_release());
            }
            mutableRemove = nodeAtIndex$runtime_release.mutableRemove(i, obj, obj2, i2 + 5, mutator);
            trieNode = mutableRemove;
            return mutableReplaceNode(nodeAtIndex$runtime_release, trieNode, nodeIndex$runtime_release, i3, mutator.getOwnership$runtime_release());
        } else {
            return this;
        }
    }
}
