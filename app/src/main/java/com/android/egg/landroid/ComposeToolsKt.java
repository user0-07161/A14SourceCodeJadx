package com.android.egg.landroid;

import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.Easing;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ComposeToolsKt {
    private static final EnterTransition flickerFadeIn = EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(new ComposeToolsKt$sam$androidx_compose_animation_core_Easing$0(times(new CubicBezierEasing(0.0f, 1.0f, 1.0f, 0.0f), flickerFadeEasing(Random.Default)))), 2);

    public static final Easing flickerFadeEasing(final Random rng) {
        Intrinsics.checkNotNullParameter(rng, "rng");
        return new Easing() { // from class: com.android.egg.landroid.ComposeToolsKt$flickerFadeEasing$1
            @Override // androidx.compose.animation.core.Easing
            public final float transform(float f) {
                if (Random.this.nextFloat() < f) {
                    return 1.0f;
                }
                return 0.0f;
            }
        };
    }

    public static final EnterTransition getFlickerFadeIn() {
        return flickerFadeIn;
    }

    public static final Function1 times(final Easing easing, final Easing next) {
        Intrinsics.checkNotNullParameter(easing, "<this>");
        Intrinsics.checkNotNullParameter(next, "next");
        return new Function1() { // from class: com.android.egg.landroid.ComposeToolsKt$times$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Float invoke(float f) {
                return Float.valueOf(Easing.this.transform(easing.transform(f)));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).floatValue());
            }
        };
    }

    /* renamed from: toLocalPx-8Feqmps  reason: not valid java name */
    public static final float m433toLocalPx8Feqmps(float f, Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-1542482437);
        int i2 = ComposerKt.$r8$clinit;
        float mo206toPx0680j_4 = ((Density) composerImpl.consume(CompositionLocalsKt.getLocalDensity())).mo206toPx0680j_4(f);
        composerImpl.endReplaceableGroup();
        return mo206toPx0680j_4;
    }
}
