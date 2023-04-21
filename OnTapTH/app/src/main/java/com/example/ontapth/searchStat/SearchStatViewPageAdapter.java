package com.example.ontapth.searchStat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SearchStatViewPageAdapter extends FragmentStatePagerAdapter {
    public SearchStatViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentSearch();
            case 1:
                return new FragmentStat();
            default:
                return new FragmentSearch();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SEARCH";
            case 1:
                return "STATISTIC";
            default:
                return "SEARCH";
        }
    }
}
