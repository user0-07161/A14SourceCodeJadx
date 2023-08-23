package androidx.window.core;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SpecificationComputer {
    public static final Companion Companion = new Companion();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        public static SpecificationComputer startSpecification$default(Object obj, VerificationMode verificationMode) {
            AndroidLogger androidLogger = AndroidLogger.INSTANCE;
            Intrinsics.checkNotNullParameter(verificationMode, "verificationMode");
            return new ValidSpecification(obj, verificationMode, androidLogger);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String createMessage(Object value, String message) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(message, "message");
        return message + " value: " + value;
    }

    public abstract Object compute();

    public abstract SpecificationComputer require(String str, Function1 function1);
}
