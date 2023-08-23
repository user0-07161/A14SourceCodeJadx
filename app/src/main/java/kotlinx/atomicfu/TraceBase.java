package kotlinx.atomicfu;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TraceBase {

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class None extends TraceBase {
        public static final None INSTANCE = new None();
    }

    public static void append(Object event) {
        Intrinsics.checkNotNullParameter(event, "event");
    }
}
