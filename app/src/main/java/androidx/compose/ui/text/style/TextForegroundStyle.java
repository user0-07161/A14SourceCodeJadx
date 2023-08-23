package androidx.compose.ui.text.style;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt$ShaderBrush$1;
import androidx.compose.ui.graphics.Color;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface TextForegroundStyle {

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* renamed from: from-8_81llA  reason: not valid java name */
        public static TextForegroundStyle m372from8_81llA(long j) {
            long j2;
            boolean z;
            j2 = Color.Unspecified;
            if (j != j2) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return new ColorStyle(j);
            }
            return Unspecified.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Unspecified implements TextForegroundStyle {
        public static final Unspecified INSTANCE = new Unspecified();

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        public final float getAlpha() {
            return Float.NaN;
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        public final Brush getBrush() {
            return null;
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        /* renamed from: getColor-0d7_KjU */
        public final long mo352getColor0d7_KjU() {
            long j;
            Color.Companion companion = Color.Companion;
            j = Color.Unspecified;
            return j;
        }
    }

    float getAlpha();

    Brush getBrush();

    /* renamed from: getColor-0d7_KjU */
    long mo352getColor0d7_KjU();

    default TextForegroundStyle merge(TextForegroundStyle other) {
        Intrinsics.checkNotNullParameter(other, "other");
        boolean z = other instanceof BrushStyle;
        if (z && (this instanceof BrushStyle)) {
            BrushKt$ShaderBrush$1 value = ((BrushStyle) other).getValue();
            float alpha = other.getAlpha();
            Function0 function0 = new Function0() { // from class: androidx.compose.ui.text.style.TextForegroundStyle$merge$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Float.valueOf(TextForegroundStyle.this.getAlpha());
                }
            };
            if (Float.isNaN(alpha)) {
                alpha = ((Number) function0.invoke()).floatValue();
            }
            return new BrushStyle(value, alpha);
        }
        if (!z || (this instanceof BrushStyle)) {
            if (z || !(this instanceof BrushStyle)) {
                Function0 function02 = new Function0() { // from class: androidx.compose.ui.text.style.TextForegroundStyle$merge$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TextForegroundStyle.this;
                    }
                };
                if (Intrinsics.areEqual(other, Unspecified.INSTANCE)) {
                    return (TextForegroundStyle) function02.invoke();
                }
            } else {
                return this;
            }
        }
        return other;
    }
}
