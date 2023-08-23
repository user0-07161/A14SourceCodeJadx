package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.internal.SystemPropsKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class DefaultExecutorKt {
    private static final Delay DefaultDelay;

    static {
        boolean z;
        Delay delay;
        String systemProp = SystemPropsKt.systemProp("kotlinx.coroutines.main.delay");
        if (systemProp != null) {
            z = Boolean.parseBoolean(systemProp);
        } else {
            z = false;
        }
        if (!z) {
            delay = DefaultExecutor.INSTANCE;
        } else {
            int i = Dispatchers.$r8$clinit;
            MainCoroutineDispatcher mainCoroutineDispatcher = MainDispatcherLoader.dispatcher;
            Intrinsics.checkNotNullParameter(mainCoroutineDispatcher, "<this>");
            if (!(mainCoroutineDispatcher instanceof Delay)) {
                delay = DefaultExecutor.INSTANCE;
            } else {
                delay = (Delay) mainCoroutineDispatcher;
            }
        }
        DefaultDelay = delay;
    }

    public static final Delay getDefaultDelay() {
        return DefaultDelay;
    }
}
