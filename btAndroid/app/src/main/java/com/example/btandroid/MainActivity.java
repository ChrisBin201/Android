package com.example.btandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginBtn;
    EditText userEdt, passEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = findViewById(R.id.button_login);
        userEdt =  findViewById(R.id.edit_text_username);
        passEdt =  findViewById(R.id.edit_text_password);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = String.valueOf(userEdt.getText());
                String password = String.valueOf(passEdt.getText());
                if (username.equals("quy") && password.equals("123")) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Toast.makeText(MainActivity.this, "Sai username hoặc password", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

}