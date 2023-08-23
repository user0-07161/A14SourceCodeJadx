package androidx.window.core;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ValidSpecification extends SpecificationComputer {
    private final Logger logger;
    private final String tag = "SidecarAdapter";
    private final Object value;
    private final VerificationMode verificationMode;

    public ValidSpecification(Object obj, VerificationMode verificationMode, Logger logger) {
        this.value = obj;
        this.verificationMode = verificationMode;
        this.logger = logger;
    }

    @Override // androidx.window.core.SpecificationComputer
    public final Object compute() {
        return this.value;
    }

    @Override // androidx.window.core.SpecificationComputer
    public final SpecificationComputer require(String str, Function1 condition) {
        Intrinsics.checkNotNullParameter(condition, "condition");
        if (!((Boolean) condition.invoke(this.value)).booleanValue()) {
            return new FailedSpecification(this.value, this.tag, str, this.logger, this.verificationMode);
        }
        return this;
    }
}
