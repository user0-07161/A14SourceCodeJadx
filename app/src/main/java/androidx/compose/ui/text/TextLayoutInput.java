package androidx.compose.ui.text;

import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextLayoutInput {
    private final long constraints;
    private final Density density;
    private final FontFamilyResolverImpl fontFamilyResolver;
    private final LayoutDirection layoutDirection;
    private final int maxLines;
    private final int overflow;
    private final List placeholders;
    private final boolean softWrap;
    private final TextStyle style;
    private final AnnotatedString text;

    public TextLayoutInput(AnnotatedString annotatedString, TextStyle textStyle, List list, int i, boolean z, int i2, Density density, LayoutDirection layoutDirection, FontFamilyResolverImpl fontFamilyResolverImpl, long j) {
        this.text = annotatedString;
        this.style = textStyle;
        this.placeholders = list;
        this.maxLines = i;
        this.softWrap = z;
        this.overflow = i2;
        this.density = density;
        this.layoutDirection = layoutDirection;
        this.fontFamilyResolver = fontFamilyResolverImpl;
        this.constraints = j;
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextLayoutInput)) {
            return false;
        }
        TextLayoutInput textLayoutInput = (TextLayoutInput) obj;
        if (!Intrinsics.areEqual(this.text, textLayoutInput.text) || !Intrinsics.areEqual(this.style, textLayoutInput.style) || !Intrinsics.areEqual(this.placeholders, textLayoutInput.placeholders) || this.maxLines != textLayoutInput.maxLines || this.softWrap != textLayoutInput.softWrap) {
            return false;
        }
        if (this.overflow == textLayoutInput.overflow) {
            z = true;
        } else {
            z = false;
        }
        if (z && Intrinsics.areEqual(this.density, textLayoutInput.density) && this.layoutDirection == textLayoutInput.layoutDirection && Intrinsics.areEqual(this.fontFamilyResolver, textLayoutInput.fontFamilyResolver) && Constraints.m377equalsimpl0(this.constraints, textLayoutInput.constraints)) {
            return true;
        }
        return false;
    }

    /* renamed from: getConstraints-msEJaDk  reason: not valid java name */
    public final long m310getConstraintsmsEJaDk() {
        return this.constraints;
    }

    public final Density getDensity() {
        return this.density;
    }

    public final FontFamilyResolverImpl getFontFamilyResolver() {
        return this.fontFamilyResolver;
    }

    public final LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    public final int getMaxLines() {
        return this.maxLines;
    }

    /* renamed from: getOverflow-gIe3tQ8  reason: not valid java name */
    public final int m311getOverflowgIe3tQ8() {
        return this.overflow;
    }

    public final List getPlaceholders() {
        return this.placeholders;
    }

    public final boolean getSoftWrap() {
        return this.softWrap;
    }

    public final TextStyle getStyle() {
        return this.style;
    }

    public final AnnotatedString getText() {
        return this.text;
    }

    public final int hashCode() {
        int hashCode = this.style.hashCode();
        int hashCode2 = this.placeholders.hashCode();
        int hashCode3 = Boolean.hashCode(this.softWrap);
        int hashCode4 = Integer.hashCode(this.overflow);
        int hashCode5 = this.density.hashCode();
        int hashCode6 = this.layoutDirection.hashCode();
        int hashCode7 = this.fontFamilyResolver.hashCode();
        return Long.hashCode(this.constraints) + ((hashCode7 + ((hashCode6 + ((hashCode5 + ((hashCode4 + ((hashCode3 + ((((hashCode2 + ((hashCode + (this.text.hashCode() * 31)) * 31)) * 31) + this.maxLines) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        boolean z;
        boolean z2;
        String str;
        StringBuilder sb = new StringBuilder("TextLayoutInput(text=");
        sb.append((Object) this.text);
        sb.append(", style=");
        sb.append(this.style);
        sb.append(", placeholders=");
        sb.append(this.placeholders);
        sb.append(", maxLines=");
        sb.append(this.maxLines);
        sb.append(", softWrap=");
        sb.append(this.softWrap);
        sb.append(", overflow=");
        int i = this.overflow;
        boolean z3 = false;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            str = "Clip";
        } else {
            if (i == 2) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                str = "Ellipsis";
            } else {
                if (i == 3) {
                    z3 = true;
                }
                if (z3) {
                    str = "Visible";
                } else {
                    str = "Invalid";
                }
            }
        }
        sb.append((Object) str);
        sb.append(", density=");
        sb.append(this.density);
        sb.append(", layoutDirection=");
        sb.append(this.layoutDirection);
        sb.append(", fontFamilyResolver=");
        sb.append(this.fontFamilyResolver);
        sb.append(", constraints=");
        sb.append((Object) Constraints.m384toStringimpl(this.constraints));
        sb.append(')');
        return sb.toString();
    }
}
