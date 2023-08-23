package androidx.compose.foundation.text;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextState {
    private final ParcelableSnapshotMutableState drawScopeInvalidation$delegate;
    private LayoutCoordinates layoutCoordinates;
    private final ParcelableSnapshotMutableState layoutInvalidation$delegate;
    private TextLayoutResult layoutResult;
    private long previousGlobalPosition;
    private TextDelegate textDelegate;
    private final long selectableId = 0;
    private Function1 onTextLayout = new Function1() { // from class: androidx.compose.foundation.text.TextState$onTextLayout$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            TextLayoutResult it = (TextLayoutResult) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return Unit.INSTANCE;
        }
    };

    public TextState(TextDelegate textDelegate) {
        long j;
        this.textDelegate = textDelegate;
        j = Offset.Zero;
        this.previousGlobalPosition = j;
        Color.Companion companion = Color.Companion;
        Unit unit = Unit.INSTANCE;
        this.drawScopeInvalidation$delegate = SnapshotStateKt.mutableStateOf(unit, SnapshotStateKt.neverEqualPolicy());
        this.layoutInvalidation$delegate = SnapshotStateKt.mutableStateOf(unit, SnapshotStateKt.neverEqualPolicy());
    }

    public final void getDrawScopeInvalidation() {
        this.drawScopeInvalidation$delegate.getValue();
    }

    public final void getLayoutInvalidation() {
        this.layoutInvalidation$delegate.getValue();
    }

    public final TextLayoutResult getLayoutResult() {
        return this.layoutResult;
    }

    public final Function1 getOnTextLayout() {
        return this.onTextLayout;
    }

    public final TextDelegate getTextDelegate() {
        return this.textDelegate;
    }

    public final void setLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this.layoutCoordinates = layoutCoordinates;
    }

    public final void setLayoutResult(TextLayoutResult textLayoutResult) {
        this.drawScopeInvalidation$delegate.setValue(Unit.INSTANCE);
        this.layoutResult = textLayoutResult;
    }

    public final void setOnTextLayout(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onTextLayout = function1;
    }

    public final void setTextDelegate(TextDelegate textDelegate) {
        this.layoutInvalidation$delegate.setValue(Unit.INSTANCE);
        this.textDelegate = textDelegate;
    }
}
