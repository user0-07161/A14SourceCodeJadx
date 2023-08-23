package androidx.recyclerview.widget;

import android.view.View;
import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SimpleItemAnimator {
    private RecyclerView.AnonymousClass4 mListener = null;
    private ArrayList mFinishedListeners = new ArrayList();
    private long mAddDuration = 120;
    private long mRemoveDuration = 120;
    private long mMoveDuration = 250;
    private long mChangeDuration = 250;
    boolean mSupportsChangeAnimations = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void buildAdapterChangeFlagsForAnimations(RecyclerView.ViewHolder viewHolder) {
        int i = viewHolder.mFlags & 14;
        if (!viewHolder.isInvalid() && (i & 4) == 0) {
            viewHolder.getOldPosition();
            viewHolder.getAbsoluteAdapterPosition();
        }
    }

    public abstract boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4);

    public final boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        int i;
        int i2;
        int i3 = recyclerView$ItemAnimator$ItemHolderInfo.left;
        int i4 = recyclerView$ItemAnimator$ItemHolderInfo.top;
        if (viewHolder2.shouldIgnore()) {
            int i5 = recyclerView$ItemAnimator$ItemHolderInfo.left;
            i2 = recyclerView$ItemAnimator$ItemHolderInfo.top;
            i = i5;
        } else {
            i = recyclerView$ItemAnimator$ItemHolderInfo2.left;
            i2 = recyclerView$ItemAnimator$ItemHolderInfo2.top;
        }
        return animateChange(viewHolder, viewHolder2, i3, i4, i, i2);
    }

    public final void dispatchAnimationFinished(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.AnonymousClass4 anonymousClass4 = this.mListener;
        if (anonymousClass4 != null) {
            viewHolder.setIsRecyclable(true);
            if (viewHolder.mShadowedHolder != null && viewHolder.mShadowingHolder == null) {
                viewHolder.mShadowedHolder = null;
            }
            viewHolder.mShadowingHolder = null;
            if (!viewHolder.shouldBeKeptAsChild()) {
                View view = viewHolder.itemView;
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.startInterceptRequestLayout();
                boolean removeViewIfHidden = recyclerView.mChildHelper.removeViewIfHidden(view);
                if (removeViewIfHidden) {
                    RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                    recyclerView.mRecycler.unscrapView(childViewHolderInt);
                    recyclerView.mRecycler.recycleViewHolderInternal(childViewHolderInt);
                }
                recyclerView.stopInterceptRequestLayout(!removeViewIfHidden);
                if (!removeViewIfHidden && viewHolder.isTmpDetached()) {
                    recyclerView.removeDetachedView(viewHolder.itemView, false);
                }
            }
        }
    }

    public final void dispatchAnimationsFinished() {
        if (this.mFinishedListeners.size() <= 0) {
            this.mFinishedListeners.clear();
        } else {
            OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mFinishedListeners.get(0));
            throw null;
        }
    }

    public final long getAddDuration() {
        return this.mAddDuration;
    }

    public final long getChangeDuration() {
        return this.mChangeDuration;
    }

    public final long getMoveDuration() {
        return this.mMoveDuration;
    }

    public final long getRemoveDuration() {
        return this.mRemoveDuration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setListener(RecyclerView.AnonymousClass4 anonymousClass4) {
        this.mListener = anonymousClass4;
    }
}
