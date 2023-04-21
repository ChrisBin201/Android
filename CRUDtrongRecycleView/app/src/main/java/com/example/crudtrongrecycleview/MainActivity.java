package com.example.crudtrongrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crudtrongrecycleview.adapter.CatAdapter;
import com.example.crudtrongrecycleview.model.Cat;
import com.example.crudtrongrecycleview.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CatAdapter.CartItemListener, SearchView.OnQueryTextListener {
    public Spinner spinner;
    private int[] imgs = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5};
    private RecyclerView recyclerView;
    private CatAdapter adapter;
    private EditText eName, eDescribe, ePrice;
    private Button btnAdd, btnUpdate;
    private int pcurr;

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.img);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this);
        spinner.setAdapter(spinnerAdapter);
        recyclerView = findViewById(R.id.recycleView);
        eName = findViewById(R.id.name);
        eDescribe = findViewById(R.id.describe);
        ePrice = findViewById(R.id.price);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);

        adapter = new CatAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                btnAdd.setEnabled(false);
//                btnUpdate.setEnabled(true);
                Cat cat = new Cat();
                String i = spinner.getSelectedItem().toString();
                String name = eName.getText().toString();
                String describe = eDescribe.getText().toString();
                String p = ePrice.getText().toString();
                int img = R.drawable.img1;
                double price = 0;
                try {
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    cat.setImage(img);
                    cat.setName(name);
                    cat.setDescribe(describe);
                    cat.setPrice(price);
                    adapter.add(cat);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    Toast.makeText(getApplicationContext(), "nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cat cat = new Cat();
                String i = spinner.getSelectedItem().toString();
                String name = eName.getText().toString();
                String describe = eDescribe.getText().toString();
                String p = ePrice.getText().toString();
                int img = R.drawable.img1;
                double price = 0;
                try {
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    cat.setImage(img);
                    cat.setName(name);
                    cat.setDescribe(describe);
                    cat.setPrice(price);
                    adapter.update(pcurr, cat);
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    Toast.makeText(getApplicationContext(), "nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        pcurr = position;
        Cat cat = adapter.getItem(position);
        int img = cat.getImage();
        int p = 0;
        for (int i = 0; i < imgs.length; i++) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
        spinner.setSelection(p);
        eName.setText(cat.getName());
        eDescribe.setText(cat.getDescribe());
        ePrice.setText(cat.getPrice() + "$");
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    private void filter(String s) {
        List<Cat> filterList = new ArrayList<>();
        for (Cat i : adapter.getBackup())
            if (i.getName().toLowerCase().contains(s.toLowerCase()))
                filterList.add(i);
        if (filterList.isEmpty())
            Toast.makeText(this, "không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
        else adapter.filterList(filterList);
    }
}