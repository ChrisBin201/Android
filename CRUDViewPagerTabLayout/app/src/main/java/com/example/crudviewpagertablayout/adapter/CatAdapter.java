package com.example.crudviewpagertablayout.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudviewpagertablayout.MainActivity;
import com.example.crudviewpagertablayout.R;
import com.example.crudviewpagertablayout.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private MainActivity mainActivity;
    private List<Cat> listBackup;
    private List<Cat> cats;
    CatItemListener catItemListener;
    public CatAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        cats = new ArrayList<>();
        listBackup = new ArrayList<>();
    }

    public List<Cat> getBackup() {
        return listBackup;
    }

    public void filterList(List<Cat> filterList) {
        cats = filterList;
        notifyDataSetChanged();
    }

    public void setItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = cats.get(position);
        if (cat == null) return;
        holder.imageView.setImageResource(cat.getImage());
        holder.tvName.setText(cat.getName());
        holder.tvDes.setText(cat.getDescribe());
        holder.tvPrice.setText(cat.getPrice() + "$");
        holder.btRemove.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo xoá!");
            builder.setMessage("Bạn có chắc chắn muốn xoá " + cat.getName() + " không?");
            builder.setIcon(R.drawable.remove);
//            builder.setPositiveButton("NO", (dialogInterface, i) -> {
//
//            });
            builder.setPositiveButton("YES", (dialogInterface, i) -> {
                listBackup.remove(position);
                cats.remove(position);
                notifyDataSetChanged();
                mainActivity.list = cats;
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }


    @Override
    public int getItemCount() {
        if (cats != null) return cats.size();
        return 0;
    }

    public Cat getItem(int position) {
        return cats.get(position);
    }
    public List<Cat> getListCat() {
        return cats;
    }

    public void update(int position, Cat cat) {

        listBackup.set(position, cat);
        cats.set(position, cat);
        notifyDataSetChanged();
        mainActivity.list = cats;
    }

    public void add(Cat cat) {
        listBackup.add(cat);
        cats.add(cat);
        notifyDataSetChanged();
        mainActivity.list = cats;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView tvName, tvDes, tvPrice;
        private Button btRemove;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.txtName);
            tvDes = itemView.findViewById(R.id.txtDes);
            tvPrice = itemView.findViewById(R.id.txtPrice);
            btRemove = itemView.findViewById(R.id.btRemove);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (catItemListener != null) catItemListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface CatItemListener {
        void onItemClick(View view, int position);
    }

}
