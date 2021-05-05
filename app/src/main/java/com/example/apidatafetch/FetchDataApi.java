package com.example.apidatafetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchDataApi {

    private static String baseurl = "https://jsonplaceholder.typicode.com/";

   static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static retrofit2.Retrofit fetchData;

    public static retrofit2.Retrofit getFetchData()
    {
        if(fetchData == null)
        {
             fetchData= new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return fetchData;
    }

}
