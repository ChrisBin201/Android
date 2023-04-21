package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sqlite.model.Category;
import com.example.sqlite.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spCate;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spCate = findViewById(R.id.spCate);
        txt = findViewById(R.id.txt);
        Database db = new Database(this);
//        db.insertCate(new Category("Giao duc"));
//        db.insertCate(new Category("Khoa hoc va Cong nghe"));
//        db.insertCate(new Category("PTIT"));
        List<Category> list = db.getCates();
        String[] st =  new String[list.size()];
        int k =0;
        for(Category i:list){
            st[k++] = i.getId()+"-"+i.getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.item,st);
        spCate.setAdapter(adapter);
//        db.insertItem(new Item("Mua he bong", 200, "2/2/1990",
//                new Category(2,"" )));
//        db.insertItem(new Item("LT TB DD", 900, "8/4/2023",
//                new Category(1,"" )));
//        db.insertItem(new Item("Game in unity 3D", 250, "2/2/1999",
//                new Category(1,"" )));
        List<Item> items = db.getItems();
//        db.update(new Item(1,"mua he o PTIT vui lam",400,"1/1/2023",new Category(2,"")));
//        Item i= db.getItemById(1);
//        int[] x =  {1,2};
//        db.delete(x);
        String ss ="Books:";
        for(Item i:items){
            ss+="\n"+ i.getName()+"-"+i.getCategory().getName();
//
        }
        txt.setText(ss);
    }
}