package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextStyle {
    private static final TextStyle Default = new TextStyle(0, 0, null, null, null, 0, null, null, 0, 4194303);
    private final ParagraphStyle paragraphStyle;
    private final PlatformTextStyle platformStyle;
    private final SpanStyle spanStyle;

    public TextStyle(SpanStyle spanStyle, ParagraphStyle paragraphStyle, PlatformTextStyle platformTextStyle) {
        Intrinsics.checkNotNullParameter(spanStyle, "spanStyle");
        this.spanStyle = spanStyle;
        this.paragraphStyle = paragraphStyle;
        this.platformStyle = platformTextStyle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextStyle)) {
            return false;
        }
        TextStyle textStyle = (TextStyle) obj;
        if (Intrinsics.areEqual(this.spanStyle, textStyle.spanStyle) && Intrinsics.areEqual(this.paragraphStyle, textStyle.paragraphStyle) && Intrinsics.areEqual(this.platformStyle, textStyle.platformStyle)) {
            return true;
        }
        return false;
    }

    public final float getAlpha() {
        return this.spanStyle.getAlpha();
    }

    public final Brush getBrush() {
        return this.spanStyle.getBrush();
    }

    /* renamed from: getColor-0d7_KjU  reason: not valid java name */
    public final long m319getColor0d7_KjU() {
        return this.spanStyle.m305getColor0d7_KjU();
    }

    public final FontFamily getFontFamily() {
        return this.spanStyle.getFontFamily();
    }

    /* renamed from: getFontStyle-4Lr2A7w  reason: not valid java name */
    public final FontStyle m320getFontStyle4Lr2A7w() {
        return this.spanStyle.m307getFontStyle4Lr2A7w();
    }

    /* renamed from: getFontSynthesis-ZQGJjVo  reason: not valid java name */
    public final FontSynthesis m321getFontSynthesisZQGJjVo() {
        return this.spanStyle.m308getFontSynthesisZQGJjVo();
    }

    public final FontWeight getFontWeight() {
        return this.spanStyle.getFontWeight();
    }

    /* renamed from: getLetterSpacing-XSAIIZE  reason: not valid java name */
    public final long m322getLetterSpacingXSAIIZE() {
        return this.spanStyle.m309getLetterSpacingXSAIIZE();
    }

    /* renamed from: getLineBreak-LgCVezo  reason: not valid java name */
    public final LineBreak m323getLineBreakLgCVezo() {
        return this.paragraphStyle.m298getLineBreakLgCVezo();
    }

    /* renamed from: getLineHeight-XSAIIZE  reason: not valid java name */
    public final long m324getLineHeightXSAIIZE() {
        return this.paragraphStyle.m299getLineHeightXSAIIZE();
    }

    public final LineHeightStyle getLineHeightStyle() {
        return this.paragraphStyle.getLineHeightStyle();
    }

    public final LocaleList getLocaleList() {
        return this.spanStyle.getLocaleList();
    }

    public final ParagraphStyle getParagraphStyle$ui_text_release() {
        return this.paragraphStyle;
    }

    public final PlatformTextStyle getPlatformStyle() {
        return this.platformStyle;
    }

    public final SpanStyle getSpanStyle$ui_text_release() {
        return this.spanStyle;
    }

    /* renamed from: getTextAlign-buA522U  reason: not valid java name */
    public final TextAlign m325getTextAlignbuA522U() {
        return this.paragraphStyle.m300getTextAlignbuA522U();
    }

    public final TextDecoration getTextDecoration() {
        return this.spanStyle.getTextDecoration();
    }

    /* renamed from: getTextDirection-mmuk1to  reason: not valid java name */
    public final TextDirection m326getTextDirectionmmuk1to() {
        return this.paragraphStyle.m301getTextDirectionmmuk1to();
    }

    public final TextIndent getTextIndent() {
        return this.paragraphStyle.getTextIndent();
    }

    public final TextMotion getTextMotion() {
        return this.paragraphStyle.getTextMotion();
    }

    public final boolean hasSameLayoutAffectingAttributes(TextStyle textStyle) {
        if (this != textStyle && (!Intrinsics.areEqual(this.paragraphStyle, textStyle.paragraphStyle) || !this.spanStyle.hasSameLayoutAffectingAttributes$ui_text_release(textStyle.spanStyle))) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return ((this.paragraphStyle.hashCode() + (this.spanStyle.hashCode() * 31)) * 31) + 0;
    }

    public final TextStyle merge(TextStyle textStyle) {
        return Intrinsics.areEqual(textStyle, Default) ? this : new TextStyle(this.spanStyle.merge(textStyle.spanStyle), this.paragraphStyle.merge(textStyle.paragraphStyle));
    }

    public final ParagraphStyle toParagraphStyle() {
        return this.paragraphStyle;
    }

    public final SpanStyle toSpanStyle() {
        return this.spanStyle;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TextStyle(color=");
        sb.append((Object) Color.m98toStringimpl(m319getColor0d7_KjU()));
        sb.append(", brush=");
        sb.append(getBrush());
        sb.append(", alpha=");
        sb.append(getAlpha());
        sb.append(", fontSize=");
        SpanStyle spanStyle = this.spanStyle;
        sb.append((Object) TextUnit.m413toStringimpl(spanStyle.m306getFontSizeXSAIIZE()));
        sb.append(", fontWeight=");
        sb.append(getFontWeight());
        sb.append(", fontStyle=");
        sb.append(m320getFontStyle4Lr2A7w());
        sb.append(", fontSynthesis=");
        sb.append(m321getFontSynthesisZQGJjVo());
        sb.append(", fontFamily=");
        sb.append(getFontFamily());
        sb.append(", fontFeatureSettings=");
        sb.append(spanStyle.getFontFeatureSettings());
        sb.append(", letterSpacing=");
        sb.append((Object) TextUnit.m413toStringimpl(m322getLetterSpacingXSAIIZE()));
        sb.append(", baselineShift=");
        sb.append(spanStyle.m304getBaselineShift5SSeXJ0());
        sb.append(", textGeometricTransform=");
        sb.append(spanStyle.getTextGeometricTransform());
        sb.append(", localeList=");
        sb.append(getLocaleList());
        sb.append(", background=");
        sb.append((Object) Color.m98toStringimpl(spanStyle.m303getBackground0d7_KjU()));
        sb.append(", textDecoration=");
        sb.append(getTextDecoration());
        sb.append(", shadow=");
        sb.append(spanStyle.getShadow());
        sb.append(", drawStyle=");
        sb.append(spanStyle.getDrawStyle());
        sb.append(", textAlign=");
        sb.append(m325getTextAlignbuA522U());
        sb.append(", textDirection=");
        sb.append(m326getTextDirectionmmuk1to());
        sb.append(", lineHeight=");
        sb.append((Object) TextUnit.m413toStringimpl(m324getLineHeightXSAIIZE()));
        sb.append(", textIndent=");
        sb.append(getTextIndent());
        sb.append(", platformStyle=");
        sb.append(this.platformStyle);
        sb.append(", lineHeightStyle=");
        sb.append(getLineHeightStyle());
        sb.append(", lineBreak=");
        sb.append(m323getLineBreakLgCVezo());
        sb.append(", hyphens=");
        sb.append(this.paragraphStyle.m297getHyphensEaSxIns());
        sb.append(", textMotion=");
        sb.append(getTextMotion());
        sb.append(')');
        return sb.toString();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TextStyle(SpanStyle spanStyle, ParagraphStyle paragraphStyle) {
        this(spanStyle, paragraphStyle, null);
        Intrinsics.checkNotNullParameter(spanStyle, "spanStyle");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TextStyle(long r30, long r32, androidx.compose.ui.text.font.FontWeight r34, androidx.compose.ui.text.font.FontStyle r35, androidx.compose.ui.text.font.FontFamily r36, long r37, androidx.compose.ui.text.style.TextDecoration r39, androidx.compose.ui.text.style.TextAlign r40, long r41, int r43) {
        /*
            r29 = this;
            r0 = r43
            r1 = r0 & 1
            if (r1 == 0) goto Lb
            long r1 = androidx.compose.ui.graphics.Color.access$getUnspecified$cp()
            goto Ld
        Lb:
            r1 = r30
        Ld:
            r3 = r0 & 2
            if (r3 == 0) goto L17
            long r3 = androidx.compose.ui.unit.TextUnit.access$getUnspecified$cp()
            r7 = r3
            goto L19
        L17:
            r7 = r32
        L19:
            r3 = r0 & 4
            r4 = 0
            if (r3 == 0) goto L20
            r9 = r4
            goto L22
        L20:
            r9 = r34
        L22:
            r3 = r0 & 8
            if (r3 == 0) goto L28
            r10 = r4
            goto L2a
        L28:
            r10 = r35
        L2a:
            r11 = 0
            r3 = r0 & 32
            if (r3 == 0) goto L31
            r12 = r4
            goto L33
        L31:
            r12 = r36
        L33:
            r13 = 0
            r3 = r0 & 128(0x80, float:1.8E-43)
            if (r3 == 0) goto L3e
            long r5 = androidx.compose.ui.unit.TextUnit.access$getUnspecified$cp()
            r14 = r5
            goto L40
        L3e:
            r14 = r37
        L40:
            r16 = 0
            r17 = 0
            r18 = 0
            r3 = r0 & 2048(0x800, float:2.87E-42)
            if (r3 == 0) goto L4f
            long r5 = androidx.compose.ui.graphics.Color.access$getUnspecified$cp()
            goto L51
        L4f:
            r5 = 0
        L51:
            r19 = r5
            r3 = r0 & 4096(0x1000, float:5.74E-42)
            if (r3 == 0) goto L5a
            r21 = r4
            goto L5c
        L5a:
            r21 = r39
        L5c:
            r22 = 0
            r3 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r3 == 0) goto L64
            r3 = r4
            goto L66
        L64:
            r3 = r40
        L66:
            r23 = 0
            r5 = 65536(0x10000, float:9.1835E-41)
            r0 = r0 & r5
            if (r0 == 0) goto L74
            long r5 = androidx.compose.ui.unit.TextUnit.access$getUnspecified$cp()
            r24 = r5
            goto L76
        L74:
            r24 = r41
        L76:
            r0 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            androidx.compose.ui.text.SpanStyle r6 = new androidx.compose.ui.text.SpanStyle
            r5 = r6
            androidx.compose.ui.text.style.TextForegroundStyle r1 = androidx.compose.ui.text.style.TextForegroundStyle.Companion.m372from8_81llA(r1)
            r2 = r6
            r6 = r1
            r5.<init>(r6, r7, r9, r10, r11, r12, r13, r14, r16, r17, r18, r19, r21, r22)
            androidx.compose.ui.text.ParagraphStyle r1 = new androidx.compose.ui.text.ParagraphStyle
            r30 = r1
            r31 = r3
            r32 = r23
            r33 = r24
            r35 = r0
            r36 = r26
            r37 = r27
            r38 = r28
            r30.<init>(r31, r32, r33, r35, r36, r37, r38)
            r0 = r29
            r0.<init>(r2, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.TextStyle.<init>(long, long, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int):void");
    }

    public final TextStyle merge(ParagraphStyle paragraphStyle) {
        return new TextStyle(this.spanStyle, this.paragraphStyle.merge(paragraphStyle));
    }
}
