package com.example.tgstask.Database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.tgstask.Constants;

@Entity(tableName = Constants.MESSAGE_LIST)
public class MessageList implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MessageList createFromParcel(Parcel in) {
            return new MessageList(in);
        }

        public MessageList[] newArray(int size) {
            return new MessageList[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    private int idMessage;
    private String message;

    private String otp;

    @ForeignKey(entity = ContactList.class,
           parentColumns = "id",
           childColumns = "senderId",
           onDelete = ForeignKey.CASCADE)
     private int senderId;


    public MessageList(int idMessage, String message, int senderId, String otp) {
        this.idMessage = idMessage;
        this.message = message;
        this.senderId = senderId;
        this.otp = otp;
    }

    @Ignore
    public MessageList(String message, int senderId, String otp) {
        this.message = message;
        this.senderId = senderId;
        this.otp = otp;
    }

    public MessageList(Parcel in) {
        this.idMessage = in.readInt();
        this.message = in.readString();
        this.otp = in.readString();
        this.senderId = in.readInt();
    }

    public int getIdMessage() {
        return idMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getOtp() {
        return otp;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idMessage);
        dest.writeString(this.message);
        dest.writeString(this.otp);
        dest.writeInt(this.senderId);
    }
}
