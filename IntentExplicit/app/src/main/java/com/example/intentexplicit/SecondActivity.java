package com.example.intentexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private Button bt;
    private TextView tvName, tvSub, tvSt,tvList;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bt = findViewById(R.id.bt);
        tvName = findViewById(R.id.tvName);
        tvSub = findViewById(R.id.tvSub);
        tvSt = findViewById(R.id.tvSt);
        tvList = findViewById(R.id.tvList);
        img = findViewById(R.id.img);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);
        String[] subjects = intent.getStringArrayExtra("subjects");
        tvName.setText(name+", "+age);
        tvSub.setText(Arrays.toString(subjects));
        Student student = (Student) intent.getSerializableExtra("student");
        img.setImageResource(student.getImg());
        tvSt.setText(student.getName()+", "+student.getAge());
        List<Student> list = (List<Student>) intent.getSerializableExtra("list");
        String tt ="";
        for(Student st : list){
            tt += st.getName()+", "+st.getAge()+"\n";
        }
        tvList.setText(tt);
        bt.setOnClickListener(v -> {
            finish();
        });
    }
}