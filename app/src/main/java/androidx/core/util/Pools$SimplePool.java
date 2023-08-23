package androidx.core.util;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Pools$SimplePool {
    private final Object[] mPool;
    private int mPoolSize;

    public Pools$SimplePool(int i) {
        if (i > 0) {
            this.mPool = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    public final Object acquire() {
        int i = this.mPoolSize;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.mPool;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.mPoolSize = i - 1;
        return obj;
    }

    public final void release(Object obj) {
        int i;
        Object[] objArr;
        boolean z = false;
        int i2 = 0;
        while (true) {
            i = this.mPoolSize;
            objArr = this.mPool;
            if (i2 >= i) {
                break;
            } else if (objArr[i2] == obj) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (!z) {
            if (i < objArr.length) {
                objArr[i] = obj;
                this.mPoolSize = i + 1;
                return;
            }
            return;
        }
        throw new IllegalStateException("Already in the pool!");
    }
}
