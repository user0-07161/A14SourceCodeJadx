package androidx.compose.ui.text.platform;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.emoji2.text.EmojiCompat;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DefaultImpl implements EmojiCompatStatusDelegate {
    private State loadState;

    public DefaultImpl() {
        State state;
        if (EmojiCompat.isConfigured()) {
            state = getFontLoadState();
        } else {
            state = null;
        }
        this.loadState = state;
    }

    public static final /* synthetic */ void access$setLoadState$p(DefaultImpl defaultImpl, State state) {
        defaultImpl.loadState = state;
    }

    private final State getFontLoadState() {
        EmojiCompat emojiCompat = EmojiCompat.get();
        Intrinsics.checkNotNullExpressionValue(emojiCompat, "get()");
        if (emojiCompat.getLoadState() == 1) {
            return new ImmutableBool(true);
        }
        ParcelableSnapshotMutableState mutableStateOf$default = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
        emojiCompat.registerInitCallback(new DefaultImpl$getFontLoadState$initCallback$1(mutableStateOf$default, this));
        return mutableStateOf$default;
    }

    public final State getFontLoaded() {
        ImmutableBool immutableBool;
        State state = this.loadState;
        if (state != null) {
            Intrinsics.checkNotNull(state);
            return state;
        } else if (!EmojiCompat.isConfigured()) {
            immutableBool = EmojiCompatStatusKt.Falsey;
            return immutableBool;
        } else {
            State fontLoadState = getFontLoadState();
            this.loadState = fontLoadState;
            return fontLoadState;
        }
    }
}
