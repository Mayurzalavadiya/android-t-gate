package com.tgate.gate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.SignUpResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    AppCompatEditText edt_firstname, edt_lastname, edt_email_id, edt_companyname;
    MaterialButton btn_signup;
    String is_login = "OWNER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edt_firstname = findViewById(R.id.edt_firstname);
        edt_lastname = findViewById(R.id.edt_lastname);
        edt_email_id = findViewById(R.id.edt_email_id);
        edt_companyname = findViewById(R.id.edt_companyname);
        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    Signup();
                }
            }
        });
    }


    private boolean checkValidation() {
        if (isEmpty(edt_firstname)) {
            Utility.showMessage(this, false, "Please Enter Firstnmae");
            return false;
        } else if (isEmpty(edt_lastname)) {
            Utility.showMessage(this, false, "Please Enter Lastname");
            return false;
        } else if (isEmpty(edt_email_id)) {
            Utility.showMessage(this, false, "Please Enter Email ID");
            edt_email_id.requestFocus();
            return false;
        } else if (!Utility.isEmail(edt_email_id.getText().toString())) {
            Utility.showMessage(this, false, "Please Enter Valid Email ID");
            edt_email_id.requestFocus();
            return false;
        } else if (isEmpty(edt_companyname)) {
            Utility.showMessage(this, false, "Please Enter Company Name");
            return false;
        } else {
            return true;
        }
    }

    private boolean isEmpty(EditText edt) {
        CharSequence str = edt.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }


    private void Signup() {
        if (Utility.isInternetAvailable(SignupActivity.this)) {

            Utility.showCustomProgressDialog(SignupActivity.this);

            String First_name = edt_firstname.getText().toString();
            String Last_name = edt_lastname.getText().toString();
            String Email_id = edt_email_id.getText().toString();
            String Company_name = edt_companyname.getText().toString();


            RestClient.getInstance().getApiInterface().signupresponse(First_name, Last_name, Email_id, Company_name).enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                   Utility.hideCustomProgressDialog();


                    if (response.body().getStatus()!=null) {


                        Utility.showToast(SignupActivity.this, response.body().getMessage());

                        // pref value save
                        PrefsManager.savePrefsVal(PrefsManager.IS_LOGIN, is_login);
                        Log.d("@@@_is_login", is_login);
                        PrefsManager.savePrefsVal(PrefsManager.FIRST_NAME,First_name);
                        Log.d("@@@_First_Name", First_name);
                        PrefsManager.savePrefsVal(PrefsManager.LAST_NAME,Last_name);
                        Log.d("@@@_Last_Name", Last_name);
                        PrefsManager.savePrefsVal(PrefsManager.EMAIL,Email_id);
                        Log.d("@@@_Last_Name",Email_id);
                        PrefsManager.savePrefsVal(PrefsManager.COMPANY_NAME,Company_name);
                        Log.d("@@@_Company_Name",Company_name);
                        //PrefsManager.savePrefsVal(PrefsManager.COMPANY_NMAE, response.body().getError().getCompany());
                        //PrefsManager.savePrefsVal(PrefsManager.COMPANY_EMAIL,response.body().getError().getEmail());

                        Utility.Intent(SignupActivity.this, LoginActivity.class);
                        finish();

                    } else {
                        Utility.showMessage(SignupActivity.this, false, response.body().getMessage());

                    }

                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                    Utility.showMessage(SignupActivity.this, false, t.getMessage());

                }
            });
        } else {
            Utility.showNoInternetAvailable(SignupActivity.this);
        }
    }
}