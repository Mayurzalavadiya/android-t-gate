package com.tgate.gate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.tgate.gate.R;
import com.tgate.gate.util.Utility;

public class OtpownerActivity extends AppCompatActivity implements View.OnClickListener {

    View img_clear;
    AppCompatEditText edt_otp1,edt_otp2,edt_otp3,edt_otp4;

    MaterialButton btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpowner);

        edt_otp1 = findViewById(R.id.edt_otp1);
        edt_otp2 = findViewById(R.id.edt_otp2);
        edt_otp3 = findViewById(R.id.edt_otp3);
        edt_otp4 = findViewById(R.id.edt_otp4);

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        img_clear = findViewById(R.id.img_clear);
        img_clear.setOnClickListener(this);
        otp();
    }

    private void otp() {
        edt_otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    edt_otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.img_clear:
                clear();
                break;
            case R.id.btn_submit:
                if (!edt_otp1.getText().toString().trim().isEmpty() && !edt_otp2.getText().toString().trim().isEmpty() &&
                        !edt_otp3.getText().toString().trim().isEmpty() && !edt_otp4.getText().toString().trim().isEmpty()){
//                    Utility.showMessage(OtpownerActivity.this,false,"OTP verify");
                    String OTP = edt_otp1.getText().toString()+edt_otp2.getText().toString()+
                            edt_otp3.getText().toString()+edt_otp4.getText().toString();
                }
                else {
                    Utility.showMessage(OtpownerActivity.this,false,"Please ennter proper OTP");
//                    Utility.Intent(this, MainActivity.class);x
                }
                break;
        }

    }

    private void clear() {
        edt_otp1.setText("");
        edt_otp2.setText("");
        edt_otp3.setText("");
        edt_otp4.setText("");

    }
}
