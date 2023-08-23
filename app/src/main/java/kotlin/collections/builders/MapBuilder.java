package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MapBuilder implements Map, Serializable, KMutableMap {
    private static final Companion Companion = new Companion();
    private MapBuilderEntries entriesView;
    private int[] hashArray;
    private int hashShift;
    private boolean isReadOnly;
    private Object[] keysArray;
    private MapBuilderKeys keysView;
    private int length;
    private int maxProbeDistance;
    private int[] presenceArray;
    private int size;
    private Object[] valuesArray;
    private MapBuilderValues valuesView;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class Companion {
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class EntryRef implements Map.Entry, KMappedMarker {
        private final int index;
        private final MapBuilder map;

        public EntryRef(MapBuilder map, int i) {
            Intrinsics.checkNotNullParameter(map, "map");
            this.map = map;
            this.index = i;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (Intrinsics.areEqual(entry.getKey(), getKey()) && Intrinsics.areEqual(entry.getValue(), getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.map.keysArray[this.index];
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            Object[] objArr = this.map.valuesArray;
            Intrinsics.checkNotNull(objArr);
            return objArr[this.index];
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            int i;
            Object key = getKey();
            int i2 = 0;
            if (key != null) {
                i = key.hashCode();
            } else {
                i = 0;
            }
            Object value = getValue();
            if (value != null) {
                i2 = value.hashCode();
            }
            return i ^ i2;
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            this.map.checkIsMutable$kotlin_stdlib();
            Object[] access$allocateValuesArray = MapBuilder.access$allocateValuesArray(this.map);
            int i = this.index;
            Object obj2 = access$allocateValuesArray[i];
            access$allocateValuesArray[i] = obj;
            return obj2;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getKey());
            sb.append('=');
            sb.append(getValue());
            return sb.toString();
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class Itr {
        private int index;
        private int lastIndex;
        private final MapBuilder map;

        public Itr(MapBuilder map) {
            Intrinsics.checkNotNullParameter(map, "map");
            this.map = map;
            this.lastIndex = -1;
            initNext$kotlin_stdlib();
        }

        public final int getIndex$kotlin_stdlib() {
            return this.index;
        }

        public final int getLastIndex$kotlin_stdlib() {
            return this.lastIndex;
        }

        public final MapBuilder getMap$kotlin_stdlib() {
            return this.map;
        }

        public final boolean hasNext() {
            if (this.index < this.map.length) {
                return true;
            }
            return false;
        }

        public final void initNext$kotlin_stdlib() {
            while (true) {
                int i = this.index;
                MapBuilder mapBuilder = this.map;
                if (i < mapBuilder.length) {
                    int[] iArr = mapBuilder.presenceArray;
                    int i2 = this.index;
                    if (iArr[i2] < 0) {
                        this.index = i2 + 1;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        public final void remove() {
            boolean z;
            if (this.lastIndex != -1) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                MapBuilder mapBuilder = this.map;
                mapBuilder.checkIsMutable$kotlin_stdlib();
                mapBuilder.removeKeyAt(this.lastIndex);
                this.lastIndex = -1;
                return;
            }
            throw new IllegalStateException("Call next() before removing element from the iterator.".toString());
        }

        public final void setIndex$kotlin_stdlib(int i) {
            this.index = i;
        }

        public final void setLastIndex$kotlin_stdlib(int i) {
            this.lastIndex = i;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class KeysItr extends Itr implements Iterator, KMappedMarker {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KeysItr(MapBuilder map, int i) {
            super(map);
            this.$r8$classId = i;
            if (i != 1) {
                if (i != 2) {
                    Intrinsics.checkNotNullParameter(map, "map");
                    return;
                }
                Intrinsics.checkNotNullParameter(map, "map");
                super(map);
                return;
            }
            Intrinsics.checkNotNullParameter(map, "map");
            super(map);
        }

        @Override // java.util.Iterator
        public final Object next() {
            switch (this.$r8$classId) {
                case 0:
                    if (getIndex$kotlin_stdlib() < getMap$kotlin_stdlib().length) {
                        int index$kotlin_stdlib = getIndex$kotlin_stdlib();
                        setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
                        setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
                        Object obj = getMap$kotlin_stdlib().keysArray[getLastIndex$kotlin_stdlib()];
                        initNext$kotlin_stdlib();
                        return obj;
                    }
                    throw new NoSuchElementException();
                case 1:
                    if (getIndex$kotlin_stdlib() < getMap$kotlin_stdlib().length) {
                        int index$kotlin_stdlib2 = getIndex$kotlin_stdlib();
                        setIndex$kotlin_stdlib(index$kotlin_stdlib2 + 1);
                        setLastIndex$kotlin_stdlib(index$kotlin_stdlib2);
                        EntryRef entryRef = new EntryRef(getMap$kotlin_stdlib(), getLastIndex$kotlin_stdlib());
                        initNext$kotlin_stdlib();
                        return entryRef;
                    }
                    throw new NoSuchElementException();
                default:
                    if (getIndex$kotlin_stdlib() < getMap$kotlin_stdlib().length) {
                        int index$kotlin_stdlib3 = getIndex$kotlin_stdlib();
                        setIndex$kotlin_stdlib(index$kotlin_stdlib3 + 1);
                        setLastIndex$kotlin_stdlib(index$kotlin_stdlib3);
                        Object[] objArr = getMap$kotlin_stdlib().valuesArray;
                        Intrinsics.checkNotNull(objArr);
                        Object obj2 = objArr[getLastIndex$kotlin_stdlib()];
                        initNext$kotlin_stdlib();
                        return obj2;
                    }
                    throw new NoSuchElementException();
            }
        }
    }

    public MapBuilder(int i) {
        Object[] arrayOfUninitializedElements = ListBuilderKt.arrayOfUninitializedElements(i);
        int[] iArr = new int[i];
        int highestOneBit = Integer.highestOneBit((i < 1 ? 1 : i) * 3);
        this.keysArray = arrayOfUninitializedElements;
        this.valuesArray = null;
        this.presenceArray = iArr;
        this.hashArray = new int[highestOneBit];
        this.maxProbeDistance = 2;
        this.length = 0;
        this.hashShift = Integer.numberOfLeadingZeros(highestOneBit) + 1;
    }

    public static final Object[] access$allocateValuesArray(MapBuilder mapBuilder) {
        Object[] objArr = mapBuilder.valuesArray;
        if (objArr == null) {
            Object[] arrayOfUninitializedElements = ListBuilderKt.arrayOfUninitializedElements(mapBuilder.keysArray.length);
            mapBuilder.valuesArray = arrayOfUninitializedElements;
            return arrayOfUninitializedElements;
        }
        return objArr;
    }

    private final void ensureExtraCapacity(int i) {
        Object[] objArr;
        int i2 = this.length;
        int i3 = i + i2;
        if (i3 >= 0) {
            Object[] objArr2 = this.keysArray;
            if (i3 > objArr2.length) {
                int length = (objArr2.length * 3) / 2;
                if (i3 <= length) {
                    i3 = length;
                }
                Object[] copyOf = Arrays.copyOf(objArr2, i3);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                this.keysArray = copyOf;
                Object[] objArr3 = this.valuesArray;
                if (objArr3 != null) {
                    objArr = Arrays.copyOf(objArr3, i3);
                    Intrinsics.checkNotNullExpressionValue(objArr, "copyOf(this, newSize)");
                } else {
                    objArr = null;
                }
                this.valuesArray = objArr;
                int[] copyOf2 = Arrays.copyOf(this.presenceArray, i3);
                Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
                this.presenceArray = copyOf2;
                if (i3 < 1) {
                    i3 = 1;
                }
                int highestOneBit = Integer.highestOneBit(i3 * 3);
                if (highestOneBit > this.hashArray.length) {
                    rehash(highestOneBit);
                    return;
                }
                return;
            } else if ((i2 + i3) - this.size > objArr2.length) {
                rehash(this.hashArray.length);
                return;
            } else {
                return;
            }
        }
        throw new OutOfMemoryError();
    }

    private final int findKey(Object obj) {
        int hash = hash(obj);
        int i = this.maxProbeDistance;
        while (true) {
            int i2 = this.hashArray[hash];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (Intrinsics.areEqual(this.keysArray[i3], obj)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            int i4 = hash - 1;
            if (hash == 0) {
                hash = this.hashArray.length - 1;
            } else {
                hash = i4;
            }
        }
    }

    private final int hash(Object obj) {
        int i;
        if (obj != null) {
            i = obj.hashCode();
        } else {
            i = 0;
        }
        return (i * (-1640531527)) >>> this.hashShift;
    }

    private final void rehash(int i) {
        boolean z;
        int i2;
        if (this.length > this.size) {
            Object[] objArr = this.valuesArray;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                i2 = this.length;
                if (i3 >= i2) {
                    break;
                }
                if (this.presenceArray[i3] >= 0) {
                    Object[] objArr2 = this.keysArray;
                    objArr2[i4] = objArr2[i3];
                    if (objArr != null) {
                        objArr[i4] = objArr[i3];
                    }
                    i4++;
                }
                i3++;
            }
            ListBuilderKt.resetRange(i4, i2, this.keysArray);
            if (objArr != null) {
                ListBuilderKt.resetRange(i4, this.length, objArr);
            }
            this.length = i4;
        }
        int[] iArr = this.hashArray;
        if (i != iArr.length) {
            this.hashArray = new int[i];
            this.hashShift = Integer.numberOfLeadingZeros(i) + 1;
        } else {
            Arrays.fill(iArr, 0, iArr.length, 0);
        }
        int i5 = 0;
        while (i5 < this.length) {
            int i6 = i5 + 1;
            int hash = hash(this.keysArray[i5]);
            int i7 = this.maxProbeDistance;
            while (true) {
                int[] iArr2 = this.hashArray;
                if (iArr2[hash] == 0) {
                    iArr2[hash] = i6;
                    this.presenceArray[i5] = hash;
                    z = true;
                    break;
                }
                i7--;
                if (i7 < 0) {
                    z = false;
                    break;
                }
                int i8 = hash - 1;
                if (hash == 0) {
                    hash = iArr2.length - 1;
                } else {
                    hash = i8;
                }
            }
            if (z) {
                i5 = i6;
            } else {
                throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void removeKeyAt(int r12) {
        /*
            r11 = this;
            java.lang.Object[] r0 = r11.keysArray
            java.lang.String r1 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r1 = 0
            r0[r12] = r1
            int[] r0 = r11.presenceArray
            r0 = r0[r12]
            int r1 = r11.maxProbeDistance
            int r1 = r1 * 2
            int[] r2 = r11.hashArray
            int r2 = r2.length
            int r2 = r2 / 2
            if (r1 <= r2) goto L1a
            r1 = r2
        L1a:
            r2 = 0
            r3 = r1
            r4 = r2
            r1 = r0
        L1e:
            int r5 = r0 + (-1)
            r6 = -1
            if (r0 != 0) goto L28
            int[] r0 = r11.hashArray
            int r0 = r0.length
            int r0 = r0 + r6
            goto L29
        L28:
            r0 = r5
        L29:
            int r4 = r4 + 1
            int r5 = r11.maxProbeDistance
            if (r4 <= r5) goto L34
            int[] r0 = r11.hashArray
            r0[r1] = r2
            goto L63
        L34:
            int[] r5 = r11.hashArray
            r7 = r5[r0]
            if (r7 != 0) goto L3d
            r5[r1] = r2
            goto L63
        L3d:
            if (r7 >= 0) goto L42
            r5[r1] = r6
            goto L5a
        L42:
            java.lang.Object[] r5 = r11.keysArray
            int r8 = r7 + (-1)
            r5 = r5[r8]
            int r5 = r11.hash(r5)
            int r5 = r5 - r0
            int[] r9 = r11.hashArray
            int r10 = r9.length
            int r10 = r10 + r6
            r5 = r5 & r10
            if (r5 < r4) goto L5c
            r9[r1] = r7
            int[] r4 = r11.presenceArray
            r4[r8] = r1
        L5a:
            r1 = r0
            r4 = r2
        L5c:
            int r3 = r3 + r6
            if (r3 >= 0) goto L1e
            int[] r0 = r11.hashArray
            r0[r1] = r6
        L63:
            int[] r0 = r11.presenceArray
            r0[r12] = r6
            int r12 = r11.size
            int r12 = r12 + r6
            r11.size = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.builders.MapBuilder.removeKeyAt(int):void");
    }

    private final Object writeReplace() {
        if (this.isReadOnly) {
            return new SerializedMap(this);
        }
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    public final int addKey$kotlin_stdlib(Object obj) {
        checkIsMutable$kotlin_stdlib();
        while (true) {
            int hash = hash(obj);
            int i = this.maxProbeDistance * 2;
            int length = this.hashArray.length / 2;
            if (i > length) {
                i = length;
            }
            int i2 = 0;
            while (true) {
                int[] iArr = this.hashArray;
                int i3 = iArr[hash];
                if (i3 <= 0) {
                    int i4 = this.length;
                    Object[] objArr = this.keysArray;
                    if (i4 >= objArr.length) {
                        ensureExtraCapacity(1);
                    } else {
                        int i5 = i4 + 1;
                        this.length = i5;
                        objArr[i4] = obj;
                        this.presenceArray[i4] = hash;
                        iArr[hash] = i5;
                        this.size++;
                        if (i2 > this.maxProbeDistance) {
                            this.maxProbeDistance = i2;
                        }
                        return i4;
                    }
                } else if (Intrinsics.areEqual(this.keysArray[i3 - 1], obj)) {
                    return -i3;
                } else {
                    i2++;
                    if (i2 > i) {
                        rehash(this.hashArray.length * 2);
                        break;
                    }
                    int i6 = hash - 1;
                    if (hash == 0) {
                        hash = this.hashArray.length - 1;
                    } else {
                        hash = i6;
                    }
                }
            }
        }
    }

    public final void build$1() {
        checkIsMutable$kotlin_stdlib();
        this.isReadOnly = true;
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if (!this.isReadOnly) {
            return;
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void clear() {
        checkIsMutable$kotlin_stdlib();
        IntProgressionIterator it = new IntRange(0, this.length - 1).iterator();
        while (it.hasNext()) {
            int nextInt = it.nextInt();
            int[] iArr = this.presenceArray;
            int i = iArr[nextInt];
            if (i >= 0) {
                this.hashArray[i] = 0;
                iArr[nextInt] = -1;
            }
        }
        ListBuilderKt.resetRange(0, this.length, this.keysArray);
        Object[] objArr = this.valuesArray;
        if (objArr != null) {
            ListBuilderKt.resetRange(0, this.length, objArr);
        }
        this.size = 0;
        this.length = 0;
    }

    public final boolean containsAllEntries$kotlin_stdlib(Collection m) {
        Intrinsics.checkNotNullParameter(m, "m");
        for (Object obj : m) {
            if (obj != null) {
                try {
                    if (!containsEntry$kotlin_stdlib((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        int findKey = findKey(entry.getKey());
        if (findKey < 0) {
            return false;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        return Intrinsics.areEqual(objArr[findKey], entry.getValue());
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        if (findKey(obj) >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        int i;
        int i2 = this.length;
        while (true) {
            i = -1;
            i2--;
            if (i2 < 0) {
                break;
            } else if (this.presenceArray[i2] >= 0) {
                Object[] objArr = this.valuesArray;
                Intrinsics.checkNotNull(objArr);
                if (Intrinsics.areEqual(objArr[i2], obj)) {
                    i = i2;
                    break;
                }
            }
        }
        if (i >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        MapBuilderEntries mapBuilderEntries = this.entriesView;
        if (mapBuilderEntries == null) {
            MapBuilderEntries mapBuilderEntries2 = new MapBuilderEntries(this);
            this.entriesView = mapBuilderEntries2;
            return mapBuilderEntries2;
        }
        return mapBuilderEntries;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this.size == map.size() && containsAllEntries$kotlin_stdlib(map.entrySet())) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        int findKey = findKey(obj);
        if (findKey < 0) {
            return null;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        return objArr[findKey];
    }

    @Override // java.util.Map
    public final int hashCode() {
        int i;
        int i2;
        KeysItr keysItr = new KeysItr(this, 1);
        int i3 = 0;
        while (keysItr.hasNext()) {
            if (keysItr.getIndex$kotlin_stdlib() < keysItr.getMap$kotlin_stdlib().length) {
                int index$kotlin_stdlib = keysItr.getIndex$kotlin_stdlib();
                keysItr.setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
                keysItr.setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
                Object obj = keysItr.getMap$kotlin_stdlib().keysArray[keysItr.getLastIndex$kotlin_stdlib()];
                if (obj != null) {
                    i = obj.hashCode();
                } else {
                    i = 0;
                }
                Object[] objArr = keysItr.getMap$kotlin_stdlib().valuesArray;
                Intrinsics.checkNotNull(objArr);
                Object obj2 = objArr[keysItr.getLastIndex$kotlin_stdlib()];
                if (obj2 != null) {
                    i2 = obj2.hashCode();
                } else {
                    i2 = 0;
                }
                keysItr.initNext$kotlin_stdlib();
                i3 += i ^ i2;
            } else {
                throw new NoSuchElementException();
            }
        }
        return i3;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    public final boolean isReadOnly$kotlin_stdlib() {
        return this.isReadOnly;
    }

    @Override // java.util.Map
    public final Set keySet() {
        MapBuilderKeys mapBuilderKeys = this.keysView;
        if (mapBuilderKeys == null) {
            MapBuilderKeys mapBuilderKeys2 = new MapBuilderKeys(this);
            this.keysView = mapBuilderKeys2;
            return mapBuilderKeys2;
        }
        return mapBuilderKeys;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        checkIsMutable$kotlin_stdlib();
        int addKey$kotlin_stdlib = addKey$kotlin_stdlib(obj);
        Object[] objArr = this.valuesArray;
        if (objArr == null) {
            objArr = ListBuilderKt.arrayOfUninitializedElements(this.keysArray.length);
            this.valuesArray = objArr;
        }
        if (addKey$kotlin_stdlib < 0) {
            int i = (-addKey$kotlin_stdlib) - 1;
            Object obj3 = objArr[i];
            objArr[i] = obj2;
            return obj3;
        }
        objArr[addKey$kotlin_stdlib] = obj2;
        return null;
    }

    @Override // java.util.Map
    public final void putAll(Map from) {
        Intrinsics.checkNotNullParameter(from, "from");
        checkIsMutable$kotlin_stdlib();
        Set<Map.Entry> entrySet = from.entrySet();
        if (!entrySet.isEmpty()) {
            ensureExtraCapacity(entrySet.size());
            for (Map.Entry entry : entrySet) {
                int addKey$kotlin_stdlib = addKey$kotlin_stdlib(entry.getKey());
                Object[] objArr = this.valuesArray;
                if (objArr == null) {
                    objArr = ListBuilderKt.arrayOfUninitializedElements(this.keysArray.length);
                    this.valuesArray = objArr;
                }
                if (addKey$kotlin_stdlib >= 0) {
                    objArr[addKey$kotlin_stdlib] = entry.getValue();
                } else {
                    int i = (-addKey$kotlin_stdlib) - 1;
                    if (!Intrinsics.areEqual(entry.getValue(), objArr[i])) {
                        objArr[i] = entry.getValue();
                    }
                }
            }
        }
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        int removeKey$kotlin_stdlib = removeKey$kotlin_stdlib(obj);
        if (removeKey$kotlin_stdlib < 0) {
            return null;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        Object obj2 = objArr[removeKey$kotlin_stdlib];
        objArr[removeKey$kotlin_stdlib] = null;
        return obj2;
    }

    public final boolean removeEntry$kotlin_stdlib(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        checkIsMutable$kotlin_stdlib();
        int findKey = findKey(entry.getKey());
        if (findKey < 0) {
            return false;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        if (!Intrinsics.areEqual(objArr[findKey], entry.getValue())) {
            return false;
        }
        removeKeyAt(findKey);
        return true;
    }

    public final int removeKey$kotlin_stdlib(Object obj) {
        checkIsMutable$kotlin_stdlib();
        int findKey = findKey(obj);
        if (findKey < 0) {
            return -1;
        }
        removeKeyAt(findKey);
        return findKey;
    }

    public final boolean removeValue$kotlin_stdlib(Object obj) {
        int i;
        checkIsMutable$kotlin_stdlib();
        int i2 = this.length;
        while (true) {
            i = -1;
            i2--;
            if (i2 < 0) {
                break;
            } else if (this.presenceArray[i2] >= 0) {
                Object[] objArr = this.valuesArray;
                Intrinsics.checkNotNull(objArr);
                if (Intrinsics.areEqual(objArr[i2], obj)) {
                    i = i2;
                    break;
                }
            }
        }
        if (i < 0) {
            return false;
        }
        removeKeyAt(i);
        return true;
    }

    @Override // java.util.Map
    public final int size() {
        return this.size;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.size * 3) + 2);
        sb.append("{");
        KeysItr keysItr = new KeysItr(this, 1);
        int i = 0;
        while (keysItr.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            if (keysItr.getIndex$kotlin_stdlib() < keysItr.getMap$kotlin_stdlib().length) {
                int index$kotlin_stdlib = keysItr.getIndex$kotlin_stdlib();
                keysItr.setIndex$kotlin_stdlib(index$kotlin_stdlib + 1);
                keysItr.setLastIndex$kotlin_stdlib(index$kotlin_stdlib);
                Object obj = keysItr.getMap$kotlin_stdlib().keysArray[keysItr.getLastIndex$kotlin_stdlib()];
                if (Intrinsics.areEqual(obj, keysItr.getMap$kotlin_stdlib())) {
                    sb.append("(this Map)");
                } else {
                    sb.append(obj);
                }
                sb.append('=');
                Object[] objArr = keysItr.getMap$kotlin_stdlib().valuesArray;
                Intrinsics.checkNotNull(objArr);
                Object obj2 = objArr[keysItr.getLastIndex$kotlin_stdlib()];
                if (Intrinsics.areEqual(obj2, keysItr.getMap$kotlin_stdlib())) {
                    sb.append("(this Map)");
                } else {
                    sb.append(obj2);
                }
                keysItr.initNext$kotlin_stdlib();
                i++;
            } else {
                throw new NoSuchElementException();
            }
        }
        sb.append("}");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
        return sb2;
    }

    @Override // java.util.Map
    public final Collection values() {
        MapBuilderValues mapBuilderValues = this.valuesView;
        if (mapBuilderValues == null) {
            MapBuilderValues mapBuilderValues2 = new MapBuilderValues(this);
            this.valuesView = mapBuilderValues2;
            return mapBuilderValues2;
        }
        return mapBuilderValues;
    }
}
