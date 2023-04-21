package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListLayout extends AppCompatActivity {
    ArrayList<RadioButton> rbs;
    Spinner sp;
    Button submit;
    RatingBar rt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        initSpinner();
        init();
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String choose = "";
                int rating = 1;
                String languge = "";

                for(int i = 0; i< rbs.size(); i++){
                    if(rbs.get(i).isChecked()){
                        choose = rbs.get(i).getText().toString();
                    }
                }

                rating = (int)rt.getRating();
                languge = sp.getSelectedItem().toString();

                System.out.printf("Choose:%s\nRating:%d\nLanguge:%s\n", choose, rating,languge);
            }
        });
    }


    private void initSpinner(){
        String[] list = getResources().getStringArray(R.array.list_pro);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item,list);
        sp = findViewById(R.id.sp);
        sp.setAdapter(adapter);
    }

    private  void init(){
        rt = findViewById(R.id.rt);
        submit = findViewById(R.id.sb);
       rbs = new ArrayList<>();
       rbs.add( findViewById(R.id.rb1));
       rbs.add( findViewById(R.id.rb2));
       rbs.add( findViewById(R.id.rb3));
       rbs.add( findViewById(R.id.rb4));

    }
}