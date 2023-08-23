package androidx.compose.runtime;

import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ComposableSingletons$CompositionKt {

    /* renamed from: lambda-1  reason: not valid java name */
    public static ComposableLambdaImpl f0lambda1 = ComposableLambdaKt.composableLambdaInstance(954879418, new Function2() { // from class: androidx.compose.runtime.ComposableSingletons$CompositionKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Composer composer = (Composer) obj;
            if ((((Number) obj2).intValue() & 11) == 2) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            int i = ComposerKt.$r8$clinit;
            return Unit.INSTANCE;
        }
    }, false);

    /* renamed from: lambda-2  reason: not valid java name */
    public static ComposableLambdaImpl f1lambda2 = ComposableLambdaKt.composableLambdaInstance(1918065384, new Function2() { // from class: androidx.compose.runtime.ComposableSingletons$CompositionKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Composer composer = (Composer) obj;
            if ((((Number) obj2).intValue() & 11) == 2) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            int i = ComposerKt.$r8$clinit;
            return Unit.INSTANCE;
        }
    }, false);
}
