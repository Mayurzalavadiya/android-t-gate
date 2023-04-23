package com.tgate.gate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tgate.gate.Adapter.companyListAdapter;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.CompanyDataResponse;
import com.tgate.gate.apiResponse.ContactlistGuardResponse;
import com.tgate.gate.model.getHostList;
import com.tgate.gate.Adapter.hostListAdapter;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HostActivity extends AppCompatActivity {

    AppCompatImageView img_notification, host_back;
    RecyclerView rvhostlist;
    List<CompanyDataResponse.Datum> getCompanyList;
    SearchView searchview;
    companyListAdapter companyListAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        img_notification = findViewById(R.id.img_notification);
        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.Intent(HostActivity.this,NotificationActivity.class);
            }
        });

        host_back = findViewById(R.id.host_back);
        host_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

            }
        });

        rvhostlist = findViewById(R.id.rvhostlist);

        companylist();

        rvhostlist.setLayoutManager(new LinearLayoutManager(this));
        rvhostlist.setItemAnimator(new DefaultItemAnimator());


        searchview = findViewById(R.id.searchview);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void companylist() {
        if (Utility.isInternetAvailable(HostActivity.this)) {
            Utility.showCustomProgressDialog(HostActivity.this);
            RestClient.getInstance().getApiInterface().company_data_response(PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID)).enqueue(new Callback<CompanyDataResponse>() {
                @Override
                public void onResponse(Call<CompanyDataResponse> call, Response<CompanyDataResponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body().getData().size() > 0) {
                        getCompanyList = response.body().getData();
                        companyListAdapter = new companyListAdapter(HostActivity.this, getCompanyList);
                        rvhostlist.setAdapter(companyListAdapter);
                        companyListAdapter.notifyDataSetChanged();
                    } else {
                        Utility.showMessage(HostActivity.this, false, response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<CompanyDataResponse> call, Throwable t) {

                    Utility.hideCustomProgressDialog();
                    Utility.showMessage(HostActivity.this, false, t.getMessage());
                }
            });
        } else {
            Utility.showNoInternetAvailable(HostActivity.this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }
}