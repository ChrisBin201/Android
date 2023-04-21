package com.example.ontapth.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapth.R;
import com.example.ontapth.model.Task;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<Task> mSearch;

    public SearchAdapter() {
        this.mSearch = new ArrayList<>();
    }

    public void setList(List<Task> mSearch) {
        this.mSearch = mSearch;
        notifyDataSetChanged();
    }

    public List<Task> getList() {
        return mSearch;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Task task = mSearch.get(position);
        holder.tName.setText(task.getName());
        holder.tDes.setText(task.getDescription());
        holder.tDate.setText(task.getDate());
        holder.tStatus.setText(task.getStatus());
        holder.tCollab.setText(task.isCollab() ?"Collab" : "Not Collab");
    }

    @Override
    public int getItemCount() {
        return mSearch.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
//        ImageView img;
        private TextView tName, tDes, tDate, tStatus, tCollab;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.tName);
            tDes = itemView.findViewById(R.id.tDes);
            tDate = itemView.findViewById(R.id.tDate);
            tStatus = itemView.findViewById(R.id.tStatus);
            tCollab = itemView.findViewById(R.id.tCollab);
        }
    }
}
