package com.ebook.kindle.androidmesh;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    @Override
    public void onTextClicked(String text) {
        if (text.equals("Java")) {
            Intent i = new Intent(MainActivity.this, PlayerListActivity.class);
            startActivity(i);
        }

        /*if (text.equals("Kotlin")) {
            Intent i = new Intent(MainActivity.this, AsyncTaskKotlinActivity.class);
            startActivity(i);
        }*/

    }

}