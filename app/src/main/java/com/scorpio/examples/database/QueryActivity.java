package com.scorpio.examples.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scorpio.examples.R;

public class QueryActivity extends AppCompatActivity {
    SQLAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        adapter = new SQLAdapter(getApplicationContext());
        adapter.openToRead();
        Cursor c = adapter.query("select * from video;");
        while (c.moveToNext()) {
            System.out.println(c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3) + " " + c.getString(4));
        }
    }
}
