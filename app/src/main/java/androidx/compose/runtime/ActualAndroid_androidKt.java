package androidx.compose.runtime;

import android.os.Looper;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ActualAndroid_androidKt {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        LazyKt.lazy$1(new Function0() { // from class: androidx.compose.runtime.ActualAndroid_androidKt$DefaultMonotonicFrameClock$2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (Looper.getMainLooper() != null) {
                    return DefaultChoreographerFrameClock.INSTANCE;
                }
                return SdkStubsFallbackFrameClock.INSTANCE;
            }
        });
    }
}
