package com.ebook.kindle.androidmesh;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    // Instantiated Button View
    private Button btnRequest;

    //Instantiated RequestQueue, StringRequest
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://run.mocky.io/v3/9c206e41-1ffb-4c8d-88cd-05c1cfda18ac";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Here we find in the View of Button, that have to be clicked
        btnRequest = (Button) findViewById(R.id.buttonRequest);


        //Button OnClick is Implemented, while onClick() we are calling the function called sendAndRequestResponse()
        btnRequest.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v){

                  sendRequestResponse();

              }
        });

    }



    //This the method where we do operation on Toasting the response in our App
    private void sendRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized, with Params that we are passing our RESTful API url in here, with GET Call
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //display the response on screen
                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }

}