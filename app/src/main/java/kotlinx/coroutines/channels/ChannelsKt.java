package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ChannelsKt {
    public static final void cancelConsumed(ReceiveChannel receiveChannel, Throwable th) {
        Intrinsics.checkNotNullParameter(receiveChannel, "<this>");
        CancellationException cancellationException = null;
        if (th != null) {
            if (th instanceof CancellationException) {
                cancellationException = (CancellationException) th;
            }
            if (cancellationException == null) {
                cancellationException = new CancellationException("Channel was consumed, consumer had failed");
                cancellationException.initCause(th);
            }
        }
        receiveChannel.cancel(cancellationException);
    }
}
