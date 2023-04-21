package com.example.ontapth.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ontapth.R;
import com.example.ontapth.searchStat.SearchStatViewPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class FragmentSearchStat extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public FragmentSearchStat() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_stat, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        SearchStatViewPageAdapter adapter = new SearchStatViewPageAdapter(getChildFragmentManager(), 2);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
