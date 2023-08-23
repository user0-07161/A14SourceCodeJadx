package com.android.egg.neko;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.egg.R;
import com.android.egg.neko.PrefState;
import com.android.internal.logging.MetricsLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class NekoLand extends Activity implements PrefState.PrefsListener {
    private static boolean CAT_GEN = false;
    public static String CHAN_ID = "EGG";
    public static boolean DEBUG = false;
    public static boolean DEBUG_NOTIFICATIONS = false;
    private static final int EXPORT_BITMAP_SIZE = 600;
    private static final int STORAGE_PERM_REQUEST = 123;
    private CatAdapter mAdapter;
    private Cat mPendingShareCat;
    private PrefState mPrefs;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public class CatAdapter extends RecyclerView.Adapter {
        private Cat[] mCats;

        /* synthetic */ CatAdapter(NekoLand nekoLand, int i) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContextGroupVisible(final CatHolder catHolder, boolean z) {
            final View view = catHolder.contextGroup;
            if (z && view.getVisibility() != 0) {
                view.setAlpha(0.0f);
                view.setVisibility(0);
                view.animate().alpha(1.0f).setDuration(333L);
                Runnable runnable = new Runnable() { // from class: com.android.egg.neko.NekoLand.CatAdapter.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CatAdapter.this.setContextGroupVisible(catHolder, false);
                    }
                };
                view.setTag(runnable);
                view.postDelayed(runnable, 5000L);
            } else if (!z && view.getVisibility() == 0) {
                view.removeCallbacks((Runnable) view.getTag());
                view.animate().alpha(0.0f).setDuration(250L).withEndAction(new Runnable() { // from class: com.android.egg.neko.NekoLand.CatAdapter.2
                    @Override // java.lang.Runnable
                    public void run() {
                        view.setVisibility(4);
                    }
                });
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mCats.length;
        }

        public void setCats(Cat[] catArr) {
            this.mCats = catArr;
            notifyDataSetChanged();
        }

        private CatAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(final CatHolder catHolder, final int i) {
            Context context = catHolder.itemView.getContext();
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.neko_display_size);
            catHolder.imageView.setImageIcon(this.mCats[i].createIcon(context, dimensionPixelSize, dimensionPixelSize));
            catHolder.textView.setText(this.mCats[i].getName());
            catHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.egg.neko.NekoLand.CatAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CatAdapter catAdapter = CatAdapter.this;
                    NekoLand.this.onCatClick(catAdapter.mCats[catHolder.getAdapterPosition()]);
                }
            });
            catHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.egg.neko.NekoLand.CatAdapter.4
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    CatAdapter.this.setContextGroupVisible(catHolder, true);
                    return true;
                }
            });
            catHolder.delete.setOnClickListener(new View.OnClickListener() { // from class: com.android.egg.neko.NekoLand.CatAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CatAdapter.this.setContextGroupVisible(catHolder, false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(NekoLand.this);
                    CatAdapter catAdapter = CatAdapter.this;
                    builder.setTitle(NekoLand.this.getString(R.string.confirm_delete, new Object[]{catAdapter.mCats[i].getName()})).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.android.egg.neko.NekoLand.CatAdapter.5.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            CatAdapter catAdapter2 = CatAdapter.this;
                            NekoLand.this.onCatRemove(catAdapter2.mCats[catHolder.getAdapterPosition()]);
                        }
                    }).show();
                }
            });
            catHolder.share.setOnClickListener(new View.OnClickListener() { // from class: com.android.egg.neko.NekoLand.CatAdapter.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CatAdapter.this.setContextGroupVisible(catHolder, false);
                    Cat cat = CatAdapter.this.mCats[catHolder.getAdapterPosition()];
                    if (NekoLand.this.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                        NekoLand.this.mPendingShareCat = cat;
                        NekoLand.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, NekoLand.STORAGE_PERM_REQUEST);
                        return;
                    }
                    NekoLand.this.shareCat(cat);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public CatHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new CatHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cat_view, viewGroup, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public class CatHolder extends RecyclerView.ViewHolder {
        private final View contextGroup;
        private final View delete;
        private final ImageView imageView;
        private final View share;
        private final TextView textView;

        public CatHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(16908294);
            this.textView = (TextView) view.findViewById(16908310);
            this.contextGroup = view.findViewById(R.id.contextGroup);
            this.delete = view.findViewById(16908327);
            this.share = view.findViewById(16908341);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCatClick(Cat cat) {
        if (CAT_GEN) {
            this.mPrefs.addCat(cat);
            new AlertDialog.Builder(this).setTitle("Cat added").setPositiveButton(17039370, (DialogInterface.OnClickListener) null).show();
            return;
        }
        showNameDialog(cat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCatRemove(Cat cat) {
        cat.logRemove(this);
        this.mPrefs.removeCat(cat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shareCat(Cat cat) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Cats");
        if (!file.exists() && !file.mkdirs()) {
            Log.e("NekoLand", "save: error: can't create Pictures directory");
            return;
        }
        File file2 = new File(file, cat.getName().replaceAll("[/ #:]+", "_") + ".png");
        Bitmap createBitmap = cat.createBitmap(EXPORT_BITMAP_SIZE, EXPORT_BITMAP_SIZE);
        if (createBitmap != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                createBitmap.compress(Bitmap.CompressFormat.PNG, 0, fileOutputStream);
                fileOutputStream.close();
                MediaScannerConnection.scanFile(this, new String[]{file2.toString()}, new String[]{"image/png"}, null);
                Log.v("Neko", "cat file: " + file2);
                Uri uriForFile = FileProvider.getUriForFile(this, file2);
                Log.v("Neko", "cat uri: " + uriForFile);
                Intent intent = new Intent("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.STREAM", uriForFile);
                intent.putExtra("android.intent.extra.SUBJECT", cat.getName());
                intent.addFlags(1);
                intent.setType("image/png");
                startActivity(Intent.createChooser(intent, null).addFlags(1));
                cat.logShare(this);
            } catch (IOException e) {
                Log.e("NekoLand", "save: error: " + e);
            }
        }
    }

    private void showNameDialog(final Cat cat) {
        final ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this, 16974396);
        View inflate = LayoutInflater.from(contextThemeWrapper).inflate(R.layout.edit_text, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(16908291);
        editText.setText(cat.getName());
        editText.setSelection(cat.getName().length());
        int dimensionPixelSize = contextThemeWrapper.getResources().getDimensionPixelSize(17104896);
        new AlertDialog.Builder(contextThemeWrapper).setTitle(" ").setIcon(cat.createIcon(this, dimensionPixelSize, dimensionPixelSize).loadDrawable(this)).setView(inflate).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.android.egg.neko.NekoLand.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                cat.logRename(contextThemeWrapper);
                cat.setName(editText.getText().toString().trim());
                NekoLand.this.mPrefs.addCat(cat);
            }
        }).show();
    }

    private int updateCats() {
        Cat[] catArr;
        if (CAT_GEN) {
            catArr = new Cat[50];
            for (int i = 0; i < 50; i++) {
                catArr[i] = Cat.create(this);
            }
        } else {
            final float[] fArr = new float[3];
            List cats = this.mPrefs.getCats();
            Collections.sort(cats, new Comparator() { // from class: com.android.egg.neko.NekoLand.1
                @Override // java.util.Comparator
                public int compare(Cat cat, Cat cat2) {
                    Color.colorToHSV(cat.getBodyColor(), fArr);
                    float f = fArr[0];
                    Color.colorToHSV(cat2.getBodyColor(), fArr);
                    return Float.compare(f, fArr[0]);
                }
            });
            catArr = (Cat[]) cats.toArray(new Cat[0]);
        }
        this.mAdapter.setCats(catArr);
        return catArr.length;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.neko_activity);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setLogo(Cat.create(this));
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        PrefState prefState = new PrefState(this);
        this.mPrefs = prefState;
        prefState.setListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.holder);
        CatAdapter catAdapter = new CatAdapter(this, 0);
        this.mAdapter = catAdapter;
        recyclerView.setAdapter(catAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(3));
        MetricsLogger.histogram(this, "egg_neko_visit_gallery", updateCats());
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mPrefs.setListener(null);
    }

    @Override // com.android.egg.neko.PrefState.PrefsListener
    public void onPrefsChanged() {
        updateCats();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Cat cat;
        if (i == STORAGE_PERM_REQUEST && (cat = this.mPendingShareCat) != null) {
            shareCat(cat);
            this.mPendingShareCat = null;
        }
    }
}
