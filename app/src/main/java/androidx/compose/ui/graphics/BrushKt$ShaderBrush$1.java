package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Size;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class BrushKt$ShaderBrush$1 extends Brush {
    final /* synthetic */ Shader $shader;
    private long createdSize;
    private Shader internalShader;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BrushKt$ShaderBrush$1(Shader shader) {
        long j;
        this.$shader = shader;
        j = Size.Unspecified;
        this.createdSize = j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:
        if (r3 == false) goto L22;
     */
    @Override // androidx.compose.ui.graphics.Brush
    /* renamed from: applyTo-Pq9zytI */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mo89applyToPq9zytI(float r7, long r8, androidx.compose.ui.graphics.AndroidPaint r10) {
        /*
            r6 = this;
            java.lang.String r0 = "p"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            android.graphics.Shader r0 = r6.internalShader
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L18
            long r3 = r6.createdSize
            androidx.compose.ui.geometry.Size$Companion r5 = androidx.compose.ui.geometry.Size.Companion
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r3 != 0) goto L15
            r3 = r1
            goto L16
        L15:
            r3 = r2
        L16:
            if (r3 != 0) goto L1e
        L18:
            android.graphics.Shader r0 = r6.$shader
            r6.internalShader = r0
            r6.createdSize = r8
        L1e:
            long r8 = r10.m77getColor0d7_KjU()
            long r3 = androidx.compose.ui.graphics.Color.access$getBlack$cp()
            boolean r6 = androidx.compose.ui.graphics.Color.m93equalsimpl0(r8, r3)
            if (r6 != 0) goto L33
            long r8 = androidx.compose.ui.graphics.Color.access$getBlack$cp()
            r10.m82setColor8_81llA(r8)
        L33:
            android.graphics.Shader r6 = r10.getShader()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
            if (r6 != 0) goto L40
            r10.setShader(r0)
        L40:
            float r6 = r10.getAlpha()
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 != 0) goto L49
            goto L4a
        L49:
            r1 = r2
        L4a:
            if (r1 != 0) goto L4f
            r10.setAlpha(r7)
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.BrushKt$ShaderBrush$1.mo89applyToPq9zytI(float, long, androidx.compose.ui.graphics.AndroidPaint):void");
    }

    /* renamed from: createShader-uvyYCjk  reason: not valid java name */
    public final Shader m90createShaderuvyYCjk() {
        return this.$shader;
    }
}
