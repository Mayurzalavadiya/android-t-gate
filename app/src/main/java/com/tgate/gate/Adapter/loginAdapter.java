package com.tgate.gate.Adapter;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.tgate.gate.Fragment.GuardFragment;
import com.tgate.gate.Fragment.OwnerFragment;

import java.text.FieldPosition;

public class loginAdapter extends FragmentPagerAdapter {


    public loginAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Owner";
            case 1:
                return "Guard";
        }
        return super.getPageTitle(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new OwnerFragment();
            case 1:
                return new GuardFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
