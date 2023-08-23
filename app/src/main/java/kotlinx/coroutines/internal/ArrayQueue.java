package kotlinx.coroutines.internal;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ArrayQueue {
    private Object[] elements = new Object[16];
    private int head;
    private int tail;

    public final void addLast(Object element) {
        Intrinsics.checkNotNullParameter(element, "element");
        Object[] objArr = this.elements;
        int i = this.tail;
        objArr[i] = element;
        int length = (objArr.length - 1) & (i + 1);
        this.tail = length;
        int i2 = this.head;
        if (length == i2) {
            int length2 = objArr.length;
            Object[] objArr2 = new Object[length2 << 1];
            ArraysKt.copyInto$default(objArr, objArr2, 0, i2, 0, 10);
            Object[] objArr3 = this.elements;
            int length3 = objArr3.length;
            int i3 = this.head;
            ArraysKt.copyInto$default(objArr3, objArr2, length3 - i3, 0, i3, 4);
            this.elements = objArr2;
            this.head = 0;
            this.tail = length2;
        }
    }

    public final boolean isEmpty() {
        if (this.head == this.tail) {
            return true;
        }
        return false;
    }

    public final Object removeFirstOrNull() {
        int i = this.head;
        if (i == this.tail) {
            return null;
        }
        Object[] objArr = this.elements;
        Object obj = objArr[i];
        objArr[i] = null;
        this.head = (i + 1) & (objArr.length - 1);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of kotlinx.coroutines.internal.ArrayQueue");
        return obj;
    }
}
