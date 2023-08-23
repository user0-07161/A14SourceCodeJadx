package androidx.window.layout;

import android.app.Activity;
import androidx.window.SafeWindowExtensionsProvider;
import androidx.window.core.ConsumerAdapter;
import androidx.window.reflection.ReflectionUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SafeWindowLayoutComponentProvider {
    private final ConsumerAdapter consumerAdapter;
    private final ClassLoader loader;
    private final SafeWindowExtensionsProvider safeWindowExtensionsProvider;

    public SafeWindowLayoutComponentProvider(ClassLoader classLoader, ConsumerAdapter consumerAdapter) {
        this.loader = classLoader;
        this.consumerAdapter = consumerAdapter;
        this.safeWindowExtensionsProvider = new SafeWindowExtensionsProvider(classLoader);
    }

    public static final Class access$getFoldingFeatureClass(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) {
        Class<?> loadClass = safeWindowLayoutComponentProvider.loader.loadClass("androidx.window.extensions.layout.FoldingFeature");
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(FOLDING_FEATURE_CLASS)");
        return loadClass;
    }

    public static final Class access$getWindowLayoutComponentClass(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) {
        Class<?> loadClass = safeWindowLayoutComponentProvider.loader.loadClass("androidx.window.extensions.layout.WindowLayoutComponent");
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WINDOW_LAYOUT_COMPONENT_CLASS)");
        return loadClass;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0072, code lost:
        if (androidx.window.reflection.ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + android.content.Context.class.getName() + ", androidx.window.extensions.core.util.function.Consumer) is not valid", new androidx.window.layout.SafeWindowLayoutComponentProvider$isMethodWindowLayoutInfoListenerWindowConsumerValid$1(r4)) != false) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.window.extensions.layout.WindowLayoutComponent getWindowLayoutComponent() {
        /*
            r4 = this;
            androidx.window.SafeWindowExtensionsProvider r0 = r4.safeWindowExtensionsProvider
            boolean r0 = r0.isWindowExtensionsValid$window_release()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L26
            androidx.window.layout.SafeWindowLayoutComponentProvider$isWindowLayoutProviderValid$1 r0 = new androidx.window.layout.SafeWindowLayoutComponentProvider$isWindowLayoutProviderValid$1
            r0.<init>()
            java.lang.String r3 = "WindowExtensions#getWindowLayoutComponent is not valid"
            boolean r0 = androidx.window.reflection.ReflectionUtils.validateReflection$window_release(r3, r0)
            if (r0 == 0) goto L26
            androidx.window.layout.SafeWindowLayoutComponentProvider$isFoldingFeatureValid$1 r0 = new androidx.window.layout.SafeWindowLayoutComponentProvider$isFoldingFeatureValid$1
            r0.<init>()
            java.lang.String r3 = "FoldingFeature class is not valid"
            boolean r0 = androidx.window.reflection.ReflectionUtils.validateReflection$window_release(r3, r0)
            if (r0 == 0) goto L26
            r0 = r1
            goto L27
        L26:
            r0 = r2
        L27:
            if (r0 != 0) goto L2a
            goto L77
        L2a:
            int r0 = androidx.window.core.ExtensionsUtil.$r8$clinit
            androidx.window.extensions.WindowExtensions r0 = androidx.window.extensions.WindowExtensionsProvider.getWindowExtensions()     // Catch: java.lang.Throwable -> L35
            int r0 = r0.getVendorApiLevel()     // Catch: java.lang.Throwable -> L35
            goto L36
        L35:
            r0 = r2
        L36:
            if (r0 != r1) goto L3d
            boolean r2 = r4.hasValidVendorApiLevel1$window_release()
            goto L77
        L3d:
            r3 = 2
            if (r3 > r0) goto L47
            r3 = 2147483647(0x7fffffff, float:NaN)
            if (r0 > r3) goto L47
            r0 = r1
            goto L48
        L47:
            r0 = r2
        L48:
            if (r0 == 0) goto L77
            boolean r0 = r4.hasValidVendorApiLevel1$window_release()
            if (r0 == 0) goto L75
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "WindowLayoutComponent#addWindowLayoutInfoListener("
            r0.<init>(r3)
            java.lang.Class<android.content.Context> r3 = android.content.Context.class
            java.lang.String r3 = r3.getName()
            r0.append(r3)
            java.lang.String r3 = ", androidx.window.extensions.core.util.function.Consumer) is not valid"
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.window.layout.SafeWindowLayoutComponentProvider$isMethodWindowLayoutInfoListenerWindowConsumerValid$1 r3 = new androidx.window.layout.SafeWindowLayoutComponentProvider$isMethodWindowLayoutInfoListenerWindowConsumerValid$1
            r3.<init>()
            boolean r4 = androidx.window.reflection.ReflectionUtils.validateReflection$window_release(r0, r3)
            if (r4 == 0) goto L75
            goto L76
        L75:
            r1 = r2
        L76:
            r2 = r1
        L77:
            if (r2 == 0) goto L82
            androidx.window.extensions.WindowExtensions r4 = androidx.window.extensions.WindowExtensionsProvider.getWindowExtensions()     // Catch: java.lang.UnsupportedOperationException -> L82
            androidx.window.extensions.layout.WindowLayoutComponent r4 = r4.getWindowLayoutComponent()     // Catch: java.lang.UnsupportedOperationException -> L82
            goto L83
        L82:
            r4 = 0
        L83:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.SafeWindowLayoutComponentProvider.getWindowLayoutComponent():androidx.window.extensions.layout.WindowLayoutComponent");
    }

    public final boolean hasValidVendorApiLevel1$window_release() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Activity.class.getName() + ", java.util.function.Consumer) is not valid", new Function0() { // from class: androidx.window.layout.SafeWindowLayoutComponentProvider$isMethodWindowLayoutInfoListenerJavaConsumerValid$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ConsumerAdapter consumerAdapter;
                boolean z;
                consumerAdapter = SafeWindowLayoutComponentProvider.this.consumerAdapter;
                Class consumerClassOrNull$window_release = consumerAdapter.consumerClassOrNull$window_release();
                if (consumerClassOrNull$window_release == null) {
                    return Boolean.FALSE;
                }
                Class access$getWindowLayoutComponentClass = SafeWindowLayoutComponentProvider.access$getWindowLayoutComponentClass(SafeWindowLayoutComponentProvider.this);
                Method addListenerMethod = access$getWindowLayoutComponentClass.getMethod("addWindowLayoutInfoListener", Activity.class, consumerClassOrNull$window_release);
                Method removeListenerMethod = access$getWindowLayoutComponentClass.getMethod("removeWindowLayoutInfoListener", consumerClassOrNull$window_release);
                Intrinsics.checkNotNullExpressionValue(addListenerMethod, "addListenerMethod");
                if (Modifier.isPublic(addListenerMethod.getModifiers())) {
                    Intrinsics.checkNotNullExpressionValue(removeListenerMethod, "removeListenerMethod");
                    if (Modifier.isPublic(removeListenerMethod.getModifiers())) {
                        z = true;
                        return Boolean.valueOf(z);
                    }
                }
                z = false;
                return Boolean.valueOf(z);
            }
        });
    }
}
