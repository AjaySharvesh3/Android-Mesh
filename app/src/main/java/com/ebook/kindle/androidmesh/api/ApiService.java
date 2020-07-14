package com.ebook.kindle.androidmesh.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.learn2crack.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
