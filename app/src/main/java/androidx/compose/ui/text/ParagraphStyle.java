package androidx.compose.ui.text;

import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ParagraphStyle {
    private final Hyphens hyphens;
    private final LineBreak lineBreak;
    private final long lineHeight;
    private final LineHeightStyle lineHeightStyle;
    private final TextAlign textAlign;
    private final TextDirection textDirection;
    private final TextIndent textIndent;
    private final TextMotion textMotion;

    public ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j, TextIndent textIndent, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion) {
        long j2;
        this.textAlign = textAlign;
        this.textDirection = textDirection;
        this.lineHeight = j;
        this.textIndent = textIndent;
        this.lineHeightStyle = lineHeightStyle;
        this.lineBreak = lineBreak;
        this.hyphens = hyphens;
        this.textMotion = textMotion;
        j2 = TextUnit.Unspecified;
        if (TextUnit.m410equalsimpl0(j, j2)) {
            return;
        }
        if (TextUnit.m412getValueimpl(j) >= 0.0f) {
            return;
        }
        throw new IllegalStateException(("lineHeight can't be negative (" + TextUnit.m412getValueimpl(j) + ')').toString());
    }

    /* renamed from: copy-ciSxzs0$default  reason: not valid java name */
    public static ParagraphStyle m296copyciSxzs0$default(ParagraphStyle paragraphStyle, TextDirection textDirection) {
        return new ParagraphStyle(paragraphStyle.textAlign, textDirection, paragraphStyle.lineHeight, paragraphStyle.textIndent, paragraphStyle.lineHeightStyle, paragraphStyle.lineBreak, paragraphStyle.hyphens, paragraphStyle.textMotion);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ParagraphStyle)) {
            return false;
        }
        ParagraphStyle paragraphStyle = (ParagraphStyle) obj;
        if (!Intrinsics.areEqual(this.textAlign, paragraphStyle.textAlign) || !Intrinsics.areEqual(this.textDirection, paragraphStyle.textDirection) || !TextUnit.m410equalsimpl0(this.lineHeight, paragraphStyle.lineHeight) || !Intrinsics.areEqual(this.textIndent, paragraphStyle.textIndent)) {
            return false;
        }
        paragraphStyle.getClass();
        if (Intrinsics.areEqual(null, null) && Intrinsics.areEqual(this.lineHeightStyle, paragraphStyle.lineHeightStyle) && Intrinsics.areEqual(this.lineBreak, paragraphStyle.lineBreak) && Intrinsics.areEqual(this.hyphens, paragraphStyle.hyphens) && Intrinsics.areEqual(this.textMotion, paragraphStyle.textMotion)) {
            return true;
        }
        return false;
    }

    /* renamed from: getHyphens-EaSxIns  reason: not valid java name */
    public final Hyphens m297getHyphensEaSxIns() {
        return this.hyphens;
    }

    /* renamed from: getLineBreak-LgCVezo  reason: not valid java name */
    public final LineBreak m298getLineBreakLgCVezo() {
        return this.lineBreak;
    }

    /* renamed from: getLineHeight-XSAIIZE  reason: not valid java name */
    public final long m299getLineHeightXSAIIZE() {
        return this.lineHeight;
    }

    public final LineHeightStyle getLineHeightStyle() {
        return this.lineHeightStyle;
    }

    /* renamed from: getTextAlign-buA522U  reason: not valid java name */
    public final TextAlign m300getTextAlignbuA522U() {
        return this.textAlign;
    }

    /* renamed from: getTextDirection-mmuk1to  reason: not valid java name */
    public final TextDirection m301getTextDirectionmmuk1to() {
        return this.textDirection;
    }

    public final TextIndent getTextIndent() {
        return this.textIndent;
    }

    public final TextMotion getTextMotion() {
        return this.textMotion;
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        TextAlign textAlign = this.textAlign;
        if (textAlign != null) {
            i = Integer.hashCode(textAlign.m369unboximpl());
        } else {
            i = 0;
        }
        int i8 = i * 31;
        TextDirection textDirection = this.textDirection;
        if (textDirection != null) {
            i2 = Integer.hashCode(textDirection.m371unboximpl());
        } else {
            i2 = 0;
        }
        TextUnit.Companion companion = TextUnit.Companion;
        int hashCode = (Long.hashCode(this.lineHeight) + ((i8 + i2) * 31)) * 31;
        TextIndent textIndent = this.textIndent;
        if (textIndent != null) {
            i3 = textIndent.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (((hashCode + i3) * 31) + 0) * 31;
        LineHeightStyle lineHeightStyle = this.lineHeightStyle;
        if (lineHeightStyle != null) {
            i4 = lineHeightStyle.hashCode();
        } else {
            i4 = 0;
        }
        int i10 = (i9 + i4) * 31;
        LineBreak lineBreak = this.lineBreak;
        if (lineBreak != null) {
            i5 = Integer.hashCode(lineBreak.m356unboximpl());
        } else {
            i5 = 0;
        }
        int i11 = (i10 + i5) * 31;
        Hyphens hyphens = this.hyphens;
        if (hyphens != null) {
            i6 = Integer.hashCode(hyphens.m354unboximpl());
        } else {
            i6 = 0;
        }
        int i12 = (i11 + i6) * 31;
        TextMotion textMotion = this.textMotion;
        if (textMotion != null) {
            i7 = textMotion.hashCode();
        }
        return i12 + i7;
    }

    public final ParagraphStyle merge(ParagraphStyle paragraphStyle) {
        TextMotion textMotion;
        if (paragraphStyle == null) {
            return this;
        }
        long j = paragraphStyle.lineHeight;
        if (TextUnitKt.m415isUnspecifiedR2X_6o(j)) {
            j = this.lineHeight;
        }
        long j2 = j;
        TextIndent textIndent = paragraphStyle.textIndent;
        if (textIndent == null) {
            textIndent = this.textIndent;
        }
        TextIndent textIndent2 = textIndent;
        TextAlign textAlign = paragraphStyle.textAlign;
        if (textAlign == null) {
            textAlign = this.textAlign;
        }
        TextAlign textAlign2 = textAlign;
        TextDirection textDirection = paragraphStyle.textDirection;
        if (textDirection == null) {
            textDirection = this.textDirection;
        }
        TextDirection textDirection2 = textDirection;
        LineHeightStyle lineHeightStyle = paragraphStyle.lineHeightStyle;
        if (lineHeightStyle == null) {
            lineHeightStyle = this.lineHeightStyle;
        }
        LineHeightStyle lineHeightStyle2 = lineHeightStyle;
        LineBreak lineBreak = paragraphStyle.lineBreak;
        if (lineBreak == null) {
            lineBreak = this.lineBreak;
        }
        LineBreak lineBreak2 = lineBreak;
        Hyphens hyphens = paragraphStyle.hyphens;
        if (hyphens == null) {
            hyphens = this.hyphens;
        }
        Hyphens hyphens2 = hyphens;
        TextMotion textMotion2 = paragraphStyle.textMotion;
        if (textMotion2 == null) {
            textMotion = this.textMotion;
        } else {
            textMotion = textMotion2;
        }
        return new ParagraphStyle(textAlign2, textDirection2, j2, textIndent2, lineHeightStyle2, lineBreak2, hyphens2, textMotion);
    }

    public final String toString() {
        return "ParagraphStyle(textAlign=" + this.textAlign + ", textDirection=" + this.textDirection + ", lineHeight=" + ((Object) TextUnit.m413toStringimpl(this.lineHeight)) + ", textIndent=" + this.textIndent + ", platformStyle=null, lineHeightStyle=" + this.lineHeightStyle + ", lineBreak=" + this.lineBreak + ", hyphens=" + this.hyphens + ", textMotion=" + this.textMotion + ')';
    }

    public ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j, TextIndent textIndent, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens) {
        this(textAlign, textDirection, j, textIndent, lineHeightStyle, lineBreak, hyphens, null);
    }
}
