package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ebook.kindle.androidmesh.api.JSONPlaceHolderApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DeletePostActivity extends AppCompatActivity {

    private TextView textViewResult;
    private EditText userIdEt;
    private Button deleteBtn;
    private JSONPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Delete Post");
        actionBar.setDisplayHomeAsUpEnabled(true);

        userIdEt = findViewById(R.id.user_id_et);
        deleteBtn = findViewById(R.id.delete_btn);

        textViewResult = findViewById(R.id.text_view_result);


        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        jsonPlaceHolderApi = retrofit.create(JSONPlaceHolderApi.class);


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePost();
            }
        });

    }


    private void deletePost() {
        int userId = Integer.parseInt(userIdEt.getText().toString());

        Call<Void> call = jsonPlaceHolderApi.deletePost(userId);

        final ProgressDialog loading = new ProgressDialog(DeletePostActivity.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        call.enqueue(new Callback<Void>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                loading.dismiss();
                textViewResult.setText("Status Code: " + response.code());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                loading.dismiss();
                textViewResult.setText(t.getMessage());
            }
        });
    }

}