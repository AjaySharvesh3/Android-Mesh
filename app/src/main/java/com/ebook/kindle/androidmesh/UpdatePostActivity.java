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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UpdatePostActivity extends AppCompatActivity {


    private TextView textViewResult;
    private EditText titleEt;
    private EditText bodyEt;
    private Button updateBtn;
    private JSONPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Update Post");
        actionBar.setDisplayHomeAsUpEnabled(true);

        titleEt = findViewById(R.id.title_et);
        bodyEt = findViewById(R.id.body_et);
        updateBtn = findViewById(R.id.post_btn);

        textViewResult = findViewById(R.id.text_view_result);


        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        jsonPlaceHolderApi = retrofit.create(JSONPlaceHolderApi.class);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePost();
            }
        });

    }

    private void updatePost() {
        String title = titleEt.getText().toString();
        String body = bodyEt.getText().toString();

        PostModel post = new PostModel(29, title, body);
        Call<PostModel> call = jsonPlaceHolderApi.putPost(30, post);

        final ProgressDialog loading = new ProgressDialog(UpdatePostActivity.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

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
                content += "Body: " + postResponse.getText() + "\n";
                textViewResult.setText(content);
            }
            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                loading.dismiss();
                textViewResult.setText(t.getMessage());
            }
        });
    }

}