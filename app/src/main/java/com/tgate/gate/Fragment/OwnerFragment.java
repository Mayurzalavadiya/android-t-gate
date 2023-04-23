package com.tgate.gate.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
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
import com.tgate.gate.Activity.ForgetpasswordActivity;
import com.tgate.gate.Activity.MainActivity;
import com.tgate.gate.Activity.OwnerMainActivity;
import com.tgate.gate.Activity.SignupActivity;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.LoginOwnerResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OwnerFragment extends Fragment {

    ImageView img_showpass;
    AppCompatEditText edt_email_id, edt_password;

    AppCompatTextView txt_signup, txt_forgot_password;
    MaterialButton btn_login;


    public OwnerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_owner, container, false);

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
                            Log.i("Firebase token Owner", "Refreshed token: " + token);
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

        txt_signup = view.findViewById(R.id.txt_signup);
        txt_forgot_password = view.findViewById(R.id.txt_forgot_password);

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.Intent(getActivity(), SignupActivity.class);
            }
        });

        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.Intent(getActivity(), ForgetpasswordActivity.class);
            }
        });
        return view;
    }


    private boolean checkValidation() {
        if (isEmpty(edt_email_id)) {
            Utility.showMessage(getActivity(), false, "Please Enter Email ID");
            edt_email_id.requestFocus();
            return false;
        } else if (!Utility.isEmail(edt_email_id.getText().toString())) {
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
            String Devicename = Utility.getDeviceName();
            String DeviceVerson = Utility.getappVersion(getContext());
            String owner = "OWNER";

            RestClient.getInstance().getApiInterface().loginownerresponse(Email, Password, owner, PrefsManager.readStringPrefsVal(PrefsManager.FCM_TOKEN),
                    Devicename, currentapiVersion, DeviceVerson, "ANDROID").enqueue(new Callback<LoginOwnerResponse>() {
                @Override
                public void onResponse(Call<LoginOwnerResponse> call, Response<LoginOwnerResponse> response) {
                    Utility.hideCustomProgressDialog();
                    if(response.isSuccessful())
                    {
                        if (response.body().getStatus()) {
                            Utility.showMessage(getActivity(), true, response.body().getMessage());

                            // pref value save
                            PrefsManager.savePrefsVal(PrefsManager.IS_LOGIN, owner);
                            PrefsManager.savePrefsVal(PrefsManager.STAFF_ID, response.body().getData().getUserid());
                            PrefsManager.savePrefsVal(PrefsManager.FIRST_NAME, response.body().getData().getFirstname());
                            Object image = response.body().getData().getProfileImage();
                            PrefsManager.savePrefsVal(PrefsManager.PHONE_NUMBER, String.valueOf(response.body().getData().getPhonenumber()));
                            PrefsManager.savePrefsVal(PrefsManager.PASSWORD, Password);
                            PrefsManager.savePrefsVal(PrefsManager.PROFILE_IMAGE, String.valueOf(response.body().getData().getProfileImage()));

                            Log.d("@@@_is_login",PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN));
                            Log.d("@@@_staff_id",PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID));
                            Log.d("@@@_owner_name",PrefsManager.readStringPrefsVal(PrefsManager.FIRST_NAME));
                            Log.d("@@@_owner_name",PrefsManager.readStringPrefsVal(PrefsManager.PASSWORD));

                            Utility.Intent(getActivity(), OwnerMainActivity.class);
                            getActivity().finish();

                        } else {
                            Utility.showMessage(getActivity(), false, response.body().getMessage());
                        }
                    }

                }

                @Override
                public void onFailure(Call<LoginOwnerResponse> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                    Utility.showMessage(getActivity(), false, t.getMessage());

                }
            });
        } else {
            Utility.showNoInternetAvailable(getActivity());
        }
    }


}