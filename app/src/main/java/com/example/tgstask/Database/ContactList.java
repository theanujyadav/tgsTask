package com.example.tgstask.Database;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.tgstask.Constants;

@Entity(tableName = Constants.TABLE_NAME)
public class ContactList implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String contact;

    public ContactList(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
        dest.writeString(this.contact);
    }

    protected ContactList(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.contact = in.readString();
    }

    public static final Parcelable.Creator<ContactList> CREATOR = new Parcelable.Creator<ContactList>() {
        @Override
        public ContactList createFromParcel(Parcel source) {
            return new ContactList(source);
        }

        @Override
        public ContactList[] newArray(int size) {
            return new ContactList[size];
        }
    };
}
