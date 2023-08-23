package androidx.arch.core.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DefaultTaskExecutor extends TaskExecutor {
    private final Object mLock = new Object();

    public DefaultTaskExecutor() {
        Executors.newFixedThreadPool(4, new ThreadFactory() { // from class: androidx.arch.core.executor.DefaultTaskExecutor.1
            private final AtomicInteger mThreadId = new AtomicInteger(0);

            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("arch_disk_io_" + this.mThreadId.getAndIncrement());
                return thread;
            }
        });
    }
}
