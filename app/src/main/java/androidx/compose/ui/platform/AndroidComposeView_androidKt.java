package androidx.compose.ui.platform;

import android.content.res.Configuration;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AndroidComposeView_androidKt {
    private static Function1 textInputServiceFactory = AndroidComposeView_androidKt$textInputServiceFactory$1.INSTANCE;

    public static final LayoutDirection access$layoutDirectionFromInt(int i) {
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        if (i != 0 && i == 1) {
            return LayoutDirection.Rtl;
        }
        return layoutDirection;
    }

    public static final LayoutDirection getLocaleLayoutDirection(Configuration configuration) {
        int layoutDirection = configuration.getLayoutDirection();
        LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
        if (layoutDirection != 0 && layoutDirection == 1) {
            return LayoutDirection.Rtl;
        }
        return layoutDirection2;
    }

    public static final Function1 getTextInputServiceFactory() {
        return textInputServiceFactory;
    }
}
