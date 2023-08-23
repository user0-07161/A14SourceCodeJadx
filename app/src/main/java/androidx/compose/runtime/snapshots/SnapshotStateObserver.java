package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.IdentityScopeMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SnapshotStateObserver {
    private ObserverHandle applyUnsubscribe;
    private ObservedScopeMap currentMap;
    private boolean isPaused;
    private final Function1 onChangedExecutor;
    private boolean sendingNotifications;
    private final AtomicReference pendingChanges = new AtomicReference(null);
    private final Function2 applyObserver = new Function2() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$applyObserver$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Set applied = (Set) obj;
            Intrinsics.checkNotNullParameter(applied, "applied");
            Intrinsics.checkNotNullParameter((Snapshot) obj2, "<anonymous parameter 1>");
            SnapshotStateObserver.access$addChanges(SnapshotStateObserver.this, applied);
            if (SnapshotStateObserver.access$drainChanges(SnapshotStateObserver.this)) {
                SnapshotStateObserver.access$sendNotifications(SnapshotStateObserver.this);
            }
            return Unit.INSTANCE;
        }
    };
    private final Function1 readObserver = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$readObserver$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object state) {
            boolean z;
            MutableVector mutableVector;
            SnapshotStateObserver.ObservedScopeMap observedScopeMap;
            Intrinsics.checkNotNullParameter(state, "state");
            z = SnapshotStateObserver.this.isPaused;
            if (!z) {
                mutableVector = SnapshotStateObserver.this.observedScopeMaps;
                SnapshotStateObserver snapshotStateObserver = SnapshotStateObserver.this;
                synchronized (mutableVector) {
                    observedScopeMap = snapshotStateObserver.currentMap;
                    Intrinsics.checkNotNull(observedScopeMap);
                    observedScopeMap.recordRead(state);
                }
            }
            return Unit.INSTANCE;
        }
    };
    private final MutableVector observedScopeMaps = new MutableVector(new ObservedScopeMap[16]);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ObservedScopeMap {
        private Object currentScope;
        private IdentityArrayIntMap currentScopeReads;
        private int currentToken;
        private final IdentityScopeMap dependencyToDerivedStates;
        private int deriveStateScopeCount;
        private final Function1 derivedStateEnterObserver;
        private final Function1 derivedStateExitObserver;
        private final IdentityArraySet invalidated;
        private final Function1 onChanged;
        private final HashMap recordedDerivedStateValues;
        private final IdentityArrayMap scopeToValues;
        private final IdentityScopeMap valueToScopes;

        public ObservedScopeMap(Function1 onChanged) {
            Intrinsics.checkNotNullParameter(onChanged, "onChanged");
            this.onChanged = onChanged;
            this.currentToken = -1;
            this.valueToScopes = new IdentityScopeMap();
            this.scopeToValues = new IdentityArrayMap();
            this.invalidated = new IdentityArraySet();
            this.derivedStateEnterObserver = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateEnterObserver$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    int i;
                    State it = (State) obj;
                    Intrinsics.checkNotNullParameter(it, "it");
                    SnapshotStateObserver.ObservedScopeMap observedScopeMap = SnapshotStateObserver.ObservedScopeMap.this;
                    i = observedScopeMap.deriveStateScopeCount;
                    observedScopeMap.deriveStateScopeCount = i + 1;
                    return Unit.INSTANCE;
                }
            };
            this.derivedStateExitObserver = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateExitObserver$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    int i;
                    State it = (State) obj;
                    Intrinsics.checkNotNullParameter(it, "it");
                    SnapshotStateObserver.ObservedScopeMap observedScopeMap = SnapshotStateObserver.ObservedScopeMap.this;
                    i = observedScopeMap.deriveStateScopeCount;
                    observedScopeMap.deriveStateScopeCount = i - 1;
                    return Unit.INSTANCE;
                }
            };
            this.dependencyToDerivedStates = new IdentityScopeMap();
            this.recordedDerivedStateValues = new HashMap();
        }

        public static final void access$clearObsoleteStateReads(ObservedScopeMap observedScopeMap, Object obj) {
            boolean z;
            IdentityArrayIntMap identityArrayIntMap = observedScopeMap.currentScopeReads;
            if (identityArrayIntMap != null) {
                int size = identityArrayIntMap.getSize();
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    Object obj2 = identityArrayIntMap.getKeys()[i2];
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Any");
                    int i3 = identityArrayIntMap.getValues()[i2];
                    if (i3 != observedScopeMap.currentToken) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        IdentityScopeMap identityScopeMap = observedScopeMap.valueToScopes;
                        identityScopeMap.remove(obj2, obj);
                        if ((obj2 instanceof DerivedState) && !identityScopeMap.contains(obj2)) {
                            observedScopeMap.dependencyToDerivedStates.removeScope(obj2);
                            observedScopeMap.recordedDerivedStateValues.remove(obj2);
                        }
                    }
                    if (!z) {
                        if (i != i2) {
                            identityArrayIntMap.getKeys()[i] = obj2;
                            identityArrayIntMap.getValues()[i] = i3;
                        }
                        i++;
                    }
                }
                int size2 = identityArrayIntMap.getSize();
                for (int i4 = i; i4 < size2; i4++) {
                    identityArrayIntMap.getKeys()[i4] = null;
                }
                identityArrayIntMap.setSize(i);
            }
        }

        public final void clear() {
            this.valueToScopes.clear();
            this.scopeToValues.clear();
            this.dependencyToDerivedStates.clear();
            this.recordedDerivedStateValues.clear();
        }

        public final Function1 getDerivedStateEnterObserver() {
            return this.derivedStateEnterObserver;
        }

        public final Function1 getDerivedStateExitObserver() {
            return this.derivedStateExitObserver;
        }

        public final Function1 getOnChanged() {
            return this.onChanged;
        }

        public final void notifyInvalidatedScopes() {
            IdentityArraySet identityArraySet = this.invalidated;
            int size = identityArraySet.size();
            for (int i = 0; i < size; i++) {
                this.onChanged.invoke(identityArraySet.get(i));
            }
            identityArraySet.clear();
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x004e, code lost:
            r9 = r7.find(r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
            r4 = r3.find(r2);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean recordInvalidation(java.util.Set r14) {
            /*
                r13 = this;
                java.util.Iterator r14 = r14.iterator()
                r0 = 0
                r1 = r0
            L6:
                boolean r2 = r14.hasNext()
                if (r2 == 0) goto L89
                java.lang.Object r2 = r14.next()
                androidx.compose.runtime.collection.IdentityScopeMap r3 = r13.dependencyToDerivedStates
                boolean r4 = r3.contains(r2)
                r5 = 1
                androidx.compose.runtime.collection.IdentityArraySet r6 = r13.invalidated
                androidx.compose.runtime.collection.IdentityScopeMap r7 = r13.valueToScopes
                if (r4 == 0) goto L6d
                int r4 = androidx.compose.runtime.collection.IdentityScopeMap.access$find(r3, r2)
                if (r4 < 0) goto L6d
                androidx.compose.runtime.collection.IdentityArraySet r3 = androidx.compose.runtime.collection.IdentityScopeMap.access$scopeSetAt(r3, r4)
                int r4 = r3.size()
                r8 = r0
            L2c:
                if (r8 >= r4) goto L6d
                java.lang.Object r9 = r3.get(r8)
                androidx.compose.runtime.DerivedState r9 = (androidx.compose.runtime.DerivedState) r9
                java.util.HashMap r10 = r13.recordedDerivedStateValues
                java.lang.Object r10 = r10.get(r9)
                androidx.compose.runtime.SnapshotMutationPolicy r11 = r9.getPolicy()
                if (r11 != 0) goto L44
                androidx.compose.runtime.SnapshotMutationPolicy r11 = androidx.compose.runtime.SnapshotStateKt.structuralEqualityPolicy()
            L44:
                java.lang.Object r12 = r9.getCurrentValue()
                boolean r10 = r11.equivalent(r12, r10)
                if (r10 != 0) goto L6a
                int r9 = androidx.compose.runtime.collection.IdentityScopeMap.access$find(r7, r9)
                if (r9 < 0) goto L6a
                androidx.compose.runtime.collection.IdentityArraySet r9 = androidx.compose.runtime.collection.IdentityScopeMap.access$scopeSetAt(r7, r9)
                int r10 = r9.size()
                r11 = r0
            L5d:
                if (r11 >= r10) goto L6a
                java.lang.Object r1 = r9.get(r11)
                r6.add(r1)
                int r11 = r11 + 1
                r1 = r5
                goto L5d
            L6a:
                int r8 = r8 + 1
                goto L2c
            L6d:
                int r2 = androidx.compose.runtime.collection.IdentityScopeMap.access$find(r7, r2)
                if (r2 < 0) goto L6
                androidx.compose.runtime.collection.IdentityArraySet r2 = androidx.compose.runtime.collection.IdentityScopeMap.access$scopeSetAt(r7, r2)
                int r3 = r2.size()
                r4 = r0
            L7c:
                if (r4 >= r3) goto L6
                java.lang.Object r1 = r2.get(r4)
                r6.add(r1)
                int r4 = r4 + 1
                r1 = r5
                goto L7c
            L89:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.recordInvalidation(java.util.Set):boolean");
        }

        public final void recordRead(Object value) {
            Object[] dependencies;
            Intrinsics.checkNotNullParameter(value, "value");
            if (this.deriveStateScopeCount > 0) {
                return;
            }
            Object obj = this.currentScope;
            Intrinsics.checkNotNull(obj);
            IdentityArrayIntMap identityArrayIntMap = this.currentScopeReads;
            if (identityArrayIntMap == null) {
                identityArrayIntMap = new IdentityArrayIntMap();
                this.currentScopeReads = identityArrayIntMap;
                this.scopeToValues.set(obj, identityArrayIntMap);
            }
            int add = identityArrayIntMap.add(this.currentToken, value);
            if ((value instanceof DerivedState) && add != this.currentToken) {
                DerivedState derivedState = (DerivedState) value;
                for (Object obj2 : derivedState.getDependencies()) {
                    if (obj2 == null) {
                        break;
                    }
                    this.dependencyToDerivedStates.add(obj2, value);
                }
                this.recordedDerivedStateValues.put(value, derivedState.getCurrentValue());
            }
            if (add == -1) {
                this.valueToScopes.add(value, obj);
            }
        }

        public final void removeScopeIf(Function1 predicate) {
            Intrinsics.checkNotNullParameter(predicate, "predicate");
            IdentityArrayMap identityArrayMap = this.scopeToValues;
            int size$runtime_release = identityArrayMap.getSize$runtime_release();
            int i = 0;
            for (int i2 = 0; i2 < size$runtime_release; i2++) {
                Object obj = identityArrayMap.getKeys$runtime_release()[i2];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
                IdentityArrayIntMap identityArrayIntMap = (IdentityArrayIntMap) identityArrayMap.getValues$runtime_release()[i2];
                Boolean bool = (Boolean) predicate.invoke(obj);
                if (bool.booleanValue()) {
                    int size = identityArrayIntMap.getSize();
                    for (int i3 = 0; i3 < size; i3++) {
                        Object obj2 = identityArrayIntMap.getKeys()[i3];
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Any");
                        int i4 = identityArrayIntMap.getValues()[i3];
                        IdentityScopeMap identityScopeMap = this.valueToScopes;
                        identityScopeMap.remove(obj2, obj);
                        if ((obj2 instanceof DerivedState) && !identityScopeMap.contains(obj2)) {
                            this.dependencyToDerivedStates.removeScope(obj2);
                            this.recordedDerivedStateValues.remove(obj2);
                        }
                    }
                }
                if (!bool.booleanValue()) {
                    if (i != i2) {
                        identityArrayMap.getKeys$runtime_release()[i] = obj;
                        identityArrayMap.getValues$runtime_release()[i] = identityArrayMap.getValues$runtime_release()[i2];
                    }
                    i++;
                }
            }
            if (identityArrayMap.getSize$runtime_release() > i) {
                int size$runtime_release2 = identityArrayMap.getSize$runtime_release();
                for (int i5 = i; i5 < size$runtime_release2; i5++) {
                    identityArrayMap.getKeys$runtime_release()[i5] = null;
                    identityArrayMap.getValues$runtime_release()[i5] = null;
                }
                identityArrayMap.setSize$runtime_release(i);
            }
        }
    }

    public SnapshotStateObserver(Function1 function1) {
        this.onChangedExecutor = function1;
    }

    public static final void access$addChanges(SnapshotStateObserver snapshotStateObserver, Set set) {
        AtomicReference atomicReference;
        Object obj;
        Collection plus;
        do {
            atomicReference = snapshotStateObserver.pendingChanges;
            obj = atomicReference.get();
            if (obj == null) {
                plus = set;
            } else if (obj instanceof Set) {
                plus = CollectionsKt.listOf((Object[]) new Set[]{(Set) obj, set});
            } else if (obj instanceof List) {
                plus = CollectionsKt.plus(CollectionsKt.listOf(set), (Collection) obj);
            } else {
                ComposerKt.composeRuntimeError("Unexpected notification");
                throw null;
            }
        } while (!atomicReference.compareAndSet(obj, plus));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean access$drainChanges(SnapshotStateObserver snapshotStateObserver) {
        boolean z;
        Set set;
        synchronized (snapshotStateObserver.observedScopeMaps) {
            z = snapshotStateObserver.sendingNotifications;
        }
        if (z) {
            return false;
        }
        boolean z2 = false;
        while (true) {
            AtomicReference atomicReference = snapshotStateObserver.pendingChanges;
            Object obj = atomicReference.get();
            Set set2 = null;
            List list = null;
            List list2 = null;
            if (obj != null) {
                if (obj instanceof Set) {
                    set = (Set) obj;
                } else if (obj instanceof List) {
                    List list3 = (List) obj;
                    Set set3 = (Set) list3.get(0);
                    if (list3.size() == 2) {
                        list2 = list3.get(1);
                    } else if (list3.size() > 2) {
                        list2 = list3.subList(1, list3.size());
                    }
                    set = set3;
                    list = list2;
                } else {
                    ComposerKt.composeRuntimeError("Unexpected notification");
                    throw null;
                }
                if (atomicReference.compareAndSet(obj, list)) {
                    set2 = set;
                } else {
                    continue;
                }
            }
            if (set2 == null) {
                return z2;
            }
            synchronized (snapshotStateObserver.observedScopeMaps) {
                MutableVector mutableVector = snapshotStateObserver.observedScopeMaps;
                int size = mutableVector.getSize();
                if (size > 0) {
                    Object[] content = mutableVector.getContent();
                    int i = 0;
                    do {
                        if (!((ObservedScopeMap) content[i]).recordInvalidation(set2) && !z2) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        i++;
                    } while (i < size);
                }
            }
        }
    }

    public static final void access$sendNotifications(final SnapshotStateObserver snapshotStateObserver) {
        snapshotStateObserver.getClass();
        snapshotStateObserver.onChangedExecutor.invoke(new Function0() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$sendNotifications$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                MutableVector mutableVector;
                boolean z;
                MutableVector mutableVector2;
                do {
                    mutableVector = SnapshotStateObserver.this.observedScopeMaps;
                    SnapshotStateObserver snapshotStateObserver2 = SnapshotStateObserver.this;
                    synchronized (mutableVector) {
                        z = snapshotStateObserver2.sendingNotifications;
                        if (!z) {
                            snapshotStateObserver2.sendingNotifications = true;
                            mutableVector2 = snapshotStateObserver2.observedScopeMaps;
                            int size = mutableVector2.getSize();
                            if (size <= 0) {
                                snapshotStateObserver2.sendingNotifications = false;
                            } else {
                                Object[] content = mutableVector2.getContent();
                                int i = 0;
                                do {
                                    ((SnapshotStateObserver.ObservedScopeMap) content[i]).notifyInvalidatedScopes();
                                    i++;
                                } while (i < size);
                                snapshotStateObserver2.sendingNotifications = false;
                            }
                        }
                    }
                } while (SnapshotStateObserver.access$drainChanges(SnapshotStateObserver.this));
                return Unit.INSTANCE;
            }
        });
    }

    public final void clear() {
        synchronized (this.observedScopeMaps) {
            MutableVector mutableVector = this.observedScopeMaps;
            int size = mutableVector.getSize();
            if (size > 0) {
                Object[] content = mutableVector.getContent();
                int i = 0;
                do {
                    ((ObservedScopeMap) content[i]).clear();
                    i++;
                } while (i < size);
            }
        }
    }

    public final void clearIf(Function1 predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        synchronized (this.observedScopeMaps) {
            MutableVector mutableVector = this.observedScopeMaps;
            int size = mutableVector.getSize();
            if (size > 0) {
                Object[] content = mutableVector.getContent();
                int i = 0;
                do {
                    ((ObservedScopeMap) content[i]).removeScopeIf(predicate);
                    i++;
                } while (i < size);
            }
        }
    }

    public final void observeReads(Object scope, Function1 onValueChangedForScope, final Function0 function0) {
        Object obj;
        ObservedScopeMap observedScopeMap;
        boolean z;
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(onValueChangedForScope, "onValueChangedForScope");
        synchronized (this.observedScopeMaps) {
            MutableVector mutableVector = this.observedScopeMaps;
            int size = mutableVector.getSize();
            if (size > 0) {
                Object[] content = mutableVector.getContent();
                int i = 0;
                do {
                    obj = content[i];
                    if (((ObservedScopeMap) obj).getOnChanged() == onValueChangedForScope) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        break;
                    }
                    i++;
                } while (i < size);
                obj = null;
            } else {
                obj = null;
            }
            observedScopeMap = obj;
            if (observedScopeMap == null) {
                TypeIntrinsics.beforeCheckcastToFunctionOfArity(1, onValueChangedForScope);
                observedScopeMap = new ObservedScopeMap(onValueChangedForScope);
                mutableVector.add(observedScopeMap);
            }
        }
        boolean z2 = this.isPaused;
        ObservedScopeMap observedScopeMap2 = this.currentMap;
        try {
            this.isPaused = false;
            this.currentMap = observedScopeMap;
            Object obj2 = observedScopeMap.currentScope;
            IdentityArrayIntMap identityArrayIntMap = observedScopeMap.currentScopeReads;
            int i2 = observedScopeMap.currentToken;
            observedScopeMap.currentScope = scope;
            observedScopeMap.currentScopeReads = (IdentityArrayIntMap) observedScopeMap.scopeToValues.get(scope);
            if (observedScopeMap.currentToken == -1) {
                observedScopeMap.currentToken = SnapshotKt.currentSnapshot().getId();
            }
            SnapshotStateKt.observeDerivedStateRecalculations(observedScopeMap.getDerivedStateEnterObserver(), observedScopeMap.getDerivedStateExitObserver(), new Function0() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$observeReads$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Function1 function1;
                    function1 = SnapshotStateObserver.this.readObserver;
                    Snapshot.Companion.observe(function1, function0);
                    return Unit.INSTANCE;
                }
            });
            Object obj3 = observedScopeMap.currentScope;
            Intrinsics.checkNotNull(obj3);
            ObservedScopeMap.access$clearObsoleteStateReads(observedScopeMap, obj3);
            observedScopeMap.currentScope = obj2;
            observedScopeMap.currentScopeReads = identityArrayIntMap;
            observedScopeMap.currentToken = i2;
        } finally {
            this.currentMap = observedScopeMap2;
            this.isPaused = z2;
        }
    }

    public final void start() {
        List list;
        Function2 observer = this.applyObserver;
        Intrinsics.checkNotNullParameter(observer, "observer");
        SnapshotKt.advanceGlobalSnapshot(SnapshotKt.emptyLambda);
        synchronized (SnapshotKt.getLock()) {
            list = SnapshotKt.applyObservers;
            ((ArrayList) list).add(observer);
        }
        this.applyUnsubscribe = new Snapshot$Companion$registerApplyObserver$2((Lambda) observer, 0);
    }

    public final void stop() {
        ObserverHandle observerHandle = this.applyUnsubscribe;
        if (observerHandle != null) {
            ((Snapshot$Companion$registerApplyObserver$2) observerHandle).dispose();
        }
    }
}
