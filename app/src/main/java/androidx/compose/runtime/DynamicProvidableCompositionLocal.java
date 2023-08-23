package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DynamicProvidableCompositionLocal extends ProvidableCompositionLocal {
    private final SnapshotMutationPolicy policy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicProvidableCompositionLocal(SnapshotMutationPolicy policy, Function0 defaultFactory) {
        super(defaultFactory);
        Intrinsics.checkNotNullParameter(policy, "policy");
        Intrinsics.checkNotNullParameter(defaultFactory, "defaultFactory");
        this.policy = policy;
    }

    @Override // androidx.compose.runtime.CompositionLocal
    public final State provided$runtime_release(Object obj, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-84026900);
        int i = ComposerKt.$r8$clinit;
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot = composerImpl.nextSlot();
        if (nextSlot == Composer.Companion.getEmpty()) {
            nextSlot = SnapshotStateKt.mutableStateOf(obj, this.policy);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.endReplaceableGroup();
        MutableState mutableState = (MutableState) nextSlot;
        ((SnapshotMutableStateImpl) mutableState).setValue(obj);
        composerImpl.endReplaceableGroup();
        return mutableState;
    }
}
