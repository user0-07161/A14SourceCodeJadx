package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class BlockingCoroutine extends AbstractCoroutine {
    private final Thread blockedThread;
    private final EventLoopImplPlatform eventLoop;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlockingCoroutine(CoroutineContext parentContext, Thread thread, EventLoopImplPlatform eventLoopImplPlatform) {
        super(parentContext, true);
        Intrinsics.checkNotNullParameter(parentContext, "parentContext");
        this.blockedThread = thread;
        this.eventLoop = eventLoopImplPlatform;
    }

    @Override // kotlinx.coroutines.JobSupport
    protected final void afterCompletion(Object obj) {
        Thread currentThread = Thread.currentThread();
        Thread thread = this.blockedThread;
        if (!Intrinsics.areEqual(currentThread, thread)) {
            LockSupport.unpark(thread);
        }
    }

    public final Object joinBlocking() {
        long j;
        CompletedExceptionally completedExceptionally;
        EventLoopImplPlatform eventLoopImplPlatform = this.eventLoop;
        if (eventLoopImplPlatform != null) {
            int i = EventLoopImplPlatform.$r8$clinit;
            eventLoopImplPlatform.incrementUseCount(false);
        }
        while (!Thread.interrupted()) {
            try {
                if (eventLoopImplPlatform != null) {
                    j = eventLoopImplPlatform.processNextEvent();
                } else {
                    j = Long.MAX_VALUE;
                }
                if (!(!(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() instanceof Incomplete))) {
                    LockSupport.parkNanos(this, j);
                } else {
                    Object unboxState = JobSupportKt.unboxState(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines());
                    if (unboxState instanceof CompletedExceptionally) {
                        completedExceptionally = (CompletedExceptionally) unboxState;
                    } else {
                        completedExceptionally = null;
                    }
                    if (completedExceptionally == null) {
                        return unboxState;
                    }
                    throw completedExceptionally.cause;
                }
            } finally {
                if (eventLoopImplPlatform != null) {
                    int i2 = EventLoopImplPlatform.$r8$clinit;
                    eventLoopImplPlatform.decrementUseCount(false);
                }
            }
        }
        InterruptedException interruptedException = new InterruptedException();
        cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines(interruptedException);
        throw interruptedException;
    }
}
