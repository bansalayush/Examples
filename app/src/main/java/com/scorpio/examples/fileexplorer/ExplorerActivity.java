package com.scorpio.examples.fileexplorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.scorpio.examples.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExplorerActivity extends AppCompatActivity {
    List<File> directories;
    List<File> videos;
    FilenameFilter directoryFilter = new FilenameFilter() {
        @Override
        public boolean accept(File file, String s) {
            return file.isDirectory();
        }
    };
    FilenameFilter mp4Filter = new FilenameFilter() {
        @Override
        public boolean accept(File file, String s) {
            String lowercase = s.toLowerCase();
            if (lowercase.endsWith(".mp4"))
                return true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer);
        Spinner spinner = (Spinner) findViewById(R.id.directoryList);
        directories = new ArrayList<>();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("-----------" + i);
                System.out.println(adapterView.getItemAtPosition(i).toString());
                getVideoList(adapterView,i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void getVideoList(AdapterView<?> adapterView,int i) {
        File ff = new File(adapterView.getItemAtPosition(i).toString());
        directories.add(ff);
        videos = new ArrayList<>();
        while (directories.size() != 0) {
            File f = directories.remove(0);
            if (f.isDirectory()) {
                if (f.list().length > 0) {
                    List<File> directoryList = Arrays.asList(f.listFiles(directoryFilter));
                    List<File> videoList = Arrays.asList(f.listFiles(mp4Filter));
                    directories.addAll(directoryList);
                    videos.addAll(videoList);
                }
            }

        }
        for (File s : videos) {
            System.out.println("hi -> " + s.getAbsolutePath());
        }
    }
}
