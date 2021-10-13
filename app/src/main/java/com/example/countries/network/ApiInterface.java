package com.example.countries.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("rest/v2/region/asia")//endpoint

    Call<ResponseBody> countries();
}
