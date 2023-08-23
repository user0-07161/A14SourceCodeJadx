package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SuspendingPointerInputFilterKt {
    private static final PointerEvent EmptyPointerEvent = new PointerEvent(EmptyList.INSTANCE);

    public static final /* synthetic */ PointerEvent access$getEmptyPointerEvent$p() {
        return EmptyPointerEvent;
    }

    public static final Modifier pointerInput(Modifier modifier, final Object obj, final Function2 block) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt$pointerInput$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj2, Object obj3, Object obj4) {
                Modifier composed = (Modifier) obj2;
                ((Number) obj4).intValue();
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj3);
                composerImpl.startReplaceableGroup(-906157935);
                int i = ComposerKt.$r8$clinit;
                Density density = (Density) composerImpl.consume(CompositionLocalsKt.getLocalDensity());
                ViewConfiguration viewConfiguration = (ViewConfiguration) composerImpl.consume(CompositionLocalsKt.getLocalViewConfiguration());
                composerImpl.startReplaceableGroup(1157296644);
                boolean changed = composerImpl.changed(density);
                Object nextSlot = composerImpl.nextSlot();
                if (changed || nextSlot == Composer.Companion.getEmpty()) {
                    nextSlot = new SuspendingPointerInputFilter(viewConfiguration, density);
                    composerImpl.updateValue(nextSlot);
                }
                composerImpl.endReplaceableGroup();
                SuspendingPointerInputFilter suspendingPointerInputFilter = (SuspendingPointerInputFilter) nextSlot;
                EffectsKt.LaunchedEffect(suspendingPointerInputFilter, obj, new SuspendingPointerInputFilterKt$pointerInput$2$2$1(suspendingPointerInputFilter, block, null), composerImpl);
                composerImpl.endReplaceableGroup();
                return suspendingPointerInputFilter;
            }
        });
    }
}
