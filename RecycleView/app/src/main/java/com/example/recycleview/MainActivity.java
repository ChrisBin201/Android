package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.recycleview.adapter.AdapterCat;
import com.example.recycleview.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterCat.ItemListenerCat {
    RecyclerView rView;
    Button btAdd;
    AdapterCat adapter;
    List<Cat> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rView = findViewById(R.id.rView);
        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(view -> {
            Cat cat = new Cat(R.drawable.img2, "Cat moi");
            adapter.add(cat);
        });
        getListCat();
        adapter = new AdapterCat(this,list);
        adapter.setItemCat(this);
        rView.setAdapter(adapter);
//        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        rView.setLayoutManager(manager);
    }

    private void getListCat(){
        list = new ArrayList<>();
        list.add(new Cat(R.drawable.img1,"Cat 1"));
        list.add(new Cat(R.drawable.img2,"Cat 2"));
        list.add(new Cat(R.drawable.img3,"Cat 3"));
        list.add(new Cat(R.drawable.img4,"Cat 4"));
        list.add(new Cat(R.drawable.img5,"Cat 5"));
        list.add(new Cat(R.drawable.img2,"Cat 6"));
    }

    @Override
    public void onItemClick(View view, int position) {
        Cat cat = adapter.getItemAt(position);
        Toast.makeText(this, cat.getName(), Toast.LENGTH_SHORT).show();
    }
}