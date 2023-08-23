package androidx.compose.ui.focus;

import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class OneDimensionalFocusSearchKt {
    private static final boolean backwardFocusSearch(FocusTargetModifierNode focusTargetModifierNode, Function1 function1) {
        int ordinal = focusTargetModifierNode.getFocusStateImpl$ui_release().ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal == 3) {
                        if (pickChildForBackwardSearch(focusTargetModifierNode, function1)) {
                            return true;
                        }
                        focusTargetModifierNode.fetchFocusProperties$ui_release();
                        if (((Boolean) function1.invoke(focusTargetModifierNode)).booleanValue()) {
                            return true;
                        }
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                }
            } else {
                FocusTargetModifierNode activeChild = FocusTraversalKt.getActiveChild(focusTargetModifierNode);
                if (activeChild != null) {
                    int ordinal2 = activeChild.getFocusStateImpl$ui_release().ordinal();
                    if (ordinal2 != 0) {
                        if (ordinal2 != 1) {
                            if (ordinal2 != 2) {
                                if (ordinal2 != 3) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
                            }
                        } else if (backwardFocusSearch(activeChild, function1) || m31generateAndSearchChildren4C6V_qg(focusTargetModifierNode, activeChild, 2, function1)) {
                            return true;
                        } else {
                            focusTargetModifierNode.fetchFocusProperties$ui_release();
                            if (((Boolean) function1.invoke(activeChild)).booleanValue()) {
                                return true;
                            }
                        }
                    }
                    return m31generateAndSearchChildren4C6V_qg(focusTargetModifierNode, activeChild, 2, function1);
                }
                throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
            }
            return false;
        }
        return pickChildForBackwardSearch(focusTargetModifierNode, function1);
    }

    private static final boolean forwardFocusSearch(FocusTargetModifierNode focusTargetModifierNode, Function1 function1) {
        boolean z;
        int ordinal = focusTargetModifierNode.getFocusStateImpl$ui_release().ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal == 3) {
                        focusTargetModifierNode.fetchFocusProperties$ui_release();
                        return ((Boolean) function1.invoke(focusTargetModifierNode)).booleanValue();
                    }
                    throw new NoWhenBranchMatchedException();
                }
            } else {
                FocusTargetModifierNode activeChild = FocusTraversalKt.getActiveChild(focusTargetModifierNode);
                if (activeChild != null) {
                    if (!forwardFocusSearch(activeChild, function1) && !m31generateAndSearchChildren4C6V_qg(focusTargetModifierNode, activeChild, 1, function1)) {
                        return false;
                    }
                    return true;
                }
                throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
            }
        }
        MutableVector mutableVector = new MutableVector(new FocusTargetModifierNode[16]);
        if (focusTargetModifierNode.getNode().isAttached()) {
            MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16]);
            Modifier.Node child$ui_release = focusTargetModifierNode.getNode().getChild$ui_release();
            if (child$ui_release == null) {
                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, focusTargetModifierNode.getNode());
            } else {
                mutableVector2.add(child$ui_release);
            }
            while (mutableVector2.isNotEmpty()) {
                Modifier.Node node = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
                if ((node.getAggregateChildKindSet$ui_release() & 1024) == 0) {
                    DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node);
                } else {
                    while (true) {
                        if (node == null) {
                            break;
                        } else if ((node.getKindSet$ui_release() & 1024) != 0) {
                            if (node instanceof FocusTargetModifierNode) {
                                mutableVector.add((FocusTargetModifierNode) node);
                            }
                        } else {
                            node = node.getChild$ui_release();
                        }
                    }
                }
            }
            mutableVector.sortWith(FocusableChildrenComparator.INSTANCE);
            int size = mutableVector.getSize();
            if (size <= 0) {
                return false;
            }
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                FocusTargetModifierNode focusTargetModifierNode2 = (FocusTargetModifierNode) content[i];
                if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode2) && forwardFocusSearch(focusTargetModifierNode2, function1)) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
                i++;
            } while (i < size);
            return false;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* renamed from: generateAndSearchChildren-4C6V_qg  reason: not valid java name */
    private static final boolean m31generateAndSearchChildren4C6V_qg(final FocusTargetModifierNode focusTargetModifierNode, final FocusTargetModifierNode focusTargetModifierNode2, final int i, final Function1 function1) {
        if (m33searchChildren4C6V_qg(focusTargetModifierNode, focusTargetModifierNode2, i, function1)) {
            return true;
        }
        BeyondBoundsLayoutKt.m25searchBeyondBoundsOMvw8(focusTargetModifierNode, new Function1() { // from class: androidx.compose.ui.focus.OneDimensionalFocusSearchKt$generateAndSearchChildren$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                Intrinsics.checkNotNullParameter(null, "$this$searchBeyondBounds");
                throw null;
            }
        });
        return false;
    }

    /* renamed from: oneDimensionalFocusSearch--OM-vw8  reason: not valid java name */
    public static final boolean m32oneDimensionalFocusSearchOMvw8(FocusTargetModifierNode focusTargetModifierNode, int i, Function1 function1) {
        boolean z;
        boolean z2 = false;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return forwardFocusSearch(focusTargetModifierNode, function1);
        }
        if (i == 2) {
            z2 = true;
        }
        if (z2) {
            return backwardFocusSearch(focusTargetModifierNode, function1);
        }
        throw new IllegalStateException("This function should only be used for 1-D focus search".toString());
    }

    private static final boolean pickChildForBackwardSearch(FocusTargetModifierNode focusTargetModifierNode, Function1 function1) {
        MutableVector mutableVector = new MutableVector(new FocusTargetModifierNode[16]);
        if (focusTargetModifierNode.getNode().isAttached()) {
            MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16]);
            Modifier.Node child$ui_release = focusTargetModifierNode.getNode().getChild$ui_release();
            if (child$ui_release == null) {
                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, focusTargetModifierNode.getNode());
            } else {
                mutableVector2.add(child$ui_release);
            }
            while (mutableVector2.isNotEmpty()) {
                Modifier.Node node = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
                if ((node.getAggregateChildKindSet$ui_release() & 1024) == 0) {
                    DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node);
                } else {
                    while (true) {
                        if (node == null) {
                            break;
                        } else if ((node.getKindSet$ui_release() & 1024) != 0) {
                            if (node instanceof FocusTargetModifierNode) {
                                mutableVector.add((FocusTargetModifierNode) node);
                            }
                        } else {
                            node = node.getChild$ui_release();
                        }
                    }
                }
            }
            mutableVector.sortWith(FocusableChildrenComparator.INSTANCE);
            int size = mutableVector.getSize();
            if (size > 0) {
                int i = size - 1;
                Object[] content = mutableVector.getContent();
                do {
                    FocusTargetModifierNode focusTargetModifierNode2 = (FocusTargetModifierNode) content[i];
                    if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode2) && backwardFocusSearch(focusTargetModifierNode2, function1)) {
                        return true;
                    }
                    i--;
                } while (i >= 0);
                return false;
            }
            return false;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* renamed from: searchChildren-4C6V_qg  reason: not valid java name */
    private static final boolean m33searchChildren4C6V_qg(FocusTargetModifierNode focusTargetModifierNode, FocusTargetModifierNode focusTargetModifierNode2, int i, Function1 function1) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        if (focusTargetModifierNode.getFocusStateImpl$ui_release() == FocusStateImpl.ActiveParent) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            MutableVector mutableVector = new MutableVector(new FocusTargetModifierNode[16]);
            if (focusTargetModifierNode.getNode().isAttached()) {
                MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16]);
                Modifier.Node child$ui_release = focusTargetModifierNode.getNode().getChild$ui_release();
                if (child$ui_release == null) {
                    DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, focusTargetModifierNode.getNode());
                } else {
                    mutableVector2.add(child$ui_release);
                }
                while (mutableVector2.isNotEmpty()) {
                    Modifier.Node node = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
                    if ((node.getAggregateChildKindSet$ui_release() & 1024) == 0) {
                        DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node);
                    } else {
                        while (true) {
                            if (node == null) {
                                break;
                            } else if ((node.getKindSet$ui_release() & 1024) != 0) {
                                if (node instanceof FocusTargetModifierNode) {
                                    mutableVector.add((FocusTargetModifierNode) node);
                                }
                            } else {
                                node = node.getChild$ui_release();
                            }
                        }
                    }
                }
                mutableVector.sortWith(FocusableChildrenComparator.INSTANCE);
                if (i == 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    IntRange intRange = new IntRange(0, mutableVector.getSize() - 1);
                    int first = intRange.getFirst();
                    int last = intRange.getLast();
                    if (first <= last) {
                        boolean z6 = false;
                        while (true) {
                            if (z6) {
                                FocusTargetModifierNode focusTargetModifierNode3 = (FocusTargetModifierNode) mutableVector.getContent()[first];
                                if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode3) && forwardFocusSearch(focusTargetModifierNode3, function1)) {
                                    return true;
                                }
                            }
                            if (Intrinsics.areEqual(mutableVector.getContent()[first], focusTargetModifierNode2)) {
                                z6 = true;
                            }
                            if (first == last) {
                                break;
                            }
                            first++;
                        }
                    }
                } else {
                    if (i == 2) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        IntRange intRange2 = new IntRange(0, mutableVector.getSize() - 1);
                        int first2 = intRange2.getFirst();
                        int last2 = intRange2.getLast();
                        if (first2 <= last2) {
                            boolean z7 = false;
                            while (true) {
                                if (z7) {
                                    FocusTargetModifierNode focusTargetModifierNode4 = (FocusTargetModifierNode) mutableVector.getContent()[last2];
                                    if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode4) && backwardFocusSearch(focusTargetModifierNode4, function1)) {
                                        return true;
                                    }
                                }
                                if (Intrinsics.areEqual(mutableVector.getContent()[last2], focusTargetModifierNode2)) {
                                    z7 = true;
                                }
                                if (last2 == first2) {
                                    break;
                                }
                                last2--;
                            }
                        }
                    } else {
                        throw new IllegalStateException("This function should only be used for 1-D focus search".toString());
                    }
                }
                if (i == 1) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (!z4) {
                    focusTargetModifierNode.fetchFocusProperties$ui_release();
                    Modifier.Node nearestAncestor = DelegatableNodeKt.nearestAncestor(focusTargetModifierNode, 1024);
                    if (!(nearestAncestor instanceof FocusTargetModifierNode)) {
                        nearestAncestor = null;
                    }
                    if (((FocusTargetModifierNode) nearestAncestor) != null) {
                        z5 = false;
                    }
                    if (!z5) {
                        return ((Boolean) function1.invoke(focusTargetModifierNode)).booleanValue();
                    }
                }
                return false;
            }
            throw new IllegalStateException("Check failed.".toString());
        }
        throw new IllegalStateException("This function should only be used within a parent that has focus.".toString());
    }
}
