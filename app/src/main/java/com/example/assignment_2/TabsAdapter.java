package com.example.assignment_2;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.assignment_2.View.ClassicFragment;
import com.example.assignment_2.View.PopFragment;
import com.example.assignment_2.View.RockFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fragmentManager, int numOfTabs){
        super(fragmentManager);
        this.mNumOfTabs = numOfTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                RockFragment rockFragment = new RockFragment();
                return rockFragment;
            case 1:
                ClassicFragment classicFragment = new ClassicFragment();
                return classicFragment;
            case 2:
                PopFragment popFragment = new PopFragment();
                return popFragment;
            default:
                return null;
        }
    }





}