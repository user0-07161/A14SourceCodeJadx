package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MetadataItem extends Table {
    public final int codepoints(int i) {
        int __offset = __offset(16);
        if (__offset != 0) {
            ByteBuffer byteBuffer = this.bb;
            int i2 = __offset + this.bb_pos;
            return byteBuffer.getInt((i * 4) + byteBuffer.getInt(i2) + i2 + 4);
        }
        return 0;
    }

    public final int codepointsLength() {
        int __offset = __offset(16);
        if (__offset != 0) {
            int i = __offset + this.bb_pos;
            return this.bb.getInt(this.bb.getInt(i) + i);
        }
        return 0;
    }

    public final boolean emojiStyle() {
        int __offset = __offset(6);
        if (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) {
            return false;
        }
        return true;
    }

    public final short height() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.bb.getShort(__offset + this.bb_pos);
        }
        return (short) 0;
    }

    public final int id() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public final short sdkAdded() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getShort(__offset + this.bb_pos);
        }
        return (short) 0;
    }

    public final short width() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.getShort(__offset + this.bb_pos);
        }
        return (short) 0;
    }
}
