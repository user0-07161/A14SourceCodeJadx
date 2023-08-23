package androidx.compose.animation.core;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AnimateAsStateKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final SpringSpec defaultAnimation = AnimationSpecKt.spring$default(0.0f, null, 7);

    static {
        int i = VisibilityThresholdsKt.$r8$clinit;
        Dp.m389boximpl(0.1f);
        Size.Companion companion = Size.Companion;
        SizeKt.Size(0.5f, 0.5f);
        Offset.Companion companion2 = Offset.Companion;
        OffsetKt.Offset(0.5f, 0.5f);
        IntOffset.Companion companion3 = IntOffset.Companion;
        IntOffsetKt.IntOffset(1, 1);
    }

    public static final State animateFloatAsState(float f, String str, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(668842840);
        int i = ComposerKt.$r8$clinit;
        composerImpl.startReplaceableGroup(841393662);
        Float valueOf = Float.valueOf(0.01f);
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(valueOf);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.getEmpty()) {
            nextSlot = AnimationSpecKt.spring$default(0.0f, Float.valueOf(0.01f), 3);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.endReplaceableGroup();
        Object obj = (AnimationSpec) nextSlot;
        composerImpl.endReplaceableGroup();
        final Float valueOf2 = Float.valueOf(f);
        TwoWayConverter typeConverter = VectorConvertersKt.getVectorConverter();
        Float valueOf3 = Float.valueOf(0.01f);
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        composerImpl.startReplaceableGroup(-1994373980);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot2 = composerImpl.nextSlot();
        if (nextSlot2 == Composer.Companion.getEmpty()) {
            nextSlot2 = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateValue(nextSlot2);
        }
        composerImpl.endReplaceableGroup();
        MutableState mutableState = (MutableState) nextSlot2;
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot3 = composerImpl.nextSlot();
        if (nextSlot3 == Composer.Companion.getEmpty()) {
            nextSlot3 = new Animatable(valueOf2, typeConverter, valueOf3, str);
            composerImpl.updateValue(nextSlot3);
        }
        composerImpl.endReplaceableGroup();
        Animatable animatable = (Animatable) nextSlot3;
        MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(null, composerImpl);
        if (valueOf3 != null && (obj instanceof SpringSpec)) {
            SpringSpec springSpec = (SpringSpec) obj;
            if (!Intrinsics.areEqual(springSpec.getVisibilityThreshold(), valueOf3)) {
                obj = new SpringSpec(springSpec.getDampingRatio(), springSpec.getStiffness(), valueOf3);
            }
        }
        MutableState rememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(obj, composerImpl);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot4 = composerImpl.nextSlot();
        if (nextSlot4 == Composer.Companion.getEmpty()) {
            nextSlot4 = ChannelKt.Channel$default(-1, null, 6);
            composerImpl.updateValue(nextSlot4);
        }
        composerImpl.endReplaceableGroup();
        final Channel channel = (Channel) nextSlot4;
        Function0 function0 = new Function0() { // from class: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Channel.this.mo484trySendJP2dKIU(valueOf2);
                return Unit.INSTANCE;
            }
        };
        composerImpl.startReplaceableGroup(-1288466761);
        composerImpl.recordSideEffect(function0);
        composerImpl.endReplaceableGroup();
        EffectsKt.LaunchedEffect(channel, new AnimateAsStateKt$animateValueAsState$3(channel, animatable, rememberUpdatedState2, rememberUpdatedState, null), composerImpl);
        State state = (State) ((SnapshotMutableStateImpl) mutableState).getValue();
        if (state == null) {
            state = animatable.asState();
        }
        composerImpl.endReplaceableGroup();
        composerImpl.endReplaceableGroup();
        return state;
    }
}
