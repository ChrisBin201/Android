package com.example.bottomnavigation.cafe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CafeViewPagerAdapter extends FragmentStatePagerAdapter {
    public CafeViewPagerAdapter(@NonNull FragmentManager fragmentManager, int behavior) {
        super(fragmentManager, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentCuli();
            case 1:
                return new FragmentMoca();
            case 2:
                return new FragmentRobusta();
            default:
                return new FragmentCuli();
        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "CULI";
            case 1:
                return "MOCA";
            case 2:
                return "ROBUSTA";
            default:
                return "CULI ";
        }
    }
}
