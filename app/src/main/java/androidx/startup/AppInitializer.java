package androidx.startup;

import android.content.Context;
import android.os.Bundle;
import android.os.Trace;
import androidx.lifecycle.ProcessLifecycleInitializer;
import com.android.egg.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AppInitializer {
    private static volatile AppInitializer sInstance;
    private static final Object sLock = new Object();
    final Context mContext;
    final Set mDiscovered = new HashSet();
    final Map mInitialized = new HashMap();

    AppInitializer(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private Object doInitialize(Class cls, Set set) {
        Object obj;
        if (Trace.isEnabled()) {
            try {
                Trace.beginSection(cls.getSimpleName());
            } finally {
                Trace.endSection();
            }
        }
        HashSet hashSet = (HashSet) set;
        if (!hashSet.contains(cls)) {
            Map map = this.mInitialized;
            if (!((HashMap) map).containsKey(cls)) {
                hashSet.add(cls);
                Initializer initializer = (Initializer) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                List<Class> dependencies = initializer.dependencies();
                if (!dependencies.isEmpty()) {
                    for (Class cls2 : dependencies) {
                        if (!((HashMap) map).containsKey(cls2)) {
                            doInitialize(cls2, set);
                        }
                    }
                }
                obj = initializer.create(this.mContext);
                hashSet.remove(cls);
                ((HashMap) map).put(cls, obj);
            } else {
                obj = ((HashMap) map).get(cls);
            }
            return obj;
        }
        throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", cls.getName()));
    }

    public static AppInitializer getInstance(Context context) {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new AppInitializer(context);
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void discoverAndInitialize(Bundle bundle) {
        Set set;
        String string = this.mContext.getString(R.string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet = new HashSet();
                Iterator<String> it = bundle.keySet().iterator();
                while (true) {
                    boolean hasNext = it.hasNext();
                    set = this.mDiscovered;
                    if (!hasNext) {
                        break;
                    }
                    String next = it.next();
                    if (string.equals(bundle.getString(next, null))) {
                        Class<?> cls = Class.forName(next);
                        if (Initializer.class.isAssignableFrom(cls)) {
                            ((HashSet) set).add(cls);
                        }
                    }
                }
                Iterator it2 = ((HashSet) set).iterator();
                while (it2.hasNext()) {
                    doInitialize((Class) it2.next(), hashSet);
                }
            } catch (ClassNotFoundException e) {
                throw new StartupException(e);
            }
        }
    }

    public final Object initializeComponent() {
        Object obj;
        synchronized (sLock) {
            obj = ((HashMap) this.mInitialized).get(ProcessLifecycleInitializer.class);
            if (obj == null) {
                obj = doInitialize(ProcessLifecycleInitializer.class, new HashSet());
            }
        }
        return obj;
    }

    public final boolean isEagerlyInitialized() {
        return ((HashSet) this.mDiscovered).contains(ProcessLifecycleInitializer.class);
    }
}
