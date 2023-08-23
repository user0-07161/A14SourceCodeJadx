package androidx.compose.ui.input.key;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class KeyEvent {
    private final android.view.KeyEvent nativeKeyEvent;

    private /* synthetic */ KeyEvent(android.view.KeyEvent keyEvent) {
        this.nativeKeyEvent = keyEvent;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ KeyEvent m174boximpl(android.view.KeyEvent keyEvent) {
        return new KeyEvent(keyEvent);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof KeyEvent)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.nativeKeyEvent, ((KeyEvent) obj).nativeKeyEvent)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.nativeKeyEvent.hashCode();
    }

    public final String toString() {
        return "KeyEvent(nativeKeyEvent=" + this.nativeKeyEvent + ')';
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ android.view.KeyEvent m175unboximpl() {
        return this.nativeKeyEvent;
    }
}
