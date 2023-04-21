package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        registerForContextMenu(tv);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mFile:
                Toast.makeText(this,"ban chon File", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.mExit:
//                Toast.makeText(this,"ban chon Thoat", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.mEmail:
                Toast.makeText(this,"ban chon Email", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mPhone:
                Toast.makeText(this,"ban chon Phone", Toast.LENGTH_SHORT).show();
                break;
        }
        return  super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu,v,menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mRed:
                tv.setTextColor(Color.RED);
                break;
            case R.id.mBlue:
                tv.setTextColor(Color.BLUE);
                break;
            case R.id.mGreen:
                tv.setTextColor(Color.GREEN);
                break;
        }
        return super.onContextItemSelected(item);
    }
}