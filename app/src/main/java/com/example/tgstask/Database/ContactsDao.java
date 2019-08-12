package com.example.tgstask.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tgstask.Constants;

import java.util.List;

@Dao
public interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContact(ContactList[] contactList);

    @Query(" SELECT * FROM " + Constants.TABLE_NAME )
  LiveData<List<ContactList>> getAllContacts();


    @Query(" SELECT * FROM " + Constants.TABLE_NAME  + " WHERE  id= :id " )
        ContactList getContactById( int id);

}

