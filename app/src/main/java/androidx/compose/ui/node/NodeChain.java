package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.modifier.ModifierLocalNode;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class NodeChain {
    private MutableVector buffer;
    private Differ cachedDiffer;
    private MutableVector current;
    private Modifier.Node head;
    private final InnerNodeCoordinator innerCoordinator;
    private final LayoutNode layoutNode;
    private NodeCoordinator outerCoordinator;
    private final Modifier.Node tail;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Differ {
        private MutableVector after;
        private int aggregateChildKindSet;
        private MutableVector before;
        private Modifier.Node node;
        final /* synthetic */ NodeChain this$0;

        public Differ(NodeChain nodeChain, Modifier.Node node, int i, MutableVector mutableVector, MutableVector mutableVector2) {
            Intrinsics.checkNotNullParameter(node, "node");
            this.this$0 = nodeChain;
            this.node = node;
            this.aggregateChildKindSet = i;
            this.before = mutableVector;
            this.after = mutableVector2;
        }

        public final boolean areItemsTheSame(int i, int i2) {
            boolean z;
            char c;
            Modifier.Element prev = (Modifier.Element) this.before.getContent()[i];
            Modifier.Element next = (Modifier.Element) this.after.getContent()[i2];
            int i3 = NodeChainKt.$r8$clinit;
            Intrinsics.checkNotNullParameter(prev, "prev");
            Intrinsics.checkNotNullParameter(next, "next");
            if (Intrinsics.areEqual(prev, next)) {
                c = 2;
            } else {
                if (prev.getClass() == next.getClass()) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    c = 1;
                } else {
                    c = 0;
                }
            }
            if (c == 0) {
                return false;
            }
            return true;
        }

        public final void insert(int i) {
            Modifier.Node node = this.node;
            Modifier.Node access$createAndInsertNodeAsParent = NodeChain.access$createAndInsertNodeAsParent(this.this$0, (Modifier.Element) this.after.getContent()[i], node);
            this.node = access$createAndInsertNodeAsParent;
            int kindSet$ui_release = access$createAndInsertNodeAsParent.getKindSet$ui_release() | this.aggregateChildKindSet;
            this.aggregateChildKindSet = kindSet$ui_release;
            this.node.setAggregateChildKindSet$ui_release(kindSet$ui_release);
        }

        public final void remove() {
            Modifier.Node parent$ui_release = this.node.getParent$ui_release();
            Intrinsics.checkNotNull(parent$ui_release);
            this.node = parent$ui_release;
            this.this$0.getClass();
            Modifier.Node node = this.node;
            if (node.isAttached()) {
                NodeKindKt.autoInvalidateRemovedNode(node);
                node.detach$ui_release();
            }
            Modifier.Node child$ui_release = node.getChild$ui_release();
            Modifier.Node parent$ui_release2 = node.getParent$ui_release();
            if (child$ui_release != null) {
                child$ui_release.setParent$ui_release(parent$ui_release2);
                node.setChild$ui_release(null);
            }
            if (parent$ui_release2 != null) {
                parent$ui_release2.setChild$ui_release(child$ui_release);
                node.setParent$ui_release(null);
            }
            Intrinsics.checkNotNull(child$ui_release);
            this.node = child$ui_release;
        }

        public final void same(int i, int i2) {
            Modifier.Node parent$ui_release = this.node.getParent$ui_release();
            Intrinsics.checkNotNull(parent$ui_release);
            this.node = parent$ui_release;
            Modifier.Element element = (Modifier.Element) this.before.getContent()[i];
            Modifier.Element element2 = (Modifier.Element) this.after.getContent()[i2];
            boolean areEqual = Intrinsics.areEqual(element, element2);
            NodeChain nodeChain = this.this$0;
            if (!areEqual) {
                this.node = NodeChain.access$updateNodeAndReplaceIfNeeded(nodeChain, element, element2, this.node);
                nodeChain.getClass();
            } else {
                nodeChain.getClass();
            }
            int kindSet$ui_release = this.aggregateChildKindSet | this.node.getKindSet$ui_release();
            this.aggregateChildKindSet = kindSet$ui_release;
            this.node.setAggregateChildKindSet$ui_release(kindSet$ui_release);
        }

        public final void setAfter(MutableVector mutableVector) {
            this.after = mutableVector;
        }

        public final void setAggregateChildKindSet(int i) {
            this.aggregateChildKindSet = i;
        }

        public final void setBefore(MutableVector mutableVector) {
            this.before = mutableVector;
        }

        public final void setNode(Modifier.Node node) {
            Intrinsics.checkNotNullParameter(node, "<set-?>");
            this.node = node;
        }
    }

    public NodeChain(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.layoutNode = layoutNode;
        InnerNodeCoordinator innerNodeCoordinator = new InnerNodeCoordinator(layoutNode);
        this.innerCoordinator = innerNodeCoordinator;
        this.outerCoordinator = innerNodeCoordinator;
        Modifier.Node tail = innerNodeCoordinator.getTail();
        this.tail = tail;
        this.head = tail;
    }

    public static final /* synthetic */ Modifier.Node access$createAndInsertNodeAsParent(NodeChain nodeChain, Modifier.Element element, Modifier.Node node) {
        nodeChain.getClass();
        return createAndInsertNodeAsParent(element, node);
    }

    public static final /* synthetic */ Modifier.Node access$updateNodeAndReplaceIfNeeded(NodeChain nodeChain, Modifier.Element element, Modifier.Element element2, Modifier.Node node) {
        nodeChain.getClass();
        return updateNodeAndReplaceIfNeeded(element, element2, node);
    }

    private static Modifier.Node createAndInsertNodeAsParent(Modifier.Element element, Modifier.Node node) {
        Modifier.Node node2;
        int i;
        if (element instanceof ModifierNodeElement) {
            node2 = ((ModifierNodeElement) element).create();
            Intrinsics.checkNotNullParameter(node2, "node");
            if (node2 instanceof LayoutModifierNode) {
                i = 3;
            } else {
                i = 1;
            }
            if (node2 instanceof DrawModifierNode) {
                i |= 4;
            }
            if (node2 instanceof SemanticsModifierNode) {
                i |= 8;
            }
            if (node2 instanceof PointerInputModifierNode) {
                i |= 16;
            }
            if (node2 instanceof ModifierLocalNode) {
                i |= 32;
            }
            if (node2 instanceof ParentDataModifierNode) {
                i |= 64;
            }
            if (node2 instanceof LayoutAwareModifierNode) {
                i |= 128;
            }
            if (node2 instanceof GlobalPositionAwareModifierNode) {
                i |= 256;
            }
            if (node2 instanceof IntermediateLayoutModifierNode) {
                i |= 512;
            }
            if (node2 instanceof FocusTargetModifierNode) {
                i |= 1024;
            }
            if (node2 instanceof FocusPropertiesModifierNode) {
                i |= 2048;
            }
            if (node2 instanceof FocusEventModifierNode) {
                i |= 4096;
            }
            if (node2 instanceof KeyInputModifierNode) {
                i |= 8192;
            }
            if (node2 instanceof RotaryInputModifierNode) {
                i |= 16384;
            }
            node2.setKindSet$ui_release(i);
        } else {
            node2 = new BackwardsCompatNode(element);
        }
        Modifier.Node parent$ui_release = node.getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.setChild$ui_release(node2);
            node2.setParent$ui_release(parent$ui_release);
        }
        node.setParent$ui_release(node2);
        node2.setChild$ui_release(node);
        return node2;
    }

    private final void structuralUpdate(MutableVector mutableVector, int i, MutableVector mutableVector2, int i2, Modifier.Node node) {
        int i3;
        IntStack intStack;
        IntStack intStack2;
        int i4;
        int i5;
        boolean z;
        IntStack intStack3;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z9;
        Differ differ = this.cachedDiffer;
        if (differ == null) {
            differ = new Differ(this, node, node.getAggregateChildKindSet$ui_release(), mutableVector, mutableVector2);
            this.cachedDiffer = differ;
        } else {
            differ.setNode(node);
            differ.setAggregateChildKindSet(node.getAggregateChildKindSet$ui_release());
            differ.setBefore(mutableVector);
            differ.setAfter(mutableVector2);
        }
        int i14 = 1;
        int i15 = ((i + i2) + 1) / 2;
        IntStack intStack4 = new IntStack(i15 * 3);
        IntStack intStack5 = new IntStack(i15 * 4);
        int i16 = 0;
        intStack5.pushRange(0, i, 0, i2);
        int i17 = (i15 * 2) + 1;
        int[] iArr = new int[i17];
        int[] iArr2 = new int[i17];
        int[] iArr3 = new int[5];
        while (intStack5.isNotEmpty()) {
            int pop = intStack5.pop();
            int pop2 = intStack5.pop();
            int pop3 = intStack5.pop();
            int pop4 = intStack5.pop();
            int i18 = pop3 - pop4;
            int i19 = pop - pop2;
            if (i18 >= i14 && i19 >= i14) {
                int i20 = ((i18 + i19) + 1) / 2;
                int i21 = i17 / 2;
                int i22 = i21 + 1;
                iArr[i22] = pop4;
                iArr2[i22] = pop3;
                i3 = i17;
                int i23 = 0;
                while (i23 < i20) {
                    int i24 = i18 - i19;
                    int i25 = i20;
                    int i26 = i19;
                    if (Math.abs(i24) % 2 == 1) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    int i27 = -i23;
                    int i28 = i18;
                    int i29 = i27;
                    while (true) {
                        if (i29 <= i23) {
                            if (i29 != i27 && (i29 == i23 || iArr[i29 + 1 + i21] <= iArr[(i29 - 1) + i21])) {
                                i10 = iArr[(i29 - 1) + i21];
                                i11 = i10 + 1;
                            } else {
                                i10 = iArr[i29 + 1 + i21];
                                i11 = i10;
                            }
                            int i30 = ((i11 - pop4) + pop2) - i29;
                            if (i23 != 0 && i11 == i10) {
                                i12 = i30 - 1;
                                intStack2 = intStack5;
                                i13 = i30;
                            } else {
                                i12 = i30;
                                intStack2 = intStack5;
                                i13 = i12;
                            }
                            while (i11 < pop3 && i13 < pop && differ.areItemsTheSame(i11, i13)) {
                                i11++;
                                i13++;
                            }
                            iArr[i21 + i29] = i11;
                            if (z5) {
                                z9 = z5;
                                int i31 = i24 - i29;
                                intStack = intStack4;
                                if (i31 >= i27 + 1 && i31 <= i23 - 1 && iArr2[i21 + i31] <= i11) {
                                    iArr3[0] = i10;
                                    iArr3[1] = i12;
                                    iArr3[2] = i11;
                                    iArr3[3] = i13;
                                    iArr3[4] = 0;
                                    z6 = true;
                                    break;
                                }
                            } else {
                                z9 = z5;
                                intStack = intStack4;
                            }
                            i29 += 2;
                            intStack5 = intStack2;
                            z5 = z9;
                            intStack4 = intStack;
                        } else {
                            intStack = intStack4;
                            intStack2 = intStack5;
                            z6 = false;
                            break;
                        }
                    }
                    if (z6) {
                        i4 = pop;
                        i5 = pop3;
                    } else {
                        if (i24 % 2 == 0) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        int i32 = i27;
                        while (true) {
                            if (i32 <= i23) {
                                if (i32 != i27 && (i32 == i23 || iArr2[i32 + 1 + i21] >= iArr2[(i32 - 1) + i21])) {
                                    i6 = iArr2[(i32 - 1) + i21];
                                    i7 = i6 - 1;
                                } else {
                                    i6 = iArr2[i32 + 1 + i21];
                                    i7 = i6;
                                }
                                int i33 = pop - ((pop3 - i7) - i32);
                                if (i23 != 0 && i7 == i6) {
                                    i8 = i33 + 1;
                                } else {
                                    i8 = i33;
                                }
                                while (i7 > pop4 && i33 > pop2) {
                                    i4 = pop;
                                    int i34 = i7 - 1;
                                    i5 = pop3;
                                    int i35 = i33 - 1;
                                    if (!differ.areItemsTheSame(i34, i35)) {
                                        break;
                                    }
                                    i7 = i34;
                                    i33 = i35;
                                    pop = i4;
                                    pop3 = i5;
                                }
                                i4 = pop;
                                i5 = pop3;
                                iArr2[i21 + i32] = i7;
                                if (z7 && (i9 = i24 - i32) >= i27 && i9 <= i23 && iArr[i21 + i9] >= i7) {
                                    iArr3[0] = i7;
                                    iArr3[1] = i33;
                                    iArr3[2] = i6;
                                    iArr3[3] = i8;
                                    iArr3[4] = 1;
                                    z8 = true;
                                    break;
                                }
                                i32 += 2;
                                pop = i4;
                                pop3 = i5;
                            } else {
                                i4 = pop;
                                i5 = pop3;
                                z8 = false;
                                break;
                            }
                        }
                        if (!z8) {
                            i23++;
                            i18 = i28;
                            i19 = i26;
                            i20 = i25;
                            intStack5 = intStack2;
                            pop = i4;
                            pop3 = i5;
                            intStack4 = intStack;
                        }
                    }
                    z = true;
                    break;
                }
            } else {
                i3 = i17;
            }
            intStack = intStack4;
            intStack2 = intStack5;
            i4 = pop;
            i5 = pop3;
            z = false;
            if (z) {
                if (Snake.m274getDiagonalSizeimpl(iArr3) > 0) {
                    int i36 = iArr3[3];
                    int i37 = iArr3[1];
                    int i38 = i36 - i37;
                    int i39 = iArr3[2];
                    int i40 = iArr3[0];
                    int i41 = i39 - i40;
                    if (i38 != i41) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        if (iArr3[4] != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (z3) {
                            intStack3 = intStack;
                            intStack3.pushDiagonal(i40, i37, Snake.m274getDiagonalSizeimpl(iArr3));
                        } else {
                            intStack3 = intStack;
                            if (i38 > i41) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            if (z4) {
                                intStack3.pushDiagonal(i40, i37 + 1, Snake.m274getDiagonalSizeimpl(iArr3));
                            } else {
                                intStack3.pushDiagonal(i40 + 1, i37, Snake.m274getDiagonalSizeimpl(iArr3));
                            }
                        }
                    } else {
                        intStack3 = intStack;
                        intStack3.pushDiagonal(i40, i37, i41);
                    }
                } else {
                    intStack3 = intStack;
                }
                IntStack intStack6 = intStack2;
                intStack6.pushRange(pop4, iArr3[0], pop2, iArr3[1]);
                intStack6.pushRange(iArr3[2], i5, iArr3[3], i4);
                intStack5 = intStack6;
                i16 = 0;
                i17 = i3;
                intStack4 = intStack3;
                i14 = 1;
            } else {
                i17 = i3;
                intStack5 = intStack2;
                intStack4 = intStack;
                i14 = 1;
                i16 = 0;
            }
        }
        IntStack intStack7 = intStack4;
        intStack7.sortDiagonals();
        int i42 = i;
        int i43 = i2;
        intStack7.pushDiagonal(i42, i43, i16);
        while (intStack7.isNotEmpty()) {
            int pop5 = intStack7.pop();
            int pop6 = intStack7.pop();
            int pop7 = intStack7.pop();
            while (i42 > pop7) {
                i42--;
                differ.remove();
            }
            while (i43 > pop6) {
                i43--;
                differ.insert(i43);
            }
            while (true) {
                int i44 = pop5 - 1;
                if (pop5 > 0) {
                    i42--;
                    i43--;
                    differ.same(i42, i43);
                    pop5 = i44;
                }
            }
        }
        while (i42 > 0) {
            i42--;
            differ.remove();
        }
        while (i43 > 0) {
            i43--;
            differ.insert(i43);
        }
    }

    private static Modifier.Node updateNodeAndReplaceIfNeeded(Modifier.Element element, Modifier.Element element2, Modifier.Node node) {
        if ((element instanceof ModifierNodeElement) && (element2 instanceof ModifierNodeElement)) {
            ModifierNodeElement modifierNodeElement = (ModifierNodeElement) element2;
            int i = NodeChainKt.$r8$clinit;
            Intrinsics.checkNotNull(node, "null cannot be cast to non-null type T of androidx.compose.ui.node.NodeChainKt.updateUnsafe");
            Modifier.Node update = modifierNodeElement.update(node);
            if (update != node) {
                if (node.isAttached()) {
                    NodeKindKt.autoInvalidateRemovedNode(node);
                    node.detach$ui_release();
                }
                Modifier.Node parent$ui_release = node.getParent$ui_release();
                if (parent$ui_release != null) {
                    update.setParent$ui_release(parent$ui_release);
                    parent$ui_release.setChild$ui_release(update);
                    node.setParent$ui_release(null);
                }
                Modifier.Node child$ui_release = node.getChild$ui_release();
                if (child$ui_release != null) {
                    update.setChild$ui_release(child$ui_release);
                    child$ui_release.setParent$ui_release(update);
                    node.setChild$ui_release(null);
                }
                update.updateCoordinator$ui_release(node.getCoordinator$ui_release());
                if (node.isAttached()) {
                    NodeKindKt.autoInvalidateInsertedNode(update);
                }
                return update;
            }
            if (modifierNodeElement.getAutoInvalidate$ui_release() && update.isAttached()) {
                NodeKindKt.autoInvalidateUpdatedNode(update);
            }
            return update;
        } else if (node instanceof BackwardsCompatNode) {
            ((BackwardsCompatNode) node).setElement(element2);
            if (node.isAttached()) {
                NodeKindKt.autoInvalidateUpdatedNode(node);
            }
            return node;
        } else {
            throw new IllegalStateException("Unknown Modifier.Node type".toString());
        }
    }

    public final void attach(boolean z) {
        for (Modifier.Node node = this.head; node != null; node = node.getChild$ui_release()) {
            if (!node.isAttached()) {
                node.attach$ui_release();
                if (z) {
                    NodeKindKt.autoInvalidateInsertedNode(node);
                }
            }
        }
    }

    public final void detach$ui_release() {
        for (Modifier.Node node = this.tail; node != null; node = node.getParent$ui_release()) {
            if (node.isAttached()) {
                node.detach$ui_release();
            }
        }
    }

    public final Modifier.Node getHead$ui_release() {
        return this.head;
    }

    public final InnerNodeCoordinator getInnerCoordinator$ui_release() {
        return this.innerCoordinator;
    }

    public final NodeCoordinator getOuterCoordinator$ui_release() {
        return this.outerCoordinator;
    }

    public final Modifier.Node getTail$ui_release() {
        return this.tail;
    }

    public final boolean has$ui_release() {
        if ((this.head.getAggregateChildKindSet$ui_release() & 7168) != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: has-H91voCI$ui_release */
    public final boolean m250hasH91voCI$ui_release(int i) {
        if ((this.head.getAggregateChildKindSet$ui_release() & i) != 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Modifier.Node node = this.head;
        Modifier.Node node2 = this.tail;
        if (node != node2) {
            while (true) {
                if (node == null || node == node2) {
                    break;
                }
                sb.append(String.valueOf(node));
                if (node.getChild$ui_release() == node2) {
                    sb.append("]");
                    break;
                }
                sb.append(",");
                node = node.getChild$ui_release();
            }
        } else {
            sb.append("]");
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    /* JADX WARN: Removed duplicated region for block: B:206:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x020e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateFrom$ui_release(androidx.compose.ui.Modifier r15) {
        /*
            Method dump skipped, instructions count: 546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeChain.updateFrom$ui_release(androidx.compose.ui.Modifier):void");
    }
}
