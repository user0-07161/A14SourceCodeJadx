package androidx.compose.ui.platform.accessibility;

import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CollectionInfoKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.collections.EmptyList] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.List, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.ArrayList] */
    private static final boolean calculateIfHorizontallyStacked(List list) {
        ?? r1;
        long m51unboximpl;
        ArrayList arrayList = (ArrayList) list;
        if (arrayList.size() < 2) {
            return true;
        }
        if (arrayList.size() != 0 && arrayList.size() != 1) {
            r1 = new ArrayList();
            Object obj = arrayList.get(0);
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i = 0;
            while (i < lastIndex) {
                i++;
                Object obj2 = arrayList.get(i);
                SemanticsNode semanticsNode = (SemanticsNode) obj2;
                SemanticsNode semanticsNode2 = (SemanticsNode) obj;
                r1.add(Offset.m42boximpl(OffsetKt.Offset(Math.abs(Offset.m45getXimpl(semanticsNode2.getBoundsInRoot().m53getCenterF1C5BW0()) - Offset.m45getXimpl(semanticsNode.getBoundsInRoot().m53getCenterF1C5BW0())), Math.abs(Offset.m46getYimpl(semanticsNode2.getBoundsInRoot().m53getCenterF1C5BW0()) - Offset.m46getYimpl(semanticsNode.getBoundsInRoot().m53getCenterF1C5BW0())))));
                obj = obj2;
            }
        } else {
            r1 = EmptyList.INSTANCE;
        }
        if (r1.size() == 1) {
            m51unboximpl = ((Offset) CollectionsKt.first(r1)).m51unboximpl();
        } else if (!r1.isEmpty()) {
            Object first = CollectionsKt.first(r1);
            int lastIndex2 = CollectionsKt.getLastIndex(r1);
            if (1 <= lastIndex2) {
                int i2 = 1;
                while (true) {
                    first = Offset.m42boximpl(Offset.m48plusMKHz9U(((Offset) first).m51unboximpl(), ((Offset) r1.get(i2)).m51unboximpl()));
                    if (i2 == lastIndex2) {
                        break;
                    }
                    i2++;
                }
            }
            m51unboximpl = ((Offset) first).m51unboximpl();
        } else {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        if (Offset.m46getYimpl(m51unboximpl) < Offset.m45getXimpl(m51unboximpl)) {
            return true;
        }
        return false;
    }

    public static final void setCollectionInfo(SemanticsNode semanticsNode, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int size;
        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), SemanticsProperties.getCollectionInfo()));
        ArrayList arrayList = new ArrayList();
        if (SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), SemanticsProperties.getSelectableGroup()) != null) {
            List replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
            int size2 = replacedChildren$ui_release.size();
            for (int i = 0; i < size2; i++) {
                SemanticsNode semanticsNode2 = (SemanticsNode) replacedChildren$ui_release.get(i);
                if (semanticsNode2.getConfig().contains(SemanticsProperties.getSelected())) {
                    arrayList.add(semanticsNode2);
                }
            }
        }
        int i2 = 1;
        if (!arrayList.isEmpty()) {
            boolean calculateIfHorizontallyStacked = calculateIfHorizontallyStacked(arrayList);
            if (calculateIfHorizontallyStacked) {
                size = 1;
            } else {
                size = arrayList.size();
            }
            if (calculateIfHorizontallyStacked) {
                i2 = arrayList.size();
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(size, i2));
        }
    }

    public static final void setCollectionItemInfo(SemanticsNode semanticsNode, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int i;
        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), SemanticsProperties.getCollectionItemInfo()));
        SemanticsNode parent = semanticsNode.getParent();
        if (parent != null && SemanticsConfigurationKt.getOrNull(parent.getConfig(), SemanticsProperties.getSelectableGroup()) != null) {
            OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(SemanticsConfigurationKt.getOrNull(parent.getConfig(), SemanticsProperties.getCollectionInfo()));
            if (!semanticsNode.getConfig().contains(SemanticsProperties.getSelected())) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            List replacedChildren$ui_release = parent.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                SemanticsNode semanticsNode2 = (SemanticsNode) replacedChildren$ui_release.get(i4);
                if (semanticsNode2.getConfig().contains(SemanticsProperties.getSelected())) {
                    arrayList.add(semanticsNode2);
                    if (semanticsNode2.getLayoutNode$ui_release().getPlaceOrder$ui_release() < semanticsNode.getLayoutNode$ui_release().getPlaceOrder$ui_release()) {
                        i3++;
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                boolean calculateIfHorizontallyStacked = calculateIfHorizontallyStacked(arrayList);
                if (calculateIfHorizontallyStacked) {
                    i = 0;
                } else {
                    i = i3;
                }
                if (calculateIfHorizontallyStacked) {
                    i2 = i3;
                }
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(i, 1, i2, 1, ((Boolean) semanticsNode.getConfig().getOrElse(SemanticsProperties.getSelected(), new Function0() { // from class: androidx.compose.ui.platform.accessibility.CollectionInfoKt$setCollectionItemInfo$itemInfo$1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.FALSE;
                    }
                })).booleanValue()));
            }
        }
    }
}
