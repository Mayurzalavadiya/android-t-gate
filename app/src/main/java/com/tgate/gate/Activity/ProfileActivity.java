package com.tgate.gate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.GetOwnerPRofileResponse;
import com.tgate.gate.apiResponse.GetProfileResponse;
import com.tgate.gate.apiResponse.LoginGuardResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    AppCompatTextView txt_name, txt_email, txt_phonenumber, txt_password;
    CircleImageView img_profile;
//    AppCompatImageView img_profile;


    String is_login = PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN);
    LinearLayout linear_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        linear_layout = findViewById(R.id.linear_layout);
        txt_name = findViewById(R.id.txt_name);
        txt_email = findViewById(R.id.txt_email);
        txt_phonenumber = findViewById(R.id.txt_phonenumber);
        txt_password = findViewById(R.id.txt_password);
        img_profile = findViewById(R.id.img_profile);


        if (is_login.equals("OWNER")) {
            linear_layout.setVisibility(View.VISIBLE);
            if (Utility.isInternetAvailable(ProfileActivity.this)) {
                Utility.showCustomProgressDialog(ProfileActivity.this);
                RestClient.getInstance().getApiInterface().getownerprofileresponse(PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID)).enqueue(new Callback<GetOwnerPRofileResponse>() {
                    @Override
                    public void onResponse(Call<GetOwnerPRofileResponse> call, Response<GetOwnerPRofileResponse> response) {
                        Utility.hideCustomProgressDialog();
                        if (response.body().getStatus()) {
                            if (response.body().getData() != null) {
//                        Utility.showMessage(ProfileActivity.this, true, response.body().getMessage());
                                Glide.with(ProfileActivity.this).load(response.body().getData().getProfileImage()).placeholder(R.drawable.profile).into(img_profile);
                                String First_name = (response.body().getData().getFirstname());
                                String Last_name = (response.body().getData().getLastname());
                                txt_name.setText(First_name + " " + Last_name);

                                txt_password.setText(PrefsManager.readStringPrefsVal(PrefsManager.PASSWORD));

                                String email = (response.body().getData().getEmail());
                                txt_email.setText(email);

                                String Phonenumber = (response.body().getData().getPhonenumber());

                                if (Phonenumber.equalsIgnoreCase("") && Phonenumber == null) {
                                    txt_phonenumber.setText(response.body().getData().getPhonenumber());
                                } else {
                                    txt_phonenumber.setText("No Available");
                                }
                            }
                        } else {

                            Utility.showMessage(ProfileActivity.this, false, response.body().getMessage());

                        }
                    }

                    @Override
                    public void onFailure(Call<GetOwnerPRofileResponse> call, Throwable t) {

                    }
                });

            } else {
                Utility.showNoInternetAvailable(ProfileActivity.this);
            }

        } else {
            if (Utility.isInternetAvailable(ProfileActivity.this)) {
                Utility.showCustomProgressDialog(ProfileActivity.this);


                RestClient.getInstance().getApiInterface().getprofile_response(PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID)).enqueue(new Callback<GetProfileResponse>() {
                    @Override
                    public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                        Utility.hideCustomProgressDialog();
                        if (response.body().getStatus()) {
                            if (response.body().getData() != null) {
//                        Utility.showMessage(ProfileActivity.this, true, response.body().getMessage());
                                Glide.with(ProfileActivity.this).load(response.body().getData().getProfileImage()).placeholder(R.drawable.profile).into(img_profile);
                                String First_name = (response.body().getData().getFirstname());
                                String Last_name = (response.body().getData().getLastname());
                                txt_name.setText(First_name + " " + Last_name);

                                String email = (response.body().getData().getEmail());
                                txt_email.setText(email);

                                String Phonenumber = (response.body().getData().getPhonenumber());

                                if (Phonenumber.equalsIgnoreCase("") && Phonenumber == null) {
                                    txt_phonenumber.setText(response.body().getData().getPhonenumber());
                                } else {
                                    txt_phonenumber.setText("No Available");
                                }
                            }
                        } else {

                            Utility.showMessage(ProfileActivity.this, false, response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                        Utility.hideCustomProgressDialog();
                    }
                });
            } else {
                Utility.showNoInternetAvailable(ProfileActivity.this);
            }

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }

}