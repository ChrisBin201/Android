package com.example.intentimplict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {
    Intent t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t = new Intent(this,MyService.class);

    }
    public void start(View v) {
        t.putExtra("r",1);
        startService(t);
    }
    public void stop(View v){
        stopService(t);
    }
}