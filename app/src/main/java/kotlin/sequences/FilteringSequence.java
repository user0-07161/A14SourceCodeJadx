package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FilteringSequence implements Sequence {
    private final Function1 predicate;
    private final boolean sendWhen = false;
    private final Sequence sequence;

    public FilteringSequence(TransformingSequence transformingSequence, Function1 function1) {
        this.sequence = transformingSequence;
        this.predicate = function1;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        return new FilteringSequence$iterator$1(this);
    }
}
