package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView ;
    private List<Student> studentList;
    private ArrayAdapter<Student> studentArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        studentList = new ArrayList<>();
        studentList.add(new Student("Lê Tuấn Minh", "B19DCCN430"));
        studentList.add(new Student("Nguyễn Xuân Quý", "B19DCCN538"));
        studentList.add(new Student("Trần Bình Minh", "B19DCCN422"));

        studentArrayAdapter = new ArrayAdapter<>(this,R.layout.list_item_person);
        StudentAdapter adapter = new StudentAdapter(this, studentList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student person = (Student) adapterView.getItemAtPosition(i);
                Toast.makeText(MainActivity.this, person.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
