package com.example.widgets_androidlayout_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private List<Pair<Integer, String>> platformList = List.of(
            new Pair<>(1, "IPhone"),
            new Pair<>(2, "Android"),
            new Pair<>(3, "Window Mobile")
    );
    private List<Pair<Integer, String>> favList = List.of(
            new Pair<>(1, "Tennis"),
            new Pair<>(2, "Running"),
            new Pair<>(3, "Swimming"),
            new Pair<>(4, "Sleeping"),
            new Pair<>(5, "Reading")
    );

    private List<Integer> plList = new ArrayList<>();
    private String gender;
    private float rate;
    private String country;
    private String university;
    private List<Integer> favs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set up platform
        LinearLayout groupPlatform = (LinearLayout) findViewById(R.id.group_pl);
        for (Pair pair : platformList) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText((String) pair.second);
            checkBox.setOnCheckedChangeListener((compoundButton, checked) -> {
                if (checked == true)
                    plList.add((Integer) pair.first);
                else
                    plList = plList.stream().filter(plIndex -> plIndex != pair.first).collect(Collectors.toList());
            });
            groupPlatform.addView(checkBox);
        }

        //set up gender
        RadioGroup genderGroup = (RadioGroup) findViewById(R.id.group_gender);
        genderGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton radioButton = (RadioButton) findViewById(i);
            gender = (String) radioButton.getText();

        });

        //set up Rating
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rate);
        ratingBar.setOnRatingBarChangeListener((ratingBar1, newRate, b) -> {
            rate = newRate;
        });

        //set up country
        EditText countryEditText = (EditText) findViewById(R.id.country);
        countryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                country = s.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //set up university
        EditText uniEditText = (EditText) findViewById(R.id.university);
        uniEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                university = s.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //set up fav
        LinearLayout groupFav = (LinearLayout) findViewById(R.id.group_fav);
        for (Pair pair : favList) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText((String) pair.second);
            checkBox.setOnCheckedChangeListener((compoundButton, checked) -> {
                if (checked == true)
                    favs.add((Integer) pair.first);
                else
                    favs = favs.stream().filter(plIndex -> plIndex != pair.first).collect(Collectors.toList());
            });
            groupFav.addView(checkBox);
        }

        //set up submit button
        Button button = (Button) findViewById(R.id.btn_submit);
        button.setOnClickListener(view -> {
            try {
                TextView plTextView = (TextView) findViewById(R.id.sub_pl);
                TextView genderTextView = (TextView) findViewById(R.id.sub_gender);
                TextView rateTextView = (TextView) findViewById(R.id.sub_rate);
                TextView countryTextView = (TextView) findViewById(R.id.sub_country);
                TextView uniTextView = (TextView) findViewById(R.id.sub_uni);
                TextView favTextView = (TextView) findViewById(R.id.sub_fav);

                String plText = "";
                for (Pair<Integer, String> pair : platformList)
                    if (plList.contains(pair.first))
                        plText += pair.second + ", ";
                plText = plText.substring(0, plText.lastIndexOf(","));
                String favText = "";
                for (Pair<Integer, String> pair : favList)
                    if (favs.contains(pair.first))
                        favText += pair.second + ", ";
                favText = favText.substring(0, favText.lastIndexOf(","));

                plTextView.setText(plText);
                genderTextView.setText(gender);
                rateTextView.setText(rate + "");
                countryTextView.setText(country);
                uniTextView.setText(university);
                favTextView.setText(favText);
            } catch (Exception e){
                System.out.println(e);
                Toast.makeText(getApplicationContext(), "Nhập lại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}