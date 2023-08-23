package androidx.compose.ui.semantics;

import androidx.compose.ui.semantics.SemanticsPropertyKey;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SemanticsProperties {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final SemanticsPropertyKey CollectionInfo;
    private static final SemanticsPropertyKey CollectionItemInfo;
    private static final SemanticsPropertyKey ContentDescription = new SemanticsPropertyKey("ContentDescription", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$ContentDescription$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            List list = (List) obj;
            List childValue = (List) obj2;
            Intrinsics.checkNotNullParameter(childValue, "childValue");
            if (list != null) {
                List mutableList = CollectionsKt.toMutableList(list);
                ((ArrayList) mutableList).addAll(childValue);
                return mutableList;
            }
            return childValue;
        }
    });
    private static final SemanticsPropertyKey Disabled;
    private static final SemanticsPropertyKey EditableText;
    private static final SemanticsPropertyKey Error;
    private static final SemanticsPropertyKey Focused;
    private static final SemanticsPropertyKey Heading;
    private static final SemanticsPropertyKey HorizontalScrollAxisRange;
    private static final SemanticsPropertyKey InvisibleToUser;
    private static final SemanticsPropertyKey LiveRegion;
    private static final SemanticsPropertyKey PaneTitle;
    private static final SemanticsPropertyKey Password;
    private static final SemanticsPropertyKey ProgressBarRangeInfo;
    private static final SemanticsPropertyKey Role;
    private static final SemanticsPropertyKey SelectableGroup;
    private static final SemanticsPropertyKey Selected;
    private static final SemanticsPropertyKey StateDescription;
    private static final SemanticsPropertyKey TestTag;
    private static final SemanticsPropertyKey Text;
    private static final SemanticsPropertyKey TextSelectionRange;
    private static final SemanticsPropertyKey ToggleableState;
    private static final SemanticsPropertyKey VerticalScrollAxisRange;

    static {
        SemanticsPropertyKey.AnonymousClass1 anonymousClass1 = SemanticsPropertyKey.AnonymousClass1.INSTANCE;
        StateDescription = new SemanticsPropertyKey("StateDescription", anonymousClass1);
        ProgressBarRangeInfo = new SemanticsPropertyKey("ProgressBarRangeInfo", anonymousClass1);
        PaneTitle = new SemanticsPropertyKey("PaneTitle", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$PaneTitle$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                String str = (String) obj;
                Intrinsics.checkNotNullParameter((String) obj2, "<anonymous parameter 1>");
                throw new IllegalStateException("merge function called on unmergeable property PaneTitle.");
            }
        });
        SelectableGroup = new SemanticsPropertyKey("SelectableGroup", anonymousClass1);
        CollectionInfo = new SemanticsPropertyKey("CollectionInfo", anonymousClass1);
        CollectionItemInfo = new SemanticsPropertyKey("CollectionItemInfo", anonymousClass1);
        Heading = new SemanticsPropertyKey("Heading", anonymousClass1);
        Disabled = new SemanticsPropertyKey("Disabled", anonymousClass1);
        LiveRegion = new SemanticsPropertyKey("LiveRegion", anonymousClass1);
        Focused = new SemanticsPropertyKey("Focused", anonymousClass1);
        new SemanticsPropertyKey("IsContainer", anonymousClass1);
        InvisibleToUser = new SemanticsPropertyKey("InvisibleToUser", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$InvisibleToUser$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit unit = (Unit) obj;
                Intrinsics.checkNotNullParameter((Unit) obj2, "<anonymous parameter 1>");
                return unit;
            }
        });
        HorizontalScrollAxisRange = new SemanticsPropertyKey("HorizontalScrollAxisRange", anonymousClass1);
        VerticalScrollAxisRange = new SemanticsPropertyKey("VerticalScrollAxisRange", anonymousClass1);
        new SemanticsPropertyKey("IsPopup", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$IsPopup$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit unit = (Unit) obj;
                Intrinsics.checkNotNullParameter((Unit) obj2, "<anonymous parameter 1>");
                throw new IllegalStateException("merge function called on unmergeable property IsPopup. A popup should not be a child of a clickable/focusable node.");
            }
        });
        new SemanticsPropertyKey("IsDialog", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$IsDialog$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit unit = (Unit) obj;
                Intrinsics.checkNotNullParameter((Unit) obj2, "<anonymous parameter 1>");
                throw new IllegalStateException("merge function called on unmergeable property IsDialog. A dialog should not be a child of a clickable/focusable node.");
            }
        });
        Role = new SemanticsPropertyKey("Role", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$Role$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Role role = (Role) obj;
                ((Role) obj2).m291unboximpl();
                return role;
            }
        });
        TestTag = new SemanticsPropertyKey("TestTag", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$TestTag$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                String str = (String) obj;
                Intrinsics.checkNotNullParameter((String) obj2, "<anonymous parameter 1>");
                return str;
            }
        });
        Text = new SemanticsPropertyKey("Text", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$Text$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                List list = (List) obj;
                List childValue = (List) obj2;
                Intrinsics.checkNotNullParameter(childValue, "childValue");
                if (list != null) {
                    List mutableList = CollectionsKt.toMutableList(list);
                    ((ArrayList) mutableList).addAll(childValue);
                    return mutableList;
                }
                return childValue;
            }
        });
        EditableText = new SemanticsPropertyKey("EditableText", anonymousClass1);
        TextSelectionRange = new SemanticsPropertyKey("TextSelectionRange", anonymousClass1);
        new SemanticsPropertyKey("ImeAction", anonymousClass1);
        Selected = new SemanticsPropertyKey("Selected", anonymousClass1);
        ToggleableState = new SemanticsPropertyKey("ToggleableState", anonymousClass1);
        Password = new SemanticsPropertyKey("Password", anonymousClass1);
        Error = new SemanticsPropertyKey("Error", anonymousClass1);
        new SemanticsPropertyKey("IndexForKey", anonymousClass1);
    }

    public static SemanticsPropertyKey getCollectionInfo() {
        return CollectionInfo;
    }

    public static SemanticsPropertyKey getCollectionItemInfo() {
        return CollectionItemInfo;
    }

    public static SemanticsPropertyKey getContentDescription() {
        return ContentDescription;
    }

    public static SemanticsPropertyKey getDisabled() {
        return Disabled;
    }

    public static SemanticsPropertyKey getEditableText() {
        return EditableText;
    }

    public static SemanticsPropertyKey getError() {
        return Error;
    }

    public static SemanticsPropertyKey getFocused() {
        return Focused;
    }

    public static SemanticsPropertyKey getHeading() {
        return Heading;
    }

    public static SemanticsPropertyKey getHorizontalScrollAxisRange() {
        return HorizontalScrollAxisRange;
    }

    public static SemanticsPropertyKey getInvisibleToUser() {
        return InvisibleToUser;
    }

    public static SemanticsPropertyKey getLiveRegion() {
        return LiveRegion;
    }

    public static SemanticsPropertyKey getPaneTitle() {
        return PaneTitle;
    }

    public static SemanticsPropertyKey getPassword() {
        return Password;
    }

    public static SemanticsPropertyKey getProgressBarRangeInfo() {
        return ProgressBarRangeInfo;
    }

    public static SemanticsPropertyKey getRole() {
        return Role;
    }

    public static SemanticsPropertyKey getSelectableGroup() {
        return SelectableGroup;
    }

    public static SemanticsPropertyKey getSelected() {
        return Selected;
    }

    public static SemanticsPropertyKey getStateDescription() {
        return StateDescription;
    }

    public static SemanticsPropertyKey getTestTag() {
        return TestTag;
    }

    public static SemanticsPropertyKey getText() {
        return Text;
    }

    public static SemanticsPropertyKey getTextSelectionRange() {
        return TextSelectionRange;
    }

    public static SemanticsPropertyKey getToggleableState() {
        return ToggleableState;
    }

    public static SemanticsPropertyKey getVerticalScrollAxisRange() {
        return VerticalScrollAxisRange;
    }
}
