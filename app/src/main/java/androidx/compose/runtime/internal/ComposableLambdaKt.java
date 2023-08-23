package androidx.compose.runtime.internal;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.RecomposeScopeImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ComposableLambdaKt {
    public static final int bitsForSlot(int i, int i2) {
        return i << (((i2 % 10) * 3) + 1);
    }

    public static final ComposableLambdaImpl composableLambda(Composer composer, int i, Lambda lambda) {
        ComposableLambdaImpl composableLambdaImpl;
        Intrinsics.checkNotNullParameter(composer, "composer");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(i);
        Object nextSlot = composerImpl.nextSlot();
        if (nextSlot == Composer.Companion.getEmpty()) {
            composableLambdaImpl = new ComposableLambdaImpl(i, true);
            composerImpl.updateValue(composableLambdaImpl);
        } else {
            Intrinsics.checkNotNull(nextSlot, "null cannot be cast to non-null type androidx.compose.runtime.internal.ComposableLambdaImpl");
            composableLambdaImpl = (ComposableLambdaImpl) nextSlot;
        }
        composableLambdaImpl.update(lambda);
        composerImpl.endReplaceableGroup();
        return composableLambdaImpl;
    }

    public static final ComposableLambdaImpl composableLambdaInstance(int i, Lambda block, boolean z) {
        Intrinsics.checkNotNullParameter(block, "block");
        ComposableLambdaImpl composableLambdaImpl = new ComposableLambdaImpl(i, z);
        composableLambdaImpl.update(block);
        return composableLambdaImpl;
    }

    public static final boolean replacableWith(RecomposeScopeImpl recomposeScopeImpl, RecomposeScopeImpl recomposeScopeImpl2) {
        if (recomposeScopeImpl != null && recomposeScopeImpl.getValid() && !Intrinsics.areEqual(recomposeScopeImpl, recomposeScopeImpl2) && !Intrinsics.areEqual(recomposeScopeImpl.getAnchor(), recomposeScopeImpl2.getAnchor())) {
            return false;
        }
        return true;
    }
}
