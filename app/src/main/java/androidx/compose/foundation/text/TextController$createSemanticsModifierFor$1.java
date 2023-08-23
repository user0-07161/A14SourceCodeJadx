package androidx.compose.foundation.text;

import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class TextController$createSemanticsModifierFor$1 extends Lambda implements Function1 {
    final /* synthetic */ AnnotatedString $text;
    final /* synthetic */ TextController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextController$createSemanticsModifierFor$1(AnnotatedString annotatedString, TextController textController) {
        super(1);
        this.$text = annotatedString;
        this.this$0 = textController;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        SemanticsPropertyReceiver semantics = (SemanticsPropertyReceiver) obj;
        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
        AnnotatedString value = this.$text;
        int i = SemanticsPropertiesKt.$r8$clinit;
        Intrinsics.checkNotNullParameter(value, "value");
        SemanticsConfiguration semanticsConfiguration = (SemanticsConfiguration) semantics;
        semanticsConfiguration.set(SemanticsProperties.getText(), CollectionsKt.listOf(value));
        final TextController textController = this.this$0;
        semanticsConfiguration.set(SemanticsActions.getGetTextLayoutResult(), new AccessibilityAction(null, new Function1() { // from class: androidx.compose.foundation.text.TextController$createSemanticsModifierFor$1.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                boolean z;
                List it = (List) obj2;
                Intrinsics.checkNotNullParameter(it, "it");
                if (TextController.this.getState().getLayoutResult() != null) {
                    TextLayoutResult layoutResult = TextController.this.getState().getLayoutResult();
                    Intrinsics.checkNotNull(layoutResult);
                    it.add(layoutResult);
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }));
        return Unit.INSTANCE;
    }
}
