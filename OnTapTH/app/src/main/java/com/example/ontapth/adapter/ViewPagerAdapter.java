package com.example.ontapth.adapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ontapth.fragment.FragmentInfo;
import com.example.ontapth.fragment.FragmentList;
import com.example.ontapth.fragment.FragmentSearchStat;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int pageNum = 3;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println("position: " + position);
        switch (position) {
            case 0:
                return new FragmentList();
            case 1:
                return new FragmentInfo();
            case 2:
                return new FragmentSearchStat();
            default:
                return new FragmentList();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
