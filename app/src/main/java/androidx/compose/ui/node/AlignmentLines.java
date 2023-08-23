package androidx.compose.ui.node;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AlignmentLines {
    private final AlignmentLinesOwner alignmentLinesOwner;
    private boolean previousUsedDuringParentLayout;
    private AlignmentLinesOwner queryOwner;
    private boolean usedByModifierLayout;
    private boolean usedByModifierMeasurement;
    private boolean usedDuringParentLayout;
    private boolean usedDuringParentMeasurement;
    private boolean dirty = true;
    private final Map alignmentLineMap = new HashMap();

    public AlignmentLines(AlignmentLinesOwner alignmentLinesOwner) {
        this.alignmentLinesOwner = alignmentLinesOwner;
    }

    public static final void access$addAlignmentLine(AlignmentLines alignmentLines, AlignmentLine alignmentLine, int i, NodeCoordinator nodeCoordinator) {
        int roundToInt;
        alignmentLines.getClass();
        float f = i;
        long Offset = OffsetKt.Offset(f, f);
        while (true) {
            Offset = alignmentLines.mo221calculatePositionInParentR5De75A(nodeCoordinator, Offset);
            nodeCoordinator = nodeCoordinator.getWrappedBy$ui_release();
            Intrinsics.checkNotNull(nodeCoordinator);
            if (Intrinsics.areEqual(nodeCoordinator, alignmentLines.alignmentLinesOwner.getInnerCoordinator())) {
                break;
            } else if (alignmentLines.getAlignmentLinesMap(nodeCoordinator).containsKey(alignmentLine)) {
                float positionFor = alignmentLines.getPositionFor(nodeCoordinator, alignmentLine);
                Offset = OffsetKt.Offset(positionFor, positionFor);
            }
        }
        if (alignmentLine instanceof HorizontalAlignmentLine) {
            roundToInt = MathKt.roundToInt(Offset.m46getYimpl(Offset));
        } else {
            roundToInt = MathKt.roundToInt(Offset.m45getXimpl(Offset));
        }
        Map map = alignmentLines.alignmentLineMap;
        HashMap hashMap = (HashMap) map;
        if (hashMap.containsKey(alignmentLine)) {
            Intrinsics.checkNotNullParameter(map, "<this>");
            HashMap hashMap2 = (HashMap) map;
            Object obj = hashMap2.get(alignmentLine);
            if (obj == null && !hashMap2.containsKey(alignmentLine)) {
                throw new NoSuchElementException("Key " + alignmentLine + " is missing in the map.");
            }
            int intValue = ((Number) obj).intValue();
            int i2 = AlignmentLineKt.$r8$clinit;
            Intrinsics.checkNotNullParameter(alignmentLine, "<this>");
            roundToInt = ((Number) alignmentLine.getMerger$ui_release().invoke(Integer.valueOf(intValue), Integer.valueOf(roundToInt))).intValue();
        }
        hashMap.put(alignmentLine, Integer.valueOf(roundToInt));
    }

    /* renamed from: calculatePositionInParent-R5De75A  reason: not valid java name */
    protected abstract long mo221calculatePositionInParentR5De75A(NodeCoordinator nodeCoordinator, long j);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Map getAlignmentLinesMap(NodeCoordinator nodeCoordinator);

    public final AlignmentLinesOwner getAlignmentLinesOwner() {
        return this.alignmentLinesOwner;
    }

    public final boolean getDirty$ui_release() {
        return this.dirty;
    }

    public final Map getLastCalculation() {
        return this.alignmentLineMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getPositionFor(NodeCoordinator nodeCoordinator, AlignmentLine alignmentLine);

    public final boolean getQueried$ui_release() {
        if (!this.usedDuringParentMeasurement && !this.previousUsedDuringParentLayout && !this.usedByModifierMeasurement && !this.usedByModifierLayout) {
            return false;
        }
        return true;
    }

    public final boolean getRequired$ui_release() {
        recalculateQueryOwner();
        if (this.queryOwner != null) {
            return true;
        }
        return false;
    }

    public final boolean getUsedDuringParentLayout$ui_release() {
        return this.usedDuringParentLayout;
    }

    public final void onAlignmentsChanged() {
        this.dirty = true;
        AlignmentLinesOwner alignmentLinesOwner = this.alignmentLinesOwner;
        AlignmentLinesOwner parentAlignmentLinesOwner = alignmentLinesOwner.getParentAlignmentLinesOwner();
        if (parentAlignmentLinesOwner == null) {
            return;
        }
        if (this.usedDuringParentMeasurement) {
            parentAlignmentLinesOwner.requestMeasure();
        } else if (this.previousUsedDuringParentLayout || this.usedDuringParentLayout) {
            parentAlignmentLinesOwner.requestLayout();
        }
        if (this.usedByModifierMeasurement) {
            alignmentLinesOwner.requestMeasure();
        }
        if (this.usedByModifierLayout) {
            parentAlignmentLinesOwner.requestLayout();
        }
        parentAlignmentLinesOwner.getAlignmentLines().onAlignmentsChanged();
    }

    public final void recalculate() {
        HashMap hashMap = (HashMap) this.alignmentLineMap;
        hashMap.clear();
        Function1 function1 = new Function1() { // from class: androidx.compose.ui.node.AlignmentLines$recalculate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Map map;
                AlignmentLinesOwner childOwner = (AlignmentLinesOwner) obj;
                Intrinsics.checkNotNullParameter(childOwner, "childOwner");
                if (childOwner.isPlaced()) {
                    if (childOwner.getAlignmentLines().getDirty$ui_release()) {
                        childOwner.layoutChildren();
                    }
                    map = childOwner.getAlignmentLines().alignmentLineMap;
                    AlignmentLines alignmentLines = AlignmentLines.this;
                    for (Map.Entry entry : ((HashMap) map).entrySet()) {
                        AlignmentLines.access$addAlignmentLine(alignmentLines, (AlignmentLine) entry.getKey(), ((Number) entry.getValue()).intValue(), childOwner.getInnerCoordinator());
                    }
                    NodeCoordinator wrappedBy$ui_release = childOwner.getInnerCoordinator().getWrappedBy$ui_release();
                    Intrinsics.checkNotNull(wrappedBy$ui_release);
                    while (!Intrinsics.areEqual(wrappedBy$ui_release, AlignmentLines.this.getAlignmentLinesOwner().getInnerCoordinator())) {
                        Set<AlignmentLine> keySet = AlignmentLines.this.getAlignmentLinesMap(wrappedBy$ui_release).keySet();
                        AlignmentLines alignmentLines2 = AlignmentLines.this;
                        for (AlignmentLine alignmentLine : keySet) {
                            AlignmentLines.access$addAlignmentLine(alignmentLines2, alignmentLine, alignmentLines2.getPositionFor(wrappedBy$ui_release, alignmentLine), wrappedBy$ui_release);
                        }
                        wrappedBy$ui_release = wrappedBy$ui_release.getWrappedBy$ui_release();
                        Intrinsics.checkNotNull(wrappedBy$ui_release);
                    }
                }
                return Unit.INSTANCE;
            }
        };
        AlignmentLinesOwner alignmentLinesOwner = this.alignmentLinesOwner;
        alignmentLinesOwner.forEachChildAlignmentLinesOwner(function1);
        hashMap.putAll(getAlignmentLinesMap(alignmentLinesOwner.getInnerCoordinator()));
        this.dirty = false;
    }

    public final void recalculateQueryOwner() {
        AlignmentLines alignmentLines;
        AlignmentLines alignmentLines2;
        boolean queried$ui_release = getQueried$ui_release();
        AlignmentLinesOwner alignmentLinesOwner = this.alignmentLinesOwner;
        if (!queried$ui_release) {
            AlignmentLinesOwner parentAlignmentLinesOwner = alignmentLinesOwner.getParentAlignmentLinesOwner();
            if (parentAlignmentLinesOwner == null) {
                return;
            }
            alignmentLinesOwner = parentAlignmentLinesOwner.getAlignmentLines().queryOwner;
            if (alignmentLinesOwner == null || !alignmentLinesOwner.getAlignmentLines().getQueried$ui_release()) {
                AlignmentLinesOwner alignmentLinesOwner2 = this.queryOwner;
                if (alignmentLinesOwner2 != null && !alignmentLinesOwner2.getAlignmentLines().getQueried$ui_release()) {
                    AlignmentLinesOwner parentAlignmentLinesOwner2 = alignmentLinesOwner2.getParentAlignmentLinesOwner();
                    if (parentAlignmentLinesOwner2 != null && (alignmentLines2 = parentAlignmentLinesOwner2.getAlignmentLines()) != null) {
                        alignmentLines2.recalculateQueryOwner();
                    }
                    AlignmentLinesOwner parentAlignmentLinesOwner3 = alignmentLinesOwner2.getParentAlignmentLinesOwner();
                    if (parentAlignmentLinesOwner3 != null && (alignmentLines = parentAlignmentLinesOwner3.getAlignmentLines()) != null) {
                        alignmentLinesOwner = alignmentLines.queryOwner;
                    } else {
                        alignmentLinesOwner = null;
                    }
                } else {
                    return;
                }
            }
        }
        this.queryOwner = alignmentLinesOwner;
    }

    public final void reset$ui_release() {
        this.dirty = true;
        this.usedDuringParentMeasurement = false;
        this.previousUsedDuringParentLayout = false;
        this.usedDuringParentLayout = false;
        this.usedByModifierMeasurement = false;
        this.usedByModifierLayout = false;
        this.queryOwner = null;
    }

    public final void setPreviousUsedDuringParentLayout$ui_release(boolean z) {
        this.previousUsedDuringParentLayout = z;
    }

    public final void setUsedByModifierLayout$ui_release(boolean z) {
        this.usedByModifierLayout = z;
    }

    public final void setUsedByModifierMeasurement$ui_release(boolean z) {
        this.usedByModifierMeasurement = z;
    }

    public final void setUsedDuringParentMeasurement$ui_release() {
        this.usedDuringParentMeasurement = false;
    }
}
