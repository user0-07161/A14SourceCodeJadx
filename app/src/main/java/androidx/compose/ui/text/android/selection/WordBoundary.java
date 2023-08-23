package androidx.compose.ui.text.android.selection;

import androidx.compose.ui.text.android.CharSequenceCharacterIterator;
import java.text.BreakIterator;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class WordBoundary {
    public WordBoundary(Locale locale, CharSequence charSequence) {
        boolean z;
        int length = charSequence.length();
        boolean z2 = true;
        if (charSequence.length() >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if ((length < 0 || length > charSequence.length()) ? false : z2) {
                BreakIterator wordInstance = BreakIterator.getWordInstance(locale);
                Intrinsics.checkNotNullExpressionValue(wordInstance, "getWordInstance(locale)");
                Math.max(0, -50);
                Math.min(charSequence.length(), length + 50);
                wordInstance.setText(new CharSequenceCharacterIterator(charSequence, length));
                return;
            }
            throw new IllegalArgumentException("input end index is outside the CharSequence".toString());
        }
        throw new IllegalArgumentException("input start index is outside the CharSequence".toString());
    }
}
