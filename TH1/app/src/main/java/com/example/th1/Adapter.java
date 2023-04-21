package com.example.th1;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.PViewHolder> {
    private Context context;
    private List<Project> projectList;

    public Adapter(Context context) {
        this.context = context;
        this.projectList = new ArrayList<>();
    }


    @NonNull
    @Override
    public PViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new PViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PViewHolder holder, int position) {
        Project project = projectList.get(position);
        if (project == null) return;
//        holder.imageView.setImageResource(cat.getImage());
        holder.tvName.setText(project.getName());
        holder.tvEndDate.setText(project.getEndDate());
        holder.tvStartDate.setText(project.getStartDate());
        holder.checkBoxStatus.setChecked(project.isCompleted());
        holder.btRemove.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo xoá!");
            builder.setMessage("Bạn có chắc chắn muốn xoá project" + project.getName() + " không?");
            builder.setIcon(R.drawable.remove);
//            builder.setPositiveButton("NO", (dialogInterface, i) -> {
//
//            });
            builder.setPositiveButton("YES", (dialogInterface, i) -> {
//                listBackup.remove(position);
                projectList.remove(position);
                notifyDataSetChanged();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }

    @Override
    public int getItemCount() {
        if (projectList != null) return projectList.size();
        return 0;
    }

    public void add(Project project) {
//        listBackup.add(cat);
        projectList.add(project);
        notifyDataSetChanged();
    }

public class PViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName, tvStartDate, tvEndDate;
    private Button btRemove;
    private CheckBox checkBoxStatus;

    public PViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.txtName);
        tvStartDate = itemView.findViewById(R.id.txtStartDate);
        tvEndDate = itemView.findViewById(R.id.txtEndDate);
        checkBoxStatus = itemView.findViewById(R.id.checkBoxStatus);
        btRemove = itemView.findViewById(R.id.btRemove);
//        itemView.setOnClickListener(this);
    }

}
}
