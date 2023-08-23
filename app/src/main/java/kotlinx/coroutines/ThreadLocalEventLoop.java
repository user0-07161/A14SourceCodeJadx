package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ThreadLocalEventLoop {
    private static final ThreadLocal ref = new ThreadLocal();

    public static EventLoopImplPlatform currentOrNull$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return (EventLoopImplPlatform) ref.get();
    }

    public static EventLoopImplPlatform getEventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        ThreadLocal threadLocal = ref;
        EventLoopImplPlatform eventLoopImplPlatform = (EventLoopImplPlatform) threadLocal.get();
        if (eventLoopImplPlatform == null) {
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue(currentThread, "currentThread()");
            BlockingEventLoop blockingEventLoop = new BlockingEventLoop(currentThread);
            threadLocal.set(blockingEventLoop);
            return blockingEventLoop;
        }
        return eventLoopImplPlatform;
    }

    public static void resetEventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        ref.set(null);
    }

    public static void setEventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines(EventLoopImplPlatform eventLoop) {
        Intrinsics.checkNotNullParameter(eventLoop, "eventLoop");
        ref.set(eventLoop);
    }
}
