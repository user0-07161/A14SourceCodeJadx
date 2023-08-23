package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusRequesterModifierNode;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputModifier;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilter;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.modifier.EmptyMap;
import androidx.compose.ui.modifier.ModifierLocalMap;
import androidx.compose.ui.modifier.ModifierLocalNode;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifier;
import androidx.compose.ui.semantics.SemanticsModifierCore;
import androidx.compose.ui.unit.Density;
import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class BackwardsCompatNode extends Modifier.Node implements LayoutModifierNode, IntermediateLayoutModifierNode, DrawModifierNode, SemanticsModifierNode, PointerInputModifierNode, ModifierLocalNode, ParentDataModifierNode, LayoutAwareModifierNode, GlobalPositionAwareModifierNode, FocusEventModifierNode, FocusPropertiesModifierNode, FocusRequesterModifierNode, OwnerScope {
    private Modifier.Element element;
    private boolean invalidateCache;
    private LayoutCoordinates lastOnPlacedCoordinates;
    private HashSet readValues;

    public BackwardsCompatNode(Modifier.Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
        setKindSet$ui_release(NodeKindKt.calculateNodeKindSetFrom(element));
        this.element = element;
        this.invalidateCache = true;
        this.readValues = new HashSet();
    }

    private final void initializeModifier() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (isAttached()) {
            Modifier.Element element = this.element;
            boolean z5 = true;
            if ((getKindSet$ui_release() & 4) != 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                DelegatableNodeKt.m224requireCoordinator64DMado(this, 2).invalidateLayer();
            }
            if ((getKindSet$ui_release() & 2) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (DelegatableNodeKt.requireLayoutNode(this).getNodes$ui_release().getTail$ui_release().isAttached()) {
                    NodeCoordinator coordinator$ui_release = getCoordinator$ui_release();
                    Intrinsics.checkNotNull(coordinator$ui_release);
                    ((LayoutModifierNodeCoordinator) coordinator$ui_release).setLayoutModifierNode$ui_release(this);
                    coordinator$ui_release.onLayoutModifierNodeChanged();
                }
                DelegatableNodeKt.m224requireCoordinator64DMado(this, 2).invalidateLayer();
                DelegatableNodeKt.requireLayoutNode(this).invalidateMeasurements$ui_release();
            }
            if ((getKindSet$ui_release() & 256) != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3 && (element instanceof OnGloballyPositionedModifier) && DelegatableNodeKt.requireLayoutNode(this).getNodes$ui_release().getTail$ui_release().isAttached()) {
                DelegatableNodeKt.requireLayoutNode(this).invalidateMeasurements$ui_release();
            }
            if ((getKindSet$ui_release() & 16) != 0) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (z4 && (element instanceof PointerInputModifier)) {
                SuspendingPointerInputFilter suspendingPointerInputFilter = (SuspendingPointerInputFilter) ((PointerInputModifier) element);
                suspendingPointerInputFilter.getClass();
                suspendingPointerInputFilter.setLayoutCoordinates$ui_release(getCoordinator$ui_release());
            }
            if ((getKindSet$ui_release() & 8) == 0) {
                z5 = false;
            }
            if (z5) {
                ((AndroidComposeView) DelegatableNodeKt.requireOwner(this)).onSemanticsChange();
                return;
            }
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private final void unInitializeModifier() {
        if (isAttached()) {
            boolean z = true;
            if ((getKindSet$ui_release() & 32) != 0) {
            }
            if ((getKindSet$ui_release() & 8) == 0) {
                z = false;
            }
            if (z) {
                ((AndroidComposeView) DelegatableNodeKt.requireOwner(this)).onSemanticsChange();
                return;
            }
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(ContentDrawScope contentDrawScope) {
        Intrinsics.checkNotNullParameter(contentDrawScope, "<this>");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.draw.DrawModifier");
        BackwardsCompatNode$$ExternalSyntheticThrowCCEIfNotNull0.m(element);
        throw null;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalNode
    public final ModifierLocalMap getProvidedValues() {
        return EmptyMap.INSTANCE;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final SemanticsConfiguration getSemanticsConfiguration() {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsModifier");
        return ((SemanticsModifierCore) ((SemanticsModifier) element)).getSemanticsConfiguration();
    }

    public final void interceptOutOfBoundsChildEvents() {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        PointerInputModifier pointerInputModifier = (PointerInputModifier) element;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo88measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((LayoutModifier) element).mo2measure3p2s80s(measure, measurable, j);
    }

    public final Object modifyParentData(Density density, Object obj) {
        Intrinsics.checkNotNullParameter(density, "<this>");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.ParentDataModifier");
        return ((ParentDataModifier) element).modifyParentData(density, obj);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        initializeModifier();
    }

    public final void onCancelPointerInput() {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        ((SuspendingPointerInputFilter) ((PointerInputModifier) element)).onCancel();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        unInitializeModifier();
    }

    public final void onGloballyPositioned(NodeCoordinator nodeCoordinator) {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.OnGloballyPositionedModifier");
        ((OnGloballyPositionedModifier) element).onGloballyPositioned(nodeCoordinator);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void onMeasureResultChanged() {
        this.invalidateCache = true;
        DrawModifierNodeKt.invalidateDraw(this);
    }

    public final void onPlaced(NodeCoordinator coordinates) {
        Intrinsics.checkNotNullParameter(coordinates, "coordinates");
        this.lastOnPlacedCoordinates = coordinates;
    }

    /* renamed from: onPointerEvent-H0pRuoY  reason: not valid java name */
    public final void m222onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        ((SuspendingPointerInputFilter) ((PointerInputModifier) element)).m201onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
    }

    public final void setElement(Modifier.Element value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (isAttached()) {
            unInitializeModifier();
        }
        this.element = value;
        setKindSet$ui_release(NodeKindKt.calculateNodeKindSetFrom(value));
        if (isAttached()) {
            initializeModifier();
        }
    }

    /* renamed from: setTargetSize-ozmzZPI  reason: not valid java name */
    public final void m223setTargetSizeozmzZPI() {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.IntermediateLayoutModifier");
        BackwardsCompatNode$$ExternalSyntheticThrowCCEIfNotNull0.m(element);
        throw null;
    }

    public final void sharePointerInputWithSiblings() {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        PointerInputModifier pointerInputModifier = (PointerInputModifier) element;
    }

    public final String toString() {
        return this.element.toString();
    }
}
