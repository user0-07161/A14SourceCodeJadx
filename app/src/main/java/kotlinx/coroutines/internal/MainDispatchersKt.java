package kotlinx.coroutines.internal;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class MainDispatchersKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void createMissingDispatcher$default(Throwable th, int i) {
        if ((i & 1) != 0) {
            th = null;
        }
        if (th != null) {
            throw th;
        }
        throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'");
    }
}
