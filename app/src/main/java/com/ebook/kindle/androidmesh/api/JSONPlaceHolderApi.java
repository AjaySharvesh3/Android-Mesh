package com.ebook.kindle.androidmesh.api;

import com.ebook.kindle.androidmesh.model.PostModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {

    @PUT("posts/{id}")
    Call<PostModel> putPost(@Path("id") int id, @Body PostModel post);

}
