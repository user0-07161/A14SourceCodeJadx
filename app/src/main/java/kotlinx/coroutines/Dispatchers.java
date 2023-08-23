package kotlinx.coroutines;

import kotlinx.coroutines.scheduling.DefaultIoScheduler;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Dispatchers {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final DefaultScheduler Default = DefaultScheduler.INSTANCE;
    private static final DefaultIoScheduler IO;

    static {
        int i = Unconfined.$r8$clinit;
        IO = DefaultIoScheduler.INSTANCE;
    }

    public static final DefaultScheduler getDefault() {
        return Default;
    }

    public static final DefaultIoScheduler getIO() {
        return IO;
    }
}
