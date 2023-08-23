package androidx.compose.runtime;

import androidx.compose.runtime.CompositionImpl;
import androidx.compose.runtime.collection.IdentityArraySet;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ComposerKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Function3 removeCurrentGroupInstance = new Function3() { // from class: androidx.compose.runtime.ComposerKt$removeCurrentGroupInstance$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            SlotWriter slots = (SlotWriter) obj2;
            RememberManager rememberManager = (RememberManager) obj3;
            Intrinsics.checkNotNullParameter((Applier) obj, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(slots, "slots");
            Intrinsics.checkNotNullParameter(rememberManager, "rememberManager");
            ComposerKt.removeCurrentGroup(slots, rememberManager);
            return Unit.INSTANCE;
        }
    };
    private static final Function3 skipToGroupEndInstance = new Function3() { // from class: androidx.compose.runtime.ComposerKt$skipToGroupEndInstance$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            SlotWriter slots = (SlotWriter) obj2;
            Intrinsics.checkNotNullParameter((Applier) obj, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(slots, "slots");
            Intrinsics.checkNotNullParameter((RememberManager) obj3, "<anonymous parameter 2>");
            slots.skipToGroupEnd();
            return Unit.INSTANCE;
        }
    };
    private static final Function3 endGroupInstance = new Function3() { // from class: androidx.compose.runtime.ComposerKt$endGroupInstance$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            SlotWriter slots = (SlotWriter) obj2;
            Intrinsics.checkNotNullParameter((Applier) obj, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(slots, "slots");
            Intrinsics.checkNotNullParameter((RememberManager) obj3, "<anonymous parameter 2>");
            slots.endGroup();
            return Unit.INSTANCE;
        }
    };
    private static final Function3 startRootGroup = new Function3() { // from class: androidx.compose.runtime.ComposerKt$startRootGroup$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            SlotWriter slots = (SlotWriter) obj2;
            Intrinsics.checkNotNullParameter((Applier) obj, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(slots, "slots");
            Intrinsics.checkNotNullParameter((RememberManager) obj3, "<anonymous parameter 2>");
            slots.ensureStarted(0);
            return Unit.INSTANCE;
        }
    };
    private static final Function3 resetSlotsInstance = new Function3() { // from class: androidx.compose.runtime.ComposerKt$resetSlotsInstance$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            SlotWriter slots = (SlotWriter) obj2;
            Intrinsics.checkNotNullParameter((Applier) obj, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(slots, "slots");
            Intrinsics.checkNotNullParameter((RememberManager) obj3, "<anonymous parameter 2>");
            slots.reset();
            return Unit.INSTANCE;
        }
    };
    private static final OpaqueKey invocation = new OpaqueKey("provider");
    private static final OpaqueKey provider = new OpaqueKey("provider");
    private static final OpaqueKey compositionLocalMap = new OpaqueKey("compositionLocalMap");
    private static final OpaqueKey providerValues = new OpaqueKey("providerValues");
    private static final OpaqueKey providerMaps = new OpaqueKey("providers");
    private static final OpaqueKey reference = new OpaqueKey("reference");

    public static final List access$collectNodesFrom(SlotTable slotTable, Anchor anchor) {
        ArrayList arrayList = new ArrayList();
        SlotReader openReader = slotTable.openReader();
        try {
            collectNodesFrom$lambda$10$collectFromGroup(openReader, arrayList, slotTable.anchorIndex(anchor));
            return arrayList;
        } finally {
            openReader.close();
        }
    }

    public static final Invalidation access$firstInRange(List list, int i, int i2) {
        int findLocation = findLocation(list, i);
        if (findLocation < 0) {
            findLocation = -(findLocation + 1);
        }
        ArrayList arrayList = (ArrayList) list;
        if (findLocation < arrayList.size()) {
            Invalidation invalidation = (Invalidation) arrayList.get(findLocation);
            if (invalidation.getLocation() < i2) {
                return invalidation;
            }
        }
        return null;
    }

    public static final void access$insertIfMissing(List list, int i, RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        IdentityArraySet identityArraySet;
        int findLocation = findLocation(list, i);
        if (findLocation < 0) {
            int i2 = -(findLocation + 1);
            if (obj != null) {
                identityArraySet = new IdentityArraySet();
                identityArraySet.add(obj);
            } else {
                identityArraySet = null;
            }
            ((ArrayList) list).add(i2, new Invalidation(recomposeScopeImpl, i, identityArraySet));
        } else if (obj == null) {
            ((Invalidation) ((ArrayList) list).get(findLocation)).setInstances();
        } else {
            IdentityArraySet instances = ((Invalidation) ((ArrayList) list).get(findLocation)).getInstances();
            if (instances != null) {
                instances.add(obj);
            }
        }
    }

    public static final int access$nearestCommonRootOf(SlotReader slotReader, int i, int i2, int i3) {
        if (i != i2) {
            if (i != i3 && i2 != i3) {
                if (slotReader.parent(i) == i2) {
                    return i2;
                }
                if (slotReader.parent(i2) != i) {
                    if (slotReader.parent(i) == slotReader.parent(i2)) {
                        return slotReader.parent(i);
                    }
                    int i4 = i;
                    int i5 = 0;
                    while (i4 > 0 && i4 != i3) {
                        i4 = slotReader.parent(i4);
                        i5++;
                    }
                    int i6 = i2;
                    int i7 = 0;
                    while (i6 > 0 && i6 != i3) {
                        i6 = slotReader.parent(i6);
                        i7++;
                    }
                    int i8 = i5 - i7;
                    for (int i9 = 0; i9 < i8; i9++) {
                        i = slotReader.parent(i);
                    }
                    int i10 = i7 - i5;
                    for (int i11 = 0; i11 < i10; i11++) {
                        i2 = slotReader.parent(i2);
                    }
                    while (i != i2) {
                        i = slotReader.parent(i);
                        i2 = slotReader.parent(i2);
                    }
                    return i;
                }
                return i;
            }
            return i3;
        }
        return i;
    }

    public static final Invalidation access$removeLocation(List list, int i) {
        int findLocation = findLocation(list, i);
        if (findLocation >= 0) {
            return (Invalidation) ((ArrayList) list).remove(findLocation);
        }
        return null;
    }

    public static final void access$removeRange(List list, int i, int i2) {
        int findLocation = findLocation(list, i);
        if (findLocation < 0) {
            findLocation = -(findLocation + 1);
        }
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (findLocation < arrayList.size() && ((Invalidation) arrayList.get(findLocation)).getLocation() < i2) {
                arrayList.remove(findLocation);
            } else {
                return;
            }
        }
    }

    private static final void collectNodesFrom$lambda$10$collectFromGroup(SlotReader slotReader, List list, int i) {
        if (slotReader.isNode(i)) {
            ((ArrayList) list).add(slotReader.node(i));
            return;
        }
        int i2 = i + 1;
        int groupSize = slotReader.groupSize(i) + i;
        while (i2 < groupSize) {
            collectNodesFrom$lambda$10$collectFromGroup(slotReader, list, i2);
            i2 += slotReader.groupSize(i2);
        }
    }

    public static final void composeRuntimeError(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        throw new ComposeRuntimeError("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (" + message + "). Please report to Google or use https://goo.gle/compose-feedback");
    }

    private static final int findLocation(List list, int i) {
        int size = list.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            int compare = Intrinsics.compare(((Invalidation) list.get(i3)).getLocation(), i);
            if (compare < 0) {
                i2 = i3 + 1;
            } else if (compare > 0) {
                size = i3 - 1;
            } else {
                return i3;
            }
        }
        return -(i2 + 1);
    }

    public static final OpaqueKey getCompositionLocalMap() {
        return compositionLocalMap;
    }

    public static final OpaqueKey getInvocation() {
        return invocation;
    }

    public static final OpaqueKey getProvider() {
        return provider;
    }

    public static final OpaqueKey getProviderMaps() {
        return providerMaps;
    }

    public static final OpaqueKey getProviderValues() {
        return providerValues;
    }

    public static final OpaqueKey getReference() {
        return reference;
    }

    public static final void removeCurrentGroup(SlotWriter slotWriter, RememberManager rememberManager) {
        RecomposeScopeImpl recomposeScopeImpl;
        CompositionImpl composition;
        Intrinsics.checkNotNullParameter(slotWriter, "<this>");
        Intrinsics.checkNotNullParameter(rememberManager, "rememberManager");
        SlotWriter$groupSlots$1 groupSlots = slotWriter.groupSlots();
        while (groupSlots.hasNext()) {
            Object next = groupSlots.next();
            if (next instanceof RememberObserver) {
                ((CompositionImpl.RememberEventDispatcher) rememberManager).forgetting((RememberObserver) next);
            }
            if ((next instanceof RecomposeScopeImpl) && (composition = (recomposeScopeImpl = (RecomposeScopeImpl) next).getComposition()) != null) {
                composition.setPendingInvalidScopes$runtime_release();
                recomposeScopeImpl.release();
            }
        }
        slotWriter.removeGroup();
    }

    public static final void runtimeCheck(boolean z) {
        if (z) {
            return;
        }
        composeRuntimeError("Check failed".toString());
        throw null;
    }
}
