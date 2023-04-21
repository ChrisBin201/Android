package com.example.crudtrongrecycleview.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudtrongrecycleview.R;
import com.example.crudtrongrecycleview.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private Context context;
    private List<Cat> listBackup;
    private List<Cat> cats;
    private CartItemListener cartItemListener;

    public CatAdapter(Context context) {
        this.context = context;
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

    public void setClickListener(CartItemListener cartItemListener) {
        this.cartItemListener = cartItemListener;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = cats.get(position);
        if (cat == null) return;
        holder.imageView.setImageResource(cat.getImage());
        holder.tvName.setText(cat.getName());
        holder.tvDescribe.setText(cat.getDescribe());
        holder.tvPrice.setText(cat.getPrice() + "$");
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo xoá!");
                builder.setMessage("Bạn có chắc chắn muốn xoá " + cat.getName() + " không?");
                builder.setIcon(R.drawable.remove);
                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listBackup.remove(position);
                        cats.remove(position);
                        notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void add(Cat cat) {
        listBackup.add(cat);
        cats.add(cat);
        notifyDataSetChanged();
    }

    public void update(int position, Cat cat) {

        listBackup.set(position, cat);
        cats.set(position, cat);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (cats != null) return cats.size();
        return 0;
    }

    public Cat getItem(int position) {
        return cats.get(position);
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView tvName, tvDescribe, tvPrice;
        private Button btnRemove;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.txtName);
            tvDescribe = itemView.findViewById(R.id.txtDescribe);
            tvPrice = itemView.findViewById(R.id.txtPrice);
            btnRemove = itemView.findViewById(R.id.btnRemote);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (cartItemListener != null) cartItemListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface CartItemListener {
        void onItemClick(View view, int position);
    }
}
