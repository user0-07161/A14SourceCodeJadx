package androidx.compose.animation.core;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Animatable {
    private final SpringSpec defaultSpringSpec;
    private final AnimationState internalState;
    private final ParcelableSnapshotMutableState isRunning$delegate;
    private AnimationVector lowerBoundVector;
    private final MutatorMutex mutatorMutex;
    private final AnimationVector negativeInfinityBounds;
    private final AnimationVector positiveInfinityBounds;
    private final ParcelableSnapshotMutableState targetValue$delegate;
    private final TwoWayConverter typeConverter;
    private AnimationVector upperBoundVector;
    private final Object visibilityThreshold;

    public Animatable(Object obj, TwoWayConverter twoWayConverter, Object obj2, String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        this.typeConverter = twoWayConverter;
        this.visibilityThreshold = obj2;
        this.internalState = new AnimationState(twoWayConverter, obj, null, Long.MIN_VALUE, Long.MIN_VALUE, false);
        this.isRunning$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
        this.targetValue$delegate = SnapshotStateKt.mutableStateOf$default(obj);
        this.mutatorMutex = new MutatorMutex();
        this.defaultSpringSpec = new SpringSpec(1.0f, 1500.0f, obj2);
        AnimationVector animationVector = (AnimationVector) ((TwoWayConverterImpl) twoWayConverter).getConvertToVector().invoke(obj);
        int size$animation_core_release = animationVector.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            animationVector.set$animation_core_release(Float.NEGATIVE_INFINITY, i);
        }
        this.negativeInfinityBounds = animationVector;
        AnimationVector animationVector2 = (AnimationVector) ((TwoWayConverterImpl) this.typeConverter).getConvertToVector().invoke(obj);
        int size$animation_core_release2 = animationVector2.getSize$animation_core_release();
        for (int i2 = 0; i2 < size$animation_core_release2; i2++) {
            animationVector2.set$animation_core_release(Float.POSITIVE_INFINITY, i2);
        }
        this.positiveInfinityBounds = animationVector2;
        this.lowerBoundVector = animationVector;
        this.upperBoundVector = animationVector2;
    }

    public static final Object access$clampToBounds(Animatable animatable, Object obj) {
        if (!Intrinsics.areEqual(animatable.lowerBoundVector, animatable.negativeInfinityBounds) || !Intrinsics.areEqual(animatable.upperBoundVector, animatable.positiveInfinityBounds)) {
            TwoWayConverterImpl twoWayConverterImpl = (TwoWayConverterImpl) animatable.typeConverter;
            AnimationVector animationVector = (AnimationVector) twoWayConverterImpl.getConvertToVector().invoke(obj);
            int size$animation_core_release = animationVector.getSize$animation_core_release();
            boolean z = false;
            for (int i = 0; i < size$animation_core_release; i++) {
                if (animationVector.get$animation_core_release(i) < animatable.lowerBoundVector.get$animation_core_release(i) || animationVector.get$animation_core_release(i) > animatable.upperBoundVector.get$animation_core_release(i)) {
                    animationVector.set$animation_core_release(RangesKt.coerceIn(animationVector.get$animation_core_release(i), animatable.lowerBoundVector.get$animation_core_release(i), animatable.upperBoundVector.get$animation_core_release(i)), i);
                    z = true;
                }
            }
            if (z) {
                return twoWayConverterImpl.getConvertFromVector().invoke(animationVector);
            }
            return obj;
        }
        return obj;
    }

    public static final void access$endAnimation(Animatable animatable) {
        AnimationState animationState = animatable.internalState;
        animationState.getVelocityVector().reset$animation_core_release();
        animationState.setLastFrameTimeNanos$animation_core_release(Long.MIN_VALUE);
        animatable.isRunning$delegate.setValue(Boolean.FALSE);
    }

    public static Object animateTo$default(Animatable animatable, Object obj, AnimationSpec animationSpec, Continuation continuation) {
        Object invoke = ((TwoWayConverterImpl) animatable.typeConverter).getConvertFromVector().invoke(animatable.internalState.getVelocityVector());
        Object value = animatable.getValue();
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        TwoWayConverter typeConverter = animatable.typeConverter;
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        Animatable$runAnimation$2 animatable$runAnimation$2 = new Animatable$runAnimation$2(animatable, invoke, new TargetBasedAnimation(animationSpec, typeConverter, value, obj, (AnimationVector) ((TwoWayConverterImpl) typeConverter).getConvertToVector().invoke(invoke)), animatable.internalState.getLastFrameTimeNanos(), null, null);
        MutatePriority mutatePriority = MutatePriority.Default;
        MutatorMutex mutatorMutex = animatable.mutatorMutex;
        mutatorMutex.getClass();
        return CoroutineScopeKt.coroutineScope(new MutatorMutex$mutate$2(mutatePriority, mutatorMutex, animatable$runAnimation$2, null), continuation);
    }

    public final AnimationState asState() {
        return this.internalState;
    }

    public final AnimationState getInternalState$animation_core_release() {
        return this.internalState;
    }

    public final Object getTargetValue() {
        return this.targetValue$delegate.getValue();
    }

    public final TwoWayConverter getTypeConverter() {
        return this.typeConverter;
    }

    public final Object getValue() {
        return this.internalState.getValue();
    }
}
