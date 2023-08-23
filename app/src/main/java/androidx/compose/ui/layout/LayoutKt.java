package androidx.compose.ui.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class LayoutKt {
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.layout.LayoutKt$materializerOf$1, kotlin.jvm.internal.Lambda] */
    public static final ComposableLambdaImpl materializerOf(final Modifier modifier) {
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        return ComposableLambdaKt.composableLambdaInstance(-1586257396, new Function3() { // from class: androidx.compose.ui.layout.LayoutKt$materializerOf$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Composer m22unboximpl = ((SkippableUpdater) obj).m22unboximpl();
                ((Number) obj3).intValue();
                Intrinsics.checkNotNullParameter(m22unboximpl, "$this$null");
                int i = ComposerKt.$r8$clinit;
                Modifier materialize = ComposedModifierKt.materialize((Composer) obj2, Modifier.this);
                ComposerImpl composerImpl = (ComposerImpl) m22unboximpl;
                composerImpl.startReplaceableGroup(509942095);
                ComposeUiNode.Companion.getClass();
                Updater.m23setimpl(m22unboximpl, materialize, ComposeUiNode.Companion.getSetModifier());
                composerImpl.endReplaceableGroup();
                return Unit.INSTANCE;
            }
        }, true);
    }
}
