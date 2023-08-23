package androidx.window.layout.adapter.sidecar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.util.Consumer;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.sidecar.ExtensionInterfaceCompat;
import androidx.window.layout.adapter.sidecar.SidecarWindowBackend;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarDisplayFeature;
import androidx.window.sidecar.SidecarInterface;
import androidx.window.sidecar.SidecarProvider;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SidecarCompat implements ExtensionInterfaceCompat {
    private final Map componentCallbackMap;
    private DistinctElementCallback extensionCallback;
    private final SidecarInterface sidecar;
    private final SidecarAdapter sidecarAdapter;
    private final Map windowListenerRegisteredContexts;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class DistinctElementCallback implements ExtensionInterfaceCompat.ExtensionCallbackInterface {
        private final ExtensionInterfaceCompat.ExtensionCallbackInterface callbackInterface;
        private final ReentrantLock lock = new ReentrantLock();
        private final WeakHashMap activityWindowLayoutInfo = new WeakHashMap();

        public DistinctElementCallback(SidecarWindowBackend.ExtensionListenerImpl extensionListenerImpl) {
            this.callbackInterface = extensionListenerImpl;
        }

        public final void clearWindowLayoutInfo(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                this.activityWindowLayoutInfo.put(activity, null);
            } finally {
                reentrantLock.unlock();
            }
        }

        @Override // androidx.window.layout.adapter.sidecar.ExtensionInterfaceCompat.ExtensionCallbackInterface
        public final void onWindowLayoutChanged(Activity activity, WindowLayoutInfo windowLayoutInfo) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            WeakHashMap weakHashMap = this.activityWindowLayoutInfo;
            try {
                if (Intrinsics.areEqual(windowLayoutInfo, (WindowLayoutInfo) weakHashMap.get(activity))) {
                    return;
                }
                WindowLayoutInfo windowLayoutInfo2 = (WindowLayoutInfo) weakHashMap.put(activity, windowLayoutInfo);
                reentrantLock.unlock();
                this.callbackInterface.onWindowLayoutChanged(activity, windowLayoutInfo);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class FirstAttachAdapter implements View.OnAttachStateChangeListener {
        private final WeakReference activityWeakReference;
        private final SidecarCompat sidecarCompat;

        public FirstAttachAdapter(SidecarCompat sidecarCompat, Activity activity) {
            this.sidecarCompat = sidecarCompat;
            this.activityWeakReference = new WeakReference(activity);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            IBinder iBinder;
            Window window;
            WindowManager.LayoutParams attributes;
            Intrinsics.checkNotNullParameter(view, "view");
            view.removeOnAttachStateChangeListener(this);
            Activity activity = (Activity) this.activityWeakReference.get();
            if (activity != null && (window = activity.getWindow()) != null && (attributes = window.getAttributes()) != null) {
                iBinder = attributes.token;
            } else {
                iBinder = null;
            }
            if (activity == null || iBinder == null) {
                return;
            }
            this.sidecarCompat.register(iBinder, activity);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class TranslatingCallback implements SidecarInterface.SidecarCallback {
        public TranslatingCallback() {
        }

        public void onDeviceStateChanged(SidecarDeviceState newDeviceState) {
            IBinder iBinder;
            SidecarInterface sidecar;
            Window window;
            WindowManager.LayoutParams attributes;
            Intrinsics.checkNotNullParameter(newDeviceState, "newDeviceState");
            Collection<Activity> values = ((LinkedHashMap) SidecarCompat.this.windowListenerRegisteredContexts).values();
            SidecarCompat sidecarCompat = SidecarCompat.this;
            for (Activity activity : values) {
                SidecarWindowLayoutInfo sidecarWindowLayoutInfo = null;
                if (activity != null && (window = activity.getWindow()) != null && (attributes = window.getAttributes()) != null) {
                    iBinder = attributes.token;
                } else {
                    iBinder = null;
                }
                if (iBinder != null && (sidecar = sidecarCompat.getSidecar()) != null) {
                    sidecarWindowLayoutInfo = sidecar.getWindowLayoutInfo(iBinder);
                }
                DistinctElementCallback distinctElementCallback = sidecarCompat.extensionCallback;
                if (distinctElementCallback != null) {
                    distinctElementCallback.onWindowLayoutChanged(activity, sidecarCompat.sidecarAdapter.translate(sidecarWindowLayoutInfo, newDeviceState));
                }
            }
        }

        public void onWindowLayoutChanged(IBinder windowToken, SidecarWindowLayoutInfo newLayout) {
            SidecarDeviceState sidecarDeviceState;
            Intrinsics.checkNotNullParameter(windowToken, "windowToken");
            Intrinsics.checkNotNullParameter(newLayout, "newLayout");
            Activity activity = (Activity) ((LinkedHashMap) SidecarCompat.this.windowListenerRegisteredContexts).get(windowToken);
            if (activity != null) {
                SidecarAdapter sidecarAdapter = SidecarCompat.this.sidecarAdapter;
                SidecarInterface sidecar = SidecarCompat.this.getSidecar();
                if (sidecar == null || (sidecarDeviceState = sidecar.getDeviceState()) == null) {
                    sidecarDeviceState = new SidecarDeviceState();
                }
                WindowLayoutInfo translate = sidecarAdapter.translate(newLayout, sidecarDeviceState);
                DistinctElementCallback distinctElementCallback = SidecarCompat.this.extensionCallback;
                if (distinctElementCallback != null) {
                    distinctElementCallback.onWindowLayoutChanged(activity, translate);
                    return;
                }
                return;
            }
            Log.w("SidecarCompat", "Unable to resolve activity from window token. Missing a call to #onWindowLayoutChangeListenerAdded()?");
        }
    }

    /* renamed from: $r8$lambda$wNkse0AIN2v-fCgvjk8xABKY5FM  reason: not valid java name */
    public static void m421$r8$lambda$wNkse0AIN2vfCgvjk8xABKY5FM(SidecarCompat this$0, Activity activity) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        DistinctElementCallback distinctElementCallback = this$0.extensionCallback;
        if (distinctElementCallback != null) {
            distinctElementCallback.onWindowLayoutChanged(activity, this$0.getWindowLayoutInfo(activity));
        }
    }

    public SidecarCompat(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SidecarInterface sidecarImpl = SidecarProvider.getSidecarImpl(context.getApplicationContext());
        SidecarAdapter sidecarAdapter = new SidecarAdapter();
        this.sidecar = sidecarImpl;
        this.sidecarAdapter = sidecarAdapter;
        this.windowListenerRegisteredContexts = new LinkedHashMap();
        this.componentCallbackMap = new LinkedHashMap();
    }

    public final SidecarInterface getSidecar() {
        return this.sidecar;
    }

    public final WindowLayoutInfo getWindowLayoutInfo(Activity activity) {
        IBinder iBinder;
        SidecarDeviceState sidecarDeviceState;
        WindowManager.LayoutParams attributes;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Window window = activity.getWindow();
        SidecarWindowLayoutInfo sidecarWindowLayoutInfo = null;
        if (window != null && (attributes = window.getAttributes()) != null) {
            iBinder = attributes.token;
        } else {
            iBinder = null;
        }
        if (iBinder == null) {
            return new WindowLayoutInfo(EmptyList.INSTANCE);
        }
        SidecarInterface sidecarInterface = this.sidecar;
        if (sidecarInterface != null) {
            sidecarWindowLayoutInfo = sidecarInterface.getWindowLayoutInfo(iBinder);
        }
        if (sidecarInterface == null || (sidecarDeviceState = sidecarInterface.getDeviceState()) == null) {
            sidecarDeviceState = new SidecarDeviceState();
        }
        return this.sidecarAdapter.translate(sidecarWindowLayoutInfo, sidecarDeviceState);
    }

    public final void onWindowLayoutChangeListenerRemoved(Activity activity) {
        IBinder iBinder;
        boolean z;
        WindowManager.LayoutParams attributes;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Window window = activity.getWindow();
        if (window != null && (attributes = window.getAttributes()) != null) {
            iBinder = attributes.token;
        } else {
            iBinder = null;
        }
        if (iBinder == null) {
            return;
        }
        SidecarInterface sidecarInterface = this.sidecar;
        if (sidecarInterface != null) {
            sidecarInterface.onWindowLayoutChangeListenerRemoved(iBinder);
        }
        Map map = this.componentCallbackMap;
        Consumer consumer = (Consumer) ((LinkedHashMap) map).get(activity);
        if (consumer != null) {
            if (activity instanceof OnConfigurationChangedProvider) {
                ((OnConfigurationChangedProvider) activity).removeOnConfigurationChangedListener(consumer);
            }
            map.remove(activity);
        }
        DistinctElementCallback distinctElementCallback = this.extensionCallback;
        if (distinctElementCallback != null) {
            distinctElementCallback.clearWindowLayoutInfo(activity);
        }
        Map map2 = this.windowListenerRegisteredContexts;
        if (map2.size() == 1) {
            z = true;
        } else {
            z = false;
        }
        map2.remove(iBinder);
        if (z && sidecarInterface != null) {
            sidecarInterface.onDeviceStateListenersChanged(true);
        }
    }

    public final void register(IBinder iBinder, final Activity activity) {
        Map map = this.windowListenerRegisteredContexts;
        map.put(iBinder, activity);
        SidecarInterface sidecarInterface = this.sidecar;
        if (sidecarInterface != null) {
            sidecarInterface.onWindowLayoutChangeListenerAdded(iBinder);
        }
        if (map.size() == 1 && sidecarInterface != null) {
            sidecarInterface.onDeviceStateListenersChanged(false);
        }
        DistinctElementCallback distinctElementCallback = this.extensionCallback;
        if (distinctElementCallback != null) {
            distinctElementCallback.onWindowLayoutChanged(activity, getWindowLayoutInfo(activity));
        }
        Map map2 = this.componentCallbackMap;
        if (((LinkedHashMap) map2).get(activity) == null && (activity instanceof OnConfigurationChangedProvider)) {
            Consumer consumer = new Consumer() { // from class: androidx.window.layout.adapter.sidecar.SidecarCompat$$ExternalSyntheticLambda0
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    Configuration configuration = (Configuration) obj;
                    SidecarCompat.m421$r8$lambda$wNkse0AIN2vfCgvjk8xABKY5FM(SidecarCompat.this, activity);
                }
            };
            map2.put(activity, consumer);
            ((OnConfigurationChangedProvider) activity).addOnConfigurationChangedListener(consumer);
        }
    }

    public final void setExtensionCallback(SidecarWindowBackend.ExtensionListenerImpl extensionListenerImpl) {
        this.extensionCallback = new DistinctElementCallback(extensionListenerImpl);
        SidecarInterface sidecarInterface = this.sidecar;
        if (sidecarInterface != null) {
            sidecarInterface.setSidecarCallback(new DistinctElementSidecarCallback(this.sidecarAdapter, new TranslatingCallback()));
        }
    }

    public final boolean validateExtensionInterface() {
        Method method;
        Class<?> cls;
        Method method2;
        Class<?> cls2;
        Method method3;
        Class<?> cls3;
        Method method4;
        SidecarInterface sidecarInterface = this.sidecar;
        Class<?> cls4 = null;
        if (sidecarInterface != null) {
            try {
                method = sidecarInterface.getClass().getMethod("setSidecarCallback", SidecarInterface.SidecarCallback.class);
            } catch (Throwable unused) {
                return false;
            }
        } else {
            method = null;
        }
        if (method != null) {
            cls = method.getReturnType();
        } else {
            cls = null;
        }
        if (Intrinsics.areEqual(cls, Void.TYPE)) {
            if (sidecarInterface != null) {
                sidecarInterface.getDeviceState();
            }
            if (sidecarInterface != null) {
                sidecarInterface.onDeviceStateListenersChanged(true);
            }
            if (sidecarInterface != null) {
                method2 = sidecarInterface.getClass().getMethod("getWindowLayoutInfo", IBinder.class);
            } else {
                method2 = null;
            }
            if (method2 != null) {
                cls2 = method2.getReturnType();
            } else {
                cls2 = null;
            }
            if (Intrinsics.areEqual(cls2, SidecarWindowLayoutInfo.class)) {
                if (sidecarInterface != null) {
                    method3 = sidecarInterface.getClass().getMethod("onWindowLayoutChangeListenerAdded", IBinder.class);
                } else {
                    method3 = null;
                }
                if (method3 != null) {
                    cls3 = method3.getReturnType();
                } else {
                    cls3 = null;
                }
                if (Intrinsics.areEqual(cls3, Void.TYPE)) {
                    if (sidecarInterface != null) {
                        method4 = sidecarInterface.getClass().getMethod("onWindowLayoutChangeListenerRemoved", IBinder.class);
                    } else {
                        method4 = null;
                    }
                    if (method4 != null) {
                        cls4 = method4.getReturnType();
                    }
                    if (Intrinsics.areEqual(cls4, Void.TYPE)) {
                        SidecarDeviceState sidecarDeviceState = new SidecarDeviceState();
                        try {
                            sidecarDeviceState.posture = 3;
                        } catch (NoSuchFieldError unused2) {
                            SidecarDeviceState.class.getMethod("setPosture", Integer.TYPE).invoke(sidecarDeviceState, 3);
                            Object invoke = SidecarDeviceState.class.getMethod("getPosture", new Class[0]).invoke(sidecarDeviceState, new Object[0]);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.Int");
                            if (((Integer) invoke).intValue() != 3) {
                                throw new Exception("Invalid device posture getter/setter");
                            }
                        }
                        SidecarDisplayFeature sidecarDisplayFeature = new SidecarDisplayFeature();
                        Rect rect = sidecarDisplayFeature.getRect();
                        Intrinsics.checkNotNullExpressionValue(rect, "displayFeature.rect");
                        sidecarDisplayFeature.setRect(rect);
                        sidecarDisplayFeature.getType();
                        sidecarDisplayFeature.setType(1);
                        new SidecarWindowLayoutInfo();
                        return true;
                    }
                    throw new NoSuchMethodException("Illegal return type for 'onWindowLayoutChangeListenerRemoved': " + cls4);
                }
                throw new NoSuchMethodException("Illegal return type for 'onWindowLayoutChangeListenerAdded': " + cls3);
            }
            throw new NoSuchMethodException("Illegal return type for 'getWindowLayoutInfo': " + cls2);
        }
        throw new NoSuchMethodException("Illegal return type for 'setSidecarCallback': " + cls);
    }
}
