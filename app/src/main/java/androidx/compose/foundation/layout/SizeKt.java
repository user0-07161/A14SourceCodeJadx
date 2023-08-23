package androidx.compose.foundation.layout;

import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SizeKt {
    private static final FillModifier FillWholeMaxSize;

    static {
        Direction direction = Direction.Horizontal;
        new FillModifier(direction, 1.0f, new Function1() { // from class: androidx.compose.foundation.layout.SizeKt$createFillWidthModifier$1
            final /* synthetic */ float $fraction = 1.0f;

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                Intrinsics.checkNotNullParameter(null, "$this$$receiver");
                throw null;
            }
        });
        Direction direction2 = Direction.Vertical;
        new FillModifier(direction2, 1.0f, new Function1() { // from class: androidx.compose.foundation.layout.SizeKt$createFillHeightModifier$1
            final /* synthetic */ float $fraction = 1.0f;

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                Intrinsics.checkNotNullParameter(null, "$this$$receiver");
                throw null;
            }
        });
        Direction direction3 = Direction.Both;
        FillWholeMaxSize = new FillModifier(direction3, 1.0f, new Function1() { // from class: androidx.compose.foundation.layout.SizeKt$createFillSizeModifier$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                Intrinsics.checkNotNullParameter(null, "$this$$receiver");
                throw null;
            }
        });
        final BiasAlignment.Horizontal centerHorizontally = Alignment.Companion.getCenterHorizontally();
        new WrapContentModifier(direction, new Function2() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentWidthModifier$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long m407unboximpl = ((IntSize) obj).m407unboximpl();
                LayoutDirection layoutDirection = (LayoutDirection) obj2;
                Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
                return IntOffset.m401boximpl(IntOffsetKt.IntOffset(centerHorizontally.align((int) (m407unboximpl >> 32), layoutDirection), 0));
            }
        }, centerHorizontally, new Function1() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentWidthModifier$2
            final /* synthetic */ boolean $unbounded = false;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                Intrinsics.checkNotNullParameter(null, "$this$$receiver");
                throw null;
            }
        });
        final BiasAlignment.Horizontal start = Alignment.Companion.getStart();
        new WrapContentModifier(direction, new Function2() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentWidthModifier$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long m407unboximpl = ((IntSize) obj).m407unboximpl();
                LayoutDirection layoutDirection = (LayoutDirection) obj2;
                Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
                return IntOffset.m401boximpl(IntOffsetKt.IntOffset(start.align((int) (m407unboximpl >> 32), layoutDirection), 0));
            }
        }, start, new Function1() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentWidthModifier$2
            final /* synthetic */ boolean $unbounded = false;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                Intrinsics.checkNotNullParameter(null, "$this$$receiver");
                throw null;
            }
        });
        final BiasAlignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
        new WrapContentModifier(direction2, new Function2() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentHeightModifier$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long m407unboximpl = ((IntSize) obj).m407unboximpl();
                Intrinsics.checkNotNullParameter((LayoutDirection) obj2, "<anonymous parameter 1>");
                return IntOffset.m401boximpl(IntOffsetKt.IntOffset(0, ((BiasAlignment.Vertical) centerVertically).align(IntSize.m405getHeightimpl(m407unboximpl))));
            }
        }, centerVertically, new Function1() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentHeightModifier$2
            final /* synthetic */ boolean $unbounded = false;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                Intrinsics.checkNotNullParameter(null, "$this$$receiver");
                throw null;
            }
        });
        final BiasAlignment.Vertical top = Alignment.Companion.getTop();
        new WrapContentModifier(direction2, new Function2() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentHeightModifier$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long m407unboximpl = ((IntSize) obj).m407unboximpl();
                Intrinsics.checkNotNullParameter((LayoutDirection) obj2, "<anonymous parameter 1>");
                return IntOffset.m401boximpl(IntOffsetKt.IntOffset(0, ((BiasAlignment.Vertical) top).align(IntSize.m405getHeightimpl(m407unboximpl))));
            }
        }, top, new Function1() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentHeightModifier$2
            final /* synthetic */ boolean $unbounded = false;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                Intrinsics.checkNotNullParameter(null, "$this$$receiver");
                throw null;
            }
        });
        final BiasAlignment center = Alignment.Companion.getCenter();
        new WrapContentModifier(direction3, new Function2() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentSizeModifier$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long m407unboximpl = ((IntSize) obj).m407unboximpl();
                LayoutDirection layoutDirection = (LayoutDirection) obj2;
                Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
                return IntOffset.m401boximpl(((BiasAlignment) center).m24alignKFBX0sM(0L, m407unboximpl, layoutDirection));
            }
        }, center, new Function1() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentSizeModifier$2
            final /* synthetic */ boolean $unbounded = false;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                Intrinsics.checkNotNullParameter(null, "$this$$receiver");
                throw null;
            }
        });
        final BiasAlignment topStart = Alignment.Companion.getTopStart();
        new WrapContentModifier(direction3, new Function2() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentSizeModifier$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long m407unboximpl = ((IntSize) obj).m407unboximpl();
                LayoutDirection layoutDirection = (LayoutDirection) obj2;
                Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
                return IntOffset.m401boximpl(((BiasAlignment) topStart).m24alignKFBX0sM(0L, m407unboximpl, layoutDirection));
            }
        }, topStart, new Function1() { // from class: androidx.compose.foundation.layout.SizeKt$createWrapContentSizeModifier$2
            final /* synthetic */ boolean $unbounded = false;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                Intrinsics.checkNotNullParameter(null, "$this$$receiver");
                throw null;
            }
        });
    }

    public static Modifier fillMaxSize$default(Modifier.Companion companion) {
        FillModifier other = FillWholeMaxSize;
        Intrinsics.checkNotNullParameter(other, "other");
        return other;
    }

    /* renamed from: heightIn-VpY3zN4 */
    public static final Modifier m15heightInVpY3zN4(Modifier.Companion companion, float f, float f2) {
        return new SizeModifier(f, f2, InspectableValueKt.getNoInspectorInfo());
    }
}
