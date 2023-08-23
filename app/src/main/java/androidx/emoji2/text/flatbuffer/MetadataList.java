package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MetadataList extends Table {
    public static MetadataList getRootAsMetadataList(ByteBuffer byteBuffer) {
        MetadataList metadataList = new MetadataList();
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        metadataList.__reset(byteBuffer.position() + byteBuffer.getInt(byteBuffer.position()), byteBuffer);
        return metadataList;
    }

    public final void list(MetadataItem metadataItem, int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            int i2 = __offset + this.bb_pos;
            int i3 = (i * 4) + this.bb.getInt(i2) + i2 + 4;
            metadataItem.__reset(this.bb.getInt(i3) + i3, this.bb);
        }
    }

    public final int listLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            int i = __offset + this.bb_pos;
            return this.bb.getInt(this.bb.getInt(i) + i);
        }
        return 0;
    }
}
