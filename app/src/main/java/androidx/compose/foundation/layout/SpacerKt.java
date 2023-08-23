package androidx.compose.foundation.layout;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SpacerKt {
    public static final void Spacer(Modifier modifier, Composer composer) {
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-72882467);
        int i = ComposerKt.$r8$clinit;
        SpacerMeasurePolicy spacerMeasurePolicy = SpacerMeasurePolicy.INSTANCE;
        composerImpl.startReplaceableGroup(-1323940314);
        Density density = (Density) composerImpl.consume(CompositionLocalsKt.getLocalDensity());
        LayoutDirection layoutDirection = (LayoutDirection) composerImpl.consume(CompositionLocalsKt.getLocalLayoutDirection());
        ViewConfiguration viewConfiguration = (ViewConfiguration) composerImpl.consume(CompositionLocalsKt.getLocalViewConfiguration());
        ComposeUiNode.Companion.getClass();
        Function0 constructor = ComposeUiNode.Companion.getConstructor();
        ComposableLambdaImpl materializerOf = LayoutKt.materializerOf(modifier);
        if (composerImpl.getApplier() instanceof Applier) {
            composerImpl.startReusableNode();
            if (composerImpl.getInserting()) {
                composerImpl.createNode(constructor);
            } else {
                composerImpl.useNode();
            }
            composerImpl.disableReusing();
            Updater.m23setimpl(composerImpl, spacerMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.m23setimpl(composerImpl, density, ComposeUiNode.Companion.getSetDensity());
            Updater.m23setimpl(composerImpl, layoutDirection, ComposeUiNode.Companion.getSetLayoutDirection());
            Updater.m23setimpl(composerImpl, viewConfiguration, ComposeUiNode.Companion.getSetViewConfiguration());
            composerImpl.enableReusing();
            materializerOf.invoke((Object) SkippableUpdater.m21boximpl(composerImpl), (Object) composerImpl, (Object) 0);
            composerImpl.startReplaceableGroup(2058660585);
            composerImpl.endReplaceableGroup();
            composerImpl.endNode();
            composerImpl.endReplaceableGroup();
            composerImpl.endReplaceableGroup();
            return;
        }
        ComposablesKt.invalidApplier();
        throw null;
    }
}
