package com.example.tgstask.ViewModel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.tgstask.Constants;
import com.example.tgstask.Database.AppDatabase;
import com.example.tgstask.Database.MessageDao;
import com.example.tgstask.Database.MessageList;
import com.example.tgstask.Fragments.OtpDetailsFragment;
import com.example.tgstask.MainActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ComposeScreenViewModel extends AndroidViewModel {
   public Context context;
    String sResult;
    int id;
    otpDetailsViewModel mViewModel;
    public ComposeScreenViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();


    }


    public void sendSms(int id, String number, String extraData ,String otp) {
        this.id = id;
           MyTask myTask = new MyTask();
        myTask.execute(number,extraData,otp);

    }
    class MyTask extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            sendMessage(strings[0],strings[1],strings[2]);

            return null;
        }
    }

      public void sendMessage(String number, String messageToSend, String otp) {
          try {

             Log.d(Constants.MY_TAG,"sending Message");
              // Construct data
              String apiKey = "apikey=" + URLEncoder.encode("0oJ5WhLB834-3kSQVkeKVIs20YjzXfm8eI8gplicNQ", "UTF-8");
              String message = "&message=" + URLEncoder.encode(messageToSend, "UTF-8");
              String sender = "&sender=" + URLEncoder.encode("TXTLCL", "UTF-8");
              String numbers = "&numbers=" + URLEncoder.encode(number, "UTF-8");

              // Send data
              String data = "https://api.textlocal.in/send/?" + apiKey + numbers + message + sender;
              URL url = new URL(data);
              URLConnection conn = url.openConnection();
              conn.setDoOutput(true);

              // Get the response
              BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
              String line;
              String sResult="";
              while ((line = rd.readLine()) != null) {
                  // Process line...
                  sResult=sResult+line+" ";
              }
              rd.close();
              Log.d(Constants.MY_TAG,sResult);
              saveMessage(this.id,number, messageToSend, otp);
          } catch (Exception e) {

              Log.d(Constants.MY_TAG,e.toString());
           broadCastStaus(false);

          }

      }

 public   void saveMessage(int id, String number, String message ,String otp){

//new code
      AppDatabase mDatabase;
      MessageDao messageDao;
     mDatabase = AppDatabase.getInstance(context);
     messageDao = mDatabase.messageDao();

         MessageList messageList = new MessageList(message,id,otp);
         Log.d(Constants.MY_TAG,messageList.getMessage().toString() +"in backgroid sdf sfsfdsf");
         new otpDetailsViewModel.AddContactAsynkTask(messageDao).execute(messageList);
        broadCastStaus(true);

 }
     public void broadCastStaus(Boolean status){
        Log.d(Constants.MY_TAG,"Broad Cast" + status.toString());
        Intent intent = new Intent(Constants.SERVICE_PAYLOAD);
        intent.putExtra(Constants.BroadID, status );
        LocalBroadcastManager.getInstance(context)
                .sendBroadcast(intent);


    }



}

