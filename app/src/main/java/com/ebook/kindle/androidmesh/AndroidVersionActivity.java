package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class AndroidVersionActivity extends AppCompatActivity {

    //TODO #5: Create sub-package & name it as model and create AndroidVersion.java under model package (Follow Book)
    //TODO #6: Create JSONResponse.java under model package (Follow Book)
    //TODO #7: Create sub-package & name it as api and create an Interface and name it as ApiInterface under api package (Follow Book)
    //TODO #8: Create sub-package & name it as adapter and create an Java class and name it as AndroidAdapter under adapter package (Follow Book)

    //TODO #9: Create entities & create method initViews(), loadJSON() (Follow Book).


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_version);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Android Versions");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


}