package com.scorpio.examples.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Ayush Bansal on 10-05-2017.
 */

public class SQLAdapter {



    private SQLiteDatabase sqlDB;
    public SQLAdapter adapter;
    private SQLHelper sqlHelper;
    private Context c;

    public SQLAdapter(Context c){
        this.c=c;
    }

//    /*public static SQLAdapter getInstance(Context context){
//        if(adapter==null){
//            adapter = new SQLAdapter(context);
//        }
//        return adapter;
//    }*/

    public Cursor query(String q){
        return sqlDB.rawQuery(q,null);
    }




    public void openToRead() throws SQLException {
        sqlHelper = new SQLHelper(c);
        sqlDB = sqlHelper.getReadableDatabase();
    }

    public void openToWrite() throws SQLException{
        sqlHelper = new SQLHelper(c);
        sqlDB = sqlHelper.getWritableDatabase();
    }

    public long insert(String videoName,String videoPath,String size,String duration){
        ContentValues cv = new ContentValues();
        cv.put(MContract.VideoEntry.COLOUMN_VIDEO_NAME,videoName);
        cv.put(MContract.VideoEntry.COLOUMN_VIDEO_PATH,videoPath);
        cv.put(MContract.VideoEntry.COLOUMN_VIDEO_SIZE,size);
        cv.put(MContract.VideoEntry.COLOUMN_VIDEO_DURATION,duration);
        long val = sqlDB.insert(MContract.VideoEntry.TABLE_NAME,null,cv);
        return  val;
    }

    public int delete(String videoPath){
        int number = sqlDB.delete(MContract.VideoEntry.TABLE_NAME, MContract.VideoEntry.COLOUMN_VIDEO_PATH+"="+videoPath,null);
        return number;
    }

    class SQLHelper extends SQLiteOpenHelper {

        private static final String CREATE_VIDEO_TABLE = "CREATE TABLE " + MContract.VideoEntry.TABLE_NAME +
                "( "
                + MContract.VideoEntry._ID + " INTEGER KEY,"
                + MContract.VideoEntry.COLOUMN_VIDEO_NAME + " TEXT,"
                + MContract.VideoEntry.COLOUMN_VIDEO_PATH + " TEXT PRIMARY KEY,"
                + MContract.VideoEntry.COLOUMN_VIDEO_DURATION + " VARCHAR(10),"
                + MContract.VideoEntry.COLOUMN_VIDEO_SIZE + " VARCHAR(10) )";
        private static final String DB_NAME = "vid2aud.db";

        public SQLHelper(Context context) {
            super(context,DB_NAME,null,1/*version*/);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_VIDEO_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
