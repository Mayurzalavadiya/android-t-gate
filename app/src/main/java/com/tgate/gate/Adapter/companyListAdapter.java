package com.tgate.gate.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tgate.gate.Activity.HostActivity;
import com.tgate.gate.Activity.VisitorsActivity;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.CompanyDataResponse;
import com.tgate.gate.apiResponse.ContactlistGuardResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class companyListAdapter extends RecyclerView.Adapter<companyListAdapter.myCompanyListHolder> {
    Activity context;
    List<CompanyDataResponse.Datum> getCompanyList;
    List<ContactlistGuardResponse.Datum> getContactList;
    RecyclerView rvhostlist;


    public companyListAdapter(Context context, List<CompanyDataResponse.Datum> getCompanyList) {
        this.context = (Activity) context;
        this.getCompanyList = getCompanyList;
    }

    @NonNull
    @Override
    public companyListAdapter.myCompanyListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_company_list, parent, false);
        return new myCompanyListHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull companyListAdapter.myCompanyListHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txt_companyname.setText(getCompanyList.get(position).getCompany());
        holder.txt3_number.setText(getCompanyList.get(position).getUserid());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PrefsManager.savePrefsVal(PrefsManager.COMPANY_HOST_USER_ID, getCompanyList.get(position).getUserid());
            PrefsManager.savePrefsVal(PrefsManager.COMPANY_HOST_NAME, getCompanyList.get(position).getCompany());
            Log.d("@@@_Company_id", PrefsManager.readStringPrefsVal(PrefsManager.COMPANY_HOST_USER_ID));
//            Utility.showMessage(context,true,getCompanyList.get(position).getUserid());
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext(), R.style.alertDialogThem);
            bottomSheetDialog.setContentView(R.layout.bottomsheetdialog_hostlist);
            bottomSheetDialog.setCanceledOnTouchOutside(true);

            rvhostlist = bottomSheetDialog.findViewById(R.id.rvhostlist);
            hostlist();
            StaggeredGridLayoutManager managar = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
            rvhostlist.setLayoutManager(managar);
            rvhostlist.setItemAnimator(new DefaultItemAnimator());

            AppCompatImageView img_close = bottomSheetDialog.findViewById(R.id.img_close);
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.show();
       }
    });



}

    private void hostlist() {
        if (Utility.isInternetAvailable(context)) {
            Utility.showCustomProgressDialog(context);
            RestClient.getInstance().getApiInterface().contactlist_guard_response(PrefsManager.readStringPrefsVal(PrefsManager.COMPANY_HOST_USER_ID)).enqueue(new Callback<ContactlistGuardResponse>() {
                @Override
                public void onResponse(Call<ContactlistGuardResponse> call, Response<ContactlistGuardResponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body().getData().size() > 0) {
                        getContactList = response.body().getData();
                        hostAdapter hostAdapter = new hostAdapter(context, getContactList);
                        rvhostlist.setAdapter(hostAdapter);
                        hostAdapter.notifyDataSetChanged();
                    } else {
                        Utility.showMessage(context, false, response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ContactlistGuardResponse> call, Throwable t) {

                    Utility.hideCustomProgressDialog();
                    Utility.showMessage(context, false, t.getMessage());
                }
            });
        } else {
            Utility.showNoInternetAvailable(context);
        }
    }

    @Override
    public int getItemCount() {
        return getCompanyList.size();
    }

    public class myCompanyListHolder extends RecyclerView.ViewHolder {
        AppCompatTextView txt_companyname;
        AppCompatTextView txt3_number;
        CardView maincard;
        public myCompanyListHolder(View itemView) {
            super(itemView);
            maincard = itemView.findViewById(R.id.maincard);
            txt_companyname = itemView.findViewById(R.id.txt_companyname);
            txt3_number = itemView.findViewById(R.id.txt3_number);


        }
    }
}