package com.tgate.gate.Adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.tgate.gate.Fragment.CancelFragment;
import com.tgate.gate.Fragment.PastFragment;
import com.tgate.gate.Fragment.ScheduledFragment;
import com.tgate.gate.Fragment.TodayFragment;

public class adminPagerAdapter extends FragmentPagerAdapter {
    public adminPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);

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
                    return new TodayFragment();
                case 1:
                    return new ScheduledFragment();
                case 2:
                    return new PastFragment();
                case 3:
                    return new CancelFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
