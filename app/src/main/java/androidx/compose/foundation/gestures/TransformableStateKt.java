package androidx.compose.foundation.gestures;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TransformableStateKt {
    public static final TransformableState rememberTransformableState(Function3 onTransformation, Composer composer) {
        Intrinsics.checkNotNullParameter(onTransformation, "onTransformation");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(1681419281);
        int i = ComposerKt.$r8$clinit;
        final MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(onTransformation, composerImpl);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot = composerImpl.nextSlot();
        if (nextSlot == Composer.Companion.getEmpty()) {
            DefaultTransformableState defaultTransformableState = new DefaultTransformableState(new Function3() { // from class: androidx.compose.foundation.gestures.TransformableStateKt$rememberTransformableState$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    ((Function3) rememberUpdatedState.getValue()).invoke(Float.valueOf(((Number) obj).floatValue()), Offset.m42boximpl(((Offset) obj2).m51unboximpl()), Float.valueOf(((Number) obj3).floatValue()));
                    return Unit.INSTANCE;
                }
            });
            composerImpl.updateValue(defaultTransformableState);
            nextSlot = defaultTransformableState;
        }
        composerImpl.endReplaceableGroup();
        TransformableState transformableState = (TransformableState) nextSlot;
        composerImpl.endReplaceableGroup();
        return transformableState;
    }
}
