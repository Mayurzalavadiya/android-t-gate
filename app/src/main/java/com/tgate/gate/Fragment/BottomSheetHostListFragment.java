package com.tgate.gate.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tgate.gate.Adapter.hostListAdapter;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.ContactlistGuardResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BottomSheetHostListFragment extends BottomSheetDialogFragment {

    List<ContactlistGuardResponse.Datum> getContactlist;
    RecyclerView rvhostlist;
    com.tgate.gate.Adapter.hostListAdapter hostListAdapter;
    AppCompatImageView img_close_bottom_host_list;

    public static BottomSheetHostListFragment newInstance() {
        return new BottomSheetHostListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bottomsheet_hostlist, container, false);
        rvhostlist = view.findViewById(R.id.rvhostlist);
        img_close_bottom_host_list= view.findViewById(R.id.img_close_bottom_host_list);
        img_close_bottom_host_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        hostlist();

        rvhostlist.setLayoutManager(new LinearLayoutManager(getContext()));
        rvhostlist.setItemAnimator(new DefaultItemAnimator());


        return view;

    }
    private void hostlist() {
        if (Utility.isInternetAvailable(getContext())) {
            Utility.showCustomProgressDialog(getContext());
            RestClient.getInstance().getApiInterface().contactlist_guard_response(PrefsManager.readStringPrefsVal(PrefsManager.COMPANY_USER_ID)).enqueue(new Callback<ContactlistGuardResponse>() {
                @Override
                public void onResponse(Call<ContactlistGuardResponse> call, Response<ContactlistGuardResponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body().getData().size() > 0) {
                        getContactlist = response.body().getData();
                        hostListAdapter = new hostListAdapter(getContext(), getContactlist,img_close_bottom_host_list);
                        rvhostlist.setAdapter(hostListAdapter);
                        hostListAdapter.notifyDataSetChanged();
                    } else {
                        Utility.showMessage(getActivity(), false, response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ContactlistGuardResponse> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                    Utility.showMessage(getActivity(), false, t.getMessage());
                }
            });
        } else {
            Utility.showNoInternetAvailable(getActivity());
        }
    }

}