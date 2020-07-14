package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostActivity extends AppCompatActivity {

    private TextView mResponseTv;
    private EditText titleEt;
    private EditText bodyEt;
    private Button submitBtn;
    private ApiService mAPIService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Publish Post");
        actionBar.setDisplayHomeAsUpEnabled(true);


        titleEt = (EditText) findViewById(R.id.et_title);
        bodyEt = (EditText) findViewById(R.id.et_body);
        submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);


        mAPIService = ApiUtils.getAPIService();


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();
                String body = bodyEt.getText().toString().trim();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    sendPost(title, body);
                }
            }
        });

    }


    public void sendPost(String title, String body) {
        final ProgressDialog loading = new ProgressDialog(PostActivity.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        mAPIService.savePost(title, body, 1).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {

                if(response.isSuccessful()) {
                    loading.dismiss();
                    showResponse(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(PostActivity.this, "Unable to submit post to API.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }

}