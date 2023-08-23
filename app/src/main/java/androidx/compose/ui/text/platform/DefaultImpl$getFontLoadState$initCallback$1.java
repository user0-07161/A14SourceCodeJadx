package androidx.compose.ui.text.platform;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DefaultImpl$getFontLoadState$initCallback$1 {
    final /* synthetic */ MutableState $mutableLoaded;
    final /* synthetic */ DefaultImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultImpl$getFontLoadState$initCallback$1(ParcelableSnapshotMutableState parcelableSnapshotMutableState, DefaultImpl defaultImpl) {
        this.$mutableLoaded = parcelableSnapshotMutableState;
        this.this$0 = defaultImpl;
    }

    public final void onFailed() {
        ImmutableBool immutableBool;
        immutableBool = EmojiCompatStatusKt.Falsey;
        DefaultImpl.access$setLoadState$p(this.this$0, immutableBool);
    }

    public final void onInitialized() {
        ((SnapshotMutableStateImpl) this.$mutableLoaded).setValue(Boolean.TRUE);
        DefaultImpl.access$setLoadState$p(this.this$0, new ImmutableBool(true));
    }
}
