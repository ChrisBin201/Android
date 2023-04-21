package com.example.recycleview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.R;
import com.example.recycleview.model.Cat;

import java.util.List;

public class AdapterCat extends RecyclerView.Adapter<AdapterCat.ViewHolderCat>{
    private List<Cat> mList;
    private Context context;
    private ItemListenerCat itemCat;


    public AdapterCat(Context context, List<Cat> mList) {
        this.mList = mList;
        this.context = context;
    }
    public ItemListenerCat getItemCat() {
        return itemCat;
    }
    public Cat getItemAt(int p){
        return mList.get(p);
    }
    public void add(Cat cat){
        mList.add(cat);
        notifyDataSetChanged();
    }
    public void setItemCat(ItemListenerCat itemCat) {
        this.itemCat = itemCat;
    }

    @NonNull
    @Override
    public ViewHolderCat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolderCat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCat holder, int position) {
        Cat cat = mList.get(position);
        holder.img.setImageResource(cat.getImg());
        holder.tv.setText(cat.getName());
//        holder.cardView.setOnClickListener(view -> {
//            Toast.makeText(context, cat.getName(), Toast.LENGTH_SHORT).show();
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolderCat extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView tv;
//        CardView cardView;
        public ViewHolderCat(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.img);
            tv = v.findViewById(R.id.tv_name);
//            cardView = itemView.findViewById(R.id.cardView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemCat.onItemClick(view, getAdapterPosition());
        }
    }
    public interface ItemListenerCat{
        void onItemClick(View view, int position);
    }
}
