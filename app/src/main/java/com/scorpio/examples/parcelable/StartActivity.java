package com.scorpio.examples.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scorpio.examples.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        PojoObject obj = new PojoObject(512,1280);
        Intent i = new Intent(StartActivity.this,EndActivity.class);
        Bundle b = new Bundle();
        b.putParcelable("userData",obj);
        i.putExtras(b);
        startActivity(i);
    }
}
