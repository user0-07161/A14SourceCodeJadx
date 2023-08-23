package kotlinx.coroutines.channels;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ChannelKt {
    public static AbstractChannel Channel$default(int i, BufferOverflow onBufferOverflow, int i2) {
        boolean z = false;
        if ((i2 & 1) != 0) {
            i = 0;
        }
        int i3 = i2 & 2;
        BufferOverflow bufferOverflow = BufferOverflow.SUSPEND;
        if (i3 != 0) {
            onBufferOverflow = bufferOverflow;
        }
        Intrinsics.checkNotNullParameter(onBufferOverflow, "onBufferOverflow");
        int i4 = 1;
        if (i != -2) {
            if (i != -1) {
                if (i != 0) {
                    if (i != Integer.MAX_VALUE) {
                        if (i == 1 && onBufferOverflow == BufferOverflow.DROP_OLDEST) {
                            return new ConflatedChannel(null);
                        }
                        return new ArrayChannel(i, onBufferOverflow, null);
                    }
                    return new LinkedListChannel(null);
                } else if (onBufferOverflow == bufferOverflow) {
                    return new RendezvousChannel(null);
                } else {
                    return new ArrayChannel(1, onBufferOverflow, null);
                }
            }
            if (onBufferOverflow == bufferOverflow) {
                z = true;
            }
            if (z) {
                return new ConflatedChannel(null);
            }
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
        }
        if (onBufferOverflow == bufferOverflow) {
            Channel.Factory.getClass();
            i4 = Channel.Factory.CHANNEL_DEFAULT_CAPACITY;
        }
        return new ArrayChannel(i4, onBufferOverflow, null);
    }
}
