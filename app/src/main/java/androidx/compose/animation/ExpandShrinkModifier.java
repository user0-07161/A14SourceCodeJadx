package androidx.compose.animation;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class ExpandShrinkModifier extends LayoutModifierWithPassThroughIntrinsics {
    private final State alignment;
    private Alignment currentAlignment;
    private final State expand;
    private final Transition.DeferredAnimation offsetAnimation;
    private final State shrink;
    private final Transition.DeferredAnimation sizeAnimation;
    private final Function1 sizeTransitionSpec;

    public ExpandShrinkModifier(Transition.DeferredAnimation sizeAnimation, Transition.DeferredAnimation offsetAnimation, State expand, State shrink, MutableState alignment) {
        Intrinsics.checkNotNullParameter(sizeAnimation, "sizeAnimation");
        Intrinsics.checkNotNullParameter(offsetAnimation, "offsetAnimation");
        Intrinsics.checkNotNullParameter(expand, "expand");
        Intrinsics.checkNotNullParameter(shrink, "shrink");
        Intrinsics.checkNotNullParameter(alignment, "alignment");
        this.sizeAnimation = sizeAnimation;
        this.offsetAnimation = offsetAnimation;
        this.expand = expand;
        this.shrink = shrink;
        this.alignment = alignment;
        this.sizeTransitionSpec = new Function1() { // from class: androidx.compose.animation.ExpandShrinkModifier$sizeTransitionSpec$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                FiniteAnimationSpec access$getDefaultSizeAnimationSpec$p;
                Transition.Segment segment = (Transition.Segment) obj;
                Intrinsics.checkNotNullParameter(segment, "$this$null");
                EnterExitState enterExitState = EnterExitState.PreEnter;
                EnterExitState enterExitState2 = EnterExitState.Visible;
                if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                    ChangeSize changeSize = (ChangeSize) ExpandShrinkModifier.this.getExpand().getValue();
                    if (changeSize != null) {
                        access$getDefaultSizeAnimationSpec$p = changeSize.getAnimationSpec();
                    }
                    access$getDefaultSizeAnimationSpec$p = null;
                } else if (segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                    ChangeSize changeSize2 = (ChangeSize) ExpandShrinkModifier.this.getShrink().getValue();
                    if (changeSize2 != null) {
                        access$getDefaultSizeAnimationSpec$p = changeSize2.getAnimationSpec();
                    }
                    access$getDefaultSizeAnimationSpec$p = null;
                } else {
                    access$getDefaultSizeAnimationSpec$p = EnterExitTransitionKt.access$getDefaultSizeAnimationSpec$p();
                }
                if (access$getDefaultSizeAnimationSpec$p == null) {
                    return EnterExitTransitionKt.access$getDefaultSizeAnimationSpec$p();
                }
                return access$getDefaultSizeAnimationSpec$p;
            }
        };
    }

    public final Alignment getCurrentAlignment() {
        return this.currentAlignment;
    }

    public final State getExpand() {
        return this.expand;
    }

    public final State getShrink() {
        return this.shrink;
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s  reason: not valid java name */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        long j2;
        MeasureScope$layout$1 layout;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        final Placeable mo210measureBRTryo0 = measurable.mo210measureBRTryo0(j);
        final long IntSize = IntSizeKt.IntSize(mo210measureBRTryo0.getWidth(), mo210measureBRTryo0.getHeight());
        long m407unboximpl = ((IntSize) this.sizeAnimation.animate(this.sizeTransitionSpec, new Function1() { // from class: androidx.compose.animation.ExpandShrinkModifier$measure$currentSize$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                EnterExitState it = (EnterExitState) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                return IntSize.m404boximpl(ExpandShrinkModifier.this.m3sizeByStateUzc_VyU(it, IntSize));
            }
        }).getValue()).m407unboximpl();
        final long m403unboximpl = ((IntOffset) this.offsetAnimation.animate(new Function1() { // from class: androidx.compose.animation.ExpandShrinkModifier$measure$offsetDelta$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Transition.Segment animate = (Transition.Segment) obj;
                Intrinsics.checkNotNullParameter(animate, "$this$animate");
                return EnterExitTransitionKt.access$getDefaultOffsetAnimationSpec$p();
            }
        }, new Function1() { // from class: androidx.compose.animation.ExpandShrinkModifier$measure$offsetDelta$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                EnterExitState it = (EnterExitState) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                return IntOffset.m401boximpl(ExpandShrinkModifier.this.m4targetOffsetByStateoFUgxo0(it, IntSize));
            }
        }).getValue()).m403unboximpl();
        Alignment alignment = this.currentAlignment;
        if (alignment == null) {
            j2 = IntOffset.Zero;
        } else {
            j2 = ((BiasAlignment) alignment).m24alignKFBX0sM(IntSize, m407unboximpl, LayoutDirection.Ltr);
        }
        final long j3 = j2;
        layout = measure.layout((int) (m407unboximpl >> 32), IntSize.m405getHeightimpl(m407unboximpl), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.animation.ExpandShrinkModifier$measure$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope layout2 = (Placeable.PlacementScope) obj;
                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                Placeable placeable = Placeable.this;
                long j4 = j3;
                IntOffset.Companion companion = IntOffset.Companion;
                Placeable.PlacementScope.place$default(layout2, placeable, ((int) (m403unboximpl >> 32)) + ((int) (j4 >> 32)), IntOffset.m402getYimpl(m403unboximpl) + IntOffset.m402getYimpl(j4));
                return Unit.INSTANCE;
            }
        });
        return layout;
    }

    public final void setCurrentAlignment(Alignment alignment) {
        this.currentAlignment = alignment;
    }

    /* renamed from: sizeByState-Uzc_VyU  reason: not valid java name */
    public final long m3sizeByStateUzc_VyU(EnterExitState targetState, long j) {
        long j2;
        long j3;
        Intrinsics.checkNotNullParameter(targetState, "targetState");
        ChangeSize changeSize = (ChangeSize) this.expand.getValue();
        if (changeSize != null) {
            j2 = ((IntSize) changeSize.getSize().invoke(IntSize.m404boximpl(j))).m407unboximpl();
        } else {
            j2 = j;
        }
        ChangeSize changeSize2 = (ChangeSize) this.shrink.getValue();
        if (changeSize2 != null) {
            j3 = ((IntSize) changeSize2.getSize().invoke(IntSize.m404boximpl(j))).m407unboximpl();
        } else {
            j3 = j;
        }
        int ordinal = targetState.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    return j3;
                }
                throw new NoWhenBranchMatchedException();
            }
            return j;
        }
        return j2;
    }

    /* renamed from: targetOffsetByState-oFUgxo0  reason: not valid java name */
    public final long m4targetOffsetByStateoFUgxo0(EnterExitState targetState, long j) {
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        Intrinsics.checkNotNullParameter(targetState, "targetState");
        if (this.currentAlignment == null) {
            IntOffset.Companion companion = IntOffset.Companion;
            j7 = IntOffset.Zero;
            return j7;
        }
        State state = this.alignment;
        if (state.getValue() == null) {
            IntOffset.Companion companion2 = IntOffset.Companion;
            j6 = IntOffset.Zero;
            return j6;
        } else if (Intrinsics.areEqual(this.currentAlignment, state.getValue())) {
            IntOffset.Companion companion3 = IntOffset.Companion;
            j5 = IntOffset.Zero;
            return j5;
        } else {
            int ordinal = targetState.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal == 2) {
                        ChangeSize changeSize = (ChangeSize) this.shrink.getValue();
                        if (changeSize != null) {
                            long m407unboximpl = ((IntSize) changeSize.getSize().invoke(IntSize.m404boximpl(j))).m407unboximpl();
                            Object value = state.getValue();
                            Intrinsics.checkNotNull(value);
                            LayoutDirection layoutDirection = LayoutDirection.Ltr;
                            long m24alignKFBX0sM = ((BiasAlignment) ((Alignment) value)).m24alignKFBX0sM(j, m407unboximpl, layoutDirection);
                            Alignment alignment = this.currentAlignment;
                            Intrinsics.checkNotNull(alignment);
                            long m24alignKFBX0sM2 = ((BiasAlignment) alignment).m24alignKFBX0sM(j, m407unboximpl, layoutDirection);
                            return IntOffsetKt.IntOffset(((int) (m24alignKFBX0sM >> 32)) - ((int) (m24alignKFBX0sM2 >> 32)), IntOffset.m402getYimpl(m24alignKFBX0sM) - IntOffset.m402getYimpl(m24alignKFBX0sM2));
                        }
                        IntOffset.Companion companion4 = IntOffset.Companion;
                        j4 = IntOffset.Zero;
                        return j4;
                    }
                    throw new NoWhenBranchMatchedException();
                }
                IntOffset.Companion companion5 = IntOffset.Companion;
                j3 = IntOffset.Zero;
                return j3;
            }
            IntOffset.Companion companion6 = IntOffset.Companion;
            j2 = IntOffset.Zero;
            return j2;
        }
    }
}
