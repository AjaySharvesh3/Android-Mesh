package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



public class AddUserActivity extends AppCompatActivity {

    /* TODO #4: Create entities like 2 EditTexts (For Name & Job)
     * One Button For Posting data
     * Three TextViews entities for showing response data
     * Finally, RESTful API URL.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add User");
        actionBar.setDisplayHomeAsUpEnabled(true);


        // TODO #5: Identify by findViewById for 2 EditTexts, 1 button & 3 TextViews




        //TODO #7: Create setOnClickListener() for button and call postUser() method

    }


    //TODO #6: Create method called postUser(), that performs POSTing data in Volley (Refer Book)


}