package androidx.compose.foundation.layout;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class BoxKt {
    private static final MeasurePolicy EmptyBoxMeasurePolicy;

    static {
        int i = Alignment.$r8$clinit;
        EmptyBoxMeasurePolicy = BoxKt$EmptyBoxMeasurePolicy$1.INSTANCE;
    }

    public static final void Box(final Modifier modifier, Composer composer, final int i) {
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-211209833);
        if ((i & 14) == 0) {
            if (composerImpl.changed(modifier)) {
                i3 = 4;
            } else {
                i3 = 2;
            }
            i2 = i3 | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            int i4 = ComposerKt.$r8$clinit;
            MeasurePolicy measurePolicy = EmptyBoxMeasurePolicy;
            composerImpl.startReplaceableGroup(-1323940314);
            Density density = (Density) composerImpl.consume(CompositionLocalsKt.getLocalDensity());
            LayoutDirection layoutDirection = (LayoutDirection) composerImpl.consume(CompositionLocalsKt.getLocalLayoutDirection());
            ViewConfiguration viewConfiguration = (ViewConfiguration) composerImpl.consume(CompositionLocalsKt.getLocalViewConfiguration());
            ComposeUiNode.Companion.getClass();
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            ComposableLambdaImpl materializerOf = LayoutKt.materializerOf(modifier);
            int i5 = (((((i2 << 3) & 112) | 384) << 9) & 7168) | 6;
            if (composerImpl.getApplier() instanceof Applier) {
                composerImpl.startReusableNode();
                if (composerImpl.getInserting()) {
                    composerImpl.createNode(constructor);
                } else {
                    composerImpl.useNode();
                }
                composerImpl.disableReusing();
                Updater.m23setimpl(composerImpl, measurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.m23setimpl(composerImpl, density, ComposeUiNode.Companion.getSetDensity());
                Updater.m23setimpl(composerImpl, layoutDirection, ComposeUiNode.Companion.getSetLayoutDirection());
                Updater.m23setimpl(composerImpl, viewConfiguration, ComposeUiNode.Companion.getSetViewConfiguration());
                composerImpl.enableReusing();
                materializerOf.invoke(SkippableUpdater.m21boximpl(composerImpl), composerImpl, Integer.valueOf((i5 >> 3) & 112));
                composerImpl.startReplaceableGroup(2058660585);
                composerImpl.endReplaceableGroup();
                composerImpl.endNode();
                composerImpl.endReplaceableGroup();
            } else {
                ComposablesKt.invalidApplier();
                throw null;
            }
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.layout.BoxKt$Box$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    BoxKt.Box(Modifier.this, (Composer) obj, i | 1);
                    return Unit.INSTANCE;
                }
            });
        }
    }
}
