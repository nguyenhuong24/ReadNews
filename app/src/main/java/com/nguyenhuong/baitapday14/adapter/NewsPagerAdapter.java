package com.nguyenhuong.baitapday14.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nguyenhuong.baitapday14.fragment.Fragment24h;
import com.nguyenhuong.baitapday14.fragment.FragmentDantri;

public class NewsPagerAdapter extends FragmentPagerAdapter {
    String title[]={"24h","Dân trí"};

    public NewsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return Fragment24h.getInstance();
            case 1:
                return FragmentDantri.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
