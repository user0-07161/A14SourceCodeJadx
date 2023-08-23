package kotlin.sequences;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SequencesKt extends SequencesKt___SequencesJvmKt {
    public static Sequence generateSequence(final Object obj, Function1 nextFunction) {
        Intrinsics.checkNotNullParameter(nextFunction, "nextFunction");
        if (obj == null) {
            return EmptySequence.INSTANCE;
        }
        return new GeneratorSequence(new Function0() { // from class: kotlin.sequences.SequencesKt__SequencesKt$generateSequence$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return obj;
            }
        }, nextFunction);
    }

    public static FilteringSequence mapNotNull(Sequence sequence, Function1 transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        TransformingSequence transformingSequence = new TransformingSequence(sequence, transform);
        SequencesKt___SequencesKt$filterNotNull$1 predicate = new Function1() { // from class: kotlin.sequences.SequencesKt___SequencesKt$filterNotNull$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean z;
                if (obj == null) {
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        };
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return new FilteringSequence(transformingSequence, predicate);
    }

    public static List toList(Sequence sequence) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : sequence) {
            arrayList.add(obj);
        }
        int size = arrayList.size();
        if (size != 0) {
            if (size == 1) {
                return CollectionsKt.listOf(arrayList.get(0));
            }
            return arrayList;
        }
        return EmptyList.INSTANCE;
    }

    public static Sequence generateSequence(Function0 function0, Function1 nextFunction) {
        Intrinsics.checkNotNullParameter(nextFunction, "nextFunction");
        return new GeneratorSequence(function0, nextFunction);
    }
}
