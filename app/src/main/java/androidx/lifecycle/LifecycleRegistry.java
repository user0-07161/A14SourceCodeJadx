package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LifecycleRegistry extends Lifecycle {
    private final WeakReference mLifecycleOwner;
    private FastSafeIterableMap mObserverMap = new FastSafeIterableMap();
    private int mAddingObserverCounter = 0;
    private boolean mHandlingEvent = false;
    private boolean mNewEventOccurred = false;
    private ArrayList mParentStates = new ArrayList();
    private Lifecycle.State mState = Lifecycle.State.INITIALIZED;
    private final boolean mEnforceMainThread = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ObserverWithState {
        LifecycleEventObserver mLifecycleObserver;
        Lifecycle.State mState;

        ObserverWithState(LifecycleObserver lifecycleObserver, Lifecycle.State state) {
            this.mLifecycleObserver = Lifecycling.lifecycleEventObserver(lifecycleObserver);
            this.mState = state;
        }

        final void dispatchEvent(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State targetState = event.getTargetState();
            Lifecycle.State state = this.mState;
            if (targetState.compareTo(state) < 0) {
                state = targetState;
            }
            this.mState = state;
            this.mLifecycleObserver.onStateChanged(lifecycleOwner, event);
            this.mState = targetState;
        }
    }

    public LifecycleRegistry(LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = new WeakReference(lifecycleOwner);
    }

    private Lifecycle.State calculateTargetState(LifecycleObserver lifecycleObserver) {
        Lifecycle.State state;
        ArrayList arrayList;
        Map.Entry ceil = this.mObserverMap.ceil(lifecycleObserver);
        Lifecycle.State state2 = null;
        if (ceil != null) {
            state = ((ObserverWithState) ceil.getValue()).mState;
        } else {
            state = null;
        }
        if (!this.mParentStates.isEmpty()) {
            state2 = (Lifecycle.State) this.mParentStates.get(arrayList.size() - 1);
        }
        Lifecycle.State state3 = this.mState;
        if (state == null || state.compareTo(state3) >= 0) {
            state = state3;
        }
        if (state2 == null || state2.compareTo(state) >= 0) {
            return state;
        }
        return state2;
    }

    private void enforceMainThreadIfNeeded(String str) {
        if (this.mEnforceMainThread && !ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException("Method " + str + " must be called on the main thread");
        }
    }

    private void moveToState(Lifecycle.State state) {
        Lifecycle.State state2 = this.mState;
        if (state2 == state) {
            return;
        }
        Lifecycle.State state3 = Lifecycle.State.INITIALIZED;
        Lifecycle.State state4 = Lifecycle.State.DESTROYED;
        if (state2 == state3 && state == state4) {
            throw new IllegalStateException("no event down from " + this.mState + " in component " + this.mLifecycleOwner.get());
        }
        this.mState = state;
        if (!this.mHandlingEvent && this.mAddingObserverCounter == 0) {
            this.mHandlingEvent = true;
            sync();
            this.mHandlingEvent = false;
            if (this.mState == state4) {
                this.mObserverMap = new FastSafeIterableMap();
                return;
            }
            return;
        }
        this.mNewEventOccurred = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x015c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void sync() {
        /*
            Method dump skipped, instructions count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.LifecycleRegistry.sync():void");
    }

    @Override // androidx.lifecycle.Lifecycle
    public final void addObserver(LifecycleObserver lifecycleObserver) {
        LifecycleOwner lifecycleOwner;
        boolean z;
        Lifecycle.Event event;
        ArrayList arrayList;
        enforceMainThreadIfNeeded("addObserver");
        Lifecycle.State state = this.mState;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        ObserverWithState observerWithState = new ObserverWithState(lifecycleObserver, state2);
        if (((ObserverWithState) this.mObserverMap.putIfAbsent(lifecycleObserver, observerWithState)) != null || (lifecycleOwner = (LifecycleOwner) this.mLifecycleOwner.get()) == null) {
            return;
        }
        if (this.mAddingObserverCounter == 0 && !this.mHandlingEvent) {
            z = false;
        } else {
            z = true;
        }
        Lifecycle.State calculateTargetState = calculateTargetState(lifecycleObserver);
        this.mAddingObserverCounter++;
        while (observerWithState.mState.compareTo(calculateTargetState) < 0 && this.mObserverMap.contains(lifecycleObserver)) {
            this.mParentStates.add(observerWithState.mState);
            int ordinal = observerWithState.mState.ordinal();
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        event = null;
                    } else {
                        event = Lifecycle.Event.ON_RESUME;
                    }
                } else {
                    event = Lifecycle.Event.ON_START;
                }
            } else {
                event = Lifecycle.Event.ON_CREATE;
            }
            if (event != null) {
                observerWithState.dispatchEvent(lifecycleOwner, event);
                this.mParentStates.remove(arrayList.size() - 1);
                calculateTargetState = calculateTargetState(lifecycleObserver);
            } else {
                throw new IllegalStateException("no event up from " + observerWithState.mState);
            }
        }
        if (!z) {
            sync();
        }
        this.mAddingObserverCounter--;
    }

    @Override // androidx.lifecycle.Lifecycle
    public final Lifecycle.State getCurrentState() {
        return this.mState;
    }

    public final void handleLifecycleEvent(Lifecycle.Event event) {
        enforceMainThreadIfNeeded("handleLifecycleEvent");
        moveToState(event.getTargetState());
    }

    public final void markState() {
        enforceMainThreadIfNeeded("markState");
        setCurrentState();
    }

    @Override // androidx.lifecycle.Lifecycle
    public final void removeObserver(LifecycleObserver lifecycleObserver) {
        enforceMainThreadIfNeeded("removeObserver");
        this.mObserverMap.remove(lifecycleObserver);
    }

    public final void setCurrentState() {
        Lifecycle.State state = Lifecycle.State.CREATED;
        enforceMainThreadIfNeeded("setCurrentState");
        moveToState(state);
    }
}
