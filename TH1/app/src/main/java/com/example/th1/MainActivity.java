package com.example.th1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Adapter adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);
        Button btAdd = findViewById(R.id.btAdd);
        Button btUpdate = findViewById(R.id.btUpdate);
        EditText eName = findViewById(R.id.name);
        EditText eStartDate = findViewById(R.id.startDate);
        EditText eEndDate = findViewById(R.id.endDate);
        Button btStartDate = findViewById(R.id.btStartDate);
        Button btEndDate = findViewById(R.id.btEndDate);
        CheckBox checkBox = findViewById(R.id.checkBox);


        btStartDate.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            int curYear = c.get(Calendar.YEAR);
            int curMonth = c.get(Calendar.MONTH);
            int curDay = c.get(Calendar.DATE);
            DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, (DatePickerDialog.OnDateSetListener) (datePicker, y, m, d) -> {
                eStartDate.setText(d+"/"+m+"/"+y);
            },curYear, curMonth, curDay);
            dialog.show();
        });

        btEndDate.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            int curYear = c.get(Calendar.YEAR);
            int curMonth = c.get(Calendar.MONTH);
            int curDay = c.get(Calendar.DATE);
            DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, (DatePickerDialog.OnDateSetListener) (datePicker, y, m, d) -> {
                eEndDate.setText(d+"/"+m+"/"+y);
            },curYear, curMonth, curDay);
            dialog.show();
        });

        btAdd.setOnClickListener(view -> {
            try{
                Project project = new Project();
                project.setName(eName.getText().toString());
                project.setStartDate(eStartDate.getText().toString());
                project.setEndDate(eEndDate.getText().toString());
                project.setCompleted(checkBox.isChecked());
                if(eName.getText().toString().isEmpty() || eStartDate.getText().toString().isEmpty() ||
                        eEndDate.getText().toString().isEmpty()
                ){
                    Toast.makeText(getApplicationContext(),"Nhập lại",Toast.LENGTH_SHORT).show();
                    return;
                }
                adapter.add(project);

//                cat.setImage(imgs[spinnerImg.getSelectedItemPosition()]);
//                catAdapter.add(cat);
            } catch (Exception e) {
                System.out.println(e);
                Toast.makeText(getApplicationContext(),"Nhập lại",Toast.LENGTH_SHORT).show();
            }
        });
    }
}