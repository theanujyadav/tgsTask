package com.example.tgstask.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContact(MessageList[] messageLists);

    @Query("SELECT Contacts_List.name, Contacts_List.contact, Message_data.message, Message_data.otp FROM Contacts_List INNER JOIN Message_data ON Contacts_List.id = Message_data.senderId")
    LiveData<List<savedMessageList>> getMessageList();
}
