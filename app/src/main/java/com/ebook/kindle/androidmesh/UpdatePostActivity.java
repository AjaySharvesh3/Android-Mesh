package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class UpdatePostActivity extends AppCompatActivity {


    /** TODO #6: Create entities for the layout and identify by findViewById
     * Create a method called updatePost() and do PUT call (Follow Book)
     * */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Update Post");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

}