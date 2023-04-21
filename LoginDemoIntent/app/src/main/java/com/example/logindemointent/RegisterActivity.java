package com.example.logindemointent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.logindemointent.model.Account;

public class RegisterActivity extends AppCompatActivity implements  View.OnClickListener{
    private TextView tvUsername, tvPassword;
    private Button btnRegister, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvUsername = findViewById(R.id.username_edittext);
        tvPassword = findViewById(R.id.password_edittext);
        btnRegister = findViewById(R.id.register_button);
        btnCancel = findViewById(R.id.cancel_button);
        btnRegister.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_button:
                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                Account account = new Account(tvUsername.getText().toString(), tvPassword.getText().toString());
                registerIntent.putExtra("account", account);
                setResult(RESULT_OK, registerIntent);
                finish();
                break;
            case R.id.cancel_button:
                setResult(RESULT_CANCELED,null);
                finish();
                break;
        }
    }

}