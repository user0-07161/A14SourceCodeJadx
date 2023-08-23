package androidx.emoji2.text;

import android.text.TextPaint;
import androidx.core.graphics.PaintCompat;
import androidx.emoji2.text.EmojiCompat;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class DefaultGlyphChecker implements EmojiCompat.GlyphChecker {
    private static final ThreadLocal sStringBuilder = new ThreadLocal();
    private final TextPaint mTextPaint;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultGlyphChecker() {
        TextPaint textPaint = new TextPaint();
        this.mTextPaint = textPaint;
        textPaint.setTextSize(10.0f);
    }

    public final boolean hasGlyph(CharSequence charSequence, int i, int i2) {
        ThreadLocal threadLocal = sStringBuilder;
        if (threadLocal.get() == null) {
            threadLocal.set(new StringBuilder());
        }
        StringBuilder sb = (StringBuilder) threadLocal.get();
        sb.setLength(0);
        while (i < i2) {
            sb.append(charSequence.charAt(i));
            i++;
        }
        TextPaint textPaint = this.mTextPaint;
        String sb2 = sb.toString();
        int i3 = PaintCompat.$r8$clinit;
        return textPaint.hasGlyph(sb2);
    }
}
