package com.scorpio.examples.parcelable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.scorpio.examples.R;

public class EndActivity extends AppCompatActivity {
    String TAG = "EndActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Bundle b = this.getIntent().getExtras();
        PojoObject obj = (PojoObject) b.getParcelable("userData");
        Log.i(TAG,obj.getWidth() + " " + obj.getHeight());
    }
}
