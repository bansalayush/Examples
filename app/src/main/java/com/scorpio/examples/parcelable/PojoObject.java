package com.scorpio.examples.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ayush Bansal on 19-04-2017.
 */

public class PojoObject implements Parcelable {

    private int height;
    private int width;

    public PojoObject(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public PojoObject(){}

    protected PojoObject(Parcel in) {
        this.height = in.readInt();
        this.width = in.readInt();
    }



    public static final Creator<PojoObject> CREATOR = new Creator<PojoObject>() {
        @Override
        public PojoObject createFromParcel(Parcel in) {
            return new PojoObject(in);
        }

        @Override
        public PojoObject[] newArray(int size) {
            return new PojoObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(height);
        parcel.writeInt(width);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
