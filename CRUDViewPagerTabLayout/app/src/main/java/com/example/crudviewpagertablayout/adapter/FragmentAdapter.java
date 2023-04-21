package com.example.crudviewpagertablayout.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.crudviewpagertablayout.fragment.FragmentAdd;
import com.example.crudviewpagertablayout.fragment.FragmentSearch;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int numPage=2;

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, int num) {
        super(fragmentManager,num);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println("position: "+position);
        switch (position){
            case 0:return new FragmentAdd();
            case 1:return new FragmentSearch();
            default:return new FragmentAdd();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
