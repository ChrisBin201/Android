package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud.adapter.AdapterSpinner;
import com.example.crud.adapter.CatAdapter;
import com.example.crud.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerImg;
    private AdapterSpinner adapterImg;
    private CatAdapter catAdapter;
    private int pcurr;
    int[] imgs = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapterImg = new AdapterSpinner(this, imgs);
        spinnerImg.setAdapter(adapterImg);
        RecyclerView recyclerView = findViewById(R.id.rView);
        Button btAdd = findViewById(R.id.btAdd);
        Button btUpdate = findViewById(R.id.btUpdate);
        EditText eName = findViewById(R.id.name);
        EditText eDes = findViewById(R.id.describe);
        EditText ePrice = findViewById(R.id.price);
        SearchView search = findViewById(R.id.search);
        catAdapter = new CatAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(catAdapter);
        catAdapter.setClickListener((view, position) -> {
            btAdd.setEnabled(false);
            btUpdate.setEnabled(true);
            pcurr = position;
            Cat cat = catAdapter.getItem(position);
            int img = cat.getImage();
            int p = 0;
            for (int i = 0; i < imgs.length; i++) {
                if (img == imgs[i]) {
                    p = i;
                    System.out.println(p);
                    break;
                }
            }
            spinnerImg.setSelection(p);
            eName.setText(cat.getName());
            eDes.setText(cat.getDescribe());
            ePrice.setText(cat.getPrice() + "$");
        });

        btAdd.setOnClickListener(view -> {
            try{
                Cat cat = new Cat();
                cat.setName(eName.getText().toString());
                cat.setDescribe(eDes.getText().toString());
                cat.setPrice(Double.parseDouble(ePrice.getText().toString()));
                cat.setImage(imgs[spinnerImg.getSelectedItemPosition()]);
                catAdapter.add(cat);
            } catch (NumberFormatException e) {
                System.out.println(e);
                Toast.makeText(getApplicationContext(),"Nhập lại",Toast.LENGTH_SHORT).show();
            }
        });

        btUpdate.setOnClickListener(view -> {
            try{
                Cat cat = new Cat();
                cat.setName(eName.getText().toString());
                cat.setDescribe(eDes.getText().toString());
                cat.setPrice(Double.parseDouble(ePrice.getText().toString()));
                cat.setImage(imgs[spinnerImg.getSelectedItemPosition()]);
                catAdapter.update(pcurr, cat);
                btAdd.setEnabled(true);
                btUpdate.setEnabled(false);
            } catch (NumberFormatException e) {
                System.out.println(e);
                Toast.makeText(getApplicationContext(),"Nhập lại",Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

    }
    private void initView() {
        spinnerImg = findViewById(R.id.spinnerImg);
    }
    private void filter(String s) {
        List<Cat> filterList = new ArrayList<>();
        for (Cat i : catAdapter.getBackup())
            if (i.getName().toLowerCase().contains(s.toLowerCase()))
                filterList.add(i);
        if (filterList.isEmpty())
            Toast.makeText(this, "không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
        else catAdapter.filterList(filterList);
    }
}