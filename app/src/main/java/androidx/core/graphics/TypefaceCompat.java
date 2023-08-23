package androidx.core.graphics;

import android.content.Context;
import android.graphics.Typeface;
import androidx.collection.LruCache;
import androidx.core.provider.FontsContractCompat;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TypefaceCompat {
    private static final TypefaceCompatApi29Impl sTypefaceCompatImpl = new TypefaceCompatApi29Impl();

    static {
        new LruCache();
    }

    public static Typeface createFromFontInfo(Context context, FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        return sTypefaceCompatImpl.createFromFontInfo(context, fontInfoArr, i);
    }
}
