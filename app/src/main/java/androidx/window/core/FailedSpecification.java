package androidx.window.core;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class FailedSpecification extends SpecificationComputer {
    private final WindowStrictModeException exception;
    private final Logger logger;
    private final String message;
    private final String tag;
    private final Object value;
    private final VerificationMode verificationMode;

    public FailedSpecification(Object value, String tag, String str, Logger logger, VerificationMode verificationMode) {
        boolean z;
        Collection collection;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(verificationMode, "verificationMode");
        this.value = value;
        this.tag = tag;
        this.message = str;
        this.logger = logger;
        this.verificationMode = verificationMode;
        WindowStrictModeException windowStrictModeException = new WindowStrictModeException(SpecificationComputer.createMessage(value, str));
        StackTraceElement[] stackTrace = windowStrictModeException.getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "stackTrace");
        int length = stackTrace.length - 2;
        length = length < 0 ? 0 : length;
        if (length >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (length == 0) {
                collection = EmptyList.INSTANCE;
            } else {
                int length2 = stackTrace.length;
                if (length >= length2) {
                    collection = ArraysKt.toList(stackTrace);
                } else if (length == 1) {
                    collection = CollectionsKt.listOf(stackTrace[length2 - 1]);
                } else {
                    ArrayList arrayList = new ArrayList(length);
                    for (int i = length2 - length; i < length2; i++) {
                        arrayList.add(stackTrace[i]);
                    }
                    collection = arrayList;
                }
            }
            windowStrictModeException.setStackTrace((StackTraceElement[]) collection.toArray(new StackTraceElement[0]));
            this.exception = windowStrictModeException;
            return;
        }
        throw new IllegalArgumentException(("Requested element count " + length + " is less than zero.").toString());
    }

    @Override // androidx.window.core.SpecificationComputer
    public final Object compute() {
        int ordinal = this.verificationMode.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                return null;
            }
            String message = SpecificationComputer.createMessage(this.value, this.message);
            ((AndroidLogger) this.logger).getClass();
            String tag = this.tag;
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(message, "message");
            Log.d(tag, message);
            return null;
        }
        throw this.exception;
    }

    @Override // androidx.window.core.SpecificationComputer
    public final SpecificationComputer require(String str, Function1 condition) {
        Intrinsics.checkNotNullParameter(condition, "condition");
        return this;
    }
}
