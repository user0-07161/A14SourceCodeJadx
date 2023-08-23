package androidx.compose.runtime;

import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.collection.IdentityArrayMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RecomposeScopeImpl {
    private Anchor anchor;
    private Function2 block;
    private CompositionImpl composition;
    private int currentToken;
    private int flags;
    private IdentityArrayMap trackedDependencies;
    private IdentityArrayIntMap trackedInstances;

    public RecomposeScopeImpl(CompositionImpl compositionImpl) {
        this.composition = compositionImpl;
    }

    public final void adoptedBy(CompositionImpl compositionImpl) {
        this.composition = compositionImpl;
    }

    public final void compose(Composer composer) {
        Unit unit;
        Intrinsics.checkNotNullParameter(composer, "composer");
        Function2 function2 = this.block;
        if (function2 != null) {
            function2.invoke(composer, 1);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit != null) {
            return;
        }
        throw new IllegalStateException("Invalid restart scope".toString());
    }

    public final Function1 end(final int i) {
        boolean z;
        final IdentityArrayIntMap identityArrayIntMap = this.trackedInstances;
        if (identityArrayIntMap == null || getSkipped$runtime_release()) {
            return null;
        }
        int size = identityArrayIntMap.getSize();
        boolean z2 = false;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            Intrinsics.checkNotNull(identityArrayIntMap.getKeys()[i2], "null cannot be cast to non-null type kotlin.Any");
            if (identityArrayIntMap.getValues()[i2] != i) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                z2 = true;
                break;
            }
            i2++;
        }
        if (!z2) {
            return null;
        }
        return new Function1() { // from class: androidx.compose.runtime.RecomposeScopeImpl$end$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int i3;
                IdentityArrayIntMap identityArrayIntMap2;
                boolean z3;
                IdentityArrayMap identityArrayMap;
                Composition composition = (Composition) obj;
                Intrinsics.checkNotNullParameter(composition, "composition");
                i3 = RecomposeScopeImpl.this.currentToken;
                if (i3 == i) {
                    IdentityArrayIntMap identityArrayIntMap3 = identityArrayIntMap;
                    identityArrayIntMap2 = RecomposeScopeImpl.this.trackedInstances;
                    if (Intrinsics.areEqual(identityArrayIntMap3, identityArrayIntMap2) && (composition instanceof CompositionImpl)) {
                        IdentityArrayIntMap identityArrayIntMap4 = identityArrayIntMap;
                        int i4 = i;
                        RecomposeScopeImpl recomposeScopeImpl = RecomposeScopeImpl.this;
                        int size2 = identityArrayIntMap4.getSize();
                        int i5 = 0;
                        int i6 = 0;
                        while (true) {
                            DerivedState derivedState = null;
                            if (i5 >= size2) {
                                break;
                            }
                            Object obj2 = identityArrayIntMap4.getKeys()[i5];
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Any");
                            int i7 = identityArrayIntMap4.getValues()[i5];
                            if (i7 != i4) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (z3) {
                                CompositionImpl compositionImpl = (CompositionImpl) composition;
                                compositionImpl.removeObservation$runtime_release(recomposeScopeImpl, obj2);
                                if (obj2 instanceof DerivedState) {
                                    derivedState = (DerivedState) obj2;
                                }
                                if (derivedState != null) {
                                    compositionImpl.removeDerivedStateObservation$runtime_release(derivedState);
                                    identityArrayMap = recomposeScopeImpl.trackedDependencies;
                                    if (identityArrayMap != null) {
                                        identityArrayMap.remove(derivedState);
                                        if (identityArrayMap.getSize$runtime_release() == 0) {
                                            recomposeScopeImpl.trackedDependencies = null;
                                        }
                                    }
                                }
                            }
                            if (!z3) {
                                if (i6 != i5) {
                                    identityArrayIntMap4.getKeys()[i6] = obj2;
                                    identityArrayIntMap4.getValues()[i6] = i7;
                                }
                                i6++;
                            }
                            i5++;
                        }
                        int size3 = identityArrayIntMap4.getSize();
                        for (int i8 = i6; i8 < size3; i8++) {
                            identityArrayIntMap4.getKeys()[i8] = null;
                        }
                        identityArrayIntMap4.setSize(i6);
                        if (identityArrayIntMap.getSize() == 0) {
                            RecomposeScopeImpl.this.trackedInstances = null;
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        };
    }

    public final Anchor getAnchor() {
        return this.anchor;
    }

    public final boolean getCanRecompose() {
        if (this.block != null) {
            return true;
        }
        return false;
    }

    public final CompositionImpl getComposition() {
        return this.composition;
    }

    public final boolean getDefaultsInScope() {
        if ((this.flags & 2) != 0) {
            return true;
        }
        return false;
    }

    public final boolean getDefaultsInvalid() {
        if ((this.flags & 4) != 0) {
            return true;
        }
        return false;
    }

    public final boolean getRequiresRecompose() {
        if ((this.flags & 8) != 0) {
            return true;
        }
        return false;
    }

    public final boolean getSkipped$runtime_release() {
        if ((this.flags & 16) != 0) {
            return true;
        }
        return false;
    }

    public final boolean getUsed() {
        if ((this.flags & 1) != 0) {
            return true;
        }
        return false;
    }

    public final boolean getValid() {
        boolean z;
        if (this.composition == null) {
            return false;
        }
        Anchor anchor = this.anchor;
        if (anchor != null) {
            z = anchor.getValid();
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        return true;
    }

    public final void invalidate() {
        CompositionImpl compositionImpl = this.composition;
        if (compositionImpl != null) {
            compositionImpl.invalidate(this, null);
        }
    }

    public final InvalidationResult invalidateForResult(Object obj) {
        InvalidationResult invalidate;
        CompositionImpl compositionImpl = this.composition;
        if (compositionImpl == null || (invalidate = compositionImpl.invalidate(this, obj)) == null) {
            return InvalidationResult.IGNORED;
        }
        return invalidate;
    }

    public final boolean isConditional() {
        if (this.trackedDependencies != null) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0049 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isInvalidFor(androidx.compose.runtime.collection.IdentityArraySet r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != 0) goto L4
            return r0
        L4:
            androidx.compose.runtime.collection.IdentityArrayMap r5 = r5.trackedDependencies
            if (r5 != 0) goto L9
            return r0
        L9:
            boolean r1 = r6.isNotEmpty()
            if (r1 == 0) goto L4d
            boolean r1 = r6.isEmpty()
            r2 = 0
            if (r1 == 0) goto L18
        L16:
            r5 = r0
            goto L4a
        L18:
            java.util.Iterator r6 = r6.iterator()
        L1c:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L16
            java.lang.Object r1 = r6.next()
            boolean r3 = r1 instanceof androidx.compose.runtime.DerivedState
            if (r3 == 0) goto L46
            androidx.compose.runtime.DerivedState r1 = (androidx.compose.runtime.DerivedState) r1
            androidx.compose.runtime.DerivedSnapshotState r1 = (androidx.compose.runtime.DerivedSnapshotState) r1
            androidx.compose.runtime.SnapshotMutationPolicy r3 = r1.getPolicy()
            if (r3 != 0) goto L36
            androidx.compose.runtime.StructuralEqualityPolicy r3 = androidx.compose.runtime.StructuralEqualityPolicy.INSTANCE
        L36:
            java.lang.Object r4 = r1.getCurrentValue()
            java.lang.Object r1 = r5.get(r1)
            boolean r1 = r3.equivalent(r4, r1)
            if (r1 == 0) goto L46
            r1 = r0
            goto L47
        L46:
            r1 = r2
        L47:
            if (r1 != 0) goto L1c
            r5 = r2
        L4a:
            if (r5 == 0) goto L4d
            return r2
        L4d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.isInvalidFor(androidx.compose.runtime.collection.IdentityArraySet):boolean");
    }

    public final void recordRead(Object instance) {
        boolean z;
        Intrinsics.checkNotNullParameter(instance, "instance");
        if ((this.flags & 32) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        IdentityArrayIntMap identityArrayIntMap = this.trackedInstances;
        if (identityArrayIntMap == null) {
            identityArrayIntMap = new IdentityArrayIntMap();
            this.trackedInstances = identityArrayIntMap;
        }
        identityArrayIntMap.add(this.currentToken, instance);
        if (instance instanceof DerivedState) {
            IdentityArrayMap identityArrayMap = this.trackedDependencies;
            if (identityArrayMap == null) {
                identityArrayMap = new IdentityArrayMap();
                this.trackedDependencies = identityArrayMap;
            }
            identityArrayMap.set(instance, ((DerivedSnapshotState) ((DerivedState) instance)).getCurrentValue());
        }
    }

    public final void release() {
        this.composition = null;
        this.trackedInstances = null;
        this.trackedDependencies = null;
    }

    public final void rereadTrackedInstances() {
        IdentityArrayIntMap identityArrayIntMap;
        CompositionImpl compositionImpl = this.composition;
        if (compositionImpl != null && (identityArrayIntMap = this.trackedInstances) != null) {
            this.flags |= 32;
            try {
                int size = identityArrayIntMap.getSize();
                for (int i = 0; i < size; i++) {
                    Object obj = identityArrayIntMap.getKeys()[i];
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Any");
                    int i2 = identityArrayIntMap.getValues()[i];
                    compositionImpl.recordReadOf(obj);
                }
            } finally {
                this.flags &= -33;
            }
        }
    }

    public final void scopeSkipped() {
        this.flags |= 16;
    }

    public final void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public final void setDefaultsInScope() {
        this.flags |= 2;
    }

    public final void setDefaultsInvalid(boolean z) {
        if (z) {
            this.flags |= 4;
        } else {
            this.flags &= -5;
        }
    }

    public final void setRequiresRecompose(boolean z) {
        if (z) {
            this.flags |= 8;
        } else {
            this.flags &= -9;
        }
    }

    public final void setUsed() {
        this.flags |= 1;
    }

    public final void start(int i) {
        this.currentToken = i;
        this.flags &= -17;
    }

    public final void updateScope(Function2 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.block = block;
    }
}
