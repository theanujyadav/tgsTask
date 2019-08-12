package com.example.tgstask.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tgstask.Constants;

@Database(entities = {ContactList.class,MessageList.class},version = 1,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public static volatile AppDatabase instance;

    private static final Object LOCK = new Object();

    public abstract ContactsDao contactsDao();
    public abstract MessageDao messageDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, Constants.DATABASE_NAME).build();
                }
            }
            }
        return instance;
    }


}