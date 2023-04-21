package com.example.myapplication;

// Build the java logic for multiplication table
// using button, text view, edit text

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity
        extends AppCompatActivity
        implements  View.OnClickListener
{

    Button b;
    EditText eU, eP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        b = findViewById(R.id.login);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.login:
                eU = findViewById(R.id.username);
                eP = findViewById(R.id.password);
                String password = eP.getText().toString();
                String username  = eU.getText().toString();
                if(password.equals("123") && username.equals("ok")){
                    Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(this, "Fuck",  Toast.LENGTH_SHORT ).show();
                return;
            default:

        }
    }


}
