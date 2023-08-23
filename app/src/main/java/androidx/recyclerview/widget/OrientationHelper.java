package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class OrientationHelper {
    protected final RecyclerView.LayoutManager mLayoutManager;
    private int mLastTotalSpace = Integer.MIN_VALUE;
    final Rect mTmpRect = new Rect();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* renamed from: androidx.recyclerview.widget.OrientationHelper$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends OrientationHelper {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass1(RecyclerView.LayoutManager layoutManager, int i) {
            super(layoutManager);
            this.$r8$classId = i;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getDecoratedEnd(View view) {
            int bottom;
            int i;
            int i2 = this.$r8$classId;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i2) {
                case 0:
                    layoutManager.getClass();
                    bottom = view.getRight() + ((RecyclerView.LayoutParams) view.getLayoutParams()).mDecorInsets.right;
                    i = ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).rightMargin;
                    break;
                default:
                    layoutManager.getClass();
                    bottom = view.getBottom() + ((RecyclerView.LayoutParams) view.getLayoutParams()).mDecorInsets.bottom;
                    i = ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).bottomMargin;
                    break;
            }
            return bottom + i;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getDecoratedMeasurement(View view) {
            int measuredHeight;
            int i;
            int i2 = this.$r8$classId;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i2) {
                case 0:
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                    layoutManager.getClass();
                    Rect rect = ((RecyclerView.LayoutParams) view.getLayoutParams()).mDecorInsets;
                    measuredHeight = view.getMeasuredWidth() + rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    i = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                    break;
                default:
                    RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) view.getLayoutParams();
                    layoutManager.getClass();
                    Rect rect2 = ((RecyclerView.LayoutParams) view.getLayoutParams()).mDecorInsets;
                    measuredHeight = view.getMeasuredHeight() + rect2.top + rect2.bottom + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin;
                    i = ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin;
                    break;
            }
            return measuredHeight + i;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getDecoratedMeasurementInOther(View view) {
            int measuredWidth;
            int i;
            int i2 = this.$r8$classId;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i2) {
                case 0:
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                    layoutManager.getClass();
                    Rect rect = ((RecyclerView.LayoutParams) view.getLayoutParams()).mDecorInsets;
                    measuredWidth = view.getMeasuredHeight() + rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                    i = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                    break;
                default:
                    RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) view.getLayoutParams();
                    layoutManager.getClass();
                    Rect rect2 = ((RecyclerView.LayoutParams) view.getLayoutParams()).mDecorInsets;
                    measuredWidth = view.getMeasuredWidth() + rect2.left + rect2.right + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin;
                    i = ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin;
                    break;
            }
            return measuredWidth + i;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getDecoratedStart(View view) {
            int top;
            int i;
            int i2 = this.$r8$classId;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i2) {
                case 0:
                    layoutManager.getClass();
                    top = view.getLeft() - ((RecyclerView.LayoutParams) view.getLayoutParams()).mDecorInsets.left;
                    i = ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).leftMargin;
                    break;
                default:
                    layoutManager.getClass();
                    top = view.getTop() - ((RecyclerView.LayoutParams) view.getLayoutParams()).mDecorInsets.top;
                    i = ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).topMargin;
                    break;
            }
            return top - i;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getEnd() {
            int i = this.$r8$classId;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i) {
                case 0:
                    return layoutManager.getWidth();
                default:
                    return layoutManager.getHeight();
            }
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getEndAfterPadding() {
            int height;
            int paddingBottom;
            int i = this.$r8$classId;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i) {
                case 0:
                    height = layoutManager.getWidth();
                    paddingBottom = layoutManager.getPaddingRight();
                    break;
                default:
                    height = layoutManager.getHeight();
                    paddingBottom = layoutManager.getPaddingBottom();
                    break;
            }
            return height - paddingBottom;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getMode() {
            int i = this.$r8$classId;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i) {
                case 0:
                    return layoutManager.getWidthMode();
                default:
                    return layoutManager.getHeightMode();
            }
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getStartAfterPadding() {
            int i = this.$r8$classId;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i) {
                case 0:
                    return layoutManager.getPaddingLeft();
                default:
                    return layoutManager.getPaddingTop();
            }
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getTotalSpace() {
            int height;
            int paddingBottom;
            int i = this.$r8$classId;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i) {
                case 0:
                    height = layoutManager.getWidth() - layoutManager.getPaddingLeft();
                    paddingBottom = layoutManager.getPaddingRight();
                    break;
                default:
                    height = layoutManager.getHeight() - layoutManager.getPaddingTop();
                    paddingBottom = layoutManager.getPaddingBottom();
                    break;
            }
            return height - paddingBottom;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getTransformedEndWithDecoration(View view) {
            int i = this.$r8$classId;
            Rect rect = this.mTmpRect;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i) {
                case 0:
                    layoutManager.getTransformedBoundingBox(view, rect);
                    return rect.right;
                default:
                    layoutManager.getTransformedBoundingBox(view, rect);
                    return rect.bottom;
            }
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final int getTransformedStartWithDecoration(View view) {
            int i = this.$r8$classId;
            Rect rect = this.mTmpRect;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i) {
                case 0:
                    layoutManager.getTransformedBoundingBox(view, rect);
                    return rect.left;
                default:
                    layoutManager.getTransformedBoundingBox(view, rect);
                    return rect.top;
            }
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public final void offsetChildren(int i) {
            int i2 = this.$r8$classId;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            switch (i2) {
                case 0:
                    layoutManager.offsetChildrenHorizontal(i);
                    return;
                default:
                    layoutManager.offsetChildrenVertical(i);
                    return;
            }
        }
    }

    OrientationHelper(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public static OrientationHelper createOrientationHelper(RecyclerView.LayoutManager layoutManager, int i) {
        if (i != 0) {
            if (i == 1) {
                return new AnonymousClass1(layoutManager, 1);
            }
            throw new IllegalArgumentException("invalid orientation");
        }
        return new AnonymousClass1(layoutManager, 0);
    }

    public abstract int getDecoratedEnd(View view);

    public abstract int getDecoratedMeasurement(View view);

    public abstract int getDecoratedMeasurementInOther(View view);

    public abstract int getDecoratedStart(View view);

    public abstract int getEnd();

    public abstract int getEndAfterPadding();

    public abstract int getMode();

    public abstract int getStartAfterPadding();

    public abstract int getTotalSpace();

    public final int getTotalSpaceChange() {
        if (Integer.MIN_VALUE == this.mLastTotalSpace) {
            return 0;
        }
        return getTotalSpace() - this.mLastTotalSpace;
    }

    public abstract int getTransformedEndWithDecoration(View view);

    public abstract int getTransformedStartWithDecoration(View view);

    public abstract void offsetChildren(int i);

    public final void onLayoutComplete() {
        this.mLastTotalSpace = getTotalSpace();
    }
}
