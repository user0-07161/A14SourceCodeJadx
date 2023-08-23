package androidx.compose.runtime;

import androidx.compose.runtime.collection.IdentityArraySet;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Recomposer$performRecompose$1$1 extends Lambda implements Function0 {
    final /* synthetic */ ControlledComposition $composition;
    final /* synthetic */ IdentityArraySet $modifiedValues;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recomposer$performRecompose$1$1(ControlledComposition controlledComposition, IdentityArraySet identityArraySet) {
        super(0);
        this.$modifiedValues = identityArraySet;
        this.$composition = controlledComposition;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        IdentityArraySet identityArraySet = this.$modifiedValues;
        ControlledComposition controlledComposition = this.$composition;
        int size = identityArraySet.size();
        for (int i = 0; i < size; i++) {
            ((CompositionImpl) controlledComposition).recordWriteOf(identityArraySet.get(i));
        }
        return Unit.INSTANCE;
    }
}
