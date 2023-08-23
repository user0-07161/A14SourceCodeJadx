package com.android.egg.paint;

import android.view.MotionEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SpotFilter {
    private int lastTool;
    private int mBufSize;
    private Plotter mPlotter;
    private final float posDecay;
    private final float pressureDecay;
    private final LinkedList spots;
    private final MotionEvent.PointerCoords tmpSpot;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static boolean PRECISE_STYLUS_INPUT = true;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean getPRECISE_STYLUS_INPUT() {
            return SpotFilter.PRECISE_STYLUS_INPUT;
        }

        public final void setPRECISE_STYLUS_INPUT(boolean z) {
            SpotFilter.PRECISE_STYLUS_INPUT = z;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Plotter {
        void plot(MotionEvent.PointerCoords pointerCoords);
    }

    public SpotFilter(int i, float f, float f2, Plotter mPlotter) {
        boolean z;
        Intrinsics.checkNotNullParameter(mPlotter, "mPlotter");
        this.mBufSize = i;
        this.mPlotter = mPlotter;
        this.spots = new LinkedList();
        this.tmpSpot = new MotionEvent.PointerCoords();
        boolean z2 = true;
        if (0.0f <= f && f <= 1.0f) {
            z = true;
        } else {
            z = false;
        }
        this.posDecay = z ? f : 1.0f;
        this.pressureDecay = (0.0f > f2 || f2 > 1.0f) ? false : z2 ? f2 : 1.0f;
    }

    public final void add(List cv, int i) {
        Intrinsics.checkNotNullParameter(cv, "cv");
        Iterator it = cv.iterator();
        while (it.hasNext()) {
            addInternal((MotionEvent.PointerCoords) it.next(), i);
        }
    }

    protected final void addInternal(MotionEvent.PointerCoords c, int i) {
        MotionEvent.PointerCoords pointerCoords;
        Intrinsics.checkNotNullParameter(c, "c");
        if (this.spots.size() == this.mBufSize) {
            pointerCoords = (MotionEvent.PointerCoords) this.spots.removeLast();
        } else {
            pointerCoords = new MotionEvent.PointerCoords();
        }
        pointerCoords.copyFrom(c);
        this.spots.add(0, pointerCoords);
        filterInto(this.tmpSpot, i);
        this.mPlotter.plot(this.tmpSpot);
    }

    public final MotionEvent.PointerCoords filterInto(MotionEvent.PointerCoords out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        this.lastTool = i;
        Iterator it = this.spots.iterator();
        float f = 1.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 1.0f;
        while (it.hasNext()) {
            MotionEvent.PointerCoords pointerCoords = (MotionEvent.PointerCoords) it.next();
            f2 += pointerCoords.x * f;
            f3 += pointerCoords.y * f;
            f4 += pointerCoords.pressure * f8;
            f5 += pointerCoords.size * f8;
            f6 += f;
            f *= this.posDecay;
            f7 += f8;
            f8 *= this.pressureDecay;
            if (PRECISE_STYLUS_INPUT && i == 2) {
                break;
            }
        }
        out.x = f2 / f6;
        out.y = f3 / f6;
        out.pressure = f4 / f7;
        out.size = f5 / f7;
        return out;
    }

    public final void finish() {
        while (this.spots.size() > 0) {
            filterInto(this.tmpSpot, this.lastTool);
            this.spots.removeLast();
            this.mPlotter.plot(this.tmpSpot);
        }
        this.spots.clear();
    }

    public final int getLastTool() {
        return this.lastTool;
    }

    public final int getMBufSize$frameworks__base__packages__EasterEgg__android_common__EasterEgg() {
        return this.mBufSize;
    }

    public final Plotter getMPlotter$frameworks__base__packages__EasterEgg__android_common__EasterEgg() {
        return this.mPlotter;
    }

    public final float getPosDecay() {
        return this.posDecay;
    }

    public final float getPressureDecay() {
        return this.pressureDecay;
    }

    public final LinkedList getSpots() {
        return this.spots;
    }

    public final MotionEvent.PointerCoords getTmpSpot() {
        return this.tmpSpot;
    }

    public final void setLastTool(int i) {
        this.lastTool = i;
    }

    public final void setMBufSize$frameworks__base__packages__EasterEgg__android_common__EasterEgg(int i) {
        this.mBufSize = i;
    }

    public final void setMPlotter$frameworks__base__packages__EasterEgg__android_common__EasterEgg(Plotter plotter) {
        Intrinsics.checkNotNullParameter(plotter, "<set-?>");
        this.mPlotter = plotter;
    }

    public final void add(MotionEvent evt) {
        Intrinsics.checkNotNullParameter(evt, "evt");
        int toolType = evt.getToolType(0);
        int historySize = evt.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            evt.getHistoricalPointerCoords(0, i, this.tmpSpot);
            addInternal(this.tmpSpot, toolType);
        }
        evt.getPointerCoords(0, this.tmpSpot);
        addInternal(this.tmpSpot, toolType);
    }
}
