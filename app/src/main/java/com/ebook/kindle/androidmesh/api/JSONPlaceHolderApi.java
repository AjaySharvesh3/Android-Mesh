package com.ebook.kindle.androidmesh.api;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;


public interface JSONPlaceHolderApi {

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

}
