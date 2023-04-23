package com.tgate.gate.api;


public interface DataResponseListener {
    public void onData_SuccessfulResponse(String stringResponse);

    void onData_FailureResponse();
}