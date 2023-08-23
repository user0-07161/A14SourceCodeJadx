package androidx.emoji2.text;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.MetadataRepo;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class EmojiProcessor {
    private EmojiCompat.GlyphChecker mGlyphChecker;
    private final MetadataRepo mMetadataRepo;
    private final EmojiCompat.DefaultSpanFactory mSpanFactory;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class EmojiProcessAddSpanCallback implements EmojiProcessCallback {
        private final EmojiCompat.DefaultSpanFactory mSpanFactory;
        public UnprecomputeTextOnModificationSpannable spannable;

        EmojiProcessAddSpanCallback(UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable, EmojiCompat.DefaultSpanFactory defaultSpanFactory) {
            this.spannable = unprecomputeTextOnModificationSpannable;
            this.mSpanFactory = defaultSpanFactory;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final Object getResult() {
            return this.spannable;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            Spannable spannableString;
            if (typefaceEmojiRasterizer.isPreferredSystemRender()) {
                return true;
            }
            if (this.spannable == null) {
                if (charSequence instanceof Spannable) {
                    spannableString = (Spannable) charSequence;
                } else {
                    spannableString = new SpannableString(charSequence);
                }
                this.spannable = new UnprecomputeTextOnModificationSpannable(spannableString);
            }
            this.mSpanFactory.getClass();
            this.spannable.setSpan(new TypefaceEmojiSpan(typefaceEmojiRasterizer), i, i2, 33);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface EmojiProcessCallback {
        Object getResult();

        boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ProcessorSm {
        private int mCurrentDepth;
        private MetadataRepo.Node mCurrentNode;
        private final int[] mEmojiAsDefaultStyleExceptions;
        private MetadataRepo.Node mFlushNode;
        private int mLastCodepoint;
        private final MetadataRepo.Node mRootNode;
        private int mState = 1;
        private final boolean mUseEmojiAsDefaultStyle;

        ProcessorSm(MetadataRepo.Node node, boolean z, int[] iArr) {
            this.mRootNode = node;
            this.mCurrentNode = node;
            this.mUseEmojiAsDefaultStyle = z;
            this.mEmojiAsDefaultStyleExceptions = iArr;
        }

        private void reset() {
            this.mState = 1;
            this.mCurrentNode = this.mRootNode;
            this.mCurrentDepth = 0;
        }

        private boolean shouldUseEmojiPresentationStyleForSingleCodepoint() {
            boolean z;
            int[] iArr;
            if (this.mCurrentNode.getData().isDefaultEmoji()) {
                return true;
            }
            if (this.mLastCodepoint == 65039) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
            if (this.mUseEmojiAsDefaultStyle && ((iArr = this.mEmojiAsDefaultStyleExceptions) == null || Arrays.binarySearch(iArr, this.mCurrentNode.getData().getCodepointAt(0)) < 0)) {
                return true;
            }
            return false;
        }

        final int check(int i) {
            boolean z;
            MetadataRepo.Node node = this.mCurrentNode.get(i);
            int i2 = 1;
            if (this.mState != 2) {
                if (node == null) {
                    reset();
                } else {
                    this.mState = 2;
                    this.mCurrentNode = node;
                    this.mCurrentDepth = 1;
                    i2 = 2;
                }
            } else {
                if (node != null) {
                    this.mCurrentNode = node;
                    this.mCurrentDepth++;
                } else {
                    boolean z2 = false;
                    if (i == 65038) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        reset();
                    } else {
                        if (i == 65039) {
                            z2 = true;
                        }
                        if (!z2) {
                            if (this.mCurrentNode.getData() != null) {
                                if (this.mCurrentDepth == 1) {
                                    if (shouldUseEmojiPresentationStyleForSingleCodepoint()) {
                                        this.mFlushNode = this.mCurrentNode;
                                        reset();
                                    } else {
                                        reset();
                                    }
                                } else {
                                    this.mFlushNode = this.mCurrentNode;
                                    reset();
                                }
                                i2 = 3;
                            } else {
                                reset();
                            }
                        }
                    }
                }
                i2 = 2;
            }
            this.mLastCodepoint = i;
            return i2;
        }

        final TypefaceEmojiRasterizer getCurrentMetadata() {
            return this.mCurrentNode.getData();
        }

        final TypefaceEmojiRasterizer getFlushMetadata() {
            return this.mFlushNode.getData();
        }

        final boolean isInFlushableState() {
            if (this.mState == 2 && this.mCurrentNode.getData() != null && (this.mCurrentDepth > 1 || shouldUseEmojiPresentationStyleForSingleCodepoint())) {
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmojiProcessor(MetadataRepo metadataRepo, EmojiCompat.DefaultSpanFactory defaultSpanFactory, EmojiCompat.GlyphChecker glyphChecker, Set set) {
        this.mSpanFactory = defaultSpanFactory;
        this.mMetadataRepo = metadataRepo;
        this.mGlyphChecker = glyphChecker;
        if (!set.isEmpty()) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                int[] iArr = (int[]) it.next();
                String str = new String(iArr, 0, iArr.length);
                process(str, 0, str.length(), 1, true, new MarkExclusionCallback(str));
            }
        }
    }

    private boolean hasGlyph(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        if (typefaceEmojiRasterizer.getHasGlyph() == 0) {
            EmojiCompat.GlyphChecker glyphChecker = this.mGlyphChecker;
            typefaceEmojiRasterizer.getSdkAdded();
            typefaceEmojiRasterizer.setHasGlyph(((DefaultGlyphChecker) glyphChecker).hasGlyph(charSequence, i, i2));
        }
        if (typefaceEmojiRasterizer.getHasGlyph() == 2) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final CharSequence process(CharSequence charSequence, int i, boolean z) {
        UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable;
        int i2;
        int i3;
        UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable2;
        TypefaceEmojiSpan[] typefaceEmojiSpanArr;
        if (charSequence instanceof Spannable) {
            unprecomputeTextOnModificationSpannable = new UnprecomputeTextOnModificationSpannable((Spannable) charSequence);
        } else {
            unprecomputeTextOnModificationSpannable = (!(charSequence instanceof Spanned) || ((Spanned) charSequence).nextSpanTransition(-1, i + 1, TypefaceEmojiSpan.class) > i) ? null : new UnprecomputeTextOnModificationSpannable(charSequence);
        }
        if (unprecomputeTextOnModificationSpannable == null || (typefaceEmojiSpanArr = (TypefaceEmojiSpan[]) unprecomputeTextOnModificationSpannable.getSpans(0, i, TypefaceEmojiSpan.class)) == null || typefaceEmojiSpanArr.length <= 0) {
            i2 = i;
            i3 = 0;
        } else {
            int i4 = 0;
            for (TypefaceEmojiSpan typefaceEmojiSpan : typefaceEmojiSpanArr) {
                int spanStart = unprecomputeTextOnModificationSpannable.getSpanStart(typefaceEmojiSpan);
                int spanEnd = unprecomputeTextOnModificationSpannable.getSpanEnd(typefaceEmojiSpan);
                if (spanStart != i) {
                    unprecomputeTextOnModificationSpannable.removeSpan(typefaceEmojiSpan);
                }
                i4 = Math.min(spanStart, i4);
                i = Math.max(spanEnd, i);
            }
            i2 = i;
            i3 = i4;
        }
        return (i3 == i2 || i3 >= charSequence.length() || (unprecomputeTextOnModificationSpannable2 = (UnprecomputeTextOnModificationSpannable) process(charSequence, i3, i2, Integer.MAX_VALUE, z, new EmojiProcessAddSpanCallback(unprecomputeTextOnModificationSpannable, this.mSpanFactory))) == null) ? charSequence : unprecomputeTextOnModificationSpannable2.getUnwrappedSpannable();
    }

    private Object process(CharSequence charSequence, int i, int i2, int i3, boolean z, EmojiProcessCallback emojiProcessCallback) {
        int i4;
        int i5 = 0;
        ProcessorSm processorSm = new ProcessorSm(this.mMetadataRepo.getRootNode(), false, null);
        int codePointAt = Character.codePointAt(charSequence, i);
        boolean z2 = true;
        loop0: while (true) {
            int i6 = codePointAt;
            i4 = i;
            while (i4 < i2 && i5 < i3 && z2) {
                int check = processorSm.check(i6);
                if (check == 1) {
                    i += Character.charCount(Character.codePointAt(charSequence, i));
                    if (i < i2) {
                        codePointAt = Character.codePointAt(charSequence, i);
                    }
                } else if (check == 2) {
                    i4 += Character.charCount(i6);
                    if (i4 < i2) {
                        i6 = Character.codePointAt(charSequence, i4);
                    }
                } else if (check == 3) {
                    if (z || !hasGlyph(charSequence, i, i4, processorSm.getFlushMetadata())) {
                        z2 = emojiProcessCallback.handleEmoji(charSequence, i, i4, processorSm.getFlushMetadata());
                        i5++;
                    }
                    i = i4;
                }
                codePointAt = i6;
            }
        }
        if (processorSm.isInFlushableState() && i5 < i3 && z2 && (z || !hasGlyph(charSequence, i, i4, processorSm.getCurrentMetadata()))) {
            emojiProcessCallback.handleEmoji(charSequence, i, i4, processorSm.getCurrentMetadata());
        }
        return emojiProcessCallback.getResult();
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class MarkExclusionCallback implements EmojiProcessCallback {
        private final String mExclusion;

        MarkExclusionCallback(String str) {
            this.mExclusion = str;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            if (TextUtils.equals(charSequence.subSequence(i, i2), this.mExclusion)) {
                typefaceEmojiRasterizer.setExclusion();
                return false;
            }
            return true;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final Object getResult() {
            return this;
        }
    }
}
