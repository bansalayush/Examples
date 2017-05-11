package com.scorpio.examples.database;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;

import com.scorpio.examples.R;
import com.scorpio.examples.videotoaudio.VideoToAudio;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DecimalFormat;

public class DatabaseActivity extends AppCompatActivity {
    String list[];
    SQLAdapter sqlAdapter;
    DecimalFormat df;
    ProgressDialog pDialog;
    String path;


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
        setContentView(R.layout.activity_database);
        pDialog = new ProgressDialog(DatabaseActivity.this);

        df = new DecimalFormat("#0.00");
        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        sqlAdapter = new SQLAdapter(getApplicationContext());
        path = path + "/DCIM/Camera/";
        File f = new File(path);
        list = f.list(mp4Filter);
        System.out.println(list.length);

        new MSyncTask().execute();
    }

    class MSyncTask extends AsyncTask {
        MediaMetadataRetriever metaData = new MediaMetadataRetriever();

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Converting...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            pDialog.dismiss();
            sqlAdapter.openToRead();
            startActivity(new Intent(DatabaseActivity.this,QueryActivity.class));
            super.onPostExecute(o);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            sqlAdapter.openToWrite();
            File f ;
            for(String s : list){

                f = new File(path+s);

                metaData.setDataSource(f.getAbsolutePath());
                String time = metaData.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                long timeInSec = Long.parseLong(time) / 1000L;
                String duration = DateUtils.formatElapsedTime(timeInSec);

                double size = (double)(f.length())/(1000*1000);
                sqlAdapter.insert(s.substring(0,s.length()-4),f.getAbsolutePath(),df.format(size),duration);

            }
            return null;
        }
    }
}
