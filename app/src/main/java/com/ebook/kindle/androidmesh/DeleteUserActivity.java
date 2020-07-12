package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class DeleteUserActivity extends AppCompatActivity {

    /* TODO #4: Create entities like 1 EditText (For User ID)
     * One Button For Deleting data
     * One TextView for showing Status Code
     * Finally, RESTful API URL.
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Delete User");
        actionBar.setDisplayHomeAsUpEnabled(true);


        // TODO #5: Identify by findViewById for 1 EditText, 1 button & 1 TextView


        //TODO #7: Create setOnClickListener() for button and call deleteUser() method


    }


    //TODO #6: Create method called deleteUser(), that performs DELETE-ing data in Volley (Refer Book)


}