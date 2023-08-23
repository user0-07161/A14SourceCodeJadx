package androidx.compose.runtime;

import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SlotTableKt {
    public static final int access$auxIndex(int i, int[] iArr) {
        int i2 = i * 5;
        if (i2 >= iArr.length) {
            return iArr.length;
        }
        return countOneBits(iArr[i2 + 1] >> 29) + iArr[i2 + 4];
    }

    public static final boolean access$containsMark(int i, int[] iArr) {
        if ((iArr[(i * 5) + 1] & 67108864) != 0) {
            return true;
        }
        return false;
    }

    public static final int access$groupSize(int i, int[] iArr) {
        return iArr[(i * 5) + 3];
    }

    public static final boolean access$hasAux(int i, int[] iArr) {
        if ((iArr[(i * 5) + 1] & 268435456) != 0) {
            return true;
        }
        return false;
    }

    public static final boolean access$isNode(int i, int[] iArr) {
        if ((iArr[(i * 5) + 1] & 1073741824) != 0) {
            return true;
        }
        return false;
    }

    public static final int access$locationOf(ArrayList arrayList, int i, int i2) {
        int search = search(arrayList, i, i2);
        if (search < 0) {
            return -(search + 1);
        }
        return search;
    }

    public static final int access$nodeCount(int i, int[] iArr) {
        return iArr[(i * 5) + 1] & 67108863;
    }

    public static final int access$objectKeyIndex(int i, int[] iArr) {
        int i2 = i * 5;
        return iArr[i2 + 4] + countOneBits(iArr[i2 + 1] >> 30);
    }

    public static final /* synthetic */ int access$search(ArrayList arrayList, int i, int i2) {
        return search(arrayList, i, i2);
    }

    public static final int access$slotAnchor(int i, int[] iArr) {
        int i2 = i * 5;
        return iArr[i2 + 4] + countOneBits(iArr[i2 + 1] >> 28);
    }

    public static final void access$updateGroupSize(int i, int i2, int[] iArr) {
        boolean z;
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        ComposerKt.runtimeCheck(z);
        iArr[(i * 5) + 3] = i2;
    }

    public static final void access$updateNodeCount(int i, int i2, int[] iArr) {
        boolean z;
        if (i2 >= 0 && i2 < 67108863) {
            z = true;
        } else {
            z = false;
        }
        ComposerKt.runtimeCheck(z);
        int i3 = (i * 5) + 1;
        iArr[i3] = i2 | (iArr[i3] & (-67108864));
    }

    public static final int countOneBits(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
            case 2:
            case 4:
                return 1;
            case 3:
            case 5:
            case 6:
                return 2;
            default:
                return 3;
        }
    }

    public static final int search(ArrayList arrayList, int i, int i2) {
        int size = arrayList.size() - 1;
        int i3 = 0;
        while (i3 <= size) {
            int i4 = (i3 + size) >>> 1;
            int location$runtime_release = ((Anchor) arrayList.get(i4)).getLocation$runtime_release();
            if (location$runtime_release < 0) {
                location$runtime_release += i2;
            }
            int compare = Intrinsics.compare(location$runtime_release, i);
            if (compare < 0) {
                i3 = i4 + 1;
            } else if (compare > 0) {
                size = i4 - 1;
            } else {
                return i4;
            }
        }
        return -(i3 + 1);
    }
}
