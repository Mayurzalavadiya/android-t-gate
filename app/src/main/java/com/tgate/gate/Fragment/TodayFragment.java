package com.tgate.gate.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tgate.gate.Adapter.visitordataAdapter;
import com.tgate.gate.Pagination.EndlessRecyclerOnScrollListener;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.VisitorDataGuardresponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodayFragment extends Fragment {

    RecyclerView rvVisitorList;

    SearchView searchview;

    //Guard
    ArrayList<VisitorDataGuardresponse.Datum> getVisitorGuardList;

    int currentpage = 1;
    boolean isIndex1;
    visitordataAdapter visitordataadapter;
    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    public TodayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);


        rvVisitorList = view.findViewById(R.id.rvVisitorList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvVisitorList.setLayoutManager(linearLayoutManager);

        getVisitorGuardList = new ArrayList<>();
        visitordataadapter = new visitordataAdapter(getVisitorGuardList, getContext());
        rvVisitorList.setAdapter(visitordataadapter);
        hostList(1);

        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                currentpage = current_page;
                isIndex1 = false;
                hostList(current_page);
                Log.d("TPage", String.valueOf(current_page));

            }

        };


        rvVisitorList.addOnScrollListener(endlessRecyclerOnScrollListener);
        searchview = view.findViewById(R.id.searchview);
/*
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                visitordataadapter.getFilter().filter(newText);
                return false;
            }
        });
*/
        return view;
    }

    private void hostList(int page) {

        if (Utility.isInternetAvailable(getActivity())) {
//            Utility.showCustomProgressDialog(getActivity());
            RestClient.getInstance().getApiInterface().visitordata_Guard_response("TODAY", page, PrefsManager.readStringPrefsVal(PrefsManager.STAFF_ID), PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN)).enqueue(new Callback<VisitorDataGuardresponse>() {
                @Override
                public void onResponse(Call<VisitorDataGuardresponse> call, Response<VisitorDataGuardresponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus() != null && response.body().getStatus().equals(true)) {
                            List<VisitorDataGuardresponse.Datum> b = response.body().getData();
                            if (b != null && b.size() > 0) {

                                if (page == 1)
                                    visitordataadapter.replaceAll(b);
                                else
                                    visitordataadapter.addAllS(b);


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
                        endlessRecyclerOnScrollListener.previousState();
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
