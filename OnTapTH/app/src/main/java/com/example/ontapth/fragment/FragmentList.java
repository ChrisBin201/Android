package com.example.ontapth.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapth.AddActivity;
import com.example.ontapth.EditActivity;
import com.example.ontapth.R;
import com.example.ontapth.adapter.RecycleViewAdapter;
import com.example.ontapth.db.Database;
import com.example.ontapth.model.Task;

import java.io.Serializable;
import java.util.List;

public class FragmentList extends Fragment implements RecycleViewAdapter.ItemListener{
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private Database db;
    public FragmentList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        db = new Database(getContext());
        adapter = new RecycleViewAdapter();

        List<Task> list = db.getAllTasks();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);

    }

    @Override
    public void OnItemClick(View view, int p) {
        Task task = adapter.getItem(p);
        Intent intent = new Intent(getActivity(), EditActivity.class);
        intent.putExtra("data",  task);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Task> list = db.getAllTasks();
        adapter.setList(list);
    }
}
