package androidx.compose.ui.text.font;

import androidx.compose.ui.text.caches.LruCache;
import androidx.compose.ui.text.font.TypefaceResult;
import androidx.compose.ui.text.platform.SynchronizedObject;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TypefaceRequestCache {
    private final SynchronizedObject lock = new SynchronizedObject();
    private final LruCache resultCache = new LruCache();

    public final SynchronizedObject getLock$ui_text_release() {
        return this.lock;
    }

    public final TypefaceResult runCached(final TypefaceRequest typefaceRequest, Function1 function1) {
        synchronized (this.lock) {
            TypefaceResult typefaceResult = (TypefaceResult) this.resultCache.get(typefaceRequest);
            if (typefaceResult != null) {
                if (((TypefaceResult.Immutable) typefaceResult).getCacheable()) {
                    return typefaceResult;
                }
                TypefaceResult typefaceResult2 = (TypefaceResult) this.resultCache.remove(typefaceRequest);
            }
            try {
                TypefaceResult typefaceResult3 = (TypefaceResult) ((FontFamilyResolverImpl$resolve$result$1) function1).invoke(new Function1() { // from class: androidx.compose.ui.text.font.TypefaceRequestCache$runCached$currentTypefaceResult$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LruCache lruCache;
                        LruCache lruCache2;
                        TypefaceResult finalResult = (TypefaceResult) obj;
                        Intrinsics.checkNotNullParameter(finalResult, "finalResult");
                        SynchronizedObject lock$ui_text_release = TypefaceRequestCache.this.getLock$ui_text_release();
                        TypefaceRequestCache typefaceRequestCache = TypefaceRequestCache.this;
                        TypefaceRequest typefaceRequest2 = typefaceRequest;
                        synchronized (lock$ui_text_release) {
                            if (finalResult.getCacheable()) {
                                lruCache2 = typefaceRequestCache.resultCache;
                                lruCache2.put(typefaceRequest2, finalResult);
                            } else {
                                lruCache = typefaceRequestCache.resultCache;
                                lruCache.remove(typefaceRequest2);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                });
                synchronized (this.lock) {
                    if (this.resultCache.get(typefaceRequest) == null) {
                        TypefaceResult.Immutable immutable = (TypefaceResult.Immutable) typefaceResult3;
                        if (immutable.getCacheable()) {
                            this.resultCache.put(typefaceRequest, immutable);
                        }
                    }
                }
                return typefaceResult3;
            } catch (Exception e) {
                throw new IllegalStateException("Could not load font", e);
            }
        }
    }
}
