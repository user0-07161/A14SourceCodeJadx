package androidx.compose.ui.graphics.colorspace;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Rgb$oetf$1 extends Lambda implements Function1 {
    final /* synthetic */ Rgb this$0;

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        float f;
        float f2;
        double invoke = this.this$0.getOetfOrig$ui_graphics_release().invoke(((Number) obj).doubleValue());
        f = this.this$0.min;
        f2 = this.this$0.max;
        return Double.valueOf(RangesKt.coerceIn(invoke, f, f2));
    }
}
