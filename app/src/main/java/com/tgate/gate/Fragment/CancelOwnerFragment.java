package com.tgate.gate.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tgate.gate.Adapter.visitorOwnerdataAdapter;
import com.tgate.gate.Adapter.visitorOwnerdataListAdapter;
import com.tgate.gate.Pagination.EndlessRecyclerOnScrollListener;
import com.tgate.gate.R;
import com.tgate.gate.api.RestClient;
import com.tgate.gate.apiResponse.VisitordataResponse;
import com.tgate.gate.util.PrefsManager;
import com.tgate.gate.util.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelOwnerFragment extends Fragment {

    RecyclerView rvVisitorList;
    //OWNER
    ArrayList<VisitordataResponse.Datum> getVisitorOwnerList;
    visitorOwnerdataListAdapter visitorownerdataListAdapter;
    int currentpage = 1;
    boolean isIndex1;

    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;


    public CancelOwnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cancel_owner, container, false);
        rvVisitorList = view.findViewById(R.id.rvVisitorList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvVisitorList.setLayoutManager(linearLayoutManager);

        getVisitorOwnerList = new ArrayList<>();
        visitorownerdataListAdapter = new visitorOwnerdataListAdapter(getVisitorOwnerList, getContext());
        rvVisitorList.setAdapter(visitorownerdataListAdapter);
        hostList(1);

        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                currentpage = current_page;
                isIndex1 = false;
                hostList(current_page);
            }

        };


        rvVisitorList.addOnScrollListener(endlessRecyclerOnScrollListener);

        return view;
    }
    private void hostList(int page) {
        if (Utility.isInternetAvailable(getActivity())) {
//            Utility.showCustomProgressDialog(getActivity());
            RestClient.getInstance().getApiInterface().visitor_data_response("2","CANCELLED", page,  PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN)).enqueue(new Callback<VisitordataResponse>() {
                @Override
                public void onResponse(Call<VisitordataResponse> call, Response<VisitordataResponse> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus() != null && response.body().getStatus().equals(true)) {
                            List<VisitordataResponse.Datum> b = response.body().getData();
                            if (b != null && b.size() > 0) {

                                if (page == 1)
                                    visitorownerdataListAdapter.replaceAll(b);
                                else
                                    visitorownerdataListAdapter.addAllS(b);


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
                public void onFailure(Call<VisitordataResponse> call, Throwable t) {
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
