package androidx.compose.ui.text.android;

import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.core.os.BuildCompat;
import java.text.BreakIterator;
import java.util.Iterator;
import java.util.PriorityQueue;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LayoutIntrinsics {
    private final Lazy boringMetrics$delegate;
    private final Lazy maxIntrinsicWidth$delegate;
    private final Lazy minIntrinsicWidth$delegate;

    public LayoutIntrinsics(final int i, final AndroidTextPaint textPaint, final CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "charSequence");
        Intrinsics.checkNotNullParameter(textPaint, "textPaint");
        this.boringMetrics$delegate = LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.text.android.LayoutIntrinsics$boringMetrics$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TextDirectionHeuristic textDirectionHeuristic = TextLayoutKt.getTextDirectionHeuristic(i);
                CharSequence text = charSequence;
                TextPaint paint = textPaint;
                Intrinsics.checkNotNullParameter(text, "text");
                Intrinsics.checkNotNullParameter(paint, "paint");
                int i2 = BuildCompat.$r8$clinit;
                return BoringLayout.isBoring(text, paint, textDirectionHeuristic, true, null);
            }
        });
        this.minIntrinsicWidth$delegate = LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.text.android.LayoutIntrinsics$minIntrinsicWidth$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence text = charSequence;
                TextPaint paint = textPaint;
                Intrinsics.checkNotNullParameter(text, "text");
                Intrinsics.checkNotNullParameter(paint, "paint");
                BreakIterator lineInstance = BreakIterator.getLineInstance(paint.getTextLocale());
                lineInstance.setText(new CharSequenceCharacterIterator(text, text.length()));
                PriorityQueue priorityQueue = new PriorityQueue(10, new LayoutIntrinsicsKt$$ExternalSyntheticLambda0());
                int i2 = 0;
                for (int next = lineInstance.next(); next != -1; next = lineInstance.next()) {
                    if (priorityQueue.size() < 10) {
                        priorityQueue.add(new Pair(Integer.valueOf(i2), Integer.valueOf(next)));
                    } else {
                        Pair pair = (Pair) priorityQueue.peek();
                        if (pair != null && ((Number) pair.getSecond()).intValue() - ((Number) pair.getFirst()).intValue() < next - i2) {
                            priorityQueue.poll();
                            priorityQueue.add(new Pair(Integer.valueOf(i2), Integer.valueOf(next)));
                        }
                    }
                    i2 = next;
                }
                Iterator it = priorityQueue.iterator();
                float f = 0.0f;
                while (it.hasNext()) {
                    Pair pair2 = (Pair) it.next();
                    f = Math.max(f, Layout.getDesiredWidth(text, ((Number) pair2.component1()).intValue(), ((Number) pair2.component2()).intValue(), paint));
                }
                return Float.valueOf(f);
            }
        });
        this.maxIntrinsicWidth$delegate = LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.text.android.LayoutIntrinsics$maxIntrinsicWidth$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Code restructure failed: missing block: B:19:0x0051, code lost:
                if (androidx.compose.ui.text.android.SpannedExtensionsKt.hasSpan(r3, androidx.compose.ui.text.android.style.LetterSpacingSpanEm.class) == false) goto L18;
             */
            /* JADX WARN: Code restructure failed: missing block: B:24:0x005e, code lost:
                if (r6 == false) goto L17;
             */
            @Override // kotlin.jvm.functions.Function0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invoke() {
                /*
                    r6 = this;
                    androidx.compose.ui.text.android.LayoutIntrinsics r0 = androidx.compose.ui.text.android.LayoutIntrinsics.this
                    android.text.BoringLayout$Metrics r0 = r0.getBoringMetrics()
                    if (r0 == 0) goto L10
                    int r0 = r0.width
                    float r0 = (float) r0
                    java.lang.Float r0 = java.lang.Float.valueOf(r0)
                    goto L11
                L10:
                    r0 = 0
                L11:
                    r1 = 0
                    if (r0 != 0) goto L2a
                    java.lang.CharSequence r0 = r2
                    int r2 = r0.length()
                    android.text.TextPaint r3 = r3
                    float r0 = android.text.Layout.getDesiredWidth(r0, r1, r2, r3)
                    double r2 = (double) r0
                    double r2 = java.lang.Math.ceil(r2)
                    float r0 = (float) r2
                    java.lang.Float r0 = java.lang.Float.valueOf(r0)
                L2a:
                    float r2 = r0.floatValue()
                    java.lang.CharSequence r3 = r2
                    android.text.TextPaint r6 = r3
                    r4 = 0
                    int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                    r5 = 1
                    if (r2 != 0) goto L3a
                    r2 = r5
                    goto L3b
                L3a:
                    r2 = r1
                L3b:
                    if (r2 != 0) goto L61
                    boolean r2 = r3 instanceof android.text.Spanned
                    if (r2 == 0) goto L53
                    android.text.Spanned r3 = (android.text.Spanned) r3
                    java.lang.Class<androidx.compose.ui.text.android.style.LetterSpacingSpanPx> r2 = androidx.compose.ui.text.android.style.LetterSpacingSpanPx.class
                    boolean r2 = androidx.compose.ui.text.android.SpannedExtensionsKt.hasSpan(r3, r2)
                    if (r2 != 0) goto L60
                    java.lang.Class<androidx.compose.ui.text.android.style.LetterSpacingSpanEm> r2 = androidx.compose.ui.text.android.style.LetterSpacingSpanEm.class
                    boolean r2 = androidx.compose.ui.text.android.SpannedExtensionsKt.hasSpan(r3, r2)
                    if (r2 != 0) goto L60
                L53:
                    float r6 = r6.getLetterSpacing()
                    int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
                    if (r6 != 0) goto L5d
                    r6 = r5
                    goto L5e
                L5d:
                    r6 = r1
                L5e:
                    if (r6 != 0) goto L61
                L60:
                    r1 = r5
                L61:
                    if (r1 == 0) goto L6e
                    float r6 = r0.floatValue()
                    r0 = 1056964608(0x3f000000, float:0.5)
                    float r6 = r6 + r0
                    java.lang.Float r0 = java.lang.Float.valueOf(r6)
                L6e:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.LayoutIntrinsics$maxIntrinsicWidth$2.invoke():java.lang.Object");
            }
        });
    }

    public final BoringLayout.Metrics getBoringMetrics() {
        return (BoringLayout.Metrics) this.boringMetrics$delegate.getValue();
    }

    public final float getMaxIntrinsicWidth() {
        return ((Number) this.maxIntrinsicWidth$delegate.getValue()).floatValue();
    }

    public final float getMinIntrinsicWidth() {
        return ((Number) this.minIntrinsicWidth$delegate.getValue()).floatValue();
    }
}
