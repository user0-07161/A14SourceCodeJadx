package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilter;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class EffectsKt {
    private static final DisposableEffectScope InternalDisposableEffectScope = new DisposableEffectScope();

    public static final void DisposableEffect(Object obj, Function1 effect, Composer composer) {
        Intrinsics.checkNotNullParameter(effect, "effect");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-1371986847);
        int i = ComposerKt.$r8$clinit;
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(obj);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.getEmpty()) {
            composerImpl.updateValue(new DisposableEffectImpl(effect));
        }
        composerImpl.endReplaceableGroup();
        composerImpl.endReplaceableGroup();
    }

    public static final void LaunchedEffect(Object obj, Function2 block, Composer composer) {
        Intrinsics.checkNotNullParameter(block, "block");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(1179185413);
        int i = ComposerKt.$r8$clinit;
        CoroutineContext applyCoroutineContext = composerImpl.getApplyCoroutineContext();
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(obj);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.getEmpty()) {
            composerImpl.updateValue(new LaunchedEffectImpl(applyCoroutineContext, block));
        }
        composerImpl.endReplaceableGroup();
        composerImpl.endReplaceableGroup();
    }

    public static final void LaunchedEffect(SuspendingPointerInputFilter suspendingPointerInputFilter, Object obj, Function2 function2, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(590241125);
        int i = ComposerKt.$r8$clinit;
        CoroutineContext applyCoroutineContext = composerImpl.getApplyCoroutineContext();
        composerImpl.startReplaceableGroup(511388516);
        boolean changed = composerImpl.changed(suspendingPointerInputFilter) | composerImpl.changed(obj);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.getEmpty()) {
            composerImpl.updateValue(new LaunchedEffectImpl(applyCoroutineContext, function2));
        }
        composerImpl.endReplaceableGroup();
        composerImpl.endReplaceableGroup();
    }
}
