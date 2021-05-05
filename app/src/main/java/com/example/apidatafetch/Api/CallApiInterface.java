package com.example.apidatafetch.Api;


import com.example.apidatafetch.ModelClasses.FetchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallApiInterface
{
    @GET("photos")
    Call<List<FetchResponse>> getData();

}
