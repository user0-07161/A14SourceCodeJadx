package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.BackwardsCompatNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FocusInvalidationManager {
    private final Function1 onRequestApplyChangesListener;
    private Set focusTargetNodes = new LinkedHashSet();
    private Set focusEventNodes = new LinkedHashSet();
    private Set focusPropertiesNodes = new LinkedHashSet();
    private final Function0 invalidateNodes = new Function0() { // from class: androidx.compose.ui.focus.FocusInvalidationManager$invalidateNodes$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Set<FocusPropertiesModifierNode> set;
            Set set2;
            Set<FocusEventModifierNode> set3;
            Set set4;
            Set<FocusTargetModifierNode> set5;
            Set set6;
            Set set7;
            Set set8;
            Set set9;
            Set set10;
            Set set11;
            set = FocusInvalidationManager.this.focusPropertiesNodes;
            FocusInvalidationManager focusInvalidationManager = FocusInvalidationManager.this;
            for (FocusPropertiesModifierNode focusPropertiesModifierNode : set) {
                Modifier.Node node = (Modifier.Node) focusPropertiesModifierNode;
                if (node.getNode().isAttached()) {
                    MutableVector mutableVector = new MutableVector(new Modifier.Node[16]);
                    Modifier.Node child$ui_release = node.getNode().getChild$ui_release();
                    if (child$ui_release == null) {
                        DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector, node.getNode());
                    } else {
                        mutableVector.add(child$ui_release);
                    }
                    while (mutableVector.isNotEmpty()) {
                        Modifier.Node node2 = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
                        if ((node2.getAggregateChildKindSet$ui_release() & 1024) == 0) {
                            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector, node2);
                        } else {
                            while (true) {
                                if (node2 == null) {
                                    break;
                                } else if ((node2.getKindSet$ui_release() & 1024) != 0) {
                                    if (node2 instanceof FocusTargetModifierNode) {
                                        set11 = focusInvalidationManager.focusTargetNodes;
                                        set11.add((FocusTargetModifierNode) node2);
                                    }
                                } else {
                                    node2 = node2.getChild$ui_release();
                                }
                            }
                        }
                    }
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
            set2 = FocusInvalidationManager.this.focusPropertiesNodes;
            set2.clear();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            set3 = FocusInvalidationManager.this.focusEventNodes;
            FocusInvalidationManager focusInvalidationManager2 = FocusInvalidationManager.this;
            for (FocusEventModifierNode focusEventModifierNode : set3) {
                Modifier.Node node3 = (Modifier.Node) focusEventModifierNode;
                if (node3.getNode().isAttached()) {
                    if (node3.getNode().isAttached()) {
                        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16]);
                        Modifier.Node child$ui_release2 = node3.getNode().getChild$ui_release();
                        if (child$ui_release2 == null) {
                            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node3.getNode());
                        } else {
                            mutableVector2.add(child$ui_release2);
                        }
                        FocusTargetModifierNode focusTargetModifierNode = null;
                        boolean z = true;
                        boolean z2 = false;
                        while (mutableVector2.isNotEmpty()) {
                            Modifier.Node node4 = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
                            if ((node4.getAggregateChildKindSet$ui_release() & 1024) == 0) {
                                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node4);
                            } else {
                                while (true) {
                                    if (node4 == null) {
                                        break;
                                    } else if ((node4.getKindSet$ui_release() & 1024) != 0) {
                                        if (node4 instanceof FocusTargetModifierNode) {
                                            FocusTargetModifierNode focusTargetModifierNode2 = (FocusTargetModifierNode) node4;
                                            if (focusTargetModifierNode != null) {
                                                z2 = true;
                                            }
                                            set10 = focusInvalidationManager2.focusTargetNodes;
                                            if (set10.contains(focusTargetModifierNode2)) {
                                                linkedHashSet.add(focusTargetModifierNode2);
                                                z = false;
                                            }
                                            focusTargetModifierNode = focusTargetModifierNode2;
                                        }
                                    } else {
                                        node4 = node4.getChild$ui_release();
                                    }
                                }
                            }
                        }
                        if (z) {
                            if (!z2) {
                                if (focusTargetModifierNode != null) {
                                    focusTargetModifierNode.getFocusState();
                                }
                            } else {
                                FocusEventModifierNodeKt.getFocusState(focusEventModifierNode);
                            }
                            ((BackwardsCompatNode) focusEventModifierNode).getClass();
                            throw new IllegalStateException("Check failed.".toString());
                        }
                    } else {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                }
            }
            set4 = FocusInvalidationManager.this.focusEventNodes;
            set4.clear();
            set5 = FocusInvalidationManager.this.focusTargetNodes;
            for (FocusTargetModifierNode focusTargetModifierNode3 : set5) {
                if (focusTargetModifierNode3.isAttached()) {
                    FocusStateImpl focusState = focusTargetModifierNode3.getFocusState();
                    focusTargetModifierNode3.invalidateFocus$ui_release();
                    if (!Intrinsics.areEqual(focusState, focusTargetModifierNode3.getFocusState()) || linkedHashSet.contains(focusTargetModifierNode3)) {
                        FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode3);
                    }
                }
            }
            set6 = FocusInvalidationManager.this.focusTargetNodes;
            set6.clear();
            linkedHashSet.clear();
            set7 = FocusInvalidationManager.this.focusPropertiesNodes;
            if (set7.isEmpty()) {
                set8 = FocusInvalidationManager.this.focusEventNodes;
                if (set8.isEmpty()) {
                    set9 = FocusInvalidationManager.this.focusTargetNodes;
                    if (set9.isEmpty()) {
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("Check failed.".toString());
                }
                throw new IllegalStateException("Check failed.".toString());
            }
            throw new IllegalStateException("Check failed.".toString());
        }
    };

    public FocusInvalidationManager(Function1 function1) {
        this.onRequestApplyChangesListener = function1;
    }

    public final void scheduleInvalidation(FocusTargetModifierNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        scheduleInvalidation(this.focusTargetNodes, node);
    }

    public final void scheduleInvalidation(FocusEventModifierNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        scheduleInvalidation(this.focusEventNodes, node);
    }

    private final void scheduleInvalidation(Set set, Object obj) {
        if (set.contains(obj)) {
            return;
        }
        set.add(obj);
        if (this.focusPropertiesNodes.size() + this.focusEventNodes.size() + this.focusTargetNodes.size() == 1) {
            this.onRequestApplyChangesListener.invoke(this.invalidateNodes);
        }
    }
}
