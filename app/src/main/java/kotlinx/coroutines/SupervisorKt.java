package kotlinx.coroutines;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SupervisorKt {
    public static final JobImpl SupervisorJob(Job job) {
        return new SupervisorJobImpl(job);
    }

    public static JobImpl SupervisorJob$default() {
        return new SupervisorJobImpl(null);
    }
}
