package kotlin.collections.builders;

import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SerializedCollection implements Externalizable {
    private static final long serialVersionUID = 0;
    private Collection collection;
    private final int tag;

    public SerializedCollection(int i, Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        this.collection = collection;
        this.tag = i;
    }

    private final Object readResolve() {
        return this.collection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput input) {
        ListBuilder listBuilder;
        Intrinsics.checkNotNullParameter(input, "input");
        byte readByte = input.readByte();
        int i = readByte & 1;
        if ((readByte & (-2)) == 0) {
            int readInt = input.readInt();
            if (readInt >= 0) {
                int i2 = 0;
                if (i != 0) {
                    if (i == 1) {
                        SetBuilder setBuilder = new SetBuilder(readInt);
                        while (i2 < readInt) {
                            setBuilder.add(input.readObject());
                            i2++;
                        }
                        setBuilder.build$2();
                        listBuilder = setBuilder;
                    } else {
                        throw new InvalidObjectException("Unsupported collection type tag: " + i + '.');
                    }
                } else {
                    ListBuilder listBuilder2 = new ListBuilder(readInt);
                    while (i2 < readInt) {
                        listBuilder2.add(input.readObject());
                        i2++;
                    }
                    listBuilder2.build();
                    listBuilder = listBuilder2;
                }
                this.collection = listBuilder;
                return;
            }
            throw new InvalidObjectException("Illegal size value: " + readInt + '.');
        }
        throw new InvalidObjectException("Unsupported flags value: " + ((int) readByte) + '.');
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput output) {
        Intrinsics.checkNotNullParameter(output, "output");
        output.writeByte(this.tag);
        output.writeInt(this.collection.size());
        for (Object obj : this.collection) {
            output.writeObject(obj);
        }
    }
}
