package com.tgate.gate.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.TokenupdateCopyOwnerResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    TextView txt_tgate;
    String is_login = PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN);
    ArrayList<TokenupdateCopyOwnerResponse> getfcmtoken;

    String fcmToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //  getSupportActionBar().hide();

        txt_tgate = findViewById(R.id.txt_tgate);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2800);
        txt_tgate.startAnimation(alphaAnimation);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        if(task.isSuccessful()){
                            fcmToken=task.getResult().getToken();
                            PrefsManager.savePrefsVal(PrefsManager.FCM_TOKEN, fcmToken);
                            Log.i("Firebase token", "Refreshed token: " + fcmToken);
                        }
                    }
                });




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (is_login!=null && is_login.equals("GUARD")) {
                    Utility.Intent(SplashActivity.this, MainActivity.class);
                    finish();
                } else if(is_login!=null && is_login.equals("OWNER")){
                    Utility.Intent(SplashActivity.this, OwnerMainActivity.class);
                    finish();
                }else {
                    Utility.Intent(SplashActivity.this, LoginActivity.class);
                    finish();

                }
            }
        }, 3000);
    }


}