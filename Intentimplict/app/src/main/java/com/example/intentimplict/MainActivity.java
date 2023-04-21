package com.example.intentimplict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt1,bt2,bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt1.setOnClickListener(view -> {
            Intent t = new Intent(Intent.ACTION_VIEW);
            t.setData(Uri.parse("https://www.youtube.com/playlist?list=PLD8zSU7U1L2GVhpPIUlJegpP8HRC2r58w"));
            startActivity(t);
        });
        bt2.setOnClickListener(view -> {
            Intent t = new Intent(Intent.ACTION_VIEW);
            t.setData(Uri.parse("sms: 096677028"));
            t.putExtra("sms_body:","hoi tham");
            startActivity(t);
        });
        bt3.setOnClickListener(view -> {
            Intent t = new Intent(Intent.ACTION_DIAL);
            t.setData(Uri.parse("tel: 096677028"));
            startActivity(t);
        });
    }
}