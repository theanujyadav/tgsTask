package com.example.tgstask.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tgstask.Constants;
import com.example.tgstask.Database.AppDatabase;
import com.example.tgstask.Database.ContactList;
import com.example.tgstask.Database.ContactsDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ContactDetailsViewModel extends AndroidViewModel {
    private AppDatabase mDatabase;
    private ContactsDao contactsDao;
    Context context;
    private Executor mExecutor = Executors.newSingleThreadExecutor();
    ContactList contactList;
    LiveData<String> otp;

   public MutableLiveData<ContactList> liveContactList = new MutableLiveData<>() ;

    public ContactDetailsViewModel(@NonNull Application application) {
        super(application);
        mDatabase =AppDatabase.getInstance(context);
        contactsDao= mDatabase.contactsDao();
        liveContactList.postValue(contactList);

    }
public void LoadContactDetails(final int id){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    contactList = contactsDao.getContactById(id);
                    liveContactList.postValue(contactList);
                }catch (Exception e){
                    Log.d(Constants.MY_TAG,e.toString());
                }



            }
        });
}


    public void generateOtp() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }
}
