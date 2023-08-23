package androidx.window.layout.adapter.sidecar;

import android.os.IBinder;
import androidx.window.layout.adapter.sidecar.SidecarCompat;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarInterface;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class DistinctElementSidecarCallback implements SidecarInterface.SidecarCallback {
    private final SidecarAdapter mAdapter;
    private final SidecarInterface.SidecarCallback mCallback;
    private SidecarDeviceState mLastDeviceState;
    private final Object mLock = new Object();
    private final Map mActivityWindowLayoutInfo = new WeakHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public DistinctElementSidecarCallback(SidecarAdapter sidecarAdapter, SidecarCompat.TranslatingCallback translatingCallback) {
        this.mAdapter = sidecarAdapter;
        this.mCallback = translatingCallback;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001f, code lost:
        if (androidx.window.layout.adapter.sidecar.SidecarAdapter.Companion.getSidecarDevicePosture$window_release(r2) == androidx.window.layout.adapter.sidecar.SidecarAdapter.Companion.getSidecarDevicePosture$window_release(r4)) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0026 A[Catch: all -> 0x0031, DONT_GENERATE, TryCatch #0 {, blocks: (B:6:0x0006, B:16:0x0026, B:18:0x0028, B:19:0x002f, B:11:0x0017), top: B:24:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0028 A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:6:0x0006, B:16:0x0026, B:18:0x0028, B:19:0x002f, B:11:0x0017), top: B:24:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onDeviceStateChanged(androidx.window.sidecar.SidecarDeviceState r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L3
            return
        L3:
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            androidx.window.layout.adapter.sidecar.SidecarAdapter r1 = r3.mAdapter     // Catch: java.lang.Throwable -> L31
            androidx.window.sidecar.SidecarDeviceState r2 = r3.mLastDeviceState     // Catch: java.lang.Throwable -> L31
            r1.getClass()     // Catch: java.lang.Throwable -> L31
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)     // Catch: java.lang.Throwable -> L31
            if (r1 == 0) goto L14
            goto L21
        L14:
            if (r2 != 0) goto L17
            goto L23
        L17:
            int r1 = androidx.window.layout.adapter.sidecar.SidecarAdapter.Companion.getSidecarDevicePosture$window_release(r2)     // Catch: java.lang.Throwable -> L31
            int r2 = androidx.window.layout.adapter.sidecar.SidecarAdapter.Companion.getSidecarDevicePosture$window_release(r4)     // Catch: java.lang.Throwable -> L31
            if (r1 != r2) goto L23
        L21:
            r1 = 1
            goto L24
        L23:
            r1 = 0
        L24:
            if (r1 == 0) goto L28
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L31
            return
        L28:
            r3.mLastDeviceState = r4     // Catch: java.lang.Throwable -> L31
            androidx.window.sidecar.SidecarInterface$SidecarCallback r3 = r3.mCallback     // Catch: java.lang.Throwable -> L31
            r3.onDeviceStateChanged(r4)     // Catch: java.lang.Throwable -> L31
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L31
            return
        L31:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L31
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.DistinctElementSidecarCallback.onDeviceStateChanged(androidx.window.sidecar.SidecarDeviceState):void");
    }

    public void onWindowLayoutChanged(IBinder iBinder, SidecarWindowLayoutInfo sidecarWindowLayoutInfo) {
        synchronized (this.mLock) {
            this.mAdapter.getClass();
            if (SidecarAdapter.isEqualSidecarWindowLayoutInfo((SidecarWindowLayoutInfo) ((WeakHashMap) this.mActivityWindowLayoutInfo).get(iBinder), sidecarWindowLayoutInfo)) {
                return;
            }
            ((WeakHashMap) this.mActivityWindowLayoutInfo).put(iBinder, sidecarWindowLayoutInfo);
            this.mCallback.onWindowLayoutChanged(iBinder, sidecarWindowLayoutInfo);
        }
    }
}
