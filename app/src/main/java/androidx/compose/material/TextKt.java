package androidx.compose.material;

import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TextKt {
    private static final DynamicProvidableCompositionLocal LocalTextStyle;

    static {
        SnapshotMutationPolicy structuralEqualityPolicy = SnapshotStateKt.structuralEqualityPolicy();
        TextKt$LocalTextStyle$1 defaultFactory = new Function0() { // from class: androidx.compose.material.TextKt$LocalTextStyle$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TextStyle textStyle;
                textStyle = TextStyle.Default;
                return textStyle;
            }
        };
        Intrinsics.checkNotNullParameter(defaultFactory, "defaultFactory");
        LocalTextStyle = new DynamicProvidableCompositionLocal(structuralEqualityPolicy, defaultFactory);
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:262:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0137  */
    /* renamed from: Text--4IGK_g  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m19Text4IGK_g(final java.lang.String r40, androidx.compose.ui.Modifier r41, long r42, long r44, androidx.compose.ui.text.font.FontStyle r46, androidx.compose.ui.text.font.FontWeight r47, androidx.compose.ui.text.font.FontFamily r48, long r49, androidx.compose.ui.text.style.TextDecoration r51, androidx.compose.ui.text.style.TextAlign r52, long r53, int r55, boolean r56, int r57, int r58, kotlin.jvm.functions.Function1 r59, androidx.compose.ui.text.TextStyle r60, androidx.compose.runtime.Composer r61, final int r62, final int r63, final int r64) {
        /*
            Method dump skipped, instructions count: 1014
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextKt.m19Text4IGK_g(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
