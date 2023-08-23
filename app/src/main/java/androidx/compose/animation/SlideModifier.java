package androidx.compose.animation;

import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.State;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class SlideModifier extends LayoutModifierWithPassThroughIntrinsics {
    private final Transition.DeferredAnimation lazyAnimation;
    private final State slideIn;
    private final State slideOut;
    private final Function1 transitionSpec;

    public SlideModifier(Transition.DeferredAnimation lazyAnimation, State slideIn, State slideOut) {
        Intrinsics.checkNotNullParameter(lazyAnimation, "lazyAnimation");
        Intrinsics.checkNotNullParameter(slideIn, "slideIn");
        Intrinsics.checkNotNullParameter(slideOut, "slideOut");
        this.lazyAnimation = lazyAnimation;
        this.slideIn = slideIn;
        this.slideOut = slideOut;
        this.transitionSpec = new Function1() { // from class: androidx.compose.animation.SlideModifier$transitionSpec$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Transition.Segment segment = (Transition.Segment) obj;
                Intrinsics.checkNotNullParameter(segment, "$this$null");
                EnterExitState enterExitState = EnterExitState.PreEnter;
                EnterExitState enterExitState2 = EnterExitState.Visible;
                if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                    OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(SlideModifier.this.getSlideIn().getValue());
                    return EnterExitTransitionKt.access$getDefaultOffsetAnimationSpec$p();
                } else if (segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                    OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(SlideModifier.this.getSlideOut().getValue());
                    return EnterExitTransitionKt.access$getDefaultOffsetAnimationSpec$p();
                } else {
                    return EnterExitTransitionKt.access$getDefaultOffsetAnimationSpec$p();
                }
            }
        };
    }

    public final Transition.DeferredAnimation getLazyAnimation() {
        return this.lazyAnimation;
    }

    public final State getSlideIn() {
        return this.slideIn;
    }

    public final State getSlideOut() {
        return this.slideOut;
    }

    public final Function1 getTransitionSpec() {
        return this.transitionSpec;
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        MeasureScope$layout$1 layout;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        final Placeable mo210measureBRTryo0 = measurable.mo210measureBRTryo0(j);
        final long IntSize = IntSizeKt.IntSize(mo210measureBRTryo0.getWidth(), mo210measureBRTryo0.getHeight());
        layout = measure.layout(mo210measureBRTryo0.getWidth(), mo210measureBRTryo0.getHeight(), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.animation.SlideModifier$measure$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Function1 function1;
                Placeable.PlacementScope layout2 = (Placeable.PlacementScope) obj;
                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                Transition.DeferredAnimation lazyAnimation = SlideModifier.this.getLazyAnimation();
                Function1 transitionSpec = SlideModifier.this.getTransitionSpec();
                final SlideModifier slideModifier = SlideModifier.this;
                final long j2 = IntSize;
                Transition.DeferredAnimation.DeferredAnimationData animate = lazyAnimation.animate(transitionSpec, new Function1() { // from class: androidx.compose.animation.SlideModifier$measure$1$slideOffset$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        EnterExitState it = (EnterExitState) obj2;
                        Intrinsics.checkNotNullParameter(it, "it");
                        return IntOffset.m401boximpl(SlideModifier.this.m5targetValueByStateoFUgxo0(it));
                    }
                });
                Placeable placeable = mo210measureBRTryo0;
                long m403unboximpl = ((IntOffset) animate.getValue()).m403unboximpl();
                function1 = PlaceableKt.DefaultLayerBlock;
                Placeable.PlacementScope.m220placeWithLayeraW9wM(placeable, m403unboximpl, 0.0f, function1);
                return Unit.INSTANCE;
            }
        });
        return layout;
    }

    /* renamed from: targetValueByState-oFUgxo0  reason: not valid java name */
    public final long m5targetValueByStateoFUgxo0(EnterExitState targetState) {
        long j;
        long j2;
        long j3;
        Intrinsics.checkNotNullParameter(targetState, "targetState");
        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(this.slideIn.getValue());
        j = IntOffset.Zero;
        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(this.slideOut.getValue());
        j2 = IntOffset.Zero;
        int ordinal = targetState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                j3 = IntOffset.Zero;
                return j3;
            } else if (ordinal == 2) {
                return j2;
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        return j;
    }
}
