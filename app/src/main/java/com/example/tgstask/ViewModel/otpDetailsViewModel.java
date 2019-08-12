package com.example.tgstask.ViewModel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.LocusId;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.tgstask.Constants;
import com.example.tgstask.Database.AppDatabase;
import com.example.tgstask.Database.ContactList;
import com.example.tgstask.Database.ContactsDao;
import com.example.tgstask.Database.MessaageLoadedList;
import com.example.tgstask.Database.MessageDao;
import com.example.tgstask.Database.MessageList;
import com.example.tgstask.Database.savedMessageList;

import java.util.List;

public class otpDetailsViewModel extends AndroidViewModel {
    private AppDatabase mDatabase;
    private MessageDao messageDao;
    private Context context;
    public LiveData<List<savedMessageList>> saveMessageListData;

    public otpDetailsViewModel(@NonNull Application application) {
            super(application);
        context = application.getApplicationContext();
        mDatabase =AppDatabase.getInstance(context);
        messageDao = mDatabase.messageDao();
        saveMessageListData = getSavedMessage();
    }

    private LiveData<List<savedMessageList>> getSavedMessage() {
        return messageDao.getMessageList();
    }

    public static class AddContactAsynkTask extends AsyncTask<MessageList,Void, Void>{
        MessageDao messageDao;

        public AddContactAsynkTask(MessageDao messageDao) {
            this.messageDao = messageDao;

        }
        @Override
        protected Void doInBackground(MessageList... messageLists) {
            Log.d(Constants.MY_TAG,messageLists[0].getMessage().toString() +"in backgroid");
            messageDao.insertContact(messageLists);
            return null;
        }

    }
}
