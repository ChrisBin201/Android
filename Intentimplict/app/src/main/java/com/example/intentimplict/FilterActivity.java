package com.example.intentimplict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class FilterActivity extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        txt = findViewById(R.id.txt);
//        Uri url =  getIntent().getData();
//        String st = "Scheme:"+url.getScheme()+"\n host:"+url.getHost();
//        int k =1;
//        for(String i: url.getPathSegments()){
//            st+="\n segment "+(k++)+i;
//        }
//        txt.setText(st);
        String s = "Content:"+getIntent().getStringExtra("sms_body");
        String pNumber = getIntent().getDataString();
        s+="\n pattern: "+pNumber;
        txt.setText(s);
    }
}