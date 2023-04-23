package com.tgate.gate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tgate.gate.Fragment.CancelFragment;
import com.tgate.gate.Fragment.GuardFragment;
import com.tgate.gate.Fragment.OwnerFragment;
import com.tgate.gate.Fragment.PastFragment;
import com.tgate.gate.Fragment.ScheduledFragment;
import com.tgate.gate.Fragment.TodayFragment;

import java.util.List;

public class viewPagerAdapter extends FragmentPagerAdapter {


    public viewPagerAdapter(@NonNull FragmentManager fm) {
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
