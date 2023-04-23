package com.tgate.gate.api;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class DefaultCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        Log.e("CT_", "--->   " + " Status code : " + response.code());
        if (/*response.isSuccessful()*/response.code() == 200) {
            Log.e("CT_", "--->   " + " RESPONSE = " + String.valueOf(response.body()));
            //Log.e("CT_", "--->   " + " Response Message : " + response.raw().message());
            onSuccess(response.body(), response.code());
        } else {
            Log.e("CT_", "--->   " + " Response NOT OK : " + response.raw().message());
            Log.e("CT_", "--->   " + " Response Error Body : " + getErrorBody(response));
            onError(getErrorBody(response), response.code());
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        Log.e("CT_", "--->   " + " ON_Failure = " + t.toString());
        Log.e("CT_", "--->   " + " ON_Failure Localize Message = " + t.getLocalizedMessage());
        Log.e("CT_", "--->   " + " ON_Failure Message = " + t.getMessage());
        if (!call.isCanceled())
            onError(null, -1);
    }

    public abstract void onSuccess(final T response, int code);

    public abstract void onError(@Nullable String body, int code);

    private String getErrorBody(Response<T> response) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
