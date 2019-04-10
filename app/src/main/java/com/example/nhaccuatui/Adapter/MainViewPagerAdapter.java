package com.example.nhaccuatui.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrayListFragment = new ArrayList<>();
    private ArrayList<String> arrayListTitle = new ArrayList<>();


    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return arrayListFragment.get(i);
    }

    @Override
    public int getCount() {
        return arrayListFragment.size();
    }

    public void addFragment(Fragment fragment, String title) {
        arrayListFragment.add(fragment);
        arrayListTitle.add(title);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayListTitle.get(position);
    }
}