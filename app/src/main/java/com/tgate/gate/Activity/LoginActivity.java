package com.tgate.gate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.tgate.gate.Adapter.loginAdapter;
import com.tgate.gate.R;

public class LoginActivity extends AppCompatActivity {

    TabLayout tab_layout;
    ViewPager view_pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
 //       getSupportActionBar().hide();

        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);


        tab_layout.post(new Runnable() {
            @Override
            public void run() {
                tab_layout.setupWithViewPager(view_pager);

            }
        });
        view_pager.setAdapter(new loginAdapter(getSupportFragmentManager()));





    }
}