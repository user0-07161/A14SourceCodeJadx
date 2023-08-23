package kotlinx.coroutines.flow;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class StartedLazily implements SharingStarted {
    @Override // kotlinx.coroutines.flow.SharingStarted
    public final Flow command(StateFlow stateFlow) {
        return new SafeFlow(new StartedLazily$command$1(stateFlow, null));
    }

    public final String toString() {
        return "SharingStarted.Lazily";
    }
}
