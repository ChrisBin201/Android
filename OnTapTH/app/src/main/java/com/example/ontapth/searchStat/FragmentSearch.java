package com.example.ontapth.searchStat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapth.R;
import com.example.ontapth.adapter.SearchAdapter;
import com.example.ontapth.db.Database;
import com.example.ontapth.model.Task;

import java.util.List;

public class FragmentSearch extends Fragment {
    private SearchAdapter adapter;
    private SearchView search;
    private RecyclerView recyclerView;
//    private List<Task> mList;
    private Database db;
    public FragmentSearch() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.reViewSearch);
        adapter = new SearchAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        db = new Database(getContext());
        adapter.setList(db.getAllTasks());

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.setList(db.searchByNameOrDes(s));
                return true;
            }
        });

    }
}
