package com.ebook.kindle.androidmesh;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)   //LINE NO. 12
                    .addConverterFactory(GsonConverterFactory.create())   //LINE NO. 13
                    .build();
        }
        return retrofit;
    }

}
