package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicArray;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LockFreeTaskQueueCore {
    public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
    private final AtomicRef _next = new AtomicRef(null);
    private final AtomicLong _state = new AtomicLong(0);
    private final AtomicArray array;
    private final int capacity;
    private final int mask;
    private final boolean singleConsumer;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Placeholder {
        public final int index;

        public Placeholder(int i) {
            this.index = i;
        }
    }

    public LockFreeTaskQueueCore(int i, boolean z) {
        boolean z2;
        this.capacity = i;
        this.singleConsumer = z;
        int i2 = i - 1;
        this.mask = i2;
        this.array = new AtomicArray(i);
        if (i2 <= 1073741823) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if ((i & i2) == 0) {
                return;
            }
            throw new IllegalStateException("Check failed.".toString());
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final int addLast(Object element) {
        LockFreeTaskQueueCore lockFreeTaskQueueCore = this;
        Intrinsics.checkNotNullParameter(element, "element");
        while (true) {
            AtomicLong atomicLong = lockFreeTaskQueueCore._state;
            long value = atomicLong.getValue();
            if ((3458764513820540928L & value) != 0) {
                if ((2305843009213693952L & value) == 0) {
                    return 1;
                }
                return 2;
            }
            int i = (int) ((1073741823 & value) >> 0);
            int i2 = (int) ((1152921503533105152L & value) >> 30);
            int i3 = lockFreeTaskQueueCore.mask;
            if (((i2 + 2) & i3) == (i & i3)) {
                return 1;
            }
            boolean z = lockFreeTaskQueueCore.singleConsumer;
            AtomicArray atomicArray = lockFreeTaskQueueCore.array;
            if (!z && atomicArray.get(i2 & i3).getValue() != null) {
                int i4 = lockFreeTaskQueueCore.capacity;
                if (i4 < 1024 || ((i2 - i) & 1073741823) > (i4 >> 1)) {
                    break;
                }
            } else {
                if (atomicLong.compareAndSet(value, (((i2 + 1) & 1073741823) << 30) | ((-1152921503533105153L) & value))) {
                    atomicArray.get(i2 & i3).setValue(element);
                    while ((lockFreeTaskQueueCore._state.getValue() & 1152921504606846976L) != 0) {
                        lockFreeTaskQueueCore = lockFreeTaskQueueCore.next();
                        int i5 = lockFreeTaskQueueCore.mask & i2;
                        AtomicArray atomicArray2 = lockFreeTaskQueueCore.array;
                        Object value2 = atomicArray2.get(i5).getValue();
                        if ((value2 instanceof Placeholder) && ((Placeholder) value2).index == i2) {
                            atomicArray2.get(i5).setValue(element);
                            continue;
                        } else {
                            lockFreeTaskQueueCore = null;
                            continue;
                        }
                        if (lockFreeTaskQueueCore == null) {
                            return 0;
                        }
                    }
                    return 0;
                }
            }
        }
        return 1;
    }

    public final boolean close() {
        AtomicLong atomicLong;
        long value;
        do {
            atomicLong = this._state;
            value = atomicLong.getValue();
            if ((value & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & value) != 0) {
                return false;
            }
        } while (!atomicLong.compareAndSet(value, 2305843009213693952L | value));
        return true;
    }

    public final int getSize() {
        long value = this._state.getValue();
        return 1073741823 & (((int) ((value & 1152921503533105152L) >> 30)) - ((int) ((1073741823 & value) >> 0)));
    }

    public final boolean isEmpty() {
        long value = this._state.getValue();
        if (((int) ((1073741823 & value) >> 0)) != ((int) ((value & 1152921503533105152L) >> 30))) {
            return false;
        }
        return true;
    }

    public final LockFreeTaskQueueCore next() {
        long value;
        while (true) {
            AtomicLong atomicLong = this._state;
            value = atomicLong.getValue();
            if ((value & 1152921504606846976L) == 0) {
                long j = 1152921504606846976L | value;
                if (atomicLong.compareAndSet(value, j)) {
                    value = j;
                    break;
                }
            } else {
                break;
            }
        }
        while (true) {
            AtomicRef atomicRef = this._next;
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) atomicRef.getValue();
            if (lockFreeTaskQueueCore != null) {
                return lockFreeTaskQueueCore;
            }
            LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(this.capacity * 2, this.singleConsumer);
            int i = (int) ((1073741823 & value) >> 0);
            int i2 = (int) ((1152921503533105152L & value) >> 30);
            while (true) {
                int i3 = this.mask;
                int i4 = i & i3;
                if (i4 != (i3 & i2)) {
                    Object value2 = this.array.get(i4).getValue();
                    if (value2 == null) {
                        value2 = new Placeholder(i);
                    }
                    lockFreeTaskQueueCore2.array.get(lockFreeTaskQueueCore2.mask & i).setValue(value2);
                    i++;
                }
            }
            lockFreeTaskQueueCore2._state.setValue((-1152921504606846977L) & value);
            atomicRef.compareAndSet(null, lockFreeTaskQueueCore2);
        }
    }

    public final Object removeFirstOrNull() {
        int i;
        LockFreeTaskQueueCore lockFreeTaskQueueCore = this;
        while (true) {
            AtomicLong atomicLong = lockFreeTaskQueueCore._state;
            long value = atomicLong.getValue();
            if ((value & 1152921504606846976L) != 0) {
                return REMOVE_FROZEN;
            }
            long j = 1073741823;
            int i2 = lockFreeTaskQueueCore.mask;
            int i3 = ((int) ((1152921503533105152L & value) >> 30)) & i2;
            int i4 = i2 & ((int) ((value & 1073741823) >> 0));
            if (i3 == i4) {
                return null;
            }
            AtomicArray atomicArray = lockFreeTaskQueueCore.array;
            Object value2 = atomicArray.get(i4).getValue();
            boolean z = lockFreeTaskQueueCore.singleConsumer;
            if (value2 == null) {
                if (z) {
                    return null;
                }
            } else if (value2 instanceof Placeholder) {
                return null;
            } else {
                long j2 = (1073741823 & (i + 1)) << 0;
                if (atomicLong.compareAndSet(value, j2 | (value & (-1073741824)))) {
                    atomicArray.get(i4).setValue(null);
                    return value2;
                } else if (z) {
                    while (true) {
                        AtomicLong atomicLong2 = lockFreeTaskQueueCore._state;
                        long value3 = atomicLong2.getValue();
                        int i5 = (int) ((value3 & j) >> 0);
                        if ((value3 & 1152921504606846976L) != 0) {
                            lockFreeTaskQueueCore = lockFreeTaskQueueCore.next();
                        } else if (atomicLong2.compareAndSet(value3, j2 | (value3 & (-1073741824)))) {
                            lockFreeTaskQueueCore.array.get(lockFreeTaskQueueCore.mask & i5).setValue(null);
                            lockFreeTaskQueueCore = null;
                        } else {
                            continue;
                            j = 1073741823;
                        }
                        if (lockFreeTaskQueueCore == null) {
                            return value2;
                        }
                        j = 1073741823;
                    }
                }
            }
        }
    }
}
