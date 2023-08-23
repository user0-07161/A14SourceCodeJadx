package androidx.window;

import androidx.window.reflection.ReflectionUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SafeWindowExtensionsProvider {
    private final ClassLoader loader;

    public SafeWindowExtensionsProvider(ClassLoader classLoader) {
        this.loader = classLoader;
    }

    public static final Class access$getWindowExtensionsProviderClass(SafeWindowExtensionsProvider safeWindowExtensionsProvider) {
        Class<?> loadClass = safeWindowExtensionsProvider.loader.loadClass("androidx.window.extensions.WindowExtensionsProvider");
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WindowE…XTENSIONS_PROVIDER_CLASS)");
        return loadClass;
    }

    public final Class getWindowExtensionsClass$window_release() {
        Class<?> loadClass = this.loader.loadClass("androidx.window.extensions.WindowExtensions");
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WindowE….WINDOW_EXTENSIONS_CLASS)");
        return loadClass;
    }

    public final boolean isWindowExtensionsValid$window_release() {
        boolean z;
        try {
            new Function0() { // from class: androidx.window.SafeWindowExtensionsProvider$isWindowExtensionsPresent$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ClassLoader classLoader;
                    classLoader = SafeWindowExtensionsProvider.this.loader;
                    Class<?> loadClass = classLoader.loadClass("androidx.window.extensions.WindowExtensionsProvider");
                    Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WindowE…XTENSIONS_PROVIDER_CLASS)");
                    return loadClass;
                }
            }.invoke();
            z = true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            z = false;
        }
        if (!z || !ReflectionUtils.validateReflection$window_release("WindowExtensionsProvider#getWindowExtensions is not valid", new Function0() { // from class: androidx.window.SafeWindowExtensionsProvider$isWindowExtensionsValid$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z2 = false;
                Method getWindowExtensionsMethod = SafeWindowExtensionsProvider.access$getWindowExtensionsProviderClass(SafeWindowExtensionsProvider.this).getDeclaredMethod("getWindowExtensions", new Class[0]);
                Class windowExtensionsClass$window_release = SafeWindowExtensionsProvider.this.getWindowExtensionsClass$window_release();
                Intrinsics.checkNotNullExpressionValue(getWindowExtensionsMethod, "getWindowExtensionsMethod");
                if (getWindowExtensionsMethod.getReturnType().equals(windowExtensionsClass$window_release) && Modifier.isPublic(getWindowExtensionsMethod.getModifiers())) {
                    z2 = true;
                }
                return Boolean.valueOf(z2);
            }
        })) {
            return false;
        }
        return true;
    }
}
