package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class PlayerListActivity extends AppCompatActivity {

    //TODO #7: Create entities as String URL, ProgressDialog, ListView, ArrayList<PlayerModel> and PlayerListAdapter attributes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tennis Players");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //TODO: Identify ListView as findViewById

        //TODO: Call retrieveJSON() method
    }

    //TODO: Create method called retrieveJSON() method, (Follow eBook)

    //TODO: Create method called setupListView() method, (Follow eBook)

    //TODO: Create method called showProgressDialog() method, (Follow eBook)

    //TODO: Create method called removeProgressDialog() method, (Follow eBook)

}
