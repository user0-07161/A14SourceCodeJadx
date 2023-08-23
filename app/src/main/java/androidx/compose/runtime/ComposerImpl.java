package androidx.compose.runtime;

import android.os.Trace;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionImpl;
import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.IntMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.compose.ui.node.UiApplier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ComposerImpl implements Composer {
    private final Set abandonSet;
    private final Applier applier;
    private List changes;
    private int childrenComposing;
    private final ControlledComposition composition;
    private int compositionToken;
    private int compoundKeyHash;
    private Stack downNodes;
    private final IntStack entersStack;
    private boolean forceRecomposeScopes;
    private int groupNodeCount;
    private IntStack groupNodeCountStack;
    private boolean implicitRootStart;
    private Anchor insertAnchor;
    private final List insertFixups;
    private SlotTable insertTable;
    private final Stack insertUpFixups;
    private boolean inserting;
    private final Stack invalidateStack;
    private final List invalidations;
    private boolean isComposing;
    private List lateChanges;
    private int[] nodeCountOverrides;
    private HashMap nodeCountVirtualOverrides;
    private boolean nodeExpected;
    private int nodeIndex;
    private IntStack nodeIndexStack;
    private final CompositionContext parentContext;
    private PersistentHashMap parentProvider;
    private Pending pending;
    private final Stack pendingStack;
    private int pendingUps;
    private int previousCount;
    private int previousMoveFrom;
    private int previousMoveTo;
    private int previousRemove;
    private PersistentMap providerCache;
    private final IntMap providerUpdates;
    private boolean providersInvalid;
    private final IntStack providersInvalidStack;
    private SlotReader reader;
    private boolean reusing;
    private int reusingGroup;
    private final SlotTable slotTable;
    private boolean sourceInformationEnabled;
    private boolean startedGroup;
    private final IntStack startedGroups;
    private SlotWriter writer;
    private boolean writerHasAProvider;
    private int writersReaderDelta;

    public ComposerImpl(UiApplier uiApplier, CompositionContext compositionContext, SlotTable slotTable, Set set, List list, List list2, ControlledComposition composition) {
        Intrinsics.checkNotNullParameter(composition, "composition");
        this.applier = uiApplier;
        this.parentContext = compositionContext;
        this.slotTable = slotTable;
        this.abandonSet = set;
        this.changes = list;
        this.lateChanges = list2;
        this.composition = composition;
        this.pendingStack = new Stack();
        this.nodeIndexStack = new IntStack();
        this.groupNodeCountStack = new IntStack();
        this.invalidations = new ArrayList();
        this.entersStack = new IntStack();
        PersistentHashMap persistentHashMap = PersistentHashMap.EMPTY;
        Intrinsics.checkNotNull(persistentHashMap, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf>");
        this.parentProvider = persistentHashMap;
        this.providerUpdates = new IntMap();
        this.providersInvalidStack = new IntStack();
        this.reusingGroup = -1;
        SnapshotKt.currentSnapshot();
        this.sourceInformationEnabled = true;
        this.invalidateStack = new Stack();
        SlotReader openReader = slotTable.openReader();
        openReader.close();
        this.reader = openReader;
        SlotTable slotTable2 = new SlotTable();
        this.insertTable = slotTable2;
        SlotWriter openWriter = slotTable2.openWriter();
        openWriter.close();
        this.writer = openWriter;
        SlotReader openReader2 = this.insertTable.openReader();
        try {
            Anchor anchor = openReader2.anchor(0);
            openReader2.close();
            this.insertAnchor = anchor;
            this.insertFixups = new ArrayList();
            this.downNodes = new Stack();
            this.implicitRootStart = true;
            this.startedGroups = new IntStack();
            this.insertUpFixups = new Stack();
            this.previousRemove = -1;
            this.previousMoveFrom = -1;
            this.previousMoveTo = -1;
        } catch (Throwable th) {
            openReader2.close();
            throw th;
        }
    }

    private final void abortRoot() {
        cleanUpCompose();
        this.pendingStack.clear();
        this.nodeIndexStack.clear();
        this.groupNodeCountStack.clear();
        this.entersStack.clear();
        this.providersInvalidStack.clear();
        this.providerUpdates.clear();
        if (!this.reader.getClosed()) {
            this.reader.close();
        }
        if (!this.writer.getClosed()) {
            this.writer.close();
        }
        ComposerKt.runtimeCheck(this.writer.getClosed());
        SlotTable slotTable = new SlotTable();
        this.insertTable = slotTable;
        SlotWriter openWriter = slotTable.openWriter();
        openWriter.close();
        this.writer = openWriter;
        this.compoundKeyHash = 0;
        this.childrenComposing = 0;
        this.nodeExpected = false;
        this.inserting = false;
        this.reusing = false;
        this.isComposing = false;
    }

    public static final int access$insertMovableContentGuarded$positionToInsert(SlotWriter slotWriter, Anchor anchor, Applier applier) {
        boolean z;
        int nodeCount;
        int anchorIndex = slotWriter.anchorIndex(anchor);
        boolean z2 = true;
        if (slotWriter.getCurrentGroup() < anchorIndex) {
            z = true;
        } else {
            z = false;
        }
        ComposerKt.runtimeCheck(z);
        while (!slotWriter.indexInParent(anchorIndex)) {
            slotWriter.skipToGroupEnd();
            if (slotWriter.isNode(slotWriter.getParent())) {
                applier.up();
            }
            slotWriter.endGroup();
        }
        int currentGroup = slotWriter.getCurrentGroup();
        int parent = slotWriter.getParent();
        while (parent >= 0 && !slotWriter.isNode(parent)) {
            parent = slotWriter.parent(parent);
        }
        int i = parent + 1;
        int i2 = 0;
        while (i < currentGroup) {
            if (slotWriter.indexInGroup(currentGroup, i)) {
                if (slotWriter.isNode(i)) {
                    i2 = 0;
                }
                i++;
            } else {
                if (slotWriter.isNode(i)) {
                    nodeCount = 1;
                } else {
                    nodeCount = slotWriter.nodeCount(i);
                }
                i2 += nodeCount;
                i += slotWriter.groupSize(i);
            }
        }
        while (slotWriter.getCurrentGroup() < anchorIndex) {
            if (slotWriter.indexInCurrentGroup(anchorIndex)) {
                if (slotWriter.isNode()) {
                    applier.down(slotWriter.node(slotWriter.getCurrentGroup()));
                    i2 = 0;
                }
                slotWriter.startGroup();
            } else {
                i2 += slotWriter.skipGroup();
            }
        }
        if (slotWriter.getCurrentGroup() != anchorIndex) {
            z2 = false;
        }
        ComposerKt.runtimeCheck(z2);
        return i2;
    }

    public static final void access$insertMovableContentGuarded$positionToParentOf(SlotWriter slotWriter, Applier applier) {
        while (!slotWriter.indexInParent(0)) {
            slotWriter.skipToGroupEnd();
            if (slotWriter.isNode(slotWriter.getParent())) {
                applier.up();
            }
            slotWriter.endGroup();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f A[Catch: all -> 0x0063, TryCatch #0 {all -> 0x0063, blocks: (B:3:0x000d, B:5:0x0013, B:6:0x0018, B:14:0x002f, B:15:0x003a, B:9:0x001e), top: B:21:0x000d }] */
    /* JADX WARN: Type inference failed for: r0v8, types: [kotlin.jvm.internal.Lambda, androidx.compose.runtime.ComposerImpl$invokeMovableContentLambda$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void access$invokeMovableContentLambda(androidx.compose.runtime.ComposerImpl r6, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap r7, final java.lang.Object r8) {
        /*
            r0 = 126665345(0x78cc281, float:2.1179178E-34)
            r1 = 0
            r6.startMovableGroup(r0, r1)
            r6.changed(r8)
            int r1 = r6.compoundKeyHash
            r2 = 0
            r6.compoundKeyHash = r0     // Catch: java.lang.Throwable -> L63
            boolean r0 = r6.inserting     // Catch: java.lang.Throwable -> L63
            if (r0 == 0) goto L18
            androidx.compose.runtime.SlotWriter r0 = r6.writer     // Catch: java.lang.Throwable -> L63
            androidx.compose.runtime.SlotWriter.markGroup$default(r0)     // Catch: java.lang.Throwable -> L63
        L18:
            boolean r0 = r6.inserting     // Catch: java.lang.Throwable -> L63
            r3 = 1
            if (r0 == 0) goto L1e
            goto L2c
        L1e:
            androidx.compose.runtime.SlotReader r0 = r6.reader     // Catch: java.lang.Throwable -> L63
            java.lang.Object r0 = r0.getGroupAux()     // Catch: java.lang.Throwable -> L63
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r7)     // Catch: java.lang.Throwable -> L63
            if (r0 != 0) goto L2c
            r0 = r3
            goto L2d
        L2c:
            r0 = r2
        L2d:
            if (r0 == 0) goto L3a
            androidx.compose.runtime.collection.IntMap r4 = r6.providerUpdates     // Catch: java.lang.Throwable -> L63
            androidx.compose.runtime.SlotReader r5 = r6.reader     // Catch: java.lang.Throwable -> L63
            int r5 = r5.getCurrentGroup()     // Catch: java.lang.Throwable -> L63
            r4.set(r5, r7)     // Catch: java.lang.Throwable -> L63
        L3a:
            androidx.compose.runtime.OpaqueKey r4 = androidx.compose.runtime.ComposerKt.getCompositionLocalMap()     // Catch: java.lang.Throwable -> L63
            r5 = 202(0xca, float:2.83E-43)
            r6.m20startBaiHCIY(r5, r2, r4, r7)     // Catch: java.lang.Throwable -> L63
            boolean r7 = r6.inserting     // Catch: java.lang.Throwable -> L63
            boolean r7 = r6.providersInvalid     // Catch: java.lang.Throwable -> L63
            r6.providersInvalid = r0     // Catch: java.lang.Throwable -> L63
            androidx.compose.runtime.ComposerImpl$invokeMovableContentLambda$1 r0 = new androidx.compose.runtime.ComposerImpl$invokeMovableContentLambda$1     // Catch: java.lang.Throwable -> L63
            r0.<init>()     // Catch: java.lang.Throwable -> L63
            r8 = 694380496(0x296367d0, float:5.049417E-14)
            androidx.compose.runtime.internal.ComposableLambdaImpl r8 = androidx.compose.runtime.internal.ComposableLambdaKt.composableLambdaInstance(r8, r0, r3)     // Catch: java.lang.Throwable -> L63
            androidx.compose.runtime.ActualJvm_jvmKt.invokeComposable(r6, r8)     // Catch: java.lang.Throwable -> L63
            r6.providersInvalid = r7     // Catch: java.lang.Throwable -> L63
            r6.end(r2)
            r6.compoundKeyHash = r1
            r6.end(r2)
            return
        L63:
            r7 = move-exception
            r6.end(r2)
            r6.compoundKeyHash = r1
            r6.end(r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.access$invokeMovableContentLambda(androidx.compose.runtime.ComposerImpl, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap, java.lang.Object):void");
    }

    private final void cleanUpCompose() {
        this.pending = null;
        this.nodeIndex = 0;
        this.groupNodeCount = 0;
        this.writersReaderDelta = 0;
        this.compoundKeyHash = 0;
        this.nodeExpected = false;
        this.startedGroup = false;
        this.startedGroups.clear();
        this.invalidateStack.clear();
        this.nodeCountOverrides = null;
        this.nodeCountVirtualOverrides = null;
    }

    private final int compoundKeyOf(int i, int i2, int i3) {
        int i4;
        Object groupAux;
        if (i != i2) {
            SlotReader slotReader = this.reader;
            if (slotReader.hasObjectKey(i)) {
                Object groupObjectKey = slotReader.groupObjectKey(i);
                if (groupObjectKey != null) {
                    if (groupObjectKey instanceof Enum) {
                        i4 = ((Enum) groupObjectKey).ordinal();
                    } else {
                        i4 = groupObjectKey.hashCode();
                    }
                } else {
                    i4 = 0;
                }
            } else {
                int groupKey = slotReader.groupKey(i);
                if (groupKey == 207 && (groupAux = slotReader.groupAux(i)) != null && !Intrinsics.areEqual(groupAux, Composer.Companion.getEmpty())) {
                    i4 = groupAux.hashCode();
                } else {
                    i4 = groupKey;
                }
            }
            if (i4 == 126665345) {
                return i4;
            }
            return Integer.rotateLeft(compoundKeyOf(this.reader.parent(i), i2, i3), 3) ^ i4;
        }
        return i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0054, code lost:
        r10 = (java.util.ArrayList) r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005b, code lost:
        if (r10.size() <= 1) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005d, code lost:
        kotlin.collections.CollectionsKt.sortWith(r10, new androidx.compose.runtime.ComposerImpl$doCompose$lambda$37$$inlined$sortBy$1());
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0065, code lost:
        r9.nodeIndex = 0;
        r9.isComposing = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0069, code lost:
        startRoot();
        r10 = nextSlot();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0070, code lost:
        if (r10 == r11) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0072, code lost:
        if (r11 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0074, code lost:
        updateValue(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
        androidx.compose.runtime.SnapshotStateKt__DerivedStateKt.observeDerivedStateRecalculations(new androidx.compose.runtime.ComposerImpl$doCompose$2$3(r9), new androidx.compose.runtime.ComposerImpl$doCompose$2$4(r9), new androidx.compose.runtime.ComposerImpl$doCompose$2$5(r11, r9, r10));
        endRoot();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008c, code lost:
        r9.isComposing = false;
        ((java.util.ArrayList) r4).clear();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0096, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void doCompose(androidx.compose.runtime.collection.IdentityArrayMap r10, final androidx.compose.runtime.internal.ComposableLambdaImpl r11) {
        /*
            r9 = this;
            boolean r0 = r9.isComposing
            r1 = 1
            r0 = r0 ^ r1
            if (r0 == 0) goto La8
            java.lang.String r0 = "Compose:recompose"
            android.os.Trace.beginSection(r0)
            androidx.compose.runtime.snapshots.Snapshot r0 = androidx.compose.runtime.snapshots.SnapshotKt.currentSnapshot()     // Catch: java.lang.Throwable -> La3
            int r0 = r0.getId()     // Catch: java.lang.Throwable -> La3
            r9.compositionToken = r0     // Catch: java.lang.Throwable -> La3
            androidx.compose.runtime.collection.IntMap r0 = r9.providerUpdates     // Catch: java.lang.Throwable -> La3
            r0.clear()     // Catch: java.lang.Throwable -> La3
            int r0 = r10.getSize$runtime_release()     // Catch: java.lang.Throwable -> La3
            r2 = 0
            r3 = r2
        L20:
            java.util.List r4 = r9.invalidations
            if (r3 >= r0) goto L54
            java.lang.Object[] r5 = r10.getKeys$runtime_release()     // Catch: java.lang.Throwable -> La3
            r5 = r5[r3]     // Catch: java.lang.Throwable -> La3
            java.lang.String r6 = "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r6)     // Catch: java.lang.Throwable -> La3
            java.lang.Object[] r6 = r10.getValues$runtime_release()     // Catch: java.lang.Throwable -> La3
            r6 = r6[r3]     // Catch: java.lang.Throwable -> La3
            androidx.compose.runtime.collection.IdentityArraySet r6 = (androidx.compose.runtime.collection.IdentityArraySet) r6     // Catch: java.lang.Throwable -> La3
            androidx.compose.runtime.RecomposeScopeImpl r5 = (androidx.compose.runtime.RecomposeScopeImpl) r5     // Catch: java.lang.Throwable -> La3
            androidx.compose.runtime.Anchor r7 = r5.getAnchor()     // Catch: java.lang.Throwable -> La3
            if (r7 == 0) goto L50
            int r7 = r7.getLocation$runtime_release()     // Catch: java.lang.Throwable -> La3
            androidx.compose.runtime.Invalidation r8 = new androidx.compose.runtime.Invalidation     // Catch: java.lang.Throwable -> La3
            r8.<init>(r5, r7, r6)     // Catch: java.lang.Throwable -> La3
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch: java.lang.Throwable -> La3
            r4.add(r8)     // Catch: java.lang.Throwable -> La3
            int r3 = r3 + 1
            goto L20
        L50:
            android.os.Trace.endSection()
            return
        L54:
            r10 = r4
            java.util.ArrayList r10 = (java.util.ArrayList) r10     // Catch: java.lang.Throwable -> La3
            int r0 = r10.size()     // Catch: java.lang.Throwable -> La3
            if (r0 <= r1) goto L65
            androidx.compose.runtime.ComposerImpl$doCompose$lambda$37$$inlined$sortBy$1 r0 = new androidx.compose.runtime.ComposerImpl$doCompose$lambda$37$$inlined$sortBy$1     // Catch: java.lang.Throwable -> La3
            r0.<init>()     // Catch: java.lang.Throwable -> La3
            kotlin.collections.CollectionsKt.sortWith(r10, r0)     // Catch: java.lang.Throwable -> La3
        L65:
            r9.nodeIndex = r2     // Catch: java.lang.Throwable -> La3
            r9.isComposing = r1     // Catch: java.lang.Throwable -> La3
            r9.startRoot()     // Catch: java.lang.Throwable -> L97
            java.lang.Object r10 = r9.nextSlot()     // Catch: java.lang.Throwable -> L97
            if (r10 == r11) goto L77
            if (r11 == 0) goto L77
            r9.updateValue(r11)     // Catch: java.lang.Throwable -> L97
        L77:
            androidx.compose.runtime.ComposerImpl$doCompose$2$3 r0 = new androidx.compose.runtime.ComposerImpl$doCompose$2$3     // Catch: java.lang.Throwable -> L97
            r0.<init>()     // Catch: java.lang.Throwable -> L97
            androidx.compose.runtime.ComposerImpl$doCompose$2$4 r1 = new androidx.compose.runtime.ComposerImpl$doCompose$2$4     // Catch: java.lang.Throwable -> L97
            r1.<init>()     // Catch: java.lang.Throwable -> L97
            androidx.compose.runtime.ComposerImpl$doCompose$2$5 r3 = new androidx.compose.runtime.ComposerImpl$doCompose$2$5     // Catch: java.lang.Throwable -> L97
            r3.<init>()     // Catch: java.lang.Throwable -> L97
            androidx.compose.runtime.SnapshotStateKt__DerivedStateKt.observeDerivedStateRecalculations(r0, r1, r3)     // Catch: java.lang.Throwable -> L97
            r9.endRoot()     // Catch: java.lang.Throwable -> L97
            r9.isComposing = r2     // Catch: java.lang.Throwable -> La3
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch: java.lang.Throwable -> La3
            r4.clear()     // Catch: java.lang.Throwable -> La3
            android.os.Trace.endSection()
            return
        L97:
            r10 = move-exception
            r9.isComposing = r2     // Catch: java.lang.Throwable -> La3
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch: java.lang.Throwable -> La3
            r4.clear()     // Catch: java.lang.Throwable -> La3
            r9.abortRoot()     // Catch: java.lang.Throwable -> La3
            throw r10     // Catch: java.lang.Throwable -> La3
        La3:
            r9 = move-exception
            android.os.Trace.endSection()
            throw r9
        La8:
            java.lang.String r9 = "Reentrant composition is not supported"
            java.lang.String r9 = r9.toString()
            androidx.compose.runtime.ComposerKt.composeRuntimeError(r9)
            r9 = 0
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.doCompose(androidx.compose.runtime.collection.IdentityArrayMap, androidx.compose.runtime.internal.ComposableLambdaImpl):void");
    }

    private final void doRecordDownsFor(int i, int i2) {
        if (i > 0 && i != i2) {
            doRecordDownsFor(this.reader.parent(i), i2);
            if (this.reader.isNode(i)) {
                this.downNodes.push(this.reader.node(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v5, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v6 */
    public final void end(boolean z) {
        int i;
        int i2;
        Function3 function3;
        boolean z2;
        SlotReader slotReader;
        int parent;
        Function3 function32;
        ?? r9;
        SlotReader slotReader2;
        int parent2;
        boolean z3;
        Function3 function33;
        Function3 function34;
        SlotReader slotReader3;
        int parent3;
        boolean z4;
        Function3 function35;
        HashSet hashSet;
        Function3 function36;
        int i3;
        int i4;
        boolean z5;
        Function3 function37;
        LinkedHashSet linkedHashSet;
        int i5;
        int i6;
        ArrayList arrayList;
        if (this.inserting) {
            int parent4 = this.writer.getParent();
            updateCompoundKeyWhenWeExitGroup(this.writer.groupKey(parent4), this.writer.groupObjectKey(parent4), this.writer.groupAux(parent4));
        } else {
            int parent5 = this.reader.getParent();
            updateCompoundKeyWhenWeExitGroup(this.reader.groupKey(parent5), this.reader.groupObjectKey(parent5), this.reader.groupAux(parent5));
        }
        int i7 = this.groupNodeCount;
        Pending pending = this.pending;
        List list = this.invalidations;
        IntStack intStack = this.startedGroups;
        if (pending == null || pending.getKeyInfos().size() <= 0) {
            i = i7;
        } else {
            List keyInfos = pending.getKeyInfos();
            List used = pending.getUsed();
            Intrinsics.checkNotNullParameter(used, "<this>");
            ArrayList arrayList2 = (ArrayList) used;
            HashSet hashSet2 = new HashSet(arrayList2.size());
            int size = arrayList2.size();
            for (int i8 = 0; i8 < size; i8++) {
                hashSet2.add(arrayList2.get(i8));
            }
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            int size2 = arrayList2.size();
            int size3 = keyInfos.size();
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            while (i9 < size3) {
                KeyInfo keyInfo = (KeyInfo) keyInfos.get(i9);
                if (!hashSet2.contains(keyInfo)) {
                    recordRemoveNode(pending.nodePositionOf(keyInfo) + pending.getStartIndex(), keyInfo.getNodes());
                    pending.updateNodeCount(keyInfo.getLocation(), 0);
                    hashSet = hashSet2;
                    this.writersReaderDelta = keyInfo.getLocation() - (this.reader.getCurrentGroup() - this.writersReaderDelta);
                    this.reader.reposition(keyInfo.getLocation());
                    reportFreeMovableContent$reportGroup(this, this.reader.getCurrentGroup(), false, 0);
                    realizeMovement();
                    function36 = ComposerKt.removeCurrentGroupInstance;
                    realizeOperationLocation(false);
                    if (this.reader.getSize() > 0) {
                        SlotReader slotReader4 = this.reader;
                        int parent6 = slotReader4.getParent();
                        i3 = i7;
                        i4 = size3;
                        if (intStack.peekOr(-2) != parent6) {
                            if (this.startedGroup || !this.implicitRootStart) {
                                z5 = false;
                            } else {
                                function37 = ComposerKt.startRootGroup;
                                z5 = false;
                                recordSlotTableOperation(false, function37);
                                this.startedGroup = true;
                            }
                            if (parent6 > 0) {
                                Anchor anchor = slotReader4.anchor(parent6);
                                intStack.push(parent6);
                                recordSlotTableOperation(z5, new ComposerImpl$recordSlotEditing$1(anchor));
                            }
                        }
                    } else {
                        i3 = i7;
                        i4 = size3;
                    }
                    record(function36);
                    this.writersReaderDelta = this.reader.getGroupSize() + this.writersReaderDelta;
                    this.reader.skipGroup();
                    ComposerKt.access$removeRange(list, keyInfo.getLocation(), this.reader.groupSize(keyInfo.getLocation()) + keyInfo.getLocation());
                } else {
                    i3 = i7;
                    hashSet = hashSet2;
                    i4 = size3;
                    if (!linkedHashSet2.contains(keyInfo)) {
                        if (i10 < size2) {
                            KeyInfo keyInfo2 = (KeyInfo) arrayList2.get(i10);
                            if (keyInfo2 != keyInfo) {
                                int nodePositionOf = pending.nodePositionOf(keyInfo2);
                                linkedHashSet2.add(keyInfo2);
                                i6 = i11;
                                if (nodePositionOf != i6) {
                                    int updatedNodeCountOf = pending.updatedNodeCountOf(keyInfo2);
                                    int startIndex = pending.getStartIndex() + nodePositionOf;
                                    int startIndex2 = pending.getStartIndex() + i6;
                                    if (updatedNodeCountOf > 0) {
                                        arrayList = arrayList2;
                                        int i12 = this.previousCount;
                                        if (i12 > 0) {
                                            linkedHashSet = linkedHashSet2;
                                            i5 = size2;
                                            if (this.previousMoveFrom == startIndex - i12 && this.previousMoveTo == startIndex2 - i12) {
                                                this.previousCount = i12 + updatedNodeCountOf;
                                            }
                                        } else {
                                            linkedHashSet = linkedHashSet2;
                                            i5 = size2;
                                        }
                                        realizeMovement();
                                        this.previousMoveFrom = startIndex;
                                        this.previousMoveTo = startIndex2;
                                        this.previousCount = updatedNodeCountOf;
                                    } else {
                                        arrayList = arrayList2;
                                        linkedHashSet = linkedHashSet2;
                                        i5 = size2;
                                    }
                                    pending.registerMoveNode(nodePositionOf, i6, updatedNodeCountOf);
                                } else {
                                    arrayList = arrayList2;
                                    linkedHashSet = linkedHashSet2;
                                    i5 = size2;
                                }
                            } else {
                                linkedHashSet = linkedHashSet2;
                                i5 = size2;
                                i6 = i11;
                                arrayList = arrayList2;
                                i9++;
                            }
                            i10++;
                            arrayList2 = arrayList;
                            hashSet2 = hashSet;
                            size3 = i4;
                            linkedHashSet2 = linkedHashSet;
                            size2 = i5;
                            i11 = pending.updatedNodeCountOf(keyInfo2) + i6;
                            i7 = i3;
                        } else {
                            arrayList2 = arrayList2;
                            hashSet2 = hashSet;
                            size3 = i4;
                            i7 = i3;
                            linkedHashSet2 = linkedHashSet2;
                            size2 = size2;
                            i11 = i11;
                        }
                    }
                }
                i9++;
                arrayList2 = arrayList2;
                hashSet2 = hashSet;
                size3 = i4;
                i7 = i3;
                linkedHashSet2 = linkedHashSet2;
                size2 = size2;
                i11 = i11;
            }
            i = i7;
            realizeMovement();
            if (keyInfos.size() > 0) {
                this.writersReaderDelta = this.reader.getGroupEnd() - (this.reader.getCurrentGroup() - this.writersReaderDelta);
                this.reader.skipToGroupEnd();
            }
        }
        int i13 = this.nodeIndex;
        while (!this.reader.isGroupEnd()) {
            int currentGroup = this.reader.getCurrentGroup();
            reportFreeMovableContent$reportGroup(this, this.reader.getCurrentGroup(), false, 0);
            realizeMovement();
            function34 = ComposerKt.removeCurrentGroupInstance;
            realizeOperationLocation(false);
            if (this.reader.getSize() > 0 && intStack.peekOr(-2) != (parent3 = (slotReader3 = this.reader).getParent())) {
                if (this.startedGroup || !this.implicitRootStart) {
                    z4 = false;
                } else {
                    function35 = ComposerKt.startRootGroup;
                    z4 = false;
                    recordSlotTableOperation(false, function35);
                    this.startedGroup = true;
                }
                if (parent3 > 0) {
                    Anchor anchor2 = slotReader3.anchor(parent3);
                    intStack.push(parent3);
                    recordSlotTableOperation(z4, new ComposerImpl$recordSlotEditing$1(anchor2));
                }
            }
            record(function34);
            this.writersReaderDelta = this.reader.getGroupSize() + this.writersReaderDelta;
            recordRemoveNode(i13, this.reader.skipGroup());
            ComposerKt.access$removeRange(list, currentGroup, this.reader.getCurrentGroup());
        }
        boolean z6 = this.inserting;
        if (z6) {
            List list2 = this.insertFixups;
            if (z) {
                ((ArrayList) list2).add(this.insertUpFixups.pop());
                i2 = 1;
            } else {
                i2 = i;
            }
            this.reader.endEmpty();
            int parent7 = this.writer.getParent();
            this.writer.endGroup();
            if (!this.reader.getInEmpty()) {
                int i14 = (-2) - parent7;
                this.writer.endInsert();
                this.writer.close();
                final Anchor anchor3 = this.insertAnchor;
                if (((ArrayList) list2).isEmpty()) {
                    final SlotTable slotTable = this.insertTable;
                    Function3 function38 = new Function3() { // from class: androidx.compose.runtime.ComposerImpl$recordInsert$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            SlotWriter slots = (SlotWriter) obj2;
                            Intrinsics.checkNotNullParameter((Applier) obj, "<anonymous parameter 0>");
                            Intrinsics.checkNotNullParameter(slots, "slots");
                            Intrinsics.checkNotNullParameter((RememberManager) obj3, "<anonymous parameter 2>");
                            slots.beginInsert();
                            SlotTable slots2 = SlotTable.this;
                            Anchor anchor4 = anchor3;
                            anchor4.getClass();
                            Intrinsics.checkNotNullParameter(slots2, "slots");
                            slots.moveFrom(slots2, slots2.anchorIndex(anchor4));
                            slots.endInsert();
                            return Unit.INSTANCE;
                        }
                    };
                    realizeOperationLocation(false);
                    if (this.reader.getSize() > 0 && intStack.peekOr(-2) != (parent2 = (slotReader2 = this.reader).getParent())) {
                        if (this.startedGroup || !this.implicitRootStart) {
                            z3 = false;
                        } else {
                            function33 = ComposerKt.startRootGroup;
                            z3 = false;
                            recordSlotTableOperation(false, function33);
                            this.startedGroup = true;
                        }
                        if (parent2 > 0) {
                            Anchor anchor4 = slotReader2.anchor(parent2);
                            intStack.push(parent2);
                            recordSlotTableOperation(z3, new ComposerImpl$recordSlotEditing$1(anchor4));
                        }
                    }
                    record(function38);
                    r9 = 0;
                } else {
                    final List mutableList = CollectionsKt.toMutableList(list2);
                    ((ArrayList) list2).clear();
                    realizeUps();
                    if (!this.downNodes.isEmpty()) {
                        record(new ComposerImpl$realizeDowns$1(this.downNodes.toArray()));
                        this.downNodes.clear();
                    }
                    final SlotTable slotTable2 = this.insertTable;
                    Function3 function39 = new Function3() { // from class: androidx.compose.runtime.ComposerImpl$recordInsert$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            Applier applier = (Applier) obj;
                            SlotWriter slotWriter = (SlotWriter) obj2;
                            RememberManager rememberManager = (RememberManager) obj3;
                            ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", slotWriter, "slots", rememberManager, "rememberManager");
                            SlotTable slotTable3 = SlotTable.this;
                            List list3 = mutableList;
                            SlotWriter openWriter = slotTable3.openWriter();
                            try {
                                int size4 = list3.size();
                                for (int i15 = 0; i15 < size4; i15++) {
                                    ((Function3) list3.get(i15)).invoke(applier, openWriter, rememberManager);
                                }
                                openWriter.close();
                                slotWriter.beginInsert();
                                SlotTable slots = SlotTable.this;
                                Anchor anchor5 = anchor3;
                                anchor5.getClass();
                                Intrinsics.checkNotNullParameter(slots, "slots");
                                slotWriter.moveFrom(slots, slots.anchorIndex(anchor5));
                                slotWriter.endInsert();
                                return Unit.INSTANCE;
                            } catch (Throwable th) {
                                openWriter.close();
                                throw th;
                            }
                        }
                    };
                    realizeOperationLocation(false);
                    if (this.reader.getSize() <= 0 || intStack.peekOr(-2) == (parent = (slotReader = this.reader).getParent())) {
                        z2 = false;
                    } else {
                        if (this.startedGroup || !this.implicitRootStart) {
                            z2 = false;
                        } else {
                            function32 = ComposerKt.startRootGroup;
                            z2 = false;
                            recordSlotTableOperation(false, function32);
                            this.startedGroup = true;
                        }
                        if (parent > 0) {
                            Anchor anchor5 = slotReader.anchor(parent);
                            intStack.push(parent);
                            recordSlotTableOperation(z2, new ComposerImpl$recordSlotEditing$1(anchor5));
                        }
                    }
                    record(function39);
                    r9 = z2;
                }
                this.inserting = r9;
                if (!this.slotTable.isEmpty()) {
                    updateNodeCount(i14, r9);
                    updateNodeCountOverrides(i14, i2);
                }
            }
        } else {
            if (z) {
                recordUp();
            }
            int parent8 = this.reader.getParent();
            if (intStack.peekOr(-1) <= parent8) {
                if (intStack.peekOr(-1) == parent8) {
                    intStack.pop();
                    function3 = ComposerKt.endGroupInstance;
                    recordSlotTableOperation(false, function3);
                }
                int parent9 = this.reader.getParent();
                int i15 = i;
                if (i15 != updatedNodeCount(parent9)) {
                    updateNodeCountOverrides(parent9, i15);
                }
                if (z) {
                    i15 = 1;
                }
                this.reader.endGroup();
                realizeMovement();
                i2 = i15;
            } else {
                ComposerKt.composeRuntimeError("Missed recording an endGroup".toString());
                throw null;
            }
        }
        Pending pending2 = (Pending) this.pendingStack.pop();
        if (pending2 != null && !z6) {
            pending2.setGroupIndex(pending2.getGroupIndex() + 1);
        }
        this.pending = pending2;
        this.nodeIndex = this.nodeIndexStack.pop() + i2;
        this.groupNodeCount = this.groupNodeCountStack.pop() + i2;
    }

    private final void endRoot() {
        Function3 function3;
        end(false);
        this.parentContext.getClass();
        end(false);
        if (this.startedGroup) {
            function3 = ComposerKt.endGroupInstance;
            recordSlotTableOperation(false, function3);
            this.startedGroup = false;
        }
        realizeUps();
        if (this.pendingStack.isEmpty()) {
            if (this.startedGroups.isEmpty()) {
                cleanUpCompose();
                this.reader.close();
                return;
            }
            ComposerKt.composeRuntimeError("Missed recording an endGroup()".toString());
            throw null;
        }
        ComposerKt.composeRuntimeError("Start/end imbalance".toString());
        throw null;
    }

    private final void enterGroup(boolean z, Pending pending) {
        this.pendingStack.push(this.pending);
        this.pending = pending;
        this.nodeIndexStack.push(this.nodeIndex);
        if (z) {
            this.nodeIndex = 0;
        }
        this.groupNodeCountStack.push(this.groupNodeCount);
        this.groupNodeCount = 0;
    }

    private final void insertMovableContentGuarded(List list) {
        Function3 function3;
        SlotTable slotTable$runtime_release;
        Anchor anchor$runtime_release;
        int[] iArr;
        List list2;
        ArrayList arrayList;
        SlotTable slotTable$runtime_release2;
        Function3 function32;
        SlotTable slotTable = this.slotTable;
        List list3 = this.lateChanges;
        List list4 = this.changes;
        try {
            this.changes = list3;
            function3 = ComposerKt.resetSlotsInstance;
            record(function3);
            ArrayList arrayList2 = (ArrayList) list;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Pair pair = (Pair) arrayList2.get(i);
                final MovableContentStateReference movableContentStateReference = (MovableContentStateReference) pair.component1();
                final MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) pair.component2();
                final Anchor anchor$runtime_release2 = movableContentStateReference.getAnchor$runtime_release();
                int anchorIndex = movableContentStateReference.getSlotTable$runtime_release().anchorIndex(anchor$runtime_release2);
                final Ref$IntRef ref$IntRef = new Ref$IntRef();
                realizeUps();
                record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        Applier applier = (Applier) obj;
                        SlotWriter slotWriter = (SlotWriter) obj2;
                        ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", slotWriter, "slots", (RememberManager) obj3, "<anonymous parameter 2>");
                        Ref$IntRef.this.element = ComposerImpl.access$insertMovableContentGuarded$positionToInsert(slotWriter, anchor$runtime_release2, applier);
                        return Unit.INSTANCE;
                    }
                });
                if (movableContentStateReference2 == null) {
                    if (Intrinsics.areEqual(movableContentStateReference.getSlotTable$runtime_release(), this.insertTable)) {
                        ComposerKt.runtimeCheck(this.writer.getClosed());
                        SlotTable slotTable2 = new SlotTable();
                        this.insertTable = slotTable2;
                        SlotWriter openWriter = slotTable2.openWriter();
                        openWriter.close();
                        this.writer = openWriter;
                    }
                    final SlotReader openReader = movableContentStateReference.getSlotTable$runtime_release().openReader();
                    openReader.reposition(anchorIndex);
                    this.writersReaderDelta = anchorIndex;
                    final ArrayList arrayList3 = new ArrayList();
                    recomposeMovableContent(null, null, null, EmptyList.INSTANCE, new Function0() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$2$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            List list5;
                            SlotReader slotReader;
                            int[] iArr2;
                            ComposerImpl composerImpl = ComposerImpl.this;
                            List list6 = arrayList3;
                            SlotReader slotReader2 = openReader;
                            MovableContentStateReference movableContentStateReference3 = movableContentStateReference;
                            list5 = composerImpl.changes;
                            try {
                                composerImpl.changes = list6;
                                slotReader = composerImpl.reader;
                                iArr2 = composerImpl.nodeCountOverrides;
                                composerImpl.nodeCountOverrides = null;
                                composerImpl.reader = slotReader2;
                                movableContentStateReference3.getClass();
                                ComposerImpl.access$invokeMovableContentLambda(composerImpl, movableContentStateReference3.getLocals$runtime_release(), movableContentStateReference3.getParameter$runtime_release());
                                composerImpl.reader = slotReader;
                                composerImpl.nodeCountOverrides = iArr2;
                                composerImpl.changes = list5;
                                return Unit.INSTANCE;
                            } catch (Throwable th) {
                                composerImpl.changes = list5;
                                throw th;
                            }
                        }
                    });
                    if (!arrayList3.isEmpty()) {
                        record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$2$2
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj, Object obj2, Object obj3) {
                                Applier applier = (Applier) obj;
                                SlotWriter slotWriter = (SlotWriter) obj2;
                                RememberManager rememberManager = (RememberManager) obj3;
                                ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", slotWriter, "slots", rememberManager, "rememberManager");
                                int i2 = Ref$IntRef.this.element;
                                if (i2 > 0) {
                                    applier = new OffsetApplier(applier, i2);
                                }
                                List list5 = arrayList3;
                                int size2 = list5.size();
                                for (int i3 = 0; i3 < size2; i3++) {
                                    ((Function3) list5.get(i3)).invoke(applier, slotWriter, rememberManager);
                                }
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    openReader.close();
                    arrayList = arrayList2;
                } else {
                    final MovableContentState movableContentStateResolve$runtime_release = this.parentContext.movableContentStateResolve$runtime_release(movableContentStateReference2);
                    if (movableContentStateResolve$runtime_release == null || (slotTable$runtime_release = movableContentStateResolve$runtime_release.getSlotTable$runtime_release()) == null) {
                        slotTable$runtime_release = movableContentStateReference2.getSlotTable$runtime_release();
                    }
                    if (movableContentStateResolve$runtime_release != null && (slotTable$runtime_release2 = movableContentStateResolve$runtime_release.getSlotTable$runtime_release()) != null) {
                        anchor$runtime_release = slotTable$runtime_release2.anchor();
                    } else {
                        anchor$runtime_release = movableContentStateReference2.getAnchor$runtime_release();
                    }
                    final List access$collectNodesFrom = ComposerKt.access$collectNodesFrom(slotTable$runtime_release, anchor$runtime_release);
                    if (!((ArrayList) access$collectNodesFrom).isEmpty()) {
                        record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$3
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj, Object obj2, Object obj3) {
                                Applier applier = (Applier) obj;
                                ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", (SlotWriter) obj2, "<anonymous parameter 1>", (RememberManager) obj3, "<anonymous parameter 2>");
                                int i2 = Ref$IntRef.this.element;
                                List list5 = access$collectNodesFrom;
                                int size2 = list5.size();
                                for (int i3 = 0; i3 < size2; i3++) {
                                    Object obj4 = list5.get(i3);
                                    int i4 = i2 + i3;
                                    applier.insertBottomUp(i4, obj4);
                                    applier.insertTopDown(i4, obj4);
                                }
                                return Unit.INSTANCE;
                            }
                        });
                        if (Intrinsics.areEqual(movableContentStateReference.getSlotTable$runtime_release(), slotTable)) {
                            int anchorIndex2 = slotTable.anchorIndex(anchor$runtime_release2);
                            updateNodeCount(anchorIndex2, updatedNodeCount(anchorIndex2) + ((ArrayList) access$collectNodesFrom).size());
                        }
                    }
                    record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$4
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            RecomposeScopeImpl recomposeScopeImpl;
                            CompositionContext compositionContext;
                            SlotWriter slotWriter = (SlotWriter) obj2;
                            ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m((Applier) obj, "<anonymous parameter 0>", slotWriter, "slots", (RememberManager) obj3, "<anonymous parameter 2>");
                            MovableContentState movableContentState = MovableContentState.this;
                            if (movableContentState == null) {
                                compositionContext = this.parentContext;
                                movableContentState = compositionContext.movableContentStateResolve$runtime_release(movableContentStateReference2);
                                if (movableContentState == null) {
                                    ComposerKt.composeRuntimeError("Could not resolve state for movable content");
                                    throw null;
                                }
                            }
                            List moveIntoGroupFrom = slotWriter.moveIntoGroupFrom(movableContentState.getSlotTable$runtime_release());
                            if (!moveIntoGroupFrom.isEmpty()) {
                                ControlledComposition composition$runtime_release = movableContentStateReference.getComposition$runtime_release();
                                Intrinsics.checkNotNull(composition$runtime_release, "null cannot be cast to non-null type androidx.compose.runtime.CompositionImpl");
                                CompositionImpl compositionImpl = (CompositionImpl) composition$runtime_release;
                                int size2 = moveIntoGroupFrom.size();
                                for (int i2 = 0; i2 < size2; i2++) {
                                    Object slot = slotWriter.slot((Anchor) moveIntoGroupFrom.get(i2));
                                    if (slot instanceof RecomposeScopeImpl) {
                                        recomposeScopeImpl = (RecomposeScopeImpl) slot;
                                    } else {
                                        recomposeScopeImpl = null;
                                    }
                                    if (recomposeScopeImpl != null) {
                                        recomposeScopeImpl.adoptedBy(compositionImpl);
                                    }
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    });
                    SlotReader openReader2 = slotTable$runtime_release.openReader();
                    SlotReader slotReader = this.reader;
                    int[] iArr2 = this.nodeCountOverrides;
                    this.nodeCountOverrides = null;
                    try {
                        this.reader = openReader2;
                        int anchorIndex3 = slotTable$runtime_release.anchorIndex(anchor$runtime_release);
                        openReader2.reposition(anchorIndex3);
                        this.writersReaderDelta = anchorIndex3;
                        final ArrayList arrayList4 = new ArrayList();
                        List list5 = this.changes;
                        try {
                            this.changes = arrayList4;
                            iArr = iArr2;
                            arrayList = arrayList2;
                            list2 = list5;
                        } catch (Throwable th) {
                            th = th;
                            iArr = iArr2;
                            list2 = list5;
                        }
                        try {
                            recomposeMovableContent(movableContentStateReference2.getComposition$runtime_release(), movableContentStateReference.getComposition$runtime_release(), Integer.valueOf(openReader2.getCurrentGroup()), movableContentStateReference2.getInvalidations$runtime_release(), new Function0() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$5$1$1$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    ComposerImpl composerImpl = ComposerImpl.this;
                                    movableContentStateReference.getClass();
                                    ComposerImpl.access$invokeMovableContentLambda(composerImpl, movableContentStateReference.getLocals$runtime_release(), movableContentStateReference.getParameter$runtime_release());
                                    return Unit.INSTANCE;
                                }
                            });
                            try {
                                this.changes = list2;
                                if (!arrayList4.isEmpty()) {
                                    record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$5$1$2
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                                            Applier applier = (Applier) obj;
                                            SlotWriter slotWriter = (SlotWriter) obj2;
                                            RememberManager rememberManager = (RememberManager) obj3;
                                            ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", slotWriter, "slots", rememberManager, "rememberManager");
                                            int i2 = Ref$IntRef.this.element;
                                            if (i2 > 0) {
                                                applier = new OffsetApplier(applier, i2);
                                            }
                                            List list6 = arrayList4;
                                            int size2 = list6.size();
                                            for (int i3 = 0; i3 < size2; i3++) {
                                                ((Function3) list6.get(i3)).invoke(applier, slotWriter, rememberManager);
                                            }
                                            return Unit.INSTANCE;
                                        }
                                    });
                                }
                                this.reader = slotReader;
                                this.nodeCountOverrides = iArr;
                                openReader2.close();
                            } catch (Throwable th2) {
                                th = th2;
                                this.reader = slotReader;
                                this.nodeCountOverrides = iArr;
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            this.changes = list2;
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        iArr = iArr2;
                    }
                }
                function32 = ComposerKt.skipToGroupEndInstance;
                record(function32);
                i++;
                arrayList2 = arrayList;
            }
            record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Applier applier = (Applier) obj;
                    SlotWriter slots = (SlotWriter) obj2;
                    Intrinsics.checkNotNullParameter(applier, "applier");
                    Intrinsics.checkNotNullParameter(slots, "slots");
                    Intrinsics.checkNotNullParameter((RememberManager) obj3, "<anonymous parameter 2>");
                    ComposerImpl.access$insertMovableContentGuarded$positionToParentOf(slots, applier);
                    slots.endGroup();
                    return Unit.INSTANCE;
                }
            });
            this.writersReaderDelta = 0;
            this.changes = list4;
        } catch (Throwable th5) {
            this.changes = list4;
            throw th5;
        }
    }

    private final void realizeMovement() {
        final int i = this.previousCount;
        this.previousCount = 0;
        if (i > 0) {
            final int i2 = this.previousRemove;
            if (i2 >= 0) {
                this.previousRemove = -1;
                Function3 function3 = new Function3() { // from class: androidx.compose.runtime.ComposerImpl$realizeMovement$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        Applier applier = (Applier) obj;
                        ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", (SlotWriter) obj2, "<anonymous parameter 1>", (RememberManager) obj3, "<anonymous parameter 2>");
                        applier.remove(i2, i);
                        return Unit.INSTANCE;
                    }
                };
                realizeUps();
                if (!this.downNodes.isEmpty()) {
                    record(new ComposerImpl$realizeDowns$1(this.downNodes.toArray()));
                    this.downNodes.clear();
                }
                record(function3);
                return;
            }
            final int i3 = this.previousMoveFrom;
            this.previousMoveFrom = -1;
            final int i4 = this.previousMoveTo;
            this.previousMoveTo = -1;
            Function3 function32 = new Function3() { // from class: androidx.compose.runtime.ComposerImpl$realizeMovement$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Applier applier = (Applier) obj;
                    ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", (SlotWriter) obj2, "<anonymous parameter 1>", (RememberManager) obj3, "<anonymous parameter 2>");
                    applier.move(i3, i4, i);
                    return Unit.INSTANCE;
                }
            };
            realizeUps();
            if (!this.downNodes.isEmpty()) {
                record(new ComposerImpl$realizeDowns$1(this.downNodes.toArray()));
                this.downNodes.clear();
            }
            record(function32);
        }
    }

    private final void realizeOperationLocation(boolean z) {
        int currentGroup;
        boolean z2;
        if (z) {
            currentGroup = this.reader.getParent();
        } else {
            currentGroup = this.reader.getCurrentGroup();
        }
        final int i = currentGroup - this.writersReaderDelta;
        if (i >= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (i > 0) {
                record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$realizeOperationLocation$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        SlotWriter slotWriter = (SlotWriter) obj2;
                        ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m((Applier) obj, "<anonymous parameter 0>", slotWriter, "slots", (RememberManager) obj3, "<anonymous parameter 2>");
                        slotWriter.advanceBy(i);
                        return Unit.INSTANCE;
                    }
                });
                this.writersReaderDelta = currentGroup;
                return;
            }
            return;
        }
        ComposerKt.composeRuntimeError("Tried to seek backward".toString());
        throw null;
    }

    private final void realizeUps() {
        final int i = this.pendingUps;
        if (i > 0) {
            this.pendingUps = 0;
            record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$realizeUps$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Applier applier = (Applier) obj;
                    ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", (SlotWriter) obj2, "<anonymous parameter 1>", (RememberManager) obj3, "<anonymous parameter 2>");
                    int i2 = i;
                    for (int i3 = 0; i3 < i2; i3++) {
                        applier.up();
                    }
                    return Unit.INSTANCE;
                }
            });
        }
    }

    private final Object recomposeMovableContent(ControlledComposition controlledComposition, ControlledComposition controlledComposition2, Integer num, List list, Function0 function0) {
        Object obj;
        int i;
        boolean z = this.implicitRootStart;
        boolean z2 = this.isComposing;
        int i2 = this.nodeIndex;
        try {
            this.implicitRootStart = false;
            this.isComposing = true;
            this.nodeIndex = 0;
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                Pair pair = (Pair) list.get(i3);
                RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) pair.component1();
                IdentityArraySet identityArraySet = (IdentityArraySet) pair.component2();
                if (identityArraySet != null) {
                    int size2 = identityArraySet.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        tryImminentInvalidation$runtime_release(recomposeScopeImpl, identityArraySet.get(i4));
                    }
                } else {
                    tryImminentInvalidation$runtime_release(recomposeScopeImpl, null);
                }
            }
            if (controlledComposition != null) {
                if (num != null) {
                    i = num.intValue();
                } else {
                    i = -1;
                }
                obj = ((CompositionImpl) controlledComposition).delegateInvalidations(controlledComposition2, i, function0);
                if (obj == null) {
                }
                return obj;
            }
            obj = function0.invoke();
            return obj;
        } finally {
            this.implicitRootStart = z;
            this.isComposing = z2;
            this.nodeIndex = i2;
        }
    }

    private final void recomposeToGroupEnd() {
        int i;
        boolean z = this.isComposing;
        this.isComposing = true;
        int parent = this.reader.getParent();
        int groupSize = this.reader.groupSize(parent) + parent;
        int i2 = this.nodeIndex;
        int i3 = this.compoundKeyHash;
        int i4 = this.groupNodeCount;
        List list = this.invalidations;
        Invalidation access$firstInRange = ComposerKt.access$firstInRange(list, this.reader.getCurrentGroup(), groupSize);
        int i5 = parent;
        boolean z2 = false;
        while (access$firstInRange != null) {
            int location = access$firstInRange.getLocation();
            ComposerKt.access$removeLocation(list, location);
            if (access$firstInRange.isInvalid()) {
                this.reader.reposition(location);
                int currentGroup = this.reader.getCurrentGroup();
                SlotReader slotReader = this.reader;
                int access$nearestCommonRootOf = ComposerKt.access$nearestCommonRootOf(slotReader, i5, currentGroup, parent);
                while (i5 > 0 && i5 != access$nearestCommonRootOf) {
                    if (slotReader.isNode(i5)) {
                        recordUp();
                    }
                    i5 = slotReader.parent(i5);
                }
                doRecordDownsFor(currentGroup, access$nearestCommonRootOf);
                int parent2 = this.reader.parent(currentGroup);
                while (parent2 != parent && !this.reader.isNode(parent2)) {
                    parent2 = this.reader.parent(parent2);
                }
                if (this.reader.isNode(parent2)) {
                    i = 0;
                } else {
                    i = i2;
                }
                if (parent2 != currentGroup) {
                    int updatedNodeCount = (updatedNodeCount(parent2) - this.reader.nodeCount(currentGroup)) + i;
                    while (i < updatedNodeCount && parent2 != location) {
                        parent2++;
                        while (parent2 < location) {
                            int groupSize2 = this.reader.groupSize(parent2) + parent2;
                            if (location >= groupSize2) {
                                i += updatedNodeCount(parent2);
                                parent2 = groupSize2;
                            }
                        }
                        break;
                    }
                }
                this.nodeIndex = i;
                this.compoundKeyHash = compoundKeyOf(this.reader.parent(currentGroup), parent, i3);
                this.providerCache = null;
                access$firstInRange.getScope().compose(this);
                this.providerCache = null;
                this.reader.restoreParent(parent);
                i5 = currentGroup;
                z2 = true;
            } else {
                RecomposeScopeImpl scope = access$firstInRange.getScope();
                Stack stack = this.invalidateStack;
                stack.push(scope);
                access$firstInRange.getScope().rereadTrackedInstances();
                stack.pop();
            }
            access$firstInRange = ComposerKt.access$firstInRange(list, this.reader.getCurrentGroup(), groupSize);
        }
        if (z2) {
            SlotReader slotReader2 = this.reader;
            int access$nearestCommonRootOf2 = ComposerKt.access$nearestCommonRootOf(slotReader2, i5, parent, parent);
            while (i5 > 0 && i5 != access$nearestCommonRootOf2) {
                if (slotReader2.isNode(i5)) {
                    recordUp();
                }
                i5 = slotReader2.parent(i5);
            }
            doRecordDownsFor(parent, access$nearestCommonRootOf2);
            this.reader.skipToGroupEnd();
            int updatedNodeCount2 = updatedNodeCount(parent);
            this.nodeIndex = i2 + updatedNodeCount2;
            this.groupNodeCount = i4 + updatedNodeCount2;
        } else {
            this.groupNodeCount = this.reader.getParentNodes();
            this.reader.skipToGroupEnd();
        }
        this.compoundKeyHash = i3;
        this.isComposing = z;
    }

    private final void record(Function3 function3) {
        this.changes.add(function3);
    }

    private final void recordRemoveNode(int i, int i2) {
        boolean z;
        if (i2 > 0) {
            if (i >= 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                if (this.previousRemove == i) {
                    this.previousCount += i2;
                    return;
                }
                realizeMovement();
                this.previousRemove = i;
                this.previousCount = i2;
                return;
            }
            ComposerKt.composeRuntimeError(("Invalid remove index " + i).toString());
            throw null;
        }
    }

    private final void recordSlotTableOperation(boolean z, Function3 function3) {
        realizeOperationLocation(z);
        record(function3);
    }

    private final void recordUp() {
        if (!this.downNodes.isEmpty()) {
            this.downNodes.pop();
        } else {
            this.pendingUps++;
        }
    }

    private static final int reportFreeMovableContent$reportGroup(ComposerImpl composerImpl, int i, boolean z, int i2) {
        boolean z2;
        int i3;
        if (composerImpl.reader.hasMark(i)) {
            int groupKey = composerImpl.reader.groupKey(i);
            Object groupObjectKey = composerImpl.reader.groupObjectKey(i);
            if (groupKey == 206 && Intrinsics.areEqual(groupObjectKey, ComposerKt.getReference())) {
                composerImpl.reader.groupGet(i, 0);
                return composerImpl.reader.nodeCount(i);
            }
            return composerImpl.reader.nodeCount(i);
        } else if (composerImpl.reader.containsMark(i)) {
            int groupSize = composerImpl.reader.groupSize(i) + i;
            int i4 = i + 1;
            int i5 = 0;
            while (i4 < groupSize) {
                boolean isNode = composerImpl.reader.isNode(i4);
                if (isNode) {
                    composerImpl.realizeMovement();
                    composerImpl.downNodes.push(composerImpl.reader.node(i4));
                }
                if (!isNode && !z) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (isNode) {
                    i3 = 0;
                } else {
                    i3 = i2 + i5;
                }
                i5 += reportFreeMovableContent$reportGroup(composerImpl, i4, z2, i3);
                if (isNode) {
                    composerImpl.realizeMovement();
                    composerImpl.recordUp();
                }
                i4 += composerImpl.reader.groupSize(i4);
            }
            return i5;
        } else {
            return composerImpl.reader.nodeCount(i);
        }
    }

    private static Object resolveCompositionLocal(ProvidableCompositionLocal key, PersistentMap persistentMap) {
        int i = ComposerKt.$r8$clinit;
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (((PersistentHashMap) persistentMap).containsKey(key)) {
            State state = (State) ((PersistentHashMap) persistentMap).get(key);
            if (state != null) {
                return state.getValue();
            }
            return null;
        }
        return key.getDefaultValueHolder$runtime_release().getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: start-BaiHCIY  reason: not valid java name */
    public final void m20startBaiHCIY(int i, int i2, Object obj, Object obj2) {
        boolean z;
        boolean z2;
        boolean z3;
        Function3 function3;
        Pending pending = null;
        if (!this.nodeExpected) {
            updateCompoundKeyWhenWeEnterGroup(i, obj, obj2);
            int i3 = 0;
            if (i2 != 0) {
                z = true;
            } else {
                z = false;
            }
            if (this.inserting) {
                this.reader.beginEmpty();
                int currentGroup = this.writer.getCurrentGroup();
                if (z) {
                    this.writer.startNode(i, Composer.Companion.getEmpty());
                } else if (obj2 != null) {
                    SlotWriter slotWriter = this.writer;
                    if (obj == null) {
                        obj = Composer.Companion.getEmpty();
                    }
                    slotWriter.startData(i, obj, obj2);
                } else {
                    SlotWriter slotWriter2 = this.writer;
                    if (obj == null) {
                        obj = Composer.Companion.getEmpty();
                    }
                    slotWriter2.startGroup(i, obj);
                }
                Pending pending2 = this.pending;
                if (pending2 != null) {
                    KeyInfo keyInfo = new KeyInfo(i, -1, (-2) - currentGroup, -1);
                    pending2.registerInsert(keyInfo, this.nodeIndex - pending2.getStartIndex());
                    pending2.recordUsed(keyInfo);
                }
                enterGroup(z, null);
                return;
            }
            if (i2 != 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2 && this.reusing) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (this.pending == null) {
                int groupKey = this.reader.getGroupKey();
                if (!z3 && groupKey == i && Intrinsics.areEqual(obj, this.reader.getGroupObjectKey())) {
                    startReaderGroup(obj2, z);
                } else {
                    this.pending = new Pending(this.reader.extractKeys(), this.nodeIndex);
                }
            }
            Pending pending3 = this.pending;
            if (pending3 != null) {
                KeyInfo next = pending3.getNext(i, obj);
                if (!z3 && next != null) {
                    pending3.recordUsed(next);
                    int location = next.getLocation();
                    this.nodeIndex = pending3.nodePositionOf(next) + pending3.getStartIndex();
                    int slotPositionOf = pending3.slotPositionOf(next);
                    final int groupIndex = slotPositionOf - pending3.getGroupIndex();
                    pending3.registerMoveSlot(slotPositionOf, pending3.getGroupIndex());
                    this.writersReaderDelta = location - (this.reader.getCurrentGroup() - this.writersReaderDelta);
                    this.reader.reposition(location);
                    if (groupIndex > 0) {
                        Function3 function32 = new Function3() { // from class: androidx.compose.runtime.ComposerImpl$start$2
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj3, Object obj4, Object obj5) {
                                SlotWriter slotWriter3 = (SlotWriter) obj4;
                                ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m((Applier) obj3, "<anonymous parameter 0>", slotWriter3, "slots", (RememberManager) obj5, "<anonymous parameter 2>");
                                slotWriter3.moveGroup(groupIndex);
                                return Unit.INSTANCE;
                            }
                        };
                        realizeOperationLocation(false);
                        if (this.reader.getSize() > 0) {
                            SlotReader slotReader = this.reader;
                            int parent = slotReader.getParent();
                            IntStack intStack = this.startedGroups;
                            if (intStack.peekOr(-2) != parent) {
                                if (!this.startedGroup && this.implicitRootStart) {
                                    function3 = ComposerKt.startRootGroup;
                                    recordSlotTableOperation(false, function3);
                                    this.startedGroup = true;
                                }
                                if (parent > 0) {
                                    Anchor anchor = slotReader.anchor(parent);
                                    intStack.push(parent);
                                    recordSlotTableOperation(false, new ComposerImpl$recordSlotEditing$1(anchor));
                                }
                            }
                        }
                        record(function32);
                    }
                    startReaderGroup(obj2, z);
                } else {
                    this.reader.beginEmpty();
                    this.inserting = true;
                    this.providerCache = null;
                    if (this.writer.getClosed()) {
                        SlotWriter openWriter = this.insertTable.openWriter();
                        this.writer = openWriter;
                        openWriter.skipToGroupEnd();
                        this.writerHasAProvider = false;
                        this.providerCache = null;
                    }
                    this.writer.beginInsert();
                    int currentGroup2 = this.writer.getCurrentGroup();
                    if (z) {
                        this.writer.startNode(i, Composer.Companion.getEmpty());
                    } else if (obj2 != null) {
                        SlotWriter slotWriter3 = this.writer;
                        if (obj == null) {
                            obj = Composer.Companion.getEmpty();
                        }
                        slotWriter3.startData(i, obj, obj2);
                    } else {
                        SlotWriter slotWriter4 = this.writer;
                        if (obj == null) {
                            obj = Composer.Companion.getEmpty();
                        }
                        slotWriter4.startGroup(i, obj);
                    }
                    this.insertAnchor = this.writer.anchor(currentGroup2);
                    KeyInfo keyInfo2 = new KeyInfo(i, -1, (-2) - currentGroup2, -1);
                    pending3.registerInsert(keyInfo2, this.nodeIndex - pending3.getStartIndex());
                    pending3.recordUsed(keyInfo2);
                    ArrayList arrayList = new ArrayList();
                    if (!z) {
                        i3 = this.nodeIndex;
                    }
                    pending = new Pending(arrayList, i3);
                }
            }
            enterGroup(z, pending);
            return;
        }
        ComposerKt.composeRuntimeError("A call to createNode(), emitNode() or useNode() expected".toString());
        throw null;
    }

    private final void startReaderGroup(final Object obj, boolean z) {
        if (z) {
            this.reader.startNode();
            return;
        }
        if (obj != null && this.reader.getGroupAux() != obj) {
            recordSlotTableOperation(false, new Function3() { // from class: androidx.compose.runtime.ComposerImpl$startReaderGroup$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    SlotWriter slotWriter = (SlotWriter) obj3;
                    ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m((Applier) obj2, "<anonymous parameter 0>", slotWriter, "slots", (RememberManager) obj4, "<anonymous parameter 2>");
                    slotWriter.updateAux(obj);
                    return Unit.INSTANCE;
                }
            });
        }
        this.reader.startGroup();
    }

    private final void startRoot() {
        PersistentHashMap persistentHashMap;
        SlotTable slotTable = this.slotTable;
        this.reader = slotTable.openReader();
        m20startBaiHCIY(100, 0, null, null);
        this.parentContext.getClass();
        persistentHashMap = CompositionContextKt.EmptyCompositionLocalMap;
        this.parentProvider = persistentHashMap;
        boolean z = this.providersInvalid;
        int i = ComposerKt.$r8$clinit;
        this.providersInvalidStack.push(z ? 1 : 0);
        this.providersInvalid = changed(this.parentProvider);
        this.providerCache = null;
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = false;
        }
        Set set = (Set) resolveCompositionLocal(InspectionTablesKt.getLocalInspectionTables(), this.parentProvider);
        if (set != null) {
            set.add(slotTable);
        }
        m20startBaiHCIY(1000, 0, null, null);
    }

    private final void updateCompoundKeyWhenWeEnterGroup(int i, Object obj, Object obj2) {
        if (obj == null) {
            if (obj2 != null && i == 207 && !Intrinsics.areEqual(obj2, Composer.Companion.getEmpty())) {
                this.compoundKeyHash = obj2.hashCode() ^ Integer.rotateLeft(this.compoundKeyHash, 3);
            } else {
                this.compoundKeyHash = i ^ Integer.rotateLeft(this.compoundKeyHash, 3);
            }
        } else if (obj instanceof Enum) {
            this.compoundKeyHash = ((Enum) obj).ordinal() ^ Integer.rotateLeft(this.compoundKeyHash, 3);
        } else {
            this.compoundKeyHash = obj.hashCode() ^ Integer.rotateLeft(this.compoundKeyHash, 3);
        }
    }

    private final void updateCompoundKeyWhenWeExitGroup(int i, Object obj, Object obj2) {
        if (obj == null) {
            if (obj2 != null && i == 207 && !Intrinsics.areEqual(obj2, Composer.Companion.getEmpty())) {
                updateCompoundKeyWhenWeExitGroupKeyHash(obj2.hashCode());
            } else {
                updateCompoundKeyWhenWeExitGroupKeyHash(i);
            }
        } else if (obj instanceof Enum) {
            updateCompoundKeyWhenWeExitGroupKeyHash(((Enum) obj).ordinal());
        } else {
            updateCompoundKeyWhenWeExitGroupKeyHash(obj.hashCode());
        }
    }

    private final void updateCompoundKeyWhenWeExitGroupKeyHash(int i) {
        this.compoundKeyHash = Integer.rotateRight(Integer.hashCode(i) ^ this.compoundKeyHash, 3);
    }

    private final void updateNodeCount(int i, int i2) {
        if (updatedNodeCount(i) != i2) {
            if (i < 0) {
                HashMap hashMap = this.nodeCountVirtualOverrides;
                if (hashMap == null) {
                    hashMap = new HashMap();
                    this.nodeCountVirtualOverrides = hashMap;
                }
                hashMap.put(Integer.valueOf(i), Integer.valueOf(i2));
                return;
            }
            int[] iArr = this.nodeCountOverrides;
            if (iArr == null) {
                int size = this.reader.getSize();
                int[] iArr2 = new int[size];
                Arrays.fill(iArr2, 0, size, -1);
                this.nodeCountOverrides = iArr2;
                iArr = iArr2;
            }
            iArr[i] = i2;
        }
    }

    private final void updateNodeCountOverrides(int i, int i2) {
        int updatedNodeCount = updatedNodeCount(i);
        if (updatedNodeCount != i2) {
            int i3 = i2 - updatedNodeCount;
            Stack stack = this.pendingStack;
            int size = stack.getSize() - 1;
            while (i != -1) {
                int updatedNodeCount2 = updatedNodeCount(i) + i3;
                updateNodeCount(i, updatedNodeCount2);
                int i4 = size;
                while (true) {
                    if (-1 < i4) {
                        Pending pending = (Pending) stack.peek(i4);
                        if (pending != null && pending.updateNodeCount(i, updatedNodeCount2)) {
                            size = i4 - 1;
                            break;
                        }
                        i4--;
                    } else {
                        break;
                    }
                }
                if (i < 0) {
                    i = this.reader.getParent();
                } else if (!this.reader.isNode(i)) {
                    i = this.reader.parent(i);
                } else {
                    return;
                }
            }
        }
    }

    private final PersistentHashMap updateProviderMapGroup(PersistentMap persistentMap, PersistentMap persistentMap2) {
        PersistentHashMap persistentHashMap = (PersistentHashMap) persistentMap;
        persistentHashMap.getClass();
        PersistentHashMapBuilder persistentHashMapBuilder = new PersistentHashMapBuilder(persistentHashMap);
        persistentHashMapBuilder.putAll(persistentMap2);
        PersistentHashMap build = persistentHashMapBuilder.build();
        m20startBaiHCIY(204, 0, ComposerKt.getProviderMaps(), null);
        changed(build);
        changed(persistentMap2);
        end(false);
        return build;
    }

    private final int updatedNodeCount(int i) {
        int i2;
        Integer num;
        if (i < 0) {
            HashMap hashMap = this.nodeCountVirtualOverrides;
            if (hashMap != null && (num = (Integer) hashMap.get(Integer.valueOf(i))) != null) {
                return num.intValue();
            }
            return 0;
        }
        int[] iArr = this.nodeCountOverrides;
        if (iArr != null && (i2 = iArr[i]) >= 0) {
            return i2;
        }
        return this.reader.nodeCount(i);
    }

    public final void apply(final Object obj, final Function2 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        Function3 function3 = new Function3() { // from class: androidx.compose.runtime.ComposerImpl$apply$operation$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj2, Object obj3, Object obj4) {
                Applier applier = (Applier) obj2;
                ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", (SlotWriter) obj3, "<anonymous parameter 1>", (RememberManager) obj4, "<anonymous parameter 2>");
                block.invoke(applier.getCurrent(), obj);
                return Unit.INSTANCE;
            }
        };
        if (this.inserting) {
            ((ArrayList) this.insertFixups).add(function3);
            return;
        }
        realizeUps();
        if (!this.downNodes.isEmpty()) {
            record(new ComposerImpl$realizeDowns$1(this.downNodes.toArray()));
            this.downNodes.clear();
        }
        record(function3);
    }

    public final boolean changed(Object obj) {
        if (Intrinsics.areEqual(nextSlot(), obj)) {
            return false;
        }
        updateValue(obj);
        return true;
    }

    public final boolean changedInstance(Object obj) {
        if (nextSlot() != obj) {
            updateValue(obj);
            return true;
        }
        return false;
    }

    public final void changesApplied$runtime_release() {
        this.providerUpdates.clear();
    }

    public final void collectParameterInformation() {
        this.forceRecomposeScopes = true;
    }

    public final void composeContent$runtime_release(IdentityArrayMap invalidationsRequested, ComposableLambdaImpl composableLambdaImpl) {
        Intrinsics.checkNotNullParameter(invalidationsRequested, "invalidationsRequested");
        if (this.changes.isEmpty()) {
            doCompose(invalidationsRequested, composableLambdaImpl);
        } else {
            ComposerKt.composeRuntimeError("Expected applyChanges() to have been called".toString());
            throw null;
        }
    }

    public final Object consume(ProvidableCompositionLocal key) {
        Intrinsics.checkNotNullParameter(key, "key");
        PersistentMap persistentMap = this.providerCache;
        if (persistentMap == null) {
            int parent = this.reader.getParent();
            if (this.inserting && this.writerHasAProvider) {
                int parent2 = this.writer.getParent();
                while (parent2 > 0) {
                    if (this.writer.groupKey(parent2) == 202 && Intrinsics.areEqual(this.writer.groupObjectKey(parent2), ComposerKt.getCompositionLocalMap())) {
                        Object groupAux = this.writer.groupAux(parent2);
                        Intrinsics.checkNotNull(groupAux, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap<androidx.compose.runtime.CompositionLocal<kotlin.Any?>, androidx.compose.runtime.State<kotlin.Any?>>{ androidx.compose.runtime.ComposerKt.CompositionLocalMap }");
                        persistentMap = (PersistentMap) groupAux;
                        this.providerCache = persistentMap;
                        break;
                    }
                    parent2 = this.writer.parent(parent2);
                }
            }
            if (this.reader.getSize() > 0) {
                while (parent > 0) {
                    if (this.reader.groupKey(parent) == 202 && Intrinsics.areEqual(this.reader.groupObjectKey(parent), ComposerKt.getCompositionLocalMap())) {
                        PersistentMap persistentMap2 = (PersistentMap) this.providerUpdates.get(parent);
                        if (persistentMap2 == null) {
                            Object groupAux2 = this.reader.groupAux(parent);
                            Intrinsics.checkNotNull(groupAux2, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap<androidx.compose.runtime.CompositionLocal<kotlin.Any?>, androidx.compose.runtime.State<kotlin.Any?>>{ androidx.compose.runtime.ComposerKt.CompositionLocalMap }");
                            persistentMap2 = (PersistentMap) groupAux2;
                        }
                        this.providerCache = persistentMap2;
                        persistentMap = persistentMap2;
                    } else {
                        parent = this.reader.parent(parent);
                    }
                }
            }
            persistentMap = this.parentProvider;
            this.providerCache = persistentMap;
        }
        return resolveCompositionLocal(key, persistentMap);
    }

    public final void createNode(final Function0 factory) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        if (this.nodeExpected) {
            this.nodeExpected = false;
            if (this.inserting) {
                final int peek = this.nodeIndexStack.peek();
                SlotWriter slotWriter = this.writer;
                final Anchor anchor = slotWriter.anchor(slotWriter.getParent());
                this.groupNodeCount++;
                ((ArrayList) this.insertFixups).add(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$createNode$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        Applier applier = (Applier) obj;
                        SlotWriter slotWriter2 = (SlotWriter) obj2;
                        ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", slotWriter2, "slots", (RememberManager) obj3, "<anonymous parameter 2>");
                        Object invoke = Function0.this.invoke();
                        slotWriter2.updateNode(anchor, invoke);
                        applier.insertTopDown(peek, invoke);
                        applier.down(invoke);
                        return Unit.INSTANCE;
                    }
                });
                this.insertUpFixups.push(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$createNode$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        Applier applier = (Applier) obj;
                        SlotWriter slotWriter2 = (SlotWriter) obj2;
                        ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m(applier, "applier", slotWriter2, "slots", (RememberManager) obj3, "<anonymous parameter 2>");
                        Anchor anchor2 = anchor;
                        Intrinsics.checkNotNullParameter(anchor2, "anchor");
                        Object node = slotWriter2.node(slotWriter2.anchorIndex(anchor2));
                        applier.up();
                        applier.insertBottomUp(peek, node);
                        return Unit.INSTANCE;
                    }
                });
                return;
            }
            ComposerKt.composeRuntimeError("createNode() can only be called when inserting".toString());
            throw null;
        }
        ComposerKt.composeRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected".toString());
        throw null;
    }

    public final void disableReusing() {
        this.reusing = false;
    }

    public final void dispose$runtime_release() {
        Trace.beginSection("Compose:Composer.dispose");
        try {
            this.parentContext.getClass();
            this.invalidateStack.clear();
            ((ArrayList) this.invalidations).clear();
            this.changes.clear();
            this.providerUpdates.clear();
            this.applier.clear();
        } finally {
            Trace.endSection();
        }
    }

    public final void enableReusing() {
        boolean z;
        if (this.reusingGroup >= 0) {
            z = true;
        } else {
            z = false;
        }
        this.reusing = z;
    }

    public final void endDefaults() {
        end(false);
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null && currentRecomposeScope$runtime_release.getUsed()) {
            currentRecomposeScope$runtime_release.setDefaultsInScope();
        }
    }

    public final void endMovableGroup() {
        end(false);
    }

    public final void endNode() {
        end(true);
    }

    public final void endProviders() {
        boolean z = false;
        end(false);
        end(false);
        int pop = this.providersInvalidStack.pop();
        int i = ComposerKt.$r8$clinit;
        if (pop != 0) {
            z = true;
        }
        this.providersInvalid = z;
        this.providerCache = null;
    }

    public final void endReplaceableGroup() {
        end(false);
    }

    public final RecomposeScopeImpl endRestartGroup() {
        RecomposeScopeImpl recomposeScopeImpl;
        Anchor anchor;
        final Function1 end;
        Stack stack = this.invalidateStack;
        RecomposeScopeImpl recomposeScopeImpl2 = null;
        if (!stack.isEmpty()) {
            recomposeScopeImpl = (RecomposeScopeImpl) stack.pop();
        } else {
            recomposeScopeImpl = null;
        }
        if (recomposeScopeImpl != null) {
            recomposeScopeImpl.setRequiresRecompose(false);
        }
        if (recomposeScopeImpl != null && (end = recomposeScopeImpl.end(this.compositionToken)) != null) {
            record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$endRestartGroup$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m((Applier) obj, "<anonymous parameter 0>", (SlotWriter) obj2, "<anonymous parameter 1>", (RememberManager) obj3, "<anonymous parameter 2>");
                    Function1.this.invoke(this.getComposition());
                    return Unit.INSTANCE;
                }
            });
        }
        if (recomposeScopeImpl != null && !recomposeScopeImpl.getSkipped$runtime_release() && (recomposeScopeImpl.getUsed() || this.forceRecomposeScopes)) {
            if (recomposeScopeImpl.getAnchor() == null) {
                if (this.inserting) {
                    SlotWriter slotWriter = this.writer;
                    anchor = slotWriter.anchor(slotWriter.getParent());
                } else {
                    SlotReader slotReader = this.reader;
                    anchor = slotReader.anchor(slotReader.getParent());
                }
                recomposeScopeImpl.setAnchor(anchor);
            }
            recomposeScopeImpl.setDefaultsInvalid(false);
            recomposeScopeImpl2 = recomposeScopeImpl;
        }
        end(false);
        return recomposeScopeImpl2;
    }

    public final Applier getApplier() {
        return this.applier;
    }

    public final CoroutineContext getApplyCoroutineContext() {
        return this.parentContext.getEffectCoroutineContext$runtime_release();
    }

    public final boolean getAreChildrenComposing$runtime_release() {
        if (this.childrenComposing > 0) {
            return true;
        }
        return false;
    }

    public final ControlledComposition getComposition() {
        return this.composition;
    }

    public final SlotTable getCompositionData() {
        return this.slotTable;
    }

    public final RecomposeScopeImpl getCurrentRecomposeScope$runtime_release() {
        if (this.childrenComposing == 0) {
            Stack stack = this.invalidateStack;
            if (!stack.isEmpty()) {
                return (RecomposeScopeImpl) stack.peek();
            }
        }
        return null;
    }

    public final boolean getDefaultsInvalid() {
        boolean z;
        if (this.providersInvalid) {
            return true;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null && currentRecomposeScope$runtime_release.getDefaultsInvalid()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        return false;
    }

    public final boolean getInserting() {
        return this.inserting;
    }

    public final boolean getSkipping() {
        boolean z;
        if (this.inserting || this.reusing || this.providersInvalid) {
            return false;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null && !currentRecomposeScope$runtime_release.getRequiresRecompose()) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        return true;
    }

    public final void insertMovableContentReferences(List list) {
        try {
            insertMovableContentGuarded(list);
            cleanUpCompose();
        } catch (Throwable th) {
            abortRoot();
            throw th;
        }
    }

    public final boolean isComposing$runtime_release() {
        return this.isComposing;
    }

    public final Object nextSlot() {
        if (this.inserting) {
            if (!this.nodeExpected) {
                return Composer.Companion.getEmpty();
            }
            ComposerKt.composeRuntimeError("A call to createNode(), emitNode() or useNode() expected".toString());
            throw null;
        }
        Object next = this.reader.next();
        if (this.reusing) {
            return Composer.Companion.getEmpty();
        }
        return next;
    }

    public final void prepareCompose$runtime_release(Function0 function0) {
        if (!this.isComposing) {
            this.isComposing = true;
            try {
                ((Recomposer$performRecompose$1$1) function0).invoke();
                return;
            } finally {
                this.isComposing = false;
            }
        }
        ComposerKt.composeRuntimeError("Preparing a composition while composing is not supported".toString());
        throw null;
    }

    public final boolean recompose$runtime_release(IdentityArrayMap invalidationsRequested) {
        Intrinsics.checkNotNullParameter(invalidationsRequested, "invalidationsRequested");
        if (this.changes.isEmpty()) {
            if (!invalidationsRequested.isNotEmpty() && !(!((ArrayList) this.invalidations).isEmpty())) {
                return false;
            }
            doCompose(invalidationsRequested, null);
            return !this.changes.isEmpty();
        }
        ComposerKt.composeRuntimeError("Expected applyChanges() to have been called".toString());
        throw null;
    }

    public final void recordSideEffect(final Function0 function0) {
        record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$recordSideEffect$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                RememberManager rememberManager = (RememberManager) obj3;
                ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m((Applier) obj, "<anonymous parameter 0>", (SlotWriter) obj2, "<anonymous parameter 1>", rememberManager, "rememberManager");
                ((CompositionImpl.RememberEventDispatcher) rememberManager).sideEffect(Function0.this);
                return Unit.INSTANCE;
            }
        });
    }

    public final void skipCurrentGroup() {
        if (((ArrayList) this.invalidations).isEmpty()) {
            this.groupNodeCount = this.reader.skipGroup() + this.groupNodeCount;
            return;
        }
        SlotReader slotReader = this.reader;
        int groupKey = slotReader.getGroupKey();
        Object groupObjectKey = slotReader.getGroupObjectKey();
        Object groupAux = slotReader.getGroupAux();
        updateCompoundKeyWhenWeEnterGroup(groupKey, groupObjectKey, groupAux);
        startReaderGroup(null, slotReader.isNode());
        recomposeToGroupEnd();
        slotReader.endGroup();
        updateCompoundKeyWhenWeExitGroup(groupKey, groupObjectKey, groupAux);
    }

    public final void skipToGroupEnd() {
        boolean z;
        if (this.groupNodeCount == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
            if (currentRecomposeScope$runtime_release != null) {
                currentRecomposeScope$runtime_release.scopeSkipped();
            }
            if (((ArrayList) this.invalidations).isEmpty()) {
                this.groupNodeCount = this.reader.getParentNodes();
                this.reader.skipToGroupEnd();
                return;
            }
            recomposeToGroupEnd();
            return;
        }
        ComposerKt.composeRuntimeError("No nodes can be emitted before calling skipAndEndGroup".toString());
        throw null;
    }

    public final void startDefaults() {
        m20startBaiHCIY(-127, 0, null, null);
    }

    public final void startMovableGroup(int i, Object obj) {
        m20startBaiHCIY(i, 0, obj, null);
    }

    public final void startProviders(final ProvidedValue[] values) {
        PersistentHashMap updateProviderMapGroup;
        boolean areEqual;
        Intrinsics.checkNotNullParameter(values, "values");
        final PersistentMap persistentMap = this.providerCache;
        IntMap intMap = this.providerUpdates;
        if (persistentMap == null) {
            int parent = this.reader.getParent();
            if (this.inserting && this.writerHasAProvider) {
                int parent2 = this.writer.getParent();
                while (parent2 > 0) {
                    if (this.writer.groupKey(parent2) == 202 && Intrinsics.areEqual(this.writer.groupObjectKey(parent2), ComposerKt.getCompositionLocalMap())) {
                        Object groupAux = this.writer.groupAux(parent2);
                        Intrinsics.checkNotNull(groupAux, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap<androidx.compose.runtime.CompositionLocal<kotlin.Any?>, androidx.compose.runtime.State<kotlin.Any?>>{ androidx.compose.runtime.ComposerKt.CompositionLocalMap }");
                        persistentMap = (PersistentMap) groupAux;
                        this.providerCache = persistentMap;
                        break;
                    }
                    parent2 = this.writer.parent(parent2);
                }
            }
            if (this.reader.getSize() > 0) {
                while (parent > 0) {
                    if (this.reader.groupKey(parent) == 202 && Intrinsics.areEqual(this.reader.groupObjectKey(parent), ComposerKt.getCompositionLocalMap())) {
                        PersistentMap persistentMap2 = (PersistentMap) intMap.get(parent);
                        if (persistentMap2 == null) {
                            Object groupAux2 = this.reader.groupAux(parent);
                            Intrinsics.checkNotNull(groupAux2, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap<androidx.compose.runtime.CompositionLocal<kotlin.Any?>, androidx.compose.runtime.State<kotlin.Any?>>{ androidx.compose.runtime.ComposerKt.CompositionLocalMap }");
                            persistentMap2 = (PersistentMap) groupAux2;
                        }
                        this.providerCache = persistentMap2;
                        persistentMap = persistentMap2;
                    } else {
                        parent = this.reader.parent(parent);
                    }
                }
            }
            persistentMap = this.parentProvider;
            this.providerCache = persistentMap;
        }
        m20startBaiHCIY(201, 0, ComposerKt.getProvider(), null);
        m20startBaiHCIY(203, 0, ComposerKt.getProviderValues(), null);
        Function2 function2 = new Function2() { // from class: androidx.compose.runtime.ComposerImpl$startProviders$currentProviders$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((Number) obj2).intValue();
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj);
                composerImpl.startReplaceableGroup(935231726);
                int i = ComposerKt.$r8$clinit;
                ProvidedValue[] providedValueArr = values;
                PersistentMap persistentMap3 = persistentMap;
                composerImpl.startReplaceableGroup(721128344);
                PersistentHashMap persistentHashMap = PersistentHashMap.EMPTY;
                Intrinsics.checkNotNull(persistentHashMap, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf>");
                PersistentHashMapBuilder persistentHashMapBuilder = new PersistentHashMapBuilder(persistentHashMap);
                for (ProvidedValue providedValue : providedValueArr) {
                    composerImpl.startReplaceableGroup(680853375);
                    if (!providedValue.getCanOverride()) {
                        CompositionLocal key = providedValue.getCompositionLocal();
                        Intrinsics.checkNotNullParameter(persistentMap3, "<this>");
                        Intrinsics.checkNotNullParameter(key, "key");
                        if (((PersistentHashMap) persistentMap3).containsKey(key)) {
                            composerImpl.endReplaceableGroup();
                        }
                    }
                    CompositionLocal compositionLocal = providedValue.getCompositionLocal();
                    Intrinsics.checkNotNull(compositionLocal, "null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>");
                    persistentHashMapBuilder.put(compositionLocal, providedValue.getCompositionLocal().provided$runtime_release(providedValue.getValue(), composerImpl));
                    composerImpl.endReplaceableGroup();
                }
                PersistentHashMap build = persistentHashMapBuilder.build();
                composerImpl.endReplaceableGroup();
                int i2 = ComposerKt.$r8$clinit;
                composerImpl.endReplaceableGroup();
                return build;
            }
        };
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, function2);
        PersistentMap persistentMap3 = (PersistentMap) function2.invoke(this, 1);
        end(false);
        if (this.inserting) {
            updateProviderMapGroup = updateProviderMapGroup(persistentMap, persistentMap3);
            this.writerHasAProvider = true;
        } else {
            Object groupGet = this.reader.groupGet(0);
            Intrinsics.checkNotNull(groupGet, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap<androidx.compose.runtime.CompositionLocal<kotlin.Any?>, androidx.compose.runtime.State<kotlin.Any?>>{ androidx.compose.runtime.ComposerKt.CompositionLocalMap }");
            PersistentMap persistentMap4 = (PersistentMap) groupGet;
            Object groupGet2 = this.reader.groupGet(1);
            Intrinsics.checkNotNull(groupGet2, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap<androidx.compose.runtime.CompositionLocal<kotlin.Any?>, androidx.compose.runtime.State<kotlin.Any?>>{ androidx.compose.runtime.ComposerKt.CompositionLocalMap }");
            PersistentMap persistentMap5 = (PersistentMap) groupGet2;
            if (getSkipping() && Intrinsics.areEqual(persistentMap5, persistentMap3)) {
                this.groupNodeCount = this.reader.skipGroup() + this.groupNodeCount;
                updateProviderMapGroup = persistentMap4;
            } else {
                updateProviderMapGroup = updateProviderMapGroup(persistentMap, persistentMap3);
                areEqual = true ^ Intrinsics.areEqual(updateProviderMapGroup, persistentMap4);
                if (areEqual && !this.inserting) {
                    intMap.set(this.reader.getCurrentGroup(), updateProviderMapGroup);
                }
                this.providersInvalidStack.push(this.providersInvalid ? 1 : 0);
                this.providersInvalid = areEqual;
                this.providerCache = updateProviderMapGroup;
                m20startBaiHCIY(202, 0, ComposerKt.getCompositionLocalMap(), updateProviderMapGroup);
            }
        }
        areEqual = false;
        if (areEqual) {
            intMap.set(this.reader.getCurrentGroup(), updateProviderMapGroup);
        }
        this.providersInvalidStack.push(this.providersInvalid ? 1 : 0);
        this.providersInvalid = areEqual;
        this.providerCache = updateProviderMapGroup;
        m20startBaiHCIY(202, 0, ComposerKt.getCompositionLocalMap(), updateProviderMapGroup);
    }

    public final void startReplaceableGroup(int i) {
        m20startBaiHCIY(i, 0, null, null);
    }

    public final ComposerImpl startRestartGroup(int i) {
        RecomposeScopeImpl recomposeScopeImpl;
        boolean z = false;
        m20startBaiHCIY(i, 0, null, null);
        boolean z2 = this.inserting;
        Stack stack = this.invalidateStack;
        ControlledComposition controlledComposition = this.composition;
        if (z2) {
            Intrinsics.checkNotNull(controlledComposition, "null cannot be cast to non-null type androidx.compose.runtime.CompositionImpl");
            RecomposeScopeImpl recomposeScopeImpl2 = new RecomposeScopeImpl((CompositionImpl) controlledComposition);
            stack.push(recomposeScopeImpl2);
            updateValue(recomposeScopeImpl2);
            recomposeScopeImpl2.start(this.compositionToken);
        } else {
            Invalidation access$removeLocation = ComposerKt.access$removeLocation(this.invalidations, this.reader.getParent());
            Object next = this.reader.next();
            if (Intrinsics.areEqual(next, Composer.Companion.getEmpty())) {
                Intrinsics.checkNotNull(controlledComposition, "null cannot be cast to non-null type androidx.compose.runtime.CompositionImpl");
                recomposeScopeImpl = new RecomposeScopeImpl((CompositionImpl) controlledComposition);
                updateValue(recomposeScopeImpl);
            } else {
                Intrinsics.checkNotNull(next, "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl");
                recomposeScopeImpl = (RecomposeScopeImpl) next;
            }
            if (access$removeLocation != null) {
                z = true;
            }
            recomposeScopeImpl.setRequiresRecompose(z);
            stack.push(recomposeScopeImpl);
            recomposeScopeImpl.start(this.compositionToken);
        }
        return this;
    }

    public final void startReusableNode() {
        m20startBaiHCIY(125, 2, null, null);
        this.nodeExpected = true;
    }

    public final boolean tryImminentInvalidation$runtime_release(RecomposeScopeImpl scope, Object obj) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Anchor anchor = scope.getAnchor();
        if (anchor == null) {
            return false;
        }
        SlotTable slots = this.slotTable;
        Intrinsics.checkNotNullParameter(slots, "slots");
        int anchorIndex = slots.anchorIndex(anchor);
        if (!this.isComposing || anchorIndex < this.reader.getCurrentGroup()) {
            return false;
        }
        ComposerKt.access$insertIfMissing(this.invalidations, anchorIndex, scope, obj);
        return true;
    }

    public final void updateValue(final Object obj) {
        boolean z = this.inserting;
        Set set = this.abandonSet;
        if (z) {
            this.writer.update(obj);
            if (obj instanceof RememberObserver) {
                record(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$updateValue$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        RememberManager rememberManager = (RememberManager) obj4;
                        ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m((Applier) obj2, "<anonymous parameter 0>", (SlotWriter) obj3, "<anonymous parameter 1>", rememberManager, "rememberManager");
                        ((CompositionImpl.RememberEventDispatcher) rememberManager).remembering((RememberObserver) obj);
                        return Unit.INSTANCE;
                    }
                });
                set.add(obj);
                return;
            }
            return;
        }
        final int groupSlotIndex = this.reader.getGroupSlotIndex() - 1;
        if (obj instanceof RememberObserver) {
            set.add(obj);
        }
        recordSlotTableOperation(true, new Function3() { // from class: androidx.compose.runtime.ComposerImpl$updateValue$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj2, Object obj3, Object obj4) {
                RecomposeScopeImpl recomposeScopeImpl;
                CompositionImpl composition;
                SlotWriter slotWriter = (SlotWriter) obj3;
                RememberManager rememberManager = (RememberManager) obj4;
                ComposerImpl$apply$operation$1$$ExternalSyntheticOutline0.m((Applier) obj2, "<anonymous parameter 0>", slotWriter, "slots", rememberManager, "rememberManager");
                Object obj5 = obj;
                if (obj5 instanceof RememberObserver) {
                    ((CompositionImpl.RememberEventDispatcher) rememberManager).remembering((RememberObserver) obj5);
                }
                Object obj6 = slotWriter.set(groupSlotIndex, obj);
                if (obj6 instanceof RememberObserver) {
                    ((CompositionImpl.RememberEventDispatcher) rememberManager).forgetting((RememberObserver) obj6);
                } else if ((obj6 instanceof RecomposeScopeImpl) && (composition = (recomposeScopeImpl = (RecomposeScopeImpl) obj6).getComposition()) != null) {
                    recomposeScopeImpl.release();
                    composition.setPendingInvalidScopes$runtime_release();
                }
                return Unit.INSTANCE;
            }
        });
    }

    public final void useNode() {
        if (this.nodeExpected) {
            this.nodeExpected = false;
            if (!this.inserting) {
                SlotReader slotReader = this.reader;
                this.downNodes.push(slotReader.node(slotReader.getParent()));
                return;
            }
            ComposerKt.composeRuntimeError("useNode() called while inserting".toString());
            throw null;
        }
        ComposerKt.composeRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected".toString());
        throw null;
    }

    public final boolean changed(boolean z) {
        Object nextSlot = nextSlot();
        if ((nextSlot instanceof Boolean) && z == ((Boolean) nextSlot).booleanValue()) {
            return false;
        }
        updateValue(Boolean.valueOf(z));
        return true;
    }

    public final boolean changed(float f) {
        Object nextSlot = nextSlot();
        if (nextSlot instanceof Float) {
            if (f == ((Number) nextSlot).floatValue()) {
                return false;
            }
        }
        updateValue(Float.valueOf(f));
        return true;
    }

    public final boolean changed(long j) {
        Object nextSlot = nextSlot();
        if ((nextSlot instanceof Long) && j == ((Number) nextSlot).longValue()) {
            return false;
        }
        updateValue(Long.valueOf(j));
        return true;
    }

    public final boolean changed(int i) {
        Object nextSlot = nextSlot();
        if ((nextSlot instanceof Integer) && i == ((Number) nextSlot).intValue()) {
            return false;
        }
        updateValue(Integer.valueOf(i));
        return true;
    }
}
