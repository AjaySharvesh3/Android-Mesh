package com.ebook.kindle.androidmesh;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener  {

    private static final String TAG = MainActivity.class.getName();
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOpenBottomSheet = findViewById(R.id.volley_button);
        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialogAsyncTask = new BottomSheetDialog();
                bottomSheetDialogAsyncTask.show(getSupportFragmentManager(), "VolleyBottomSheet");
            }
        });
    }

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