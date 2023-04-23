package com.tgate.gate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.ForgotPasswordResponse;
import com.tgate.gate.util.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetpasswordActivity extends AppCompatActivity {

    AppCompatEditText edit_email_id;
    MaterialButton btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        edit_email_id = findViewById(R.id.edit_email_id);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {

                    ForgotPassword();
                }
            }
        });

    }

    private void ForgotPassword() {
        if (Utility.isInternetAvailable(ForgetpasswordActivity.this))
        {
            Utility.showCustomProgressDialog(ForgetpasswordActivity.this);
            String Email = edit_email_id.getText().toString().trim();
//            String is_login = PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN);
//            Log.d("@@@_Forgot_Email", PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN));
            RestClient.getInstance().getApiInterface().forgotpasswordresponse(Email, "OWNER").enqueue(new Callback<ForgotPasswordResponse>() {
                @Override
                public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body().getStatus()) {
                        Utility.showMessage(ForgetpasswordActivity.this, true, response.body().getMessage());
//                        Utility.Intent(ForgetpasswordActivity.this,LoginActivity.class);
                    } else {
                        Utility.showMessage(ForgetpasswordActivity.this, false, response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                    Utility.showMessage(ForgetpasswordActivity.this, false, t.getMessage());
                }
            });
        }
    }

    private boolean checkValidation() {
        if (Utility.isEmpty(edit_email_id)) {
            Utility.showMessage(ForgetpasswordActivity.this, false, "Please Enter Email ID");
            return false;
        } else if (!Utility.isEmail(edit_email_id.getText().toString().trim())) {
            Utility.showMessage(ForgetpasswordActivity.this, false, "Please Enter Valid Email ID");
            return false;
        } else {
            return true;
        }
    }

    private boolean isEmpty(EditText edt) {
        CharSequence str = edt.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }
}