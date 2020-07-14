package com.ebook.kindle.androidmesh.api;

import com.ebook.kindle.androidmesh.model.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("android/jsonandroid")
    Call<JSONResponse> getJSON();

}
