package com.example.logindemointent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logindemointent.model.Account;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvUsername, tvPassword;
    private Button btnLogin, btnRegister;
    private final static int REQUEST_CODE = 10000;
    private Account user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvUsername = findViewById(R.id.username_edittext);
        tvPassword = findViewById(R.id.password_edittext);
        btnLogin = findViewById(R.id.login_button);
        btnRegister = findViewById(R.id.register_button);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                Account account = new Account(tvUsername.getText().toString(), tvPassword.getText().toString());
                loginIntent.putExtra("account", account);
                loginIntent.putExtra("user",user);
                startActivity(loginIntent);
                break;
            case R.id.register_button:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(registerIntent, REQUEST_CODE );
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Nguoi dung huy dang ky", Toast.LENGTH_SHORT).show();
            }
            else {
                user = (Account) data.getSerializableExtra("account");
                tvUsername.setText(user.getUsername());
                tvPassword.setText(user.getPassword());
            }
        } else {
            user = null;
        }
    }
}