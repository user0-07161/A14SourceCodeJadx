package androidx.compose.animation.core;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class VisibilityThresholdsKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Rect rectVisibilityThreshold;
    private static final Map visibilityThresholdMap;

    static {
        TwoWayConverter twoWayConverter;
        TwoWayConverter twoWayConverter2;
        TwoWayConverter twoWayConverter3;
        TwoWayConverter twoWayConverter4;
        Float valueOf = Float.valueOf(0.5f);
        rectVisibilityThreshold = new Rect(0.5f, 0.5f, 0.5f, 0.5f);
        twoWayConverter = VectorConvertersKt.IntToVector;
        Float valueOf2 = Float.valueOf(1.0f);
        Pair pair = new Pair(twoWayConverter, valueOf2);
        twoWayConverter2 = VectorConvertersKt.IntSizeToVector;
        Pair pair2 = new Pair(twoWayConverter2, valueOf2);
        IntOffset.Companion companion = IntOffset.Companion;
        Pair pair3 = new Pair(VectorConvertersKt.getVectorConverter$7(), valueOf2);
        Pair pair4 = new Pair(VectorConvertersKt.getVectorConverter(), Float.valueOf(0.01f));
        twoWayConverter3 = VectorConvertersKt.RectToVector;
        Pair pair5 = new Pair(twoWayConverter3, valueOf);
        Size.Companion companion2 = Size.Companion;
        Pair pair6 = new Pair(VectorConvertersKt.getVectorConverter$5(), valueOf);
        Offset.Companion companion3 = Offset.Companion;
        Pair pair7 = new Pair(VectorConvertersKt.getVectorConverter$6(), valueOf);
        twoWayConverter4 = VectorConvertersKt.DpToVector;
        Float valueOf3 = Float.valueOf(0.1f);
        Pair pair8 = new Pair(twoWayConverter4, valueOf3);
        DpOffset.Companion companion4 = DpOffset.Companion;
        visibilityThresholdMap = MapsKt.mapOf(pair, pair2, pair3, pair4, pair5, pair6, pair7, pair8, new Pair(VectorConvertersKt.getVectorConverter$4(), valueOf3));
    }

    public static final Map getVisibilityThresholdMap() {
        return visibilityThresholdMap;
    }
}
