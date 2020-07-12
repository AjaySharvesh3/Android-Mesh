package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class DeleteUserActivity extends AppCompatActivity {


    private EditText userIDEt;
    private TextView statusCodeTv;
    private Button deleteUserBtn;

    private String deleteURL = "https://reqres.in/api/users/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Delete User");
        actionBar.setDisplayHomeAsUpEnabled(true);

        userIDEt = findViewById(R.id.user_id_et);
        statusCodeTv = findViewById(R.id.status_code_tv);
        deleteUserBtn = findViewById(R.id.delete_user_btn);


        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });

    }


    public void deleteUser() {
        final ProgressDialog loading = new ProgressDialog(DeleteUserActivity.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        deleteURL += userIDEt.getText();

        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, deleteURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(DeleteUserActivity.this, "Success", Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loading.dismiss();
                        Toast.makeText(DeleteUserActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        )
        {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                statusCodeTv.setText("Response Status Code: " + response.statusCode);
                return super.parseNetworkResponse(response);
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(deleteRequest);
    }

}