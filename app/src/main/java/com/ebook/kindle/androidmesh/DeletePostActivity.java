package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class DeletePostActivity extends AppCompatActivity {


    /** TODO #6: Create entities for the layout and identify by findViewById
     * Create a method called deletePost() and do DELETE call (Follow Book)
     * */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Delete Post");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

}