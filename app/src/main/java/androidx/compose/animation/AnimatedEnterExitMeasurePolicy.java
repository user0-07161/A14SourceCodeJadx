package androidx.compose.animation;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class AnimatedEnterExitMeasurePolicy implements MeasurePolicy {
    private final AnimatedVisibilityScopeImpl scope;

    public AnimatedEnterExitMeasurePolicy(AnimatedVisibilityScopeImpl scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.scope = scope;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s  reason: not valid java name */
    public final MeasureResult mo1measure3p2s80s(MeasureScope measure, List list, long j) {
        Object obj;
        int i;
        MeasureScope$layout$1 layout;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        final ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Measurable) it.next()).mo210measureBRTryo0(j));
        }
        int i2 = 1;
        Object obj2 = null;
        int i3 = 0;
        if (arrayList.isEmpty()) {
            obj = null;
        } else {
            obj = arrayList.get(0);
            int width = ((Placeable) obj).getWidth();
            int lastIndex = CollectionsKt.getLastIndex(arrayList);
            if (1 <= lastIndex) {
                int i4 = 1;
                while (true) {
                    Object obj3 = arrayList.get(i4);
                    int width2 = ((Placeable) obj3).getWidth();
                    if (width < width2) {
                        obj = obj3;
                        width = width2;
                    }
                    if (i4 == lastIndex) {
                        break;
                    }
                    i4++;
                }
            }
        }
        Placeable placeable = (Placeable) obj;
        if (placeable != null) {
            i = placeable.getWidth();
        } else {
            i = 0;
        }
        if (!arrayList.isEmpty()) {
            obj2 = arrayList.get(0);
            int height = ((Placeable) obj2).getHeight();
            int lastIndex2 = CollectionsKt.getLastIndex(arrayList);
            if (1 <= lastIndex2) {
                while (true) {
                    Object obj4 = arrayList.get(i2);
                    int height2 = ((Placeable) obj4).getHeight();
                    if (height < height2) {
                        obj2 = obj4;
                        height = height2;
                    }
                    if (i2 == lastIndex2) {
                        break;
                    }
                    i2++;
                }
            }
        }
        Placeable placeable2 = (Placeable) obj2;
        if (placeable2 != null) {
            i3 = placeable2.getHeight();
        }
        this.scope.getTargetSize$animation_release().setValue(IntSize.m404boximpl(IntSizeKt.IntSize(i, i3)));
        layout = measure.layout(i, i3, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.animation.AnimatedEnterExitMeasurePolicy$measure$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj5) {
                Placeable.PlacementScope layout2 = (Placeable.PlacementScope) obj5;
                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                List list2 = arrayList;
                int size = list2.size();
                for (int i5 = 0; i5 < size; i5++) {
                    Placeable.PlacementScope.place$default(layout2, (Placeable) list2.get(i5), 0, 0);
                }
                return Unit.INSTANCE;
            }
        });
        return layout;
    }
}
