package androidx.compose.foundation.gestures;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
abstract class TransformEvent {

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class TransformDelta extends TransformEvent {
        private final long panChange;
        private final float rotationChange;
        private final float zoomChange;

        public TransformDelta(float f, long j, float f2) {
            this.zoomChange = f;
            this.panChange = j;
            this.rotationChange = f2;
        }

        /* renamed from: getPanChange-F1C5BW0  reason: not valid java name */
        public final long m7getPanChangeF1C5BW0() {
            return this.panChange;
        }

        public final float getRotationChange() {
            return this.rotationChange;
        }

        public final float getZoomChange() {
            return this.zoomChange;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class TransformStarted extends TransformEvent {
        public static final TransformStarted INSTANCE = new TransformStarted();
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class TransformStopped extends TransformEvent {
        public static final TransformStopped INSTANCE = new TransformStopped();
    }
}
