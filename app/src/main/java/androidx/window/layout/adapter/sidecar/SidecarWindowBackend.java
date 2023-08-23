package androidx.window.layout.adapter.sidecar;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.util.Consumer;
import androidx.profileinstaller.ProfileInstaller$$ExternalSyntheticLambda0;
import androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.WindowBackend;
import androidx.window.layout.adapter.sidecar.ExtensionInterfaceCompat;
import androidx.window.layout.adapter.sidecar.SidecarCompat;
import androidx.window.layout.adapter.sidecar.SidecarWindowBackend;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SidecarWindowBackend implements WindowBackend {
    private static volatile SidecarWindowBackend globalInstance;
    private static final ReentrantLock globalLock = new ReentrantLock();
    private ExtensionInterfaceCompat windowExtension;
    private final CopyOnWriteArrayList windowLayoutChangeCallbacks = new CopyOnWriteArrayList();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ExtensionListenerImpl implements ExtensionInterfaceCompat.ExtensionCallbackInterface {
        public ExtensionListenerImpl() {
        }

        @Override // androidx.window.layout.adapter.sidecar.ExtensionInterfaceCompat.ExtensionCallbackInterface
        public final void onWindowLayoutChanged(Activity activity, WindowLayoutInfo windowLayoutInfo) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Iterator it = SidecarWindowBackend.this.getWindowLayoutChangeCallbacks().iterator();
            while (it.hasNext()) {
                WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper = (WindowLayoutChangeCallbackWrapper) it.next();
                if (Intrinsics.areEqual(windowLayoutChangeCallbackWrapper.getActivity(), activity)) {
                    windowLayoutChangeCallbackWrapper.accept(windowLayoutInfo);
                }
            }
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class WindowLayoutChangeCallbackWrapper {
        private final Activity activity;
        private final Consumer callback;
        private final Executor executor;
        private WindowLayoutInfo lastInfo;

        public static void $r8$lambda$1Fzggiz8q3lTHWyU2mIB89dvBzk(WindowLayoutChangeCallbackWrapper this$0, WindowLayoutInfo newLayoutInfo) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(newLayoutInfo, "$newLayoutInfo");
            this$0.callback.accept(newLayoutInfo);
        }

        public WindowLayoutChangeCallbackWrapper(Activity activity, ProfileInstaller$$ExternalSyntheticLambda0 profileInstaller$$ExternalSyntheticLambda0, WindowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0 windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0) {
            this.activity = activity;
            this.executor = profileInstaller$$ExternalSyntheticLambda0;
            this.callback = windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0;
        }

        public final void accept(final WindowLayoutInfo windowLayoutInfo) {
            this.lastInfo = windowLayoutInfo;
            this.executor.execute(new Runnable() { // from class: androidx.window.layout.adapter.sidecar.SidecarWindowBackend$WindowLayoutChangeCallbackWrapper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SidecarWindowBackend.WindowLayoutChangeCallbackWrapper.$r8$lambda$1Fzggiz8q3lTHWyU2mIB89dvBzk(SidecarWindowBackend.WindowLayoutChangeCallbackWrapper.this, windowLayoutInfo);
                }
            });
        }

        public final Activity getActivity() {
            return this.activity;
        }

        public final Consumer getCallback() {
            return this.callback;
        }

        public final WindowLayoutInfo getLastInfo() {
            return this.lastInfo;
        }
    }

    public SidecarWindowBackend(SidecarCompat sidecarCompat) {
        this.windowExtension = sidecarCompat;
        ExtensionInterfaceCompat extensionInterfaceCompat = this.windowExtension;
        if (extensionInterfaceCompat != null) {
            ((SidecarCompat) extensionInterfaceCompat).setExtensionCallback(new ExtensionListenerImpl());
        }
    }

    public final CopyOnWriteArrayList getWindowLayoutChangeCallbacks() {
        return this.windowLayoutChangeCallbacks;
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public final void registerLayoutChangeCallback(Context context, ProfileInstaller$$ExternalSyntheticLambda0 profileInstaller$$ExternalSyntheticLambda0, WindowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0 windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0) {
        Activity activity;
        boolean z;
        Object obj;
        WindowManager.LayoutParams attributes;
        Intrinsics.checkNotNullParameter(context, "context");
        Unit unit = null;
        r1 = null;
        IBinder iBinder = null;
        WindowLayoutInfo windowLayoutInfo = null;
        if (context instanceof Activity) {
            activity = (Activity) context;
        } else {
            activity = null;
        }
        if (activity != null) {
            ReentrantLock reentrantLock = globalLock;
            reentrantLock.lock();
            try {
                ExtensionInterfaceCompat extensionInterfaceCompat = this.windowExtension;
                if (extensionInterfaceCompat == null) {
                    windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0.accept(new WindowLayoutInfo(EmptyList.INSTANCE));
                    return;
                }
                CopyOnWriteArrayList copyOnWriteArrayList = this.windowLayoutChangeCallbacks;
                if (!(copyOnWriteArrayList instanceof Collection) || !copyOnWriteArrayList.isEmpty()) {
                    Iterator it = copyOnWriteArrayList.iterator();
                    while (it.hasNext()) {
                        if (Intrinsics.areEqual(((WindowLayoutChangeCallbackWrapper) it.next()).getActivity(), activity)) {
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
                WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper = new WindowLayoutChangeCallbackWrapper(activity, profileInstaller$$ExternalSyntheticLambda0, windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0);
                copyOnWriteArrayList.add(windowLayoutChangeCallbackWrapper);
                if (!z) {
                    SidecarCompat sidecarCompat = (SidecarCompat) extensionInterfaceCompat;
                    Window window = activity.getWindow();
                    if (window != null && (attributes = window.getAttributes()) != null) {
                        iBinder = attributes.token;
                    }
                    if (iBinder != null) {
                        sidecarCompat.register(iBinder, activity);
                    } else {
                        activity.getWindow().getDecorView().addOnAttachStateChangeListener(new SidecarCompat.FirstAttachAdapter(sidecarCompat, activity));
                    }
                } else {
                    Iterator it2 = copyOnWriteArrayList.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            obj = it2.next();
                            if (Intrinsics.areEqual(activity, ((WindowLayoutChangeCallbackWrapper) obj).getActivity())) {
                                break;
                            }
                        } else {
                            obj = null;
                            break;
                        }
                    }
                    WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper2 = (WindowLayoutChangeCallbackWrapper) obj;
                    if (windowLayoutChangeCallbackWrapper2 != null) {
                        windowLayoutInfo = windowLayoutChangeCallbackWrapper2.getLastInfo();
                    }
                    if (windowLayoutInfo != null) {
                        windowLayoutChangeCallbackWrapper.accept(windowLayoutInfo);
                    }
                }
                reentrantLock.unlock();
                unit = Unit.INSTANCE;
            } finally {
                reentrantLock.unlock();
            }
        }
        if (unit == null) {
            windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0.accept(new WindowLayoutInfo(EmptyList.INSTANCE));
        }
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public final void unregisterLayoutChangeCallback(Consumer callback) {
        boolean z;
        ExtensionInterfaceCompat extensionInterfaceCompat;
        Intrinsics.checkNotNullParameter(callback, "callback");
        synchronized (globalLock) {
            if (this.windowExtension == null) {
                return;
            }
            ArrayList<WindowLayoutChangeCallbackWrapper> arrayList = new ArrayList();
            Iterator it = this.windowLayoutChangeCallbacks.iterator();
            while (it.hasNext()) {
                WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper = (WindowLayoutChangeCallbackWrapper) it.next();
                if (windowLayoutChangeCallbackWrapper.getCallback() == callback) {
                    arrayList.add(windowLayoutChangeCallbackWrapper);
                }
            }
            this.windowLayoutChangeCallbacks.removeAll(arrayList);
            for (WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper2 : arrayList) {
                Activity activity = windowLayoutChangeCallbackWrapper2.getActivity();
                CopyOnWriteArrayList copyOnWriteArrayList = this.windowLayoutChangeCallbacks;
                if (!(copyOnWriteArrayList instanceof Collection) || !copyOnWriteArrayList.isEmpty()) {
                    Iterator it2 = copyOnWriteArrayList.iterator();
                    while (it2.hasNext()) {
                        if (Intrinsics.areEqual(((WindowLayoutChangeCallbackWrapper) it2.next()).getActivity(), activity)) {
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
                if (!z && (extensionInterfaceCompat = this.windowExtension) != null) {
                    ((SidecarCompat) extensionInterfaceCompat).onWindowLayoutChangeListenerRemoved(activity);
                }
            }
        }
    }
}
