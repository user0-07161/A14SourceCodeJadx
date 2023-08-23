package androidx.compose.ui.platform;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CompositionLocalsKt {
    private static final StaticProvidableCompositionLocal LocalAccessibilityManager = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalAccessibilityManager$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalAutofill = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalAutofill$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalAutofillTree = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalAutofillTree$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalAutofillTree");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalClipboardManager = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalClipboardManager$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalClipboardManager");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalDensity = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalDensity$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalDensity");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalFocusManager = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalFocusManager$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalFocusManager");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalFontLoader = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalFontLoader$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalFontLoader");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalFontFamilyResolver = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalFontFamilyResolver$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalFontFamilyResolver");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalHapticFeedback = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalHapticFeedback$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalHapticFeedback");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalInputModeManager = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalInputModeManager$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalInputManager");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalLayoutDirection = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalLayoutDirection$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalLayoutDirection");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalTextInputService = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalTextInputService$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalTextToolbar = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalTextToolbar$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalTextToolbar");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalUriHandler = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalUriHandler$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalUriHandler");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalViewConfiguration = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalViewConfiguration$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalViewConfiguration");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalWindowInfo = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalWindowInfo$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalWindowInfo");
            throw null;
        }
    });
    private static final StaticProvidableCompositionLocal LocalPointerIconService = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalPointerIconService$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });

    public static final void ProvideCommonCompositionLocals(final Owner owner, final UriHandler uriHandler, final Function2 content, Composer composer, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(uriHandler, "uriHandler");
        Intrinsics.checkNotNullParameter(content, "content");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(874662829);
        if ((i & 14) == 0) {
            if (composerImpl.changed(owner)) {
                i5 = 4;
            } else {
                i5 = 2;
            }
            i2 = i5 | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            if (composerImpl.changed(uriHandler)) {
                i4 = 32;
            } else {
                i4 = 16;
            }
            i2 |= i4;
        }
        if ((i & 896) == 0) {
            if (composerImpl.changedInstance(content)) {
                i3 = 256;
            } else {
                i3 = 128;
            }
            i2 |= i3;
        }
        if ((i2 & 731) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            int i6 = ComposerKt.$r8$clinit;
            AndroidComposeView androidComposeView = (AndroidComposeView) owner;
            ProvidedValue provides = LocalAccessibilityManager.provides(androidComposeView.getAccessibilityManager());
            ProvidedValue provides2 = LocalAutofill.provides(androidComposeView.getAutofill());
            ProvidedValue provides3 = LocalAutofillTree.provides(androidComposeView.getAutofillTree());
            ProvidedValue provides4 = LocalClipboardManager.provides(androidComposeView.getClipboardManager());
            ProvidedValue provides5 = LocalDensity.provides(androidComposeView.getDensity());
            ProvidedValue provides6 = LocalFocusManager.provides(androidComposeView.getFocusOwner());
            AndroidFontResourceLoader fontLoader = androidComposeView.getFontLoader();
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = LocalFontLoader;
            staticProvidableCompositionLocal.getClass();
            ProvidedValue providedValue = new ProvidedValue(staticProvidableCompositionLocal, fontLoader, false);
            FontFamilyResolverImpl fontFamilyResolver = androidComposeView.getFontFamilyResolver();
            StaticProvidableCompositionLocal staticProvidableCompositionLocal2 = LocalFontFamilyResolver;
            staticProvidableCompositionLocal2.getClass();
            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{provides, provides2, provides3, provides4, provides5, provides6, providedValue, new ProvidedValue(staticProvidableCompositionLocal2, fontFamilyResolver, false), LocalHapticFeedback.provides(androidComposeView.getHapticFeedBack()), LocalInputModeManager.provides(androidComposeView.getInputModeManager()), LocalLayoutDirection.provides(androidComposeView.getLayoutDirection()), LocalTextInputService.provides(androidComposeView.getTextInputService()), LocalTextToolbar.provides(androidComposeView.getTextToolbar()), LocalUriHandler.provides(uriHandler), LocalViewConfiguration.provides(androidComposeView.getViewConfiguration()), LocalWindowInfo.provides(androidComposeView.getWindowInfo()), LocalPointerIconService.provides(androidComposeView.getPointerIconService())}, content, composerImpl, ((i2 >> 3) & 112) | 8);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$ProvideCommonCompositionLocals$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CompositionLocalsKt.ProvideCommonCompositionLocals(Owner.this, uriHandler, content, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            });
        }
    }

    public static final void access$noLocalProvidedFor(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }

    public static final StaticProvidableCompositionLocal getLocalDensity() {
        return LocalDensity;
    }

    public static final StaticProvidableCompositionLocal getLocalFontFamilyResolver() {
        return LocalFontFamilyResolver;
    }

    public static final StaticProvidableCompositionLocal getLocalLayoutDirection() {
        return LocalLayoutDirection;
    }

    public static final StaticProvidableCompositionLocal getLocalViewConfiguration() {
        return LocalViewConfiguration;
    }
}
