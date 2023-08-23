package androidx.compose.runtime;

import android.os.Trace;
import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.IdentityArraySet$iterator$1;
import androidx.compose.runtime.collection.IdentityScopeMap;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.node.UiApplier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class CompositionImpl implements ControlledComposition {
    private final HashSet abandonSet;
    private final Applier applier;
    private final List changes;
    private final ComposerImpl composer;
    private final HashSet conditionallyInvalidatedScopes;
    private final IdentityScopeMap derivedStates;
    private boolean disposed;
    private CompositionImpl invalidationDelegate;
    private int invalidationDelegateGroup;
    private IdentityArrayMap invalidations;
    private final List lateChanges;
    private final IdentityScopeMap observations;
    private final IdentityScopeMap observationsProcessed;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final SlotTable slotTable;
    private final AtomicReference pendingModifications = new AtomicReference(null);
    private final Object lock = new Object();

    public CompositionImpl(CompositionContext compositionContext, UiApplier uiApplier) {
        this.parent = compositionContext;
        this.applier = uiApplier;
        HashSet hashSet = new HashSet();
        this.abandonSet = hashSet;
        SlotTable slotTable = new SlotTable();
        this.slotTable = slotTable;
        this.observations = new IdentityScopeMap();
        this.conditionallyInvalidatedScopes = new HashSet();
        this.derivedStates = new IdentityScopeMap();
        ArrayList arrayList = new ArrayList();
        this.changes = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.lateChanges = arrayList2;
        this.observationsProcessed = new IdentityScopeMap();
        this.invalidations = new IdentityArrayMap();
        this.composer = new ComposerImpl(uiApplier, compositionContext, slotTable, hashSet, arrayList, arrayList2, this);
        boolean z = compositionContext instanceof Recomposer;
        ComposableLambdaImpl composableLambdaImpl = ComposableSingletons$CompositionKt.f0lambda1;
    }

    private final void abandonChanges() {
        this.pendingModifications.set(null);
        ((ArrayList) this.changes).clear();
        ((ArrayList) this.lateChanges).clear();
        this.abandonSet.clear();
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00a8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void addPendingInvalidationsLocked(java.util.Set r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.addPendingInvalidationsLocked(java.util.Set, boolean):void");
    }

    private static final void addPendingInvalidationsLocked$invalidate(CompositionImpl compositionImpl, boolean z, Ref$ObjectRef ref$ObjectRef, Object obj) {
        int find;
        IdentityScopeMap identityScopeMap = compositionImpl.observations;
        find = identityScopeMap.find(obj);
        if (find >= 0) {
            IdentityArraySet access$scopeSetAt = IdentityScopeMap.access$scopeSetAt(identityScopeMap, find);
            int size = access$scopeSetAt.size();
            for (int i = 0; i < size; i++) {
                RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) access$scopeSetAt.get(i);
                if (!compositionImpl.observationsProcessed.remove(obj, recomposeScopeImpl) && recomposeScopeImpl.invalidateForResult(obj) != InvalidationResult.IGNORED) {
                    if (recomposeScopeImpl.isConditional() && !z) {
                        compositionImpl.conditionallyInvalidatedScopes.add(recomposeScopeImpl);
                    } else {
                        HashSet hashSet = (HashSet) ref$ObjectRef.element;
                        if (hashSet == null) {
                            hashSet = new HashSet();
                            ref$ObjectRef.element = hashSet;
                        }
                        hashSet.add(recomposeScopeImpl);
                    }
                }
            }
        }
    }

    private final void applyChangesInLocked(List list) {
        boolean isEmpty;
        Applier applier = this.applier;
        List list2 = this.lateChanges;
        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
        try {
            if (list.isEmpty()) {
                if (isEmpty) {
                    return;
                }
                return;
            }
            Trace.beginSection("Compose:applyChanges");
            applier.getClass();
            SlotWriter openWriter = this.slotTable.openWriter();
            try {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ((Function3) list.get(i)).invoke(applier, openWriter, rememberEventDispatcher);
                }
                list.clear();
                openWriter.close();
                applier.onEndChanges();
                Trace.endSection();
                rememberEventDispatcher.dispatchRememberObservers();
                rememberEventDispatcher.dispatchNodeCallbacks();
                rememberEventDispatcher.dispatchSideEffects();
                if (this.pendingInvalidScopes) {
                    Trace.beginSection("Compose:unobserve");
                    this.pendingInvalidScopes = false;
                    IdentityScopeMap identityScopeMap = this.observations;
                    int size2 = identityScopeMap.getSize();
                    int i2 = 0;
                    for (int i3 = 0; i3 < size2; i3++) {
                        int i4 = identityScopeMap.getValueOrder()[i3];
                        IdentityArraySet identityArraySet = identityScopeMap.getScopeSets()[i4];
                        Intrinsics.checkNotNull(identityArraySet);
                        int size3 = identityArraySet.size();
                        int i5 = 0;
                        for (int i6 = 0; i6 < size3; i6++) {
                            Object obj = identityArraySet.getValues()[i6];
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                            if (!(!((RecomposeScopeImpl) obj).getValid())) {
                                if (i5 != i6) {
                                    identityArraySet.getValues()[i5] = obj;
                                }
                                i5++;
                            }
                        }
                        int size4 = identityArraySet.size();
                        for (int i7 = i5; i7 < size4; i7++) {
                            identityArraySet.getValues()[i7] = null;
                        }
                        identityArraySet.setSize(i5);
                        if (identityArraySet.size() > 0) {
                            if (i2 != i3) {
                                int i8 = identityScopeMap.getValueOrder()[i2];
                                identityScopeMap.getValueOrder()[i2] = i4;
                                identityScopeMap.getValueOrder()[i3] = i8;
                            }
                            i2++;
                        }
                    }
                    int size5 = identityScopeMap.getSize();
                    for (int i9 = i2; i9 < size5; i9++) {
                        identityScopeMap.getValues()[identityScopeMap.getValueOrder()[i9]] = null;
                    }
                    identityScopeMap.setSize(i2);
                    cleanUpDerivedStateObservations();
                    Trace.endSection();
                }
                if (((ArrayList) list2).isEmpty()) {
                    rememberEventDispatcher.dispatchAbandons();
                }
            } catch (Throwable th) {
                openWriter.close();
                throw th;
            }
        } finally {
            if (((ArrayList) list2).isEmpty()) {
                rememberEventDispatcher.dispatchAbandons();
            }
        }
    }

    private final void cleanUpDerivedStateObservations() {
        IdentityScopeMap identityScopeMap = this.derivedStates;
        int size = identityScopeMap.getSize();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int i3 = identityScopeMap.getValueOrder()[i2];
            IdentityArraySet identityArraySet = identityScopeMap.getScopeSets()[i3];
            Intrinsics.checkNotNull(identityArraySet);
            int size2 = identityArraySet.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size2; i5++) {
                Object obj = identityArraySet.getValues()[i5];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                if (!(!this.observations.contains((DerivedState) obj))) {
                    if (i4 != i5) {
                        identityArraySet.getValues()[i4] = obj;
                    }
                    i4++;
                }
            }
            int size3 = identityArraySet.size();
            for (int i6 = i4; i6 < size3; i6++) {
                identityArraySet.getValues()[i6] = null;
            }
            identityArraySet.setSize(i4);
            if (identityArraySet.size() > 0) {
                if (i != i2) {
                    int i7 = identityScopeMap.getValueOrder()[i];
                    identityScopeMap.getValueOrder()[i] = i3;
                    identityScopeMap.getValueOrder()[i2] = i7;
                }
                i++;
            }
        }
        int size4 = identityScopeMap.getSize();
        for (int i8 = i; i8 < size4; i8++) {
            identityScopeMap.getValues()[identityScopeMap.getValueOrder()[i8]] = null;
        }
        identityScopeMap.setSize(i);
        Iterator it = this.conditionallyInvalidatedScopes.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator()");
        while (it.hasNext()) {
            if (!((RecomposeScopeImpl) it.next()).isConditional()) {
                it.remove();
            }
        }
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object obj;
        Object obj2;
        AtomicReference atomicReference = this.pendingModifications;
        obj = CompositionKt.PendingApplyNoModifications;
        Object andSet = atomicReference.getAndSet(obj);
        if (andSet != null) {
            obj2 = CompositionKt.PendingApplyNoModifications;
            if (!Intrinsics.areEqual(andSet, obj2)) {
                if (andSet instanceof Set) {
                    addPendingInvalidationsLocked((Set) andSet, true);
                    return;
                } else if (andSet instanceof Object[]) {
                    for (Set set : (Set[]) andSet) {
                        addPendingInvalidationsLocked(set, true);
                    }
                    return;
                } else {
                    ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + atomicReference);
                    throw null;
                }
            }
            ComposerKt.composeRuntimeError("pending composition has not been applied");
            throw null;
        }
    }

    private final void drainPendingModificationsLocked() {
        Object obj;
        AtomicReference atomicReference = this.pendingModifications;
        Object andSet = atomicReference.getAndSet(null);
        obj = CompositionKt.PendingApplyNoModifications;
        if (!Intrinsics.areEqual(andSet, obj)) {
            if (andSet instanceof Set) {
                addPendingInvalidationsLocked((Set) andSet, false);
            } else if (andSet instanceof Object[]) {
                for (Set set : (Set[]) andSet) {
                    addPendingInvalidationsLocked(set, false);
                }
            } else if (andSet == null) {
                ComposerKt.composeRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
                throw null;
            } else {
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + atomicReference);
                throw null;
            }
        }
    }

    private final InvalidationResult invalidateChecked(RecomposeScopeImpl recomposeScopeImpl, Anchor anchor, Object obj) {
        synchronized (this.lock) {
            CompositionImpl compositionImpl = this.invalidationDelegate;
            if (compositionImpl == null || !this.slotTable.groupContainsAnchor(this.invalidationDelegateGroup, anchor)) {
                compositionImpl = null;
            }
            if (compositionImpl == null) {
                if (isComposing() && this.composer.tryImminentInvalidation$runtime_release(recomposeScopeImpl, obj)) {
                    return InvalidationResult.IMMINENT;
                } else if (obj == null) {
                    this.invalidations.set(recomposeScopeImpl, null);
                } else {
                    IdentityArrayMap identityArrayMap = this.invalidations;
                    if (identityArrayMap.contains(recomposeScopeImpl)) {
                        IdentityArraySet identityArraySet = (IdentityArraySet) identityArrayMap.get(recomposeScopeImpl);
                        if (identityArraySet != null) {
                            identityArraySet.add(obj);
                        }
                    } else {
                        IdentityArraySet identityArraySet2 = new IdentityArraySet();
                        identityArraySet2.add(obj);
                        identityArrayMap.set(recomposeScopeImpl, identityArraySet2);
                    }
                }
            }
            if (compositionImpl != null) {
                return compositionImpl.invalidateChecked(recomposeScopeImpl, anchor, obj);
            }
            this.parent.invalidate$runtime_release(this);
            if (isComposing()) {
                return InvalidationResult.DEFERRED;
            }
            return InvalidationResult.SCHEDULED;
        }
    }

    private final void invalidateScopeOfLocked(Object obj) {
        int find;
        IdentityScopeMap identityScopeMap = this.observations;
        find = identityScopeMap.find(obj);
        if (find >= 0) {
            IdentityArraySet access$scopeSetAt = IdentityScopeMap.access$scopeSetAt(identityScopeMap, find);
            int size = access$scopeSetAt.size();
            for (int i = 0; i < size; i++) {
                RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) access$scopeSetAt.get(i);
                if (recomposeScopeImpl.invalidateForResult(obj) == InvalidationResult.IMMINENT) {
                    this.observationsProcessed.add(obj, recomposeScopeImpl);
                }
            }
        }
    }

    public final void applyChanges() {
        synchronized (this.lock) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
            } catch (Throwable th) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                    }
                    throw th;
                } catch (Exception e) {
                    abandonChanges();
                    throw e;
                }
            }
        }
    }

    public final void applyLateChanges() {
        synchronized (this.lock) {
            try {
                if (!this.lateChanges.isEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
            } catch (Throwable th) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                    }
                    throw th;
                } catch (Exception e) {
                    abandonChanges();
                    throw e;
                }
            }
        }
    }

    public final void changesApplied() {
        synchronized (this.lock) {
            try {
                this.composer.changesApplied$runtime_release();
                if (!this.abandonSet.isEmpty()) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
            } catch (Throwable th) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                    }
                    throw th;
                } catch (Exception e) {
                    abandonChanges();
                    throw e;
                }
            }
        }
    }

    public final void composeContent(ComposableLambdaImpl composableLambdaImpl) {
        try {
            synchronized (this.lock) {
                drainPendingModificationsForCompositionLocked();
                IdentityArrayMap identityArrayMap = this.invalidations;
                this.invalidations = new IdentityArrayMap();
                try {
                    this.composer.composeContent$runtime_release(identityArrayMap, composableLambdaImpl);
                } catch (Exception e) {
                    this.invalidations = identityArrayMap;
                    throw e;
                }
            }
        } catch (Throwable th) {
            try {
                if (!this.abandonSet.isEmpty()) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
                throw th;
            } catch (Exception e2) {
                abandonChanges();
                throw e2;
            }
        }
    }

    public final Object delegateInvalidations(ControlledComposition controlledComposition, int i, Function0 function0) {
        if (controlledComposition != null && !Intrinsics.areEqual(controlledComposition, this) && i >= 0) {
            this.invalidationDelegate = (CompositionImpl) controlledComposition;
            this.invalidationDelegateGroup = i;
            try {
                return function0.invoke();
            } finally {
                this.invalidationDelegate = null;
                this.invalidationDelegateGroup = 0;
            }
        }
        return function0.invoke();
    }

    @Override // androidx.compose.runtime.Composition
    public final void dispose() {
        boolean z;
        synchronized (this.lock) {
            if (!this.disposed) {
                this.disposed = true;
                ComposableLambdaImpl composableLambdaImpl = ComposableSingletons$CompositionKt.f0lambda1;
                this.composer.getClass();
                if (this.slotTable.getGroupsSize() > 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z || (true ^ this.abandonSet.isEmpty())) {
                    RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
                    if (z) {
                        SlotWriter openWriter = this.slotTable.openWriter();
                        ComposerKt.removeCurrentGroup(openWriter, rememberEventDispatcher);
                        openWriter.close();
                        this.applier.clear();
                        rememberEventDispatcher.dispatchRememberObservers();
                    }
                    rememberEventDispatcher.dispatchAbandons();
                }
                this.composer.dispose$runtime_release();
            }
        }
        this.parent.unregisterComposition$runtime_release(this);
    }

    public final void disposeUnusedMovableContent(MovableContentState movableContentState) {
        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
        SlotWriter openWriter = movableContentState.getSlotTable$runtime_release().openWriter();
        try {
            ComposerKt.removeCurrentGroup(openWriter, rememberEventDispatcher);
            openWriter.close();
            rememberEventDispatcher.dispatchRememberObservers();
        } catch (Throwable th) {
            openWriter.close();
            throw th;
        }
    }

    public final void insertMovableContent(List list) {
        ArrayList arrayList = (ArrayList) list;
        int size = arrayList.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i < size) {
                if (!Intrinsics.areEqual(((MovableContentStateReference) ((Pair) arrayList.get(i)).getFirst()).getComposition$runtime_release(), this)) {
                    break;
                }
                i++;
            } else {
                z = true;
                break;
            }
        }
        ComposerKt.runtimeCheck(z);
        try {
            this.composer.insertMovableContentReferences(list);
        } finally {
        }
    }

    public final InvalidationResult invalidate(RecomposeScopeImpl scope, Object obj) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        InvalidationResult invalidationResult = InvalidationResult.IGNORED;
        if (anchor != null && this.slotTable.ownsAnchor(anchor) && anchor.getValid()) {
            if (!anchor.getValid()) {
                return invalidationResult;
            }
            if (!scope.getCanRecompose()) {
                return invalidationResult;
            }
            return invalidateChecked(scope, anchor, obj);
        }
        return invalidationResult;
    }

    public final void invalidateAll() {
        Object[] slots;
        RecomposeScopeImpl recomposeScopeImpl;
        synchronized (this.lock) {
            for (Object obj : this.slotTable.getSlots()) {
                if (obj instanceof RecomposeScopeImpl) {
                    recomposeScopeImpl = (RecomposeScopeImpl) obj;
                } else {
                    recomposeScopeImpl = null;
                }
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.invalidate();
                }
            }
        }
    }

    public final boolean isComposing() {
        return this.composer.isComposing$runtime_release();
    }

    public final boolean isDisposed() {
        return this.disposed;
    }

    public final boolean observesAnyOf(IdentityArraySet identityArraySet) {
        Object next;
        Iterator it = identityArraySet.iterator();
        do {
            IdentityArraySet$iterator$1 identityArraySet$iterator$1 = (IdentityArraySet$iterator$1) it;
            if (identityArraySet$iterator$1.hasNext()) {
                next = identityArraySet$iterator$1.next();
                if (this.observations.contains(next)) {
                    return true;
                }
            } else {
                return false;
            }
        } while (!this.derivedStates.contains(next));
        return true;
    }

    public final void prepareCompose(Function0 function0) {
        this.composer.prepareCompose$runtime_release(function0);
    }

    public final boolean recompose() {
        boolean recompose$runtime_release;
        synchronized (this.lock) {
            drainPendingModificationsForCompositionLocked();
            IdentityArrayMap identityArrayMap = this.invalidations;
            this.invalidations = new IdentityArrayMap();
            try {
                recompose$runtime_release = this.composer.recompose$runtime_release(identityArrayMap);
                if (!recompose$runtime_release) {
                    drainPendingModificationsLocked();
                }
            } catch (Exception e) {
                this.invalidations = identityArrayMap;
                throw e;
            }
        }
        return recompose$runtime_release;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void recordModificationsOf(Set values) {
        Object obj;
        Object obj2;
        boolean areEqual;
        Set set;
        Intrinsics.checkNotNullParameter(values, "values");
        do {
            obj = this.pendingModifications.get();
            if (obj != null) {
                obj2 = CompositionKt.PendingApplyNoModifications;
                areEqual = Intrinsics.areEqual(obj, obj2);
            } else {
                areEqual = true;
            }
            if (areEqual) {
                set = values;
            } else if (obj instanceof Set) {
                set = new Set[]{(Set) obj, values};
            } else if (obj instanceof Object[]) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                Set[] setArr = (Set[]) obj;
                int length = setArr.length;
                Object[] copyOf = Arrays.copyOf(setArr, length + 1);
                copyOf[length] = values;
                set = copyOf;
            } else {
                throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
            }
        } while (!this.pendingModifications.compareAndSet(obj, set));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
            }
        }
    }

    public final void recordReadOf(Object value) {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        Object[] dependencies;
        Intrinsics.checkNotNullParameter(value, "value");
        ComposerImpl composerImpl = this.composer;
        if (!composerImpl.getAreChildrenComposing$runtime_release() && (currentRecomposeScope$runtime_release = composerImpl.getCurrentRecomposeScope$runtime_release()) != null) {
            currentRecomposeScope$runtime_release.setUsed();
            this.observations.add(value, currentRecomposeScope$runtime_release);
            if (value instanceof DerivedState) {
                IdentityScopeMap identityScopeMap = this.derivedStates;
                identityScopeMap.removeScope(value);
                for (Object obj : ((DerivedSnapshotState) ((DerivedState) value)).getDependencies()) {
                    if (obj == null) {
                        break;
                    }
                    identityScopeMap.add(obj, value);
                }
            }
            currentRecomposeScope$runtime_release.recordRead(value);
        }
    }

    public final void recordWriteOf(Object value) {
        int find;
        Intrinsics.checkNotNullParameter(value, "value");
        synchronized (this.lock) {
            invalidateScopeOfLocked(value);
            IdentityScopeMap identityScopeMap = this.derivedStates;
            find = identityScopeMap.find(value);
            if (find >= 0) {
                IdentityArraySet access$scopeSetAt = IdentityScopeMap.access$scopeSetAt(identityScopeMap, find);
                int size = access$scopeSetAt.size();
                for (int i = 0; i < size; i++) {
                    invalidateScopeOfLocked((DerivedState) access$scopeSetAt.get(i));
                }
            }
        }
    }

    public final void removeDerivedStateObservation$runtime_release(DerivedState derivedState) {
        if (!this.observations.contains(derivedState)) {
            this.derivedStates.removeScope(derivedState);
        }
    }

    public final void removeObservation$runtime_release(RecomposeScopeImpl scope, Object obj) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.observations.remove(obj, scope);
    }

    @Override // androidx.compose.runtime.Composition
    public final void setContent(Function2 function2) {
        if (!this.disposed) {
            this.parent.composeInitial$runtime_release(this, (ComposableLambdaImpl) function2);
            return;
        }
        throw new IllegalStateException("The composition is disposed".toString());
    }

    public final void setPendingInvalidScopes$runtime_release() {
        this.pendingInvalidScopes = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class RememberEventDispatcher implements RememberManager {
        private final Set abandoning;
        private final List forgetting;
        private final List remembering;
        private final List sideEffects;

        public RememberEventDispatcher(Set abandoning) {
            Intrinsics.checkNotNullParameter(abandoning, "abandoning");
            this.abandoning = abandoning;
            this.remembering = new ArrayList();
            this.forgetting = new ArrayList();
            this.sideEffects = new ArrayList();
        }

        public final void dispatchAbandons() {
            Set set = this.abandoning;
            if (!set.isEmpty()) {
                Trace.beginSection("Compose:abandons");
                try {
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        it.remove();
                        ((RememberObserver) it.next()).onAbandoned();
                    }
                } finally {
                    Trace.endSection();
                }
            }
        }

        public final void dispatchRememberObservers() {
            List list = this.forgetting;
            boolean z = !list.isEmpty();
            Set set = this.abandoning;
            if (z) {
                Trace.beginSection("Compose:onForgotten");
                try {
                    int size = ((ArrayList) list).size();
                    while (true) {
                        size--;
                        if (-1 >= size) {
                            break;
                        }
                        RememberObserver rememberObserver = (RememberObserver) ((ArrayList) list).get(size);
                        if (!set.contains(rememberObserver)) {
                            rememberObserver.onForgotten();
                        }
                    }
                } finally {
                }
            }
            List list2 = this.remembering;
            if (!list2.isEmpty()) {
                Trace.beginSection("Compose:onRemembered");
                try {
                    ArrayList arrayList = (ArrayList) list2;
                    int size2 = arrayList.size();
                    for (int i = 0; i < size2; i++) {
                        RememberObserver rememberObserver2 = (RememberObserver) arrayList.get(i);
                        set.remove(rememberObserver2);
                        rememberObserver2.onRemembered();
                    }
                } finally {
                }
            }
        }

        public final void dispatchSideEffects() {
            List list = this.sideEffects;
            if (!list.isEmpty()) {
                Trace.beginSection("Compose:sideeffects");
                try {
                    ArrayList arrayList = (ArrayList) list;
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        ((Function0) arrayList.get(i)).invoke();
                    }
                    ((ArrayList) list).clear();
                } finally {
                    Trace.endSection();
                }
            }
        }

        public final void forgetting(RememberObserver instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            List list = this.remembering;
            int lastIndexOf = ((ArrayList) list).lastIndexOf(instance);
            if (lastIndexOf >= 0) {
                ((ArrayList) list).remove(lastIndexOf);
                this.abandoning.remove(instance);
                return;
            }
            ((ArrayList) this.forgetting).add(instance);
        }

        public final void remembering(RememberObserver instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            List list = this.forgetting;
            int lastIndexOf = ((ArrayList) list).lastIndexOf(instance);
            if (lastIndexOf >= 0) {
                ((ArrayList) list).remove(lastIndexOf);
                this.abandoning.remove(instance);
                return;
            }
            ((ArrayList) this.remembering).add(instance);
        }

        public final void sideEffect(Function0 effect) {
            Intrinsics.checkNotNullParameter(effect, "effect");
            this.sideEffects.add(effect);
        }

        public final void dispatchNodeCallbacks() {
        }
    }
}
