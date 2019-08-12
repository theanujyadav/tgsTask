package com.example.tgstask.ViewModel;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tgstask.Constants;
import com.example.tgstask.Database.ContactList;
import com.example.tgstask.Database.ContactsDao;
import com.google.gson.Gson;
import com.example.tgstask.Database.AppDatabase;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class ContactsListViewModel extends AndroidViewModel {

    private Context context;
    private Handler mHanlder;
    Gson gson = new Gson();
    ContactList[] contactList;
    private AppDatabase mDatabase;
    private ContactsDao contactsDao;

    public ContactsListViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        mDatabase =AppDatabase.getInstance(context);
        contactsDao =mDatabase.contactsDao();


     }


      public void getJsonData() {

        Thread thread = new Thread(getJsonDataThread);
        thread.setName("Json Data Fetch Thread");
        thread.start();

    }
    Runnable getJsonDataThread = new Runnable() {
        @Override
        public void run() {
            StringBuilder builder = new StringBuilder();
            try{
                InputStream inputStream = context.getAssets().open("ContactsList.json");
                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNextLine()){
                    builder.append(scanner.nextLine());
                }
                 contactList = gson.fromJson(builder.toString(),ContactList[].class);
                Log.d(Constants.MY_TAG,contactList[1].getName());
                addContacts();
            }
            catch (Exception e){
                Log.d(Constants.MY_TAG,"Exception" + e.toString());
            }

        }
    };
        public void addContacts(){
            new AddContactAsynkTask(contactsDao).execute(contactList);
        }
        public class AddContactAsynkTask extends AsyncTask<ContactList[],Void , Void>{
            ContactsDao contactsDao;

            public AddContactAsynkTask(ContactsDao contactsDao) {
                this.contactsDao = contactsDao;
            }

            @Override
            protected Void doInBackground(ContactList[]... contactLists) {
                contactsDao.insertContact(contactLists[0]);
                return null;
            }
        }




}
