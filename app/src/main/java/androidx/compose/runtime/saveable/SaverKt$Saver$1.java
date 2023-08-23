package androidx.compose.runtime.saveable;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SaverKt$Saver$1 {
    final /* synthetic */ Function1 $restore;
    final /* synthetic */ Function2 $save;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SaverKt$Saver$1(Function2 function2, Function1 function1) {
        this.$save = function2;
        this.$restore = function1;
    }

    public final Object restore(Object obj) {
        return this.$restore.invoke(obj);
    }

    public final Object save(SaverScope saverScope, Object obj) {
        Intrinsics.checkNotNullParameter(saverScope, "<this>");
        return this.$save.invoke(saverScope, obj);
    }
}
