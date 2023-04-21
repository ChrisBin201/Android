package com.example.ontapth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
//    public Spinner spinerCategory;
    private EditText eName, eDes, eDate;
    private Button btnAdd, btnCancel;
    private CheckBox checkCollab;
    //    private RatingBar rating_bar;
    private RadioGroup radio_group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        eName = findViewById(R.id.eName);
        eDes = findViewById(R.id.eDes);
        eDate = findViewById(R.id.eDate);
        btnAdd = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        checkCollab = findViewById(R.id.checkCollab);
        radio_group = findViewById(R.id.radio_group);
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        eDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == eDate) {
            final Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, (view, year, month, dayOfMonth) -> {
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
        if (v == btnCancel) {
            finish();
        }
        if (v == btnAdd) {
            String name = eName.getText().toString();
            String des = eDes.getText().toString();
            String date = eDate.getText().toString();
//            int rate = (int) rating_bar.getRating();
            int scopeID = radio_group.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(scopeID);
            String status = radioButton.getText().toString();
            if(!name.isEmpty() && !des.isEmpty() && !date.isEmpty() && !status.isEmpty()) {
                boolean collab = checkCollab.isChecked();
                Task task = new Task(name, des, date, status,collab);
                Database db = new Database(this);
                db.insertTask(task);
                finish();
            }
//            String category = spinerCategory.getSelectedItem().toString();
//            if (!title.isEmpty() && price.matches("\\d+") && !date.isEmpty() && !scope.isEmpty() && !category.isEmpty()) {
//                Item item = new Item(title, category, price, date, scope, rate);
//                SQLiteHelper db = new SQLiteHelper(this);
//                db.addItem(item);
//                finish();
//            }
        }
    }
}