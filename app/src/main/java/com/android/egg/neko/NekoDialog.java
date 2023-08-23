package com.android.egg.neko;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.egg.R;
import java.util.ArrayList;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class NekoDialog extends Dialog {
    private final Adapter mAdapter;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    class Adapter extends RecyclerView.Adapter {
        private final Context mContext;
        private final ArrayList mFoods = new ArrayList();

        public Adapter(Context context) {
            this.mContext = context;
            int[] intArray = context.getResources().getIntArray(R.array.food_names);
            for (int i = 1; i < intArray.length; i++) {
                this.mFoods.add(new Food(i));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mFoods.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(final Holder holder, int i) {
            Food food = (Food) this.mFoods.get(i);
            ((ImageView) holder.itemView.findViewById(R.id.icon)).setImageIcon(food.getIcon(this.mContext));
            ((TextView) holder.itemView.findViewById(R.id.text)).setText(food.getName(this.mContext));
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.egg.neko.NekoDialog.Adapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Adapter adapter = Adapter.this;
                    NekoDialog.this.onFoodSelected((Food) adapter.mFoods.get(holder.getAdapterPosition()));
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_layout, viewGroup, false));
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View view) {
            super(view);
        }
    }

    public NekoDialog(Context context) {
        super(context, 16974376);
        RecyclerView recyclerView = new RecyclerView(getContext());
        Adapter adapter = new Adapter(getContext());
        this.mAdapter = adapter;
        getContext();
        recyclerView.setLayoutManager(new GridLayoutManager(2));
        recyclerView.setAdapter(adapter);
        int i = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
        recyclerView.setPadding(i, i, i, i);
        setContentView(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFoodSelected(Food food) {
        PrefState prefState = new PrefState(getContext());
        if (prefState.getFoodState() == 0 && food.getType() != 0) {
            NekoService.registerJob(getContext(), food.getInterval(getContext()));
        }
        prefState.setFoodState(food.getType());
        dismiss();
    }
}
