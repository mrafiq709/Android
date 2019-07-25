package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetDataService {

    @Headers("x-api-key:"+ BuildConfig.API_KEY)
    @GET("/www/WeeklyReportAPI/public/index.php/api/v1/user/list")
    Call<UserListModel> getUserList();
}
