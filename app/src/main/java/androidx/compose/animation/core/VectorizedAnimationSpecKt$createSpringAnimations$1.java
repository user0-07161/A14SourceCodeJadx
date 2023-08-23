package androidx.compose.animation.core;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class VectorizedAnimationSpecKt$createSpringAnimations$1 implements Animations {
    public final /* synthetic */ int $r8$classId = 1;
    private final Object anims;

    public VectorizedAnimationSpecKt$createSpringAnimations$1(float f, float f2, AnimationVector animationVector) {
        IntRange until = RangesKt.until(0, animationVector.getSize$animation_core_release());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until));
        IntProgressionIterator it = until.iterator();
        while (it.hasNext()) {
            arrayList.add(new FloatSpringSpec(f, f2, animationVector.get$animation_core_release(it.nextInt())));
        }
        this.anims = arrayList;
    }

    @Override // androidx.compose.animation.core.Animations
    public final FloatAnimationSpec get(int i) {
        Object obj = this.anims;
        int i2 = this.$r8$classId;
        switch (i2) {
            case 0:
                switch (i2) {
                    case 0:
                        return (FloatSpringSpec) ((List) obj).get(i);
                    default:
                        return (FloatSpringSpec) obj;
                }
            default:
                switch (i2) {
                    case 0:
                        return (FloatSpringSpec) ((List) obj).get(i);
                    default:
                        return (FloatSpringSpec) obj;
                }
        }
    }

    public VectorizedAnimationSpecKt$createSpringAnimations$1(float f, float f2) {
        this.anims = new FloatSpringSpec(f, f2, 0.01f);
    }
}
