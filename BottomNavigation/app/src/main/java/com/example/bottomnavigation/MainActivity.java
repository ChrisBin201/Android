package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.bottomnavigation.adapter.ViewPagerAdapter;
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
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), 4);
        button = findViewById(R.id.fab);
        button.setOnClickListener(v -> {
            Toast.makeText(this, "Floating Action Button", Toast.LENGTH_SHORT).show();
        });
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.mHome).setChecked(true);
                        break;
                    case 1: navigationView.getMenu().findItem(R.id.mNoti).setChecked(true);
                        break;
                    case 2: navigationView.getMenu().findItem(R.id.mSearch).setChecked(true);
                        break;
                    case 3: navigationView.getMenu().findItem(R.id.mCafe).setChecked(true);
                        break;
//                    default: navigationView.getMenu().findItem(R.id.mHome).setCheckable(true);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.mHome:
                    viewPager.setCurrentItem(0);
                    System.out.println(viewPager.getCurrentItem());
                    break;
                case R.id.mNoti:
                    viewPager.setCurrentItem(1);
                    System.out.println(viewPager.getAdapter());

                    break;
                case R.id.mSearch:
                    viewPager.setCurrentItem(2);
                    System.out.println(viewPager.getCurrentItem());

                    break;
                case R.id.mCafe:
                    viewPager.setCurrentItem(3);
                    System.out.println(viewPager.getCurrentItem());

                    break;
            }
            return true;
        });
    }
}