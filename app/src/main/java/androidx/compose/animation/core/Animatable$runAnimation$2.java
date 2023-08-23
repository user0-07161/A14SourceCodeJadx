package androidx.compose.animation.core;

import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
@DebugMetadata(c = "androidx.compose.animation.core.Animatable$runAnimation$2", f = "Animatable.kt", l = {305}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class Animatable$runAnimation$2 extends SuspendLambda implements Function1 {
    final /* synthetic */ Animation $animation;
    final /* synthetic */ Function1 $block;
    final /* synthetic */ Object $initialVelocity;
    final /* synthetic */ long $startTime;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Animatable this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Animatable$runAnimation$2(Animatable animatable, Object obj, Animation animation, long j, Function1 function1, Continuation continuation) {
        super(1, continuation);
        this.this$0 = animatable;
        this.$initialVelocity = obj;
        this.$animation = animation;
        this.$startTime = j;
        this.$block = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation continuation) {
        return new Animatable$runAnimation$2(this.this$0, this.$initialVelocity, this.$animation, this.$startTime, this.$block, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return ((Animatable$runAnimation$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref$BooleanRef ref$BooleanRef;
        AnimationState animationState;
        AnimationEndReason animationEndReason;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i != 0) {
                if (i == 1) {
                    ref$BooleanRef = (Ref$BooleanRef) this.L$1;
                    animationState = (AnimationState) this.L$0;
                    ResultKt.throwOnFailure(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } else {
                ResultKt.throwOnFailure(obj);
                this.this$0.getInternalState$animation_core_release().setVelocityVector$animation_core_release((AnimationVector) ((TwoWayConverterImpl) this.this$0.getTypeConverter()).getConvertToVector().invoke(this.$initialVelocity));
                this.this$0.targetValue$delegate.setValue(((TargetBasedAnimation) this.$animation).getTargetValue());
                this.this$0.isRunning$delegate.setValue(Boolean.TRUE);
                AnimationState internalState$animation_core_release = this.this$0.getInternalState$animation_core_release();
                final AnimationState animationState2 = new AnimationState(internalState$animation_core_release.getTypeConverter(), internalState$animation_core_release.getValue(), AnimationVectorsKt.copy(internalState$animation_core_release.getVelocityVector()), internalState$animation_core_release.getLastFrameTimeNanos(), Long.MIN_VALUE, internalState$animation_core_release.isRunning());
                final Ref$BooleanRef ref$BooleanRef2 = new Ref$BooleanRef();
                Animation animation = this.$animation;
                long j = this.$startTime;
                final Animatable animatable = this.this$0;
                final Function1 function1 = this.$block;
                Function1 function12 = new Function1() { // from class: androidx.compose.animation.core.Animatable$runAnimation$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        AnimationScope animate = (AnimationScope) obj2;
                        Intrinsics.checkNotNullParameter(animate, "$this$animate");
                        SuspendAnimationKt.updateState(animate, Animatable.this.getInternalState$animation_core_release());
                        Object access$clampToBounds = Animatable.access$clampToBounds(Animatable.this, animate.getValue());
                        if (!Intrinsics.areEqual(access$clampToBounds, animate.getValue())) {
                            Animatable.this.getInternalState$animation_core_release().setValue$animation_core_release(access$clampToBounds);
                            animationState2.setValue$animation_core_release(access$clampToBounds);
                            Function1 function13 = function1;
                            if (function13 != null) {
                                function13.invoke(Animatable.this);
                            }
                            animate.cancelAnimation();
                            ref$BooleanRef2.element = true;
                        } else {
                            Function1 function14 = function1;
                            if (function14 != null) {
                                function14.invoke(Animatable.this);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.L$0 = animationState2;
                this.L$1 = ref$BooleanRef2;
                this.label = 1;
                if (SuspendAnimationKt.animate(animationState2, animation, j, function12, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                ref$BooleanRef = ref$BooleanRef2;
                animationState = animationState2;
            }
            if (ref$BooleanRef.element) {
                animationEndReason = AnimationEndReason.BoundReached;
            } else {
                animationEndReason = AnimationEndReason.Finished;
            }
            Animatable.access$endAnimation(this.this$0);
            return new AnimationResult(animationState, animationEndReason);
        } catch (CancellationException e) {
            Animatable.access$endAnimation(this.this$0);
            throw e;
        }
    }
}
