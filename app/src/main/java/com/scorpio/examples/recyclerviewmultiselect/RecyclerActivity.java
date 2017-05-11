package com.scorpio.examples.recyclerviewmultiselect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scorpio.examples.R;

import java.io.File;

public class RecyclerActivity extends AppCompatActivity {

    private ActionMode actionMode;
    File externalStorageDirectoryPath;
    File [] mp4Path;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

       // recyclerView.setAdapter();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


}
