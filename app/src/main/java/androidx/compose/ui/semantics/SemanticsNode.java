package androidx.compose.ui.semantics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SemanticsNode {
    private SemanticsNode fakeNodeParent;
    private final int id;
    private boolean isFake;
    private final LayoutNode layoutNode;
    private final boolean mergingEnabled;
    private final SemanticsModifierNode outerSemanticsNode;
    private final SemanticsConfiguration unmergedConfig;

    public SemanticsNode(SemanticsModifierNode outerSemanticsNode, boolean z, LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(outerSemanticsNode, "outerSemanticsNode");
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.outerSemanticsNode = outerSemanticsNode;
        this.mergingEnabled = z;
        this.layoutNode = layoutNode;
        this.unmergedConfig = SemanticsModifierNodeKt.collapsedSemanticsConfiguration(outerSemanticsNode);
        this.id = layoutNode.getSemanticsId();
    }

    /* renamed from: fakeSemanticsNode-ypyhhiA  reason: not valid java name */
    private final SemanticsNode m292fakeSemanticsNodeypyhhiA(Role role, Function1 function1) {
        int i;
        SemanticsNode$fakeSemanticsNode$fakeNode$1 semanticsNode$fakeSemanticsNode$fakeNode$1 = new SemanticsNode$fakeSemanticsNode$fakeNode$1(function1);
        if (role != null) {
            i = 1000000000;
        } else {
            i = 2000000000;
        }
        SemanticsNode semanticsNode = new SemanticsNode(semanticsNode$fakeSemanticsNode$fakeNode$1, false, new LayoutNode(this.id + i, true));
        semanticsNode.isFake = true;
        semanticsNode.fakeNodeParent = this;
        return semanticsNode;
    }

    static List findOneLayerOfMergingSemanticsNodes$default(SemanticsNode semanticsNode, List list, boolean z, int i) {
        if ((i & 1) != 0) {
            list = new ArrayList();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        semanticsNode.getClass();
        List unmergedChildren$ui_release = semanticsNode.unmergedChildren$ui_release(z, false);
        int size = unmergedChildren$ui_release.size();
        for (int i2 = 0; i2 < size; i2++) {
            SemanticsNode semanticsNode2 = (SemanticsNode) unmergedChildren$ui_release.get(i2);
            if (semanticsNode2.isMergingSemanticsOfDescendants()) {
                list.add(semanticsNode2);
            } else if (!semanticsNode2.unmergedConfig.isClearingSemantics()) {
                findOneLayerOfMergingSemanticsNodes$default(semanticsNode2, list, false, 2);
            }
        }
        return list;
    }

    private final List getChildren(boolean z) {
        if (this.unmergedConfig.isClearingSemantics()) {
            return EmptyList.INSTANCE;
        }
        if (isMergingSemanticsOfDescendants()) {
            return findOneLayerOfMergingSemanticsNodes$default(this, null, z, 1);
        }
        return unmergedChildren$ui_release(z, true);
    }

    private final boolean isMergingSemanticsOfDescendants() {
        if (this.mergingEnabled && this.unmergedConfig.isMergingSemanticsOfDescendants()) {
            return true;
        }
        return false;
    }

    private final void mergeConfig(SemanticsConfiguration semanticsConfiguration) {
        if (!this.unmergedConfig.isClearingSemantics()) {
            List unmergedChildren$ui_release = unmergedChildren$ui_release(false, false);
            int size = unmergedChildren$ui_release.size();
            for (int i = 0; i < size; i++) {
                SemanticsNode semanticsNode = (SemanticsNode) unmergedChildren$ui_release.get(i);
                if (!semanticsNode.isMergingSemanticsOfDescendants()) {
                    semanticsConfiguration.mergeChild$ui_release(semanticsNode.unmergedConfig);
                    semanticsNode.mergeConfig(semanticsConfiguration);
                }
            }
        }
    }

    public final NodeCoordinator findCoordinatorToGetBounds$ui_release() {
        boolean isMergingSemanticsOfDescendants = this.unmergedConfig.isMergingSemanticsOfDescendants();
        SemanticsModifierNode semanticsModifierNode = this.outerSemanticsNode;
        if (isMergingSemanticsOfDescendants) {
            SemanticsModifierNode outerMergingSemantics = SemanticsNodeKt.getOuterMergingSemantics(this.layoutNode);
            if (outerMergingSemantics != null) {
                semanticsModifierNode = outerMergingSemantics;
            }
            return DelegatableNodeKt.m224requireCoordinator64DMado(semanticsModifierNode, 8);
        }
        return DelegatableNodeKt.m224requireCoordinator64DMado(semanticsModifierNode, 8);
    }

    public final Rect getBoundsInRoot() {
        Rect rect;
        if (!this.layoutNode.isAttached()) {
            rect = Rect.Zero;
            return rect;
        }
        return LayoutCoordinatesKt.boundsInRoot(findCoordinatorToGetBounds$ui_release());
    }

    public final SemanticsConfiguration getConfig() {
        boolean isMergingSemanticsOfDescendants = isMergingSemanticsOfDescendants();
        SemanticsConfiguration semanticsConfiguration = this.unmergedConfig;
        if (isMergingSemanticsOfDescendants) {
            SemanticsConfiguration copy = semanticsConfiguration.copy();
            mergeConfig(copy);
            return copy;
        }
        return semanticsConfiguration;
    }

    public final int getId() {
        return this.id;
    }

    public final LayoutNode getLayoutInfo() {
        return this.layoutNode;
    }

    public final LayoutNode getLayoutNode$ui_release() {
        return this.layoutNode;
    }

    public final SemanticsModifierNode getOuterSemanticsNode$ui_release() {
        return this.outerSemanticsNode;
    }

    public final SemanticsNode getParent() {
        LayoutNode layoutNode;
        SemanticsModifierNode semanticsModifierNode;
        SemanticsNode semanticsNode = this.fakeNodeParent;
        if (semanticsNode != null) {
            return semanticsNode;
        }
        boolean z = this.mergingEnabled;
        LayoutNode layoutNode2 = this.layoutNode;
        if (z) {
            SemanticsNode$parent$1 semanticsNode$parent$1 = SemanticsNode$parent$1.INSTANCE;
            layoutNode = layoutNode2.getParent$ui_release();
            while (layoutNode != null) {
                if (((Boolean) semanticsNode$parent$1.invoke(layoutNode)).booleanValue()) {
                    break;
                }
                layoutNode = layoutNode.getParent$ui_release();
            }
        }
        layoutNode = null;
        if (layoutNode == null) {
            SemanticsNode$parent$2 semanticsNode$parent$2 = SemanticsNode$parent$2.INSTANCE;
            LayoutNode parent$ui_release = layoutNode2.getParent$ui_release();
            while (true) {
                if (parent$ui_release != null) {
                    if (((Boolean) semanticsNode$parent$2.invoke(parent$ui_release)).booleanValue()) {
                        layoutNode = parent$ui_release;
                        break;
                    }
                    parent$ui_release = parent$ui_release.getParent$ui_release();
                } else {
                    layoutNode = null;
                    break;
                }
            }
        }
        if (layoutNode != null) {
            semanticsModifierNode = SemanticsNodeKt.getOuterSemantics(layoutNode);
        } else {
            semanticsModifierNode = null;
        }
        if (semanticsModifierNode == null) {
            return null;
        }
        return new SemanticsNode(semanticsModifierNode, z, DelegatableNodeKt.requireLayoutNode(semanticsModifierNode));
    }

    /* renamed from: getPositionInRoot-F1C5BW0  reason: not valid java name */
    public final long m293getPositionInRootF1C5BW0() {
        long j;
        long j2;
        if (!this.layoutNode.isAttached()) {
            j2 = Offset.Zero;
            return j2;
        }
        NodeCoordinator findCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        j = Offset.Zero;
        return findCoordinatorToGetBounds$ui_release.m263localToRootMKHz9U(j);
    }

    public final List getReplacedChildren$ui_release() {
        return getChildren(false);
    }

    public final List getReplacedChildrenSortedByBounds$ui_release() {
        return getChildren(true);
    }

    public final Rect getTouchBoundsInRoot() {
        SemanticsModifierNode semanticsModifierNode;
        boolean z;
        Rect rect;
        if (!this.unmergedConfig.isMergingSemanticsOfDescendants() || (semanticsModifierNode = SemanticsNodeKt.getOuterMergingSemantics(this.layoutNode)) == null) {
            semanticsModifierNode = this.outerSemanticsNode;
        }
        Intrinsics.checkNotNullParameter(semanticsModifierNode, "<this>");
        if (!((Modifier.Node) semanticsModifierNode).getNode().isAttached()) {
            rect = Rect.Zero;
            return rect;
        }
        if (SemanticsConfigurationKt.getOrNull(semanticsModifierNode.getSemanticsConfiguration(), SemanticsActions.getOnClick()) != null) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return LayoutCoordinatesKt.boundsInRoot(DelegatableNodeKt.m224requireCoordinator64DMado(semanticsModifierNode, 8));
        }
        return DelegatableNodeKt.m224requireCoordinator64DMado(semanticsModifierNode, 8).touchBoundsInRoot();
    }

    public final SemanticsConfiguration getUnmergedConfig$ui_release() {
        return this.unmergedConfig;
    }

    public final boolean isFake$ui_release() {
        return this.isFake;
    }

    public final List unmergedChildren$ui_release(boolean z, boolean z2) {
        List findOneLayerOfSemanticsWrappers$default;
        final String str;
        if (this.isFake) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        LayoutNode layoutNode = this.layoutNode;
        if (z) {
            findOneLayerOfSemanticsWrappers$default = new ArrayList();
            SemanticsSortKt.findOneLayerOfSemanticsWrappersSortedByBounds(layoutNode, findOneLayerOfSemanticsWrappers$default);
        } else {
            findOneLayerOfSemanticsWrappers$default = SemanticsNodeKt.findOneLayerOfSemanticsWrappers$default(layoutNode);
        }
        int size = findOneLayerOfSemanticsWrappers$default.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new SemanticsNode((SemanticsModifierNode) findOneLayerOfSemanticsWrappers$default.get(i), this.mergingEnabled));
        }
        if (z2) {
            SemanticsPropertyKey role = SemanticsProperties.getRole();
            SemanticsConfiguration semanticsConfiguration = this.unmergedConfig;
            final Role role2 = (Role) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, role);
            if (role2 != null && semanticsConfiguration.isMergingSemanticsOfDescendants() && (!arrayList.isEmpty())) {
                arrayList.add(m292fakeSemanticsNodeypyhhiA(role2, new Function1() { // from class: androidx.compose.ui.semantics.SemanticsNode$emitFakeNodes$fakeNode$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        SemanticsPropertyReceiver fakeSemanticsNode = (SemanticsPropertyReceiver) obj;
                        Intrinsics.checkNotNullParameter(fakeSemanticsNode, "$this$fakeSemanticsNode");
                        int m291unboximpl = Role.this.m291unboximpl();
                        int i2 = SemanticsPropertiesKt.$r8$clinit;
                        SemanticsPropertyKey role3 = SemanticsProperties.getRole();
                        KProperty property = SemanticsPropertiesKt.$$delegatedProperties[8];
                        Role m290boximpl = Role.m290boximpl(m291unboximpl);
                        role3.getClass();
                        Intrinsics.checkNotNullParameter(property, "property");
                        ((SemanticsConfiguration) fakeSemanticsNode).set(role3, m290boximpl);
                        return Unit.INSTANCE;
                    }
                }));
            }
            if (semanticsConfiguration.contains(SemanticsProperties.getContentDescription()) && (!arrayList.isEmpty()) && semanticsConfiguration.isMergingSemanticsOfDescendants()) {
                List list = (List) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.getContentDescription());
                if (list != null) {
                    str = (String) CollectionsKt.firstOrNull(list);
                } else {
                    str = null;
                }
                if (str != null) {
                    arrayList.add(0, m292fakeSemanticsNodeypyhhiA(null, new Function1() { // from class: androidx.compose.ui.semantics.SemanticsNode$emitFakeNodes$fakeNode$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            SemanticsPropertyReceiver fakeSemanticsNode = (SemanticsPropertyReceiver) obj;
                            Intrinsics.checkNotNullParameter(fakeSemanticsNode, "$this$fakeSemanticsNode");
                            String value = str;
                            int i2 = SemanticsPropertiesKt.$r8$clinit;
                            Intrinsics.checkNotNullParameter(value, "value");
                            ((SemanticsConfiguration) fakeSemanticsNode).set(SemanticsProperties.getContentDescription(), CollectionsKt.listOf(value));
                            return Unit.INSTANCE;
                        }
                    }));
                }
            }
        }
        return arrayList;
    }

    public /* synthetic */ SemanticsNode(SemanticsModifierNode semanticsModifierNode, boolean z) {
        this(semanticsModifierNode, z, DelegatableNodeKt.requireLayoutNode(semanticsModifierNode));
    }
}
