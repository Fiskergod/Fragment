package com.example.fragment.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

public class Note implements Parcelable {

    private final String id;

    private final String title;

    private final String imageUrl;

    private final Date createdAt;

    public Note(String id, String title, String imageUrl, Date createdAt) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    protected Note(Parcel in) {
        id = in.readString();
        title = in.readString();
        imageUrl = in.readString();
        createdAt = (Date) in.readSerializable();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) &&
                Objects.equals(title, note.title) &&
                Objects.equals(imageUrl, note.imageUrl) &&
                Objects.equals(createdAt, note.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imageUrl, createdAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeSerializable(createdAt);
    }
}
