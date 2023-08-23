package androidx.compose.ui.text.platform;

import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.android.LayoutIntrinsics;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.unit.Density;
import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidParagraphIntrinsics implements ParagraphIntrinsics {
    private final CharSequence charSequence;
    private final Density density;
    private final boolean emojiCompatProcessed;
    private final FontFamilyResolverImpl fontFamilyResolver;
    private final LayoutIntrinsics layoutIntrinsics;
    private final List placeholders;
    private TypefaceDirtyTrackerLinkedList resolvedTypefaces;
    private final List spanStyles;
    private final TextStyle style;
    private final String text;
    private final int textDirectionHeuristic;
    private final AndroidTextPaint textPaint;

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a0, code lost:
        if (r6 == null) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ae, code lost:
        if (r6 == 1) goto L170;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x032a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01c9 A[LOOP:0: B:92:0x01c3->B:94:0x01c9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0200  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AndroidParagraphIntrinsics(androidx.compose.ui.text.TextStyle r32, androidx.compose.ui.text.font.FontFamilyResolverImpl r33, androidx.compose.ui.unit.Density r34, java.lang.String r35, java.util.List r36, java.util.List r37) {
        /*
            Method dump skipped, instructions count: 983
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.platform.AndroidParagraphIntrinsics.<init>(androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.font.FontFamilyResolverImpl, androidx.compose.ui.unit.Density, java.lang.String, java.util.List, java.util.List):void");
    }

    public final CharSequence getCharSequence$ui_text_release() {
        return this.charSequence;
    }

    public final FontFamilyResolverImpl getFontFamilyResolver() {
        return this.fontFamilyResolver;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final boolean getHasStaleResolvedFonts() {
        boolean z;
        TypefaceDirtyTrackerLinkedList typefaceDirtyTrackerLinkedList = this.resolvedTypefaces;
        if (typefaceDirtyTrackerLinkedList != null) {
            z = typefaceDirtyTrackerLinkedList.isStaleResolvedFont();
        } else {
            z = false;
        }
        if (!z) {
            if (this.emojiCompatProcessed) {
                return false;
            }
            this.style.getPlatformStyle();
            if (!((Boolean) EmojiCompatStatus.INSTANCE.getFontLoaded().getValue()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public final LayoutIntrinsics getLayoutIntrinsics$ui_text_release() {
        return this.layoutIntrinsics;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMaxIntrinsicWidth() {
        return this.layoutIntrinsics.getMaxIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMinIntrinsicWidth() {
        return this.layoutIntrinsics.getMinIntrinsicWidth();
    }

    public final TextStyle getStyle() {
        return this.style;
    }

    public final int getTextDirectionHeuristic$ui_text_release() {
        return this.textDirectionHeuristic;
    }

    public final AndroidTextPaint getTextPaint$ui_text_release() {
        return this.textPaint;
    }
}
