package com.tgate.gate.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralRetrofit {

    private final Call<JsonElement> call;
    private final Object params;
    private DataResponseListener dataResponseListener;

    public GeneralRetrofit(Call<JsonElement> call, Object params, DataResponseListener dataResponseListener) {
        this.call = call;
        this.params = params;
        this.dataResponseListener = dataResponseListener;
    }

   public Call<JsonElement> call() {
        Log.v("@@Inside Class", "--->   " + " API URL = " + call.request().url());
        if (params != null && call.request().body() != null) {
                Log.v("@@Inside Class", "--->   " + " Passing Params = " + new Gson().toJson(params));
        }
        call.enqueue(postCall);
        return call;
    }

    private Callback<JsonElement> postCall = new Callback<JsonElement>() {
        @Override
        public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
            Log.v("@@Inside Class", "--->   " + " Status code : " + response.code());
            if (response.code() != 200) {
                Log.v("@@Inside Class", "--->   " + " Response NOT OK : " + response.raw().message());
            }
            if (response.body() != null) {
                Log.v("@@Inside Class", "--->   " + " RESPONSE = " + String.valueOf(response.body()));
                String responseString = String.valueOf(response.body());
                if (dataResponseListener != null)
                    dataResponseListener.onData_SuccessfulResponse(responseString);
            } else {
                if (dataResponseListener != null)
                    dataResponseListener.onData_FailureResponse();
            }

        }

        @Override
        public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
            Log.v("@@Inside Class", "--->   " + " ON_Failure = " + t.toString());
            Log.v("@@Inside Class", "--->   " + " ON_Failure Localize Message = " + t.getLocalizedMessage());
            Log.v("@@Inside Class", "--->   " + " ON_Failure Message = " + t.getMessage());
            if (!call.isCanceled())
                if (dataResponseListener != null)
                    dataResponseListener.onData_FailureResponse();
        }
    };
    

}