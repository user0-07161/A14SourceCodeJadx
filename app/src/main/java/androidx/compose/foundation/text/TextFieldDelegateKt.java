package androidx.compose.foundation.text;

import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TextFieldDelegateKt {
    private static final String EmptyTextReplacement;

    static {
        String str;
        int length = "H".length();
        if (length != 0) {
            if (length != 1) {
                StringBuilder sb = new StringBuilder("H".length() * 10);
                IntProgressionIterator it = new IntRange(1, 10).iterator();
                while (it.hasNext()) {
                    it.nextInt();
                    sb.append((CharSequence) "H");
                }
                str = sb.toString();
                Intrinsics.checkNotNullExpressionValue(str, "{\n                    va…tring()\n                }");
            } else {
                char charAt = "H".charAt(0);
                char[] cArr = new char[10];
                for (int i = 0; i < 10; i++) {
                    cArr[i] = charAt;
                }
                str = new String(cArr);
            }
        } else {
            str = "";
        }
        EmptyTextReplacement = str;
    }

    public static final long computeSizeForDefaultText(TextStyle style, Density density, FontFamilyResolverImpl fontFamilyResolver, String text, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(fontFamilyResolver, "fontFamilyResolver");
        Intrinsics.checkNotNullParameter(text, "text");
        EmptyList spanStyles = EmptyList.INSTANCE;
        long Constraints$default = ConstraintsKt.Constraints$default(0, 0, 15);
        Intrinsics.checkNotNullParameter(spanStyles, "spanStyles");
        AndroidParagraph androidParagraph = new AndroidParagraph(new AndroidParagraphIntrinsics(style, fontFamilyResolver, density, text, spanStyles, spanStyles), i, false, Constraints$default);
        return IntSizeKt.IntSize(TextDelegateKt.ceilToIntPx(androidParagraph.getMinIntrinsicWidth()), TextDelegateKt.ceilToIntPx(androidParagraph.getHeight()));
    }

    public static final String getEmptyTextReplacement() {
        return EmptyTextReplacement;
    }
}
