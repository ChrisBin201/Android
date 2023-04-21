package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.model.Techology;

public class TechologyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techology);

        ListView ls = findViewById(R.id.list);
        Techology[] data = initData();
        ls.setAdapter(new TechologyAdapter(this, data));
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Techology tech =  data[i];
                Toast.makeText(getApplicationContext(),tech.getName()+"\n"
                        +tech.getDesc(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Techology[] initData(){
        Techology[] list = new Techology[4];
        int imgs[] = {R.drawable.cplus, R.drawable.js, R.drawable.java,
                R.drawable.golang};
        String name[] = {"C++", "JS", "Java", "Golang"};
        String subs[] = {"sC++", "sJS", "sJava", "sGolang"};
        String descs[] = {"dC++", "dJS", "dJava", "dGolang"};
        for(int i = 0; i<4;i++){
            list[i] = new Techology(name[i],subs[i],descs[i],imgs[i]);
        }
        return list;
    }
}