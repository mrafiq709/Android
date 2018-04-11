package com.rafiq.retrofit;

import com.rafiq.retrofit.pojo.CompanyList;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by RAFIQ on 4/11/2018.
 */

public class Rest {
    public static String BASEURL="https://api.myjson.com";

    public interface api_carCompanies {

        @GET("/bins/1c68pj") // specify the sub url for the base url
        void getData(Callback<CompanyList> response); // will give you the json data
    }
}
