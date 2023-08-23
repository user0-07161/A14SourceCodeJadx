package androidx.compose.runtime;

import android.util.Log;
import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.compose.runtime.snapshots.SnapshotKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ExceptionsKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Recomposer extends CompositionContext {
    public static final Companion Companion = new Companion();
    private static final AtomicReference _hotReloadEnabled;
    private static final MutableStateFlow _runningRecomposers;
    private final MutableStateFlow _state;
    private final BroadcastFrameClock broadcastFrameClock;
    private long changeCount;
    private Throwable closeCause;
    private final List compositionInvalidations;
    private final Map compositionValueStatesAvailable;
    private final List compositionValuesAwaitingInsert;
    private final Map compositionValuesRemoved;
    private final List compositionsAwaitingApply;
    private final CoroutineContext effectCoroutineContext;
    private final JobImpl effectJob;
    private Companion errorState;
    private List failedCompositions;
    private final List knownCompositions;
    private final Companion recomposerInfo;
    private Job runnerJob;
    private Set snapshotInvalidations;
    private final Object stateLock;
    private CancellableContinuation workContinuation;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        public Companion(Exception exc) {
        }

        public static final void access$removeRunning(Companion companion) {
            PersistentOrderedSet persistentOrderedSet;
            PersistentOrderedSet remove;
            Companion companion2 = Recomposer.Companion;
            do {
                persistentOrderedSet = (PersistentOrderedSet) ((PersistentSet) Recomposer._runningRecomposers.getValue());
                remove = persistentOrderedSet.remove((Object) companion);
                if (persistentOrderedSet == remove) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.compareAndSet(persistentOrderedSet, remove));
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public enum State {
        ShutDown,
        ShuttingDown,
        Inactive,
        InactivePendingWork,
        Idle,
        PendingWork
    }

    static {
        PersistentOrderedSet persistentOrderedSet;
        persistentOrderedSet = PersistentOrderedSet.EMPTY;
        _runningRecomposers = StateFlowKt.MutableStateFlow(persistentOrderedSet);
        _hotReloadEnabled = new AtomicReference(Boolean.FALSE);
    }

    public Recomposer(CoroutineContext effectCoroutineContext) {
        Intrinsics.checkNotNullParameter(effectCoroutineContext, "effectCoroutineContext");
        BroadcastFrameClock broadcastFrameClock = new BroadcastFrameClock(new Function0() { // from class: androidx.compose.runtime.Recomposer$broadcastFrameClock$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Object obj;
                CancellableContinuation deriveStateLocked;
                MutableStateFlow mutableStateFlow;
                Throwable th;
                obj = Recomposer.this.stateLock;
                Recomposer recomposer = Recomposer.this;
                synchronized (obj) {
                    deriveStateLocked = recomposer.deriveStateLocked();
                    mutableStateFlow = recomposer._state;
                    if (((Recomposer.State) mutableStateFlow.getValue()).compareTo(Recomposer.State.ShuttingDown) <= 0) {
                        th = recomposer.closeCause;
                        CancellationException cancellationException = new CancellationException("Recomposer shutdown; frame clock awaiter will never resume");
                        cancellationException.initCause(th);
                        throw cancellationException;
                    }
                }
                if (deriveStateLocked != null) {
                    ((CancellableContinuationImpl) deriveStateLocked).resumeWith(Unit.INSTANCE);
                }
                return Unit.INSTANCE;
            }
        });
        this.broadcastFrameClock = broadcastFrameClock;
        this.stateLock = new Object();
        this.knownCompositions = new ArrayList();
        this.snapshotInvalidations = new LinkedHashSet();
        this.compositionInvalidations = new ArrayList();
        this.compositionsAwaitingApply = new ArrayList();
        this.compositionValuesAwaitingInsert = new ArrayList();
        this.compositionValuesRemoved = new LinkedHashMap();
        this.compositionValueStatesAvailable = new LinkedHashMap();
        this._state = StateFlowKt.MutableStateFlow(State.Inactive);
        JobImpl jobImpl = new JobImpl((Job) effectCoroutineContext.get(Job.Key));
        jobImpl.invokeOnCompletion(false, true, new Function1() { // from class: androidx.compose.runtime.Recomposer$effectJob$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Object obj2;
                Job job;
                MutableStateFlow mutableStateFlow;
                MutableStateFlow mutableStateFlow2;
                final Throwable th = (Throwable) obj;
                CancellationException cancellationException = new CancellationException("Recomposer effect job completed");
                cancellationException.initCause(th);
                obj2 = Recomposer.this.stateLock;
                final Recomposer recomposer = Recomposer.this;
                synchronized (obj2) {
                    job = recomposer.runnerJob;
                    if (job != null) {
                        mutableStateFlow2 = recomposer._state;
                        mutableStateFlow2.setValue(Recomposer.State.ShuttingDown);
                        job.cancel(cancellationException);
                        recomposer.workContinuation = null;
                        ((JobSupport) job).invokeOnCompletion(false, true, new Function1() { // from class: androidx.compose.runtime.Recomposer$effectJob$1$1$1$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj3) {
                                Object obj4;
                                MutableStateFlow mutableStateFlow3;
                                Throwable th2 = (Throwable) obj3;
                                obj4 = Recomposer.this.stateLock;
                                Recomposer recomposer2 = Recomposer.this;
                                Throwable th3 = th;
                                synchronized (obj4) {
                                    if (th3 != null) {
                                        if (th2 != null) {
                                            if (!(!(th2 instanceof CancellationException))) {
                                                th2 = null;
                                            }
                                            if (th2 != null) {
                                                ExceptionsKt.addSuppressed(th3, th2);
                                            }
                                        }
                                    } else {
                                        th3 = null;
                                    }
                                    recomposer2.closeCause = th3;
                                    mutableStateFlow3 = recomposer2._state;
                                    mutableStateFlow3.setValue(Recomposer.State.ShutDown);
                                }
                                return Unit.INSTANCE;
                            }
                        });
                    } else {
                        recomposer.closeCause = cancellationException;
                        mutableStateFlow = recomposer._state;
                        mutableStateFlow.setValue(Recomposer.State.ShutDown);
                    }
                }
                return Unit.INSTANCE;
            }
        });
        this.effectJob = jobImpl;
        this.effectCoroutineContext = effectCoroutineContext.plus(broadcastFrameClock).plus(jobImpl);
        this.recomposerInfo = new Companion();
    }

    public static final Object access$awaitWorkAvailable(Recomposer recomposer, Continuation continuation) {
        if (!recomposer.getHasSchedulingWork()) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt.intercepted(continuation));
            cancellableContinuationImpl.initCancellability();
            synchronized (recomposer.stateLock) {
                if (recomposer.getHasSchedulingWork()) {
                    cancellableContinuationImpl.resumeWith(Unit.INSTANCE);
                } else {
                    recomposer.workContinuation = cancellableContinuationImpl;
                }
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result != CoroutineSingletons.COROUTINE_SUSPENDED) {
                return Unit.INSTANCE;
            }
            return result;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void access$discardUnusedValues(Recomposer recomposer) {
        int i;
        EmptyList emptyList;
        synchronized (recomposer.stateLock) {
            if (!recomposer.compositionValuesRemoved.isEmpty()) {
                Collection<Iterable> values = ((LinkedHashMap) recomposer.compositionValuesRemoved).values();
                Intrinsics.checkNotNullParameter(values, "<this>");
                ArrayList arrayList = new ArrayList();
                for (Iterable iterable : values) {
                    CollectionsKt.addAll(iterable, arrayList);
                }
                ((LinkedHashMap) recomposer.compositionValuesRemoved).clear();
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    MovableContentStateReference movableContentStateReference = (MovableContentStateReference) arrayList.get(i2);
                    arrayList2.add(new Pair(movableContentStateReference, ((LinkedHashMap) recomposer.compositionValueStatesAvailable).get(movableContentStateReference)));
                }
                ((LinkedHashMap) recomposer.compositionValueStatesAvailable).clear();
                emptyList = arrayList2;
            } else {
                emptyList = EmptyList.INSTANCE;
            }
        }
        int size2 = emptyList.size();
        for (i = 0; i < size2; i++) {
            Pair pair = (Pair) emptyList.get(i);
            MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) pair.component1();
            MovableContentState movableContentState = (MovableContentState) pair.component2();
            if (movableContentState != null) {
                ((CompositionImpl) movableContentStateReference2.getComposition$runtime_release()).disposeUnusedMovableContent(movableContentState);
            }
        }
    }

    public static final boolean access$getHasFrameWorkLocked(Recomposer recomposer) {
        if ((!((ArrayList) recomposer.compositionInvalidations).isEmpty()) || recomposer.broadcastFrameClock.getHasAwaiters()) {
            return true;
        }
        return false;
    }

    public static final void access$getShouldKeepRecomposing(Recomposer recomposer) {
        synchronized (recomposer.stateLock) {
        }
    }

    public static final CompositionImpl access$performRecompose(Recomposer recomposer, ControlledComposition controlledComposition, IdentityArraySet identityArraySet) {
        MutableSnapshot mutableSnapshot;
        MutableSnapshot takeNestedMutableSnapshot;
        CompositionImpl compositionImpl = (CompositionImpl) controlledComposition;
        if (compositionImpl.isComposing() || compositionImpl.isDisposed()) {
            return null;
        }
        Recomposer$readObserverOf$1 recomposer$readObserverOf$1 = new Recomposer$readObserverOf$1(compositionImpl);
        Recomposer$writeObserverOf$1 recomposer$writeObserverOf$1 = new Recomposer$writeObserverOf$1(compositionImpl, identityArraySet);
        Snapshot currentSnapshot = SnapshotKt.currentSnapshot();
        if (currentSnapshot instanceof MutableSnapshot) {
            mutableSnapshot = (MutableSnapshot) currentSnapshot;
        } else {
            mutableSnapshot = null;
        }
        if (mutableSnapshot != null && (takeNestedMutableSnapshot = mutableSnapshot.takeNestedMutableSnapshot(recomposer$readObserverOf$1, recomposer$writeObserverOf$1)) != null) {
            try {
                Snapshot makeCurrent = takeNestedMutableSnapshot.makeCurrent();
                boolean z = true;
                if (!identityArraySet.isNotEmpty()) {
                    z = false;
                }
                if (z) {
                    compositionImpl.prepareCompose(new Recomposer$performRecompose$1$1(compositionImpl, identityArraySet));
                }
                boolean recompose = compositionImpl.recompose();
                Snapshot.restoreCurrent(makeCurrent);
                if (!recompose) {
                    compositionImpl = null;
                }
                return compositionImpl;
            } finally {
                applyAndCheck(takeNestedMutableSnapshot);
            }
        }
        throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot".toString());
    }

    public static final void access$recordComposerModificationsLocked(Recomposer recomposer) {
        Set set = recomposer.snapshotInvalidations;
        if (!set.isEmpty()) {
            ArrayList arrayList = (ArrayList) recomposer.knownCompositions;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((CompositionImpl) ((ControlledComposition) arrayList.get(i))).recordModificationsOf(set);
                if (((State) recomposer._state.getValue()).compareTo(State.ShuttingDown) <= 0) {
                    break;
                }
            }
            recomposer.snapshotInvalidations = new LinkedHashSet();
            if (recomposer.deriveStateLocked() != null) {
                throw new IllegalStateException("called outside of runRecomposeAndApplyChanges".toString());
            }
        }
    }

    public static final void access$registerRunnerJob(Recomposer recomposer, Job job) {
        synchronized (recomposer.stateLock) {
            Throwable th = recomposer.closeCause;
            if (th == null) {
                if (((State) recomposer._state.getValue()).compareTo(State.ShuttingDown) > 0) {
                    if (recomposer.runnerJob == null) {
                        recomposer.runnerJob = job;
                        recomposer.deriveStateLocked();
                    } else {
                        throw new IllegalStateException("Recomposer already running".toString());
                    }
                } else {
                    throw new IllegalStateException("Recomposer shut down".toString());
                }
            } else {
                throw th;
            }
        }
    }

    private static void applyAndCheck(MutableSnapshot mutableSnapshot) {
        try {
            if (!(mutableSnapshot.apply() instanceof SnapshotApplyResult.Failure)) {
                return;
            }
            throw new IllegalStateException("Unsupported concurrent change during composition. A state object was modified by composition as well as being modified outside composition.".toString());
        } finally {
            mutableSnapshot.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CancellableContinuation deriveStateLocked() {
        MutableStateFlow mutableStateFlow = this._state;
        int compareTo = ((State) mutableStateFlow.getValue()).compareTo(State.ShuttingDown);
        List list = this.compositionValuesAwaitingInsert;
        List list2 = this.compositionsAwaitingApply;
        List list3 = this.compositionInvalidations;
        if (compareTo <= 0) {
            ((ArrayList) this.knownCompositions).clear();
            this.snapshotInvalidations = new LinkedHashSet();
            ((ArrayList) list3).clear();
            ((ArrayList) list2).clear();
            ((ArrayList) list).clear();
            this.failedCompositions = null;
            CancellableContinuation cancellableContinuation = this.workContinuation;
            if (cancellableContinuation != null) {
                ((CancellableContinuationImpl) cancellableContinuation).cancel(null);
            }
            this.workContinuation = null;
            this.errorState = null;
            return null;
        }
        Companion companion = this.errorState;
        State state = State.PendingWork;
        State state2 = State.Inactive;
        if (companion == null) {
            Job job = this.runnerJob;
            BroadcastFrameClock broadcastFrameClock = this.broadcastFrameClock;
            if (job == null) {
                this.snapshotInvalidations = new LinkedHashSet();
                ((ArrayList) list3).clear();
                if (broadcastFrameClock.getHasAwaiters()) {
                    state2 = State.InactivePendingWork;
                }
            } else {
                state2 = ((list3.isEmpty() ^ true) || (this.snapshotInvalidations.isEmpty() ^ true) || (list2.isEmpty() ^ true) || (list.isEmpty() ^ true) || broadcastFrameClock.getHasAwaiters()) ? state : State.Idle;
            }
        }
        mutableStateFlow.setValue(state2);
        if (state2 != state) {
            return null;
        }
        CancellableContinuation cancellableContinuation2 = this.workContinuation;
        this.workContinuation = null;
        return cancellableContinuation2;
    }

    private final boolean getHasSchedulingWork() {
        boolean z;
        synchronized (this.stateLock) {
            z = true;
            if (!(!this.snapshotInvalidations.isEmpty()) && !(!this.compositionInvalidations.isEmpty())) {
                if (!this.broadcastFrameClock.getHasAwaiters()) {
                    z = false;
                }
            }
        }
        return z;
    }

    private final void performInitialMovableContentInserts(ControlledComposition controlledComposition) {
        synchronized (this.stateLock) {
            ArrayList arrayList = (ArrayList) this.compositionValuesAwaitingInsert;
            int size = arrayList.size();
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                } else if (Intrinsics.areEqual(((MovableContentStateReference) arrayList.get(i)).getComposition$runtime_release(), controlledComposition)) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                return;
            }
            ArrayList arrayList2 = new ArrayList();
            performInitialMovableContentInserts$fillToInsert(arrayList2, this, controlledComposition);
            while (!arrayList2.isEmpty()) {
                performInsertValues(arrayList2, null);
                performInitialMovableContentInserts$fillToInsert(arrayList2, this, controlledComposition);
            }
        }
    }

    private static final void performInitialMovableContentInserts$fillToInsert(List list, Recomposer recomposer, ControlledComposition controlledComposition) {
        ArrayList arrayList = (ArrayList) list;
        arrayList.clear();
        synchronized (recomposer.stateLock) {
            Iterator it = ((ArrayList) recomposer.compositionValuesAwaitingInsert).iterator();
            while (it.hasNext()) {
                MovableContentStateReference movableContentStateReference = (MovableContentStateReference) it.next();
                if (Intrinsics.areEqual(movableContentStateReference.getComposition$runtime_release(), controlledComposition)) {
                    arrayList.add(movableContentStateReference);
                    it.remove();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List performInsertValues(List list, IdentityArraySet identityArraySet) {
        MutableSnapshot mutableSnapshot;
        MutableSnapshot takeNestedMutableSnapshot;
        ArrayList arrayList;
        Object obj;
        HashMap hashMap = new HashMap(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj2 = list.get(i);
            ControlledComposition composition$runtime_release = ((MovableContentStateReference) obj2).getComposition$runtime_release();
            Object obj3 = hashMap.get(composition$runtime_release);
            if (obj3 == null) {
                obj3 = new ArrayList();
                hashMap.put(composition$runtime_release, obj3);
            }
            ((ArrayList) obj3).add(obj2);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            List list2 = (List) entry.getValue();
            CompositionImpl compositionImpl = (CompositionImpl) ((ControlledComposition) entry.getKey());
            ComposerKt.runtimeCheck(!compositionImpl.isComposing());
            Recomposer$readObserverOf$1 recomposer$readObserverOf$1 = new Recomposer$readObserverOf$1(compositionImpl);
            Recomposer$writeObserverOf$1 recomposer$writeObserverOf$1 = new Recomposer$writeObserverOf$1(compositionImpl, identityArraySet);
            Snapshot currentSnapshot = SnapshotKt.currentSnapshot();
            Object obj4 = null;
            if (currentSnapshot instanceof MutableSnapshot) {
                mutableSnapshot = (MutableSnapshot) currentSnapshot;
            } else {
                mutableSnapshot = null;
            }
            if (mutableSnapshot != null && (takeNestedMutableSnapshot = mutableSnapshot.takeNestedMutableSnapshot(recomposer$readObserverOf$1, recomposer$writeObserverOf$1)) != null) {
                try {
                    Snapshot makeCurrent = takeNestedMutableSnapshot.makeCurrent();
                    synchronized (this.stateLock) {
                        arrayList = new ArrayList(list2.size());
                        int size2 = list2.size();
                        int i2 = 0;
                        while (i2 < size2) {
                            MovableContentStateReference movableContentStateReference = (MovableContentStateReference) list2.get(i2);
                            Map map = this.compositionValuesRemoved;
                            movableContentStateReference.getClass();
                            Intrinsics.checkNotNullParameter(map, "<this>");
                            List list3 = (List) ((LinkedHashMap) map).get(obj4);
                            if (list3 != null) {
                                if (!list3.isEmpty()) {
                                    Object remove = list3.remove(0);
                                    if (list3.isEmpty()) {
                                        map.remove(null);
                                    }
                                    obj = remove;
                                } else {
                                    throw new NoSuchElementException("List is empty.");
                                }
                            } else {
                                obj = obj4;
                            }
                            arrayList.add(new Pair(movableContentStateReference, obj));
                            i2++;
                            obj4 = null;
                        }
                    }
                    compositionImpl.insertMovableContent(arrayList);
                    Snapshot.restoreCurrent(makeCurrent);
                } finally {
                    applyAndCheck(takeNestedMutableSnapshot);
                }
            } else {
                throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot".toString());
            }
        }
        return CollectionsKt.toList(hashMap.keySet());
    }

    private final void processCompositionError(Exception exc, ControlledComposition controlledComposition, boolean z) {
        Object obj = _hotReloadEnabled.get();
        Intrinsics.checkNotNullExpressionValue(obj, "_hotReloadEnabled.get()");
        if (((Boolean) obj).booleanValue() && !(exc instanceof ComposeRuntimeError)) {
            synchronized (this.stateLock) {
                int i = ActualAndroid_androidKt.$r8$clinit;
                Log.e("ComposeInternal", "Error was captured in composition while live edit was enabled.", exc);
                ((ArrayList) this.compositionsAwaitingApply).clear();
                ((ArrayList) this.compositionInvalidations).clear();
                this.snapshotInvalidations = new LinkedHashSet();
                ((ArrayList) this.compositionValuesAwaitingInsert).clear();
                ((LinkedHashMap) this.compositionValuesRemoved).clear();
                ((LinkedHashMap) this.compositionValueStatesAvailable).clear();
                this.errorState = new Companion(exc);
                if (controlledComposition != null) {
                    List list = this.failedCompositions;
                    if (list == null) {
                        list = new ArrayList();
                        this.failedCompositions = list;
                    }
                    if (!list.contains(controlledComposition)) {
                        list.add(controlledComposition);
                    }
                    ((ArrayList) this.knownCompositions).remove(controlledComposition);
                }
                deriveStateLocked();
            }
            return;
        }
        throw exc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void processCompositionError$default(Recomposer recomposer, Exception exc, boolean z, int i) {
        if ((i & 4) != 0) {
            z = false;
        }
        recomposer.processCompositionError(exc, null, z);
    }

    public final void cancel() {
        synchronized (this.stateLock) {
            if (((State) this._state.getValue()).compareTo(State.Idle) >= 0) {
                this._state.setValue(State.ShuttingDown);
            }
        }
        this.effectJob.cancel(null);
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void composeInitial$runtime_release(ControlledComposition composition, ComposableLambdaImpl composableLambdaImpl) {
        MutableSnapshot takeNestedMutableSnapshot;
        Intrinsics.checkNotNullParameter(composition, "composition");
        CompositionImpl compositionImpl = (CompositionImpl) composition;
        boolean isComposing = compositionImpl.isComposing();
        try {
            Recomposer$readObserverOf$1 recomposer$readObserverOf$1 = new Recomposer$readObserverOf$1(composition);
            MutableSnapshot mutableSnapshot = null;
            Recomposer$writeObserverOf$1 recomposer$writeObserverOf$1 = new Recomposer$writeObserverOf$1(composition, null);
            Snapshot currentSnapshot = SnapshotKt.currentSnapshot();
            if (currentSnapshot instanceof MutableSnapshot) {
                mutableSnapshot = (MutableSnapshot) currentSnapshot;
            }
            if (mutableSnapshot != null && (takeNestedMutableSnapshot = mutableSnapshot.takeNestedMutableSnapshot(recomposer$readObserverOf$1, recomposer$writeObserverOf$1)) != null) {
                Snapshot makeCurrent = takeNestedMutableSnapshot.makeCurrent();
                try {
                    compositionImpl.composeContent(composableLambdaImpl);
                    applyAndCheck(takeNestedMutableSnapshot);
                    if (!isComposing) {
                        SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
                    }
                    synchronized (this.stateLock) {
                        if (((State) this._state.getValue()).compareTo(State.ShuttingDown) > 0 && !((ArrayList) this.knownCompositions).contains(composition)) {
                            ((ArrayList) this.knownCompositions).add(composition);
                        }
                    }
                    try {
                        performInitialMovableContentInserts(composition);
                        try {
                            compositionImpl.applyChanges();
                            compositionImpl.applyLateChanges();
                            if (!isComposing) {
                                SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            processCompositionError$default(this, e, false, 6);
                            return;
                        }
                    } catch (Exception e2) {
                        processCompositionError(e2, composition, true);
                        return;
                    }
                } finally {
                    Snapshot.restoreCurrent(makeCurrent);
                }
            }
            throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot".toString());
        } catch (Exception e3) {
            processCompositionError(e3, composition, true);
        }
    }

    public final long getChangeCount() {
        return this.changeCount;
    }

    public final StateFlow getCurrentState() {
        return this._state;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final CoroutineContext getEffectCoroutineContext$runtime_release() {
        return this.effectCoroutineContext;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void invalidate$runtime_release(ControlledComposition composition) {
        CancellableContinuation cancellableContinuation;
        Intrinsics.checkNotNullParameter(composition, "composition");
        synchronized (this.stateLock) {
            if (!((ArrayList) this.compositionInvalidations).contains(composition)) {
                this.compositionInvalidations.add(composition);
                cancellableContinuation = deriveStateLocked();
            } else {
                cancellableContinuation = null;
            }
        }
        if (cancellableContinuation != null) {
            cancellableContinuation.resumeWith(Unit.INSTANCE);
        }
    }

    public final Object join(Continuation continuation) {
        Object first = FlowKt.first(this._state, new Recomposer$join$2(null), continuation);
        if (first == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return first;
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final MovableContentState movableContentStateResolve$runtime_release(MovableContentStateReference reference) {
        MovableContentState movableContentState;
        Intrinsics.checkNotNullParameter(reference, "reference");
        synchronized (this.stateLock) {
            movableContentState = (MovableContentState) this.compositionValueStatesAvailable.remove(reference);
        }
        return movableContentState;
    }

    public final Object runRecomposeAndApplyChanges(Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.broadcastFrameClock, new Recomposer$recompositionRunner$2(this, new Recomposer$runRecomposeAndApplyChanges$2(this, null), MonotonicFrameClockKt.getMonotonicFrameClock(continuation.getContext()), null), continuation);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Unit unit = Unit.INSTANCE;
        if (withContext != coroutineSingletons) {
            withContext = unit;
        }
        if (withContext == coroutineSingletons) {
            return withContext;
        }
        return unit;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void unregisterComposition$runtime_release(ControlledComposition composition) {
        Intrinsics.checkNotNullParameter(composition, "composition");
        synchronized (this.stateLock) {
            this.knownCompositions.remove(composition);
            this.compositionInvalidations.remove(composition);
            this.compositionsAwaitingApply.remove(composition);
        }
    }
}
