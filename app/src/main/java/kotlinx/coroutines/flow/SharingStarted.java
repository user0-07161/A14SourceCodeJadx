package kotlinx.coroutines.flow;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface SharingStarted {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final SharingStarted Eagerly = new StartedEagerly();
        private static final SharingStarted Lazily = new StartedLazily();

        public static SharingStarted WhileSubscribed$default() {
            return new StartedWhileSubscribed(0L, Long.MAX_VALUE);
        }

        public static SharingStarted getEagerly() {
            return Eagerly;
        }

        public static SharingStarted getLazily() {
            return Lazily;
        }
    }

    Flow command(StateFlow stateFlow);
}
