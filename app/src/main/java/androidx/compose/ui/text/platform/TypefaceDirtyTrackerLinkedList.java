package androidx.compose.ui.text.platform;

import android.graphics.Typeface;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.font.TypefaceResult;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TypefaceDirtyTrackerLinkedList {
    private final Object initial;
    private final TypefaceDirtyTrackerLinkedList next;
    private final State resolveResult;

    public TypefaceDirtyTrackerLinkedList(TypefaceResult resolveResult, TypefaceDirtyTrackerLinkedList typefaceDirtyTrackerLinkedList) {
        Intrinsics.checkNotNullParameter(resolveResult, "resolveResult");
        this.resolveResult = resolveResult;
        this.next = typefaceDirtyTrackerLinkedList;
        this.initial = ((TypefaceResult.Immutable) resolveResult).getValue();
    }

    public final Typeface getTypeface() {
        Object obj = this.initial;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.graphics.Typeface");
        return (Typeface) obj;
    }

    public final boolean isStaleResolvedFont() {
        TypefaceDirtyTrackerLinkedList typefaceDirtyTrackerLinkedList;
        if (this.resolveResult.getValue() == this.initial && ((typefaceDirtyTrackerLinkedList = this.next) == null || !typefaceDirtyTrackerLinkedList.isStaleResolvedFont())) {
            return false;
        }
        return true;
    }
}
