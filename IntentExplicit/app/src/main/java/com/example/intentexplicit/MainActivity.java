package com.example.intentexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            String name = "Nguyen Van A";
            intent.putExtra("name", name);
            int age = 20;
            intent.putExtra("age", age);
            String[] subjects = {"Android", "Java", "PHP"};
            intent.putExtra("subjects", subjects);
            Student student = new Student(R.drawable.ic_launcher_background, "Nguyen Van B", "22");
            intent.putExtra("student", student);
            List<Student> list = new ArrayList<>();
            list.add(student);
            list.add(new Student(R.drawable.ic_launcher_background, "Nguyen Van C", "28"));
            list.add(new Student(R.drawable.ic_launcher_background, "Nguyen Van D", "30"));
            intent.putExtra("list", (Serializable) list);
            startActivity(intent);
        });
    }
}