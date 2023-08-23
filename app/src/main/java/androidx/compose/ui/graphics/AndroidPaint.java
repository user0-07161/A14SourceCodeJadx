package androidx.compose.ui.graphics;

import android.graphics.BlendMode;
import android.graphics.Paint;
import android.graphics.Shader;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Color;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidPaint {
    private int _blendMode;
    private Paint internalPaint;
    private Shader internalShader;
    private PathEffect pathEffect;

    public AndroidPaint(Paint internalPaint) {
        Intrinsics.checkNotNullParameter(internalPaint, "internalPaint");
        this.internalPaint = internalPaint;
        this._blendMode = 3;
    }

    public final Paint asFrameworkPaint() {
        return this.internalPaint;
    }

    public final float getAlpha() {
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        return paint.getAlpha() / 255.0f;
    }

    /* renamed from: getBlendMode-0nO6VwU  reason: not valid java name */
    public final int m76getBlendMode0nO6VwU() {
        return this._blendMode;
    }

    /* renamed from: getColor-0d7_KjU  reason: not valid java name */
    public final long m77getColor0d7_KjU() {
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        long color = paint.getColor() << 32;
        Color.Companion companion = Color.Companion;
        return color;
    }

    /* renamed from: getFilterQuality-f-v9h1I  reason: not valid java name */
    public final int m78getFilterQualityfv9h1I() {
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        return paint.isFilterBitmap() ? 1 : 0;
    }

    public final PathEffect getPathEffect() {
        return this.pathEffect;
    }

    public final Shader getShader() {
        return this.internalShader;
    }

    /* renamed from: getStrokeCap-KaPHkGw  reason: not valid java name */
    public final int m79getStrokeCapKaPHkGw() {
        int i;
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        Paint.Cap strokeCap = paint.getStrokeCap();
        if (strokeCap == null) {
            i = -1;
        } else {
            i = AndroidPaint_androidKt.WhenMappings.$EnumSwitchMapping$1[strokeCap.ordinal()];
        }
        if (i != 1) {
            if (i == 2) {
                return 1;
            }
            if (i == 3) {
                return 2;
            }
        }
        return 0;
    }

    /* renamed from: getStrokeJoin-LxFBmk8  reason: not valid java name */
    public final int m80getStrokeJoinLxFBmk8() {
        int i;
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        Paint.Join strokeJoin = paint.getStrokeJoin();
        if (strokeJoin == null) {
            i = -1;
        } else {
            i = AndroidPaint_androidKt.WhenMappings.$EnumSwitchMapping$2[strokeJoin.ordinal()];
        }
        if (i != 1) {
            if (i == 2) {
                return 2;
            }
            if (i == 3) {
                return 1;
            }
        }
        return 0;
    }

    public final float getStrokeMiterLimit() {
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        return paint.getStrokeMiter();
    }

    public final float getStrokeWidth() {
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        return paint.getStrokeWidth();
    }

    public final void setAlpha(float f) {
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        paint.setAlpha((int) Math.rint(f * 255.0f));
    }

    /* renamed from: setBlendMode-s9anfk8  reason: not valid java name */
    public final void m81setBlendModes9anfk8(int i) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15;
        boolean z16;
        boolean z17;
        boolean z18;
        boolean z19;
        boolean z20;
        boolean z21;
        boolean z22;
        boolean z23;
        boolean z24;
        boolean z25;
        boolean z26;
        boolean z27;
        boolean z28;
        BlendMode blendMode;
        this._blendMode = i;
        Paint setNativeBlendMode = this.internalPaint;
        Intrinsics.checkNotNullParameter(setNativeBlendMode, "$this$setNativeBlendMode");
        boolean z29 = false;
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            blendMode = BlendMode.CLEAR;
        } else {
            if (i == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                blendMode = BlendMode.SRC;
            } else {
                if (i == 2) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    blendMode = BlendMode.DST;
                } else {
                    if (i == 3) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        blendMode = BlendMode.SRC_OVER;
                    } else {
                        if (i == 4) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (z5) {
                            blendMode = BlendMode.DST_OVER;
                        } else {
                            if (i == 5) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            if (z6) {
                                blendMode = BlendMode.SRC_IN;
                            } else {
                                if (i == 6) {
                                    z7 = true;
                                } else {
                                    z7 = false;
                                }
                                if (z7) {
                                    blendMode = BlendMode.DST_IN;
                                } else {
                                    if (i == 7) {
                                        z8 = true;
                                    } else {
                                        z8 = false;
                                    }
                                    if (z8) {
                                        blendMode = BlendMode.SRC_OUT;
                                    } else {
                                        if (i == 8) {
                                            z9 = true;
                                        } else {
                                            z9 = false;
                                        }
                                        if (z9) {
                                            blendMode = BlendMode.DST_OUT;
                                        } else {
                                            if (i == 9) {
                                                z10 = true;
                                            } else {
                                                z10 = false;
                                            }
                                            if (z10) {
                                                blendMode = BlendMode.SRC_ATOP;
                                            } else {
                                                if (i == 10) {
                                                    z11 = true;
                                                } else {
                                                    z11 = false;
                                                }
                                                if (z11) {
                                                    blendMode = BlendMode.DST_ATOP;
                                                } else {
                                                    if (i == 11) {
                                                        z12 = true;
                                                    } else {
                                                        z12 = false;
                                                    }
                                                    if (z12) {
                                                        blendMode = BlendMode.XOR;
                                                    } else {
                                                        if (i == 12) {
                                                            z13 = true;
                                                        } else {
                                                            z13 = false;
                                                        }
                                                        if (z13) {
                                                            blendMode = BlendMode.PLUS;
                                                        } else {
                                                            if (i == 13) {
                                                                z14 = true;
                                                            } else {
                                                                z14 = false;
                                                            }
                                                            if (z14) {
                                                                blendMode = BlendMode.MODULATE;
                                                            } else {
                                                                if (i == 14) {
                                                                    z15 = true;
                                                                } else {
                                                                    z15 = false;
                                                                }
                                                                if (z15) {
                                                                    blendMode = BlendMode.SCREEN;
                                                                } else {
                                                                    if (i == 15) {
                                                                        z16 = true;
                                                                    } else {
                                                                        z16 = false;
                                                                    }
                                                                    if (z16) {
                                                                        blendMode = BlendMode.OVERLAY;
                                                                    } else {
                                                                        if (i == 16) {
                                                                            z17 = true;
                                                                        } else {
                                                                            z17 = false;
                                                                        }
                                                                        if (z17) {
                                                                            blendMode = BlendMode.DARKEN;
                                                                        } else {
                                                                            if (i == 17) {
                                                                                z18 = true;
                                                                            } else {
                                                                                z18 = false;
                                                                            }
                                                                            if (z18) {
                                                                                blendMode = BlendMode.LIGHTEN;
                                                                            } else {
                                                                                if (i == 18) {
                                                                                    z19 = true;
                                                                                } else {
                                                                                    z19 = false;
                                                                                }
                                                                                if (z19) {
                                                                                    blendMode = BlendMode.COLOR_DODGE;
                                                                                } else {
                                                                                    if (i == 19) {
                                                                                        z20 = true;
                                                                                    } else {
                                                                                        z20 = false;
                                                                                    }
                                                                                    if (z20) {
                                                                                        blendMode = BlendMode.COLOR_BURN;
                                                                                    } else {
                                                                                        if (i == 20) {
                                                                                            z21 = true;
                                                                                        } else {
                                                                                            z21 = false;
                                                                                        }
                                                                                        if (z21) {
                                                                                            blendMode = BlendMode.HARD_LIGHT;
                                                                                        } else {
                                                                                            if (i == 21) {
                                                                                                z22 = true;
                                                                                            } else {
                                                                                                z22 = false;
                                                                                            }
                                                                                            if (z22) {
                                                                                                blendMode = BlendMode.SOFT_LIGHT;
                                                                                            } else {
                                                                                                if (i == 22) {
                                                                                                    z23 = true;
                                                                                                } else {
                                                                                                    z23 = false;
                                                                                                }
                                                                                                if (z23) {
                                                                                                    blendMode = BlendMode.DIFFERENCE;
                                                                                                } else {
                                                                                                    if (i == 23) {
                                                                                                        z24 = true;
                                                                                                    } else {
                                                                                                        z24 = false;
                                                                                                    }
                                                                                                    if (z24) {
                                                                                                        blendMode = BlendMode.EXCLUSION;
                                                                                                    } else {
                                                                                                        if (i == 24) {
                                                                                                            z25 = true;
                                                                                                        } else {
                                                                                                            z25 = false;
                                                                                                        }
                                                                                                        if (z25) {
                                                                                                            blendMode = BlendMode.MULTIPLY;
                                                                                                        } else {
                                                                                                            if (i == 25) {
                                                                                                                z26 = true;
                                                                                                            } else {
                                                                                                                z26 = false;
                                                                                                            }
                                                                                                            if (z26) {
                                                                                                                blendMode = BlendMode.HUE;
                                                                                                            } else {
                                                                                                                if (i == 26) {
                                                                                                                    z27 = true;
                                                                                                                } else {
                                                                                                                    z27 = false;
                                                                                                                }
                                                                                                                if (z27) {
                                                                                                                    blendMode = BlendMode.SATURATION;
                                                                                                                } else {
                                                                                                                    if (i == 27) {
                                                                                                                        z28 = true;
                                                                                                                    } else {
                                                                                                                        z28 = false;
                                                                                                                    }
                                                                                                                    if (z28) {
                                                                                                                        blendMode = BlendMode.COLOR;
                                                                                                                    } else {
                                                                                                                        if (i == 28) {
                                                                                                                            z29 = true;
                                                                                                                        }
                                                                                                                        if (z29) {
                                                                                                                            blendMode = BlendMode.LUMINOSITY;
                                                                                                                        } else {
                                                                                                                            blendMode = BlendMode.SRC_OVER;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        setNativeBlendMode.setBlendMode(blendMode);
    }

    /* renamed from: setColor-8_81llA  reason: not valid java name */
    public final void m82setColor8_81llA(long j) {
        Paint setNativeColor = this.internalPaint;
        Intrinsics.checkNotNullParameter(setNativeColor, "$this$setNativeColor");
        setNativeColor.setColor(ColorKt.m100toArgb8_81llA(j));
    }

    public final void setColorFilter() {
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        paint.setColorFilter(null);
    }

    /* renamed from: setFilterQuality-vDHp3xo  reason: not valid java name */
    public final void m83setFilterQualityvDHp3xo(int i) {
        boolean z;
        Paint setNativeFilterQuality = this.internalPaint;
        Intrinsics.checkNotNullParameter(setNativeFilterQuality, "$this$setNativeFilterQuality");
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        setNativeFilterQuality.setFilterBitmap(!z);
    }

    public final void setPathEffect(PathEffect pathEffect) {
        android.graphics.PathEffect pathEffect2;
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        AndroidPathEffect androidPathEffect = (AndroidPathEffect) pathEffect;
        if (androidPathEffect != null) {
            pathEffect2 = androidPathEffect.getNativePathEffect();
        } else {
            pathEffect2 = null;
        }
        paint.setPathEffect(pathEffect2);
        this.pathEffect = pathEffect;
    }

    public final void setShader(Shader shader) {
        this.internalShader = shader;
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        paint.setShader(shader);
    }

    /* renamed from: setStrokeCap-BeK7IIE  reason: not valid java name */
    public final void m84setStrokeCapBeK7IIE(int i) {
        boolean z;
        boolean z2;
        Paint.Cap cap;
        Paint setNativeStrokeCap = this.internalPaint;
        Intrinsics.checkNotNullParameter(setNativeStrokeCap, "$this$setNativeStrokeCap");
        boolean z3 = false;
        if (i == 2) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            cap = Paint.Cap.SQUARE;
        } else {
            if (i == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                cap = Paint.Cap.ROUND;
            } else {
                if (i == 0) {
                    z3 = true;
                }
                if (z3) {
                    cap = Paint.Cap.BUTT;
                } else {
                    cap = Paint.Cap.BUTT;
                }
            }
        }
        setNativeStrokeCap.setStrokeCap(cap);
    }

    /* renamed from: setStrokeJoin-Ww9F2mQ  reason: not valid java name */
    public final void m85setStrokeJoinWw9F2mQ(int i) {
        boolean z;
        boolean z2;
        Paint.Join join;
        Paint setNativeStrokeJoin = this.internalPaint;
        Intrinsics.checkNotNullParameter(setNativeStrokeJoin, "$this$setNativeStrokeJoin");
        boolean z3 = false;
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            join = Paint.Join.MITER;
        } else {
            if (i == 2) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                join = Paint.Join.BEVEL;
            } else {
                if (i == 1) {
                    z3 = true;
                }
                if (z3) {
                    join = Paint.Join.ROUND;
                } else {
                    join = Paint.Join.MITER;
                }
            }
        }
        setNativeStrokeJoin.setStrokeJoin(join);
    }

    public final void setStrokeMiterLimit(float f) {
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        paint.setStrokeMiter(f);
    }

    public final void setStrokeWidth(float f) {
        Paint paint = this.internalPaint;
        Intrinsics.checkNotNullParameter(paint, "<this>");
        paint.setStrokeWidth(f);
    }

    /* renamed from: setStyle-k9PVt8s  reason: not valid java name */
    public final void m86setStylek9PVt8s(int i) {
        Paint.Style style;
        Paint setNativeStyle = this.internalPaint;
        Intrinsics.checkNotNullParameter(setNativeStyle, "$this$setNativeStyle");
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        if (z) {
            style = Paint.Style.STROKE;
        } else {
            style = Paint.Style.FILL;
        }
        setNativeStyle.setStyle(style);
    }
}
