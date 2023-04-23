package com.tgate.gate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tgate.gate.Fragment.BottomSheetHostListFragment;
import com.tgate.gate.Interface.IntentReceiver;
import com.tgate.gate.R;
import com.tgate.gate.api.MultipartUtil;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.CompanyDataResponse;
import com.tgate.gate.apiResponse.ContactlistGuardResponse;
import com.tgate.gate.apiResponse.VisitorDetailsResponse;
import com.tgate.gate.util.Constant;
import com.tgate.gate.util.FilePath;
import com.tgate.gate.util.PermissionUtil;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

public class VisitorDetails extends AppCompatActivity {

    AppCompatImageView check_in_back, img_editimage, img_visitorimg, img_notification;
    AppCompatTextView txt_date, txt_time, txt_document, edt_selecthost;
    AppCompatEditText edt_name, edit_email, edit_phone, edt_no_person, edit_title, edt_remark;
    MaterialButton btn_submit;
    String cityarray[];
    AppCompatSpinner spinner_companys, spinner_city;
    ArrayList<String> Cname;
    List<CompanyDataResponse.Datum> getCompanyDetail;

    List<ContactlistGuardResponse.Datum> getContactlist;

    //Uri
    Uri Profile;
    String Document;

    //stor images uries in array list
    ArrayList<String> imagePathList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitordetails);

//        PrefsManager.removeKey(PrefsManager.CONTACT_USER_ID);
        edt_name = findViewById(R.id.edt_name);
        edit_email = findViewById(R.id.edit_email);
        edit_phone = findViewById(R.id.edit_phone);
        edt_remark = findViewById(R.id.edt_remark);
        edit_title = findViewById(R.id.edit_title);
        edt_no_person = findViewById(R.id.edt_no_person);

        cityarray = getResources().getStringArray(R.array.City);

        spinner_city = findViewById(R.id.spinner_city);
        ArrayAdapter adapter = new ArrayAdapter(VisitorDetails.this, android.R.layout.simple_list_item_1, cityarray);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner_city.setAdapter(adapter);
        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PrefsManager.savePrefsVal(PrefsManager.CITY, cityarray[position]);
                Log.d("@@@_CITY", cityarray[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_companys = findViewById(R.id.spinner_companys);
        Companyname();

        edt_selecthost = findViewById(R.id.edt_selecthost);

        img_notification = findViewById(R.id.img_notification);
        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.Intent(VisitorDetails.this, NotificationActivity.class);
            }
        });

        edt_selecthost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PrefsManager.readStringPrefsVal(PrefsManager.COMPANY_USER_ID) != null) {


                    BroadcastReceiver receiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {

                            if (intent.getExtras() != null) {
//                                String first_name = (intent.getStringExtra("host_first_name"));
//                                String last_name = (intent.getStringExtra("host_last_name"));
                                edt_selecthost.setText((intent.getStringExtra("host_first_name") + " " + (intent.getStringExtra("host_last_name"))));
                            }
                        }
                    };
                    LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, IntentReceiver.getBroadcast());

                    BottomSheetHostListFragment hostlistFragment =
                            BottomSheetHostListFragment.newInstance();
                    hostlistFragment.show(getSupportFragmentManager(), "");


                } else {
                    Utility.showMessage(VisitorDetails.this, false, "Please Select Company");
                }
            }

        });


        img_visitorimg = findViewById(R.id.img_visitorimg);
        img_visitorimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(VisitorDetails.this).withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            ImagePicker.Companion.with(VisitorDetails.this)
                                    .crop()
                                    .galleryOnly()
                                    .cropOval()
                                    .maxResultSize(1080, 1080)
                                    .start(Constant.IMAGE_CODE);
                        } else {
                            PermissionUtil.showPermissionDailog(VisitorDetails.this);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

            }
        });

        img_editimage = findViewById(R.id.img_editimage);
        img_editimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(VisitorDetails.this).withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            ImagePicker.Companion.with(VisitorDetails.this)
                                    .crop()
                                    .galleryOnly()
                                    .cropOval()
                                    .maxResultSize(1080, 1080)
                                    .start(Constant.IMAGE_CODE);
                        } else {
                            PermissionUtil.showPermissionDailog(VisitorDetails.this);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

            }
        });

        txt_document = findViewById(R.id.txt_document);
        txt_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(VisitorDetails.this).withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            Intent intent = new Intent();
                            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            intent.setType("*/*");
                            startActivityForResult(Intent.createChooser(intent, "Select File"), Constant.Document_CODE);
                        } else {
                            PermissionUtil.showPermissionDailog(VisitorDetails.this);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
            }
        });

        check_in_back = findViewById(R.id.check_in_back);
        check_in_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(calendar.getTime());
        txt_date =

                findViewById(R.id.txt_date);
        txt_date.setText(date);

        SimpleDateFormat timeFormet = new SimpleDateFormat("HH:mm a");
        String time = timeFormet.format(calendar.getTime());
        txt_time =

                findViewById(R.id.txt_time);
        txt_time.setText(time);

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkValidation()) {
                    VisitorData();

                    Utility.showMessage(VisitorDetails.this, true, "Submit successfully");
                }
            }
        });

    }

    private void VisitorData() {
        String visitorspurpose_id = PrefsManager.readStringPrefsVal(PrefsManager.VISITOR_PURPOSE_ID);
        String staff_id = PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID);
        String clients_id = PrefsManager.readStringPrefsVal(PrefsManager.COMPANY_USER_ID);
        String contacts_id = PrefsManager.readStringPrefsVal(PrefsManager.HOST_USER_ID);
        String name = edt_name.getText().toString();
        String email = edit_email.getText().toString();
        String phone = edit_phone.getText().toString();
        String title = edit_title.getText().toString();
        String number_of_persons = edt_no_person.getText().toString();
        String checkin_date = txt_date.getText().toString();
        String checkin_time = txt_time.getText().toString();
        String profile = PrefsManager.readStringPrefsVal(PrefsManager.PROFILE_IMAGE);
        String city = PrefsManager.readStringPrefsVal(PrefsManager.CITY);
//        String document[] =  imagePathList;
        String remark = edt_remark.getText().toString();


        if (Utility.isInternetAvailable(VisitorDetails.this)) {
            Utility.showCustomProgressDialog(VisitorDetails.this);

         /*   RestClient.getInstance().getApiInterface().visitordetailsresponse(visitorspurpose_id, staff_id, clients_id, contacts_id, name, email, phone, title, number_of_persons, remark, city, checkin_date, checkin_time, , imagePathList).enqueue(new Callback<VisitorDetailsResponse>() {
                @Override
                public void onResponse(Call<VisitorDetailsResponse> call, Response<VisitorDetailsResponse> response) {


                }

                @Override
                public void onFailure(Call<VisitorDetailsResponse> call, Throwable t) {

                }
            });*/

        } else {
            Utility.showNoInternetAvailable(VisitorDetails.this);
        }

    }


    private void Companyname() {
        if (Utility.isInternetAvailable(VisitorDetails.this)) {
            Utility.showCustomProgressDialog(VisitorDetails.this);
            RestClient.getInstance().getApiInterface().company_data_response(PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID)).enqueue(new Callback<CompanyDataResponse>() {
                @Override
                public void onResponse(Call<CompanyDataResponse> call, Response<CompanyDataResponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus()) {
                            Cname = new ArrayList<>();
                            getCompanyDetail = response.body().getData();
                            for (int i = 0; i < getCompanyDetail.size(); i++) {
                                String Company_name = getCompanyDetail.get(i).getCompany();
                                Cname.add(Company_name);
                            }

                            ArrayAdapter arrayAdapter = new ArrayAdapter(VisitorDetails.this, android.R.layout.simple_spinner_item, Cname);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
                            spinner_companys.setAdapter(arrayAdapter);

                            spinner_companys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    PrefsManager.savePrefsVal(PrefsManager.COMPANY_USER_ID, getCompanyDetail.get(position).getUserid());
                                    Log.d("@@@_Company_User_Id", PrefsManager.readStringPrefsVal(PrefsManager.COMPANY_USER_ID));

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }
                    }
                }

                @Override
                public void onFailure(Call<CompanyDataResponse> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                }

            });

        } else {
            Utility.showNoInternetAvailable(VisitorDetails.this);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.IMAGE_CODE) {
            if (resultCode == RESULT_OK) {
                Profile = data.getData();
                img_visitorimg.setImageURI(Profile);
                String iage_path = FilePath.getPath(VisitorDetails.this, Profile);
                PrefsManager.savePrefsVal(PrefsManager.PROFILE_IMAGE, iage_path);
                Log.d("@@@_Profile_Image", FilePath.getPath(VisitorDetails.this, Profile));
            }
        }

        if (requestCode == Constant.Document_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {

                    if (data.getClipData() != null) {
                        imagePathList = new ArrayList<>();
                        for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                            Document = FilePath.getPath(VisitorDetails.this, data.getClipData().getItemAt(i).getUri());
                            imagePathList.add(Document);
//                        Log.d("@@@_path " + i + "=", Document);
                            Log.d("@@@_path", imagePathList.toString());
//                        MultipartBody.Part imagepart = MultipartUtil.prepareFilePart("dfdf", FilePath.getPath(getApplication(), Uri.parse(Document)));
                            txt_document.setText("you have " + (i + 1) + " Document selected");
                        }
                    } /*else {
                        imagePathList = new ArrayList<>();
                        Uri uri = data.getData();
                        String doc = FilePath.getPath(VisitorDetails.this, uri);
                        imagePathList.add(doc);
                        txt_document.setText("you have " + (1) + " Document selected");
                        Log.d("@@@_path", imagePathList.toString());

                    }*/
                }
            }
        }
    }


    private boolean checkValidation() {

        if (isEmpty(edt_name)) {
            Utility.showMessage(VisitorDetails.this, false, "Please Enter Name");
            edt_name.requestFocus();
            return false;
        } else if (isEmpty(edit_email)) {
            Utility.showMessage(VisitorDetails.this, false, "Please Enter Email Id");
            edit_email.requestFocus();
            return false;
        } else if (!Utility.isEmail(edit_email.getText().toString().trim())) {
            Utility.showMessage(VisitorDetails.this, false, "Please Enter valid Email Id");
            edit_email.requestFocus();
            return false;
        } else if (isEmpty(edit_phone)) {
            Utility.showMessage(VisitorDetails.this, false, "Please Enter Phone Number");
            edit_phone.requestFocus();
            return false;
        } else if (ismobile(edit_phone)) {
            Utility.showMessage(VisitorDetails.this, false, "Please Enter valid Phone Number");
            edit_phone.requestFocus();
            return false;
        } else if (isEmpty(edit_title)) {
            Utility.showMessage(VisitorDetails.this, false, "Please Enter Title");
            edit_title.requestFocus();
            return false;
        } else if (isEmpty(edt_remark)) {
            Utility.showMessage(VisitorDetails.this, false, "Please Enter Remark");
            edt_remark.requestFocus();
            return false;
        } else if (isEmpty(edt_no_person)) {
            Utility.showMessage(VisitorDetails.this, false, "Please Enter Number of Person");
            edt_no_person.requestFocus();
            return false;
        } else if (Document == null) {
            Utility.showMessage(VisitorDetails.this, false, "Please select Document");
            txt_document.requestFocus();
            return false;
        } else if (edt_selecthost.getText().toString().equalsIgnoreCase("")) {
            Utility.showMessage(VisitorDetails.this, false, "Please select Host");
            txt_document.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    private boolean isEmpty(AppCompatEditText edt) {
        CharSequence str = edt.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private boolean ismobile(EditText edt) {
        int mlengh = edt.getText().length();
        if (mlengh == 10) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }
}