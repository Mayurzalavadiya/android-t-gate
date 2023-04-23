package com.tgate.gate.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.tgate.gate.Adapter.viewOwnwrPagerAdapter;
import com.tgate.gate.Adapter.viewPagerAdapter;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.TokenupdateCopyOwnerResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerMainActivity extends AppCompatActivity {

    TabLayout tab_layout;
    ViewPager view_pager;

    AppCompatImageView img_notification;

    AppCompatTextView txt_name;
    DrawerLayout drawerlayout;
    MaterialToolbar toolbar;
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);


        //Owner fcm Token Update
             updateFcmToken();



        img_notification = findViewById(R.id.img_notification);
        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Utility.Intent(OwnerMainActivity.this,NotificationOwnerActivity.class);
            }
        });
        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);

        tab_layout.post(new Runnable() {
            @Override
            public void run() {
                tab_layout.setupWithViewPager(view_pager);
            }
        });
        view_pager.setAdapter(new viewOwnwrPagerAdapter(getSupportFragmentManager()));


        txt_name = findViewById(R.id.txt_name);
        txt_name.setText(PrefsManager.readStringPrefsVal(PrefsManager.FIRST_NAME));

        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        drawerlayout = findViewById(R.id.drawerlayout);
        navigation = findViewById(R.id.navigation);

        View view = navigation.getHeaderView(0);
        ShapeableImageView img_userimage = view.findViewById(R.id.img_userimage);
//        Log.d("@@@_IMAGE", PrefsManager.readStringPrefsVal(PrefsManager.PROFILE_IMAGE));
        Glide.with(OwnerMainActivity.this).load(PrefsManager.readStringPrefsVal(PrefsManager.PROFILE_IMAGE)).placeholder(R.drawable.profile).into(img_userimage);
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
                    case R.id.my_account:
                        Utility.Intent(OwnerMainActivity.this, ProfileActivity.class);
                        drawerlayout.closeDrawer(GravityCompat.START);
//                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                        break;
                    case R.id.logout:
                        drawerlayout.closeDrawer(GravityCompat.START);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OwnerMainActivity.this);

                        alertDialogBuilder
                                .setTitle("Logout")
                                .setMessage("Are you sure to logout?")
                                .setCancelable(false)
                                .setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        PrefsManager.removeAllKey();
                                        Utility.Intent(OwnerMainActivity.this, LoginActivity.class);
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

    }


    private void updateFcmToken() {
        if (Utility.isInternetAvailable(OwnerMainActivity.this)){

            RestClient.getInstance().getApiInterface().token_update_copy_owner_response(PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID),
                    PrefsManager.readStringPrefsVal(PrefsManager.FCM_TOKEN),PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN),"ANDROID").enqueue(new Callback<TokenupdateCopyOwnerResponse>() {
                @Override
                public void onResponse(Call<TokenupdateCopyOwnerResponse> call, Response<TokenupdateCopyOwnerResponse> response) {
                    if (response.isSuccessful()){
                        if (response.body().getStatus()){
                            Log.e("Fcm Token","Update----"+response.body().getMessage());
                        }
                        else {
                            Log.e("Fcm Token","Update----"+response.body().getMessage());
                        }

                    }
                }

                @Override
                public void onFailure(Call<TokenupdateCopyOwnerResponse> call, Throwable t) {

                }
            });
        } else {
            Utility.showNoInternetAvailable(OwnerMainActivity.this);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(navigation)) {
            drawerlayout.closeDrawer(navigation, true);
        } else {
            super.onBackPressed();
        }
    }
}
