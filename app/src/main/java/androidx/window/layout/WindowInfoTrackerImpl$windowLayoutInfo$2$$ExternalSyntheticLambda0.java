package androidx.window.layout;

import androidx.core.util.Consumer;
import kotlinx.coroutines.channels.ChannelCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final /* synthetic */ class WindowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ ProducerScope f$0;

    @Override // androidx.core.util.Consumer
    public final void accept(Object obj) {
        ((ChannelCoroutine) this.f$0).mo484trySendJP2dKIU((WindowLayoutInfo) obj);
    }
}
