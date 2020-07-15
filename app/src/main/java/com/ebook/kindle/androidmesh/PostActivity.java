package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ebook.kindle.androidmesh.api.JSONPlaceHolderApi;
import com.ebook.kindle.androidmesh.model.PostModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostActivity extends AppCompatActivity {

    /** TODO #6: Create entities for the layout and identify by findViewById
     * Create a method called createPost() and do POST call (Follow Book)
     * */
    private TextView textViewResult;
    private EditText userIDEt;
    private EditText titleEt;
    private Button postBtn;
    private JSONPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Publish Post");
        actionBar.setDisplayHomeAsUpEnabled(true);

        userIDEt = findViewById(R.id.user_id_et);
        titleEt = findViewById(R.id.title_et);
        postBtn = findViewById(R.id.post_btn);

        textViewResult = findViewById(R.id.text_view_result);


        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        jsonPlaceHolderApi = retrofit.create(JSONPlaceHolderApi.class);


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPost();
            }
        });

    }

    private void createPost() {
        Map<String, String> fields = new HashMap<>();
        fields.put("userId", userIDEt.getText().toString());
        fields.put("title", titleEt.getText().toString());

        final ProgressDialog loading = new ProgressDialog(PostActivity.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        Call<PostModel> call = jsonPlaceHolderApi.createPost(fields);
        call.enqueue(new Callback<PostModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                loading.dismiss();

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                PostModel postResponse = response.body();
                String content = "";
                content += "Status code: " + response.code() + "\n\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                textViewResult.setText(content);
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                loading.dismiss();
                textViewResult.setText("Error: " + t.getMessage());
            }
        });
    }

}