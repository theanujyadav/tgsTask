package com.example.tgstask.Database;

import android.os.Parcel;
import android.os.Parcelable;

public class MessaageLoadedList implements Parcelable {
    private int id;
    private String name;
    private String message;
    private String contact;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getContact() {
        return contact;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.message);
        dest.writeString(this.contact);
    }

    public MessaageLoadedList() {
    }

    protected MessaageLoadedList(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.message = in.readString();
        this.contact = in.readString();
    }

    public static final Parcelable.Creator<MessaageLoadedList> CREATOR = new Parcelable.Creator<MessaageLoadedList>() {
        @Override
        public MessaageLoadedList createFromParcel(Parcel source) {
            return new MessaageLoadedList(source);
        }

        @Override
        public MessaageLoadedList[] newArray(int size) {
            return new MessaageLoadedList[size];
        }
    };
}
