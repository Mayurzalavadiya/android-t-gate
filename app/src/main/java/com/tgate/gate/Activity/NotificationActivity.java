package com.tgate.gate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tgate.gate.Adapter.notificationListAdapter;
import com.tgate.gate.Pagination.EndlessRecyclerOnScrollListener;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.getGuardNotificationResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView rvnotificationlist;

    ArrayList<getGuardNotificationResponse.Datum> getNotificationList;
    AppCompatImageView img_back;

    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    int currentpage = 1;
    public static boolean isIndex1 = false;
    notificationListAdapter notificationlistadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rvnotificationlist = findViewById(R.id.rvnotificationlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotificationActivity.this);
        rvnotificationlist.setLayoutManager(linearLayoutManager);

        getNotificationList = new ArrayList<>();
        notificationlistadapter = new notificationListAdapter(getNotificationList, NotificationActivity.this);
        rvnotificationlist.setAdapter(notificationlistadapter);

        Notification(1);

        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                currentpage = current_page;
                isIndex1 = false;
                Notification(current_page);
                Log.d("Page", String.valueOf(current_page));
            }

        };

        rvnotificationlist.addOnScrollListener(endlessRecyclerOnScrollListener);


    }

    private void Notification(int page) {
        if (Utility.isInternetAvailable(NotificationActivity.this)) {
            Utility.showCustomProgressDialog(NotificationActivity.this);


            RestClient.getInstance().getApiInterface().getguardnotification_response(PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID), PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN), page).enqueue(new Callback<getGuardNotificationResponse>() {
                @Override
                public void onResponse(Call<getGuardNotificationResponse> call, Response<getGuardNotificationResponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus() != null && response.body().getStatus().equals(true)) {
                            List<getGuardNotificationResponse.Datum> b = response.body().getData();
                            if (b != null && b.size() > 0) {

                                getNotificationList.addAll(b);


                                if (page == 1)
                                    notificationlistadapter.replaceAll(b);
                                else
                                    notificationlistadapter.addAllS(b);


//                                rvnotificationlist.setAdapter(notificationlistadapter);

                            }
                            else {
                               Utility.hideCustomProgressDialog();
                                endlessRecyclerOnScrollListener.previousState();
                            }
                        }else {
                            endlessRecyclerOnScrollListener.previousState();
                            Utility.showMessage(NotificationActivity.this, false, response.body().getMessage());
                        }
                    } else {
                        endlessRecyclerOnScrollListener.previousState();
                        Utility.showMessage(NotificationActivity.this, false, getString(R.string.data_not_Available));
                    }
                }

                @Override
                public void onFailure(Call<getGuardNotificationResponse> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                    endlessRecyclerOnScrollListener.previousState();
                    Utility.showMessage(NotificationActivity.this, false, t.getMessage());
                }
            });

        } else {
            Utility.showNoInternetAvailable(NotificationActivity.this);
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}
