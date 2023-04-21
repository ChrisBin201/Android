package com.example.datetimepicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText ngayEdt, thoiGianEdt;
    Button ngayBtn, thoiGianBtn;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ngayBtn = findViewById(R.id.button_chonNgay);
        thoiGianBtn = findViewById(R.id.button_chonThoiGian);
        ngayEdt = findViewById(R.id.ngayTxt);
        thoiGianEdt = findViewById(R.id.thoiGianTxt);
        listView = findViewById(R.id.listView);
        String[] st = getResources().getStringArray(R.array.tech);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item1, st);
        listView.setAdapter(adapter);

        ngayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int curYear = c.get(Calendar.YEAR);
                int curMonth = c.get(Calendar.MONTH);
                int curDay = c.get(Calendar.DATE);
                DatePickerDialog diag = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        ngayEdt.setText(d+"/"+m+"/"+y);
                    }
                }, curYear, curMonth, curDay);
                diag.show();


            }

        });

        thoiGianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog diag = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        thoiGianEdt.setText(h+":"+m);
                    }
                }, 6, 53, false);
                diag.show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, adapter.getItem(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}