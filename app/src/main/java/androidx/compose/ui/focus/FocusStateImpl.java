package androidx.compose.ui.focus;

import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public enum FocusStateImpl {
    Active,
    ActiveParent,
    Captured,
    Inactive;

    public final boolean isFocused() {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal != 1) {
            if (ordinal == 2) {
                return true;
            }
            if (ordinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
        }
        return false;
    }
}
