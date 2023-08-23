package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.ConstrainedOnceSequence;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class MainDispatcherLoader {
    public static final MainCoroutineDispatcher dispatcher;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [kotlin.sequences.ConstrainedOnceSequence] */
    static {
        Object next;
        MainCoroutineDispatcher createDispatcher;
        String systemProp = SystemPropsKt.systemProp("kotlinx.coroutines.fast.service.loader");
        if (systemProp != null) {
            Boolean.parseBoolean(systemProp);
        }
        try {
            Iterator m = MainDispatcherLoader$$ExternalSyntheticServiceLoad0.m();
            Intrinsics.checkNotNullExpressionValue(m, "load(\n                  …             ).iterator()");
            SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 = new SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1(m);
            if (!(sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 instanceof ConstrainedOnceSequence)) {
                sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 = new ConstrainedOnceSequence(sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1);
            }
            List list = SequencesKt.toList(sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1);
            Iterator it = list.iterator();
            if (!it.hasNext()) {
                next = null;
            } else {
                next = it.next();
                if (it.hasNext()) {
                    int loadPriority = ((MainDispatcherFactory) next).getLoadPriority();
                    do {
                        Object next2 = it.next();
                        int loadPriority2 = ((MainDispatcherFactory) next2).getLoadPriority();
                        if (loadPriority < loadPriority2) {
                            next = next2;
                            loadPriority = loadPriority2;
                        }
                    } while (it.hasNext());
                }
            }
            MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) next;
            if (mainDispatcherFactory != null && (createDispatcher = mainDispatcherFactory.createDispatcher(list)) != null) {
                dispatcher = createDispatcher;
            } else {
                MainDispatchersKt.createMissingDispatcher$default(null, 3);
                throw null;
            }
        } catch (Throwable th) {
            MainDispatchersKt.createMissingDispatcher$default(th, 2);
            throw null;
        }
    }
}
