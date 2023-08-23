package kotlin.text;

import java.util.Iterator;
import java.util.regex.Matcher;
import kotlin.collections.AbstractCollection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.TransformingSequence;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MatcherMatchResult$groups$1 extends AbstractCollection {
    final /* synthetic */ MatcherMatchResult this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MatcherMatchResult$groups$1(MatcherMatchResult matcherMatchResult) {
        this.this$0 = matcherMatchResult;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        boolean z;
        if (obj == null) {
            z = true;
        } else {
            z = obj instanceof MatchGroup;
        }
        if (!z) {
            return false;
        }
        return super.contains((MatchGroup) obj);
    }

    public final MatchGroup get(int i) {
        Matcher matcher = (Matcher) MatcherMatchResult.access$getMatchResult(this.this$0);
        IntRange until = RangesKt.until(matcher.start(i), matcher.end(i));
        if (Integer.valueOf(until.getFirst()).intValue() >= 0) {
            String group = ((Matcher) MatcherMatchResult.access$getMatchResult(this.this$0)).group(i);
            Intrinsics.checkNotNullExpressionValue(group, "matchResult.group(index)");
            return new MatchGroup(group, until);
        }
        return null;
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        return ((Matcher) MatcherMatchResult.access$getMatchResult(this.this$0)).groupCount() + 1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        final IntRange intRange = new IntRange(0, getSize() - 1);
        return new TransformingSequence(new Sequence() { // from class: kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public final Iterator iterator() {
                return intRange.iterator();
            }
        }, new Function1() { // from class: kotlin.text.MatcherMatchResult$groups$1$iterator$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MatcherMatchResult$groups$1.this.get(((Number) obj).intValue());
            }
        }).iterator();
    }
}
