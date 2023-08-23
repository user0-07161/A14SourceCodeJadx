package androidx.compose.ui.text.input;

import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.SaversKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextFieldValue {
    private final AnnotatedString annotatedString;
    private final TextRange composition;
    private final long selection;

    static {
        SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.input.TextFieldValue$Companion$Saver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SaverScope Saver = (SaverScope) obj;
                TextFieldValue it = (TextFieldValue) obj2;
                Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
                Intrinsics.checkNotNullParameter(it, "it");
                return CollectionsKt.arrayListOf(SaversKt.save(it.getAnnotatedString(), SaversKt.getAnnotatedStringSaver(), Saver), SaversKt.save(TextRange.m314boximpl(it.m340getSelectiond9O1mEE()), SaversKt.getSaver$5(), Saver));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.input.TextFieldValue$Companion$Saver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object it) {
                AnnotatedString annotatedString;
                TextRange textRange;
                Intrinsics.checkNotNullParameter(it, "it");
                List list = (List) it;
                Object obj = list.get(0);
                SaverKt$Saver$1 annotatedStringSaver = SaversKt.getAnnotatedStringSaver();
                Boolean bool = Boolean.FALSE;
                if (!Intrinsics.areEqual(obj, bool) && obj != null) {
                    annotatedString = (AnnotatedString) annotatedStringSaver.restore(obj);
                } else {
                    annotatedString = null;
                }
                Intrinsics.checkNotNull(annotatedString);
                Object obj2 = list.get(1);
                TextRange.Companion companion = TextRange.Companion;
                SaverKt$Saver$1 saver$5 = SaversKt.getSaver$5();
                if (!Intrinsics.areEqual(obj2, bool) && obj2 != null) {
                    textRange = (TextRange) saver$5.restore(obj2);
                } else {
                    textRange = null;
                }
                Intrinsics.checkNotNull(textRange);
                return new TextFieldValue(annotatedString, textRange.m317unboximpl(), null);
            }
        });
    }

    public TextFieldValue(AnnotatedString annotatedString, long j, TextRange textRange) {
        TextRange textRange2;
        this.annotatedString = annotatedString;
        this.selection = TextRangeKt.m318constrain8ffj60Q(j, getText().length());
        if (textRange != null) {
            textRange2 = TextRange.m314boximpl(TextRangeKt.m318constrain8ffj60Q(textRange.m317unboximpl(), getText().length()));
        } else {
            textRange2 = null;
        }
        this.composition = textRange2;
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextFieldValue)) {
            return false;
        }
        TextFieldValue textFieldValue = (TextFieldValue) obj;
        long j = textFieldValue.selection;
        TextRange.Companion companion = TextRange.Companion;
        if (this.selection == j) {
            z = true;
        } else {
            z = false;
        }
        if (z && Intrinsics.areEqual(this.composition, textFieldValue.composition) && Intrinsics.areEqual(this.annotatedString, textFieldValue.annotatedString)) {
            return true;
        }
        return false;
    }

    public final AnnotatedString getAnnotatedString() {
        return this.annotatedString;
    }

    /* renamed from: getSelection-d9O1mEE  reason: not valid java name */
    public final long m340getSelectiond9O1mEE() {
        return this.selection;
    }

    public final String getText() {
        return this.annotatedString.getText();
    }

    public final int hashCode() {
        int i;
        TextRange.Companion companion = TextRange.Companion;
        int hashCode = (Long.hashCode(this.selection) + (this.annotatedString.hashCode() * 31)) * 31;
        TextRange textRange = this.composition;
        if (textRange != null) {
            i = Long.hashCode(textRange.m317unboximpl());
        } else {
            i = 0;
        }
        return hashCode + i;
    }

    public final String toString() {
        return "TextFieldValue(text='" + ((Object) this.annotatedString) + "', selection=" + ((Object) TextRange.m316toStringimpl(this.selection)) + ", composition=" + this.composition + ')';
    }
}
