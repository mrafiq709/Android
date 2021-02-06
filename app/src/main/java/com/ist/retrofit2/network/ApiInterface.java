package com.ist.retrofit2.network;

import com.ist.retrofit2.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/json") //Here, `json` is the PATH PARAMETER
    Call<ServerResponse> getMyIp();
}
