package com.example.ontapth.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapth.R;
import com.example.ontapth.model.Task;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder>{
    private List<Task> list;
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<Task> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Task getItem(int i) {
        return list.get(i);
    }

    @NonNull
    @Override
    public RecycleViewAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.RecycleViewHolder holder, int position) {
        Task task = list.get(position);
        holder.tName.setText(task.getName());
        holder.tDes.setText(task.getDescription());
        holder.tDate.setText(task.getDate());
        holder.tStatus.setText(task.getStatus());
        holder.tCollab.setText(task.isCollab() ?"Collab" : "Not Collab");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         TextView tName, tDes, tDate, tStatus, tCollab;
//        private RatingBar rating_bar;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.tName);
            tDes = itemView.findViewById(R.id.tDes);
            tDate = itemView.findViewById(R.id.tDate);
            tStatus = itemView.findViewById(R.id.tStatus);
            tCollab = itemView.findViewById(R.id.tCollab);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemListener != null) {
                itemListener.OnItemClick(v, getAdapterPosition());
            }

        }
    }

    public interface ItemListener {
        void OnItemClick(View view, int p);
    }
}
