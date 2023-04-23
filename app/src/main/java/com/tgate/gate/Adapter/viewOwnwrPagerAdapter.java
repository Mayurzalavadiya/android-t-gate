package com.tgate.gate.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.tgate.gate.Fragment.CancelOwnerFragment;
import com.tgate.gate.Fragment.PastOwnerFragment;
import com.tgate.gate.Fragment.ScheduledOwnerFragment;
import com.tgate.gate.Fragment.TodayOwnerFragment;

public class viewOwnwrPagerAdapter extends FragmentPagerAdapter {


    public viewOwnwrPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Today";
            case 1:
                return "Scheduled";
            case 2:
                return "Past";
            case 3:
                return "Cancelled";
        }
        return super.getPageTitle(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TodayOwnerFragment();
            case 1:
                return new ScheduledOwnerFragment();
            case 2:
                return new PastOwnerFragment();
            case 3:
                return new CancelOwnerFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
