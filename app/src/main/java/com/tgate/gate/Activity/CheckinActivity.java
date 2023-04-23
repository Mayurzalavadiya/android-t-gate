package com.tgate.gate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.tgate.gate.Adapter.checkinListAdapter;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.VisitorpurposeResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckinActivity extends AppCompatActivity {

    AppCompatImageView img_notification, check_in_back;
    MaterialButton btn_submit;

    RecyclerView rvCheckList;
    List<VisitorpurposeResponse.Datum> getVisitorArrayList;
    String[] visitor_id = {" "};
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
         img_notification = findViewById(R.id.img_notification);
         img_notification.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Utility.Intent(CheckinActivity.this,NotificationActivity.class);
             }
         });

         PrefsManager.removeKey(PrefsManager.VISITOR_PURPOSE_ID);

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Visitor_purpose_id = PrefsManager.readStringPrefsVal(PrefsManager.VISITOR_PURPOSE_ID);
                if (Visitor_purpose_id != null && Visitor_purpose_id != ""){
                    Utility.Intent(CheckinActivity.this, VisitorDetails.class);
                    overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                    Log.d("@@@_Visitor_purpose_id", Visitor_purpose_id);
                }else {
                    Utility.showMessage(CheckinActivity.this, false, "Please Any one Select");

                }
            }
        });

        check_in_back = findViewById(R.id.check_in_back);
        check_in_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
            }
        });
        rvCheckList = findViewById(R.id.rvCheckList);


        Visitorpurpose();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvCheckList.setLayoutManager(gridLayoutManager);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        PrefsManager.removeKey(PrefsManager.VISITOR_PURPOSE_ID);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }

    private void Visitorpurpose() {
        if (Utility.isInternetAvailable(CheckinActivity.this)) {
            Utility.showCustomProgressDialog(CheckinActivity.this);
            RestClient.getInstance().getApiInterface().visitorpurposeresponse().enqueue(new Callback<VisitorpurposeResponse>() {
                @Override
                public void onResponse(Call<VisitorpurposeResponse> call, Response<VisitorpurposeResponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body().getData().size() > 0) {
                        getVisitorArrayList = response.body().getData();
                        checkinListAdapter visitorListAdapter = new checkinListAdapter(CheckinActivity.this, getVisitorArrayList);
                        rvCheckList.setAdapter(visitorListAdapter);
                        visitorListAdapter.notifyDataSetChanged();
                    } else {

                        Utility.showMessage(CheckinActivity.this, false, response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<VisitorpurposeResponse> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                    Utility.showMessage(CheckinActivity.this, false, t.getMessage());


                }
            });
        } else {
            Utility.showNoInternetAvailable(CheckinActivity.this);
        }
    }
}