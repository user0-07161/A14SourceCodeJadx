package androidx.compose.ui.focus;

import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FocusPropertiesImpl implements FocusProperties {
    private FocusRequester down;
    private FocusRequester end;
    private Function1 enter;
    private Function1 exit;
    private FocusRequester left;
    private FocusRequester next;
    private FocusRequester previous;
    private FocusRequester right;
    private FocusRequester start;
    private FocusRequester up;

    public FocusPropertiesImpl() {
        FocusRequester focusRequester;
        FocusRequester focusRequester2;
        FocusRequester focusRequester3;
        FocusRequester focusRequester4;
        FocusRequester focusRequester5;
        FocusRequester focusRequester6;
        FocusRequester focusRequester7;
        FocusRequester focusRequester8;
        focusRequester = FocusRequester.Default;
        this.next = focusRequester;
        focusRequester2 = FocusRequester.Default;
        this.previous = focusRequester2;
        focusRequester3 = FocusRequester.Default;
        this.up = focusRequester3;
        focusRequester4 = FocusRequester.Default;
        this.down = focusRequester4;
        focusRequester5 = FocusRequester.Default;
        this.left = focusRequester5;
        focusRequester6 = FocusRequester.Default;
        this.right = focusRequester6;
        focusRequester7 = FocusRequester.Default;
        this.start = focusRequester7;
        focusRequester8 = FocusRequester.Default;
        this.end = focusRequester8;
        this.enter = FocusPropertiesImpl$enter$1.INSTANCE;
        this.exit = FocusPropertiesImpl$exit$1.INSTANCE;
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public final boolean getCanFocus() {
        return true;
    }

    public final FocusRequester getDown() {
        return this.down;
    }

    public final FocusRequester getEnd() {
        return this.end;
    }

    public final Function1 getEnter() {
        return this.enter;
    }

    public final Function1 getExit() {
        return this.exit;
    }

    public final FocusRequester getLeft() {
        return this.left;
    }

    public final FocusRequester getNext() {
        return this.next;
    }

    public final FocusRequester getPrevious() {
        return this.previous;
    }

    public final FocusRequester getRight() {
        return this.right;
    }

    public final FocusRequester getStart() {
        return this.start;
    }

    public final FocusRequester getUp() {
        return this.up;
    }
}
