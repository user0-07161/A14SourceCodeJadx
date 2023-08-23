package androidx.compose.runtime;

import androidx.compose.runtime.collection.IdentityArraySet;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Recomposer$writeObserverOf$1 extends Lambda implements Function1 {
    final /* synthetic */ ControlledComposition $composition;
    final /* synthetic */ IdentityArraySet $modifiedValues;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recomposer$writeObserverOf$1(ControlledComposition controlledComposition, IdentityArraySet identityArraySet) {
        super(1);
        this.$composition = controlledComposition;
        this.$modifiedValues = identityArraySet;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ((CompositionImpl) this.$composition).recordWriteOf(value);
        IdentityArraySet identityArraySet = this.$modifiedValues;
        if (identityArraySet != null) {
            identityArraySet.add(value);
        }
        return Unit.INSTANCE;
    }
}
