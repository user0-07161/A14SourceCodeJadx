package androidx.lifecycle;

import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Lifecycling {
    private static Map sCallbackCache = new HashMap();
    private static Map sClassToAdapters = new HashMap();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* renamed from: androidx.lifecycle.Lifecycling$1  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements LifecycleEventObserver {
        @Override // androidx.lifecycle.LifecycleEventObserver
        public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            throw null;
        }
    }

    private static void createGeneratedAdapter(Constructor constructor, Object obj) {
        try {
            OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(constructor.newInstance(obj));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static String getAdapterName(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }

    private static int getObserverConstructorType(Class cls) {
        Constructor<?> constructor;
        boolean z;
        boolean z2;
        String str;
        Integer num = (Integer) ((HashMap) sCallbackCache).get(cls);
        if (num != null) {
            return num.intValue();
        }
        int i = 1;
        if (cls.getCanonicalName() != null) {
            ArrayList arrayList = null;
            try {
                Package r2 = cls.getPackage();
                String canonicalName = cls.getCanonicalName();
                if (r2 != null) {
                    str = r2.getName();
                } else {
                    str = "";
                }
                if (!str.isEmpty()) {
                    canonicalName = canonicalName.substring(str.length() + 1);
                }
                String adapterName = getAdapterName(canonicalName);
                if (!str.isEmpty()) {
                    adapterName = str + "." + adapterName;
                }
                constructor = Class.forName(adapterName).getDeclaredConstructor(cls);
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
            } catch (ClassNotFoundException unused) {
                constructor = null;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            if (constructor != null) {
                ((HashMap) sClassToAdapters).put(cls, Collections.singletonList(constructor));
            } else if (!ClassesInfoCache.sInstance.hasLifecycleMethods(cls)) {
                Class superclass = cls.getSuperclass();
                if (superclass != null && LifecycleObserver.class.isAssignableFrom(superclass)) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    if (getObserverConstructorType(superclass) != 1) {
                        arrayList = new ArrayList((Collection) ((HashMap) sClassToAdapters).get(superclass));
                    }
                }
                Class<?>[] interfaces = cls.getInterfaces();
                int length = interfaces.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        Class<?> cls2 = interfaces[i2];
                        if (cls2 != null && LifecycleObserver.class.isAssignableFrom(cls2)) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            if (getObserverConstructorType(cls2) == 1) {
                                break;
                            }
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.addAll((Collection) ((HashMap) sClassToAdapters).get(cls2));
                        }
                        i2++;
                    } else if (arrayList != null) {
                        ((HashMap) sClassToAdapters).put(cls, arrayList);
                    }
                }
            }
            i = 2;
        }
        ((HashMap) sCallbackCache).put(cls, Integer.valueOf(i));
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LifecycleEventObserver lifecycleEventObserver(Object obj) {
        boolean z = obj instanceof LifecycleEventObserver;
        boolean z2 = obj instanceof DefaultLifecycleObserver;
        if (z && z2) {
            return new FullLifecycleObserverAdapter((DefaultLifecycleObserver) obj, (LifecycleEventObserver) obj);
        }
        if (z2) {
            return new FullLifecycleObserverAdapter((DefaultLifecycleObserver) obj, null);
        }
        if (z) {
            return (LifecycleEventObserver) obj;
        }
        Class<?> cls = obj.getClass();
        if (getObserverConstructorType(cls) == 2) {
            List list = (List) ((HashMap) sClassToAdapters).get(cls);
            if (list.size() == 1) {
                createGeneratedAdapter((Constructor) list.get(0), obj);
                return new SingleGeneratedAdapterObserver();
            }
            GeneratedAdapter[] generatedAdapterArr = new GeneratedAdapter[list.size()];
            for (int i = 0; i < list.size(); i++) {
                createGeneratedAdapter((Constructor) list.get(i), obj);
                generatedAdapterArr[i] = null;
            }
            return new CompositeGeneratedAdaptersObserver(generatedAdapterArr);
        }
        return new ReflectiveGenericLifecycleObserver(obj);
    }
}
