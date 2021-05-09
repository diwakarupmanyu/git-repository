package com.example.apidatafetch.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchDataApi {


    private static String baseurl = "https://jsonplaceholder.typicode.com/";

    private static Retrofit fetchData;

    public static Retrofit getFetchData()
    {
        if(fetchData == null)
        {
             fetchData= new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return fetchData;
    }

}
