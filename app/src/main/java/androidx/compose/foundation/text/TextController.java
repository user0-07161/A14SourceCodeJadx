package androidx.compose.foundation.text;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.semantics.SemanticsModifierCore;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextController implements RememberObserver {
    private final Modifier coreModifiers;
    private final TextController$measurePolicy$1 measurePolicy = new MeasurePolicy() { // from class: androidx.compose.foundation.text.TextController$measurePolicy$1
        @Override // androidx.compose.ui.layout.MeasurePolicy
        /* renamed from: measure-3p2s80s */
        public final MeasureResult mo1measure3p2s80s(MeasureScope measure, List list, long j) {
            boolean z;
            Pair pair;
            Intrinsics.checkNotNullParameter(measure, "$this$measure");
            TextController textController = TextController.this;
            textController.getState().getLayoutInvalidation();
            TextLayoutResult layoutResult = textController.getState().getLayoutResult();
            TextLayoutResult m18layoutNN6EwU = textController.getState().getTextDelegate().m18layoutNN6EwU(j, measure.getLayoutDirection(), layoutResult);
            if (!Intrinsics.areEqual(layoutResult, m18layoutNN6EwU)) {
                textController.getState().getOnTextLayout().invoke(m18layoutNN6EwU);
                if (layoutResult != null) {
                    Intrinsics.areEqual(layoutResult.getLayoutInput().getText(), m18layoutNN6EwU.getLayoutInput().getText());
                }
            }
            textController.getState().setLayoutResult(m18layoutNN6EwU);
            if (list.size() >= m18layoutNN6EwU.getPlaceholderRects().size()) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                List placeholderRects = m18layoutNN6EwU.getPlaceholderRects();
                final ArrayList arrayList = new ArrayList(placeholderRects.size());
                int size = placeholderRects.size();
                for (int i = 0; i < size; i++) {
                    Rect rect = (Rect) placeholderRects.get(i);
                    if (rect != null) {
                        pair = new Pair(((Measurable) list.get(i)).mo210measureBRTryo0(ConstraintsKt.Constraints$default((int) Math.floor(rect.getWidth()), (int) Math.floor(rect.getHeight()), 5)), IntOffset.m401boximpl(IntOffsetKt.IntOffset(MathKt.roundToInt(rect.getLeft()), MathKt.roundToInt(rect.getTop()))));
                    } else {
                        pair = null;
                    }
                    if (pair != null) {
                        arrayList.add(pair);
                    }
                }
                return measure.layout((int) (m18layoutNN6EwU.m313getSizeYbymL2g() >> 32), IntSize.m405getHeightimpl(m18layoutNN6EwU.m313getSizeYbymL2g()), MapsKt.mapOf(new Pair(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(MathKt.roundToInt(m18layoutNN6EwU.getFirstBaseline()))), new Pair(AlignmentLineKt.getLastBaseline(), Integer.valueOf(MathKt.roundToInt(m18layoutNN6EwU.getLastBaseline())))), new Function1() { // from class: androidx.compose.foundation.text.TextController$measurePolicy$1$measure$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Placeable.PlacementScope layout = (Placeable.PlacementScope) obj;
                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                        List list2 = arrayList;
                        int size2 = list2.size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            Pair pair2 = (Pair) list2.get(i2);
                            Placeable.PlacementScope.m219place70tqf50((Placeable) pair2.component1(), ((IntOffset) pair2.component2()).m403unboximpl(), 0.0f);
                        }
                        return Unit.INSTANCE;
                    }
                });
            }
            throw new IllegalStateException("Check failed.".toString());
        }
    };
    private Modifier selectionModifiers;
    private SemanticsModifierCore semanticsModifier;
    private final TextState state;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.text.TextController$measurePolicy$1] */
    public TextController(TextState textState) {
        this.state = textState;
        Modifier.Companion companion = Modifier.Companion;
        this.coreModifiers = OnGloballyPositionedModifierKt.onGloballyPositioned(DrawModifierKt.drawBehind(GraphicsLayerModifierKt.m103graphicsLayerAp8cVGQ$default(companion, false, 131071), new Function1() { // from class: androidx.compose.foundation.text.TextController$drawTextAndSelectionBehind$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
            /* JADX WARN: Removed duplicated region for block: B:17:0x0097  */
            /* JADX WARN: Removed duplicated region for block: B:20:0x00a2  */
            /* JADX WARN: Removed duplicated region for block: B:23:0x00ad  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00b8 A[Catch: all -> 0x00f8, TRY_ENTER, TryCatch #0 {all -> 0x00f8, blocks: (B:25:0x00b0, B:28:0x00b8, B:30:0x00be, B:32:0x00c9, B:33:0x00d6, B:35:0x00dc, B:37:0x00e9, B:36:0x00e5), top: B:46:0x00b0 }] */
            /* JADX WARN: Removed duplicated region for block: B:33:0x00d6 A[Catch: all -> 0x00f8, TryCatch #0 {all -> 0x00f8, blocks: (B:25:0x00b0, B:28:0x00b8, B:30:0x00be, B:32:0x00c9, B:33:0x00d6, B:35:0x00dc, B:37:0x00e9, B:36:0x00e5), top: B:46:0x00b0 }] */
            /* JADX WARN: Removed duplicated region for block: B:39:0x00f4 A[DONT_GENERATE] */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invoke(java.lang.Object r9) {
                /*
                    Method dump skipped, instructions count: 258
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextController$drawTextAndSelectionBehind$1.invoke(java.lang.Object):java.lang.Object");
            }
        }), new Function1() { // from class: androidx.compose.foundation.text.TextController$coreModifiers$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LayoutCoordinates it = (LayoutCoordinates) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                TextController.this.getState().setLayoutCoordinates(it);
                TextController.this.getClass();
                TextController.this.getState().getClass();
                int i = SelectionRegistrarKt.$r8$clinit;
                return Unit.INSTANCE;
            }
        });
        this.semanticsModifier = new SemanticsModifierCore(false, new TextController$createSemanticsModifierFor$1(textState.getTextDelegate().getText(), this), InspectableValueKt.getNoInspectorInfo());
        this.selectionModifiers = companion;
    }

    public final TextController$measurePolicy$1 getMeasurePolicy() {
        return this.measurePolicy;
    }

    public final Modifier getModifiers() {
        TextState textState = this.state;
        final TextStyle textStyle = textState.getTextDelegate().getStyle();
        final int minLines = textState.getTextDelegate().getMinLines();
        Modifier modifier = this.coreModifiers;
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(textStyle, "textStyle");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.foundation.text.HeightInLinesModifierKt$heightInLines$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                int i;
                int i2;
                Integer valueOf;
                float f;
                Modifier composed = (Modifier) obj;
                ((Number) obj3).intValue();
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceableGroup(408240218);
                int i3 = ComposerKt.$r8$clinit;
                HeightInLinesModifierKt.validateMinMaxLines(minLines, r2);
                if (minLines == 1 && r2 == Integer.MAX_VALUE) {
                    Modifier.Companion companion = Modifier.Companion;
                    composerImpl.endReplaceableGroup();
                    return companion;
                }
                Density density = (Density) composerImpl.consume(CompositionLocalsKt.getLocalDensity());
                FontFamilyResolverImpl fontFamilyResolverImpl = (FontFamilyResolverImpl) composerImpl.consume(CompositionLocalsKt.getLocalFontFamilyResolver());
                LayoutDirection layoutDirection = (LayoutDirection) composerImpl.consume(CompositionLocalsKt.getLocalLayoutDirection());
                TextStyle textStyle2 = textStyle;
                composerImpl.startReplaceableGroup(511388516);
                boolean changed = composerImpl.changed(textStyle2) | composerImpl.changed(layoutDirection);
                Object nextSlot = composerImpl.nextSlot();
                if (changed || nextSlot == Composer.Companion.getEmpty()) {
                    nextSlot = TextStyleKt.resolveDefaults(textStyle2, layoutDirection);
                    composerImpl.updateValue(nextSlot);
                }
                composerImpl.endReplaceableGroup();
                TextStyle textStyle3 = (TextStyle) nextSlot;
                composerImpl.startReplaceableGroup(511388516);
                boolean changed2 = composerImpl.changed(fontFamilyResolverImpl) | composerImpl.changed(textStyle3);
                Object nextSlot2 = composerImpl.nextSlot();
                if (changed2 || nextSlot2 == Composer.Companion.getEmpty()) {
                    FontFamily fontFamily = textStyle3.getFontFamily();
                    FontWeight fontWeight = textStyle3.getFontWeight();
                    if (fontWeight == null) {
                        fontWeight = FontWeight.Normal;
                    }
                    FontStyle m320getFontStyle4Lr2A7w = textStyle3.m320getFontStyle4Lr2A7w();
                    if (m320getFontStyle4Lr2A7w != null) {
                        i = m320getFontStyle4Lr2A7w.m330unboximpl();
                    } else {
                        i = 0;
                    }
                    FontSynthesis m321getFontSynthesisZQGJjVo = textStyle3.m321getFontSynthesisZQGJjVo();
                    if (m321getFontSynthesisZQGJjVo != null) {
                        i2 = m321getFontSynthesisZQGJjVo.m333unboximpl();
                    } else {
                        i2 = 1;
                    }
                    nextSlot2 = fontFamilyResolverImpl.m328resolveDPcqOEQ(fontFamily, fontWeight, i, i2);
                    composerImpl.updateValue(nextSlot2);
                }
                composerImpl.endReplaceableGroup();
                State state = (State) nextSlot2;
                Object[] objArr = {density, fontFamilyResolverImpl, textStyle, layoutDirection, state.getValue()};
                composerImpl.startReplaceableGroup(-568225417);
                boolean z = false;
                for (int i4 = 0; i4 < 5; i4++) {
                    z |= composerImpl.changed(objArr[i4]);
                }
                Object nextSlot3 = composerImpl.nextSlot();
                if (z || nextSlot3 == Composer.Companion.getEmpty()) {
                    nextSlot3 = Integer.valueOf(IntSize.m405getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText(textStyle3, density, fontFamilyResolverImpl, TextFieldDelegateKt.getEmptyTextReplacement(), 1)));
                    composerImpl.updateValue(nextSlot3);
                }
                composerImpl.endReplaceableGroup();
                int intValue = ((Number) nextSlot3).intValue();
                Object[] objArr2 = {density, fontFamilyResolverImpl, textStyle, layoutDirection, state.getValue()};
                composerImpl.startReplaceableGroup(-568225417);
                boolean z2 = false;
                for (int i5 = 0; i5 < 5; i5++) {
                    z2 |= composerImpl.changed(objArr2[i5]);
                }
                Object nextSlot4 = composerImpl.nextSlot();
                if (z2 || nextSlot4 == Composer.Companion.getEmpty()) {
                    nextSlot4 = Integer.valueOf(IntSize.m405getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText(textStyle3, density, fontFamilyResolverImpl, TextFieldDelegateKt.getEmptyTextReplacement() + '\n' + TextFieldDelegateKt.getEmptyTextReplacement(), 2)));
                    composerImpl.updateValue(nextSlot4);
                }
                composerImpl.endReplaceableGroup();
                int intValue2 = ((Number) nextSlot4).intValue() - intValue;
                int i6 = minLines;
                Integer num = null;
                if (i6 == 1) {
                    valueOf = null;
                } else {
                    valueOf = Integer.valueOf(((i6 - 1) * intValue2) + intValue);
                }
                int i7 = r2;
                if (i7 != Integer.MAX_VALUE) {
                    num = Integer.valueOf(((i7 - 1) * intValue2) + intValue);
                }
                Modifier.Companion companion2 = Modifier.Companion;
                float f2 = Float.NaN;
                if (valueOf != null) {
                    f = density.mo204toDpu2uoSUM(valueOf.intValue());
                } else {
                    f = Float.NaN;
                }
                if (num != null) {
                    f2 = density.mo204toDpu2uoSUM(num.intValue());
                }
                Modifier m15heightInVpY3zN4 = SizeKt.m15heightInVpY3zN4(companion2, f, f2);
                int i8 = ComposerKt.$r8$clinit;
                composerImpl.endReplaceableGroup();
                return m15heightInVpY3zN4;
            }
        }).then(this.semanticsModifier).then(this.selectionModifiers);
    }

    public final TextState getState() {
        return this.state;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onAbandoned() {
        this.state.getClass();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onForgotten() {
        this.state.getClass();
    }

    public final void setTextDelegate(TextDelegate textDelegate) {
        TextState textState = this.state;
        if (textState.getTextDelegate() == textDelegate) {
            return;
        }
        textState.setTextDelegate(textDelegate);
        AnnotatedString text = textState.getTextDelegate().getText();
        Modifier.Companion companion = Modifier.Companion;
        this.semanticsModifier = new SemanticsModifierCore(false, new TextController$createSemanticsModifierFor$1(text, this), InspectableValueKt.getNoInspectorInfo());
    }

    public final void update() {
        this.selectionModifiers = Modifier.Companion;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onRemembered() {
    }
}
