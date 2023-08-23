package androidx.core.view;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class NestedScrollingChildHelper {
    private boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParentNonTouch;
    private ViewParent mNestedScrollingParentTouch;
    private int[] mTempNestedScrollConsumed;
    private final View mView;

    public NestedScrollingChildHelper(View view) {
        this.mView = view;
    }

    private boolean dispatchNestedScrollInternal(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        ViewParent nestedScrollingParentForType;
        int i6;
        int i7;
        int[] iArr3;
        if (!this.mIsNestedScrollingEnabled || (nestedScrollingParentForType = getNestedScrollingParentForType(i5)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        View view = this.mView;
        if (iArr != null) {
            view.getLocationInWindow(iArr);
            i6 = iArr[0];
            i7 = iArr[1];
        } else {
            i6 = 0;
            i7 = 0;
        }
        if (iArr2 == null) {
            if (this.mTempNestedScrollConsumed == null) {
                this.mTempNestedScrollConsumed = new int[2];
            }
            iArr3 = this.mTempNestedScrollConsumed;
            iArr3[0] = 0;
            iArr3[1] = 0;
        } else {
            iArr3 = iArr2;
        }
        View view2 = this.mView;
        iArr3[0] = iArr3[0] + i3;
        iArr3[1] = iArr3[1] + i4;
        if (i5 == 0) {
            try {
                nestedScrollingParentForType.onNestedScroll(view2, i, i2, i3, i4);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + nestedScrollingParentForType + " does not implement interface method onNestedScroll", e);
            }
        }
        if (iArr != null) {
            view.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i6;
            iArr[1] = iArr[1] - i7;
        }
        return true;
    }

    private ViewParent getNestedScrollingParentForType(int i) {
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            return this.mNestedScrollingParentNonTouch;
        }
        return this.mNestedScrollingParentTouch;
    }

    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        ViewParent nestedScrollingParentForType;
        if (!this.mIsNestedScrollingEnabled || (nestedScrollingParentForType = getNestedScrollingParentForType(0)) == null) {
            return false;
        }
        try {
            return nestedScrollingParentForType.onNestedFling(this.mView, f, f2, z);
        } catch (AbstractMethodError e) {
            Log.e("ViewParentCompat", "ViewParent " + nestedScrollingParentForType + " does not implement interface method onNestedFling", e);
            return false;
        }
    }

    public final boolean dispatchNestedPreFling(float f, float f2) {
        ViewParent nestedScrollingParentForType;
        if (!this.mIsNestedScrollingEnabled || (nestedScrollingParentForType = getNestedScrollingParentForType(0)) == null) {
            return false;
        }
        try {
            return nestedScrollingParentForType.onNestedPreFling(this.mView, f, f2);
        } catch (AbstractMethodError e) {
            Log.e("ViewParentCompat", "ViewParent " + nestedScrollingParentForType + " does not implement interface method onNestedPreFling", e);
            return false;
        }
    }

    public final boolean dispatchNestedPreScroll(int i, int i2, int i3, int[] iArr, int[] iArr2) {
        ViewParent nestedScrollingParentForType;
        int i4;
        int i5;
        if (!this.mIsNestedScrollingEnabled || (nestedScrollingParentForType = getNestedScrollingParentForType(i3)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        View view = this.mView;
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            i4 = iArr2[0];
            i5 = iArr2[1];
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (iArr == null) {
            if (this.mTempNestedScrollConsumed == null) {
                this.mTempNestedScrollConsumed = new int[2];
            }
            iArr = this.mTempNestedScrollConsumed;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        if (i3 == 0) {
            try {
                nestedScrollingParentForType.onNestedPreScroll(view, i, i2, iArr);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + nestedScrollingParentForType + " does not implement interface method onNestedPreScroll", e);
            }
        }
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i4;
            iArr2[1] = iArr2[1] - i5;
        }
        if (iArr[0] == 0 && iArr[1] == 0) {
            return false;
        }
        return true;
    }

    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return dispatchNestedScrollInternal(i, i2, i3, i4, iArr, 0, null);
    }

    public final boolean hasNestedScrollingParent(int i) {
        if (getNestedScrollingParentForType(i) != null) {
            return true;
        }
        return false;
    }

    public final boolean isNestedScrollingEnabled() {
        return this.mIsNestedScrollingEnabled;
    }

    public final void setNestedScrollingEnabled(boolean z) {
        if (this.mIsNestedScrollingEnabled) {
            int i = ViewCompat.$r8$clinit;
            ViewCompat.Api21Impl.stopNestedScroll(this.mView);
        }
        this.mIsNestedScrollingEnabled = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0039 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean startNestedScroll(int r11, int r12) {
        /*
            r10 = this;
            boolean r0 = r10.hasNestedScrollingParent(r12)
            r1 = 1
            if (r0 == 0) goto L8
            return r1
        L8:
            boolean r0 = r10.mIsNestedScrollingEnabled
            r2 = 0
            if (r0 == 0) goto L6b
            android.view.View r0 = r10.mView
            android.view.ViewParent r3 = r0.getParent()
            r4 = r0
        L14:
            if (r3 == 0) goto L6b
            java.lang.String r5 = "ViewParentCompat"
            java.lang.String r6 = "ViewParent "
            if (r12 != 0) goto L36
            boolean r7 = r3.onStartNestedScroll(r4, r0, r11)     // Catch: java.lang.AbstractMethodError -> L21
            goto L37
        L21:
            r7 = move-exception
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r6)
            r8.append(r3)
            java.lang.String r9 = " does not implement interface method onStartNestedScroll"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.util.Log.e(r5, r8, r7)
        L36:
            r7 = r2
        L37:
            if (r7 == 0) goto L5f
            if (r12 == 0) goto L41
            if (r12 == r1) goto L3e
            goto L43
        L3e:
            r10.mNestedScrollingParentNonTouch = r3
            goto L43
        L41:
            r10.mNestedScrollingParentTouch = r3
        L43:
            if (r12 != 0) goto L5e
            r3.onNestedScrollAccepted(r4, r0, r11)     // Catch: java.lang.AbstractMethodError -> L49
            goto L5e
        L49:
            r10 = move-exception
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r6)
            r11.append(r3)
            java.lang.String r12 = " does not implement interface method onNestedScrollAccepted"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            android.util.Log.e(r5, r11, r10)
        L5e:
            return r1
        L5f:
            boolean r5 = r3 instanceof android.view.View
            if (r5 == 0) goto L66
            r4 = r3
            android.view.View r4 = (android.view.View) r4
        L66:
            android.view.ViewParent r3 = r3.getParent()
            goto L14
        L6b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.NestedScrollingChildHelper.startNestedScroll(int, int):boolean");
    }

    public final void stopNestedScroll(int i) {
        ViewParent nestedScrollingParentForType = getNestedScrollingParentForType(i);
        if (nestedScrollingParentForType != null) {
            View view = this.mView;
            if (i == 0) {
                try {
                    nestedScrollingParentForType.onStopNestedScroll(view);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", "ViewParent " + nestedScrollingParentForType + " does not implement interface method onStopNestedScroll", e);
                }
            }
            if (i != 0) {
                if (i == 1) {
                    this.mNestedScrollingParentNonTouch = null;
                    return;
                }
                return;
            }
            this.mNestedScrollingParentTouch = null;
        }
    }

    public final void dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        dispatchNestedScrollInternal(i, i2, i3, i4, iArr, i5, iArr2);
    }
}
