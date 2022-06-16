package com.to_do_app.to_do;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class homefragementadapter extends FragmentPagerAdapter {
    ArrayList<Fragment> flist;
    ArrayList<String> tlist;
    public homefragementadapter(@NonNull FragmentManager fm) {
        super(fm);
        flist=new ArrayList<>();
        tlist=new ArrayList<>();
    }
    public void add(Fragment fm,String title){
        flist.add(fm);
        tlist.add(title);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tlist.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return flist.get(position);
    }
    @Override
    public int getCount() {
        return flist.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
