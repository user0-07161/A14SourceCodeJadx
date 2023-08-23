package androidx.core.view;

import android.view.View;
import androidx.compose.ui.platform.AbstractComposeView;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ViewKt {
    public static final SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 getAllViews(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return new SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(new ViewKt$allViews$1(view, null));
    }

    public static final Sequence getAncestors(AbstractComposeView abstractComposeView) {
        return SequencesKt.generateSequence(abstractComposeView.getParent(), ViewKt$ancestors$1.INSTANCE);
    }
}
