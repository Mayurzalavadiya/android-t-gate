package com.tgate.gate.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tgate.gate.Activity.MainActivity;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.LoginGuardResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GuardFragment extends Fragment {

    MaterialButton btn_login;
    ImageView img_showpass;
    AppCompatEditText edt_email_id, edt_password;


    public GuardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guard, container, false);



        edt_email_id = view.findViewById(R.id.edt_email_id);
        edt_password = view.findViewById(R.id.edt_password);
        img_showpass = view.findViewById(R.id.img_showpass);

        img_showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.img_showpass) {

                    if (edt_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        img_showpass.setImageResource(R.drawable.icon_showpassword);

                        //Show Password
                        edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        img_showpass.setImageResource(R.drawable.icon_hidepassword);

                        //Hide Password
                        edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());


                    }
                }
            }
        });


/*
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        if(task.isSuccessful()){
                            String token=task.getResult().getToken();
                            PrefsManager.savePrefsVal(PrefsManager.FCM_TOKEN, token);
                            Log.i("Firebase token Guard", "Refreshed token: " + token);
                        }else{
                        }

                    }
                });
*/

        btn_login = view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {


                    loginApiCall();

                }

            }
        });

        return view;
    }

    private boolean checkValidation() {

        if (isEmpty(edt_email_id)) {
            Utility.showMessage(getActivity(), false, "Please Enter Email ID");
            edt_email_id.requestFocus();
            return false;
        } else if (!Utility.isEmail(edt_email_id.getText().toString().trim())) {
            Utility.showMessage(getActivity(), false, "Please Enter Valid Email ID");
            edt_email_id.requestFocus();
            return false;
        } else if (isEmpty(edt_password)) {
            Utility.showMessage(getActivity(), false, "Please Enter Password");
            edt_password.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    boolean isEmpty(EditText edt) {
        CharSequence str = edt.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }


    private void loginApiCall() {

        if (Utility.isInternetAvailable(getContext())) {
            Utility.showCustomProgressDialog(getContext());

            String Email = edt_email_id.getText().toString();
            String Password = edt_password.getText().toString();
            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
            String deviceName = Utility.getDeviceName();
            String version = Utility.getappVersion(getContext());
            String guard = "GUARD";

            RestClient.getInstance().getApiInterface().loginguardresponse(Email, Password, guard, PrefsManager.readStringPrefsVal(PrefsManager.FCM_TOKEN), deviceName,
                    currentapiVersion, version, "ANDROID").enqueue(new Callback<LoginGuardResponse>() {
                @Override
                public void onResponse(Call<LoginGuardResponse> call, Response<LoginGuardResponse> response) {
                    Utility.hideCustomProgressDialog();

                    if (response.body().getStatus()) {
                        Utility.showMessage(getActivity(), true, response.body().getMessage());

                        // pref value save
                        PrefsManager.savePrefsVal(PrefsManager.IS_LOGIN, guard);
                        PrefsManager.savePrefsVal(PrefsManager.STAFF_ID, response.body().getData().getStaffid());
                        PrefsManager.savePrefsVal(PrefsManager.FIRST_NAME, response.body().getData().getFirstname());
                        Object image = response.body().getData().getProfileImage();
                        PrefsManager.savePrefsVal(PrefsManager.PHONE_NUMBER, String.valueOf(response.body().getData().getPhonenumber()));
                        PrefsManager.savePrefsVal(PrefsManager.PROFILE_IMAGE, String.valueOf(response.body().getData().getProfileImage()));

                        Log.d("@@@_is_login",PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN));
                        Log.d("@@@_staff_id",PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID));
                        Log.d("@@@_guard_name",PrefsManager.readStringPrefsVal(PrefsManager.FIRST_NAME));



                        Utility.Intent(getActivity(), MainActivity.class);
                        getActivity().finish();

                    } else {
                        Utility.showMessage(getActivity(), false, response.body().getMessage());
                    }

                }

                @Override
                public void onFailure(Call<LoginGuardResponse> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                    Utility.showMessage(getActivity(), false, t.getMessage());

                }
            });
        } else {
            Utility.showNoInternetAvailable(getActivity());
        }
    }
}


