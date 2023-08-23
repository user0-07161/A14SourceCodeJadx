package androidx.compose.ui.semantics;

import androidx.compose.ui.semantics.SemanticsPropertyKey;
import kotlin.Function;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SemanticsActions {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final SemanticsPropertyKey Collapse;
    private static final SemanticsPropertyKey CopyText;
    private static final SemanticsPropertyKey CustomActions;
    private static final SemanticsPropertyKey CutText;
    private static final SemanticsPropertyKey Dismiss;
    private static final SemanticsPropertyKey Expand;
    private static final SemanticsPropertyKey GetTextLayoutResult;
    private static final SemanticsPropertyKey OnClick;
    private static final SemanticsPropertyKey OnLongClick;
    private static final SemanticsPropertyKey PageDown;
    private static final SemanticsPropertyKey PageLeft;
    private static final SemanticsPropertyKey PageRight;
    private static final SemanticsPropertyKey PageUp;
    private static final SemanticsPropertyKey PasteText;
    private static final SemanticsPropertyKey RequestFocus;
    private static final SemanticsPropertyKey ScrollBy;
    private static final SemanticsPropertyKey SetProgress;
    private static final SemanticsPropertyKey SetSelection;
    private static final SemanticsPropertyKey SetText;

    static {
        SemanticsPropertiesKt$ActionPropertyKey$1 semanticsPropertiesKt$ActionPropertyKey$1 = new Function2() { // from class: androidx.compose.ui.semantics.SemanticsPropertiesKt$ActionPropertyKey$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                String label;
                Function action;
                AccessibilityAction accessibilityAction = (AccessibilityAction) obj;
                AccessibilityAction childValue = (AccessibilityAction) obj2;
                Intrinsics.checkNotNullParameter(childValue, "childValue");
                if (accessibilityAction == null || (label = accessibilityAction.getLabel()) == null) {
                    label = childValue.getLabel();
                }
                if (accessibilityAction == null || (action = accessibilityAction.getAction()) == null) {
                    action = childValue.getAction();
                }
                return new AccessibilityAction(label, action);
            }
        };
        GetTextLayoutResult = new SemanticsPropertyKey("GetTextLayoutResult", semanticsPropertiesKt$ActionPropertyKey$1);
        OnClick = new SemanticsPropertyKey("OnClick", semanticsPropertiesKt$ActionPropertyKey$1);
        OnLongClick = new SemanticsPropertyKey("OnLongClick", semanticsPropertiesKt$ActionPropertyKey$1);
        ScrollBy = new SemanticsPropertyKey("ScrollBy", semanticsPropertiesKt$ActionPropertyKey$1);
        new SemanticsPropertyKey("ScrollToIndex", semanticsPropertiesKt$ActionPropertyKey$1);
        SetProgress = new SemanticsPropertyKey("SetProgress", semanticsPropertiesKt$ActionPropertyKey$1);
        SetSelection = new SemanticsPropertyKey("SetSelection", semanticsPropertiesKt$ActionPropertyKey$1);
        SetText = new SemanticsPropertyKey("SetText", semanticsPropertiesKt$ActionPropertyKey$1);
        CopyText = new SemanticsPropertyKey("CopyText", semanticsPropertiesKt$ActionPropertyKey$1);
        CutText = new SemanticsPropertyKey("CutText", semanticsPropertiesKt$ActionPropertyKey$1);
        PasteText = new SemanticsPropertyKey("PasteText", semanticsPropertiesKt$ActionPropertyKey$1);
        Expand = new SemanticsPropertyKey("Expand", semanticsPropertiesKt$ActionPropertyKey$1);
        Collapse = new SemanticsPropertyKey("Collapse", semanticsPropertiesKt$ActionPropertyKey$1);
        Dismiss = new SemanticsPropertyKey("Dismiss", semanticsPropertiesKt$ActionPropertyKey$1);
        RequestFocus = new SemanticsPropertyKey("RequestFocus", semanticsPropertiesKt$ActionPropertyKey$1);
        CustomActions = new SemanticsPropertyKey("CustomActions", SemanticsPropertyKey.AnonymousClass1.INSTANCE);
        PageUp = new SemanticsPropertyKey("PageUp", semanticsPropertiesKt$ActionPropertyKey$1);
        PageLeft = new SemanticsPropertyKey("PageLeft", semanticsPropertiesKt$ActionPropertyKey$1);
        PageDown = new SemanticsPropertyKey("PageDown", semanticsPropertiesKt$ActionPropertyKey$1);
        PageRight = new SemanticsPropertyKey("PageRight", semanticsPropertiesKt$ActionPropertyKey$1);
    }

    public static SemanticsPropertyKey getCollapse() {
        return Collapse;
    }

    public static SemanticsPropertyKey getCopyText() {
        return CopyText;
    }

    public static SemanticsPropertyKey getCustomActions() {
        return CustomActions;
    }

    public static SemanticsPropertyKey getCutText() {
        return CutText;
    }

    public static SemanticsPropertyKey getDismiss() {
        return Dismiss;
    }

    public static SemanticsPropertyKey getExpand() {
        return Expand;
    }

    public static SemanticsPropertyKey getGetTextLayoutResult() {
        return GetTextLayoutResult;
    }

    public static SemanticsPropertyKey getOnClick() {
        return OnClick;
    }

    public static SemanticsPropertyKey getOnLongClick() {
        return OnLongClick;
    }

    public static SemanticsPropertyKey getPageDown() {
        return PageDown;
    }

    public static SemanticsPropertyKey getPageLeft() {
        return PageLeft;
    }

    public static SemanticsPropertyKey getPageRight() {
        return PageRight;
    }

    public static SemanticsPropertyKey getPageUp() {
        return PageUp;
    }

    public static SemanticsPropertyKey getPasteText() {
        return PasteText;
    }

    public static SemanticsPropertyKey getRequestFocus() {
        return RequestFocus;
    }

    public static SemanticsPropertyKey getScrollBy() {
        return ScrollBy;
    }

    public static SemanticsPropertyKey getSetProgress() {
        return SetProgress;
    }

    public static SemanticsPropertyKey getSetSelection() {
        return SetSelection;
    }

    public static SemanticsPropertyKey getSetText() {
        return SetText;
    }
}
