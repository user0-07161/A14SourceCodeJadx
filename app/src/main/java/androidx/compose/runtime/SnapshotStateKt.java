package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SnapshotStateKt {
    public static final State derivedStateOf(Function0 function0) {
        int i = SnapshotStateKt__DerivedStateKt.$r8$clinit;
        return new DerivedSnapshotState(function0);
    }

    public static final ParcelableSnapshotMutableState mutableStateOf(Object obj, SnapshotMutationPolicy policy) {
        Intrinsics.checkNotNullParameter(policy, "policy");
        int i = ActualAndroid_androidKt.$r8$clinit;
        return new ParcelableSnapshotMutableState(obj, policy);
    }

    public static /* synthetic */ ParcelableSnapshotMutableState mutableStateOf$default(Object obj) {
        return mutableStateOf(obj, StructuralEqualityPolicy.INSTANCE);
    }

    public static final SnapshotMutationPolicy neverEqualPolicy() {
        return NeverEqualPolicy.INSTANCE;
    }

    public static final void observeDerivedStateRecalculations(Function1 function1, Function1 function12, Function0 function0) {
        SnapshotStateKt__DerivedStateKt.observeDerivedStateRecalculations(function1, function12, function0);
    }

    public static final SnapshotMutationPolicy referentialEqualityPolicy() {
        return ReferentialEqualityPolicy.INSTANCE;
    }

    public static final MutableState rememberUpdatedState(Object obj, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-1058319986);
        int i = ComposerKt.$r8$clinit;
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot = composerImpl.nextSlot();
        if (nextSlot == Composer.Companion.getEmpty()) {
            nextSlot = mutableStateOf$default(obj);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.endReplaceableGroup();
        MutableState mutableState = (MutableState) nextSlot;
        ((SnapshotMutableStateImpl) mutableState).setValue(obj);
        composerImpl.endReplaceableGroup();
        return mutableState;
    }

    public static final Flow snapshotFlow(Function0 function0) {
        return FlowKt.flow(new SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1(function0, null));
    }

    public static final SnapshotMutationPolicy structuralEqualityPolicy() {
        return StructuralEqualityPolicy.INSTANCE;
    }
}
