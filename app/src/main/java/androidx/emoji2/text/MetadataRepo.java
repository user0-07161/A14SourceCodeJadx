package androidx.emoji2.text;

import android.graphics.Typeface;
import android.os.Trace;
import android.util.SparseArray;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.nio.ByteBuffer;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MetadataRepo {
    private final char[] mEmojiCharArray;
    private final MetadataList mMetadataList;
    private final Node mRootNode = new Node(1024);
    private final Typeface mTypeface;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Node {
        private final SparseArray mChildren;
        private TypefaceEmojiRasterizer mData;

        Node(int i) {
            this.mChildren = new SparseArray(i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Node get(int i) {
            SparseArray sparseArray = this.mChildren;
            if (sparseArray == null) {
                return null;
            }
            return (Node) sparseArray.get(i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final TypefaceEmojiRasterizer getData() {
            return this.mData;
        }

        final void put(TypefaceEmojiRasterizer typefaceEmojiRasterizer, int i, int i2) {
            Node node = get(typefaceEmojiRasterizer.getCodepointAt(i));
            if (node == null) {
                node = new Node(1);
                this.mChildren.put(typefaceEmojiRasterizer.getCodepointAt(i), node);
            }
            if (i2 > i) {
                node.put(typefaceEmojiRasterizer, i + 1, i2);
            } else {
                node.mData = typefaceEmojiRasterizer;
            }
        }
    }

    private MetadataRepo(Typeface typeface, MetadataList metadataList) {
        boolean z;
        this.mTypeface = typeface;
        this.mMetadataList = metadataList;
        this.mEmojiCharArray = new char[metadataList.listLength() * 2];
        int listLength = metadataList.listLength();
        for (int i = 0; i < listLength; i++) {
            TypefaceEmojiRasterizer typefaceEmojiRasterizer = new TypefaceEmojiRasterizer(this, i);
            Character.toChars(typefaceEmojiRasterizer.getId(), this.mEmojiCharArray, i * 2);
            if (typefaceEmojiRasterizer.getCodepointsLength() > 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument("invalid metadata codepoint length", z);
            this.mRootNode.put(typefaceEmojiRasterizer, 0, typefaceEmojiRasterizer.getCodepointsLength() - 1);
        }
    }

    public static MetadataRepo create(Typeface typeface, ByteBuffer byteBuffer) {
        try {
            Trace.beginSection("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(typeface, MetadataListReader.read(byteBuffer));
        } finally {
            Trace.endSection();
        }
    }

    public final char[] getEmojiCharArray() {
        return this.mEmojiCharArray;
    }

    public final MetadataList getMetadataList() {
        return this.mMetadataList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Node getRootNode() {
        return this.mRootNode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Typeface getTypeface() {
        return this.mTypeface;
    }
}
