package androidx.window.layout;

import androidx.window.core.ConsumerAdapter;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface WindowInfoTracker {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static WindowInfoTrackerDecorator decorator;
        private static final Lazy extensionBackend$delegate;

        static {
            Reflection.getOrCreateKotlinClass(WindowInfoTracker.class).getSimpleName();
            extensionBackend$delegate = LazyKt.lazy$1(new Function0() { // from class: androidx.window.layout.WindowInfoTracker$Companion$extensionBackend$2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider;
                    WindowLayoutComponent windowLayoutComponent;
                    try {
                        ClassLoader loader = WindowInfoTracker.class.getClassLoader();
                        if (loader != null) {
                            safeWindowLayoutComponentProvider = new SafeWindowLayoutComponentProvider(loader, new ConsumerAdapter(loader));
                        } else {
                            safeWindowLayoutComponentProvider = null;
                        }
                        if (safeWindowLayoutComponentProvider == null || (windowLayoutComponent = safeWindowLayoutComponentProvider.getWindowLayoutComponent()) == null) {
                            return null;
                        }
                        Intrinsics.checkNotNullExpressionValue(loader, "loader");
                        return new ExtensionWindowLayoutInfoBackend(windowLayoutComponent, new ConsumerAdapter(loader));
                    } catch (Throwable unused) {
                        WindowInfoTracker.Companion companion = WindowInfoTracker.Companion.$$INSTANCE;
                        return null;
                    }
                }
            });
            decorator = EmptyDecorator.INSTANCE;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0038 A[Catch: all -> 0x0054, TRY_ENTER, TryCatch #2 {all -> 0x0054, blocks: (B:10:0x0023, B:12:0x002d, B:17:0x0038, B:22:0x0047), top: B:38:0x0023 }] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0047 A[Catch: all -> 0x0054, TRY_LEAVE, TryCatch #2 {all -> 0x0054, blocks: (B:10:0x0023, B:12:0x002d, B:17:0x0038, B:22:0x0047), top: B:38:0x0023 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static androidx.window.layout.WindowInfoTrackerImpl getOrCreate(android.content.Context r4) {
            /*
                java.lang.String r0 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                kotlin.Lazy r0 = androidx.window.layout.WindowInfoTracker.Companion.extensionBackend$delegate
                java.lang.Object r0 = r0.getValue()
                androidx.window.layout.adapter.WindowBackend r0 = (androidx.window.layout.adapter.WindowBackend) r0
                if (r0 != 0) goto L6c
                androidx.window.layout.adapter.sidecar.SidecarWindowBackend r0 = androidx.window.layout.adapter.sidecar.SidecarWindowBackend.access$getGlobalInstance$cp()
                if (r0 != 0) goto L65
                java.util.concurrent.locks.ReentrantLock r0 = androidx.window.layout.adapter.sidecar.SidecarWindowBackend.access$getGlobalLock$cp()
                r0.lock()
                androidx.window.layout.adapter.sidecar.SidecarWindowBackend r1 = androidx.window.layout.adapter.sidecar.SidecarWindowBackend.access$getGlobalInstance$cp()     // Catch: java.lang.Throwable -> L60
                if (r1 != 0) goto L5c
                r1 = 0
                java.lang.String r2 = androidx.window.sidecar.SidecarProvider.getApiVersion()     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L54
                boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L54
                if (r3 != 0) goto L34
                int r3 = androidx.window.core.Version.$r8$clinit     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L54
                androidx.window.core.Version r2 = androidx.window.core.Version.Companion.parse(r2)     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L54
                goto L35
            L34:
                r2 = r1
            L35:
                if (r2 != 0) goto L38
                goto L44
            L38:
                androidx.window.core.Version r3 = androidx.window.core.Version.access$getVERSION_0_1$cp()     // Catch: java.lang.Throwable -> L54
                int r2 = r2.compareTo(r3)     // Catch: java.lang.Throwable -> L54
                if (r2 < 0) goto L44
                r2 = 1
                goto L45
            L44:
                r2 = 0
            L45:
                if (r2 == 0) goto L54
                androidx.window.layout.adapter.sidecar.SidecarCompat r2 = new androidx.window.layout.adapter.sidecar.SidecarCompat     // Catch: java.lang.Throwable -> L54
                r2.<init>(r4)     // Catch: java.lang.Throwable -> L54
                boolean r4 = r2.validateExtensionInterface()     // Catch: java.lang.Throwable -> L54
                if (r4 != 0) goto L53
                goto L54
            L53:
                r1 = r2
            L54:
                androidx.window.layout.adapter.sidecar.SidecarWindowBackend r4 = new androidx.window.layout.adapter.sidecar.SidecarWindowBackend     // Catch: java.lang.Throwable -> L60
                r4.<init>(r1)     // Catch: java.lang.Throwable -> L60
                androidx.window.layout.adapter.sidecar.SidecarWindowBackend.access$setGlobalInstance$cp(r4)     // Catch: java.lang.Throwable -> L60
            L5c:
                r0.unlock()
                goto L65
            L60:
                r4 = move-exception
                r0.unlock()
                throw r4
            L65:
                androidx.window.layout.adapter.sidecar.SidecarWindowBackend r0 = androidx.window.layout.adapter.sidecar.SidecarWindowBackend.access$getGlobalInstance$cp()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            L6c:
                androidx.window.layout.WindowInfoTrackerImpl r4 = new androidx.window.layout.WindowInfoTrackerImpl
                androidx.window.layout.WindowMetricsCalculatorCompat r1 = androidx.window.layout.WindowMetricsCalculatorCompat.INSTANCE
                r4.<init>(r0)
                androidx.window.layout.WindowInfoTrackerDecorator r0 = androidx.window.layout.WindowInfoTracker.Companion.decorator
                androidx.window.layout.EmptyDecorator r0 = (androidx.window.layout.EmptyDecorator) r0
                r0.getClass()
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.WindowInfoTracker.Companion.getOrCreate(android.content.Context):androidx.window.layout.WindowInfoTrackerImpl");
        }
    }
}
