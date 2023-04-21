package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragment.fragment.FragmentA;
import com.example.fragment.fragment.FragmentB;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btFrA, btFrB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btFrA = findViewById(R.id.btFragmentA);
        btFrB = findViewById(R.id.btFragmentB);
        btFrA.setOnClickListener(this);
        btFrB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fg;
        switch (view.getId()) {
            case R.id.btFragmentA:
                fg = new FragmentA();
                fragmentTransaction.add(R.id.frame,fg);
                break;
            case R.id.btFragmentB:
                fg = new FragmentB();
                fragmentTransaction.add(R.id.frame,fg);
                break;
        }
        fragmentTransaction.commit();
    }
}