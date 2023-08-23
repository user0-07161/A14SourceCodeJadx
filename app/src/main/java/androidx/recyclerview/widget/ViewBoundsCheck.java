package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ViewBoundsCheck {
    BoundFlags mBoundFlags = new BoundFlags();
    final Callback mCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class BoundFlags {
        int mBoundFlags = 0;
        int mChildEnd;
        int mChildStart;
        int mRvEnd;
        int mRvStart;

        BoundFlags() {
        }

        final boolean boundsMatch() {
            int i;
            int i2;
            int i3;
            int i4 = this.mBoundFlags;
            int i5 = 2;
            if ((i4 & 7) != 0) {
                int i6 = this.mChildStart;
                int i7 = this.mRvStart;
                if (i6 > i7) {
                    i3 = 1;
                } else if (i6 == i7) {
                    i3 = 2;
                } else {
                    i3 = 4;
                }
                if (((i3 << 0) & i4) == 0) {
                    return false;
                }
            }
            if ((i4 & 112) != 0) {
                int i8 = this.mChildStart;
                int i9 = this.mRvEnd;
                if (i8 > i9) {
                    i2 = 1;
                } else if (i8 == i9) {
                    i2 = 2;
                } else {
                    i2 = 4;
                }
                if (((i2 << 4) & i4) == 0) {
                    return false;
                }
            }
            if ((i4 & 1792) != 0) {
                int i10 = this.mChildEnd;
                int i11 = this.mRvStart;
                if (i10 > i11) {
                    i = 1;
                } else if (i10 == i11) {
                    i = 2;
                } else {
                    i = 4;
                }
                if (((i << 8) & i4) == 0) {
                    return false;
                }
            }
            if ((i4 & 28672) != 0) {
                int i12 = this.mChildEnd;
                int i13 = this.mRvEnd;
                if (i12 > i13) {
                    i5 = 1;
                } else if (i12 != i13) {
                    i5 = 4;
                }
                if (((i5 << 12) & i4) == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Callback {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewBoundsCheck(RecyclerView.LayoutManager.AnonymousClass1 anonymousClass1) {
        this.mCallback = anonymousClass1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final View findOneViewWithinBoundFlags(int i, int i2, int i3, int i4) {
        int paddingTop;
        int i5;
        View childAt;
        RecyclerView.LayoutManager.AnonymousClass1 anonymousClass1 = (RecyclerView.LayoutManager.AnonymousClass1) this.mCallback;
        int i6 = anonymousClass1.$r8$classId;
        RecyclerView.LayoutManager layoutManager = anonymousClass1.this$0;
        switch (i6) {
            case 0:
                paddingTop = layoutManager.getPaddingLeft();
                break;
            default:
                paddingTop = layoutManager.getPaddingTop();
                break;
        }
        int parentEnd = anonymousClass1.getParentEnd();
        if (i2 > i) {
            i5 = 1;
        } else {
            i5 = -1;
        }
        View view = null;
        while (i != i2) {
            int i7 = anonymousClass1.$r8$classId;
            RecyclerView.LayoutManager layoutManager2 = anonymousClass1.this$0;
            switch (i7) {
                case 0:
                    childAt = layoutManager2.getChildAt(i);
                    break;
                default:
                    childAt = layoutManager2.getChildAt(i);
                    break;
            }
            int childStart = anonymousClass1.getChildStart(childAt);
            int childEnd = anonymousClass1.getChildEnd(childAt);
            BoundFlags boundFlags = this.mBoundFlags;
            boundFlags.mRvStart = paddingTop;
            boundFlags.mRvEnd = parentEnd;
            boundFlags.mChildStart = childStart;
            boundFlags.mChildEnd = childEnd;
            if (i3 != 0) {
                boundFlags.mBoundFlags = i3 | 0;
                if (boundFlags.boundsMatch()) {
                    return childAt;
                }
            }
            if (i4 != 0) {
                BoundFlags boundFlags2 = this.mBoundFlags;
                boundFlags2.mBoundFlags = i4 | 0;
                if (boundFlags2.boundsMatch()) {
                    view = childAt;
                }
            }
            i += i5;
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isViewWithinBoundFlags(View view) {
        int paddingTop;
        BoundFlags boundFlags = this.mBoundFlags;
        RecyclerView.LayoutManager.AnonymousClass1 anonymousClass1 = (RecyclerView.LayoutManager.AnonymousClass1) this.mCallback;
        int i = anonymousClass1.$r8$classId;
        RecyclerView.LayoutManager layoutManager = anonymousClass1.this$0;
        switch (i) {
            case 0:
                paddingTop = layoutManager.getPaddingLeft();
                break;
            default:
                paddingTop = layoutManager.getPaddingTop();
                break;
        }
        int parentEnd = anonymousClass1.getParentEnd();
        int childStart = anonymousClass1.getChildStart(view);
        int childEnd = anonymousClass1.getChildEnd(view);
        boundFlags.mRvStart = paddingTop;
        boundFlags.mRvEnd = parentEnd;
        boundFlags.mChildStart = childStart;
        boundFlags.mChildEnd = childEnd;
        BoundFlags boundFlags2 = this.mBoundFlags;
        boundFlags2.mBoundFlags = 24579 | 0;
        return boundFlags2.boundsMatch();
    }
}
