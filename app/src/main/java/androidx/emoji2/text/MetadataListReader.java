package androidx.emoji2.text;

import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
abstract class MetadataListReader {

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class ByteBufferReader {
        private final ByteBuffer mByteBuffer;

        ByteBufferReader(ByteBuffer byteBuffer) {
            this.mByteBuffer = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        public final long getPosition() {
            return this.mByteBuffer.position();
        }

        public final int readTag() {
            return this.mByteBuffer.getInt();
        }

        public final long readUnsignedInt() {
            return this.mByteBuffer.getInt() & 4294967295L;
        }

        public final int readUnsignedShort() {
            return this.mByteBuffer.getShort() & 65535;
        }

        public final void skip(int i) {
            ByteBuffer byteBuffer = this.mByteBuffer;
            byteBuffer.position(byteBuffer.position() + i);
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class OffsetInfo {
        private final long mStartOffset;

        OffsetInfo(long j) {
            this.mStartOffset = j;
        }

        final long getStartOffset() {
            return this.mStartOffset;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MetadataList read(ByteBuffer byteBuffer) {
        long j;
        ByteBuffer duplicate = byteBuffer.duplicate();
        ByteBufferReader byteBufferReader = new ByteBufferReader(duplicate);
        byteBufferReader.skip(4);
        int readUnsignedShort = byteBufferReader.readUnsignedShort();
        if (readUnsignedShort <= 100) {
            byteBufferReader.skip(6);
            int i = 0;
            while (true) {
                if (i < readUnsignedShort) {
                    int readTag = byteBufferReader.readTag();
                    byteBufferReader.skip(4);
                    j = byteBufferReader.readUnsignedInt();
                    byteBufferReader.skip(4);
                    if (1835365473 == readTag) {
                        break;
                    }
                    i++;
                } else {
                    j = -1;
                    break;
                }
            }
            if (j != -1) {
                byteBufferReader.skip((int) (j - byteBufferReader.getPosition()));
                byteBufferReader.skip(12);
                long readUnsignedInt = byteBufferReader.readUnsignedInt();
                for (int i2 = 0; i2 < readUnsignedInt; i2++) {
                    int readTag2 = byteBufferReader.readTag();
                    long readUnsignedInt2 = byteBufferReader.readUnsignedInt();
                    byteBufferReader.readUnsignedInt();
                    if (1164798569 == readTag2 || 1701669481 == readTag2) {
                        duplicate.position((int) new OffsetInfo(readUnsignedInt2 + j).getStartOffset());
                        return MetadataList.getRootAsMetadataList(duplicate);
                    }
                }
            }
            throw new IOException("Cannot read metadata.");
        }
        throw new IOException("Cannot read metadata.");
    }
}
