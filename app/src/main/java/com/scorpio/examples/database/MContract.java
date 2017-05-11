package com.scorpio.examples.database;

import android.provider.BaseColumns;

/**
 * Created by Ayush Bansal on 10-05-2017.
 */

public final class MContract {

    private MContract(){}

    public static class VideoEntry implements BaseColumns{
        public static final String TABLE_NAME = "video";
        public static final String COLOUMN_VIDEO_NAME = "videoname";
        public static final String COLOUMN_VIDEO_PATH = "videopath";
        public static final String COLOUMN_VIDEO_DURATION = "videoduration";//text in HH:MM:SS format
        public static final String COLOUMN_VIDEO_SIZE = "videosize";//text in MB format

    }
}
