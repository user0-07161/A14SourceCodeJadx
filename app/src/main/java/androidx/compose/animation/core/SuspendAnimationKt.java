package androidx.compose.animation.core;

import androidx.compose.ui.MotionDurationScale;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SuspendAnimationKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e2 A[Catch: CancellationException -> 0x0049, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x0049, blocks: (B:16:0x0044, B:33:0x00d5, B:35:0x00e2), top: B:61:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0117 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object animate(final androidx.compose.animation.core.AnimationState r24, androidx.compose.animation.core.Animation r25, long r26, final kotlin.jvm.functions.Function1 r28, kotlin.coroutines.Continuation r29) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.SuspendAnimationKt.animate(androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.Animation, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void doAnimationFrameWithScale(AnimationScope animationScope, long j, float f, Animation animation, AnimationState animationState, Function1 function1) {
        boolean z;
        long startTimeNanos;
        if (f == 0.0f) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            startTimeNanos = ((TargetBasedAnimation) animation).getDurationNanos();
        } else {
            startTimeNanos = ((float) (j - animationScope.getStartTimeNanos())) / f;
        }
        animationScope.setLastFrameTimeNanos$animation_core_release(j);
        TargetBasedAnimation targetBasedAnimation = (TargetBasedAnimation) animation;
        animationScope.setValue$animation_core_release(targetBasedAnimation.getValueFromNanos(startTimeNanos));
        animationScope.setVelocityVector$animation_core_release(targetBasedAnimation.getVelocityVectorFromNanos(startTimeNanos));
        if (targetBasedAnimation.isFinishedFromNanos(startTimeNanos)) {
            animationScope.setFinishedTimeNanos$animation_core_release(animationScope.getLastFrameTimeNanos());
            animationScope.setRunning$animation_core_release();
        }
        updateState(animationScope, animationState);
        function1.invoke(animationScope);
    }

    public static final float getDurationScale(CoroutineContext coroutineContext) {
        float f;
        boolean z;
        Intrinsics.checkNotNullParameter(coroutineContext, "<this>");
        MotionDurationScale motionDurationScale = (MotionDurationScale) coroutineContext.get(MotionDurationScale.Key);
        if (motionDurationScale != null) {
            f = motionDurationScale.getScaleFactor();
        } else {
            f = 1.0f;
        }
        if (f >= 0.0f) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return f;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public static final void updateState(AnimationScope animationScope, AnimationState state) {
        Intrinsics.checkNotNullParameter(animationScope, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        state.setValue$animation_core_release(animationScope.getValue());
        AnimationVector velocityVector = state.getVelocityVector();
        AnimationVector source = animationScope.getVelocityVector();
        Intrinsics.checkNotNullParameter(velocityVector, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int size$animation_core_release = velocityVector.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            velocityVector.set$animation_core_release(source.get$animation_core_release(i), i);
        }
        state.setFinishedTimeNanos$animation_core_release(animationScope.getFinishedTimeNanos());
        state.setLastFrameTimeNanos$animation_core_release(animationScope.getLastFrameTimeNanos());
        state.setRunning$animation_core_release(animationScope.isRunning());
    }
}
