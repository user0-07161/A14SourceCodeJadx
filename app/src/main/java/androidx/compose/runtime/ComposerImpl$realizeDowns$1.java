package androidx.compose.runtime;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ComposerImpl$realizeDowns$1 extends Lambda implements Function3 {
    final /* synthetic */ Object[] $nodes;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerImpl$realizeDowns$1(Object[] objArr) {
        super(3);
        this.$nodes = objArr;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        Applier applier = (Applier) obj;
        ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", (SlotWriter) obj2, "<anonymous parameter 1>", (RememberManager) obj3, "<anonymous parameter 2>");
        int length = this.$nodes.length;
        for (int i = 0; i < length; i++) {
            applier.down(this.$nodes[i]);
        }
        return Unit.INSTANCE;
    }
}
