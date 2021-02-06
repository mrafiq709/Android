package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetDataService {

    @Headers("x-api-key:"+ BuildConfig.API_KEY)
    @GET("api_link_without_base_url")
    Call<UserListModel> getUserList();
}
