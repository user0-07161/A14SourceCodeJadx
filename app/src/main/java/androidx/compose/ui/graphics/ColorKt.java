package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.Color;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ColorKt {
    /* JADX WARN: Removed duplicated region for block: B:34:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long Color(float r9, float r10, float r11, float r12, androidx.compose.ui.graphics.colorspace.ColorSpace r13) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.ColorKt.Color(float, float, float, float, androidx.compose.ui.graphics.colorspace.ColorSpace):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0075  */
    /* renamed from: toArgb-8_81llA  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int m100toArgb8_81llA(long r10) {
        /*
            androidx.compose.ui.graphics.Color$Companion r0 = androidx.compose.ui.graphics.Color.Companion
            r0 = 63
            long r0 = r0 & r10
            int r0 = (int) r0
            androidx.compose.ui.graphics.colorspace.ColorSpace[] r1 = androidx.compose.ui.graphics.colorspace.ColorSpaces.getColorSpacesArray$ui_graphics_release()
            r0 = r1[r0]
            boolean r1 = r0.isSrgb()
            if (r1 == 0) goto L17
            r0 = 32
            long r10 = r10 >>> r0
            int r10 = (int) r10
            return r10
        L17:
            r1 = 4
            float[] r1 = new float[r1]
            float r2 = androidx.compose.ui.graphics.Color.m97getRedimpl(r10)
            r3 = 0
            r1[r3] = r2
            float r2 = androidx.compose.ui.graphics.Color.m96getGreenimpl(r10)
            r4 = 1
            r1[r4] = r2
            float r2 = androidx.compose.ui.graphics.Color.m95getBlueimpl(r10)
            r5 = 2
            r1[r5] = r2
            float r10 = androidx.compose.ui.graphics.Color.m94getAlphaimpl(r10)
            r11 = 3
            r1[r11] = r10
            androidx.compose.ui.graphics.colorspace.Rgb r10 = androidx.compose.ui.graphics.colorspace.ColorSpaces.getSrgb()
            java.lang.String r2 = "destination"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r2)
            androidx.compose.ui.graphics.colorspace.Rgb r2 = androidx.compose.ui.graphics.colorspace.ColorSpaces.getSrgb()
            if (r0 != r2) goto L5b
            androidx.compose.ui.graphics.colorspace.Rgb r2 = androidx.compose.ui.graphics.colorspace.ColorSpaces.getSrgb()
            if (r10 != r2) goto L50
            androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1 r10 = androidx.compose.ui.graphics.colorspace.Connector.access$getSrgbIdentity$cp()
            goto L9f
        L50:
            androidx.compose.ui.graphics.colorspace.Oklab r2 = androidx.compose.ui.graphics.colorspace.ColorSpaces.getOklab()
            if (r10 != r2) goto L6c
            androidx.compose.ui.graphics.colorspace.Connector r10 = androidx.compose.ui.graphics.colorspace.Connector.access$getSrgbToOklabPerceptual$cp()
            goto L9f
        L5b:
            androidx.compose.ui.graphics.colorspace.Oklab r2 = androidx.compose.ui.graphics.colorspace.ColorSpaces.getOklab()
            if (r0 != r2) goto L6c
            androidx.compose.ui.graphics.colorspace.Rgb r2 = androidx.compose.ui.graphics.colorspace.ColorSpaces.getSrgb()
            if (r10 != r2) goto L6c
            androidx.compose.ui.graphics.colorspace.Connector r10 = androidx.compose.ui.graphics.colorspace.Connector.access$getOklabToSrgbPerceptual$cp()
            goto L9f
        L6c:
            if (r0 != r10) goto L75
            androidx.compose.ui.graphics.colorspace.Connector$Companion r10 = androidx.compose.ui.graphics.colorspace.Connector.Companion
            androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1 r10 = androidx.compose.ui.graphics.colorspace.Connector.Companion.identity$ui_graphics_release(r0)
            goto L9f
        L75:
            long r6 = r0.m130getModelxdoWZVw()
            long r8 = androidx.compose.ui.graphics.colorspace.ColorModel.access$getRgb$cp()
            boolean r2 = androidx.compose.ui.graphics.colorspace.ColorModel.m128equalsimpl0(r6, r8)
            if (r2 == 0) goto L99
            long r6 = r10.m130getModelxdoWZVw()
            long r8 = androidx.compose.ui.graphics.colorspace.ColorModel.access$getRgb$cp()
            boolean r2 = androidx.compose.ui.graphics.colorspace.ColorModel.m128equalsimpl0(r6, r8)
            if (r2 == 0) goto L99
            androidx.compose.ui.graphics.colorspace.Connector$RgbConnector r2 = new androidx.compose.ui.graphics.colorspace.Connector$RgbConnector
            androidx.compose.ui.graphics.colorspace.Rgb r0 = (androidx.compose.ui.graphics.colorspace.Rgb) r0
            r2.<init>(r0, r10, r3)
            goto L9e
        L99:
            androidx.compose.ui.graphics.colorspace.Connector r2 = new androidx.compose.ui.graphics.colorspace.Connector
            r2.<init>(r0, r10, r3)
        L9e:
            r10 = r2
        L9f:
            r10.transform(r1)
            r10 = r1[r11]
            r11 = 1132396544(0x437f0000, float:255.0)
            float r10 = r10 * r11
            r0 = 1056964608(0x3f000000, float:0.5)
            float r10 = r10 + r0
            int r10 = (int) r10
            int r10 = r10 << 24
            r2 = r1[r3]
            float r2 = r2 * r11
            float r2 = r2 + r0
            int r2 = (int) r2
            int r2 = r2 << 16
            r10 = r10 | r2
            r2 = r1[r4]
            float r2 = r2 * r11
            float r2 = r2 + r0
            int r2 = (int) r2
            int r2 = r2 << 8
            r10 = r10 | r2
            r1 = r1[r5]
            float r1 = r1 * r11
            float r1 = r1 + r0
            int r11 = (int) r1
            r10 = r10 | r11
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.ColorKt.m100toArgb8_81llA(long):int");
    }

    public static final long Color(long j) {
        long j2 = (j & 4294967295L) << 32;
        Color.Companion companion = Color.Companion;
        return j2;
    }
}
