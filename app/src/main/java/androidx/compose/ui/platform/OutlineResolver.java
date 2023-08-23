package androidx.compose.ui.platform;

import android.graphics.Outline;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.RectangleShapeKt$RectangleShape$1;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class OutlineResolver {
    private boolean cacheIsDirty;
    private final Outline cachedOutline;
    private Outline.Rectangle calculatedOutline;
    private Density density;
    private boolean isSupportedOutline;
    private LayoutDirection layoutDirection;
    private boolean outlineNeeded;
    private Path outlinePath;
    private long rectSize;
    private long rectTopLeft;
    private float roundedCornerRadius;
    private Shape shape;
    private long size;
    private AndroidPath tmpPath;
    private RoundRect tmpRoundRect;
    private boolean usePathForClip;

    public OutlineResolver(Density density) {
        long j;
        long j2;
        long j3;
        Intrinsics.checkNotNullParameter(density, "density");
        this.density = density;
        this.isSupportedOutline = true;
        android.graphics.Outline outline = new android.graphics.Outline();
        outline.setAlpha(1.0f);
        this.cachedOutline = outline;
        j = Size.Zero;
        this.size = j;
        this.shape = RectangleShapeKt.getRectangleShape();
        j2 = Offset.Zero;
        this.rectTopLeft = j2;
        j3 = Size.Zero;
        this.rectSize = j3;
        this.layoutDirection = LayoutDirection.Ltr;
    }

    private final void updateCache() {
        long j;
        long j2;
        if (this.cacheIsDirty) {
            j = Offset.Zero;
            this.rectTopLeft = j;
            long j3 = this.size;
            this.rectSize = j3;
            this.roundedCornerRadius = 0.0f;
            this.outlinePath = null;
            this.cacheIsDirty = false;
            this.usePathForClip = false;
            boolean z = this.outlineNeeded;
            android.graphics.Outline outline = this.cachedOutline;
            if (z && Size.m64getWidthimpl(j3) > 0.0f && Size.m63getHeightimpl(this.size) > 0.0f) {
                this.isSupportedOutline = true;
                Shape shape = this.shape;
                long j4 = this.size;
                LayoutDirection layoutDirection = this.layoutDirection;
                Density density = this.density;
                ((RectangleShapeKt$RectangleShape$1) shape).getClass();
                Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
                Intrinsics.checkNotNullParameter(density, "density");
                j2 = Offset.Zero;
                Outline.Rectangle rectangle = new Outline.Rectangle(RectKt.m56Recttz77jQw(j2, j4));
                this.calculatedOutline = rectangle;
                Rect rect = rectangle.getRect();
                this.rectTopLeft = OffsetKt.Offset(rect.getLeft(), rect.getTop());
                this.rectSize = SizeKt.Size(rect.getWidth(), rect.getHeight());
                outline.setRect(MathKt.roundToInt(rect.getLeft()), MathKt.roundToInt(rect.getTop()), MathKt.roundToInt(rect.getRight()), MathKt.roundToInt(rect.getBottom()));
                return;
            }
            outline.setEmpty();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:76:0x013c, code lost:
        if (r8 == false) goto L79;
     */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void clipToOutline(androidx.compose.ui.graphics.Canvas r14) {
        /*
            Method dump skipped, instructions count: 453
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.OutlineResolver.clipToOutline(androidx.compose.ui.graphics.Canvas):void");
    }

    public final Path getClipPath() {
        updateCache();
        return this.outlinePath;
    }

    public final android.graphics.Outline getOutline() {
        updateCache();
        if (this.outlineNeeded && this.isSupportedOutline) {
            return this.cachedOutline;
        }
        return null;
    }

    public final boolean getOutlineClipSupported() {
        return !this.usePathForClip;
    }

    /* renamed from: isInOutline-k-4lQ0M  reason: not valid java name */
    public final boolean m286isInOutlinek4lQ0M(long j) {
        Outline.Rectangle rectangle;
        if (!this.outlineNeeded || (rectangle = this.calculatedOutline) == null) {
            return true;
        }
        float m45getXimpl = Offset.m45getXimpl(j);
        float m46getYimpl = Offset.m46getYimpl(j);
        Rect rect = rectangle.getRect();
        if (rect.getLeft() <= m45getXimpl && m45getXimpl < rect.getRight() && rect.getTop() <= m46getYimpl && m46getYimpl < rect.getBottom()) {
            return true;
        }
        return false;
    }

    public final boolean update(Shape shape, float f, boolean z, float f2, LayoutDirection layoutDirection, Density density) {
        boolean z2;
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(density, "density");
        this.cachedOutline.setAlpha(f);
        boolean z3 = !Intrinsics.areEqual(this.shape, shape);
        if (z3) {
            this.shape = shape;
            this.cacheIsDirty = true;
        }
        if (!z && f2 <= 0.0f) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (this.outlineNeeded != z2) {
            this.outlineNeeded = z2;
            this.cacheIsDirty = true;
        }
        if (this.layoutDirection != layoutDirection) {
            this.layoutDirection = layoutDirection;
            this.cacheIsDirty = true;
        }
        if (!Intrinsics.areEqual(this.density, density)) {
            this.density = density;
            this.cacheIsDirty = true;
        }
        return z3;
    }

    /* renamed from: update-uvyYCjk  reason: not valid java name */
    public final void m287updateuvyYCjk(long j) {
        boolean z;
        long j2 = this.size;
        Size.Companion companion = Size.Companion;
        if (j2 == j) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            this.size = j;
            this.cacheIsDirty = true;
        }
    }
}
