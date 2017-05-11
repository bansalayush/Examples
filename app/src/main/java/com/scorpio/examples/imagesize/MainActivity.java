package com.scorpio.examples.imagesize;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scorpio.examples.R;

import java.io.File;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFileChooser();
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int dataSize=0;
        if (requestCode == 2 && resultCode == RESULT_OK)
        {
            Uri uri  = data.getData();
            String scheme = uri.getScheme();
            System.out.println("Scheme type " + scheme);
            if(scheme.equals(ContentResolver.SCHEME_CONTENT))
            {
                try {
                    InputStream fileInputStream=getApplicationContext().getContentResolver().openInputStream(uri);
                    dataSize = fileInputStream.available();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("File size in bytes"+dataSize);

            }
            else if(scheme.equals(ContentResolver.SCHEME_FILE))
            {
                String path = uri.getPath();
                try {
                    f = new File(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("File size in bytes"+f.length());
            }

        }
    }
}
