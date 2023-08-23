package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.emoji2.text.flatbuffer.MetadataItem;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TypefaceEmojiRasterizer {
    private static final ThreadLocal sMetadataItem = new ThreadLocal();
    private volatile int mCache = 0;
    private final int mIndex;
    private final MetadataRepo mMetadataRepo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TypefaceEmojiRasterizer(MetadataRepo metadataRepo, int i) {
        this.mMetadataRepo = metadataRepo;
        this.mIndex = i;
    }

    private MetadataItem getMetadataItem() {
        ThreadLocal threadLocal = sMetadataItem;
        MetadataItem metadataItem = (MetadataItem) threadLocal.get();
        if (metadataItem == null) {
            metadataItem = new MetadataItem();
            threadLocal.set(metadataItem);
        }
        this.mMetadataRepo.getMetadataList().list(metadataItem, this.mIndex);
        return metadataItem;
    }

    public final void draw(Canvas canvas, float f, float f2, Paint paint) {
        MetadataRepo metadataRepo = this.mMetadataRepo;
        Typeface typeface = metadataRepo.getTypeface();
        Typeface typeface2 = paint.getTypeface();
        paint.setTypeface(typeface);
        canvas.drawText(metadataRepo.getEmojiCharArray(), this.mIndex * 2, 2, f, f2, paint);
        paint.setTypeface(typeface2);
    }

    public final int getCodepointAt(int i) {
        return getMetadataItem().codepoints(i);
    }

    public final int getCodepointsLength() {
        return getMetadataItem().codepointsLength();
    }

    public final int getHasGlyph() {
        return this.mCache & 3;
    }

    public final int getHeight() {
        return getMetadataItem().height();
    }

    public final int getId() {
        return getMetadataItem().id();
    }

    public final short getSdkAdded() {
        return getMetadataItem().sdkAdded();
    }

    public final int getWidth() {
        return getMetadataItem().width();
    }

    public final boolean isDefaultEmoji() {
        return getMetadataItem().emojiStyle();
    }

    public final boolean isPreferredSystemRender() {
        if ((this.mCache & 4) > 0) {
            return true;
        }
        return false;
    }

    public final void setExclusion() {
        this.mCache = (this.mCache & 3) | 4;
    }

    public final void setHasGlyph(boolean z) {
        int i;
        int i2 = this.mCache & 4;
        if (z) {
            i = i2 | 2;
        } else {
            i = i2 | 1;
        }
        this.mCache = i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        sb.append(Integer.toHexString(getId()));
        sb.append(", codepoints:");
        int codepointsLength = getCodepointsLength();
        for (int i = 0; i < codepointsLength; i++) {
            sb.append(Integer.toHexString(getCodepointAt(i)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
