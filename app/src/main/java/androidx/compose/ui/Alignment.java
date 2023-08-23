package androidx.compose.ui;

import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface Alignment {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final BiasAlignment TopStart = new BiasAlignment(-1.0f, -1.0f);
        private static final BiasAlignment TopCenter = new BiasAlignment(0.0f, -1.0f);
        private static final BiasAlignment Center = new BiasAlignment(0.0f, 0.0f);
        private static final BiasAlignment BottomCenter = new BiasAlignment(0.0f, 1.0f);
        private static final BiasAlignment.Vertical Top = new BiasAlignment.Vertical(-1.0f);
        private static final BiasAlignment.Vertical CenterVertically = new BiasAlignment.Vertical(0.0f);
        private static final BiasAlignment.Vertical Bottom = new BiasAlignment.Vertical(1.0f);
        private static final BiasAlignment.Horizontal Start = new BiasAlignment.Horizontal(-1.0f);
        private static final BiasAlignment.Horizontal CenterHorizontally = new BiasAlignment.Horizontal(0.0f);

        public static BiasAlignment.Vertical getBottom() {
            return Bottom;
        }

        public static BiasAlignment getBottomCenter() {
            return BottomCenter;
        }

        public static BiasAlignment getCenter() {
            return Center;
        }

        public static BiasAlignment.Horizontal getCenterHorizontally() {
            return CenterHorizontally;
        }

        public static BiasAlignment.Vertical getCenterVertically() {
            return CenterVertically;
        }

        public static BiasAlignment.Horizontal getStart() {
            return Start;
        }

        public static BiasAlignment.Vertical getTop() {
            return Top;
        }

        public static BiasAlignment getTopCenter() {
            return TopCenter;
        }

        public static BiasAlignment getTopStart() {
            return TopStart;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Horizontal {
        int align(int i, LayoutDirection layoutDirection);
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Vertical {
    }
}
