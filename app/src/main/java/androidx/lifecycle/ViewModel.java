package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ViewModel {
    private final Map mBagOfTags = new HashMap();
    private final Set mCloseables = new LinkedHashSet();
    private volatile boolean mCleared = false;

    private static void closeWithRuntimeException(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void clear() {
        this.mCleared = true;
        Map map = this.mBagOfTags;
        if (map != null) {
            synchronized (map) {
                for (Object obj : ((HashMap) this.mBagOfTags).values()) {
                    closeWithRuntimeException(obj);
                }
            }
        }
        Set set = this.mCloseables;
        if (set != null) {
            synchronized (set) {
                for (Closeable closeable : this.mCloseables) {
                    closeWithRuntimeException(closeable);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object getTag() {
        Object obj;
        Map map = this.mBagOfTags;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            obj = ((HashMap) this.mBagOfTags).get("androidx.lifecycle.savedstate.vm.tag");
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setTagIfAbsent(Object obj) {
        Object obj2;
        synchronized (this.mBagOfTags) {
            obj2 = ((HashMap) this.mBagOfTags).get("androidx.lifecycle.savedstate.vm.tag");
            if (obj2 == null) {
                ((HashMap) this.mBagOfTags).put("androidx.lifecycle.savedstate.vm.tag", obj);
            }
        }
        if (obj2 != null) {
            obj = obj2;
        }
        if (this.mCleared) {
            closeWithRuntimeException(obj);
        }
    }
}
