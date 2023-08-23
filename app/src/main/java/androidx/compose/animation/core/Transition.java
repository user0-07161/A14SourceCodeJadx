package androidx.compose.animation.core;

import androidx.compose.animation.EnterExitState;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import java.util.ListIterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Transition {
    private final String label;
    private long lastSeekedTimeNanos;
    private final MutableTransitionState transitionState;
    private final ParcelableSnapshotMutableState targetState$delegate = SnapshotStateKt.mutableStateOf$default(getCurrentState());
    private final ParcelableSnapshotMutableState segment$delegate = SnapshotStateKt.mutableStateOf$default(new SegmentImpl(getCurrentState(), getCurrentState()));
    private final ParcelableSnapshotMutableState playTimeNanos$delegate = SnapshotStateKt.mutableStateOf$default(0L);
    private final ParcelableSnapshotMutableState startTimeNanos$delegate = SnapshotStateKt.mutableStateOf$default(Long.MIN_VALUE);
    private final ParcelableSnapshotMutableState updateChildrenNeeded$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
    private final SnapshotStateList _animations = new SnapshotStateList();
    private final SnapshotStateList _transitions = new SnapshotStateList();
    private final ParcelableSnapshotMutableState isSeeking$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
    private final State totalDurationNanos$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.animation.core.Transition$totalDurationNanos$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            SnapshotStateList snapshotStateList;
            SnapshotStateList snapshotStateList2;
            snapshotStateList = Transition.this._animations;
            ListIterator listIterator = snapshotStateList.listIterator();
            long j = 0;
            while (listIterator.hasNext()) {
                j = Math.max(j, ((Transition.TransitionAnimationState) listIterator.next()).getAnimation().getDurationNanos());
            }
            snapshotStateList2 = Transition.this._transitions;
            ListIterator listIterator2 = snapshotStateList2.listIterator();
            while (listIterator2.hasNext()) {
                j = Math.max(j, ((Transition) listIterator2.next()).getTotalDurationNanos());
            }
            return Long.valueOf(j);
        }
    });

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class DeferredAnimation {
        private final ParcelableSnapshotMutableState data$delegate;
        private final String label;
        final /* synthetic */ Transition this$0;
        private final TwoWayConverter typeConverter;

        /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
        /* loaded from: classes.dex */
        public final class DeferredAnimationData implements State {
            private final TransitionAnimationState animation;
            private Function1 targetValueByState;
            final /* synthetic */ DeferredAnimation this$0;
            private Function1 transitionSpec;

            public DeferredAnimationData(DeferredAnimation deferredAnimation, TransitionAnimationState transitionAnimationState, Function1 transitionSpec, Function1 function1) {
                Intrinsics.checkNotNullParameter(transitionSpec, "transitionSpec");
                this.this$0 = deferredAnimation;
                this.animation = transitionAnimationState;
                this.transitionSpec = transitionSpec;
                this.targetValueByState = function1;
            }

            public final TransitionAnimationState getAnimation() {
                return this.animation;
            }

            public final Function1 getTargetValueByState() {
                return this.targetValueByState;
            }

            public final Function1 getTransitionSpec() {
                return this.transitionSpec;
            }

            @Override // androidx.compose.runtime.State
            public final Object getValue() {
                updateAnimationStates(this.this$0.this$0.getSegment());
                return this.animation.getValue();
            }

            public final void setTargetValueByState(Function1 function1) {
                this.targetValueByState = function1;
            }

            public final void setTransitionSpec(Function1 function1) {
                Intrinsics.checkNotNullParameter(function1, "<set-?>");
                this.transitionSpec = function1;
            }

            public final void updateAnimationStates(Segment segment) {
                Intrinsics.checkNotNullParameter(segment, "segment");
                SegmentImpl segmentImpl = (SegmentImpl) segment;
                Object invoke = this.targetValueByState.invoke(segmentImpl.getTargetState());
                boolean isSeeking = this.this$0.this$0.isSeeking();
                TransitionAnimationState transitionAnimationState = this.animation;
                if (isSeeking) {
                    transitionAnimationState.updateInitialAndTargetValue$animation_core_release(this.targetValueByState.invoke(segmentImpl.getInitialState()), invoke, (FiniteAnimationSpec) this.transitionSpec.invoke(segment));
                } else {
                    transitionAnimationState.updateTargetValue$animation_core_release(invoke, (FiniteAnimationSpec) this.transitionSpec.invoke(segment));
                }
            }
        }

        public DeferredAnimation(Transition transition, TwoWayConverter typeConverter, String label) {
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(label, "label");
            this.this$0 = transition;
            this.typeConverter = typeConverter;
            this.label = label;
            this.data$delegate = SnapshotStateKt.mutableStateOf$default(null);
        }

        public final DeferredAnimationData animate(Function1 transitionSpec, Function1 function1) {
            Intrinsics.checkNotNullParameter(transitionSpec, "transitionSpec");
            DeferredAnimationData data$animation_core_release = getData$animation_core_release();
            Transition transition = this.this$0;
            if (data$animation_core_release == null) {
                data$animation_core_release = new DeferredAnimationData(this, new TransitionAnimationState(transition, function1.invoke(transition.getCurrentState()), AnimationStateKt.createZeroVectorFrom(this.typeConverter, function1.invoke(transition.getCurrentState())), this.typeConverter, this.label), transitionSpec, function1);
                this.data$delegate.setValue(data$animation_core_release);
                transition.addAnimation$animation_core_release(data$animation_core_release.getAnimation());
            }
            data$animation_core_release.setTargetValueByState(function1);
            data$animation_core_release.setTransitionSpec(transitionSpec);
            data$animation_core_release.updateAnimationStates(transition.getSegment());
            return data$animation_core_release;
        }

        public final DeferredAnimationData getData$animation_core_release() {
            return (DeferredAnimationData) this.data$delegate.getValue();
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Segment {
        default boolean isTransitioningTo(EnterExitState enterExitState, EnterExitState enterExitState2) {
            SegmentImpl segmentImpl = (SegmentImpl) this;
            if (Intrinsics.areEqual(enterExitState, segmentImpl.getInitialState()) && Intrinsics.areEqual(enterExitState2, segmentImpl.getTargetState())) {
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class SegmentImpl implements Segment {
        private final Object initialState;
        private final Object targetState;

        public SegmentImpl(Object obj, Object obj2) {
            this.initialState = obj;
            this.targetState = obj2;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Segment) {
                SegmentImpl segmentImpl = (SegmentImpl) ((Segment) obj);
                if (Intrinsics.areEqual(this.initialState, segmentImpl.initialState)) {
                    if (Intrinsics.areEqual(this.targetState, segmentImpl.targetState)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public final Object getInitialState() {
            return this.initialState;
        }

        public final Object getTargetState() {
            return this.targetState;
        }

        public final int hashCode() {
            int i;
            int i2 = 0;
            Object obj = this.initialState;
            if (obj != null) {
                i = obj.hashCode();
            } else {
                i = 0;
            }
            int i3 = i * 31;
            Object obj2 = this.targetState;
            if (obj2 != null) {
                i2 = obj2.hashCode();
            }
            return i3 + i2;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class TransitionAnimationState implements State {
        private final ParcelableSnapshotMutableState animation$delegate;
        private final ParcelableSnapshotMutableState animationSpec$delegate;
        private final SpringSpec interruptionSpec;
        private final ParcelableSnapshotMutableState isFinished$delegate;
        private final ParcelableSnapshotMutableState needsReset$delegate;
        private final ParcelableSnapshotMutableState offsetTimeNanos$delegate;
        private final ParcelableSnapshotMutableState targetValue$delegate;
        final /* synthetic */ Transition this$0;
        private final TwoWayConverter typeConverter;
        private final ParcelableSnapshotMutableState value$delegate;
        private AnimationVector velocityVector;

        public TransitionAnimationState(Transition transition, Object obj, AnimationVector animationVector, TwoWayConverter typeConverter, String label) {
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(label, "label");
            this.this$0 = transition;
            this.typeConverter = typeConverter;
            ParcelableSnapshotMutableState mutableStateOf$default = SnapshotStateKt.mutableStateOf$default(obj);
            this.targetValue$delegate = mutableStateOf$default;
            Object obj2 = null;
            this.animationSpec$delegate = SnapshotStateKt.mutableStateOf$default(AnimationSpecKt.spring$default(0.0f, null, 7));
            this.animation$delegate = SnapshotStateKt.mutableStateOf$default(new TargetBasedAnimation(getAnimationSpec(), typeConverter, obj, mutableStateOf$default.getValue(), animationVector));
            this.isFinished$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
            this.offsetTimeNanos$delegate = SnapshotStateKt.mutableStateOf$default(0L);
            this.needsReset$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
            this.value$delegate = SnapshotStateKt.mutableStateOf$default(obj);
            this.velocityVector = animationVector;
            Float f = (Float) VisibilityThresholdsKt.getVisibilityThresholdMap().get(typeConverter);
            if (f != null) {
                float floatValue = f.floatValue();
                AnimationVector animationVector2 = (AnimationVector) ((TwoWayConverterImpl) typeConverter).getConvertToVector().invoke(obj);
                int size$animation_core_release = animationVector2.getSize$animation_core_release();
                for (int i = 0; i < size$animation_core_release; i++) {
                    animationVector2.set$animation_core_release(floatValue, i);
                }
                obj2 = ((TwoWayConverterImpl) this.typeConverter).getConvertFromVector().invoke(animationVector2);
            }
            this.interruptionSpec = AnimationSpecKt.spring$default(0.0f, obj2, 3);
        }

        static void updateAnimation$default(TransitionAnimationState transitionAnimationState, Object obj, boolean z, int i) {
            FiniteAnimationSpec animationSpec;
            if ((i & 1) != 0) {
                obj = transitionAnimationState.getValue();
            }
            Object obj2 = obj;
            if ((i & 2) != 0) {
                z = false;
            }
            if (z) {
                if (transitionAnimationState.getAnimationSpec() instanceof SpringSpec) {
                    animationSpec = transitionAnimationState.getAnimationSpec();
                } else {
                    animationSpec = transitionAnimationState.interruptionSpec;
                }
            } else {
                animationSpec = transitionAnimationState.getAnimationSpec();
            }
            transitionAnimationState.animation$delegate.setValue(new TargetBasedAnimation(animationSpec, transitionAnimationState.typeConverter, obj2, transitionAnimationState.targetValue$delegate.getValue(), transitionAnimationState.velocityVector));
            Transition.access$onChildAnimationUpdated(transitionAnimationState.this$0);
        }

        public final TargetBasedAnimation getAnimation() {
            return (TargetBasedAnimation) this.animation$delegate.getValue();
        }

        public final FiniteAnimationSpec getAnimationSpec() {
            return (FiniteAnimationSpec) this.animationSpec$delegate.getValue();
        }

        @Override // androidx.compose.runtime.State
        public final Object getValue() {
            return this.value$delegate.getValue();
        }

        public final boolean isFinished$animation_core_release() {
            return ((Boolean) this.isFinished$delegate.getValue()).booleanValue();
        }

        public final void onPlayTimeChanged$animation_core_release(long j, float f) {
            long durationNanos;
            int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
            ParcelableSnapshotMutableState parcelableSnapshotMutableState = this.offsetTimeNanos$delegate;
            if (i > 0) {
                float longValue = ((float) (j - ((Number) parcelableSnapshotMutableState.getValue()).longValue())) / f;
                if (!Float.isNaN(longValue)) {
                    durationNanos = longValue;
                } else {
                    throw new IllegalStateException(("Duration scale adjusted time is NaN. Duration scale: " + f + ",playTimeNanos: " + j + ", offsetTimeNanos: " + ((Number) parcelableSnapshotMutableState.getValue()).longValue()).toString());
                }
            } else {
                durationNanos = getAnimation().getDurationNanos();
            }
            this.value$delegate.setValue(getAnimation().getValueFromNanos(durationNanos));
            this.velocityVector = getAnimation().getVelocityVectorFromNanos(durationNanos);
            if (getAnimation().isFinishedFromNanos(durationNanos)) {
                this.isFinished$delegate.setValue(Boolean.TRUE);
                parcelableSnapshotMutableState.setValue(0L);
            }
        }

        public final void resetAnimation$animation_core_release() {
            this.needsReset$delegate.setValue(Boolean.TRUE);
        }

        public final void seekTo$animation_core_release(long j) {
            this.value$delegate.setValue(getAnimation().getValueFromNanos(j));
            this.velocityVector = getAnimation().getVelocityVectorFromNanos(j);
        }

        public final void updateInitialAndTargetValue$animation_core_release(Object obj, Object obj2, FiniteAnimationSpec animationSpec) {
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            this.targetValue$delegate.setValue(obj2);
            this.animationSpec$delegate.setValue(animationSpec);
            if (Intrinsics.areEqual(getAnimation().getInitialValue(), obj) && Intrinsics.areEqual(getAnimation().getTargetValue(), obj2)) {
                return;
            }
            updateAnimation$default(this, obj, false, 2);
        }

        public final void updateTargetValue$animation_core_release(Object obj, FiniteAnimationSpec animationSpec) {
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            ParcelableSnapshotMutableState parcelableSnapshotMutableState = this.targetValue$delegate;
            boolean areEqual = Intrinsics.areEqual(parcelableSnapshotMutableState.getValue(), obj);
            ParcelableSnapshotMutableState parcelableSnapshotMutableState2 = this.needsReset$delegate;
            if (!areEqual || ((Boolean) parcelableSnapshotMutableState2.getValue()).booleanValue()) {
                parcelableSnapshotMutableState.setValue(obj);
                this.animationSpec$delegate.setValue(animationSpec);
                updateAnimation$default(this, null, !isFinished$animation_core_release(), 1);
                Boolean bool = Boolean.FALSE;
                this.isFinished$delegate.setValue(bool);
                this.offsetTimeNanos$delegate.setValue(Long.valueOf(this.this$0.getPlayTimeNanos()));
                parcelableSnapshotMutableState2.setValue(bool);
            }
        }
    }

    public Transition(MutableTransitionState mutableTransitionState, String str) {
        this.transitionState = mutableTransitionState;
        this.label = str;
    }

    public static final void access$onChildAnimationUpdated(Transition transition) {
        transition.updateChildrenNeeded$delegate.setValue(Boolean.TRUE);
        if (transition.isSeeking()) {
            ListIterator listIterator = transition._animations.listIterator();
            long j = 0;
            while (listIterator.hasNext()) {
                TransitionAnimationState transitionAnimationState = (TransitionAnimationState) listIterator.next();
                j = Math.max(j, transitionAnimationState.getAnimation().getDurationNanos());
                transitionAnimationState.seekTo$animation_core_release(transition.lastSeekedTimeNanos);
            }
            transition.updateChildrenNeeded$delegate.setValue(Boolean.FALSE);
        }
    }

    public final void addAnimation$animation_core_release(TransitionAnimationState animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this._animations.add(animation);
    }

    public final void addTransition$animation_core_release(Transition transition) {
        Intrinsics.checkNotNullParameter(transition, "transition");
        this._transitions.add(transition);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0076, code lost:
        if (((java.lang.Boolean) r4.updateChildrenNeeded$delegate.getValue()).booleanValue() == false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void animateTo$animation_core_release(final java.lang.Object r5, androidx.compose.runtime.Composer r6, final int r7) {
        /*
            r4 = this;
            androidx.compose.runtime.ComposerImpl r6 = (androidx.compose.runtime.ComposerImpl) r6
            r0 = -1493585151(0xffffffffa6f9b301, float:-1.7326365E-15)
            r6.startRestartGroup(r0)
            r0 = r7 & 14
            if (r0 != 0) goto L17
            boolean r0 = r6.changed(r5)
            if (r0 == 0) goto L14
            r0 = 4
            goto L15
        L14:
            r0 = 2
        L15:
            r0 = r0 | r7
            goto L18
        L17:
            r0 = r7
        L18:
            r1 = r7 & 112(0x70, float:1.57E-43)
            if (r1 != 0) goto L28
            boolean r1 = r6.changed(r4)
            if (r1 == 0) goto L25
            r1 = 32
            goto L27
        L25:
            r1 = 16
        L27:
            r0 = r0 | r1
        L28:
            r1 = r0 & 91
            r2 = 18
            if (r1 != r2) goto L39
            boolean r1 = r6.getSkipping()
            if (r1 != 0) goto L35
            goto L39
        L35:
            r6.skipToGroupEnd()
            goto L9f
        L39:
            int r1 = androidx.compose.runtime.ComposerKt.$r8$clinit
            boolean r1 = r4.isSeeking()
            if (r1 != 0) goto L9f
            r1 = r0 & 14
            r0 = r0 & 112(0x70, float:1.57E-43)
            r0 = r0 | r1
            r4.updateTarget$animation_core_release(r5, r6, r0)
            java.lang.Object r0 = r4.getCurrentState()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
            if (r0 == 0) goto L78
            androidx.compose.runtime.ParcelableSnapshotMutableState r0 = r4.startTimeNanos$delegate
            java.lang.Object r0 = r0.getValue()
            java.lang.Number r0 = (java.lang.Number) r0
            long r0 = r0.longValue()
            r2 = -9223372036854775808
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L67
            r0 = 1
            goto L68
        L67:
            r0 = 0
        L68:
            if (r0 != 0) goto L78
            androidx.compose.runtime.ParcelableSnapshotMutableState r0 = r4.updateChildrenNeeded$delegate
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L9f
        L78:
            r0 = 1157296644(0x44faf204, float:2007.563)
            r6.startReplaceableGroup(r0)
            boolean r0 = r6.changed(r4)
            java.lang.Object r1 = r6.nextSlot()
            if (r0 != 0) goto L8e
            androidx.compose.runtime.Composer$Companion$Empty$1 r0 = androidx.compose.runtime.Composer.Companion.getEmpty()
            if (r1 != r0) goto L97
        L8e:
            androidx.compose.animation.core.Transition$animateTo$1$1 r1 = new androidx.compose.animation.core.Transition$animateTo$1$1
            r0 = 0
            r1.<init>(r4, r0)
            r6.updateValue(r1)
        L97:
            r6.endReplaceableGroup()
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            androidx.compose.runtime.EffectsKt.LaunchedEffect(r4, r1, r6)
        L9f:
            androidx.compose.runtime.RecomposeScopeImpl r6 = r6.endRestartGroup()
            if (r6 != 0) goto La6
            goto Lae
        La6:
            androidx.compose.animation.core.Transition$animateTo$2 r0 = new androidx.compose.animation.core.Transition$animateTo$2
            r0.<init>()
            r6.updateScope(r0)
        Lae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.Transition.animateTo$animation_core_release(java.lang.Object, androidx.compose.runtime.Composer, int):void");
    }

    public final Object getCurrentState() {
        return this.transitionState.getCurrentState();
    }

    public final String getLabel() {
        return this.label;
    }

    public final long getLastSeekedTimeNanos$animation_core_release() {
        return this.lastSeekedTimeNanos;
    }

    public final long getPlayTimeNanos() {
        return ((Number) this.playTimeNanos$delegate.getValue()).longValue();
    }

    public final Segment getSegment() {
        return (Segment) this.segment$delegate.getValue();
    }

    public final Object getTargetState() {
        return this.targetState$delegate.getValue();
    }

    public final long getTotalDurationNanos() {
        return ((Number) this.totalDurationNanos$delegate.getValue()).longValue();
    }

    public final boolean isSeeking() {
        return ((Boolean) this.isSeeking$delegate.getValue()).booleanValue();
    }

    public final void onFrame$animation_core_release(long j, float f) {
        ParcelableSnapshotMutableState parcelableSnapshotMutableState = this.startTimeNanos$delegate;
        boolean z = true;
        if (((Number) parcelableSnapshotMutableState.getValue()).longValue() == Long.MIN_VALUE) {
            parcelableSnapshotMutableState.setValue(Long.valueOf(j));
            this.transitionState.setRunning$animation_core_release(true);
        }
        this.updateChildrenNeeded$delegate.setValue(Boolean.FALSE);
        this.playTimeNanos$delegate.setValue(Long.valueOf(j - ((Number) parcelableSnapshotMutableState.getValue()).longValue()));
        ListIterator listIterator = this._animations.listIterator();
        while (listIterator.hasNext()) {
            TransitionAnimationState transitionAnimationState = (TransitionAnimationState) listIterator.next();
            if (!transitionAnimationState.isFinished$animation_core_release()) {
                transitionAnimationState.onPlayTimeChanged$animation_core_release(getPlayTimeNanos(), f);
            }
            if (!transitionAnimationState.isFinished$animation_core_release()) {
                z = false;
            }
        }
        ListIterator listIterator2 = this._transitions.listIterator();
        while (listIterator2.hasNext()) {
            Transition transition = (Transition) listIterator2.next();
            if (!Intrinsics.areEqual(transition.getTargetState(), transition.getCurrentState())) {
                transition.onFrame$animation_core_release(getPlayTimeNanos(), f);
            }
            if (!Intrinsics.areEqual(transition.getTargetState(), transition.getCurrentState())) {
                z = false;
            }
        }
        if (z) {
            onTransitionEnd$animation_core_release();
        }
    }

    public final void onTransitionEnd$animation_core_release() {
        this.startTimeNanos$delegate.setValue(Long.MIN_VALUE);
        Object targetState = getTargetState();
        MutableTransitionState mutableTransitionState = this.transitionState;
        mutableTransitionState.setCurrentState$animation_core_release(targetState);
        this.playTimeNanos$delegate.setValue(0L);
        mutableTransitionState.setRunning$animation_core_release(false);
    }

    public final void removeAnimation$animation_core_release(TransitionAnimationState animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this._animations.remove(animation);
    }

    public final void removeTransition$animation_core_release(Transition transition) {
        Intrinsics.checkNotNullParameter(transition, "transition");
        this._transitions.remove(transition);
    }

    public final void seek(Object obj, Object obj2, long j) {
        this.startTimeNanos$delegate.setValue(Long.MIN_VALUE);
        MutableTransitionState mutableTransitionState = this.transitionState;
        mutableTransitionState.setRunning$animation_core_release(false);
        if (!isSeeking() || !Intrinsics.areEqual(getCurrentState(), obj) || !Intrinsics.areEqual(getTargetState(), obj2)) {
            mutableTransitionState.setCurrentState$animation_core_release(obj);
            this.targetState$delegate.setValue(obj2);
            setSeeking$animation_core_release(true);
            this.segment$delegate.setValue(new SegmentImpl(obj, obj2));
        }
        ListIterator listIterator = this._transitions.listIterator();
        while (listIterator.hasNext()) {
            Transition transition = (Transition) listIterator.next();
            Intrinsics.checkNotNull(transition, "null cannot be cast to non-null type androidx.compose.animation.core.Transition<kotlin.Any>");
            if (transition.isSeeking()) {
                transition.seek(transition.getCurrentState(), transition.getTargetState(), j);
            }
        }
        ListIterator listIterator2 = this._animations.listIterator();
        while (listIterator2.hasNext()) {
            ((TransitionAnimationState) listIterator2.next()).seekTo$animation_core_release(j);
        }
        this.lastSeekedTimeNanos = j;
    }

    public final void setSeeking$animation_core_release(boolean z) {
        this.isSeeking$delegate.setValue(Boolean.valueOf(z));
    }

    public final void updateTarget$animation_core_release(final Object obj, Composer composer, final int i) {
        int i2;
        boolean z;
        int i3;
        int i4;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-583974681);
        if ((i & 14) == 0) {
            if (composerImpl.changed(obj)) {
                i4 = 4;
            } else {
                i4 = 2;
            }
            i2 = i4 | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            if (composerImpl.changed(this)) {
                i3 = 32;
            } else {
                i3 = 16;
            }
            i2 |= i3;
        }
        if ((i2 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            int i5 = ComposerKt.$r8$clinit;
            if (!isSeeking() && !Intrinsics.areEqual(getTargetState(), obj)) {
                this.segment$delegate.setValue(new SegmentImpl(getTargetState(), obj));
                this.transitionState.setCurrentState$animation_core_release(getTargetState());
                this.targetState$delegate.setValue(obj);
                if (((Number) this.startTimeNanos$delegate.getValue()).longValue() != Long.MIN_VALUE) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    this.updateChildrenNeeded$delegate.setValue(Boolean.TRUE);
                }
                ListIterator listIterator = this._animations.listIterator();
                while (listIterator.hasNext()) {
                    ((TransitionAnimationState) listIterator.next()).resetAnimation$animation_core_release();
                }
            }
            int i6 = ComposerKt.$r8$clinit;
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.animation.core.Transition$updateTarget$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    Transition.this.updateTarget$animation_core_release(obj, (Composer) obj2, i | 1);
                    return Unit.INSTANCE;
                }
            });
        }
    }
}
