package com.ebook.kindle.androidmesh.api;

import com.ebook.kindle.androidmesh.model.PostModel;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {

    @FormUrlEncoded
    @POST("posts")
    Call<PostModel> createPost(@FieldMap Map<String, String> fields);

}
