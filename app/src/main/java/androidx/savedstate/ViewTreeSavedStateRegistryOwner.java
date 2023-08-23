package androidx.savedstate;

import android.view.View;
import android.view.ViewParent;
import com.android.egg.R;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ViewTreeSavedStateRegistryOwner {
    public static final SavedStateRegistryOwner get(View view) {
        Object next;
        Intrinsics.checkNotNullParameter(view, "<this>");
        FilteringSequence$iterator$1 filteringSequence$iterator$1 = (FilteringSequence$iterator$1) SequencesKt.mapNotNull(SequencesKt.generateSequence(view, new Function1() { // from class: androidx.savedstate.ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                View view2 = (View) obj;
                Intrinsics.checkNotNullParameter(view2, "view");
                ViewParent parent = view2.getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            }
        }), new Function1() { // from class: androidx.savedstate.ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                View view2 = (View) obj;
                Intrinsics.checkNotNullParameter(view2, "view");
                Object tag = view2.getTag(R.id.view_tree_saved_state_registry_owner);
                if (tag instanceof SavedStateRegistryOwner) {
                    return (SavedStateRegistryOwner) tag;
                }
                return null;
            }
        }).iterator();
        if (!filteringSequence$iterator$1.hasNext()) {
            next = null;
        } else {
            next = filteringSequence$iterator$1.next();
        }
        return (SavedStateRegistryOwner) next;
    }
}
