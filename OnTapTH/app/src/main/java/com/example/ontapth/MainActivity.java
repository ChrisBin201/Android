package com.example.ontapth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ontapth.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton button;
    private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewPagerMain);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), 3);
        button = findViewById(R.id.fab);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.mList).setChecked(true);
                        break;
                    case 1: navigationView.getMenu().findItem(R.id.mInfo).setChecked(true);
                        break;
                    case 2: navigationView.getMenu().findItem(R.id.mSearchStat).setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.mList:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.mInfo:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.mSearchStat:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });
    }
}