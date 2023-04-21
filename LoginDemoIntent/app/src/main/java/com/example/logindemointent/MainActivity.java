package com.example.logindemointent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.logindemointent.model.Account;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txtInfor);
        Intent intent = getIntent();
        if(intent.getSerializableExtra("account")!=null && intent.getSerializableExtra("user")!=null){
            Account log = (Account) intent.getSerializableExtra("account");
            Account user = (Account) intent.getSerializableExtra("user");
            if(log.getUsername().equals(user.getUsername()) && log.getPassword().equals(user.getPassword())){
                txt.setText("Login success");
            }
            else{
                txt.setText("Login fail");
            }
        }
    }
}