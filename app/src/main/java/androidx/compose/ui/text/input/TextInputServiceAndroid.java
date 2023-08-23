package androidx.compose.ui.text.input;

import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import java.util.ArrayList;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextInputServiceAndroid implements PlatformTextInputService {
    private final InputMethodManagerImpl inputMethodManager;
    private final AbstractChannel textInputCommandChannel;
    private final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public enum TextInputCommand {
        /* JADX INFO: Fake field, exist only in values array */
        StartInput,
        /* JADX INFO: Fake field, exist only in values array */
        StopInput,
        ShowKeyboard,
        /* JADX INFO: Fake field, exist only in values array */
        HideKeyboard
    }

    public TextInputServiceAndroid(View view) {
        long j;
        Intrinsics.checkNotNullParameter(view, "view");
        InputMethodManagerImpl inputMethodManagerImpl = new InputMethodManagerImpl(view);
        this.view = view;
        this.inputMethodManager = inputMethodManagerImpl;
        j = TextRange.Zero;
        new TextFieldValue(new AnnotatedString(""), j, null);
        new ArrayList();
        LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$baseInputConnection$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new BaseInputConnection(TextInputServiceAndroid.this.getView(), false);
            }
        });
        this.textInputCommandChannel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, 6);
    }

    public final View getView() {
        return this.view;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0051 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00fa  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0052 -> B:12:0x0031). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object textInputCommandEventLoop(kotlin.coroutines.Continuation r11) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.input.TextInputServiceAndroid.textInputCommandEventLoop(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
