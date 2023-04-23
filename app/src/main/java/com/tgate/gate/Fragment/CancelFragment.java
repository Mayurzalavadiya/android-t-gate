package com.tgate.gate.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tgate.gate.Adapter.visitorListAdapter;
import com.tgate.gate.Adapter.visitordataListAdapter;
import com.tgate.gate.Pagination.EndlessRecyclerOnScrollListener;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.VisitorDataGuardresponse;
import com.tgate.gate.apiResponse.VisitordataResponse;
import com.tgate.gate.model.getVisitorList;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelFragment extends Fragment {

    RecyclerView rvVisitorList;
    ArrayList<VisitorDataGuardresponse.Datum> getVisitorList;
    visitordataListAdapter visitordataListAdapter;
    int currentpage = 1;
    boolean isIndex1;
    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    public CancelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cancel, container, false);
        rvVisitorList = view.findViewById(R.id.rvVisitorList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvVisitorList.setLayoutManager(layoutManager);

        getVisitorList = new ArrayList<>();
        visitordataListAdapter = new visitordataListAdapter(getVisitorList, getContext());
        rvVisitorList.setAdapter(visitordataListAdapter);
        cancle(1);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                currentpage = current_page;
                isIndex1 = false;
                cancle(current_page);
                Log.d("CPage", String.valueOf(current_page));

            }

        };

        rvVisitorList.addOnScrollListener(endlessRecyclerOnScrollListener);
        return view;
    }

    private void cancle(int page) {

        if (Utility.isInternetAvailable(getActivity())) {
//            Utility.showCustomProgressDialog(getActivity());
            RestClient.getInstance().getApiInterface().visitordata_Guard_response("CANCELLED", page, PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID), PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN)).enqueue(new Callback<VisitorDataGuardresponse>() {
                @Override
                public void onResponse(Call<VisitorDataGuardresponse> call, Response<VisitorDataGuardresponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus() != null && response.body().getStatus().equals(true)) {
                            List<VisitorDataGuardresponse.Datum> b = response.body().getData();
                            if (b != null && b.size() > 0) {

                                if (page == 1)
                                    visitordataListAdapter.replaceAll(b);
                                else
                                    visitordataListAdapter.addAllS(b);


                            }
                            else {
                                Utility.hideCustomProgressDialog();
                                endlessRecyclerOnScrollListener.previousState();
                            }

                        } else {
                            endlessRecyclerOnScrollListener.previousState();
                            Utility.showMessage(getActivity(), false, response.body().getMessage());
                        }
                    } /*else {
                        Utility.showMessage(getActivity(), false, getString(R.string.data_not_Available));
                    }*/
                }

                @Override
                public void onFailure(Call<VisitorDataGuardresponse> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                    endlessRecyclerOnScrollListener.previousState();
                    Utility.showMessage(getActivity(), false, t.getMessage());
                }
            });
        } else {
            Utility.showNoInternetAvailable(getActivity());
        }
    }
}