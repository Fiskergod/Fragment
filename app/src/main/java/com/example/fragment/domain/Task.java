package com.example.fragment.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class Task implements Parcelable {

    @DrawableRes
    private final int drawRes;

    @StringRes
    private final int titleRes;

    public Task(int drawRes, int titleRes) {
        this.drawRes = drawRes;
        this.titleRes = titleRes;
    }

    protected Task(Parcel in) {
        drawRes = in.readInt();
        titleRes = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public int getDrawRes() {
        return drawRes;
    }

    public int getTitleRes() {
        return titleRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(drawRes);
        dest.writeInt(titleRes);
    }
}
