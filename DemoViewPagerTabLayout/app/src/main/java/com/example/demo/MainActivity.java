package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btPre, btNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        btPre = findViewById(R.id.btPre);
        btNext = findViewById(R.id.btNext);
        btPre.setOnClickListener(this);
        btNext.setOnClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter= new FragmentAdapter(manager,3);
        viewPager.setPageTransformer(true, new Horizo);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabLayoutTitleColor();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.pink));
                        btNext.setBackgroundColor(getResources().getColor(R.color.pink));
                        btPre.setBackgroundColor(getResources().getColor(R.color.pink));
                        break;
                    case 1:
                        tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.green));
                        btNext.setBackgroundColor(getResources().getColor(R.color.green));
                        btPre.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    case 2:
                        tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.blue));
                        btNext.setBackgroundColor(getResources().getColor(R.color.blue));
                        btPre.setBackgroundColor(getResources().getColor(R.color.blue));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btNext:
                if(viewPager.getCurrentItem()==2){
                    return;
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    setTabLayoutTitleColor();
                }
                break;
            case R.id.btPre:
                if(viewPager.getCurrentItem()==0){
                    return;
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                    setTabLayoutTitleColor();
                }
                break;
        }
    }

    private void setTabLayoutTitleColor(){
        switch (viewPager.getCurrentItem()){
            case 0:
                tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.pink));
                break;
            case 1:
                tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.green));
                break;
            case 2:
                tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.blue));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
    }
}