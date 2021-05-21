package com.example.covid19tracker.Services;

import com.example.covid19tracker.ModelClass.CountryData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("countries")
    Call<List<CountryData>> getCountryData();


}
