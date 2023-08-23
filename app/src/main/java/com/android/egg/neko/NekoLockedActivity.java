package com.android.egg.neko;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class NekoLockedActivity extends Activity implements DialogInterface.OnDismissListener {
    private NekoDialog mDialog;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(524288);
        getWindow().addFlags(4194304);
        getWindow().addFlags(128);
        getWindow().addFlags(2097152);
        NekoDialog nekoDialog = new NekoDialog(this);
        this.mDialog = nekoDialog;
        nekoDialog.setOnDismissListener(this);
        this.mDialog.show();
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        finish();
    }
}
