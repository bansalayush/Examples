package com.scorpio.examples.videotoaudio;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.scorpio.examples.R;

public class VideoToAudio extends AppCompatActivity {

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_to_audio);

        pDialog = new ProgressDialog(VideoToAudio.this);
        new OutAsync().execute();

    }

    class OutAsync extends AsyncTask {
        String mssg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setMessage("Converting...");
            //pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            //pDialog.setProgress(0);
            pDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            String[] complexCommand = {"-y", "-i",
                    "/storage/emulated/0/DCIM/Camera/VID_20161229_085151.mp4","-strict",
                    "experimental" ,"-vn", "-ar" ,"44100" ,"-ac", "2" ,"-ab" ,"256k" ,"-f" ,"mp3"
                    ,"/storage/emulated/0/output3.mp3"
            };

            FFmpeg ffmpeg = FFmpeg.getInstance(getApplicationContext());
            try {
                ffmpeg.loadBinary(new LoadBinaryResponseHandler() {

                    @Override
                    public void onStart() {}

                    @Override
                    public void onFailure() {}

                    @Override
                    public void onSuccess() {}

                    @Override
                    public void onFinish() {}
                });
            } catch (FFmpegNotSupportedException e) {
                // Handle if FFmpeg is not supported by device
                e.printStackTrace();
            }

            FFmpeg ffmpeg2 = FFmpeg.getInstance(getApplicationContext());
            try {
                // to execute "ffmpeg -version" command you just need to pass "-version"
                ffmpeg2.execute(complexCommand, new ExecuteBinaryResponseHandler() {

                    @Override
                    public void onStart() {
                        System.out.println("onStart");
                    }

                    @Override
                    public void onProgress(final String message) {
                        System.out.println("onProgress"+message);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pDialog.setMessage(message);
                                pDialog.show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(String message) {
                        System.out.println("onFailure" + message);


                    }

                    @Override
                    public void onSuccess(String message) {
                        mssg = message;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pDialog.setMessage(mssg);
                                pDialog.show();
                            }
                        });

                    }

                    @Override
                    public void onFinish() {
                        System.out.println("onFinish");
                        pDialog.dismiss();

                    }
                });
            } catch (FFmpegCommandAlreadyRunningException e) {
                // Handle if FFmpeg is already running
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            pDialog.dismiss();
        }
    }
}
