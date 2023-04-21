package com.example.ontapth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ontapth.db.Database;
import com.example.ontapth.model.Task;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eName, eDes, eDate;
    private Button btnUpdate, btnRemove, btnBack;
    private CheckBox checkCollab;
    //    private RatingBar rating_bar;
    private RadioGroup radio_group;
    private Task task;
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        eName = findViewById(R.id.eName);
        eDes = findViewById(R.id.eDes);
        eDate = findViewById(R.id.eDate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRemove = findViewById(R.id.btnRemove);
        btnBack = findViewById(R.id.btnBack);
        checkCollab = findViewById(R.id.checkCollab);
        radio_group = findViewById(R.id.radio_group);
        btnUpdate.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        eDate.setOnClickListener(this);

        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("data");

        eName.setText(task.getName());
        eDes.setText(task.getDescription());
        eDate.setText(task.getDate());
        checkCollab.setChecked(task.isCollab());

        String status = task.getStatus();
        int count = radio_group.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = radio_group.getChildAt(i);
            if (v instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) v;
                if (radioButton.getText().equals(status)) {
                    radioButton.setChecked(true);
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v == eDate) {
            final Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(EditActivity.this, (view, year, month, dayOfMonth) -> {
                String date = "";
                if (month > 8) {
                    date = dayOfMonth + "/" + (month + 1) + "/" + y;
                } else {
                    date = dayOfMonth + "/0" + (month + 1) + "/" + y;
                }
                eDate.setText(date);
            }, y, m, d);
            dialog.show();
        }
        if (v == btnBack) {
            finish();
        }

        if (v == btnUpdate) {
            String name = eName.getText().toString();
            String des = eDes.getText().toString();
            String date = eDate.getText().toString();
//            int rate = (int) rating_bar.getRating();
            int scopeID = radio_group.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(scopeID);
            String status = radioButton.getText().toString();
            if (!name.isEmpty() && !des.isEmpty() && !date.isEmpty() && !status.isEmpty()) {
                boolean collab = checkCollab.isChecked();
                Task newTask = new Task(task.getId(), name, des, date, status, collab);
//                Database db = new Database(this);
                db.update(newTask);
                finish();
            }
        }

        if (v == btnRemove) {
            int id = task.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("WARNING");
            builder.setMessage("Are you sure you want to delete " + task.getName() + "?");
            builder.setIcon(R.drawable.ic_remove);
            builder.setPositiveButton("Yes", (dialog, which) -> {
//                int[] ids = {id};
                db.delete(id);
                finish();
            });
            builder.setNegativeButton("No", (dialog, which) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}