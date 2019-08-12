package com.example.tgstask.ViewModel;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tgstask.Constants;
import com.example.tgstask.Database.AppDatabase;
import com.example.tgstask.Database.ContactList;
import com.example.tgstask.Database.ContactsDao;

import java.util.List;

public class FragContactListViewModel extends AndroidViewModel {
    public LiveData<List<ContactList>> mContactList;
    private AppDatabase mDatabase;
    private ContactsDao contactsDao;
    Context context;
    public FragContactListViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
        mDatabase = AppDatabase.getInstance(context);
        contactsDao= mDatabase.contactsDao();

mContactList = getAllData();
}

    private LiveData<List<ContactList>> getAllData() {
        return contactsDao.getAllContacts();
    }


}
