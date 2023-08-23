package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CreationExtras {
    private final Map map = new LinkedHashMap();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Empty extends CreationExtras {
        public static final Empty INSTANCE = new Empty();
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Key {
    }

    public final Map getMap$lifecycle_viewmodel_release() {
        return this.map;
    }
}
