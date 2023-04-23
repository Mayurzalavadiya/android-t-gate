package com.tgate.gate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tgate.gate.Adapter.checkoutListAdapter;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.CheckInResponse;
import com.tgate.gate.model.getCheckoutList;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutActivity extends AppCompatActivity {

    AppCompatImageView img_notification, check_out_back;
    RecyclerView rv_chechoutList;
    List<CheckInResponse.Datum> getCheckoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        img_notification = findViewById(R.id.img_notification);
        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.Intent(CheckOutActivity.this,NotificationActivity.class);
            }
        });

        check_out_back = findViewById(R.id.check_out_back);
//        name = getResources().getStringArray(R.array.visitorname);
        check_out_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

            }
        });

        rv_chechoutList = findViewById(R.id.rv_chechoutList);


        Checkoutlist();

        rv_chechoutList.setLayoutManager(new LinearLayoutManager(CheckOutActivity.this));
        rv_chechoutList.setItemAnimator(new DefaultItemAnimator());

    }

    private void Checkoutlist() {
        if (Utility.isInternetAvailable(CheckOutActivity.this)) {
            Utility.showCustomProgressDialog(CheckOutActivity.this);

            RestClient.getInstance().getApiInterface().checkin_response(PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID)).enqueue(new Callback<CheckInResponse>() {
                @Override
                public void onResponse(Call<CheckInResponse> call, Response<CheckInResponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body()!=null && response.body().getStatus().equals(true)) {
                        if (response.body().getData() != null ) {
                            getCheckoutList = response.body().getData();
                            checkoutListAdapter checkoutListAdapter = new checkoutListAdapter(CheckOutActivity.this, getCheckoutList);
                            rv_chechoutList.setAdapter(checkoutListAdapter);
                            checkoutListAdapter.notifyDataSetChanged();
                        }
                        else {
                            Utility.showMessage(CheckOutActivity.this, false, response.body().getMessage());
                        }
                    }else {
                        Utility.showMessage(CheckOutActivity.this, false, getString(R.string.data_not_Available));

                    }
                }

                @Override
                public void onFailure(Call<CheckInResponse> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                    Utility.showMessage(CheckOutActivity.this, false, t.getMessage());

                }
            });
        } else {
            Utility.showNoInternetAvailable(CheckOutActivity.this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }
}