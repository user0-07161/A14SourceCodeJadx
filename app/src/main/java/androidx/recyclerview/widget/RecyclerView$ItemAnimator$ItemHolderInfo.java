package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RecyclerView$ItemAnimator$ItemHolderInfo {
    public int left;
    public int top;

    public final void setFrom(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        this.left = view.getLeft();
        this.top = view.getTop();
        view.getRight();
        view.getBottom();
    }
}
