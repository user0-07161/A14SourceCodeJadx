package androidx.compose.ui.text.font;

import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FontListFontFamilyTypefaceAdapter {
    private static final FontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1 DropExceptionHandler = new FontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key);
    private ContextScope asyncLoadScope;

    public FontListFontFamilyTypefaceAdapter(AsyncTypefaceCache asyncTypefaceCache) {
        EmptyCoroutineContext injectedContext = EmptyCoroutineContext.INSTANCE;
        Intrinsics.checkNotNullParameter(asyncTypefaceCache, "asyncTypefaceCache");
        Intrinsics.checkNotNullParameter(injectedContext, "injectedContext");
        FontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1 fontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1 = DropExceptionHandler;
        fontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1.getClass();
        CoroutineContext plus = CoroutineContext.DefaultImpls.plus(fontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1, injectedContext);
        Job.Key key = Job.Key;
        this.asyncLoadScope = CoroutineScopeKt.CoroutineScope(plus.plus(SupervisorKt.SupervisorJob(null)));
    }
}
