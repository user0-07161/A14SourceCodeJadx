package com.android.egg.quares;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import com.android.egg.R;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class QuaresActivity extends Activity {
    public static final int $stable = 8;
    private GridLayout grid;
    private Icon icon;
    private Button label;
    private int resId;
    private Quare q = new Quare(16, 16, 1);
    private String resName = "";

    public final void checkVictory() {
        Drawable drawable;
        if (this.q.check()) {
            float f = getResources().getDisplayMetrics().density;
            View findViewById = findViewById(R.id.label);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.label)");
            Button button = (Button) findViewById;
            button.setText(new Regex("^.*/").replace(this.resName));
            Icon icon = this.icon;
            if (icon != null && (drawable = icon.loadDrawable(this)) != null) {
                int i = (int) (32 * f);
                drawable.setBounds(0, 0, i, i);
                drawable.setTint(button.getCurrentTextColor());
            } else {
                drawable = null;
            }
            button.setCompoundDrawables(drawable, null, null, null);
            button.setVisibility(0);
            return;
        }
        Button button2 = this.label;
        if (button2 != null) {
            button2.setVisibility(8);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("label");
            throw null;
        }
    }

    public final String getPackageNameForResourceName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (StringsKt.contains$default(name) && !name.startsWith("android:")) {
            String substring = name.substring(0, StringsKt.indexOf$default((CharSequence) name, ":", 0, false, 6));
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        String packageName = getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "{\n            packageName\n        }");
        return packageName;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01ab A[LOOP:1: B:21:0x00d3->B:58:0x01ab, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01c0 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v12, types: [com.android.egg.quares.PixelButton, android.widget.CompoundButton, android.view.View] */
    /* JADX WARN: Type inference failed for: r13v3, types: [com.android.egg.quares.ClueView] */
    /* JADX WARN: Type inference failed for: r13v4, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r13v5, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r4v12, types: [android.widget.GridLayout] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void loadPuzzle() {
        /*
            Method dump skipped, instructions count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.quares.QuaresActivity.loadPuzzle():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x007c, code lost:
        r7.resId = r5;
        r7.resName = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void newPuzzle() {
        /*
            r7 = this;
            java.lang.String r0 = "new puzzle..."
            java.lang.String r1 = "Quares"
            android.util.Log.v(r1, r0)
            com.android.egg.quares.Quare r0 = r7.q
            r0.resetUserMarks()
            int r0 = r7.resId
            r2 = 17301642(0x108008a, float:2.4979642E-38)
            r7.resId = r2
            r2 = 0
        L14:
            r3 = 4
            if (r2 >= r3) goto L8a
            android.content.res.Resources r3 = r7.getResources()     // Catch: java.lang.RuntimeException -> L84
            r4 = 2130771980(0x7f01000c, float:1.7147065E38)
            android.content.res.TypedArray r3 = r3.obtainTypedArray(r4)     // Catch: java.lang.RuntimeException -> L84
            java.util.Random r4 = new java.util.Random     // Catch: java.lang.RuntimeException -> L84
            r4.<init>()     // Catch: java.lang.RuntimeException -> L84
            int r5 = r3.length()     // Catch: java.lang.RuntimeException -> L84
            int r4 = r4.nextInt(r5)     // Catch: java.lang.RuntimeException -> L84
            java.lang.String r3 = r3.getString(r4)     // Catch: java.lang.RuntimeException -> L84
            if (r3 == 0) goto L81
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.RuntimeException -> L84
            r4.<init>()     // Catch: java.lang.RuntimeException -> L84
            java.lang.String r5 = "Looking for icon "
            r4.append(r5)     // Catch: java.lang.RuntimeException -> L84
            r4.append(r3)     // Catch: java.lang.RuntimeException -> L84
            java.lang.String r4 = r4.toString()     // Catch: java.lang.RuntimeException -> L84
            android.util.Log.v(r1, r4)     // Catch: java.lang.RuntimeException -> L84
            java.lang.String r4 = r7.getPackageNameForResourceName(r3)     // Catch: java.lang.RuntimeException -> L84
            android.content.pm.PackageManager r5 = r7.getPackageManager()     // Catch: java.lang.RuntimeException -> L84
            android.content.res.Resources r5 = r5.getResourcesForApplication(r4)     // Catch: java.lang.RuntimeException -> L84
            java.lang.String r6 = "drawable"
            int r5 = r5.getIdentifier(r3, r6, r4)     // Catch: java.lang.RuntimeException -> L84
            if (r5 != 0) goto L7a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.RuntimeException -> L84
            r5.<init>()     // Catch: java.lang.RuntimeException -> L84
            java.lang.String r6 = "oops, "
            r5.append(r6)     // Catch: java.lang.RuntimeException -> L84
            r5.append(r3)     // Catch: java.lang.RuntimeException -> L84
            java.lang.String r3 = " doesn't resolve from pkg "
            r5.append(r3)     // Catch: java.lang.RuntimeException -> L84
            r5.append(r4)     // Catch: java.lang.RuntimeException -> L84
            java.lang.String r3 = r5.toString()     // Catch: java.lang.RuntimeException -> L84
            android.util.Log.v(r1, r3)     // Catch: java.lang.RuntimeException -> L84
            goto L81
        L7a:
            if (r5 == r0) goto L81
            r7.resId = r5     // Catch: java.lang.RuntimeException -> L84
            r7.resName = r3     // Catch: java.lang.RuntimeException -> L84
            goto L8a
        L81:
            int r2 = r2 + 1
            goto L14
        L84:
            r0 = move-exception
            java.lang.String r2 = "problem loading puzzle, using fallback"
            android.util.Log.v(r1, r2, r0)
        L8a:
            r7.loadPuzzle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.quares.QuaresActivity.newPuzzle():void");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(768);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_quares);
        View findViewById = findViewById(R.id.grid);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.grid)");
        this.grid = (GridLayout) findViewById;
        View findViewById2 = findViewById(R.id.label);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.label)");
        this.label = (Button) findViewById2;
        if (bundle != null) {
            Log.v(QuaresActivityKt.TAG, "restoring puzzle from state");
            Quare quare = (Quare) bundle.getParcelable("q");
            if (quare == null) {
                quare = this.q;
            }
            this.q = quare;
            this.resId = bundle.getInt("resId");
            String string = bundle.getString("resName", "");
            Intrinsics.checkNotNullExpressionValue(string, "savedInstanceState.getString(\"resName\", \"\")");
            this.resName = string;
            loadPuzzle();
        }
        Button button = this.label;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.android.egg.quares.QuaresActivity$onCreate$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QuaresActivity.this.newPuzzle();
                }
            });
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("label");
            throw null;
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.resId == 0) {
            newPuzzle();
        }
        checkVictory();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putParcelable("q", this.q);
        outState.putInt("resId", this.resId);
        outState.putString("resName", this.resName);
    }
}
