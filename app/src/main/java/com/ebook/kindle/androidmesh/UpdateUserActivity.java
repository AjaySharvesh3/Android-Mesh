package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class UpdateUserActivity extends AppCompatActivity {

    /* TODO #4: Create entities like 2 EditTexts (For Name & Job)
     * One Button For Posting data
     * Three TextViews entities for showing response data
     * Finally, RESTful API URL.
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Update User");
        actionBar.setDisplayHomeAsUpEnabled(true);


        // TODO #5: Identify by findViewById for 2 EditTexts, 1 button & 3 TextViews



        //TODO #7: Create setOnClickListener() for button and call putUser() method

    }


    //TODO #6: Create method called putUser(), that performs PUT-ing data in Volley (Refer Book)
}