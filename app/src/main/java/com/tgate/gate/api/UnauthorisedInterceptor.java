package com.tgate.gate.api;

import android.content.Context;
import android.content.Intent;


import com.tgate.gate.Activity.LoginActivity;
import com.tgate.gate.ApplicationController;
import com.tgate.gate.util.PrefsManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class UnauthorisedInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() == 401) {
            Context context = ApplicationController.getAppContext();
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            PrefsManager.removeAllKey();
        }
        return response;
    }
}