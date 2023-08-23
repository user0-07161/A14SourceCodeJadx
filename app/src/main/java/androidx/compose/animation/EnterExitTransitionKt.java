package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class EnterExitTransitionKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final SpringSpec DefaultOffsetAnimationSpec;
    private static final SpringSpec DefaultSizeAnimationSpec;
    private static final TwoWayConverter TransformOriginVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            long m127unboximpl = ((TransformOrigin) obj).m127unboximpl();
            return new AnimationVector2D(Float.intBitsToFloat((int) (m127unboximpl >> 32)), TransformOrigin.m126getPivotFractionYimpl(m127unboximpl));
        }
    }, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D it = (AnimationVector2D) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            float v1 = it.getV1();
            float v2 = it.getV2();
            return TransformOrigin.m125boximpl((Float.floatToIntBits(v2) & 4294967295L) | (Float.floatToIntBits(v1) << 32));
        }
    });
    private static final ParcelableSnapshotMutableState DefaultAlpha = SnapshotStateKt.mutableStateOf$default(Float.valueOf(1.0f));
    private static final SpringSpec DefaultAlphaAndScaleSpring = AnimationSpecKt.spring$default(400.0f, null, 5);

    static {
        IntOffset.Companion companion = IntOffset.Companion;
        DefaultOffsetAnimationSpec = AnimationSpecKt.spring$default(400.0f, IntOffset.m401boximpl(IntOffsetKt.IntOffset(1, 1)), 1);
        DefaultSizeAnimationSpec = AnimationSpecKt.spring$default(400.0f, IntSize.m404boximpl(IntSizeKt.IntSize(1, 1)), 1);
    }

    public static final /* synthetic */ SpringSpec access$getDefaultOffsetAnimationSpec$p() {
        return DefaultOffsetAnimationSpec;
    }

    public static final /* synthetic */ SpringSpec access$getDefaultSizeAnimationSpec$p() {
        return DefaultSizeAnimationSpec;
    }

    /* JADX WARN: Removed duplicated region for block: B:167:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x01c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.Modifier createModifier(final androidx.compose.animation.core.Transition r20, final androidx.compose.animation.EnterTransition r21, final androidx.compose.animation.ExitTransition r22, androidx.compose.runtime.Composer r23, int r24) {
        /*
            Method dump skipped, instructions count: 1145
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.EnterExitTransitionKt.createModifier(androidx.compose.animation.core.Transition, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, androidx.compose.runtime.Composer, int):androidx.compose.ui.Modifier");
    }

    public static EnterTransition expandVertically$default() {
        int i = VisibilityThresholdsKt.$r8$clinit;
        SpringSpec spring$default = AnimationSpecKt.spring$default(400.0f, IntSize.m404boximpl(IntSizeKt.IntSize(1, 1)), 1);
        BiasAlignment.Vertical bottom = Alignment.Companion.getBottom();
        final EnterExitTransitionKt$expandVertically$1 initialHeight = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$expandVertically$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Number) obj).intValue();
                return 0;
            }
        };
        Intrinsics.checkNotNullParameter(initialHeight, "initialHeight");
        return new EnterTransitionImpl(new TransitionData(null, new ChangeSize(spring$default, toAlignment(bottom), new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$expandVertically$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long m407unboximpl = ((IntSize) obj).m407unboximpl();
                return IntSize.m404boximpl(IntSizeKt.IntSize((int) (m407unboximpl >> 32), ((Number) Function1.this.invoke(Integer.valueOf(IntSize.m405getHeightimpl(m407unboximpl)))).intValue()));
            }
        }, true), 11));
    }

    public static EnterTransition fadeIn$default(TweenSpec tweenSpec, int i) {
        FiniteAnimationSpec animationSpec = tweenSpec;
        if ((i & 1) != 0) {
            animationSpec = AnimationSpecKt.spring$default(400.0f, null, 5);
        }
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new EnterTransitionImpl(new TransitionData(new Fade(0.0f, animationSpec), null, 14));
    }

    public static ExitTransition shrinkVertically$default() {
        int i = VisibilityThresholdsKt.$r8$clinit;
        SpringSpec spring$default = AnimationSpecKt.spring$default(400.0f, IntSize.m404boximpl(IntSizeKt.IntSize(1, 1)), 1);
        BiasAlignment.Vertical bottom = Alignment.Companion.getBottom();
        final EnterExitTransitionKt$shrinkVertically$1 targetHeight = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkVertically$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Number) obj).intValue();
                return 0;
            }
        };
        Intrinsics.checkNotNullParameter(targetHeight, "targetHeight");
        return new ExitTransitionImpl(new TransitionData(null, new ChangeSize(spring$default, toAlignment(bottom), new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$shrinkVertically$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long m407unboximpl = ((IntSize) obj).m407unboximpl();
                return IntSize.m404boximpl(IntSizeKt.IntSize((int) (m407unboximpl >> 32), ((Number) Function1.this.invoke(Integer.valueOf(IntSize.m405getHeightimpl(m407unboximpl)))).intValue()));
            }
        }, true), 11));
    }

    private static final BiasAlignment toAlignment(Alignment.Vertical vertical) {
        if (Intrinsics.areEqual(vertical, Alignment.Companion.getTop())) {
            return Alignment.Companion.getTopCenter();
        }
        if (Intrinsics.areEqual(vertical, Alignment.Companion.getBottom())) {
            return Alignment.Companion.getBottomCenter();
        }
        return Alignment.Companion.getCenter();
    }
}
