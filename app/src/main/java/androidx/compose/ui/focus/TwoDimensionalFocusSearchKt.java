package androidx.compose.ui.focus;

import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.DelegatableNodeKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TwoDimensionalFocusSearchKt {
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
        if (r10.getLeft() >= r12.getRight()) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003b, code lost:
        if (r10.getRight() <= r12.getLeft()) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004f, code lost:
        if (r10.getTop() >= r12.getBottom()) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0063, code lost:
        if (r10.getBottom() <= r12.getTop()) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0065, code lost:
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0067, code lost:
        r3 = false;
     */
    /* renamed from: beamBeats-I7lrPNg  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final boolean m34beamBeatsI7lrPNg(androidx.compose.ui.geometry.Rect r10, androidx.compose.ui.geometry.Rect r11, androidx.compose.ui.geometry.Rect r12, int r13) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.TwoDimensionalFocusSearchKt.m34beamBeatsI7lrPNg(androidx.compose.ui.geometry.Rect, androidx.compose.ui.geometry.Rect, androidx.compose.ui.geometry.Rect, int):boolean");
    }

    private static final boolean beamBeats_I7lrPNg$inSourceBeam(int i, Rect rect, Rect rect2) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (i == 3) {
            z = true;
        } else {
            z = false;
        }
        if (z || i == 4) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (rect.getBottom() <= rect2.getTop() || rect.getTop() >= rect2.getBottom()) {
                return false;
            }
        } else {
            if (i == 5) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3 || i == 6) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (z4) {
                if (rect.getRight() <= rect2.getLeft() || rect.getLeft() >= rect2.getRight()) {
                    return false;
                }
            } else {
                throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
            }
        }
        return true;
    }

    /* renamed from: findBestCandidate-4WY_MpI  reason: not valid java name */
    private static final FocusTargetModifierNode m35findBestCandidate4WY_MpI(MutableVector mutableVector, Rect rect, int i) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        Rect translate;
        boolean z5;
        if (i == 3) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            translate = rect.translate(rect.getWidth() + 1, 0.0f);
        } else {
            if (i == 4) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                translate = rect.translate(-(rect.getWidth() + 1), 0.0f);
            } else {
                if (i == 5) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    translate = rect.translate(0.0f, rect.getHeight() + 1);
                } else {
                    if (i == 6) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        translate = rect.translate(0.0f, -(rect.getHeight() + 1));
                    } else {
                        throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
                    }
                }
            }
        }
        int size = mutableVector.getSize();
        FocusTargetModifierNode focusTargetModifierNode = null;
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i2 = 0;
            do {
                FocusTargetModifierNode focusTargetModifierNode2 = (FocusTargetModifierNode) content[i2];
                if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode2)) {
                    Rect focusRect = FocusTraversalKt.focusRect(focusTargetModifierNode2);
                    if (isBetterCandidate_I7lrPNg$isCandidate(i, focusRect, rect) && (!isBetterCandidate_I7lrPNg$isCandidate(i, translate, rect) || m34beamBeatsI7lrPNg(rect, focusRect, translate, i) || (!m34beamBeatsI7lrPNg(rect, translate, focusRect, i) && isBetterCandidate_I7lrPNg$weightedDistance(i, rect, focusRect) < isBetterCandidate_I7lrPNg$weightedDistance(i, rect, translate)))) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (z5) {
                        focusTargetModifierNode = focusTargetModifierNode2;
                        translate = focusRect;
                    }
                }
                i2++;
            } while (i2 < size);
            return focusTargetModifierNode;
        }
        return focusTargetModifierNode;
    }

    private static final boolean isBetterCandidate_I7lrPNg$isCandidate(int i, Rect rect, Rect rect2) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (i == 3) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if ((rect2.getRight() > rect.getRight() || rect2.getLeft() >= rect.getRight()) && rect2.getLeft() > rect.getLeft()) {
                return true;
            }
        } else {
            if (i == 4) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if ((rect2.getLeft() < rect.getLeft() || rect2.getRight() <= rect.getLeft()) && rect2.getRight() < rect.getRight()) {
                    return true;
                }
            } else {
                if (i == 5) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    if ((rect2.getBottom() > rect.getBottom() || rect2.getTop() >= rect.getBottom()) && rect2.getTop() > rect.getTop()) {
                        return true;
                    }
                } else {
                    if (i == 6) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        if ((rect2.getTop() < rect.getTop() || rect2.getBottom() <= rect.getTop()) && rect2.getBottom() < rect.getBottom()) {
                            return true;
                        }
                    } else {
                        throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
                    }
                }
            }
        }
        return false;
    }

    private static final long isBetterCandidate_I7lrPNg$weightedDistance(int i, Rect rect, Rect rect2) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        float top;
        float bottom;
        boolean z5;
        boolean z6;
        boolean z7;
        float f;
        float width;
        float left;
        float width2;
        boolean z8 = false;
        boolean z9 = true;
        if (i == 3) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            top = rect.getLeft();
            bottom = rect2.getRight();
        } else {
            if (i == 4) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                top = rect2.getLeft();
                bottom = rect.getRight();
            } else {
                if (i == 5) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    top = rect.getTop();
                    bottom = rect2.getBottom();
                } else {
                    if (i == 6) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        top = rect2.getTop();
                        bottom = rect.getBottom();
                    } else {
                        throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
                    }
                }
            }
        }
        long abs = Math.abs(Math.max(0.0f, top - bottom));
        if (i == 3) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5 || i == 4) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (z6) {
            f = 2;
            width = (rect.getHeight() / f) + rect.getTop();
            left = rect2.getTop();
            width2 = rect2.getHeight();
        } else {
            if (i == 5) {
                z7 = true;
            } else {
                z7 = false;
            }
            if (!z7) {
                if (i == 6) {
                    z8 = true;
                }
                z9 = z8;
            }
            if (z9) {
                f = 2;
                width = (rect.getWidth() / f) + rect.getLeft();
                left = rect2.getLeft();
                width2 = rect2.getWidth();
            } else {
                throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
            }
        }
        long abs2 = Math.abs(width - ((width2 / f) + left));
        return (abs2 * abs2) + (13 * abs * abs);
    }

    /* renamed from: searchChildren-4C6V_qg  reason: not valid java name */
    private static final boolean m36searchChildren4C6V_qg(FocusTargetModifierNode focusTargetModifierNode, FocusTargetModifierNode focusTargetModifierNode2, int i, Function1 function1) {
        FocusTargetModifierNode m35findBestCandidate4WY_MpI;
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
            if (!mutableVector.isNotEmpty() || (m35findBestCandidate4WY_MpI = m35findBestCandidate4WY_MpI(mutableVector, FocusTraversalKt.focusRect(focusTargetModifierNode2), i)) == null) {
                return false;
            }
            m35findBestCandidate4WY_MpI.fetchFocusProperties$ui_release();
            return ((Boolean) function1.invoke(m35findBestCandidate4WY_MpI)).booleanValue();
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* renamed from: twoDimensionalFocusSearch--OM-vw8  reason: not valid java name */
    public static final boolean m37twoDimensionalFocusSearchOMvw8(final FocusTargetModifierNode focusTargetModifierNode, final int i, final Function1 function1) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        Rect rect;
        Object obj;
        boolean z5;
        boolean z6;
        int ordinal = focusTargetModifierNode.getFocusStateImpl$ui_release().ordinal();
        boolean z7 = false;
        boolean z8 = true;
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal == 3) {
                        focusTargetModifierNode.fetchFocusProperties$ui_release();
                        return ((Boolean) ((FocusOwnerImpl$moveFocus$foundNextItem$1) function1).invoke(focusTargetModifierNode)).booleanValue();
                    }
                    throw new NoWhenBranchMatchedException();
                }
            } else {
                final FocusTargetModifierNode activeChild = FocusTraversalKt.getActiveChild(focusTargetModifierNode);
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
                        } else if (m37twoDimensionalFocusSearchOMvw8(activeChild, i, function1)) {
                            return true;
                        } else {
                            Boolean findFocusTarget$ui_release = ((FocusRequester) ((FocusPropertiesImpl$exit$1) activeChild.fetchFocusProperties$ui_release().getExit()).invoke(FocusDirection.m26boximpl(i))).findFocusTarget$ui_release(function1);
                            if (findFocusTarget$ui_release != null) {
                                return findFocusTarget$ui_release.booleanValue();
                            }
                            if (activeChild.getFocusState() == FocusStateImpl.ActiveParent) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            if (z6) {
                                final FocusTargetModifierNode findActiveFocusNode = FocusTraversalKt.findActiveFocusNode(activeChild);
                                if (findActiveFocusNode != null) {
                                    if (m36searchChildren4C6V_qg(focusTargetModifierNode, findActiveFocusNode, i, function1)) {
                                        return true;
                                    }
                                    BeyondBoundsLayoutKt.m25searchBeyondBoundsOMvw8(focusTargetModifierNode, new Function1() { // from class: androidx.compose.ui.focus.TwoDimensionalFocusSearchKt$generateAndSearchChildren$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj2) {
                                            OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj2);
                                            Intrinsics.checkNotNullParameter(null, "$this$searchBeyondBounds");
                                            throw null;
                                        }
                                    });
                                    return false;
                                }
                                throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
                            }
                            throw new IllegalStateException("Check failed.".toString());
                        }
                    }
                    if (m36searchChildren4C6V_qg(focusTargetModifierNode, activeChild, i, function1)) {
                        return true;
                    }
                    BeyondBoundsLayoutKt.m25searchBeyondBoundsOMvw8(focusTargetModifierNode, new Function1() { // from class: androidx.compose.ui.focus.TwoDimensionalFocusSearchKt$generateAndSearchChildren$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj2);
                            Intrinsics.checkNotNullParameter(null, "$this$searchBeyondBounds");
                            throw null;
                        }
                    });
                    return false;
                }
                throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
            }
        }
        Boolean findFocusTarget$ui_release2 = ((FocusRequester) ((FocusPropertiesImpl$enter$1) focusTargetModifierNode.fetchFocusProperties$ui_release().getEnter()).invoke(FocusDirection.m26boximpl(i))).findFocusTarget$ui_release(function1);
        if (findFocusTarget$ui_release2 != null) {
            return findFocusTarget$ui_release2.booleanValue();
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
                if ((node.getAggregateChildKindSet$ui_release() & 1024) != 0) {
                    for (Modifier.Node node2 = node; node2 != null; node2 = node2.getChild$ui_release()) {
                        if ((node2.getKindSet$ui_release() & 1024) != 0) {
                            if (node2 instanceof FocusTargetModifierNode) {
                                FocusTargetModifierNode focusTargetModifierNode2 = (FocusTargetModifierNode) node2;
                                focusTargetModifierNode2.fetchFocusProperties$ui_release();
                                mutableVector.add(focusTargetModifierNode2);
                                z5 = false;
                            } else {
                                z5 = true;
                            }
                            if (z5) {
                            }
                        }
                    }
                }
                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node);
            }
            if (mutableVector.getSize() <= 1) {
                if (mutableVector.isEmpty()) {
                    obj = null;
                } else {
                    obj = mutableVector.getContent()[0];
                }
                FocusTargetModifierNode focusTargetModifierNode3 = (FocusTargetModifierNode) obj;
                if (focusTargetModifierNode3 != null) {
                    z7 = ((Boolean) ((FocusOwnerImpl$moveFocus$foundNextItem$1) function1).invoke(focusTargetModifierNode3)).booleanValue();
                }
            } else {
                if (i == 7) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    i = 4;
                }
                if (i == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2 || i == 6) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    Rect focusRect = FocusTraversalKt.focusRect(focusTargetModifierNode);
                    rect = new Rect(focusRect.getLeft(), focusRect.getTop(), focusRect.getLeft(), focusRect.getTop());
                } else {
                    if (i == 3) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (!z4 && i != 5) {
                        z8 = false;
                    }
                    if (z8) {
                        Rect focusRect2 = FocusTraversalKt.focusRect(focusTargetModifierNode);
                        rect = new Rect(focusRect2.getRight(), focusRect2.getBottom(), focusRect2.getRight(), focusRect2.getBottom());
                    } else {
                        throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
                    }
                }
                FocusTargetModifierNode m35findBestCandidate4WY_MpI = m35findBestCandidate4WY_MpI(mutableVector, rect, i);
                if (m35findBestCandidate4WY_MpI != null) {
                    z7 = ((Boolean) ((FocusOwnerImpl$moveFocus$foundNextItem$1) function1).invoke(m35findBestCandidate4WY_MpI)).booleanValue();
                }
            }
            return z7;
        }
        throw new IllegalStateException("Check failed.".toString());
    }
}
