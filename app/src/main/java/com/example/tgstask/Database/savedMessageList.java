package com.example.tgstask.Database;

import android.os.Parcel;
import android.os.Parcelable;

public class savedMessageList implements Parcelable {
    private String name;
    private String contact;
    private String message;
    private String otp;

    public savedMessageList(String name, String contact, String message, String otp) {
        this.name = name;
        this.contact = contact;
        this.message = message;
        this.otp = otp;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getMessage() {
        return message;
    }

    public String getOtp() {
        return otp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.message);
        dest.writeString(this.contact);
        dest.writeString(this.otp);
    }

    protected savedMessageList(Parcel in) {
        this.name = in.readString();
        this.message = in.readString();
        this.contact = in.readString();
        this.otp = in.readString();
    }

    public static final Parcelable.Creator<savedMessageList> CREATOR = new Parcelable.Creator<savedMessageList>() {
        @Override
        public savedMessageList createFromParcel(Parcel source) {
            return new savedMessageList(source);
        }

        @Override
        public savedMessageList[] newArray(int size) {
            return new savedMessageList[size];
        }
    };
}
