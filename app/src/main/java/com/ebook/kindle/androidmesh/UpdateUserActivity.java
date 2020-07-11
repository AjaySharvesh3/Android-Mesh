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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class UpdateUserActivity extends AppCompatActivity {


    private EditText userNameEt;
    private EditText userJobEt;
    private TextView userNameTv;
    private TextView userJobTv;
    private TextView userIDTv;
    private Button putUserBtn;

    private String URL = "https://reqres.in/api/users/2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Update User");
        actionBar.setDisplayHomeAsUpEnabled(true);


        userNameEt = findViewById(R.id.user_name_et);
        userJobEt = findViewById(R.id.user_job_et);
        userNameTv = findViewById(R.id.user_name_tv);
        userJobTv = findViewById(R.id.user_job_tv);
        userIDTv = findViewById(R.id.user_id_tv);

        putUserBtn = findViewById(R.id.put_user_btn);


        putUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putUser();
            }
        });

    }


    public void putUser() {
        final ProgressDialog loading = new ProgressDialog(UpdateUserActivity.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("name", userNameEt.getText());
            object.put("job", userJobEt.getText());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, URL, object,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            loading.dismiss();

                            Toast.makeText(UpdateUserActivity.this, "Response : " + response.toString(),
                                    Toast.LENGTH_LONG).show();

                            String[] parts = URL.split("/");
                            userIDTv.setText("ID: " + parts[5]);
                            userNameTv.setText("Name: " + response.getString("name"));
                            userJobTv.setText("Job: " + response.getString("job"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        VolleyLog.d("Error", "Error: " + error.getMessage());
                        Toast.makeText(UpdateUserActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }
}