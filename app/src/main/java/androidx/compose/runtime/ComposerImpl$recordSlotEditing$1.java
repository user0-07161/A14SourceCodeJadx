package androidx.compose.runtime;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ComposerImpl$recordSlotEditing$1 extends Lambda implements Function3 {
    final /* synthetic */ Anchor $anchor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerImpl$recordSlotEditing$1(Anchor anchor) {
        super(3);
        this.$anchor = anchor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SlotWriter slotWriter = (SlotWriter) obj2;
        ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m((Applier) obj, "<anonymous parameter 0>", slotWriter, "slots", (RememberManager) obj3, "<anonymous parameter 2>");
        Anchor anchor = this.$anchor;
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        slotWriter.ensureStarted(slotWriter.anchorIndex(anchor));
        return Unit.INSTANCE;
    }
}
