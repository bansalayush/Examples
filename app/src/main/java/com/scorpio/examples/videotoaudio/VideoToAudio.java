package com.scorpio.examples.videotoaudio;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpegLoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.netcompss.loader.LoadJNI;
import com.scorpio.examples.R;

import java.io.File;
import java.io.FilenameFilter;

public class VideoToAudio extends AppCompatActivity {

    /*FFmpeg ffmpeg;
    *//**//*
    String[] cmd = {"-y -i /storage/emulated/0/DCIM/Camera/VID_19700114_202244.mp4 -strict experimental -vn -ar 44100 -ac 2 -ab 256k -f /storage/emulated/0/firstAudioTry.mp3", ""};
    */ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_to_audio);

        File f = new File("/storage/emulated/0/DCIM/Camera");
        System.out.println("External path -- " + f.getAbsolutePath());

        String files[] = f.list();
        for (int i = 0; i < files.length; i++) {
            System.out.println("Files in Videos Directory" + files[i]);

        }

        pDialog = new ProgressDialog(VideoToAudio.this);
        new OutAsync().execute();

    }

    class OutAsync extends AsyncTask {



        @Override
        protected Object doInBackground(Object[] objects) {
            LoadJNI vk = new LoadJNI();
            try {
                String workFolder = "/storage/emulated/0/DCIM/Camera";
                String[] complexCommand = {"ffmpeg", "-y", "-i",
                        "/storage/emulated/0/DCIM/Camera/VID_20161218_185300.mp4" ,"-strict",
                        "experimental" ,"-vn", "-ar" ,"44100" ,"-ac", "2" ,"-ab" ,"256k" ,"-f" ,"mp3"
                        ,"/storage/emulated/0/output.mp3"
                };
                vk.run(complexCommand , workFolder , getApplicationContext());
                Log.i("test", "ffmpeg4android finished successfully");
            } catch (Throwable e) {
                Log.e("test", "vk run exception.", e);
            }
            return null;
        }
    }
}