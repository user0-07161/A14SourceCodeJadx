package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ChildHelper {
    final RecyclerView.AnonymousClass4 mCallback;
    final Bucket mBucket = new Bucket();
    final List mHiddenViews = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Bucket {
        long mData = 0;
        Bucket mNext;

        Bucket() {
        }

        private void ensureNext() {
            if (this.mNext == null) {
                this.mNext = new Bucket();
            }
        }

        final void clear(int i) {
            if (i >= 64) {
                Bucket bucket = this.mNext;
                if (bucket != null) {
                    bucket.clear(i - 64);
                    return;
                }
                return;
            }
            this.mData &= ~(1 << i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final int countOnesBefore(int i) {
            Bucket bucket = this.mNext;
            if (bucket == null) {
                if (i >= 64) {
                    return Long.bitCount(this.mData);
                }
                return Long.bitCount(((1 << i) - 1) & this.mData);
            } else if (i < 64) {
                return Long.bitCount(((1 << i) - 1) & this.mData);
            } else {
                return Long.bitCount(this.mData) + bucket.countOnesBefore(i - 64);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean get(int i) {
            if (i >= 64) {
                ensureNext();
                return this.mNext.get(i - 64);
            }
            if (((1 << i) & this.mData) != 0) {
                return true;
            }
            return false;
        }

        final void insert(int i, boolean z) {
            boolean z2;
            if (i >= 64) {
                ensureNext();
                this.mNext.insert(i - 64, z);
                return;
            }
            long j = this.mData;
            if ((Long.MIN_VALUE & j) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            long j2 = (1 << i) - 1;
            this.mData = ((j & (~j2)) << 1) | (j & j2);
            if (z) {
                set(i);
            } else {
                clear(i);
            }
            if (z2 || this.mNext != null) {
                ensureNext();
                this.mNext.insert(0, z2);
            }
        }

        final boolean remove(int i) {
            boolean z;
            if (i >= 64) {
                ensureNext();
                return this.mNext.remove(i - 64);
            }
            long j = 1 << i;
            long j2 = this.mData;
            if ((j2 & j) != 0) {
                z = true;
            } else {
                z = false;
            }
            long j3 = j2 & (~j);
            this.mData = j3;
            long j4 = j - 1;
            this.mData = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
            Bucket bucket = this.mNext;
            if (bucket != null) {
                if (bucket.get(0)) {
                    set(63);
                }
                this.mNext.remove(0);
            }
            return z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void reset() {
            this.mData = 0L;
            Bucket bucket = this.mNext;
            if (bucket != null) {
                bucket.reset();
            }
        }

        final void set(int i) {
            if (i >= 64) {
                ensureNext();
                this.mNext.set(i - 64);
                return;
            }
            this.mData |= 1 << i;
        }

        public final String toString() {
            if (this.mNext == null) {
                return Long.toBinaryString(this.mData);
            }
            return this.mNext.toString() + "xx" + Long.toBinaryString(this.mData);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChildHelper(RecyclerView.AnonymousClass4 anonymousClass4) {
        this.mCallback = anonymousClass4;
    }

    private int getOffset(int i) {
        if (i < 0) {
            return -1;
        }
        int childCount = this.mCallback.getChildCount();
        int i2 = i;
        while (i2 < childCount) {
            Bucket bucket = this.mBucket;
            int countOnesBefore = i - (i2 - bucket.countOnesBefore(i2));
            if (countOnesBefore == 0) {
                while (bucket.get(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += countOnesBefore;
        }
        return -1;
    }

    private void hideViewInternal(View view) {
        ((ArrayList) this.mHiddenViews).add(view);
        RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
        anonymousClass4.getClass();
        RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            childViewHolderInt.onEnteredHiddenState(RecyclerView.this);
        }
    }

    private void unhideViewInternal(View view) {
        if (((ArrayList) this.mHiddenViews).remove(view)) {
            RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
            anonymousClass4.getClass();
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                childViewHolderInt.onLeftHiddenState(RecyclerView.this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addView(View view, int i, boolean z) {
        int offset;
        RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
        if (i < 0) {
            offset = anonymousClass4.getChildCount();
        } else {
            offset = getOffset(i);
        }
        this.mBucket.insert(offset, z);
        if (z) {
            hideViewInternal(view);
        }
        RecyclerView recyclerView = RecyclerView.this;
        recyclerView.addView(view, offset);
        RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        RecyclerView.Adapter adapter = recyclerView.mAdapter;
        if (adapter != null && childViewHolderInt != null) {
            adapter.onViewAttachedToWindow(childViewHolderInt);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int offset;
        RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
        if (i < 0) {
            offset = anonymousClass4.getChildCount();
        } else {
            offset = getOffset(i);
        }
        this.mBucket.insert(offset, z);
        if (z) {
            hideViewInternal(view);
        }
        anonymousClass4.getClass();
        RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        RecyclerView recyclerView = RecyclerView.this;
        if (childViewHolderInt != null) {
            if (!childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + childViewHolderInt + recyclerView.exceptionLabel());
            }
            childViewHolderInt.clearTmpDetachFlag();
        }
        recyclerView.attachViewToParent(view, offset, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void detachViewFromParent(int i) {
        int offset = getOffset(i);
        this.mBucket.remove(offset);
        RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
        View childAt = RecyclerView.this.getChildAt(offset);
        RecyclerView recyclerView = RecyclerView.this;
        if (childAt != null) {
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
            if (childViewHolderInt != null) {
                if (childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + childViewHolderInt + recyclerView.exceptionLabel());
                }
                childViewHolderInt.addFlags(256);
            }
        } else {
            boolean z = RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
        }
        recyclerView.detachViewFromParent(offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final View getChildAt(int i) {
        return RecyclerView.this.getChildAt(getOffset(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getChildCount() {
        return this.mCallback.getChildCount() - ((ArrayList) this.mHiddenViews).size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final View getUnfilteredChildAt(int i) {
        return RecyclerView.this.getChildAt(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void hide(View view) {
        int indexOfChild = RecyclerView.this.indexOfChild(view);
        if (indexOfChild >= 0) {
            this.mBucket.set(indexOfChild);
            hideViewInternal(view);
            return;
        }
        throw new IllegalArgumentException("view is not a child, cannot hide " + view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isHidden(View view) {
        return ((ArrayList) this.mHiddenViews).contains(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeView(View view) {
        RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
        int indexOfChild = RecyclerView.this.indexOfChild(view);
        if (indexOfChild < 0) {
            return;
        }
        if (this.mBucket.remove(indexOfChild)) {
            unhideViewInternal(view);
        }
        anonymousClass4.removeViewAt(indexOfChild);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeViewAt(int i) {
        int offset = getOffset(i);
        RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
        View childAt = RecyclerView.this.getChildAt(offset);
        if (childAt == null) {
            return;
        }
        if (this.mBucket.remove(offset)) {
            unhideViewInternal(childAt);
        }
        anonymousClass4.removeViewAt(offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean removeViewIfHidden(View view) {
        RecyclerView.AnonymousClass4 anonymousClass4 = this.mCallback;
        int indexOfChild = RecyclerView.this.indexOfChild(view);
        if (indexOfChild == -1) {
            unhideViewInternal(view);
            return true;
        }
        Bucket bucket = this.mBucket;
        if (bucket.get(indexOfChild)) {
            bucket.remove(indexOfChild);
            unhideViewInternal(view);
            anonymousClass4.removeViewAt(indexOfChild);
            return true;
        }
        return false;
    }

    public final String toString() {
        return this.mBucket.toString() + ", hidden list:" + ((ArrayList) this.mHiddenViews).size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void unhide(View view) {
        int indexOfChild = RecyclerView.this.indexOfChild(view);
        if (indexOfChild >= 0) {
            Bucket bucket = this.mBucket;
            if (bucket.get(indexOfChild)) {
                bucket.clear(indexOfChild);
                unhideViewInternal(view);
                return;
            }
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
        throw new IllegalArgumentException("view is not a child, cannot hide " + view);
    }
}
