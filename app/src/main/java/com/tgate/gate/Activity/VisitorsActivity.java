package com.tgate.gate.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.tgate.gate.Adapter.timeListAdapter;
import com.tgate.gate.Adapter.viewPagerAdapter;
import com.tgate.gate.R;
import com.tgate.gate.model.getTimeList;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class VisitorsActivity extends AppCompatActivity implements DatePickerListener {

    TabLayout tab_layout;
    ViewPager view_pager;

    AppCompatTextView txt_name;

    AppCompatImageView img_notification, img_filter;
    DrawerLayout drawerlayout;
    NavigationView navigation;
    MaterialToolbar toolbar;

    List<getTimeList> getTimeLists;
    String timeArray[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);

        img_notification = findViewById(R.id.img_notification);
        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.Intent(VisitorsActivity.this,NotificationActivity.class);
            }
        });

        txt_name = findViewById(R.id.txt_name);
        txt_name.setText(PrefsManager.readStringPrefsVal(PrefsManager.FIRST_NAME));

        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);


        tab_layout.post(new Runnable() {
            @Override
            public void run() {
                tab_layout.setupWithViewPager(view_pager);
            }
        });
        view_pager.setAdapter(new viewPagerAdapter(getSupportFragmentManager()));




        img_filter = findViewById(R.id.img_filter);
        img_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog_filter = new BottomSheetDialog(VisitorsActivity.this, R.style.alertDialogThem);
                dialog_filter.setContentView(R.layout.bottomsheetdialog_filter);
                dialog_filter.setCanceledOnTouchOutside(true);

                timeArray = getResources().getStringArray(R.array.Time);
                RecyclerView rv_time = dialog_filter.findViewById(R.id.rv_time);
                StaggeredGridLayoutManager managar = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
                rv_time.setLayoutManager(managar);
                rv_time.setItemAnimator(new DefaultItemAnimator());

                getTimeLists = new ArrayList<>();
                for (int i = 0; i < timeArray.length; i++) {
                    getTimeList list = new getTimeList();
                    list.setTime(timeArray[i]);
                    getTimeLists.add(list);
                }

                timeListAdapter timeListAdapter = new timeListAdapter(VisitorsActivity.this, getTimeLists);
                rv_time.setAdapter(timeListAdapter);

                HorizontalPicker picker = dialog_filter.findViewById(R.id.datePicker);
                picker.setListener(VisitorsActivity.this);
                picker.setDays(240);
                picker.setOffset(183);
                picker.setDateSelectedColor(getResources().getColor(R.color.blue));
                picker.setDateSelectedTextColor(getResources().getColor(R.color.white));
                picker.setMonthAndYearTextColor(getResources().getColor(R.color.black));
                picker.setTodayButtonTextColor(getResources().getColor(R.color.black));
                picker.setTodayDateTextColor(getResources().getColor(R.color.black));
                picker.setTodayDateBackgroundColor(getResources().getColor(R.color.blue));
                picker.setUnselectedDayTextColor(getResources().getColor(R.color.blue));
                picker.setDayOfWeekTextColor(getResources().getColor(R.color.black));
                picker.setUnselectedDayTextColor(getResources().getColor(R.color.blue));
                picker.showTodayButton(true);
                picker.init();
                picker.setBackgroundColor(getResources().getColor(R.color.white));
                picker.setDate(new DateTime());


                AppCompatSpinner spinner = dialog_filter.findViewById(R.id.spinner);
                ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(VisitorsActivity.this, R.array.Spinner, android.R.layout.simple_list_item_1);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
                spinner.setAdapter(arrayAdapter);

                ImageView img_close = dialog_filter.findViewById(R.id.img_close);
                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_filter.dismiss();
                    }
                });
                dialog_filter.show();


            }

        });





        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        drawerlayout = findViewById(R.id.drawerlayout);

        navigation = findViewById(R.id.navigation);

        View view = navigation.getHeaderView(0);
        AppCompatImageView img_userimage = view.findViewById(R.id.img_userimage);
        Glide.with(VisitorsActivity.this).load(PrefsManager.readStringPrefsVal(PrefsManager.PROFILE_IMAGE)).placeholder(R.drawable.profile).into(img_userimage);
        AppCompatImageView img_back = view.findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.closeDrawer(GravityCompat.START);
            }
        });
        AppCompatTextView txt_number = view.findViewById(R.id.txt_number);
        txt_number.setText(PrefsManager.readStringPrefsVal(PrefsManager.PHONE_NUMBER));
        AppCompatTextView txt_name = view.findViewById(R.id.txt_name);
        txt_name.setText(PrefsManager.readStringPrefsVal(PrefsManager.FIRST_NAME));
//        toolbar.setNavigationIcon(R.drawable.icon_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
//                drawerlayout.closeDrawer(GravityCompat.START);
                switch (id) {
                    case R.id.my_profile:
                        Utility.Intent(VisitorsActivity.this, VisitorDetails.class);
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        drawerlayout.closeDrawer(GravityCompat.START);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VisitorsActivity.this);

                        alertDialogBuilder
                                .setTitle("Logout")
                                .setMessage("Are you sure to logout?")
                                .setCancelable(false)
                                .setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        PrefsManager.removeAllKey();
                                        Utility.Intent(VisitorsActivity.this, LoginActivity.class);
                                        finish();

                                    }
                                })

                                .setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();

                                    }
                                });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();

                        break;
                }
                return true;
            }
        });
//        cv_today = findViewById(R.id.cv_today);
//        cv_today.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog alertdialog_metting = new BottomSheetDialog(VisitorsActivity.this, R.style.alertDialogThem);
//                alertdialog_metting.setContentView(R.layout.dialog_mettingguard);
//                alertdialog_metting.setCanceledOnTouchOutside(true);
//
//                ImageView img_close = alertdialog_metting.findViewById(R.id.img_close);
//                img_close.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        alertdialog_metting.dismiss();
//                    }
//                });
//                alertdialog_metting.show();
//            }
//        });

    }




    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(navigation)) {
            drawerlayout.closeDrawer(navigation, true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDateSelected(DateTime dateSelected) {
        Log.d("Picker", "Date is" + dateSelected.toString());

    }

}