package androidx.compose.animation.core;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class VectorConvertersKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final TwoWayConverter FloatToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$FloatToVector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new AnimationVector1D(((Number) obj).floatValue());
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$FloatToVector$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector1D it = (AnimationVector1D) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return Float.valueOf(it.getValue());
        }
    });
    private static final TwoWayConverter IntToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$IntToVector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new AnimationVector1D(((Number) obj).intValue());
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$IntToVector$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector1D it = (AnimationVector1D) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return Integer.valueOf((int) it.getValue());
        }
    });
    private static final TwoWayConverter DpToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$DpToVector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new AnimationVector1D(((Dp) obj).m392unboximpl());
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$DpToVector$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector1D it = (AnimationVector1D) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return Dp.m389boximpl(it.getValue());
        }
    });
    private static final TwoWayConverter DpOffsetToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$DpOffsetToVector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            long m398unboximpl = ((DpOffset) obj).m398unboximpl();
            return new AnimationVector2D(DpOffset.m396getXD9Ej5fM(m398unboximpl), DpOffset.m397getYD9Ej5fM(m398unboximpl));
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$DpOffsetToVector$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D it = (AnimationVector2D) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return DpOffset.m395boximpl(DpKt.m393DpOffsetYgX7TsA(it.getV1(), it.getV2()));
        }
    });
    private static final TwoWayConverter SizeToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$SizeToVector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            long m66unboximpl = ((Size) obj).m66unboximpl();
            return new AnimationVector2D(Size.m64getWidthimpl(m66unboximpl), Size.m63getHeightimpl(m66unboximpl));
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$SizeToVector$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D it = (AnimationVector2D) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return Size.m62boximpl(SizeKt.Size(it.getV1(), it.getV2()));
        }
    });
    private static final TwoWayConverter OffsetToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$OffsetToVector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            long m51unboximpl = ((Offset) obj).m51unboximpl();
            return new AnimationVector2D(Offset.m45getXimpl(m51unboximpl), Offset.m46getYimpl(m51unboximpl));
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$OffsetToVector$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D it = (AnimationVector2D) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return Offset.m42boximpl(OffsetKt.Offset(it.getV1(), it.getV2()));
        }
    });
    private static final TwoWayConverter IntOffsetToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$IntOffsetToVector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            long m403unboximpl = ((IntOffset) obj).m403unboximpl();
            return new AnimationVector2D((int) (m403unboximpl >> 32), IntOffset.m402getYimpl(m403unboximpl));
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$IntOffsetToVector$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D it = (AnimationVector2D) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return IntOffset.m401boximpl(IntOffsetKt.IntOffset(MathKt.roundToInt(it.getV1()), MathKt.roundToInt(it.getV2())));
        }
    });
    private static final TwoWayConverter IntSizeToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$IntSizeToVector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            long m407unboximpl = ((IntSize) obj).m407unboximpl();
            return new AnimationVector2D((int) (m407unboximpl >> 32), IntSize.m405getHeightimpl(m407unboximpl));
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$IntSizeToVector$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector2D it = (AnimationVector2D) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return IntSize.m404boximpl(IntSizeKt.IntSize(MathKt.roundToInt(it.getV1()), MathKt.roundToInt(it.getV2())));
        }
    });
    private static final TwoWayConverter RectToVector = TwoWayConverter(new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$RectToVector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Rect it = (Rect) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return new AnimationVector4D(it.getLeft(), it.getTop(), it.getRight(), it.getBottom());
        }
    }, new Function1() { // from class: androidx.compose.animation.core.VectorConvertersKt$RectToVector$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            AnimationVector4D it = (AnimationVector4D) obj;
            Intrinsics.checkNotNullParameter(it, "it");
            return new Rect(it.getV1(), it.getV2(), it.getV3(), it.getV4());
        }
    });

    public static final TwoWayConverter TwoWayConverter(Function1 convertToVector, Function1 convertFromVector) {
        Intrinsics.checkNotNullParameter(convertToVector, "convertToVector");
        Intrinsics.checkNotNullParameter(convertFromVector, "convertFromVector");
        return new TwoWayConverterImpl(convertToVector, convertFromVector);
    }

    public static final TwoWayConverter getVectorConverter() {
        return FloatToVector;
    }

    public static final TwoWayConverter getVectorConverter$4() {
        DpOffset.Companion companion = DpOffset.Companion;
        return DpOffsetToVector;
    }

    public static final TwoWayConverter getVectorConverter$5() {
        Size.Companion companion = Size.Companion;
        return SizeToVector;
    }

    public static final TwoWayConverter getVectorConverter$6() {
        Offset.Companion companion = Offset.Companion;
        return OffsetToVector;
    }

    public static final TwoWayConverter getVectorConverter$7() {
        IntOffset.Companion companion = IntOffset.Companion;
        return IntOffsetToVector;
    }
}
