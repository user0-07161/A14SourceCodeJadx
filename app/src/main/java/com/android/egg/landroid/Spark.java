package com.android.egg.landroid;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Spark extends Body implements Removable {
    public static final int $stable = 8;
    private final long color;
    private float lifetime;
    private final float size;
    private final Style style;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public enum Style {
        LINE,
        LINE_ABSOLUTE,
        DOT,
        DOT_ABSOLUTE,
        RING
    }

    public /* synthetic */ Spark(float f, boolean z, float f2, Style style, long j, float f3, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, z, f2, style, j, f3);
    }

    @Override // com.android.egg.landroid.Removable
    public boolean canBeRemoved() {
        if (this.lifetime < 0.0f) {
            return true;
        }
        return false;
    }

    /* renamed from: getColor-0d7_KjU  reason: not valid java name */
    public final long m442getColor0d7_KjU() {
        return this.color;
    }

    public final float getLifetime() {
        return this.lifetime;
    }

    public final float getSize() {
        return this.size;
    }

    public final Style getStyle() {
        return this.style;
    }

    public final void setLifetime(float f) {
        this.lifetime = f;
    }

    @Override // com.android.egg.landroid.Body, com.android.egg.landroid.Entity
    public void update(Simulator sim, float f) {
        Intrinsics.checkNotNullParameter(sim, "sim");
        super.update(sim, f);
        this.lifetime -= f;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Spark(float r11, boolean r12, float r13, com.android.egg.landroid.Spark.Style r14, long r15, float r17, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {
        /*
            r10 = this;
            r0 = r18 & 2
            if (r0 == 0) goto L7
            r0 = 0
            r3 = r0
            goto L8
        L7:
            r3 = r12
        L8:
            r0 = r18 & 4
            if (r0 == 0) goto Lf
            r0 = 0
            r4 = r0
            goto L10
        Lf:
            r4 = r13
        L10:
            r0 = r18 & 8
            if (r0 == 0) goto L18
            com.android.egg.landroid.Spark$Style r0 = com.android.egg.landroid.Spark.Style.LINE
            r5 = r0
            goto L19
        L18:
            r5 = r14
        L19:
            r0 = r18 & 16
            if (r0 == 0) goto L25
            androidx.compose.ui.graphics.Color$Companion r0 = androidx.compose.ui.graphics.Color.Companion
            long r0 = androidx.compose.ui.graphics.Color.access$getGray$cp()
            r6 = r0
            goto L26
        L25:
            r6 = r15
        L26:
            r0 = r18 & 32
            if (r0 == 0) goto L2e
            r0 = 1073741824(0x40000000, float:2.0)
            r8 = r0
            goto L30
        L2e:
            r8 = r17
        L30:
            r9 = 0
            r1 = r10
            r2 = r11
            r1.<init>(r2, r3, r4, r5, r6, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.landroid.Spark.<init>(float, boolean, float, com.android.egg.landroid.Spark$Style, long, float, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    private Spark(float f, boolean z, float f2, Style style, long j, float f3) {
        super(null, 1, null);
        this.lifetime = f;
        this.style = style;
        this.color = j;
        this.size = f3;
        setCollides(z);
        setMass(f2);
    }
}
