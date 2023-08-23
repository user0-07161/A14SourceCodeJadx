package androidx.compose.ui.platform;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.collection.ArraySet;
import androidx.collection.SparseArrayCompat;
import androidx.collection.SparseArrayCompatKt;
import androidx.collection.internal.ContainerHelpersKt;
import androidx.compose.ui.TempListUtilsKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.accessibility.CollectionInfoKt;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesAndroid;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.text.platform.AndroidAccessibilitySpannableString_androidKt;
import androidx.compose.ui.unit.IntSize;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.android.egg.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidComposeViewAccessibilityDelegateCompat extends AccessibilityDelegateCompat {
    private static final int[] AccessibilityActionsResourceIds = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
    private final String EXTRA_DATA_TEST_TRAVERSALAFTER_VAL;
    private final String EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL;
    private int accessibilityCursorPosition;
    private final AccessibilityManager accessibilityManager;
    private SparseArrayCompat actionIdToLabel;
    private final AbstractChannel boundsUpdateChannel;
    private boolean checkingForSemanticsChanges;
    private Map currentSemanticsNodes;
    private boolean currentSemanticsNodesInvalidated;
    private List enabledServices;
    private final AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0 enabledStateListener;
    private int focusedVirtualViewId;
    private final Handler handler;
    private int hoveredVirtualViewId;
    private HashMap idToAfterMap;
    private HashMap idToBeforeMap;
    private SparseArrayCompat labelToActionId;
    private AccessibilityNodeProviderCompat nodeProvider;
    private ArraySet paneDisplayed;
    private PendingTextTraversedEvent pendingTextTraversedEvent;
    private Map previousSemanticsNodes;
    private SemanticsNodeCopy previousSemanticsRoot;
    private Integer previousTraversedNode;
    private final List scrollObservationScopes;
    private final AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda2 semanticsChangeChecker;
    private final Function1 sendScrollEventIfNeededLambda;
    private final ArraySet subtreeChangedLayoutNodes;
    private final AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1 touchExplorationStateListener;
    private final AndroidComposeView view;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class MyNodeProvider extends AccessibilityNodeProvider {
        public MyNodeProvider() {
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public final void addExtraDataToAccessibilityNodeInfo(int i, AccessibilityNodeInfo info, String extraDataKey, Bundle bundle) {
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(extraDataKey, "extraDataKey");
            AndroidComposeViewAccessibilityDelegateCompat.this.addExtraDataToAccessibilityNodeInfoHelper(i, info, extraDataKey, bundle);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            return AndroidComposeViewAccessibilityDelegateCompat.access$createNodeInfo(AndroidComposeViewAccessibilityDelegateCompat.this, i);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public final boolean performAction(int i, int i2, Bundle bundle) {
            return AndroidComposeViewAccessibilityDelegateCompat.access$performActionHelper(AndroidComposeViewAccessibilityDelegateCompat.this, i, i2, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class PendingTextTraversedEvent {
        private final int action;
        private final int fromIndex;
        private final int granularity;
        private final SemanticsNode node;
        private final int toIndex;
        private final long traverseTime;

        public PendingTextTraversedEvent(SemanticsNode semanticsNode, int i, int i2, int i3, int i4, long j) {
            this.node = semanticsNode;
            this.action = i;
            this.granularity = i2;
            this.fromIndex = i3;
            this.toIndex = i4;
            this.traverseTime = j;
        }

        public final int getAction() {
            return this.action;
        }

        public final int getFromIndex() {
            return this.fromIndex;
        }

        public final int getGranularity() {
            return this.granularity;
        }

        public final SemanticsNode getNode() {
            return this.node;
        }

        public final int getToIndex() {
            return this.toIndex;
        }

        public final long getTraverseTime() {
            return this.traverseTime;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class SemanticsNodeCopy {
        private final Set children;
        private final SemanticsNode semanticsNode;
        private final SemanticsConfiguration unmergedConfig;

        public SemanticsNodeCopy(SemanticsNode semanticsNode, Map currentSemanticsNodes) {
            Intrinsics.checkNotNullParameter(semanticsNode, "semanticsNode");
            Intrinsics.checkNotNullParameter(currentSemanticsNodes, "currentSemanticsNodes");
            this.semanticsNode = semanticsNode;
            this.unmergedConfig = semanticsNode.getUnmergedConfig$ui_release();
            this.children = new LinkedHashSet();
            List replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            for (int i = 0; i < size; i++) {
                SemanticsNode semanticsNode2 = (SemanticsNode) replacedChildren$ui_release.get(i);
                if (currentSemanticsNodes.containsKey(Integer.valueOf(semanticsNode2.getId()))) {
                    this.children.add(Integer.valueOf(semanticsNode2.getId()));
                }
            }
        }

        public final Set getChildren() {
            return this.children;
        }

        public final SemanticsNode getSemanticsNode() {
            return this.semanticsNode;
        }

        public final SemanticsConfiguration getUnmergedConfig() {
            return this.unmergedConfig;
        }

        public final boolean hasPaneTitle() {
            return this.unmergedConfig.contains(SemanticsProperties.getPaneTitle());
        }
    }

    /* renamed from: $r8$lambda$A5854EBxUZ0AAY8o-ALxs0fffYE  reason: not valid java name */
    public static void m280$r8$lambda$A5854EBxUZ0AAY8oALxs0fffYE(AndroidComposeViewAccessibilityDelegateCompat this$0, boolean z) {
        List<AccessibilityServiceInfo> list;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            list = this$0.accessibilityManager.getEnabledAccessibilityServiceList(-1);
        } else {
            list = EmptyList.INSTANCE;
        }
        this$0.enabledServices = list;
    }

    /* JADX WARN: Code restructure failed: missing block: B:191:0x0507, code lost:
        if (r0.getAction() != null) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0514, code lost:
        if (r0.getAction() == null) goto L194;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v42, types: [java.util.Collection, java.util.Set, java.util.LinkedHashSet] */
    /* JADX WARN: Type inference failed for: r1v36, types: [androidx.compose.ui.text.AnnotatedString] */
    /* JADX WARN: Type inference failed for: r2v35, types: [java.util.Collection, java.util.Set, java.util.LinkedHashSet] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void $r8$lambda$OKeIiDIY_Qg4Z8rAuPxelsO_7S8(androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r26) {
        /*
            Method dump skipped, instructions count: 1662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.$r8$lambda$OKeIiDIY_Qg4Z8rAuPxelsO_7S8(androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat):void");
    }

    public static void $r8$lambda$si7RJd1YNpLYzgPw5VvhP8SsU3U(AndroidComposeViewAccessibilityDelegateCompat this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.enabledServices = this$0.accessibilityManager.getEnabledAccessibilityServiceList(-1);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r5v1, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda2] */
    public AndroidComposeViewAccessibilityDelegateCompat(AndroidComposeView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
        this.hoveredVirtualViewId = Integer.MIN_VALUE;
        Object systemService = view.getContext().getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        AccessibilityManager accessibilityManager = (AccessibilityManager) systemService;
        this.accessibilityManager = accessibilityManager;
        this.enabledStateListener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public final void onAccessibilityStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat.m280$r8$lambda$A5854EBxUZ0AAY8oALxs0fffYE(AndroidComposeViewAccessibilityDelegateCompat.this, z);
            }
        };
        this.touchExplorationStateListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat.$r8$lambda$si7RJd1YNpLYzgPw5VvhP8SsU3U(AndroidComposeViewAccessibilityDelegateCompat.this);
            }
        };
        this.enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        this.handler = new Handler(Looper.getMainLooper());
        this.nodeProvider = new AccessibilityNodeProviderCompat(new MyNodeProvider());
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        this.actionIdToLabel = new SparseArrayCompat();
        this.labelToActionId = new SparseArrayCompat();
        this.accessibilityCursorPosition = -1;
        this.subtreeChangedLayoutNodes = new ArraySet();
        this.boundsUpdateChannel = ChannelKt.Channel$default(-1, null, 6);
        this.currentSemanticsNodesInvalidated = true;
        this.currentSemanticsNodes = MapsKt.emptyMap();
        this.paneDisplayed = new ArraySet();
        this.idToBeforeMap = new HashMap();
        this.idToAfterMap = new HashMap();
        this.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL";
        this.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL";
        this.previousSemanticsNodes = new LinkedHashMap();
        this.previousSemanticsRoot = new SemanticsNodeCopy(view.getSemanticsOwner().getUnmergedRootSemanticsNode(), MapsKt.emptyMap());
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.1
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager$ui_release().addAccessibilityStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getEnabledStateListener$ui_release());
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager$ui_release().addTouchExplorationStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getTouchExplorationStateListener$ui_release());
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                AndroidComposeViewAccessibilityDelegateCompat.this.handler.removeCallbacks(AndroidComposeViewAccessibilityDelegateCompat.this.semanticsChangeChecker);
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager$ui_release().removeAccessibilityStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getEnabledStateListener$ui_release());
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager$ui_release().removeTouchExplorationStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getTouchExplorationStateListener$ui_release());
            }
        });
        this.semanticsChangeChecker = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeViewAccessibilityDelegateCompat.$r8$lambda$OKeIiDIY_Qg4Z8rAuPxelsO_7S8(AndroidComposeViewAccessibilityDelegateCompat.this);
            }
        };
        this.scrollObservationScopes = new ArrayList();
        this.sendScrollEventIfNeededLambda = new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendScrollEventIfNeededLambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ScrollObservationScope it = (ScrollObservationScope) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                AndroidComposeViewAccessibilityDelegateCompat.access$sendScrollEventIfNeeded(AndroidComposeViewAccessibilityDelegateCompat.this, it);
                return Unit.INSTANCE;
            }
        };
    }

    public static final AccessibilityNodeInfo access$createNodeInfo(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, int i) {
        Lifecycle.State state;
        boolean z;
        SpannableString spannableString;
        SpannableString spannableString2;
        String str;
        NodeCoordinator findCoordinatorToGetBounds$ui_release;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        AccessibilityAction accessibilityAction;
        ProgressBarRangeInfo progressBarRangeInfo;
        boolean z8;
        float current;
        boolean z9;
        boolean z10;
        int i2;
        String str2;
        boolean z11;
        boolean z12;
        boolean z13;
        String string;
        boolean z14;
        boolean z15;
        AnnotatedString annotatedString;
        boolean z16;
        boolean z17;
        boolean z18;
        boolean z19;
        boolean z20;
        boolean z21;
        boolean z22;
        String str3;
        boolean z23;
        View view;
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycle;
        AndroidComposeView androidComposeView = androidComposeViewAccessibilityDelegateCompat.view;
        AndroidComposeView.ViewTreeOwners viewTreeOwners = androidComposeView.getViewTreeOwners();
        if (viewTreeOwners != null && (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
            state = lifecycle.getCurrentState();
        } else {
            state = null;
        }
        if (state != Lifecycle.State.DESTROYED) {
            AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) androidComposeViewAccessibilityDelegateCompat.getCurrentSemanticsNodes().get(Integer.valueOf(i));
            if (semanticsNodeWithAdjustedBounds != null) {
                SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode();
                int i3 = -1;
                if (i == -1) {
                    ViewParent parentForAccessibility = ViewCompat.getParentForAccessibility(androidComposeView);
                    if (parentForAccessibility instanceof View) {
                        view = (View) parentForAccessibility;
                    } else {
                        view = null;
                    }
                    obtain.setParent(view);
                } else if (semanticsNode.getParent() != null) {
                    SemanticsNode parent = semanticsNode.getParent();
                    Intrinsics.checkNotNull(parent);
                    int id = parent.getId();
                    if (id != androidComposeView.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
                        i3 = id;
                    }
                    obtain.setParent(androidComposeView, i3);
                } else {
                    throw new IllegalStateException("semanticsNode " + i + " has null parent");
                }
                obtain.setSource(androidComposeView, i);
                Rect adjustedBounds = semanticsNodeWithAdjustedBounds.getAdjustedBounds();
                long m278localToScreenMKHz9U = androidComposeView.m278localToScreenMKHz9U(OffsetKt.Offset(adjustedBounds.left, adjustedBounds.top));
                long m278localToScreenMKHz9U2 = androidComposeView.m278localToScreenMKHz9U(OffsetKt.Offset(adjustedBounds.right, adjustedBounds.bottom));
                obtain.setBoundsInScreen(new Rect((int) Math.floor(Offset.m45getXimpl(m278localToScreenMKHz9U)), (int) Math.floor(Offset.m46getYimpl(m278localToScreenMKHz9U)), (int) Math.ceil(Offset.m45getXimpl(m278localToScreenMKHz9U2)), (int) Math.ceil(Offset.m46getYimpl(m278localToScreenMKHz9U2))));
                Intrinsics.checkNotNullParameter(semanticsNode, "semanticsNode");
                boolean z24 = true;
                if (!semanticsNode.isFake$ui_release() && semanticsNode.getReplacedChildren$ui_release().isEmpty() && AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$findClosestParentNode(semanticsNode.getLayoutNode$ui_release(), new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$populateAccessibilityNodeInfoProperties$isUnmergedLeafNode$1
                    /* JADX WARN: Code restructure failed: missing block: B:7:0x0018, code lost:
                        if (r0.isMergingSemanticsOfDescendants() == true) goto L7;
                     */
                    @Override // kotlin.jvm.functions.Function1
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object invoke(java.lang.Object r1) {
                        /*
                            r0 = this;
                            androidx.compose.ui.node.LayoutNode r1 = (androidx.compose.ui.node.LayoutNode) r1
                            java.lang.String r0 = "it"
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
                            androidx.compose.ui.node.SemanticsModifierNode r0 = androidx.compose.ui.semantics.SemanticsNodeKt.getOuterSemantics(r1)
                            if (r0 == 0) goto L1b
                            androidx.compose.ui.semantics.SemanticsConfiguration r0 = androidx.compose.ui.node.SemanticsModifierNodeKt.collapsedSemanticsConfiguration(r0)
                            if (r0 == 0) goto L1b
                            boolean r0 = r0.isMergingSemanticsOfDescendants()
                            r1 = 1
                            if (r0 != r1) goto L1b
                            goto L1c
                        L1b:
                            r1 = 0
                        L1c:
                            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$populateAccessibilityNodeInfoProperties$isUnmergedLeafNode$1.invoke(java.lang.Object):java.lang.Object");
                    }
                }) == null) {
                    z = true;
                } else {
                    z = false;
                }
                obtain.setClassName("android.view.View");
                Role role = (Role) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getRole());
                if (role != null) {
                    int m291unboximpl = role.m291unboximpl();
                    if (semanticsNode.isFake$ui_release() || semanticsNode.getReplacedChildren$ui_release().isEmpty()) {
                        if (role.m291unboximpl() == 4) {
                            z16 = true;
                        } else {
                            z16 = false;
                        }
                        if (z16) {
                            obtain.setRoleDescription(androidComposeView.getContext().getResources().getString(R.string.tab));
                        } else {
                            if (role.m291unboximpl() == 2) {
                                z17 = true;
                            } else {
                                z17 = false;
                            }
                            if (z17) {
                                obtain.setRoleDescription(androidComposeView.getContext().getResources().getString(R.string.switch_role));
                            } else {
                                if (m291unboximpl == 0) {
                                    z18 = true;
                                } else {
                                    z18 = false;
                                }
                                if (z18) {
                                    str3 = "android.widget.Button";
                                } else {
                                    if (m291unboximpl == 1) {
                                        z19 = true;
                                    } else {
                                        z19 = false;
                                    }
                                    if (z19) {
                                        str3 = "android.widget.CheckBox";
                                    } else {
                                        if (m291unboximpl == 3) {
                                            z20 = true;
                                        } else {
                                            z20 = false;
                                        }
                                        if (z20) {
                                            str3 = "android.widget.RadioButton";
                                        } else {
                                            if (m291unboximpl == 5) {
                                                z21 = true;
                                            } else {
                                                z21 = false;
                                            }
                                            if (z21) {
                                                str3 = "android.widget.ImageView";
                                            } else {
                                                if (m291unboximpl == 6) {
                                                    z22 = true;
                                                } else {
                                                    z22 = false;
                                                }
                                                if (z22) {
                                                    str3 = "android.widget.Spinner";
                                                } else {
                                                    str3 = null;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (role.m291unboximpl() == 5) {
                                    z23 = true;
                                } else {
                                    z23 = false;
                                }
                                if (!z23 || z || semanticsNode.getUnmergedConfig$ui_release().isMergingSemanticsOfDescendants()) {
                                    obtain.setClassName(str3);
                                }
                            }
                        }
                    }
                }
                if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$isTextField(semanticsNode)) {
                    obtain.setClassName("android.widget.EditText");
                }
                if (semanticsNode.getConfig().contains(SemanticsProperties.getText())) {
                    obtain.setClassName("android.widget.TextView");
                }
                obtain.setPackageName(androidComposeView.getContext().getPackageName());
                obtain.setImportantForAccessibility();
                List replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
                int size = replacedChildren$ui_release.size();
                for (int i4 = 0; i4 < size; i4++) {
                    SemanticsNode semanticsNode2 = (SemanticsNode) replacedChildren$ui_release.get(i4);
                    if (androidComposeViewAccessibilityDelegateCompat.getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode2.getId()))) {
                        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(androidComposeView.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(semanticsNode2.getLayoutNode$ui_release()));
                        obtain.addChild(androidComposeView, semanticsNode2.getId());
                    }
                }
                if (androidComposeViewAccessibilityDelegateCompat.focusedVirtualViewId == i) {
                    obtain.setAccessibilityFocused(true);
                    obtain.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                } else {
                    obtain.setAccessibilityFocused(false);
                    obtain.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_ACCESSIBILITY_FOCUS);
                }
                FontFamilyResolverImpl fontFamilyResolver = androidComposeView.getFontFamilyResolver();
                AnnotatedString textForTextField = getTextForTextField(semanticsNode.getUnmergedConfig$ui_release());
                if (textForTextField != null) {
                    spannableString = AndroidAccessibilitySpannableString_androidKt.toAccessibilitySpannableString(textForTextField, androidComposeView.getDensity(), fontFamilyResolver);
                } else {
                    spannableString = null;
                }
                SpannableString spannableString3 = (SpannableString) trimToSize(spannableString);
                List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getText());
                if (list != null && (annotatedString = (AnnotatedString) CollectionsKt.firstOrNull(list)) != null) {
                    spannableString2 = AndroidAccessibilitySpannableString_androidKt.toAccessibilitySpannableString(annotatedString, androidComposeView.getDensity(), fontFamilyResolver);
                } else {
                    spannableString2 = null;
                }
                SpannableString spannableString4 = (SpannableString) trimToSize(spannableString2);
                if (spannableString3 == null) {
                    spannableString3 = spannableString4;
                }
                obtain.setText(spannableString3);
                if (semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsProperties.getError())) {
                    obtain.setContentInvalid();
                    obtain.setError((CharSequence) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getError()));
                }
                obtain.setStateDescription((CharSequence) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getStateDescription()));
                ToggleableState toggleableState = (ToggleableState) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getToggleableState());
                if (toggleableState != null) {
                    obtain.setCheckable();
                    int ordinal = toggleableState.ordinal();
                    if (ordinal != 0) {
                        if (ordinal != 1) {
                            if (ordinal == 2 && obtain.getStateDescription() == null) {
                                obtain.setStateDescription(androidComposeView.getContext().getResources().getString(R.string.indeterminate));
                            }
                        } else {
                            obtain.setChecked(false);
                            if (role != null && role.m291unboximpl() == 2) {
                                z15 = true;
                            } else {
                                z15 = false;
                            }
                            if (z15 && obtain.getStateDescription() == null) {
                                obtain.setStateDescription(androidComposeView.getContext().getResources().getString(R.string.off));
                            }
                        }
                    } else {
                        obtain.setChecked(true);
                        if (role != null && role.m291unboximpl() == 2) {
                            z14 = true;
                        } else {
                            z14 = false;
                        }
                        if (z14 && obtain.getStateDescription() == null) {
                            obtain.setStateDescription(androidComposeView.getContext().getResources().getString(R.string.on));
                        }
                    }
                }
                Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getSelected());
                if (bool != null) {
                    boolean booleanValue = bool.booleanValue();
                    if (role != null && role.m291unboximpl() == 4) {
                        z13 = true;
                    } else {
                        z13 = false;
                    }
                    if (z13) {
                        obtain.setSelected(booleanValue);
                    } else {
                        obtain.setCheckable();
                        obtain.setChecked(booleanValue);
                        if (obtain.getStateDescription() == null) {
                            if (booleanValue) {
                                string = androidComposeView.getContext().getResources().getString(R.string.selected);
                            } else {
                                string = androidComposeView.getContext().getResources().getString(R.string.not_selected);
                            }
                            obtain.setStateDescription(string);
                        }
                    }
                }
                if (!semanticsNode.getUnmergedConfig$ui_release().isMergingSemanticsOfDescendants() || semanticsNode.getReplacedChildren$ui_release().isEmpty()) {
                    List list2 = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getContentDescription());
                    if (list2 != null) {
                        str = (String) CollectionsKt.firstOrNull(list2);
                    } else {
                        str = null;
                    }
                    obtain.setContentDescription(str);
                }
                String str4 = (String) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getTestTag());
                if (str4 != null) {
                    SemanticsNode semanticsNode3 = semanticsNode;
                    while (true) {
                        if (semanticsNode3 != null) {
                            if (semanticsNode3.getUnmergedConfig$ui_release().contains(SemanticsPropertiesAndroid.getTestTagsAsResourceId())) {
                                z12 = ((Boolean) semanticsNode3.getUnmergedConfig$ui_release().get(SemanticsPropertiesAndroid.getTestTagsAsResourceId())).booleanValue();
                                break;
                            }
                            semanticsNode3 = semanticsNode3.getParent();
                        } else {
                            z12 = false;
                            break;
                        }
                    }
                    if (z12) {
                        obtain.setViewIdResourceName(str4);
                    }
                }
                if (((Unit) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getHeading())) != null) {
                    obtain.setHeading(true);
                }
                obtain.setPassword(AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$isPassword(semanticsNode));
                obtain.setEditable(AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$isTextField(semanticsNode));
                obtain.setEnabled(AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$enabled(semanticsNode));
                obtain.setFocusable(semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsProperties.getFocused()));
                if (obtain.isFocusable()) {
                    obtain.setFocused(((Boolean) semanticsNode.getUnmergedConfig$ui_release().get(SemanticsProperties.getFocused())).booleanValue());
                    if (obtain.isFocused()) {
                        obtain.addAction(2);
                    } else {
                        obtain.addAction(1);
                    }
                }
                if (semanticsNode.isFake$ui_release()) {
                    SemanticsNode parent2 = semanticsNode.getParent();
                    if (parent2 != null) {
                        findCoordinatorToGetBounds$ui_release = parent2.findCoordinatorToGetBounds$ui_release();
                    } else {
                        findCoordinatorToGetBounds$ui_release = null;
                    }
                } else {
                    findCoordinatorToGetBounds$ui_release = semanticsNode.findCoordinatorToGetBounds$ui_release();
                }
                if (findCoordinatorToGetBounds$ui_release != null) {
                    z2 = findCoordinatorToGetBounds$ui_release.isTransparent();
                } else {
                    z2 = false;
                }
                if (!z2 && SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getInvisibleToUser()) == null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                obtain.setVisibleToUser(z3);
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getLiveRegion()));
                obtain.setClickable(false);
                AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getOnClick());
                if (accessibilityAction2 != null) {
                    boolean areEqual = Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getSelected()), Boolean.TRUE);
                    obtain.setClickable(!areEqual);
                    if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$enabled(semanticsNode) && !areEqual) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction2.getLabel(), 16));
                    }
                }
                obtain.setLongClickable(false);
                AccessibilityAction accessibilityAction3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getOnLongClick());
                if (accessibilityAction3 != null) {
                    obtain.setLongClickable(true);
                    if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$enabled(semanticsNode)) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction3.getLabel(), 32));
                    }
                }
                AccessibilityAction accessibilityAction4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getCopyText());
                if (accessibilityAction4 != null) {
                    obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction4.getLabel(), 16384));
                }
                if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$enabled(semanticsNode)) {
                    AccessibilityAction accessibilityAction5 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getSetText());
                    if (accessibilityAction5 != null) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction5.getLabel(), 2097152));
                    }
                    AccessibilityAction accessibilityAction6 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getCutText());
                    if (accessibilityAction6 != null) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction6.getLabel(), 65536));
                    }
                    AccessibilityAction accessibilityAction7 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getPasteText());
                    if (accessibilityAction7 != null && obtain.isFocused() && androidComposeView.getClipboardManager$1().hasText()) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction7.getLabel(), 32768));
                    }
                }
                String iterableTextForAccessibility = getIterableTextForAccessibility(semanticsNode);
                if (iterableTextForAccessibility != null && iterableTextForAccessibility.length() != 0) {
                    z4 = false;
                } else {
                    z4 = true;
                }
                if (!z4) {
                    obtain.setTextSelection(androidComposeViewAccessibilityDelegateCompat.getAccessibilitySelectionStart(semanticsNode), androidComposeViewAccessibilityDelegateCompat.getAccessibilitySelectionEnd(semanticsNode));
                    AccessibilityAction accessibilityAction8 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getSetSelection());
                    if (accessibilityAction8 != null) {
                        str2 = accessibilityAction8.getLabel();
                    } else {
                        str2 = null;
                    }
                    obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(str2, 131072));
                    obtain.addAction(256);
                    obtain.addAction(512);
                    obtain.setMovementGranularities(11);
                    List list3 = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getContentDescription());
                    if (list3 != null && !list3.isEmpty()) {
                        z11 = false;
                    } else {
                        z11 = true;
                    }
                    if (z11 && semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsActions.getGetTextLayoutResult()) && !AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$excludeLineAndPageGranularities(semanticsNode)) {
                        obtain.setMovementGranularities(obtain.getMovementGranularities() | 4 | 16);
                    }
                }
                ArrayList arrayList = new ArrayList();
                CharSequence text = obtain.getText();
                if (text != null && text.length() != 0) {
                    z5 = false;
                } else {
                    z5 = true;
                }
                if (!z5 && semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsActions.getGetTextLayoutResult())) {
                    arrayList.add("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY");
                }
                if (semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsProperties.getTestTag())) {
                    arrayList.add("androidx.compose.ui.semantics.testTag");
                }
                if (!arrayList.isEmpty()) {
                    AccessibilityNodeInfo unwrap = obtain.unwrap();
                    Intrinsics.checkNotNullExpressionValue(unwrap, "info.unwrap()");
                    unwrap.setAvailableExtraData(arrayList);
                }
                ProgressBarRangeInfo progressBarRangeInfo2 = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getProgressBarRangeInfo());
                if (progressBarRangeInfo2 != null) {
                    if (semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsActions.getSetProgress())) {
                        obtain.setClassName("android.widget.SeekBar");
                    } else {
                        obtain.setClassName("android.widget.ProgressBar");
                    }
                    progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate;
                    if (progressBarRangeInfo2 != progressBarRangeInfo) {
                        obtain.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(((Number) progressBarRangeInfo2.getRange().getStart()).floatValue(), ((Number) progressBarRangeInfo2.getRange().getEndInclusive()).floatValue(), progressBarRangeInfo2.getCurrent()));
                        if (obtain.getStateDescription() == null) {
                            ClosedFloatingPointRange range = progressBarRangeInfo2.getRange();
                            if (((Number) range.getEndInclusive()).floatValue() - ((Number) range.getStart()).floatValue() == 0.0f) {
                                z8 = true;
                            } else {
                                z8 = false;
                            }
                            if (z8) {
                                current = 0.0f;
                            } else {
                                current = (progressBarRangeInfo2.getCurrent() - ((Number) range.getStart()).floatValue()) / (((Number) range.getEndInclusive()).floatValue() - ((Number) range.getStart()).floatValue());
                            }
                            float coerceIn = RangesKt.coerceIn(current, 0.0f, 1.0f);
                            if (coerceIn == 0.0f) {
                                z9 = true;
                            } else {
                                z9 = false;
                            }
                            if (z9) {
                                i2 = 0;
                            } else {
                                if (coerceIn == 1.0f) {
                                    z10 = true;
                                } else {
                                    z10 = false;
                                }
                                i2 = 100;
                                if (!z10) {
                                    i2 = RangesKt.coerceIn(MathKt.roundToInt(coerceIn * 100), 1, 99);
                                }
                            }
                            obtain.setStateDescription(androidComposeView.getContext().getResources().getString(R.string.template_percent, Integer.valueOf(i2)));
                        }
                    } else if (obtain.getStateDescription() == null) {
                        obtain.setStateDescription(androidComposeView.getContext().getResources().getString(R.string.in_progress));
                    }
                    if (semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsActions.getSetProgress()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$enabled(semanticsNode)) {
                        float current2 = progressBarRangeInfo2.getCurrent();
                        float floatValue = ((Number) progressBarRangeInfo2.getRange().getEndInclusive()).floatValue();
                        float floatValue2 = ((Number) progressBarRangeInfo2.getRange().getStart()).floatValue();
                        if (floatValue < floatValue2) {
                            floatValue = floatValue2;
                        }
                        if (current2 < floatValue) {
                            obtain.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                        }
                        float current3 = progressBarRangeInfo2.getCurrent();
                        float floatValue3 = ((Number) progressBarRangeInfo2.getRange().getStart()).floatValue();
                        float floatValue4 = ((Number) progressBarRangeInfo2.getRange().getEndInclusive()).floatValue();
                        if (floatValue3 > floatValue4) {
                            floatValue3 = floatValue4;
                        }
                        if (current3 > floatValue3) {
                            obtain.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                        }
                    }
                }
                if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$enabled(semanticsNode) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getSetProgress())) != null) {
                    obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction.getLabel(), 16908349));
                }
                CollectionInfoKt.setCollectionInfo(semanticsNode, obtain);
                CollectionInfoKt.setCollectionItemInfo(semanticsNode, obtain);
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getHorizontalScrollAxisRange()));
                AccessibilityAction accessibilityAction9 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getScrollBy());
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getVerticalScrollAxisRange()));
                if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$enabled(semanticsNode)) {
                    AccessibilityAction accessibilityAction10 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getPageUp());
                    if (accessibilityAction10 != null) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction10.getLabel(), 16908358));
                    }
                    AccessibilityAction accessibilityAction11 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getPageDown());
                    if (accessibilityAction11 != null) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction11.getLabel(), 16908359));
                    }
                    AccessibilityAction accessibilityAction12 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getPageLeft());
                    if (accessibilityAction12 != null) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction12.getLabel(), 16908360));
                    }
                    AccessibilityAction accessibilityAction13 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getPageRight());
                    if (accessibilityAction13 != null) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction13.getLabel(), 16908361));
                    }
                }
                obtain.setPaneTitle((CharSequence) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getPaneTitle()));
                if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$enabled(semanticsNode)) {
                    AccessibilityAction accessibilityAction14 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getExpand());
                    if (accessibilityAction14 != null) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction14.getLabel(), 262144));
                    }
                    AccessibilityAction accessibilityAction15 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getCollapse());
                    if (accessibilityAction15 != null) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction15.getLabel(), 524288));
                    }
                    AccessibilityAction accessibilityAction16 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsActions.getDismiss());
                    if (accessibilityAction16 != null) {
                        obtain.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(accessibilityAction16.getLabel(), 1048576));
                    }
                    if (semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsActions.getCustomActions())) {
                        List list4 = (List) semanticsNode.getUnmergedConfig$ui_release().get(SemanticsActions.getCustomActions());
                        if (list4.size() < 32) {
                            SparseArrayCompat sparseArrayCompat = new SparseArrayCompat();
                            LinkedHashMap linkedHashMap = new LinkedHashMap();
                            SparseArrayCompat sparseArrayCompat2 = androidComposeViewAccessibilityDelegateCompat.labelToActionId;
                            if (ContainerHelpersKt.binarySearch(sparseArrayCompat2.size, i, sparseArrayCompat2.keys) >= 0) {
                                z7 = true;
                            } else {
                                z7 = false;
                            }
                            if (z7) {
                                SparseArrayCompat sparseArrayCompat3 = androidComposeViewAccessibilityDelegateCompat.labelToActionId;
                                sparseArrayCompat3.getClass();
                                Map map = (Map) SparseArrayCompatKt.commonGet(sparseArrayCompat3, i);
                                List mutableList = ArraysKt.toMutableList(AccessibilityActionsResourceIds);
                                ArrayList arrayList2 = new ArrayList();
                                if (list4.size() <= 0) {
                                    if (arrayList2.size() > 0) {
                                        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(arrayList2.get(0));
                                        ((Number) ((ArrayList) mutableList).get(0)).intValue();
                                        throw null;
                                    }
                                } else {
                                    OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(list4.get(0));
                                    Intrinsics.checkNotNull(map);
                                    throw null;
                                }
                            } else if (list4.size() > 0) {
                                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(list4.get(0));
                                throw null;
                            }
                            androidComposeViewAccessibilityDelegateCompat.actionIdToLabel.put(i, sparseArrayCompat);
                            androidComposeViewAccessibilityDelegateCompat.labelToActionId.put(i, linkedHashMap);
                        } else {
                            throw new IllegalStateException("Can't have more than 32 custom actions for one widget");
                        }
                    }
                }
                if (obtain.getContentDescription() == null && obtain.getText() == null && obtain.getHintText() == null && obtain.getStateDescription() == null && !obtain.isCheckable()) {
                    z6 = false;
                } else {
                    z6 = true;
                }
                if (!semanticsNode.getUnmergedConfig$ui_release().isMergingSemanticsOfDescendants() && (!z || !z6)) {
                    z24 = false;
                }
                obtain.setScreenReaderFocusable(z24);
                if (androidComposeViewAccessibilityDelegateCompat.idToBeforeMap.get(Integer.valueOf(i)) != null) {
                    Integer num = (Integer) androidComposeViewAccessibilityDelegateCompat.idToBeforeMap.get(Integer.valueOf(i));
                    if (num != null) {
                        obtain.setTraversalBefore(androidComposeView, num.intValue());
                    }
                    AccessibilityNodeInfo unwrap2 = obtain.unwrap();
                    Intrinsics.checkNotNullExpressionValue(unwrap2, "info.unwrap()");
                    androidComposeViewAccessibilityDelegateCompat.addExtraDataToAccessibilityNodeInfoHelper(i, unwrap2, androidComposeViewAccessibilityDelegateCompat.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL, null);
                }
                if (androidComposeViewAccessibilityDelegateCompat.idToAfterMap.get(Integer.valueOf(i)) != null) {
                    Integer num2 = (Integer) androidComposeViewAccessibilityDelegateCompat.idToAfterMap.get(Integer.valueOf(i));
                    if (num2 != null) {
                        obtain.setTraversalAfter(androidComposeView, num2.intValue());
                    }
                    AccessibilityNodeInfo unwrap3 = obtain.unwrap();
                    Intrinsics.checkNotNullExpressionValue(unwrap3, "info.unwrap()");
                    androidComposeViewAccessibilityDelegateCompat.addExtraDataToAccessibilityNodeInfoHelper(i, unwrap3, androidComposeViewAccessibilityDelegateCompat.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL, null);
                }
                return obtain.unwrap();
            }
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x055c, code lost:
        if (r1 != 16) goto L320;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:306:0x06af  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x06b1  */
    /* JADX WARN: Removed duplicated region for block: B:400:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e1  */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator] */
    /* JADX WARN: Type inference failed for: r8v10, types: [androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator] */
    /* JADX WARN: Type inference failed for: r8v12, types: [androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v13, types: [androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator] */
    /* JADX WARN: Type inference failed for: r8v15, types: [java.lang.Object, androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator] */
    /* JADX WARN: Type inference failed for: r8v18, types: [androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator, java.lang.Object, androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator] */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v27 */
    /* JADX WARN: Type inference failed for: r8v28 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Object, androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator] */
    /* JADX WARN: Type inference failed for: r8v7, types: [androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator] */
    /* JADX WARN: Type inference failed for: r8v9, types: [androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator, java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00c0 -> B:46:0x00c1). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean access$performActionHelper(androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r17, int r18, int r19, android.os.Bundle r20) {
        /*
            Method dump skipped, instructions count: 1876
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$performActionHelper(androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat, int, int, android.os.Bundle):boolean");
    }

    public static final void access$sendScrollEventIfNeeded(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, ScrollObservationScope scrollObservationScope) {
        androidComposeViewAccessibilityDelegateCompat.getClass();
        if (scrollObservationScope.isValidOwnerScope()) {
            androidComposeViewAccessibilityDelegateCompat.view.getSnapshotObserver().observeReads$ui_release(scrollObservationScope, androidComposeViewAccessibilityDelegateCompat.sendScrollEventIfNeededLambda, new AndroidComposeViewAccessibilityDelegateCompat$sendScrollEventIfNeeded$1(androidComposeViewAccessibilityDelegateCompat, scrollObservationScope));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addExtraDataToAccessibilityNodeInfoHelper(int i, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
        SemanticsNode semanticsNode;
        String str2;
        int i2;
        Boolean bool;
        androidx.compose.ui.geometry.Rect rect;
        RectF rectF;
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) getCurrentSemanticsNodes().get(Integer.valueOf(i));
        if (semanticsNodeWithAdjustedBounds != null && (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null) {
            String iterableTextForAccessibility = getIterableTextForAccessibility(semanticsNode);
            if (Intrinsics.areEqual(str, this.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL)) {
                Integer num = (Integer) this.idToBeforeMap.get(Integer.valueOf(i));
                if (num != null) {
                    accessibilityNodeInfo.getExtras().putInt(str, num.intValue());
                }
            } else if (Intrinsics.areEqual(str, this.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL)) {
                Integer num2 = (Integer) this.idToAfterMap.get(Integer.valueOf(i));
                if (num2 != null) {
                    accessibilityNodeInfo.getExtras().putInt(str, num2.intValue());
                }
            } else if (semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsActions.getGetTextLayoutResult()) && bundle != null && Intrinsics.areEqual(str, "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
                int i3 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
                int i4 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
                if (i4 > 0 && i3 >= 0) {
                    if (iterableTextForAccessibility != null) {
                        i2 = iterableTextForAccessibility.length();
                    } else {
                        i2 = Integer.MAX_VALUE;
                    }
                    if (i3 < i2) {
                        ArrayList arrayList = new ArrayList();
                        Function1 function1 = (Function1) ((AccessibilityAction) semanticsNode.getUnmergedConfig$ui_release().get(SemanticsActions.getGetTextLayoutResult())).getAction();
                        if (function1 != null) {
                            bool = (Boolean) function1.invoke(arrayList);
                        } else {
                            bool = null;
                        }
                        if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
                            TextLayoutResult textLayoutResult = (TextLayoutResult) arrayList.get(0);
                            ArrayList arrayList2 = new ArrayList();
                            for (int i5 = 0; i5 < i4; i5++) {
                                int i6 = i3 + i5;
                                if (i6 >= textLayoutResult.getLayoutInput().getText().length()) {
                                    arrayList2.add(null);
                                } else {
                                    androidx.compose.ui.geometry.Rect m55translatek4lQ0M = textLayoutResult.getBoundingBox(i6).m55translatek4lQ0M(semanticsNode.m293getPositionInRootF1C5BW0());
                                    androidx.compose.ui.geometry.Rect boundsInRoot = semanticsNode.getBoundsInRoot();
                                    if (m55translatek4lQ0M.overlaps(boundsInRoot)) {
                                        rect = m55translatek4lQ0M.intersect(boundsInRoot);
                                    } else {
                                        rect = null;
                                    }
                                    if (rect != null) {
                                        long Offset = OffsetKt.Offset(rect.getLeft(), rect.getTop());
                                        AndroidComposeView androidComposeView = this.view;
                                        long m278localToScreenMKHz9U = androidComposeView.m278localToScreenMKHz9U(Offset);
                                        long m278localToScreenMKHz9U2 = androidComposeView.m278localToScreenMKHz9U(OffsetKt.Offset(rect.getRight(), rect.getBottom()));
                                        rectF = new RectF(Offset.m45getXimpl(m278localToScreenMKHz9U), Offset.m46getYimpl(m278localToScreenMKHz9U), Offset.m45getXimpl(m278localToScreenMKHz9U2), Offset.m46getYimpl(m278localToScreenMKHz9U2));
                                    } else {
                                        rectF = null;
                                    }
                                    arrayList2.add(rectF);
                                }
                            }
                            accessibilityNodeInfo.getExtras().putParcelableArray(str, (Parcelable[]) arrayList2.toArray(new RectF[0]));
                            return;
                        }
                        return;
                    }
                }
                Log.e("AccessibilityDelegate", "Invalid arguments for accessibility character locations");
            } else if (semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsProperties.getTestTag()) && bundle != null && Intrinsics.areEqual(str, "androidx.compose.ui.semantics.testTag") && (str2 = (String) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getTestTag())) != null) {
                accessibilityNodeInfo.getExtras().putCharSequence(str, str2);
            }
        }
    }

    private final AccessibilityEvent createTextSelectionChangedEvent(int i, Integer num, Integer num2, Integer num3, CharSequence charSequence) {
        AccessibilityEvent createEvent$ui_release = createEvent$ui_release(i, 8192);
        if (num != null) {
            createEvent$ui_release.setFromIndex(num.intValue());
        }
        if (num2 != null) {
            createEvent$ui_release.setToIndex(num2.intValue());
        }
        if (num3 != null) {
            createEvent$ui_release.setItemCount(num3.intValue());
        }
        if (charSequence != null) {
            createEvent$ui_release.getText().add(charSequence);
        }
        return createEvent$ui_release;
    }

    private final int getAccessibilitySelectionEnd(SemanticsNode semanticsNode) {
        if (!semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsProperties.getContentDescription()) && semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsProperties.getTextSelectionRange())) {
            return TextRange.m315getEndimpl(((TextRange) semanticsNode.getUnmergedConfig$ui_release().get(SemanticsProperties.getTextSelectionRange())).m317unboximpl());
        }
        return this.accessibilityCursorPosition;
    }

    private final int getAccessibilitySelectionStart(SemanticsNode semanticsNode) {
        if (!semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsProperties.getContentDescription()) && semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsProperties.getTextSelectionRange())) {
            return (int) (((TextRange) semanticsNode.getUnmergedConfig$ui_release().get(SemanticsProperties.getTextSelectionRange())).m317unboximpl() >> 32);
        }
        return this.accessibilityCursorPosition;
    }

    private final Map getCurrentSemanticsNodes() {
        SemanticsNode semanticsNode;
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = AndroidComposeViewAccessibilityDelegateCompat_androidKt.getAllUncoveredSemanticsNodesToMap(this.view.getSemanticsOwner());
            this.idToBeforeMap.clear();
            this.idToAfterMap.clear();
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            ref$ObjectRef.element = new ArrayList();
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) getCurrentSemanticsNodes().get(-1);
            if (semanticsNodeWithAdjustedBounds != null && (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null) {
                setTraversalValues$depthFirstSearch(ref$ObjectRef, semanticsNode);
            }
            int lastIndex = CollectionsKt.getLastIndex((List) ref$ObjectRef.element);
            int i = 1;
            if (1 <= lastIndex) {
                while (true) {
                    int intValue = ((Number) ((Pair) ((List) ref$ObjectRef.element).get(i - 1)).getFirst()).intValue();
                    int intValue2 = ((Number) ((Pair) ((List) ref$ObjectRef.element).get(i)).getFirst()).intValue();
                    this.idToBeforeMap.put(Integer.valueOf(intValue), Integer.valueOf(intValue2));
                    this.idToAfterMap.put(Integer.valueOf(intValue2), Integer.valueOf(intValue));
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
        }
        return this.currentSemanticsNodes;
    }

    private static String getIterableTextForAccessibility(SemanticsNode semanticsNode) {
        AnnotatedString annotatedString;
        if (semanticsNode == null) {
            return null;
        }
        if (semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsProperties.getContentDescription())) {
            return TempListUtilsKt.fastJoinToString$default((List) semanticsNode.getUnmergedConfig$ui_release().get(SemanticsProperties.getContentDescription()));
        }
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$isTextField(semanticsNode)) {
            AnnotatedString textForTextField = getTextForTextField(semanticsNode.getUnmergedConfig$ui_release());
            if (textForTextField == null) {
                return null;
            }
            return textForTextField.getText();
        }
        List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig$ui_release(), SemanticsProperties.getText());
        if (list == null || (annotatedString = (AnnotatedString) CollectionsKt.firstOrNull(list)) == null) {
            return null;
        }
        return annotatedString.getText();
    }

    private static AnnotatedString getTextForTextField(SemanticsConfiguration semanticsConfiguration) {
        return (AnnotatedString) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.getEditableText());
    }

    private final void notifySubtreeAccessibilityStateChangedIfNeeded(LayoutNode layoutNode) {
        if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
            this.boundsUpdateChannel.mo484trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    private static final float performActionHelper$scrollDelta(float f, float f2) {
        boolean z;
        if (Math.signum(f) == Math.signum(f2)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (Math.abs(f) >= Math.abs(f2)) {
                return f2;
            }
            return f;
        }
        return 0.0f;
    }

    private final int semanticsNodeIdToAccessibilityVirtualNodeId(int i) {
        if (i == this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
            return -1;
        }
        return i;
    }

    private final boolean sendEvent(AccessibilityEvent accessibilityEvent) {
        if (!isEnabled$ui_release()) {
            return false;
        }
        AndroidComposeView androidComposeView = this.view;
        return androidComposeView.getParent().requestSendAccessibilityEvent(androidComposeView, accessibilityEvent);
    }

    private final boolean sendEventForVirtualView(int i, int i2, Integer num, List list) {
        if (i != Integer.MIN_VALUE && isEnabled$ui_release()) {
            AccessibilityEvent createEvent$ui_release = createEvent$ui_release(i, i2);
            if (num != null) {
                createEvent$ui_release.setContentChangeTypes(num.intValue());
            }
            if (list != null) {
                createEvent$ui_release.setContentDescription(TempListUtilsKt.fastJoinToString$default(list));
            }
            return sendEvent(createEvent$ui_release);
        }
        return false;
    }

    static /* synthetic */ void sendEventForVirtualView$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, int i, int i2, Integer num, int i3) {
        if ((i3 & 4) != 0) {
            num = null;
        }
        androidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView(i, i2, num, null);
    }

    private final void sendPaneChangeEvents(int i, int i2, String str) {
        AccessibilityEvent createEvent$ui_release = createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(i), 32);
        createEvent$ui_release.setContentChangeTypes(i2);
        if (str != null) {
            createEvent$ui_release.getText().add(str);
        }
        sendEvent(createEvent$ui_release);
    }

    private final void sendPendingTextTraversedAtGranularityEvent(int i) {
        PendingTextTraversedEvent pendingTextTraversedEvent = this.pendingTextTraversedEvent;
        if (pendingTextTraversedEvent != null) {
            if (i != pendingTextTraversedEvent.getNode().getId()) {
                return;
            }
            if (SystemClock.uptimeMillis() - pendingTextTraversedEvent.getTraverseTime() <= 1000) {
                AccessibilityEvent createEvent$ui_release = createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(pendingTextTraversedEvent.getNode().getId()), 131072);
                createEvent$ui_release.setFromIndex(pendingTextTraversedEvent.getFromIndex());
                createEvent$ui_release.setToIndex(pendingTextTraversedEvent.getToIndex());
                createEvent$ui_release.setAction(pendingTextTraversedEvent.getAction());
                createEvent$ui_release.setMovementGranularity(pendingTextTraversedEvent.getGranularity());
                createEvent$ui_release.getText().add(getIterableTextForAccessibility(pendingTextTraversedEvent.getNode()));
                sendEvent(createEvent$ui_release);
            }
        }
        this.pendingTextTraversedEvent = null;
    }

    private final void sendSemanticsStructureChangeEvents(SemanticsNode semanticsNode, SemanticsNodeCopy semanticsNodeCopy) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size();
        for (int i = 0; i < size; i++) {
            SemanticsNode semanticsNode2 = (SemanticsNode) replacedChildren$ui_release.get(i);
            if (getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode2.getId()))) {
                if (!semanticsNodeCopy.getChildren().contains(Integer.valueOf(semanticsNode2.getId()))) {
                    notifySubtreeAccessibilityStateChangedIfNeeded(semanticsNode.getLayoutNode$ui_release());
                    return;
                }
                linkedHashSet.add(Integer.valueOf(semanticsNode2.getId()));
            }
        }
        for (Number number : semanticsNodeCopy.getChildren()) {
            if (!linkedHashSet.contains(Integer.valueOf(number.intValue()))) {
                notifySubtreeAccessibilityStateChangedIfNeeded(semanticsNode.getLayoutNode$ui_release());
                return;
            }
        }
        List replacedChildren$ui_release2 = semanticsNode.getReplacedChildren$ui_release();
        int size2 = replacedChildren$ui_release2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            SemanticsNode semanticsNode3 = (SemanticsNode) replacedChildren$ui_release2.get(i2);
            if (getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode3.getId()))) {
                Object obj = ((LinkedHashMap) this.previousSemanticsNodes).get(Integer.valueOf(semanticsNode3.getId()));
                Intrinsics.checkNotNull(obj);
                sendSemanticsStructureChangeEvents(semanticsNode3, (SemanticsNodeCopy) obj);
            }
        }
    }

    private final void sendSubtreeChangeAccessibilityEvents(LayoutNode layoutNode, ArraySet arraySet) {
        LayoutNode access$findClosestParentNode;
        SemanticsModifierNode outerSemantics;
        if (!layoutNode.isAttached() || this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(layoutNode)) {
            return;
        }
        SemanticsModifierNode outerSemantics2 = SemanticsNodeKt.getOuterSemantics(layoutNode);
        if (outerSemantics2 == null) {
            LayoutNode access$findClosestParentNode2 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$findClosestParentNode(layoutNode, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsWrapper$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    boolean z;
                    LayoutNode it = (LayoutNode) obj;
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (SemanticsNodeKt.getOuterSemantics(it) != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
            });
            if (access$findClosestParentNode2 != null) {
                outerSemantics2 = SemanticsNodeKt.getOuterSemantics(access$findClosestParentNode2);
            } else {
                outerSemantics2 = null;
            }
            if (outerSemantics2 == null) {
                return;
            }
        }
        if (!SemanticsModifierNodeKt.collapsedSemanticsConfiguration(outerSemantics2).isMergingSemanticsOfDescendants() && (access$findClosestParentNode = AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$findClosestParentNode(layoutNode, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1
            /* JADX WARN: Code restructure failed: missing block: B:7:0x0018, code lost:
                if (r0.isMergingSemanticsOfDescendants() == true) goto L7;
             */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invoke(java.lang.Object r1) {
                /*
                    r0 = this;
                    androidx.compose.ui.node.LayoutNode r1 = (androidx.compose.ui.node.LayoutNode) r1
                    java.lang.String r0 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
                    androidx.compose.ui.node.SemanticsModifierNode r0 = androidx.compose.ui.semantics.SemanticsNodeKt.getOuterSemantics(r1)
                    if (r0 == 0) goto L1b
                    androidx.compose.ui.semantics.SemanticsConfiguration r0 = androidx.compose.ui.node.SemanticsModifierNodeKt.collapsedSemanticsConfiguration(r0)
                    if (r0 == 0) goto L1b
                    boolean r0 = r0.isMergingSemanticsOfDescendants()
                    r1 = 1
                    if (r0 != r1) goto L1b
                    goto L1c
                L1b:
                    r1 = 0
                L1c:
                    java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1.invoke(java.lang.Object):java.lang.Object");
            }
        })) != null && (outerSemantics = SemanticsNodeKt.getOuterSemantics(access$findClosestParentNode)) != null) {
            outerSemantics2 = outerSemantics;
        }
        int semanticsId = DelegatableNodeKt.requireLayoutNode(outerSemantics2).getSemanticsId();
        if (!arraySet.add(Integer.valueOf(semanticsId))) {
            return;
        }
        sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(semanticsId), 2048, 1, 8);
    }

    private final boolean setAccessibilitySelection(SemanticsNode semanticsNode, int i, int i2, boolean z) {
        String iterableTextForAccessibility;
        Integer num;
        Integer num2;
        boolean z2 = false;
        if (semanticsNode.getUnmergedConfig$ui_release().contains(SemanticsActions.getSetSelection()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$enabled(semanticsNode)) {
            Function3 function3 = (Function3) ((AccessibilityAction) semanticsNode.getUnmergedConfig$ui_release().get(SemanticsActions.getSetSelection())).getAction();
            if (function3 == null) {
                return false;
            }
            return ((Boolean) function3.invoke(Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } else if ((i == i2 && i2 == this.accessibilityCursorPosition) || (iterableTextForAccessibility = getIterableTextForAccessibility(semanticsNode)) == null) {
            return false;
        } else {
            this.accessibilityCursorPosition = (i < 0 || i != i2 || i2 > iterableTextForAccessibility.length()) ? -1 : -1;
            if (iterableTextForAccessibility.length() > 0) {
                z2 = true;
            }
            int semanticsNodeIdToAccessibilityVirtualNodeId = semanticsNodeIdToAccessibilityVirtualNodeId(semanticsNode.getId());
            Integer num3 = null;
            if (z2) {
                num = Integer.valueOf(this.accessibilityCursorPosition);
            } else {
                num = null;
            }
            if (z2) {
                num2 = Integer.valueOf(this.accessibilityCursorPosition);
            } else {
                num2 = null;
            }
            if (z2) {
                num3 = Integer.valueOf(iterableTextForAccessibility.length());
            }
            sendEvent(createTextSelectionChangedEvent(semanticsNodeIdToAccessibilityVirtualNodeId, num, num2, num3, iterableTextForAccessibility));
            sendPendingTextTraversedAtGranularityEvent(semanticsNode.getId());
            return true;
        }
    }

    private static final void setTraversalValues$depthFirstSearch(Ref$ObjectRef ref$ObjectRef, SemanticsNode semanticsNode) {
        boolean z;
        int i;
        Pair pair;
        boolean z2;
        Pair pair2;
        androidx.compose.ui.geometry.Rect rect;
        boolean z3;
        LayoutNode layoutNode$ui_release;
        InnerNodeCoordinator innerCoordinator$ui_release;
        SemanticsNode parent = semanticsNode.getParent();
        if (parent != null && (layoutNode$ui_release = parent.getLayoutNode$ui_release()) != null && (innerCoordinator$ui_release = layoutNode$ui_release.getInnerCoordinator$ui_release()) != null && innerCoordinator$ui_release.isAttached()) {
            z = true;
        } else {
            z = false;
        }
        if (z && semanticsNode.getLayoutNode$ui_release().getInnerCoordinator$ui_release().isAttached()) {
            List list = (List) ref$ObjectRef.element;
            Integer valueOf = Integer.valueOf(semanticsNode.getId());
            InnerNodeCoordinator innerCoordinator$ui_release2 = semanticsNode.getLayoutNode$ui_release().getInnerCoordinator$ui_release();
            Intrinsics.checkNotNullParameter(innerCoordinator$ui_release2, "<this>");
            LayoutCoordinates findRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(innerCoordinator$ui_release2);
            androidx.compose.ui.geometry.Rect boundsInRoot = LayoutCoordinatesKt.boundsInRoot(innerCoordinator$ui_release2);
            float mo208getSizeYbymL2g = (int) (findRootCoordinates.mo208getSizeYbymL2g() >> 32);
            float m405getHeightimpl = IntSize.m405getHeightimpl(findRootCoordinates.mo208getSizeYbymL2g());
            float coerceIn = RangesKt.coerceIn(boundsInRoot.getLeft(), 0.0f, mo208getSizeYbymL2g);
            float coerceIn2 = RangesKt.coerceIn(boundsInRoot.getTop(), 0.0f, m405getHeightimpl);
            float coerceIn3 = RangesKt.coerceIn(boundsInRoot.getRight(), 0.0f, mo208getSizeYbymL2g);
            float coerceIn4 = RangesKt.coerceIn(boundsInRoot.getBottom(), 0.0f, m405getHeightimpl);
            if (coerceIn == coerceIn3) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                if (coerceIn2 == coerceIn4) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (!z3) {
                    long mo209localToWindowMKHz9U = findRootCoordinates.mo209localToWindowMKHz9U(OffsetKt.Offset(coerceIn, coerceIn2));
                    long mo209localToWindowMKHz9U2 = findRootCoordinates.mo209localToWindowMKHz9U(OffsetKt.Offset(coerceIn3, coerceIn2));
                    long mo209localToWindowMKHz9U3 = findRootCoordinates.mo209localToWindowMKHz9U(OffsetKt.Offset(coerceIn3, coerceIn4));
                    pair2 = pair;
                    long mo209localToWindowMKHz9U4 = findRootCoordinates.mo209localToWindowMKHz9U(OffsetKt.Offset(coerceIn, coerceIn4));
                    float m45getXimpl = Offset.m45getXimpl(mo209localToWindowMKHz9U);
                    float[] fArr = {Offset.m45getXimpl(mo209localToWindowMKHz9U2), Offset.m45getXimpl(mo209localToWindowMKHz9U4), Offset.m45getXimpl(mo209localToWindowMKHz9U3)};
                    for (int i2 = 0; i2 < 3; i2++) {
                        m45getXimpl = Math.min(m45getXimpl, fArr[i2]);
                    }
                    float m46getYimpl = Offset.m46getYimpl(mo209localToWindowMKHz9U);
                    float[] fArr2 = {Offset.m46getYimpl(mo209localToWindowMKHz9U2), Offset.m46getYimpl(mo209localToWindowMKHz9U4), Offset.m46getYimpl(mo209localToWindowMKHz9U3)};
                    int i3 = 0;
                    for (int i4 = 3; i3 < i4; i4 = 3) {
                        m46getYimpl = Math.min(m46getYimpl, fArr2[i3]);
                        i3++;
                    }
                    float m45getXimpl2 = Offset.m45getXimpl(mo209localToWindowMKHz9U);
                    float[] fArr3 = {Offset.m45getXimpl(mo209localToWindowMKHz9U2), Offset.m45getXimpl(mo209localToWindowMKHz9U4), Offset.m45getXimpl(mo209localToWindowMKHz9U3)};
                    int i5 = 0;
                    for (int i6 = 3; i5 < i6; i6 = 3) {
                        m45getXimpl2 = Math.max(m45getXimpl2, fArr3[i5]);
                        i5++;
                    }
                    float m46getYimpl2 = Offset.m46getYimpl(mo209localToWindowMKHz9U);
                    i = 0;
                    float[] fArr4 = {Offset.m46getYimpl(mo209localToWindowMKHz9U2), Offset.m46getYimpl(mo209localToWindowMKHz9U4), Offset.m46getYimpl(mo209localToWindowMKHz9U3)};
                    for (int i7 = 0; i7 < 3; i7++) {
                        m46getYimpl2 = Math.max(m46getYimpl2, fArr4[i7]);
                    }
                    rect = new androidx.compose.ui.geometry.Rect(m45getXimpl, m46getYimpl, m45getXimpl2, m46getYimpl2);
                    list.add(new Pair(valueOf, rect));
                }
            }
            i = 0;
            pair2 = pair;
            rect = androidx.compose.ui.geometry.Rect.Zero;
            list.add(new Pair(valueOf, rect));
        } else {
            i = 0;
        }
        List replacedChildrenSortedByBounds$ui_release = semanticsNode.getReplacedChildrenSortedByBounds$ui_release();
        int size = replacedChildrenSortedByBounds$ui_release.size();
        for (int i8 = i; i8 < size; i8++) {
            setTraversalValues$depthFirstSearch(ref$ObjectRef, (SemanticsNode) replacedChildrenSortedByBounds$ui_release.get(i8));
        }
    }

    private static CharSequence trimToSize(CharSequence charSequence) {
        boolean z;
        if (charSequence != null && charSequence.length() != 0) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            int i = 100000;
            if (charSequence.length() > 100000) {
                if (Character.isHighSurrogate(charSequence.charAt(99999)) && Character.isLowSurrogate(charSequence.charAt(100000))) {
                    i = 99999;
                }
                CharSequence subSequence = charSequence.subSequence(0, i);
                Intrinsics.checkNotNull(subSequence, "null cannot be cast to non-null type T of androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.trimToSize");
                return subSequence;
            }
            return charSequence;
        }
        return charSequence;
    }

    private final void updateHoveredVirtualView(int i) {
        int i2 = this.hoveredVirtualViewId;
        if (i2 == i) {
            return;
        }
        this.hoveredVirtualViewId = i;
        sendEventForVirtualView$default(this, i, 128, null, 12);
        sendEventForVirtualView$default(this, i2, 256, null, 12);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0075 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0082 A[Catch: all -> 0x00d4, TRY_LEAVE, TryCatch #0 {all -> 0x00d4, blocks: (B:25:0x0067, B:29:0x007a, B:31:0x0082, B:34:0x008d, B:36:0x0094, B:37:0x00a3, B:39:0x00aa, B:40:0x00b3, B:24:0x005c), top: B:50:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0022 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00c7 -> B:25:0x0067). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object boundsUpdatesEventLoop(kotlin.coroutines.Continuation r11) {
        /*
            Method dump skipped, instructions count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.boundsUpdatesEventLoop(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: canScroll-0AR0LA0$ui_release  reason: not valid java name */
    public final void m281canScroll0AR0LA0$ui_release(long j, boolean z) {
        long j2;
        boolean z2;
        SemanticsPropertyKey horizontalScrollAxisRange;
        Rect adjustedBounds;
        Collection<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes = getCurrentSemanticsNodes().values();
        Intrinsics.checkNotNullParameter(currentSemanticsNodes, "currentSemanticsNodes");
        j2 = Offset.Unspecified;
        if (!Offset.m43equalsimpl0(j, j2)) {
            if (!Float.isNaN(Offset.m45getXimpl(j)) && !Float.isNaN(Offset.m46getYimpl(j))) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (z) {
                    horizontalScrollAxisRange = SemanticsProperties.getVerticalScrollAxisRange();
                } else if (!z) {
                    horizontalScrollAxisRange = SemanticsProperties.getHorizontalScrollAxisRange();
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                if (!currentSemanticsNodes.isEmpty()) {
                    for (SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds : currentSemanticsNodes) {
                        Intrinsics.checkNotNullParameter(semanticsNodeWithAdjustedBounds.getAdjustedBounds(), "<this>");
                        if (new androidx.compose.ui.geometry.Rect(adjustedBounds.left, adjustedBounds.top, adjustedBounds.right, adjustedBounds.bottom).m52containsk4lQ0M(j)) {
                            OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(SemanticsConfigurationKt.getOrNull(semanticsNodeWithAdjustedBounds.getSemanticsNode().getConfig(), horizontalScrollAxisRange));
                        }
                    }
                    return;
                }
                return;
            }
            throw new IllegalStateException("Offset argument contained a NaN value.".toString());
        }
    }

    public final AccessibilityEvent createEvent$ui_release(int i, int i2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        Intrinsics.checkNotNullExpressionValue(obtain, "obtain(eventType)");
        obtain.setEnabled(true);
        obtain.setClassName("android.view.View");
        AndroidComposeView androidComposeView = this.view;
        obtain.setPackageName(androidComposeView.getContext().getPackageName());
        obtain.setSource(androidComposeView, i);
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) getCurrentSemanticsNodes().get(Integer.valueOf(i));
        if (semanticsNodeWithAdjustedBounds != null) {
            obtain.setPassword(AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$isPassword(semanticsNodeWithAdjustedBounds.getSemanticsNode()));
        }
        return obtain;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean dispatchHoverEvent(android.view.MotionEvent r10) {
        /*
            r9 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            android.view.accessibility.AccessibilityManager r0 = r9.accessibilityManager
            boolean r1 = r0.isEnabled()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L17
            boolean r0 = r0.isTouchExplorationEnabled()
            if (r0 == 0) goto L17
            r0 = r2
            goto L18
        L17:
            r0 = r3
        L18:
            if (r0 != 0) goto L1b
            return r3
        L1b:
            int r0 = r10.getAction()
            r1 = 7
            androidx.compose.ui.platform.AndroidComposeView r4 = r9.view
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 == r1) goto L40
            r1 = 9
            if (r0 == r1) goto L40
            r1 = 10
            if (r0 == r1) goto L2f
            return r3
        L2f:
            int r0 = r9.hoveredVirtualViewId
            if (r0 == r5) goto L37
            r9.updateHoveredVirtualView(r5)
            goto L3f
        L37:
            androidx.compose.ui.platform.AndroidViewsHandler r9 = r4.getAndroidViewsHandler$ui_release()
            boolean r2 = r9.dispatchGenericMotionEvent(r10)
        L3f:
            return r2
        L40:
            float r0 = r10.getX()
            float r1 = r10.getY()
            androidx.compose.ui.node.Owner.measureAndLayout$default(r4)
            androidx.compose.ui.node.HitTestResult r6 = new androidx.compose.ui.node.HitTestResult
            r6.<init>()
            androidx.compose.ui.node.LayoutNode r7 = r4.getRoot()
            long r0 = androidx.compose.ui.geometry.OffsetKt.Offset(r0, r1)
            int r8 = androidx.compose.ui.node.LayoutNode.$r8$clinit
            r7.m231hitTestSemanticsM_7yMNQ$ui_release(r0, r6, r2)
            boolean r0 = r6.isEmpty()
            r1 = 0
            if (r0 == 0) goto L66
            r0 = r1
            goto L70
        L66:
            int r0 = r6.size()
            int r0 = r0 + (-1)
            java.lang.Object r0 = r6.get(r0)
        L70:
            androidx.compose.ui.node.SemanticsModifierNode r0 = (androidx.compose.ui.node.SemanticsModifierNode) r0
            if (r0 == 0) goto L7e
            androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutNode(r0)
            if (r0 == 0) goto L7e
            androidx.compose.ui.node.SemanticsModifierNode r1 = androidx.compose.ui.semantics.SemanticsNodeKt.getOuterSemantics(r0)
        L7e:
            if (r1 == 0) goto Lbd
            androidx.compose.ui.semantics.SemanticsNode r0 = new androidx.compose.ui.semantics.SemanticsNode
            androidx.compose.ui.node.LayoutNode r6 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutNode(r1)
            r0.<init>(r1, r3, r6)
            androidx.compose.ui.node.NodeCoordinator r3 = r0.findCoordinatorToGetBounds$ui_release()
            androidx.compose.ui.semantics.SemanticsConfiguration r0 = r0.getUnmergedConfig$ui_release()
            androidx.compose.ui.semantics.SemanticsPropertyKey r6 = androidx.compose.ui.semantics.SemanticsProperties.getInvisibleToUser()
            boolean r0 = r0.contains(r6)
            if (r0 != 0) goto Lbd
            boolean r0 = r3.isTransparent()
            if (r0 != 0) goto Lbd
            androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutNode(r1)
            androidx.compose.ui.platform.AndroidViewsHandler r1 = r4.getAndroidViewsHandler$ui_release()
            java.util.HashMap r1 = r1.getLayoutNodeToHolder()
            java.lang.Object r1 = r1.get(r0)
            androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(r1)
            int r0 = r0.getSemanticsId()
            int r0 = r9.semanticsNodeIdToAccessibilityVirtualNodeId(r0)
            goto Lbe
        Lbd:
            r0 = r5
        Lbe:
            androidx.compose.ui.platform.AndroidViewsHandler r1 = r4.getAndroidViewsHandler$ui_release()
            boolean r10 = r1.dispatchGenericMotionEvent(r10)
            r9.updateHoveredVirtualView(r0)
            if (r0 != r5) goto Lcc
            r2 = r10
        Lcc:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.dispatchHoverEvent(android.view.MotionEvent):boolean");
    }

    public final AccessibilityManager getAccessibilityManager$ui_release() {
        return this.accessibilityManager;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public final AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
        Intrinsics.checkNotNullParameter(host, "host");
        return this.nodeProvider;
    }

    public final AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0 getEnabledStateListener$ui_release() {
        return this.enabledStateListener;
    }

    public final AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1 getTouchExplorationStateListener$ui_release() {
        return this.touchExplorationStateListener;
    }

    public final boolean isEnabled$ui_release() {
        if (this.accessibilityManager.isEnabled()) {
            List enabledServices = this.enabledServices;
            Intrinsics.checkNotNullExpressionValue(enabledServices, "enabledServices");
            if (!enabledServices.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public final void onLayoutChange$ui_release(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.currentSemanticsNodesInvalidated = true;
        if (!isEnabled$ui_release()) {
            return;
        }
        notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
    }

    public final void onSemanticsChange$ui_release() {
        this.currentSemanticsNodesInvalidated = true;
        if (isEnabled$ui_release() && !this.checkingForSemanticsChanges) {
            this.checkingForSemanticsChanges = true;
            this.handler.post(this.semanticsChangeChecker);
        }
    }
}
